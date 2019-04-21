package com.mcdonalds.app.ordering.alert.checkout;

import com.ensighten.Ensighten;
import com.mcdonalds.gma.hongkong.C2658R;

public class ItemsUnavailableCheckoutAlertFragment extends CheckoutAlertFragment {
    public int getFragmentResourceId() {
        Ensighten.evaluateEvent(this, "getFragmentResourceId", null);
        return C2658R.layout.fragment_check_out_items_unavailable;
    }
}
