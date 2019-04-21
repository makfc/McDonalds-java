package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MWAvailability implements Serializable {
    @SerializedName("ProductCode")
    public int productCode;
}
