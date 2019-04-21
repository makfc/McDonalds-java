package com.google.android.gms.analytics.internal;

public class zzz extends zzq<zzaa> {

    private static class zza implements com.google.android.gms.analytics.internal.zzq.zza<zzaa> {
        private final zzf zzWg;
        private final zzaa zzYw = new zzaa();

        public zza(zzf zzf) {
            this.zzWg = zzf;
        }

        public void zzc(String str, int i) {
            if ("ga_dispatchPeriod".equals(str)) {
                this.zzYw.zzYy = i;
            } else {
                this.zzWg.zzlR().zzd("Int xml configuration name not recognized", str);
            }
        }

        public void zzg(String str, boolean z) {
            if ("ga_dryRun".equals(str)) {
                this.zzYw.zzYz = z ? 1 : 0;
                return;
            }
            this.zzWg.zzlR().zzd("Bool xml configuration name not recognized", str);
        }

        public void zzn(String str, String str2) {
        }

        /* renamed from: zznI */
        public zzaa zzmV() {
            return this.zzYw;
        }

        public void zzo(String str, String str2) {
            if ("ga_appName".equals(str)) {
                this.zzYw.zzVd = str2;
            } else if ("ga_appVersion".equals(str)) {
                this.zzYw.zzVe = str2;
            } else if ("ga_logLevel".equals(str)) {
                this.zzYw.zzYx = str2;
            } else {
                this.zzWg.zzlR().zzd("String xml configuration name not recognized", str);
            }
        }
    }

    public zzz(zzf zzf) {
        super(zzf, new zza(zzf));
    }
}
