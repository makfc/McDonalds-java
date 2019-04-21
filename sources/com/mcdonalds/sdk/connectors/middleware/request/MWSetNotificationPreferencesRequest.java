package com.mcdonalds.sdk.connectors.middleware.request;

import android.os.Build;
import android.os.Build.VERSION;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.DCSPolicy;
import com.mcdonalds.sdk.connectors.middleware.model.MWNotificationPreferences;
import com.mcdonalds.sdk.connectors.middleware.response.MWSetNotificationPreferencesResponse;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.notification.NotificationModule;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import com.mcdonalds.sdk.utils.DateUtils;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.List;
import java.util.Map;

public class MWSetNotificationPreferencesRequest extends MWRequest<MWSetNotificationPreferencesResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/preference/notification";
    private final MWJSONRequestBody mBody;
    private final MWRequestHeaders mHeaderMap;

    @Deprecated
    public MWSetNotificationPreferencesRequest(MiddlewareConnector ignored, String ecpToken, String username, MWNotificationPreferences preferences) {
        this(ecpToken, username, preferences);
    }

    public MWSetNotificationPreferencesRequest(String ecpToken, String username, MWNotificationPreferences preferences) {
        this.mBody = new MWJSONRequestBody();
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mBody.put("userName", username);
        this.mBody.put("preferences", preferences);
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        CustomerProfile profile = customerModule.getCurrentProfile();
        this.mBody.put("firstName", profile.getFirstName());
        this.mBody.put("lastName", profile.getLastName());
        this.mBody.put("customerId", Long.valueOf(profile.getCustomerId()));
        this.mBody.put("emailAddress", profile.getEmailAddress());
        this.mBody.put("zipCode", profile.getZipCode());
        this.mBody.put("source", DCSPolicy.DEFAULT_SOURCE_ID);
        String storeId = "";
        Store store = customerModule.getCurrentStore();
        if (store != null) {
            storeId = String.valueOf(store.getStoreId());
        }
        this.mBody.put("restaurantId", storeId);
        NotificationModule notificationModule = (NotificationModule) ModuleManager.getModule("notification");
        if (notificationModule != null) {
            this.mBody.put("deviceToken", notificationModule.getRegistrationId());
        }
        this.mBody.put("socialNetworkProvider", profile.getSocialProvider());
        this.mBody.put("systemLanguage", Configuration.getSharedInstance().getCurrentLanguage());
        this.mBody.put("systemVersion", VERSION.RELEASE);
        this.mBody.put("timeZone", DateUtils.timeZoneForNotificationCall());
        this.mBody.put("systemName", "Android");
        this.mBody.put(PushConstants.TITLE_KEY, null);
        this.mBody.put("gender", null);
        this.mBody.put("sourceProgram", DCSPolicy.DEFAULT_SOURCE_ID);
        this.mBody.put("deviceBuildId", Integer.valueOf(4));
        this.mBody.put("userKey", Long.valueOf(profile.getCustomerId()));
        this.mBody.put(AnalyticAttribute.UUID_ATTRIBUTE, null);
        this.mBody.put("deviceName", Build.MODEL);
        this.mBody.put("deviceBrand", Build.BRAND);
        this.mBody.put(AnalyticAttribute.DEVICE_MANUFACTURER_ATTRIBUTE, Build.MANUFACTURER);
        this.mBody.put("mobilePhone", profile.getMobileNumber());
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
        return this.mBody.toJson();
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWSetNotificationPreferencesResponse> getResponseClass() {
        return MWSetNotificationPreferencesResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWSetNoificationPreferencesRequest{mHeaderMap=" + this.mHeaderMap + ", mBody=" + this.mBody + "}";
    }
}
