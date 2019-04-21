package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MWPaymentLabel implements Serializable {
    @SerializedName("LanguageID")
    public String languageID;
    @SerializedName("Name")
    public String name;
    @SerializedName("OptionName")
    public String optionName;
}
