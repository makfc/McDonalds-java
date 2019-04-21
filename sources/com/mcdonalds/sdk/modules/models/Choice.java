package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.mcdonalds.sdk.connectors.utils.SerializableSparseArray;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.models.Product.ProductType;
import com.mcdonalds.sdk.utils.ListUtils;
import com.mcdonalds.sdk.utils.SDKUtils;
import java.util.ArrayList;
import java.util.List;

public class Choice extends OrderProduct {
    public static final Creator<Choice> CREATOR = new C25701();
    private List<Choice> mCategories;
    private List<OrderProduct> mOptions;
    private OrderProduct mSelection;

    /* renamed from: com.mcdonalds.sdk.modules.models.Choice$1 */
    static class C25701 implements Creator<Choice> {
        C25701() {
        }

        public Choice createFromParcel(Parcel in) {
            return new Choice(in);
        }

        public Choice[] newArray(int size) {
            return new Choice[size];
        }
    }

    public Choice() {
        this.mOptions = new ArrayList();
        this.mCategories = new ArrayList();
    }

    protected Choice(Ingredient ingredient, Integer quantity) {
        super(ingredient.getProduct(), quantity);
        this.mOptions = new ArrayList();
        this.mCategories = new ArrayList();
        setCostInclusive(ingredient.getCostInclusive());
        setBasePriceReferenceProductCode(ingredient.getReferencePriceProductCode());
    }

    protected Choice(OrderProduct orderProduct) {
        super(orderProduct.getProduct(), Integer.valueOf(orderProduct.getQuantity()));
        this.mOptions = new ArrayList();
        this.mCategories = new ArrayList();
        setCostInclusive(orderProduct.isCostInclusive());
        setBasePriceReferenceProductCode(orderProduct.getBasePriceReferenceProductCode());
    }

    @Nullable
    public static Choice createChoice(Ingredient sourceIngredient, Integer quantity) {
        if (sourceIngredient.getProduct().getProductType().equals(ProductType.Choice)) {
            return new Choice(sourceIngredient, quantity);
        }
        return null;
    }

    @Nullable
    public static Choice createChoice(OrderProduct orderProduct) {
        if (!orderProduct.getProduct().getProductType().equals(ProductType.Choice)) {
            return null;
        }
        Choice choice = new Choice(orderProduct);
        choice.setIngredients(orderProduct.getIngredients());
        choice.setChoices(orderProduct.getChoices());
        choice.setChoiceSolutions(orderProduct.getChoiceSolutions());
        return choice;
    }

    public void addOption(OrderProduct orderProduct) {
        this.mOptions.add(orderProduct);
    }

    public void addCategory(Choice category) {
        this.mCategories.add(category);
    }

    public List<OrderProduct> getOptions() {
        if (ListUtils.isEmpty(this.mOptions)) {
            List<Ingredient> options = getProduct().getIngredients();
            if (options != null) {
                int size = options.size();
                for (int i = 0; i < size; i++) {
                    addOption(OrderProduct.createProduct((Ingredient) options.get(i), Integer.valueOf(getQuantity())));
                }
            }
        }
        return this.mOptions;
    }

    public void setOptions(List<OrderProduct> options) {
        this.mOptions = options;
    }

    public List<Choice> getRealChoices() {
        return getCategories();
    }

    public List<Choice> getCategories() {
        if (ListUtils.isEmpty(this.mCategories)) {
            List<Ingredient> categories = getProduct().getChoices();
            if (categories != null) {
                int size = categories.size();
                for (int i = 0; i < size; i++) {
                    addCategory(createChoice((Ingredient) categories.get(i), Integer.valueOf(getQuantity())));
                }
            }
        }
        return this.mCategories;
    }

    public void setCategories(List<Choice> categories) {
        this.mCategories = categories;
    }

    public OrderProduct getSelection() {
        return this.mSelection;
    }

    public void setSelection(OrderProduct selection) {
        this.mSelection = selection;
    }

    @Deprecated
    public List<OrderProduct> getChoices() {
        List<OrderProduct> compatChoices = new ArrayList();
        compatChoices.addAll(this.mCategories);
        return compatChoices;
    }

    public void setChoices(List<OrderProduct> choices) {
        this.mCategories = new ArrayList();
        for (OrderProduct choice : choices) {
            addChoice(choice);
        }
    }

    @Deprecated
    public List<OrderProduct> getIngredients() {
        return getOptions();
    }

    @Deprecated
    public void setIngredients(List<OrderProduct> ingredients) {
        this.mOptions = ingredients;
    }

    public SerializableSparseArray<OrderProduct> getChoiceSolutions() {
        SerializableSparseArray<OrderProduct> compatChoiceSolutions = new SerializableSparseArray();
        if (ListUtils.isEmpty(this.mCategories) && this.mSelection != null) {
            compatChoiceSolutions.put(0, this.mSelection);
        }
        for (int i = 0; i < this.mCategories.size(); i++) {
            Choice category = (Choice) this.mCategories.get(i);
            if (category.getSelection() != null) {
                compatChoiceSolutions.put(i, category.getSelection());
            }
        }
        return compatChoiceSolutions;
    }

    public void addChoice(OrderProduct choice) {
        this.mCategories.add(createChoice(choice));
    }

    public void setChoiceSolution(int index, OrderProduct choiceSolution) {
        if (index < this.mCategories.size()) {
            ((Choice) this.mCategories.get(index)).setSelection(choiceSolution);
        }
    }

    @Deprecated
    public void addIngredient(OrderProduct ingredient) {
        this.mOptions.add(ingredient);
    }

    public double getBasePrice(PriceType priceType) {
        Product product = getProduct();
        int referencePriceProductCode = getBasePriceReferenceProductCode();
        List<Ingredient> ingredients = new ArrayList();
        if (!ListUtils.isEmpty(product.getChoices())) {
            ingredients.addAll(product.getChoices());
        }
        if (!ListUtils.isEmpty(product.getIngredients())) {
            ingredients.addAll(product.getIngredients());
        }
        if (ListUtils.isEmpty(ingredients)) {
            return 0.0d;
        }
        Ingredient ingredient = (Ingredient) ingredients.get(0);
        for (int i = 1; i < ingredients.size(); i++) {
            Ingredient tmpIngredient = (Ingredient) ingredients.get(i);
            if (tmpIngredient.getProduct().getExternalId().equals(Integer.valueOf(referencePriceProductCode))) {
                ingredient = tmpIngredient;
            }
        }
        if (ingredient.getCostInclusive()) {
            return 0.0d;
        }
        if (priceType == PriceType.EatIn) {
            if (ingredient.getProduct().getBasePriceEatIn() == 0.0d) {
                return ingredient.getProduct().getPriceEatIn();
            }
            return ingredient.getProduct().getBasePriceEatIn();
        } else if (priceType == PriceType.TakeOut) {
            if (ingredient.getProduct().getBasePriceTakeOut() == 0.0d) {
                return ingredient.getProduct().getPriceTakeOut();
            }
            return ingredient.getProduct().getBasePriceTakeOut();
        } else if (ingredient.getProduct().getBasePriceDelivery() == 0.0d) {
            return ingredient.getProduct().getPriceDelivery();
        } else {
            return ingredient.getProduct().getBasePriceDelivery();
        }
    }

    public double getTotalPrice(PriceType priceType, boolean allowDownCharge) {
        OrderProduct choiceSolution = getSelection();
        double basePrice = getBasePrice(priceType);
        double choicePrice = 0.0d;
        if (choiceSolution != null) {
            choicePrice = choiceSolution.getTotalPrice(priceType, allowDownCharge);
            if (SDKUtils.doubleEquals(choicePrice, 0.0d) && (choiceSolution instanceof Choice) && ((Choice) choiceSolution).getSelection() != null) {
                choicePrice = ((Choice) choiceSolution).getSelection().getTotalPrice(priceType, allowDownCharge);
            }
        }
        if (choiceSolution == null) {
            return basePrice;
        }
        if (allowDownCharge) {
            return choicePrice;
        }
        return Math.max(choicePrice, basePrice);
    }

    public String getCustomizationsString() {
        OrderProduct choiceSolution = getSelection();
        if (choiceSolution != null) {
            return choiceSolution.getCustomizationsString();
        }
        return null;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(this.mCategories);
        dest.writeTypedList(this.mOptions);
        dest.writeParcelable(this.mSelection, flags);
    }

    protected Choice(Parcel in) {
        super(in);
        this.mCategories = in.createTypedArrayList(CREATOR);
        this.mOptions = in.createTypedArrayList(OrderProduct.CREATOR);
        this.mSelection = (OrderProduct) in.readParcelable(OrderProduct.class.getClassLoader());
    }
}
