package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetRecipeForIdResponse;

public class MWNutritionGetItemDetailsRequest extends MWNutritionRequest<MWGetRecipeForIdResponse> {
    private static final String URL_PATH = "/item/nutrition/itemDetail";

    @Deprecated
    public MWNutritionGetItemDetailsRequest(MiddlewareConnector ignored, String notUsed, String itemId, String combination) {
        this(itemId, combination);
    }

    public MWNutritionGetItemDetailsRequest(String itemId, String combination) {
        this.mQueryArgs.put("item", itemId);
        this.mQueryArgs.put("combination", combination);
    }

    public Class<MWGetRecipeForIdResponse> getResponseClass() {
        return MWGetRecipeForIdResponse.class;
    }

    /* Access modifiers changed, original: 0000 */
    public String getEndpoint() {
        return URL_PATH;
    }
}
