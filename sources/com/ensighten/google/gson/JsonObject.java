package com.ensighten.google.gson;

import com.ensighten.google.gson.internal.LinkedTreeMap;
import java.util.Map.Entry;
import java.util.Set;

public final class JsonObject extends JsonElement {
    private final LinkedTreeMap<String, JsonElement> members = new LinkedTreeMap();

    /* Access modifiers changed, original: final */
    public final JsonObject deepCopy() {
        JsonObject jsonObject = new JsonObject();
        for (Entry entry : this.members.entrySet()) {
            jsonObject.add((String) entry.getKey(), ((JsonElement) entry.getValue()).deepCopy());
        }
        return jsonObject;
    }

    public final void add(String property, JsonElement value) {
        Object value2;
        if (value2 == null) {
            value2 = JsonNull.INSTANCE;
        }
        this.members.put(property, value2);
    }

    public final JsonElement remove(String property) {
        return (JsonElement) this.members.remove(property);
    }

    public final void addProperty(String property, String value) {
        add(property, createJsonElement(value));
    }

    public final void addProperty(String property, Number value) {
        add(property, createJsonElement(value));
    }

    public final void addProperty(String property, Boolean value) {
        add(property, createJsonElement(value));
    }

    public final void addProperty(String property, Character value) {
        add(property, createJsonElement(value));
    }

    private JsonElement createJsonElement(Object value) {
        return value == null ? JsonNull.INSTANCE : new JsonPrimitive(value);
    }

    public final Set<Entry<String, JsonElement>> entrySet() {
        return this.members.entrySet();
    }

    public final boolean has(String memberName) {
        return this.members.containsKey(memberName);
    }

    public final JsonElement get(String memberName) {
        return (JsonElement) this.members.get(memberName);
    }

    public final JsonPrimitive getAsJsonPrimitive(String memberName) {
        return (JsonPrimitive) this.members.get(memberName);
    }

    public final JsonArray getAsJsonArray(String memberName) {
        return (JsonArray) this.members.get(memberName);
    }

    public final JsonObject getAsJsonObject(String memberName) {
        return (JsonObject) this.members.get(memberName);
    }

    public final boolean equals(Object o) {
        return o == this || ((o instanceof JsonObject) && ((JsonObject) o).members.equals(this.members));
    }

    public final int hashCode() {
        return this.members.hashCode();
    }
}
