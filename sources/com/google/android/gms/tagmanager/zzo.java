package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tagmanager.ContainerHolder.ContainerAvailableListener;

class zzo implements ContainerHolder {
    private Status zzaaO;
    private final Looper zzakW;
    private boolean zzawk;
    private Container zzbnZ;
    private Container zzboa;
    private zzb zzbob;
    private zza zzboc;
    private TagManager zzbod;

    public interface zza {
        String zzJm();

        void zzJo();

        void zzgn(String str);
    }

    private class zzb extends Handler {
        private final ContainerAvailableListener zzboe;
        final /* synthetic */ zzo zzbof;

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    zzgp((String) message.obj);
                    return;
                default:
                    zzbn.m7501e("Don't know how to handle this message.");
                    return;
            }
        }

        public void zzgo(String str) {
            sendMessage(obtainMessage(1, str));
        }

        /* Access modifiers changed, original: protected */
        public void zzgp(String str) {
            this.zzboe.onContainerAvailable(this.zzbof, str);
        }
    }

    public zzo(Status status) {
        this.zzaaO = status;
        this.zzakW = null;
    }

    public zzo(TagManager tagManager, Looper looper, Container container, zza zza) {
        this.zzbod = tagManager;
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        this.zzakW = looper;
        this.zzbnZ = container;
        this.zzboc = zza;
        this.zzaaO = Status.zzalw;
        tagManager.zza(this);
    }

    private void zzJn() {
        if (this.zzbob != null) {
            this.zzbob.zzgo(this.zzboa.zzJk());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public String getContainerId() {
        if (!this.zzawk) {
            return this.zzbnZ.getContainerId();
        }
        zzbn.m7501e("getContainerId called on a released ContainerHolder.");
        return "";
    }

    public Status getStatus() {
        return this.zzaaO;
    }

    public synchronized void refresh() {
        if (this.zzawk) {
            zzbn.m7501e("Refreshing a released ContainerHolder.");
        } else {
            this.zzboc.zzJo();
        }
    }

    public synchronized void release() {
        if (this.zzawk) {
            zzbn.m7501e("Releasing a released ContainerHolder.");
        } else {
            this.zzawk = true;
            this.zzbod.zzb(this);
            this.zzbnZ.release();
            this.zzbnZ = null;
            this.zzboa = null;
            this.zzboc = null;
            this.zzbob = null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public String zzJm() {
        if (!this.zzawk) {
            return this.zzboc.zzJm();
        }
        zzbn.m7501e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        return "";
    }

    public synchronized void zza(Container container) {
        if (!this.zzawk) {
            if (container == null) {
                zzbn.m7501e("Unexpected null container.");
            } else {
                this.zzboa = container;
                zzJn();
            }
        }
    }

    public synchronized void zzgl(String str) {
        if (!this.zzawk) {
            this.zzbnZ.zzgl(str);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zzgn(String str) {
        if (this.zzawk) {
            zzbn.m7501e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        } else {
            this.zzboc.zzgn(str);
        }
    }
}
