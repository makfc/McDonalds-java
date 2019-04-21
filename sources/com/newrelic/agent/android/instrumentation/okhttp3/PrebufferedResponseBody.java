package com.newrelic.agent.android.instrumentation.okhttp3;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class PrebufferedResponseBody extends ResponseBody {
    private final long contentLength;
    private final ResponseBody impl;
    private final BufferedSource source;

    public PrebufferedResponseBody(ResponseBody impl) {
        BufferedSource source = impl.source();
        Buffer buffer = new Buffer();
        try {
            source.readAll(buffer);
            source = buffer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.impl = impl;
        this.source = source;
        this.contentLength = impl.contentLength() >= 0 ? impl.contentLength() : source.buffer().size();
    }

    public MediaType contentType() {
        return this.impl.contentType();
    }

    public long contentLength() {
        return this.contentLength;
    }

    public BufferedSource source() {
        return this.source;
    }

    public void close() {
        this.impl.close();
    }
}
