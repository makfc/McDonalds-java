package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWGeneratePaymentBarcodeResult {
    @SerializedName("BarCodeContent")
    public String barCodeContent;
    @SerializedName("QrCode")
    public String qrCode;
    @SerializedName("RandomCode")
    public String randomCode;
}
