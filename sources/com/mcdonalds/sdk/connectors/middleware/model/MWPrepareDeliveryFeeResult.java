package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.OrderResponse;

public class MWPrepareDeliveryFeeResult {
    @SerializedName("Price")
    private Double mDeliveryAmount;

    public static OrderResponse toOrderResponse(MWPrepareDeliveryFeeResult result) {
        OrderResponse ret = new OrderResponse();
        ret.setDeliveryFee(result.getDeliveryAmount());
        return ret;
    }

    public Double getDeliveryAmount() {
        return this.mDeliveryAmount;
    }

    public void setDeliveryAmount(Double mDeliveryAmount) {
        this.mDeliveryAmount = mDeliveryAmount;
    }
}
