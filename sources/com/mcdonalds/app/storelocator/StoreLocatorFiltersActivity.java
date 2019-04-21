package com.mcdonalds.app.storelocator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.gma.hongkong.C2658R;

public class StoreLocatorFiltersActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(C2658R.string.title_activity_storelocator_filters));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new StoreLocatorFiltersFragment()));
        transaction.commit();
    }

    @SuppressLint({"NewApi"})
    public boolean onNavigateUp() {
        Ensighten.evaluateEvent(this, "onNavigateUp", null);
        if (getDisplayedFragment() != null) {
            ((StoreLocatorFiltersFragment) getDisplayedFragment()).performNewLocationSearch();
        }
        setResult(-1);
        finish();
        return true;
    }
}
