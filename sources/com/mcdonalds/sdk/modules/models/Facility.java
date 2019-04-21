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

public class Facility extends DatabaseModel implements Parcelable {
    public static final String COLUMN_FACILITY_NAME = "facility_name";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IS_VALID = "is_valid";
    public static final String COLUMN_NAME = "name";
    public static final Creator<Facility> CREATOR = new C40481();
    public static final String TABLE_NAME = "facilities";
    private String mFacilityName;
    private int mID;
    private boolean mIsValid;
    private String mName;

    /* renamed from: com.mcdonalds.sdk.modules.models.Facility$1 */
    static class C40481 implements Creator<Facility> {
        C40481() {
        }

        public Facility createFromParcel(Parcel source) {
            return new Facility(source);
        }

        public Facility[] newArray(int size) {
            return new Facility[size];
        }
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"id"};
    }

    public List<ForeignKey> getForeignKeys() {
        return null;
    }

    public List<ContentValues> getForeignKeyValue(String key) {
        return null;
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("id", DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_FACILITY_NAME, "text"), new DatabaseField("is_valid", DatabaseModel.TYPE_INTEGER), new DatabaseField("name", "text")});
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put("id", Integer.valueOf(this.mID));
        values.put(COLUMN_FACILITY_NAME, this.mFacilityName);
        values.put("is_valid", Integer.valueOf(getIntForBoolean(this.mIsValid)));
        values.put("name", this.mName);
        return values;
    }

    public String getSelection() {
        return String.format("%s=?", new Object[]{"id"});
    }

    public String[] getSelectionArgs() {
        return new String[]{Integer.toString(this.mID)};
    }

    public void populateFromCursor(Cursor cursor) {
        this.mID = cursor.getInt(cursor.getColumnIndex("id"));
        this.mFacilityName = cursor.getString(cursor.getColumnIndex(COLUMN_FACILITY_NAME));
        this.mIsValid = getBooleanForInt(cursor.getInt(cursor.getColumnIndex("is_valid")));
        this.mName = cursor.getString(cursor.getColumnIndex("name"));
    }

    public int getID() {
        return this.mID;
    }

    public void setID(int mID) {
        this.mID = mID;
    }

    public String getFacilityName() {
        return this.mFacilityName;
    }

    public void setFacilityName(String mFacilityName) {
        this.mFacilityName = mFacilityName;
    }

    public boolean isValid() {
        return this.mIsValid;
    }

    public void setIsValid(boolean mIsValid) {
        this.mIsValid = mIsValid;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mID);
        dest.writeString(this.mFacilityName);
        dest.writeByte(this.mIsValid ? (byte) 1 : (byte) 0);
        dest.writeString(this.mName);
    }

    protected Facility(Parcel in) {
        this.mID = in.readInt();
        this.mFacilityName = in.readString();
        this.mIsValid = in.readByte() != (byte) 0;
        this.mName = in.readString();
    }
}
