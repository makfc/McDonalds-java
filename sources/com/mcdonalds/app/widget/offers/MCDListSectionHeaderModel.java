package com.mcdonalds.app.widget.offers;

import com.ensighten.Ensighten;
import com.mcdonalds.app.widget.offers.OfferHomeItemModel.RowType;

public class MCDListSectionHeaderModel extends OfferHomeItemModel {
    private final int mBackgroundColorResource;
    private final int mImageResource;
    private final boolean mImageVisible;
    private final int mTextColor;
    private final String mTitle;

    public MCDListSectionHeaderModel(String title, int imageResource, boolean imageVisible, int textColor, int backgroundColorResource) {
        this.mTitle = title;
        this.mImageResource = imageResource;
        this.mImageVisible = imageVisible;
        this.mTextColor = textColor;
        this.mBackgroundColorResource = backgroundColorResource;
    }

    public MCDListSectionHeaderModel(String title, int imageResource, boolean imageVisible) {
        this.mTitle = title;
        this.mImageResource = imageResource;
        this.mImageVisible = imageVisible;
        this.mBackgroundColorResource = 0;
        this.mTextColor = 0;
    }

    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return this.mTitle;
    }

    public int getImageResource() {
        Ensighten.evaluateEvent(this, "getImageResource", null);
        return this.mImageResource;
    }

    public boolean isImageVisible() {
        Ensighten.evaluateEvent(this, "isImageVisible", null);
        return this.mImageVisible;
    }

    public int getBackgroundColorResource() {
        Ensighten.evaluateEvent(this, "getBackgroundColorResource", null);
        return this.mBackgroundColorResource;
    }

    public RowType getItemType() {
        Ensighten.evaluateEvent(this, "getItemType", null);
        return RowType.Header;
    }

    public boolean equals(Object o) {
        Ensighten.evaluateEvent(this, "equals", new Object[]{o});
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MCDListSectionHeaderModel that = (MCDListSectionHeaderModel) o;
        if (this.mImageResource != that.mImageResource) {
            return false;
        }
        if (this.mImageVisible != that.mImageVisible) {
            return false;
        }
        if (!this.mTitle.equals(that.mTitle)) {
            return false;
        }
        if (this.mBackgroundColorResource != that.mBackgroundColorResource) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        Ensighten.evaluateEvent(this, "hashCode", null);
        return ((((this.mTitle.hashCode() * 31) + this.mImageResource) * 31) + (this.mImageVisible ? 1 : 0)) + this.mBackgroundColorResource;
    }

    public String toString() {
        Ensighten.evaluateEvent(this, "toString", null);
        return "MCDListSectionHeaderModel{mTitle=\"" + this.mTitle + "\", mImageResource=" + this.mImageResource + ", mBackgroundColorResource" + this.mBackgroundColorResource + "}";
    }

    public int getTextColor() {
        Ensighten.evaluateEvent(this, "getTextColor", null);
        return this.mTextColor;
    }
}
