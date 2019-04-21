package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MWLanguage implements Serializable {
    @SerializedName("CultureAbbreviation")
    public String cultureAbbreviation;
    @SerializedName("IsValid")
    public boolean isValid;
    @SerializedName("LanguageID")
    public int languageID;
    @SerializedName("Name")
    public String name;
}
