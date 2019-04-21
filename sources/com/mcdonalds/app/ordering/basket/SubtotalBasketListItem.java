package com.mcdonalds.app.ordering.basket;

import com.ensighten.Ensighten;

public class SubtotalBasketListItem extends BasketListItem {
    private double mDeliveryFee;
    private double mDeliveryFeeDiscount;
    private boolean mHideOfferUnavailableContainer;
    private boolean mIsDeliveryFeeOfferHidden;
    private boolean mIsDeliveryHidden;
    private boolean mIsNonProductOfferAvailable = false;
    private String mOfferName;
    private boolean mShowTotal = false;

    public boolean isSubtotalItem() {
        Ensighten.evaluateEvent(this, "isSubtotalItem", null);
        return true;
    }

    public boolean isDeliveryHidden() {
        Ensighten.evaluateEvent(this, "isDeliveryHidden", null);
        return this.mIsDeliveryHidden;
    }

    public void setDeliveryHidden(boolean isDeliveryHidden) {
        Ensighten.evaluateEvent(this, "setDeliveryHidden", new Object[]{new Boolean(isDeliveryHidden)});
        this.mIsDeliveryHidden = isDeliveryHidden;
    }

    public boolean isDeliveryFeeOfferHidden() {
        Ensighten.evaluateEvent(this, "isDeliveryFeeOfferHidden", null);
        return this.mIsDeliveryFeeOfferHidden;
    }

    public void setDeliveryFeeOfferHidden(boolean isDeliveryFeeOfferHidden) {
        Ensighten.evaluateEvent(this, "setDeliveryFeeOfferHidden", new Object[]{new Boolean(isDeliveryFeeOfferHidden)});
        this.mIsDeliveryFeeOfferHidden = isDeliveryFeeOfferHidden;
    }

    public void setShowTotal(boolean showTotal) {
        Ensighten.evaluateEvent(this, "setShowTotal", new Object[]{new Boolean(showTotal)});
        this.mShowTotal = showTotal;
    }

    public boolean isShowTotal() {
        Ensighten.evaluateEvent(this, "isShowTotal", null);
        return this.mShowTotal;
    }

    public boolean getHideOfferUnavailableContainer() {
        Ensighten.evaluateEvent(this, "getHideOfferUnavailableContainer", null);
        return this.mHideOfferUnavailableContainer;
    }

    public void setHideOfferUnavailableContainer(boolean isOfferUnavailableHidden) {
        Ensighten.evaluateEvent(this, "setHideOfferUnavailableContainer", new Object[]{new Boolean(isOfferUnavailableHidden)});
        this.mHideOfferUnavailableContainer = isOfferUnavailableHidden;
    }

    public double getDeliveryFee() {
        Ensighten.evaluateEvent(this, "getDeliveryFee", null);
        return this.mDeliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        Ensighten.evaluateEvent(this, "setDeliveryFee", new Object[]{new Double(deliveryFee)});
        this.mDeliveryFee = deliveryFee;
    }

    public double getDeliveryFeeDiscount() {
        Ensighten.evaluateEvent(this, "getDeliveryFeeDiscount", null);
        return this.mDeliveryFeeDiscount;
    }

    public void setDeliveryFeeDiscount(double deliveryFeeDiscount) {
        Ensighten.evaluateEvent(this, "setDeliveryFeeDiscount", new Object[]{new Double(deliveryFeeDiscount)});
        this.mDeliveryFeeDiscount = deliveryFeeDiscount;
    }

    public String getOfferName() {
        Ensighten.evaluateEvent(this, "getOfferName", null);
        return this.mOfferName;
    }

    public void setOfferName(String offerName) {
        Ensighten.evaluateEvent(this, "setOfferName", new Object[]{offerName});
        this.mOfferName = offerName;
    }

    public boolean getIsNonProductOfferAvailable() {
        Ensighten.evaluateEvent(this, "getIsNonProductOfferAvailable", null);
        return this.mIsNonProductOfferAvailable;
    }

    public void setIsNonProductOfferAvailable(boolean isNonProductOfferAvailable) {
        Ensighten.evaluateEvent(this, "setIsNonProductOfferAvailable", new Object[]{new Boolean(isNonProductOfferAvailable)});
        this.mIsNonProductOfferAvailable = isNonProductOfferAvailable;
    }
}
