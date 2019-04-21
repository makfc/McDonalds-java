package com.mcdonalds.app.ordering;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.LocaleList;
import android.support.p000v4.app.FragmentTransaction;
import com.ensighten.Ensighten;
import com.mcdonalds.app.McDonaldsApplication;
import com.mcdonalds.app.ordering.customization.ProductCustomizationFragment;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.util.LanguageUtil;
import com.mcdonalds.gma.hongkong.C2658R;
import java.util.Locale;

public class ProductCustomizationActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(C2658R.string.title_activity_product_customization));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new ProductCustomizationFragment()));
        transaction.commit();
    }

    /* Access modifiers changed, original: protected */
    public void attachBaseContext(Context newBase) {
        Ensighten.evaluateEvent(this, "attachBaseContext", new Object[]{newBase});
        if (VERSION.SDK_INT >= 24) {
            Configuration config = McDonaldsApplication.getInstance().getResources().getConfiguration();
            config.setLocale(new Locale(LanguageUtil.getAppLanguage(), "HK"));
            config.setLocales(new LocaleList(new Locale[]{locale}));
            super.attachBaseContext(newBase.createConfigurationContext(config));
            return;
        }
        super.attachBaseContext(newBase);
    }
}
