package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWCustomerCardData;
import com.mcdonalds.sdk.connectors.middleware.model.MWPaymentCardData;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetCustomerDataResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWRegisterPaymentResponse;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MWUpdatePaymentRequest extends MWRequest<MWGetCustomerDataResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/payment";
    private final MWRequestHeaders mHeaderMap;
    protected MWJSONRequestBody mPostBody;

    @Deprecated
    public MWUpdatePaymentRequest(MiddlewareConnector ignored, String ecpToken, String userName, MWRegisterPaymentResponse registerPaymentResponse, boolean isPreferred) {
        this(ecpToken, userName, registerPaymentResponse, isPreferred);
    }

    public MWUpdatePaymentRequest(String ecpToken, String userName, MWRegisterPaymentResponse registerPaymentResponse, boolean isPreferred) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        MWPaymentCardData cardData = registerPaymentResponse != null ? (MWPaymentCardData) registerPaymentResponse.getData() : null;
        if (cardData != null) {
            cardData.isValid = Boolean.valueOf(true);
        }
        MWCustomerCardData ecpCustomerCardData = MWCustomerCardData.fromPaymentCardData(cardData);
        ecpCustomerCardData.isPreferred = isPreferred;
        this.mPostBody = new MWJSONRequestBody();
        this.mPostBody.put("userName", userName);
        this.mPostBody.put("accountItems", new ArrayList());
        this.mPostBody.put("cardItems", Collections.singletonList(ecpCustomerCardData));
    }

    @Deprecated
    public MWUpdatePaymentRequest(MiddlewareConnector ignored, String ecpToken, String userName, List<PaymentCard> paymentCards) {
        this(ecpToken, userName, paymentCards);
    }

    public MWUpdatePaymentRequest(String ecpToken, String userName, List<PaymentCard> paymentCards) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        List<MWCustomerCardData> customerCardDataList = new ArrayList();
        for (int i = 0; i < paymentCards.size(); i++) {
            MWPaymentCardData cardData = MWPaymentCardData.toMWPaymentCardData((PaymentCard) paymentCards.get(i));
            if (cardData != null && cardData.isValid == null) {
                cardData.isValid = Boolean.valueOf(true);
            }
            customerCardDataList.add(MWCustomerCardData.fromPaymentCardData(cardData));
        }
        this.mPostBody = new MWJSONRequestBody();
        this.mPostBody.put("userName", userName);
        this.mPostBody.put("accountItems", new ArrayList());
        this.mPostBody.put("cardItems", customerCardDataList);
    }

    public MethodType getMethodType() {
        return MethodType.PUT;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    /* Access modifiers changed, original: 0000 */
    public String getEndpoint() {
        return URL_PATH;
    }

    public Map<String, String> getHeaders() {
        return this.mHeaderMap;
    }

    public String getBody() {
        return this.mPostBody.toJson();
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWGetCustomerDataResponse> getResponseClass() {
        return MWGetCustomerDataResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
