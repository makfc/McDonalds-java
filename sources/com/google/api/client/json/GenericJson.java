package com.google.api.client.json;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Throwables;
import java.io.IOException;

public class GenericJson extends GenericData implements Cloneable {
    private JsonFactory jsonFactory;

    public final JsonFactory getFactory() {
        return this.jsonFactory;
    }

    public final void setFactory(JsonFactory factory) {
        this.jsonFactory = factory;
    }

    public String toString() {
        if (this.jsonFactory == null) {
            return super.toString();
        }
        try {
            return this.jsonFactory.toString(this);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public String toPrettyString() throws IOException {
        if (this.jsonFactory != null) {
            return this.jsonFactory.toPrettyString(this);
        }
        return super.toString();
    }

    public GenericJson clone() {
        return (GenericJson) super.clone();
    }

    public GenericJson set(String fieldName, Object value) {
        return (GenericJson) super.set(fieldName, value);
    }
}
