package com.mcdonalds.app.ordering.alert.checkin;

import com.ensighten.Ensighten;
import com.mcdonalds.gma.hongkong.C2658R;

public class ItemsOutOfStockCheckinAlertFragment extends CheckinAlertFragment {
    public int getFragmentResourceId() {
        Ensighten.evaluateEvent(this, "getFragmentResourceId", null);
        return C2658R.layout.fragment_check_in_items_out_of_stock;
    }
}
