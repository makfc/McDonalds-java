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

public class Allergen extends DatabaseModel implements Parcelable {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_VALUE = "value";
    public static final Creator<Allergen> CREATOR = new C40361();
    public static final String TABLE_NAME = "allergen";
    private int mId;
    private String mName;
    private String mValue;

    /* renamed from: com.mcdonalds.sdk.modules.models.Allergen$1 */
    static class C40361 implements Creator<Allergen> {
        C40361() {
        }

        public Allergen createFromParcel(Parcel in) {
            return new Allergen(in);
        }

        public Allergen[] newArray(int size) {
            return new Allergen[size];
        }
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"id"};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("id", DatabaseModel.TYPE_INTEGER), new DatabaseField("name", "text"), new DatabaseField("value", "text")});
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
        values.put("id", Integer.valueOf(this.mId));
        values.put("name", "text");
        values.put("value", "text");
        return values;
    }

    public String[] getSelectionArgs() {
        return new String[]{String.valueOf(this.mId)};
    }

    public void populateFromCursor(Cursor cursor) {
        this.mId = cursor.getInt(cursor.getColumnIndex("id"));
        this.mName = cursor.getString(cursor.getColumnIndex("name"));
        this.mValue = cursor.getString(cursor.getColumnIndex("value"));
    }

    protected Allergen(Parcel in) {
        this.mId = in.readInt();
        this.mName = in.readString();
        this.mValue = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeString(this.mName);
        dest.writeString(this.mValue);
    }

    public int describeContents() {
        return 0;
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getValue() {
        return this.mValue;
    }

    public void setValue(String value) {
        this.mValue = value;
    }
}
