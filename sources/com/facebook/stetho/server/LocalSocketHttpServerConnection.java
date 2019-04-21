package com.facebook.stetho.server;

import android.net.LocalSocket;
import com.facebook.stetho.common.Util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.http.impl.AbstractHttpServerConnection;
import org.apache.http.impl.io.AbstractSessionInputBuffer;
import org.apache.http.impl.io.AbstractSessionOutputBuffer;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class LocalSocketHttpServerConnection extends AbstractHttpServerConnection {
    private volatile LocalSocketSessionInputBuffer mInputBuffer;
    private volatile boolean mOpen;
    private volatile LocalSocket mSocket;

    private static class LocalSocketSessionInputBuffer extends AbstractSessionInputBuffer {
        public LocalSocketSessionInputBuffer(LocalSocket socket, int bufferSize, HttpParams params) throws IOException {
            if (HttpConnectionParams.isStaleCheckingEnabled(params)) {
                throw new UnsupportedOperationException("Stale connection checking should not be used for local sockets");
            }
            init(socket.getInputStream(), bufferSize, params);
        }

        public byte[] clearCurrentBuffer() {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            while (hasBufferedData()) {
                try {
                    boolean z;
                    int b = read();
                    if (b != -1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Util.throwIfNot(z, "Buffered data cannot EOF", new Object[0]);
                    buffer.write(b);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return buffer.toByteArray();
        }

        public boolean isDataAvailable(int timeout) throws IOException {
            throw new UnsupportedOperationException("CoreConnectionPNames.STALE_CONNECTION_CHECK appears to be true but that can't be?");
        }
    }

    private static class LocalSocketSessionOutputBuffer extends AbstractSessionOutputBuffer {
        public LocalSocketSessionOutputBuffer(LocalSocket socket, int bufferSize, HttpParams params) throws IOException {
            init(socket.getOutputStream(), bufferSize, params);
        }

        public void flush() throws IOException {
            flushBuffer();
        }
    }

    public void bind(LocalSocket socket, HttpParams params) throws IOException {
        Util.throwIfNull(socket);
        Util.throwIfNull(params);
        this.mSocket = socket;
        int bufferSize = HttpConnectionParams.getSocketBufferSize(params);
        this.mInputBuffer = new LocalSocketSessionInputBuffer(socket, bufferSize, params);
        init(this.mInputBuffer, new LocalSocketSessionOutputBuffer(socket, bufferSize, params), params);
        this.mOpen = true;
    }

    public LocalSocket getSocket() {
        return this.mSocket;
    }

    public byte[] clearInputBuffer() {
        return this.mInputBuffer.clearCurrentBuffer();
    }

    /* Access modifiers changed, original: protected */
    public void assertOpen() throws IllegalStateException {
        Util.throwIfNot(this.mOpen);
    }

    public boolean isOpen() {
        return this.mOpen;
    }

    public void setSocketTimeout(int timeout) {
        try {
            this.mSocket.setSoTimeout(timeout);
        } catch (IOException e) {
            Util.throwIfNot(this.mSocket.isClosed());
        }
    }

    public int getSocketTimeout() {
        try {
            return this.mSocket.getSoTimeout();
        } catch (IOException e) {
            Util.throwIfNot(this.mSocket.isClosed());
            return -1;
        }
    }

    public void shutdown() throws IOException {
        close(false);
    }

    public void close() throws IOException {
        close(true);
    }

    private void close(boolean doFlush) throws IOException {
        if (this.mOpen) {
            this.mOpen = false;
            if (doFlush) {
                doFlush();
            }
            this.mSocket.close();
        }
    }
}
