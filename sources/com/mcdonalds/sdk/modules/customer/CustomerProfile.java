package com.mcdonalds.sdk.modules.customer;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.mcdonalds.sdk.modules.AppModel;
import com.mcdonalds.sdk.modules.models.CommunicationCategory;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.models.CustomerLoginInfo;
import com.mcdonalds.sdk.modules.models.CustomerOrderProduct;
import com.mcdonalds.sdk.modules.models.CustomerPaymentAccount;
import com.mcdonalds.sdk.modules.models.FavoriteItem;
import com.mcdonalds.sdk.modules.models.FavoriteItem.FavoriteProductType;
import com.mcdonalds.sdk.modules.models.NotificationPreferences;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.modules.storelocator.Store;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CustomerProfile extends AppModel implements Parcelable {
    public static final Creator<CustomerProfile> CREATOR = new C25561();
    private AccountDeleteType mAccountDeleteType;
    private List<CustomerPaymentAccount> mAccountItems;
    private boolean mAccountVerified;
    private int mActivationOption;
    private int mAgeOfCustomerId;
    private int mAgeOfOldestOrder;
    private String mAgeRange;
    private Calendar mBirthDate;
    private List<PaymentCard> mCardItems;
    private List<CustomerAddress> mCustomerAddresses;
    private long mCustomerId;
    private CustomerLoginInfo mCustomerLoginInfo;
    private Integer mDayOfBirth;
    private String mDeactivateAccountTimeStamp;
    private boolean mEmailActivated;
    private String mEmailAddress;
    private String mEthnicity;
    private List<FavoriteItem> mFavoriteItems;
    private List<String> mFavoriteStoreIdList;
    private List<Store> mFavoriteStores;
    private String mFirstName;
    private String mGender;
    private boolean mHasAcceptedPrivacyPolicy;
    private boolean mHasAcceptedTermsAndCondition;
    private boolean mIsActive;
    private String mLastName;
    private int mLoginPreference;
    private boolean mMSAlarmEnabled;
    private String mMiddleName;
    private String mMobileNumber;
    private Integer mMonthOfBirth;
    private String mNewPassword;
    private String mNewUserName;
    private String mNickName;
    private NotificationPreferences mNotificationPreferences;
    private int mNumberOfOrders;
    private Boolean mOptInForCommunicationChannel;
    private Boolean mOptInForContests;
    private Boolean mOptInForOtherMarketingMessages;
    private Boolean mOptInForProgramChanges;
    private Boolean mOptInForSurveys;
    private List<OptIn> mOptIns = new ArrayList(1);
    private String mPassword;
    private boolean mPasswordChangeRequired;
    private List<CommunicationCategory> mPreferredCommunicationCategories;
    private Locale mPreferredLocale;
    private Integer mPreferredNotification;
    private List<Integer> mPreferredOfferCategories;
    private String mPrivacyPolicyAcceptanceDate;
    private String mPrivacyPolicyVersion;
    private Boolean mReceivePromotions;
    private List<String> mRecentStoreIdList;
    private boolean mShouldChangeBirthdate;
    private boolean mShouldUpdatePrivacyPolicy;
    private boolean mShouldUpdateTermsAndCondition;
    private boolean mSmsVerified;
    private Boolean mSocialAccountLoginRegistered;
    private String mSocialAuthenticationToken;
    private String mSocialProvider;
    private int mSocialServiceAuthenticationID;
    private String mSocialUserID;
    private boolean mSubscribedToOffers;
    private String mTermsAndConditionAcceptanceDate;
    private String mTermsAndConditionVersion;
    private String mUserName;
    private boolean mUsingSocialLogin;
    private boolean mUsingSocialLoginWithoutEmail;
    private AccountVerificationType mVerificationType;
    private Integer mYearOfBirth;
    private String mZipCode;

    /* renamed from: com.mcdonalds.sdk.modules.customer.CustomerProfile$1 */
    static class C25561 implements Creator<CustomerProfile> {
        C25561() {
        }

        public CustomerProfile createFromParcel(Parcel source) {
            return new CustomerProfile(source);
        }

        public CustomerProfile[] newArray(int size) {
            return new CustomerProfile[size];
        }
    }

    public enum AccountDeleteType {
        DEACTIVATE,
        DELETE,
        NONE
    }

    public enum AccountVerificationType {
        SMS,
        EMAIL,
        NONE
    }

    public String getSocialProvider() {
        return this.mSocialProvider;
    }

    public void setSocialProvider(String socialProvider) {
        this.mSocialProvider = socialProvider;
    }

    public CustomerProfile() {
        setPreferredLocale(Locale.getDefault());
        this.mIsActive = true;
    }

    public long getCustomerId() {
        return this.mCustomerId;
    }

    public void setCustomerId(long customerId) {
        this.mCustomerId = customerId;
    }

    public String getUserName() {
        return this.mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    public String getNewUserName() {
        return this.mNewUserName;
    }

    public void setNewUserName(String mNewUserName) {
        this.mNewUserName = mNewUserName;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getMiddleName() {
        return this.mMiddleName;
    }

    public void setMiddleName(String middleName) {
        this.mMiddleName = middleName;
    }

    public String getLastName() {
        return this.mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public String getNickName() {
        return this.mNickName;
    }

    public void setNickName(String nickName) {
        this.mNickName = nickName;
    }

    public String getMobileNumber() {
        return this.mMobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mMobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return this.mEmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.mEmailAddress = emailAddress;
    }

    public Calendar getBirthDate() {
        return this.mBirthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        if (birthDate != null) {
            this.mBirthDate = birthDate;
            this.mYearOfBirth = Integer.valueOf(birthDate.get(1));
            this.mMonthOfBirth = Integer.valueOf(birthDate.get(2));
            this.mDayOfBirth = Integer.valueOf(birthDate.get(5));
            return;
        }
        this.mBirthDate = null;
        this.mYearOfBirth = null;
        this.mMonthOfBirth = null;
        this.mDayOfBirth = null;
    }

    public Integer getYearOfBirth() {
        return this.mYearOfBirth;
    }

    public void setYearOfBirth(Integer mYearOfBirth) {
        this.mYearOfBirth = mYearOfBirth;
    }

    public Integer getMonthOfBirth() {
        return this.mMonthOfBirth;
    }

    public void setMonthOfBirth(Integer mMonthOfBirth) {
        this.mMonthOfBirth = mMonthOfBirth;
    }

    public Integer getDayOfBirth() {
        return this.mDayOfBirth;
    }

    public void setDayOfBirth(Integer mDayOfBirth) {
        this.mDayOfBirth = mDayOfBirth;
    }

    public Locale getPreferredLocale() {
        return this.mPreferredLocale;
    }

    public void setPreferredLocale(Locale preferredLocale) {
        this.mPreferredLocale = preferredLocale;
    }

    public Integer getPreferredNotification() {
        return this.mPreferredNotification;
    }

    public void setPreferredNotification(Integer preferredNotification) {
        this.mPreferredNotification = preferredNotification;
    }

    public String getGender() {
        return this.mGender;
    }

    public void setGender(String mGender) {
        this.mGender = mGender;
    }

    public String getEthnicity() {
        return this.mEthnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.mEthnicity = ethnicity;
    }

    public String getAgeRange() {
        return this.mAgeRange;
    }

    public void setAgeRange(String ageRange) {
        this.mAgeRange = ageRange;
    }

    public boolean isSubscribedToOffers() {
        return this.mSubscribedToOffers;
    }

    public boolean isMobileVerified() {
        if (this.mCustomerLoginInfo != null) {
            return this.mCustomerLoginInfo.isDefaultPhoneNumberVerified();
        }
        return false;
    }

    public boolean isEmailVerified() {
        return this.mEmailActivated || (this.mCustomerLoginInfo != null && this.mCustomerLoginInfo.isEmailAddressVerified());
    }

    public boolean isEmailTakeOver() {
        if (this.mCustomerLoginInfo != null) {
            return this.mCustomerLoginInfo.isEmailAddressTakenOver();
        }
        return false;
    }

    public boolean isMobileTakeOver() {
        if (this.mCustomerLoginInfo != null) {
            return this.mCustomerLoginInfo.isDefaultPhoneNumberTakenOver();
        }
        return false;
    }

    public void setSubscribedToOffers(boolean subscribedToOffers) {
        this.mSubscribedToOffers = subscribedToOffers;
    }

    public Boolean isReceivePromotions() {
        return this.mReceivePromotions;
    }

    public void setReceivePromotions(Boolean receivePromotions) {
        this.mReceivePromotions = receivePromotions;
    }

    public List<CustomerAddress> getCustomerAddresses() {
        return this.mCustomerAddresses;
    }

    public void setCustomerAddresses(List<CustomerAddress> customerAddresses) {
        this.mCustomerAddresses = customerAddresses;
    }

    public List<PaymentCard> getCardItems() {
        return this.mCardItems;
    }

    public void setCardItems(List<PaymentCard> cardItems) {
        this.mCardItems = cardItems;
    }

    public List<CustomerPaymentAccount> getAccountItems() {
        return this.mAccountItems;
    }

    public void setAccountItems(List<CustomerPaymentAccount> accountItems) {
        this.mAccountItems = accountItems;
    }

    public List<String> getFavoriteStoreIdList() {
        return this.mFavoriteStoreIdList;
    }

    public void setFavoriteStoreIdList(List<String> favoriteStoreIdList) {
        this.mFavoriteStoreIdList = favoriteStoreIdList;
    }

    public List<String> getRecentStoreIdList() {
        return this.mRecentStoreIdList;
    }

    public void setRecentStoreIdList(List<String> recentStoreIdList) {
        this.mRecentStoreIdList = recentStoreIdList;
    }

    public String getZipCode() {
        return this.mZipCode;
    }

    public void setZipCode(String mZipCode) {
        this.mZipCode = mZipCode;
    }

    public boolean isPasswordChangeRequired() {
        return this.mPasswordChangeRequired;
    }

    public void setPasswordChangeRequired(boolean passwordChangeRequired) {
        this.mPasswordChangeRequired = passwordChangeRequired;
    }

    public List<Integer> getPreferredOfferCategories() {
        if (this.mPreferredOfferCategories == null) {
            this.mPreferredOfferCategories = new ArrayList();
        }
        return this.mPreferredOfferCategories;
    }

    public void setPreferredOfferCategories(List<Integer> preferredOfferCategories) {
        this.mPreferredOfferCategories = preferredOfferCategories;
    }

    public List<CommunicationCategory> getPreferredCommunicationCategories() {
        return this.mPreferredCommunicationCategories;
    }

    public void setPreferredCommunicationCategories(List<CommunicationCategory> mPreferredCommunicationCategories) {
        this.mPreferredCommunicationCategories = mPreferredCommunicationCategories;
    }

    public NotificationPreferences getNotificationPreferences() {
        if (this.mNotificationPreferences == null) {
            this.mNotificationPreferences = new NotificationPreferences();
        }
        return this.mNotificationPreferences;
    }

    public void setNotificationPreferences(NotificationPreferences mNotificationPreferences) {
        this.mNotificationPreferences = mNotificationPreferences;
    }

    public boolean needsFavoriteItemsRefresh() {
        return this.mFavoriteItems == null;
    }

    public List<FavoriteItem> getFavoriteItems() {
        if (this.mFavoriteItems == null) {
            return new ArrayList();
        }
        return this.mFavoriteItems;
    }

    public void setFavoriteItems(List<FavoriteItem> favoriteItems) {
        this.mFavoriteItems = favoriteItems;
    }

    public void addFavoriteOrderProductId(FavoriteItem favoriteItem) {
        this.mFavoriteItems.add(favoriteItem);
    }

    public boolean isFavoriteOrderProduct(OrderProduct orderProduct, FavoriteProductType favoriteProductType) {
        if (this.mFavoriteItems != null) {
            for (FavoriteItem favoriteItem : this.mFavoriteItems) {
                if (favoriteItem.getProducts() != null && favoriteProductType == favoriteItem.getType()) {
                    for (CustomerOrderProduct customerOrderProduct : favoriteItem.getProducts()) {
                        if (orderProduct.equals(customerOrderProduct)) {
                            return true;
                        }
                    }
                    continue;
                }
            }
        }
        return false;
    }

    public FavoriteItem getFavoriteOrderProduct(OrderProduct orderProduct) {
        if (this.mFavoriteItems != null) {
            for (FavoriteItem favoriteItem : this.mFavoriteItems) {
                if (favoriteItem.getProducts() != null && favoriteItem.getType() == FavoriteProductType.FAVORITE_PRODUCT_TYPE_ITEM) {
                    for (CustomerOrderProduct product : favoriteItem.getProducts()) {
                        if (String.valueOf(product.getProductCode()).equals(orderProduct.getProductCode())) {
                            return favoriteItem;
                        }
                    }
                    continue;
                }
            }
        }
        return null;
    }

    public List<Store> getFavoriteStores() {
        return this.mFavoriteStores;
    }

    public void setFavoriteStores(List<Store> favoriteStores) {
        this.mFavoriteStores = favoriteStores;
    }

    public boolean isSocialAccountLoginRegistered() {
        return this.mSocialAccountLoginRegistered.booleanValue();
    }

    public void setSocialAccountLoginRegistered(boolean socialAccountLoginRegistered) {
        this.mSocialAccountLoginRegistered = Boolean.valueOf(socialAccountLoginRegistered);
    }

    public boolean isEmailActivated() {
        return this.mEmailActivated;
    }

    public void setEmailActivated(boolean emailActivated) {
        this.mEmailActivated = emailActivated;
    }

    public boolean isAccountVerified() {
        switch (this.mVerificationType) {
            case SMS:
                return this.mCustomerLoginInfo.isDefaultPhoneNumberVerified();
            case EMAIL:
                return this.mCustomerLoginInfo.isEmailAddressVerified();
            default:
                return false;
        }
    }

    public boolean isSmsVerified() {
        return this.mSmsVerified;
    }

    public void setSmsVerified(boolean mSmsVerified) {
        this.mSmsVerified = mSmsVerified;
    }

    public Boolean getOptInForOtherMarketingMessages() {
        return this.mOptInForOtherMarketingMessages;
    }

    public void setOptInForOtherMarketingMessages(Boolean optInForOtherMarketingMessages) {
        this.mOptInForOtherMarketingMessages = optInForOtherMarketingMessages;
    }

    public Boolean getOptInForContests() {
        return this.mOptInForContests;
    }

    public void setOptInForContests(Boolean optInForContests) {
        this.mOptInForContests = optInForContests;
    }

    public Boolean getOptInForProgramChanges() {
        return this.mOptInForProgramChanges;
    }

    public void setOptInForProgramChanges(Boolean optInForProgramChanges) {
        this.mOptInForProgramChanges = optInForProgramChanges;
    }

    public Boolean getOptInForSurveys() {
        return this.mOptInForSurveys;
    }

    public void setOptInForSurveys(Boolean optInForSurveys) {
        this.mOptInForSurveys = optInForSurveys;
    }

    public Boolean getOptInForCommunicationChannel() {
        return this.mOptInForCommunicationChannel;
    }

    public void setOptInForCommunicationChannel(Boolean optInForCommunicationChannel) {
        this.mOptInForCommunicationChannel = optInForCommunicationChannel;
    }

    public int getSocialServiceAuthenticationID() {
        return this.mSocialServiceAuthenticationID;
    }

    public void setSocialServiceAuthenticationID(int socialServiceAuthenticationID) {
        this.mSocialServiceAuthenticationID = socialServiceAuthenticationID;
    }

    public boolean isUsingSocialLogin() {
        return this.mUsingSocialLogin;
    }

    public void setUsingSocialLogin(boolean usingSocialLogin) {
        this.mUsingSocialLogin = usingSocialLogin;
    }

    public boolean isUsingSocialLoginWithoutEmail() {
        return this.mUsingSocialLoginWithoutEmail;
    }

    public void setUsingSocialLoginWithoutEmail(boolean usingSocialLoginWithoutEmail) {
        this.mUsingSocialLoginWithoutEmail = usingSocialLoginWithoutEmail;
    }

    public boolean ismMSAlarmEnabled() {
        return this.mMSAlarmEnabled;
    }

    public void setMSAlarmEnabled(boolean MSAAlarmEnable) {
        this.mMSAlarmEnabled = MSAAlarmEnable;
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

    public Boolean getSubscribedToOffers() {
        return Boolean.valueOf(this.mSubscribedToOffers);
    }

    public Boolean getReceivePromotions() {
        return this.mReceivePromotions;
    }

    public CustomerLoginInfo getCustomerLoginInfo() {
        return this.mCustomerLoginInfo;
    }

    public void setCustomerLoginInfo(CustomerLoginInfo info) {
        this.mCustomerLoginInfo = info;
    }

    public int getLoginPreference() {
        return this.mLoginPreference;
    }

    public void setLoginPreference(int preference) {
        this.mLoginPreference = preference;
    }

    public String toString() {
        return "CustomerProfile{mCustomerId=" + this.mCustomerId + ", mUserName=\"" + this.mUserName + "\", mNewUserName=\"" + this.mNewUserName + "\", mPassword=\"" + this.mPassword + "\", mFirstName=\"" + this.mFirstName + "\", mMiddleName=\"" + this.mMiddleName + "\", mLastName=\"" + this.mLastName + "\", mNickName=\"" + this.mNickName + "\", mZipCode=\"" + this.mZipCode + "\", mMobileNumber=\"" + this.mMobileNumber + "\", mEmailAddress=\"" + this.mEmailAddress + "\", mEmailActivated=" + this.mEmailActivated + ", mBirthDate=" + this.mBirthDate + ", mYearOfBirth=" + this.mYearOfBirth + ", mMonthOfBirth=" + this.mMonthOfBirth + ", mDayOfBirth=" + this.mDayOfBirth + ", mPreferredLocale=" + this.mPreferredLocale + ", mPreferredNotification=" + this.mPreferredNotification + ", mNotificationPreferences=" + this.mNotificationPreferences + ", mSubscribedToOffers=" + this.mSubscribedToOffers + ", mReceivePromotions=" + this.mReceivePromotions + ", mCardItems=" + this.mCardItems + ", mAccountItems=" + this.mAccountItems + ", mFavoriteStoreIdList=" + this.mFavoriteStoreIdList + ", mRecentStoreIdList=" + this.mRecentStoreIdList + ", mPreferredOfferCategories=" + this.mPreferredOfferCategories + ", mPreferredCommunicationCategories=" + this.mPreferredCommunicationCategories + ", mFavoriteItems=" + this.mFavoriteItems + ", mOptInForCommunicationChannel=" + this.mOptInForCommunicationChannel + ", mOptInForSurveys=" + this.mOptInForSurveys + ", mOptInForProgramChanges=" + this.mOptInForProgramChanges + ", mOptInForContests=" + this.mOptInForContests + ", mOptInForOtherMarketingMessages=" + this.mOptInForOtherMarketingMessages + ", mPasswordChangeRequired=" + this.mPasswordChangeRequired + ", mAgeOfCustomerId=" + this.mAgeOfCustomerId + ", mAgeOfOldestOrder=" + this.mAgeOfOldestOrder + ", mNumberOfOrders=" + this.mNumberOfOrders + "}";
    }

    public int getActivationOption() {
        return this.mActivationOption;
    }

    public void setActivationOption(int option) {
        this.mActivationOption = option;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        byte b;
        byte b2 = (byte) 1;
        dest.writeLong(this.mCustomerId);
        dest.writeString(this.mUserName);
        dest.writeString(this.mNewUserName);
        dest.writeString(this.mPassword);
        dest.writeString(this.mFirstName);
        dest.writeString(this.mMiddleName);
        dest.writeString(this.mLastName);
        dest.writeString(this.mNickName);
        dest.writeString(this.mZipCode);
        dest.writeInt(this.mSocialServiceAuthenticationID);
        dest.writeByte(this.mUsingSocialLogin ? (byte) 1 : (byte) 0);
        if (this.mUsingSocialLoginWithoutEmail) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        dest.writeByte(b);
        if (this.mMSAlarmEnabled) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        dest.writeByte(b);
        dest.writeString(this.mSocialAuthenticationToken);
        dest.writeString(this.mSocialUserID);
        if (this.mSocialAccountLoginRegistered == null) {
            this.mSocialAccountLoginRegistered = Boolean.valueOf(false);
        }
        if (this.mSocialAccountLoginRegistered.booleanValue()) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        dest.writeByte(b);
        dest.writeString(this.mMobileNumber);
        dest.writeString(this.mEmailAddress);
        if (this.mEmailActivated) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        dest.writeByte(b);
        dest.writeSerializable(this.mBirthDate);
        dest.writeValue(this.mYearOfBirth);
        dest.writeValue(this.mMonthOfBirth);
        dest.writeValue(this.mDayOfBirth);
        dest.writeSerializable(this.mPreferredLocale);
        dest.writeValue(this.mPreferredNotification);
        dest.writeParcelable(this.mNotificationPreferences, 0);
        dest.writeValue(Boolean.valueOf(this.mSubscribedToOffers));
        dest.writeValue(this.mReceivePromotions);
        dest.writeList(this.mCardItems);
        dest.writeTypedList(this.mAccountItems);
        dest.writeStringList(this.mFavoriteStoreIdList);
        dest.writeStringList(this.mRecentStoreIdList);
        dest.writeList(this.mPreferredOfferCategories);
        dest.writeTypedList(this.mPreferredCommunicationCategories);
        dest.writeTypedList(this.mFavoriteItems);
        dest.writeValue(this.mOptInForCommunicationChannel);
        dest.writeValue(this.mOptInForSurveys);
        dest.writeValue(this.mOptInForProgramChanges);
        dest.writeValue(this.mOptInForContests);
        dest.writeValue(this.mOptInForOtherMarketingMessages);
        dest.writeParcelable(this.mCustomerLoginInfo, 0);
        dest.writeByte(this.mPasswordChangeRequired ? (byte) 1 : (byte) 0);
        dest.writeInt(this.mLoginPreference);
        dest.writeInt(this.mAgeOfCustomerId);
        dest.writeInt(this.mAgeOfOldestOrder);
        dest.writeInt(this.mNumberOfOrders);
        dest.writeInt(this.mActivationOption);
        dest.writeString(this.mGender);
        dest.writeValue(this.mVerificationType);
        dest.writeValue(this.mAccountDeleteType);
        if (!this.mIsActive) {
            b2 = (byte) 0;
        }
        dest.writeByte(b2);
    }

    protected CustomerProfile(Parcel in) {
        boolean z;
        boolean z2 = true;
        this.mCustomerId = in.readLong();
        this.mUserName = in.readString();
        this.mNewUserName = in.readString();
        this.mPassword = in.readString();
        this.mFirstName = in.readString();
        this.mMiddleName = in.readString();
        this.mLastName = in.readString();
        this.mNickName = in.readString();
        this.mZipCode = in.readString();
        this.mSocialServiceAuthenticationID = in.readInt();
        this.mUsingSocialLogin = in.readByte() != (byte) 0;
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.mUsingSocialLoginWithoutEmail = z;
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.mMSAlarmEnabled = z;
        this.mSocialAuthenticationToken = in.readString();
        this.mSocialUserID = in.readString();
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.mSocialAccountLoginRegistered = Boolean.valueOf(z);
        this.mMobileNumber = in.readString();
        this.mEmailAddress = in.readString();
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.mEmailActivated = z;
        this.mBirthDate = (Calendar) in.readSerializable();
        this.mYearOfBirth = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mMonthOfBirth = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mDayOfBirth = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mPreferredLocale = (Locale) in.readSerializable();
        this.mPreferredNotification = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mNotificationPreferences = (NotificationPreferences) in.readParcelable(NotificationPreferences.class.getClassLoader());
        this.mSubscribedToOffers = ((Boolean) in.readValue(Boolean.class.getClassLoader())).booleanValue();
        this.mReceivePromotions = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mCardItems = new ArrayList();
        in.readList(this.mCardItems, PaymentCard.class.getClassLoader());
        this.mAccountItems = in.createTypedArrayList(CustomerPaymentAccount.CREATOR);
        this.mFavoriteStoreIdList = in.createStringArrayList();
        this.mRecentStoreIdList = in.createStringArrayList();
        this.mPreferredOfferCategories = new ArrayList();
        in.readList(this.mPreferredOfferCategories, Integer.class.getClassLoader());
        this.mPreferredCommunicationCategories = in.createTypedArrayList(CommunicationCategory.CREATOR);
        this.mFavoriteItems = in.createTypedArrayList(FavoriteItem.CREATOR);
        this.mOptInForCommunicationChannel = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mOptInForSurveys = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mOptInForProgramChanges = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mOptInForContests = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mOptInForOtherMarketingMessages = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mCustomerLoginInfo = (CustomerLoginInfo) in.readParcelable(CustomerLoginInfo.class.getClassLoader());
        this.mPasswordChangeRequired = in.readByte() != (byte) 0;
        this.mLoginPreference = in.readInt();
        this.mAgeOfCustomerId = in.readInt();
        this.mAgeOfOldestOrder = in.readInt();
        this.mNumberOfOrders = in.readInt();
        this.mActivationOption = in.readInt();
        this.mGender = in.readString();
        this.mVerificationType = (AccountVerificationType) in.readValue(AccountVerificationType.class.getClassLoader());
        this.mAccountDeleteType = (AccountDeleteType) in.readValue(AccountDeleteType.class.getClassLoader());
        if (in.readByte() == (byte) 0) {
            z2 = false;
        }
        this.mIsActive = z2;
    }

    public String getmTermsAndConditionVersion() {
        return this.mTermsAndConditionVersion;
    }

    public void setmTermsAndConditionVersion(String mTermsAndConditionVersion) {
        this.mTermsAndConditionVersion = mTermsAndConditionVersion;
    }

    public String getmPrivacyPolicyVersion() {
        return this.mPrivacyPolicyVersion;
    }

    public void setmPrivacyPolicyVersion(String mPrivacyPolicyVersion) {
        this.mPrivacyPolicyVersion = mPrivacyPolicyVersion;
    }

    public boolean hasAcceptedTermsAndCondition() {
        return this.mHasAcceptedTermsAndCondition;
    }

    public void setHasAcceptedTermsAndCondition(boolean hasAcceptedTermsAndCondition) {
        this.mHasAcceptedTermsAndCondition = hasAcceptedTermsAndCondition;
    }

    public boolean hasAcceptedPrivacyPolicy() {
        return this.mHasAcceptedPrivacyPolicy;
    }

    public void setHasAcceptedPrivacyPolicy(boolean hasAcceptedPrivacyPolicy) {
        this.mHasAcceptedPrivacyPolicy = hasAcceptedPrivacyPolicy;
    }

    public boolean shouldUpdateTermsAndCondition() {
        return this.mShouldUpdateTermsAndCondition;
    }

    public void setShouldUpdateTermsAndCondition(boolean mShouldUpdateTermsAndCondition) {
        this.mShouldUpdateTermsAndCondition = mShouldUpdateTermsAndCondition;
    }

    public boolean shouldUpdatePrivacyPolicy() {
        return this.mShouldUpdatePrivacyPolicy;
    }

    public void setShouldUpdatePrivacyPolicy(boolean mShouldUpdatePrivacyPolicy) {
        this.mShouldUpdatePrivacyPolicy = mShouldUpdatePrivacyPolicy;
    }

    public AccountVerificationType getVerificationType() {
        return this.mVerificationType;
    }

    public void setVerificationType(AccountVerificationType mVerificationType) {
        this.mVerificationType = mVerificationType;
    }

    public String getNewPassword() {
        return this.mNewPassword;
    }

    public void setNewPassword(String mNewPassword) {
        this.mNewPassword = mNewPassword;
    }

    public String getDeactivateAccountTimeStamp() {
        return this.mDeactivateAccountTimeStamp;
    }

    public void setDeactivateAccountTimeStamp(String mDeactivateAccountTimeStamp) {
        this.mDeactivateAccountTimeStamp = mDeactivateAccountTimeStamp;
    }

    public AccountDeleteType getAccountDeleteType() {
        return this.mAccountDeleteType;
    }

    public void setAccountDeleteType(AccountDeleteType mAccountDeleteType) {
        this.mAccountDeleteType = mAccountDeleteType;
    }

    public boolean shouldChangeBirthdate() {
        return this.mShouldChangeBirthdate;
    }

    public void setShouldChangeBirthdate(boolean mShouldChangeBirthdate) {
        this.mShouldChangeBirthdate = mShouldChangeBirthdate;
    }

    public boolean isActive() {
        return this.mIsActive;
    }

    public void setIsActive(boolean mIsActive) {
        this.mIsActive = mIsActive;
    }

    public String getTermsAndConditionAcceptanceDate() {
        return this.mTermsAndConditionAcceptanceDate;
    }

    public void setTermsAndConditionAcceptanceDate(String termsAndConditionAcceptanceDate) {
        this.mTermsAndConditionAcceptanceDate = termsAndConditionAcceptanceDate;
    }

    public String getPrivacyPolicyAcceptanceDate() {
        return this.mPrivacyPolicyAcceptanceDate;
    }

    public void setPrivacyPolicyAcceptanceDate(String privacyPolicyAcceptanceDate) {
        this.mPrivacyPolicyAcceptanceDate = privacyPolicyAcceptanceDate;
    }

    public int getAgeOfCustomerId() {
        return this.mAgeOfCustomerId;
    }

    public void setAgeOfCustomerId(int ageOfCustomerId) {
        this.mAgeOfCustomerId = ageOfCustomerId;
    }

    public int getAgeOfOldestOrder() {
        return this.mAgeOfOldestOrder;
    }

    public void setAgeOfOldestOrder(int ageOfOldestOrder) {
        this.mAgeOfOldestOrder = ageOfOldestOrder;
    }

    public int getNumberOfOrders() {
        return this.mNumberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.mNumberOfOrders = numberOfOrders;
    }

    @NonNull
    public List<OptIn> getOptIns() {
        return this.mOptIns;
    }

    public void setOptIns(List<OptIn> optIns) {
        if (optIns == null) {
            optIns = new ArrayList(1);
        }
        this.mOptIns = optIns;
    }
}
