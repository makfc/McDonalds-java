package com.mcdonalds.sdk.connectors.middleware.model;

import com.mcdonalds.sdk.modules.models.Order.PriceType;

public enum MWPriceType {
    Undefined(Integer.valueOf(0)),
    EatIn(Integer.valueOf(1)),
    TakeOut(Integer.valueOf(2)),
    Other(Integer.valueOf(3));
    
    private Integer mValue;

    public static MWPriceType fromOrderPriceType(PriceType priceType) {
        return values()[priceType.integerValue().intValue()];
    }

    private MWPriceType(Integer x) {
        this.mValue = x;
    }

    public Integer integerValue() {
        return this.mValue;
    }
}
