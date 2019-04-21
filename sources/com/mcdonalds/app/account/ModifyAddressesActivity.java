package com.mcdonalds.app.account;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import android.view.MenuItem;
import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.util.LanguageUtil;
import com.mcdonalds.gma.hongkong.C2658R;

public class ModifyAddressesActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(C2658R.string.title_activity_modify_addresses));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new ModifyAddressesFragment()));
        transaction.commit();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        if (VERSION.SDK_INT >= 24) {
            LanguageUtil.changeAppLanguage(getResources(), LanguageUtil.getAppLanguage());
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldShowHamburgerMenu() {
        Ensighten.evaluateEvent(this, "shouldShowHamburgerMenu", null);
        return false;
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getDisplayedFragment();
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        ModifyAddressesFragment displayedFragment = (ModifyAddressesFragment) getDisplayedFragment();
        switch (item.getItemId()) {
            case 16908332:
                if (displayedFragment != null) {
                    return displayedFragment.onOptionsItemSelected(item);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        Ensighten.evaluateEvent(this, "onBackPressed", null);
        ((ModifyAddressesFragment) getDisplayedFragment()).selectSingleAddress();
    }
}
