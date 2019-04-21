package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.modules.models.PaymentWallet;
import java.util.ArrayList;
import java.util.List;

public class MWPaymentWallet {
    @SerializedName("PaymentAccount")
    public List<MWPaymentAccount> paymentAccount;
    @SerializedName("PaymentCard")
    public List<MWPaymentCardData> paymentCard;

    public PaymentWallet toPaymentWallet() {
        PaymentWallet wallet = new PaymentWallet();
        wallet.setCardItems(new ArrayList());
        if (this.paymentCard != null) {
            for (MWPaymentCardData cardData : this.paymentCard) {
                PaymentCard card = MWPaymentCardData.toPaymentCard(cardData);
                if (card != null) {
                    wallet.getCardItems().add(card);
                }
            }
        }
        wallet.setAccountItems(new ArrayList());
        if (this.paymentAccount != null) {
            for (MWPaymentAccount account : this.paymentAccount) {
                wallet.getAccountItems().add(MWPaymentAccount.toCustomerPaymentAccount(account));
            }
        }
        return wallet;
    }
}
