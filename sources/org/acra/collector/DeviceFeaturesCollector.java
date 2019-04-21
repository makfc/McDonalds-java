package org.acra.collector;

import android.content.Context;
import android.content.pm.PackageManager;
import org.acra.ACRA;

final class DeviceFeaturesCollector {
    DeviceFeaturesCollector() {
    }

    public static String getFeatures(Context ctx) {
        if (Compatibility.getAPILevel() < 5) {
            return "Data available only with API Level >= 5";
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Object obj : (Object[]) PackageManager.class.getMethod("getSystemAvailableFeatures", null).invoke(ctx.getPackageManager(), new Object[0])) {
                String str = (String) obj.getClass().getField("name").get(obj);
                if (str != null) {
                    stringBuilder.append(str);
                } else {
                    str = (String) obj.getClass().getMethod("getGlEsVersion", null).invoke(obj, new Object[0]);
                    stringBuilder.append("glEsVersion = ");
                    stringBuilder.append(str);
                }
                stringBuilder.append("\n");
            }
        } catch (Throwable th) {
            ACRA.log.mo23354w(ACRA.LOG_TAG, "Couldn't retrieve DeviceFeatures for " + ctx.getPackageName(), th);
            stringBuilder.append("Could not retrieve data: ");
            stringBuilder.append(th.getMessage());
        }
        return stringBuilder.toString();
    }
}
