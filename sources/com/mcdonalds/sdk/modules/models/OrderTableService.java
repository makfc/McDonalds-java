package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;

public class OrderTableService extends AppModel implements Parcelable {
    public static final Creator<OrderTableService> CREATOR = new C40721();
    private int mTableServiceId;
    private int mTableTagId;
    private int mTablseServiceZoneId;

    /* renamed from: com.mcdonalds.sdk.modules.models.OrderTableService$1 */
    static class C40721 implements Creator<OrderTableService> {
        C40721() {
        }

        public OrderTableService createFromParcel(Parcel in) {
            return new OrderTableService(in);
        }

        public OrderTableService[] newArray(int size) {
            return new OrderTableService[size];
        }
    }

    public int getTableTagId() {
        return this.mTableTagId;
    }

    public void setTableTagId(int tableTagId) {
        this.mTableTagId = tableTagId;
    }

    public int getTablseServiceZoneId() {
        return this.mTablseServiceZoneId;
    }

    public void setTablseServiceZoneId(int tablseServiceZoneId) {
        this.mTablseServiceZoneId = tablseServiceZoneId;
    }

    public int getTableServiceId() {
        return this.mTableServiceId;
    }

    public void setTableServiceId(int tableServiceId) {
        this.mTableServiceId = tableServiceId;
    }

    protected OrderTableService(Parcel in) {
        this.mTableServiceId = in.readInt();
        this.mTablseServiceZoneId = in.readInt();
        this.mTableTagId = in.readInt();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mTableServiceId);
        dest.writeInt(this.mTablseServiceZoneId);
        dest.writeInt(this.mTableTagId);
    }

    public int describeContents() {
        return 0;
    }
}
