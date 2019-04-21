package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class DCSPolicy {
    public static final String DEFAULT_SOURCE_ID = "GMA";
    public static final String PRIVACY_POLICY_NAME = "PrivacyPolicyAcceptanceType";
    public static final String PRIVACY_POLICY_TYPE = "2";
    public static final String TERMS_AND_CONDITIONS_NAME = "TermsOfUseAcceptanceType";
    public static final String TERMS_AND_CONDITIONS_TYPE = "1";
    @SerializedName("acceptanceDate")
    public String acceptanceDate;
    @SerializedName("acceptanceInd")
    public String acceptanceInd;
    @SerializedName("channelId")
    public String channelId;
    @SerializedName("deviceId")
    public String deviceId;
    @SerializedName("isExpired")
    public boolean expired;
    @SerializedName("name")
    public String name;
    @SerializedName("sourceId")
    public String sourceId;
    @SerializedName("type")
    public String type;
    @SerializedName("version")
    public String version;
}
