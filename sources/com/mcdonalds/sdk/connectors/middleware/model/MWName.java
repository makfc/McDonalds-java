package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Name;
import java.io.Serializable;

public class MWName implements Serializable {
    @SerializedName("LanguageID")
    public String languageID;
    @SerializedName("LongName")
    public String longName;
    @SerializedName("Name")
    public String name;
    @SerializedName("ShortName")
    public String shortName;

    public static Name toName(MWName ecpName) {
        Name ret = new Name();
        ret.setName(ecpName.name);
        ret.setLongName(ecpName.longName);
        ret.setShortName(ecpName.shortName);
        return ret;
    }
}
