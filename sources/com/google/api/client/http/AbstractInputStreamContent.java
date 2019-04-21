package com.google.api.client.http;

import com.google.api.client.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractInputStreamContent implements HttpContent {
    private boolean closeInputStream;
    private String type;

    public abstract InputStream getInputStream() throws IOException;

    public void writeTo(OutputStream out) throws IOException {
        IOUtils.copy(getInputStream(), out, this.closeInputStream);
        out.flush();
    }

    public String getType() {
        return this.type;
    }
}
