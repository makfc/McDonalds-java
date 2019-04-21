package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWCustomerAccountData {
    @SerializedName("AccountID")
    public String accountID;
    @SerializedName("CustomerPaymentMethodId")
    public int customerPaymentMethodId;
    @SerializedName("IsPreferred")
    public boolean isPreferred;
    @SerializedName("IsValid")
    public Boolean isValid;
    @SerializedName("NickName")
    public String nickName;
    @SerializedName("PaymentMethodId")
    public int paymentMethodId;
    @SerializedName("SchemaId")
    public int schemaId;
}
