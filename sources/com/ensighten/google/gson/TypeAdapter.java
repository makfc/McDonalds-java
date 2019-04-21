package com.ensighten.google.gson;

import com.ensighten.google.gson.internal.bind.JsonTreeReader;
import com.ensighten.google.gson.internal.bind.JsonTreeWriter;
import com.ensighten.google.gson.stream.JsonReader;
import com.ensighten.google.gson.stream.JsonToken;
import com.ensighten.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public abstract class TypeAdapter<T> {

    /* renamed from: com.ensighten.google.gson.TypeAdapter$1 */
    class C17641 extends TypeAdapter<T> {
        C17641() {
        }

        public void write(JsonWriter out, T value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                TypeAdapter.this.write(out, value);
            }
        }

        public T read(JsonReader reader) throws IOException {
            if (reader.peek() != JsonToken.NULL) {
                return TypeAdapter.this.read(reader);
            }
            reader.nextNull();
            return null;
        }
    }

    public abstract T read(JsonReader jsonReader) throws IOException;

    public abstract void write(JsonWriter jsonWriter, T t) throws IOException;

    public final void toJson(Writer out, T value) throws IOException {
        write(new JsonWriter(out), value);
    }

    public final TypeAdapter<T> nullSafe() {
        return new C17641();
    }

    public final String toJson(T value) throws IOException {
        StringWriter stringWriter = new StringWriter();
        toJson(stringWriter, value);
        return stringWriter.toString();
    }

    public final JsonElement toJsonTree(T value) {
        try {
            JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();
            write(jsonTreeWriter, value);
            return jsonTreeWriter.get();
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }

    public final T fromJson(Reader in) throws IOException {
        return read(new JsonReader(in));
    }

    public final T fromJson(String json) throws IOException {
        return fromJson(new StringReader(json));
    }

    public final T fromJsonTree(JsonElement jsonTree) {
        try {
            return read(new JsonTreeReader(jsonTree));
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }
}
