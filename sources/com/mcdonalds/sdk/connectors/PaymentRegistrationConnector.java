package com.mcdonalds.sdk.connectors;

import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.CreditCard;

public interface PaymentRegistrationConnector {
    void saveCard(CustomerProfile customerProfile, int i, String str, String str2, boolean z, CreditCard creditCard, AsyncListener<String> asyncListener);

    void saveCard(String str, int i, String str2, String str3, boolean z, CreditCard creditCard, AsyncListener<String> asyncListener);
}
