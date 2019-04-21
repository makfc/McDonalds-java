package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.PaymentCard;

public class MWPaymentCardData extends MWCustomerAccountData {
    @SerializedName("CardAlias")
    public String cardAlias;
    @SerializedName("CardExpiration")
    public String cardExpiration;
    @SerializedName("CardHolderName")
    public String cardHolderName;
    @SerializedName("OneTimePayment")
    public boolean oneTimePayment;

    public PaymentCard toPaymentCard() {
        PaymentCard card = new PaymentCard();
        card.setIdentifier(Integer.valueOf(this.customerPaymentMethodId));
        card.setPaymentMethodId(Integer.valueOf(this.paymentMethodId));
        card.setAlias("Card ending in " + (this.cardAlias != null ? this.cardAlias : "").replace("*", ""));
        card.setExpiration(this.cardExpiration);
        card.setHolderName(this.cardHolderName);
        card.setNickName(this.nickName);
        card.setIsPreferred(Boolean.valueOf(this.isPreferred));
        card.setIsValid(this.isValid);
        card.setIsOneTimePayment(Boolean.valueOf(this.oneTimePayment));
        return card;
    }

    public static MWPaymentCardData fromPaymentCard(PaymentCard paymentCard) {
        MWPaymentCardData paymentCardData = new MWPaymentCardData();
        if (paymentCard != null) {
            paymentCardData.customerPaymentMethodId = paymentCard.getIdentifier().intValue();
            paymentCardData.paymentMethodId = paymentCard.getPaymentMethodId().intValue();
            paymentCardData.cardAlias = paymentCard.getAlias();
            paymentCardData.cardExpiration = paymentCard.getExpiration();
            paymentCardData.cardHolderName = paymentCard.getHolderName();
            paymentCardData.nickName = paymentCard.getNickName();
            paymentCardData.isPreferred = paymentCard.isPreferred().booleanValue();
            paymentCardData.isValid = paymentCard.getIsValid();
            paymentCardData.oneTimePayment = paymentCard.getIsOneTimePayment().booleanValue();
        }
        return paymentCardData;
    }

    @Deprecated
    public static PaymentCard toPaymentCard(MWPaymentCardData cardData) {
        if (cardData != null) {
            return cardData.toPaymentCard();
        }
        return new PaymentCard();
    }

    @Deprecated
    public static MWPaymentCardData toMWPaymentCardData(PaymentCard paymentCard) {
        return fromPaymentCard(paymentCard);
    }
}
