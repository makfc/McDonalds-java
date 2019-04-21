package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.CustomerOrderProduct;
import com.mcdonalds.sdk.modules.models.OrderProduct;

public class DCSCustomization extends DCSProductBase {
    @SerializedName("IsLight")
    public boolean isLight;

    public static DCSCustomization fromCustomerOrderProduct(CustomerOrderProduct customerOrderProduct) {
        DCSCustomization dcsCustomization = new DCSCustomization();
        dcsCustomization.populate(customerOrderProduct);
        dcsCustomization.isLight = customerOrderProduct.getIsLight().booleanValue();
        return dcsCustomization;
    }

    public CustomerOrderProduct toCustomerOrderProduct() {
        CustomerOrderProduct customerOrderProduct = super.toCustomerOrderProduct();
        customerOrderProduct.setIsLight(Boolean.valueOf(this.isLight));
        return customerOrderProduct;
    }

    public static DCSCustomization fromOrderProduct(OrderProduct orderProduct) {
        DCSCustomization dcsCustomization = new DCSCustomization();
        dcsCustomization.populate(orderProduct);
        dcsCustomization.isLight = orderProduct.getIsLight();
        return dcsCustomization;
    }

    public OrderProduct toOrderProduct() {
        OrderProduct orderProduct = super.toOrderProduct();
        orderProduct.setIsLight(this.isLight);
        return orderProduct;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        if (this.isLight != ((DCSCustomization) o).isLight) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (super.hashCode() * 31) + (this.isLight ? 1 : 0);
    }
}
