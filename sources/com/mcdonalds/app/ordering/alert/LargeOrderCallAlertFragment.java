package com.mcdonalds.app.ordering.alert;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.basket.BasketActivity;
import com.mcdonalds.app.ordering.summary.OrderSummaryActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity.PermissionListener;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class LargeOrderCallAlertFragment extends AlertFragment {
    private boolean mLargeOrderError;
    private String mPhoneNumber;

    /* renamed from: com.mcdonalds.app.ordering.alert.LargeOrderCallAlertFragment$1 */
    class C33941 implements PermissionListener {
        C33941() {
        }

        public void onRequestPermissionsResult(int requestCode, String permission, int grantResult) {
            Ensighten.evaluateEvent(this, "onRequestPermissionsResult", new Object[]{new Integer(requestCode), permission, new Integer(grantResult)});
            if (grantResult != -1) {
                LargeOrderCallAlertFragment.access$000(LargeOrderCallAlertFragment.this);
            }
        }
    }

    static /* synthetic */ void access$000(LargeOrderCallAlertFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.alert.LargeOrderCallAlertFragment", "access$000", new Object[]{x0});
        x0.makeCall();
    }

    public int getFragmentResourceId() {
        Ensighten.evaluateEvent(this, "getFragmentResourceId", null);
        return C2658R.layout.fragment_large_order_call_alert;
    }

    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String stringForKey;
        CharSequence string;
        if (getArguments() != null) {
            this.mLargeOrderError = getArguments().getBoolean("large_order");
        }
        View view = inflater.inflate(getFragmentResourceId(), null);
        this.mNegativeButton = (Button) view.findViewById(C2358R.C2357id.button_negative);
        this.mNegativeButton.setOnClickListener(this);
        this.mPositiveButton = (Button) view.findViewById(C2358R.C2357id.button_positive);
        this.mPositiveButton.setOnClickListener(this);
        boolean isDelivery = OrderingManager.getInstance().getCurrentOrder().isDelivery();
        if (isDelivery) {
            stringForKey = Configuration.getSharedInstance().getStringForKey("interface.confirmationNeededOrders.deliveryCallCenter");
        } else {
            stringForKey = Configuration.getSharedInstance().getStringForKey("interface.confirmationNeededOrders.mcdonaldsCallCenter");
        }
        this.mPhoneNumber = stringForKey;
        TextView instructions = (TextView) view.findViewById(C2358R.C2357id.instructions);
        if (isDelivery) {
            string = getString(C2658R.string.alert_user_calls_text_delivery, this.mPhoneNumber);
        } else {
            string = getString(C2658R.string.alert_user_calls_text, this.mPhoneNumber);
        }
        instructions.setText(string);
        return view;
    }

    public void onNegativeButtonClicked() {
        Ensighten.evaluateEvent(this, "onNegativeButtonClicked", null);
        if (this.mLargeOrderError) {
            getActivity().finish();
            startActivity(BasketActivity.class, "basket");
            return;
        }
        startActivity(OrderSummaryActivity.class, "order_summary");
    }

    public void onPositiveButtonClicked() {
        Ensighten.evaluateEvent(this, "onPositiveButtonClicked", null);
        if (getActivity() instanceof URLNavigationActivity) {
            URLNavigationActivity activity = (URLNavigationActivity) getActivity();
            if (activity.isPermissionGranted("android.permission.CALL_PHONE")) {
                makeCall();
                return;
            }
            activity.requestPermission("android.permission.CALL_PHONE", 2, C2658R.string.permission_explanation_call_phone, new C33941());
        }
    }

    private void makeCall() {
        Ensighten.evaluateEvent(this, "makeCall", null);
        if (this.mPhoneNumber != null) {
            Intent intent = new Intent("android.intent.action.CALL");
            intent.setData(Uri.parse("tel:" + this.mPhoneNumber));
            startActivity(intent);
        }
    }
}
