package com.mcdonalds.app.p043ui.listview;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.ensighten.Ensighten;

/* renamed from: com.mcdonalds.app.ui.listview.AdapterViewUtil */
class AdapterViewUtil {
    AdapterViewUtil() {
    }

    public static int getPositionForView(AdapterView<?> adapterView, View view) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.listview.AdapterViewUtil", "getPositionForView", new Object[]{adapterView, view});
        int position = adapterView.getPositionForView(view);
        if (adapterView instanceof ListView) {
            return position - ((ListView) adapterView).getHeaderViewsCount();
        }
        return position;
    }
}
