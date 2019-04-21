package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWErrorResponseData<T> {
    @SerializedName("BackendResponse")
    public T backEndResponse;
    @SerializedName("Message")
    public String message;
}
