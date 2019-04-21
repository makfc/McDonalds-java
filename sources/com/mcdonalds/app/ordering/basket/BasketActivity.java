package com.mcdonalds.app.ordering.basket;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import com.ensighten.Ensighten;
import com.mcdonalds.app.offers.OfferFragment;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.gma.hongkong.C2658R;

public class BasketActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        BasketFragment fragment = (BasketFragment) getDisplayedFragment();
        if (requestCode == ProductDetailsActivity.REQUEST_CODE.intValue() && resultCode == -1) {
            fragment.updateOrder();
            fragment.refreshStoreInfoAndDeliveryFee();
            fragment.prepareEditMode();
            fragment.refresh();
        }
        if ((requestCode == OfferFragment.REQUEST_CODE.intValue() && resultCode == -1) || requestCode == 18) {
            fragment.refresh();
        }
        if (resultCode == 39 || resultCode == 15207) {
            finish();
        }
        if (requestCode == 12730 && resultCode == -1) {
            fragment.removeUnavailableItemsComplete();
        }
        if (requestCode == 40) {
            fragment.updateOrder();
            fragment.updateOrderMethodSelectionHeader();
            fragment.refreshStoreInfoAndDeliveryFee();
            fragment.totalize();
        }
        if (requestCode != 154) {
            return;
        }
        if (resultCode == -1) {
            fragment.refresh();
        } else if (resultCode == 0) {
            fragment.preparePayment(false);
        } else if (requestCode == 100) {
            fragment.preparePayment(true);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(C2658R.string.title_activity_order_basket));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new BasketFragment()));
        transaction.commit();
    }

    public void onBackPressed() {
        Ensighten.evaluateEvent(this, "onBackPressed", null);
        BasketFragment fragment = (BasketFragment) getDisplayedFragment();
        if (fragment.isEditMode().booleanValue()) {
            fragment.closeEdit(null);
            fragment.editDone();
            return;
        }
        OrderingManager.getInstance().setProductErrorCode(1);
        finish();
    }
}
