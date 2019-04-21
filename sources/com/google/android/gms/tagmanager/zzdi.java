package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

public class zzdi {
    private Context mContext;
    private Tracker zzTU;
    private GoogleAnalytics zzTW;

    static class zza implements Logger {
        zza() {
        }

        private static int zzkW(int i) {
            switch (i) {
                case 2:
                    return 0;
                case 3:
                case 4:
                    return 1;
                case 5:
                    return 2;
                default:
                    return 3;
            }
        }

        public void error(String str) {
            zzbn.m7501e(str);
        }

        public int getLogLevel() {
            return zzkW(zzbn.getLogLevel());
        }

        public void info(String str) {
            zzbn.zzaV(str);
        }

        public void setLogLevel(int i) {
            zzbn.zzaW("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
        }

        public void verbose(String str) {
            zzbn.m7502v(str);
        }

        public void warn(String str) {
            zzbn.zzaW(str);
        }
    }

    public zzdi(Context context) {
        this.mContext = context;
    }

    private synchronized void zzgL(String str) {
        if (this.zzTW == null) {
            this.zzTW = GoogleAnalytics.getInstance(this.mContext);
            this.zzTW.setLogger(new zza());
            this.zzTU = this.zzTW.newTracker(str);
        }
    }

    public Tracker zzgK(String str) {
        zzgL(str);
        return this.zzTU;
    }
}
