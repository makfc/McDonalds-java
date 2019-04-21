package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class ProductView extends AppModel implements Parcelable {
    public static final Creator<ProductView> CREATOR = new C40801();
    private Integer mChangeStatus;
    private ArrayList<ProductView> mChoices;
    private ArrayList<ProductView> mComponents;
    private ArrayList<ProductView> mCustomizations;
    private Boolean mIsLight;
    private Boolean mIsPromotional;
    private Integer mProductCode;
    private Integer mPromoQuantity;
    private Promotion mPromotion;
    private Integer mQuantity;
    private Double mTotalEnergy;
    private Double mTotalValue;
    private Double mUnitPrice;
    private Integer mValidationErrorCode;

    /* renamed from: com.mcdonalds.sdk.modules.models.ProductView$1 */
    static class C40801 implements Creator<ProductView> {
        C40801() {
        }

        public ProductView createFromParcel(Parcel source) {
            return new ProductView(source);
        }

        public ProductView[] newArray(int size) {
            return new ProductView[size];
        }
    }

    public Integer getProductCode() {
        return this.mProductCode;
    }

    public void setProductCode(Integer productCode) {
        this.mProductCode = productCode;
    }

    public Integer getValidationErrorCode() {
        return this.mValidationErrorCode;
    }

    public void setValidationErrorCode(Integer validationErrorCode) {
        this.mValidationErrorCode = validationErrorCode;
    }

    public Promotion getPromotion() {
        return this.mPromotion;
    }

    public void setPromotion(Promotion mPromotion) {
        this.mPromotion = mPromotion;
    }

    public Integer getChangeStatus() {
        return this.mChangeStatus;
    }

    public void setChangeStatus(Integer changeStatus) {
        this.mChangeStatus = changeStatus;
    }

    public Boolean getIsLight() {
        return this.mIsLight;
    }

    public void setIsLight(Boolean isLight) {
        this.mIsLight = isLight;
    }

    public Boolean getIsPromotional() {
        return this.mIsPromotional;
    }

    public void setIsPromotional(Boolean isPromotional) {
        this.mIsPromotional = isPromotional;
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

    public Double getmTotalEnergy() {
        return this.mTotalEnergy;
    }

    public void setmTotalEnergy(Double totalEnergy) {
        this.mTotalEnergy = totalEnergy;
    }

    public Double getTotalValue() {
        return this.mTotalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.mTotalValue = totalValue;
    }

    public Double getUnitPrice() {
        return Double.valueOf(this.mUnitPrice != null ? this.mUnitPrice.doubleValue() : 0.0d);
    }

    public void setUnitPrice(Double unitPrice) {
        this.mUnitPrice = unitPrice;
    }

    public ArrayList<ProductView> getChoices() {
        return this.mChoices;
    }

    public void setChoices(ArrayList<ProductView> choices) {
        this.mChoices = choices;
    }

    public ArrayList<ProductView> getCustomizations() {
        return this.mCustomizations;
    }

    public void setCustomizations(ArrayList<ProductView> customizations) {
        this.mCustomizations = customizations;
    }

    public void setComponents(ArrayList<ProductView> components) {
        this.mComponents = components;
    }

    public ArrayList<ProductView> getComponents() {
        return this.mComponents;
    }

    public ProductView getActualChoice() {
        if (getProductCode().intValue() != 0 || ListUtils.isEmpty(getChoices())) {
            return this;
        }
        return (ProductView) getChoices().get(0);
    }

    public ProductView getSubProductView(OrderProduct orderProduct) {
        if (orderProduct.equals(this, true)) {
            return this;
        }
        if (!ListUtils.isEmpty(getChoices())) {
            Iterator it = getChoices().iterator();
            while (it.hasNext()) {
                ProductView subProduct = ((ProductView) it.next()).getSubProductView(orderProduct);
                if (subProduct != null) {
                    return subProduct;
                }
            }
        }
        return null;
    }

    public boolean equals(Object o) {
        if (o instanceof OrderProduct) {
            return Integer.parseInt(((OrderProduct) o).getProductCode()) == getProductCode().intValue();
        } else if (!(o instanceof ProductView)) {
            return super.equals(o);
        } else {
            ProductView object = (ProductView) o;
            boolean equals = object.getProductCode().equals(getProductCode());
            if (equals) {
                ArrayList<ProductView> productViewCustomizations = object.getCustomizations();
                int productViewCustomizationSize = productViewCustomizations == null ? 0 : productViewCustomizations.size();
                if (productViewCustomizationSize == 0 && !ListUtils.isEmpty(getCustomizations())) {
                    return false;
                }
                int i = 0;
                while (i < productViewCustomizationSize) {
                    ProductView productViewCustomization = (ProductView) productViewCustomizations.get(i);
                    if (ListUtils.isEmpty(getCustomizations()) || !((ProductView) getCustomizations().get(i)).equals(productViewCustomization)) {
                        return false;
                    }
                    i++;
                }
                if (getActualChoice() != this && !getActualChoice().equals(object.getActualChoice())) {
                    return false;
                }
                ArrayList<ProductView> productViewChoices = object.getChoices();
                int productViewChoiceSize = productViewChoices == null ? 0 : productViewChoices.size();
                i = 0;
                while (i < productViewChoiceSize) {
                    ProductView productViewChoice = (ProductView) productViewChoices.get(i);
                    if (ListUtils.isEmpty(getChoices()) || !((ProductView) getChoices().get(i)).equals(productViewChoice)) {
                        return false;
                    }
                    i++;
                }
            }
            return equals;
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mQuantity);
        dest.writeValue(this.mIsLight);
        dest.writeValue(this.mUnitPrice);
        dest.writeValue(this.mTotalValue);
        dest.writeValue(this.mTotalEnergy);
        dest.writeValue(this.mPromoQuantity);
        dest.writeValue(this.mChangeStatus);
        dest.writeValue(this.mIsPromotional);
        dest.writeValue(this.mProductCode);
        dest.writeValue(this.mValidationErrorCode);
        dest.writeList(this.mChoices);
        dest.writeParcelable(this.mPromotion, flags);
        dest.writeList(this.mComponents);
    }

    protected ProductView(Parcel in) {
        this.mQuantity = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mIsLight = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mUnitPrice = (Double) in.readValue(Double.class.getClassLoader());
        this.mTotalValue = (Double) in.readValue(Double.class.getClassLoader());
        this.mTotalEnergy = (Double) in.readValue(Double.class.getClassLoader());
        this.mPromoQuantity = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mChangeStatus = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mIsPromotional = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mProductCode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mValidationErrorCode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mChoices = new ArrayList();
        in.readList(this.mChoices, ProductView.class.getClassLoader());
        this.mPromotion = (Promotion) in.readParcelable(Promotion.class.getClassLoader());
        this.mComponents = new ArrayList();
        in.readList(this.mComponents, ProductView.class.getClassLoader());
    }
}
