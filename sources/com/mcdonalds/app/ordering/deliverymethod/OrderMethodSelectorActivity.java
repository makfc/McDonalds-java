package com.mcdonalds.app.ordering.deliverymethod;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.DialogFragment;
import android.support.p000v4.app.FragmentTransaction;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.deliverymethod.OnDeliveryMethodChangeDialog.OnDeliveryMethodChangeDialogListener;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.models.Order;

public class OrderMethodSelectorActivity extends URLActionBarActivity implements OnDeliveryMethodChangeDialogListener {

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OrderMethodSelectorActivity$1 */
    class C34741 implements AsyncListener<Boolean> {
        C34741() {
        }

        public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
            int i = 0;
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            OrderMethodSelectorActivity orderMethodSelectorActivity = OrderMethodSelectorActivity.this;
            if (response.booleanValue()) {
                i = -1;
            }
            orderMethodSelectorActivity.setResult(i);
            OrderMethodSelectorActivity.this.finish();
        }
    }

    /* Access modifiers changed, original: protected */
    public int getContentViewResource() {
        Ensighten.evaluateEvent(this, "getContentViewResource", null);
        return C2658R.layout.activity_delivery_method_selector;
    }

    /* Access modifiers changed, original: protected */
    public int getContainerResource() {
        Ensighten.evaluateEvent(this, "getContainerResource", null);
        return C2358R.C2357id.container;
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldShowHamburgerMenu() {
        Ensighten.evaluateEvent(this, "shouldShowHamburgerMenu", null);
        return false;
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldAutoSetParentIntent() {
        Ensighten.evaluateEvent(this, "shouldAutoSetParentIntent", null);
        return false;
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new OrderMethodSelectorFragment()));
        transaction.commit();
    }

    public void onBackPressed() {
        Ensighten.evaluateEvent(this, "onBackPressed", null);
        if (getDisplayedFragment() != null) {
            ((OrderMethodSelectorFragment) getDisplayedFragment()).onBackPressed(new C34741());
        } else {
            super.onBackPressed();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (getDisplayedFragment() != null && resultCode == -1) {
            getDisplayedFragment().onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onDialogPositiveClick(DialogFragment dialog) {
        Ensighten.evaluateEvent(this, "onDialogPositiveClick", new Object[]{dialog});
        Order order = OrderingManager.getInstance().getCurrentOrder();
        order.clearOffers();
        order.clearProducts();
        setResult(39);
        if (getDisplayedFragment() != null && (getDisplayedFragment() instanceof OrderMethodSelectorFragment)) {
            ((OrderMethodSelectorFragment) getDisplayedFragment()).changeState(true);
        }
    }

    public void onDialogNegativeClick(DialogFragment dialog) {
        Ensighten.evaluateEvent(this, "onDialogNegativeClick", new Object[]{dialog});
        if (getDisplayedFragment() != null && (getDisplayedFragment() instanceof OrderMethodSelectorFragment)) {
            ((OrderMethodSelectorFragment) getDisplayedFragment()).changeState(false);
        }
    }
}
