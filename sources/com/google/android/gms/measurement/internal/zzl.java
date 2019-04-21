package com.google.android.gms.measurement.internal;

import com.autonavi.amap.mapcore.VTMCDataCache;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzpg;
import com.mcdonalds.sdk.connectors.autonavi.AutoNaviConnector;

public final class zzl {
    public static zza<String> zzbcA = zza.zzT("measurement.config.url_authority", "app-measurement.com");
    public static zza<Integer> zzbcB = zza.zzA("measurement.upload.max_bundles", 100);
    public static zza<Integer> zzbcC = zza.zzA("measurement.upload.max_batch_size", 65536);
    public static zza<Integer> zzbcD = zza.zzA("measurement.upload.max_bundle_size", 65536);
    public static zza<Integer> zzbcE = zza.zzA("measurement.upload.max_events_per_bundle", 1000);
    public static zza<Integer> zzbcF = zza.zzA("measurement.upload.max_events_per_day", 100000);
    public static zza<Integer> zzbcG = zza.zzA("measurement.upload.max_public_events_per_day", AutoNaviConnector.DEFAULT_SEARCH_RADIUS);
    public static zza<Integer> zzbcH = zza.zzA("measurement.upload.max_conversions_per_day", VTMCDataCache.MAXSIZE);
    public static zza<Integer> zzbcI = zza.zzA("measurement.store.max_stored_events_per_app", 100000);
    public static zza<String> zzbcJ = zza.zzT("measurement.upload.url", "https://app-measurement.com/a");
    public static zza<Long> zzbcK = zza.zze("measurement.upload.backoff_period", 43200000);
    public static zza<Long> zzbcL = zza.zze("measurement.upload.window_interval", 3600000);
    public static zza<Long> zzbcM = zza.zze("measurement.upload.interval", 3600000);
    public static zza<Long> zzbcN = zza.zze("measurement.upload.stale_data_deletion_interval", 86400000);
    public static zza<Long> zzbcO = zza.zze("measurement.upload.initial_upload_delay_time", 15000);
    public static zza<Long> zzbcP = zza.zze("measurement.upload.retry_time", 1800000);
    public static zza<Integer> zzbcQ = zza.zzA("measurement.upload.retry_count", 6);
    public static zza<Long> zzbcR = zza.zze("measurement.upload.max_queue_time", 2419200000L);
    public static zza<Integer> zzbcS = zza.zzA("measurement.lifetimevalue.max_currency_tracked", 4);
    public static zza<Long> zzbcT = zza.zze("measurement.service_client.idle_disconnect_millis", 5000);
    public static zza<Boolean> zzbct = zza.zzn("measurement.service_enabled", true);
    public static zza<Boolean> zzbcu = zza.zzn("measurement.service_client_enabled", true);
    public static zza<String> zzbcv = zza.zzl("measurement.log_tag", "FA", "FA-SVC");
    public static zza<Long> zzbcw = zza.zze("measurement.ad_id_cache_time", 10000);
    public static zza<Long> zzbcx = zza.zze("measurement.monitoring.sample_period_millis", 86400000);
    public static zza<Long> zzbcy = zza.zzb("measurement.config.cache_time", 86400000, 3600000);
    public static zza<String> zzbcz = zza.zzT("measurement.config.url_scheme", "https");

    public static final class zza<V> {
        private final V zzYu;
        private final zzpg<V> zzYv;
        private final String zzwQ;

        private zza(String str, zzpg<V> zzpg, V v) {
            zzaa.zzz(zzpg);
            this.zzYv = zzpg;
            this.zzYu = v;
            this.zzwQ = str;
        }

        static zza<Integer> zzA(String str, int i) {
            return zzo(str, i, i);
        }

        static zza<String> zzT(String str, String str2) {
            return zzl(str, str2, str2);
        }

        static zza<Long> zzb(String str, long j, long j2) {
            return new zza(str, zzpg.zza(str, Long.valueOf(j2)), Long.valueOf(j));
        }

        static zza<Boolean> zzb(String str, boolean z, boolean z2) {
            return new zza(str, zzpg.zzl(str, z2), Boolean.valueOf(z));
        }

        static zza<Long> zze(String str, long j) {
            return zzb(str, j, j);
        }

        static zza<String> zzl(String str, String str2, String str3) {
            return new zza(str, zzpg.zzz(str, str3), str2);
        }

        static zza<Boolean> zzn(String str, boolean z) {
            return zzb(str, z, z);
        }

        static zza<Integer> zzo(String str, int i, int i2) {
            return new zza(str, zzpg.zza(str, Integer.valueOf(i2)), Integer.valueOf(i));
        }

        public V get() {
            return this.zzYu;
        }

        public V get(V v) {
            return v != null ? v : this.zzYu;
        }

        public String getKey() {
            return this.zzwQ;
        }
    }
}
