package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;
import java.util.ArrayList;
import java.util.List;

public class OfferProduct extends AppModel implements Parcelable {
    public static final Creator<OfferProduct> CREATOR = new C40621();
    private OfferAction mAction;
    private String mAlias;
    private Boolean mAnyProduct;
    private String mCodesFromAlias;
    private Boolean mExpired;
    private Double mMaxUnitPrice;
    private String mMaxUnitPriceAlias;
    private Double mMinUnitPrice;
    private String mMinUnitPriceAlias;
    private List<OfferProductOption> mProducts;
    private Boolean mPromoItem;
    private Integer mQuantity;
    private OfferProductOption mSelectedOption;

    /* renamed from: com.mcdonalds.sdk.modules.models.OfferProduct$1 */
    static class C40621 implements Creator<OfferProduct> {
        C40621() {
        }

        public OfferProduct createFromParcel(Parcel source) {
            return new OfferProduct(source);
        }

        public OfferProduct[] newArray(int size) {
            return new OfferProduct[size];
        }
    }

    public OfferProduct clone() {
        OfferProduct newOfferProduct = new OfferProduct();
        newOfferProduct.setAction(getAction());
        newOfferProduct.setAlias(getAlias());
        newOfferProduct.setAnyProduct(getAnyProduct());
        newOfferProduct.setMaxUnitPrice(getMaxUnitPrice());
        newOfferProduct.setMaxUnitPriceAlias(getMaxUnitPriceAlias());
        newOfferProduct.setMinUnitPrice(getMinUnitPrice());
        newOfferProduct.setMinUnitPriceAlias(getMinUnitPriceAlias());
        newOfferProduct.setPromoItem(this.mPromoItem);
        newOfferProduct.setQuantity(getQuantity());
        newOfferProduct.setExpired(getExpired());
        newOfferProduct.setCodesFromAlias(getCodesFromAlias());
        List<OfferProductOption> newOfferProductOptions = new ArrayList();
        for (OfferProductOption offerProductOption : getProducts()) {
            OfferProductOption newOfferProductOption = offerProductOption.clone();
            if (offerProductOption.equals(getSelectedOption())) {
                newOfferProduct.setSelectedOption(newOfferProductOption);
            }
            newOfferProductOptions.add(newOfferProductOption);
        }
        newOfferProduct.setProducts(newOfferProductOptions);
        return newOfferProduct;
    }

    public OfferAction getAction() {
        return this.mAction;
    }

    public void setAction(OfferAction action) {
        this.mAction = action;
    }

    public OfferProductOption getSelectedOption() {
        return this.mSelectedOption;
    }

    public void setSelectedOption(OfferProductOption selectedOption) {
        this.mSelectedOption = selectedOption;
    }

    public String getAlias() {
        return this.mAlias;
    }

    public void setAlias(String alias) {
        this.mAlias = alias;
    }

    public Boolean getAnyProduct() {
        return this.mAnyProduct;
    }

    public void setAnyProduct(Boolean anyProduct) {
        this.mAnyProduct = anyProduct;
    }

    public Double getMaxUnitPrice() {
        return this.mMaxUnitPrice;
    }

    public void setMaxUnitPrice(Double maxUnitPrice) {
        this.mMaxUnitPrice = maxUnitPrice;
    }

    public String getMaxUnitPriceAlias() {
        return this.mMaxUnitPriceAlias;
    }

    public void setMaxUnitPriceAlias(String maxUnitPriceAlias) {
        this.mMaxUnitPriceAlias = maxUnitPriceAlias;
    }

    public Double getMinUnitPrice() {
        return this.mMinUnitPrice;
    }

    public void setMinUnitPrice(Double minUnitPrice) {
        this.mMinUnitPrice = minUnitPrice;
    }

    public String getMinUnitPriceAlias() {
        return this.mMinUnitPriceAlias;
    }

    public void setMinUnitPriceAlias(String minUnitPriceAlias) {
        this.mMinUnitPriceAlias = minUnitPriceAlias;
    }

    public List<OfferProductOption> getProducts() {
        return this.mProducts;
    }

    public void setProducts(List<OfferProductOption> products) {
        this.mProducts = products;
    }

    public Boolean isPromoItem() {
        return this.mPromoItem;
    }

    public void setPromoItem(Boolean promoItem) {
        this.mPromoItem = promoItem;
    }

    public Integer getQuantity() {
        return this.mQuantity;
    }

    public void setQuantity(Integer quantity) {
        this.mQuantity = quantity;
    }

    public Boolean getExpired() {
        return this.mExpired;
    }

    public void setExpired(Boolean expired) {
        this.mExpired = expired;
    }

    public String toString() {
        return "OfferProduct{mAction=" + this.mAction + ", mSelectedOption=" + this.mSelectedOption + ", mAlias=\"" + this.mAlias + "\", mAnyProduct=" + this.mAnyProduct + ", mMaxUnitPrice=" + this.mMaxUnitPrice + ", mMaxUnitPriceAlias=\"" + this.mMaxUnitPriceAlias + "\", mMinUnitPrice=" + this.mMinUnitPrice + ", mMinUnitPriceAlias=\"" + this.mMinUnitPriceAlias + "\", mProducts=" + this.mProducts + ", mPromoItem=" + this.mPromoItem + ", mQuantity=" + this.mQuantity + ", mExpired=" + this.mExpired + "}";
    }

    public String getCodesFromAlias() {
        return this.mCodesFromAlias;
    }

    public void setCodesFromAlias(String codesFromAlias) {
        this.mCodesFromAlias = codesFromAlias;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mAction, 0);
        dest.writeParcelable(this.mSelectedOption, flags);
        dest.writeString(this.mAlias);
        dest.writeValue(this.mAnyProduct);
        dest.writeValue(this.mMaxUnitPrice);
        dest.writeString(this.mMaxUnitPriceAlias);
        dest.writeValue(this.mMinUnitPrice);
        dest.writeString(this.mMinUnitPriceAlias);
        dest.writeList(this.mProducts);
        dest.writeValue(this.mPromoItem);
        dest.writeValue(this.mQuantity);
        dest.writeValue(this.mExpired);
        dest.writeString(this.mCodesFromAlias);
    }

    protected OfferProduct(Parcel in) {
        this.mAction = (OfferAction) in.readParcelable(OfferAction.class.getClassLoader());
        this.mSelectedOption = (OfferProductOption) in.readParcelable(OfferProductOption.class.getClassLoader());
        this.mAlias = in.readString();
        this.mAnyProduct = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mMaxUnitPrice = (Double) in.readValue(Double.class.getClassLoader());
        this.mMaxUnitPriceAlias = in.readString();
        this.mMinUnitPrice = (Double) in.readValue(Double.class.getClassLoader());
        this.mMinUnitPriceAlias = in.readString();
        this.mProducts = new ArrayList();
        in.readList(this.mProducts, OfferProductOption.class.getClassLoader());
        this.mPromoItem = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mQuantity = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mExpired = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mCodesFromAlias = in.readString();
    }
}
