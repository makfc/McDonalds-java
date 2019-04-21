package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.C2063R;

public class zzah {
    private final Resources zzass;
    private final String zzast = this.zzass.getResourcePackageName(C2063R.string.common_google_play_services_unknown_issue);

    public zzah(Context context) {
        zzaa.zzz(context);
        this.zzass = context.getResources();
    }

    public String getString(String str) {
        int identifier = this.zzass.getIdentifier(str, "string", this.zzast);
        return identifier == 0 ? null : this.zzass.getString(identifier);
    }
}
