package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;

public class CustomerLoginInfo extends AppModel implements Parcelable {
    public static final Creator<CustomerLoginInfo> CREATOR = new C40431();
    private boolean defaultPhoneNumberTakenOver;
    private boolean defaultPhoneNumberVerified;
    private boolean emailAddressTakenOver;
    private boolean emailAddressVerified;

    /* renamed from: com.mcdonalds.sdk.modules.models.CustomerLoginInfo$1 */
    static class C40431 implements Creator<CustomerLoginInfo> {
        C40431() {
        }

        public CustomerLoginInfo createFromParcel(Parcel source) {
            return new CustomerLoginInfo(source);
        }

        public CustomerLoginInfo[] newArray(int size) {
            return new CustomerLoginInfo[size];
        }
    }

    public CustomerLoginInfo(boolean emailAddressTakenOver, boolean emailAddressVerified, boolean defaultPhoneNumberTakenOver, boolean defaultPhoneNumberVerified) {
        this.emailAddressTakenOver = emailAddressTakenOver;
        this.emailAddressVerified = emailAddressVerified;
        this.defaultPhoneNumberTakenOver = defaultPhoneNumberTakenOver;
        this.defaultPhoneNumberVerified = defaultPhoneNumberVerified;
    }

    public boolean isEmailAddressTakenOver() {
        return this.emailAddressTakenOver;
    }

    public boolean isEmailAddressVerified() {
        return this.emailAddressVerified;
    }

    public boolean isDefaultPhoneNumberTakenOver() {
        return this.defaultPhoneNumberTakenOver;
    }

    public boolean isDefaultPhoneNumberVerified() {
        return this.defaultPhoneNumberVerified;
    }

    public void setDefaultPhoneNumberVerified(boolean phoneNumberVerified) {
        this.defaultPhoneNumberVerified = phoneNumberVerified;
    }

    public void setEmailAddressVerified(boolean emailVerified) {
        this.emailAddressVerified = emailVerified;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        byte b;
        byte b2 = (byte) 1;
        if (this.emailAddressTakenOver) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        dest.writeByte(b);
        if (this.emailAddressVerified) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        dest.writeByte(b);
        if (this.defaultPhoneNumberTakenOver) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        dest.writeByte(b);
        if (!this.defaultPhoneNumberVerified) {
            b2 = (byte) 0;
        }
        dest.writeByte(b2);
    }

    protected CustomerLoginInfo(Parcel in) {
        boolean z;
        boolean z2 = true;
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.emailAddressTakenOver = z;
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.emailAddressVerified = z;
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.defaultPhoneNumberTakenOver = z;
        if (in.readByte() == (byte) 0) {
            z2 = false;
        }
        this.defaultPhoneNumberVerified = z2;
    }
}
