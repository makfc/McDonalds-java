package com.google.api.client.http;

import com.google.api.client.util.StreamingContent;
import java.io.IOException;

public abstract class LowLevelHttpRequest {
    private String contentEncoding;
    private long contentLength = -1;
    private String contentType;
    private StreamingContent streamingContent;

    public abstract void addHeader(String str, String str2) throws IOException;

    public abstract LowLevelHttpResponse execute() throws IOException;

    public final void setContentLength(long contentLength) throws IOException {
        this.contentLength = contentLength;
    }

    public final long getContentLength() {
        return this.contentLength;
    }

    public final void setContentEncoding(String contentEncoding) throws IOException {
        this.contentEncoding = contentEncoding;
    }

    public final String getContentEncoding() {
        return this.contentEncoding;
    }

    public final void setContentType(String contentType) throws IOException {
        this.contentType = contentType;
    }

    public final String getContentType() {
        return this.contentType;
    }

    public final void setStreamingContent(StreamingContent streamingContent) throws IOException {
        this.streamingContent = streamingContent;
    }

    public final StreamingContent getStreamingContent() {
        return this.streamingContent;
    }

    public void setTimeout(int connectTimeout, int readTimeout) throws IOException {
    }
}
