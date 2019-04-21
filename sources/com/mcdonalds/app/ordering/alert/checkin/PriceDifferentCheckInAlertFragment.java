package com.mcdonalds.app.ordering.alert.checkin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.basket.BasketFragment;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.OrderResponse;

public class PriceDifferentCheckInAlertFragment extends CheckinAlertFragment {
    public int getFragmentResourceId() {
        Ensighten.evaluateEvent(this, "getFragmentResourceId", null);
        return C2658R.layout.fragment_check_in_price_changed;
    }

    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        TextView newPriceAlert = (TextView) v.findViewById(C2358R.C2357id.newPriceAlert);
        if (!getOutOfStockAndUnavailableProducts().isEmpty() || hasPriceChanged()) {
            newPriceAlert.setText(getString(C2658R.string.text_product_price_changed));
        } else {
            newPriceAlert.setText(getString(C2658R.string.alert_order_total_changed));
        }
        TextView newPriceView = (TextView) v.findViewById(C2358R.C2357id.newPrice);
        OrderResponse orderResponse = getOrderResponse();
        if (!(orderResponse == null || orderResponse.getTotalValue() == null)) {
            String newTotal = UIUtils.getLocalizedCurrencyFormatter().format(orderResponse.getTotalValue());
            newPriceView.setText(getString(C2658R.string.new_price_change_checkin, newTotal));
            newPriceView.setVisibility(0);
        }
        OrderingManager.getInstance().setProductErrorCode(OrderResponse.PRODUCT_PRICE_CHANGED);
        this.mBundle.putInt(BasketFragment.PARAMETER_PRODUCT_ERROR_CODE, OrderResponse.PRODUCT_PRICE_CHANGED);
        return v;
    }

    private boolean hasPriceChanged() {
        Ensighten.evaluateEvent(this, "hasPriceChanged", null);
        if (this.mOrder == null || this.mOrder.getTotalizeResult() == null || this.mOrder.getTotalizeResult().getTotalValue() == null || this.mOrder.getPreparePaymentResult() == null || this.mOrder.getPreparePaymentResult().getTotalValue() == null || Math.abs(this.mOrder.getTotalizeResult().getTotalValue().doubleValue() - this.mOrder.getPreparePaymentResult().getTotalValue().doubleValue()) <= 0.01d) {
            return false;
        }
        return true;
    }

    public void onPositiveButtonClicked() {
        Ensighten.evaluateEvent(this, "onPositiveButtonClicked", null);
        this.mOrder.setTotalizeBeforeCheckin(this.mOrder.getPreparePaymentResult());
        Intent intent = new Intent();
        intent.putExtras(this.mBundle);
        getActivity().setResult(-1, intent);
        getActivity().finish();
    }

    private OrderResponse getOrderResponse() {
        Ensighten.evaluateEvent(this, "getOrderResponse", null);
        if (this.mOrder == null) {
            return null;
        }
        return this.mOrder.getMostRecentOrderResponse();
    }
}
