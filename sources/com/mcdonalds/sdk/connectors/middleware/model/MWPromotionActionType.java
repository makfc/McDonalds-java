package com.mcdonalds.sdk.connectors.middleware.model;

public enum MWPromotionActionType {
    INVALID(Integer.valueOf(0)),
    PercentDiscount(Integer.valueOf(1)),
    AmountDiscount(Integer.valueOf(2)),
    FixedAmount(Integer.valueOf(3)),
    Promo(Integer.valueOf(4));
    
    private Integer mValue;

    private MWPromotionActionType(Integer x) {
        this.mValue = x;
    }

    public Integer integerValue() {
        return this.mValue;
    }
}
