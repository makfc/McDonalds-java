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

public class RecipeComponent extends DatabaseModel implements Parcelable {
    public static final String COLUMN_CUSTOMER_FRIENDLY = "customer_friendly";
    public static final String COLUMN_DEFAULT_QUANTITY = "min_default_quantity";
    public static final String COLUMN_DISPLAY_ORDER = "display_order";
    public static final String COLUMN_INGREDIENTS_STATEMENT = "ingredients_statement";
    public static final String COLUMN_MAX_QUANTITY = "max_quantity";
    public static final String COLUMN_MIN_QUANTITY = "min_quantity";
    public static final String COLUMN_PRODUCTS_ADDITIONAL_ALLERGEN = "product_additional_allergen";
    public static final String COLUMN_PRODUCTS_ALLERGEN = "product_allergen";
    public static final String COLUMN_PRODUCT_CODE = "product_code";
    public static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final Creator<RecipeComponent> CREATOR = new C40831();
    public static final String TABLE_NAME = "recipe_component";
    private String mAdditionalIngredient;
    private Boolean mCustomerFriendly;
    private int mDefaultQuantity;
    private int mDisplayOrder;
    private String mIngredientStatement;
    private int mMaxQuantity;
    private int mMinQuantity;
    private String mProductAdditionalAllergen;
    private String mProductAllergen;
    private long mProductCode;
    private String mProductName;

    /* renamed from: com.mcdonalds.sdk.modules.models.RecipeComponent$1 */
    static class C40831 implements Creator<RecipeComponent> {
        C40831() {
        }

        public RecipeComponent createFromParcel(Parcel source) {
            return new RecipeComponent(source);
        }

        public RecipeComponent[] newArray(int size) {
            return new RecipeComponent[size];
        }
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"product_code"};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("product_code", DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_PRODUCT_NAME, "text"), new DatabaseField(COLUMN_INGREDIENTS_STATEMENT, "text"), new DatabaseField(COLUMN_PRODUCTS_ALLERGEN, "text"), new DatabaseField(COLUMN_PRODUCTS_ADDITIONAL_ALLERGEN, "text"), new DatabaseField("display_order", DatabaseModel.TYPE_INTEGER), new DatabaseField("customer_friendly", DatabaseModel.TYPE_INTEGER), new DatabaseField("min_quantity", DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_DEFAULT_QUANTITY, DatabaseModel.TYPE_INTEGER), new DatabaseField("max_quantity", DatabaseModel.TYPE_INTEGER)});
    }

    public List<ForeignKey> getForeignKeys() {
        return null;
    }

    public String getSelection() {
        return String.format("%s=?", new Object[]{"product_code"});
    }

    public List<ContentValues> getForeignKeyValue(String key) {
        return null;
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put("product_code", Long.valueOf(this.mProductCode));
        values.put(COLUMN_PRODUCT_NAME, this.mProductName);
        values.put(COLUMN_INGREDIENTS_STATEMENT, this.mIngredientStatement);
        values.put(COLUMN_PRODUCTS_ALLERGEN, this.mProductAllergen);
        values.put(COLUMN_PRODUCTS_ADDITIONAL_ALLERGEN, this.mProductAdditionalAllergen);
        values.put("display_order", Integer.valueOf(this.mDisplayOrder));
        values.put("customer_friendly", Integer.valueOf(getIntForBoolean(this.mCustomerFriendly.booleanValue())));
        values.put("min_quantity", Integer.valueOf(this.mMinQuantity));
        values.put(COLUMN_DEFAULT_QUANTITY, Integer.valueOf(this.mDefaultQuantity));
        values.put("max_quantity", Integer.valueOf(this.mMaxQuantity));
        return values;
    }

    public String[] getSelectionArgs() {
        return new String[]{String.valueOf(this.mProductCode)};
    }

    public void populateFromCursor(Cursor cursor) {
        this.mProductCode = (long) cursor.getInt(cursor.getColumnIndex("product_code"));
        this.mProductName = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME));
        this.mIngredientStatement = cursor.getString(cursor.getColumnIndex(COLUMN_INGREDIENTS_STATEMENT));
        this.mProductAllergen = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCTS_ALLERGEN));
        this.mProductAdditionalAllergen = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCTS_ADDITIONAL_ALLERGEN));
        this.mDisplayOrder = cursor.getInt(cursor.getColumnIndex("display_order"));
        this.mCustomerFriendly = Boolean.valueOf(getBooleanForInt(cursor.getInt(cursor.getColumnIndex("customer_friendly"))));
        this.mMinQuantity = cursor.getInt(cursor.getColumnIndex("min_quantity"));
        this.mDefaultQuantity = cursor.getInt(cursor.getColumnIndex(COLUMN_DEFAULT_QUANTITY));
        this.mMaxQuantity = cursor.getInt(cursor.getColumnIndex("max_quantity"));
    }

    public String getProductName() {
        return this.mProductName;
    }

    public void setProductName(String productName) {
        this.mProductName = productName;
    }

    public String getIngredientStatement() {
        return this.mIngredientStatement;
    }

    public void setIngredientStatement(String ingredientStatement) {
        this.mIngredientStatement = ingredientStatement;
    }

    public String getProductAllergen() {
        return this.mProductAllergen;
    }

    public void setProductAllergen(String productAllergen) {
        this.mProductAllergen = productAllergen;
    }

    public String getProductAdditionalAllergen() {
        return this.mProductAdditionalAllergen;
    }

    public void setProductAdditionalAllergen(String productAdditionalAllergen) {
        this.mProductAdditionalAllergen = productAdditionalAllergen;
    }

    public int getDisplayOrder() {
        return this.mDisplayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.mDisplayOrder = displayOrder;
    }

    public Boolean getCustomerFriendly() {
        return this.mCustomerFriendly;
    }

    public void setCustomerFriendly(Boolean customerFriendly) {
        this.mCustomerFriendly = customerFriendly;
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

    public long getProductCode() {
        return this.mProductCode;
    }

    public void setProductCode(long productCode) {
        this.mProductCode = productCode;
    }

    public String toString() {
        return "RecipeComponent{mProductName='" + this.mProductName + '\'' + ", mIngredientStatement='" + this.mIngredientStatement + '\'' + ", mProductAllergen='" + this.mProductAllergen + '\'' + ", mProductAdditionalAllergen='" + this.mProductAdditionalAllergen + '\'' + ", mDisplayOrder=" + this.mDisplayOrder + '}';
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mProductName);
        dest.writeString(this.mIngredientStatement);
        dest.writeString(this.mProductAllergen);
        dest.writeString(this.mProductAdditionalAllergen);
        dest.writeInt(this.mDisplayOrder);
        dest.writeValue(this.mCustomerFriendly);
        dest.writeInt(this.mMinQuantity);
        dest.writeInt(this.mDefaultQuantity);
        dest.writeInt(this.mMaxQuantity);
        dest.writeLong(this.mProductCode);
    }

    protected RecipeComponent(Parcel in) {
        this.mProductName = in.readString();
        this.mIngredientStatement = in.readString();
        this.mProductAllergen = in.readString();
        this.mProductAdditionalAllergen = in.readString();
        this.mDisplayOrder = in.readInt();
        this.mCustomerFriendly = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mMinQuantity = in.readInt();
        this.mDefaultQuantity = in.readInt();
        this.mMaxQuantity = in.readInt();
        this.mProductCode = in.readLong();
    }

    public String getAdditionalIngredient() {
        return this.mAdditionalIngredient;
    }

    public void setAdditionalIngredient(String additionalIngredient) {
        this.mAdditionalIngredient = additionalIngredient;
    }
}
