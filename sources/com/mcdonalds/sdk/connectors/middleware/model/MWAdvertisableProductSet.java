package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MWAdvertisableProductSet {
    @SerializedName("Action")
    public Object action;
    @SerializedName("Alias")
    public String alias;
    @SerializedName("AnyProduct")
    public boolean anyProduct;
    @SerializedName("MaxUnitPrice")
    public double maxUnitPrice;
    @SerializedName("MaxUnitPriceAlias")
    public String maxUnitPriceAlias;
    @SerializedName("MinUnitPrice")
    public double minUnitPrice;
    @SerializedName("MinUnitPriceAlias")
    public String minUnitPriceAlias;
    @SerializedName("Products")
    public List<String> products;
    @SerializedName("PromoItem")
    public boolean promoItem;
    @SerializedName("Quantity")
    public int quantity;
    @SerializedName("SwapMapping")
    public List<MWAdvertisableSwapMapping> swapMapping;
}
