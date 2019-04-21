package com.mcdonalds.app.ordering;

import android.view.View;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;

public class ProductTotalPriceEnergy {
    private TextView mTotalEnergy;
    private TextView mTotalLabel;
    private TextView mTotalPrice;
    private View mView;

    public ProductTotalPriceEnergy(View view) {
        this.mView = view;
        this.mTotalLabel = (TextView) view.findViewById(C2358R.C2357id.total_label);
        this.mTotalEnergy = (TextView) view.findViewById(C2358R.C2357id.cal_total_value);
        this.mTotalPrice = (TextView) view.findViewById(C2358R.C2357id.total_price);
    }

    public TextView getTotalEnergy() {
        Ensighten.evaluateEvent(this, "getTotalEnergy", null);
        return this.mTotalEnergy;
    }

    public TextView getTotalPrice() {
        Ensighten.evaluateEvent(this, "getTotalPrice", null);
        return this.mTotalPrice;
    }
}
