package com.google.android.gms.analytics.internal;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

class zzs implements Logger {
    private boolean zzUc;
    private int zzXw = 2;

    zzs() {
    }

    public void error(String str) {
    }

    public int getLogLevel() {
        return this.zzXw;
    }

    public void info(String str) {
    }

    public void setLogLevel(int i) {
        this.zzXw = i;
        if (!this.zzUc) {
            String str = (String) zzy.zzXF.get();
            Log.i((String) zzy.zzXF.get(), new StringBuilder(String.valueOf(str).length() + 91).append("Logger is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag.").append(str).append(" DEBUG").toString());
            this.zzUc = true;
        }
    }

    public void verbose(String str) {
    }

    public void warn(String str) {
    }
}
