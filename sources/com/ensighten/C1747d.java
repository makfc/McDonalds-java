package com.ensighten;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest.ErrorCode;

/* renamed from: com.ensighten.d */
public final class C1747d implements AdListener {
    /* renamed from: a */
    private AdListener f5838a;

    public C1747d(AdListener adListener) {
        this.f5838a = adListener;
    }

    public final void onDismissScreen(Ad ad) {
        Ensighten.evaluateNotification(new C1864y("onDismissScreen"));
        if (this.f5838a != null) {
            this.f5838a.onDismissScreen(ad);
        }
    }

    public final void onFailedToReceiveAd(Ad ad, ErrorCode err) {
        Ensighten.evaluateNotification(new C1864y("onFailedToReceiveAd"));
        if (this.f5838a != null) {
            this.f5838a.onFailedToReceiveAd(ad, err);
        }
    }

    public final void onLeaveApplication(Ad ad) {
        Ensighten.evaluateNotification(new C1864y("onLeaveApplication"));
        if (this.f5838a != null) {
            this.f5838a.onLeaveApplication(ad);
        }
    }

    public final void onPresentScreen(Ad ad) {
        Ensighten.evaluateNotification(new C1864y("onPresentScreen"));
        if (this.f5838a != null) {
            this.f5838a.onPresentScreen(ad);
        }
    }

    public final void onReceiveAd(Ad ad) {
        Ensighten.evaluateNotification(new C1864y("onReceiveAd"));
        if (this.f5838a != null) {
            this.f5838a.onReceiveAd(ad);
        }
    }
}
