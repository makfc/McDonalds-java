package com.mcdonalds.app.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import com.ensighten.Ensighten;
import com.mcdonalds.app.customer.MobileVerifyFragmet;
import com.mcdonalds.app.customer.UserValidationFragment;
import com.mcdonalds.app.p043ui.FragmentNotFoundFragment;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;

public class ProfileUpdateActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateFragment();
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        Ensighten.evaluateEvent(this, "onNewIntent", new Object[]{intent});
        setIntent(intent);
        updateFragment();
    }

    /* Access modifiers changed, original: protected */
    public Fragment getScreenFragment(@NonNull String screen) {
        Ensighten.evaluateEvent(this, "getScreenFragment", new Object[]{screen});
        if (screen.equals(ChangeMobileFragment.NAME)) {
            return new ChangeMobileFragment();
        }
        if (screen.equals(ChangeLoginPreferenceFragment.NAME)) {
            return new ChangeLoginPreferenceFragment();
        }
        if (screen.equals(ChangeEmailAddressFragment.NAME)) {
            return new ChangeEmailAddressFragment();
        }
        if (screen.equals("reset_password")) {
            return new ResetPasswordFragment();
        }
        if (screen.equals("mail_validation")) {
            return new UserValidationFragment();
        }
        if (screen.equals("mobile_take_over")) {
            return new MobileTakeOverFragment();
        }
        if (screen.equals("email_take_over")) {
            return new EmailAddressTakeOverFragment();
        }
        if (screen.equals("reset_password_confirmation")) {
            return new ResetPasswordConfirmationFragment();
        }
        if (screen.equals("mobile_verify")) {
            return new MobileVerifyFragmet();
        }
        return new FragmentNotFoundFragment();
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldShowHamburgerMenu() {
        Ensighten.evaluateEvent(this, "shouldShowHamburgerMenu", null);
        return true;
    }

    private void updateFragment() {
        Ensighten.evaluateEvent(this, "updateFragment", null);
        Bundle extras = getIntent().getExtras();
        String screen = extras == null ? "" : extras.getString(URLNavigationActivity.ARG_FRAGMENT, "");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(getScreenFragment(screen)));
        transaction.commitAllowingStateLoss();
    }
}
