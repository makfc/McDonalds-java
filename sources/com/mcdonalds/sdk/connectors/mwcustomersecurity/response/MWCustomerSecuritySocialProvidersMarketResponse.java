package com.mcdonalds.sdk.connectors.mwcustomersecurity.response;

import com.google.gson.annotations.SerializedName;
import java.util.Arrays;

public class MWCustomerSecuritySocialProvidersMarketResponse {
    @SerializedName("market")
    private String market;
    @SerializedName("providers")
    private MWCustomerSecuritySocialProvidersDetailsResponse[] providerDetails;

    public String getMarket() {
        return this.market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public MWCustomerSecuritySocialProvidersDetailsResponse[] getProviderDetails() {
        return this.providerDetails;
    }

    public void setProviderDetails(MWCustomerSecuritySocialProvidersDetailsResponse[] providerDetails) {
        this.providerDetails = providerDetails;
    }

    public String toString() {
        return "MWCustomerSecuritySocialProvidersMarketResponse{market='" + this.market + '\'' + ", providerDetails=" + Arrays.toString(this.providerDetails) + '}';
    }
}
