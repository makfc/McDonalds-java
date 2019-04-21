package com.mcdonalds.app.ordering.choiceselector;

import android.util.Pair;
import com.ensighten.Ensighten;
import com.mcdonalds.app.ordering.ProductUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.Choice;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ChoiceSelectorPresenter {
    private Stack<Pair<OrderProduct, OrderProduct>> mBackStack = new Stack();
    private int mIndex;
    private List<OrderProduct> mOptions;
    private OrderProduct mOrderProduct;
    private OrderingModule mOrderingModule = ((OrderingModule) ModuleManager.getModule("ordering"));
    private List<Integer> mOutageCode;
    private int mProductPosition;
    private OrderProduct mSelection;
    private ChoiceSelectorView mView;

    public ChoiceSelectorPresenter(ChoiceSelectorView view, OrderProduct orderProduct) {
        this.mView = view;
        setupOrderProduct(orderProduct);
    }

    private void setupOrderProduct(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "setupOrderProduct", new Object[]{orderProduct});
        this.mOrderProduct = orderProduct;
        filterOptions();
    }

    private void filterOptions() {
        Choice choice;
        Ensighten.evaluateEvent(this, "filterOptions", null);
        this.mOptions = new ArrayList();
        List<Choice> choices = new ArrayList();
        List<OrderProduct> ingredients = new ArrayList();
        if (this.mOrderProduct instanceof Choice) {
            choice = this.mOrderProduct;
            choices.addAll(choice.getCategories());
            ingredients.addAll(choice.getOptions());
        } else if (this.mOrderProduct != null) {
            choices.addAll(this.mOrderProduct.getRealChoices());
        }
        boolean hideSingleChoice = ProductUtils.hideSingleChoice();
        List<Choice> choicesFiltered;
        List<OrderProduct> ingredientsFiltered;
        if (OrderManager.getInstance().getCurrentOrder().isDelivery()) {
            choicesFiltered = new ArrayList();
            for (Choice choice2 : choices) {
                if (!(choice2 == null || choice2.getProduct() == null || !choice2.getProduct().getPODs().contains(Pod.DELIVERY))) {
                    if (!choice2.isSingleChoice() || !hideSingleChoice) {
                        choicesFiltered.add(choice2);
                    }
                }
            }
            ingredientsFiltered = new ArrayList();
            for (OrderProduct p : ingredients) {
                if (p.getProduct().getPODs().contains(Pod.DELIVERY)) {
                    ingredientsFiltered.add(p);
                }
            }
            if (this.mOrderProduct != null) {
                this.mOrderProduct.setRealChoices(choicesFiltered);
                this.mOrderProduct.setIngredients(ingredientsFiltered);
            }
            this.mOptions.addAll(choicesFiltered);
            this.mOptions.addAll(ingredientsFiltered);
        } else {
            choicesFiltered = new ArrayList();
            for (Choice choice22 : choices) {
                if ((choice22 != null && !choice22.isSingleChoice()) || !hideSingleChoice) {
                    choicesFiltered.add(choice22);
                }
            }
            ingredientsFiltered = new ArrayList();
            for (OrderProduct p2 : ingredients) {
                if (p2.getProduct().getPODs().contains(Pod.PICKUP)) {
                    ingredientsFiltered.add(p2);
                }
            }
            if (this.mOrderProduct != null) {
                this.mOrderProduct.setRealChoices(choicesFiltered);
                this.mOrderProduct.setIngredients(ingredientsFiltered);
            }
            this.mOptions.addAll(choicesFiltered);
            this.mOptions.addAll(ingredientsFiltered);
        }
        setupOutageProducts();
        if (Configuration.getSharedInstance().getBooleanForKey("interface.hideOutagedItemsInMenu")) {
            filterOutageProducts(this.mOptions, this.mOutageCode);
        }
        this.mView.showOptions(this.mOptions, this.mOutageCode);
    }

    public double getBasePrice() {
        Ensighten.evaluateEvent(this, "getBasePrice", null);
        PriceType priceType = OrderManager.getInstance().getCurrentOrder().getPriceType();
        if (this.mOrderProduct != null) {
            return this.mOrderProduct.getBasePrice(priceType);
        }
        return 0.0d;
    }

    public void setSelection(OrderProduct selection) {
        boolean doneEnabled = true;
        Ensighten.evaluateEvent(this, "setSelection", new Object[]{selection});
        setupSelection(selection);
        if (ProductUtils.hasSubChoice(selection)) {
            this.mBackStack.push(Pair.create(this.mOrderProduct, this.mSelection));
            setupOrderProduct(selection);
            this.mSelection = null;
        }
        if (this.mSelection == null) {
            doneEnabled = false;
        }
        this.mView.setDoneEnabled(doneEnabled);
    }

    private void setupSelection(OrderProduct selection) {
        Ensighten.evaluateEvent(this, "setupSelection", new Object[]{selection});
        int position = this.mOptions.indexOf(selection);
        if (position != -1) {
            this.mSelection = selection;
            this.mView.setSelected(position);
        }
    }

    public void setIndex(int mIndex) {
        Ensighten.evaluateEvent(this, "setIndex", new Object[]{new Integer(mIndex)});
        this.mIndex = mIndex;
    }

    public void setProductPosition(int mProductPosition) {
        Ensighten.evaluateEvent(this, "setProductPosition", new Object[]{new Integer(mProductPosition)});
        this.mProductPosition = mProductPosition;
    }

    public void productCustomized(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "productCustomized", new Object[]{orderProduct});
        int position = this.mOptions.indexOf(orderProduct);
        if (position != -1) {
            ((OrderProduct) this.mOptions.get(position)).setCustomizations(orderProduct.getCustomizations());
            this.mView.updateCustomization(position);
        }
    }

    public void back() {
        Ensighten.evaluateEvent(this, "back", null);
        boolean allChoicesMade = setSingleChoiceItemIfAvailableAndCheckChoiceSelections();
        if (this.mBackStack.empty() || !allChoicesMade) {
            done();
            return;
        }
        if (this.mOrderProduct instanceof Choice) {
            ((Choice) this.mOrderProduct).setSelection(this.mSelection);
        }
        Pair<OrderProduct, OrderProduct> pair = (Pair) this.mBackStack.pop();
        setupOrderProduct((OrderProduct) pair.first);
        setupSelection((OrderProduct) pair.second);
    }

    public void done() {
        Ensighten.evaluateEvent(this, "done", null);
        boolean allChoicesMade = setSingleChoiceItemIfAvailableAndCheckChoiceSelections();
        if (this.mSelection == null || !allChoicesMade) {
            this.mView.cancel();
            return;
        }
        while (!this.mBackStack.isEmpty() && allChoicesMade) {
            if (this.mOrderProduct instanceof Choice) {
                ((Choice) this.mOrderProduct).setSelection(this.mSelection);
            }
            Pair<OrderProduct, OrderProduct> pair = (Pair) this.mBackStack.pop();
            this.mOrderProduct = (OrderProduct) pair.first;
            this.mSelection = (OrderProduct) pair.second;
            allChoicesMade = setSingleChoiceItemIfAvailableAndCheckChoiceSelections();
        }
        if (!allChoicesMade) {
            setupOrderProduct(this.mOrderProduct);
            setupSelection(this.mSelection);
        }
        if (this.mOutageCode.contains(this.mSelection.getProduct().getExternalId())) {
            this.mView.cancel();
        } else {
            this.mView.finalize(this.mSelection, this.mIndex, this.mProductPosition);
        }
    }

    private boolean setSingleChoiceItemIfAvailableAndCheckChoiceSelections() {
        Ensighten.evaluateEvent(this, "setSingleChoiceItemIfAvailableAndCheckChoiceSelections", null);
        boolean allChoicesMade = true;
        if (this.mSelection == null) {
            return false;
        }
        if (ListUtils.isEmpty(this.mSelection.getRealChoices())) {
            return true;
        }
        for (int choiceIndex = 0; choiceIndex < this.mSelection.getRealChoices().size(); choiceIndex++) {
            Choice choice = (Choice) this.mSelection.getRealChoices().get(choiceIndex);
            boolean hideSingleChoice = ProductUtils.hideSingleChoice();
            if (choice.isSingleChoice() && hideSingleChoice) {
                OrderProduct choiceSolution = (OrderProduct) choice.getOptions().get(0);
                choiceSolution.setQuantity(1);
                choice.setSelection(choiceSolution);
            }
            if (choice.getSelection() == null) {
                allChoicesMade = false;
            }
        }
        return allChoicesMade;
    }

    private void setupOutageProducts() {
        Ensighten.evaluateEvent(this, "setupOutageProducts", null);
        List<String> outageProductCodes = new ArrayList();
        Store store = OrderManager.getInstance().getCurrentStore();
        if (store != null) {
            outageProductCodes = store.getOutageProducts();
        }
        this.mOutageCode = new ArrayList();
        if (!com.mcdonalds.sdk.utils.ListUtils.isEmpty(outageProductCodes)) {
            for (String productCode : outageProductCodes) {
                this.mOutageCode.add(Integer.valueOf(Integer.parseInt(productCode)));
            }
        }
    }

    private void filterOutageProducts(List<OrderProduct> products, List<Integer> outageCodes) {
        Ensighten.evaluateEvent(this, "filterOutageProducts", new Object[]{products, outageCodes});
        ArrayList<OrderProduct> outageProducts = new ArrayList();
        for (OrderProduct p : products) {
            if (outageCodes.contains(p.getProduct().getExternalId())) {
                outageProducts.add(p);
            }
        }
        products.removeAll(outageProducts);
    }
}
