package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;

public class OrderPayment extends AppModel implements Parcelable {
    public static final Creator<OrderPayment> CREATOR = new C40691();
    private static final int INVALID_PAYMENT_DATA_ID = -1;
    private String mCVV;
    private int mCustomerPaymentMethodId;
    private String mDeviceFingerPrintId;
    private String mIpAddress;
    private boolean mNewCardStub;
    private String mOrderPaymentId;
    private PointOfDistribution mPOD;
    private int mPaymentDataId = -1;
    private int mPaymentMethodId;

    /* renamed from: com.mcdonalds.sdk.modules.models.OrderPayment$1 */
    static class C40691 implements Creator<OrderPayment> {
        C40691() {
        }

        public OrderPayment createFromParcel(Parcel source) {
            return new OrderPayment(source);
        }

        public OrderPayment[] newArray(int size) {
            return new OrderPayment[size];
        }
    }

    public static OrderPayment fromPaymentCard(PaymentCard paymentCard) {
        OrderPayment ret = new OrderPayment();
        ret.setCustomerPaymentMethodId(paymentCard.getIdentifier().intValue());
        ret.setPaymentMethodId(paymentCard.getPaymentMethodId().intValue());
        ret.setNewCardStub(paymentCard.isNewCardStub());
        return ret;
    }

    public static OrderPayment fromCashPayment(Integer cashPaymentMethodId) {
        OrderPayment ret = new OrderPayment();
        ret.setPaymentMethodId(cashPaymentMethodId.intValue());
        return ret;
    }

    public PointOfDistribution getPOD() {
        return this.mPOD;
    }

    public void setPOD(PointOfDistribution POD) {
        this.mPOD = POD;
    }

    public int getCustomerPaymentMethodId() {
        return this.mCustomerPaymentMethodId;
    }

    public void setCustomerPaymentMethodId(int customerPaymentMethodId) {
        this.mCustomerPaymentMethodId = customerPaymentMethodId;
    }

    public int getPaymentMethodId() {
        return this.mPaymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.mPaymentMethodId = paymentMethodId;
    }

    public String getOrderPaymentId() {
        return this.mOrderPaymentId;
    }

    public void setOrderPaymentId(String orderPaymentId) {
        this.mOrderPaymentId = orderPaymentId;
    }

    public int getPaymentDataId() {
        return this.mPaymentDataId;
    }

    public void setPaymentDataId(int paymentDataId) {
        this.mPaymentDataId = paymentDataId;
    }

    public String getCVV() {
        return this.mCVV;
    }

    public void setCVV(String CVV) {
        this.mCVV = CVV;
    }

    public String getIpAddress() {
        return this.mIpAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.mIpAddress = ipAddress;
    }

    public String getDeviceFingerPrintId() {
        return this.mDeviceFingerPrintId;
    }

    public void setDeviceFingerPrintId(String deviceFingerPrintId) {
        this.mDeviceFingerPrintId = deviceFingerPrintId;
    }

    public boolean isNewCardStub() {
        return this.mNewCardStub;
    }

    public void setNewCardStub(boolean newCardStub) {
        this.mNewCardStub = newCardStub;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mPOD == null ? -1 : this.mPOD.ordinal());
        dest.writeInt(this.mCustomerPaymentMethodId);
        dest.writeInt(this.mPaymentMethodId);
        dest.writeString(this.mOrderPaymentId);
        dest.writeInt(this.mPaymentDataId);
        dest.writeString(this.mCVV);
        dest.writeString(this.mIpAddress);
        dest.writeString(this.mDeviceFingerPrintId);
        dest.writeValue(Boolean.valueOf(this.mNewCardStub));
        dest.writeInt(this.mNewCardStub ? 1 : 0);
    }

    protected OrderPayment(Parcel in) {
        int tmpMPOD = in.readInt();
        this.mPOD = tmpMPOD == -1 ? null : PointOfDistribution.values()[tmpMPOD];
        this.mCustomerPaymentMethodId = in.readInt();
        this.mPaymentMethodId = in.readInt();
        this.mOrderPaymentId = in.readString();
        this.mPaymentDataId = in.readInt();
        this.mCVV = in.readString();
        this.mIpAddress = in.readString();
        this.mDeviceFingerPrintId = in.readString();
        this.mNewCardStub = in.readInt() == 1;
    }
}
