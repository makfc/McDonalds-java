package com.mcdonalds.app.nutrition;

import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import com.mcdonalds.app.p043ui.URLBasketNavigationActivity;
import com.mcdonalds.gma.hongkong.C2658R;

public class RecentsListActivity extends URLBasketNavigationActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(C2658R.string.title_activity_recents_list));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new RecentsListFragment()));
        transaction.commit();
    }
}
