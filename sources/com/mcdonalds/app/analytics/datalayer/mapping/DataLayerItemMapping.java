package com.mcdonalds.app.analytics.datalayer.mapping;

import com.ensighten.Ensighten;
import com.mcdonalds.app.nutrition.NutritionCategoryListAdapter.ViewHolder;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataLayerItemMapping {
    private static final Map<String, String> MAP;

    public static String get(String key) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.analytics.datalayer.mapping.DataLayerItemMapping", "get", new Object[]{key});
        return (String) MAP.get(key);
    }

    static {
        Map<String, String> result = new LinkedHashMap();
        result.put(ViewHolder.class.getName(), "NutritionalRecipeItemPressed");
        MAP = Collections.unmodifiableMap(result);
    }
}
