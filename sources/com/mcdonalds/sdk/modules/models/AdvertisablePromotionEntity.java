package com.mcdonalds.sdk.modules.models;

import android.content.ContentValues;
import android.database.Cursor;
import com.mcdonalds.sdk.services.data.database.DatabaseModel;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.DatabaseField;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.ForeignKey;
import java.util.Arrays;
import java.util.List;

public class AdvertisablePromotionEntity extends DatabaseModel {
    public static final String COLUMN_BASE_PRODUCT_ID = "base_product_id";
    public static final String COLUMN_STORE_ID = "store_id";
    public static final String COLUMN_SWAP_PRODUCT_ID = "swap_product_id";
    public static final String COLUMN_WEEKDAY = "weekday";
    public static final String TABLE_NAME = "advertisable_promotion";
    private int mBaseProductId;
    private int mStoreId;
    private int mSwapProductId;
    private int mWeekday;

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"store_id", COLUMN_BASE_PRODUCT_ID, COLUMN_WEEKDAY};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("store_id", DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_BASE_PRODUCT_ID, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_SWAP_PRODUCT_ID, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_WEEKDAY, DatabaseModel.TYPE_INTEGER)});
    }

    public List<ForeignKey> getForeignKeys() {
        return Arrays.asList(new ForeignKey[]{new ForeignKey("store_id", StoreCatalog.TABLE_NAME, "store_id"), new ForeignKey(COLUMN_BASE_PRODUCT_ID, Product.TABLE_NAME, "external_id"), new ForeignKey(COLUMN_SWAP_PRODUCT_ID, Product.TABLE_NAME, "external_id")});
    }

    public String getSelection() {
        return String.format("%s=? and %s=? and %s=?", new Object[]{"store_id", COLUMN_BASE_PRODUCT_ID, COLUMN_WEEKDAY});
    }

    public List<ContentValues> getForeignKeyValue(String key) {
        return null;
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put("store_id", Integer.valueOf(this.mStoreId));
        values.put(COLUMN_BASE_PRODUCT_ID, Integer.valueOf(this.mBaseProductId));
        values.put(COLUMN_SWAP_PRODUCT_ID, Integer.valueOf(this.mSwapProductId));
        values.put(COLUMN_WEEKDAY, Integer.valueOf(this.mWeekday));
        return values;
    }

    public String[] getSelectionArgs() {
        return new String[]{Integer.toString(this.mStoreId), Integer.toString(this.mBaseProductId), Integer.toString(this.mWeekday)};
    }

    public void populateFromCursor(Cursor cursor) {
        this.mStoreId = cursor.getInt(cursor.getColumnIndex("store_id"));
        this.mBaseProductId = cursor.getInt(cursor.getColumnIndex(COLUMN_BASE_PRODUCT_ID));
        this.mSwapProductId = cursor.getInt(cursor.getColumnIndex(COLUMN_SWAP_PRODUCT_ID));
        this.mWeekday = cursor.getInt(cursor.getColumnIndex(COLUMN_WEEKDAY));
    }

    public int getStoreId() {
        return this.mStoreId;
    }

    public void setStoreId(int storeId) {
        this.mStoreId = storeId;
    }

    public int getBaseProductId() {
        return this.mBaseProductId;
    }

    public void setBaseProductId(int baseProductId) {
        this.mBaseProductId = baseProductId;
    }

    public int getSwapProductId() {
        return this.mSwapProductId;
    }

    public void setSwapProductId(int swapProductId) {
        this.mSwapProductId = swapProductId;
    }

    public int getWeekday() {
        return this.mWeekday;
    }

    public void setWeekday(int weekday) {
        this.mWeekday = weekday;
    }
}
