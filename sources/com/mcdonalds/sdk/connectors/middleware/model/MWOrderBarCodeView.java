package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWOrderBarCodeView {
    @SerializedName("BarCode")
    public String barCode;
    @SerializedName("OrderView")
    public MWOrderViewResult orderViewResult;
}
