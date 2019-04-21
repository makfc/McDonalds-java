package com.mcdonalds.sdk.connectors.middlewarestorelocator.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MiddlewareStoreLocatorAttribute {
    @SerializedName("attributeClass")
    public String attributeClass;
    @SerializedName("dayOfWeek")
    public List<String> daysOfWeek;
    @SerializedName("type")
    public String type;
}
