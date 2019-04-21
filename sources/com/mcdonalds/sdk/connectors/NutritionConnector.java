package com.mcdonalds.sdk.connectors;

import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.NutritionRecipe;
import java.util.List;

public interface NutritionConnector {
    void getAllCategories(AsyncListener<List<Category>> asyncListener);

    void getAllRecipes(AsyncListener<List<NutritionRecipe>> asyncListener);

    void getAllRecipesForCategory(String str, AsyncListener<List<NutritionRecipe>> asyncListener);

    void getRecipeForExternalId(String str, AsyncListener<NutritionRecipe> asyncListener);

    void getRecipeForId(String str, AsyncListener<NutritionRecipe> asyncListener);

    void getRecipesForCategory(String str, AsyncListener<List<NutritionRecipe>> asyncListener);

    void populateFullRecipeDetails(NutritionRecipe nutritionRecipe, AsyncListener<NutritionRecipe> asyncListener);

    void setBaseImagePath(String str);
}
