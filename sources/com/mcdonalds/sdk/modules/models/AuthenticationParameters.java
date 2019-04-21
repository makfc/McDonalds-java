package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;

public class AuthenticationParameters implements Parcelable {
    public static final Creator<AuthenticationParameters> CREATOR = new C40371();
    private boolean mAllowSocialLoginWithoutEmail;
    private String mEmailAddress;
    private String mFirstName;
    private String mLastName;
    private String mNewPassword;
    private String mPassword;
    private String mSocialAuthenticationToken;
    private String mSocialProvider;
    private int mSocialServiceID;
    private String mSocialUserID;
    private String mUpdatedUserName;
    private String mUserName;
    private boolean mUsingSocialLogin;

    /* renamed from: com.mcdonalds.sdk.modules.models.AuthenticationParameters$1 */
    static class C40371 implements Creator<AuthenticationParameters> {
        C40371() {
        }

        public AuthenticationParameters createFromParcel(Parcel source) {
            return new AuthenticationParameters(source);
        }

        public AuthenticationParameters[] newArray(int size) {
            return new AuthenticationParameters[size];
        }
    }

    public boolean isAllowSocialLoginWithoutEmail() {
        return this.mAllowSocialLoginWithoutEmail;
    }

    public void setAllowSocialLoginWithoutEmail(boolean allowSocialLoginWithoutEmail) {
        this.mAllowSocialLoginWithoutEmail = allowSocialLoginWithoutEmail;
    }

    public boolean isUsingSocialLogin() {
        return this.mUsingSocialLogin;
    }

    public void setUsingSocialLogin(boolean usingSocialLogin) {
        this.mUsingSocialLogin = usingSocialLogin;
    }

    public int getSocialServiceID() {
        return this.mSocialServiceID;
    }

    public void setSocialServiceID(int socialServiceID) {
        this.mSocialServiceID = socialServiceID;
    }

    public String getUserName() {
        return this.mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    public String getUpdatedUserName() {
        return this.mUpdatedUserName;
    }

    public void setUpdatedUserName(String updatedUserName) {
        this.mUpdatedUserName = updatedUserName;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public String getNewPassword() {
        return this.mNewPassword;
    }

    public void setNewPassword(String newPassword) {
        this.mNewPassword = newPassword;
    }

    public String getSocialAuthenticationToken() {
        return this.mSocialAuthenticationToken;
    }

    public void setSocialAuthenticationToken(String socialAuthenticationToken) {
        this.mSocialAuthenticationToken = socialAuthenticationToken;
    }

    public String getSocialUserID() {
        return this.mSocialUserID;
    }

    public void setSocialUserID(String socialUserID) {
        this.mSocialUserID = socialUserID;
    }

    public String getEmailAddress() {
        return this.mEmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.mEmailAddress = emailAddress;
    }

    public String getLastName() {
        return this.mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public static AuthenticationParameters fromProfile(CustomerProfile profile) {
        AuthenticationParameters parameters = new AuthenticationParameters();
        if (profile.isUsingSocialLogin()) {
            if (profile.isUsingSocialLoginWithoutEmail()) {
                parameters.setUserName(profile.getUserName());
            } else {
                parameters.setUserName(profile.getEmailAddress());
            }
            parameters.setUsingSocialLogin(true);
            parameters.setSocialServiceID(profile.getSocialServiceAuthenticationID());
            parameters.setSocialAuthenticationToken(profile.getSocialAuthenticationToken());
            parameters.setSocialUserID(profile.getSocialUserID());
            parameters.setFirstName(profile.getFirstName());
            parameters.setLastName(profile.getLastName());
            parameters.setSocialProvider(profile.getSocialProvider());
        } else {
            parameters.setUserName(profile.getUserName());
            parameters.setPassword(profile.getPassword());
        }
        return parameters;
    }

    public String getSocialProvider() {
        return this.mSocialProvider;
    }

    public void setSocialProvider(String socialProvider) {
        this.mSocialProvider = socialProvider;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        byte b = (byte) 1;
        dest.writeByte(this.mAllowSocialLoginWithoutEmail ? (byte) 1 : (byte) 0);
        if (!this.mUsingSocialLogin) {
            b = (byte) 0;
        }
        dest.writeByte(b);
        dest.writeInt(this.mSocialServiceID);
        dest.writeString(this.mSocialAuthenticationToken);
        dest.writeString(this.mSocialUserID);
        dest.writeString(this.mUserName);
        dest.writeString(this.mUpdatedUserName);
        dest.writeString(this.mPassword);
        dest.writeString(this.mNewPassword);
        dest.writeString(this.mEmailAddress);
        dest.writeString(this.mSocialProvider);
    }

    protected AuthenticationParameters(Parcel in) {
        boolean z = true;
        this.mAllowSocialLoginWithoutEmail = in.readByte() != (byte) 0;
        if (in.readByte() == (byte) 0) {
            z = false;
        }
        this.mUsingSocialLogin = z;
        this.mSocialServiceID = in.readInt();
        this.mSocialAuthenticationToken = in.readString();
        this.mSocialUserID = in.readString();
        this.mUserName = in.readString();
        this.mUpdatedUserName = in.readString();
        this.mPassword = in.readString();
        this.mNewPassword = in.readString();
        this.mEmailAddress = in.readString();
        this.mSocialProvider = in.readString();
    }
}
