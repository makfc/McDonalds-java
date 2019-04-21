package com.mcdonalds.app.ordering.summary;

import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import com.ensighten.Ensighten;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.gma.hongkong.C2658R;

public class OrderSummaryActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(C2658R.string.title_activity_order_summary));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new OrderSummaryFragment()));
        transaction.commit();
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldAutoSetParentIntent() {
        Ensighten.evaluateEvent(this, "shouldAutoSetParentIntent", null);
        return false;
    }

    public void onBackPressed() {
        Ensighten.evaluateEvent(this, "onBackPressed", null);
        Bundle bundle = new Bundle();
        bundle.putBoolean("REFRESH_LAST_ORDER", true);
        startActivity(MainActivity.class, bundle);
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldNavigateUp() {
        Ensighten.evaluateEvent(this, "shouldNavigateUp", null);
        return false;
    }
}
