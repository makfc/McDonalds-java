package com.mcdonalds.app.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import com.ensighten.Ensighten;
import com.mcdonalds.app.nutrition.NutritionInformationActivity;
import com.mcdonalds.app.nutrition.TabbedNutritionInformationActivity;
import com.mcdonalds.app.offers.OfferActivity;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.connectors.utils.Utils;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class NavigationManager {
    private static NavigationManager sInstance;

    /* renamed from: com.mcdonalds.app.navigation.NavigationManager$1 */
    class C32891 implements OnClickListener {
        C32891() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    public static NavigationManager getInstance() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.navigation.NavigationManager", "getInstance", null);
        if (sInstance == null) {
            sInstance = new NavigationManager();
        }
        return sInstance;
    }

    public void showNutrition(Context context, String recipeId, String categoryName, String analyticsTag, Activity navigationActivity) {
        Ensighten.evaluateEvent(this, "showNutrition", new Object[]{context, recipeId, categoryName, analyticsTag, navigationActivity});
        if (context != null && recipeId != null) {
            if (Utils.checkConnection(navigationActivity)) {
                Class<? extends Activity> activityClass;
                Bundle extras = new Bundle();
                extras.putString("recipe_id", recipeId);
                if (categoryName != null) {
                    extras.putString("category_name", categoryName);
                }
                if (analyticsTag != null) {
                    extras.putString("ARG_ANALYTICS_TAG", analyticsTag);
                }
                if (Configuration.getSharedInstance().hasKey("interface.nutritionalInfo.tabbedNutritionalInfo")) {
                    activityClass = TabbedNutritionInformationActivity.class;
                } else {
                    activityClass = NutritionInformationActivity.class;
                }
                Intent intent = new Intent(context, activityClass);
                intent.putExtras(extras);
                context.startActivity(intent);
                return;
            }
            MCDAlertDialogBuilder.withContext(navigationActivity).setMessage(context.getString(C2658R.string.connectionless_error)).setPositiveButton((int) C2658R.string.f6083ok, new C32891()).create().show();
        }
    }

    public void showOffer(Context context, String offerId) {
        Ensighten.evaluateEvent(this, "showOffer", new Object[]{context, offerId});
        if (context != null && offerId != null) {
            Bundle extras = new Bundle();
            extras.putString("extra_offer", offerId);
            Intent intent = new Intent(context, OfferActivity.class);
            intent.putExtras(extras);
            context.startActivity(intent);
        }
    }
}
