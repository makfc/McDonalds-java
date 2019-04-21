package com.ensighten.google.gson.internal.bind;

import com.ensighten.google.gson.Gson;
import com.ensighten.google.gson.JsonSyntaxException;
import com.ensighten.google.gson.TypeAdapter;
import com.ensighten.google.gson.TypeAdapterFactory;
import com.ensighten.google.gson.reflect.TypeToken;
import com.ensighten.google.gson.stream.JsonReader;
import com.ensighten.google.gson.stream.JsonToken;
import com.ensighten.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class SqlDateTypeAdapter extends TypeAdapter<Date> {
    public static final TypeAdapterFactory FACTORY = new C18031();
    private final DateFormat format = new SimpleDateFormat("MMM d, yyyy");

    /* renamed from: com.ensighten.google.gson.internal.bind.SqlDateTypeAdapter$1 */
    static class C18031 implements TypeAdapterFactory {
        C18031() {
        }

        public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            return typeToken.getRawType() == Date.class ? new SqlDateTypeAdapter() : null;
        }
    }

    public final synchronized Date read(JsonReader in) throws IOException {
        Date date;
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            date = null;
        } else {
            try {
                date = new Date(this.format.parse(in.nextString()).getTime());
            } catch (ParseException e) {
                throw new JsonSyntaxException(e);
            }
        }
        return date;
    }

    public final synchronized void write(JsonWriter out, Date value) throws IOException {
        out.value(value == null ? null : this.format.format(value));
    }
}
