package com.mcdonalds.sdk.connectors.middleware.response;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.connectors.middleware.model.MWAccessData;
import com.mcdonalds.sdk.connectors.middleware.model.MWCustomerData;

public class MWSignInData {
    @SerializedName("AccessData")
    private MWAccessData mAccessData;
    @SerializedName("Catalog")
    private Object mCatalog;
    @SerializedName("CatalogVersion")
    private Object mCatalogVersion;
    @SerializedName("CustomerData")
    private MWCustomerData mCustomerData;

    public MWAccessData getAccessData() {
        return this.mAccessData;
    }

    public void setAccessData(MWAccessData mAccessData) {
        this.mAccessData = mAccessData;
    }

    public MWCustomerData getCustomerData() {
        return this.mCustomerData;
    }

    public void setCustomerData(MWCustomerData customerData) {
        this.mCustomerData = customerData;
    }
}
