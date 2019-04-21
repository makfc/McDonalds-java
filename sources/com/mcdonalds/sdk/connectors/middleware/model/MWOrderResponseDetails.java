package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.OrderProductionResponse;
import com.mcdonalds.sdk.modules.models.PointOfDistribution;

public class MWOrderResponseDetails {
    @SerializedName("POD")
    public MWPointOfDistribution POD;
    @SerializedName("DisplayOrderNumber")
    public String displayOrderNumber;
    @SerializedName("Major")
    public String major;
    @SerializedName("MdsOrderNumber")
    public String mdsOrderNumber;
    @SerializedName("Minor")
    public String minor;
    @SerializedName("TotalDue")
    public double totalDue;
    @SerializedName("TotalEnergy")
    public int totalEnergy;

    public static OrderProductionResponse toOrderResponse(MWOrderResponseDetails details) {
        OrderProductionResponse response = new OrderProductionResponse();
        response.setDisplayOrderNumber(details.displayOrderNumber);
        response.setMajor(details.major);
        response.setMinor(details.minor);
        response.setMdsOrderNumber(details.mdsOrderNumber);
        MWPointOfDistribution POD = details.POD;
        response.setPointOfDistribution(POD == null ? null : PointOfDistribution.values()[POD.integerValue().intValue()]);
        return response;
    }
}
