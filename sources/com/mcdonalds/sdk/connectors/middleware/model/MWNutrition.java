package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Nutrient;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MWNutrition implements Serializable {
    private List<Nutrient> allNutrients = null;
    @SerializedName("Allergenes")
    private String allergenes;
    @SerializedName("Calcium")
    private double calcium;
    @SerializedName("Caloriesfromfat")
    private double caloriesfromfat;
    @SerializedName("Carbohydrates")
    private double carbohydrates;
    @SerializedName("CarbohydratesDV")
    private double carbohydratesDV;
    @SerializedName("Cholesterol")
    private double cholesterol;
    @SerializedName("CholesterolDV")
    private double cholesterolDV;
    @SerializedName("Dietaryfiber")
    private double dietaryfiber;
    @SerializedName("DietaryfiberDV")
    private double dietaryfiberDV;
    @SerializedName("Energy")
    private double energy;
    @SerializedName("Ingredients")
    private String ingredients;
    @SerializedName("Iron")
    private double iron;
    @SerializedName("KCal")
    private double kCal;
    @SerializedName("Name")
    private String name;
    @SerializedName("Protein")
    private double protein;
    @SerializedName("ProteinDV")
    private double proteinDV;
    @SerializedName("SaturatedFat")
    private double saturatedFat;
    @SerializedName("SaturatedFatDV")
    private double saturatedFatDV;
    @SerializedName("Serving")
    private String serving;
    @SerializedName("Sodium")
    private double sodium;
    @SerializedName("SodiumDV")
    private double sodiumDV;
    @SerializedName("SpecialInfo")
    private String specialInfo;
    @SerializedName("Sugars")
    private double sugars;
    @SerializedName("Totalfat")
    private double totalfat;
    @SerializedName("TotalfatDV")
    private double totalfatDV;
    @SerializedName("Transfat")
    private double transfat;
    @SerializedName("Vitamina")
    private double vitamina;
    @SerializedName("Vitaminc")
    private double vitaminc;

    private void buildNutrientLists() {
        this.allNutrients = new ArrayList();
        List<String> notANutrient = Arrays.asList(new String[]{"Allergenes", "Ingredients", "Name", "Serving", "SpecialInfo"});
        for (Method method : MWNutrition.class.getMethods()) {
            if (method.getDeclaringClass() == MWNutrition.class && method.getName().substring(0, 3).equals("get")) {
                String propName = method.getName().substring(3);
                if (!notANutrient.contains(propName)) {
                    Nutrient nutrient = new Nutrient();
                    nutrient.setName(propName);
                    try {
                        nutrient.setValue(String.valueOf(method.invoke(this, new Object[0])));
                    } catch (Exception ignore) {
                        ignore.printStackTrace();
                        nutrient.setValue("0");
                    }
                    this.allNutrients.add(nutrient);
                }
            }
        }
    }

    public List<Nutrient> allNutrients() {
        if (this.allNutrients == null) {
            buildNutrientLists();
        }
        return this.allNutrients;
    }

    public String getAllergenes() {
        return this.allergenes;
    }

    public void setAllergenes(String allergenes) {
        this.allergenes = allergenes;
    }

    public double getCalcium() {
        return this.calcium;
    }

    public void setCalcium(double calcium) {
        this.calcium = calcium;
    }

    public double getCaloriesfromfat() {
        return this.caloriesfromfat;
    }

    public void setCaloriesfromfat(double caloriesfromfat) {
        this.caloriesfromfat = caloriesfromfat;
    }

    public double getCarbohydrates() {
        return this.carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getCarbohydratesDV() {
        return this.carbohydratesDV;
    }

    public void setCarbohydratesDV(double carbohydratesDV) {
        this.carbohydratesDV = carbohydratesDV;
    }

    public double getCholesterol() {
        return this.cholesterol;
    }

    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public double getCholesterolDV() {
        return this.cholesterolDV;
    }

    public void setCholesterolDV(double cholesterolDV) {
        this.cholesterolDV = cholesterolDV;
    }

    public double getDietaryfiber() {
        return this.dietaryfiber;
    }

    public void setDietaryfiber(double dietaryfiber) {
        this.dietaryfiber = dietaryfiber;
    }

    public double getDietaryfiberDV() {
        return this.dietaryfiberDV;
    }

    public void setDietaryfiberDV(double dietaryfiberDV) {
        this.dietaryfiberDV = dietaryfiberDV;
    }

    public double getEnergy() {
        return this.energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getkCal() {
        return this.kCal;
    }

    public void setkCal(double kCal) {
        this.kCal = kCal;
    }

    public String getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public double getIron() {
        return this.iron;
    }

    public void setIron(double iron) {
        this.iron = iron;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProtein() {
        return this.protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getProteinDV() {
        return this.proteinDV;
    }

    public void setProteinDV(double proteinDV) {
        this.proteinDV = proteinDV;
    }

    public double getSaturatedFat() {
        return this.saturatedFat;
    }

    public void setSaturatedFat(double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public double getSaturatedFatDV() {
        return this.saturatedFatDV;
    }

    public void setSaturatedFatDV(double saturatedFatDV) {
        this.saturatedFatDV = saturatedFatDV;
    }

    public String getServing() {
        return this.serving;
    }

    public void setServing(String serving) {
        this.serving = serving;
    }

    public double getSodium() {
        return this.sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getSodiumDV() {
        return this.sodiumDV;
    }

    public void setSodiumDV(double sodiumDV) {
        this.sodiumDV = sodiumDV;
    }

    public String getSpecialInfo() {
        return this.specialInfo;
    }

    public void setSpecialInfo(String specialInfo) {
        this.specialInfo = specialInfo;
    }

    public double getSugars() {
        return this.sugars;
    }

    public void setSugars(double sugars) {
        this.sugars = sugars;
    }

    public double getTotalfat() {
        return this.totalfat;
    }

    public void setTotalfat(double totalfat) {
        this.totalfat = totalfat;
    }

    public double getTotalfatDV() {
        return this.totalfatDV;
    }

    public void setTotalfatDV(double totalfatDV) {
        this.totalfatDV = totalfatDV;
    }

    public double getTransfat() {
        return this.transfat;
    }

    public void setTransfat(double transfat) {
        this.transfat = transfat;
    }

    public double getVitamina() {
        return this.vitamina;
    }

    public void setVitamina(double vitamina) {
        this.vitamina = vitamina;
    }

    public double getVitaminc() {
        return this.vitaminc;
    }

    public void setVitaminc(double vitaminc) {
        this.vitaminc = vitaminc;
    }
}
