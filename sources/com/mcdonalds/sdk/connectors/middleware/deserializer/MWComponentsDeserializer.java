package com.mcdonalds.sdk.connectors.middleware.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mcdonalds.sdk.connectors.middleware.model.MWRecipeComponent;
import com.mcdonalds.sdk.connectors.middleware.model.MWRecipeComponents;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MWComponentsDeserializer implements JsonDeserializer<MWRecipeComponents>, CustomTypeAdapter<MWRecipeComponents> {
    public Class<MWRecipeComponents> getType() {
        return MWRecipeComponents.class;
    }

    public Object getDeserializer() {
        return this;
    }

    public Object getSerializer() {
        return null;
    }

    public MWRecipeComponents deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        MWRecipeComponents components = new MWRecipeComponents();
        Gson gson = new Gson();
        JsonElement component = jsonElement.getAsJsonObject().get("component");
        if (component instanceof JsonObject) {
            Class cls = MWRecipeComponent.class;
            MWRecipeComponent mwRecipeComponent = !(gson instanceof Gson) ? gson.fromJson(component, cls) : GsonInstrumentation.fromJson(gson, component, cls);
            components.recipeComponentList = Arrays.asList(new MWRecipeComponent[]{mwRecipeComponent});
            return components;
        } else if (!(component instanceof JsonArray)) {
            return null;
        } else {
            List<MWRecipeComponent> depRecipeComponents = new ArrayList();
            for (int i = 0; i < component.getAsJsonArray().size(); i++) {
                JsonElement item = (JsonObject) component.getAsJsonArray().get(i);
                Class cls2 = MWRecipeComponent.class;
                depRecipeComponents.add(!(gson instanceof Gson) ? gson.fromJson(item, cls2) : GsonInstrumentation.fromJson(gson, item, cls2));
            }
            components.recipeComponentList = depRecipeComponents;
            return components;
        }
    }
}
