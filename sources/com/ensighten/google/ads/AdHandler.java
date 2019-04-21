package com.ensighten.google.ads;

import com.ensighten.C1747d;
import com.ensighten.C1845i;
import com.ensighten.Utils;
import com.google.ads.AdListener;
import com.google.ads.AdView;
import com.google.ads.InterstitialAd;

public class AdHandler {
    public static void handleLoadAd(Object source) {
        try {
            Object fieldValue;
            AdListener adListener;
            if (source instanceof AdView) {
                fieldValue = Utils.getFieldValue(Utils.getFieldValue(source, "a"), "k");
                if (Utils.getField(fieldValue.getClass(), "a").getType().isAssignableFrom(AdListener.class)) {
                    adListener = (AdListener) Utils.getFieldValue(fieldValue, "a");
                    if (adListener == null || !(adListener instanceof C1747d)) {
                        ((AdView) source).setAdListener(new C1747d(adListener));
                    }
                } else if (C1845i.m7359f()) {
                    C1845i.m7356d("Unable to find the AdListener member field on com.google.ads.AdView, can not set AdListener without potentially overwriting client code, no ad tracking available.");
                }
            } else if (source instanceof InterstitialAd) {
                fieldValue = Utils.getFieldValue(Utils.getFieldValue(source, "b"), "k");
                if (Utils.getField(fieldValue.getClass(), "a").getType().isAssignableFrom(AdListener.class)) {
                    adListener = (AdListener) Utils.getFieldValue(fieldValue, "a");
                    if (adListener == null || !(adListener instanceof C1747d)) {
                        ((InterstitialAd) source).setAdListener(new C1747d(adListener));
                    }
                } else if (C1845i.m7359f()) {
                    C1845i.m7356d("Unable to find the AdListener member field on com.google.ads.InterstitialAd, can not set AdListener without potentially overwriting client code, no ad tracking available");
                }
            }
        } catch (Exception e) {
            if (C1845i.m7359f()) {
                C1845i.m7353c(e);
            }
        }
    }
}
