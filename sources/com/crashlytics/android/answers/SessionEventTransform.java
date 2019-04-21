package com.crashlytics.android.answers;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import p041io.fabric.sdk.android.services.events.EventTransform;

class SessionEventTransform implements EventTransform<SessionEvent> {
    SessionEventTransform() {
    }

    public byte[] toBytes(SessionEvent event) throws IOException {
        JSONObject buildJsonForEvent = buildJsonForEvent(event);
        return (!(buildJsonForEvent instanceof JSONObject) ? buildJsonForEvent.toString() : JSONObjectInstrumentation.toString(buildJsonForEvent)).getBytes(Utf8Charset.NAME);
    }

    @TargetApi(9)
    public JSONObject buildJsonForEvent(SessionEvent event) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject();
            SessionEventMetadata eventMetadata = event.sessionEventMetadata;
            jsonObject.put("appBundleId", eventMetadata.appBundleId);
            jsonObject.put("executionId", eventMetadata.executionId);
            jsonObject.put("installationId", eventMetadata.installationId);
            jsonObject.put("limitAdTrackingEnabled", eventMetadata.limitAdTrackingEnabled);
            jsonObject.put("betaDeviceToken", eventMetadata.betaDeviceToken);
            jsonObject.put("buildId", eventMetadata.buildId);
            jsonObject.put(AnalyticAttribute.OS_VERSION_ATTRIBUTE, eventMetadata.osVersion);
            jsonObject.put(AnalyticAttribute.DEVICE_MODEL_ATTRIBUTE, eventMetadata.deviceModel);
            jsonObject.put("appVersionCode", eventMetadata.appVersionCode);
            jsonObject.put("appVersionName", eventMetadata.appVersionName);
            jsonObject.put(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE, event.timestamp);
            jsonObject.put("type", event.type.toString());
            if (event.details != null) {
                jsonObject.put("details", new JSONObject(event.details));
            }
            jsonObject.put("customType", event.customType);
            if (event.customAttributes != null) {
                jsonObject.put("customAttributes", new JSONObject(event.customAttributes));
            }
            jsonObject.put("predefinedType", event.predefinedType);
            if (event.predefinedAttributes != null) {
                jsonObject.put("predefinedAttributes", new JSONObject(event.predefinedAttributes));
            }
            return jsonObject;
        } catch (JSONException e) {
            if (VERSION.SDK_INT >= 9) {
                throw new IOException(e.getMessage(), e);
            }
            throw new IOException(e.getMessage());
        }
    }
}
