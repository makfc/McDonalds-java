package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetAllRecipesResponse;

public class MWNutritionGetAllItemsRequest extends MWNutritionRequest<MWGetAllRecipesResponse> {
    private static final String URL_PATH = "/item/nutrition/all";

    @Deprecated
    public MWNutritionGetAllItemsRequest(MiddlewareConnector ignored, String notUsed, String itemType, String showLiveData) {
        this(itemType, showLiveData);
    }

    public MWNutritionGetAllItemsRequest(String itemType, String showLiveData) {
        this.mQueryArgs.put("itemType", itemType);
        this.mQueryArgs.put("showLiveData", showLiveData);
    }

    public Class<MWGetAllRecipesResponse> getResponseClass() {
        return MWGetAllRecipesResponse.class;
    }

    /* Access modifiers changed, original: 0000 */
    public String getEndpoint() {
        return URL_PATH;
    }
}
