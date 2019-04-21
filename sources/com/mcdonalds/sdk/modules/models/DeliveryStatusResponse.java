package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Date;

public class DeliveryStatusResponse implements Parcelable {
    public static final Creator<DeliveryStatusResponse> CREATOR = new C40471();
    public static final int STATUS_DELIVERY_IN_PROGRESS = 3;
    public static final int STATUS_ORDER_CANCELLED = 5;
    public static final int STATUS_ORDER_DELIVERED = 4;
    public static final int STATUS_ORDER_IN_PROGRESS = 2;
    public static final int STATUS_ORDER_RECEIVED = 1;
    private String mOrderNumber;
    private int mStatus;
    private Date mTimestamp;
    private Date mTimestampInStoreLocalTime;

    /* renamed from: com.mcdonalds.sdk.modules.models.DeliveryStatusResponse$1 */
    static class C40471 implements Creator<DeliveryStatusResponse> {
        C40471() {
        }

        public DeliveryStatusResponse createFromParcel(Parcel source) {
            return new DeliveryStatusResponse(source);
        }

        public DeliveryStatusResponse[] newArray(int size) {
            return new DeliveryStatusResponse[size];
        }
    }

    public String getOrderNumber() {
        return this.mOrderNumber;
    }

    public void setmOrderNumber(String mOrderNumber) {
        this.mOrderNumber = mOrderNumber;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setStatus(int status) {
        this.mStatus = status;
    }

    public Date getTimestamp() {
        return this.mTimestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.mTimestamp = timestamp;
    }

    public Date getTimestampInStoreLocalTime() {
        return this.mTimestampInStoreLocalTime;
    }

    public void setTimestampInStoreLocalTime(Date timestampInStoreLocalTime) {
        this.mTimestampInStoreLocalTime = timestampInStoreLocalTime;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        long time;
        long j = -1;
        dest.writeString(this.mOrderNumber);
        dest.writeInt(this.mStatus);
        if (this.mTimestamp != null) {
            time = this.mTimestamp.getTime();
        } else {
            time = -1;
        }
        dest.writeLong(time);
        if (this.mTimestampInStoreLocalTime != null) {
            j = this.mTimestampInStoreLocalTime.getTime();
        }
        dest.writeLong(j);
    }

    protected DeliveryStatusResponse(Parcel in) {
        Date date = null;
        this.mOrderNumber = in.readString();
        this.mStatus = in.readInt();
        long tmpMTimestamp = in.readLong();
        this.mTimestamp = tmpMTimestamp == -1 ? null : new Date(tmpMTimestamp);
        long tmpMTimestampInStoreLocalTime = in.readLong();
        if (tmpMTimestampInStoreLocalTime != -1) {
            date = new Date(tmpMTimestampInStoreLocalTime);
        }
        this.mTimestampInStoreLocalTime = date;
    }
}
