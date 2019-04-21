package com.mcdonalds.sdk.modules.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.mcdonalds.sdk.connectors.middleware.model.MWIngredient;
import com.mcdonalds.sdk.services.data.database.DatabaseModel;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.DatabaseField;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.ForeignKey;
import java.util.Arrays;
import java.util.List;

public class Ingredient extends DatabaseModel implements Parcelable, Comparable<Ingredient> {
    public static final String COLUMN_CHARGE_THRESHOLD = "charge_threshold";
    public static final String COLUMN_COST_INCLUSIVE = "cost_inclusive";
    public static final String COLUMN_DEFAULT_QUANTITY = "default_quantity";
    public static final String COLUMN_DEFAULT_SOLUTION = "default_solution";
    public static final String COLUMN_DISPLAY_ORDER = "display_order";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IS_CUSTOMER_FRIENDLY = "is_customer_friendly";
    public static final String COLUMN_MAX_QUANTITY = "max_quantity";
    public static final String COLUMN_MIN_QUANTITY = "min_quantity";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_REFERENCE_PRICE_PRODUCT_CODE = "reference_price_product_code";
    public static final String COLUMN_REFUND_THRESHOLD = "refund_threshold";
    public static final Creator<Ingredient> CREATOR = new C25721();
    public static final String TABLE_NAME = "ingredients";
    private int mChargeThreshold;
    private boolean mCostInclusive;
    private int mDefaultQuantity;
    private String mDefaultSolution;
    private int mDisplayOrder;
    private boolean mIsCustomerFriendly;
    private int mMaxQuantity;
    private int mMinQuantity;
    private Product mProduct;
    private int mReferencePriceProductCode;
    private int mRefundThreshold;

    /* renamed from: com.mcdonalds.sdk.modules.models.Ingredient$1 */
    static class C25721 implements Creator<Ingredient> {
        C25721() {
        }

        public Ingredient createFromParcel(Parcel source) {
            return new Ingredient(source);
        }

        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    }

    @Deprecated
    public static Ingredient fromMWIngredient(MWIngredient mwIngredient) {
        Ingredient ingredient = new Ingredient();
        ingredient.setIsCustomerFriendly(mwIngredient.isCustomerFriendly);
        ingredient.setMinQuantity(mwIngredient.minQuantity);
        ingredient.setDefaultQuantity(mwIngredient.defaultQuantity);
        ingredient.setMaxQuantity(mwIngredient.maxQuantity);
        ingredient.setRefundThreshold(mwIngredient.refundTreshold);
        ingredient.setChargeThreshold(mwIngredient.chargeTreshold);
        ingredient.setCostInclusive(mwIngredient.isCostInclusive);
        ingredient.setDefaultSolution(mwIngredient.defaultSolution);
        ingredient.setReferencePriceProductCode(mwIngredient.referencePriceProductCode);
        return ingredient;
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"id"};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("id", DatabaseModel.TYPE_INTEGER), new DatabaseField("product_id", DatabaseModel.TYPE_INTEGER), new DatabaseField("is_customer_friendly", DatabaseModel.TYPE_INTEGER), new DatabaseField("min_quantity", DatabaseModel.TYPE_INTEGER), new DatabaseField("default_quantity", DatabaseModel.TYPE_INTEGER), new DatabaseField("max_quantity", DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_REFUND_THRESHOLD, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_CHARGE_THRESHOLD, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_COST_INCLUSIVE, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_REFERENCE_PRICE_PRODUCT_CODE, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_DEFAULT_SOLUTION, DatabaseModel.TYPE_INTEGER), new DatabaseField("display_order", DatabaseModel.TYPE_INTEGER)});
    }

    public List<ForeignKey> getForeignKeys() {
        return null;
    }

    public List<ContentValues> getForeignKeyValue(String key) {
        return null;
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put("id", Integer.valueOf(hashCode()));
        if (this.mProduct != null) {
            values.put("product_id", this.mProduct.getExternalId());
        }
        values.put("is_customer_friendly", Boolean.valueOf(this.mIsCustomerFriendly));
        values.put("min_quantity", Integer.valueOf(this.mMinQuantity));
        values.put("default_quantity", Integer.valueOf(this.mDefaultQuantity));
        values.put("max_quantity", Integer.valueOf(this.mMaxQuantity));
        values.put(COLUMN_REFUND_THRESHOLD, Integer.valueOf(this.mRefundThreshold));
        values.put(COLUMN_CHARGE_THRESHOLD, Integer.valueOf(this.mChargeThreshold));
        values.put(COLUMN_COST_INCLUSIVE, Boolean.valueOf(this.mCostInclusive));
        values.put(COLUMN_REFERENCE_PRICE_PRODUCT_CODE, Integer.valueOf(this.mReferencePriceProductCode));
        values.put(COLUMN_DEFAULT_SOLUTION, this.mDefaultSolution);
        values.put("display_order", Integer.valueOf(this.mDisplayOrder));
        return values;
    }

    public String getSelection() {
        return String.format("%s=?", new Object[]{"id"});
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
        this.mIsCustomerFriendly = getBooleanForInt(cursor.getInt(cursor.getColumnIndex(prefix + "is_customer_friendly")));
        this.mMinQuantity = cursor.getInt(cursor.getColumnIndex(prefix + "min_quantity"));
        this.mDefaultQuantity = cursor.getInt(cursor.getColumnIndex(prefix + "default_quantity"));
        this.mMaxQuantity = cursor.getInt(cursor.getColumnIndex(prefix + "max_quantity"));
        this.mRefundThreshold = cursor.getInt(cursor.getColumnIndex(prefix + COLUMN_REFUND_THRESHOLD));
        this.mChargeThreshold = cursor.getInt(cursor.getColumnIndex(prefix + COLUMN_CHARGE_THRESHOLD));
        this.mCostInclusive = getBooleanForInt(cursor.getInt(cursor.getColumnIndex(prefix + COLUMN_COST_INCLUSIVE)));
        this.mReferencePriceProductCode = cursor.getInt(cursor.getColumnIndex(prefix + COLUMN_REFERENCE_PRICE_PRODUCT_CODE));
        this.mDisplayOrder = cursor.getInt(cursor.getColumnIndex(prefix + "display_order"));
        this.mDefaultSolution = cursor.getString(cursor.getColumnIndex(prefix + COLUMN_DEFAULT_SOLUTION));
    }

    public Product getProduct() {
        return this.mProduct;
    }

    public void setProduct(Product product) {
        this.mProduct = product;
    }

    public boolean getIsCustomerFriendly() {
        return this.mIsCustomerFriendly;
    }

    public void setIsCustomerFriendly(boolean isCustomerFriendly) {
        this.mIsCustomerFriendly = isCustomerFriendly;
    }

    public int getMinQuantity() {
        return this.mMinQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.mMinQuantity = minQuantity;
    }

    public int getDefaultQuantity() {
        return this.mDefaultQuantity;
    }

    public void setDefaultQuantity(int defaultQuantity) {
        this.mDefaultQuantity = defaultQuantity;
    }

    public int getMaxQuantity() {
        return this.mMaxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.mMaxQuantity = maxQuantity;
    }

    public int getRefundThreshold() {
        return this.mRefundThreshold;
    }

    public void setRefundThreshold(int refundThreshold) {
        this.mRefundThreshold = refundThreshold;
    }

    public int getChargeThreshold() {
        return this.mChargeThreshold;
    }

    public void setChargeThreshold(int chargeThreshold) {
        this.mChargeThreshold = chargeThreshold;
    }

    public boolean getCostInclusive() {
        return this.mCostInclusive;
    }

    public void setCostInclusive(boolean costInclusive) {
        this.mCostInclusive = costInclusive;
    }

    public String getDefaultSolution() {
        return this.mDefaultSolution;
    }

    public void setDefaultSolution(String defaultSolution) {
        this.mDefaultSolution = defaultSolution;
    }

    public int getReferencePriceProductCode() {
        return this.mReferencePriceProductCode;
    }

    public void setReferencePriceProductCode(int referencePriceProductCode) {
        this.mReferencePriceProductCode = referencePriceProductCode;
    }

    public int getDisplayOrder() {
        return this.mDisplayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.mDisplayOrder = displayOrder;
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ingredient that = (Ingredient) o;
        if (this.mIsCustomerFriendly != that.mIsCustomerFriendly || this.mMinQuantity != that.mMinQuantity || this.mDefaultQuantity != that.mDefaultQuantity || this.mMaxQuantity != that.mMaxQuantity || this.mRefundThreshold != that.mRefundThreshold || this.mChargeThreshold != that.mChargeThreshold || this.mCostInclusive != that.mCostInclusive || this.mReferencePriceProductCode != that.mReferencePriceProductCode || this.mDisplayOrder != that.mDisplayOrder) {
            return false;
        }
        if (this.mProduct != null) {
            if (!this.mProduct.equals(that.mProduct)) {
                return false;
            }
        } else if (that.mProduct != null) {
            return false;
        }
        if (this.mDefaultSolution != null) {
            z = this.mDefaultSolution.equals(that.mDefaultSolution);
        } else if (that.mDefaultSolution != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i;
        int i2 = 1;
        int i3 = 0;
        if (this.mIsCustomerFriendly) {
            i = 1;
        } else {
            i = 0;
        }
        i = (((((((((((i * 13) * 17) + this.mMinQuantity) * 19) + this.mDefaultQuantity) * 23) + this.mMaxQuantity) * 31) + this.mRefundThreshold) * 37) + this.mChargeThreshold) * 41;
        if (!this.mCostInclusive) {
            i2 = 0;
        }
        i2 = (i + i2) * 43;
        if (this.mDefaultSolution != null) {
            i = this.mDefaultSolution.hashCode();
        } else {
            i = 0;
        }
        i = (((((i2 + i) * 31) + this.mReferencePriceProductCode) * 31) + this.mDisplayOrder) * 13;
        if (this.mProduct != null) {
            i3 = this.mProduct.hashCode();
        }
        return i + i3;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mProduct, 0);
        dest.writeValue(Boolean.valueOf(this.mIsCustomerFriendly));
        dest.writeValue(Integer.valueOf(this.mMinQuantity));
        dest.writeValue(Integer.valueOf(this.mDefaultQuantity));
        dest.writeValue(Integer.valueOf(this.mMaxQuantity));
        dest.writeValue(Integer.valueOf(this.mRefundThreshold));
        dest.writeValue(Integer.valueOf(this.mChargeThreshold));
        dest.writeValue(Boolean.valueOf(this.mCostInclusive));
        dest.writeString(this.mDefaultSolution);
        dest.writeValue(Integer.valueOf(this.mReferencePriceProductCode));
    }

    protected Ingredient(Parcel in) {
        this.mProduct = (Product) in.readParcelable(Product.class.getClassLoader());
        this.mIsCustomerFriendly = ((Boolean) in.readValue(Boolean.class.getClassLoader())).booleanValue();
        this.mMinQuantity = ((Integer) in.readValue(Integer.class.getClassLoader())).intValue();
        this.mDefaultQuantity = ((Integer) in.readValue(Integer.class.getClassLoader())).intValue();
        this.mMaxQuantity = ((Integer) in.readValue(Integer.class.getClassLoader())).intValue();
        this.mRefundThreshold = ((Integer) in.readValue(Integer.class.getClassLoader())).intValue();
        this.mChargeThreshold = ((Integer) in.readValue(Integer.class.getClassLoader())).intValue();
        this.mCostInclusive = ((Boolean) in.readValue(Boolean.class.getClassLoader())).booleanValue();
        this.mDefaultSolution = in.readString();
        this.mReferencePriceProductCode = ((Integer) in.readValue(Integer.class.getClassLoader())).intValue();
    }

    public int compareTo(@NonNull Ingredient another) {
        return this.mDisplayOrder - another.getDisplayOrder();
    }
}
