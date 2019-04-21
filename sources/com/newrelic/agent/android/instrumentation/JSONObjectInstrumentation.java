package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONObjectInstrumentation {
    private static final ArrayList<String> categoryParams = new ArrayList(Arrays.asList(new String[]{"category", MetricCategory.class.getName(), "JSON"}));

    JSONObjectInstrumentation() {
    }

    @TraceConstructor
    public static JSONObject init(String json) throws JSONException {
        if (json == null) {
            throw new JSONException("Failed to initialize JSONObject: json string is null.");
        }
        try {
            TraceMachine.enterMethod(null, "JSONObject#<init>", categoryParams);
            JSONObject jsonObject = new JSONObject(json);
            TraceMachine.exitMethod();
            return jsonObject;
        } catch (JSONException e) {
            TraceMachine.exitMethod();
            throw e;
        }
    }

    @ReplaceCallSite(scope = "org.json.JSONObject")
    public static String toString(JSONObject jsonObject) {
        TraceMachine.enterMethod(null, "JSONObject#toString", categoryParams);
        String jsonString = jsonObject.toString();
        TraceMachine.exitMethod();
        return jsonString;
    }

    @ReplaceCallSite(scope = "org.json.JSONObject")
    public static String toString(JSONObject jsonObject, int indentFactor) throws JSONException {
        TraceMachine.enterMethod(null, "JSONObject#toString", categoryParams);
        try {
            String jsonString = jsonObject.toString(indentFactor);
            TraceMachine.exitMethod();
            return jsonString;
        } catch (JSONException e) {
            TraceMachine.exitMethod();
            throw e;
        }
    }
}
