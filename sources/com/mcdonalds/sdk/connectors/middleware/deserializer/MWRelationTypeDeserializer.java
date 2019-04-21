package com.mcdonalds.sdk.connectors.middleware.deserializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mcdonalds.sdk.connectors.middleware.model.MWMenuItemRelatedItem;
import com.mcdonalds.sdk.connectors.middleware.model.MWMenuItemRelatedItems;
import com.mcdonalds.sdk.connectors.middleware.model.MWMenuItemRelationType;
import com.mcdonalds.sdk.connectors.middleware.model.MWMenuItemRelationTypes;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MWRelationTypeDeserializer implements JsonDeserializer<MWMenuItemRelationTypes>, CustomTypeAdapter<MWMenuItemRelationTypes> {
    public static final String DISPLAY_ORDER = "display_order";
    /* renamed from: ID */
    public static final String f6680ID = "id";
    public static final String IS_DEFAULT = "is_default";
    public static final String LABEL = "label";
    public static final String RELATED_ITEM = "related_item";
    public static final String RELATED_ITEMS = "related_items";
    public static final String RELATION_TYPE = "relation_type";
    public static final String TYPE = "type";

    public MWMenuItemRelationTypes deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        MWMenuItemRelationTypes depMenuItemRelationTypes = new MWMenuItemRelationTypes();
        List<MWMenuItemRelationType> depMenuItemRelationTypeList = new ArrayList();
        JsonArray relation_type = new JsonArray();
        if (jsonElement.getAsJsonObject().get(RELATION_TYPE).isJsonObject()) {
            JsonObject relationType = jsonElement.getAsJsonObject().get(RELATION_TYPE).getAsJsonObject();
            relation_type = new JsonArray();
            relation_type.add(relationType);
        } else {
            relation_type = jsonElement.getAsJsonObject().getAsJsonArray(RELATION_TYPE);
        }
        Iterator it = relation_type.iterator();
        while (it.hasNext()) {
            JsonElement element = (JsonElement) it.next();
            MWMenuItemRelationType depMenuItemRelationType = new MWMenuItemRelationType();
            depMenuItemRelationType.type = element.getAsJsonObject().get("type").getAsString();
            JsonObject relatedItems = element.getAsJsonObject().getAsJsonObject(RELATED_ITEMS);
            JsonArray relatedItem = new JsonArray();
            if (relatedItems.get(RELATED_ITEM).isJsonObject()) {
                relatedItem.add(relatedItems.get(RELATED_ITEM).getAsJsonObject());
            } else {
                relatedItem = relatedItems.getAsJsonArray(RELATED_ITEM);
            }
            List<MWMenuItemRelatedItem> depMenuItemRelatedItems = new ArrayList();
            Iterator it2 = relatedItem.iterator();
            while (it2.hasNext()) {
                JsonElement jsonElement1 = (JsonElement) it2.next();
                MWMenuItemRelatedItem depMenuItemRelatedItem = new MWMenuItemRelatedItem();
                JsonObject object = jsonElement1.getAsJsonObject();
                depMenuItemRelatedItem.isDefault = Boolean.valueOf(object.get("is_default").getAsBoolean());
                depMenuItemRelatedItem.displayOrder = object.get("display_order").getAsInt();
                depMenuItemRelatedItem.f6068id = object.get("id").getAsString();
                depMenuItemRelatedItem.label = object.get("label").getAsString();
                depMenuItemRelatedItems.add(depMenuItemRelatedItem);
            }
            MWMenuItemRelatedItems itemRelatedItems = new MWMenuItemRelatedItems();
            itemRelatedItems.menuItemRelatedItemList = depMenuItemRelatedItems;
            depMenuItemRelationType.menuItemRelatedItems = itemRelatedItems;
            depMenuItemRelationTypeList.add(depMenuItemRelationType);
        }
        depMenuItemRelationTypes.setItemRelationType(depMenuItemRelationTypeList);
        return depMenuItemRelationTypes;
    }

    public Class<MWMenuItemRelationTypes> getType() {
        return MWMenuItemRelationTypes.class;
    }

    public Object getDeserializer() {
        return this;
    }

    public Object getSerializer() {
        return null;
    }
}
