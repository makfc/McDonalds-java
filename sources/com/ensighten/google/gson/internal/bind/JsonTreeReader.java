package com.ensighten.google.gson.internal.bind;

import com.ensighten.google.gson.JsonArray;
import com.ensighten.google.gson.JsonElement;
import com.ensighten.google.gson.JsonNull;
import com.ensighten.google.gson.JsonObject;
import com.ensighten.google.gson.JsonPrimitive;
import com.ensighten.google.gson.stream.JsonReader;
import com.ensighten.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public final class JsonTreeReader extends JsonReader {
    private static final Object SENTINEL_CLOSED = new Object();
    private static final Reader UNREADABLE_READER = new C17971();
    private final List<Object> stack = new ArrayList();

    /* renamed from: com.ensighten.google.gson.internal.bind.JsonTreeReader$1 */
    static class C17971 extends Reader {
        C17971() {
        }

        public final int read(char[] buffer, int offset, int count) throws IOException {
            throw new AssertionError();
        }

        public final void close() throws IOException {
            throw new AssertionError();
        }
    }

    public JsonTreeReader(JsonElement element) {
        super(UNREADABLE_READER);
        this.stack.add(element);
    }

    public final void beginArray() throws IOException {
        expect(JsonToken.BEGIN_ARRAY);
        this.stack.add(((JsonArray) peekStack()).iterator());
    }

    public final void endArray() throws IOException {
        expect(JsonToken.END_ARRAY);
        popStack();
        popStack();
    }

    public final void beginObject() throws IOException {
        expect(JsonToken.BEGIN_OBJECT);
        this.stack.add(((JsonObject) peekStack()).entrySet().iterator());
    }

    public final void endObject() throws IOException {
        expect(JsonToken.END_OBJECT);
        popStack();
        popStack();
    }

    public final boolean hasNext() throws IOException {
        JsonToken peek = peek();
        return (peek == JsonToken.END_OBJECT || peek == JsonToken.END_ARRAY) ? false : true;
    }

    public final JsonToken peek() throws IOException {
        while (!this.stack.isEmpty()) {
            Object peekStack = peekStack();
            if (peekStack instanceof Iterator) {
                boolean z = this.stack.get(this.stack.size() - 2) instanceof JsonObject;
                Iterator it = (Iterator) peekStack;
                if (!it.hasNext()) {
                    return z ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
                } else {
                    if (z) {
                        return JsonToken.NAME;
                    }
                    this.stack.add(it.next());
                }
            } else if (peekStack instanceof JsonObject) {
                return JsonToken.BEGIN_OBJECT;
            } else {
                if (peekStack instanceof JsonArray) {
                    return JsonToken.BEGIN_ARRAY;
                }
                if (peekStack instanceof JsonPrimitive) {
                    JsonPrimitive jsonPrimitive = (JsonPrimitive) peekStack;
                    if (jsonPrimitive.isString()) {
                        return JsonToken.STRING;
                    }
                    if (jsonPrimitive.isBoolean()) {
                        return JsonToken.BOOLEAN;
                    }
                    if (jsonPrimitive.isNumber()) {
                        return JsonToken.NUMBER;
                    }
                    throw new AssertionError();
                } else if (peekStack instanceof JsonNull) {
                    return JsonToken.NULL;
                } else {
                    if (peekStack == SENTINEL_CLOSED) {
                        throw new IllegalStateException("JsonReader is closed");
                    }
                    throw new AssertionError();
                }
            }
        }
        return JsonToken.END_DOCUMENT;
    }

    private Object peekStack() {
        return this.stack.get(this.stack.size() - 1);
    }

    private Object popStack() {
        return this.stack.remove(this.stack.size() - 1);
    }

    private void expect(JsonToken expected) throws IOException {
        if (peek() != expected) {
            throw new IllegalStateException("Expected " + expected + " but was " + peek());
        }
    }

    public final String nextName() throws IOException {
        expect(JsonToken.NAME);
        Entry entry = (Entry) ((Iterator) peekStack()).next();
        this.stack.add(entry.getValue());
        return (String) entry.getKey();
    }

    public final String nextString() throws IOException {
        JsonToken peek = peek();
        if (peek == JsonToken.STRING || peek == JsonToken.NUMBER) {
            return ((JsonPrimitive) popStack()).getAsString();
        }
        throw new IllegalStateException("Expected " + JsonToken.STRING + " but was " + peek);
    }

    public final boolean nextBoolean() throws IOException {
        expect(JsonToken.BOOLEAN);
        return ((JsonPrimitive) popStack()).getAsBoolean();
    }

    public final void nextNull() throws IOException {
        expect(JsonToken.NULL);
        popStack();
    }

    public final double nextDouble() throws IOException {
        JsonToken peek = peek();
        if (peek == JsonToken.NUMBER || peek == JsonToken.STRING) {
            double asDouble = ((JsonPrimitive) peekStack()).getAsDouble();
            if (isLenient() || !(Double.isNaN(asDouble) || Double.isInfinite(asDouble))) {
                popStack();
                return asDouble;
            }
            throw new NumberFormatException("JSON forbids NaN and infinities: " + asDouble);
        }
        throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + peek);
    }

    public final long nextLong() throws IOException {
        JsonToken peek = peek();
        if (peek == JsonToken.NUMBER || peek == JsonToken.STRING) {
            long asLong = ((JsonPrimitive) peekStack()).getAsLong();
            popStack();
            return asLong;
        }
        throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + peek);
    }

    public final int nextInt() throws IOException {
        JsonToken peek = peek();
        if (peek == JsonToken.NUMBER || peek == JsonToken.STRING) {
            int asInt = ((JsonPrimitive) peekStack()).getAsInt();
            popStack();
            return asInt;
        }
        throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + peek);
    }

    public final void close() throws IOException {
        this.stack.clear();
        this.stack.add(SENTINEL_CLOSED);
    }

    public final void skipValue() throws IOException {
        if (peek() == JsonToken.NAME) {
            nextName();
        } else {
            popStack();
        }
    }

    public final String toString() {
        return getClass().getSimpleName();
    }

    public final void promoteNameToValue() throws IOException {
        expect(JsonToken.NAME);
        Entry entry = (Entry) ((Iterator) peekStack()).next();
        this.stack.add(entry.getValue());
        this.stack.add(new JsonPrimitive((String) entry.getKey()));
    }
}
