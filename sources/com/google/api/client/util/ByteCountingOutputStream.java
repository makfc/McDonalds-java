package com.google.api.client.util;

import java.io.IOException;
import java.io.OutputStream;

final class ByteCountingOutputStream extends OutputStream {
    long count;

    ByteCountingOutputStream() {
    }

    public void write(byte[] b, int off, int len) throws IOException {
        this.count += (long) len;
    }

    public void write(int b) throws IOException {
        this.count++;
    }
}
