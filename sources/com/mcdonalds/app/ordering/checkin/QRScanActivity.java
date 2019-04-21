package com.mcdonalds.app.ordering.checkin;

import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.gma.hongkong.C2658R;

public class QRScanActivity extends URLNavigationActivity {
    /* Access modifiers changed, original: protected */
    public int getContentViewResource() {
        Ensighten.evaluateEvent(this, "getContentViewResource", null);
        return C2658R.layout.activity_qrscan;
    }

    /* Access modifiers changed, original: protected */
    public int getContainerResource() {
        Ensighten.evaluateEvent(this, "getContainerResource", null);
        return C2358R.C2357id.container;
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new QRScanFragment()));
        transaction.commit();
    }

    public void onBackPressed() {
        Ensighten.evaluateEvent(this, "onBackPressed", null);
        super.onBackPressed();
        finish();
    }
}
