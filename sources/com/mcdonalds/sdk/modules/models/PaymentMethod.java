package com.mcdonalds.sdk.modules.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.connectors.middleware.model.MWPaymentLabel;
import com.mcdonalds.sdk.connectors.middleware.model.MWPaymentMethod;
import com.mcdonalds.sdk.services.data.database.DatabaseModel;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.DatabaseField;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.ForeignKey;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PaymentMethod extends DatabaseModel implements Parcelable {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PAYMENT_MODE = "payment_mode";
    public static final String COLUMN_REGISTRATION_RETURN_URL = "registration_return_url";
    public static final Creator<PaymentMethod> CREATOR = new C25851();
    public static final String KEY_CASH = "supportedPaymentMethods.cash.expectedPaymentMethodID";
    public static final String KEY_CREDIT_CARD = "supportedPaymentMethods.creditCard.expectedPaymentMethodID";
    public static final String KEY_OTHER = "supportedPaymentMethods.other.expectedPaymentMethodID";
    public static final String KEY_SUPPORTED_PAYMENTS = "supportedPaymentMethods.paymentMethodIDs";
    public static final String KEY_THIRD_PARTY = "supportedPaymentMethods.thirdParty.expectedPaymentMethodID";
    public static final String KEY_WECHAT_PAYMENT = "supportedPaymentMethods.wechatPayment.expectedPaymentMethodID";
    public static final String TABLE_NAME = "payment_methods";
    private Integer mID;
    private String mName;
    private PaymentMode mPaymentMode;
    private Integer mPaymentType;
    private String mRegistrationReturnURL;
    private Integer mRegistrationType;

    /* renamed from: com.mcdonalds.sdk.modules.models.PaymentMethod$1 */
    static class C25851 implements Creator<PaymentMethod> {
        C25851() {
        }

        public PaymentMethod createFromParcel(Parcel source) {
            return new PaymentMethod(source);
        }

        public PaymentMethod[] newArray(int size) {
            return new PaymentMethod[size];
        }
    }

    public enum PaymentMode {
        Credit,
        Cash,
        ThirdPart,
        WeChat,
        Other
    }

    public PaymentMethod(Integer ID, String name, PaymentMode paymentMode) {
        this.mID = ID;
        this.mName = name;
        this.mPaymentMode = paymentMode;
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"id"};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("id", DatabaseModel.TYPE_INTEGER), new DatabaseField("name", "text"), new DatabaseField(COLUMN_PAYMENT_MODE, DatabaseModel.TYPE_INTEGER), new DatabaseField(COLUMN_REGISTRATION_RETURN_URL, "text")});
    }

    public List<ForeignKey> getForeignKeys() {
        return null;
    }

    public List<ContentValues> getForeignKeyValue(String key) {
        return null;
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put("id", this.mID);
        values.put("name", this.mName);
        values.put(COLUMN_PAYMENT_MODE, Integer.valueOf(Arrays.asList(PaymentMode.values()).indexOf(this.mPaymentMode)));
        values.put(COLUMN_REGISTRATION_RETURN_URL, this.mRegistrationReturnURL);
        return values;
    }

    public String getSelection() {
        return String.format("%s=?", new Object[]{"id"});
    }

    public String[] getSelectionArgs() {
        return new String[]{Integer.toString(this.mID.intValue())};
    }

    public void populateFromCursor(Cursor cursor) {
        this.mID = Integer.valueOf(cursor.getInt(cursor.getColumnIndex("id")));
        this.mName = cursor.getString(cursor.getColumnIndex("name"));
        this.mPaymentMode = PaymentMode.values()[cursor.getInt(cursor.getColumnIndex(COLUMN_PAYMENT_MODE))];
        this.mRegistrationReturnURL = cursor.getString(cursor.getColumnIndex(COLUMN_REGISTRATION_RETURN_URL));
    }

    @Deprecated
    public static PaymentMethod fromMWPaymentMethod(MWPaymentMethod mwPaymentMethod, Map<Integer, PaymentMode> intToModeMap) {
        PaymentMethod ret = new PaymentMethod();
        ret.setID(mwPaymentMethod.getPaymentMethodID());
        ret.setName(((MWPaymentLabel) mwPaymentMethod.getPaymentLabels().get(0)).name);
        PaymentMode paymentMode = (PaymentMode) intToModeMap.get(mwPaymentMethod.getPaymentMode());
        if (paymentMode == null) {
            paymentMode = PaymentMode.Other;
        }
        ret.setPaymentMode(paymentMode);
        ret.setPaymentType(mwPaymentMethod.getPaymentType());
        ret.setRegistrationType(mwPaymentMethod.getRegistrationType());
        return ret;
    }

    public Integer getID() {
        return this.mID;
    }

    public void setID(Integer ID) {
        this.mID = ID;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public PaymentMode getPaymentMode() {
        return this.mPaymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.mPaymentMode = paymentMode;
    }

    public Integer getRegistrationType() {
        return this.mRegistrationType;
    }

    public void setRegistrationType(Integer mRegistrationType) {
        this.mRegistrationType = mRegistrationType;
    }

    public Integer getPaymentType() {
        return this.mPaymentType;
    }

    public void setPaymentType(Integer mPaymentType) {
        this.mPaymentType = mPaymentType;
    }

    public String getRegistrationReturnURL() {
        return this.mRegistrationReturnURL;
    }

    public void setRegistrationReturnURL(String mRegistrationReturnURL) {
        this.mRegistrationReturnURL = mRegistrationReturnURL;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mID);
        dest.writeString(this.mName);
        dest.writeInt(this.mPaymentMode == null ? -1 : this.mPaymentMode.ordinal());
        dest.writeString(this.mRegistrationReturnURL);
    }

    protected PaymentMethod(Parcel in) {
        this.mID = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mName = in.readString();
        int tmpMPaymentMode = in.readInt();
        this.mPaymentMode = tmpMPaymentMode == -1 ? null : PaymentMode.values()[tmpMPaymentMode];
        this.mRegistrationReturnURL = in.readString();
    }
}
