package com.ensighten.google.gson;

public final class JsonNull extends JsonElement {
    public static final JsonNull INSTANCE = new JsonNull();

    /* Access modifiers changed, original: final */
    public final JsonNull deepCopy() {
        return INSTANCE;
    }

    public final int hashCode() {
        return JsonNull.class.hashCode();
    }

    public final boolean equals(Object other) {
        return this == other || (other instanceof JsonNull);
    }
}
