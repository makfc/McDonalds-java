package com.mcdonalds.sdk.connectors.middlewarestorelocator.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MiddlewareDayOfWeekService implements Serializable {
    @SerializedName("dayOfWeek")
    public String dayOfWeek;
    @SerializedName("endTime")
    public String endTime;
    @SerializedName("service")
    public String service;
    @SerializedName("startTime")
    public String startTime;
}
