package com.mcdonalds.app.util;

import com.ensighten.Ensighten;
import com.mcdonalds.sdk.modules.models.Choice;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.List;

public class OrderProductUtils {
    public static String getTotalEnergyUnit(OrderProduct product) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.OrderProductUtils", "getTotalEnergyUnit", new Object[]{product});
        String unit = product.getTotalEnergyUnit();
        if (unit != null) {
            return unit;
        }
        if (Configuration.getSharedInstance().hasKey("interface.nutritionalInfo.energyUnit")) {
            return Configuration.getSharedInstance().getLocalizedStringForKey("interface.nutritionalInfo.energyUnit");
        }
        if (Configuration.getSharedInstance().hasKey("interface.nutritionalInfo.secondaryEnergyUnit")) {
            return Configuration.getSharedInstance().getLocalizedStringForKey("interface.nutritionalInfo.secondaryEnergyUnit");
        }
        return "";
    }

    public static String getCustomizationsString(OrderProduct product) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.OrderProductUtils", "getCustomizationsString", new Object[]{product});
        if (product == null || (Configuration.getSharedInstance().hasKey("interface.hideProductCustomizationButton") && Configuration.getSharedInstance().getBooleanForKey("interface.hideProductCustomizationButton"))) {
            return "";
        }
        return product.getCustomizationsString();
    }

    public static StringBuilder getIngredientChoiceString(OrderProduct product) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.OrderProductUtils", "getIngredientChoiceString", new Object[]{product});
        StringBuilder builder = new StringBuilder("");
        if (!(product == null || ListUtils.isEmpty(product.getRealChoices()))) {
            for (Choice choice : product.getRealChoices()) {
                if (choice != null) {
                    OrderProduct choiceSolution = OrderProduct.getChoiceWithinChoiceProduct(choice.getSelection());
                    if (choiceSolution != null) {
                        builder.append(choiceSolution.getProduct().getLongName() + ", ");
                    }
                }
            }
            if (builder.length() > ", ".length()) {
                builder.deleteCharAt(builder.length() - ", ".length());
            }
        }
        return builder;
    }

    public static List<OrderProduct> getAllChoices(OrderProduct input) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.OrderProductUtils", "getAllChoices", new Object[]{input});
        ArrayList<OrderProduct> result = new ArrayList();
        getAllChoices(input, result);
        return result;
    }

    private static void getAllChoices(OrderProduct input, List<OrderProduct> result) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.OrderProductUtils", "getAllChoices", new Object[]{input, result});
        if (input != null && input.getRealChoices() != null) {
            for (OrderProduct choice : input.getRealChoices()) {
                if (choice instanceof Choice) {
                    OrderProduct solution = ((Choice) choice).getSelection();
                    if (solution != null) {
                        result.add(solution);
                        getAllChoices(solution, result);
                    }
                }
            }
        }
    }
}
