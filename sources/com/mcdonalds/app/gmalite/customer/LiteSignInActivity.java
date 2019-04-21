package com.mcdonalds.app.gmalite.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import com.ensighten.Ensighten;
import com.facebook.internal.ServerProtocol;
import com.mcdonalds.app.customer.SignInChangePasswordFragment;
import com.mcdonalds.app.p043ui.URLActionBarActivity;

public class LiteSignInActivity extends URLActionBarActivity {
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

    /* Access modifiers changed, original: protected */
    public Fragment getScreenFragment(@NonNull String screen) {
        Ensighten.evaluateEvent(this, "getScreenFragment", new Object[]{screen});
        if (screen.equals("gmalite_sign_up")) {
            return new LiteSignInFragment();
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
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new LiteSignInFragment()));
        transaction.commit();
    }
}
