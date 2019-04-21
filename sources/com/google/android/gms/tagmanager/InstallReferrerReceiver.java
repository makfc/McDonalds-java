package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.CampaignTrackingService;

public final class InstallReferrerReceiver extends CampaignTrackingReceiver {
    /* Access modifiers changed, original: protected */
    public void zzh(Context context, String str) {
        zzbe.zzgB(str);
        zzbe.zzr(context, str);
    }

    /* Access modifiers changed, original: protected */
    public Class<? extends CampaignTrackingService> zzko() {
        return InstallReferrerService.class;
    }
}
