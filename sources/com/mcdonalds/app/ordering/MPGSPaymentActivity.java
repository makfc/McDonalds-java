package com.mcdonalds.app.ordering;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.util.LanguageUtil;
import com.mcdonalds.gma.hongkong.C2658R;

public class MPGSPaymentActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(C2658R.string.add_new_card_title));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new MPGSPaymentFragment()));
        transaction.commit();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        if (VERSION.SDK_INT >= 24) {
            LanguageUtil.changeAppLanguage(getResources(), LanguageUtil.getAppLanguage());
        }
    }
}
