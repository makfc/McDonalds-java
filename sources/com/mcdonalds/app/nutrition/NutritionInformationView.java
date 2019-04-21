package com.mcdonalds.app.nutrition;

import com.mcdonalds.sdk.modules.models.Allergen;
import com.mcdonalds.sdk.modules.models.Nutrient;
import com.mcdonalds.sdk.modules.models.RecipeComponent;
import java.util.List;

public interface NutritionInformationView {
    void displayRecipeInformation(String str, String str2, List<Nutrient> list, List<Allergen> list2, List<String> list3, List<RecipeComponent> list4, List<String> list5, String str3);
}
