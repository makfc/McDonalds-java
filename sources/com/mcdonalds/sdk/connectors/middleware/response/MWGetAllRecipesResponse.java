package com.mcdonalds.sdk.connectors.middleware.response;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.connectors.middleware.model.MWFullMenu;
import java.io.Serializable;

public class MWGetAllRecipesResponse implements Serializable {
    @SerializedName("full_menu")
    private MWFullMenu mFullMenu;

    public String toString() {
        return "MWGetAllRecipesResponse{mFullMenu=" + this.mFullMenu + '}';
    }

    public MWFullMenu getFullMenu() {
        return this.mFullMenu;
    }

    public boolean hasFullMenuItemList() {
        return (getFullMenu() == null || getFullMenu().fullMenuItems == null || getFullMenu().fullMenuItems.menuItemList == null) ? false : true;
    }
}
