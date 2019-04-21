package com.mcdonalds.app.analytics.ensighten;

import android.content.Context;
import com.ensighten.Ensighten;

public class EnsightenWrapper {
    public static void init(Context context) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.analytics.ensighten.EnsightenWrapper", "init", new Object[]{context});
    }
}
