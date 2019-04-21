package com.mcdonalds.app.customer.push.model;

import com.ensighten.Ensighten;
import com.google.gson.annotations.SerializedName;

public class NotificationCustomContent {
    @SerializedName("_dId")
    private String deliveryId;
    @SerializedName("_mId")
    private String messageId;

    public String getMessageId() {
        Ensighten.evaluateEvent(this, "getMessageId", null);
        return this.messageId;
    }

    public String getDeliveryId() {
        Ensighten.evaluateEvent(this, "getDeliveryId", null);
        return this.deliveryId;
    }
}
