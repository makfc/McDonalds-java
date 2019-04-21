package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import java.util.ArrayList;
import java.util.List;

public class MWProductViewInput extends MWProductViewBaseInput {
    @SerializedName("Choices")
    public List<MWProductViewChoiceInput> choices;
    @SerializedName("Components")
    public List<MWProductViewInput> components;
    @SerializedName("Customizations")
    public List<MWProductViewCustomizationInput> customizations;

    public MWProductViewBaseInput populateWithOrder(OrderProduct orderProduct) {
        if (super.populateWithOrder(orderProduct) == null) {
            return null;
        }
        this.components = new ArrayList();
        if (orderProduct.isMeal()) {
            this.components = new ArrayList();
            if (orderProduct.getIngredients() != null && orderProduct.getIngredients().size() > 0) {
                for (OrderProduct componentProduct : orderProduct.getIngredients()) {
                    MWProductViewInput component = new MWProductViewInput();
                    component.populateWithOrder(componentProduct);
                    this.components.add(component);
                }
            }
        }
        this.choices = new ArrayList();
        if (orderProduct.getChoiceSolutions() != null && orderProduct.getChoiceSolutions().size() > 0) {
            for (int i = 0; i < orderProduct.getChoiceSolutions().size(); i++) {
                int choiceIdx = orderProduct.getChoiceSolutions().keyAt(i);
                OrderProduct choiceProduct = (OrderProduct) orderProduct.getChoices().get(choiceIdx);
                OrderProduct solutionProduct = (OrderProduct) orderProduct.getChoiceSolutions().get(choiceIdx);
                MWProductViewChoiceInput choice = new MWProductViewChoiceInput();
                choice.populateWithChoiceAndSolution(choiceProduct, solutionProduct);
                this.choices.add(choice);
            }
        }
        this.customizations = new ArrayList();
        if (orderProduct.getCustomizations() == null || orderProduct.getCustomizations().size() <= 0) {
            return this;
        }
        for (OrderProduct customizationProduct : orderProduct.getCustomizations().values()) {
            MWProductViewCustomizationInput customization = new MWProductViewCustomizationInput();
            customization.populateWithOrder(customizationProduct);
            this.customizations.add(customization);
        }
        return this;
    }
}
