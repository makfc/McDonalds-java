package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWPaymentTypeRegistrationPostUrlResponse;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.List;
import java.util.Map;

public class MWPaymentTypePostRegistrationUrlRequest implements RequestProvider<MWPaymentTypeRegistrationPostUrlResponse, Void> {
    private static final String APPLICATION_KEY = ".application";
    private static final String LANGUAGE_KEY = ".languageName";
    private static final String MARKET_ID_KEY = ".marketId";
    private static final String URL_PATH = "/customer/payment/card/registration/config";
    private final MWRequestHeaders mHeaderMap;
    protected final MWJSONRequestBody mPostBody = new MWJSONRequestBody(false);
    private final String mUrl;

    public MWPaymentTypePostRegistrationUrlRequest(String ecpToken, int storeId, Long customerId, Integer paymentMethodId, Boolean oneTimePayment) {
        this.mHeaderMap = new MWRequestHeaders(ecpToken);
        Configuration configuration = Configuration.getSharedInstance();
        this.mPostBody.put("marketId", configuration.getStringForKey("connectors.Middleware.marketId"));
        this.mPostBody.put("application", configuration.getStringForKey("connectors.Middleware.application"));
        this.mPostBody.put("languageName", configuration.getStringForKey("connectors.Middleware.languageName"));
        this.mPostBody.put(AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE, "android");
        this.mPostBody.put("paymentMethodId", paymentMethodId);
        this.mPostBody.put("oneTimePayment", oneTimePayment);
        this.mPostBody.put("customerId", customerId);
        this.mPostBody.put("storeId", Integer.valueOf(storeId));
        this.mUrl = MiddlewareConnector.getURLStringForEndpoint(URL_PATH);
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

    public void setBody(Void body) {
    }

    public Class<MWPaymentTypeRegistrationPostUrlResponse> getResponseClass() {
        return MWPaymentTypeRegistrationPostUrlResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
