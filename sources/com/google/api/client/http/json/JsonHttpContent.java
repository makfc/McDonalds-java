package com.google.api.client.http.json;

import com.google.api.client.http.AbstractHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import java.io.IOException;
import java.io.OutputStream;

public class JsonHttpContent extends AbstractHttpContent {
    private final Object data;
    private final JsonFactory jsonFactory;
    private String wrapperKey;

    public void writeTo(OutputStream out) throws IOException {
        JsonGenerator generator = this.jsonFactory.createJsonGenerator(out, getCharset());
        if (this.wrapperKey != null) {
            generator.writeStartObject();
            generator.writeFieldName(this.wrapperKey);
        }
        generator.serialize(this.data);
        if (this.wrapperKey != null) {
            generator.writeEndObject();
        }
        generator.flush();
    }
}
