package com.mcdonalds.sdk.connectors.middleware.response;

import com.google.gson.annotations.SerializedName;

public class MWJSONResponse<T> {
    @SerializedName("Data")
    private T mData;
    @SerializedName("ResultCode")
    private int mResultCode;
    @SerializedName("statusCode")
    private int mStatusCode;

    public T getData() {
        return this.mData;
    }

    public void setData(T data) {
        this.mData = data;
    }

    public int getResultCode() {
        return this.mResultCode != 0 ? this.mResultCode : this.mStatusCode;
    }

    public void setResultCode(int resultCode) {
        this.mResultCode = resultCode;
    }

    public void setStatusCode(int statusCode) {
        this.mStatusCode = statusCode;
    }

    public String toString() {
        return "MWJSONResponse{mData=" + this.mData + ", mResultCode=" + this.mResultCode + "}";
    }
}
