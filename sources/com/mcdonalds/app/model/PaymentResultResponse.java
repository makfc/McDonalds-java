package com.mcdonalds.app.model;

import com.ensighten.Ensighten;
import com.google.gson.annotations.SerializedName;

public class PaymentResultResponse {
    @SerializedName("Data")
    private PaymentResult data;
    @SerializedName("ResultCode")
    private int resultCode;

    public PaymentResult getData() {
        Ensighten.evaluateEvent(this, "getData", null);
        return this.data;
    }
}
