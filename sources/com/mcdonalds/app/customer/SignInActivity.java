package com.mcdonalds.app.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import com.ensighten.Ensighten;
import com.facebook.internal.ServerProtocol;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.sdk.services.analytics.JiceArgs;

public class SignInActivity extends URLActionBarActivity {
    public static String ARG_ACCOUNT_TYPE = "account_type";
    public static String ARG_AUTH_TYPE = ServerProtocol.DIALOG_PARAM_AUTH_TYPE;
    public static String ARG_IS_ADDING_NEW_ACCOUNT = "new_account";

    /* Access modifiers changed, original: protected */
    public boolean shouldAutoSetParentIntent() {
        Ensighten.evaluateEvent(this, "shouldAutoSetParentIntent", null);
        return false;
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldShowHamburgerMenu() {
        Ensighten.evaluateEvent(this, "shouldShowHamburgerMenu", null);
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

    /* Access modifiers changed, original: protected */
    public Fragment getScreenFragment(@NonNull String screen) {
        Ensighten.evaluateEvent(this, "getScreenFragment", new Object[]{screen});
        if (screen.equals(JiceArgs.EVENT_CHECK_IN)) {
            return new SignInFragment();
        }
        if (screen.equals("signin_change_password")) {
            return new SignInChangePasswordFragment();
        }
        return super.getScreenFragment(screen);
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new SignInFragment()));
        transaction.commit();
    }

    public void changeFragment(Fragment frag) {
        Ensighten.evaluateEvent(this, "changeFragment", new Object[]{frag});
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(frag));
        transaction.commit();
    }
}
