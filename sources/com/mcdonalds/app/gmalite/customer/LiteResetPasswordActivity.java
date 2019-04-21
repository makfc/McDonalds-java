package com.mcdonalds.app.gmalite.customer;

import android.os.Bundle;
import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.URLActionBarActivity;

public class LiteResetPasswordActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public boolean shouldAutoSetParentIntent() {
        Ensighten.evaluateEvent(this, "shouldAutoSetParentIntent", null);
        return false;
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(getContainerResource(), passIntentExtrasAsArgument(new LiteResetPasswordFragment())).commit();
    }
}
