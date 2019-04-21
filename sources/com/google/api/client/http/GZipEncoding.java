package com.google.api.client.http;

import com.google.api.client.util.StreamingContent;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class GZipEncoding implements HttpEncoding {
    public String getName() {
        return "gzip";
    }

    public void encode(StreamingContent content, OutputStream out) throws IOException {
        GZIPOutputStream zipper = new GZIPOutputStream(new BufferedOutputStream(out) {
            public void close() throws IOException {
                try {
                    flush();
                } catch (IOException e) {
                }
            }
        });
        content.writeTo(zipper);
        zipper.close();
    }
}
