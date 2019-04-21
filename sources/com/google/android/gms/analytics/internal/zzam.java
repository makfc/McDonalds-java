package com.google.android.gms.analytics.internal;

public class zzam extends zzq<zzan> {

    private static class zza extends zzc implements com.google.android.gms.analytics.internal.zzq.zza<zzan> {
        private final zzan zzZh;

        public void zzc(String str, int i) {
            if ("ga_sessionTimeout".equals(str)) {
                this.zzZh.zzZj = i;
            } else {
                zzd("int configuration name not recognized", str);
            }
        }

        public void zzg(String str, boolean z) {
            int i = 1;
            zzan zzan;
            if ("ga_autoActivityTracking".equals(str)) {
                zzan = this.zzZh;
                if (!z) {
                    i = 0;
                }
                zzan.zzZk = i;
            } else if ("ga_anonymizeIp".equals(str)) {
                zzan = this.zzZh;
                if (!z) {
                    i = 0;
                }
                zzan.zzZl = i;
            } else if ("ga_reportUncaughtExceptions".equals(str)) {
                zzan = this.zzZh;
                if (!z) {
                    i = 0;
                }
                zzan.zzZm = i;
            } else {
                zzd("bool configuration name not recognized", str);
            }
        }

        public void zzn(String str, String str2) {
            this.zzZh.zzZn.put(str, str2);
        }

        public void zzo(String str, String str2) {
            if ("ga_trackingId".equals(str)) {
                this.zzZh.zzTJ = str2;
            } else if ("ga_sampleFrequency".equals(str)) {
                try {
                    this.zzZh.zzZi = Double.parseDouble(str2);
                } catch (NumberFormatException e) {
                    zzc("Error parsing ga_sampleFrequency value", str2, e);
                }
            } else {
                zzd("string configuration name not recognized", str);
            }
        }

        /* renamed from: zzou */
        public zzan zzmV() {
            return this.zzZh;
        }
    }
}
