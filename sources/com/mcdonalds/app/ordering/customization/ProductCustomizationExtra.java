package com.mcdonalds.app.ordering.customization;

import com.ensighten.Ensighten;
import com.mcdonalds.sdk.modules.models.Ingredient;
import com.mcdonalds.sdk.modules.models.OrderProduct;

public class ProductCustomizationExtra extends ProductCustomizationItem {
    public ProductCustomizationExtra(Ingredient ingredient, OrderProduct product) {
        super(ingredient, product, 1);
    }

    /* Access modifiers changed, original: protected */
    public void incrementPortion() {
        Ensighten.evaluateEvent(this, "incrementPortion", null);
        if (this.portionQuantity == PortionQuantity.LIGHT) {
            this.portionQuantity = PortionQuantity.EXTRA;
        } else {
            this.portionQuantity = PortionQuantity.values()[this.portionQuantity.ordinal() + 1];
        }
    }

    /* Access modifiers changed, original: protected */
    public void decrementPortion() {
        Ensighten.evaluateEvent(this, "decrementPortion", null);
        if (this.portionQuantity == PortionQuantity.EXTRA) {
            this.portionQuantity = PortionQuantity.LIGHT;
        } else {
            this.portionQuantity = PortionQuantity.values()[this.portionQuantity.ordinal() - 1];
        }
    }
}
