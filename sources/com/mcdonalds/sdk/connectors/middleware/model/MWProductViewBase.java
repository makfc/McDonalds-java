package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.CustomerOrderProduct;
import com.mcdonalds.sdk.modules.models.OrderProduct;

public class MWProductViewBase {
    @SerializedName("ChangeStatus")
    public int changeStatus;
    @SerializedName("IsLight")
    public boolean isLight;
    @SerializedName("IsPromotional")
    public boolean isPromotional;
    @SerializedName("ProductCode")
    public int productCode;
    @SerializedName("PromoQuantity")
    public int promoQuantity;
    @SerializedName("Promotion")
    public MWPromotion promotion;
    @SerializedName("Quantity")
    public int quantity;
    @SerializedName("TotalEnergy")
    public double totalEnergy;
    @SerializedName("TotalValue")
    public double totalValue;
    @SerializedName("UnitPrice")
    public double unitPrice;
    @SerializedName("ValidationErrorCode")
    public int validationErrorCode;

    public MWProductViewBase populateWithOrder(OrderProduct orderProduct) {
        if (orderProduct == null) {
            return null;
        }
        this.productCode = Integer.parseInt(orderProduct.getProductCode());
        this.quantity = orderProduct.getQuantity();
        return this;
    }

    public CustomerOrderProduct toCustomerOrderProduct() {
        CustomerOrderProduct ret = new CustomerOrderProduct();
        ret.setQuantity(Integer.valueOf(this.quantity));
        ret.setPromoQuantity(Integer.valueOf(this.promoQuantity));
        ret.setProductCode(Integer.valueOf(this.productCode));
        return ret;
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
    public int getQuantity() {
        return this.quantity;
    }

    @Deprecated
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Deprecated
    public boolean isLight() {
        return this.isLight;
    }

    @Deprecated
    public void setIsLight(boolean isLight) {
        this.isLight = isLight;
    }

    @Deprecated
    public double getUnitPrice() {
        return this.unitPrice;
    }

    @Deprecated
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Deprecated
    public double getTotalValue() {
        return this.totalValue;
    }

    @Deprecated
    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    @Deprecated
    public double getTotalEnergy() {
        return this.totalEnergy;
    }

    @Deprecated
    public void setTotalEnergy(double totalEnergy) {
        this.totalEnergy = totalEnergy;
    }

    @Deprecated
    public int getValidationErrorCode() {
        return this.validationErrorCode;
    }

    @Deprecated
    public void setValidationErrorCode(int validationErrorCode) {
        this.validationErrorCode = validationErrorCode;
    }

    @Deprecated
    public int getPromoQuantity() {
        return this.promoQuantity;
    }

    @Deprecated
    public void setPromoQuantity(int promoQuantity) {
        this.promoQuantity = promoQuantity;
    }

    @Deprecated
    public int getChangeStatus() {
        return this.changeStatus;
    }

    @Deprecated
    public void setChangeStatus(int changeStatus) {
        this.changeStatus = changeStatus;
    }

    @Deprecated
    public boolean isPromotional() {
        return this.isPromotional;
    }

    @Deprecated
    public void setIsPromotional(boolean isPromotional) {
        this.isPromotional = isPromotional;
    }

    @Deprecated
    public MWPromotion getPromotion() {
        return this.promotion;
    }

    @Deprecated
    public void setPromotion(MWPromotion promotion) {
        this.promotion = promotion;
    }
}
