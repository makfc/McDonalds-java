package com.facebook.stetho.websocket;

import com.facebook.stetho.common.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
class CompositeInputStream extends InputStream {
    private int mCurrentIndex;
    private final InputStream[] mStreams;

    public CompositeInputStream(InputStream[] streams) {
        if (streams == null || streams.length < 2) {
            throw new IllegalArgumentException("Streams must be non-null and have more than 1 entry");
        }
        this.mStreams = streams;
        this.mCurrentIndex = 0;
    }

    public int available() throws IOException {
        return this.mStreams[this.mCurrentIndex].available();
    }

    public void close() throws IOException {
        closeAll(this.mCurrentIndex);
    }

    private void closeAll(int mostImportantIndex) throws IOException {
        IOException exceptionToThrow = null;
        for (int i = 0; i < this.mStreams.length; i++) {
            try {
                this.mStreams[i].close();
            } catch (IOException e) {
                Throwable previousException = exceptionToThrow;
                if (i == mostImportantIndex || exceptionToThrow == null) {
                    exceptionToThrow = e;
                }
                if (!(previousException == null || previousException == exceptionToThrow)) {
                    LogUtil.m7461w(previousException, "Suppressing exception");
                }
            }
        }
    }

    public void mark(int readlimit) {
        throw new UnsupportedOperationException();
    }

    public boolean markSupported() {
        return false;
    }

    public void reset() throws IOException {
        throw new UnsupportedOperationException();
    }

    public int read(byte[] buffer) throws IOException {
        return read(buffer, 0, buffer.length);
    }

    public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
        int n;
        do {
            n = this.mStreams[this.mCurrentIndex].read(buffer, byteOffset, byteCount);
            if (n != -1) {
                break;
            }
        } while (tryMoveToNextStream());
        return n;
    }

    public int read() throws IOException {
        int b;
        do {
            b = this.mStreams[this.mCurrentIndex].read();
            if (b != -1) {
                break;
            }
        } while (tryMoveToNextStream());
        return b;
    }

    private boolean tryMoveToNextStream() {
        if (this.mCurrentIndex + 1 >= this.mStreams.length) {
            return false;
        }
        this.mCurrentIndex++;
        return true;
    }

    public long skip(long byteCount) throws IOException {
        int n = read(new byte[((int) byteCount)]);
        return n >= 0 ? (long) n : -1;
    }
}
