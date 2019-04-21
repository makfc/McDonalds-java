package com.newrelic.agent.android.instrumentation.retrofit;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import retrofit.mime.TypedInput;

public class EmptyBodyTypedInput implements TypedInput {
    public String mimeType() {
        return "text/html;charset=utf-8";
    }

    public long length() {
        return 0;
    }

    /* renamed from: in */
    public InputStream mo32409in() throws IOException {
        return new ByteArrayInputStream(new byte[0]);
    }
}
