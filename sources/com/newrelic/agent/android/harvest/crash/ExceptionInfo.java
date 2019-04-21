package com.newrelic.agent.android.harvest.crash;

import com.newrelic.agent.android.agentdata.HexAttributes;
import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;

public class ExceptionInfo extends HarvestableObject {
    private String className;
    private String message;

    public ExceptionInfo(Throwable throwable) {
        if (throwable.getClass().getName().equalsIgnoreCase("com.newrelic.agent.android.unity.UnityException")) {
            this.className = throwable.toString();
        } else {
            this.className = throwable.getClass().getName();
        }
        if (throwable.getMessage() != null) {
            this.message = throwable.getMessage();
        } else {
            this.message = "";
        }
    }

    public String getClassName() {
        return this.className;
    }

    public String getMessage() {
        return this.message;
    }

    public JsonObject asJsonObject() {
        JsonObject data = new JsonObject();
        data.add("name", new JsonPrimitive(this.className != null ? this.className : ""));
        data.add(HexAttributes.HEX_ATTR_CAUSE, new JsonPrimitive(this.message != null ? this.message : ""));
        return data;
    }

    public static ExceptionInfo newFromJson(JsonObject jsonObject) {
        ExceptionInfo info = new ExceptionInfo();
        info.className = jsonObject.has("name") ? jsonObject.get("name").getAsString() : "";
        info.message = jsonObject.has(HexAttributes.HEX_ATTR_CAUSE) ? jsonObject.get(HexAttributes.HEX_ATTR_CAUSE).getAsString() : "";
        return info;
    }
}
