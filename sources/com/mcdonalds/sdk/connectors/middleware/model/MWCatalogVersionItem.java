package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.services.data.sync.CatalogVersionType;
import java.io.Serializable;

public class MWCatalogVersionItem implements Serializable {
    @SerializedName("Type")
    public CatalogVersionType type;
    @SerializedName("Version")
    public String version;

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this.type != ((MWCatalogVersionItem) o).type) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.type != null ? this.type.hashCode() : 0;
    }
}
