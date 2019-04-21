package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class SocialLoginAuthenticationResults implements Parcelable {
    public static final Creator<SocialLoginAuthenticationResults> CREATOR = new C40851();
    private String mAccessToken;
    private boolean mAllowSocialLoginWithoutEmail;
    private String mEmailAddress;
    private String mFirstName;
    private String mLastName;
    private String mUserId;
    private String mZipCode;

    /* renamed from: com.mcdonalds.sdk.modules.models.SocialLoginAuthenticationResults$1 */
    static class C40851 implements Creator<SocialLoginAuthenticationResults> {
        C40851() {
        }

        public SocialLoginAuthenticationResults createFromParcel(Parcel source) {
            return new SocialLoginAuthenticationResults(source);
        }

        public SocialLoginAuthenticationResults[] newArray(int size) {
            return new SocialLoginAuthenticationResults[size];
        }
    }

    public SocialLoginAuthenticationResults(String accessToken) {
        this.mAccessToken = accessToken;
    }

    public SocialLoginAuthenticationResults(String username, String userId, String accessToken, boolean AllowSocialLoginWithoutEmail) {
        this.mFirstName = username;
        this.mUserId = userId;
        this.mAccessToken = accessToken;
        this.mAllowSocialLoginWithoutEmail = AllowSocialLoginWithoutEmail;
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return this.mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        this.mAccessToken = accessToken;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public void setUserId(String userId) {
        this.mUserId = userId;
    }

    public String getEmailAddress() {
        return this.mEmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.mEmailAddress = emailAddress;
    }

    public String getZipCode() {
        return this.mZipCode;
    }

    public void setZipCode(String zipCode) {
        this.mZipCode = zipCode;
    }

    public boolean isAllowSocialLoginWithoutEmail() {
        return this.mAllowSocialLoginWithoutEmail;
    }

    public void setAllowSocialLoginWithoutEmail(boolean allowSocialLoginWithoutEmail) {
        this.mAllowSocialLoginWithoutEmail = allowSocialLoginWithoutEmail;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mFirstName);
        dest.writeString(this.mLastName);
        dest.writeString(this.mAccessToken);
        dest.writeString(this.mUserId);
        dest.writeString(this.mEmailAddress);
        dest.writeString(this.mZipCode);
        dest.writeByte((byte) (this.mAllowSocialLoginWithoutEmail ? 1 : 0));
    }

    protected SocialLoginAuthenticationResults(Parcel in) {
        this.mFirstName = in.readString();
        this.mLastName = in.readString();
        this.mAccessToken = in.readString();
        this.mUserId = in.readString();
        this.mEmailAddress = in.readString();
        this.mZipCode = in.readString();
        this.mAllowSocialLoginWithoutEmail = in.readByte() != (byte) 0;
    }
}
