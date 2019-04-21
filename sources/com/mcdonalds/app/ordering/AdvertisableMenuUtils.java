package com.mcdonalds.app.ordering;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Product;
import java.util.List;

public class AdvertisableMenuUtils {
    public static boolean isFavorite(Product product, List<Integer> favoriteCodes) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.AdvertisableMenuUtils", "isFavorite", new Object[]{product, favoriteCodes});
        if (product.isAdvertisable().booleanValue()) {
            return favoriteCodes.contains(Integer.valueOf(product.getAdvertisableBaseProductId()));
        }
        return favoriteCodes.contains(product.getExternalId());
    }

    public static void setItemTitle(Context context, TextView title, Product product) {
        int color;
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.AdvertisableMenuUtils", "setItemTitle", new Object[]{context, title, product});
        if (product.isAdvertisable().booleanValue()) {
            color = C2658R.color.mcd_red;
        } else {
            color = C2658R.color.mcd_black;
        }
        title.setText(product.getName());
        title.setTextColor(ContextCompat.getColor(context, color));
    }
}
