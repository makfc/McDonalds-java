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

public class StoreCatalog extends DatabaseModel implements Parcelable {
    public static final String COLUMN_STORE_ID = "store_id";
    public static final String COLUMN_VERSION = "version";
    public static final Creator<StoreCatalog> CREATOR = new C40861();
    public static final String TABLE_NAME = "store_catalogs";
    private List<Facility> mFacilities;
    private String mFacilitiesVersion;
    private String mProductPricesVersion;
    private List<Product> mProducts;
    private String mProductsVersion;
    private List<Promotion> mPromotions;
    private String mPromotionsVersion;
    private int mStoreId;
    private String mVersion;

    /* renamed from: com.mcdonalds.sdk.modules.models.StoreCatalog$1 */
    static class C40861 implements Creator<StoreCatalog> {
        C40861() {
        }

        public StoreCatalog createFromParcel(Parcel source) {
            return new StoreCatalog(source);
        }

        public StoreCatalog[] newArray(int size) {
            return new StoreCatalog[size];
        }
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"store_id"};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("store_id", DatabaseModel.TYPE_INTEGER), new DatabaseField("version", "text")});
    }

    public List<ForeignKey> getForeignKeys() {
        return Arrays.asList(new ForeignKey[]{new ForeignKey("store_id", Promotion.TABLE_NAME, "id", 2, null), new ForeignKey("store_id", Facility.TABLE_NAME, "id", 2, null)});
    }

    public List<ContentValues> getForeignKeyValue(String key) {
        Object obj = -1;
        switch (key.hashCode()) {
            case 536683137:
                if (key.equals(Facility.TABLE_NAME)) {
                    obj = 1;
                    break;
                }
                break;
            case 994220080:
                if (key.equals(Promotion.TABLE_NAME)) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return getRelationValues(this.mPromotions);
            case 1:
                return getRelationValues(this.mFacilities);
            default:
                return null;
        }
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put("store_id", Integer.valueOf(this.mStoreId));
        values.put("version", this.mVersion);
        return values;
    }

    public String getSelection() {
        return String.format("%s = ?", new Object[]{"store_id"});
    }

    public String[] getSelectionArgs() {
        return new String[]{Integer.toString(this.mStoreId)};
    }

    public void populateFromCursor(Cursor cursor) {
        this.mStoreId = cursor.getInt(cursor.getColumnIndex("store_id"));
        this.mVersion = cursor.getString(cursor.getColumnIndex("version"));
    }

    public int getStoreId() {
        return this.mStoreId;
    }

    public void setStoreId(int storeId) {
        this.mStoreId = storeId;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public void setVersion(String version) {
        this.mVersion = version;
    }

    public List<Promotion> getPromotions() {
        return this.mPromotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.mPromotions = promotions;
    }

    public List<Facility> getFacilities() {
        return this.mFacilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.mFacilities = facilities;
    }

    public List<Product> getProducts() {
        return this.mProducts;
    }

    public void setProducts(List<Product> products) {
        this.mProducts = products;
    }

    public String getPromotionsVersion() {
        return this.mPromotionsVersion;
    }

    public void setPromotionsVersion(String promotionsVersion) {
        this.mPromotionsVersion = promotionsVersion;
    }

    public String getFacilitiesVersion() {
        return this.mFacilitiesVersion;
    }

    public void setFacilitiesVersion(String facilitiesVersion) {
        this.mFacilitiesVersion = facilitiesVersion;
    }

    public String getProductsVersion() {
        return this.mProductsVersion;
    }

    public void setProductsVersion(String productsVersion) {
        this.mProductsVersion = productsVersion;
    }

    public String getProductPricesVersion() {
        return this.mProductPricesVersion;
    }

    public void setProductPricesVersion(String productPricesVersion) {
        this.mProductPricesVersion = productPricesVersion;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mStoreId);
        dest.writeString(this.mVersion);
        dest.writeString(this.mPromotionsVersion);
        dest.writeTypedList(this.mPromotions);
        dest.writeString(this.mFacilitiesVersion);
        dest.writeTypedList(this.mFacilities);
        dest.writeString(this.mProductsVersion);
        dest.writeTypedList(this.mProducts);
        dest.writeString(this.mProductPricesVersion);
    }

    protected StoreCatalog(Parcel in) {
        this.mStoreId = in.readInt();
        this.mVersion = in.readString();
        this.mPromotionsVersion = in.readString();
        this.mPromotions = in.createTypedArrayList(Promotion.CREATOR);
        this.mFacilitiesVersion = in.readString();
        this.mFacilities = in.createTypedArrayList(Facility.CREATOR);
        this.mProductsVersion = in.readString();
        this.mProducts = in.createTypedArrayList(Product.CREATOR);
        this.mProductPricesVersion = in.readString();
    }
}
