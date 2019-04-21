package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.OrderPayment;
import java.util.ArrayList;
import java.util.List;

public class MWOrderPayment {
    @SerializedName("CVV")
    public String CVV;
    @SerializedName("POD")
    public MWPointOfDistribution POD;
    @SerializedName("CustomerPaymentMethodId")
    public Integer customerPaymentMethodId;
    @SerializedName("DeviceFingerprintID")
    public String deviceFingerPrintId;
    @SerializedName("IpAddress")
    public String ipAddress;
    @SerializedName("OrderPaymentId")
    public String orderPaymentId;
    @SerializedName("PaymentDataId")
    public Integer paymentDataId;
    @SerializedName("PaymentMethodId")
    public Integer paymentMethodId;

    public static MWOrderPayment fromOrderPayment(OrderPayment orderPayment) {
        MWOrderPayment ret = new MWOrderPayment();
        ret.POD = MWPointOfDistribution.values()[Integer.valueOf(orderPayment.getPOD() == null ? 0 : orderPayment.getPOD().integerValue().intValue()).intValue()];
        ret.customerPaymentMethodId = Integer.valueOf(orderPayment.getCustomerPaymentMethodId());
        ret.orderPaymentId = orderPayment.getOrderPaymentId();
        ret.paymentDataId = Integer.valueOf(orderPayment.getPaymentDataId());
        ret.paymentMethodId = Integer.valueOf(orderPayment.getPaymentMethodId());
        ret.CVV = orderPayment.getCVV();
        ret.ipAddress = orderPayment.getIpAddress();
        ret.deviceFingerPrintId = orderPayment.getDeviceFingerPrintId();
        return ret;
    }

    public static List<MWOrderPayment> fromOrderPayment(List<OrderPayment> orderPayments) {
        List<MWOrderPayment> mwOrderPayments = new ArrayList();
        for (OrderPayment orderPayment : orderPayments) {
            mwOrderPayments.add(fromOrderPayment(orderPayment));
        }
        return mwOrderPayments.size() > 0 ? mwOrderPayments : null;
    }
}
