package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWGetItemByExternalIdResponse;

public class MWNutritionGetItemListOnExternalIDRequest extends MWNutritionRequest<MWGetItemByExternalIdResponse> {
    private static final String URL_PATH = "/item/nutrition/listExternal";

    @Deprecated
    public MWNutritionGetItemListOnExternalIDRequest(MiddlewareConnector ignored, String notUsed, String externalItemId) {
        this(externalItemId);
    }

    public MWNutritionGetItemListOnExternalIDRequest(String externalItemId) {
        this.mQueryArgs.put("externalItemId", externalItemId);
    }

    public Class<MWGetItemByExternalIdResponse> getResponseClass() {
        return MWGetItemByExternalIdResponse.class;
    }

    /* Access modifiers changed, original: 0000 */
    public String getEndpoint() {
        return URL_PATH;
    }
}
