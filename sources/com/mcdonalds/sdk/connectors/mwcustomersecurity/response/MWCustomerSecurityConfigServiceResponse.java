package com.mcdonalds.sdk.connectors.mwcustomersecurity.response;

import com.google.gson.annotations.SerializedName;

public class MWCustomerSecurityConfigServiceResponse {
    @SerializedName("Configuration")
    MWCustomerSecurityConfigServiceConfigurationResponse configuration;

    public MWCustomerSecurityConfigServiceConfigurationResponse getConfiguration() {
        return this.configuration;
    }

    public void setConfiguration(MWCustomerSecurityConfigServiceConfigurationResponse configuration) {
        this.configuration = configuration;
    }

    public String toString() {
        return "MWCustomerSecurityConfigServiceResponse{configuration=" + this.configuration + '}';
    }
}
