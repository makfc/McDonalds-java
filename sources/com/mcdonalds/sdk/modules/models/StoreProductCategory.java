package com.mcdonalds.sdk.modules.models;

import android.content.ContentValues;
import android.database.Cursor;
import com.mcdonalds.sdk.services.data.database.DatabaseModel;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.DatabaseField;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.ForeignKey;
import java.util.Arrays;
import java.util.List;

public class StoreProductCategory extends DatabaseModel {
    public static final String COLUMN_CATEGORY_ID = "category_id";
    public static final String COLUMN_DISPLAY_ORDER = "display_order";
    public static final String COLUMN_DISPLAY_SIZE_SELECTION = "display_size_selection";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_STORE_ID = "store_id";
    public static final String TABLE_NAME = "store_product_category";
    private int mCategoryId;
    private int mDisplayOrder;
    private int mDisplaySizeSelection;
    private int mProductId;
    private int mStoreId;

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"product_id", "store_id", "category_id"};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("product_id", DatabaseModel.TYPE_INTEGER), new DatabaseField("store_id", DatabaseModel.TYPE_INTEGER), new DatabaseField("category_id", DatabaseModel.TYPE_INTEGER), new DatabaseField("display_order", DatabaseModel.TYPE_INTEGER), new DatabaseField("display_size_selection", DatabaseModel.TYPE_INTEGER)});
    }

    public List<ForeignKey> getForeignKeys() {
        r0 = new ForeignKey[2];
        r0[0] = new ForeignKey(new String[]{"product_id", "store_id"}, StoreProduct.TABLE_NAME, new String[]{"id", "store_id"});
        r0[1] = new ForeignKey("category_id", Category.TABLE_NAME, "category_id");
        return Arrays.asList(r0);
    }

    public String getSelection() {
        return String.format("%s=? and %s=? and %s=?", new Object[]{"product_id", "store_id", "category_id"});
    }

    public List<ContentValues> getForeignKeyValue(String key) {
        return null;
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put("product_id", Integer.valueOf(this.mProductId));
        values.put("store_id", Integer.valueOf(this.mStoreId));
        values.put("category_id", Integer.valueOf(this.mCategoryId));
        values.put("display_order", Integer.valueOf(this.mDisplayOrder));
        values.put("display_size_selection", Integer.valueOf(this.mDisplaySizeSelection));
        return values;
    }

    public String[] getSelectionArgs() {
        return new String[]{Integer.toString(this.mProductId), Integer.toString(this.mStoreId), Integer.toString(this.mCategoryId)};
    }

    public void populateFromCursor(Cursor cursor) {
        populateFromCursor(cursor, null);
    }

    public void populateFromCursor(Cursor cursor, String alias) {
        String prefix = "";
        if (!(alias == null || alias.isEmpty())) {
            prefix = String.format("%s_", new Object[]{alias});
        }
        this.mProductId = cursor.getInt(cursor.getColumnIndex(prefix + "product_id"));
        this.mStoreId = cursor.getInt(cursor.getColumnIndex(prefix + "store_id"));
        this.mCategoryId = cursor.getInt(cursor.getColumnIndex(prefix + "category_id"));
        this.mDisplayOrder = cursor.getInt(cursor.getColumnIndex(prefix + "display_order"));
        this.mDisplaySizeSelection = cursor.getInt(cursor.getColumnIndex(prefix + "display_size_selection"));
    }

    public int getProductId() {
        return this.mProductId;
    }

    public void setProductId(int productId) {
        this.mProductId = productId;
    }

    public int getStoreId() {
        return this.mStoreId;
    }

    public void setStoreId(int storeId) {
        this.mStoreId = storeId;
    }

    public int getCategoryId() {
        return this.mCategoryId;
    }

    public void setCategoryId(int categoryId) {
        this.mCategoryId = categoryId;
    }

    public int getDisplayOrder() {
        return this.mDisplayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.mDisplayOrder = displayOrder;
    }

    public int getDisplaySizeSelection() {
        return this.mDisplaySizeSelection;
    }

    public void setDisplaySizeSelection(int displaySizeSelection) {
        this.mDisplaySizeSelection = displaySizeSelection;
    }
}
