package com.mcdonalds.app.ordering.alert;

import com.ensighten.Ensighten;
import com.mcdonalds.app.ordering.summary.OrderSummaryActivity;
import com.mcdonalds.gma.hongkong.C2658R;

public class LargeOrderAlertFragment extends AlertFragment {
    public int getFragmentResourceId() {
        Ensighten.evaluateEvent(this, "getFragmentResourceId", null);
        return C2658R.layout.fragment_large_order_alert;
    }

    public void onNegativeButtonClicked() {
        Ensighten.evaluateEvent(this, "onNegativeButtonClicked", null);
    }

    public void onPositiveButtonClicked() {
        Ensighten.evaluateEvent(this, "onPositiveButtonClicked", null);
        startActivity(OrderSummaryActivity.class, "order_summary");
    }
}
