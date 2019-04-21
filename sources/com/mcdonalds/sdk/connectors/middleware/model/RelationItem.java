package com.mcdonalds.sdk.connectors.middleware.model;

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

public class RelationItem extends DatabaseModel implements Parcelable {
    public static final String COLUMN_DISPLAY_ORDER = "is_customer_friendly";
    public static final String COLUMN_IS_DEFAULT = "default_quantity";
    public static final String COLUMN_LABEL = "min_quantity";
    public static final String COLUMN_RELATION_ID = "id";
    public static final Creator<RelationItem> CREATOR = new C40071();
    public static final String TABLE_NAME = "relationItem";
    public int displayOrder;
    /* renamed from: id */
    public String f6681id;
    public Boolean isDefault;
    public String label;

    /* renamed from: com.mcdonalds.sdk.connectors.middleware.model.RelationItem$1 */
    static class C40071 implements Creator<RelationItem> {
        C40071() {
        }

        public RelationItem createFromParcel(Parcel source) {
            return new RelationItem(source);
        }

        public RelationItem[] newArray(int size) {
            return new RelationItem[size];
        }
    }

    public Boolean isDefault() {
        return this.isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getId() {
        return this.f6681id;
    }

    public void setId(String id) {
        this.f6681id = id;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.f6681id);
        dest.writeString(this.label);
        dest.writeInt(this.displayOrder);
        dest.writeByte(this.isDefault.booleanValue() ? (byte) 1 : (byte) 0);
    }

    public String toString() {
        return "RelationItem{Id=\"" + this.f6681id + "\", label=\"" + this.label + "\", displayOrder=\"" + this.displayOrder + "\", isDefault=" + this.isDefault + "}";
    }

    protected RelationItem(Parcel in) {
        this.f6681id = in.readString();
        this.label = in.readString();
        this.displayOrder = in.readInt();
        this.isDefault = Boolean.valueOf(in.readByte() != (byte) 0);
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"id"};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("id", "text"), new DatabaseField("is_customer_friendly", DatabaseModel.TYPE_INTEGER), new DatabaseField("min_quantity", "text"), new DatabaseField("default_quantity", DatabaseModel.TYPE_INTEGER)});
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
        values.put("id", this.f6681id);
        values.put("is_customer_friendly", Integer.valueOf(this.displayOrder));
        values.put("min_quantity", this.label);
        values.put("default_quantity", Integer.valueOf(this.isDefault.booleanValue() ? 1 : 0));
        return values;
    }

    public String[] getSelectionArgs() {
        return new String[]{this.f6681id};
    }

    public void populateFromCursor(Cursor cursor) {
        populateFromCursor(cursor, null);
    }

    public void populateFromCursor(Cursor cursor, String alias) {
        String prefix = "";
        if (!(alias == null || alias.isEmpty())) {
            prefix = String.format("%s_", new Object[]{alias});
        }
        this.f6681id = cursor.getString(cursor.getColumnIndex(prefix + "id"));
        this.displayOrder = cursor.getInt(cursor.getColumnIndex(prefix + "is_customer_friendly"));
        this.label = cursor.getString(cursor.getColumnIndex(prefix + "min_quantity"));
        this.isDefault = Boolean.valueOf(getBooleanForInt(cursor.getInt(cursor.getColumnIndex(prefix + "default_quantity"))));
    }
}
