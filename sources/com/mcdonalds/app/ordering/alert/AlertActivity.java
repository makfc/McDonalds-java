package com.mcdonalds.app.ordering.alert;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import com.ensighten.Ensighten;
import com.mcdonalds.app.ordering.alert.checkin.AllItemsUnavailableCheckinAlertFragment;
import com.mcdonalds.app.ordering.alert.checkin.ItemsOutOfStockCheckinAlertFragment;
import com.mcdonalds.app.ordering.alert.checkin.ItemsTimeRestrictionCheckinAlertFragment;
import com.mcdonalds.app.ordering.alert.checkin.ItemsUnavailableCheckinAlertFragment;
import com.mcdonalds.app.ordering.alert.checkin.OffersNotAvailableCheckinAlertFragment;
import com.mcdonalds.app.ordering.alert.checkin.PriceDifferentCheckInAlertFragment;
import com.mcdonalds.app.ordering.alert.checkin.RestaurantMismatchFragment;
import com.mcdonalds.app.ordering.alert.checkout.ItemsOutOfStockCheckoutAlertFragment;
import com.mcdonalds.app.ordering.alert.checkout.ItemsUnavailableCheckoutAlertFragment;
import com.mcdonalds.app.ordering.alert.checkout.PODUnavailableCheckoutAlertFragment;
import com.mcdonalds.app.p043ui.FragmentNotFoundFragment;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.gma.hongkong.C2658R;

public class AlertActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getDisplayedFragment().onActivityResult(requestCode, resultCode, data);
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(C2658R.string.title_activity_main));
        Bundle extras = getIntent().getExtras();
        String screen = extras == null ? "" : extras.getString(URLNavigationActivity.ARG_FRAGMENT, "");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(getScreenFragment(screen)));
        transaction.commit();
    }

    /* Access modifiers changed, original: protected */
    public Fragment getScreenFragment(@NonNull String screen) {
        Ensighten.evaluateEvent(this, "getScreenFragment", new Object[]{screen});
        if (screen.equals("check_out_items_unavailable")) {
            return new ItemsUnavailableCheckoutAlertFragment();
        }
        if (screen.equals("check_in_items_unavailable")) {
            return new ItemsUnavailableCheckinAlertFragment();
        }
        if (screen.equals("check_in_all_items_unavailable")) {
            return new AllItemsUnavailableCheckinAlertFragment();
        }
        if (screen.equals("check_out_items_out_of_stock")) {
            return new ItemsOutOfStockCheckoutAlertFragment();
        }
        if (screen.equals("check_out_pod_unavailable")) {
            return new PODUnavailableCheckoutAlertFragment();
        }
        if (screen.equals("check_in_items_out_of_stock")) {
            return new ItemsOutOfStockCheckinAlertFragment();
        }
        if (screen.equals("check_in_items_time_restriction")) {
            return new ItemsTimeRestrictionCheckinAlertFragment();
        }
        if (screen.equals("check_in_price_different")) {
            return new PriceDifferentCheckInAlertFragment();
        }
        if (screen.equals("day_part_alert")) {
            return new DayPartAlertFragment();
        }
        if (screen.equals("check_in_offers_not_valid")) {
            return new OffersNotAvailableCheckinAlertFragment();
        }
        if (screen.equals("large_order_alert")) {
            return new LargeOrderAlertFragment();
        }
        if (screen.equals("large_order_call_alert")) {
            return new LargeOrderCallAlertFragment();
        }
        if (screen.equals("RestaurantMismatchFragment")) {
            return new RestaurantMismatchFragment();
        }
        return new FragmentNotFoundFragment();
    }
}
