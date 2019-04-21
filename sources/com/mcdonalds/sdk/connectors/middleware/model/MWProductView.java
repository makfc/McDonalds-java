package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Choice;
import com.mcdonalds.sdk.modules.models.CustomerOrderProduct;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.ProductView;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.List;

public class MWProductView extends MWProductViewBase {
    @SerializedName("Choices")
    public List<MWProductViewChoice> choices;
    @SerializedName("Components")
    public List<MWProductViewComponent> components;
    @SerializedName("Customizations")
    public List<MWProductViewCustomization> customizations;

    public CustomerOrderProduct toCustomerOrderProduct() {
        int size;
        int i;
        CustomerOrderProduct ret = super.toCustomerOrderProduct();
        List<CustomerOrderProduct> components = new ArrayList();
        if (this.components != null) {
            size = this.components.size();
            for (i = 0; i < size; i++) {
                components.add(((MWProductViewComponent) this.components.get(i)).toCustomerOrderProduct());
            }
        }
        ret.setComponents(components);
        List<CustomerOrderProduct> appChoices = new ArrayList();
        if (this.choices != null) {
            for (MWProductViewChoice choice : this.choices) {
                for (i = 0; i < choice.quantity; i++) {
                    appChoices.add(choice.toCustomerOrderProduct());
                }
            }
        }
        ret.setChoices(appChoices);
        List<CustomerOrderProduct> customizations = new ArrayList();
        if (this.customizations != null) {
            size = this.customizations.size();
            for (i = 0; i < size; i++) {
                customizations.add(((MWProductViewCustomization) this.customizations.get(i)).toCustomerOrderProduct());
            }
        }
        ret.setCustomizations(customizations);
        ret.setQuantity(Integer.valueOf(this.quantity));
        return ret;
    }

    public static MWProductView fromOrderProduct(OrderProduct orderProduct) {
        return (MWProductView) new MWProductView().populateWithOrder(orderProduct);
    }

    public MWProductViewBase populateWithOrder(OrderProduct orderProduct) {
        if (super.populateWithOrder(orderProduct) == null) {
            return null;
        }
        processMealIngredients(orderProduct);
        processChoices(orderProduct);
        processCustomizations(orderProduct);
        return this;
    }

    public static ProductView toProductView(MWProductView mwProductView) {
        ProductView productView = new ProductView();
        productView.setProductCode(Integer.valueOf(mwProductView.productCode));
        productView.setValidationErrorCode(Integer.valueOf(mwProductView.validationErrorCode));
        productView.setTotalValue(Double.valueOf(mwProductView.totalValue));
        productView.setUnitPrice(Double.valueOf(mwProductView.unitPrice));
        if (!ListUtils.isEmpty(mwProductView.components)) {
            ArrayList<ProductView> tempComponentList = new ArrayList();
            for (MWProductViewComponent mwProductViewComponent : mwProductView.components) {
                tempComponentList.add(toProductView(mwProductViewComponent));
            }
            productView.setComponents(tempComponentList);
        }
        if (!ListUtils.isEmpty(mwProductView.choices)) {
            ArrayList<ProductView> tempChoiceList = new ArrayList();
            for (MWProductViewChoice mwProductViewChoice : mwProductView.choices) {
                for (int i = 0; i < mwProductViewChoice.quantity; i++) {
                    tempChoiceList.add(toProductView(mwProductViewChoice.choiceSolution));
                }
            }
            productView.setChoices(tempChoiceList);
        }
        if (!ListUtils.isEmpty(mwProductView.customizations)) {
            ArrayList<ProductView> tempCustomizationList = new ArrayList();
            for (MWProductViewCustomization mwProductViewCustomization : mwProductView.customizations) {
                tempCustomizationList.add(toProductView(mwProductViewCustomization));
            }
            productView.setCustomizations(tempCustomizationList);
        }
        MWPromotion mwPromotion = mwProductView.promotion;
        if (mwPromotion != null) {
            productView.setPromotion(mwPromotion.toPromotion());
        }
        productView.setQuantity(Integer.valueOf(mwProductView.quantity));
        return productView;
    }

    @Deprecated
    public List<MWProductViewComponent> getComponents() {
        return this.components;
    }

    @Deprecated
    public void setComponents(List<MWProductViewComponent> components) {
        this.components = components;
    }

    @Deprecated
    public List<MWProductViewChoice> getChoices() {
        return this.choices;
    }

    @Deprecated
    public void setChoices(List<MWProductViewChoice> choices) {
        this.choices = choices;
    }

    @Deprecated
    public List<MWProductViewCustomization> getCustomizations() {
        return this.customizations;
    }

    @Deprecated
    public void setCustomizations(List<MWProductViewCustomization> customizations) {
        this.customizations = customizations;
    }

    private void processMealIngredients(OrderProduct orderProduct) {
        this.components = new ArrayList();
        if (orderProduct.isMeal()) {
            List<OrderProduct> ingredients = orderProduct.getIngredients();
            if (ingredients != null) {
                int size = ingredients.size();
                for (int i = 0; i < size; i++) {
                    OrderProduct componentProduct = new OrderProduct((OrderProduct) ingredients.get(i));
                    MWProductViewComponent component = new MWProductViewComponent();
                    component.populateWithOrder(componentProduct);
                    this.components.add(component);
                }
            }
        }
    }

    private void processChoices(OrderProduct orderProduct) {
        this.choices = new ArrayList();
        List<Choice> orderProductChoices = orderProduct.getRealChoices();
        if (!ListUtils.isEmpty(orderProductChoices)) {
            for (Choice choice : orderProductChoices) {
                MWProductViewChoice mwProductViewChoice = new MWProductViewChoice();
                mwProductViewChoice.populateWithChoice(choice);
                this.choices.add(mwProductViewChoice);
            }
        }
    }

    private void processCustomizations(OrderProduct orderProduct) {
        this.customizations = new ArrayList();
        if (orderProduct.getCustomizations() != null) {
            for (OrderProduct customizationProduct : orderProduct.getCustomizations().values()) {
                MWProductViewCustomization customization = new MWProductViewCustomization();
                customization.populateWithOrder(customizationProduct);
                this.customizations.add(customization);
            }
        }
    }
}
