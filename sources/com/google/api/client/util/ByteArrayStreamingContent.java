package com.google.api.client.util;

import java.io.IOException;
import java.io.OutputStream;

public class ByteArrayStreamingContent implements StreamingContent {
    private final byte[] byteArray;
    private final int length;
    private final int offset;

    public void writeTo(OutputStream out) throws IOException {
        out.write(this.byteArray, this.offset, this.length);
        out.flush();
    }
}
