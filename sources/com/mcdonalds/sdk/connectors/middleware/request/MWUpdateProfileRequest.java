package com.mcdonalds.sdk.connectors.middleware.request;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.DCSPolicy;
import com.mcdonalds.sdk.connectors.middleware.model.DCSPreference;
import com.mcdonalds.sdk.connectors.middleware.model.MWCustomerCardData;
import com.mcdonalds.sdk.connectors.middleware.model.MWCustomerData;
import com.mcdonalds.sdk.connectors.middleware.model.MWNotificationPreferences;
import com.mcdonalds.sdk.connectors.middleware.model.MWPaymentCardData;
import com.mcdonalds.sdk.connectors.middleware.response.MWUpdateProfileResponse;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.modules.notification.NotificationModule;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import com.mcdonalds.sdk.utils.DateUtils;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MWUpdateProfileRequest extends MWRequest<MWUpdateProfileResponse, MWJSONRequestBody> {
    private static final String SKIP_BIRTHDAY_DAY = "interface.register.skipBirthDayDayInfo";
    private static final String URL_PATH = "/customer/profile";
    private final MWRequestHeaders mHeaderMap;
    protected MWJSONRequestBody mPostBody;

    @Deprecated
    public MWUpdateProfileRequest(MiddlewareConnector ignored, String ecpToken, CustomerProfile profile) {
        this(ecpToken, profile);
    }

    public MWUpdateProfileRequest(String ecpToken, CustomerProfile profile) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        List<PaymentCard> paymentCards = profile.getCardItems();
        List<MWCustomerCardData> customerCardDataList = new ArrayList();
        if (paymentCards != null) {
            int size = paymentCards.size();
            for (int i = 0; i < size; i++) {
                MWPaymentCardData cardData = MWPaymentCardData.toMWPaymentCardData((PaymentCard) paymentCards.get(i));
                if (cardData != null) {
                    cardData.isValid = Boolean.valueOf(true);
                }
                customerCardDataList.add(MWCustomerCardData.fromPaymentCardData(cardData));
            }
        }
        this.mPostBody = new MWJSONRequestBody();
        this.mPostBody.put("userName", profile.getUserName());
        if (profile.getNewUserName() == null) {
            this.mPostBody.put("newUserName", profile.getUserName());
        } else {
            this.mPostBody.put("newUserName", profile.getNewUserName());
        }
        this.mPostBody.put("password", profile.getPassword());
        this.mPostBody.put("firstName", profile.getFirstName());
        this.mPostBody.put("lastName", TextUtils.isEmpty(profile.getLastName()) ? "-" : profile.getLastName());
        this.mPostBody.put("middleName", profile.getMiddleName());
        this.mPostBody.put("nickName", profile.getNickName());
        this.mPostBody.put("mobileNumber", profile.getMobileNumber());
        this.mPostBody.put("emailAddress", profile.getEmailAddress());
        this.mPostBody.put("receivePromotions", Boolean.valueOf(false));
        this.mPostBody.put("customerId", Long.valueOf(profile.getCustomerId()));
        this.mPostBody.put("yearOfBirth", profile.getYearOfBirth());
        this.mPostBody.put("monthOfBirth", profile.getMonthOfBirth());
        this.mPostBody.put("MSAlarmEnabled", Boolean.valueOf(profile.ismMSAlarmEnabled()));
        if (!Configuration.getSharedInstance().getBooleanForKey(SKIP_BIRTHDAY_DAY)) {
            this.mPostBody.put("dayOfBirth", profile.getDayOfBirth());
        }
        this.mPostBody.put("cardItems", customerCardDataList);
        this.mPostBody.put("isPrivacyPolicyAccepted", Boolean.valueOf(true));
        this.mPostBody.put("isTermsOfUseAccepted", Boolean.valueOf(true));
        this.mPostBody.put("zipCode", profile.getZipCode());
        this.mPostBody.put("subscribedToOffer", Boolean.valueOf(profile.isSubscribedToOffers()));
        this.mPostBody.put("optInForCommunicationChannel", profile.getOptInForCommunicationChannel());
        this.mPostBody.put("optInForSurveys", profile.getOptInForSurveys());
        this.mPostBody.put("optInForProgramChanges", profile.getOptInForProgramChanges());
        this.mPostBody.put("optInForContests", profile.getOptInForContests());
        this.mPostBody.put("optInForOtherMarketingMessages", profile.getOptInForOtherMarketingMessages());
        this.mPostBody.put("notificationPreferences", MWNotificationPreferences.fromNotificationPreferences(profile.getNotificationPreferences()));
        this.mPostBody.put("preferredOfferCategories", profile.getPreferredOfferCategories());
        this.mPostBody.put("preferredNotification", Integer.valueOf(profile.getPreferredNotification() != null ? profile.getPreferredNotification().intValue() : 1));
        this.mPostBody.put(DCSPreference.DESC_PREFERRED_LANGUAGE, LocalDataManager.getSharedInstance().getDeviceLanguage());
        if (profile.getLoginPreference() != 0) {
            this.mPostBody.put("loginPreference", Integer.valueOf(profile.getLoginPreference()));
        }
        this.mPostBody.put("source", DCSPolicy.DEFAULT_SOURCE_ID);
        String storeId = "";
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        Store store = customerModule.getNearByStore();
        if (store == null) {
            store = customerModule.getCurrentStore();
        }
        if (store != null) {
            storeId = String.valueOf(store.getStoreId());
        }
        this.mPostBody.put("restaurantId", storeId);
        NotificationModule notificationModule = (NotificationModule) ModuleManager.getModule("notification");
        if (notificationModule != null) {
            String registrationId = notificationModule.getRegistrationId();
            this.mPostBody.put("deviceToken", registrationId);
            this.mPostBody.put("deviceId", registrationId);
        }
        this.mPostBody.put("socialNetworkProvider", profile.getSocialProvider());
        this.mPostBody.put("systemLanguage", Configuration.getSharedInstance().getCurrentLanguage());
        this.mPostBody.put("systemVersion", VERSION.RELEASE);
        this.mPostBody.put("timeZone", DateUtils.timeZoneForNotificationCall());
        this.mPostBody.put("systemName", "Android");
        this.mPostBody.put(PushConstants.TITLE_KEY, null);
        this.mPostBody.put("gender", null);
        this.mPostBody.put("sourceProgram", DCSPolicy.DEFAULT_SOURCE_ID);
        this.mPostBody.put("deviceBuildId", Integer.valueOf(4));
        this.mPostBody.put("userKey", Long.valueOf(profile.getCustomerId()));
        this.mPostBody.put(AnalyticAttribute.UUID_ATTRIBUTE, null);
        this.mPostBody.put("deviceName", Build.MODEL);
        this.mPostBody.put("deviceBrand", Build.BRAND);
        this.mPostBody.put(AnalyticAttribute.DEVICE_MANUFACTURER_ATTRIBUTE, Build.MANUFACTURER);
        this.mPostBody.put("mobilePhone", profile.getMobileNumber());
        this.mPostBody.put("Opt-Ins", MWCustomerData.fromCustomer(profile).optIns);
    }

    public MethodType getMethodType() {
        return MethodType.PUT;
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

    public Class<MWUpdateProfileResponse> getResponseClass() {
        return MWUpdateProfileResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
