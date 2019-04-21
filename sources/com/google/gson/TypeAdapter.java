package com.google.gson;

import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public abstract class TypeAdapter<T> {

    /* renamed from: com.google.gson.TypeAdapter$1 */
    class C27911 extends TypeAdapter<T> {
        final /* synthetic */ TypeAdapter this$0;

        public void write(JsonWriter out, T value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                this.this$0.write(out, value);
            }
        }

        public T read(JsonReader reader) throws IOException {
            if (reader.peek() != JsonToken.NULL) {
                return this.this$0.read(reader);
            }
            reader.nextNull();
            return null;
        }
    }

    public abstract T read(JsonReader jsonReader) throws IOException;

    public abstract void write(JsonWriter jsonWriter, T t) throws IOException;

    public final JsonElement toJsonTree(T value) {
        try {
            JsonTreeWriter jsonWriter = new JsonTreeWriter();
            write(jsonWriter, value);
            return jsonWriter.get();
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }
}
