package com.mcdonalds.sdk.connectors.middleware.response;

import com.google.gson.annotations.SerializedName;

public class MWKioskCheckin {
    @SerializedName("Barcode")
    private String mBarcode;
    @SerializedName("RandomCode")
    private String mRandomCode;

    public String getBarcode() {
        return this.mBarcode;
    }

    public String getRandomCode() {
        return this.mRandomCode;
    }
}
