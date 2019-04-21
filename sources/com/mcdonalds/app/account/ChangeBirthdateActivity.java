package com.mcdonalds.app.account;

import android.os.Bundle;
import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.URLActionBarActivity;

public class ChangeBirthdateActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(getContainerResource(), passIntentExtrasAsArgument(new ChangeBirthdateFragment())).commit();
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldShowHamburgerMenu() {
        Ensighten.evaluateEvent(this, "shouldShowHamburgerMenu", null);
        return true;
    }
}
