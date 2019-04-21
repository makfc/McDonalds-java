package com.mcdonalds.app.ordering.checkin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.gma.hongkong.C2658R;

public class OneTimeOrderCheckinFragment extends OrderCheckinFragment {
    private boolean mPaymentPrepared;

    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_qr_scan);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_one_time_order_location, container, false);
        this.mMainView = view.findViewById(C2358R.C2357id.main_view);
        if (this.mOrder.isDelivery()) {
            this.mMainView.setVisibility(8);
        } else {
            this.mMainView.setVisibility(0);
        }
        this.mTableServiceButton = view.findViewById(C2358R.C2357id.table_service_button);
        this.mTableServiceButton.setOnClickListener(this);
        this.mEatInButton = view.findViewById(C2358R.C2357id.eatin_button);
        this.mEatInButton.setOnClickListener(this);
        this.mTakeOutButton = view.findViewById(C2358R.C2357id.takeout_button);
        this.mTakeOutButton.setOnClickListener(this);
        if (!this.mOrder.isDelivery()) {
            setupTableServiceButton(this.mTableServiceButton, this.mTakeOutButton);
        }
        return view;
    }

    private void choosePayment() {
        Ensighten.evaluateEvent(this, "choosePayment", null);
        String QRCode = this.mCode;
        Bundle extras = new Bundle();
        extras.putString("qr_code", QRCode);
        getNavigationActivity().startActivityForResult(ChoosePaymentActivity.class, "choose_payment", extras, 4082);
    }

    public void preparePaymentAndCheckin() {
        Ensighten.evaluateEvent(this, "preparePaymentAndCheckin", null);
        if (this.mPaymentPrepared || this.mOrder.isZeroPriced()) {
            super.preparePaymentAndCheckin();
            return;
        }
        this.mPaymentPrepared = true;
        choosePayment();
    }

    public void onNewIntent(Intent intent) {
        Ensighten.evaluateEvent(this, "onNewIntent", new Object[]{intent});
    }

    public void reset() {
        Ensighten.evaluateEvent(this, "reset", null);
        this.mPaymentPrepared = false;
        if (this.mOrder.isDelivery()) {
            this.mMainView.setVisibility(8);
            getActivity().finish();
            return;
        }
        this.mMainView.setVisibility(0);
    }
}
