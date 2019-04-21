package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class MWDeliveryProductViewComponent {
    @SerializedName("ProductCode")
    public Integer ProductCode;
    @SerializedName("Quantity")
    public Integer Quantity;
    @SerializedName("TotalValue")
    public Double TotalValue;
    @SerializedName("UnitPrice")
    public Double UnitPrice;
    @SerializedName("ValidationErrorCode")
    public Integer ValidationErrorCode;
    @SerializedName("ChoiceSolutions")
    public List<Object> choiceSolutions = new ArrayList();
    @SerializedName("Customizations")
    public MWDeliveryProductViewCustomization customizations;
    @SerializedName("DisplayApart")
    public Boolean displayApart;

    @Deprecated
    public Boolean getDisplayApart() {
        return this.displayApart;
    }

    @Deprecated
    public void setDisplayApart(Boolean displayApart) {
        this.displayApart = displayApart;
    }
}
