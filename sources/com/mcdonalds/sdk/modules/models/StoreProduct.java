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

public class StoreProduct extends DatabaseModel implements Parcelable {
    public static final String COLUMN_BASE_PRICE_DELIVERY = "base_price_delivery";
    public static final String COLUMN_BASE_PRICE_EAT_IN = "base_price_eat_in";
    public static final String COLUMN_BASE_PRICE_TAKE_OUT = "base_price_take_out";
    public static final String COLUMN_ENERGY = "energy";
    public static final String COLUMN_HAS_CHOICE = "has_choice";
    public static final String COLUMN_HAS_NON_SINGLE_CHOICE_CHOICE = "has_non_single_choice_choice";
    public static final String COLUMN_KCAL = "kcal";
    public static final String COLUMN_MAX_ENERGY = "max_energy";
    public static final String COLUMN_MIN_ENERGY = "min_energy";
    public static final String COLUMN_PRICE_DELIVERY = "price_delivery";
    public static final String COLUMN_PRICE_EAT_IN = "price_eat_in";
    public static final String COLUMN_PRICE_TAKE_OUT = "price_take_out";
    public static final String COLUMN_PRODUCT_ID = "id";
    public static final String COLUMN_SINGLE_CHOICE = "single_choice";
    public static final String COLUMN_STORE_ID = "store_id";
    public static final Creator<StoreProduct> CREATOR = new C40881();
    public static final String RELATION_CHOICES = "store_products_choices";
    public static final String RELATION_COMMENTS = "store_product_comments";
    public static final String RELATION_EXTRAS = "store_product_extras";
    public static final String TABLE_NAME = "store_product";
    private double mBasePriceDelivery;
    private double mBasePriceEatIn;
    private double mBasePriceTakeOut;
    private List<Ingredient> mChoices;
    private List<Ingredient> mComments;
    private List<ProductDimension> mDimensions;
    private Double mEnergy;
    private List<Ingredient> mExtras;
    private Boolean mHasChoice;
    private Boolean mHasNonSingleChoiceChoice;
    private List<Ingredient> mIngredients;
    private Boolean mIsSingleChoice;
    private double mKCal;
    private int mMaxEnergy;
    private List<MenuType> mMenuTypes;
    private int mMinEnergy;
    private List<Pod> mPODs;
    private double mPriceDelivery;
    private double mPriceEatIn;
    private double mPriceTakeOut;
    private int mProductId;
    private int mStoreId;
    private List<StoreProductCategory> mStoreProductCategories;

    /* renamed from: com.mcdonalds.sdk.modules.models.StoreProduct$1 */
    static class C40881 implements Creator<StoreProduct> {
        C40881() {
        }

        public StoreProduct createFromParcel(Parcel source) {
            return new StoreProduct(source);
        }

        public StoreProduct[] newArray(int size) {
            return new StoreProduct[size];
        }
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"id", "store_id"};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("id", DatabaseModel.TYPE_INTEGER), new DatabaseField("store_id", DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_PRICE_EAT_IN, DatabaseModel.TYPE_REAL), new DatabaseField(COLUMN_PRICE_TAKE_OUT, DatabaseModel.TYPE_REAL), new DatabaseField(COLUMN_PRICE_DELIVERY, DatabaseModel.TYPE_REAL), new DatabaseField(COLUMN_BASE_PRICE_EAT_IN, DatabaseModel.TYPE_REAL), new DatabaseField(COLUMN_BASE_PRICE_TAKE_OUT, DatabaseModel.TYPE_REAL), new DatabaseField(COLUMN_BASE_PRICE_DELIVERY, DatabaseModel.TYPE_REAL), new DatabaseField(COLUMN_SINGLE_CHOICE, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_HAS_CHOICE, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_HAS_NON_SINGLE_CHOICE_CHOICE, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_ENERGY, DatabaseModel.TYPE_REAL), new DatabaseField(COLUMN_KCAL, DatabaseModel.TYPE_REAL), new DatabaseField(COLUMN_MIN_ENERGY, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_MAX_ENERGY, DatabaseModel.TYPE_INTEGER)});
    }

    public List<ForeignKey> getForeignKeys() {
        r12 = new ForeignKey[9];
        r12[5] = new ForeignKey(getPrimaryKeyNames(), Ingredient.TABLE_NAME, "id", 2, RELATION_EXTRAS);
        r12[6] = new ForeignKey(getPrimaryKeyNames(), Ingredient.TABLE_NAME, "id", 2, RELATION_CHOICES);
        r12[7] = new ForeignKey(getPrimaryKeyNames(), Ingredient.TABLE_NAME, "id", 2, RELATION_COMMENTS);
        r12[8] = new ForeignKey(getPrimaryKeyNames(), MenuType.TABLE_NAME, "id", 2, null);
        return Arrays.asList(r12);
    }

    public String getSelection() {
        return String.format("%s=? and %s=?", new Object[]{"id", "store_id"});
    }

    public String[] getSelectionArgs() {
        return new String[]{Integer.toString(this.mProductId), Integer.toString(this.mStoreId)};
    }

    public ContentValues getValues() {
        boolean z = false;
        ContentValues values = new ContentValues();
        values.put("id", Integer.valueOf(this.mProductId));
        values.put("store_id", Integer.valueOf(this.mStoreId));
        values.put(COLUMN_PRICE_EAT_IN, Double.valueOf(this.mPriceEatIn));
        values.put(COLUMN_PRICE_TAKE_OUT, Double.valueOf(this.mPriceTakeOut));
        values.put(COLUMN_PRICE_DELIVERY, Double.valueOf(this.mPriceDelivery));
        values.put(COLUMN_BASE_PRICE_EAT_IN, Double.valueOf(this.mBasePriceEatIn));
        values.put(COLUMN_BASE_PRICE_TAKE_OUT, Double.valueOf(this.mBasePriceTakeOut));
        values.put(COLUMN_BASE_PRICE_DELIVERY, Double.valueOf(this.mBasePriceDelivery));
        values.put(COLUMN_SINGLE_CHOICE, Integer.valueOf(getIntForBoolean(this.mIsSingleChoice == null ? false : this.mIsSingleChoice.booleanValue())));
        values.put(COLUMN_HAS_CHOICE, Integer.valueOf(getIntForBoolean(this.mHasChoice == null ? false : this.mHasChoice.booleanValue())));
        String str = COLUMN_HAS_NON_SINGLE_CHOICE_CHOICE;
        if (this.mHasNonSingleChoiceChoice != null) {
            z = this.mHasNonSingleChoiceChoice.booleanValue();
        }
        values.put(str, Integer.valueOf(getIntForBoolean(z)));
        values.put(COLUMN_ENERGY, getEnergy());
        values.put(COLUMN_KCAL, Double.valueOf(this.mKCal));
        values.put(COLUMN_MIN_ENERGY, Integer.valueOf(this.mMinEnergy));
        values.put(COLUMN_MAX_ENERGY, Integer.valueOf(this.mMaxEnergy));
        return values;
    }

    public List<ContentValues> getForeignKeyValue(String key) {
        Object obj = -1;
        switch (key.hashCode()) {
            case -2103719742:
                if (key.equals(Ingredient.TABLE_NAME)) {
                    obj = 3;
                    break;
                }
                break;
            case -1863757131:
                if (key.equals(RELATION_CHOICES)) {
                    obj = 5;
                    break;
                }
                break;
            case -1506870575:
                if (key.equals(RELATION_EXTRAS)) {
                    obj = 4;
                    break;
                }
                break;
            case -84472423:
                if (key.equals(MenuType.TABLE_NAME)) {
                    obj = null;
                    break;
                }
                break;
            case 3446478:
                if (key.equals(Pod.TABLE_NAME)) {
                    obj = 1;
                    break;
                }
                break;
            case 414334925:
                if (key.equals(ProductDimension.TABLE_NAME)) {
                    obj = 2;
                    break;
                }
                break;
            case 508200674:
                if (key.equals(RELATION_COMMENTS)) {
                    obj = 6;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return getRelationValues(this.mMenuTypes);
            case 1:
                return getRelationValues(this.mPODs);
            case 2:
                return getRelationValues(this.mDimensions);
            case 3:
                return getRelationValues(this.mIngredients);
            case 4:
                return getRelationValues(this.mExtras);
            case 5:
                return getRelationValues(this.mChoices);
            case 6:
                return getRelationValues(this.mComments);
            default:
                return null;
        }
    }

    public void populateFromCursor(Cursor cursor) {
        populateFromCursor(cursor, null);
    }

    public void populateFromCursor(Cursor cursor, String alias) {
        String prefix = "";
        if (!(alias == null || alias.isEmpty())) {
            prefix = String.format("%s_", new Object[]{alias});
        }
        this.mProductId = cursor.getInt(cursor.getColumnIndex(prefix + "id"));
        this.mStoreId = cursor.getInt(cursor.getColumnIndex(prefix + "store_id"));
        this.mPriceEatIn = cursor.getDouble(cursor.getColumnIndex(prefix + COLUMN_PRICE_EAT_IN));
        this.mPriceTakeOut = cursor.getDouble(cursor.getColumnIndex(prefix + COLUMN_PRICE_TAKE_OUT));
        this.mPriceDelivery = cursor.getDouble(cursor.getColumnIndex(prefix + COLUMN_PRICE_DELIVERY));
        this.mBasePriceEatIn = cursor.getDouble(cursor.getColumnIndex(prefix + COLUMN_BASE_PRICE_EAT_IN));
        this.mBasePriceTakeOut = cursor.getDouble(cursor.getColumnIndex(prefix + COLUMN_BASE_PRICE_TAKE_OUT));
        this.mBasePriceDelivery = cursor.getDouble(cursor.getColumnIndex(prefix + COLUMN_BASE_PRICE_DELIVERY));
        this.mIsSingleChoice = Boolean.valueOf(getBooleanForInt(cursor.getInt(cursor.getColumnIndex(prefix + COLUMN_SINGLE_CHOICE))));
        this.mHasChoice = Boolean.valueOf(getBooleanForInt(cursor.getInt(cursor.getColumnIndex(prefix + COLUMN_HAS_CHOICE))));
        this.mHasNonSingleChoiceChoice = Boolean.valueOf(getBooleanForInt(cursor.getInt(cursor.getColumnIndex(prefix + COLUMN_HAS_NON_SINGLE_CHOICE_CHOICE))));
        this.mEnergy = Double.valueOf(cursor.getDouble(cursor.getColumnIndex(prefix + COLUMN_ENERGY)));
        this.mKCal = cursor.getDouble(cursor.getColumnIndex(prefix + COLUMN_KCAL));
        this.mMinEnergy = cursor.getInt(cursor.getColumnIndex(prefix + COLUMN_MIN_ENERGY));
        this.mMaxEnergy = cursor.getInt(cursor.getColumnIndex(prefix + COLUMN_MAX_ENERGY));
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

    public double getPriceEatIn() {
        return this.mPriceEatIn;
    }

    public void setPriceEatIn(double priceEatIn) {
        this.mPriceEatIn = priceEatIn;
    }

    public double getPriceTakeOut() {
        return this.mPriceTakeOut;
    }

    public void setPriceTakeOut(double priceTakeOut) {
        this.mPriceTakeOut = priceTakeOut;
    }

    public double getPriceDelivery() {
        return this.mPriceDelivery;
    }

    public void setPriceDelivery(double priceDelivery) {
        this.mPriceDelivery = priceDelivery;
    }

    public double getBasePriceEatIn() {
        return this.mBasePriceEatIn;
    }

    public void setBasePriceEatIn(double mBasePriceEatIn) {
        this.mBasePriceEatIn = mBasePriceEatIn;
    }

    public double getBasePriceTakeOut() {
        return this.mBasePriceTakeOut;
    }

    public void setBasePriceTakeOut(double mBasePriceTakeOut) {
        this.mBasePriceTakeOut = mBasePriceTakeOut;
    }

    public double getBasePriceDelivery() {
        return this.mBasePriceDelivery;
    }

    public void setBasePriceDelivery(double mBasePriceDelivery) {
        this.mBasePriceDelivery = mBasePriceDelivery;
    }

    public Boolean isSingleChoice() {
        return this.mIsSingleChoice;
    }

    public void setSingleChoice(Boolean singleChoice) {
        this.mIsSingleChoice = singleChoice;
    }

    public Boolean hasChoice() {
        return this.mHasChoice;
    }

    public void setHasChoice(Boolean hasChoice) {
        this.mHasChoice = hasChoice;
    }

    public Boolean hasNonSingleChoiceChoice() {
        return this.mHasNonSingleChoiceChoice;
    }

    public void setHasNonSingleChoiceChoice(Boolean hasNonSingleChoiceChoice) {
        this.mHasNonSingleChoiceChoice = hasNonSingleChoiceChoice;
    }

    public Double getEnergy() {
        return Double.valueOf(this.mEnergy == null ? 0.0d : this.mEnergy.doubleValue());
    }

    public void setEnergy(Double energy) {
        this.mEnergy = energy;
    }

    public double getKCal() {
        return this.mKCal;
    }

    public void setKCal(double kCal) {
        this.mKCal = kCal;
    }

    public List<Pod> getPODs() {
        return this.mPODs;
    }

    public void setPODs(List<Pod> PODs) {
        this.mPODs = PODs;
    }

    public List<ProductDimension> getDimensions() {
        return this.mDimensions;
    }

    public void setDimensions(List<ProductDimension> dimensions) {
        this.mDimensions = dimensions;
    }

    public List<Ingredient> getIngredients() {
        return this.mIngredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.mIngredients = ingredients;
    }

    public List<Ingredient> getExtras() {
        return this.mExtras;
    }

    public void setExtras(List<Ingredient> extras) {
        this.mExtras = extras;
    }

    public List<Ingredient> getChoices() {
        return this.mChoices;
    }

    public void setChoices(List<Ingredient> choices) {
        this.mChoices = choices;
    }

    public List<Ingredient> getComments() {
        return this.mComments;
    }

    public void setComments(List<Ingredient> comments) {
        this.mComments = comments;
    }

    public List<MenuType> getMenuTypes() {
        return this.mMenuTypes;
    }

    public void setMenuTypes(List<MenuType> menuTypes) {
        this.mMenuTypes = menuTypes;
    }

    public List<StoreProductCategory> getStoreProductCategories() {
        return this.mStoreProductCategories;
    }

    public void setStoreProductCategories(List<StoreProductCategory> storeProductCategories) {
        this.mStoreProductCategories = storeProductCategories;
    }

    public int getMinEnergy() {
        return this.mMinEnergy;
    }

    public void setMinEnergy(int minEnergy) {
        this.mMinEnergy = minEnergy;
    }

    public int getMaxEnergy() {
        return this.mMaxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.mMaxEnergy = maxEnergy;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StoreProduct that = (StoreProduct) o;
        if (this.mProductId != that.mProductId) {
            return false;
        }
        if (this.mStoreId != that.mStoreId) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.mProductId * 31) + this.mStoreId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mProductId);
        dest.writeInt(this.mStoreId);
        dest.writeDouble(this.mPriceEatIn);
        dest.writeDouble(this.mPriceTakeOut);
        dest.writeDouble(this.mPriceDelivery);
        dest.writeDouble(this.mBasePriceEatIn);
        dest.writeDouble(this.mBasePriceTakeOut);
        dest.writeDouble(this.mBasePriceDelivery);
        dest.writeValue(this.mIsSingleChoice);
        dest.writeValue(this.mHasChoice);
        dest.writeValue(this.mHasNonSingleChoiceChoice);
        dest.writeDouble(getEnergy().doubleValue());
        dest.writeTypedList(this.mPODs);
        dest.writeTypedList(this.mDimensions);
        dest.writeList(this.mIngredients);
        dest.writeList(this.mExtras);
        dest.writeList(this.mChoices);
        dest.writeList(this.mComments);
        dest.writeTypedList(this.mMenuTypes);
        dest.writeInt(this.mMinEnergy);
        dest.writeInt(this.mMaxEnergy);
    }

    protected StoreProduct(Parcel in) {
        this.mProductId = in.readInt();
        this.mStoreId = in.readInt();
        this.mPriceEatIn = in.readDouble();
        this.mPriceTakeOut = in.readDouble();
        this.mPriceDelivery = in.readDouble();
        this.mBasePriceEatIn = in.readDouble();
        this.mBasePriceTakeOut = in.readDouble();
        this.mBasePriceDelivery = in.readDouble();
        this.mIsSingleChoice = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mHasChoice = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mHasNonSingleChoiceChoice = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mEnergy = Double.valueOf(in.readDouble());
        this.mPODs = in.createTypedArrayList(Pod.CREATOR);
        this.mDimensions = in.createTypedArrayList(ProductDimension.CREATOR);
        this.mIngredients = new ArrayList();
        in.readList(this.mIngredients, Ingredient.class.getClassLoader());
        this.mExtras = new ArrayList();
        in.readList(this.mExtras, Ingredient.class.getClassLoader());
        this.mChoices = new ArrayList();
        in.readList(this.mChoices, Ingredient.class.getClassLoader());
        this.mComments = new ArrayList();
        in.readList(this.mComments, Ingredient.class.getClassLoader());
        this.mMenuTypes = in.createTypedArrayList(MenuType.CREATOR);
        this.mMinEnergy = in.readInt();
        this.mMaxEnergy = in.readInt();
    }
}
