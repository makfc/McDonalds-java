package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.OrderProductionResponse;
import com.mcdonalds.sdk.modules.models.PointOfDistribution;

public class MWOrderProductionResponse {
    @SerializedName("DisplayOrderNumber")
    public String displayOrderNumber;
    @SerializedName("Major")
    public String major;
    @SerializedName("MdsOrderNumber")
    public String mdsOrderNumber;
    @SerializedName("Minor")
    public String minor;
    @SerializedName("POD")
    public MWPointOfDistribution pointOfDistribution;

    public static OrderProductionResponse toOrderProductionResponse(MWOrderProductionResponse ecpResponse) {
        OrderProductionResponse response = new OrderProductionResponse();
        MWPointOfDistribution POD = ecpResponse.pointOfDistribution;
        response.setPointOfDistribution(POD == null ? null : PointOfDistribution.values()[POD.integerValue().intValue()]);
        response.setMajor(ecpResponse.major);
        response.setMinor(ecpResponse.minor);
        response.setDisplayOrderNumber(ecpResponse.displayOrderNumber);
        response.setMdsOrderNumber(ecpResponse.mdsOrderNumber);
        return response;
    }
}
