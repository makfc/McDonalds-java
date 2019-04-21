package com.mcdonalds.app.ordering.instorepickup;

import android.os.Bundle;
import com.mcdonalds.app.p043ui.URLActionBarActivity;

public class DriveThruConfirmationActivity extends URLActionBarActivity {
    private DriveThruConfirmationFragment fragment;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.fragment = new DriveThruConfirmationFragment();
        getSupportFragmentManager().beginTransaction().replace(getContainerResource(), this.fragment).commit();
    }
}
