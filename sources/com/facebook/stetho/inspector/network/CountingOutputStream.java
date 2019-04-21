package com.facebook.stetho.inspector.network;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class CountingOutputStream extends FilterOutputStream {
    private long mCount;

    public CountingOutputStream(OutputStream out) {
        super(out);
    }

    public long getCount() {
        return this.mCount;
    }

    public void write(int oneByte) throws IOException {
        this.out.write(oneByte);
        this.mCount++;
    }

    public void write(byte[] buffer) throws IOException {
        write(buffer, 0, buffer.length);
    }

    public void write(byte[] buffer, int offset, int length) throws IOException {
        this.out.write(buffer, offset, length);
        this.mCount += (long) length;
    }
}
