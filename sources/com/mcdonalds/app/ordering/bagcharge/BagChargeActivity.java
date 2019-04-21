package com.mcdonalds.app.ordering.bagcharge;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.ensighten.Ensighten;
import com.mcdonalds.app.databinding.ActivityBagChargeBinding;
import com.mcdonalds.app.databinding.BoundProductDetailsItemBinding;
import com.mcdonalds.app.ordering.checkout.CheckoutActivity;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ordering.OrderManager;

public class BagChargeActivity extends URLActionBarActivity implements BagChargeView {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBagChargeBinding binding = (ActivityBagChargeBinding) DataBindingUtil.setContentView(this, C2658R.layout.activity_bag_charge);
        final BagChargePresenter presenter = new BagChargePresenter(this);
        binding.setPresenter(presenter);
        setTitle(C2658R.string.bag_charge_title);
        BoundProductDetailsItemBinding productDetails = binding.productDetails;
        productDetails.productCheckBox.setVisibility(8);
        productDetails.infoButton.setVisibility(8);
        productDetails.hatButton.setVisibility(8);
        binding.quantityControls.increaseQuantity.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                presenter.increaseQuantity();
            }
        });
        binding.quantityControls.decreaseQuantity.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                presenter.decreaseQuantity();
            }
        });
        binding.dismissButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                OrderManager orderManager = OrderManager.getInstance();
                if (orderManager.allowBagCharges() && orderManager.isBagChargeAdded()) {
                    OrderManager.getInstance().cleanBagsFromOrder();
                }
                presenter.dismiss();
            }
        });
        binding.continueButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                OrderManager orderManager = OrderManager.getInstance();
                if (orderManager.allowBagCharges() && orderManager.isBagChargeAdded()) {
                    OrderManager.getInstance().cleanBagsFromOrder();
                }
                presenter.confirm();
            }
        });
    }

    public void proceedToCheckout() {
        Ensighten.evaluateEvent(this, "proceedToCheckout", null);
        Bundle extras = new Bundle();
        extras.putBoolean("FROM_BAG_CHARGE", true);
        Intent intent = new Intent(this, CheckoutActivity.class);
        intent.putExtras(extras);
        startActivityForResult(intent, 18);
    }
}
