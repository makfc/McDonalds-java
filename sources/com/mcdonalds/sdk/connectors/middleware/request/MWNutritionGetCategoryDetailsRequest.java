package com.mcdonalds.sdk.connectors.middleware.request;

import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.deserializer.MWNutritionCategoryItemDeserializer;
import com.mcdonalds.sdk.connectors.middleware.model.MWNutritionGetCategoryDetailsResponse;

public class MWNutritionGetCategoryDetailsRequest extends MWNutritionRequest<MWNutritionGetCategoryDetailsResponse> {
    private static final String URL_PATH = "/nutrition/category/detail";

    @Deprecated
    public MWNutritionGetCategoryDetailsRequest(MiddlewareConnector ignored, String notUsed, String categoryId, String showLiveData) {
        this(categoryId, showLiveData);
    }

    public MWNutritionGetCategoryDetailsRequest(String categoryId, String showLiveData) {
        addCustomDeserializer(new MWNutritionCategoryItemDeserializer());
        this.mQueryArgs.put("categoryId", categoryId);
        this.mQueryArgs.put("showLiveData", showLiveData);
    }

    public Class<MWNutritionGetCategoryDetailsResponse> getResponseClass() {
        return MWNutritionGetCategoryDetailsResponse.class;
    }

    /* Access modifiers changed, original: 0000 */
    public String getEndpoint() {
        return URL_PATH;
    }
}
