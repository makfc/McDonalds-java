package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MWStaticData implements Serializable {
    @SerializedName("URL")
    public String URL;
    @SerializedName("IsValid")
    public boolean isValid;
    @SerializedName("LanguageID")
    public String languageID;
    @SerializedName("LastModification")
    public String lastModification;
    @SerializedName("StaticDataID")
    public int staticDataID;
    @SerializedName("StaticDataTypeID")
    public int staticDataTypeID;
}
