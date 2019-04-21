package com.mcdonalds.sdk.modules.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.connectors.middleware.model.MWSocialNetwork;
import com.mcdonalds.sdk.services.data.database.DatabaseModel;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.DatabaseField;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.ForeignKey;
import java.util.Arrays;
import java.util.List;

public class SocialNetwork extends DatabaseModel implements Parcelable {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IMAGE_RESOURCE = "image_resource";
    public static final String COLUMN_IS_VALID = "is_valid";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final Creator<SocialNetwork> CREATOR = new C25861();
    public static final int FACEBOOK = 2;
    public static final String FACEBOOK_NAME = "Facebook";
    public static final int GOOGLEPLUS = 1;
    public static final String GOOGLEPLUS_NAME = "GooglePlus";
    public static final int MAX_SOCIAL_NETWORK_TYPE = 5;
    public static final int PINTEREST = 4;
    public static final String PINTEREST_NAME = "Pinterest";
    public static final String TABLE_NAME = "social_networks";
    public static final int TWITTER = 5;
    public static final String TWITTER_NAME = "Twitter";
    public static final int UNUSED = -1;
    public static final String UNUSED_NAME = "unused";
    public static final int WECHAT = 3;
    public static final String WECHAT_NAME = "WeChat";
    private int mImageResource;
    private boolean mIsValid;
    private int mSocialNetworkID;
    private String mSocialNetworkName;
    private int mType;

    /* renamed from: com.mcdonalds.sdk.modules.models.SocialNetwork$1 */
    static class C25861 implements Creator<SocialNetwork> {
        C25861() {
        }

        public SocialNetwork createFromParcel(Parcel source) {
            return new SocialNetwork(source);
        }

        public SocialNetwork[] newArray(int size) {
            return new SocialNetwork[size];
        }
    }

    public SocialNetwork(int socialNetworkID, int type) {
        this.mSocialNetworkID = socialNetworkID;
        this.mType = type;
        setImageResource();
    }

    @Deprecated
    public SocialNetwork(MWSocialNetwork socialNetwork) {
        int i;
        int i2 = -1;
        if (socialNetwork.socialNetworkID == null) {
            i = -1;
        } else {
            i = socialNetwork.socialNetworkID.intValue();
        }
        this.mSocialNetworkID = i;
        if (this.mSocialNetworkID > 0) {
            if (this.mSocialNetworkID < 5) {
                i2 = getSocialNetworkID();
            }
            this.mType = i2;
        }
        this.mSocialNetworkName = socialNetwork.socialNetworkName;
        this.mIsValid = socialNetwork.isValid;
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public String[] getPrimaryKeyNames() {
        return new String[]{"id"};
    }

    public List<DatabaseField> getFields() {
        return Arrays.asList(new DatabaseField[]{new DatabaseField("id", DatabaseModel.TYPE_INTEGER), new DatabaseField("type", DatabaseModel.TYPE_INTEGER), new DatabaseField("name", "text"), new DatabaseField(COLUMN_IMAGE_RESOURCE, DatabaseModel.TYPE_INTEGER), new DatabaseField("is_valid", DatabaseModel.TYPE_INTEGER)});
    }

    public List<ForeignKey> getForeignKeys() {
        return null;
    }

    public String getSelection() {
        return String.format("%s=?", new Object[]{"id"});
    }

    public List<ContentValues> getForeignKeyValue(String key) {
        return null;
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put("id", Integer.valueOf(this.mSocialNetworkID));
        values.put("type", Integer.valueOf(this.mType));
        values.put("name", this.mSocialNetworkName);
        values.put(COLUMN_IMAGE_RESOURCE, Integer.valueOf(this.mImageResource));
        values.put("is_valid", Integer.valueOf(getIntForBoolean(this.mIsValid)));
        return values;
    }

    public String[] getSelectionArgs() {
        return new String[]{String.valueOf(this.mSocialNetworkID)};
    }

    public void populateFromCursor(Cursor cursor) {
        this.mSocialNetworkID = cursor.getInt(cursor.getColumnIndex("id"));
        this.mType = cursor.getInt(cursor.getColumnIndex("type"));
        this.mSocialNetworkName = cursor.getString(cursor.getColumnIndex("name"));
        this.mImageResource = cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE_RESOURCE));
        this.mIsValid = getBooleanForInt(cursor.getInt(cursor.getColumnIndex("is_valid")));
    }

    public int getSocialNetworkID() {
        return this.mSocialNetworkID;
    }

    public void setSocialNetworkID(int socialNetworkID) {
        this.mSocialNetworkID = socialNetworkID;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int type) {
        this.mType = type;
        setImageResource();
    }

    public static String getNameForType(int type) {
        switch (type) {
            case 1:
                return GOOGLEPLUS_NAME;
            case 2:
                return FACEBOOK_NAME;
            case 3:
                return WECHAT_NAME;
            case 4:
                return PINTEREST_NAME;
            case 5:
                return TWITTER_NAME;
            default:
                return UNUSED_NAME;
        }
    }

    public int getImageResource() {
        return this.mImageResource;
    }

    public void setImageResource(int imageResource) {
        this.mImageResource = imageResource;
    }

    private void setImageResource() {
        switch (this.mType) {
            case 1:
                this.mImageResource = C3883R.C3881drawable.googleplus;
                return;
            case 2:
                this.mImageResource = C3883R.C3881drawable.facebook;
                return;
            case 3:
                this.mImageResource = C3883R.C3881drawable.wechat;
                return;
            case 4:
                this.mImageResource = C3883R.C3881drawable.pinterest;
                return;
            case 5:
                this.mImageResource = C3883R.C3881drawable.twitter;
                return;
            default:
                return;
        }
    }

    public String getSocialNetworkName() {
        return this.mSocialNetworkName;
    }

    public void setSocialNetworkName(String socialNetworkName) {
        this.mSocialNetworkName = socialNetworkName;
    }

    public boolean isValid() {
        return this.mIsValid;
    }

    public void setValid(boolean isValid) {
        this.mIsValid = isValid;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mSocialNetworkID);
        dest.writeInt(this.mType);
        dest.writeString(this.mSocialNetworkName);
        dest.writeByte(this.mIsValid ? (byte) 1 : (byte) 0);
    }

    protected SocialNetwork(Parcel in) {
        this.mSocialNetworkID = in.readInt();
        this.mType = in.readInt();
        this.mSocialNetworkName = in.readString();
        this.mIsValid = in.readByte() != (byte) 0;
    }
}
