package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.response.MWGetPaymentWalletResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWGetPaymentWalletRequest extends MWRequest<MWGetPaymentWalletResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/payment/wallet";
    private final MWRequestHeaders mHeaderMap;

    public MWGetPaymentWalletRequest(String ecpToken) {
        this.mHeaderMap = getHeaderMap(ecpToken);
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

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWGetPaymentWalletResponse> getResponseClass() {
        return MWGetPaymentWalletResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    /* Access modifiers changed, original: 0000 */
    public String getEndpoint() {
        return URL_PATH;
    }
}
