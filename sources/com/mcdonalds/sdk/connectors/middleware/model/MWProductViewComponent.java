package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.OrderProduct;

public class MWProductViewComponent extends MWProductView {
    @SerializedName("DisplayApart")
    public Boolean displayApart;

    public MWProductViewBase populateWithOrder(OrderProduct orderProduct) {
        if (super.populateWithOrder(orderProduct) == null) {
            return null;
        }
        this.displayApart = null;
        return this;
    }

    @Deprecated
    public Boolean getDisplayApart() {
        return this.displayApart;
    }

    @Deprecated
    public void setDisplayApart(Boolean displayApart) {
        this.displayApart = displayApart;
    }
}
