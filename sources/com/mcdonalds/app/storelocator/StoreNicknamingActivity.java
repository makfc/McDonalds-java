package com.mcdonalds.app.storelocator;

import android.os.Bundle;
import com.mcdonalds.app.p043ui.URLActionBarActivity;

public class StoreNicknamingActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(getContainerResource(), passIntentExtrasAsArgument(new StoreNicknamingFragment())).commit();
    }
}
