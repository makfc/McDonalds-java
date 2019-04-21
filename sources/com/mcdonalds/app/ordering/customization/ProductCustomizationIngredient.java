package com.mcdonalds.app.ordering.customization;

import com.ensighten.Ensighten;
import com.mcdonalds.sdk.modules.models.Ingredient;
import com.mcdonalds.sdk.modules.models.OrderProduct;

public class ProductCustomizationIngredient extends ProductCustomizationItem {
    public ProductCustomizationIngredient(Ingredient ingredient, OrderProduct product) {
        super(ingredient, product, 0);
    }

    /* Access modifiers changed, original: protected */
    public void incrementPortion() {
        Ensighten.evaluateEvent(this, "incrementPortion", null);
        this.portionQuantity = PortionQuantity.values()[this.portionQuantity.ordinal() + 1];
    }

    /* Access modifiers changed, original: protected */
    public void decrementPortion() {
        Ensighten.evaluateEvent(this, "decrementPortion", null);
        this.portionQuantity = PortionQuantity.values()[this.portionQuantity.ordinal() - 1];
    }
}
