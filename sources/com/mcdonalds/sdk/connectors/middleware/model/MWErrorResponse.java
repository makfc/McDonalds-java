package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWErrorResponse<T> {
    @SerializedName("Data")
    public MWErrorResponseData<T> data;
    @SerializedName("ResultCode")
    public String ecpResultCode;
}
