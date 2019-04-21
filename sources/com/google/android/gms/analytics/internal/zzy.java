package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzpg;
import com.newrelic.agent.android.payload.PayloadController;
import com.newrelic.agent.android.tracing.ActivityTrace;

public final class zzy {
    public static zza<Boolean> zzXD = zza.zzh("analytics.service_enabled", false);
    public static zza<Boolean> zzXE = zza.zzh("analytics.service_client_enabled", true);
    public static zza<String> zzXF = zza.zze("analytics.log_tag", "GAv4", "GAv4-SVC");
    public static zza<Long> zzXG = zza.zzb("analytics.max_tokens", 60);
    public static zza<Float> zzXH = zza.zza("analytics.tokens_per_sec", 0.5f);
    public static zza<Integer> zzXI = zza.zza("analytics.max_stored_hits", (int) ActivityTrace.MAX_TRACES, 20000);
    public static zza<Integer> zzXJ = zza.zzd("analytics.max_stored_hits_per_app", ActivityTrace.MAX_TRACES);
    public static zza<Integer> zzXK = zza.zzd("analytics.max_stored_properties_per_app", 100);
    public static zza<Long> zzXL = zza.zza("analytics.local_dispatch_millis", 1800000, (long) PayloadController.PAYLOAD_REQUEUE_PERIOD_MS);
    public static zza<Long> zzXM = zza.zza("analytics.initial_local_dispatch_millis", 5000, 5000);
    public static zza<Long> zzXN = zza.zzb("analytics.min_local_dispatch_millis", PayloadController.PAYLOAD_REQUEUE_PERIOD_MS);
    public static zza<Long> zzXO = zza.zzb("analytics.max_local_dispatch_millis", 7200000);
    public static zza<Long> zzXP = zza.zzb("analytics.dispatch_alarm_millis", 7200000);
    public static zza<Long> zzXQ = zza.zzb("analytics.max_dispatch_alarm_millis", 32400000);
    public static zza<Integer> zzXR = zza.zzd("analytics.max_hits_per_dispatch", 20);
    public static zza<Integer> zzXS = zza.zzd("analytics.max_hits_per_batch", 20);
    public static zza<String> zzXT = zza.zzp("analytics.insecure_host", "http://www.google-analytics.com");
    public static zza<String> zzXU = zza.zzp("analytics.secure_host", "https://ssl.google-analytics.com");
    public static zza<String> zzXV = zza.zzp("analytics.simple_endpoint", "/collect");
    public static zza<String> zzXW = zza.zzp("analytics.batching_endpoint", "/batch");
    public static zza<Integer> zzXX = zza.zzd("analytics.max_get_length", 2036);
    public static zza<String> zzXY = zza.zze("analytics.batching_strategy.k", zzm.BATCH_BY_COUNT.name(), zzm.BATCH_BY_COUNT.name());
    public static zza<String> zzXZ = zza.zzp("analytics.compression_strategy.k", zzo.GZIP.name());
    public static zza<Integer> zzYa = zza.zzd("analytics.max_hits_per_request.k", 20);
    public static zza<Integer> zzYb = zza.zzd("analytics.max_hit_length.k", 8192);
    public static zza<Integer> zzYc = zza.zzd("analytics.max_post_length.k", 8192);
    public static zza<Integer> zzYd = zza.zzd("analytics.max_batch_post_length", 8192);
    public static zza<String> zzYe = zza.zzp("analytics.fallback_responses.k", "404,502");
    public static zza<Integer> zzYf = zza.zzd("analytics.batch_retry_interval.seconds.k", 3600);
    public static zza<Long> zzYg = zza.zzb("analytics.service_monitor_interval", 86400000);
    public static zza<Integer> zzYh = zza.zzd("analytics.http_connection.connect_timeout_millis", 60000);
    public static zza<Integer> zzYi = zza.zzd("analytics.http_connection.read_timeout_millis", 61000);
    public static zza<Long> zzYj = zza.zzb("analytics.campaigns.time_limit", 86400000);
    public static zza<String> zzYk = zza.zzp("analytics.first_party_experiment_id", "");
    public static zza<Integer> zzYl = zza.zzd("analytics.first_party_experiment_variant", 0);
    public static zza<Boolean> zzYm = zza.zzh("analytics.test.disable_receiver", false);
    public static zza<Long> zzYn = zza.zza("analytics.service_client.idle_disconnect_millis", 10000, 10000);
    public static zza<Long> zzYo = zza.zzb("analytics.service_client.connect_timeout_millis", 5000);
    public static zza<Long> zzYp = zza.zzb("analytics.service_client.second_connect_delay_millis", 5000);
    public static zza<Long> zzYq = zza.zzb("analytics.service_client.unexpected_reconnect_millis", 60000);
    public static zza<Long> zzYr = zza.zzb("analytics.service_client.reconnect_throttle_millis", 1800000);
    public static zza<Long> zzYs = zza.zzb("analytics.monitoring.sample_period_millis", 86400000);
    public static zza<Long> zzYt = zza.zzb("analytics.initialization_warning_threshold", 5000);

    public static final class zza<V> {
        private final V zzYu;
        private final zzpg<V> zzYv;

        private zza(zzpg<V> zzpg, V v) {
            zzaa.zzz(zzpg);
            this.zzYv = zzpg;
            this.zzYu = v;
        }

        static zza<Float> zza(String str, float f) {
            return zza(str, f, f);
        }

        static zza<Float> zza(String str, float f, float f2) {
            return new zza(zzpg.zza(str, Float.valueOf(f2)), Float.valueOf(f));
        }

        static zza<Integer> zza(String str, int i, int i2) {
            return new zza(zzpg.zza(str, Integer.valueOf(i2)), Integer.valueOf(i));
        }

        static zza<Long> zza(String str, long j, long j2) {
            return new zza(zzpg.zza(str, Long.valueOf(j2)), Long.valueOf(j));
        }

        static zza<Boolean> zza(String str, boolean z, boolean z2) {
            return new zza(zzpg.zzl(str, z2), Boolean.valueOf(z));
        }

        static zza<Long> zzb(String str, long j) {
            return zza(str, j, j);
        }

        static zza<Integer> zzd(String str, int i) {
            return zza(str, i, i);
        }

        static zza<String> zze(String str, String str2, String str3) {
            return new zza(zzpg.zzz(str, str3), str2);
        }

        static zza<Boolean> zzh(String str, boolean z) {
            return zza(str, z, z);
        }

        static zza<String> zzp(String str, String str2) {
            return zze(str, str2, str2);
        }

        public V get() {
            return this.zzYu;
        }
    }
}
