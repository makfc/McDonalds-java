package com.mcdonalds.app.ordering.customization;

import com.ensighten.Ensighten;

public enum PortionQuantity {
    NONE(0, false),
    LIGHT(1, true),
    REGULAR(1, false),
    EXTRA(2, false);
    
    private boolean mIsLight;
    private int mQuantity;

    private PortionQuantity(int quantity, boolean isLight) {
        this.mQuantity = quantity;
        this.mIsLight = isLight;
    }

    public int getQuantity() {
        Ensighten.evaluateEvent(this, "getQuantity", null);
        return this.mQuantity;
    }

    public boolean isLight() {
        Ensighten.evaluateEvent(this, "isLight", null);
        return this.mIsLight;
    }

    static PortionQuantity withQuantityAndLight(int quantity, boolean isLight) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.customization.PortionQuantity", "withQuantityAndLight", new Object[]{new Integer(quantity), new Boolean(isLight)});
        PortionQuantity ret = NONE;
        switch (quantity) {
            case 0:
                return NONE;
            case 1:
                if (isLight) {
                    return LIGHT;
                }
                return REGULAR;
            case 2:
                return EXTRA;
            default:
                return ret;
        }
    }
}
