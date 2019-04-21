package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWLocationEventResponse;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class MWLocationEventRequest implements RequestProvider<MWLocationEventResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/location/event";
    private final String CONFIG = MiddlewareConnector.CONFIG_BASE_PATH;
    private Map<String, String> mHeaderMap;
    private final MWJSONRequestBody mPostBody;
    private String mUrl;

    public MWLocationEventRequest(String eventType, String closestRestaurantId, String gblNumber, String deviceToken) {
        if (this.mHeaderMap == null) {
            this.mHeaderMap = new HashMap();
        }
        this.mHeaderMap.put(MiddlewareConnector.CONFIG_HEADER_API_KEY, Configuration.getSharedInstance().getStringForKey("connectors.Middleware.mcd_apikey"));
        this.mHeaderMap.put("marketId", Configuration.getSharedInstance().getStringForKey("connectors.Middleware.headerMarketId"));
        this.mPostBody = new MWJSONRequestBody(false);
        setPostData(eventType, closestRestaurantId, gblNumber, deviceToken);
        this.mUrl = MiddlewareConnector.getURLStringForEndpoint(URL_PATH);
    }

    private void setPostData(String eventType, String closestRestaurantId, String gblNumber, String deviceToken) {
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mPostBody.put("timeZone", TimeZone.getDefault().getID());
        this.mPostBody.put(AnalyticAttribute.EVENT_TYPE_ATTRIBUTE, eventType);
        this.mPostBody.put("closestRestaurantId", closestRestaurantId);
        this.mPostBody.put("gblNumber", gblNumber);
        this.mPostBody.put("deviceToken", deviceToken);
        this.mPostBody.put("systemMobilePhone", customerModule.getCurrentProfile().getMobileNumber());
        this.mPostBody.put("customerId", Long.valueOf(customerModule.getCurrentProfile().getCustomerId()));
        this.mPostBody.put("emailAddress", customerModule.getCurrentProfile().getEmailAddress());
    }

    public MethodType getMethodType() {
        return MethodType.POST;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    public String getURLString() {
        return this.mUrl;
    }

    public Map<String, String> getHeaders() {
        return this.mHeaderMap;
    }

    public String getBody() {
        return this.mPostBody.toJson();
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWLocationEventResponse> getResponseClass() {
        return MWLocationEventResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
