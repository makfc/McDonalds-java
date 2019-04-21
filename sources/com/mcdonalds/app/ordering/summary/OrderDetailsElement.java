package com.mcdonalds.app.ordering.summary;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;

public class OrderDetailsElement {
    private final View mBottomBorder = this.mContainer.findViewById(C2358R.C2357id.border2);
    private final RelativeLayout mContainer;
    private final TextView mCustomSpecialInstructions = ((TextView) this.mContainer.findViewById(C2358R.C2357id.custom_special_instructions));
    private final TextView mInfo = ((TextView) this.mContainer.findViewById(C2358R.C2357id.info_label));
    private final TextView mPrice = ((TextView) this.mContainer.findViewById(C2358R.C2357id.price_label));
    private final TextView mQuantity = ((TextView) this.mContainer.findViewById(C2358R.C2357id.quantity_label));
    private final TextView mTitle = ((TextView) this.mContainer.findViewById(C2358R.C2357id.title_label));
    private final View mTopBorder = this.mContainer.findViewById(C2358R.C2357id.border1);

    public OrderDetailsElement(RelativeLayout layout) {
        this.mContainer = layout;
    }

    public RelativeLayout getContainer() {
        Ensighten.evaluateEvent(this, "getContainer", null);
        return this.mContainer;
    }

    public TextView getQuantity() {
        Ensighten.evaluateEvent(this, "getQuantity", null);
        return this.mQuantity;
    }

    public TextView getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return this.mTitle;
    }

    public TextView getInfo() {
        Ensighten.evaluateEvent(this, "getInfo", null);
        return this.mInfo;
    }

    public TextView getPrice() {
        Ensighten.evaluateEvent(this, "getPrice", null);
        return this.mPrice;
    }

    public TextView getCustomSpecialInstructions() {
        Ensighten.evaluateEvent(this, "getCustomSpecialInstructions", null);
        return this.mCustomSpecialInstructions;
    }

    public View getTopBorder() {
        Ensighten.evaluateEvent(this, "getTopBorder", null);
        return this.mTopBorder;
    }

    public View getBottomBorder() {
        Ensighten.evaluateEvent(this, "getBottomBorder", null);
        return this.mBottomBorder;
    }
}
