package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.OfferCategory;

public class MWOfferCategory {
    @SerializedName("Description")
    public String description;
    @SerializedName("Id")
    /* renamed from: id */
    public int f6071id;

    public static OfferCategory toOfferCategory(MWOfferCategory ecpOfferCategory) {
        OfferCategory ret = new OfferCategory();
        ret.setId(Integer.valueOf(ecpOfferCategory.f6071id));
        ret.setDescription(ecpOfferCategory.description);
        return ret;
    }
}
