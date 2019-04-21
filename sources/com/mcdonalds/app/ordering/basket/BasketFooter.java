package com.mcdonalds.app.ordering.basket;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;

class BasketFooter {
    final Button mAddToBasketButton;
    final View mCaloriesWarningView;
    final Button mDeleteOrderButton;
    final TextView mPriceWarningTextView;
    final View mView;
    final View mWarningsContainer;

    BasketFooter(View view) {
        this.mView = view;
        this.mWarningsContainer = view.findViewById(C2358R.C2357id.warnings_container);
        this.mPriceWarningTextView = (TextView) view.findViewById(C2358R.C2357id.price_warning_textview);
        this.mCaloriesWarningView = view.findViewById(C2358R.C2357id.energy_warning_view);
        this.mDeleteOrderButton = (Button) view.findViewById(C2358R.C2357id.delete_order_button);
        this.mAddToBasketButton = (Button) view.findViewById(C2358R.C2357id.add_to_basket_button);
    }

    public View getWarningsContainer() {
        Ensighten.evaluateEvent(this, "getWarningsContainer", null);
        return this.mWarningsContainer;
    }

    public Button getDeleteOrderButton() {
        Ensighten.evaluateEvent(this, "getDeleteOrderButton", null);
        return this.mDeleteOrderButton;
    }

    public Button getAddToBasketButton() {
        Ensighten.evaluateEvent(this, "getAddToBasketButton", null);
        return this.mAddToBasketButton;
    }

    public View getCaloriesWarningView() {
        Ensighten.evaluateEvent(this, "getCaloriesWarningView", null);
        return this.mCaloriesWarningView;
    }
}
