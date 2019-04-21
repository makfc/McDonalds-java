package com.mcdonalds.app.offers;

import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.storelocator.StoreLocatorContainerFragment;

public class OffersInStoreActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new StoreLocatorContainerFragment()));
        transaction.commit();
    }
}
