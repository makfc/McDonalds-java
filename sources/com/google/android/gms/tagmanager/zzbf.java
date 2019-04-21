package com.google.android.gms.tagmanager;

import com.facebook.internal.NativeProtocol;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class zzbf extends zzal {
    /* renamed from: ID */
    private static final String f6490ID = zzad.JOINER.toString();
    private static final String zzbpb = zzae.ARG0.toString();
    private static final String zzbpt = zzae.ITEM_SEPARATOR.toString();
    private static final String zzbpu = zzae.KEY_VALUE_SEPARATOR.toString();
    private static final String zzbpv = zzae.ESCAPE.toString();

    private enum zza {
        NONE,
        URL,
        BACKSLASH
    }

    public zzbf() {
        super(f6490ID, zzbpb);
    }

    private String zza(String str, zza zza, Set<Character> set) {
        switch (zza) {
            case URL:
                try {
                    return zzdp.zzgU(str);
                } catch (UnsupportedEncodingException e) {
                    zzbn.zzb("Joiner: unsupported encoding", e);
                    return str;
                }
            case BACKSLASH:
                String replace = str.replace("\\", "\\\\");
                Iterator it = set.iterator();
                while (true) {
                    String str2 = replace;
                    if (!it.hasNext()) {
                        return str2;
                    }
                    String ch = ((Character) it.next()).toString();
                    String str3 = "\\";
                    replace = String.valueOf(ch);
                    replace = str2.replace(ch, replace.length() != 0 ? str3.concat(replace) : new String(str3));
                }
            default:
                return str;
        }
    }

    private void zza(StringBuilder stringBuilder, String str, zza zza, Set<Character> set) {
        stringBuilder.append(zza(str, zza, set));
    }

    private void zza(Set<Character> set, String str) {
        for (int i = 0; i < str.length(); i++) {
            set.add(Character.valueOf(str.charAt(i)));
        }
    }

    public boolean zzJf() {
        return true;
    }

    public com.google.android.gms.internal.zzag.zza zzV(Map<String, com.google.android.gms.internal.zzag.zza> map) {
        com.google.android.gms.internal.zzag.zza zza = (com.google.android.gms.internal.zzag.zza) map.get(zzbpb);
        if (zza == null) {
            return zzdl.zzKT();
        }
        zza zza2;
        Set set;
        com.google.android.gms.internal.zzag.zza zza3 = (com.google.android.gms.internal.zzag.zza) map.get(zzbpt);
        String zzg = zza3 != null ? zzdl.zzg(zza3) : "";
        zza3 = (com.google.android.gms.internal.zzag.zza) map.get(zzbpu);
        String zzg2 = zza3 != null ? zzdl.zzg(zza3) : "=";
        zza zza4 = zza.NONE;
        zza3 = (com.google.android.gms.internal.zzag.zza) map.get(zzbpv);
        if (zza3 != null) {
            String zzg3 = zzdl.zzg(zza3);
            if (NativeProtocol.IMAGE_URL_KEY.equals(zzg3)) {
                zza2 = zza.URL;
                set = null;
            } else if ("backslash".equals(zzg3)) {
                zza2 = zza.BACKSLASH;
                set = new HashSet();
                zza(set, zzg);
                zza(set, zzg2);
                set.remove(Character.valueOf('\\'));
            } else {
                zzg = "Joiner: unsupported escape type: ";
                String valueOf = String.valueOf(zzg3);
                zzbn.m7501e(valueOf.length() != 0 ? zzg.concat(valueOf) : new String(zzg));
                return zzdl.zzKT();
            }
        }
        set = null;
        zza2 = zza4;
        StringBuilder stringBuilder = new StringBuilder();
        switch (zza.type) {
            case 2:
                Object obj = 1;
                com.google.android.gms.internal.zzag.zza[] zzaArr = zza.zzjK;
                int length = zzaArr.length;
                int i = 0;
                while (i < length) {
                    com.google.android.gms.internal.zzag.zza zza5 = zzaArr[i];
                    if (obj == null) {
                        stringBuilder.append(zzg);
                    }
                    zza(stringBuilder, zzdl.zzg(zza5), zza2, set);
                    i++;
                    obj = null;
                }
                break;
            case 3:
                for (int i2 = 0; i2 < zza.zzjL.length; i2++) {
                    if (i2 > 0) {
                        stringBuilder.append(zzg);
                    }
                    String zzg4 = zzdl.zzg(zza.zzjL[i2]);
                    String zzg5 = zzdl.zzg(zza.zzjM[i2]);
                    zza(stringBuilder, zzg4, zza2, set);
                    stringBuilder.append(zzg2);
                    zza(stringBuilder, zzg5, zza2, set);
                }
                break;
            default:
                zza(stringBuilder, zzdl.zzg(zza), zza2, set);
                break;
        }
        return zzdl.zzQ(stringBuilder.toString());
    }
}
