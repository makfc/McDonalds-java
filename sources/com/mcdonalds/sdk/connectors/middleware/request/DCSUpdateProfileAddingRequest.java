package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.model.DCSProfile;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;

public class DCSUpdateProfileAddingRequest extends DCSUpdateProfileRequest {
    public DCSUpdateProfileAddingRequest(DCSProfile profile) {
        super(profile);
    }

    public MethodType getMethodType() {
        return MethodType.PUT;
    }
}
