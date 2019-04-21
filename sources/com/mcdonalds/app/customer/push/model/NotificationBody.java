package com.mcdonalds.app.customer.push.model;

import com.ensighten.Ensighten;
import com.google.gson.annotations.SerializedName;

public class NotificationBody {
    @SerializedName("description")
    private String description;
    @SerializedName("custom_content")
    private NotificationCustomContent mCustomContent;

    public NotificationCustomContent getCustomContent() {
        Ensighten.evaluateEvent(this, "getCustomContent", null);
        return this.mCustomContent;
    }

    public String getDescription() {
        Ensighten.evaluateEvent(this, "getDescription", null);
        return this.description;
    }
}
