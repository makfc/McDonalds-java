package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.services.network.RequestProvider;

public abstract class MWRequest<T, E> implements RequestProvider<T, E> {
    public abstract String getEndpoint();

    public String getURLString() {
        return MiddlewareConnector.getURLStringForEndpoint(getMethodType(), getEndpoint()) + "?" + getQueryString();
    }

    /* Access modifiers changed, original: 0000 */
    public MWRequestHeaders getHeaderMap() {
        return getHeaderMap(null, null);
    }

    /* Access modifiers changed, original: 0000 */
    public MWRequestHeaders getHeaderMap(String ecpToken) {
        return getHeaderMap(ecpToken, null);
    }

    /* Access modifiers changed, original: 0000 */
    public MWRequestHeaders getHeaderMap(String ecpToken, String nonce) {
        return new MWRequestHeaders(ecpToken, nonce, MiddlewareConnector.getVersion(getMethodType(), getEndpoint()), MiddlewareConnector.getApiKey(getEndpoint()), MiddlewareConnector.getGuestApiKey(getEndpoint()));
    }

    /* Access modifiers changed, original: 0000 */
    public String getQueryString() {
        return "";
    }
}
