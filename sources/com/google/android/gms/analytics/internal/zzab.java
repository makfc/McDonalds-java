package com.google.android.gms.analytics.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzaa;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class zzab {
    private final Map<String, String> zzAd;
    private final List<Command> zzYA;
    private final long zzYB;
    private final long zzYC;
    private final int zzYD;
    private final boolean zzYE;
    private final String zzYF;

    public zzab(zzc zzc, Map<String, String> map, long j, boolean z) {
        this(zzc, map, j, z, 0, 0, null);
    }

    public zzab(zzc zzc, Map<String, String> map, long j, boolean z, long j2, int i) {
        this(zzc, map, j, z, j2, i, null);
    }

    public zzab(zzc zzc, Map<String, String> map, long j, boolean z, long j2, int i, List<Command> list) {
        String zza;
        zzaa.zzz(zzc);
        zzaa.zzz(map);
        this.zzYC = j;
        this.zzYE = z;
        this.zzYB = j2;
        this.zzYD = i;
        this.zzYA = list != null ? list : Collections.emptyList();
        this.zzYF = zzr(list);
        HashMap hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            if (zzl(entry.getKey())) {
                zza = zza(zzc, entry.getKey());
                if (zza != null) {
                    hashMap.put(zza, zzb(zzc, entry.getValue()));
                }
            }
        }
        for (Entry entry2 : map.entrySet()) {
            if (!zzl(entry2.getKey())) {
                zza = zza(zzc, entry2.getKey());
                if (zza != null) {
                    hashMap.put(zza, zzb(zzc, entry2.getValue()));
                }
            }
        }
        if (!TextUtils.isEmpty(this.zzYF)) {
            zzao.zzc(hashMap, "_v", this.zzYF);
            if (this.zzYF.equals("ma4.0.0") || this.zzYF.equals("ma4.0.1")) {
                hashMap.remove("adid");
            }
        }
        this.zzAd = Collections.unmodifiableMap(hashMap);
    }

    public static zzab zza(zzc zzc, zzab zzab, Map<String, String> map) {
        return new zzab(zzc, map, zzab.zznT(), zzab.zznV(), zzab.zznS(), zzab.zznR(), zzab.zznU());
    }

    private static String zza(zzc zzc, Object obj) {
        if (obj == null) {
            return null;
        }
        CharSequence obj2 = obj.toString();
        if (obj2.startsWith("&")) {
            obj2 = obj2.substring(1);
        }
        int length = obj2.length();
        if (length > 256) {
            obj2 = obj2.substring(0, 256);
            zzc.zzc("Hit param name is too long and will be trimmed", Integer.valueOf(length), obj2);
        }
        return TextUtils.isEmpty(obj2) ? null : obj2;
    }

    private static String zzb(zzc zzc, Object obj) {
        String obj2 = obj == null ? "" : obj.toString();
        int length = obj2.length();
        if (length <= 8192) {
            return obj2;
        }
        obj2 = obj2.substring(0, 8192);
        zzc.zzc("Hit param value is too long and will be trimmed", Integer.valueOf(length), obj2);
        return obj2;
    }

    private static boolean zzl(Object obj) {
        return obj == null ? false : obj.toString().startsWith("&");
    }

    private String zzq(String str, String str2) {
        zzaa.zzdl(str);
        zzaa.zzb(!str.startsWith("&"), (Object) "Short param name required");
        String str3 = (String) this.zzAd.get(str);
        return str3 != null ? str3 : str2;
    }

    private static String zzr(List<Command> list) {
        CharSequence value;
        if (list != null) {
            for (Command command : list) {
                if ("appendVersion".equals(command.getId())) {
                    value = command.getValue();
                    break;
                }
            }
        }
        value = null;
        return TextUtils.isEmpty(value) ? null : value;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ht=").append(this.zzYC);
        if (this.zzYB != 0) {
            stringBuffer.append(", dbId=").append(this.zzYB);
        }
        if (this.zzYD != 0) {
            stringBuffer.append(", appUID=").append(this.zzYD);
        }
        ArrayList<String> arrayList = new ArrayList(this.zzAd.keySet());
        Collections.sort(arrayList);
        for (String str : arrayList) {
            stringBuffer.append(", ");
            stringBuffer.append(str);
            stringBuffer.append("=");
            stringBuffer.append((String) this.zzAd.get(str));
        }
        return stringBuffer.toString();
    }

    public Map<String, String> zzm() {
        return this.zzAd;
    }

    public int zznR() {
        return this.zzYD;
    }

    public long zznS() {
        return this.zzYB;
    }

    public long zznT() {
        return this.zzYC;
    }

    public List<Command> zznU() {
        return this.zzYA;
    }

    public boolean zznV() {
        return this.zzYE;
    }

    public long zznW() {
        return zzao.zzbX(zzq("_s", "0"));
    }

    public String zznX() {
        return zzq("_m", "");
    }
}
