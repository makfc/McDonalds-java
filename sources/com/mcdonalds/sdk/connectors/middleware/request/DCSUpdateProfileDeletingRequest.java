package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.model.DCSProfile;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;

public class DCSUpdateProfileDeletingRequest extends DCSUpdateProfileRequest {
    public DCSUpdateProfileDeletingRequest(DCSProfile profile) {
        super(profile);
    }

    public MethodType getMethodType() {
        return MethodType.DELETE;
    }
}
