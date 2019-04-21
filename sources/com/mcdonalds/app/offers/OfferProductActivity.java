package com.mcdonalds.app.offers;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import android.view.MenuItem;
import com.ensighten.Ensighten;
import com.mcdonalds.app.ordering.customization.ProductCustomizationFragment;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.services.data.DataPasser;

public class OfferProductActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new OfferProductFragment()));
        transaction.commit();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        OfferProductFragment offerProductFragment = (OfferProductFragment) getDisplayedFragment();
        if (offerProductFragment != null) {
            return offerProductFragment.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 45352 && resultCode == -1) {
            OfferProductFragment displayedFragment = (OfferProductFragment) getDisplayedFragment();
            if (displayedFragment != null) {
                switch (resultCode) {
                    case -1:
                        OrderProduct ingredient;
                        if (data.getExtras().containsKey(ProductCustomizationFragment.RESULT_PRODUCT)) {
                            ingredient = (OrderProduct) data.getExtras().getParcelable(ProductCustomizationFragment.RESULT_PRODUCT);
                        } else {
                            ingredient = (OrderProduct) DataPasser.getInstance().getData(ProductCustomizationFragment.RESULT_PRODUCT);
                        }
                        displayedFragment.updateCustomizationText(ingredient);
                        return;
                    default:
                        return;
                }
            }
        }
    }
}
