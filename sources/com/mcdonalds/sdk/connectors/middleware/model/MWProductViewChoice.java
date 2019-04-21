package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Choice;
import com.mcdonalds.sdk.modules.models.CustomerOrderProduct;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import java.util.Collections;

public class MWProductViewChoice extends MWProductViewBase {
    @SerializedName("ChoiceSolution")
    public MWProductView choiceSolution;

    public CustomerOrderProduct toCustomerOrderProduct() {
        CustomerOrderProduct ret = super.toCustomerOrderProduct();
        ret.setQuantity(Integer.valueOf(1));
        if (this.choiceSolution != null) {
            CustomerOrderProduct solution = this.choiceSolution.toCustomerOrderProduct();
            solution.setQuantity(Integer.valueOf(1));
            ret.setChoiceSolution(solution);
        }
        return ret;
    }

    public MWProductViewChoice populateWithChoice(Choice choice) {
        super.populateWithOrder(choice);
        OrderProduct selection = choice.getSelection();
        if (selection instanceof Choice) {
            Choice category = (Choice) selection;
            MWProductViewChoice mwProductViewChoice = new MWProductViewChoice();
            mwProductViewChoice.populateWithChoice(category);
            this.choiceSolution = getChoiceWithinChoice(mwProductViewChoice);
        } else {
            MWProductView solutionView = new MWProductView();
            solutionView.populateWithOrder(choice.getSelection());
            this.choiceSolution = solutionView;
        }
        return this;
    }

    @Deprecated
    public MWProductViewChoice populateWithChoiceAndSolution(OrderProduct choiceProduct, OrderProduct choiceSolutionProduct) {
        super.populateWithOrder(choiceProduct);
        MWProductView solutionView = new MWProductView();
        solutionView.populateWithOrder(choiceSolutionProduct);
        this.choiceSolution = solutionView;
        return this;
    }

    @Deprecated
    public MWProductView getChoiceSolution() {
        return this.choiceSolution;
    }

    @Deprecated
    public void setChoiceSolution(MWProductView choiceSolution) {
        this.choiceSolution = choiceSolution;
    }

    private static MWProductView getChoiceWithinChoice(MWProductViewChoice choice) {
        MWProductView zeroedOrderProduct = new MWProductView();
        zeroedOrderProduct.setProductCode(0);
        zeroedOrderProduct.setQuantity(0);
        zeroedOrderProduct.setChoices(Collections.singletonList(choice));
        return zeroedOrderProduct;
    }
}
