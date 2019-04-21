package com.google.android.gms.measurement.internal;

import android.os.Binder;
import android.os.Process;
import android.support.annotation.BinderThread;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zzx;
import com.google.android.gms.measurement.internal.zzm.zza;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class zzy extends zza {
    private final zzx zzbbl;
    private final boolean zzbfb;

    public zzy(zzx zzx) {
        zzaa.zzz(zzx);
        this.zzbbl = zzx;
        this.zzbfb = false;
    }

    public zzy(zzx zzx, boolean z) {
        zzaa.zzz(zzx);
        this.zzbbl = zzx;
        this.zzbfb = z;
    }

    @BinderThread
    private void zzf(AppMetadata appMetadata) {
        zzaa.zzz(appMetadata);
        zzfC(appMetadata.packageName);
        this.zzbbl.zzFi().zzfN(appMetadata.zzbbK);
    }

    @BinderThread
    private void zzfC(String str) throws SecurityException {
        if (TextUtils.isEmpty(str)) {
            this.zzbbl.zzFm().zzFE().log("Measurement Service called without app package");
            throw new SecurityException("Measurement Service called without app package");
        }
        try {
            zzfD(str);
        } catch (SecurityException e) {
            this.zzbbl.zzFm().zzFE().zzj("Measurement Service called with invalid calling package", str);
            throw e;
        }
    }

    @BinderThread
    public List<UserAttributeParcel> zza(final AppMetadata appMetadata, boolean z) {
        zzf(appMetadata);
        try {
            List<zzak> list = (List) this.zzbbl.zzFl().zzd(new Callable<List<zzak>>() {
                /* renamed from: zzGt */
                public List<zzak> call() throws Exception {
                    zzy.this.zzbbl.zzGq();
                    return zzy.this.zzbbl.zzFh().zzfj(appMetadata.packageName);
                }
            }).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzak zzak : list) {
                if (z || !zzal.zzfQ(zzak.mName)) {
                    arrayList.add(new UserAttributeParcel(zzak));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzbbl.zzFm().zzFE().zzj("Failed to get user attributes", e);
            return null;
        }
    }

    @BinderThread
    public void zza(final AppMetadata appMetadata) {
        zzf(appMetadata);
        this.zzbbl.zzFl().zzg(new Runnable() {
            public void run() {
                zzy.this.zzbbl.zzGq();
                zzy.this.zzfB(appMetadata.zzbbO);
                zzy.this.zzbbl.zzd(appMetadata);
            }
        });
    }

    @BinderThread
    public void zza(final EventParcel eventParcel, final AppMetadata appMetadata) {
        zzaa.zzz(eventParcel);
        zzf(appMetadata);
        this.zzbbl.zzFl().zzg(new Runnable() {
            public void run() {
                zzy.this.zzbbl.zzGq();
                zzy.this.zzfB(appMetadata.zzbbO);
                zzy.this.zzbbl.zzb(eventParcel, appMetadata);
            }
        });
    }

    @BinderThread
    public void zza(final EventParcel eventParcel, final String str, final String str2) {
        zzaa.zzz(eventParcel);
        zzaa.zzdl(str);
        zzfC(str);
        this.zzbbl.zzFl().zzg(new Runnable() {
            public void run() {
                zzy.this.zzbbl.zzGq();
                zzy.this.zzfB(str2);
                zzy.this.zzbbl.zzb(eventParcel, str);
            }
        });
    }

    @BinderThread
    public void zza(final UserAttributeParcel userAttributeParcel, final AppMetadata appMetadata) {
        zzaa.zzz(userAttributeParcel);
        zzf(appMetadata);
        if (userAttributeParcel.getValue() == null) {
            this.zzbbl.zzFl().zzg(new Runnable() {
                public void run() {
                    zzy.this.zzbbl.zzGq();
                    zzy.this.zzfB(appMetadata.zzbbO);
                    zzy.this.zzbbl.zzc(userAttributeParcel, appMetadata);
                }
            });
        } else {
            this.zzbbl.zzFl().zzg(new Runnable() {
                public void run() {
                    zzy.this.zzbbl.zzGq();
                    zzy.this.zzfB(appMetadata.zzbbO);
                    zzy.this.zzbbl.zzb(userAttributeParcel, appMetadata);
                }
            });
        }
    }

    @BinderThread
    public byte[] zza(final EventParcel eventParcel, final String str) {
        zzaa.zzdl(str);
        zzaa.zzz(eventParcel);
        zzfC(str);
        this.zzbbl.zzFm().zzFK().zzj("Log and bundle. event", eventParcel.name);
        long nanoTime = this.zzbbl.zzlQ().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zzbbl.zzFl().zze(new Callable<byte[]>() {
                /* renamed from: zzGs */
                public byte[] call() throws Exception {
                    zzy.this.zzbbl.zzGq();
                    return zzy.this.zzbbl.zza(eventParcel, str);
                }
            }).get();
            if (bArr == null) {
                this.zzbbl.zzFm().zzFE().log("Log and bundle returned null");
                bArr = new byte[0];
            }
            this.zzbbl.zzFm().zzFK().zzd("Log and bundle processed. event, size, time_ms", eventParcel.name, Integer.valueOf(bArr.length), Long.valueOf((this.zzbbl.zzlQ().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e) {
            this.zzbbl.zzFm().zzFE().zze("Failed to log and bundle. event, error", eventParcel.name, e);
            return null;
        }
    }

    @BinderThread
    public void zzb(final AppMetadata appMetadata) {
        zzf(appMetadata);
        this.zzbbl.zzFl().zzg(new Runnable() {
            public void run() {
                zzy.this.zzbbl.zzGq();
                zzy.this.zzfB(appMetadata.zzbbO);
                zzy.this.zzbbl.zzc(appMetadata);
            }
        });
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void zzfB(String str) {
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(":", 2);
            if (split.length == 2) {
                try {
                    long longValue = Long.valueOf(split[0]).longValue();
                    if (longValue > 0) {
                        this.zzbbl.zzFn().zzbdF.zzf(split[1], longValue);
                    } else {
                        this.zzbbl.zzFm().zzFG().zzj("Combining sample with a non-positive weight", Long.valueOf(longValue));
                    }
                } catch (NumberFormatException e) {
                    this.zzbbl.zzFm().zzFG().zzj("Combining sample with a non-number weight", split[0]);
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void zzfD(String str) throws SecurityException {
        int myUid = this.zzbfb ? Process.myUid() : Binder.getCallingUid();
        if (!zzx.zzd(this.zzbbl.getContext(), myUid, str)) {
            if (!zzx.zze(this.zzbbl.getContext(), myUid) || this.zzbbl.zzGh()) {
                throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[]{str}));
            }
        }
    }
}
