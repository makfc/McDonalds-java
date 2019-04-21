package com.mcdonalds.app.storelocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import com.mcdonalds.app.p043ui.URLActionBarActivity;

public class StoreDetailsActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new StoreDetailsFragment()));
        transaction.commit();
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getDisplayedFragment().onActivityResult(requestCode, resultCode, data);
    }
}
