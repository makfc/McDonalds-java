package com.mcdonalds.sdk.connectors.middlewarestorelocator;

import com.mcdonalds.sdk.connectors.middleware.request.MWRequestHeaders;
import com.mcdonalds.sdk.connectors.middlewarestorelocator.model.MiddlewareStoreLocatorStore;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import com.mcdonalds.sdk.services.network.SessionManager;
import java.util.List;
import java.util.Map;

public class MiddlewareStoreLocatorRequest implements RequestProvider<MiddlewareStoreLocatorStore[], Object> {
    private static final String CONFIG_HEADER_MARKET_ID = "connectors.MiddlewareStoreLocator.storeLocator.headerMarketId";
    private static final String CONFIG_MARKET_ID = "connectors.MiddlewareStoreLocator.storeLocator.marketId";
    private static final String CONFIG_MCD_API_KEY = "connectors.MiddlewareStoreLocator.storeLocator.mcd_apikey";
    private static final String CONFIG_STORE_LOCATOR_BASE_PATH = "connectors.MiddlewareStoreLocator.storeLocator";
    private static final String HEADER_MARKET_ID = "MarketId";
    private static final String HEADER_MCD_API = "mcd_apikey";
    private Map<String, String> mHeaderMap;
    private String mUrl;

    public MiddlewareStoreLocatorRequest(String url, String token) {
        this.mUrl = url;
        this.mHeaderMap = new MWRequestHeaders(token);
    }

    @Deprecated
    public MiddlewareStoreLocatorRequest(String url) {
        this(url, SessionManager.getInstance().getToken());
    }

    public String getURLString() {
        return this.mUrl;
    }

    public Class<MiddlewareStoreLocatorStore[]> getResponseClass() {
        return MiddlewareStoreLocatorStore[].class;
    }

    public MethodType getMethodType() {
        return MethodType.GET;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    public Map<String, String> getHeaders() {
        return this.mHeaderMap;
    }

    public String getBody() {
        return null;
    }

    public void setBody(Object body) {
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
