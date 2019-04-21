package com.mcdonalds.app.ordering.instorepickup;

import android.os.Bundle;
import com.mcdonalds.app.ordering.checkin.OrderCheckinActivity;

public class EatInTakeOutActivity extends OrderCheckinActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(getContainerResource(), new EatInTakeOutFragment()).commit();
    }
}
