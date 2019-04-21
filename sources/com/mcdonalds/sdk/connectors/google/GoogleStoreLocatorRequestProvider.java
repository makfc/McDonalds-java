package com.mcdonalds.sdk.connectors.google;

import com.mcdonalds.sdk.connectors.storelocator.StoreLocatorConnectorQueryParameters;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

class GoogleStoreLocatorRequestProvider implements RequestProvider<GoogleStoreResponse, Object> {
    GoogleStoreLocatorAPIBuilder mAPIBuilder;

    private GoogleStoreLocatorRequestProvider() {
    }

    GoogleStoreLocatorRequestProvider(StoreLocatorConnectorQueryParameters parameters, Map<String, GoogleAPIFilter> filtersMap) {
        Configuration config = Configuration.getSharedInstance();
        this.mAPIBuilder = new GoogleStoreLocatorAPIBuilder(config.getStringForKey("connectors.Google.storeLocator.baseURL"), config.getStringForKey("connectors.Google.storeLocator.endPoint"), config.getStringForKey("connectors.Google.storeLocator.tables"), config.getStringForKey("connectors.Google.storeLocator.apiMethod"), config.getStringForKey("connectors.Google.storeLocator.apiKey"), Integer.toString(parameters.getMaxResults()));
        if (parameters.getStoreIds() == null) {
            this.mAPIBuilder.setArea(parameters.getLatitude(), parameters.getLongitude(), parameters.getDistance());
            if (filtersMap != null && parameters.getFilters() != null) {
                List<String> keys = parameters.getFilters();
                int size = keys.size();
                for (int i = 0; i < size; i++) {
                    String key = (String) keys.get(i);
                    if (filtersMap.get(key) != null) {
                        this.mAPIBuilder.setFilter((GoogleAPIFilter) filtersMap.get(key), Boolean.valueOf(true));
                    }
                }
            }
        } else if (parameters.getStoreIds().size() > 1) {
            throw new IllegalArgumentException("Google Store Locator does not support queries for multiple stores");
        } else {
            this.mAPIBuilder.setStore((String) parameters.getStoreIds().get(0));
        }
    }

    public MethodType getMethodType() {
        return MethodType.GET;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    public String getURLString() {
        return this.mAPIBuilder.toString();
    }

    public Map<String, String> getHeaders() {
        return null;
    }

    public String getBody() {
        return null;
    }

    public void setBody(Object body) {
    }

    public Class<GoogleStoreResponse> getResponseClass() {
        return GoogleStoreResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "GoogleStoreLocatorRequestProvider{mAPIBuilder=" + this.mAPIBuilder + "}";
    }
}
