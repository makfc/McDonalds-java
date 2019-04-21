package com.mcdonalds.app.util;

import com.ensighten.Ensighten;
import com.mcdonalds.sdk.modules.models.Nutrient;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderOfferProduct;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class OrderOfferUtils {
    public static String getTotalEnergyUnit(OrderOffer orderOffer) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.OrderOfferUtils", "getTotalEnergyUnit", new Object[]{orderOffer});
        String unit = null;
        for (OrderOfferProduct orderOfferProduct : orderOffer.getOrderOfferProducts()) {
            if (orderOfferProduct.getSelectedProductOption() != null) {
                Nutrient energyNutrient = orderOfferProduct.getSelectedProductOption().getProduct().getEnergyNutrient();
                if (energyNutrient != null) {
                    unit = energyNutrient.getUnit();
                    break;
                }
            }
        }
        if (unit == null) {
            unit = Configuration.getSharedInstance().getLocalizedStringForKey("interface.nutritionalInfo.energyUnit");
        }
        if (unit == null) {
            return "";
        }
        return unit;
    }
}
