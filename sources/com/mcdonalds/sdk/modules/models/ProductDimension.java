package com.mcdonalds.sdk.modules.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.mcdonalds.sdk.services.data.database.DatabaseModel;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.DatabaseField;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.ForeignKey;
import java.util.Arrays;
import java.util.List;

public class ProductDimension extends DatabaseModel implements Parcelable, Comparable<ProductDimension> {
    public static final String COLUMN_DISPLAY_ORDER = "display_order";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_SHOW_SIZE_TO_COSTUMER = "show_size_to_costumer";
    public static final String COLUMN_SIZE_CODE = "size_code";
    public static final Creator<ProductDimension> CREATOR = new C40791();
    public static final String TABLE_NAME = "dimensions";
    private int mDisplayOrder;
    private Product mProduct;
    private boolean mShowSizeToCustomer;
    private int mSizeCode;

    /* renamed from: com.mcdonalds.sdk.modules.models.ProductDimension$1 */
    static class C40791 implements Creator<ProductDimension> {
        C40791() {
        }

        public ProductDimension createFromParcel(Parcel source) {
            return new ProductDimension(source);
        }

        public ProductDimension[] newArray(int size) {
            return new ProductDimension[size];
        }
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"product_id"};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("product_id", DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_SHOW_SIZE_TO_COSTUMER, DatabaseModel.TYPE_INTEGER), new DatabaseField("display_order", DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_SIZE_CODE, DatabaseModel.TYPE_INTEGER)});
    }

    public List<ForeignKey> getForeignKeys() {
        return null;
    }

    public List<ContentValues> getForeignKeyValue(String key) {
        return null;
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        if (this.mProduct != null) {
            values.put("product_id", this.mProduct.getExternalId());
        }
        values.put(COLUMN_SHOW_SIZE_TO_COSTUMER, Boolean.valueOf(this.mShowSizeToCustomer));
        values.put("display_order", Integer.valueOf(this.mDisplayOrder));
        values.put(COLUMN_SIZE_CODE, Integer.valueOf(this.mSizeCode));
        return values;
    }

    public String getSelection() {
        return String.format("%s=?", new Object[]{"product_id"});
    }

    public String[] getSelectionArgs() {
        if (this.mProduct == null) {
            return null;
        }
        return new String[]{Integer.toString(this.mProduct.getExternalId().intValue())};
    }

    public void populateFromCursor(Cursor cursor) {
        populateFromCursor(cursor, null);
    }

    public void populateFromCursor(Cursor cursor, String alias) {
        String prefix = "";
        if (!(alias == null || alias.isEmpty())) {
            prefix = String.format("%s_", new Object[]{alias});
        }
        this.mShowSizeToCustomer = getBooleanForInt(cursor.getInt(cursor.getColumnIndex(prefix + COLUMN_SHOW_SIZE_TO_COSTUMER)));
        this.mSizeCode = cursor.getInt(cursor.getColumnIndex(prefix + COLUMN_SIZE_CODE));
        this.mDisplayOrder = cursor.getInt(cursor.getColumnIndex(prefix + "display_order"));
    }

    public Product getProduct() {
        return this.mProduct;
    }

    public void setProduct(Product product) {
        this.mProduct = product;
    }

    public boolean getShowSizeToCustomer() {
        return this.mShowSizeToCustomer;
    }

    public void setShowSizeToCustomer(boolean showSizeToCustomer) {
        this.mShowSizeToCustomer = showSizeToCustomer;
    }

    public Integer getSizeCode() {
        return Integer.valueOf(this.mSizeCode);
    }

    public void setSizeCode(Integer sizeCode) {
        this.mSizeCode = sizeCode.intValue();
    }

    public int getDisplayOrder() {
        return this.mDisplayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.mDisplayOrder = displayOrder;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductDimension dimension = (ProductDimension) o;
        if (this.mProduct != null) {
            return this.mProduct.equals(dimension.mProduct);
        }
        if (dimension.mProduct != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.mProduct != null ? this.mProduct.hashCode() : 0;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.mProduct);
        dest.writeValue(Boolean.valueOf(this.mShowSizeToCustomer));
        dest.writeValue(Integer.valueOf(this.mSizeCode));
    }

    protected ProductDimension(Parcel in) {
        this.mProduct = (Product) in.readSerializable();
        this.mShowSizeToCustomer = ((Boolean) in.readValue(Boolean.class.getClassLoader())).booleanValue();
        this.mSizeCode = ((Integer) in.readValue(Integer.class.getClassLoader())).intValue();
    }

    public int compareTo(@NonNull ProductDimension another) {
        return this.mDisplayOrder - another.getDisplayOrder();
    }
}
