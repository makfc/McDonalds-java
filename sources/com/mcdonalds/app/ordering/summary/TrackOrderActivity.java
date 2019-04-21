package com.mcdonalds.app.ordering.summary;

import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.gma.hongkong.C2658R;

public class TrackOrderActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(C2658R.string.title_activity_track_order));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new TrackOrderFragment()));
        transaction.commit();
    }
}
