package com.google.api.client.testing.http;

import com.google.api.client.http.HttpContent;
import com.google.api.client.util.Beta;
import java.io.IOException;
import java.io.OutputStream;

@Beta
public class MockHttpContent implements HttpContent {
    private byte[] content = new byte[0];
    private long length = -1;
    private String type;

    public long getLength() throws IOException {
        return this.length;
    }

    public String getType() {
        return this.type;
    }

    public void writeTo(OutputStream out) throws IOException {
        out.write(this.content);
        out.flush();
    }

    public boolean retrySupported() {
        return true;
    }
}
