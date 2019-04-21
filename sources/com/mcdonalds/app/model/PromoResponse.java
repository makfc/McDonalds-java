package com.mcdonalds.app.model;

import com.ensighten.Ensighten;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class PromoResponse implements Serializable {
    @SerializedName("promos")
    private List<Promo> mPromos;

    public List<Promo> getPromos() {
        Ensighten.evaluateEvent(this, "getPromos", null);
        return this.mPromos;
    }

    public String toString() {
        Ensighten.evaluateEvent(this, "toString", null);
        if (this.mPromos == null) {
            return "PromoResponse { empty }";
        }
        return String.format("PromoResponse { promos = %d }", new Object[]{Integer.valueOf(this.mPromos.size())});
    }
}
