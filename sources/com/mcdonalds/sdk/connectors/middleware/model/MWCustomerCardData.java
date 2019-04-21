package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWCustomerCardData {
    @SerializedName("CustomerPaymentMethodId")
    public int customerPaymentMethodId;
    @SerializedName("IsPreferred")
    public boolean isPreferred;
    @SerializedName("IsValid")
    public boolean isValid;
    @SerializedName("NickName")
    public String nickName;

    public static MWCustomerCardData fromPaymentCardData(MWPaymentCardData cardData) {
        if (cardData == null) {
            return null;
        }
        MWCustomerCardData customerCardData = new MWCustomerCardData();
        customerCardData.customerPaymentMethodId = cardData.customerPaymentMethodId;
        customerCardData.isPreferred = cardData.isPreferred;
        customerCardData.nickName = cardData.nickName;
        customerCardData.isValid = cardData.isValid.booleanValue();
        return customerCardData;
    }
}
