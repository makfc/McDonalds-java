package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Choice;
import com.mcdonalds.sdk.modules.models.CustomerOrderProduct;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.List;

public class DCSProduct extends DCSProductBase {
    @SerializedName("Choices")
    public List<DCSChoice> choices;
    @SerializedName("Components")
    public List<DCSProduct> components;
    @SerializedName("Customizations")
    public List<DCSCustomization> customizations;
    @SerializedName("PromoQuantity")
    public int promoQuantity;

    public static DCSProduct fromCustomerOrderProduct(CustomerOrderProduct customerOrderProduct) {
        DCSProduct dcsProduct = new DCSProduct();
        dcsProduct.populate(customerOrderProduct);
        dcsProduct.promoQuantity = customerOrderProduct.getPromoQuantity().intValue();
        List<CustomerOrderProduct> components = customerOrderProduct.getComponents();
        dcsProduct.components = new ArrayList();
        if (!ListUtils.isEmpty(components)) {
            for (CustomerOrderProduct component : components) {
                dcsProduct.components.add(fromCustomerOrderProduct(component));
            }
        }
        List<CustomerOrderProduct> choices = customerOrderProduct.getChoices();
        dcsProduct.choices = new ArrayList();
        if (!ListUtils.isEmpty(choices)) {
            for (CustomerOrderProduct choice : choices) {
                dcsProduct.choices.add(DCSChoice.fromCustomerOrderProduct(choice));
            }
        }
        List<CustomerOrderProduct> customizations = customerOrderProduct.getCustomizations();
        dcsProduct.customizations = new ArrayList();
        if (!ListUtils.isEmpty(customizations)) {
            for (CustomerOrderProduct customization : customizations) {
                dcsProduct.customizations.add(DCSCustomization.fromCustomerOrderProduct(customization));
            }
        }
        return dcsProduct;
    }

    public CustomerOrderProduct toCustomerOrderProduct() {
        CustomerOrderProduct customerOrderProduct = super.toCustomerOrderProduct();
        List<CustomerOrderProduct> customerOrderProductChoices = new ArrayList();
        if (!ListUtils.isEmpty(this.choices)) {
            for (DCSChoice dcsChoice : this.choices) {
                customerOrderProductChoices.add(dcsChoice.toCustomerOrderProduct());
            }
        }
        customerOrderProduct.setChoices(customerOrderProductChoices);
        List<CustomerOrderProduct> customerOrderProductComponents = new ArrayList();
        if (!ListUtils.isEmpty(this.components)) {
            for (DCSProduct dcsComponent : this.components) {
                customerOrderProductComponents.add(dcsComponent.toCustomerOrderProduct());
            }
        }
        customerOrderProduct.setComponents(customerOrderProductComponents);
        List<CustomerOrderProduct> customerOrderProductCustomizations = new ArrayList();
        if (!ListUtils.isEmpty(this.customizations)) {
            for (DCSCustomization dcsCustomization : this.customizations) {
                customerOrderProductCustomizations.add(dcsCustomization.toCustomerOrderProduct());
            }
        }
        customerOrderProduct.setCustomizations(customerOrderProductCustomizations);
        customerOrderProduct.setPromoQuantity(Integer.valueOf(this.promoQuantity));
        return customerOrderProduct;
    }

    public static DCSProduct fromOrderProduct(OrderProduct orderProduct) {
        DCSProduct dcsProduct = new DCSProduct();
        dcsProduct.populate(orderProduct);
        dcsProduct.processMealIngredients(orderProduct);
        dcsProduct.processChoices(orderProduct);
        dcsProduct.processCustomizations(orderProduct);
        return dcsProduct;
    }

    public OrderProduct toOrderProduct() {
        OrderProduct orderProduct = super.toOrderProduct();
        if (!ListUtils.isEmpty(this.components)) {
            for (DCSProduct component : this.components) {
                orderProduct.addIngredient(component.toOrderProduct());
            }
        }
        if (!ListUtils.isEmpty(this.choices)) {
            for (DCSChoice choice : this.choices) {
                orderProduct.addChoice(choice.toChoice());
            }
        }
        if (!ListUtils.isEmpty(this.customizations)) {
            for (DCSCustomization customization : this.customizations) {
                orderProduct.addCustomization(Integer.valueOf(customization.productCode), customization.toOrderProduct());
            }
        }
        return orderProduct;
    }

    private void processMealIngredients(OrderProduct orderProduct) {
        this.components = new ArrayList();
        if (orderProduct.isMeal()) {
            List<OrderProduct> ingredients = orderProduct.getIngredients();
            if (ingredients != null) {
                for (OrderProduct ingredient : ingredients) {
                    this.components.add(fromOrderProduct(ingredient));
                }
            }
        }
    }

    private void processChoices(OrderProduct orderProduct) {
        this.choices = new ArrayList();
        List<Choice> orderProductChoices = orderProduct.getRealChoices();
        if (!ListUtils.isEmpty(orderProductChoices)) {
            for (Choice choice : orderProductChoices) {
                this.choices.add(DCSChoice.fromChoice(choice));
            }
        }
    }

    private void processCustomizations(OrderProduct orderProduct) {
        this.customizations = new ArrayList();
        if (orderProduct.getCustomizations() != null) {
            for (OrderProduct customizationProduct : orderProduct.getCustomizations().values()) {
                this.customizations.add(DCSCustomization.fromOrderProduct(customizationProduct));
            }
        }
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass() || !super.equals(o)) {
            return false;
        }
        DCSProduct that = (DCSProduct) o;
        if (this.promoQuantity != that.promoQuantity) {
            return false;
        }
        if (this.choices != null) {
            if (!this.choices.equals(that.choices)) {
                return false;
            }
        } else if (that.choices != null) {
            return false;
        }
        if (this.components != null) {
            if (!this.components.equals(that.components)) {
                return false;
            }
        } else if (that.components != null) {
            return false;
        }
        if (this.customizations != null) {
            z = this.customizations.equals(that.customizations);
        } else if (that.customizations != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = super.hashCode() * 31;
        if (this.choices != null) {
            hashCode = this.choices.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode2 + hashCode) * 31;
        if (this.components != null) {
            hashCode = this.components.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode2 + hashCode) * 31;
        if (this.customizations != null) {
            i = this.customizations.hashCode();
        }
        return ((hashCode + i) * 31) + this.promoQuantity;
    }
}
