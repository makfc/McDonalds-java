package com.mcdonalds.app.nutrition;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.URLBasketNavigationActivity;

public class AllFavoritesActivity extends URLBasketNavigationActivity {
    /* Access modifiers changed, original: protected */
    public boolean shouldShowHamburgerMenu() {
        Ensighten.evaluateEvent(this, "shouldShowHamburgerMenu", null);
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new AllFavoriteOrdersFragment()));
        transaction.commit();
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldAutoSetParentIntent() {
        Ensighten.evaluateEvent(this, "shouldAutoSetParentIntent", null);
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getDisplayedFragment();
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
