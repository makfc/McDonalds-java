package com.mcdonalds.app.ordering.alert.checkin;

import com.ensighten.Ensighten;
import com.mcdonalds.app.ordering.alert.AlertFragment;
import com.mcdonalds.gma.hongkong.C2658R;

public class RestaurantMismatchFragment extends AlertFragment {
    public int getFragmentResourceId() {
        Ensighten.evaluateEvent(this, "getFragmentResourceId", null);
        return C2658R.layout.fragment_restaurant_mismatch;
    }

    public void onNegativeButtonClicked() {
        Ensighten.evaluateEvent(this, "onNegativeButtonClicked", null);
        getActivity().setResult(0);
        getActivity().finish();
    }

    public void onPositiveButtonClicked() {
        Ensighten.evaluateEvent(this, "onPositiveButtonClicked", null);
        getActivity().setResult(-1);
        getActivity().finish();
    }
}
