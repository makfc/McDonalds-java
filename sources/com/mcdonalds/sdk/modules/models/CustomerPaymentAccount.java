package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;

public class CustomerPaymentAccount extends AppModel implements Parcelable {
    public static final Creator<CustomerPaymentAccount> CREATOR = new C40461();
    private String mAccountId;
    private int mCustomerPaymentMethodId;
    private boolean mExpired;
    private String mNickName;
    private boolean mOneTimePayment;
    private int mPaymentMethodId;
    private String mPaymentMode;
    private boolean mPreferred;
    private int mSchemaId;

    /* renamed from: com.mcdonalds.sdk.modules.models.CustomerPaymentAccount$1 */
    static class C40461 implements Creator<CustomerPaymentAccount> {
        C40461() {
        }

        public CustomerPaymentAccount createFromParcel(Parcel source) {
            return new CustomerPaymentAccount(source);
        }

        public CustomerPaymentAccount[] newArray(int size) {
            return new CustomerPaymentAccount[size];
        }
    }

    public boolean isExpired() {
        return this.mExpired;
    }

    public void setExpired(boolean expired) {
        this.mExpired = expired;
    }

    public boolean isPreferred() {
        return this.mPreferred;
    }

    public void setPreferred(boolean preferred) {
        this.mPreferred = preferred;
    }

    public boolean isOneTimePayment() {
        return this.mOneTimePayment;
    }

    public void setOneTimePayment(boolean oneTimePayment) {
        this.mOneTimePayment = oneTimePayment;
    }

    public String getAccountId() {
        return this.mAccountId;
    }

    public void setAccountId(String accountId) {
        this.mAccountId = accountId;
    }

    public String getNickName() {
        return this.mNickName;
    }

    public void setNickName(String nickName) {
        this.mNickName = nickName;
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

    public int getSchemaId() {
        return this.mSchemaId;
    }

    public void setSchemaId(int schemaId) {
        this.mSchemaId = schemaId;
    }

    public String getPaymentMode() {
        return this.mPaymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.mPaymentMode = paymentMode;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        byte b;
        byte b2 = (byte) 1;
        dest.writeByte(this.mExpired ? (byte) 1 : (byte) 0);
        if (this.mPreferred) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        dest.writeByte(b);
        if (!this.mOneTimePayment) {
            b2 = (byte) 0;
        }
        dest.writeByte(b2);
        dest.writeString(this.mAccountId);
        dest.writeString(this.mNickName);
        dest.writeInt(this.mCustomerPaymentMethodId);
        dest.writeInt(this.mPaymentMethodId);
        dest.writeInt(this.mSchemaId);
        dest.writeString(this.mPaymentMode);
    }

    protected CustomerPaymentAccount(Parcel in) {
        boolean z;
        boolean z2 = true;
        this.mExpired = in.readByte() != (byte) 0;
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.mPreferred = z;
        if (in.readByte() == (byte) 0) {
            z2 = false;
        }
        this.mOneTimePayment = z2;
        this.mAccountId = in.readString();
        this.mNickName = in.readString();
        this.mCustomerPaymentMethodId = in.readInt();
        this.mPaymentMethodId = in.readInt();
        this.mSchemaId = in.readInt();
        this.mPaymentMode = in.readString();
    }
}
