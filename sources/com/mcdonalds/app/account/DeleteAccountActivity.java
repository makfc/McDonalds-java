package com.mcdonalds.app.account;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.URLActionBarActivity;

public class DeleteAccountActivity extends URLActionBarActivity {
    boolean mCanExitActivity = true;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new DeleteAccountFragment()));
        transaction.commit();
    }

    public void onBackPressed() {
        Ensighten.evaluateEvent(this, "onBackPressed", null);
        if (this.mCanExitActivity) {
            super.onBackPressed();
        }
    }

    @SuppressLint({"NewApi"})
    public boolean onNavigateUp() {
        Ensighten.evaluateEvent(this, "onNavigateUp", null);
        if (this.mCanExitActivity) {
            super.onNavigateUp();
        }
        return true;
    }

    public void setCanExitActivity(boolean canExitActivity) {
        Ensighten.evaluateEvent(this, "setCanExitActivity", new Object[]{new Boolean(canExitActivity)});
        this.mCanExitActivity = canExitActivity;
    }
}
