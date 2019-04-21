package com.google.android.gms.common.util;

import android.support.p000v4.util.ArrayMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class zzf {
    public static <T> List<T> zzA(T t) {
        return Collections.singletonList(t);
    }

    public static <T> Set<T> zzB(T t) {
        return Collections.singleton(t);
    }

    public static <T> Set<T> zza(T t, T t2, T t3) {
        zza zza = new zza(3);
        zza.add(t);
        zza.add(t2);
        zza.add(t3);
        return Collections.unmodifiableSet(zza);
    }

    public static <T> Set<T> zza(T t, T t2, T t3, T t4) {
        zza zza = new zza(4);
        zza.add(t);
        zza.add(t2);
        zza.add(t3);
        zza.add(t4);
        return Collections.unmodifiableSet(zza);
    }

    private static <K, V> void zza(K[] kArr, V[] vArr) {
        if (kArr.length != vArr.length) {
            int length = kArr.length;
            throw new IllegalArgumentException("Key and values array lengths not equal: " + length + " != " + vArr.length);
        }
    }

    public static <K, V> Map<K, V> zzb(K[] kArr, V[] vArr) {
        int i = 0;
        zza(kArr, vArr);
        int length = kArr.length;
        switch (length) {
            case 0:
                return zzuV();
            case 1:
                return zze(kArr[0], vArr[0]);
            default:
                Map arrayMap = length <= 32 ? new ArrayMap(length) : new HashMap(length, 1.0f);
                while (i < length) {
                    arrayMap.put(kArr[i], vArr[i]);
                    i++;
                }
                return Collections.unmodifiableMap(arrayMap);
        }
    }

    public static <T> Set<T> zzc(T... tArr) {
        switch (tArr.length) {
            case 0:
                return zzuU();
            case 1:
                return zzB(tArr[0]);
            case 2:
                return zzd(tArr[0], tArr[1]);
            case 3:
                return zza(tArr[0], tArr[1], tArr[2]);
            case 4:
                return zza(tArr[0], tArr[1], tArr[2], tArr[3]);
            default:
                return Collections.unmodifiableSet(tArr.length <= 32 ? new zza(Arrays.asList(tArr)) : new HashSet(Arrays.asList(tArr)));
        }
    }

    public static <T> Set<T> zzd(T t, T t2) {
        zza zza = new zza(2);
        zza.add(t);
        zza.add(t2);
        return Collections.unmodifiableSet(zza);
    }

    public static <K, V> Map<K, V> zze(K k, V v) {
        return Collections.singletonMap(k, v);
    }

    public static <T> Set<T> zzuU() {
        return Collections.emptySet();
    }

    public static <K, V> Map<K, V> zzuV() {
        return Collections.emptyMap();
    }
}
