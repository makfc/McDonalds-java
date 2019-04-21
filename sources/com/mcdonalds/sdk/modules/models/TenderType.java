package com.mcdonalds.sdk.modules.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.services.data.database.DatabaseModel;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.DatabaseField;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.ForeignKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TenderType extends DatabaseModel implements Parcelable {
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_DEFAULT_TENDER_AMOUNT_DISPLAY = "default_tender_amount_display";
    public static final String COLUMN_DISPLAY_NAME = "display_name";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IS_DEFAULT = "is_default";
    public static final String COLUMN_IS_VALID = "is_valid";
    public static final String COLUMN_LAST_MODIFICATION = "last_modification";
    public static final String COLUMN_MARKET_ID = "market_id";
    public static final String COLUMN_MINIMUM_TENDER_AMOUNT = "minimum_tender_amount";
    public static final Creator<TenderType> CREATOR = new C40901();
    public static final String TABLE_NAME = "tender_types";
    public int mCode;
    public int mDefaultTenderAmountDisplay;
    public String mDisplayName;
    public int mID;
    public boolean mIsDefault;
    public boolean mIsValid;
    public String mLastModification;
    public int mMarketID;
    public int mMinimumTenderAmount;
    public List<Integer> mStaticsData;

    /* renamed from: com.mcdonalds.sdk.modules.models.TenderType$1 */
    static class C40901 implements Creator<TenderType> {
        C40901() {
        }

        public TenderType createFromParcel(Parcel source) {
            return new TenderType(source);
        }

        public TenderType[] newArray(int size) {
            return new TenderType[size];
        }
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"id"};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("id", DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_CODE, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_DISPLAY_NAME, "text"), new DatabaseField(COLUMN_MARKET_ID, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_MINIMUM_TENDER_AMOUNT, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_DEFAULT_TENDER_AMOUNT_DISPLAY, DatabaseModel.TYPE_INTEGER), new DatabaseField("is_default", DatabaseModel.TYPE_INTEGER), new DatabaseField("last_modification", "text"), new DatabaseField("is_valid", DatabaseModel.TYPE_INTEGER)});
    }

    public List<ForeignKey> getForeignKeys() {
        return null;
    }

    public List<ContentValues> getForeignKeyValue(String key) {
        return null;
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put("id", Integer.valueOf(this.mID));
        values.put(COLUMN_CODE, Integer.valueOf(this.mCode));
        values.put(COLUMN_DISPLAY_NAME, this.mDisplayName);
        values.put(COLUMN_MARKET_ID, Integer.valueOf(this.mMarketID));
        values.put(COLUMN_MINIMUM_TENDER_AMOUNT, Integer.valueOf(this.mMinimumTenderAmount));
        values.put(COLUMN_DEFAULT_TENDER_AMOUNT_DISPLAY, Integer.valueOf(this.mDefaultTenderAmountDisplay));
        values.put("is_default", Integer.valueOf(getIntForBoolean(this.mIsDefault)));
        values.put("last_modification", this.mLastModification);
        values.put("is_valid", Integer.valueOf(getIntForBoolean(this.mIsValid)));
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
        this.mCode = cursor.getInt(cursor.getColumnIndex(COLUMN_CODE));
        this.mDisplayName = cursor.getString(cursor.getColumnIndex(COLUMN_DISPLAY_NAME));
        this.mMarketID = cursor.getInt(cursor.getColumnIndex(COLUMN_MARKET_ID));
        this.mMinimumTenderAmount = cursor.getInt(cursor.getColumnIndex(COLUMN_MINIMUM_TENDER_AMOUNT));
        this.mDefaultTenderAmountDisplay = cursor.getInt(cursor.getColumnIndex(COLUMN_DEFAULT_TENDER_AMOUNT_DISPLAY));
        this.mIsDefault = getBooleanForInt(cursor.getInt(cursor.getColumnIndex("is_default")));
        this.mLastModification = cursor.getString(cursor.getColumnIndex("last_modification"));
        this.mIsValid = getBooleanForInt(cursor.getInt(cursor.getColumnIndex("is_valid")));
    }

    public int getID() {
        return this.mID;
    }

    public void setID(int ID) {
        this.mID = ID;
    }

    public int getCode() {
        return this.mCode;
    }

    public void setCode(int code) {
        this.mCode = code;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public void setDisplayName(String displayName) {
        this.mDisplayName = displayName;
    }

    public int getMarketID() {
        return this.mMarketID;
    }

    public void setMarketID(int marketID) {
        this.mMarketID = marketID;
    }

    public int getMinimumTenderAmount() {
        return this.mMinimumTenderAmount;
    }

    public void setMinimumTenderAmount(int minimumTenderAmount) {
        this.mMinimumTenderAmount = minimumTenderAmount;
    }

    public int getDefaultTenderAmountDisplay() {
        return this.mDefaultTenderAmountDisplay;
    }

    public void setDefaultTenderAmountDisplay(int defaultTenderAmountDisplay) {
        this.mDefaultTenderAmountDisplay = defaultTenderAmountDisplay;
    }

    public boolean isDefault() {
        return this.mIsDefault;
    }

    public void setDefault(boolean aDefault) {
        this.mIsDefault = aDefault;
    }

    public String getLastModification() {
        return this.mLastModification;
    }

    public void setLastModification(String lastModification) {
        this.mLastModification = lastModification;
    }

    public boolean isValid() {
        return this.mIsValid;
    }

    public void setValid(boolean valid) {
        this.mIsValid = valid;
    }

    public List<Integer> getStaticsData() {
        return this.mStaticsData;
    }

    public void setStaticsData(List<Integer> staticsData) {
        this.mStaticsData = staticsData;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        byte b;
        byte b2 = (byte) 1;
        dest.writeInt(this.mID);
        dest.writeInt(this.mCode);
        dest.writeString(this.mDisplayName);
        dest.writeInt(this.mMarketID);
        dest.writeInt(this.mMinimumTenderAmount);
        dest.writeInt(this.mDefaultTenderAmountDisplay);
        if (this.mIsDefault) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        dest.writeByte(b);
        dest.writeString(this.mLastModification);
        if (!this.mIsValid) {
            b2 = (byte) 0;
        }
        dest.writeByte(b2);
        dest.writeList(this.mStaticsData);
    }

    protected TenderType(Parcel in) {
        boolean z = true;
        this.mID = in.readInt();
        this.mCode = in.readInt();
        this.mDisplayName = in.readString();
        this.mMarketID = in.readInt();
        this.mMinimumTenderAmount = in.readInt();
        this.mDefaultTenderAmountDisplay = in.readInt();
        this.mIsDefault = in.readByte() != (byte) 0;
        this.mLastModification = in.readString();
        if (in.readByte() == (byte) 0) {
            z = false;
        }
        this.mIsValid = z;
        this.mStaticsData = new ArrayList();
        in.readList(this.mStaticsData, Integer.class.getClassLoader());
    }
}
