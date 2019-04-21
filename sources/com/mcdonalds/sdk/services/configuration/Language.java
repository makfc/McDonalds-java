package com.mcdonalds.sdk.services.configuration;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Language {
    @SerializedName("language")
    private String name;
    @SerializedName("nutritionLanguageName")
    private String nutritionLanguage;
    @SerializedName("languageName")
    private List<String> tags;

    public String getName() {
        return this.name;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public boolean isSingleCountry() {
        return this.tags.size() == 1;
    }

    public boolean match(String language) {
        return this.name.equals(language);
    }

    public String getNutritionLanguage() {
        return this.nutritionLanguage;
    }
}
