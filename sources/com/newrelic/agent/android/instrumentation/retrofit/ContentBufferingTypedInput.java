package com.newrelic.agent.android.instrumentation.retrofit;

import com.newrelic.agent.android.instrumentation.p053io.CountingInputStream;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import retrofit.mime.TypedInput;

public class ContentBufferingTypedInput implements TypedInput {
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private TypedInput impl;
    private CountingInputStream inputStream;

    public ContentBufferingTypedInput(TypedInput impl) {
        if (impl == null) {
            impl = new EmptyBodyTypedInput();
        }
        this.impl = impl;
        this.inputStream = null;
    }

    public String mimeType() {
        return this.impl.mimeType();
    }

    public long length() {
        try {
            prepareInputStream();
            return (long) this.inputStream.available();
        } catch (IOException e) {
            log.error("ContentBufferingTypedInput generated an IO exception: ", e);
            return -1;
        }
    }

    /* renamed from: in */
    public InputStream mo32406in() throws IOException {
        prepareInputStream();
        return this.inputStream;
    }

    private void prepareInputStream() throws IOException {
        if (this.inputStream == null) {
            try {
                InputStream is = this.impl.in();
                if (is == null) {
                    is = new ByteArrayInputStream(new byte[0]);
                }
                this.inputStream = new CountingInputStream(is, true);
            } catch (Exception e) {
                log.error("ContentBufferingTypedInput: " + e.toString());
            }
        }
    }
}
