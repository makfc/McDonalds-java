package com.mcdonalds.sdk.modules.paymentRegistration;

import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.connectors.ConnectorManager;
import com.mcdonalds.sdk.connectors.PaymentRegistrationConnector;
import com.mcdonalds.sdk.modules.BaseModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.CreditCard;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class PaymentRegistrationModule extends BaseModule {
    public static final String CONNECTOR_KEY = "connector";
    public static final String NAME = "paymentRegistration";
    private PaymentRegistrationConnector mConnector = ((PaymentRegistrationConnector) ConnectorManager.getConnector((String) Configuration.getSharedInstance().getValueForKey("modules.paymentRegistration.connector")));

    public void saveCard(String customerId, PaymentMethod paymentMethod, String providerUrl, boolean isOneTimePayment, CreditCard creditCard, AsyncListener<String> listener) {
        this.mConnector.saveCard(customerId, paymentMethod.getID().intValue(), providerUrl, paymentMethod.getRegistrationReturnURL(), isOneTimePayment, creditCard, (AsyncListener) listener);
    }

    public void saveCard(CustomerProfile customerProfile, PaymentMethod paymentMethod, String providerUrl, boolean isOneTimePayment, CreditCard creditCard, AsyncListener<String> listener) {
        this.mConnector.saveCard(customerProfile, paymentMethod.getID().intValue(), providerUrl, paymentMethod.getRegistrationReturnURL(), isOneTimePayment, creditCard, (AsyncListener) listener);
    }
}
