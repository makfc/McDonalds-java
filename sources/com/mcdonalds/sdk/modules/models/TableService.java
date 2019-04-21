package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;
import java.util.ArrayList;

public class TableService extends AppModel implements Parcelable {
    public static final Creator<TableService> CREATOR = new C40891();
    private boolean mEnablePOSTableService;
    private double mMinimumPurchaseAmount;
    private String mTableServiceEatin;
    private boolean mTableServiceEnableMap;
    private boolean mTableServiceLocatorEnabled;
    private int mTableServiceLocatorMaxNumberValue;
    private String mTableServiceTakeout;
    private ArrayList<ZoneDefinitions> mZoneDefinitionsList;

    /* renamed from: com.mcdonalds.sdk.modules.models.TableService$1 */
    static class C40891 implements Creator<TableService> {
        C40891() {
        }

        public TableService createFromParcel(Parcel in) {
            return new TableService(in);
        }

        public TableService[] newArray(int size) {
            return new TableService[size];
        }
    }

    public boolean isEnablePOSTableService() {
        return this.mEnablePOSTableService;
    }

    public void setEnablePOSTableService(boolean enablePOSTableService) {
        this.mEnablePOSTableService = enablePOSTableService;
    }

    public String getTableServiceEatin() {
        return this.mTableServiceEatin;
    }

    public void setTableServiceEatin(String tableServiceEatin) {
        this.mTableServiceEatin = tableServiceEatin;
    }

    public String getTableServiceTakeout() {
        return this.mTableServiceTakeout;
    }

    public void setTableServiceTakeout(String tableServiceTakeout) {
        this.mTableServiceTakeout = tableServiceTakeout;
    }

    public double getMinimumPurchaseAmount() {
        return this.mMinimumPurchaseAmount;
    }

    public void setMinimumPurchaseAmount(double minimumPurchaseAmount) {
        this.mMinimumPurchaseAmount = minimumPurchaseAmount;
    }

    public boolean isTableServiceLocatorEnabled() {
        return this.mTableServiceLocatorEnabled;
    }

    public void setTableServiceLocatorEnabled(boolean tableServiceLocatorEnabled) {
        this.mTableServiceLocatorEnabled = tableServiceLocatorEnabled;
    }

    public boolean isTableServiceEnableMap() {
        return this.mTableServiceEnableMap;
    }

    public void setTableServiceEnableMap(boolean tableServiceEnableMap) {
        this.mTableServiceEnableMap = tableServiceEnableMap;
    }

    public int getTableServiceLocatorMaxNumberValue() {
        return this.mTableServiceLocatorMaxNumberValue;
    }

    public void setTableServiceLocatorMaxNumberValue(int tableServiceLocatorMaxNumberValue) {
        this.mTableServiceLocatorMaxNumberValue = tableServiceLocatorMaxNumberValue;
    }

    public ArrayList<ZoneDefinitions> getZoneDefinitionsList() {
        return this.mZoneDefinitionsList;
    }

    public void setZoneDefinitionsList(ArrayList<ZoneDefinitions> zoneDefinitionsList) {
        this.mZoneDefinitionsList = zoneDefinitionsList;
    }

    protected TableService(Parcel in) {
        boolean z;
        boolean z2 = true;
        this.mEnablePOSTableService = in.readByte() != (byte) 0;
        this.mTableServiceEatin = in.readString();
        this.mTableServiceTakeout = in.readString();
        this.mMinimumPurchaseAmount = in.readDouble();
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.mTableServiceEnableMap = z;
        if (in.readByte() == (byte) 0) {
            z2 = false;
        }
        this.mTableServiceLocatorEnabled = z2;
        this.mTableServiceLocatorMaxNumberValue = in.readInt();
        this.mZoneDefinitionsList = new ArrayList();
        in.readList(this.mZoneDefinitionsList, ZoneDefinitions.class.getClassLoader());
    }

    public void writeToParcel(Parcel dest, int flags) {
        byte b;
        byte b2 = (byte) 1;
        dest.writeByte(this.mEnablePOSTableService ? (byte) 1 : (byte) 0);
        dest.writeString(this.mTableServiceEatin);
        dest.writeString(this.mTableServiceTakeout);
        dest.writeDouble(this.mMinimumPurchaseAmount);
        if (this.mTableServiceEnableMap) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        dest.writeByte(b);
        if (!this.mTableServiceLocatorEnabled) {
            b2 = (byte) 0;
        }
        dest.writeByte(b2);
        dest.writeInt(this.mTableServiceLocatorMaxNumberValue);
        dest.writeList(this.mZoneDefinitionsList);
    }

    public int describeContents() {
        return 0;
    }
}
