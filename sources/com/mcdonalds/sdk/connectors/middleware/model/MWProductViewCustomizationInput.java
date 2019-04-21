package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.OrderProduct;

public class MWProductViewCustomizationInput extends MWProductViewBaseInput {
    @SerializedName("IsLight")
    public boolean isLight;

    /* Access modifiers changed, original: 0000 */
    public MWProductViewBaseInput populateWithOrder(OrderProduct orderProduct) {
        if (super.populateWithOrder(orderProduct) == null) {
            return null;
        }
        this.isLight = orderProduct.getIsLight();
        return this;
    }
}
