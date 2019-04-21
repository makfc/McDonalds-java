package com.google.android.gms.gcm;

import android.app.Service;
import android.os.Bundle;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;

public abstract class GcmTaskService extends Service {
    private final Set<String> zzaSS = new HashSet();
    private int zzaST;

    private class zza extends Thread {
        private final Bundle mExtras;
        private final String mTag;
        private final zzb zzaSU;
        final /* synthetic */ GcmTaskService zzaSV;

        public void run() {
            Process.setThreadPriority(10);
            try {
                this.zzaSU.zzhG(this.zzaSV.onRunTask(new TaskParams(this.mTag, this.mExtras)));
            } catch (RemoteException e) {
                String str = "GcmTaskService";
                String str2 = "Error reporting result of operation to scheduler for ";
                String valueOf = String.valueOf(this.mTag);
                Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            } finally {
                this.zzaSV.zzez(this.mTag);
            }
        }
    }

    private void zzez(String str) {
        synchronized (this.zzaSS) {
            this.zzaSS.remove(str);
            if (this.zzaSS.size() == 0) {
                stopSelf(this.zzaST);
            }
        }
    }

    public abstract int onRunTask(TaskParams taskParams);
}
