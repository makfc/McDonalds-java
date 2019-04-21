package com.mcdonalds.sdk.connectors.middleware.model;

import android.support.annotation.Nullable;
import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.connectors.utils.SerializableSparseArray;
import com.mcdonalds.sdk.modules.models.Facility;
import com.mcdonalds.sdk.modules.models.Ingredient;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.models.Product.ProductType;
import com.mcdonalds.sdk.modules.models.ProductDimension;
import com.mcdonalds.sdk.modules.models.Promotion;
import com.mcdonalds.sdk.modules.models.StoreCatalog;
import com.mcdonalds.sdk.utils.ListUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MWStore implements Serializable {
    @SerializedName("Availability")
    public List<MWAvailability> availability;
    @SerializedName("AvailibilityVersion")
    public String availibilityVersion;
    @SerializedName("Facility")
    public List<MWFacility> facilities;
    @SerializedName("FacilityVersion")
    public String facilityVersion;
    private SerializableSparseArray<MWMenuType> mMWMenuTypeMap;
    private SerializableSparseArray<MWNameInfo> mMWNameInfoMap;
    private SerializableSparseArray<MWProduct> mMWProductMap;
    private SerializableSparseArray<MWProductPrice> mMWProductPriceMap;
    SerializableSparseArray<MWRecipe> mMWRecipeMap;
    private SerializableSparseArray<Product> mProductMap;
    @SerializedName("ProductPriceVersion")
    public String productPriceVersion;
    @SerializedName("ProductPrice")
    public List<MWProductPrice> productPrices;
    @SerializedName("ProductVersion")
    public String productVersion;
    @SerializedName("Products")
    public List<MWProduct> products;
    @SerializedName("PromotionVersion")
    public String promotionVersion;
    @SerializedName("Promotions")
    public List<MWPromotion> promotions;
    @SerializedName("RecipePriceVersion")
    public String recipePriceVersion;
    @SerializedName("RecipePrice")
    public List<MWRecipePrice> recipePrices;
    @SerializedName("Store")
    public int store;

    public StoreCatalog toStoreCatalog(MWMarket market) {
        StoreCatalog storeCatalog = new StoreCatalog();
        storeCatalog.setStoreId(this.store);
        storeCatalog.setVersion(this.productVersion);
        if (market != null) {
            List<Promotion> appPromotions = new ArrayList();
            if (this.promotions != null) {
                for (MWPromotion mwPromotion : this.promotions) {
                    appPromotions.add(mwPromotion.toPromotion());
                }
            }
            storeCatalog.setProductsVersion(this.promotionVersion);
            storeCatalog.setPromotions(appPromotions);
            List<Facility> appFacilities = new ArrayList();
            if (this.facilities != null) {
                for (MWFacility mwFacility : this.facilities) {
                    appFacilities.add(mwFacility.toFacility());
                }
            }
            storeCatalog.setFacilitiesVersion(this.facilityVersion);
            storeCatalog.setFacilities(appFacilities);
            this.mMWProductPriceMap = new SerializableSparseArray();
            if (this.productPrices != null) {
                for (MWProductPrice mwProductPrice : this.productPrices) {
                    this.mMWProductPriceMap.put(mwProductPrice.productCode, mwProductPrice);
                }
            }
            if (market != null) {
                this.mMWRecipeMap = new SerializableSparseArray();
                if (market.recipes != null) {
                    for (MWRecipe mwRecipe : market.recipes) {
                        this.mMWRecipeMap.put(mwRecipe.recipeID.intValue(), mwRecipe);
                    }
                }
                this.mMWMenuTypeMap = new SerializableSparseArray();
                if (market.menuTypes != null) {
                    for (MWMenuType mwMenuType : market.menuTypes) {
                        this.mMWMenuTypeMap.put(mwMenuType.menuTypeID, mwMenuType);
                    }
                }
                this.mMWNameInfoMap = new SerializableSparseArray();
                if (market.names != null) {
                    for (MWNameInfo mwNameInfo : market.names) {
                        this.mMWNameInfoMap.put(mwNameInfo.productCode, mwNameInfo);
                    }
                }
            }
            List<Product> appProducts = new ArrayList();
            this.mProductMap = new SerializableSparseArray();
            this.mMWProductMap = new SerializableSparseArray();
            if (this.products != null) {
                MWProduct mwProduct;
                int i;
                Product appProduct;
                for (MWProduct mwProduct2 : this.products) {
                    this.mMWProductMap.put(mwProduct2.productCode, mwProduct2);
                }
                for (i = 0; i < this.products.size(); i++) {
                    appProduct = getProduct((MWProduct) this.products.get(i));
                    appProduct.setDisplayOrder(Integer.valueOf(i));
                    appProducts.add(appProduct);
                }
                for (i = 0; i < this.products.size(); i++) {
                    mwProduct2 = (MWProduct) this.products.get(i);
                    appProduct = getProduct(mwProduct2);
                    if (mwProduct2.dimensions != null) {
                        appProduct.setDimensions(processDimensions(mwProduct2.dimensions));
                    }
                }
            }
            storeCatalog.setProductsVersion(this.productVersion);
            storeCatalog.setProducts(appProducts);
            storeCatalog.setProductPricesVersion(this.productPriceVersion);
        }
        return storeCatalog;
    }

    private Product getProduct(MWProduct mwProduct) {
        Product appProduct = (Product) this.mProductMap.get(mwProduct.productCode);
        if (appProduct != null) {
            return appProduct;
        }
        appProduct = mwProduct.getProduct(this.mMWMenuTypeMap, this.mMWProductPriceMap, this.mMWNameInfoMap);
        this.mProductMap.put(mwProduct.productCode, appProduct);
        if (!(mwProduct.recipe != null || mwProduct.recipeID == null || this.mMWRecipeMap == null)) {
            mwProduct.recipe = (MWRecipe) this.mMWRecipeMap.get(mwProduct.recipeID.intValue());
        }
        if (mwProduct.recipe != null) {
            if (mwProduct.recipe.ingredients != null) {
                appProduct.setIngredients(processIngredients(mwProduct.recipe.ingredients));
            }
            if (mwProduct.recipe.extras != null) {
                appProduct.setExtras(processIngredients(mwProduct.recipe.extras));
            }
            if (mwProduct.recipe.choices != null) {
                appProduct.setChoices(processIngredients(mwProduct.recipe.choices));
            }
        }
        appProduct.setSingleChoice(Boolean.valueOf(isSingleChoice(appProduct)));
        appProduct.setHasChoice(Boolean.valueOf(hasChoice(appProduct)));
        appProduct.setHasNonSingleChoiceChoice(Boolean.valueOf(hasNonSingleChoiceChoice(appProduct)));
        return appProduct;
    }

    private boolean isSingleChoice(Product product) {
        List<Ingredient> ingredients = product.getIngredients();
        List<Ingredient> choices = product.getChoices();
        ProductType type = product.getProductType();
        if (type == null || !type.equals(ProductType.Choice)) {
            return false;
        }
        if (ListUtils.isEmpty(choices) && ingredients != null && ingredients.size() == 1) {
            return true;
        }
        if (ListUtils.isEmpty(ingredients) && choices != null && choices.size() == 1 && isSingleChoice(((Ingredient) choices.get(0)).getProduct())) {
            return true;
        }
        return false;
    }

    private boolean hasChoice(Product product) {
        if (ListUtils.isEmpty(product.getChoices())) {
            return false;
        }
        return true;
    }

    private boolean hasNonSingleChoiceChoice(Product product) {
        if (!product.hasChoice().booleanValue()) {
            return false;
        }
        for (Ingredient choice : product.getChoices()) {
            boolean isSingleChoice;
            if (choice.getProduct().isSingleChoice() == null) {
                isSingleChoice = false;
                continue;
            } else {
                isSingleChoice = choice.getProduct().isSingleChoice().booleanValue();
                continue;
            }
            if (!isSingleChoice) {
                return true;
            }
        }
        return false;
    }

    private List<ProductDimension> processDimensions(List<MWDimension> mwDimensions) {
        ArrayList<ProductDimension> appDimensions = new ArrayList();
        if (mwDimensions != null) {
            for (int i = 0; i < mwDimensions.size(); i++) {
                MWDimension mwDimension = (MWDimension) mwDimensions.get(i);
                ProductDimension appDimension = mwDimension.toDimension();
                appDimension.setDisplayOrder(i);
                Product appProduct = getRecipeFromProductCode(mwDimension.productCode);
                if (appProduct != null) {
                    appDimension.setProduct(appProduct);
                    appDimensions.add(appDimension);
                }
            }
        }
        return appDimensions;
    }

    private List<Ingredient> processIngredients(List<MWIngredient> mwIngredients) {
        ArrayList<Ingredient> appIngredients = new ArrayList();
        if (mwIngredients != null) {
            for (int i = 0; i < mwIngredients.size(); i++) {
                MWIngredient mwIngredient = (MWIngredient) mwIngredients.get(i);
                Ingredient appIngredient = mwIngredient.toIngredient();
                appIngredient.setDisplayOrder(i);
                Product appProduct = getRecipeFromProductCode(Integer.valueOf(mwIngredient.productCode));
                if (appProduct != null) {
                    appIngredient.setProduct(appProduct);
                    appIngredients.add(appIngredient);
                    if (shouldSetBasePrices(appIngredient)) {
                        setChoiceBasePrices(appIngredient);
                    }
                }
            }
        }
        return appIngredients;
    }

    private void setChoiceBasePrices(Ingredient choice) {
        Product product = choice.getProduct();
        int referencePriceProductCode = choice.getReferencePriceProductCode();
        List<Ingredient> ingredients = new ArrayList();
        if (!ListUtils.isEmpty(product.getChoices())) {
            ingredients.addAll(product.getChoices());
        }
        if (!ListUtils.isEmpty(product.getIngredients())) {
            ingredients.addAll(product.getIngredients());
        }
        if (!ListUtils.isEmpty(ingredients)) {
            Ingredient ingredient = (Ingredient) ingredients.get(0);
            for (int i = 1; i < ingredients.size(); i++) {
                Ingredient tmpIngredient = (Ingredient) ingredients.get(i);
                if (tmpIngredient.getProduct().getExternalId().equals(Integer.valueOf(referencePriceProductCode))) {
                    ingredient = tmpIngredient;
                }
            }
            if (!ingredient.getCostInclusive()) {
                double priceEatIn;
                double priceTakeOut;
                double priceDelivery;
                if (shouldSetBasePrices(ingredient)) {
                    setChoiceBasePrices(ingredient);
                    priceEatIn = ingredient.getProduct().getBasePriceEatIn();
                    priceTakeOut = ingredient.getProduct().getBasePriceTakeOut();
                    priceDelivery = ingredient.getProduct().getBasePriceDelivery();
                } else {
                    priceEatIn = ingredient.getProduct().getPriceEatIn();
                    priceTakeOut = ingredient.getProduct().getPriceTakeOut();
                    priceDelivery = ingredient.getProduct().getPriceDelivery();
                }
                product.setBasePriceEatIn(priceEatIn);
                product.setBasePriceTakeOut(priceTakeOut);
                product.setBasePriceDelivery(priceDelivery);
            }
        }
    }

    private boolean shouldSetBasePrices(Ingredient ingredient) {
        return ProductType.Choice.equals(ingredient.getProduct().getProductType());
    }

    @Nullable
    private Product getRecipeFromProductCode(Integer productCode) {
        MWProduct mwProduct = (MWProduct) this.mMWProductMap.get(productCode.intValue());
        if (mwProduct == null) {
            return null;
        }
        return getProduct(mwProduct);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this.store != ((MWStore) o).store) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.store;
    }
}
