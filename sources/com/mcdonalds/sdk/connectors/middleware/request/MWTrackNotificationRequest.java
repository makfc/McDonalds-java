package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MWTrackNotificationRequest extends MWRequest<Void, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/tracking";
    private MWRequestHeaders mHeaders;
    protected MWGETQueryArgs mQueryArgs = new MWGETQueryArgs();

    public MWTrackNotificationRequest(String token, String messageId, String deliveryId, int tagID) {
        this.mHeaders = getHeaderMap(token);
        this.mQueryArgs.put("id", String.format(Locale.getDefault(), "h%s,%s,%d", new Object[]{messageId, deliveryId, Integer.valueOf(tagID)}));
    }

    public MethodType getMethodType() {
        return MethodType.POST;
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
        return this.mHeaders;
    }

    public String getBody() {
        return null;
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<Void> getResponseClass() {
        return Void.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
