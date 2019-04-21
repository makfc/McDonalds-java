package org.acra.collector;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.acra.ACRA;

final class SharedPreferencesCollector {
    SharedPreferencesCollector() {
    }

    public static String collect(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        TreeMap treeMap = new TreeMap();
        treeMap.put("default", PreferenceManager.getDefaultSharedPreferences(context));
        String[] additionalSharedPreferences = ACRA.getConfig().additionalSharedPreferences();
        if (additionalSharedPreferences != null) {
            for (String str : additionalSharedPreferences) {
                treeMap.put(str, context.getSharedPreferences(str, 0));
            }
        }
        for (Entry entry : treeMap.entrySet()) {
            String str2 = (String) entry.getKey();
            Map all = ((SharedPreferences) entry.getValue()).getAll();
            if (all.isEmpty()) {
                stringBuilder.append(str2).append('=').append("empty\n");
            } else {
                for (String str3 : all.keySet()) {
                    if (filteredKey(str3)) {
                        ACRA.log.mo23347d(ACRA.LOG_TAG, "Filtered out sharedPreference=" + str2 + "  key=" + str3 + " due to filtering rule");
                    } else {
                        Object obj = all.get(str3);
                        stringBuilder.append(str2).append('.').append(str3).append('=');
                        stringBuilder.append(obj == null ? SafeJsonPrimitive.NULL_STRING : obj.toString());
                        stringBuilder.append("\n");
                    }
                }
                stringBuilder.append(10);
            }
        }
        return stringBuilder.toString();
    }

    private static boolean filteredKey(String key) {
        for (String matches : ACRA.getConfig().excludeMatchingSharedPreferencesKeys()) {
            if (key.matches(matches)) {
                return true;
            }
        }
        return false;
    }
}
