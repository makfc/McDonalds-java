package com.mcdonalds.app.ordering;

import com.ensighten.Ensighten;

public class OrderingDisclaimerItem {
    private String mDescription;
    private boolean mHasBasketView;
    private boolean mHasMenuGridView;
    private boolean mHasOrderSummaryView;
    private boolean mHasProductView;
    private boolean mUseBoldText;
    private boolean mUseThemeColor;

    public OrderingDisclaimerItem(String description, boolean useBoldText, boolean useThemeColor, boolean hasProductView, boolean hasBasketView, boolean hasMenuGridView, boolean hasOrderSummaryView) {
        this.mDescription = description;
        this.mUseBoldText = useBoldText;
        this.mUseThemeColor = useThemeColor;
        this.mHasProductView = hasProductView;
        this.mHasBasketView = hasBasketView;
        this.mHasMenuGridView = hasMenuGridView;
        this.mHasOrderSummaryView = hasOrderSummaryView;
    }

    public String getDescription() {
        Ensighten.evaluateEvent(this, "getDescription", null);
        return this.mDescription;
    }

    public boolean useBoldText() {
        Ensighten.evaluateEvent(this, "useBoldText", null);
        return this.mUseBoldText;
    }

    public boolean useThemeColor() {
        Ensighten.evaluateEvent(this, "useThemeColor", null);
        return this.mUseThemeColor;
    }

    public boolean hasProductView() {
        Ensighten.evaluateEvent(this, "hasProductView", null);
        return this.mHasProductView;
    }

    public boolean hasBasketView() {
        Ensighten.evaluateEvent(this, "hasBasketView", null);
        return this.mHasBasketView;
    }

    public boolean hasOrderSummaryView() {
        Ensighten.evaluateEvent(this, "hasOrderSummaryView", null);
        return this.mHasOrderSummaryView;
    }

    public boolean hasMenuGridView() {
        Ensighten.evaluateEvent(this, "hasMenuGridView", null);
        return this.mHasMenuGridView;
    }
}
