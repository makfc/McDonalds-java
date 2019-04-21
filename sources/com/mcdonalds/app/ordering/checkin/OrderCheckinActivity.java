package com.mcdonalds.app.ordering.checkin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import com.ensighten.Ensighten;
import com.mcdonalds.app.ordering.IntentFragment;
import com.mcdonalds.app.ordering.basket.BasketActivity;
import com.mcdonalds.app.ordering.basket.BasketFragment;
import com.mcdonalds.app.ordering.instorepickup.EatInTakeOutFragment;
import com.mcdonalds.app.ordering.menu.MenuActivity;
import com.mcdonalds.app.p043ui.FragmentNotFoundFragment;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.PaymentCard;

public class OrderCheckinActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(C2658R.string.title_activity_order_checkin));
        Bundle extras = getIntent().getExtras();
        String screen = extras == null ? "" : extras.getString(URLNavigationActivity.ARG_FRAGMENT, "");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(getScreenFragment(screen)));
        transaction.commit();
    }

    /* Access modifiers changed, original: protected */
    public Fragment getScreenFragment(@NonNull String screen) {
        Ensighten.evaluateEvent(this, "getScreenFragment", new Object[]{screen});
        if (screen.equals("ordercheckin")) {
            return new OrderCheckinFragment();
        }
        if (screen.equals("one_time_order_checkin")) {
            return new OneTimeOrderCheckinFragment();
        }
        return new FragmentNotFoundFragment();
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        Ensighten.evaluateEvent(this, "onNewIntent", new Object[]{intent});
        if (getDisplayedFragment() instanceof IntentFragment) {
            ((IntentFragment) getDisplayedFragment()).onNewIntent(intent);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean shouldFinishActivity = false;
        Class<?> classToLaunch = null;
        Fragment displayedFragment = getDisplayedFragment();
        if (resultCode == -1) {
            if (isValidFragment(displayedFragment)) {
                OrderCheckinFragment fragment = (OrderCheckinFragment) displayedFragment;
                switch (requestCode) {
                    case 21:
                        int productErrorCode;
                        if (data != null) {
                            productErrorCode = data.getIntExtra(BasketFragment.PARAMETER_PRODUCT_ERROR_CODE, -1);
                        } else {
                            productErrorCode = -1;
                        }
                        fragment.resumeFromAlert(productErrorCode);
                        break;
                    case 51:
                        ((QRCodeListener) displayedFragment).doneScanningCode(data.getExtras().getString("result_code"));
                        break;
                    case 4081:
                        fragment.completeCheckIn((PaymentCard) data.getExtras().getParcelable("PaymentSelectionFragment.DATA_KEY"));
                        break;
                    case 4082:
                        fragment.resumeFromChoosePayment();
                        break;
                    case 4084:
                        fragment.checkin(false);
                        break;
                    case 4087:
                        fragment.resumeFromMismatch();
                        break;
                    case 12303:
                        fragment.totalizePayment();
                        break;
                }
            }
        } else if (resultCode == 39) {
            classToLaunch = MenuActivity.class;
        } else if (requestCode == 51) {
            shouldFinishActivity = true;
        } else if (requestCode == 4084) {
            shouldFinishActivity = true;
        } else if (requestCode == 4087) {
            shouldFinishActivity = true;
            classToLaunch = BasketActivity.class;
        } else if (requestCode == 4082 && (displayedFragment instanceof OneTimeOrderCheckinFragment)) {
            ((OneTimeOrderCheckinFragment) displayedFragment).reset();
        }
        if (classToLaunch != null) {
            startActivity(classToLaunch);
        }
        if (shouldFinishActivity) {
            finish();
        }
    }

    private boolean isValidFragment(Fragment displayedFragment) {
        Ensighten.evaluateEvent(this, "isValidFragment", new Object[]{displayedFragment});
        return (displayedFragment instanceof OrderCheckinFragment) || (displayedFragment instanceof EatInTakeOutFragment);
    }
}
