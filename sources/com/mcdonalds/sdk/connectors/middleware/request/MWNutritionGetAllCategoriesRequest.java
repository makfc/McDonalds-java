package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWNutritionGetAllCategoriesResponse;

public class MWNutritionGetAllCategoriesRequest extends MWNutritionRequest<MWNutritionGetAllCategoriesResponse> {
    private static final String URL_PATH = "/nutrition/category/list";

    @Deprecated
    public MWNutritionGetAllCategoriesRequest(MiddlewareConnector ignored, String notUsed, String categoryType) {
        this(categoryType);
    }

    public MWNutritionGetAllCategoriesRequest(String categoryType) {
        this.mQueryArgs.put("categoryType", categoryType);
        this.mQueryArgs.put("showLiveData", Integer.valueOf(1));
    }

    public Class<MWNutritionGetAllCategoriesResponse> getResponseClass() {
        return MWNutritionGetAllCategoriesResponse.class;
    }

    /* Access modifiers changed, original: 0000 */
    public String getEndpoint() {
        return URL_PATH;
    }
}
