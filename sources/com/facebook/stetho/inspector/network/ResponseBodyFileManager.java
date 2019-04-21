package com.facebook.stetho.inspector.network;

import android.content.Context;
import android.util.Base64OutputStream;
import com.facebook.stetho.common.ExceptionUtil;
import com.facebook.stetho.common.LogRedirector;
import com.facebook.stetho.common.Utf8Charset;
import com.facebook.stetho.common.Util;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ResponseBodyFileManager {
    private static final String FILENAME_PREFIX = "network-response-body-";
    private static final int PRETTY_PRINT_TIMEOUT_SEC = 10;
    private static final String TAG = "ResponseBodyFileManager";
    private final Context mContext;
    private final Map<String, AsyncPrettyPrinter> mRequestIdMap = Collections.synchronizedMap(new HashMap());

    private class AsyncPrettyPrintingCallable implements Callable<String> {
        private final AsyncPrettyPrinter mAsyncPrettyPrinter;
        private final InputStream mInputStream;

        public AsyncPrettyPrintingCallable(InputStream in, AsyncPrettyPrinter asyncPrettyPrinter) {
            this.mInputStream = in;
            this.mAsyncPrettyPrinter = asyncPrettyPrinter;
        }

        public String call() throws IOException {
            return prettyPrintContent(this.mInputStream, this.mAsyncPrettyPrinter);
        }

        private String prettyPrintContent(InputStream in, AsyncPrettyPrinter asyncPrettyPrinter) throws IOException {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintWriter writer = new PrintWriter(out);
            asyncPrettyPrinter.printTo(writer, in);
            writer.flush();
            return out.toString(Utf8Charset.NAME);
        }
    }

    public ResponseBodyFileManager(Context context) {
        this.mContext = context;
    }

    public void cleanupFiles() {
        for (File file : this.mContext.getFilesDir().listFiles()) {
            if (file.getName().startsWith(FILENAME_PREFIX) && !file.delete()) {
                LogRedirector.m7441w(TAG, "Failed to delete " + file.getAbsolutePath());
            }
        }
        LogRedirector.m7437i(TAG, "Cleaned up temporary network files.");
    }

    public ResponseBodyData readFile(String requestId) throws IOException {
        InputStream in = this.mContext.openFileInput(getFilename(requestId));
        try {
            int firstByte = in.read();
            if (firstByte == -1) {
                throw new EOFException("Failed to read base64Encode byte");
            }
            ResponseBodyData bodyData = new ResponseBodyData();
            bodyData.base64Encoded = firstByte != 0;
            AsyncPrettyPrinter asyncPrettyPrinter = (AsyncPrettyPrinter) this.mRequestIdMap.get(requestId);
            if (asyncPrettyPrinter != null) {
                bodyData.data = prettyPrintContentWithTimeOut(asyncPrettyPrinter, in);
            } else {
                bodyData.data = Util.readAsUTF8(in);
            }
            in.close();
            return bodyData;
        } catch (Throwable th) {
            in.close();
        }
    }

    private String prettyPrintContentWithTimeOut(AsyncPrettyPrinter asyncPrettyPrinter, InputStream in) throws IOException {
        AsyncPrettyPrintingCallable prettyPrintingCallable = new AsyncPrettyPrintingCallable(in, asyncPrettyPrinter);
        ExecutorService executorService = AsyncPrettyPrinterExecutorHolder.getExecutorService();
        if (executorService == null) {
            return null;
        }
        Future<String> future = executorService.submit(prettyPrintingCallable);
        try {
            return (String) Util.getUninterruptibly(future, 10, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
            return "Time out after 10 seconds of attempting to pretty print\n" + Util.readAsUTF8(in);
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            ExceptionUtil.propagateIfInstanceOf(cause, IOException.class);
            throw ExceptionUtil.propagate(cause);
        }
    }

    public OutputStream openResponseBodyFile(String requestId, boolean base64Encode) throws IOException {
        int i;
        OutputStream out = this.mContext.openFileOutput(getFilename(requestId), 0);
        if (base64Encode) {
            i = 1;
        } else {
            i = 0;
        }
        out.write(i);
        if (base64Encode) {
            return new Base64OutputStream(out, 0);
        }
        return out;
    }

    private static String getFilename(String requestId) {
        return FILENAME_PREFIX + requestId;
    }

    public void associateAsyncPrettyPrinterWithId(String requestId, AsyncPrettyPrinter asyncPrettyPrinter) {
        if (this.mRequestIdMap.put(requestId, asyncPrettyPrinter) != null) {
            throw new IllegalArgumentException("cannot associate different pretty printers with the same request id: " + requestId);
        }
    }
}
