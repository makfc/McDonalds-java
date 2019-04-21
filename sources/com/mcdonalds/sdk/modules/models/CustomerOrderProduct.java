package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderProduct extends AppModel implements Parcelable {
    public static final Creator<CustomerOrderProduct> CREATOR = new C40451();
    private CustomerOrderProduct choiceSolution;
    private List<CustomerOrderProduct> choices;
    private List<CustomerOrderProduct> components;
    private List<CustomerOrderProduct> customizations;
    private Boolean mIsLight;
    private Integer mProductCode;
    private Integer mPromoQuantity;
    private Integer mQuantity;

    /* renamed from: com.mcdonalds.sdk.modules.models.CustomerOrderProduct$1 */
    static class C40451 implements Creator<CustomerOrderProduct> {
        C40451() {
        }

        public CustomerOrderProduct createFromParcel(Parcel source) {
            return new CustomerOrderProduct(source);
        }

        public CustomerOrderProduct[] newArray(int size) {
            return new CustomerOrderProduct[size];
        }
    }

    public List<CustomerOrderProduct> getChoices() {
        return this.choices;
    }

    public void setChoices(List<CustomerOrderProduct> choices) {
        this.choices = choices;
    }

    public List<CustomerOrderProduct> getComponents() {
        return this.components;
    }

    public void setComponents(List<CustomerOrderProduct> components) {
        this.components = components;
    }

    public List<CustomerOrderProduct> getCustomizations() {
        return this.customizations;
    }

    public void setCustomizations(List<CustomerOrderProduct> customizations) {
        this.customizations = customizations;
    }

    public CustomerOrderProduct getChoiceSolution() {
        return this.choiceSolution;
    }

    public void setChoiceSolution(CustomerOrderProduct choiceSolution) {
        this.choiceSolution = choiceSolution;
    }

    public Integer getProductCode() {
        return this.mProductCode;
    }

    public void setProductCode(Integer productCode) {
        this.mProductCode = productCode;
    }

    public Integer getPromoQuantity() {
        return this.mPromoQuantity;
    }

    public void setPromoQuantity(Integer promoQuantity) {
        this.mPromoQuantity = promoQuantity;
    }

    public Integer getQuantity() {
        return this.mQuantity;
    }

    public void setQuantity(Integer quantity) {
        this.mQuantity = quantity;
    }

    public Boolean getIsLight() {
        return this.mIsLight;
    }

    public void setIsLight(Boolean isLight) {
        this.mIsLight = isLight;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.choices);
        dest.writeList(this.components);
        dest.writeList(this.customizations);
        dest.writeParcelable(this.choiceSolution, flags);
        dest.writeValue(this.mProductCode);
        dest.writeValue(this.mPromoQuantity);
        dest.writeValue(this.mQuantity);
        dest.writeValue(this.mIsLight);
    }

    protected CustomerOrderProduct(Parcel in) {
        this.choices = new ArrayList();
        in.readList(this.choices, CustomerOrderProduct.class.getClassLoader());
        this.components = new ArrayList();
        in.readList(this.components, CustomerOrderProduct.class.getClassLoader());
        this.customizations = new ArrayList();
        in.readList(this.customizations, CustomerOrderProduct.class.getClassLoader());
        this.choiceSolution = (CustomerOrderProduct) in.readParcelable(CustomerOrderProduct.class.getClassLoader());
        this.mProductCode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mPromoQuantity = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mQuantity = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mIsLight = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }
}
