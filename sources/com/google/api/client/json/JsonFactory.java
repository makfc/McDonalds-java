package com.google.api.client.json;

import com.facebook.stetho.common.Utf8Charset;
import com.google.api.client.util.Charsets;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public abstract class JsonFactory {
    public abstract JsonGenerator createJsonGenerator(OutputStream outputStream, Charset charset) throws IOException;

    public abstract JsonParser createJsonParser(InputStream inputStream) throws IOException;

    public abstract JsonParser createJsonParser(InputStream inputStream, Charset charset) throws IOException;

    public abstract JsonParser createJsonParser(String str) throws IOException;

    public final String toString(Object item) throws IOException {
        return toString(item, false);
    }

    public final String toPrettyString(Object item) throws IOException {
        return toString(item, true);
    }

    private String toString(Object item, boolean pretty) throws IOException {
        return toByteStream(item, pretty).toString(Utf8Charset.NAME);
    }

    private ByteArrayOutputStream toByteStream(Object item, boolean pretty) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        JsonGenerator generator = createJsonGenerator(byteStream, Charsets.UTF_8);
        if (pretty) {
            generator.enablePrettyPrint();
        }
        generator.serialize(item);
        generator.flush();
        return byteStream;
    }

    public final <T> T fromInputStream(InputStream inputStream, Class<T> destinationClass) throws IOException {
        return createJsonParser(inputStream).parseAndClose(destinationClass);
    }
}
