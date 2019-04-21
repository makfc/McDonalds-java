package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.OrderProduct;

public class MWProductViewBaseInput {
    @SerializedName("ProductCode")
    public int productCode;
    @SerializedName("Quantity")
    public int quantity;

    /* Access modifiers changed, original: 0000 */
    public MWProductViewBaseInput populateWithOrder(OrderProduct orderProduct) {
        if (orderProduct == null) {
            return null;
        }
        this.productCode = Integer.parseInt(orderProduct.getProductCode());
        this.quantity = orderProduct.getQuantity();
        return this;
    }
}
