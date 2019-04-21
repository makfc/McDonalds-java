package com.newrelic.agent.android.unity;

import java.util.HashMap;
import java.util.Map;

public class UnityEvent {
    private Map<String, Object> attributes = new HashMap();
    private String name;

    public UnityEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(String name, String value) {
        this.attributes.put(name, value);
    }

    public void addAttribute(String name, Double value) {
        this.attributes.put(name, value);
    }
}
