package com.mcdonalds.sdk.utils;

import java.util.Collection;

public class ListUtils {
    public static boolean isEmpty(Collection list) {
        return list == null || list.isEmpty();
    }

    public static <T> void union(Collection<T> original, Collection<T> adding) {
        if (adding != original && adding != null && original != null) {
            adding.removeAll(original);
            original.addAll(adding);
        }
    }

    public static <T> void addIfNotNull(Collection<T> collection, T element) {
        if (element != null) {
            collection.add(element);
        }
    }
}
