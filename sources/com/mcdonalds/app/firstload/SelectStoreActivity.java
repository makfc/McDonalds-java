package com.mcdonalds.app.firstload;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p000v4.content.LocalBroadcastManager;
import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.storelocator.StoreLocatorContainerFragment;

public class SelectStoreActivity extends URLActionBarActivity {
    private BroadcastReceiver mReceiver;

    /* renamed from: com.mcdonalds.app.firstload.SelectStoreActivity$1 */
    class C31211 extends BroadcastReceiver {
        C31211() {
        }

        public void onReceive(Context context, Intent intent) {
            Ensighten.evaluateEvent(this, "onReceive", new Object[]{context, intent});
            SelectStoreActivity.this.finish();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String screen = extras != null ? extras.getString(URLNavigationActivity.ARG_FRAGMENT, "") : "";
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(getScreenFragment(screen)));
        transaction.commit();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.mcdonalds.app.REMOVE_FIND_STORE");
        this.mReceiver = new C31211();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mReceiver, filter);
    }

    /* Access modifiers changed, original: protected */
    public Fragment getScreenFragment(@NonNull String screen) {
        Ensighten.evaluateEvent(this, "getScreenFragment", new Object[]{screen});
        if (screen.equals("store_locator")) {
            return new StoreLocatorContainerFragment();
        }
        return new ChooseSearchMethodFragment();
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mReceiver);
    }
}
