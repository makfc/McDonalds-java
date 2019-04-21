package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Allergen;

public class MWAllergen {
    @SerializedName("id")
    /* renamed from: id */
    public int f6065id;
    @SerializedName("name")
    public String name;
    @SerializedName("value")
    public String value;

    public Allergen toAllergen() {
        Allergen allergen = new Allergen();
        allergen.setId(this.f6065id);
        allergen.setName(this.name);
        allergen.setValue(this.value);
        return allergen;
    }
}
