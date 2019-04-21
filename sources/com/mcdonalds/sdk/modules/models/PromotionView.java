package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;
import java.util.List;

public class PromotionView extends AppModel implements Parcelable {
    public static final Creator<PromotionView> CREATOR = new C40821();
    private List<ProductView> mProductSet;
    private Integer mPromotionId;
    private Integer mValidationErrorCode;

    /* renamed from: com.mcdonalds.sdk.modules.models.PromotionView$1 */
    static class C40821 implements Creator<PromotionView> {
        C40821() {
        }

        public PromotionView createFromParcel(Parcel source) {
            return new PromotionView(source);
        }

        public PromotionView[] newArray(int size) {
            return new PromotionView[size];
        }
    }

    public Integer getPromotionId() {
        return this.mPromotionId;
    }

    public void setPromotionId(Integer Id) {
        this.mPromotionId = Id;
    }

    public Integer getValidationErrorCode() {
        return this.mValidationErrorCode;
    }

    public void setValidationErrorCode(Integer validationCode) {
        this.mValidationErrorCode = validationCode;
    }

    public List<ProductView> getProductSet() {
        return this.mProductSet;
    }

    public void setProductSet(List<ProductView> mProductSet) {
        this.mProductSet = mProductSet;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mPromotionId);
        dest.writeValue(this.mValidationErrorCode);
        dest.writeTypedList(this.mProductSet);
    }

    protected PromotionView(Parcel in) {
        this.mPromotionId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mValidationErrorCode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mProductSet = in.createTypedArrayList(ProductView.CREATOR);
    }
}
