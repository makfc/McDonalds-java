package com.mcdonalds.sdk.connectors.middleware.model;

public enum MWPointOfDistribution {
    FrontCounter(Integer.valueOf(0)),
    DriveThru(Integer.valueOf(1)),
    WalkThru(Integer.valueOf(2)),
    Delivery(Integer.valueOf(3)),
    ColdKiosk(Integer.valueOf(4)),
    McCafe(Integer.valueOf(5)),
    McExpress(Integer.valueOf(6)),
    ColdKioskDrink(Integer.valueOf(7)),
    CSO(Integer.valueOf(8)),
    HOT(Integer.valueOf(9));
    
    private final Integer mValue;

    private MWPointOfDistribution(Integer x) {
        this.mValue = x;
    }

    public Integer integerValue() {
        return this.mValue;
    }
}
