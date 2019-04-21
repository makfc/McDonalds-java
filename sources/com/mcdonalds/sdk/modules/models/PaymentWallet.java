package com.mcdonalds.sdk.modules.models;

import com.mcdonalds.sdk.modules.AppModel;
import java.util.List;

public class PaymentWallet extends AppModel {
    private List<CustomerPaymentAccount> mAccountItems;
    private List<PaymentCard> mCardItems;

    public List<PaymentCard> getCardItems() {
        return this.mCardItems;
    }

    public void setCardItems(List<PaymentCard> cardItems) {
        this.mCardItems = cardItems;
    }

    public List<CustomerPaymentAccount> getAccountItems() {
        return this.mAccountItems;
    }

    public void setAccountItems(List<CustomerPaymentAccount> accountItems) {
        this.mAccountItems = accountItems;
    }
}
