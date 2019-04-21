package com.mcdonalds.sdk.services.configuration;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.ExpectedTenderType;
import java.util.List;

public class DeliveryConfig {
    @SerializedName("connector")
    public String connector;
    @SerializedName("expectedTenderTypes")
    public List<ExpectedTenderType> expectedTenderTypes;
    @SerializedName("externalAddressProvider")
    public String externalAddressProvider;
}
