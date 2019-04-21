package com.facebook.stetho.inspector.network;

import com.facebook.stetho.common.ExceptionUtil;
import com.facebook.stetho.common.Util;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.GZIPInputStream;

class GunzippingOutputStream extends FilterOutputStream {
    private static final ExecutorService sExecutor = Executors.newCachedThreadPool();
    private final Future<Void> mCopyFuture;

    private static class GunzippingCallable implements Callable<Void> {
        private final InputStream mIn;
        private final OutputStream mOut;

        public GunzippingCallable(InputStream in, OutputStream out) {
            this.mIn = in;
            this.mOut = out;
        }

        public Void call() throws IOException {
            GZIPInputStream in = new GZIPInputStream(this.mIn);
            try {
                Util.copy(in, this.mOut, new byte[1024]);
                return null;
            } finally {
                in.close();
                this.mOut.close();
            }
        }
    }

    public static GunzippingOutputStream create(OutputStream finalOut) throws IOException {
        PipedInputStream pipeIn = new PipedInputStream();
        return new GunzippingOutputStream(new PipedOutputStream(pipeIn), sExecutor.submit(new GunzippingCallable(pipeIn, finalOut)));
    }

    private GunzippingOutputStream(OutputStream out, Future<Void> copyFuture) throws IOException {
        super(out);
        this.mCopyFuture = copyFuture;
    }

    public void close() throws IOException {
        boolean success = false;
        try {
            super.close();
            success = true;
        } finally {
            try {
                getAndRethrow(this.mCopyFuture);
            } catch (IOException e) {
                if (success) {
                    throw e;
                }
            }
        }
    }

    private static <T> T getAndRethrow(Future<T> future) throws IOException {
        while (true) {
            try {
                return future.get();
            } catch (InterruptedException e) {
            } catch (ExecutionException e2) {
                Throwable cause = e2.getCause();
                ExceptionUtil.propagateIfInstanceOf(cause, IOException.class);
                ExceptionUtil.propagate(cause);
            }
        }
    }
}
