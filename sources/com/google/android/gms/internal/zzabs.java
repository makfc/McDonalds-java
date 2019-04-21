package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;
import java.util.HashMap;
import java.util.Map;

public class zzabs {
    private final Context mContext;
    private String zzbot;
    Map<String, Object> zzbuP;
    private final Map<String, Object> zzbuQ;
    private final zzabu zzbwb;
    private final zze zzsd;

    public zzabs(Context context) {
        this(context, new HashMap(), new zzabu(context), zzh.zzuW());
    }

    zzabs(Context context, Map<String, Object> map, zzabu zzabu, zze zze) {
        this.zzbot = null;
        this.zzbuP = new HashMap();
        this.mContext = context;
        this.zzsd = zze;
        this.zzbwb = zzabu;
        this.zzbuQ = map;
    }

    public void zzhn(String str) {
        this.zzbot = str;
    }
}
