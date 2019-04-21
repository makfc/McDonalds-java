package com.ensighten.google.gson;

public enum LongSerializationPolicy {
    DEFAULT {
        public final JsonElement serialize(Long value) {
            return new JsonPrimitive((Number) value);
        }
    },
    STRING {
        public final JsonElement serialize(Long value) {
            return new JsonPrimitive(String.valueOf(value));
        }
    };

    public abstract JsonElement serialize(Long l);
}
