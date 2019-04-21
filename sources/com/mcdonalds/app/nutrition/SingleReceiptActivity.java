package com.mcdonalds.app.nutrition;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import com.mcdonalds.app.ordering.menu.OrderDetailsFragment;
import com.mcdonalds.app.p043ui.URLBasketNavigationActivity;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class SingleReceiptActivity extends URLBasketNavigationActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new OrderDetailsFragment()));
        transaction.commit();
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1 && requestCode == 2) {
            finish();
            if (Configuration.getSharedInstance().getBooleanForKey("interface.ordering.showCartAfterOrderAgain")) {
                navigateToBasket();
            }
        }
    }
}
