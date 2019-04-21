package com.mcdonalds.app.ordering.instorepickup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.checkin.OrderCheckinFragment;
import com.mcdonalds.gma.hongkong.C2658R;

public class EatInTakeOutFragment extends OrderCheckinFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_eat_in_take_out, container, false);
        this.mShouldLaunchQRCodeScanner = false;
        this.mMainViewVisible = true;
        this.mMainView = view.findViewById(C2358R.C2357id.main_view);
        this.mEatInButton = view.findViewById(C2358R.C2357id.eat_in_button);
        this.mEatInButton.setOnClickListener(this);
        this.mTakeOutButton = view.findViewById(C2358R.C2357id.take_out_button);
        this.mTakeOutButton.setOnClickListener(this);
        this.mTableServiceButton = view.findViewById(C2358R.C2357id.table_service_button);
        this.mTableServiceButton.setOnClickListener(this);
        return view;
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.title_activity_order_checkin);
    }

    public void updateTakeOutButton(View takeOutButton) {
        Ensighten.evaluateEvent(this, "updateTakeOutButton", new Object[]{takeOutButton});
    }
}
