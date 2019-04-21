package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;
import java.util.List;

public class CustomerOrder extends AppModel implements Parcelable {
    public static final Creator<CustomerOrder> CREATOR = new C40441();
    private static final int SERVICE_MODE_DELIVERY = 2;
    private static final int SERVICE_MODE_PICKUP = 1;
    private boolean isFinalized;
    private String mName;
    private Integer mOrderId;
    private String mOrderNumber;
    private List<CustomerOrderProduct> mProducts;
    private Integer mServiceMode;

    /* renamed from: com.mcdonalds.sdk.modules.models.CustomerOrder$1 */
    static class C40441 implements Creator<CustomerOrder> {
        C40441() {
        }

        public CustomerOrder createFromParcel(Parcel source) {
            return new CustomerOrder(source);
        }

        public CustomerOrder[] newArray(int size) {
            return new CustomerOrder[size];
        }
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getOrderNumber() {
        return this.mOrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.mOrderNumber = orderNumber;
    }

    public void setServiceMode(Integer serviceMode) {
        this.mServiceMode = serviceMode;
    }

    public Integer getServiceMode() {
        return this.mServiceMode;
    }

    public Integer getOrderId() {
        return this.mOrderId;
    }

    public void setOrderId(Integer orderId) {
        this.mOrderId = orderId;
    }

    public List<CustomerOrderProduct> getProducts() {
        return this.mProducts;
    }

    public void setProducts(List<CustomerOrderProduct> products) {
        this.mProducts = products;
    }

    public boolean isDelivery() {
        return this.mServiceMode != null && this.mServiceMode.intValue() == 2;
    }

    public boolean isFinalized() {
        return this.isFinalized;
    }

    public void setFinalized(boolean finalized) {
        this.isFinalized = finalized;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeValue(this.mOrderId);
        dest.writeTypedList(this.mProducts);
        dest.writeString(this.mOrderNumber);
        dest.writeValue(this.mServiceMode);
        dest.writeInt(this.isFinalized ? 1 : 0);
    }

    protected CustomerOrder(Parcel in) {
        this.mName = in.readString();
        this.mOrderId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mProducts = in.createTypedArrayList(CustomerOrderProduct.CREATOR);
        this.mOrderNumber = in.readString();
        this.mServiceMode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isFinalized = in.readInt() != 0;
    }
}
