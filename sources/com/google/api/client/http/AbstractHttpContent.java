package com.google.api.client.http;

import com.google.api.client.util.Charsets;
import com.google.api.client.util.IOUtils;
import java.io.IOException;
import java.nio.charset.Charset;

public abstract class AbstractHttpContent implements HttpContent {
    private long computedLength;
    private HttpMediaType mediaType;

    protected AbstractHttpContent(String mediaType) {
        this(mediaType == null ? null : new HttpMediaType(mediaType));
    }

    protected AbstractHttpContent(HttpMediaType mediaType) {
        this.computedLength = -1;
        this.mediaType = mediaType;
    }

    public long getLength() throws IOException {
        if (this.computedLength == -1) {
            this.computedLength = computeLength();
        }
        return this.computedLength;
    }

    public final HttpMediaType getMediaType() {
        return this.mediaType;
    }

    /* Access modifiers changed, original: protected|final */
    public final Charset getCharset() {
        return (this.mediaType == null || this.mediaType.getCharsetParameter() == null) ? Charsets.UTF_8 : this.mediaType.getCharsetParameter();
    }

    public String getType() {
        return this.mediaType == null ? null : this.mediaType.build();
    }

    /* Access modifiers changed, original: protected */
    public long computeLength() throws IOException {
        return computeLength(this);
    }

    public boolean retrySupported() {
        return true;
    }

    public static long computeLength(HttpContent content) throws IOException {
        if (content.retrySupported()) {
            return IOUtils.computeLength(content);
        }
        return -1;
    }
}
