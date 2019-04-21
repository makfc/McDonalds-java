package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;

public class OfferAction extends AppModel implements Parcelable {
    public static final Creator<OfferAction> CREATOR = new C40591();
    private Double mAdjustedPriceForPrice;
    private Integer mDiscountType;
    private OfferRedemptionType mOfferRedemptionType;
    private String mPriceFromCode;
    private Double mValue;

    /* renamed from: com.mcdonalds.sdk.modules.models.OfferAction$1 */
    static class C40591 implements Creator<OfferAction> {
        C40591() {
        }

        public OfferAction createFromParcel(Parcel source) {
            return new OfferAction(source);
        }

        public OfferAction[] newArray(int size) {
            return new OfferAction[size];
        }
    }

    public Integer getDiscountType() {
        return this.mDiscountType;
    }

    public void setDiscountType(Integer discountType) {
        this.mDiscountType = discountType;
    }

    public String getPriceFromCode() {
        return this.mPriceFromCode;
    }

    public void setPriceFromCode(String priceFromCode) {
        this.mPriceFromCode = priceFromCode;
    }

    public OfferRedemptionType getOfferRedemptionType() {
        return this.mOfferRedemptionType;
    }

    public void setOfferRedemptionType(Integer offerRedemptionType) {
        this.mOfferRedemptionType = OfferRedemptionType.values()[offerRedemptionType.intValue()];
    }

    public Double getValue() {
        return this.mValue;
    }

    public void setValue(Double value) {
        this.mValue = value;
    }

    public void setOfferRedemptionType(OfferRedemptionType offerRedemptionType) {
        this.mOfferRedemptionType = offerRedemptionType;
    }

    public Double getAdjustedPriceForPrice() {
        return this.mAdjustedPriceForPrice;
    }

    public void setAdjustedPriceForPrice(Double adjustedPriceForPrice) {
        this.mAdjustedPriceForPrice = adjustedPriceForPrice;
    }

    public String toString() {
        return "OfferAction{mDiscountType=" + this.mDiscountType + ", mPriceFromCode=" + this.mPriceFromCode + ", mOfferRedemptionType=" + this.mOfferRedemptionType + ", mValue=" + this.mValue + ", mAdjustedPriceForPrice=" + this.mAdjustedPriceForPrice + "}";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mDiscountType);
        dest.writeString(this.mPriceFromCode);
        dest.writeInt(this.mOfferRedemptionType == null ? -1 : this.mOfferRedemptionType.ordinal());
        dest.writeValue(this.mValue);
        dest.writeValue(this.mAdjustedPriceForPrice);
    }

    protected OfferAction(Parcel in) {
        this.mDiscountType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mPriceFromCode = in.readString();
        int tmpMOfferRedemptionType = in.readInt();
        this.mOfferRedemptionType = tmpMOfferRedemptionType == -1 ? null : OfferRedemptionType.values()[tmpMOfferRedemptionType];
        this.mValue = (Double) in.readValue(Double.class.getClassLoader());
        this.mAdjustedPriceForPrice = (Double) in.readValue(Double.class.getClassLoader());
    }
}
