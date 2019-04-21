package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.RecipeComponent;
import java.io.Serializable;

public class MWRecipeComponent implements Serializable {
    @SerializedName("product_additional_text_ingredient_statement")
    public String additionalIngredient;
    @SerializedName("display_order")
    public int displayOrder;
    @SerializedName("external_id")
    public int externalId;
    @SerializedName("ingredient_statement")
    public String ingredientStatement;
    @SerializedName("ingredients")
    public MWRecipeIngredients ingredients;
    @SerializedName("is_default")
    public boolean isDefault;
    @SerializedName("is_test_product")
    public boolean isTestProduct;
    @SerializedName("product_additional_allergen")
    public String productAdditionalAllergen;
    @SerializedName("product_allergen")
    public String productAllergen;
    @SerializedName("product_id")
    public int productId;
    @SerializedName("product_marketing_name")
    public String productMarketingName;
    @SerializedName("product_name")
    public String productName;
    @SerializedName("product_type")
    public int productType;
    @SerializedName("id")
    public int recipeComponentId;

    public RecipeComponent toRecipeComponent() {
        RecipeComponent recipeComponent = new RecipeComponent();
        recipeComponent.setDisplayOrder(this.displayOrder);
        recipeComponent.setIngredientStatement(this.ingredientStatement);
        recipeComponent.setProductName(this.productName);
        recipeComponent.setProductAllergen(this.productAllergen);
        recipeComponent.setProductAdditionalAllergen(this.productAdditionalAllergen);
        recipeComponent.setAdditionalIngredient(this.additionalIngredient);
        return recipeComponent;
    }
}
