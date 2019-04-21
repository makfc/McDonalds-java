package com.mcdonalds.app.ordering.basket;

import com.ensighten.Ensighten;
import com.mcdonalds.sdk.modules.models.ImageInfo;
import com.mcdonalds.sdk.modules.models.TimeRestriction;
import java.util.ArrayList;
import java.util.List;

public class BasketListItem {
    private Object mBasketItem;
    private Boolean mDividerHidden;
    private String mEnergyTotal;
    private Boolean mFooterHidden;
    private boolean mHasError;
    private String mHeaderDetailsText;
    private Boolean mHeaderHidden;
    private Boolean mHeaderIconHidden;
    private String mHeaderText;
    private ImageInfo mIconImage;
    private boolean mIsMealErrorItem;
    private boolean mIsMealHeaderNonErrorWarningItem;
    private boolean mIsNonProductOffer;
    private boolean mIsOffer;
    private boolean mIsOfferDelivery;
    private boolean mIsOfferPickup;
    private String mItemInstructions;
    private String mItemName;
    private double mItemUplift;
    private Boolean mMakeItAMealHidden;
    private List<String> mNonProductOfferNames = new ArrayList();
    private int mOfferPODErrorCode;
    private boolean mOfferUnavailable;
    private boolean mOutOfStock;
    int mPositionInMeal;
    private boolean mPriceChanged;
    private String mPriceTotal;
    private TimeRestriction mTimeRestriction;
    private List<TimeRestriction> mTimeRestrictions;
    private Boolean mTopPaddingHidden;
    private boolean mUnavailable;
    private String mUnavailablePODMessage;

    public boolean isSubtotalItem() {
        Ensighten.evaluateEvent(this, "isSubtotalItem", null);
        return false;
    }

    public Object getBasketItem() {
        Ensighten.evaluateEvent(this, "getBasketItem", null);
        return this.mBasketItem;
    }

    public void setBasketItem(Object basketItem) {
        Ensighten.evaluateEvent(this, "setBasketItem", new Object[]{basketItem});
        this.mBasketItem = basketItem;
    }

    public Boolean getTopPaddingHidden() {
        Ensighten.evaluateEvent(this, "getTopPaddingHidden", null);
        return this.mTopPaddingHidden;
    }

    public void setTopPaddingHidden(Boolean topPaddingHidden) {
        Ensighten.evaluateEvent(this, "setTopPaddingHidden", new Object[]{topPaddingHidden});
        this.mTopPaddingHidden = topPaddingHidden;
    }

    public Boolean getHeaderHidden() {
        Ensighten.evaluateEvent(this, "getHeaderHidden", null);
        return this.mHeaderHidden;
    }

    public void setHeaderHidden(Boolean headerHidden) {
        Ensighten.evaluateEvent(this, "setHeaderHidden", new Object[]{headerHidden});
        this.mHeaderHidden = headerHidden;
    }

    public Boolean getHeaderIconHidden() {
        Ensighten.evaluateEvent(this, "getHeaderIconHidden", null);
        return this.mHeaderIconHidden;
    }

    public void setHeaderIconHidden(Boolean headerIconHidden) {
        Ensighten.evaluateEvent(this, "setHeaderIconHidden", new Object[]{headerIconHidden});
        this.mHeaderIconHidden = headerIconHidden;
    }

    public String getHeaderText() {
        Ensighten.evaluateEvent(this, "getHeaderText", null);
        return this.mHeaderText;
    }

    public void setHeaderText(String headerText) {
        Ensighten.evaluateEvent(this, "setHeaderText", new Object[]{headerText});
        this.mHeaderText = headerText;
    }

    public String getHeaderDetailsText() {
        Ensighten.evaluateEvent(this, "getHeaderDetailsText", null);
        return this.mHeaderDetailsText;
    }

    public void setHeaderDetailsText(String headerText) {
        Ensighten.evaluateEvent(this, "setHeaderDetailsText", new Object[]{headerText});
        this.mHeaderDetailsText = headerText;
    }

    public Boolean getFooterHidden() {
        Ensighten.evaluateEvent(this, "getFooterHidden", null);
        return this.mFooterHidden;
    }

    public void setFooterHidden(Boolean footerHidden) {
        Ensighten.evaluateEvent(this, "setFooterHidden", new Object[]{footerHidden});
        this.mFooterHidden = footerHidden;
    }

    public Boolean getMakeItAMealHidden() {
        Ensighten.evaluateEvent(this, "getMakeItAMealHidden", null);
        return this.mMakeItAMealHidden;
    }

    public void setMakeItAMealHidden(Boolean makeItAMealHidden) {
        Ensighten.evaluateEvent(this, "setMakeItAMealHidden", new Object[]{makeItAMealHidden});
        this.mMakeItAMealHidden = makeItAMealHidden;
    }

    public String getItemName() {
        Ensighten.evaluateEvent(this, "getItemName", null);
        return this.mItemName;
    }

    public void setItemName(String itemName) {
        Ensighten.evaluateEvent(this, "setItemName", new Object[]{itemName});
        this.mItemName = itemName;
    }

    public String getItemInstructions() {
        Ensighten.evaluateEvent(this, "getItemInstructions", null);
        return this.mItemInstructions;
    }

    public void setItemInstructions(String itemInstructions) {
        Ensighten.evaluateEvent(this, "setItemInstructions", new Object[]{itemInstructions});
        this.mItemInstructions = itemInstructions;
    }

    public double getItemUplift() {
        Ensighten.evaluateEvent(this, "getItemUplift", null);
        return this.mItemUplift;
    }

    public void setItemUplift(double itemUplift) {
        Ensighten.evaluateEvent(this, "setItemUplift", new Object[]{new Double(itemUplift)});
        this.mItemUplift = itemUplift;
    }

    public String getEnergyTotal() {
        Ensighten.evaluateEvent(this, "getEnergyTotal", null);
        return this.mEnergyTotal;
    }

    public void setEnergyTotal(String energyTotal) {
        Ensighten.evaluateEvent(this, "setEnergyTotal", new Object[]{energyTotal});
        this.mEnergyTotal = energyTotal;
    }

    public String getPriceTotal() {
        Ensighten.evaluateEvent(this, "getPriceTotal", null);
        return this.mPriceTotal;
    }

    public void setPriceTotal(String priceTotal) {
        Ensighten.evaluateEvent(this, "setPriceTotal", new Object[]{priceTotal});
        this.mPriceTotal = priceTotal;
    }

    public ImageInfo getIconImage() {
        Ensighten.evaluateEvent(this, "getIconImage", null);
        return this.mIconImage;
    }

    public void setIconImage(ImageInfo iconImage) {
        Ensighten.evaluateEvent(this, "setIconImage", new Object[]{iconImage});
        this.mIconImage = iconImage;
    }

    public Boolean getDividerHidden() {
        Ensighten.evaluateEvent(this, "getDividerHidden", null);
        return this.mDividerHidden;
    }

    public void setDividerHidden(Boolean mDividerHidden) {
        Ensighten.evaluateEvent(this, "setDividerHidden", new Object[]{mDividerHidden});
        this.mDividerHidden = mDividerHidden;
    }

    public List<String> getNonProductOfferNames() {
        Ensighten.evaluateEvent(this, "getNonProductOfferNames", null);
        return this.mNonProductOfferNames;
    }

    public void setNonProductOfferNames(List<String> nonProductOfferNames) {
        Ensighten.evaluateEvent(this, "setNonProductOfferNames", new Object[]{nonProductOfferNames});
        this.mNonProductOfferNames = nonProductOfferNames;
    }

    public String getUnavailablePODMessage() {
        Ensighten.evaluateEvent(this, "getUnavailablePODMessage", null);
        return this.mUnavailablePODMessage;
    }

    public void setUnavailablePODMessage(String message) {
        Ensighten.evaluateEvent(this, "setUnavailablePODMessage", new Object[]{message});
        this.mUnavailablePODMessage = message;
    }

    public boolean hasError() {
        Ensighten.evaluateEvent(this, "hasError", null);
        return this.mHasError;
    }

    public void setHasError(boolean mHasError) {
        Ensighten.evaluateEvent(this, "setHasError", new Object[]{new Boolean(mHasError)});
        this.mHasError = mHasError;
    }

    public boolean isOutOfStock() {
        Ensighten.evaluateEvent(this, "isOutOfStock", null);
        return this.mOutOfStock;
    }

    public void setOutOfStock(boolean mOutOfStock) {
        Ensighten.evaluateEvent(this, "setOutOfStock", new Object[]{new Boolean(mOutOfStock)});
        this.mOutOfStock = mOutOfStock;
    }

    public boolean isUnavailable() {
        Ensighten.evaluateEvent(this, "isUnavailable", null);
        return this.mUnavailable;
    }

    public void setOfferUnavailable(boolean unavailable) {
        Ensighten.evaluateEvent(this, "setOfferUnavailable", new Object[]{new Boolean(unavailable)});
        this.mOfferUnavailable = unavailable;
    }

    public boolean isOfferUnavailable() {
        Ensighten.evaluateEvent(this, "isOfferUnavailable", null);
        return this.mOfferUnavailable;
    }

    public void setUnavailable(boolean mUnavailable) {
        Ensighten.evaluateEvent(this, "setUnavailable", new Object[]{new Boolean(mUnavailable)});
        this.mUnavailable = mUnavailable;
    }

    public boolean isPriceChanged() {
        Ensighten.evaluateEvent(this, "isPriceChanged", null);
        return this.mPriceChanged;
    }

    public void setPriceChanged(boolean priceChanged) {
        Ensighten.evaluateEvent(this, "setPriceChanged", new Object[]{new Boolean(priceChanged)});
        this.mPriceChanged = priceChanged;
    }

    public void setTimeRestriction(TimeRestriction mTimeRestriction) {
        Ensighten.evaluateEvent(this, "setTimeRestriction", new Object[]{mTimeRestriction});
        this.mTimeRestriction = mTimeRestriction;
    }

    public List<TimeRestriction> getTimeRestrictions() {
        Ensighten.evaluateEvent(this, "getTimeRestrictions", null);
        return this.mTimeRestrictions;
    }

    public void setTimeRestrictions(List<TimeRestriction> mTimeRestrictions) {
        Ensighten.evaluateEvent(this, "setTimeRestrictions", new Object[]{mTimeRestrictions});
        this.mTimeRestrictions = mTimeRestrictions;
    }

    public void setIsOfferDelivery(boolean isDelivery) {
        Ensighten.evaluateEvent(this, "setIsOfferDelivery", new Object[]{new Boolean(isDelivery)});
        this.mIsOfferDelivery = isDelivery;
    }

    public void setOfferIsPickup(boolean isPickup) {
        Ensighten.evaluateEvent(this, "setOfferIsPickup", new Object[]{new Boolean(isPickup)});
        this.mIsOfferPickup = isPickup;
    }

    public void setOfferIsNonProduct(boolean isNonProduct) {
        Ensighten.evaluateEvent(this, "setOfferIsNonProduct", new Object[]{new Boolean(isNonProduct)});
        this.mIsNonProductOffer = isNonProduct;
    }

    public boolean isNonProductOffer() {
        Ensighten.evaluateEvent(this, "isNonProductOffer", null);
        return this.mIsNonProductOffer;
    }

    public int getOfferPODErrorCode() {
        Ensighten.evaluateEvent(this, "getOfferPODErrorCode", null);
        return this.mOfferPODErrorCode;
    }

    public void setOfferPODErrorCode(int errorCode) {
        Ensighten.evaluateEvent(this, "setOfferPODErrorCode", new Object[]{new Integer(errorCode)});
        this.mOfferPODErrorCode = errorCode;
    }

    public void setOfferPriceChanged(boolean mIsOffer) {
        Ensighten.evaluateEvent(this, "setOfferPriceChanged", new Object[]{new Boolean(mIsOffer)});
        this.mIsOffer = mIsOffer;
    }

    public boolean isMealErrorItem() {
        Ensighten.evaluateEvent(this, "isMealErrorItem", null);
        return this.mIsMealErrorItem;
    }

    public void setMealErrorItem(boolean mealErrorItem) {
        Ensighten.evaluateEvent(this, "setMealErrorItem", new Object[]{new Boolean(mealErrorItem)});
        this.mIsMealErrorItem = mealErrorItem;
    }

    public boolean isMealHeaderNonErrorWarningItem() {
        Ensighten.evaluateEvent(this, "isMealHeaderNonErrorWarningItem", null);
        return this.mIsMealHeaderNonErrorWarningItem;
    }

    public void setMealHeaderNonErrorWarningItem(boolean mealHeaderNonErrorWarningItem) {
        Ensighten.evaluateEvent(this, "setMealHeaderNonErrorWarningItem", new Object[]{new Boolean(mealHeaderNonErrorWarningItem)});
        this.mIsMealHeaderNonErrorWarningItem = mealHeaderNonErrorWarningItem;
    }

    public int getPositionInMeal() {
        Ensighten.evaluateEvent(this, "getPositionInMeal", null);
        return this.mPositionInMeal;
    }

    public void setPositionInMeal(int positionInMeal) {
        Ensighten.evaluateEvent(this, "setPositionInMeal", new Object[]{new Integer(positionInMeal)});
        this.mPositionInMeal = positionInMeal;
    }
}
