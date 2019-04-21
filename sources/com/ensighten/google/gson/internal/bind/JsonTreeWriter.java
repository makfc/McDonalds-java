package com.ensighten.google.gson.internal.bind;

import com.ensighten.google.gson.JsonArray;
import com.ensighten.google.gson.JsonElement;
import com.ensighten.google.gson.JsonNull;
import com.ensighten.google.gson.JsonObject;
import com.ensighten.google.gson.JsonPrimitive;
import com.ensighten.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class JsonTreeWriter extends JsonWriter {
    private static final JsonPrimitive SENTINEL_CLOSED = new JsonPrimitive("closed");
    private static final Writer UNWRITABLE_WRITER = new C17991();
    private String pendingName;
    private JsonElement product = JsonNull.INSTANCE;
    private final List<JsonElement> stack = new ArrayList();

    /* renamed from: com.ensighten.google.gson.internal.bind.JsonTreeWriter$1 */
    static class C17991 extends Writer {
        C17991() {
        }

        public final void write(char[] buffer, int offset, int counter) {
            throw new AssertionError();
        }

        public final void flush() throws IOException {
            throw new AssertionError();
        }

        public final void close() throws IOException {
            throw new AssertionError();
        }
    }

    public JsonTreeWriter() {
        super(UNWRITABLE_WRITER);
    }

    public final JsonElement get() {
        if (this.stack.isEmpty()) {
            return this.product;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.stack);
    }

    private JsonElement peek() {
        return (JsonElement) this.stack.get(this.stack.size() - 1);
    }

    private void put(JsonElement value) {
        if (this.pendingName != null) {
            if (!value.isJsonNull() || getSerializeNulls()) {
                ((JsonObject) peek()).add(this.pendingName, value);
            }
            this.pendingName = null;
        } else if (this.stack.isEmpty()) {
            this.product = value;
        } else {
            JsonElement peek = peek();
            if (peek instanceof JsonArray) {
                ((JsonArray) peek).add(value);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public final JsonWriter beginArray() throws IOException {
        JsonArray jsonArray = new JsonArray();
        put(jsonArray);
        this.stack.add(jsonArray);
        return this;
    }

    public final JsonWriter endArray() throws IOException {
        if (this.stack.isEmpty() || this.pendingName != null) {
            throw new IllegalStateException();
        } else if (peek() instanceof JsonArray) {
            this.stack.remove(this.stack.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public final JsonWriter beginObject() throws IOException {
        JsonObject jsonObject = new JsonObject();
        put(jsonObject);
        this.stack.add(jsonObject);
        return this;
    }

    public final JsonWriter endObject() throws IOException {
        if (this.stack.isEmpty() || this.pendingName != null) {
            throw new IllegalStateException();
        } else if (peek() instanceof JsonObject) {
            this.stack.remove(this.stack.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public final JsonWriter name(String name) throws IOException {
        if (this.stack.isEmpty() || this.pendingName != null) {
            throw new IllegalStateException();
        } else if (peek() instanceof JsonObject) {
            this.pendingName = name;
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public final JsonWriter value(String value) throws IOException {
        if (value == null) {
            return nullValue();
        }
        put(new JsonPrimitive(value));
        return this;
    }

    public final JsonWriter nullValue() throws IOException {
        put(JsonNull.INSTANCE);
        return this;
    }

    public final JsonWriter value(boolean value) throws IOException {
        put(new JsonPrimitive(Boolean.valueOf(value)));
        return this;
    }

    public final JsonWriter value(double value) throws IOException {
        if (isLenient() || !(Double.isNaN(value) || Double.isInfinite(value))) {
            put(new JsonPrimitive(Double.valueOf(value)));
            return this;
        }
        throw new IllegalArgumentException("JSON forbids NaN and infinities: " + value);
    }

    public final JsonWriter value(long value) throws IOException {
        put(new JsonPrimitive(Long.valueOf(value)));
        return this;
    }

    public final JsonWriter value(Number value) throws IOException {
        if (value == null) {
            return nullValue();
        }
        if (!isLenient()) {
            double doubleValue = value.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + value);
            }
        }
        put(new JsonPrimitive(value));
        return this;
    }

    public final void flush() throws IOException {
    }

    public final void close() throws IOException {
        if (this.stack.isEmpty()) {
            this.stack.add(SENTINEL_CLOSED);
            return;
        }
        throw new IOException("Incomplete document");
    }
}
