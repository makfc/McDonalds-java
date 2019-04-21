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

public class Category extends DatabaseModel implements Parcelable {
    public static final String COLUMN_CATEGORY_DESCRIPTION = "category_description";
    public static final String COLUMN_CATEGORY_ID = "category_id";
    public static final String COLUMN_DISPLAY_ORDER = "display_order";
    public static final String COLUMN_DISPLAY_SIZE_SELECTION = "display_size_selection";
    public static final String COLUMN_EXTERNAL_ID = "external_id";
    public static final String COLUMN_MARKETING_NAME = "marketing_name";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PARENT_DISPLAY_CATEGORY_ID = "parent_display_category_id";
    public static final String COLUMN_SEQUENCE_ID = "sequence_id";
    public static final String COLUMN_TEST_CATEGORY = "test_category";
    public static final String COLUMN_TYPE = "type";
    private static final String CORE = "Core";
    public static final Creator<Category> CREATOR = new C40391();
    public static final String DO_NOT_SHOW = "do_not_show";
    public static final String TABLE_NAME = "categories";
    private String mCategoryDescription;
    private int mDisplayOrder;
    private int mDisplaySizeSelection;
    private String mDoNotShow;
    private int mExternalId;
    private int mID;
    private String mMarketingName;
    private String mName;
    private int mParentDisplayCategoryID;
    private int mSequenceID;
    private boolean mTestCategory;
    private int mType;

    /* renamed from: com.mcdonalds.sdk.modules.models.Category$1 */
    static class C40391 implements Creator<Category> {
        C40391() {
        }

        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        public Category[] newArray(int size) {
            return new Category[size];
        }
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"category_id"};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("category_id", DatabaseModel.TYPE_INTEGER), new DatabaseField("name", "text"), new DatabaseField(COLUMN_CATEGORY_DESCRIPTION, "text"), new DatabaseField(COLUMN_TEST_CATEGORY, DatabaseModel.TYPE_INTEGER), new DatabaseField("marketing_name", "text"), new DatabaseField("display_order", DatabaseModel.TYPE_INTEGER), new DatabaseField("type", DatabaseModel.TYPE_INTEGER), new DatabaseField("external_id", DatabaseModel.TYPE_INTEGER), new DatabaseField("display_size_selection", DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_PARENT_DISPLAY_CATEGORY_ID, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_SEQUENCE_ID, DatabaseModel.TYPE_INTEGER), new DatabaseField("do_not_show", "text")});
    }

    public List<ForeignKey> getForeignKeys() {
        return null;
    }

    public List<ContentValues> getForeignKeyValue(String key) {
        return null;
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put("category_id", Integer.valueOf(this.mID));
        values.put("name", this.mName);
        values.put(COLUMN_CATEGORY_DESCRIPTION, this.mCategoryDescription);
        values.put(COLUMN_TEST_CATEGORY, Boolean.valueOf(this.mTestCategory));
        values.put("marketing_name", this.mMarketingName);
        values.put("display_order", Integer.valueOf(this.mDisplayOrder));
        values.put("type", Integer.valueOf(this.mType));
        values.put("external_id", Integer.valueOf(this.mExternalId));
        values.put("display_size_selection", Integer.valueOf(this.mDisplaySizeSelection));
        values.put(COLUMN_PARENT_DISPLAY_CATEGORY_ID, Integer.valueOf(this.mParentDisplayCategoryID));
        values.put(COLUMN_SEQUENCE_ID, Integer.valueOf(this.mSequenceID));
        values.put("do_not_show", this.mDoNotShow);
        return values;
    }

    public String getSelection() {
        return String.format("%s=?", new Object[]{"category_id"});
    }

    public String[] getSelectionArgs() {
        return new String[]{Integer.toString(this.mID)};
    }

    public void populateFromCursor(Cursor cursor) {
        populateFromCursor(cursor, null);
    }

    public void populateFromCursor(Cursor cursor, String alias) {
        String prefix = "";
        if (!(alias == null || alias.isEmpty())) {
            prefix = String.format("%s_", new Object[]{alias});
        }
        this.mID = cursor.getInt(cursor.getColumnIndex(prefix + "category_id"));
        this.mName = cursor.getString(cursor.getColumnIndex(prefix + "name"));
        this.mCategoryDescription = cursor.getString(cursor.getColumnIndex(prefix + COLUMN_CATEGORY_DESCRIPTION));
        this.mTestCategory = getBooleanForInt(cursor.getInt(cursor.getColumnIndex(prefix + COLUMN_TEST_CATEGORY)));
        this.mMarketingName = cursor.getString(cursor.getColumnIndex(prefix + "marketing_name"));
        this.mDisplayOrder = cursor.getInt(cursor.getColumnIndex(prefix + "display_order"));
        this.mType = cursor.getInt(cursor.getColumnIndex(prefix + "type"));
        this.mExternalId = cursor.getInt(cursor.getColumnIndex(prefix + "external_id"));
        this.mDisplaySizeSelection = cursor.getInt(cursor.getColumnIndex(prefix + "display_size_selection"));
        this.mParentDisplayCategoryID = cursor.getInt(cursor.getColumnIndex(prefix + COLUMN_PARENT_DISPLAY_CATEGORY_ID));
        this.mSequenceID = cursor.getInt(cursor.getColumnIndex(prefix + COLUMN_SEQUENCE_ID));
        this.mDoNotShow = cursor.getString(cursor.getColumnIndex(prefix + "do_not_show"));
    }

    public boolean isTestCategory() {
        return this.mTestCategory;
    }

    public void setTestCategory(boolean testCategory) {
        this.mTestCategory = testCategory;
    }

    public String getMarketingName() {
        return this.mMarketingName;
    }

    public void setMarketingName(String marketingName) {
        this.mMarketingName = marketingName;
    }

    public int getDisplayOrder() {
        return this.mDisplayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.mDisplayOrder = displayOrder;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int type) {
        this.mType = type;
    }

    public int getExternalId() {
        return this.mExternalId;
    }

    public void setExternalId(int externalId) {
        this.mExternalId = externalId;
    }

    public int getID() {
        return this.mID;
    }

    public void setID(int ID) {
        this.mID = ID;
    }

    public int getDisplaySizeSelection() {
        return this.mDisplaySizeSelection;
    }

    public void setDisplaySizeSelection(int displaySizeSelection) {
        this.mDisplaySizeSelection = displaySizeSelection;
    }

    public boolean shouldShow() {
        return this.mDoNotShow != null && this.mDoNotShow.equals("Core");
    }

    public String getDoNotShow() {
        return this.mDoNotShow;
    }

    public void setDoNotShow(String doNotShow) {
        this.mDoNotShow = doNotShow;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getCategoryDescription() {
        return this.mCategoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.mCategoryDescription = categoryDescription;
    }

    public int getParentDisplayCategoryID() {
        return this.mParentDisplayCategoryID;
    }

    public void setParentDisplayCategoryID(int mParentDisplayCategoryID) {
        this.mParentDisplayCategoryID = mParentDisplayCategoryID;
    }

    public int getSequenceID() {
        return this.mSequenceID;
    }

    public void setSequenceID(int sequenceID) {
        this.mSequenceID = sequenceID;
    }

    public String toString() {
        return "Category{mID=" + this.mID + ", mName=\"" + this.mName + "\", mCategoryDescription=\"" + this.mCategoryDescription + "\", mTestCategory=" + this.mTestCategory + ", mMarketingName=\"" + this.mMarketingName + "\", mDisplayOrder=" + this.mDisplayOrder + ", mType=" + this.mType + ", mExternalId=" + this.mExternalId + "}";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this.mID != ((Category) o).mID) {
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
        dest.writeString(this.mName);
        dest.writeString(this.mCategoryDescription);
        dest.writeByte(this.mTestCategory ? (byte) 1 : (byte) 0);
        dest.writeString(this.mMarketingName);
        dest.writeInt(this.mDisplayOrder);
        dest.writeInt(this.mType);
        dest.writeInt(this.mExternalId);
        dest.writeInt(this.mDisplaySizeSelection);
        dest.writeInt(this.mParentDisplayCategoryID);
        dest.writeInt(this.mSequenceID);
        dest.writeString(this.mDoNotShow);
    }

    protected Category(Parcel in) {
        this.mID = in.readInt();
        this.mName = in.readString();
        this.mCategoryDescription = in.readString();
        this.mTestCategory = in.readByte() != (byte) 0;
        this.mMarketingName = in.readString();
        this.mDisplayOrder = in.readInt();
        this.mType = in.readInt();
        this.mExternalId = in.readInt();
        this.mDisplaySizeSelection = in.readInt();
        this.mParentDisplayCategoryID = in.readInt();
        this.mSequenceID = in.readInt();
        this.mDoNotShow = in.readString();
    }
}
