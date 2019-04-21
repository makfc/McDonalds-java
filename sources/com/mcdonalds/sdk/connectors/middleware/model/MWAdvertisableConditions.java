package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MWAdvertisableConditions {
    @SerializedName("DayOfWeekConditions")
    public List<String> dayOfWeekConditions;
}
