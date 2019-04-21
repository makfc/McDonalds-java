package com.mcdonalds.sdk.connectors.middleware.request;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mcdonalds.sdk.connectors.middleware.model.DCSPreference.DCSPreferenceDetails;
import com.mcdonalds.sdk.connectors.middleware.model.DCSPreference.EcpLegacyDCSPreferenceDetails;
import com.mcdonalds.sdk.connectors.middleware.model.DCSPreference.PreferredOfferCategoryDCSPreferenceDetails;
import com.mcdonalds.sdk.modules.models.NotificationPreferences;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.lang.reflect.Type;

public class PreferenceDetailsCustomTypeAdapter implements JsonDeserializer<DCSPreferenceDetails>, CustomTypeAdapter<DCSPreferenceDetails> {
    public Class<DCSPreferenceDetails> getType() {
        return DCSPreferenceDetails.class;
    }

    public Object getDeserializer() {
        return this;
    }

    public Object getSerializer() {
        return this;
    }

    public DCSPreferenceDetails deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        Gson gson = new Gson();
        try {
            Class cls;
            if (!json.getAsJsonObject().has(NotificationPreferences.PREFERENCE_ITEM_KEY_LEGACY_ID)) {
                cls = DCSPreferenceDetails.class;
                return (DCSPreferenceDetails) (!(gson instanceof Gson) ? gson.fromJson(json, cls) : GsonInstrumentation.fromJson(gson, json, cls));
            } else if (json.getAsJsonObject().get(NotificationPreferences.PREFERENCE_ITEM_KEY_LEGACY_ID).getAsString().equals("18")) {
                cls = PreferredOfferCategoryDCSPreferenceDetails.class;
                return (DCSPreferenceDetails) (!(gson instanceof Gson) ? gson.fromJson(json, cls) : GsonInstrumentation.fromJson(gson, json, cls));
            } else {
                cls = EcpLegacyDCSPreferenceDetails.class;
                return (DCSPreferenceDetails) (!(gson instanceof Gson) ? gson.fromJson(json, cls) : GsonInstrumentation.fromJson(gson, json, cls));
            }
        } catch (JsonParseException e) {
            return null;
        }
    }
}
