package com.newrelic.agent.android.instrumentation;

import java.util.HashMap;
import java.util.Map;

public enum MetricCategory {
    NONE("None"),
    VIEW_LOADING("View Loading"),
    VIEW_LAYOUT("Layout"),
    DATABASE("Database"),
    IMAGE("Images"),
    JSON("JSON"),
    NETWORK("Network");
    
    private static final Map<String, MetricCategory> methodMap = null;
    private String categoryName;

    /* renamed from: com.newrelic.agent.android.instrumentation.MetricCategory$1 */
    static class C26241 extends HashMap<String, MetricCategory> {
        C26241() {
            put("onCreate", MetricCategory.VIEW_LOADING);
        }
    }

    static {
        methodMap = new C26241();
    }

    private MetricCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public static MetricCategory categoryForMethod(String fullMethodName) {
        if (fullMethodName == null) {
            return NONE;
        }
        String methodName = null;
        int hashIndex = fullMethodName.indexOf("#");
        if (hashIndex >= 0) {
            methodName = fullMethodName.substring(hashIndex + 1);
        }
        MetricCategory category = (MetricCategory) methodMap.get(methodName);
        if (category == null) {
            return NONE;
        }
        return category;
    }
}
