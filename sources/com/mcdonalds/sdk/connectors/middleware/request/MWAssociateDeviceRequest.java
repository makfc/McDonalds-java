package com.mcdonalds.sdk.connectors.middleware.request;

import android.os.Build;
import android.os.Build.VERSION;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWDevice;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.notification.NotificationModule;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MWAssociateDeviceRequest extends MWRequest<MWJSONResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/preference/device";
    private final MWRequestHeaders mHeaderMap;
    private MWJSONRequestBody mPostBody;

    @Deprecated
    public MWAssociateDeviceRequest(MiddlewareConnector ignored, String ecpToken, String userName, String deviceToken) {
        this(ecpToken, userName, deviceToken);
    }

    public MWAssociateDeviceRequest(String ecpToken, String userName, String deviceToken) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mPostBody = new MWJSONRequestBody();
        this.mPostBody.put("userName", userName);
        this.mPostBody.put("devices", Collections.singletonList(new MWDevice(deviceToken, Integer.valueOf(1))));
        CustomerProfile profile = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentProfile();
        this.mPostBody.put("customerId", Long.valueOf(profile.getCustomerId()));
        NotificationModule notificationModule = (NotificationModule) ModuleManager.getModule("notification");
        if (notificationModule != null) {
            this.mPostBody.put("deviceToken", notificationModule.getRegistrationId());
        }
        String language = Configuration.getSharedInstance().getCurrentLanguage();
        this.mPostBody.put("systemLanguage", language);
        this.mPostBody.put("systemVersion", VERSION.RELEASE);
        this.mPostBody.put("systemName", "Android");
        this.mPostBody.put("deviceBuildId", Integer.valueOf(4));
        this.mPostBody.put(AnalyticAttribute.UUID_ATTRIBUTE, null);
        this.mPostBody.put("deviceName", Build.MODEL);
        this.mPostBody.put("deviceBrand", Build.BRAND);
        this.mPostBody.put(AnalyticAttribute.DEVICE_MANUFACTURER_ATTRIBUTE, Build.MANUFACTURER);
        this.mPostBody.put("mobilePhone", profile.getMobileNumber());
        this.mPostBody.put("languagePref", language);
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

    public Class<MWJSONResponse> getResponseClass() {
        return MWJSONResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWAssociateDeviceRequest{mHeaderMap=" + this.mHeaderMap + ", mPostBody=" + this.mPostBody.toString() + "}";
    }
}
