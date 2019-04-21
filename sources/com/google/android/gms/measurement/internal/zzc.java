package com.google.android.gms.measurement.internal;

import android.support.annotation.WorkerThread;
import android.support.p000v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzsn.zza;
import com.google.android.gms.internal.zzsn.zzb;
import com.google.android.gms.internal.zzsn.zze;
import com.google.android.gms.internal.zzsp;
import com.google.android.gms.internal.zzsp.zzf;
import com.google.android.gms.internal.zzsp.zzg;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.AppMeasurement.zzd;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

class zzc extends zzaa {
    zzc(zzx zzx) {
        super(zzx);
    }

    private Boolean zza(zzb zzb, zzsp.zzb zzb2, long j) {
        Boolean zzah;
        if (zzb.zzbgq != null) {
            zzah = new zzs(zzb.zzbgq).zzah(j);
            if (zzah == null) {
                return null;
            }
            if (!zzah.booleanValue()) {
                return Boolean.valueOf(false);
            }
        }
        HashSet hashSet = new HashSet();
        for (com.google.android.gms.internal.zzsn.zzc zzc : zzb.zzbgo) {
            if (TextUtils.isEmpty(zzc.zzbgv)) {
                zzFm().zzFG().zzj("null or empty param name in filter. event", zzb2.name);
                return null;
            }
            hashSet.add(zzc.zzbgv);
        }
        ArrayMap arrayMap = new ArrayMap();
        for (com.google.android.gms.internal.zzsp.zzc zzc2 : zzb2.zzbgW) {
            if (hashSet.contains(zzc2.name)) {
                if (zzc2.zzbha != null) {
                    arrayMap.put(zzc2.name, zzc2.zzbha);
                } else if (zzc2.zzbgf != null) {
                    arrayMap.put(zzc2.name, zzc2.zzbgf);
                } else if (zzc2.zzasH != null) {
                    arrayMap.put(zzc2.name, zzc2.zzasH);
                } else {
                    zzFm().zzFG().zze("Unknown value for param. event, param", zzb2.name, zzc2.name);
                    return null;
                }
            }
        }
        for (com.google.android.gms.internal.zzsn.zzc zzc3 : zzb.zzbgo) {
            boolean equals = Boolean.TRUE.equals(zzc3.zzbgu);
            String str = zzc3.zzbgv;
            if (TextUtils.isEmpty(str)) {
                zzFm().zzFG().zzj("Event has empty param name. event", zzb2.name);
                return null;
            }
            Object obj = arrayMap.get(str);
            if (obj instanceof Long) {
                if (zzc3.zzbgt == null) {
                    zzFm().zzFG().zze("No number filter for long param. event, param", zzb2.name, str);
                    return null;
                }
                zzah = new zzs(zzc3.zzbgt).zzah(((Long) obj).longValue());
                if (zzah == null) {
                    return null;
                }
                if (((!zzah.booleanValue() ? 1 : 0) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj instanceof Double) {
                if (zzc3.zzbgt == null) {
                    zzFm().zzFG().zze("No number filter for double param. event, param", zzb2.name, str);
                    return null;
                }
                zzah = new zzs(zzc3.zzbgt).zzk(((Double) obj).doubleValue());
                if (zzah == null) {
                    return null;
                }
                if (((!zzah.booleanValue() ? 1 : 0) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj instanceof String) {
                if (zzc3.zzbgs == null) {
                    zzFm().zzFG().zze("No string filter for String param. event, param", zzb2.name, str);
                    return null;
                }
                zzah = new zzag(zzc3.zzbgs).zzfF((String) obj);
                if (zzah == null) {
                    return null;
                }
                if (((!zzah.booleanValue() ? 1 : 0) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj == null) {
                zzFm().zzFL().zze("Missing param for filter. event, param", zzb2.name, str);
                return Boolean.valueOf(false);
            } else {
                zzFm().zzFG().zze("Unknown param type. event, param", zzb2.name, str);
                return null;
            }
        }
        return Boolean.valueOf(true);
    }

    private Boolean zza(zze zze, zzg zzg) {
        Boolean bool = null;
        com.google.android.gms.internal.zzsn.zzc zzc = zze.zzbgD;
        if (zzc == null) {
            zzFm().zzFG().zzj("Missing property filter. property", zzg.name);
            return bool;
        }
        boolean equals = Boolean.TRUE.equals(zzc.zzbgu);
        if (zzg.zzbha != null) {
            if (zzc.zzbgt != null) {
                return zza(new zzs(zzc.zzbgt).zzah(zzg.zzbha.longValue()), equals);
            }
            zzFm().zzFG().zzj("No number filter for long property. property", zzg.name);
            return bool;
        } else if (zzg.zzbgf != null) {
            if (zzc.zzbgt != null) {
                return zza(new zzs(zzc.zzbgt).zzk(zzg.zzbgf.doubleValue()), equals);
            }
            zzFm().zzFG().zzj("No number filter for double property. property", zzg.name);
            return bool;
        } else if (zzg.zzasH == null) {
            zzFm().zzFG().zzj("User property has no value, property", zzg.name);
            return bool;
        } else if (zzc.zzbgs != null) {
            return zza(new zzag(zzc.zzbgs).zzfF(zzg.zzasH), equals);
        } else {
            if (zzc.zzbgt == null) {
                zzFm().zzFG().zzj("No string or number filter defined. property", zzg.name);
                return bool;
            }
            zzs zzs = new zzs(zzc.zzbgt);
            if (zzc.zzbgt.zzbgx == null || !zzc.zzbgt.zzbgx.booleanValue()) {
                if (zzfa(zzg.zzasH)) {
                    try {
                        return zza(zzs.zzah(Long.parseLong(zzg.zzasH)), equals);
                    } catch (NumberFormatException e) {
                        zzFm().zzFG().zze("User property value exceeded Long value range. property, value", zzg.name, zzg.zzasH);
                        return bool;
                    }
                }
                zzFm().zzFG().zze("Invalid user property value for Long number filter. property, value", zzg.name, zzg.zzasH);
                return bool;
            } else if (zzfb(zzg.zzasH)) {
                try {
                    double parseDouble = Double.parseDouble(zzg.zzasH);
                    if (!Double.isInfinite(parseDouble)) {
                        return zza(zzs.zzk(parseDouble), equals);
                    }
                    zzFm().zzFG().zze("User property value exceeded Double value range. property, value", zzg.name, zzg.zzasH);
                    return bool;
                } catch (NumberFormatException e2) {
                    zzFm().zzFG().zze("User property value exceeded Double value range. property, value", zzg.name, zzg.zzasH);
                    return bool;
                }
            } else {
                zzFm().zzFG().zze("Invalid user property value for Double number filter. property, value", zzg.name, zzg.zzasH);
                return bool;
            }
        }
    }

    static Boolean zza(Boolean bool, boolean z) {
        return bool == null ? null : Boolean.valueOf(bool.booleanValue() ^ z);
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void zza(String str, zza[] zzaArr) {
        zzaa.zzz(zzaArr);
        for (zza zza : zzaArr) {
            String str2;
            for (zzb zzb : zza.zzbgk) {
                str2 = (String) AppMeasurement.zza.zzbbm.get(zzb.zzbgn);
                if (str2 != null) {
                    zzb.zzbgn = str2;
                }
                for (com.google.android.gms.internal.zzsn.zzc zzc : zzb.zzbgo) {
                    str2 = (String) zzd.zzbbn.get(zzc.zzbgv);
                    if (str2 != null) {
                        zzc.zzbgv = str2;
                    }
                }
            }
            for (zze zze : zza.zzbgj) {
                str2 = (String) AppMeasurement.zze.zzbbo.get(zze.zzbgC);
                if (str2 != null) {
                    zze.zzbgC = str2;
                }
            }
        }
        zzFh().zzb(str, zzaArr);
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public zzsp.zza[] zza(String str, zzsp.zzb[] zzbArr, zzg[] zzgArr) {
        int intValue;
        BitSet bitSet;
        BitSet bitSet2;
        zzsp.zza zza;
        Map map;
        Map map2;
        zzsp.zza zza2;
        Boolean zza3;
        Object obj;
        Boolean obj2;
        zzaa.zzdl(str);
        HashSet hashSet = new HashSet();
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        Map zzfo = zzFh().zzfo(str);
        if (zzfo != null) {
            for (Integer intValue2 : zzfo.keySet()) {
                intValue = intValue2.intValue();
                zzf zzf = (zzf) zzfo.get(Integer.valueOf(intValue));
                bitSet = (BitSet) arrayMap2.get(Integer.valueOf(intValue));
                bitSet2 = (BitSet) arrayMap3.get(Integer.valueOf(intValue));
                if (bitSet == null) {
                    bitSet = new BitSet();
                    arrayMap2.put(Integer.valueOf(intValue), bitSet);
                    bitSet2 = new BitSet();
                    arrayMap3.put(Integer.valueOf(intValue), bitSet2);
                }
                for (int i = 0; i < zzf.zzbhC.length * 64; i++) {
                    if (zzal.zza(zzf.zzbhC, i)) {
                        zzFm().zzFL().zze("Filter already evaluated. audience ID, filter ID", Integer.valueOf(intValue), Integer.valueOf(i));
                        bitSet2.set(i);
                        if (zzal.zza(zzf.zzbhD, i)) {
                            bitSet.set(i);
                        }
                    }
                }
                zza = new zzsp.zza();
                arrayMap.put(Integer.valueOf(intValue), zza);
                zza.zzbgU = Boolean.valueOf(false);
                zza.zzbgT = zzf;
                zza.zzbgS = new zzf();
                zza.zzbgS.zzbhD = zzal.zza(bitSet);
                zza.zzbgS.zzbhC = zzal.zza(bitSet2);
            }
        }
        if (zzbArr != null) {
            ArrayMap arrayMap4 = new ArrayMap();
            int length = zzbArr.length;
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 >= length) {
                    break;
                }
                zzi zzi;
                zzsp.zzb zzb = zzbArr[i3];
                zzi zzO = zzFh().zzO(str, zzb.name);
                if (zzO == null) {
                    zzFm().zzFG().zzj("Event aggregate wasn't created during raw event logging. event", zzb.name);
                    zzi = new zzi(str, zzb.name, 1, 1, zzb.zzbgX.longValue());
                } else {
                    zzi = zzO.zzFA();
                }
                zzFh().zza(zzi);
                long j = zzi.zzbck;
                map = (Map) arrayMap4.get(zzb.name);
                if (map == null) {
                    map = zzFh().zzR(str, zzb.name);
                    if (map == null) {
                        map = new ArrayMap();
                    }
                    arrayMap4.put(zzb.name, map);
                    map2 = map;
                } else {
                    map2 = map;
                }
                zzFm().zzFL().zze("event, affected audience count", zzb.name, Integer.valueOf(map2.size()));
                for (Integer intValue22 : map2.keySet()) {
                    int intValue3 = intValue22.intValue();
                    if (hashSet.contains(Integer.valueOf(intValue3))) {
                        zzFm().zzFL().zzj("Skipping failed audience ID", Integer.valueOf(intValue3));
                    } else {
                        bitSet = (BitSet) arrayMap2.get(Integer.valueOf(intValue3));
                        bitSet2 = (BitSet) arrayMap3.get(Integer.valueOf(intValue3));
                        if (((zzsp.zza) arrayMap.get(Integer.valueOf(intValue3))) == null) {
                            zza2 = new zzsp.zza();
                            arrayMap.put(Integer.valueOf(intValue3), zza2);
                            zza2.zzbgU = Boolean.valueOf(true);
                            bitSet = new BitSet();
                            arrayMap2.put(Integer.valueOf(intValue3), bitSet);
                            bitSet2 = new BitSet();
                            arrayMap3.put(Integer.valueOf(intValue3), bitSet2);
                        }
                        for (zzb zzb2 : (List) map2.get(Integer.valueOf(intValue3))) {
                            if (zzFm().zzX(2)) {
                                zzFm().zzFL().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(intValue3), zzb2.zzbgm, zzb2.zzbgn);
                                zzFm().zzFL().zzj("Filter definition", zzal.zza(zzb2));
                            }
                            if (zzb2.zzbgm == null || zzb2.zzbgm.intValue() > 256) {
                                zzFm().zzFG().zzj("Invalid event filter ID. id", String.valueOf(zzb2.zzbgm));
                            } else if (bitSet.get(zzb2.zzbgm.intValue())) {
                                zzFm().zzFL().zze("Event filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue3), zzb2.zzbgm);
                            } else {
                                zza3 = zza(zzb2, zzb, j);
                                zzp.zza zzFL = zzFm().zzFL();
                                String str2 = "Event filter result";
                                if (zza3 == null) {
                                    obj2 = SafeJsonPrimitive.NULL_STRING;
                                } else {
                                    obj2 = zza3;
                                }
                                zzFL.zzj(str2, obj2);
                                if (zza3 == null) {
                                    hashSet.add(Integer.valueOf(intValue3));
                                } else {
                                    bitSet2.set(zzb2.zzbgm.intValue());
                                    if (zza3.booleanValue()) {
                                        bitSet.set(zzb2.zzbgm.intValue());
                                    }
                                }
                            }
                        }
                    }
                }
                i2 = i3 + 1;
            }
        }
        if (zzgArr != null) {
            ArrayMap arrayMap5 = new ArrayMap();
            for (zzg zzg : zzgArr) {
                map = (Map) arrayMap5.get(zzg.name);
                if (map == null) {
                    map = zzFh().zzS(str, zzg.name);
                    if (map == null) {
                        map = new ArrayMap();
                    }
                    arrayMap5.put(zzg.name, map);
                    map2 = map;
                } else {
                    map2 = map;
                }
                zzFm().zzFL().zze("property, affected audience count", zzg.name, Integer.valueOf(map2.size()));
                for (Integer intValue222 : map2.keySet()) {
                    int intValue4 = intValue222.intValue();
                    if (hashSet.contains(Integer.valueOf(intValue4))) {
                        zzFm().zzFL().zzj("Skipping failed audience ID", Integer.valueOf(intValue4));
                    } else {
                        bitSet = (BitSet) arrayMap2.get(Integer.valueOf(intValue4));
                        bitSet2 = (BitSet) arrayMap3.get(Integer.valueOf(intValue4));
                        if (((zzsp.zza) arrayMap.get(Integer.valueOf(intValue4))) == null) {
                            zza2 = new zzsp.zza();
                            arrayMap.put(Integer.valueOf(intValue4), zza2);
                            zza2.zzbgU = Boolean.valueOf(true);
                            bitSet = new BitSet();
                            arrayMap2.put(Integer.valueOf(intValue4), bitSet);
                            bitSet2 = new BitSet();
                            arrayMap3.put(Integer.valueOf(intValue4), bitSet2);
                        }
                        for (zze zze : (List) map2.get(Integer.valueOf(intValue4))) {
                            if (zzFm().zzX(2)) {
                                zzFm().zzFL().zzd("Evaluating filter. audience, filter, property", Integer.valueOf(intValue4), zze.zzbgm, zze.zzbgC);
                                zzFm().zzFL().zzj("Filter definition", zzal.zza(zze));
                            }
                            if (zze.zzbgm == null || zze.zzbgm.intValue() > 256) {
                                zzFm().zzFG().zzj("Invalid property filter ID. id", String.valueOf(zze.zzbgm));
                                hashSet.add(Integer.valueOf(intValue4));
                                break;
                            } else if (bitSet.get(zze.zzbgm.intValue())) {
                                zzFm().zzFL().zze("Property filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue4), zze.zzbgm);
                            } else {
                                zza3 = zza(zze, zzg);
                                zzp.zza zzFL2 = zzFm().zzFL();
                                String str3 = "Property filter result";
                                if (zza3 == null) {
                                    obj2 = SafeJsonPrimitive.NULL_STRING;
                                } else {
                                    obj2 = zza3;
                                }
                                zzFL2.zzj(str3, obj2);
                                if (zza3 == null) {
                                    hashSet.add(Integer.valueOf(intValue4));
                                } else {
                                    bitSet2.set(zze.zzbgm.intValue());
                                    if (zza3.booleanValue()) {
                                        bitSet.set(zze.zzbgm.intValue());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        zzsp.zza[] zzaArr = new zzsp.zza[arrayMap2.size()];
        int i4 = 0;
        for (Integer intValue2222 : arrayMap2.keySet()) {
            intValue = intValue2222.intValue();
            if (!hashSet.contains(Integer.valueOf(intValue))) {
                zza2 = (zzsp.zza) arrayMap.get(Integer.valueOf(intValue));
                if (zza2 == null) {
                    zza2 = new zzsp.zza();
                }
                zza = zza2;
                int i5 = i4 + 1;
                zzaArr[i4] = zza;
                zza.zzbgi = Integer.valueOf(intValue);
                zza.zzbgS = new zzf();
                zza.zzbgS.zzbhD = zzal.zza((BitSet) arrayMap2.get(Integer.valueOf(intValue)));
                zza.zzbgS.zzbhC = zzal.zza((BitSet) arrayMap3.get(Integer.valueOf(intValue)));
                zzFh().zza(str, intValue, zza.zzbgS);
                i4 = i5;
            }
        }
        return (zzsp.zza[]) Arrays.copyOf(zzaArr, i4);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzfa(String str) {
        return Pattern.matches("[+-]?[0-9]+", str);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzfb(String str) {
        return Pattern.matches("[+-]?(([0-9]+\\.?)|([0-9]*\\.[0-9]+))", str);
    }

    /* Access modifiers changed, original: protected */
    public void zzkO() {
    }
}
