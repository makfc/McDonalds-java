package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.services.configuration.AppParameters;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.io.Serializable;
import java.util.List;

public class MWDisplayCategory implements Serializable {
    @SerializedName("CategoryDisplayOrder")
    public int categoryDisplayOrder;
    @SerializedName("Colors")
    public String colors;
    @SerializedName("DisplayCategoryID")
    public int displayCategoryID;
    @SerializedName("DisplayImageName")
    public String displayImageName;
    @SerializedName("ExtendedMenuTypeID")
    public Integer extendedMenuTypeID;
    @SerializedName("FromTime")
    public String fromTime;
    @SerializedName("IsValid")
    public boolean isValid;
    @SerializedName("MenuTypeID")
    public Integer menuTypeID;
    @SerializedName("Names")
    public List<MWName> names;
    @SerializedName("ParentDisplayCategoryID")
    public int parentDisplayCategoryID;
    @SerializedName("StaticData")
    public List<Integer> staticData;
    @SerializedName("ToTime")
    public String toTime;

    @Deprecated
    public static Category toCategory(MWDisplayCategory displayCategory) {
        return displayCategory.toCategory();
    }

    public Category toCategory() {
        Category category = new Category();
        category.setID(this.displayCategoryID);
        category.setDisplayOrder(this.categoryDisplayOrder);
        category.setName("");
        if (this.names != null && this.names.size() > 0) {
            String currentLanguage = Configuration.getSharedInstance().getCurrentLanguageTag();
            for (MWName name : this.names) {
                if (name.longName != null && name.languageID.equals(currentLanguage)) {
                    category.setName(name.longName.replaceAll("(\\\\r|\\\\t)", ""));
                    break;
                }
            }
        }
        if (AppParameters.getBooleanForParameter(AppParameters.ENABLE_MULTIPLE_MENU_TYPES) && this.extendedMenuTypeID != null) {
            category.setType(this.extendedMenuTypeID.intValue());
        } else if (this.menuTypeID != null) {
            category.setType(this.menuTypeID.intValue());
        } else {
            category.setType(-1);
        }
        category.setParentDisplayCategoryID(this.parentDisplayCategoryID);
        return category;
    }
}
