package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.connectors.middleware.model.MWAdvertisable;
import com.mcdonalds.sdk.connectors.middleware.model.MWAdvertisableProductSet;
import com.mcdonalds.sdk.connectors.middleware.model.MWAdvertisableSwapMapping;
import com.mcdonalds.sdk.modules.AppModel;
import com.mcdonalds.sdk.utils.ListUtils;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Deprecated
public class AdvertisablePromotion extends AppModel implements Parcelable {
    public static final Creator<AdvertisablePromotion> CREATOR = new C25651();
    private int mBaseProductId;
    private List<String> mDaysOfWeek;
    private boolean mIsAdvertisable;
    private String mPromotionName;
    private int mSwapProductId;

    /* renamed from: com.mcdonalds.sdk.modules.models.AdvertisablePromotion$1 */
    static class C25651 implements Creator<AdvertisablePromotion> {
        C25651() {
        }

        public AdvertisablePromotion createFromParcel(Parcel source) {
            return new AdvertisablePromotion(source);
        }

        public AdvertisablePromotion[] newArray(int size) {
            return new AdvertisablePromotion[size];
        }
    }

    public AdvertisablePromotion(MWAdvertisable advertisable) {
        setIsAdvertisable(advertisable);
        setpromotionName(advertisable);
        setDaysOfWeek(advertisable);
        setBaseProductId(advertisable);
        setSwapProductId(advertisable);
    }

    public void setIsAdvertisable(boolean isAdvertisable) {
        this.mIsAdvertisable = isAdvertisable;
    }

    private void setIsAdvertisable(MWAdvertisable advertisable) {
        boolean z = advertisable != null && advertisable.isAdvertisable;
        this.mIsAdvertisable = z;
    }

    public boolean isAdvertisable() {
        return this.mIsAdvertisable;
    }

    public void setpromotionName(String promotionDescription) {
        this.mPromotionName = promotionDescription;
    }

    private void setpromotionName(MWAdvertisable advertisable) {
        if (advertisable != null) {
            this.mPromotionName = advertisable.advertisableName;
        } else {
            this.mPromotionName = null;
        }
    }

    public String getPromotionName() {
        return this.mPromotionName;
    }

    public void setBaseProductId(int productId) {
        this.mBaseProductId = productId;
    }

    private void setBaseProductId(MWAdvertisable advertisable) {
        if (advertisable == null || ListUtils.isEmpty(advertisable.productSets) || advertisable.productSets.get(0) == null || ListUtils.isEmpty(((MWAdvertisableProductSet) advertisable.productSets.get(0)).swapMapping) || ((MWAdvertisableProductSet) advertisable.productSets.get(0)).swapMapping.get(0) == null) {
            this.mBaseProductId = -1;
        } else {
            this.mBaseProductId = Integer.valueOf(((MWAdvertisableSwapMapping) ((MWAdvertisableProductSet) advertisable.productSets.get(0)).swapMapping.get(0)).regular).intValue();
        }
    }

    public int getBaseProductId() {
        return this.mBaseProductId;
    }

    public void setSwapProductId(int productId) {
        this.mSwapProductId = productId;
    }

    private void setSwapProductId(MWAdvertisable advertisable) {
        if (advertisable == null || ListUtils.isEmpty(advertisable.productSets) || advertisable.productSets.get(0) == null || ListUtils.isEmpty(((MWAdvertisableProductSet) advertisable.productSets.get(0)).swapMapping) || ((MWAdvertisableProductSet) advertisable.productSets.get(0)).swapMapping.get(0) == null) {
            this.mSwapProductId = -1;
        } else {
            this.mSwapProductId = Integer.valueOf(((MWAdvertisableSwapMapping) ((MWAdvertisableProductSet) advertisable.productSets.get(0)).swapMapping.get(0)).swap).intValue();
        }
    }

    public int getSwapProductId() {
        return this.mSwapProductId;
    }

    public void setDaysOfWeek(List<String> daysOfWeek) {
        this.mDaysOfWeek = daysOfWeek;
    }

    public void setDaysOfWeek(MWAdvertisable advertisable) {
        if (advertisable == null || advertisable.conditions == null || ListUtils.isEmpty(advertisable.conditions.dayOfWeekConditions)) {
            this.mDaysOfWeek = new ArrayList();
        } else {
            this.mDaysOfWeek = advertisable.conditions.dayOfWeekConditions;
        }
    }

    public List<String> getDaysOfWeek() {
        return this.mDaysOfWeek;
    }

    public boolean isValidForToday() {
        if (!this.mIsAdvertisable) {
            return false;
        }
        String today = DateFormat.getDateInstance(0).format(new Date()).toLowerCase();
        for (String dayOfWeek : this.mDaysOfWeek) {
            if (today.contains(dayOfWeek.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAdvertisablePromotion(int productCode) {
        return productCode == this.mBaseProductId && this.mIsAdvertisable && isValidForToday() && !isAdvertisedProduct(productCode);
    }

    public boolean isAdvertisedProduct(int productCode) {
        return productCode == this.mSwapProductId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mIsAdvertisable ? 1 : 0);
        dest.writeString(this.mPromotionName);
        dest.writeInt(this.mBaseProductId);
        dest.writeInt(this.mSwapProductId);
        dest.writeList(this.mDaysOfWeek);
    }

    protected AdvertisablePromotion(Parcel in) {
        boolean z = true;
        if (in.readInt() != 1) {
            z = false;
        }
        this.mIsAdvertisable = z;
        this.mPromotionName = in.readString();
        this.mBaseProductId = in.readInt();
        this.mSwapProductId = in.readInt();
        this.mDaysOfWeek = new ArrayList();
        in.readList(this.mDaysOfWeek, String.class.getClassLoader());
    }
}
