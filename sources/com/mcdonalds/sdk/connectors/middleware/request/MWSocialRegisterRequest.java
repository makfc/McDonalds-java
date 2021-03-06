package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWCustomerData;
import com.mcdonalds.sdk.connectors.middleware.model.MWNotificationPreferences;
import com.mcdonalds.sdk.connectors.middleware.response.MWSignInAndAuthenticateResponse;
import com.mcdonalds.sdk.modules.models.NotificationPreferences;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MWSocialRegisterRequest extends MWRequest<MWSignInAndAuthenticateResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/socialLogin/register";
    private final MWRequestHeaders mHeaderMap;
    protected MWJSONRequestBody mPostBody;

    @Deprecated
    public MWSocialRegisterRequest(MiddlewareConnector ignored, String ecpToken, MWCustomerData customerData, boolean privacyPolicyAccepted) {
        this(ecpToken, customerData, privacyPolicyAccepted);
    }

    public MWSocialRegisterRequest(String ecpToken, MWCustomerData customerData, boolean privacyPolicyAccepted) {
        int i;
        boolean z = true;
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mPostBody = new MWJSONRequestBody();
        this.mPostBody.generateHash();
        this.mPostBody.put("firstName", customerData.firstName);
        this.mPostBody.put("lastName", customerData.lastName);
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
        this.mPostBody.put("loginPreference", Integer.valueOf(customerData.loginPreference));
        this.mPostBody.put("staticDataTypeID", new Integer[]{Integer.valueOf(2), Integer.valueOf(4)});
        boolean wantsOffers = customerData.subscribedToOffers;
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
        MWNotificationPreferences notificationPreferences = customerData.notificationPreferences;
        if (notificationPreferences != null) {
            notifPrefs.put(NotificationPreferences.KEY_EMAIL_ENABLED, notificationPreferences.emailNotificationPreferences_Enabled);
            notifPrefs.put(NotificationPreferences.KEY_EMAIL_YOUR_OFFERS, notificationPreferences.emailNotificationPreferences_YourOffers);
            notifPrefs.put(NotificationPreferences.KEY_EMAIL_LIMITED_TIME_OFFERS, notificationPreferences.emailNotificationPreferences_LimitedTimeOffers);
            notifPrefs.put(NotificationPreferences.KEY_EMAIL_PUNCHCARD_OFFERS, notificationPreferences.emailNotificationPreferences_PunchcardOffers);
            notifPrefs.put(NotificationPreferences.KEY_EMAIL_EVERYDAY_OFFERS, notificationPreferences.emailNotificationPreferences_EverydayOffers);
            notifPrefs.put(NotificationPreferences.KEY_EMAIL_OFFER_EXPIRATION_OPTION, Integer.valueOf(notificationPreferences.emailNotificationPreferences_OfferExpirationOption));
        } else {
            notifPrefs.put(NotificationPreferences.KEY_EMAIL_ENABLED, Boolean.valueOf(false));
            notifPrefs.put(NotificationPreferences.KEY_EMAIL_YOUR_OFFERS, Boolean.valueOf(false));
            notifPrefs.put(NotificationPreferences.KEY_EMAIL_LIMITED_TIME_OFFERS, Boolean.valueOf(false));
            notifPrefs.put(NotificationPreferences.KEY_EMAIL_PUNCHCARD_OFFERS, Boolean.valueOf(false));
            notifPrefs.put(NotificationPreferences.KEY_EMAIL_EVERYDAY_OFFERS, Boolean.valueOf(false));
            notifPrefs.put(NotificationPreferences.KEY_EMAIL_OFFER_EXPIRATION_OPTION, Integer.valueOf(0));
        }
        this.mPostBody.put("optInForCommunicationChannel", Boolean.valueOf(false));
        this.mPostBody.put("optInForSurveys", Boolean.valueOf(false));
        this.mPostBody.put("optInForProgramChanges", Boolean.valueOf(false));
        this.mPostBody.put("optInForContests", Boolean.valueOf(false));
        this.mPostBody.put("optInForOtherMarketingMessages", Boolean.valueOf(false));
        this.mPostBody.put("notificationPreferences", notifPrefs);
        this.mPostBody.put("preferredOfferCategories", customerData.preferredOfferCategories);
        this.mPostBody.put("subscribedToOffer", Boolean.valueOf(customerData.subscribedToOffers));
        MWJSONRequestBody mWJSONRequestBody = this.mPostBody;
        str = "isActive";
        if (Configuration.getSharedInstance().getBooleanForKey("requireActivationSocial")) {
            z = false;
        }
        mWJSONRequestBody.put(str, Boolean.valueOf(z));
        this.mPostBody.put("loginInfo", customerData.loginInfo);
        this.mPostBody.put("Opt-Ins", customerData.optIns);
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
        this.mPostBody = body;
    }

    public Class<MWSignInAndAuthenticateResponse> getResponseClass() {
        return MWSignInAndAuthenticateResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWSocialRegisterRequest{mHeaderMap=" + this.mHeaderMap + ", mPostBody=" + this.mPostBody + "}";
    }
}
