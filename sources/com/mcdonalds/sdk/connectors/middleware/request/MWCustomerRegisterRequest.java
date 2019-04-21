package com.mcdonalds.sdk.connectors.middleware.request;

import android.text.TextUtils;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.DCSProfile;
import com.mcdonalds.sdk.connectors.middleware.model.MWCustomerData;
import com.mcdonalds.sdk.connectors.middleware.response.MWCustomerRegisterResponse;
import com.mcdonalds.sdk.modules.models.NotificationPreferences;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MWCustomerRegisterRequest extends MWRequest<MWCustomerRegisterResponse, MWJSONRequestBody> {
    private static final String SKIP_BIRTHDAY_DAY = "interface.register.skipBirthDayDayInfo";
    private static final String URL_PATH = "/customer/registration";
    private final MWRequestHeaders mHeaderMap;
    private final MWJSONRequestBody mPostBody;

    @Deprecated
    public MWCustomerRegisterRequest(MiddlewareConnector ignored, String ecpToken, MWCustomerData customerData, boolean privacyPolicyAccepted) {
        this(ecpToken, customerData, privacyPolicyAccepted);
    }

    public MWCustomerRegisterRequest(String ecpToken, MWCustomerData customerData, boolean privacyPolicyAccepted) {
        int i;
        boolean z;
        boolean z2 = true;
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mPostBody = new MWJSONRequestBody();
        this.mPostBody.put("userName", customerData.userName);
        this.mPostBody.put("password", customerData.password);
        this.mPostBody.put("firstName", customerData.firstName);
        this.mPostBody.put("lastName", TextUtils.isEmpty(customerData.lastName) ? "-" : customerData.lastName);
        this.mPostBody.put("nickName", customerData.nickName);
        this.mPostBody.put("mobileNumber", customerData.defaultPhoneNumber);
        this.mPostBody.put("emailAddress", customerData.emailAddress);
        this.mPostBody.put("isPrivacyPolicyAccepted", Boolean.valueOf(privacyPolicyAccepted));
        this.mPostBody.put("isTermsOfUseAccepted", Boolean.valueOf(privacyPolicyAccepted));
        this.mPostBody.put("preferredNotification", Integer.valueOf(customerData.preferredNotification));
        this.mPostBody.put("receivePromotions", Boolean.valueOf(customerData.receivePromotions));
        this.mPostBody.put("cardItems", customerData.paymentCard);
        this.mPostBody.put("accountItems", customerData.paymentAccount);
        this.mPostBody.put("zipCode", customerData.zipCode);
        boolean wantsOffers = customerData.subscribedToOffers;
        this.mPostBody.put("optInForCommunicationChannel", Boolean.valueOf(wantsOffers));
        this.mPostBody.put("optInForSurveys", Boolean.valueOf(wantsOffers));
        this.mPostBody.put("optInForProgramChanges", Boolean.valueOf(wantsOffers));
        this.mPostBody.put("optInForContests", Boolean.valueOf(wantsOffers));
        this.mPostBody.put("optInForOtherMarketingMessages", Boolean.valueOf(wantsOffers));
        Map<String, Object> notifPrefs = new HashMap(12);
        notifPrefs.put(NotificationPreferences.KEY_APP_ENABLED, Boolean.valueOf(wantsOffers));
        notifPrefs.put(NotificationPreferences.KEY_APP_YOUR_OFFERS, Boolean.valueOf(wantsOffers));
        notifPrefs.put(NotificationPreferences.KEY_APP_LIMITED_TIME_OFFERS, Boolean.valueOf(wantsOffers));
        notifPrefs.put(NotificationPreferences.KEY_APP_PUNCHCARD_OFFERS, Boolean.valueOf(wantsOffers));
        notifPrefs.put(NotificationPreferences.KEY_APP_EVERYDAY_OFFERS, Boolean.valueOf(wantsOffers));
        String str = NotificationPreferences.KEY_APP_OFFER_EXPIRATION_OPTION;
        if (wantsOffers) {
            i = 1;
        } else {
            i = 0;
        }
        notifPrefs.put(str, Integer.valueOf(i));
        if (customerData.notificationPreferences != null) {
            if (customerData.notificationPreferences.emailNotificationPreferences_Enabled == null) {
                notifPrefs.put(NotificationPreferences.KEY_EMAIL_ENABLED, Boolean.valueOf(false));
            } else {
                notifPrefs.put(NotificationPreferences.KEY_EMAIL_ENABLED, customerData.notificationPreferences.emailNotificationPreferences_Enabled);
            }
            if (customerData.notificationPreferences.emailNotificationPreferences_YourOffers == null) {
                notifPrefs.put(NotificationPreferences.KEY_EMAIL_YOUR_OFFERS, Boolean.valueOf(false));
            } else {
                notifPrefs.put(NotificationPreferences.KEY_EMAIL_YOUR_OFFERS, customerData.notificationPreferences.emailNotificationPreferences_YourOffers);
            }
            if (customerData.notificationPreferences.emailNotificationPreferences_LimitedTimeOffers == null) {
                notifPrefs.put(NotificationPreferences.KEY_EMAIL_LIMITED_TIME_OFFERS, Boolean.valueOf(false));
            } else {
                notifPrefs.put(NotificationPreferences.KEY_EMAIL_LIMITED_TIME_OFFERS, customerData.notificationPreferences.emailNotificationPreferences_LimitedTimeOffers);
            }
            if (customerData.notificationPreferences.emailNotificationPreferences_PunchcardOffers == null) {
                notifPrefs.put(NotificationPreferences.KEY_EMAIL_PUNCHCARD_OFFERS, Boolean.valueOf(false));
            } else {
                notifPrefs.put(NotificationPreferences.KEY_EMAIL_PUNCHCARD_OFFERS, customerData.notificationPreferences.emailNotificationPreferences_PunchcardOffers);
            }
            if (customerData.notificationPreferences.emailNotificationPreferences_EverydayOffers == null) {
                notifPrefs.put(NotificationPreferences.KEY_EMAIL_EVERYDAY_OFFERS, Boolean.valueOf(false));
            } else {
                notifPrefs.put(NotificationPreferences.KEY_EMAIL_EVERYDAY_OFFERS, customerData.notificationPreferences.emailNotificationPreferences_EverydayOffers);
            }
            notifPrefs.put(NotificationPreferences.KEY_EMAIL_OFFER_EXPIRATION_OPTION, Integer.valueOf(customerData.notificationPreferences.emailNotificationPreferences_OfferExpirationOption));
        } else {
            notifPrefs.put(NotificationPreferences.KEY_EMAIL_ENABLED, Boolean.valueOf(false));
            notifPrefs.put(NotificationPreferences.KEY_EMAIL_YOUR_OFFERS, Boolean.valueOf(false));
            notifPrefs.put(NotificationPreferences.KEY_EMAIL_LIMITED_TIME_OFFERS, Boolean.valueOf(false));
            notifPrefs.put(NotificationPreferences.KEY_EMAIL_PUNCHCARD_OFFERS, Boolean.valueOf(false));
            notifPrefs.put(NotificationPreferences.KEY_EMAIL_EVERYDAY_OFFERS, Boolean.valueOf(false));
            notifPrefs.put(NotificationPreferences.KEY_EMAIL_OFFER_EXPIRATION_OPTION, Integer.valueOf(0));
        }
        MWJSONRequestBody mWJSONRequestBody = this.mPostBody;
        String str2 = "optInForCommunicationChannel";
        if (customerData.optInForCommunicationChannel == null || !customerData.optInForCommunicationChannel.booleanValue()) {
            z = false;
        } else {
            z = true;
        }
        mWJSONRequestBody.put(str2, Boolean.valueOf(z));
        this.mPostBody.put("optInForSurveys", Boolean.valueOf(wantsOffers));
        this.mPostBody.put("optInForProgramChanges", Boolean.valueOf(wantsOffers));
        this.mPostBody.put("optInForContests", Boolean.valueOf(wantsOffers));
        this.mPostBody.put("optInForOtherMarketingMessages", Boolean.valueOf(wantsOffers));
        this.mPostBody.put("notificationPreferences", notifPrefs);
        this.mPostBody.put("preferredOfferCategories", customerData.preferredOfferCategories);
        this.mPostBody.put("subscribedToOffer", Boolean.valueOf(customerData.subscribedToOffers));
        if (customerData.loginPreference != 0) {
            this.mPostBody.put("loginPreference", Integer.valueOf(customerData.loginPreference));
        }
        if (!Configuration.getSharedInstance().getBooleanForKey(SKIP_BIRTHDAY_DAY)) {
            this.mPostBody.put("dayOfBirth", customerData.dayOfBirth);
        }
        this.mPostBody.put("monthOfBirth", customerData.monthOfBirth);
        this.mPostBody.put("yearOfBirth", customerData.yearOfBirth);
        this.mPostBody.put("Opt-Ins", customerData.optIns);
        MWJSONRequestBody mWJSONRequestBody2 = this.mPostBody;
        str = "isActive";
        if (Configuration.getSharedInstance().getBooleanForKey(DCSProfile.KEY_REQUIRES_ACTIVATION)) {
            z2 = false;
        }
        mWJSONRequestBody2.put(str, Boolean.valueOf(z2));
    }

    public MethodType getMethodType() {
        return MethodType.POST;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    /* Access modifiers changed, original: 0000 */
    public String getEndpoint() {
        return URL_PATH;
    }

    public Map<String, String> getHeaders() {
        return this.mHeaderMap;
    }

    public String getBody() {
        return this.mPostBody.toJson();
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWCustomerRegisterResponse> getResponseClass() {
        return MWCustomerRegisterResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWCustomerRregisterRequest{mHeaderMap=" + this.mHeaderMap + ", mPostBody=" + this.mPostBody + "}";
    }

    public MWJSONRequestBody getRawBody() {
        return this.mPostBody;
    }
}
