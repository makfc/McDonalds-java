package com.mcdonalds.sdk.connectors.google;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GoogleStoreGeometry {
    @SerializedName("coordinates")
    private List<Double> mCoordinates;
    @SerializedName("type")
    private String mType;

    public String getType() {
        return this.mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public List<Double> getCoordinates() {
        return this.mCoordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.mCoordinates = coordinates;
    }

    public Double getLatitude() {
        return (Double) this.mCoordinates.get(1);
    }

    public Double getLongitude() {
        return (Double) this.mCoordinates.get(0);
    }
}
