package com.mcdonalds.sdk.connectors.middleware.model;

import com.mcdonalds.sdk.modules.models.CustomerOrderProduct;
import com.mcdonalds.sdk.modules.models.OrderProduct;

public class MWProductViewCustomization extends MWProductView {
    public CustomerOrderProduct toCustomerOrderProduct() {
        CustomerOrderProduct ret = super.toCustomerOrderProduct();
        ret.setIsLight(Boolean.valueOf(this.isLight));
        return ret;
    }

    public MWProductViewBase populateWithOrder(OrderProduct orderProduct) {
        if (super.populateWithOrder(orderProduct) == null) {
            return null;
        }
        this.isLight = orderProduct.getIsLight();
        return this;
    }
}
