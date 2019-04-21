package com.mcdonalds.sdk.modules.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.services.data.database.DatabaseModel;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.DatabaseField;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.ForeignKey;
import java.util.Arrays;
import java.util.List;

public class Pod extends DatabaseModel implements Parcelable {
    public static final String COLD_KIOSK = "ColdKiosk";
    public static final String COLD_KIOSK_DRINK = "ColdKioskDrink";
    public static final String COLUMN_SALE_TYPE_ID = "sale_type_id";
    public static final String COLUMN_TYPE_NAME = "type_name";
    public static final Creator<Pod> CREATOR = new C40751();
    public static final String DELIVERY = "Delivery";
    public static final String DRIVETHRU = "Drivethru";
    public static final String FRONT_COUNTER = "FrontCounter";
    public static final String HANDHELD = "Handheld";
    public static final String KIOSK = "Kiosk";
    public static final String MC_CAFE = "McCafe";
    public static final String MC_EXPRESS = "McExpress";
    public static final String PICKUP = "Pickup";
    public static final String TABLE_NAME = "pods";
    public static final String WALK_THRU = "WalkThru";
    private int mSaleTypeId;
    private String mTypeName;

    /* renamed from: com.mcdonalds.sdk.modules.models.Pod$1 */
    static class C40751 implements Creator<Pod> {
        C40751() {
        }

        public Pod createFromParcel(Parcel source) {
            return new Pod(source);
        }

        public Pod[] newArray(int size) {
            return new Pod[size];
        }
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{COLUMN_SALE_TYPE_ID};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField(COLUMN_SALE_TYPE_ID, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_TYPE_NAME, "text")});
    }

    public List<ForeignKey> getForeignKeys() {
        return null;
    }

    public List<ContentValues> getForeignKeyValue(String key) {
        return null;
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put(COLUMN_SALE_TYPE_ID, Integer.valueOf(this.mSaleTypeId));
        values.put(COLUMN_TYPE_NAME, this.mTypeName);
        return values;
    }

    public String getSelection() {
        return String.format("%s=?", new Object[]{COLUMN_SALE_TYPE_ID});
    }

    public String[] getSelectionArgs() {
        return new String[]{Integer.toString(this.mSaleTypeId)};
    }

    public void populateFromCursor(Cursor cursor) {
        this.mSaleTypeId = cursor.getInt(cursor.getColumnIndex(COLUMN_SALE_TYPE_ID));
        this.mTypeName = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_NAME));
    }

    public int getSaleTypeId() {
        return this.mSaleTypeId;
    }

    public void setSaleTypeId(int saleTypeId) {
        this.mSaleTypeId = saleTypeId;
    }

    public String getTypeName() {
        return this.mTypeName;
    }

    public void setTypeName(String typeName) {
        this.mTypeName = typeName;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this.mSaleTypeId != ((Pod) o).mSaleTypeId) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.mSaleTypeId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mSaleTypeId);
        dest.writeString(this.mTypeName);
    }

    protected Pod(Parcel in) {
        this.mSaleTypeId = in.readInt();
        this.mTypeName = in.readString();
    }
}
