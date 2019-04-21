package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.OrderPayment;

public class MWCheckoutWorkAroundOrderPayment {
    @SerializedName("POD")
    public MWPointOfDistribution POD;
    @SerializedName("CustomerPaymentMethodId")
    public int customerPaymentMethodId;
    @SerializedName("OrderPaymentId")
    public String orderPaymentId;
    @SerializedName("PaymentDataId")
    public int paymentDataId;
    @SerializedName("PaymentMethodId")
    public int paymentMethodId;

    public static MWCheckoutWorkAroundOrderPayment fromOrderPayment(OrderPayment orderPayment) {
        MWCheckoutWorkAroundOrderPayment ret = new MWCheckoutWorkAroundOrderPayment();
        ret.POD = MWPointOfDistribution.values()[Integer.valueOf(orderPayment.getPOD() == null ? 0 : orderPayment.getPOD().integerValue().intValue()).intValue()];
        ret.customerPaymentMethodId = orderPayment.getCustomerPaymentMethodId();
        ret.orderPaymentId = orderPayment.getOrderPaymentId();
        ret.paymentMethodId = orderPayment.getPaymentMethodId();
        ret.paymentDataId = orderPayment.getPaymentDataId();
        return ret;
    }
}
