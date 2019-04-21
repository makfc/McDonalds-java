package com.mcdonalds.app.ordering.alert.checkout;

import com.ensighten.Ensighten;
import com.mcdonalds.app.ordering.alert.EditBasketAlertFragment;

public abstract class CheckoutAlertFragment extends EditBasketAlertFragment {
    public void onPositiveButtonClicked() {
        Ensighten.evaluateEvent(this, "onPositiveButtonClicked", null);
        getActivity().finish();
    }
}
