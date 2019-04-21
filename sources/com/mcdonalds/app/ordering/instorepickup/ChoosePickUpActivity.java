package com.mcdonalds.app.ordering.instorepickup;

import android.os.Bundle;
import com.mcdonalds.app.p043ui.URLActionBarActivity;

public class ChoosePickUpActivity extends URLActionBarActivity {
    private ChoosePickUpFragment fragment;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.fragment = new ChoosePickUpFragment();
        getSupportFragmentManager().beginTransaction().replace(getContainerResource(), this.fragment).commit();
    }
}
