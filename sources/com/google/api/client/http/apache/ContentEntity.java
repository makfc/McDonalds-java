package com.google.api.client.http.apache;

import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StreamingContent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.entity.AbstractHttpEntity;

final class ContentEntity extends AbstractHttpEntity {
    private final long contentLength;
    private final StreamingContent streamingContent;

    ContentEntity(long contentLength, StreamingContent streamingContent) {
        this.contentLength = contentLength;
        this.streamingContent = (StreamingContent) Preconditions.checkNotNull(streamingContent);
    }

    public InputStream getContent() {
        throw new UnsupportedOperationException();
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public boolean isRepeatable() {
        return false;
    }

    public boolean isStreaming() {
        return true;
    }

    public void writeTo(OutputStream out) throws IOException {
        if (this.contentLength != 0) {
            this.streamingContent.writeTo(out);
        }
    }
}
