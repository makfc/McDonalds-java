package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import java.io.Serializable;

public class MWSocialNetwork implements Serializable {
    @SerializedName("CustomData")
    public Object customData;
    @SerializedName("IsValid")
    public boolean isValid;
    @SerializedName("SocialNetworkID")
    public Integer socialNetworkID;
    @SerializedName("SocialNetworkName")
    public String socialNetworkName;

    @Deprecated
    public static SocialNetwork toSocialNetwork(MWSocialNetwork socialNetwork) {
        return socialNetwork.toSocialNetwork();
    }

    public SocialNetwork toSocialNetwork() {
        if (this.socialNetworkID.intValue() <= 0) {
            return null;
        }
        SocialNetwork socialNetwork = new SocialNetwork(this.socialNetworkID.intValue(), this.socialNetworkID.intValue() < 5 ? this.socialNetworkID.intValue() : -1);
        socialNetwork.setSocialNetworkName(this.socialNetworkName);
        socialNetwork.setValid(this.isValid);
        return socialNetwork;
    }
}
