package com.mcdonalds.sdk.modules.nutrition;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.mcdonalds.sdk.AsyncCounter;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.ConnectorManager;
import com.mcdonalds.sdk.connectors.NutritionConnector;
import com.mcdonalds.sdk.modules.BaseModule;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.NutritionRecipe;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.log.MCDLog;
import java.util.List;

public class NutritionModule extends BaseModule {
    private static final String CONFIG_BASE_PATH = "connectors.Middleware";
    public static final String CONNECTOR_KEY = "connector";
    public static final String DEFAULT_IMAGE_PATH = "http://www.mcdonalds.com/content/dam/McDonalds/item";
    public static final String KEY_NUTRITION_IMAGE_BASE_URL = "nutritionInfo.nutritionImageBaseUrl";
    public static final String KEY_NUTRITION_THUMB_BASE_URL = "modules.nutritionInfo.nutritionalInfoThumbBaseURL";
    public static final String NAME = "nutritionInfo";
    private NutritionConnector mConnector;

    @Deprecated
    public NutritionModule(Context context) {
        this();
    }

    public NutritionModule() {
        this.mConnector = (NutritionConnector) ConnectorManager.getConnector((String) Configuration.getSharedInstance().getValueForKey("modules.nutritionInfo.connector"));
        this.mConnector.setBaseImagePath(getBaseImagePath());
    }

    public void getRecipeForId(String itemId, @NonNull AsyncListener<NutritionRecipe> listener) {
        this.mConnector.getRecipeForId(itemId, listener);
    }

    public void getRecipeForExternalId(String externalId, @NonNull AsyncListener<NutritionRecipe> listener) {
        if (this.mConnector != null) {
            this.mConnector.getRecipeForExternalId(externalId, listener);
        }
    }

    public void getFullRecipeForExternalId(String externalId, @NonNull final AsyncListener<NutritionRecipe> listener) {
        getRecipeForExternalId(externalId, new AsyncListener<NutritionRecipe>() {
            public void onResponse(NutritionRecipe response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    NutritionModule.this.populateFullRecipeDetails(response, listener);
                } else {
                    listener.onResponse(null, null, exception);
                }
            }
        });
    }

    public void getRecipesWithExternalIds(List<String> externalIds, @NonNull AsyncListener<List<NutritionRecipe>> listener) {
        if (this.mConnector != null) {
            final AsyncCounter<NutritionRecipe> asyncCounter = new AsyncCounter(externalIds.size(), listener);
            for (final String externalId : externalIds) {
                getRecipeForExternalId(externalId, new AsyncListener<NutritionRecipe>() {
                    public void onResponse(NutritionRecipe response, AsyncToken token, AsyncException exception) {
                        if (exception == null) {
                            asyncCounter.success(response);
                            return;
                        }
                        MCDLog.temp("Could not find recipe for Id: " + externalId);
                        asyncCounter.success(null);
                    }
                });
            }
        }
    }

    public void getAllRecipes(@NonNull AsyncListener<List<NutritionRecipe>> listener) {
        if (this.mConnector != null) {
            this.mConnector.getAllRecipes(listener);
        }
    }

    public void getAllRecipesForCategory(String categoryId, @NonNull AsyncListener<List<NutritionRecipe>> listener) {
        if (this.mConnector != null) {
            this.mConnector.getAllRecipesForCategory(categoryId, listener);
        }
    }

    public void getAllCategories(@NonNull AsyncListener<List<Category>> listener) {
        if (this.mConnector != null) {
            this.mConnector.getAllCategories(listener);
        }
    }

    public Boolean supportsDayParts() {
        return Boolean.valueOf(false);
    }

    public void populateFullRecipeDetails(NutritionRecipe recipe, @NonNull AsyncListener<NutritionRecipe> listener) {
        if (this.mConnector != null) {
            this.mConnector.populateFullRecipeDetails(recipe, listener);
        }
    }

    public String getBaseImagePath() {
        String value = Configuration.getSharedInstance().getStringForKey(KEY_NUTRITION_THUMB_BASE_URL);
        if (!TextUtils.isEmpty(value)) {
            return value;
        }
        value = Configuration.getSharedInstance().getStringForKey(String.format("%s.%s", new Object[]{"connectors.Middleware", KEY_NUTRITION_IMAGE_BASE_URL}));
        if (TextUtils.isEmpty(value)) {
            return DEFAULT_IMAGE_PATH;
        }
        return value;
    }

    public void getRecipesForCategory(String categoryId, final AsyncListener<List<NutritionRecipe>> listener) {
        if (this.mConnector != null) {
            this.mConnector.getRecipesForCategory(categoryId, new AsyncListener<List<NutritionRecipe>>() {
                public void onResponse(List<NutritionRecipe> response, AsyncToken token, AsyncException exception) {
                    listener.onResponse(response, token, exception);
                }
            });
        }
    }
}
