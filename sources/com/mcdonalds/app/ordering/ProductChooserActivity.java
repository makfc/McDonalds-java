package com.mcdonalds.app.ordering;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import android.view.MenuItem;
import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.services.data.DataPasser;

public class ProductChooserActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getIntent().getExtras().getString("ARG_TITLE"));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new ProductChooserFragment()));
        transaction.commit();
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ProductChooserFragment displayedFragment;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 45352 && resultCode == -1) {
            displayedFragment = (ProductChooserFragment) getDisplayedFragment();
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
                        break;
                }
            }
        }
        if (requestCode == 64420) {
            displayedFragment = (ProductChooserFragment) getDisplayedFragment();
            if (displayedFragment != null) {
                OrderProduct product;
                switch (resultCode) {
                    case -1:
                        if (data.getExtras().containsKey("RESULT_PRODUCT")) {
                            product = (OrderProduct) data.getExtras().getParcelable("RESULT_PRODUCT");
                        } else {
                            product = (OrderProduct) DataPasser.getInstance().getData("RESULT_PRODUCT");
                        }
                        displayedFragment.updateChoice(product);
                        return;
                    case 0:
                        displayedFragment.updateChoice(null);
                        return;
                    case 64421:
                        if (data.getExtras().containsKey("RESULT_PRODUCT")) {
                            product = (OrderProduct) data.getExtras().getParcelable("RESULT_PRODUCT");
                        } else {
                            product = (OrderProduct) DataPasser.getInstance().getData("RESULT_PRODUCT");
                        }
                        displayedFragment.updateChoice(product);
                        displayedFragment.doneAction();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        ProductChooserFragment displayedFragment = (ProductChooserFragment) getDisplayedFragment();
        switch (item.getItemId()) {
            case 16908332:
                if (displayedFragment != null) {
                    return displayedFragment.onOptionsItemSelected(item);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        Ensighten.evaluateEvent(this, "onBackPressed", null);
        ((ProductChooserFragment) getDisplayedFragment()).doneAction();
    }
}
