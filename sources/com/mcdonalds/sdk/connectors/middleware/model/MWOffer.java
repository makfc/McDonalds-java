package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.util.Date;
import java.util.List;

public class MWOffer {
    @SerializedName("Archived")
    public boolean archived;
    @SerializedName("Conditions")
    public MWOfferConditions conditions;
    @SerializedName("CurrentPunch")
    public int currentPunch;
    @SerializedName("Duration")
    public int duration;
    @SerializedName("ExpirationChanged")
    public boolean expirationChanged;
    @SerializedName("Expired")
    public boolean expired;
    @SerializedName("Id")
    /* renamed from: id */
    public int f6070id;
    @SerializedName("ImageBaseName")
    public String imageBaseName;
    @SerializedName("LocalValidFrom")
    public Date localValidFrom;
    @SerializedName("LocalValidThru")
    public Date localValidThru;
    @SerializedName("LongDescription")
    public String longDescription;
    @SerializedName("Name")
    public String name;
    @SerializedName("OfferType")
    public int offerType;
    @SerializedName("OrderDiscount")
    public double orderDiscount;
    @SerializedName("OrderDiscountType")
    public Integer orderDiscountType;
    @SerializedName("ProductSets")
    public List<MWProductSet> productSets;
    @SerializedName("Redeemed")
    public boolean redeemed;
    @SerializedName("RedeemedAt")
    public Object redeemedAt;
    @SerializedName("Restaurants")
    public List<Integer> restaurants;
    @SerializedName("Selected")
    public boolean selected;
    @SerializedName("ShortDescription")
    public String shortDescription;
    @SerializedName("TotalPunch")
    public int totalPunch;
}
