package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Promotion;
import java.io.Serializable;
import java.util.List;

public class MWPromotion implements Serializable {
    @SerializedName("ActionName")
    public String ActionName;
    @SerializedName("DiscountAmount")
    public Double discountAmount;
    @SerializedName("DisplayImageName")
    public String displayImageName;
    @SerializedName("IsValid")
    public boolean isValid;
    @SerializedName("OriginalPrice")
    public double originalPrice;
    @SerializedName("ProductCode")
    public int productCode;
    @SerializedName("PromotionID")
    public int promotionID;
    @SerializedName("StaticData")
    public List<Integer> staticData;

    public Promotion toPromotion() {
        Promotion promotion = new Promotion();
        promotion.setPromotionID(Integer.valueOf(this.promotionID));
        promotion.setProductCode(Integer.valueOf(this.productCode));
        promotion.setOriginalPrice(Double.valueOf(this.originalPrice));
        promotion.setDiscountAmount(this.discountAmount);
        promotion.setIsValid(Boolean.valueOf(this.isValid));
        promotion.setDisplayImageName(this.displayImageName);
        promotion.setStaticData(this.staticData);
        promotion.setActionName(this.ActionName);
        return promotion;
    }

    @Deprecated
    public String getActionName() {
        return this.ActionName;
    }

    @Deprecated
    public void setActionName(String actionName) {
        this.ActionName = actionName;
    }

    @Deprecated
    public int getPromotionID() {
        return this.promotionID;
    }

    @Deprecated
    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }

    @Deprecated
    public int getProductCode() {
        return this.productCode;
    }

    @Deprecated
    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    @Deprecated
    public double getOriginalPrice() {
        return this.originalPrice;
    }

    @Deprecated
    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    @Deprecated
    public Double getDiscountAmount() {
        return this.discountAmount;
    }

    @Deprecated
    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Deprecated
    public boolean isValid() {
        return this.isValid;
    }

    @Deprecated
    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    @Deprecated
    public String getDisplayImageName() {
        return this.displayImageName;
    }

    @Deprecated
    public void setDisplayImageName(String displayImageName) {
        this.displayImageName = displayImageName;
    }

    @Deprecated
    public List<Integer> getStaticData() {
        return this.staticData;
    }

    @Deprecated
    public void setStaticData(List<Integer> staticData) {
        this.staticData = staticData;
    }
}
