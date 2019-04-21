package com.ensighten.google.gson;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class JsonArray extends JsonElement implements Iterable<JsonElement> {
    private final List<JsonElement> elements = new ArrayList();

    /* Access modifiers changed, original: final */
    public final JsonArray deepCopy() {
        JsonArray jsonArray = new JsonArray();
        for (JsonElement deepCopy : this.elements) {
            jsonArray.add(deepCopy.deepCopy());
        }
        return jsonArray;
    }

    public final void add(JsonElement element) {
        Object element2;
        if (element2 == null) {
            element2 = JsonNull.INSTANCE;
        }
        this.elements.add(element2);
    }

    public final void addAll(JsonArray array) {
        this.elements.addAll(array.elements);
    }

    public final JsonElement set(int index, JsonElement element) {
        return (JsonElement) this.elements.set(index, element);
    }

    public final boolean remove(JsonElement element) {
        return this.elements.remove(element);
    }

    public final JsonElement remove(int index) {
        return (JsonElement) this.elements.remove(index);
    }

    public final boolean contains(JsonElement element) {
        return this.elements.contains(element);
    }

    public final int size() {
        return this.elements.size();
    }

    public final Iterator<JsonElement> iterator() {
        return this.elements.iterator();
    }

    public final JsonElement get(int i) {
        return (JsonElement) this.elements.get(i);
    }

    public final Number getAsNumber() {
        if (this.elements.size() == 1) {
            return ((JsonElement) this.elements.get(0)).getAsNumber();
        }
        throw new IllegalStateException();
    }

    public final String getAsString() {
        if (this.elements.size() == 1) {
            return ((JsonElement) this.elements.get(0)).getAsString();
        }
        throw new IllegalStateException();
    }

    public final double getAsDouble() {
        if (this.elements.size() == 1) {
            return ((JsonElement) this.elements.get(0)).getAsDouble();
        }
        throw new IllegalStateException();
    }

    public final BigDecimal getAsBigDecimal() {
        if (this.elements.size() == 1) {
            return ((JsonElement) this.elements.get(0)).getAsBigDecimal();
        }
        throw new IllegalStateException();
    }

    public final BigInteger getAsBigInteger() {
        if (this.elements.size() == 1) {
            return ((JsonElement) this.elements.get(0)).getAsBigInteger();
        }
        throw new IllegalStateException();
    }

    public final float getAsFloat() {
        if (this.elements.size() == 1) {
            return ((JsonElement) this.elements.get(0)).getAsFloat();
        }
        throw new IllegalStateException();
    }

    public final long getAsLong() {
        if (this.elements.size() == 1) {
            return ((JsonElement) this.elements.get(0)).getAsLong();
        }
        throw new IllegalStateException();
    }

    public final int getAsInt() {
        if (this.elements.size() == 1) {
            return ((JsonElement) this.elements.get(0)).getAsInt();
        }
        throw new IllegalStateException();
    }

    public final byte getAsByte() {
        if (this.elements.size() == 1) {
            return ((JsonElement) this.elements.get(0)).getAsByte();
        }
        throw new IllegalStateException();
    }

    public final char getAsCharacter() {
        if (this.elements.size() == 1) {
            return ((JsonElement) this.elements.get(0)).getAsCharacter();
        }
        throw new IllegalStateException();
    }

    public final short getAsShort() {
        if (this.elements.size() == 1) {
            return ((JsonElement) this.elements.get(0)).getAsShort();
        }
        throw new IllegalStateException();
    }

    public final boolean getAsBoolean() {
        if (this.elements.size() == 1) {
            return ((JsonElement) this.elements.get(0)).getAsBoolean();
        }
        throw new IllegalStateException();
    }

    public final boolean equals(Object o) {
        return o == this || ((o instanceof JsonArray) && ((JsonArray) o).elements.equals(this.elements));
    }

    public final int hashCode() {
        return this.elements.hashCode();
    }
}
