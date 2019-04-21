package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.OrderProduct;

public class MWProductViewChoiceInput extends MWProductViewBaseInput {
    @SerializedName("ChoiceSolution")
    public MWProductViewInput choiceSolution;

    public MWProductViewChoiceInput populateWithChoiceAndSolution(OrderProduct choiceProduct, OrderProduct choiceSolutionProduct) {
        super.populateWithOrder(choiceProduct);
        MWProductViewInput solutionView = new MWProductViewInput();
        solutionView.populateWithOrder(choiceSolutionProduct);
        this.choiceSolution = solutionView;
        return this;
    }
}
