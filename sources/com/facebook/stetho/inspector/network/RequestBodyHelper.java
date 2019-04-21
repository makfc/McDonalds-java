package com.facebook.stetho.inspector.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.InflaterOutputStream;
import javax.annotation.Nullable;

public class RequestBodyHelper {
    private ByteArrayOutputStream mDeflatedOutput;
    private CountingOutputStream mDeflatingOutput;
    private final NetworkEventReporter mEventReporter;
    private final String mRequestId;

    public RequestBodyHelper(NetworkEventReporter eventReporter, String requestId) {
        this.mEventReporter = eventReporter;
        this.mRequestId = requestId;
    }

    public OutputStream createBodySink(@Nullable String contentEncoding) throws IOException {
        OutputStream deflatingOutput;
        OutputStream deflatedOutput = new ByteArrayOutputStream();
        if ("gzip".equals(contentEncoding)) {
            deflatingOutput = GunzippingOutputStream.create(deflatedOutput);
        } else if ("deflate".equals(contentEncoding)) {
            deflatingOutput = new InflaterOutputStream(deflatedOutput);
        } else {
            deflatingOutput = deflatedOutput;
        }
        this.mDeflatingOutput = new CountingOutputStream(deflatingOutput);
        this.mDeflatedOutput = deflatedOutput;
        return this.mDeflatingOutput;
    }

    public byte[] getDisplayBody() {
        throwIfNoBody();
        return this.mDeflatedOutput.toByteArray();
    }

    public boolean hasBody() {
        return this.mDeflatedOutput != null;
    }

    public void reportDataSent() {
        throwIfNoBody();
        this.mEventReporter.dataSent(this.mRequestId, this.mDeflatedOutput.size(), (int) this.mDeflatingOutput.getCount());
    }

    private void throwIfNoBody() {
        if (!hasBody()) {
            throw new IllegalStateException("No body found; has createBodySink been called?");
        }
    }
}
