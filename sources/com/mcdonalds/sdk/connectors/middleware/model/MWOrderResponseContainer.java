package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWOrderResponseContainer {
    @SerializedName("ProductionResponse")
    private MWOrderResponseDetails orderResponseDetails;
}
