package com.mcdonalds.app.ordering.basket;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;

public class BasketSubtotalItemViewHolder {
    private ImageView mAlertIcon;
    private TextView mDeliveryFeeAmount = ((TextView) this.mView.findViewById(C2358R.C2357id.delivery_fee_amount));
    private View mDeliveryFeeContainer = this.mView.findViewById(C2358R.C2357id.delivery_fee_container);
    private View mDeliveryFeeOfferContainer = this.mView.findViewById(C2358R.C2357id.delivery_fee_discount_container);
    private TextView mDeliveryFeeOfferDiscountAmount = ((TextView) this.mView.findViewById(C2358R.C2357id.delivery_fee_discount_amount));
    private TextView mDeliveryFeeOfferTitle = ((TextView) this.mView.findViewById(C2358R.C2357id.delivery_fee_offer_title));
    private View mEditContainer;
    private TextView mNotAvailableWarning;
    private TextView mOfferName;
    private View mOfferUnavailableContainer;
    private TextView mOfferWillApply;
    private Button mRemoveButton;
    private View mSubtotalContainer = this.mView.findViewById(C2358R.C2357id.subtotal_container);
    private TextView mSubtotalEnergy = ((TextView) this.mView.findViewById(C2358R.C2357id.energy_total_value));
    private TextView mSubtotalPrice = ((TextView) this.mView.findViewById(C2358R.C2357id.price_total_value));
    private TextView mTotalAmount;
    private View mTotalContainer;
    private View mView;

    public BasketSubtotalItemViewHolder(View view) {
        this.mView = view;
        this.mEditContainer = view.findViewById(C2358R.C2357id.edit_container);
        this.mRemoveButton = (Button) view.findViewById(C2358R.C2357id.button_remove);
        this.mOfferUnavailableContainer = this.mView.findViewById(C2358R.C2357id.offer_unavailable_container);
        this.mOfferName = (TextView) this.mView.findViewById(C2358R.C2357id.offer_name);
        this.mAlertIcon = (ImageView) this.mView.findViewById(C2358R.C2357id.imageView);
        this.mNotAvailableWarning = (TextView) this.mView.findViewById(C2358R.C2357id.textView5);
        this.mTotalContainer = this.mView.findViewById(C2358R.C2357id.total_container);
        this.mTotalAmount = (TextView) this.mView.findViewById(C2358R.C2357id.total_amount);
        this.mOfferWillApply = (TextView) this.mView.findViewById(C2358R.C2357id.offer_will_apply);
    }

    public View getDeliveryFeeContainer() {
        Ensighten.evaluateEvent(this, "getDeliveryFeeContainer", null);
        return this.mDeliveryFeeContainer;
    }

    public View getDeliveryFeeOfferContainer() {
        Ensighten.evaluateEvent(this, "getDeliveryFeeOfferContainer", null);
        return this.mDeliveryFeeOfferContainer;
    }

    public TextView getDeliveryFeeOfferTitle() {
        Ensighten.evaluateEvent(this, "getDeliveryFeeOfferTitle", null);
        return this.mDeliveryFeeOfferTitle;
    }

    public TextView getDeliveryFeeOfferDiscountAmount() {
        Ensighten.evaluateEvent(this, "getDeliveryFeeOfferDiscountAmount", null);
        return this.mDeliveryFeeOfferDiscountAmount;
    }

    public TextView getDeliveryFeeAmount() {
        Ensighten.evaluateEvent(this, "getDeliveryFeeAmount", null);
        return this.mDeliveryFeeAmount;
    }

    public View getSubtotalContainer() {
        Ensighten.evaluateEvent(this, "getSubtotalContainer", null);
        return this.mSubtotalContainer;
    }

    public TextView getSubtotalEnergy() {
        Ensighten.evaluateEvent(this, "getSubtotalEnergy", null);
        return this.mSubtotalEnergy;
    }

    public TextView getSubtotalPrice() {
        Ensighten.evaluateEvent(this, "getSubtotalPrice", null);
        return this.mSubtotalPrice;
    }

    public View getOfferUnavailableContainer() {
        Ensighten.evaluateEvent(this, "getOfferUnavailableContainer", null);
        return this.mOfferUnavailableContainer;
    }

    public TextView getOfferName() {
        Ensighten.evaluateEvent(this, "getOfferName", null);
        return this.mOfferName;
    }

    public TextView getOfferWillApply() {
        Ensighten.evaluateEvent(this, "getOfferWillApply", null);
        return this.mOfferWillApply;
    }

    public TextView getNotAvailableWarning() {
        Ensighten.evaluateEvent(this, "getNotAvailableWarning", null);
        return this.mNotAvailableWarning;
    }

    public ImageView getAlertIcon() {
        Ensighten.evaluateEvent(this, "getAlertIcon", null);
        return this.mAlertIcon;
    }

    public View getTotalContainer() {
        Ensighten.evaluateEvent(this, "getTotalContainer", null);
        return this.mTotalContainer;
    }

    public TextView getTotalAmount() {
        Ensighten.evaluateEvent(this, "getTotalAmount", null);
        return this.mTotalAmount;
    }

    public View getEditContainer() {
        Ensighten.evaluateEvent(this, "getEditContainer", null);
        return this.mEditContainer;
    }

    public Button getRemoveButton() {
        Ensighten.evaluateEvent(this, "getRemoveButton", null);
        return this.mRemoveButton;
    }
}
