package com.mcdonalds.app.analytics.datalayer.mapping;

import com.ensighten.Ensighten;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Methods;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataLayerTabMapping {
    private static final Map<String, String> MAP;

    public static String get(String key) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.analytics.datalayer.mapping.DataLayerTabMapping", "get", new Object[]{key});
        return (String) MAP.get(key);
    }

    static {
        Map<String, String> result = new LinkedHashMap();
        result.put("map", "StoreMapTab");
        result.put(Methods.LIST, "StoreListTab");
        MAP = Collections.unmodifiableMap(result);
    }
}
