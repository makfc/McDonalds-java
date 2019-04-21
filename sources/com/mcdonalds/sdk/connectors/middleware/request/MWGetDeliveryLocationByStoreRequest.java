package com.mcdonalds.sdk.connectors.middleware.request;

import android.text.TextUtils;
import com.admaster.square.utils.Order;
import com.mcdonalds.sdk.connectors.middleware.deserializer.ISO8601DateDeserializer;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetStoreInformationResponse;
import com.mcdonalds.sdk.services.network.CatalogVersionTypeTypeAdapter;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import com.mcdonalds.sdk.utils.DateUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MWGetDeliveryLocationByStoreRequest extends MWRequest<MWGetStoreInformationResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/restaurant/deliveryLocationByStore";
    private final MWRequestHeaders mHeaderMap;
    private final MWGETQueryArgs mQueryArgs = new MWGETQueryArgs();

    public MWGetDeliveryLocationByStoreRequest(String ecpToken, Date deliveryTime, boolean isNormalOrder, double orderAmount, List<String> storeIds) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        String dateString = "";
        if (deliveryTime != null) {
            dateString = DateUtils.formatToISO8631(deliveryTime, true);
        }
        if (!TextUtils.isEmpty(dateString)) {
            this.mQueryArgs.put("deliveryTime", dateString);
        }
        this.mQueryArgs.put("isNormalOrder", Boolean.valueOf(isNormalOrder));
        this.mQueryArgs.put(Order.od_orderAcount, Double.valueOf(orderAmount));
        List<Integer> storeNumbers = new ArrayList(storeIds.size());
        for (String storeId : storeIds) {
            try {
                storeNumbers.add(Integer.valueOf(Integer.parseInt(storeId)));
            } catch (NumberFormatException e) {
            }
        }
        this.mQueryArgs.put("storeNumbers", storeNumbers);
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

    public Class<MWGetStoreInformationResponse> getResponseClass() {
        return MWGetStoreInformationResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        ArrayList<CustomTypeAdapter> adapters = new ArrayList();
        adapters.add(new CatalogVersionTypeTypeAdapter());
        adapters.add(new ISO8601DateDeserializer());
        return adapters;
    }
}
