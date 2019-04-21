package com.google.api.client.testing.json;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.api.client.util.Beta;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

@Beta
public class MockJsonFactory extends JsonFactory {
    public JsonParser createJsonParser(InputStream in) throws IOException {
        return new MockJsonParser(this);
    }

    public JsonParser createJsonParser(InputStream in, Charset charset) throws IOException {
        return new MockJsonParser(this);
    }

    public JsonParser createJsonParser(String value) throws IOException {
        return new MockJsonParser(this);
    }

    public JsonGenerator createJsonGenerator(OutputStream out, Charset enc) throws IOException {
        return new MockJsonGenerator(this);
    }
}
