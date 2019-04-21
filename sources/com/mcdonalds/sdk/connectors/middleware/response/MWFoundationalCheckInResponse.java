package com.mcdonalds.sdk.connectors.middleware.response;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.connectors.middleware.model.MWFulfillmentFacilityOpeningHour;
import com.mcdonalds.sdk.connectors.middleware.model.MWOrderViewResult;
import com.mcdonalds.sdk.modules.models.OrderView;

public class MWFoundationalCheckInResponse extends MWJSONResponse<MWOrderViewResult> {
    @SerializedName("OrderView")
    public MWOrderViewResult orderView;
    @SerializedName("PaymentDataId")
    public Integer paymentDataId;
    @SerializedName("PaymentUrl")
    public String paymentUrl;
    @SerializedName("RequireCVV")
    public Boolean requireCVV;
    @SerializedName("RequiresPassword")
    public Boolean requiresPassword;

    public static OrderView toOrderView(MWFoundationalCheckInResponse response) {
        MWOrderViewResult orderViewResult = response.orderView != null ? response.orderView : (MWOrderViewResult) response.getData();
        OrderView order = MWOrderViewResult.toOrderView(orderViewResult);
        order.setCheckInCode(orderViewResult.checkInCode);
        order.setOrderPaymentId(orderViewResult.orderPaymentId);
        if (!(orderViewResult.restaurant == null || orderViewResult.restaurant.restaurantInformation == null)) {
            order.setFacilityOpeningHours(MWFulfillmentFacilityOpeningHour.toFulfillmentFacilityOpeningHours(orderViewResult.restaurant.restaurantInformation.fulfilmentFacilityOpeningHours));
        }
        return order;
    }
}
