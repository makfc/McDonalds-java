package com.mcdonalds.app.ordering.preparepayment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import com.ensighten.Ensighten;
import com.mcdonalds.app.ordering.IntentFragment;
import com.mcdonalds.app.p043ui.FragmentNotFoundFragment;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.sdk.modules.models.PaymentCard;

public class PreparePaymentActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String screen = extras == null ? "" : extras.getString(URLNavigationActivity.ARG_FRAGMENT, "");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(getScreenFragment(screen)));
        transaction.commit();
    }

    /* Access modifiers changed, original: protected */
    public Fragment getScreenFragment(@NonNull String screen) {
        Ensighten.evaluateEvent(this, "getScreenFragment", new Object[]{screen});
        if (screen.equals("prepare_payment")) {
            return new PreparePaymentFragment();
        }
        if (screen.equals("prepare_one_time_payment")) {
            return new PrepareOneTimePaymentFragment();
        }
        return new FragmentNotFoundFragment();
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        BasePreparePaymentFragment fragment = (BasePreparePaymentFragment) getDisplayedFragment();
        if (requestCode == 4081 && resultCode == -1) {
            if (data.hasExtra("Cash")) {
                fragment.cashAsPayment();
            } else {
                fragment.updatedPaymentCard((PaymentCard) data.getExtras().getParcelable("PaymentSelectionFragment.DATA_KEY"));
            }
        } else if (resultCode == 39) {
            setResult(39);
            finish();
        } else if (requestCode == 37 && resultCode == -1) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.detach(fragment);
            transaction.attach(fragment);
            transaction.commit();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        Ensighten.evaluateEvent(this, "onNewIntent", new Object[]{intent});
        super.onNewIntent(intent);
        if (getDisplayedFragment() instanceof IntentFragment) {
            ((IntentFragment) getDisplayedFragment()).onNewIntent(intent);
        }
    }
}
