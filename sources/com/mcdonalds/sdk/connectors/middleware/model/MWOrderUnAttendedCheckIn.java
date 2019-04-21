package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.OrderUnAttendedCheckIn;
import java.util.List;

public class MWOrderUnAttendedCheckIn {
    @SerializedName("OrderChangesAccepted")
    public boolean OrderChangesAccepted = false;
    @SerializedName("AdditionalPayments")
    public List<MWOrderPayment> additionalPayments;
    @SerializedName("checkInData")
    public String checkInData;
    @SerializedName("PriceType")
    public Integer mType;
    @SerializedName("OrderPayment")
    public MWOrderPayment orderPayment;
    @SerializedName("POSStoreNumber")
    public String storeId;

    public static MWOrderUnAttendedCheckIn fromUnAttendedCheckIn(OrderUnAttendedCheckIn orderUnAttendedCheckIn) {
        MWOrderUnAttendedCheckIn mwOrderCheckInUnAttended = new MWOrderUnAttendedCheckIn();
        mwOrderCheckInUnAttended.storeId = orderUnAttendedCheckIn.getStoreID();
        mwOrderCheckInUnAttended.checkInData = orderUnAttendedCheckIn.getCheckInData();
        mwOrderCheckInUnAttended.mType = orderUnAttendedCheckIn.getPriceType();
        mwOrderCheckInUnAttended.orderPayment = MWOrderPayment.fromOrderPayment(orderUnAttendedCheckIn.getOrderPayment());
        mwOrderCheckInUnAttended.OrderChangesAccepted = orderUnAttendedCheckIn.isPriceChaged();
        mwOrderCheckInUnAttended.additionalPayments = MWOrderPayment.fromOrderPayment(orderUnAttendedCheckIn.getAdditionalPayments());
        return mwOrderCheckInUnAttended;
    }
}
