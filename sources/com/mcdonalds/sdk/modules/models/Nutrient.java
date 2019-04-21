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

public class Nutrient extends DatabaseModel implements Parcelable {
    public static final String COLUMN_ADULT_DAILY_VALUE = "adult_daily_value";
    public static final String COLUMN_CHILD_DAILY_VALUE = "child_daily_value";
    public static final String COLUMN_HUNDRED_G = "daily_g";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_UNIT = "unit";
    public static final String COLUMN_VALUE = "value";
    public static final String COLUMN_WOMAN_DAILY_VALUE = "woman_daily_value";
    public static final Creator<Nutrient> CREATOR = new C40571();
    public static final String TABLE_NAME = "nutrients";
    private String mAdultDailyValue;
    private String mChildDailyValue;
    private String mHundredG;
    private String mId;
    private String mName;
    private String mUnit;
    private String mValue;
    private String mWomanDailyValue;

    /* renamed from: com.mcdonalds.sdk.modules.models.Nutrient$1 */
    static class C40571 implements Creator<Nutrient> {
        C40571() {
        }

        public Nutrient createFromParcel(Parcel source) {
            return new Nutrient(source);
        }

        public Nutrient[] newArray(int size) {
            return new Nutrient[size];
        }
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"id"};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("id", "text"), new DatabaseField(COLUMN_UNIT, "text"), new DatabaseField("name", "text"), new DatabaseField("value", "text"), new DatabaseField(COLUMN_ADULT_DAILY_VALUE, "text"), new DatabaseField(COLUMN_WOMAN_DAILY_VALUE, "text"), new DatabaseField(COLUMN_CHILD_DAILY_VALUE, "text"), new DatabaseField(COLUMN_HUNDRED_G, "text")});
    }

    public List<ForeignKey> getForeignKeys() {
        return null;
    }

    public String getSelection() {
        return String.format("%s=?", new Object[]{"id"});
    }

    public List<ContentValues> getForeignKeyValue(String key) {
        return null;
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put("id", this.mId);
        values.put(COLUMN_UNIT, this.mUnit);
        values.put("name", this.mName);
        values.put("value", this.mValue);
        values.put(COLUMN_ADULT_DAILY_VALUE, this.mAdultDailyValue);
        values.put(COLUMN_WOMAN_DAILY_VALUE, this.mWomanDailyValue);
        values.put(COLUMN_CHILD_DAILY_VALUE, this.mChildDailyValue);
        values.put(COLUMN_HUNDRED_G, this.mHundredG);
        return values;
    }

    public String[] getSelectionArgs() {
        return new String[]{this.mId};
    }

    public void populateFromCursor(Cursor cursor) {
        this.mId = cursor.getString(cursor.getColumnIndex("id"));
        this.mUnit = cursor.getString(cursor.getColumnIndex(COLUMN_UNIT));
        this.mName = cursor.getString(cursor.getColumnIndex("name"));
        this.mValue = cursor.getString(cursor.getColumnIndex("value"));
        this.mAdultDailyValue = cursor.getString(cursor.getColumnIndex(COLUMN_ADULT_DAILY_VALUE));
        this.mWomanDailyValue = cursor.getString(cursor.getColumnIndex(COLUMN_WOMAN_DAILY_VALUE));
        this.mChildDailyValue = cursor.getString(cursor.getColumnIndex(COLUMN_CHILD_DAILY_VALUE));
        this.mHundredG = cursor.getString(cursor.getColumnIndex(COLUMN_HUNDRED_G));
    }

    public void setValue(String value) {
        this.mValue = value;
    }

    public String getValue() {
        return this.mValue;
    }

    public String getAdultDailyValue() {
        return this.mAdultDailyValue;
    }

    public void setAdultDailyValue(String adultDailyValue) {
        this.mAdultDailyValue = adultDailyValue;
    }

    public String getWomanDailyValue() {
        return this.mWomanDailyValue;
    }

    public void setWomanDailyValue(String womanDailyValue) {
        this.mWomanDailyValue = womanDailyValue;
    }

    public String getChildDailyValue() {
        return this.mChildDailyValue;
    }

    public void setChildDailyValue(String childDailyValue) {
        this.mChildDailyValue = childDailyValue;
    }

    public String getId() {
        return this.mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getUnit() {
        return this.mUnit;
    }

    public void setUnit(String unit) {
        this.mUnit = unit;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getHundredG() {
        return this.mHundredG;
    }

    public void setHundredG(String hundredG) {
        this.mHundredG = hundredG;
    }

    public String toString() {
        return "Nutrient{mId=\"" + this.mId + "\", mUnit=\"" + this.mUnit + "\", mName=\"" + this.mName + "\", mValue=" + this.mValue + ", mAdultDailyValue=" + this.mAdultDailyValue + ", mWomanDailyValue=" + this.mWomanDailyValue + ", mChildDailyValue=" + this.mChildDailyValue + "}";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mId);
        dest.writeString(this.mUnit);
        dest.writeString(this.mName);
        dest.writeString(this.mValue);
        dest.writeString(this.mAdultDailyValue);
        dest.writeString(this.mWomanDailyValue);
        dest.writeString(this.mChildDailyValue);
        dest.writeString(this.mHundredG);
    }

    protected Nutrient(Parcel in) {
        this.mId = in.readString();
        this.mUnit = in.readString();
        this.mName = in.readString();
        this.mValue = in.readString();
        this.mAdultDailyValue = in.readString();
        this.mWomanDailyValue = in.readString();
        this.mChildDailyValue = in.readString();
        this.mHundredG = in.readString();
    }
}
