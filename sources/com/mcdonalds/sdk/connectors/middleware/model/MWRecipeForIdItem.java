package com.mcdonalds.sdk.connectors.middleware.model;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.connectors.utils.Utils;
import com.mcdonalds.sdk.modules.models.Allergen;
import com.mcdonalds.sdk.modules.models.Nutrient;
import com.mcdonalds.sdk.modules.models.NutritionRecipe;
import com.mcdonalds.sdk.modules.models.RecipeComponent;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MWRecipeForIdItem extends MWRecipeItem {
    private static final String SERVING_SIZE_NUTRIENT_NAME = "Serving Size";
    private static final String SERVING_SIZE_NUTRIENT_NAME_CANADA_FR = "Portion";
    private static final Integer UNKNOWN_PRODUCT_ID = Integer.valueOf(-1);
    @SerializedName("item_additional_text_ingredient_statement")
    public String additionalIngredientStatement;
    @SerializedName("allergens")
    public MWRecipeAllergenItem allergens;
    @SerializedName("item_ingredient_statement")
    public String ingredientStatement;
    @SerializedName("item_comments")
    public String itemComments;
    @SerializedName("description")
    public String itemDescription;
    @SerializedName("item_id")
    public String itemId;
    @SerializedName("keywords")
    public String keywords;
    @SerializedName("components")
    public MWRecipeComponents recipeComponents;
    @SerializedName("text")
    public String text;

    public NutritionRecipe toRecipe(String baseImagePath) {
        NutritionRecipe product = super.toRecipe(baseImagePath);
        if (!TextUtils.isDigitsOnly(this.externalId)) {
            this.externalId = Pattern.compile("\\D").matcher(this.externalId).replaceAll("");
        }
        product.setExternalId(Integer.valueOf(this.externalId).intValue());
        product.setName(Utils.cleanToMarketName(this.marketingName));
        product.setDescription(this.itemDescription);
        setRelationTypeToCategory(product);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.nutritionalInfo.useMetricSystem") && this.servingSizeMetric != null) {
            product.setServingSizeValue(this.servingSizeMetric.value);
            product.setServingSizeUnit(this.servingSizeMetric.unit);
        } else if (this.servingSizeImperial != null) {
            product.setServingSizeValue(this.servingSizeImperial.value);
            product.setServingSizeUnit(this.servingSizeImperial.unit);
        }
        setRecipeNutrientList(product);
        setRecipeComponents(product);
        setRecipeAllergens(product);
        return product;
    }

    private void setRecipeNutrientList(NutritionRecipe recipe) {
        List<Nutrient> nutrientsList = new ArrayList();
        if (!(this.nutrientFacts == null || this.nutrientFacts.nutrientList == null)) {
            int size = this.nutrientFacts.nutrientList.size();
            for (int i = 0; i < size; i++) {
                MWNutrient mwNutrient = (MWNutrient) this.nutrientFacts.nutrientList.get(i);
                if (!mwNutrient.name.equals(SERVING_SIZE_NUTRIENT_NAME)) {
                    nutrientsList.add(mwNutrient.toNutrient());
                } else if (recipe.getServingSizeValue() == null) {
                    recipe.setServingSizeValue(mwNutrient.value);
                    recipe.setServingSizeUnit(mwNutrient.unit);
                }
            }
        }
        recipe.setNutrients(nutrientsList);
    }

    private void setRecipeComponents(NutritionRecipe recipe) {
        List<RecipeComponent> components;
        if (this.recipeComponents == null || this.recipeComponents.recipeComponentList == null) {
            components = new ArrayList(1);
            RecipeComponent recipeComponent = new RecipeComponent();
            recipeComponent.setProductAllergen(this.itemAllergen);
            recipeComponent.setProductAdditionalAllergen(this.itemAdditionalAllergen);
            recipeComponent.setAdditionalIngredient(this.additionalIngredientStatement);
            recipeComponent.setIngredientStatement(this.ingredientStatement);
            components.add(recipeComponent);
            recipe.setComponents(components);
            recipe.setAdditionalIngredientStatement(this.additionalIngredientStatement);
            recipe.setComponentsString(this.ingredientStatement);
            return;
        }
        List<MWRecipeComponent> mwRecipeComponents = this.recipeComponents.recipeComponentList;
        int size = mwRecipeComponents.size();
        components = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            components.add(((MWRecipeComponent) mwRecipeComponents.get(i)).toRecipeComponent());
        }
        recipe.setComponents(components);
    }

    private void setRecipeAllergens(NutritionRecipe recipe) {
        if (this.allergens == null || ListUtils.isEmpty(this.allergens.allergenList)) {
            recipe.setAllergensString(this.itemAllergen);
            recipe.setAdditionalAllergensString(this.itemAdditionalAllergen);
            return;
        }
        List<Allergen> recipeAllergens = new ArrayList();
        for (MWAllergen mwAllergen : this.allergens.allergenList) {
            recipeAllergens.add(mwAllergen.toAllergen());
        }
        recipe.setAllergens(recipeAllergens);
    }
}
