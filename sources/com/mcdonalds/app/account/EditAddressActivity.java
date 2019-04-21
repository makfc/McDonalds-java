package com.mcdonalds.app.account;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import com.ensighten.Ensighten;
import com.mcdonalds.app.ordering.deliverymethod.addressproviders.EasyAddrUIFragment;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.util.LanguageUtil;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.data.LocalDataManager;

public class EditAddressActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(C2658R.string.title_activity_edit_address));
        Bundle extras = getIntent().getExtras();
        String screen = extras != null ? extras.getString(URLNavigationActivity.ARG_FRAGMENT, "") : "";
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(getScreenFragment(screen)));
        transaction.commit();
    }

    /* Access modifiers changed, original: protected */
    public Fragment getScreenFragment(@NonNull String screen) {
        Ensighten.evaluateEvent(this, "getScreenFragment", new Object[]{screen});
        if (screen.equals("easyaddrui")) {
            return new EasyAddrUIFragment();
        }
        return new EditAddressFragment();
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

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getDisplayedFragment();
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
