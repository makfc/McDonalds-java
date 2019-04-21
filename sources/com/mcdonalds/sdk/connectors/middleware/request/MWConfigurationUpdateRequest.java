package com.mcdonalds.sdk.connectors.middleware.request;

import com.amap.api.services.district.DistrictSearchQuery;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.DCSPolicy;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.List;
import java.util.Map;

public class MWConfigurationUpdateRequest extends MWRequest<Map, Void> {
    private final MWJSONRequestBody mBody = new MWJSONRequestBody();
    private final String mEndpoint;
    private final MWRequestHeaders mHeaderMap = getHeaderMap();
    private final MWGETQueryArgs mQueryArgs;

    public MWConfigurationUpdateRequest() {
        Configuration config = Configuration.getSharedInstance();
        this.mHeaderMap.put(MiddlewareConnector.CONFIG_HEADER_API_KEY, config.getStringForKey("configUpdate.configUpdateAPIKey"));
        this.mQueryArgs = new MWGETQueryArgs();
        this.mQueryArgs.clear();
        this.mQueryArgs.put("clientApp", DCSPolicy.DEFAULT_SOURCE_ID);
        this.mQueryArgs.put("id", getId(config));
        this.mQueryArgs.put("version", "1.0");
        this.mQueryArgs.put(AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE, "ANDROID");
        this.mQueryArgs.put(DistrictSearchQuery.KEYWORDS_COUNTRY, config.getStringForKey("configUpdate.queryArgs.country"));
        this.mEndpoint = config.getStringForKey("configUpdate.endPoint");
    }

    private String getId(Configuration config) {
        String lang = config.getCurrentLanguage();
        if (lang == null) {
            lang = config.getStringForKey("localization.defaultConfigUpdateLanguageName");
        }
        return config.getStringForKey("configUpdate.queryArgs.idPrefix") + lang;
    }

    public MethodType getMethodType() {
        return MethodType.GET;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    /* Access modifiers changed, original: 0000 */
    public String getEndpoint() {
        return this.mEndpoint;
    }

    /* Access modifiers changed, original: 0000 */
    public String getQueryString() {
        return this.mQueryArgs.toString();
    }

    public Map<String, String> getHeaders() {
        return this.mHeaderMap;
    }

    public String getBody() {
        return this.mBody.toJson();
    }

    public void setBody(Void body) {
    }

    public Class<Map> getResponseClass() {
        return Map.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWConfigurationUpdateRequest{mHeaderMap=" + this.mHeaderMap + ", mQueryArgs=" + this.mQueryArgs + ", mEndpoint=\"" + this.mEndpoint + this.mQueryArgs + "\"}";
    }
}
