package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.connectors.utils.Utils;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.Nutrient;
import com.mcdonalds.sdk.modules.models.NutritionRecipe;
import java.util.ArrayList;
import java.util.List;

public class MWMenuItem extends MWRecipeItem {
    public String description;
    @SerializedName("display_order")
    public int displayOrder;
    public MWRecipeForIdItem recipeDetailedInfo;

    public static MWRecipeForIdItem toMWRecipeForIdItem(MWMenuItem item) {
        MWRecipeForIdItem recipeForIdItem = new MWRecipeForIdItem();
        recipeForIdItem.carouselImage = item.carouselImage;
        recipeForIdItem.itemThumbNailImage = item.itemThumbNailImage;
        recipeForIdItem.defaultCategory = item.defaultCategory;
        recipeForIdItem.name = item.name;
        recipeForIdItem.f6067id = item.f6067id;
        recipeForIdItem.externalId = item.externalId;
        recipeForIdItem.nutrientFacts = item.nutrientFacts;
        recipeForIdItem.itemAllergen = item.itemAllergen;
        recipeForIdItem.itemAdditionalAllergen = item.itemAdditionalAllergen;
        return recipeForIdItem;
    }

    public NutritionRecipe toRecipe(String baseImagePath) {
        NutritionRecipe recipe = super.toRecipe(baseImagePath);
        recipe.setName(this.name);
        recipe.setMarketingName(Utils.cleanToMarketName(this.marketingName));
        recipe.setDisplayOrder(this.displayOrder);
        recipe.setMenuItemNumber(this.menuItemNumber);
        recipe.setNeedsFullDetails(true);
        try {
            recipe.setExternalId(Integer.parseInt(this.externalId));
        } catch (NumberFormatException e) {
        }
        setRecipeNutrientList(recipe);
        setRecipeCategories(recipe);
        setRelationTypeToCategory(recipe);
        return recipe;
    }

    private void setRecipeNutrientList(NutritionRecipe recipe) {
        if (this.nutrientFacts != null) {
            List<MWNutrient> mwNutrientList = this.nutrientFacts.nutrientList;
            if (mwNutrientList != null) {
                int size = mwNutrientList.size();
                List<Nutrient> nutrients = new ArrayList(size);
                for (int i = 0; i < size; i++) {
                    MWNutrient mwNutrient = (MWNutrient) mwNutrientList.get(i);
                    nutrients.add(mwNutrient.toNutrient());
                    recipe.setServingSizeValue(mwNutrient.value);
                    recipe.setServingSizeUnit(mwNutrient.unit);
                }
                recipe.setNutrients(nutrients);
            }
        }
    }

    private void setRecipeCategories(NutritionRecipe recipe) {
        List<Category> allCategories = new ArrayList();
        if (this.defaultCategory != null) {
            MWDefaultCategoryCategory mwCategory = this.defaultCategory.category;
            if (mwCategory != null) {
                allCategories.add(mwCategory.toCategory());
            }
        }
        recipe.setCategories(allCategories);
    }
}
