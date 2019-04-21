package com.mcdonalds.app.nutrition;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLBasketNavigationActivity;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class NutritionInformationActivity extends URLBasketNavigationActivity {
    /* Access modifiers changed, original: protected */
    public boolean shouldShowHamburgerMenu() {
        Ensighten.evaluateEvent(this, "shouldShowHamburgerMenu", null);
        return true;
    }

    /* Access modifiers changed, original: protected */
    public int getContainerResource() {
        Ensighten.evaluateEvent(this, "getContainerResource", null);
        return C2358R.C2357id.container;
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(getFragmentToDisplay()));
        transaction.commit();
    }

    private Fragment getFragmentToDisplay() {
        Ensighten.evaluateEvent(this, "getFragmentToDisplay", null);
        Boolean isNewPDP = (Boolean) Configuration.getSharedInstance().getValueForKey("interface.nutritionalInfo.useDigitalToolkitUI");
        if (isNewPDP == null || !isNewPDP.booleanValue()) {
            return new NutritionInformationOldUIFragment();
        }
        return new NutritionInformationToolkitUIFragment();
    }
}
