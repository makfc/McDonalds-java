package com.mcdonalds.sdk.connectors.utils;

import android.util.SparseArray;
import java.io.Serializable;

public class SerializableSparseArray<T> extends SparseArray<T> implements Serializable, Cloneable {
    private static final long serialVersionUID = 5388757581035942403L;

    public SerializableSparseArray<T> clone() {
        return (SerializableSparseArray) super.clone();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SerializableSparseArray{");
        int initialLength = stringBuilder.length();
        int size = size();
        for (int i = 0; i < size; i++) {
            if (stringBuilder.length() > initialLength) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(keyAt(i)).append('=').append(valueAt(i));
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
