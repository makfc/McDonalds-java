package com.google.android.gms.internal;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;

public class zzl {
    private final Map<String, Queue<zzk<?>>> zzY;
    private final Set<zzk<?>> zzZ;
    private final PriorityBlockingQueue<zzk<?>> zzaa;
    private List<zza> zzae;

    public interface zza<T> {
        void zzg(zzk<T> zzk);
    }

    /* Access modifiers changed, original: 0000 */
    public <T> void zzf(zzk<T> zzk) {
        synchronized (this.zzZ) {
            this.zzZ.remove(zzk);
        }
        synchronized (this.zzae) {
            for (zza zzg : this.zzae) {
                zzg.zzg(zzk);
            }
        }
        if (zzk.zzq()) {
            synchronized (this.zzY) {
                Queue queue = (Queue) this.zzY.remove(zzk.zzg());
                if (queue != null) {
                    if (zzs.DEBUG) {
                        zzs.zza("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(queue.size()), r2);
                    }
                    this.zzaa.addAll(queue);
                }
            }
        }
    }
}
