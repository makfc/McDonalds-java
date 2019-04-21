package com.mcdonalds.sdk.connectors.middleware.model;

import android.content.Context;
import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Catalog;
import com.mcdonalds.sdk.modules.models.StoreCatalog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MWCatalog implements Serializable {
    @SerializedName("Market")
    public MWMarket market;
    @SerializedName("Store")
    public List<MWStore> stores;

    public Catalog toCatalog(Context context) {
        Catalog catalog = new Catalog();
        catalog.setMarketCatalog(this.market.toMarketCatalog(context));
        List<StoreCatalog> appStores = new ArrayList();
        for (MWStore mwStore : this.stores) {
            appStores.add(mwStore.toStoreCatalog(this.market));
        }
        catalog.setStoreCatalog(appStores);
        return catalog;
    }
}
