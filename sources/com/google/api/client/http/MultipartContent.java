package com.google.api.client.http;

import com.google.api.client.util.StreamingContent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MultipartContent extends AbstractHttpContent {
    private ArrayList<Part> parts = new ArrayList();

    public static final class Part {
        HttpContent content;
        HttpEncoding encoding;
        HttpHeaders headers;

        public Part() {
            this(null);
        }

        public Part(HttpContent content) {
            this(null, content);
        }

        public Part(HttpHeaders headers, HttpContent content) {
            setHeaders(headers);
            setContent(content);
        }

        public Part setContent(HttpContent content) {
            this.content = content;
            return this;
        }

        public Part setHeaders(HttpHeaders headers) {
            this.headers = headers;
            return this;
        }
    }

    public MultipartContent() {
        super(new HttpMediaType("multipart/related").setParameter("boundary", "__END_OF_PART__"));
    }

    public void writeTo(OutputStream out) throws IOException {
        Writer writer = new OutputStreamWriter(out, getCharset());
        String boundary = getBoundary();
        Iterator i$ = this.parts.iterator();
        while (i$.hasNext()) {
            Part part = (Part) i$.next();
            HttpHeaders headers = new HttpHeaders().setAcceptEncoding(null);
            if (part.headers != null) {
                headers.fromHttpHeaders(part.headers);
            }
            headers.setContentEncoding(null).setUserAgent(null).setContentType(null).setContentLength(null).set("Content-Transfer-Encoding", null);
            HttpContent content = part.content;
            StreamingContent streamingContent = null;
            if (content != null) {
                long contentLength;
                headers.set("Content-Transfer-Encoding", Arrays.asList(new String[]{"binary"}));
                headers.setContentType(content.getType());
                HttpEncoding encoding = part.encoding;
                if (encoding == null) {
                    contentLength = content.getLength();
                    streamingContent = content;
                } else {
                    headers.setContentEncoding(encoding.getName());
                    streamingContent = new HttpEncodingStreamingContent(content, encoding);
                    contentLength = AbstractHttpContent.computeLength(content);
                }
                if (contentLength != -1) {
                    headers.setContentLength(Long.valueOf(contentLength));
                }
            }
            writer.write("--");
            writer.write(boundary);
            writer.write("\r\n");
            HttpHeaders.serializeHeadersForMultipartRequests(headers, null, null, writer);
            if (streamingContent != null) {
                writer.write("\r\n");
                writer.flush();
                streamingContent.writeTo(out);
                writer.write("\r\n");
            }
        }
        writer.write("--");
        writer.write(boundary);
        writer.write("--");
        writer.write("\r\n");
        writer.flush();
    }

    public boolean retrySupported() {
        Iterator i$ = this.parts.iterator();
        while (i$.hasNext()) {
            if (!((Part) i$.next()).content.retrySupported()) {
                return false;
            }
        }
        return true;
    }

    public final String getBoundary() {
        return getMediaType().getParameter("boundary");
    }
}
