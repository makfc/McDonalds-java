package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWPointOfDistributionTypeAdapter;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWPriceTypeAdapter;
import com.mcdonalds.sdk.connectors.middleware.model.MWOrderUnAttendedCheckIn;
import com.mcdonalds.sdk.connectors.middleware.response.MWOrderUnAttendedCheckInResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MWOrderUnAttendedCheckInRequest implements RequestProvider<MWOrderUnAttendedCheckInResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/order/pickup/%s/unattended";
    private MWRequestHeaders mHeaders;
    private MWJSONRequestBody mRequest = new MWJSONRequestBody(false);
    private String mURL;

    public MWOrderUnAttendedCheckInRequest(String ecpToken, String checkInCode, MWOrderUnAttendedCheckIn unAttendedCheckIn) {
        this.mHeaders = new MWRequestHeaders(ecpToken);
        if (unAttendedCheckIn != null) {
            this.mRequest.put("POSStoreNumber", unAttendedCheckIn.storeId);
            this.mRequest.put("checkInData", unAttendedCheckIn.checkInData);
            this.mRequest.put("PriceType", unAttendedCheckIn.mType);
            this.mRequest.put("OrderPayment", unAttendedCheckIn.orderPayment);
            this.mRequest.put("AdditionalPayments", unAttendedCheckIn.additionalPayments);
            if (unAttendedCheckIn.OrderChangesAccepted) {
                this.mRequest.put("OrderChangesAccepted", Boolean.valueOf(unAttendedCheckIn.OrderChangesAccepted));
            }
        }
        this.mURL = MiddlewareConnector.getURLStringForEndpoint(String.format(URL_PATH, new Object[]{checkInCode}));
    }

    public MethodType getMethodType() {
        return MethodType.POST;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    public String getURLString() {
        return this.mURL;
    }

    public Map<String, String> getHeaders() {
        return this.mHeaders;
    }

    public String getBody() {
        return this.mRequest.toJson();
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWOrderUnAttendedCheckInResponse> getResponseClass() {
        return MWOrderUnAttendedCheckInResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        List<CustomTypeAdapter> adapters = new ArrayList();
        adapters.add(new MWPointOfDistributionTypeAdapter());
        adapters.add(new MWPriceTypeAdapter());
        return adapters;
    }

    public String toString() {
        return "MWOrderUnAttendedCheckInRequest{mHeaderMap=" + this.mHeaders.toString() + ", mPostBody=" + getBody() + ", mUrl=\"" + this.mURL + "\"}";
    }
}
