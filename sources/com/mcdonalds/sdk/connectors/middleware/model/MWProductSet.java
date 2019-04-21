package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MWProductSet {
    @SerializedName("Action")
    public MWOfferAction action;
    @SerializedName("Alias")
    public String alias;
    @SerializedName("AnyProduct")
    public boolean anyProduct;
    @SerializedName("CodesFromAlias")
    public String codesFromAlias;
    @SerializedName("MaxUnitPrice")
    public double maxUnitPrice;
    @SerializedName("MaxUnitPriceAlias")
    public String maxUnitPriceAlias;
    @SerializedName("MinUnitPrice")
    public double minUnitPrice;
    @SerializedName("MinUnitPriceAlias")
    public String minUnitPriceAlias;
    @SerializedName("Products")
    public List<Integer> products;
    @SerializedName("PromoItem")
    public boolean promoItem;
    @SerializedName("Quantity")
    public int quantity;
}
