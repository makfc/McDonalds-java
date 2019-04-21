package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MWAccessData {
    @SerializedName("AppParameter")
    public List<MWAppParameter> appParameter;
    @SerializedName("CurrentVersionId")
    public String currentVersionId;
    @SerializedName("Token")
    public String token;
}
