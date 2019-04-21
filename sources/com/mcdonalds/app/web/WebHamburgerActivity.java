package com.mcdonalds.app.web;

import com.ensighten.Ensighten;

public class WebHamburgerActivity extends WebActivity {
    /* Access modifiers changed, original: protected */
    public boolean shouldShowHamburgerMenu() {
        Ensighten.evaluateEvent(this, "shouldShowHamburgerMenu", null);
        return true;
    }
}
