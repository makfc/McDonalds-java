package com.mcdonalds.sdk.connectors.middleware.request;

import android.util.Log;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWPaymentTypeRegistrationURLResponse;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWPaymentTypeRegistrationURLRequest extends MWRequest<MWPaymentTypeRegistrationURLResponse, Void> {
    private static final String KEY_POST_PAYMENT_REGISTRATION_URL_REQUEST = "modules.customer.postPaymentRegistrationURLRequest";
    private static final String URL_PATH = "/customer/payment/type/registrationUrl";
    private final MWRequestHeaders mHeaderMap;
    protected final MWJSONRequestBody mPostBody;
    private final MWGETQueryArgs mQueryArgs;

    @Deprecated
    public MWPaymentTypeRegistrationURLRequest(MiddlewareConnector ignored, String ecpToken, String userName, Long customerId, Integer paymentMethodId, Boolean oneTimePayment) {
        this(ecpToken, userName, customerId, paymentMethodId, oneTimePayment);
    }

    public MWPaymentTypeRegistrationURLRequest(String ecpToken, String userName, Long customerId, Integer paymentMethodId, Boolean oneTimePayment) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mQueryArgs = new MWGETQueryArgs();
        this.mPostBody = new MWJSONRequestBody(false);
        if (Configuration.getSharedInstance().getBooleanForKey(KEY_POST_PAYMENT_REGISTRATION_URL_REQUEST)) {
            this.mPostBody.put("userName", userName);
            this.mPostBody.put("customerId", customerId);
            this.mPostBody.put("paymentMethodId", paymentMethodId);
            this.mPostBody.put("oneTimePayment", oneTimePayment);
        } else {
            this.mQueryArgs.put("userName", userName);
            this.mQueryArgs.put("customerId", customerId);
            this.mQueryArgs.put("paymentMethodId", paymentMethodId);
            this.mQueryArgs.put("oneTimePayment", oneTimePayment);
        }
        Log.i("PaymentTypeURLRequest", getURLString());
    }

    public MethodType getMethodType() {
        if (Configuration.getSharedInstance().getBooleanForKey(KEY_POST_PAYMENT_REGISTRATION_URL_REQUEST)) {
            return MethodType.POST;
        }
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
        return this.mPostBody.toJson(getCustomTypeAdapters());
    }

    public void setBody(Void body) {
    }

    public Class<MWPaymentTypeRegistrationURLResponse> getResponseClass() {
        return MWPaymentTypeRegistrationURLResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }

    public String toString() {
        return "MWPaymentTypeRegistrationURLRequest{mHeaderMap=" + this.mHeaderMap + ", mQueryArgs=" + this.mQueryArgs + "}";
    }
}
