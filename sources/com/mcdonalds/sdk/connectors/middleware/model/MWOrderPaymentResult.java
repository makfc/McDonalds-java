package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;

public class MWOrderPaymentResult {
    @SerializedName("AccountID")
    public String accountID;
    @SerializedName("CardAlias")
    public String cardAlias;
    @SerializedName("CardExpiration")
    public String cardExpiration;
    @SerializedName("CardHolderName")
    public String cardHolderName;
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
