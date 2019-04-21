package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.io.Serializable;
import java.util.List;

public class MWMenuType implements Serializable {
    @SerializedName("Color")
    public String color;
    @SerializedName("Description")
    public String description;
    @SerializedName("DisplayImage")
    public String displayImage;
    @SerializedName("IsValid")
    public boolean isValid;
    @SerializedName("LastModification")
    public String lastModification;
    @SerializedName("MenuTypeID")
    public int menuTypeID;
    @SerializedName("Names")
    public List<MWName> names;
    @SerializedName("Sequence")
    public String sequence;
    @SerializedName("StaticsData")
    public List<Integer> staticsData;

    @Deprecated
    public static MenuType toMenuType(MWMenuType ecpMenuType, String languageID) {
        return ecpMenuType.toMenuType();
    }

    public MenuType toMenuType() {
        String languageId = Configuration.getSharedInstance().getCurrentLanguageTag();
        MenuType ret = new MenuType();
        ret.setID(this.menuTypeID);
        ret.setDescription(this.description);
        ret.setColor(this.color);
        ret.setDisplayImage(this.displayImage);
        ret.setIsValid(Boolean.valueOf(this.isValid));
        for (MWName ecpName : this.names) {
            if (ecpName.languageID.equals(languageId)) {
                ret.setName(ecpName.name);
                ret.setLongName(ecpName.longName);
                ret.setShortName(ecpName.shortName);
                break;
            }
        }
        ret.setSequence(this.sequence);
        ret.setStaticsData(this.staticsData);
        ret.setLastModification(this.lastModification);
        return ret;
    }
}
