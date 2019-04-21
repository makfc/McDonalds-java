package com.mcdonalds.sdk.connectors.google;

import com.google.gson.annotations.SerializedName;

public class GoogleStore {
    @SerializedName("geometry")
    private GoogleStoreGeometry mGeometry;
    @SerializedName("properties")
    private GoogleStoreProperties mProperties;
    @SerializedName("type")
    private String mType;

    public GoogleStoreGeometry getGeometry() {
        return this.mGeometry;
    }

    public void setGeometry(GoogleStoreGeometry geometry) {
        this.mGeometry = geometry;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public GoogleStoreProperties getProperties() {
        return this.mProperties;
    }

    public void setProperties(GoogleStoreProperties properties) {
        this.mProperties = properties;
    }
}
