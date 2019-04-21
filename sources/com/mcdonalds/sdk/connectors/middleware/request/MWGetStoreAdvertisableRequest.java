package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.response.MWGetStoreAdvertisableResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.util.List;
import java.util.Map;

public class MWGetStoreAdvertisableRequest extends MWRequest<MWGetStoreAdvertisableResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/offer/promotions/advertisable";
    private final MWRequestHeaders mHeaderMap;
    private final MWGETQueryArgs mQueryArgs = new MWGETQueryArgs();

    public MWGetStoreAdvertisableRequest(String ecpToken, int storeNumber, String userName) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mHeaderMap.put(TransactionStateUtil.CONTENT_TYPE_HEADER, "application/json");
        this.mQueryArgs.put("storeId", Integer.valueOf(storeNumber));
        this.mQueryArgs.put("userName", userName);
        this.mQueryArgs.put(TransactionStateUtil.CONTENT_TYPE_HEADER, "text/json");
    }

    public MethodType getMethodType() {
        return MethodType.GET;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    /* Access modifiers changed, original: 0000 */
    public String getEndpoint() {
        return URL_PATH;
    }

    /* Access modifiers changed, original: 0000 */
    public String getQueryString() {
        return this.mQueryArgs.toString();
    }

    public Map<String, String> getHeaders() {
        return this.mHeaderMap;
    }

    public String getBody() {
        return null;
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWGetStoreAdvertisableResponse> getResponseClass() {
        return MWGetStoreAdvertisableResponse.class;
    }

    public List<CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
