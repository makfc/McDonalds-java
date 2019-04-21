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

public class MenuType extends DatabaseModel implements Parcelable {
    public static final String COLUMN_COLOR = "color";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DISPLAY_IMAGE = "display_image";
    public static final String COLUMN_FROM_TIME = "from_time";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IS_VALID = "is_valid";
    public static final String COLUMN_LAST_MODIFICATION = "last_modification";
    public static final String COLUMN_LONG_NAME = "long_name";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SEQUENCE = "sequence";
    public static final String COLUMN_SHORT_NAME = "short_name";
    public static final Creator<MenuType> CREATOR = new C25731();
    public static final int DRINK = 2;
    public static final String TABLE_NAME = "menu_types";
    private String mColor;
    private String mDescription;
    private String mDisplayImage;
    private String mFromTime;
    private int mID;
    private boolean mIsValid;
    private String mLastModification;
    private String mLongName;
    private String mName;
    private String mSequence;
    private String mShortName;
    private List<Integer> mStaticsData;

    /* renamed from: com.mcdonalds.sdk.modules.models.MenuType$1 */
    static class C25731 implements Creator<MenuType> {
        C25731() {
        }

        public MenuType createFromParcel(Parcel source) {
            return new MenuType(source);
        }

        public MenuType[] newArray(int size) {
            return new MenuType[size];
        }
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"id"};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("id", DatabaseModel.TYPE_INTEGER), new DatabaseField("description", "text"), new DatabaseField(COLUMN_COLOR, "text"), new DatabaseField(COLUMN_DISPLAY_IMAGE, "text"), new DatabaseField("is_valid", DatabaseModel.TYPE_INTEGER), new DatabaseField("name", "text"), new DatabaseField("short_name", "text"), new DatabaseField("long_name", "text"), new DatabaseField(COLUMN_SEQUENCE, "text"), new DatabaseField("last_modification", "text"), new DatabaseField(COLUMN_FROM_TIME, "text")});
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
        values.put("description", this.mDescription);
        values.put(COLUMN_COLOR, this.mColor);
        values.put(COLUMN_DISPLAY_IMAGE, this.mDisplayImage);
        values.put("is_valid", Integer.valueOf(getIntForBoolean(this.mIsValid)));
        values.put("name", this.mName);
        values.put("short_name", this.mShortName);
        values.put("long_name", this.mLongName);
        values.put(COLUMN_SEQUENCE, this.mSequence);
        values.put("last_modification", this.mLastModification);
        values.put(COLUMN_FROM_TIME, this.mFromTime);
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
        this.mDescription = cursor.getString(cursor.getColumnIndex("description"));
        this.mColor = cursor.getString(cursor.getColumnIndex(COLUMN_COLOR));
        this.mDisplayImage = cursor.getString(cursor.getColumnIndex(COLUMN_DISPLAY_IMAGE));
        this.mIsValid = getBooleanForInt(cursor.getInt(cursor.getColumnIndex("is_valid")));
        this.mName = cursor.getString(cursor.getColumnIndex("name"));
        this.mShortName = cursor.getString(cursor.getColumnIndex("short_name"));
        this.mLongName = cursor.getString(cursor.getColumnIndex("long_name"));
        this.mSequence = cursor.getString(cursor.getColumnIndex(COLUMN_SEQUENCE));
        this.mLastModification = cursor.getString(cursor.getColumnIndex("last_modification"));
        this.mFromTime = cursor.getString(cursor.getColumnIndex(COLUMN_FROM_TIME));
    }

    public int getID() {
        return this.mID;
    }

    public void setID(int ID) {
        this.mID = ID;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getColor() {
        return this.mColor;
    }

    public void setColor(String color) {
        this.mColor = color;
    }

    public String getDisplayImage() {
        return this.mDisplayImage;
    }

    public void setDisplayImage(String mDisplayImage) {
        this.mDisplayImage = mDisplayImage;
    }

    public Boolean getIsValid() {
        return Boolean.valueOf(this.mIsValid);
    }

    public void setIsValid(Boolean isValid) {
        this.mIsValid = isValid.booleanValue();
    }

    @Deprecated
    public Name getName() {
        Name name = new Name();
        name.setName(this.mName);
        name.setShortName(this.mShortName);
        name.setLongName(this.mLongName);
        return name;
    }

    @Deprecated
    public void setName(Name name) {
        this.mName = name.getName();
        this.mShortName = name.getShortName();
        this.mLongName = name.getLongName();
    }

    public String getCommonName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getShortName() {
        return this.mShortName;
    }

    public void setShortName(String shortName) {
        this.mShortName = shortName;
    }

    public String getLongName() {
        return this.mLongName;
    }

    public void setLongName(String longName) {
        this.mLongName = longName;
    }

    public String getSequence() {
        return this.mSequence;
    }

    public void setSequence(String sequence) {
        this.mSequence = sequence;
    }

    public List<Integer> getStaticsData() {
        return this.mStaticsData;
    }

    public void setStaticsData(List<Integer> staticsData) {
        this.mStaticsData = staticsData;
    }

    public String getLastModification() {
        return this.mLastModification;
    }

    public void setLastModification(String lastModification) {
        this.mLastModification = lastModification;
    }

    public String getFromTime() {
        return this.mFromTime;
    }

    public void setFromTime(String mFromTime) {
        this.mFromTime = mFromTime;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this.mID != ((MenuType) o).mID) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.mID;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mID);
        dest.writeString(this.mDescription);
        dest.writeString(this.mColor);
        dest.writeString(this.mDisplayImage);
        dest.writeByte(this.mIsValid ? (byte) 1 : (byte) 0);
        dest.writeString(this.mName);
        dest.writeString(this.mShortName);
        dest.writeString(this.mLongName);
        dest.writeString(this.mSequence);
        dest.writeList(this.mStaticsData);
        dest.writeString(this.mLastModification);
        dest.writeString(this.mFromTime);
    }

    protected MenuType(Parcel in) {
        this.mID = in.readInt();
        this.mDescription = in.readString();
        this.mColor = in.readString();
        this.mDisplayImage = in.readString();
        this.mIsValid = in.readByte() != (byte) 0;
        this.mName = in.readString();
        this.mShortName = in.readString();
        this.mLongName = in.readString();
        this.mSequence = in.readString();
        this.mStaticsData = new ArrayList();
        in.readList(this.mStaticsData, Integer.class.getClassLoader());
        this.mLastModification = in.readString();
        this.mFromTime = in.readString();
    }
}
