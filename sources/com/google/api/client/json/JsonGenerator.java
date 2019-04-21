package com.google.api.client.json;

import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Types;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.Map.Entry;

public abstract class JsonGenerator {
    public abstract void flush() throws IOException;

    public abstract void writeBoolean(boolean z) throws IOException;

    public abstract void writeEndArray() throws IOException;

    public abstract void writeEndObject() throws IOException;

    public abstract void writeFieldName(String str) throws IOException;

    public abstract void writeNull() throws IOException;

    public abstract void writeNumber(double d) throws IOException;

    public abstract void writeNumber(float f) throws IOException;

    public abstract void writeNumber(int i) throws IOException;

    public abstract void writeNumber(long j) throws IOException;

    public abstract void writeNumber(BigDecimal bigDecimal) throws IOException;

    public abstract void writeNumber(BigInteger bigInteger) throws IOException;

    public abstract void writeStartArray() throws IOException;

    public abstract void writeStartObject() throws IOException;

    public abstract void writeString(String str) throws IOException;

    public final void serialize(Object value) throws IOException {
        serialize(false, value);
    }

    private void serialize(boolean isJsonString, Object value) throws IOException {
        if (value != null) {
            Class<?> valueClass = value.getClass();
            if (Data.isNull(value)) {
                writeNull();
            } else if (value instanceof String) {
                writeString((String) value);
            } else if (value instanceof Number) {
                boolean z;
                if (isJsonString) {
                    writeString(value.toString());
                } else if (value instanceof BigDecimal) {
                    writeNumber((BigDecimal) value);
                } else if (value instanceof BigInteger) {
                    writeNumber((BigInteger) value);
                } else if (value instanceof Long) {
                    writeNumber(((Long) value).longValue());
                } else if (value instanceof Float) {
                    float floatValue = ((Number) value).floatValue();
                    z = (Float.isInfinite(floatValue) || Float.isNaN(floatValue)) ? false : true;
                    Preconditions.checkArgument(z);
                    writeNumber(floatValue);
                } else if ((value instanceof Integer) || (value instanceof Short) || (value instanceof Byte)) {
                    writeNumber(((Number) value).intValue());
                } else {
                    double doubleValue = ((Number) value).doubleValue();
                    z = (Double.isInfinite(doubleValue) || Double.isNaN(doubleValue)) ? false : true;
                    Preconditions.checkArgument(z);
                    writeNumber(doubleValue);
                }
            } else if (value instanceof Boolean) {
                writeBoolean(((Boolean) value).booleanValue());
            } else if (value instanceof DateTime) {
                writeString(((DateTime) value).toStringRfc3339());
            } else if ((value instanceof Iterable) || valueClass.isArray()) {
                writeStartArray();
                for (Object o : Types.iterableOf(value)) {
                    serialize(isJsonString, o);
                }
                writeEndArray();
            } else if (valueClass.isEnum()) {
                String name = FieldInfo.m7510of((Enum) value).getName();
                if (name == null) {
                    writeNull();
                } else {
                    writeString(name);
                }
            } else {
                writeStartObject();
                boolean isMapNotGenericData = (value instanceof Map) && !(value instanceof GenericData);
                ClassInfo classInfo = isMapNotGenericData ? null : ClassInfo.m7508of(valueClass);
                for (Entry<String, Object> entry : Data.mapOf(value).entrySet()) {
                    Object fieldValue = entry.getValue();
                    if (fieldValue != null) {
                        boolean isJsonStringForField;
                        String fieldName = (String) entry.getKey();
                        if (isMapNotGenericData) {
                            isJsonStringForField = isJsonString;
                        } else {
                            Field field = classInfo.getField(fieldName);
                            isJsonStringForField = (field == null || field.getAnnotation(JsonString.class) == null) ? false : true;
                        }
                        writeFieldName(fieldName);
                        serialize(isJsonStringForField, fieldValue);
                    }
                }
                writeEndObject();
            }
        }
    }

    public void enablePrettyPrint() throws IOException {
    }
}
