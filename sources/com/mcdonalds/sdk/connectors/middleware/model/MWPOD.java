package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Pod;
import java.io.Serializable;

public class MWPOD implements Serializable {
    @SerializedName("SaleTypeID")
    public Integer saleTypeID;
    @SerializedName("TypeName")
    public String typeName;

    public Pod toPod() {
        Pod pod = new Pod();
        pod.setSaleTypeId(this.saleTypeID.intValue());
        pod.setTypeName(this.typeName);
        return pod;
    }

    @Deprecated
    public Integer getSaleTypeID() {
        return this.saleTypeID;
    }

    @Deprecated
    public void setSaleTypeID(Integer saleTypeID) {
        this.saleTypeID = saleTypeID;
    }

    @Deprecated
    public String getTypeName() {
        return this.typeName;
    }

    @Deprecated
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
