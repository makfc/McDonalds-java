package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MWCatalogVersion implements Serializable {
    @SerializedName("Market")
    public List<MWCatalogVersionItem> market = new ArrayList();
    @SerializedName("Store")
    public List<MWStoreCatalogVersion> store = new ArrayList();
}
