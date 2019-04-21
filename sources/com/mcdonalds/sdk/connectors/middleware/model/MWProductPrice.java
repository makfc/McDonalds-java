package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class MWProductPrice implements Serializable {
    @SerializedName("Prices")
    public List<MWPrice> prices;
    @SerializedName("ProductCode")
    public int productCode;
}
