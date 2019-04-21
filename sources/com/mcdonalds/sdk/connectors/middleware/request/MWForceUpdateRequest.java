package com.mcdonalds.sdk.connectors.middleware.request;

import android.text.TextUtils;
import com.amap.api.services.district.DistrictSearchQuery;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWForceUpdateResponse;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.GsonRequest;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.List;
import java.util.Map;

public class MWForceUpdateRequest implements RequestProvider<MWForceUpdateResponse, MWJSONRequestBody> {
    private final String URL_PATH;
    private final MWJSONRequestBody mBody;
    private final MWRequestHeaders mHeaderMap;
    private final MWGETQueryArgs mQueryArgs;
    private final String mUrl;

    @Deprecated
    public MWForceUpdateRequest(MiddlewareConnector ignored, String mwToken) {
        this();
    }

    @Deprecated
    public MWForceUpdateRequest(String mwToken) {
        this();
    }

    public MWForceUpdateRequest() {
        this.URL_PATH = "/application/version";
        this.mBody = new MWJSONRequestBody();
        this.mHeaderMap = new MWRequestHeaders();
        setHeaderArgsFromConfig();
        this.mQueryArgs = new MWGETQueryArgs();
        this.mQueryArgs.clear();
        setQueryArgsFromConfig();
        this.mUrl = Configuration.getSharedInstance().getValueForKey("forceUpdate.baseUrl") + "/application/version";
    }

    private void setHeaderArgsFromConfig() {
        String apiKey = (String) Configuration.getSharedInstance().getValueForKey("forceUpdate.headerArgs.apiKey");
        if (!TextUtils.isEmpty(apiKey)) {
            this.mHeaderMap.clear();
            this.mHeaderMap.put(MiddlewareConnector.CONFIG_HEADER_API_KEY, apiKey);
        }
        String marketId = (String) Configuration.getSharedInstance().getValueForKey("forceUpdate.headerArgs.marketId");
        if (!TextUtils.isEmpty(marketId)) {
            this.mHeaderMap.put("marketId", marketId);
        }
        String nonce = (String) Configuration.getSharedInstance().getValueForKey("forceUpdate.headerArgs.nonce");
        if (!TextUtils.isEmpty(nonce)) {
            this.mHeaderMap.put("nonce", nonce);
        }
        String contentType = (String) Configuration.getSharedInstance().getValueForKey("forceUpdate.headerArgs.content-type");
        if (!TextUtils.isEmpty(contentType)) {
            this.mHeaderMap.put("content-type", contentType);
        }
    }

    private void setQueryArgsFromConfig() {
        String token = (String) Configuration.getSharedInstance().getValueForKey("forceUpdate.queryArgs.forceUpdateToken");
        if (!TextUtils.isEmpty(token)) {
            this.mHeaderMap.put(GsonRequest.HEADER_PARAM_TOKEN, token);
        }
        String clientApp = (String) Configuration.getSharedInstance().getValueForKey("forceUpdate.queryArgs.clientApp");
        if (!TextUtils.isEmpty(clientApp)) {
            this.mQueryArgs.put("clientApp", clientApp);
        }
        String platform = (String) Configuration.getSharedInstance().getValueForKey("forceUpdate.queryArgs.platform");
        if (!TextUtils.isEmpty(platform)) {
            this.mQueryArgs.put(AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE, platform);
        }
        String areaOfWorld = (String) Configuration.getSharedInstance().getValueForKey("forceUpdate.queryArgs.areaOfWorld");
        if (!TextUtils.isEmpty(areaOfWorld)) {
            this.mQueryArgs.put("areaOfWorld", areaOfWorld);
        }
        String country = (String) Configuration.getSharedInstance().getValueForKey("forceUpdate.queryArgs.country");
        if (!TextUtils.isEmpty(country)) {
            this.mQueryArgs.put(DistrictSearchQuery.KEYWORDS_COUNTRY, country);
        }
    }

    public MethodType getMethodType() {
        return MethodType.GET;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    public String getURLString() {
        return this.mUrl + "?" + this.mQueryArgs.toString();
    }

    public Map<String, String> getHeaders() {
        return this.mHeaderMap;
    }

    public String getBody() {
        return this.mBody.toJson();
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWForceUpdateResponse> getResponseClass() {
        return MWForceUpdateResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWForceUpdateRequest{mHeaderMap=" + this.mHeaderMap + ", mQueryArgs=" + this.mQueryArgs + ", mUrl=\"" + this.mUrl + this.mQueryArgs + "\"}";
    }
}
