package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class MWRecipe implements Serializable {
    @SerializedName("Choices")
    public List<MWIngredient> choices;
    @SerializedName("Comments")
    public List<MWIngredient> comments;
    @SerializedName("DefaultSolution")
    public Object defaultSolution;
    @SerializedName("Extras")
    public List<MWIngredient> extras;
    @SerializedName("Ingredients")
    public List<MWIngredient> ingredients;
    @SerializedName("IsCustomerFriendly")
    public Boolean isCustomerFriendly;
    @SerializedName("IsValid")
    public Boolean isValid;
    @SerializedName("RecipeID")
    public Integer recipeID;
}
