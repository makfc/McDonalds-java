package com.mcdonalds.app.web;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.URLBasketNavigationActivity;
import com.mcdonalds.app.util.LanguageUtil;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.data.LocalDataManager;

public class WebActivity extends URLBasketNavigationActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new WebFragment(getString(C2658R.string.title_loading))));
        transaction.commit();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        if (VERSION.SDK_INT >= 24) {
            LanguageUtil.changeAppLanguage(getResources(), LocalDataManager.getSharedInstance().getDeviceLanguage());
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldShowHamburgerMenu() {
        Ensighten.evaluateEvent(this, "shouldShowHamburgerMenu", null);
        return false;
    }

    public void onBackPressed() {
        Ensighten.evaluateEvent(this, "onBackPressed", null);
        Fragment fragment = getDisplayedFragment();
        if (fragment instanceof WebFragment) {
            WebFragment webFragment = (WebFragment) fragment;
            if (webFragment.canGoBack()) {
                webFragment.goBack();
                return;
            }
        }
        super.onBackPressed();
    }
}
