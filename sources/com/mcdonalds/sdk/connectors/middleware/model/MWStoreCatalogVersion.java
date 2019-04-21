package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MWStoreCatalogVersion implements Serializable {
    @SerializedName("Catalog")
    public List<MWCatalogVersionItem> catalog = new ArrayList();
    @SerializedName("StoreId")
    public String storeId;

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MWStoreCatalogVersion that = (MWStoreCatalogVersion) o;
        if (this.storeId != null) {
            return this.storeId.equals(that.storeId);
        }
        if (that.storeId != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.storeId != null ? this.storeId.hashCode() : 0;
    }
}
