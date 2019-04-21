package com.mcdonalds.sdk.connectors.middleware.response;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.DeliveryStatusResponse;
import com.mcdonalds.sdk.utils.DateUtils;
import java.text.ParseException;

public class MWDeliveryStatusResponse {
    public static final int STATUS_DELIVERY_IN_PROGRESS = 300;
    public static final int STATUS_ORDER_CANCELLED = 1000;
    public static final int STATUS_ORDER_DELIVERED = 400;
    public static final int STATUS_ORDER_IN_PROGRESS = 200;
    public static final int STATUS_ORDER_RECEIVED = 50;
    @SerializedName("OrderNumber")
    public String orderNumber;
    @SerializedName("Status")
    public int status;
    @SerializedName("Timestamp")
    public String timestamp;
    @SerializedName("TimestampInStoreLocalTime")
    public String timestampInStoreLocalTime;

    public DeliveryStatusResponse toDeliveryStatusResponse() {
        DeliveryStatusResponse response = new DeliveryStatusResponse();
        response.setmOrderNumber(this.orderNumber);
        if (this.status < 200) {
            response.setStatus(1);
        } else if (this.status < 300) {
            response.setStatus(2);
        } else if (this.status < STATUS_ORDER_DELIVERED) {
            response.setStatus(3);
        } else if (this.status == 1000) {
            response.setStatus(5);
        } else {
            response.setStatus(4);
        }
        try {
            response.setTimestamp(DateUtils.parseFromISO8631(this.timestamp, true));
            response.setTimestampInStoreLocalTime(DateUtils.parseFromISO8631(this.timestampInStoreLocalTime, true));
        } catch (ParseException e) {
        }
        return response;
    }
}
