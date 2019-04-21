package com.google.api.client.testing.util;

import com.google.api.client.util.Beta;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Beta
public class TestableByteArrayOutputStream extends ByteArrayOutputStream {
    private boolean closed;

    public void close() throws IOException {
        this.closed = true;
    }
}
