package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzj extends zzdj {
    /* renamed from: ID */
    private static final String f6514ID = zzad.ARBITRARY_PIXEL.toString();
    private static final String URL = zzae.URL.toString();
    private static final String zzbnK = zzae.ADDITIONAL_PARAMS.toString();
    private static final String zzbnL = zzae.UNREPEATABLE.toString();
    static final String zzbnM;
    private static final Set<String> zzbnN = new HashSet();
    private final Context mContext;
    private final zza zzbnO;

    public interface zza {
        zzas zzJg();
    }

    static {
        String str = f6514ID;
        zzbnM = new StringBuilder(String.valueOf(str).length() + 17).append("gtm_").append(str).append("_unrepeatable").toString();
    }

    public zzj(final Context context) {
        this(context, new zza() {
            public zzas zzJg() {
                return zzz.zzbh(context);
            }
        });
    }

    zzj(Context context, zza zza) {
        super(f6514ID, URL);
        this.zzbnO = zza;
        this.mContext = context;
    }

    private synchronized boolean zzgf(String str) {
        boolean z = true;
        synchronized (this) {
            if (!zzgh(str)) {
                if (zzgg(str)) {
                    zzbnN.add(str);
                } else {
                    z = false;
                }
            }
        }
        return z;
    }

    public void zzX(Map<String, com.google.android.gms.internal.zzag.zza> map) {
        String zzg = map.get(zzbnL) != null ? zzdl.zzg((com.google.android.gms.internal.zzag.zza) map.get(zzbnL)) : null;
        if (zzg == null || !zzgf(zzg)) {
            String valueOf;
            Builder buildUpon = Uri.parse(zzdl.zzg((com.google.android.gms.internal.zzag.zza) map.get(URL))).buildUpon();
            com.google.android.gms.internal.zzag.zza zza = (com.google.android.gms.internal.zzag.zza) map.get(zzbnK);
            if (zza != null) {
                Object zzl = zzdl.zzl(zza);
                if (zzl instanceof List) {
                    for (Object zzl2 : (List) zzl2) {
                        if (zzl2 instanceof Map) {
                            for (Entry entry : ((Map) zzl2).entrySet()) {
                                buildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                            }
                        } else {
                            zzg = "ArbitraryPixel: additional params contains non-map: not sending partial hit: ";
                            valueOf = String.valueOf(buildUpon.build().toString());
                            zzbn.m7501e(valueOf.length() != 0 ? zzg.concat(valueOf) : new String(zzg));
                            return;
                        }
                    }
                }
                zzg = "ArbitraryPixel: additional params not a list: not sending partial hit: ";
                valueOf = String.valueOf(buildUpon.build().toString());
                zzbn.m7501e(valueOf.length() != 0 ? zzg.concat(valueOf) : new String(zzg));
                return;
            }
            valueOf = buildUpon.build().toString();
            this.zzbnO.zzJg().zzgw(valueOf);
            String str = "ArbitraryPixel: url = ";
            valueOf = String.valueOf(valueOf);
            zzbn.m7502v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            if (zzg != null) {
                synchronized (zzj.class) {
                    zzbnN.add(zzg);
                    zzdc.zzb(this.mContext, zzbnM, zzg, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzgg(String str) {
        return this.mContext.getSharedPreferences(zzbnM, 0).contains(str);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzgh(String str) {
        return zzbnN.contains(str);
    }
}
