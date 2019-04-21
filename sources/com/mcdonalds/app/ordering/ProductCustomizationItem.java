package com.mcdonalds.app.ordering;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.sdk.modules.models.Ingredient;
import com.mcdonalds.sdk.modules.models.OrderProduct;

public class ProductCustomizationItem {
    private TextView mCost;
    private ImageView mDecreaseButton;
    private ImageView mIncreaseButton;
    private Ingredient mIngredient;
    private OrderProduct mOrderProduct;
    private TextView mQuantity;
    private TextView mTypeLabel;
    private View mView;

    ProductCustomizationItem(View view) {
        this.mView = view;
        this.mTypeLabel = (TextView) view.findViewById(C2358R.C2357id.customization_type);
        this.mQuantity = (TextView) view.findViewById(C2358R.C2357id.quantity_text);
        this.mCost = (TextView) view.findViewById(C2358R.C2357id.customization_price);
        this.mDecreaseButton = (ImageView) view.findViewById(C2358R.C2357id.decrease_quantity_img_btn);
        this.mIncreaseButton = (ImageView) view.findViewById(C2358R.C2357id.increase_quantity_img_btn);
    }

    public OrderProduct getOrderProduct() {
        Ensighten.evaluateEvent(this, "getOrderProduct", null);
        return this.mOrderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "setOrderProduct", new Object[]{orderProduct});
        this.mOrderProduct = orderProduct;
    }

    public Ingredient getIngredient() {
        Ensighten.evaluateEvent(this, "getIngredient", null);
        return this.mIngredient;
    }

    public void setIngredient(Ingredient ingredient) {
        Ensighten.evaluateEvent(this, "setIngredient", new Object[]{ingredient});
        this.mIngredient = ingredient;
    }

    public View getView() {
        return this.mView;
    }

    public TextView getTypeLabel() {
        Ensighten.evaluateEvent(this, "getTypeLabel", null);
        return this.mTypeLabel;
    }

    public TextView getQuantity() {
        Ensighten.evaluateEvent(this, "getQuantity", null);
        return this.mQuantity;
    }

    public TextView getCost() {
        Ensighten.evaluateEvent(this, "getCost", null);
        return this.mCost;
    }

    public ImageView getDecreaseButton() {
        Ensighten.evaluateEvent(this, "getDecreaseButton", null);
        return this.mDecreaseButton;
    }

    public ImageView getIncreaseButton() {
        Ensighten.evaluateEvent(this, "getIncreaseButton", null);
        return this.mIncreaseButton;
    }
}
