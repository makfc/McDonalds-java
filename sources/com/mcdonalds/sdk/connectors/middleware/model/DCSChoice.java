package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Choice;
import com.mcdonalds.sdk.modules.models.CustomerOrderProduct;
import com.mcdonalds.sdk.modules.models.OrderProduct;

public class DCSChoice extends DCSProductBase {
    @SerializedName("ChoiceSolution")
    public DCSProduct choiceSolution;

    public static DCSChoice fromCustomerOrderProduct(CustomerOrderProduct customerOrderProduct) {
        DCSChoice dcsChoice = new DCSChoice();
        dcsChoice.populate(customerOrderProduct);
        CustomerOrderProduct choiceSolution = customerOrderProduct.getChoiceSolution();
        if (choiceSolution != null) {
            dcsChoice.choiceSolution = DCSProduct.fromCustomerOrderProduct(choiceSolution);
        }
        return dcsChoice;
    }

    public CustomerOrderProduct toCustomerOrderProduct() {
        CustomerOrderProduct customerOrderProduct = super.toCustomerOrderProduct();
        if (this.choiceSolution != null) {
            customerOrderProduct.setChoiceSolution(this.choiceSolution.toCustomerOrderProduct());
        }
        return customerOrderProduct;
    }

    public Choice toChoice() {
        Choice choice = new Choice();
        choice.setProductCode(String.valueOf(this.productCode));
        choice.setQuantity(this.quantity);
        if (this.choiceSolution != null) {
            choice.setSelection(this.choiceSolution.toOrderProduct());
        }
        return choice;
    }

    public static DCSChoice fromChoice(Choice choice) {
        DCSChoice dcsChoice = new DCSChoice();
        dcsChoice.populate((OrderProduct) choice);
        OrderProduct choiceSolution = choice.getSelection();
        if (choiceSolution != null) {
            dcsChoice.choiceSolution = DCSProduct.fromOrderProduct(choiceSolution);
        }
        return dcsChoice;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        DCSChoice dcsChoice = (DCSChoice) o;
        if (this.choiceSolution != null) {
            return this.choiceSolution.equals(dcsChoice.choiceSolution);
        }
        if (dcsChoice.choiceSolution != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (super.hashCode() * 31) + (this.choiceSolution != null ? this.choiceSolution.hashCode() : 0);
    }
}
