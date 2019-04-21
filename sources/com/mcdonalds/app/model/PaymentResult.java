package com.mcdonalds.app.model;

import com.ensighten.Ensighten;
import com.google.gson.annotations.SerializedName;

public class PaymentResult {
    @SerializedName("CustomerPaymentMethodID")
    private int cutomerPaymentMethodId;
    @SerializedName("OrderNumber")
    private String orderNumber;
    @SerializedName("PaymentDataId")
    private int paymentDataId;

    public String getOrderNumber() {
        Ensighten.evaluateEvent(this, "getOrderNumber", null);
        return this.orderNumber;
    }

    public int getPaymentDataId() {
        Ensighten.evaluateEvent(this, "getPaymentDataId", null);
        return this.paymentDataId;
    }

    public int getCutomerPaymentMethodId() {
        Ensighten.evaluateEvent(this, "getCutomerPaymentMethodId", null);
        return this.cutomerPaymentMethodId;
    }
}
