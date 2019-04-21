package com.ensighten.google.gson.internal.bind;

import com.ensighten.google.gson.Gson;
import com.ensighten.google.gson.TypeAdapter;
import com.ensighten.google.gson.TypeAdapterFactory;
import com.ensighten.google.gson.internal.LinkedTreeMap;
import com.ensighten.google.gson.reflect.TypeToken;
import com.ensighten.google.gson.stream.JsonReader;
import com.ensighten.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;

public final class ObjectTypeAdapter extends TypeAdapter<Object> {
    public static final TypeAdapterFactory FACTORY = new C18001();
    private final Gson gson;

    /* renamed from: com.ensighten.google.gson.internal.bind.ObjectTypeAdapter$1 */
    static class C18001 implements TypeAdapterFactory {
        C18001() {
        }

        public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (type.getRawType() == Object.class) {
                return new ObjectTypeAdapter(gson, null);
            }
            return null;
        }
    }

    /* synthetic */ ObjectTypeAdapter(Gson x0, C18001 x1) {
        this(x0);
    }

    private ObjectTypeAdapter(Gson gson) {
        this.gson = gson;
    }

    public final Object read(JsonReader in) throws IOException {
        switch (in.peek()) {
            case BEGIN_ARRAY:
                ArrayList arrayList = new ArrayList();
                in.beginArray();
                while (in.hasNext()) {
                    arrayList.add(read(in));
                }
                in.endArray();
                return arrayList;
            case BEGIN_OBJECT:
                Object linkedTreeMap = new LinkedTreeMap();
                in.beginObject();
                while (in.hasNext()) {
                    linkedTreeMap.put(in.nextName(), read(in));
                }
                in.endObject();
                return linkedTreeMap;
            case STRING:
                return in.nextString();
            case NUMBER:
                return Double.valueOf(in.nextDouble());
            case BOOLEAN:
                return Boolean.valueOf(in.nextBoolean());
            case NULL:
                in.nextNull();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    public final void write(JsonWriter out, Object value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        TypeAdapter adapter = this.gson.getAdapter(value.getClass());
        if (adapter instanceof ObjectTypeAdapter) {
            out.beginObject();
            out.endObject();
            return;
        }
        adapter.write(out, value);
    }
}
