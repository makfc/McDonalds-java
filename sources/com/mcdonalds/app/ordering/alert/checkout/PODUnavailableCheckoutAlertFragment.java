package com.mcdonalds.app.ordering.alert.checkout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.summary.OrderSummaryActivity;
import com.mcdonalds.app.ordering.utils.PODUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import java.util.List;

public class PODUnavailableCheckoutAlertFragment extends CheckoutAlertFragment implements AsyncListener<OrderResponse> {
    private TextView mMessage;
    private String mQRCode;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            this.mQRCode = args.getString("qr_code");
        }
    }

    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        this.mMessage = (TextView) view.findViewById(C2358R.C2357id.error_title);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        checkUnavailablePODs();
    }

    private void checkUnavailablePODs() {
        Ensighten.evaluateEvent(this, "checkUnavailablePODs", null);
        if (ListUtils.isNotEmpty(this.mOrder.getProducts())) {
            List<String> unavailable = PODUtils.getOrderUnavailablePODs(this.mOrder);
            if (unavailable.size() > 0) {
                this.mMessage.setText(PODUtils.getUnavailablePODMessage(unavailable, getResources()));
            }
        }
    }

    public int getFragmentResourceId() {
        Ensighten.evaluateEvent(this, "getFragmentResourceId", null);
        return C2658R.layout.fragment_check_out_items_unavailable;
    }

    public void onPositiveButtonClicked() {
        Ensighten.evaluateEvent(this, "onPositiveButtonClicked", null);
        checkIn();
    }

    private void checkIn() {
        Ensighten.evaluateEvent(this, "checkIn", null);
        UIUtils.startActivityIndicator(getNavigationActivity(), (int) C2658R.string.dialog_checking_in);
        OrderingManager.getInstance().checkIn(null, this.mQRCode, null, this);
    }

    public void onResponse(OrderResponse response, AsyncToken token, AsyncException exception) {
        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
        UIUtils.stopActivityIndicator();
        if (exception == null) {
            startActivity(OrderSummaryActivity.class, "order_summary");
        } else {
            AsyncException.report(exception);
        }
    }
}
