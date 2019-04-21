package com.mcdonalds.app.home;

import com.ensighten.Ensighten;
import com.google.gson.annotations.SerializedName;
import com.mcdonalds.app.model.Promo;
import java.io.Serializable;
import java.util.List;

public class PromoResponse implements Serializable {
    @SerializedName("promos")
    List<Promo> mPromoList;

    public String toString() {
        Ensighten.evaluateEvent(this, "toString", null);
        return "PromoResponse{mPromoList=" + this.mPromoList + "}";
    }
}
