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

public class FeedBackType extends DatabaseModel implements Parcelable {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_VALID = "valid";
    public static final Creator<FeedBackType> CREATOR = new C40501();
    public static final String TABLE_NAME = "feedback_type";
    private int mID;
    private boolean mIsValid;
    private String mName;

    /* renamed from: com.mcdonalds.sdk.modules.models.FeedBackType$1 */
    static class C40501 implements Creator<FeedBackType> {
        C40501() {
        }

        public FeedBackType createFromParcel(Parcel source) {
            return new FeedBackType(source);
        }

        public FeedBackType[] newArray(int size) {
            return new FeedBackType[size];
        }
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"id"};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("id", DatabaseModel.TYPE_INTEGER), new DatabaseField("name", "text"), new DatabaseField("valid", DatabaseModel.TYPE_INTEGER)});
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
        values.put("id", Integer.valueOf(this.mID));
        values.put("name", this.mName);
        values.put("valid", Boolean.valueOf(this.mIsValid));
        return values;
    }

    public String[] getSelectionArgs() {
        return new String[]{String.valueOf(this.mID)};
    }

    public void populateFromCursor(Cursor cursor) {
        this.mID = cursor.getInt(cursor.getColumnIndex("id"));
        this.mName = cursor.getString(cursor.getColumnIndex("name"));
        this.mIsValid = getBooleanForInt(cursor.getInt(cursor.getColumnIndex("valid")));
    }

    public int getID() {
        return this.mID;
    }

    public void setID(int ID) {
        this.mID = ID;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public boolean isValid() {
        return this.mIsValid;
    }

    public void setValid(boolean valid) {
        this.mIsValid = valid;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mID);
        dest.writeString(this.mName);
        dest.writeByte(this.mIsValid ? (byte) 1 : (byte) 0);
    }

    protected FeedBackType(Parcel in) {
        this.mID = in.readInt();
        this.mName = in.readString();
        this.mIsValid = in.readByte() != (byte) 0;
    }
}
