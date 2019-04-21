package com.ensighten.model.activity;

import com.ensighten.C1845i;
import com.ensighten.Ensighten;

public class EnsightenActivityHandler {
    public static void onLifecycleMethod(Object thisRef, String lifecycleMethod, Object[] params) {
        try {
            Ensighten.evaluateEvent(thisRef, lifecycleMethod, params);
        } catch (Exception e) {
            if (C1845i.m7348a()) {
                C1845i.m7353c(e);
            }
        }
    }
}
