package org.acra.collector;

import android.content.Context;
import android.content.res.Configuration;
import android.util.SparseArray;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import org.acra.ACRA;

public final class ConfigurationCollector {
    private final HashMap<String, SparseArray<String>> mValueArrays = new HashMap();

    private ConfigurationCollector() {
        SparseArray sparseArray = new SparseArray();
        SparseArray sparseArray2 = new SparseArray();
        SparseArray sparseArray3 = new SparseArray();
        SparseArray sparseArray4 = new SparseArray();
        SparseArray sparseArray5 = new SparseArray();
        SparseArray sparseArray6 = new SparseArray();
        SparseArray sparseArray7 = new SparseArray();
        SparseArray sparseArray8 = new SparseArray();
        SparseArray sparseArray9 = new SparseArray();
        for (Field field : Configuration.class.getFields()) {
            if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers())) {
                String name = field.getName();
                try {
                    if (name.startsWith("HARDKEYBOARDHIDDEN_")) {
                        sparseArray.put(field.getInt(null), name);
                    } else if (name.startsWith("KEYBOARD_")) {
                        sparseArray2.put(field.getInt(null), name);
                    } else if (name.startsWith("KEYBOARDHIDDEN_")) {
                        sparseArray3.put(field.getInt(null), name);
                    } else if (name.startsWith("NAVIGATION_")) {
                        sparseArray4.put(field.getInt(null), name);
                    } else if (name.startsWith("NAVIGATIONHIDDEN_")) {
                        sparseArray5.put(field.getInt(null), name);
                    } else if (name.startsWith("ORIENTATION_")) {
                        sparseArray6.put(field.getInt(null), name);
                    } else if (name.startsWith("SCREENLAYOUT_")) {
                        sparseArray7.put(field.getInt(null), name);
                    } else if (name.startsWith("TOUCHSCREEN_")) {
                        sparseArray8.put(field.getInt(null), name);
                    } else if (name.startsWith("UI_MODE_")) {
                        sparseArray9.put(field.getInt(null), name);
                    }
                } catch (IllegalArgumentException e) {
                    ACRA.log.mo23354w(ACRA.LOG_TAG, "Error while inspecting device configuration: ", e);
                } catch (IllegalAccessException e2) {
                    ACRA.log.mo23354w(ACRA.LOG_TAG, "Error while inspecting device configuration: ", e2);
                }
            }
        }
        this.mValueArrays.put("HARDKEYBOARDHIDDEN_", sparseArray);
        this.mValueArrays.put("KEYBOARD_", sparseArray2);
        this.mValueArrays.put("KEYBOARDHIDDEN_", sparseArray3);
        this.mValueArrays.put("NAVIGATION_", sparseArray4);
        this.mValueArrays.put("NAVIGATIONHIDDEN_", sparseArray5);
        this.mValueArrays.put("ORIENTATION_", sparseArray6);
        this.mValueArrays.put("SCREENLAYOUT_", sparseArray7);
        this.mValueArrays.put("TOUCHSCREEN_", sparseArray8);
        this.mValueArrays.put("UI_MODE_", sparseArray9);
    }

    private String toString(Configuration conf) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : conf.getClass().getFields()) {
            try {
                if (!Modifier.isStatic(field.getModifiers())) {
                    stringBuilder.append(field.getName()).append('=');
                    if (field.getType().equals(Integer.TYPE)) {
                        stringBuilder.append(getFieldValueName(conf, field));
                    } else if (field.get(conf) != null) {
                        stringBuilder.append(field.get(conf).toString());
                    }
                    stringBuilder.append(10);
                }
            } catch (IllegalArgumentException e) {
                ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while inspecting device configuration: ", e);
            } catch (IllegalAccessException e2) {
                ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while inspecting device configuration: ", e2);
            }
        }
        return stringBuilder.toString();
    }

    private String getFieldValueName(Configuration conf, Field f) throws IllegalAccessException {
        String name = f.getName();
        if (name.equals("mcc") || name.equals("mnc")) {
            return Integer.toString(f.getInt(conf));
        }
        if (name.equals("uiMode")) {
            return activeFlags((SparseArray) this.mValueArrays.get("UI_MODE_"), f.getInt(conf));
        }
        if (name.equals("screenLayout")) {
            return activeFlags((SparseArray) this.mValueArrays.get("SCREENLAYOUT_"), f.getInt(conf));
        }
        SparseArray sparseArray = (SparseArray) this.mValueArrays.get(name.toUpperCase() + '_');
        if (sparseArray == null) {
            return Integer.toString(f.getInt(conf));
        }
        name = (String) sparseArray.get(f.getInt(conf));
        if (name == null) {
            return Integer.toString(f.getInt(conf));
        }
        return name;
    }

    private static String activeFlags(SparseArray<String> valueNames, int bitfield) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= valueNames.size()) {
                return stringBuilder.toString();
            }
            int keyAt = valueNames.keyAt(i2);
            if (((String) valueNames.get(keyAt)).endsWith("_MASK")) {
                i = bitfield & keyAt;
                if (i > 0) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append('+');
                    }
                    stringBuilder.append((String) valueNames.get(i));
                }
            }
            i = i2 + 1;
        }
    }

    public static String collectConfiguration(Context context) {
        try {
            return new ConfigurationCollector().toString(context.getResources().getConfiguration());
        } catch (RuntimeException e) {
            ACRA.log.mo23354w(ACRA.LOG_TAG, "Couldn't retrieve CrashConfiguration for : " + context.getPackageName(), e);
            return "Couldn't retrieve crash config";
        }
    }
}
