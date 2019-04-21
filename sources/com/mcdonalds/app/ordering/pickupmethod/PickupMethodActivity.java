package com.mcdonalds.app.ordering.pickupmethod;

import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import com.mcdonalds.app.p043ui.URLActionBarActivity;

public class PickupMethodActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new PickupMethodFragment()));
        transaction.commit();
    }
}
