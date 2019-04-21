package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class MWNameInfo implements Serializable {
    @SerializedName("IsValid")
    public boolean isValid;
    @SerializedName("Names")
    public List<MWName> names;
    @SerializedName("ProductCode")
    public int productCode;
}
