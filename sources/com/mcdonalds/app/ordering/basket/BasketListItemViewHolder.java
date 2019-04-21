package com.mcdonalds.app.ordering.basket;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.ProductDetailsItem;

class BasketListItemViewHolder extends ProductDetailsItem {
    private final View mDividerContainer;
    private final Button mEditButton;
    private final View mEditContainer;
    private final View mEnergyPriceContainer;
    private final TextView mEnergyTotalView;
    private final View mErrorContainer;
    private final ImageView mErrorIconView;
    private final TextView mErrorTextView;
    private final View mHeaderContainer;
    private final ImageView mHeaderIconView;
    private final TextView mHeaderTextView;
    private final ImageButton mInfoButton;
    private final Button mMakeItAMealButton;
    private final TextView mPriceTotalView;
    private final Button mRemoveButton;
    private final View mSpacer;
    private final TextView mTimeRestrictionWarning;
    private final View mTopPad;
    private final TextView mUnAvailablePointsOfDistributionMessage;

    BasketListItemViewHolder(View view) {
        super(view.findViewById(C2358R.C2357id.product_details_item));
        this.mTopPad = view.findViewById(C2358R.C2357id.topPadding);
        this.mErrorContainer = view.findViewById(C2358R.C2357id.error_container);
        this.mErrorTextView = (TextView) view.findViewById(C2358R.C2357id.error_textview);
        this.mErrorIconView = (ImageView) view.findViewById(C2358R.C2357id.error_imageview);
        this.mHeaderContainer = view.findViewById(C2358R.C2357id.header_container);
        this.mDividerContainer = view.findViewById(C2358R.C2357id.divider_container);
        this.mHeaderTextView = (TextView) view.findViewById(C2358R.C2357id.header_textview);
        this.mHeaderIconView = (ImageView) view.findViewById(C2358R.C2357id.header_imageview);
        this.mSpacer = view.findViewById(C2358R.C2357id.spacer_view);
        this.mEnergyPriceContainer = view.findViewById(C2358R.C2357id.energy_price_container);
        this.mEnergyTotalView = (TextView) view.findViewById(C2358R.C2357id.energy_total_textview);
        this.mPriceTotalView = (TextView) view.findViewById(C2358R.C2357id.price_total_textview);
        this.mEditContainer = view.findViewById(C2358R.C2357id.edit_container);
        this.mRemoveButton = (Button) view.findViewById(C2358R.C2357id.button_remove);
        this.mEditButton = (Button) view.findViewById(C2358R.C2357id.button_edit);
        this.mMakeItAMealButton = (Button) view.findViewById(C2358R.C2357id.make_it_a_meal_button);
        this.mInfoButton = (ImageButton) view.findViewById(C2358R.C2357id.info_button);
        this.mUnAvailablePointsOfDistributionMessage = (TextView) view.findViewById(C2358R.C2357id.unavailable_pod_message);
        this.mTimeRestrictionWarning = (TextView) view.findViewById(C2358R.C2357id.time_restriction_warning);
    }

    public View getTopPad() {
        Ensighten.evaluateEvent(this, "getTopPad", null);
        return this.mTopPad;
    }

    public View getErrorContainer() {
        Ensighten.evaluateEvent(this, "getErrorContainer", null);
        return this.mErrorContainer;
    }

    public TextView getErrorTextView() {
        Ensighten.evaluateEvent(this, "getErrorTextView", null);
        return this.mErrorTextView;
    }

    public View getHeaderContainer() {
        Ensighten.evaluateEvent(this, "getHeaderContainer", null);
        return this.mHeaderContainer;
    }

    public View getDividerContainer() {
        Ensighten.evaluateEvent(this, "getDividerContainer", null);
        return this.mDividerContainer;
    }

    public TextView getHeaderTextView() {
        Ensighten.evaluateEvent(this, "getHeaderTextView", null);
        return this.mHeaderTextView;
    }

    public ImageView getHeaderIconView() {
        Ensighten.evaluateEvent(this, "getHeaderIconView", null);
        return this.mHeaderIconView;
    }

    public View getEnergyPriceContainer() {
        Ensighten.evaluateEvent(this, "getEnergyPriceContainer", null);
        return this.mEnergyPriceContainer;
    }

    public TextView getEnergyTotalView() {
        Ensighten.evaluateEvent(this, "getEnergyTotalView", null);
        return this.mEnergyTotalView;
    }

    public TextView getPriceTotalView() {
        Ensighten.evaluateEvent(this, "getPriceTotalView", null);
        return this.mPriceTotalView;
    }

    public View getEditContainer() {
        Ensighten.evaluateEvent(this, "getEditContainer", null);
        return this.mEditContainer;
    }

    public Button getRemoveButton() {
        Ensighten.evaluateEvent(this, "getRemoveButton", null);
        return this.mRemoveButton;
    }

    public Button getEditButton() {
        Ensighten.evaluateEvent(this, "getEditButton", null);
        return this.mEditButton;
    }

    public Button getMakeItAMealButton() {
        Ensighten.evaluateEvent(this, "getMakeItAMealButton", null);
        return this.mMakeItAMealButton;
    }

    public ImageButton getInfoButton() {
        Ensighten.evaluateEvent(this, "getInfoButton", null);
        return this.mInfoButton;
    }

    public TextView getUnAvailablePODMessage() {
        Ensighten.evaluateEvent(this, "getUnAvailablePODMessage", null);
        return this.mUnAvailablePointsOfDistributionMessage;
    }

    public TextView getTimeRestrictionWarning() {
        Ensighten.evaluateEvent(this, "getTimeRestrictionWarning", null);
        return this.mTimeRestrictionWarning;
    }
}
