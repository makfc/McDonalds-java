package com.mcdonalds.sdk.connectors.middlewarestorelocator.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MiddlewareStoreLocatorIdentifier {
    @SerializedName("gblnumber")
    public String GBLNumber;
    @SerializedName("storeIdentifier")
    public List<MiddlewareStoreLocatorStoreIdentifier> storeIdentifier;
}
