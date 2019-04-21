package com.mcdonalds.sdk.connectors.mwcustomersecurity.response;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;

public class MWCustomerSecuritySocialProvidersResponse extends MWJSONResponse {
    @SerializedName("socialProviders")
    private MWCustomerSecuritySocialProvidersMarketResponse[] socialProivders;

    public MWCustomerSecuritySocialProvidersMarketResponse[] getSocialProviders() {
        return this.socialProivders;
    }

    public void setSocialProviders(MWCustomerSecuritySocialProvidersMarketResponse socialProviders) {
        this.socialProivders = this.socialProivders;
    }

    public String toString() {
        return "MWCustomerSecuritySocialProvidersResponse{socialProivders=" + this.socialProivders + '}';
    }
}
