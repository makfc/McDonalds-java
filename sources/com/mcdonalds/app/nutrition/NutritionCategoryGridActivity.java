package com.mcdonalds.app.nutrition;

import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.URLBasketNavigationActivity;

public class NutritionCategoryGridActivity extends URLBasketNavigationActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new NutritionCategoryGridFragment()));
        transaction.commit();
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldShowHamburgerMenu() {
        Ensighten.evaluateEvent(this, "shouldShowHamburgerMenu", null);
        return true;
    }

    public void onBackPressed() {
        Ensighten.evaluateEvent(this, "onBackPressed", null);
        if (getDisplayedFragment() != null) {
            NutritionCategoryGridFragment nutritionCategoryGridFragment = (NutritionCategoryGridFragment) getDisplayedFragment();
            if (nutritionCategoryGridFragment.isSearchFocused()) {
                nutritionCategoryGridFragment.onBackPressed();
                return;
            } else {
                super.onBackPressed();
                return;
            }
        }
        super.onBackPressed();
    }
}
