package com.mcdonalds.app.ordering;

import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import android.view.KeyEvent;
import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.URLActionBarActivity;

public class PaymentProviderActivity extends URLActionBarActivity {
    public static boolean enableBackButton = true;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new PaymentProviderFragment()));
        transaction.commit();
    }

    public void onBackPressed() {
        Ensighten.evaluateEvent(this, "onBackPressed", null);
        if (enableBackButton) {
            super.onBackPressed();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Ensighten.evaluateEvent(this, "onKeyDown", new Object[]{new Integer(keyCode), event});
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        onBackPressed();
        return false;
    }
}
