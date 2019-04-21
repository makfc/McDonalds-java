package com.mcdonalds.sdk.modules.models;

public enum OffersOperationType {
    NoOffers(0),
    OnlyMobile(1),
    AllModes(2),
    OnlyInStore(3);
    
    private final Integer mValue;

    private OffersOperationType(int type) {
        this.mValue = Integer.valueOf(type);
    }

    public int getValue() {
        return this.mValue.intValue();
    }
}
