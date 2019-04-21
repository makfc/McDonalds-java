package com.google.api.client.testing.util;

import com.google.api.client.util.Beta;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Beta
public class TestableByteArrayInputStream extends ByteArrayInputStream {
    private boolean closed;

    public void close() throws IOException {
        this.closed = true;
    }
}
