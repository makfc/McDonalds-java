package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.CustomerPaymentAccount;

public class MWPaymentAccount {
    @SerializedName("AccountID")
    public String accountID;
    @SerializedName("CustomerPaymentMethodId")
    public int customerPaymentMethodId;
    @SerializedName("IsExpired")
    public boolean isExpired;
    @SerializedName("IsPreferred")
    public boolean isPreferred;
    @SerializedName("NickName")
    public String nickName;
    @SerializedName("OneTimePayment")
    public boolean oneTimePayment;
    @SerializedName("PaymentMethodId")
    public int paymentMethodId;
    @SerializedName("PaymentMode")
    public String paymentMode;
    @SerializedName("PaymentModeId")
    public int paymentModeId;
    @SerializedName("SchemaId")
    public int schemaId;

    public static CustomerPaymentAccount toCustomerPaymentAccount(MWPaymentAccount account) {
        CustomerPaymentAccount ret = new CustomerPaymentAccount();
        if (account != null) {
            ret.setExpired(account.isExpired);
            ret.setPreferred(account.isPreferred);
            ret.setOneTimePayment(account.oneTimePayment);
            ret.setAccountId(account.accountID);
            ret.setCustomerPaymentMethodId(account.customerPaymentMethodId);
            ret.setPaymentMethodId(account.paymentMethodId);
            ret.setSchemaId(account.schemaId);
            ret.setNickName(account.nickName);
            ret.setPaymentMode(account.paymentMode);
        }
        return ret;
    }
}
