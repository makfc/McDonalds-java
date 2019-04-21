package com.mcdonalds.sdk.connectors.middlewarestorelocator.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MiddlewareStoreLocatorPhoneNumberList {
    @SerializedName("phonenumber")
    public List<MiddlewareStoreLocatorPhoneNumber> phoneNumberList;
}
