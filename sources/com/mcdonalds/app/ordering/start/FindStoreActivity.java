package com.mcdonalds.app.ordering.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.storelocator.StoreLocatorContainerFragment;
import com.mcdonalds.gma.hongkong.C2658R;

public class FindStoreActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(C2658R.string.title_activity_start_order_find_store));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new StoreLocatorContainerFragment()));
        transaction.commit();
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getDisplayedFragment().onActivityResult(requestCode, resultCode, data);
    }
}
