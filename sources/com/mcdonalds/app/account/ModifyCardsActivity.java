package com.mcdonalds.app.account;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.util.LanguageUtil;
import com.mcdonalds.gma.hongkong.C2658R;

public class ModifyCardsActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getDisplayedFragment();
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(C2658R.string.title_activity_modify_cards));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new ModifyCardsFragment()));
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
