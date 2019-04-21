package com.mcdonalds.app.customer.push;

import com.ensighten.Ensighten;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ExtendedData implements Serializable {
    @SerializedName("deepLinkingID")
    private Integer mDeepLinkingID;
    @SerializedName("offerID")
    private Integer mOfferID;

    public enum DeepLinkingType {
        Dashboard,
        OfferDetails
    }

    public Integer getDeepLinkingID() {
        Ensighten.evaluateEvent(this, "getDeepLinkingID", null);
        return this.mDeepLinkingID;
    }

    public Integer getOfferID() {
        Ensighten.evaluateEvent(this, "getOfferID", null);
        return this.mOfferID;
    }
}
