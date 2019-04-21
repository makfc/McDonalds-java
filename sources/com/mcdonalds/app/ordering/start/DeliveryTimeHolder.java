package com.mcdonalds.app.ordering.start;

import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;

public class DeliveryTimeHolder {
    private View mContainer;
    private TextView mDeliveryFee;
    private TextView mDeliveryFeeLabel;
    private View mDeliveryTimeView;
    private ImageView mDisclosureIcon;
    private TextView mEstimatedArrival;
    private TextView mEstimatedArrivalLabel;
    private TextView mTimeLabel;

    public DeliveryTimeHolder(View container) {
        this.mContainer = container;
        this.mDeliveryTimeView = container.findViewById(C2358R.C2357id.delivery_time_address_button);
        this.mTimeLabel = (TextView) container.findViewById(C2358R.C2357id.delivery_time_label);
        this.mDisclosureIcon = (ImageView) container.findViewById(C2358R.C2357id.delivery_disclosure_image);
        this.mEstimatedArrivalLabel = (TextView) container.findViewById(C2358R.C2357id.estimated_arrival_label);
        this.mEstimatedArrival = (TextView) container.findViewById(C2358R.C2357id.estimated_arrival);
        this.mDeliveryFeeLabel = (TextView) container.findViewById(C2358R.C2357id.delivery_fee_label);
        this.mDeliveryFee = (TextView) container.findViewById(C2358R.C2357id.delivery_fee);
    }

    public void setDeliveryHeaderText(Spanned headerText) {
        Ensighten.evaluateEvent(this, "setDeliveryHeaderText", new Object[]{headerText});
        this.mTimeLabel.setText(headerText);
    }

    public View getContainer() {
        Ensighten.evaluateEvent(this, "getContainer", null);
        return this.mContainer;
    }

    public View getDeliveryTimeView() {
        Ensighten.evaluateEvent(this, "getDeliveryTimeView", null);
        return this.mDeliveryTimeView;
    }

    public TextView getDeliveryFee() {
        Ensighten.evaluateEvent(this, "getDeliveryFee", null);
        return this.mDeliveryFee;
    }

    public ImageView getDisclosureIcon() {
        Ensighten.evaluateEvent(this, "getDisclosureIcon", null);
        return this.mDisclosureIcon;
    }
}
