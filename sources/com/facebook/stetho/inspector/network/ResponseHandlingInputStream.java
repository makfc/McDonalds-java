package com.facebook.stetho.inspector.network;

import com.facebook.stetho.inspector.console.CLog;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.protocol.module.Console.MessageLevel;
import com.facebook.stetho.inspector.protocol.module.Console.MessageSource;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class ResponseHandlingInputStream extends FilterInputStream {
    private static final int BUFFER_SIZE = 1024;
    public static final String TAG = "ResponseHandlingInputStream";
    @GuardedBy
    private boolean mClosed;
    @Nullable
    private final CountingOutputStream mDecompressedCounter;
    @GuardedBy
    private boolean mEofSeen;
    private long mLastDecompressedCount = 0;
    private final ChromePeerManager mNetworkPeerManager;
    private final OutputStream mOutputStream;
    private final String mRequestId;
    private final ResponseHandler mResponseHandler;
    @GuardedBy
    @Nullable
    private byte[] mSkipBuffer;

    public ResponseHandlingInputStream(InputStream inputStream, String requestId, OutputStream outputStream, @Nullable CountingOutputStream decompressedCounter, ChromePeerManager networkPeerManager, ResponseHandler responseHandler) {
        super(inputStream);
        this.mRequestId = requestId;
        this.mOutputStream = outputStream;
        this.mDecompressedCounter = decompressedCounter;
        this.mNetworkPeerManager = networkPeerManager;
        this.mResponseHandler = responseHandler;
        this.mClosed = false;
    }

    private synchronized int checkEOF(int n) {
        if (n == -1) {
            closeOutputStreamQuietly();
            this.mResponseHandler.onEOF();
            this.mEofSeen = true;
        }
        return n;
    }

    public int read() throws IOException {
        try {
            int result = checkEOF(this.in.read());
            if (result != -1) {
                this.mResponseHandler.onRead(1);
                writeToOutputStream(result);
            }
            return result;
        } catch (IOException ex) {
            throw handleIOException(ex);
        }
    }

    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        try {
            int result = checkEOF(this.in.read(b, off, len));
            if (result != -1) {
                this.mResponseHandler.onRead(result);
                writeToOutputStream(b, off, result);
            }
            return result;
        } catch (IOException ex) {
            throw handleIOException(ex);
        }
    }

    public synchronized long skip(long n) throws IOException {
        long total;
        byte[] buffer = getSkipBufferLocked();
        total = 0;
        while (total < n) {
            int result = read(buffer, 0, (int) Math.min((long) buffer.length, n - total));
            if (result == -1) {
                break;
            }
            total += (long) result;
        }
        return total;
    }

    @Nonnull
    private byte[] getSkipBufferLocked() {
        if (this.mSkipBuffer == null) {
            this.mSkipBuffer = new byte[1024];
        }
        return this.mSkipBuffer;
    }

    public boolean markSupported() {
        return false;
    }

    public void mark(int readlimit) {
    }

    public void reset() throws IOException {
        throw new UnsupportedOperationException("Mark not supported");
    }

    public void close() throws IOException {
        long bytesRead = 0;
        try {
            if (!this.mEofSeen) {
                byte[] buffer = new byte[1024];
                while (true) {
                    int count = read(buffer);
                    if (count == -1) {
                        break;
                    }
                    bytesRead += (long) count;
                }
            }
            if (bytesRead > 0) {
                CLog.writeToConsole(this.mNetworkPeerManager, MessageLevel.ERROR, MessageSource.NETWORK, "There were " + String.valueOf(bytesRead) + " bytes that were not consumed while " + "processing request " + this.mRequestId);
            }
            super.close();
            closeOutputStreamQuietly();
        } catch (Throwable th) {
            super.close();
            closeOutputStreamQuietly();
        }
    }

    private synchronized void closeOutputStreamQuietly() {
        if (!this.mClosed) {
            try {
                this.mOutputStream.close();
                reportDecodedSizeIfApplicable();
                this.mClosed = true;
            } catch (IOException e) {
                CLog.writeToConsole(this.mNetworkPeerManager, MessageLevel.ERROR, MessageSource.NETWORK, "Could not close the output stream" + e);
                this.mClosed = true;
            } catch (Throwable th) {
                this.mClosed = true;
            }
        }
        return;
    }

    private IOException handleIOException(IOException ex) {
        this.mResponseHandler.onError(ex);
        return ex;
    }

    private void reportDecodedSizeIfApplicable() {
        if (this.mDecompressedCounter != null) {
            long currentCount = this.mDecompressedCounter.getCount();
            this.mResponseHandler.onReadDecoded((int) (currentCount - this.mLastDecompressedCount));
            this.mLastDecompressedCount = currentCount;
        }
    }

    private synchronized void writeToOutputStream(int oneByte) {
        if (!this.mClosed) {
            try {
                this.mOutputStream.write(oneByte);
                reportDecodedSizeIfApplicable();
            } catch (IOException e) {
                handleIOExceptionWritingToStream(e);
            }
        }
        return;
    }

    private synchronized void writeToOutputStream(byte[] b, int offset, int count) {
        if (!this.mClosed) {
            try {
                this.mOutputStream.write(b, offset, count);
                reportDecodedSizeIfApplicable();
            } catch (IOException e) {
                handleIOExceptionWritingToStream(e);
            }
        }
        return;
    }

    private void handleIOExceptionWritingToStream(IOException e) {
        CLog.writeToConsole(this.mNetworkPeerManager, MessageLevel.ERROR, MessageSource.NETWORK, "Could not write response body to the stream " + e);
        closeOutputStreamQuietly();
    }
}
