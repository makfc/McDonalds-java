package com.mcdonalds.app.ordering;

import android.os.Bundle;
import com.ensighten.Ensighten;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.p043ui.URLActionBarActivity;

public class LatestOrderActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(getContainerResource(), new LatestOrderFragment()).commit();
    }

    public void onBackPressed() {
        Ensighten.evaluateEvent(this, "onBackPressed", null);
        startActivity(MainActivity.class, "dashboard");
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldShowHamburgerMenu() {
        Ensighten.evaluateEvent(this, "shouldShowHamburgerMenu", null);
        return true;
    }
}
