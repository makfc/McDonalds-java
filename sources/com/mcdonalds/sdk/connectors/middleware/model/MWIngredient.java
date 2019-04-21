package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Ingredient;
import java.io.Serializable;

public class MWIngredient implements Serializable {
    @SerializedName("ChargeTreshold")
    public int chargeTreshold;
    @SerializedName("DefaultQuantity")
    public int defaultQuantity;
    @SerializedName("DefaultSolution")
    public String defaultSolution;
    @SerializedName("CostInclusive")
    public boolean isCostInclusive;
    @SerializedName("IsCustomerFriendly")
    public boolean isCustomerFriendly;
    @SerializedName("MaxQuantity")
    public int maxQuantity;
    @SerializedName("MinQuantity")
    public int minQuantity;
    @SerializedName("ProductCode")
    public int productCode;
    @SerializedName("ReferencePriceProductCode")
    public int referencePriceProductCode;
    @SerializedName("RefundTreshold")
    public int refundTreshold;

    public Ingredient toIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setIsCustomerFriendly(this.isCustomerFriendly);
        ingredient.setMinQuantity(this.minQuantity);
        ingredient.setDefaultQuantity(this.defaultQuantity);
        ingredient.setMaxQuantity(this.maxQuantity);
        ingredient.setRefundThreshold(this.refundTreshold);
        ingredient.setChargeThreshold(this.chargeTreshold);
        ingredient.setCostInclusive(this.isCostInclusive);
        ingredient.setDefaultSolution(this.defaultSolution);
        ingredient.setReferencePriceProductCode(this.referencePriceProductCode);
        return ingredient;
    }
}
