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
import java.util.Collections;
import java.util.List;

public class Promotion extends DatabaseModel implements Parcelable {
    public static final String COLUMN_ACTION_NAME = "action_name";
    public static final String COLUMN_DISCOUNT_AMOUNT = "discount_amount";
    public static final String COLUMN_DISPLAY_IMAGE_NAME = "display_image_name";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IS_VALID = "is_valid";
    public static final String COLUMN_ORIGINAL_PRICE = "original_price";
    public static final String COLUMN_PRODUCT_CODE = "product_code";
    public static final Creator<Promotion> CREATOR = new C40811();
    public static final String TABLE_NAME = "promotions";
    private String mActionName;
    private Double mDiscountAmount;
    private String mDisplayImageName;
    private Boolean mIsValid;
    private Double mOriginalPrice;
    private Integer mProductCode;
    private Integer mPromotionID;
    private List<Integer> mStaticData;

    /* renamed from: com.mcdonalds.sdk.modules.models.Promotion$1 */
    static class C40811 implements Creator<Promotion> {
        C40811() {
        }

        public Promotion createFromParcel(Parcel source) {
            return new Promotion(source);
        }

        public Promotion[] newArray(int size) {
            return new Promotion[size];
        }
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"id"};
    }

    public List<ForeignKey> getForeignKeys() {
        return Collections.singletonList(new ForeignKey("id", Product.TABLE_NAME, "external_id"));
    }

    public List<ContentValues> getForeignKeyValue(String key) {
        return null;
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("id", DatabaseModel.TYPE_INTEGER), new DatabaseField("product_code", DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_ORIGINAL_PRICE, DatabaseModel.TYPE_REAL), new DatabaseField(COLUMN_DISCOUNT_AMOUNT, DatabaseModel.TYPE_REAL), new DatabaseField("is_valid", DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_DISPLAY_IMAGE_NAME, "text"), new DatabaseField(COLUMN_ACTION_NAME, "text")});
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put("id", this.mPromotionID);
        values.put("product_code", this.mProductCode);
        values.put(COLUMN_ORIGINAL_PRICE, this.mOriginalPrice);
        values.put(COLUMN_DISCOUNT_AMOUNT, this.mDiscountAmount);
        values.put("is_valid", Integer.valueOf(getIntForBoolean(this.mIsValid.booleanValue())));
        values.put(COLUMN_DISPLAY_IMAGE_NAME, this.mDisplayImageName);
        values.put(COLUMN_ACTION_NAME, this.mActionName);
        return values;
    }

    public String getSelection() {
        return String.format("%s=?", new Object[]{"id"});
    }

    public String[] getSelectionArgs() {
        return new String[]{Integer.toString(this.mPromotionID.intValue())};
    }

    public void populateFromCursor(Cursor cursor) {
        this.mPromotionID = Integer.valueOf(cursor.getInt(cursor.getColumnIndex("id")));
        this.mProductCode = Integer.valueOf(cursor.getInt(cursor.getColumnIndex("product_code")));
        this.mOriginalPrice = Double.valueOf(cursor.getDouble(cursor.getColumnIndex(COLUMN_ORIGINAL_PRICE)));
        this.mDiscountAmount = Double.valueOf(cursor.getDouble(cursor.getColumnIndex(COLUMN_DISCOUNT_AMOUNT)));
        this.mIsValid = Boolean.valueOf(getBooleanForInt(cursor.getInt(cursor.getColumnIndex("is_valid"))));
        this.mDisplayImageName = cursor.getString(cursor.getColumnIndex(COLUMN_DISPLAY_IMAGE_NAME));
        this.mActionName = cursor.getString(cursor.getColumnIndex(COLUMN_ACTION_NAME));
    }

    public Integer getPromotionID() {
        return this.mPromotionID;
    }

    public void setPromotionID(Integer mPromotionID) {
        this.mPromotionID = mPromotionID;
    }

    public Integer getProductCode() {
        return this.mProductCode;
    }

    public void setProductCode(Integer mProductCode) {
        this.mProductCode = mProductCode;
    }

    public Double getOriginalPrice() {
        return this.mOriginalPrice;
    }

    public void setOriginalPrice(Double mOriginalPrice) {
        this.mOriginalPrice = mOriginalPrice;
    }

    public Double getDiscountAmount() {
        return this.mDiscountAmount;
    }

    public void setDiscountAmount(Double mDiscountAmount) {
        this.mDiscountAmount = mDiscountAmount;
    }

    public Boolean getIsValid() {
        return this.mIsValid;
    }

    public void setIsValid(Boolean mIsValid) {
        this.mIsValid = mIsValid;
    }

    public String getDisplayImageName() {
        return this.mDisplayImageName;
    }

    public void setDisplayImageName(String mDisplayImageName) {
        this.mDisplayImageName = mDisplayImageName;
    }

    public String getActionName() {
        return this.mActionName;
    }

    public void setActionName(String mActionName) {
        this.mActionName = mActionName;
    }

    public List<Integer> getStaticData() {
        return this.mStaticData;
    }

    public void setStaticData(List<Integer> mStaticData) {
        this.mStaticData = mStaticData;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mPromotionID);
        dest.writeValue(this.mProductCode);
        dest.writeValue(this.mOriginalPrice);
        dest.writeValue(this.mDiscountAmount);
        dest.writeValue(this.mIsValid);
        dest.writeString(this.mDisplayImageName);
        dest.writeList(this.mStaticData);
        dest.writeString(this.mActionName);
    }

    protected Promotion(Parcel in) {
        this.mPromotionID = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mProductCode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mOriginalPrice = (Double) in.readValue(Double.class.getClassLoader());
        this.mDiscountAmount = (Double) in.readValue(Double.class.getClassLoader());
        this.mIsValid = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mDisplayImageName = in.readString();
        this.mActionName = in.readString();
        this.mStaticData = new ArrayList();
        in.readList(this.mStaticData, Integer.class.getClassLoader());
    }
}
