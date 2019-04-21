package com.mcdonalds.sdk.connectors.middleware.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mcdonalds.sdk.connectors.middleware.model.MWCategoryItems;
import com.mcdonalds.sdk.connectors.middleware.model.MWMenuItem;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MWNutritionCategoryItemDeserializer implements JsonDeserializer<MWCategoryItems>, CustomTypeAdapter<MWCategoryItems> {
    public Class<MWCategoryItems> getType() {
        return MWCategoryItems.class;
    }

    public Object getDeserializer() {
        return this;
    }

    public Object getSerializer() {
        return null;
    }

    public MWCategoryItems deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<MWMenuItem> resultList = new ArrayList();
        JsonElement jsonItem = json.getAsJsonObject().get("item");
        if (jsonItem.isJsonArray()) {
            Iterator it = jsonItem.getAsJsonArray().iterator();
            while (it.hasNext()) {
                resultList.add(context.deserialize((JsonElement) it.next(), MWMenuItem.class));
            }
        } else if (json.isJsonObject()) {
            resultList.add(context.deserialize(jsonItem, MWMenuItem.class));
        } else {
            throw new RuntimeException("Unexpected JSON type: " + jsonItem.getClass());
        }
        MWCategoryItems items = new MWCategoryItems();
        items.categoryItemList = resultList;
        return items;
    }
}
