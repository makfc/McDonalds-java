package com.mcdonalds.app.util;

import com.ensighten.Ensighten;
import java.util.Collection;

public class ListUtils {
    public static boolean isNotEmpty(Collection collection) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ListUtils", "isNotEmpty", new Object[]{collection});
        return (collection == null || collection.isEmpty()) ? false : true;
    }

    public static boolean isEmpty(Collection collection) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ListUtils", "isEmpty", new Object[]{collection});
        return collection == null || collection.isEmpty();
    }

    public static boolean sameSize(Collection collection1, Collection collection2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.ListUtils", "sameSize", new Object[]{collection1, collection2});
        return (collection1 == null || collection2 == null || collection1.size() != collection2.size()) ? false : true;
    }
}
