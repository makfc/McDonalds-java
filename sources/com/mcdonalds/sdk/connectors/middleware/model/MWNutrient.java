package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Nutrient;
import java.io.Serializable;

public class MWNutrient implements Serializable {
    @SerializedName("adult_dv")
    public String adultDailyValue;
    @SerializedName("hundred_g_per_product")
    public String hundredG;
    @SerializedName("id")
    /* renamed from: id */
    public String f6069id;
    @SerializedName("name")
    public String name;
    @SerializedName("uom")
    public String unit;
    @SerializedName("value")
    public String value;

    public MWNutrient(String id, String unit, String value, String name) {
        this.f6069id = id;
        this.unit = unit;
        this.value = value;
        this.name = name;
    }

    public Nutrient toNutrient() {
        Nutrient nutrient = new Nutrient();
        nutrient.setId(this.f6069id);
        nutrient.setName(this.name);
        nutrient.setValue(this.value);
        nutrient.setUnit(this.unit);
        nutrient.setAdultDailyValue(this.adultDailyValue);
        nutrient.setHundredG(this.hundredG);
        return nutrient;
    }
}
