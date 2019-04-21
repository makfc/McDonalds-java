package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.customer.OptIn;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MWCustomerData {
    private static final String SKIP_BIRTHDAY_DAY = "interface.register.skipBirthDayDayInfo";
    @SerializedName("AddressBook")
    public List addressBook;
    @SerializedName("AgeOfCustomerID")
    public int ageOfCustomerId;
    @SerializedName("AgeOfOldestOrder")
    public int ageOfOldestOrder;
    @SerializedName("CustomerID")
    public long customerID;
    @SerializedName("CustomerLoginInformation")
    public MWCustomerLoginInfo customerLoginInfo;
    @SerializedName("DayOfBirth")
    public Integer dayOfBirth;
    @SerializedName("DefaultPhoneNumber")
    public String defaultPhoneNumber;
    @SerializedName("EmailAddress")
    public String emailAddress;
    @SerializedName("Ethnicity")
    public Object ethnicity;
    @SerializedName("Favorites")
    public Object favorites;
    @SerializedName("FirstName")
    public String firstName;
    @SerializedName("Gender")
    public Object gender;
    @SerializedName("isActive")
    public Boolean isActive;
    @SerializedName("LastName")
    public String lastName;
    @SerializedName("loginInfo")
    public MWSocialLoginInfo loginInfo;
    @SerializedName("LoginPreference")
    public int loginPreference;
    @SerializedName("MiddleName")
    public String middleName;
    @SerializedName("MonthOfBirth")
    public Integer monthOfBirth;
    @SerializedName("MyLocations")
    public Object myLocations;
    @SerializedName("NickName")
    public String nickName;
    @SerializedName("NotificationPreferences")
    public MWNotificationPreferences notificationPreferences;
    @SerializedName("NumberOfOrders")
    public int numberOfOrders;
    @SerializedName("OptInForCommunicationChannel")
    public Boolean optInForCommunicationChannel;
    @SerializedName("OptInForContests")
    public Boolean optInForContests;
    @SerializedName("OptInForOtherMarketingMessages")
    public Boolean optInForOtherMarketingMessages;
    @SerializedName("OptInForProgramChanges")
    public Boolean optInForProgramChanges;
    @SerializedName("OptInForSurveys")
    public Boolean optInForSurveys;
    @SerializedName("Opt-Ins")
    public List<MWOptIn> optIns = new ArrayList(1);
    public String password;
    @SerializedName("PaymentAccount")
    public List<MWPaymentAccount> paymentAccount;
    @SerializedName("PaymentCard")
    public List<MWPaymentCardData> paymentCard;
    @SerializedName("PreferredLanguage")
    public String preferredLanguage;
    @SerializedName("PreferredNotification")
    public int preferredNotification;
    @SerializedName("PreferredOfferCategories")
    public List<Integer> preferredOfferCategories;
    @SerializedName("PreferredPaymentMethodId")
    public Object preferredPaymentMethodId;
    @SerializedName("ReceivePromotions")
    public boolean receivePromotions;
    @SerializedName("RecentOrders")
    public List<Object> recentOrders;
    @SerializedName("SocialNetworkAccessToken")
    public String socialNetworkAccessToken;
    @SerializedName("SubscribedToOffer")
    public boolean subscribedToOffers;
    @SerializedName("TermOfUseAcceptanceDate")
    public String termOfUseAcceptanceDate;
    @SerializedName("Title")
    public String title;
    @SerializedName("UserCategory")
    public Object userCategory;
    @SerializedName("UserName")
    public String userName;
    @SerializedName("YearOfBirth")
    public Integer yearOfBirth;
    @SerializedName("ZipCode")
    public String zipCode;

    public static MWCustomerData fromCustomer(CustomerProfile customer) {
        MWCustomerData data = new MWCustomerData();
        if (customer != null) {
            boolean z;
            data.customerID = customer.getCustomerId();
            data.userName = customer.getUserName();
            data.password = customer.getPassword();
            data.firstName = customer.getFirstName();
            data.middleName = customer.getMiddleName();
            data.lastName = customer.getLastName();
            data.emailAddress = customer.getEmailAddress();
            data.nickName = customer.getNickName();
            data.defaultPhoneNumber = customer.getMobileNumber() == null ? "" : customer.getMobileNumber();
            data.zipCode = customer.getZipCode();
            data.gender = customer.getGender();
            data.ageOfCustomerId = customer.getAgeOfCustomerId();
            data.ageOfOldestOrder = customer.getAgeOfOldestOrder();
            data.numberOfOrders = customer.getNumberOfOrders();
            if (customer.getBirthDate() != null) {
                data.dayOfBirth = Integer.valueOf(customer.getBirthDate().get(5));
                data.monthOfBirth = Integer.valueOf(customer.getBirthDate().get(2));
                data.yearOfBirth = Integer.valueOf(customer.getBirthDate().get(1));
            }
            data.preferredNotification = 1;
            data.paymentCard = new ArrayList();
            data.paymentAccount = new ArrayList();
            data.preferredOfferCategories = customer.getPreferredOfferCategories();
            data.notificationPreferences = MWNotificationPreferences.fromNotificationPreferences(customer.getNotificationPreferences());
            data.subscribedToOffers = customer.isSubscribedToOffers();
            if (customer.isReceivePromotions() == null) {
                z = false;
            } else {
                z = customer.isReceivePromotions().booleanValue();
            }
            data.receivePromotions = z;
            data.optInForCommunicationChannel = customer.getOptInForCommunicationChannel();
            data.optInForContests = customer.getOptInForContests();
            data.optInForOtherMarketingMessages = customer.getOptInForOtherMarketingMessages();
            data.optInForProgramChanges = customer.getOptInForProgramChanges();
            data.optInForSurveys = customer.getOptInForSurveys();
            if (customer.getCustomerLoginInfo() != null) {
                data.customerLoginInfo = MWCustomerLoginInfo.fromCustomerLoginInfo(customer.getCustomerLoginInfo());
            }
            data.loginPreference = customer.getLoginPreference();
            MWSocialLoginInfo loginInfo = new MWSocialLoginInfo();
            if (customer.isUsingSocialLogin()) {
                if (customer.isUsingSocialLoginWithoutEmail()) {
                    loginInfo.loginType = 1;
                    loginInfo.socialNetworkID = customer.getSocialServiceAuthenticationID();
                    loginInfo.accessToken = customer.getSocialAuthenticationToken();
                    loginInfo.internalID = customer.getSocialUserID();
                    loginInfo.userName = customer.getUserName();
                    loginInfo.emailAddress = "";
                    loginInfo.mobileNumber = customer.getMobileNumber();
                } else {
                    loginInfo.loginType = 1;
                    loginInfo.socialNetworkID = customer.getSocialServiceAuthenticationID();
                    loginInfo.accessToken = customer.getSocialAuthenticationToken();
                    loginInfo.internalID = customer.getSocialUserID();
                    loginInfo.userName = customer.getEmailAddress();
                    loginInfo.emailAddress = customer.getEmailAddress();
                }
                data.isActive = Boolean.valueOf(true);
            } else {
                loginInfo.loginType = 0;
                loginInfo.userName = customer.getEmailAddress();
                loginInfo.emailAddress = customer.getEmailAddress();
            }
            data.loginInfo = loginInfo;
            List<MWOptIn> optInList = new ArrayList(1);
            if (!ListUtils.isEmpty(customer.getOptIns())) {
                for (OptIn in : customer.getOptIns()) {
                    MWOptIn optIn = new MWOptIn();
                    optIn.setType(in.getType());
                    optIn.setStatus(in.getStatus());
                    optInList.add(optIn);
                }
            }
            data.optIns = optInList;
            data.termOfUseAcceptanceDate = customer.getTermsAndConditionAcceptanceDate();
        }
        return data;
    }

    public static CustomerProfile toCustomer(MWCustomerData data) {
        CustomerProfile customer = new CustomerProfile();
        if (data != null) {
            Calendar birthDate;
            customer.setCustomerId(data.customerID);
            customer.setUserName(data.userName);
            customer.setFirstName(data.firstName);
            customer.setMiddleName(data.middleName);
            customer.setLastName(data.lastName);
            customer.setEmailAddress(data.emailAddress);
            customer.setNickName(data.nickName);
            customer.setMobileNumber(data.defaultPhoneNumber);
            customer.setZipCode(data.zipCode);
            customer.setPreferredOfferCategories(data.preferredOfferCategories);
            customer.setNotificationPreferences(MWNotificationPreferences.toNotificationPreferences(data.notificationPreferences));
            customer.setOptInForCommunicationChannel(data.optInForCommunicationChannel);
            customer.setOptInForContests(data.optInForContests);
            customer.setOptInForOtherMarketingMessages(data.optInForOtherMarketingMessages);
            customer.setOptInForProgramChanges(data.optInForProgramChanges);
            customer.setOptInForSurveys(data.optInForSurveys);
            customer.setLoginPreference(data.loginPreference);
            customer.setPreferredNotification(Integer.valueOf(data.preferredNotification));
            customer.setSocialAuthenticationToken(data.socialNetworkAccessToken);
            if (Configuration.getSharedInstance().getBooleanForKey(SKIP_BIRTHDAY_DAY)) {
                if (!(data.monthOfBirth == null || data.yearOfBirth == null)) {
                    birthDate = Calendar.getInstance();
                    birthDate.clear();
                    birthDate.set(5, 1);
                    birthDate.set(2, data.monthOfBirth.intValue());
                    birthDate.set(1, data.yearOfBirth.intValue());
                    customer.setBirthDate(birthDate);
                }
            } else if (!(data.dayOfBirth == null || data.monthOfBirth == null || data.yearOfBirth == null)) {
                birthDate = Calendar.getInstance();
                birthDate.clear();
                birthDate.set(5, data.dayOfBirth.intValue());
                birthDate.set(2, data.monthOfBirth.intValue());
                birthDate.set(1, data.yearOfBirth.intValue());
                customer.setBirthDate(birthDate);
            }
            customer.setAgeOfCustomerId(data.ageOfCustomerId);
            customer.setAgeOfOldestOrder(data.ageOfOldestOrder);
            customer.setNumberOfOrders(data.numberOfOrders);
            if (!(data.dayOfBirth == null || data.monthOfBirth == null || data.yearOfBirth == null)) {
                birthDate = Calendar.getInstance();
                birthDate.clear();
                birthDate.set(5, data.dayOfBirth.intValue());
                birthDate.set(2, data.monthOfBirth.intValue());
                birthDate.set(1, data.yearOfBirth.intValue());
                customer.setBirthDate(birthDate);
            }
            customer.setSubscribedToOffers(data.subscribedToOffers);
            customer.setReceivePromotions(Boolean.valueOf(data.receivePromotions));
            customer.setCardItems(new ArrayList());
            if (data.customerLoginInfo != null) {
                customer.setCustomerLoginInfo(data.customerLoginInfo.toCustomerLoginInfo());
            }
            if (data.paymentCard != null) {
                for (MWPaymentCardData cardData : data.paymentCard) {
                    PaymentCard card = MWPaymentCardData.toPaymentCard(cardData);
                    if (card != null) {
                        customer.getCardItems().add(card);
                    }
                }
            }
            customer.setAccountItems(new ArrayList());
            if (data.paymentAccount != null) {
                for (MWPaymentAccount account : data.paymentAccount) {
                    customer.getAccountItems().add(MWPaymentAccount.toCustomerPaymentAccount(account));
                }
            }
            List<OptIn> optInList = new ArrayList(1);
            if (!ListUtils.isEmpty(data.optIns)) {
                for (MWOptIn mwOptIn : data.optIns) {
                    OptIn optIn = new OptIn();
                    optIn.setStatus(mwOptIn.getStatus());
                    optIn.setType(mwOptIn.getType());
                    optIn.setAcceptanceTimestamp(mwOptIn.getAcceptanceTimestamp());
                    optInList.add(optIn);
                }
            }
            customer.setOptIns(optInList);
            customer.setTermsAndConditionAcceptanceDate(data.termOfUseAcceptanceDate);
        }
        return customer;
    }
}
