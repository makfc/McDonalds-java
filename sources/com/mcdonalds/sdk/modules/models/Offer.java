package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Offer extends AppModel implements Parcelable, Comparable<Offer> {
    public static final Creator<Offer> CREATOR = new C25741();
    private List<AndCondition> mAndConditions;
    private Boolean mArchived;
    private Integer mCurrentPunch;
    private Integer mDuration;
    private Boolean mExpirationChanged;
    private Boolean mExpired;
    private boolean mIsDeliveryOffer;
    private boolean mIsNoPod;
    private boolean mIsPickupOffer;
    private String mLargeImagePath;
    private Date mLocalValidFrom;
    private Date mLocalValidThrough;
    private String mLongDescription;
    private String mName;
    private Integer mOfferId;
    private OfferType mOfferType;
    private Double mOrderDiscount;
    private OfferRedemptionType mOrderDiscountType;
    private List<OfferProduct> mProductSets;
    private Boolean mRedeemed;
    private Date mRedeemedAt;
    private List<Integer> mRestaurants;
    private List<SaleAmountCondition> mSaleAmountCondition;
    private Boolean mSelected;
    private String mShortDescription;
    private String mSmallImagePath;
    private String mSubtitle;
    private Integer mTotalPunch;
    private List<String> offerProductsCodes;

    /* renamed from: com.mcdonalds.sdk.modules.models.Offer$1 */
    static class C25741 implements Creator<Offer> {
        C25741() {
        }

        public Offer createFromParcel(Parcel source) {
            return new Offer(source);
        }

        public Offer[] newArray(int size) {
            return new Offer[size];
        }
    }

    public static class AndCondition implements Parcelable {
        public static final Creator<AndCondition> CREATOR = new C25751();
        public int mDayOfWeek;
        public int mHourOfDayFrom;
        public int mHourOfDayTo;
        public int mMinuteFrom;
        public int mMinuteTo;

        /* renamed from: com.mcdonalds.sdk.modules.models.Offer$AndCondition$1 */
        static class C25751 implements Creator<AndCondition> {
            C25751() {
            }

            public AndCondition createFromParcel(Parcel in) {
                return new AndCondition(in, null);
            }

            public AndCondition[] newArray(int size) {
                return new AndCondition[size];
            }
        }

        /* synthetic */ AndCondition(Parcel x0, C25741 x1) {
            this(x0);
        }

        public void setDayOfWeek(int dayOfWeek) {
            this.mDayOfWeek = dayOfWeek;
        }

        public int getDayOfWeek() {
            return this.mDayOfWeek;
        }

        public void setHourOfDayFrom(int hourOfDayFrom) {
            this.mHourOfDayFrom = hourOfDayFrom;
        }

        public int getHourOfDayFrom() {
            return this.mHourOfDayFrom;
        }

        public void setMinuteFrom(int minuteFrom) {
            this.mMinuteFrom = minuteFrom;
        }

        public int getMinuteFrom() {
            return this.mMinuteFrom;
        }

        public void setHourOfDayTo(int hourOfDayTo) {
            this.mHourOfDayTo = hourOfDayTo;
        }

        public int getHourOfDayTo() {
            return this.mHourOfDayTo;
        }

        public void setMinuteTo(int minuteTo) {
            this.mMinuteTo = minuteTo;
        }

        public int getMinuteTo() {
            return this.mMinuteTo;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            out.writeInt(this.mDayOfWeek);
            out.writeInt(this.mHourOfDayFrom);
            out.writeInt(this.mMinuteFrom);
            out.writeInt(this.mHourOfDayTo);
            out.writeInt(this.mMinuteTo);
        }

        private AndCondition(Parcel in) {
            this.mDayOfWeek = in.readInt();
            this.mHourOfDayFrom = in.readInt();
            this.mMinuteFrom = in.readInt();
            this.mHourOfDayTo = in.readInt();
            this.mMinuteTo = in.readInt();
        }
    }

    public enum OfferType {
        OFFER_TYPE_UNKNOWN,
        OFFER_TYPE_REWARD,
        OFFER_TYPE_WEEKLY,
        OFFER_TYPE_LIMITED_TIME,
        OFFER_TYPE_EVERYDAY,
        OFFER_TYPE_FREQUENCY,
        OFFER_TYPE_LOCATION_BASED,
        OFFER_TYPE_MSA,
        OFFER_TYPE_PLACEHOLDER_FOR_ENUM_8,
        OFFER_TYPE_RENEWABLE_FREQUENCY,
        OFFER_TYPE_DELIVERY_FEE
    }

    public static class SaleAmountCondition implements Parcelable {
        public static final Creator<SaleAmountCondition> CREATOR = new C25761();
        public double mMinimum;

        /* renamed from: com.mcdonalds.sdk.modules.models.Offer$SaleAmountCondition$1 */
        static class C25761 implements Creator<SaleAmountCondition> {
            C25761() {
            }

            public SaleAmountCondition createFromParcel(Parcel in) {
                return new SaleAmountCondition(in, null);
            }

            public SaleAmountCondition[] newArray(int size) {
                return new SaleAmountCondition[size];
            }
        }

        /* synthetic */ SaleAmountCondition(Parcel x0, C25741 x1) {
            this(x0);
        }

        public void setMinimum(double minimum) {
            this.mMinimum = minimum;
        }

        public double getMinimum() {
            return this.mMinimum;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            out.writeDouble(this.mMinimum);
        }

        private SaleAmountCondition(Parcel in) {
            this.mMinimum = in.readDouble();
        }
    }

    public Boolean relatesToProductId(String productId) {
        if (this.offerProductsCodes == null) {
            setUpOfferProductsCodes();
        }
        return Boolean.valueOf(this.offerProductsCodes.contains(productId));
    }

    private void setUpOfferProductsCodes() {
        this.offerProductsCodes = new ArrayList();
        int size = this.mProductSets.size();
        for (int i = 0; i < size; i++) {
            List<OfferProductOption> offerProductOptions = ((OfferProduct) this.mProductSets.get(i)).getProducts();
            int optSize = offerProductOptions.size();
            for (int j = 0; j < optSize; j++) {
                this.offerProductsCodes.add(((OfferProductOption) offerProductOptions.get(j)).getProductCode());
            }
        }
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public void setSubtitle(String mSubtitle) {
        this.mSubtitle = mSubtitle;
    }

    public Boolean isArchived() {
        return this.mArchived;
    }

    public void setArchived(Boolean archived) {
        this.mArchived = archived;
    }

    public Integer getCurrentPunch() {
        return this.mCurrentPunch;
    }

    public void setCurrentPunch(Integer currentPunch) {
        this.mCurrentPunch = currentPunch;
    }

    public Integer getDuration() {
        return this.mDuration;
    }

    public void setDuration(Integer duration) {
        this.mDuration = duration;
    }

    public Boolean isExpirationChanged() {
        return this.mExpirationChanged;
    }

    public void setExpirationChanged(Boolean expirationChanged) {
        this.mExpirationChanged = expirationChanged;
    }

    public Boolean isExpired() {
        return this.mExpired;
    }

    public void setExpired(Boolean expired) {
        this.mExpired = expired;
    }

    public Integer getOfferId() {
        return this.mOfferId;
    }

    public void setOfferId(Integer offerId) {
        this.mOfferId = offerId;
    }

    public Date getLocalValidFrom() {
        return this.mLocalValidFrom;
    }

    public void setLocalValidFrom(Date localValidFrom) {
        this.mLocalValidFrom = localValidFrom;
    }

    public Date getLocalValidThrough() {
        return this.mLocalValidThrough;
    }

    public void setLocalValidThrough(Date localValidThrough) {
        this.mLocalValidThrough = localValidThrough;
    }

    public String getLongDescription() {
        return this.mLongDescription;
    }

    public void setLongDescription(String longDescription) {
        this.mLongDescription = longDescription;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public OfferType getOfferType() {
        return this.mOfferType;
    }

    public void setOfferType(Integer offerType) {
        if (offerType.intValue() < OfferType.values().length) {
            this.mOfferType = OfferType.values()[offerType.intValue()];
        } else {
            this.mOfferType = OfferType.OFFER_TYPE_UNKNOWN;
        }
    }

    public List<OfferProduct> getProductSets() {
        return this.mProductSets;
    }

    public void setProductSets(List<OfferProduct> productSets) {
        this.mProductSets = productSets;
    }

    public Boolean getRedeemed() {
        return this.mRedeemed;
    }

    public void setRedeemed(Boolean redeemed) {
        this.mRedeemed = redeemed;
    }

    public Date getRedeemedAt() {
        return this.mRedeemedAt;
    }

    public void setRedeemedAt(Date redeemedAt) {
        this.mRedeemedAt = redeemedAt;
    }

    public List<Integer> getRestaurants() {
        return this.mRestaurants;
    }

    public void setRestaurants(List<Integer> restaurants) {
        this.mRestaurants = restaurants;
    }

    public Boolean getSelected() {
        return this.mSelected;
    }

    public void setSelected(Boolean selected) {
        this.mSelected = selected;
    }

    public Integer getTotalPunch() {
        return this.mTotalPunch;
    }

    public void setTotalPunch(Integer totalPunch) {
        this.mTotalPunch = totalPunch;
    }

    public void setOfferType(OfferType offerType) {
        this.mOfferType = offerType;
    }

    public String getSmallImagePath() {
        return this.mSmallImagePath;
    }

    public void setSmallImagePath(String smallImagePath) {
        this.mSmallImagePath = smallImagePath;
    }

    public String getLargeImagePath() {
        return this.mLargeImagePath;
    }

    public void setLargeImagePath(String largeImagePath) {
        this.mLargeImagePath = largeImagePath;
    }

    public boolean isDeliveryOffer() {
        return this.mIsDeliveryOffer;
    }

    public void setIsDeliveryOffer(boolean isDeliveryOnly) {
        this.mIsDeliveryOffer = isDeliveryOnly;
    }

    public boolean isPickupOffer() {
        return this.mIsPickupOffer;
    }

    public void setIsPickUpOffer(boolean isPickupOnly) {
        this.mIsPickupOffer = isPickupOnly;
    }

    public boolean isNoPod() {
        return this.mIsNoPod;
    }

    public void setIsNoPod(boolean isNoPod) {
        this.mIsNoPod = isNoPod;
    }

    public void setShortDescription(String shortDescription) {
        this.mShortDescription = shortDescription;
    }

    public String getShortDescription() {
        return this.mShortDescription;
    }

    public Double getOrderDiscount() {
        return this.mOrderDiscount;
    }

    public void setOrderDiscount(Double orderDiscount) {
        this.mOrderDiscount = orderDiscount;
    }

    public OfferRedemptionType getOrderDiscountType() {
        return this.mOrderDiscountType;
    }

    public void setOrderDiscountType(OfferRedemptionType orderDiscountType) {
        this.mOrderDiscountType = orderDiscountType;
    }

    public boolean isPunchCardType() {
        return this.mOfferType == OfferType.OFFER_TYPE_FREQUENCY || this.mOfferType == OfferType.OFFER_TYPE_RENEWABLE_FREQUENCY;
    }

    public boolean isPunchCard() {
        return (this.mOfferType == OfferType.OFFER_TYPE_FREQUENCY || this.mOfferType == OfferType.OFFER_TYPE_RENEWABLE_FREQUENCY) && this.mCurrentPunch.intValue() < this.mTotalPunch.intValue();
    }

    public boolean isBuyNGetMOffer() {
        if (!isPunchCard()) {
            for (OfferProduct offerProduct : this.mProductSets) {
                if (offerProduct.getAction() != null && offerProduct.getAction().getValue().doubleValue() == 0.0d && offerProduct.isPromoItem().booleanValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFullPunchCard() {
        return (this.mOfferType == OfferType.OFFER_TYPE_FREQUENCY || this.mOfferType == OfferType.OFFER_TYPE_RENEWABLE_FREQUENCY) && this.mCurrentPunch.intValue() >= this.mTotalPunch.intValue();
    }

    public void setAndConditions(List<AndCondition> andConditions) {
        this.mAndConditions = andConditions;
    }

    public List<AndCondition> getAndConditions() {
        return this.mAndConditions;
    }

    public void setSaleAmountCondition(List<SaleAmountCondition> saleAmountCondition) {
        this.mSaleAmountCondition = saleAmountCondition;
    }

    public List<SaleAmountCondition> getSaleAmountCondition() {
        return this.mSaleAmountCondition;
    }

    public int compareTo(Offer another) {
        return this.mLocalValidFrom.compareTo(another.getLocalValidFrom());
    }

    public String toString() {
        return "Offer{mArchived=" + this.mArchived + ", mCurrentPunch=" + this.mCurrentPunch + ", mDuration=" + this.mDuration + ", mExpirationChanged" + this.mExpirationChanged + ", mExpired=" + this.mExpired + ", mOfferId=" + this.mOfferId + ", mLocalValidFrom=" + this.mLocalValidFrom + ", mLocalValidThrough=" + this.mLocalValidThrough + ", mLongDescription=\"" + this.mLongDescription + "\", mName=\"" + this.mName + "\", mOfferType=" + this.mOfferType + ", mProductSets=" + this.mProductSets + ", mRedeemed=" + this.mRedeemed + ", mRedeemedAt=" + this.mRedeemedAt + ", mRestaurants=" + this.mRestaurants + ", mSelected=" + this.mSelected + ", mTotalPunch=" + this.mTotalPunch + ", mSmallImagePath=\"" + this.mSmallImagePath + "\"}";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        long time;
        int i;
        int i2 = -1;
        long j = -1;
        dest.writeValue(this.mArchived);
        dest.writeValue(this.mCurrentPunch);
        dest.writeValue(this.mDuration);
        dest.writeValue(this.mExpirationChanged);
        dest.writeValue(this.mExpired);
        dest.writeValue(this.mOfferId);
        if (this.mLocalValidFrom != null) {
            time = this.mLocalValidFrom.getTime();
        } else {
            time = -1;
        }
        dest.writeLong(time);
        if (this.mLocalValidThrough != null) {
            time = this.mLocalValidThrough.getTime();
        } else {
            time = -1;
        }
        dest.writeLong(time);
        dest.writeString(this.mLongDescription);
        dest.writeString(this.mName);
        dest.writeString(this.mSubtitle);
        if (this.mOfferType == null) {
            i = -1;
        } else {
            i = this.mOfferType.ordinal();
        }
        dest.writeInt(i);
        dest.writeList(this.mProductSets);
        dest.writeValue(this.mRedeemed);
        if (this.mRedeemedAt != null) {
            j = this.mRedeemedAt.getTime();
        }
        dest.writeLong(j);
        dest.writeList(this.mRestaurants);
        dest.writeValue(this.mSelected);
        dest.writeValue(this.mTotalPunch);
        dest.writeString(this.mSmallImagePath);
        dest.writeString(this.mLargeImagePath);
        dest.writeString(this.mShortDescription);
        dest.writeValue(this.mOrderDiscount);
        if (this.mOrderDiscountType != null) {
            i2 = this.mOrderDiscountType.ordinal();
        }
        dest.writeInt(i2);
        dest.writeStringList(this.offerProductsCodes);
        dest.writeBooleanArray(new boolean[]{this.mIsDeliveryOffer, this.mIsPickupOffer, this.mIsNoPod});
        dest.writeList(this.mAndConditions);
        dest.writeList(this.mSaleAmountCondition);
    }

    protected Offer(Parcel in) {
        Date date;
        this.mArchived = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mCurrentPunch = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mDuration = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mExpirationChanged = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mExpired = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mOfferId = (Integer) in.readValue(Integer.class.getClassLoader());
        long tmpMLocalValidFrom = in.readLong();
        this.mLocalValidFrom = tmpMLocalValidFrom == -1 ? null : new Date(tmpMLocalValidFrom);
        long tmpMLocalValidThrough = in.readLong();
        this.mLocalValidThrough = tmpMLocalValidThrough == -1 ? null : new Date(tmpMLocalValidThrough);
        this.mLongDescription = in.readString();
        this.mName = in.readString();
        this.mSubtitle = in.readString();
        int tmpMOfferType = in.readInt();
        this.mOfferType = tmpMOfferType == -1 ? null : OfferType.values()[tmpMOfferType];
        this.mProductSets = new ArrayList();
        in.readList(this.mProductSets, OfferProduct.class.getClassLoader());
        this.mRedeemed = (Boolean) in.readValue(Boolean.class.getClassLoader());
        long tmpMRedeemedAt = in.readLong();
        if (tmpMRedeemedAt == -1) {
            date = null;
        } else {
            date = new Date(tmpMRedeemedAt);
        }
        this.mRedeemedAt = date;
        this.mRestaurants = new ArrayList();
        in.readList(this.mRestaurants, Integer.class.getClassLoader());
        this.mSelected = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mTotalPunch = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mSmallImagePath = in.readString();
        this.mLargeImagePath = in.readString();
        this.mShortDescription = in.readString();
        this.mOrderDiscount = (Double) in.readValue(Double.class.getClassLoader());
        int tmpMOrderDiscountType = in.readInt();
        this.mOrderDiscountType = tmpMOrderDiscountType == -1 ? null : OfferRedemptionType.values()[tmpMOrderDiscountType];
        this.offerProductsCodes = in.createStringArrayList();
        boolean[] deliveryPickup = new boolean[3];
        in.readBooleanArray(deliveryPickup);
        this.mIsDeliveryOffer = deliveryPickup[0];
        this.mIsPickupOffer = deliveryPickup[1];
        this.mIsNoPod = deliveryPickup[2];
        this.mAndConditions = new ArrayList();
        in.readList(this.mAndConditions, AndCondition.class.getClassLoader());
        this.mSaleAmountCondition = new ArrayList();
        in.readList(this.mSaleAmountCondition, SaleAmountCondition.class.getClassLoader());
    }
}
