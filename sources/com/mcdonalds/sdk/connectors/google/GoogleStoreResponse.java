package com.mcdonalds.sdk.connectors.google;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GoogleStoreResponse extends GoogleStore {
    @SerializedName("features")
    private List<GoogleStore> mGoogleStores;

    public List<GoogleStore> getGoogleStores() {
        return this.mGoogleStores;
    }

    public void setGoogleStores(List<GoogleStore> googleStores) {
        this.mGoogleStores = googleStores;
    }

    public boolean isSingleStore() {
        return "Feature".equals(getType());
    }
}
