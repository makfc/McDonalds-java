package com.mcdonalds.sdk.services.configuration;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class LocalizationConfig {
    @SerializedName("defaultCurrencyFormatLanguage")
    private String defaultCurrencyFormatLanguage;
    @SerializedName("defaultEasyAddressLanguageName")
    private String defaultEasyAddressLanguageName;
    @SerializedName("defaultLanguageName")
    private String defaultLanguage;
    @SerializedName("defaultNutritionLanguageName")
    private String defaultNutritionLanguageName;
    @SerializedName("availableLanguageNames")
    private List<Language> languages;
    @SerializedName("availableNutritionLanguageNames")
    private List<Language> nutritionLanguageNames;

    public List<Language> getNutritionLanguageNames() {
        return this.nutritionLanguageNames;
    }

    public List<Language> getLanguages() {
        return this.languages;
    }

    public String getDefaultLanguage() {
        return this.defaultLanguage;
    }

    public String getDefaultCurrencyFormatLanguage() {
        return this.defaultCurrencyFormatLanguage;
    }

    public String getDefaultNutritionLanguageName() {
        return this.defaultNutritionLanguageName;
    }

    public String getDefaultEasyAddressLanguageName() {
        return this.defaultEasyAddressLanguageName;
    }

    public Language getLanguage(String language) {
        for (Language lang : this.languages) {
            if (lang.match(language)) {
                return lang;
            }
        }
        return null;
    }
}
