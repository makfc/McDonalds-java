package com.mcdonalds.sdk.connectors.middlewarestorelocator.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class MiddlewareStoreServices implements Serializable {
    @SerializedName("dayofweekservice")
    public List<MiddlewareDayOfWeekService> dayOfWeekService;
}
