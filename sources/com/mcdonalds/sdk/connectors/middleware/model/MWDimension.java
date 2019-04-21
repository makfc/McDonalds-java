package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.ProductDimension;
import java.io.Serializable;

public class MWDimension implements Serializable {
    @SerializedName("ProductCode")
    public Integer productCode;
    @SerializedName("ShowSizeToCustomer")
    public Boolean showSizeToCustomer;
    @SerializedName("SizeCodeID")
    public Integer sizeCodeID;

    public ProductDimension toDimension() {
        ProductDimension dimension = new ProductDimension();
        dimension.setSizeCode(this.sizeCodeID);
        dimension.setShowSizeToCustomer(this.showSizeToCustomer.booleanValue());
        return dimension;
    }
}
