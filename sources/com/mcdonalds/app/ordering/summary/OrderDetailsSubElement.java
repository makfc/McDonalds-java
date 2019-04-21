package com.mcdonalds.app.ordering.summary;

import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;

public class OrderDetailsSubElement {
    private final RelativeLayout mContainer;
    private final TextView mDetails = ((TextView) this.mContainer.findViewById(C2358R.C2357id.details_label));
    private final TextView mInfo = ((TextView) this.mContainer.findViewById(C2358R.C2357id.info_label));
    private final TextView mPriceUplift = ((TextView) this.mContainer.findViewById(C2358R.C2357id.price_uplift));
    private final TextView mTitle = ((TextView) this.mContainer.findViewById(C2358R.C2357id.title_label));

    public OrderDetailsSubElement(RelativeLayout layout) {
        this.mContainer = layout;
    }

    public RelativeLayout getContainer() {
        Ensighten.evaluateEvent(this, "getContainer", null);
        return this.mContainer;
    }

    public TextView getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return this.mTitle;
    }

    public TextView getDetails() {
        Ensighten.evaluateEvent(this, "getDetails", null);
        return this.mDetails;
    }

    public TextView getInfo() {
        Ensighten.evaluateEvent(this, "getInfo", null);
        return this.mInfo;
    }

    public TextView getPriceUplift() {
        Ensighten.evaluateEvent(this, "getPriceUplift", null);
        return this.mPriceUplift;
    }
}
