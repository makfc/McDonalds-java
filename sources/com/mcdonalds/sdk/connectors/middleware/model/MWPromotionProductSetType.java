package com.mcdonalds.sdk.connectors.middleware.model;

public enum MWPromotionProductSetType {
    Condition(Integer.valueOf(1)),
    Action(Integer.valueOf(2));
    
    private Integer mValue;

    private MWPromotionProductSetType(Integer x) {
        this.mValue = x;
    }

    public Integer integerValue() {
        return this.mValue;
    }
}
