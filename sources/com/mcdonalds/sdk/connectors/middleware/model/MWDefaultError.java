package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MWDefaultError implements Serializable {
    @SerializedName("code")
    public String code;
    @SerializedName("description")
    public String description;
}
