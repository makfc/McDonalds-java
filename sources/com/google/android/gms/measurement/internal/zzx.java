package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.p000v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.internal.zzamc;
import com.google.android.gms.internal.zzso;
import com.google.android.gms.internal.zzsp;
import com.google.android.gms.internal.zzsp.zzb;
import com.google.android.gms.internal.zzsp.zzc;
import com.google.android.gms.internal.zzsp.zzd;
import com.google.android.gms.internal.zzsp.zzg;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class zzx {
    private static volatile zzx zzbex;
    private final Context mContext;
    private final boolean zzWh;
    private final zzp zzbeA;
    private final zzw zzbeB;
    private final zzaf zzbeC;
    private final zzv zzbeD;
    private final AppMeasurement zzbeE;
    private final zzal zzbeF;
    private final zze zzbeG;
    private final zzq zzbeH;
    private final zzad zzbeI;
    private final zzg zzbeJ;
    private final zzac zzbeK;
    private final zzn zzbeL;
    private final zzr zzbeM;
    private final zzai zzbeN;
    private final zzc zzbeO;
    public final FirebaseAnalytics zzbeP = new FirebaseAnalytics(this);
    private boolean zzbeQ;
    private Boolean zzbeR;
    private FileLock zzbeS;
    private FileChannel zzbeT;
    private List<Long> zzbeU;
    private int zzbeV;
    private int zzbeW;
    private final zzd zzbey;
    private final zzt zzbez;
    private final zze zzsd;

    /* renamed from: com.google.android.gms.measurement.internal.zzx$1 */
    class C26971 implements Runnable {
        C26971() {
        }

        public void run() {
            zzx.this.start();
        }
    }

    /* renamed from: com.google.android.gms.measurement.internal.zzx$2 */
    class C26982 implements zza {
        C26982() {
        }

        public void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
            zzx.this.zza(i, th, bArr);
        }
    }

    /* renamed from: com.google.android.gms.measurement.internal.zzx$3 */
    class C26993 implements zza {
        C26993() {
        }

        public void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
            zzx.this.zzb(str, i, th, bArr, map);
        }
    }

    private class zza implements zzb {
        zzsp.zze zzbeY;
        List<Long> zzbeZ;
        long zzbfa;
        List<zzb> zzqD;

        private zza() {
        }

        /* synthetic */ zza(zzx zzx, C26971 c26971) {
            this();
        }

        private long zza(zzb zzb) {
            return ((zzb.zzbgX.longValue() / 1000) / 60) / 60;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean isEmpty() {
            return this.zzqD == null || this.zzqD.isEmpty();
        }

        public boolean zza(long j, zzb zzb) {
            zzaa.zzz(zzb);
            if (this.zzqD == null) {
                this.zzqD = new ArrayList();
            }
            if (this.zzbeZ == null) {
                this.zzbeZ = new ArrayList();
            }
            if (this.zzqD.size() > 0 && zza((zzb) this.zzqD.get(0)) != zza(zzb)) {
                return false;
            }
            long serializedSize = this.zzbfa + ((long) zzb.getSerializedSize());
            if (serializedSize >= ((long) zzx.this.zzFo().zzER())) {
                return false;
            }
            this.zzbfa = serializedSize;
            this.zzqD.add(zzb);
            this.zzbeZ.add(Long.valueOf(j));
            return this.zzqD.size() < zzx.this.zzFo().zzES();
        }

        public void zzc(zzsp.zze zze) {
            zzaa.zzz(zze);
            this.zzbeY = zze;
        }
    }

    zzx(zzab zzab) {
        zzaa.zzz(zzab);
        this.mContext = zzab.mContext;
        this.zzsd = zzab.zzl(this);
        this.zzbey = zzab.zza(this);
        zzt zzb = zzab.zzb(this);
        zzb.initialize();
        this.zzbez = zzb;
        zzp zzc = zzab.zzc(this);
        zzc.initialize();
        this.zzbeA = zzc;
        zzFm().zzFJ().zzj("App measurement is starting up, version", Long.valueOf(zzFo().zzEi()));
        zzFm().zzFJ().log("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        zzFm().zzFK().log("Debug logging enabled");
        zzFm().zzFK().zzj("AppMeasurement singleton hash", Integer.valueOf(System.identityHashCode(this)));
        this.zzbeF = zzab.zzi(this);
        zzg zzn = zzab.zzn(this);
        zzn.initialize();
        this.zzbeJ = zzn;
        zzn zzo = zzab.zzo(this);
        zzo.initialize();
        this.zzbeL = zzo;
        zze zzj = zzab.zzj(this);
        zzj.initialize();
        this.zzbeG = zzj;
        zzc zzr = zzab.zzr(this);
        zzr.initialize();
        this.zzbeO = zzr;
        zzq zzk = zzab.zzk(this);
        zzk.initialize();
        this.zzbeH = zzk;
        zzad zzm = zzab.zzm(this);
        zzm.initialize();
        this.zzbeI = zzm;
        zzac zzh = zzab.zzh(this);
        zzh.initialize();
        this.zzbeK = zzh;
        zzai zzq = zzab.zzq(this);
        zzq.initialize();
        this.zzbeN = zzq;
        this.zzbeM = zzab.zzp(this);
        this.zzbeE = zzab.zzg(this);
        zzaf zze = zzab.zze(this);
        zze.initialize();
        this.zzbeC = zze;
        zzv zzf = zzab.zzf(this);
        zzf.initialize();
        this.zzbeD = zzf;
        zzw zzd = zzab.zzd(this);
        zzd.initialize();
        this.zzbeB = zzd;
        if (this.zzbeV != this.zzbeW) {
            zzFm().zzFE().zze("Not all components initialized", Integer.valueOf(this.zzbeV), Integer.valueOf(this.zzbeW));
        }
        this.zzWh = true;
        if (!(this.zzbey.zzmW() || zzGh())) {
            if (!(this.mContext.getApplicationContext() instanceof Application)) {
                zzFm().zzFG().log("Application context is not an Application");
            } else if (VERSION.SDK_INT >= 14) {
                zzFd().zzGw();
            } else {
                zzFm().zzFK().log("Not tracking deep linking pre-ICS");
            }
        }
        this.zzbeB.zzg(new C26971());
    }

    private void zzD(List<Long> list) {
        zzaa.zzaj(!list.isEmpty());
        if (this.zzbeU != null) {
            zzFm().zzFE().log("Set uploading progress before finishing the previous upload");
        } else {
            this.zzbeU = new ArrayList(list);
        }
    }

    @WorkerThread
    private boolean zzGk() {
        zzkN();
        return this.zzbeU != null;
    }

    private boolean zzGm() {
        zzkN();
        zzma();
        return zzFh().zzFu() || !TextUtils.isEmpty(zzFh().zzFp());
    }

    @WorkerThread
    private void zzGn() {
        zzkN();
        zzma();
        if (!zzGr()) {
            return;
        }
        if (zzFX() && zzGm()) {
            long zzGo = zzGo();
            if (zzGo == 0) {
                zzGc().unregister();
                zzGd().cancel();
                return;
            } else if (zzGb().zzod()) {
                long j = zzFn().zzbdI.get();
                long zzEV = zzFo().zzEV();
                if (!zzFi().zzc(j, zzEV)) {
                    zzGo = Math.max(zzGo, j + zzEV);
                }
                zzGc().unregister();
                zzGo -= zzlQ().currentTimeMillis();
                if (zzGo <= 0) {
                    zzGd().zzv(1);
                    return;
                }
                zzFm().zzFL().zzj("Upload scheduled in approximately ms", Long.valueOf(zzGo));
                zzGd().zzv(zzGo);
                return;
            } else {
                zzGc().zzoa();
                zzGd().cancel();
                return;
            }
        }
        zzGc().unregister();
        zzGd().cancel();
    }

    private long zzGo() {
        long currentTimeMillis = zzlQ().currentTimeMillis();
        long zzEY = zzFo().zzEY();
        long zzEW = zzFo().zzEW();
        long j = zzFn().zzbdG.get();
        long j2 = zzFn().zzbdH.get();
        long max = Math.max(zzFh().zzFs(), zzFh().zzFt());
        if (max == 0) {
            return 0;
        }
        max = currentTimeMillis - Math.abs(max - currentTimeMillis);
        j2 = currentTimeMillis - Math.abs(j2 - currentTimeMillis);
        currentTimeMillis = Math.max(currentTimeMillis - Math.abs(j - currentTimeMillis), j2);
        zzEY += max;
        if (!zzFi().zzc(currentTimeMillis, zzEW)) {
            zzEY = currentTimeMillis + zzEW;
        }
        if (j2 == 0 || j2 < max) {
            return zzEY;
        }
        for (int i = 0; i < zzFo().zzFa(); i++) {
            zzEY += ((long) (1 << i)) * zzFo().zzEZ();
            if (zzEY > j2) {
                return zzEY;
            }
        }
        return 0;
    }

    @WorkerThread
    private void zza(int i, Throwable th, byte[] bArr) {
        int i2 = 0;
        zzkN();
        zzma();
        if (bArr == null) {
            bArr = new byte[0];
        }
        List<Long> list = this.zzbeU;
        this.zzbeU = null;
        if ((i == 200 || i == 204) && th == null) {
            zzFn().zzbdG.set(zzlQ().currentTimeMillis());
            zzFn().zzbdH.set(0);
            zzGn();
            zzFm().zzFL().zze("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
            zzFh().beginTransaction();
            try {
                for (Long longValue : list) {
                    zzFh().zzae(longValue.longValue());
                }
                zzFh().setTransactionSuccessful();
                if (zzGb().zzod() && zzGm()) {
                    zzGl();
                } else {
                    zzGn();
                }
            } finally {
                zzFh().endTransaction();
            }
        } else {
            zzFm().zzFL().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            zzFn().zzbdH.set(zzlQ().currentTimeMillis());
            if (i == 503 || i == 429) {
                i2 = 1;
            }
            if (i2 != 0) {
                zzFn().zzbdI.set(zzlQ().currentTimeMillis());
            }
            zzGn();
        }
    }

    private void zza(zzaa zzaa) {
        if (zzaa == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzaa.isInitialized()) {
            throw new IllegalStateException("Component not initialized");
        }
    }

    private void zza(zzz zzz) {
        if (zzz == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private com.google.android.gms.internal.zzsp.zza[] zza(String str, zzg[] zzgArr, zzb[] zzbArr) {
        zzaa.zzdl(str);
        return zzFc().zza(str, zzbArr, zzgArr);
    }

    public static zzx zzbd(Context context) {
        zzaa.zzz(context);
        zzaa.zzz(context.getApplicationContext());
        if (zzbex == null) {
            synchronized (zzx.class) {
                if (zzbex == null) {
                    zzbex = new zzab(context).zzGv();
                }
            }
        }
        return zzbex;
    }

    @WorkerThread
    private void zze(AppMetadata appMetadata) {
        Object obj = 1;
        zzkN();
        zzma();
        zzaa.zzz(appMetadata);
        zzaa.zzdl(appMetadata.packageName);
        zza zzfk = zzFh().zzfk(appMetadata.packageName);
        String zzfv = zzFn().zzfv(appMetadata.packageName);
        Object obj2 = null;
        if (zzfk == null) {
            zza zza = new zza(this, appMetadata.packageName);
            zza.zzeV(zzFn().zzFO());
            zza.zzeX(zzfv);
            zzfk = zza;
            obj2 = 1;
        } else if (!zzfv.equals(zzfk.zzEc())) {
            zzfk.zzeX(zzfv);
            zzfk.zzeV(zzFn().zzFO());
            int obj22 = 1;
        }
        if (!(TextUtils.isEmpty(appMetadata.zzbbK) || appMetadata.zzbbK.equals(zzfk.zzEb()))) {
            zzfk.zzeW(appMetadata.zzbbK);
            obj22 = 1;
        }
        if (!(TextUtils.isEmpty(appMetadata.zzbbS) || appMetadata.zzbbS.equals(zzfk.zzEd()))) {
            zzfk.zzeY(appMetadata.zzbbS);
            obj22 = 1;
        }
        if (!(appMetadata.zzbbM == 0 || appMetadata.zzbbM == zzfk.zzEi())) {
            zzfk.zzU(appMetadata.zzbbM);
            obj22 = 1;
        }
        if (!(TextUtils.isEmpty(appMetadata.zzaUf) || appMetadata.zzaUf.equals(zzfk.zzkV()))) {
            zzfk.setAppVersion(appMetadata.zzaUf);
            obj22 = 1;
        }
        if (appMetadata.zzbbR != zzfk.zzEg()) {
            zzfk.zzT(appMetadata.zzbbR);
            obj22 = 1;
        }
        if (!(TextUtils.isEmpty(appMetadata.zzbbL) || appMetadata.zzbbL.equals(zzfk.zzEh()))) {
            zzfk.zzeZ(appMetadata.zzbbL);
            obj22 = 1;
        }
        if (appMetadata.zzbbN != zzfk.zzEj()) {
            zzfk.zzV(appMetadata.zzbbN);
            obj22 = 1;
        }
        if (appMetadata.zzbbP != zzfk.zzEk()) {
            zzfk.setMeasurementEnabled(appMetadata.zzbbP);
        } else {
            obj = obj22;
        }
        if (obj != null) {
            zzFh().zza(zzfk);
        }
    }

    private boolean zzg(String str, long j) {
        zzFh().beginTransaction();
        try {
            zzb zza = new zza(this, null);
            zzFh().zza(str, j, zza);
            if (zza.isEmpty()) {
                zzFh().setTransactionSuccessful();
                zzFh().endTransaction();
                return false;
            }
            int i;
            zzsp.zze zze = zza.zzbeY;
            zze.zzbhe = new zzb[zza.zzqD.size()];
            int i2 = 0;
            int i3 = 0;
            while (i3 < zza.zzqD.size()) {
                if (zzFj().zzV(zza.zzbeY.appId, ((zzb) zza.zzqD.get(i3)).name)) {
                    zzFm().zzFG().zzj("Dropping blacklisted raw event", ((zzb) zza.zzqD.get(i3)).name);
                    zzFi().zze(11, "_ev", ((zzb) zza.zzqD.get(i3)).name);
                    i = i2;
                } else {
                    int i4;
                    if (zzFj().zzW(zza.zzbeY.appId, ((zzb) zza.zzqD.get(i3)).name)) {
                        int i5;
                        Object obj;
                        zzc zzc;
                        if (((zzb) zza.zzqD.get(i3)).zzbgW == null) {
                            ((zzb) zza.zzqD.get(i3)).zzbgW = new zzc[0];
                        }
                        for (zzc zzc2 : ((zzb) zza.zzqD.get(i3)).zzbgW) {
                            if ("_c".equals(zzc2.name)) {
                                zzc2.zzbha = Long.valueOf(1);
                                obj = 1;
                                break;
                            }
                        }
                        obj = null;
                        if (obj == null) {
                            zzFm().zzFL().zzj("Marking event as conversion", ((zzb) zza.zzqD.get(i3)).name);
                            zzc[] zzcArr = (zzc[]) Arrays.copyOf(((zzb) zza.zzqD.get(i3)).zzbgW, ((zzb) zza.zzqD.get(i3)).zzbgW.length + 1);
                            zzc = new zzc();
                            zzc.name = "_c";
                            zzc.zzbha = Long.valueOf(1);
                            zzcArr[zzcArr.length - 1] = zzc;
                            ((zzb) zza.zzqD.get(i3)).zzbgW = zzcArr;
                        }
                        boolean zzfG = zzal.zzfG(((zzb) zza.zzqD.get(i3)).name);
                        if (zzfG && zzFh().zza(zzGi(), zza.zzbeY.appId, false, zzfG, false).zzbbZ - ((long) zzFo().zzfc(zza.zzbeY.appId)) > 0) {
                            zzFm().zzFG().log("Too many conversions. Not logging as conversion.");
                            zzb zzb = (zzb) zza.zzqD.get(i3);
                            Object obj2 = null;
                            zzc zzc3 = null;
                            zzc[] zzcArr2 = ((zzb) zza.zzqD.get(i3)).zzbgW;
                            int length = zzcArr2.length;
                            int i6 = 0;
                            while (i6 < length) {
                                Object obj3;
                                zzc = zzcArr2[i6];
                                if ("_c".equals(zzc.name)) {
                                    obj3 = obj2;
                                } else if ("_err".equals(zzc.name)) {
                                    zzc zzc4 = zzc3;
                                    int obj32 = 1;
                                    zzc = zzc4;
                                } else {
                                    zzc = zzc3;
                                    obj32 = obj2;
                                }
                                i6++;
                                obj2 = obj32;
                                zzc3 = zzc;
                            }
                            if (obj2 != null && zzc3 != null) {
                                zzc[] zzcArr3 = new zzc[(zzb.zzbgW.length - 1)];
                                i4 = 0;
                                zzcArr2 = zzb.zzbgW;
                                length = zzcArr2.length;
                                i5 = 0;
                                while (i5 < length) {
                                    zzc zzc5 = zzcArr2[i5];
                                    if (zzc5 != zzc3) {
                                        i = i4 + 1;
                                        zzcArr3[i4] = zzc5;
                                    } else {
                                        i = i4;
                                    }
                                    i5++;
                                    i4 = i;
                                }
                                ((zzb) zza.zzqD.get(i3)).zzbgW = zzcArr3;
                            } else if (zzc3 != null) {
                                zzc3.name = "_err";
                                zzc3.zzbha = Long.valueOf(10);
                            } else {
                                zzFm().zzFE().log("Did not find conversion parameter. Error not tracked");
                            }
                        }
                    }
                    i4 = i2 + 1;
                    zze.zzbhe[i2] = (zzb) zza.zzqD.get(i3);
                    i = i4;
                }
                i3++;
                i2 = i;
            }
            if (i2 < zza.zzqD.size()) {
                zze.zzbhe = (zzb[]) Arrays.copyOf(zze.zzbhe, i2);
            }
            zze.zzbhx = zza(zza.zzbeY.appId, zza.zzbeY.zzbhf, zze.zzbhe);
            zze.zzbhh = zze.zzbhe[0].zzbgX;
            zze.zzbhi = zze.zzbhe[0].zzbgX;
            for (i = 1; i < zze.zzbhe.length; i++) {
                zzb zzb2 = zze.zzbhe[i];
                if (zzb2.zzbgX.longValue() < zze.zzbhh.longValue()) {
                    zze.zzbhh = zzb2.zzbgX;
                }
                if (zzb2.zzbgX.longValue() > zze.zzbhi.longValue()) {
                    zze.zzbhi = zzb2.zzbgX;
                }
            }
            String str2 = zza.zzbeY.appId;
            zza zzfk = zzFh().zzfk(str2);
            if (zzfk == null) {
                zzFm().zzFE().log("Bundling raw events w/o app info");
            } else {
                long zzEf = zzfk.zzEf();
                zze.zzbhk = zzEf != 0 ? Long.valueOf(zzEf) : null;
                long zzEe = zzfk.zzEe();
                if (zzEe != 0) {
                    zzEf = zzEe;
                }
                zze.zzbhj = zzEf != 0 ? Long.valueOf(zzEf) : null;
                zzfk.zzEo();
                zze.zzbhv = Integer.valueOf((int) zzfk.zzEl());
                zzfk.zzR(zze.zzbhh.longValue());
                zzfk.zzS(zze.zzbhi.longValue());
                zzFh().zza(zzfk);
            }
            zze.zzbbO = zzFm().zzFM();
            zzFh().zza(zze);
            zzFh().zzC(zza.zzbeZ);
            zzFh().zzfq(str2);
            zzFh().setTransactionSuccessful();
            return true;
        } finally {
            zzFh().endTransaction();
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    @WorkerThread
    public boolean isEnabled() {
        boolean z = false;
        zzkN();
        zzma();
        if (zzFo().zzEM()) {
            return false;
        }
        Boolean zzEN = zzFo().zzEN();
        if (zzEN != null) {
            z = zzEN.booleanValue();
        } else if (!zzFo().zzsB()) {
            z = true;
        }
        return zzFn().zzax(z);
    }

    /* Access modifiers changed, original: protected */
    @WorkerThread
    public void start() {
        zzkN();
        if (!zzGh() || (this.zzbeB.isInitialized() && !this.zzbeB.zzGu())) {
            zzFh().zzFq();
            if (zzFX()) {
                if (!(zzFo().zzmW() || TextUtils.isEmpty(zzFe().zzEb()))) {
                    String zzFR = zzFn().zzFR();
                    if (zzFR == null) {
                        zzFn().zzfw(zzFe().zzEb());
                    } else if (!zzFR.equals(zzFe().zzEb())) {
                        zzFm().zzFJ().log("Rechecking which service to use due to a GMP App Id change");
                        zzFn().zzFT();
                        this.zzbeI.disconnect();
                        this.zzbeI.zzmC();
                        zzFn().zzfw(zzFe().zzEb());
                    }
                }
                if (!(zzFo().zzmW() || zzGh() || TextUtils.isEmpty(zzFe().zzEb()))) {
                    zzFd().zzGx();
                }
            } else if (isEnabled()) {
                if (!zzFi().zzbN("android.permission.INTERNET")) {
                    zzFm().zzFE().log("App is missing INTERNET permission");
                }
                if (!zzFi().zzbN("android.permission.ACCESS_NETWORK_STATE")) {
                    zzFm().zzFE().log("App is missing ACCESS_NETWORK_STATE permission");
                }
                if (!zzu.zzU(getContext())) {
                    zzFm().zzFE().log("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzae.zzV(getContext())) {
                    zzFm().zzFE().log("AppMeasurementService not registered/enabled");
                }
                zzFm().zzFE().log("Uploading is not possible. App measurement disabled");
            }
            zzGn();
            return;
        }
        zzFm().zzFE().log("Scheduler shutting down before Scion.start() called");
    }

    /* Access modifiers changed, original: protected */
    @WorkerThread
    public boolean zzFX() {
        zzma();
        zzkN();
        if (this.zzbeR == null) {
            boolean z = zzFi().zzbN("android.permission.INTERNET") && zzFi().zzbN("android.permission.ACCESS_NETWORK_STATE") && zzu.zzU(getContext()) && zzae.zzV(getContext());
            this.zzbeR = Boolean.valueOf(z);
            if (this.zzbeR.booleanValue() && !zzFo().zzmW()) {
                this.zzbeR = Boolean.valueOf(zzFi().zzfN(zzFe().zzEb()));
            }
        }
        return this.zzbeR.booleanValue();
    }

    public zzp zzFY() {
        return (this.zzbeA == null || !this.zzbeA.isInitialized()) ? null : this.zzbeA;
    }

    /* Access modifiers changed, original: 0000 */
    public zzw zzFZ() {
        return this.zzbeB;
    }

    public zzc zzFc() {
        zza(this.zzbeO);
        return this.zzbeO;
    }

    public zzac zzFd() {
        zza(this.zzbeK);
        return this.zzbeK;
    }

    public zzn zzFe() {
        zza(this.zzbeL);
        return this.zzbeL;
    }

    public zzg zzFf() {
        zza(this.zzbeJ);
        return this.zzbeJ;
    }

    public zzad zzFg() {
        zza(this.zzbeI);
        return this.zzbeI;
    }

    public zze zzFh() {
        zza(this.zzbeG);
        return this.zzbeG;
    }

    public zzal zzFi() {
        zza(this.zzbeF);
        return this.zzbeF;
    }

    public zzv zzFj() {
        zza(this.zzbeD);
        return this.zzbeD;
    }

    public zzaf zzFk() {
        zza(this.zzbeC);
        return this.zzbeC;
    }

    public zzw zzFl() {
        zza(this.zzbeB);
        return this.zzbeB;
    }

    public zzp zzFm() {
        zza(this.zzbeA);
        return this.zzbeA;
    }

    public zzt zzFn() {
        zza(this.zzbez);
        return this.zzbez;
    }

    public zzd zzFo() {
        return this.zzbey;
    }

    public AppMeasurement zzGa() {
        return this.zzbeE;
    }

    public zzq zzGb() {
        zza(this.zzbeH);
        return this.zzbeH;
    }

    public zzr zzGc() {
        if (this.zzbeM != null) {
            return this.zzbeM;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    public zzai zzGd() {
        zza(this.zzbeN);
        return this.zzbeN;
    }

    /* Access modifiers changed, original: 0000 */
    public FileChannel zzGe() {
        return this.zzbeT;
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void zzGf() {
        zzkN();
        zzma();
        if (zzGr() && zzGg()) {
            zzv(zza(zzGe()), zzFe().zzFC());
        }
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public boolean zzGg() {
        zzkN();
        try {
            this.zzbeT = new RandomAccessFile(new File(getContext().getFilesDir(), this.zzbeG.zzmv()), "rw").getChannel();
            this.zzbeS = this.zzbeT.tryLock();
            if (this.zzbeS != null) {
                zzFm().zzFL().log("Storage concurrent access okay");
                return true;
            }
            zzFm().zzFE().log("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e) {
            zzFm().zzFE().zzj("Failed to acquire storage lock", e);
        } catch (IOException e2) {
            zzFm().zzFE().zzj("Failed to access storage lock file", e2);
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean zzGh() {
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    public long zzGi() {
        return ((((zzlQ().currentTimeMillis() + zzFn().zzFP()) / 1000) / 60) / 60) / 24;
    }

    /* Access modifiers changed, original: 0000 */
    public void zzGj() {
        if (!zzFo().zzmW()) {
            throw new IllegalStateException("Unexpected call on client side");
        }
    }

    @WorkerThread
    public void zzGl() {
        Map map = null;
        int i = 0;
        zzkN();
        zzma();
        if (!zzFo().zzmW()) {
            Boolean zzFS = zzFn().zzFS();
            if (zzFS == null) {
                zzFm().zzFG().log("Upload data called on the client side before use of service was decided");
                return;
            } else if (zzFS.booleanValue()) {
                zzFm().zzFE().log("Upload called in the client side when service should be used");
                return;
            }
        }
        if (zzGk()) {
            zzFm().zzFG().log("Uploading requested multiple times");
        } else if (zzGb().zzod()) {
            long currentTimeMillis = zzlQ().currentTimeMillis();
            zzai(currentTimeMillis - zzFo().zzEU());
            long j = zzFn().zzbdG.get();
            if (j != 0) {
                zzFm().zzFK().zzj("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(currentTimeMillis - j)));
            }
            String zzFp = zzFh().zzFp();
            if (TextUtils.isEmpty(zzFp)) {
                String zzaf = zzFh().zzaf(currentTimeMillis - zzFo().zzEU());
                if (!TextUtils.isEmpty(zzaf)) {
                    zza zzfk = zzFh().zzfk(zzaf);
                    if (zzfk != null) {
                        String zzN = zzFo().zzN(zzfk.zzEb(), zzfk.zzvx());
                        try {
                            URL url = new URL(zzN);
                            zzFm().zzFL().zzj("Fetching remote configuration", zzfk.zziC());
                            zzso.zzb zzfy = zzFj().zzfy(zzfk.zziC());
                            zzFp = zzFj().zzfz(zzfk.zziC());
                            if (!(zzfy == null || TextUtils.isEmpty(zzFp))) {
                                map = new ArrayMap();
                                map.put("If-Modified-Since", zzFp);
                            }
                            zzGb().zza(zzaf, url, map, new C26993());
                            return;
                        } catch (MalformedURLException e) {
                            zzFm().zzFE().zzj("Failed to parse config URL. Not fetching", zzN);
                            return;
                        }
                    }
                    return;
                }
                return;
            }
            List<Pair> zzn = zzFh().zzn(zzFp, zzFo().zzfg(zzFp), zzFo().zzfh(zzFp));
            if (!zzn.isEmpty()) {
                zzsp.zze zze;
                Object obj;
                List subList;
                for (Pair pair : zzn) {
                    zze = (zzsp.zze) pair.first;
                    if (!TextUtils.isEmpty(zze.zzbhr)) {
                        obj = zze.zzbhr;
                        break;
                    }
                }
                obj = null;
                if (obj != null) {
                    for (int i2 = 0; i2 < zzn.size(); i2++) {
                        zze = (zzsp.zze) ((Pair) zzn.get(i2)).first;
                        if (!TextUtils.isEmpty(zze.zzbhr) && !zze.zzbhr.equals(obj)) {
                            subList = zzn.subList(0, i2);
                            break;
                        }
                    }
                }
                subList = zzn;
                zzd zzd = new zzd();
                zzd.zzbhb = new zzsp.zze[subList.size()];
                ArrayList arrayList = new ArrayList(subList.size());
                while (i < zzd.zzbhb.length) {
                    zzd.zzbhb[i] = (zzsp.zze) ((Pair) subList.get(i)).first;
                    arrayList.add((Long) ((Pair) subList.get(i)).second);
                    zzd.zzbhb[i].zzbhq = Long.valueOf(zzFo().zzEi());
                    zzd.zzbhb[i].zzbhg = Long.valueOf(currentTimeMillis);
                    zzd.zzbhb[i].zzbhw = Boolean.valueOf(zzFo().zzmW());
                    i++;
                }
                Object zzb = zzFm().zzX(2) ? zzal.zzb(zzd) : null;
                byte[] zza = zzFi().zza(zzd);
                String zzET = zzFo().zzET();
                try {
                    URL url2 = new URL(zzET);
                    zzD(arrayList);
                    zzFn().zzbdH.set(currentTimeMillis);
                    Object obj2 = "?";
                    if (zzd.zzbhb.length > 0) {
                        obj2 = zzd.zzbhb[0].appId;
                    }
                    zzFm().zzFL().zzd("Uploading data. app, uncompressed size, data", obj2, Integer.valueOf(zza.length), zzb);
                    zzGb().zza(zzFp, url2, zza, null, new C26982());
                } catch (MalformedURLException e2) {
                    zzFm().zzFE().zzj("Failed to parse upload URL. Not uploading", zzET);
                }
            }
        } else {
            zzFm().zzFG().log("Network not connected, ignoring upload request");
            zzGn();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zzGp() {
        this.zzbeW++;
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void zzGq() {
        zzkN();
        zzma();
        if (!this.zzbeQ) {
            zzFm().zzFJ().log("This instance being marked as an uploader");
            zzGf();
        }
        this.zzbeQ = true;
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public boolean zzGr() {
        zzkN();
        zzma();
        return this.zzbeQ || zzGh();
    }

    public void zzR(boolean z) {
        zzGn();
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public int zza(FileChannel fileChannel) {
        int i = 0;
        zzkN();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzFm().zzFE().log("Bad chanel to read from");
            return i;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0);
            int read = fileChannel.read(allocate);
            if (read != 4) {
                zzFm().zzFG().zzj("Unexpected data length or empty data in channel. Bytes read", Integer.valueOf(read));
                return i;
            }
            allocate.flip();
            return allocate.getInt();
        } catch (IOException e) {
            zzFm().zzFE().zzj("Failed to read from channel", e);
            return i;
        }
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void zza(AppMetadata appMetadata, long j) {
        zza zzfk = zzFh().zzfk(appMetadata.packageName);
        if (!(zzfk == null || zzfk.zzEb() == null || zzfk.zzEb().equals(appMetadata.zzbbK))) {
            zzFm().zzFG().log("New GMP App Id passed in. Removing cached database data.");
            zzFh().zzfp(zzfk.zziC());
            zzfk = null;
        }
        if (zzfk != null && zzfk.zzkV() != null && !zzfk.zzkV().equals(appMetadata.zzaUf)) {
            Bundle bundle = new Bundle();
            bundle.putString("_pv", zzfk.zzkV());
            zzb(new EventParcel("_au", new EventParams(bundle), "auto", j), appMetadata);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zza(zzh zzh, AppMetadata appMetadata) {
        zzkN();
        zzma();
        zzaa.zzz(zzh);
        zzaa.zzz(appMetadata);
        zzaa.zzdl(zzh.zzPx);
        zzaa.zzaj(zzh.zzPx.equals(appMetadata.packageName));
        zzsp.zze zze = new zzsp.zze();
        zze.zzbhd = Integer.valueOf(1);
        zze.zzbhl = "android";
        zze.appId = appMetadata.packageName;
        zze.zzbbL = appMetadata.zzbbL;
        zze.zzaUf = appMetadata.zzaUf;
        zze.zzbhy = Integer.valueOf((int) appMetadata.zzbbR);
        zze.zzbhp = Long.valueOf(appMetadata.zzbbM);
        zze.zzbbK = appMetadata.zzbbK;
        zze.zzbhu = appMetadata.zzbbN == 0 ? null : Long.valueOf(appMetadata.zzbbN);
        Pair zzfu = zzFn().zzfu(appMetadata.packageName);
        if (zzfu != null && !TextUtils.isEmpty((CharSequence) zzfu.first)) {
            zze.zzbhr = (String) zzfu.first;
            zze.zzbhs = (Boolean) zzfu.second;
        } else if (!zzFf().zzbc(this.mContext)) {
            String string = Secure.getString(this.mContext.getContentResolver(), "android_id");
            if (string == null) {
                zzFm().zzFG().log("null secure ID");
                string = SafeJsonPrimitive.NULL_STRING;
            } else if (string.isEmpty()) {
                zzFm().zzFG().log("empty secure ID");
            }
            zze.zzbhB = string;
        }
        zze.zzbhm = zzFf().zzjb();
        zze.osVersion = zzFf().zzFx();
        zze.zzbho = Integer.valueOf((int) zzFf().zzFy());
        zze.zzbhn = zzFf().zzFz();
        zze.zzbhq = null;
        zze.zzbhg = null;
        zze.zzbhh = null;
        zze.zzbhi = null;
        zza zzfk = zzFh().zzfk(appMetadata.packageName);
        if (zzfk == null) {
            zzfk = new zza(this, appMetadata.packageName);
            zzfk.zzeV(zzFn().zzFO());
            zzfk.zzeY(appMetadata.zzbbS);
            zzfk.zzeW(appMetadata.zzbbK);
            zzfk.zzeX(zzFn().zzfv(appMetadata.packageName));
            zzfk.zzW(0);
            zzfk.zzR(0);
            zzfk.zzS(0);
            zzfk.setAppVersion(appMetadata.zzaUf);
            zzfk.zzT(appMetadata.zzbbR);
            zzfk.zzeZ(appMetadata.zzbbL);
            zzfk.zzU(appMetadata.zzbbM);
            zzfk.zzV(appMetadata.zzbbN);
            zzfk.setMeasurementEnabled(appMetadata.zzbbP);
            zzFh().zza(zzfk);
        }
        zze.zzbht = zzfk.zzvx();
        zze.zzbbS = zzfk.zzEd();
        List zzfj = zzFh().zzfj(appMetadata.packageName);
        zze.zzbhf = new zzg[zzfj.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < zzfj.size()) {
                zzg zzg = new zzg();
                zze.zzbhf[i2] = zzg;
                zzg.name = ((zzak) zzfj.get(i2)).mName;
                zzg.zzbhF = Long.valueOf(((zzak) zzfj.get(i2)).zzbgg);
                zzFi().zza(zzg, ((zzak) zzfj.get(i2)).zzRF);
                i = i2 + 1;
            } else {
                try {
                    zzFh().zza(zzh, zzFh().zzb(zze));
                    return;
                } catch (IOException e) {
                    zzFm().zzFE().zzj("Data loss. Failed to insert raw event metadata", e);
                    return;
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public boolean zza(int i, FileChannel fileChannel) {
        zzkN();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzFm().zzFE().log("Bad chanel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() == 4) {
                return true;
            }
            zzFm().zzFE().zzj("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            return true;
        } catch (IOException e) {
            zzFm().zzFE().zzj("Failed to write to channel", e);
            return false;
        }
    }

    @WorkerThread
    public byte[] zza(@NonNull EventParcel eventParcel, @Size String str) {
        zzma();
        zzkN();
        zzGj();
        zzaa.zzz(eventParcel);
        zzaa.zzdl(str);
        zzd zzd = new zzd();
        zzFh().beginTransaction();
        try {
            zza zzfk = zzFh().zzfk(str);
            byte[] bArr;
            if (zzfk == null) {
                zzFm().zzFK().zzj("Log and bundle not available. package_name", str);
                bArr = new byte[0];
                return bArr;
            } else if (zzfk.zzEk()) {
                long j;
                zzsp.zze zze = new zzsp.zze();
                zzd.zzbhb = new zzsp.zze[]{zze};
                zze.zzbhd = Integer.valueOf(1);
                zze.zzbhl = "android";
                zze.appId = zzfk.zziC();
                zze.zzbbL = zzfk.zzEh();
                zze.zzaUf = zzfk.zzkV();
                zze.zzbhy = Integer.valueOf((int) zzfk.zzEg());
                zze.zzbhp = Long.valueOf(zzfk.zzEi());
                zze.zzbbK = zzfk.zzEb();
                zze.zzbhu = Long.valueOf(zzfk.zzEj());
                Pair zzfu = zzFn().zzfu(zzfk.zziC());
                if (!(zzfu == null || TextUtils.isEmpty((CharSequence) zzfu.first))) {
                    zze.zzbhr = (String) zzfu.first;
                    zze.zzbhs = (Boolean) zzfu.second;
                }
                zze.zzbhm = zzFf().zzjb();
                zze.osVersion = zzFf().zzFx();
                zze.zzbho = Integer.valueOf((int) zzFf().zzFy());
                zze.zzbhn = zzFf().zzFz();
                zze.zzbht = zzfk.zzvx();
                zze.zzbbS = zzfk.zzEd();
                List zzfj = zzFh().zzfj(zzfk.zziC());
                zze.zzbhf = new zzg[zzfj.size()];
                for (int i = 0; i < zzfj.size(); i++) {
                    zzg zzg = new zzg();
                    zze.zzbhf[i] = zzg;
                    zzg.name = ((zzak) zzfj.get(i)).mName;
                    zzg.zzbhF = Long.valueOf(((zzak) zzfj.get(i)).zzbgg);
                    zzFi().zza(zzg, ((zzak) zzfj.get(i)).zzRF);
                }
                Bundle zzFB = eventParcel.zzbcq.zzFB();
                if ("_iap".equals(eventParcel.name)) {
                    zzFB.putLong("_c", 1);
                }
                zzFB.putString("_o", eventParcel.zzbcr);
                zzi zzO = zzFh().zzO(str, eventParcel.name);
                if (zzO == null) {
                    zzFh().zza(new zzi(str, eventParcel.name, 1, 0, eventParcel.zzbcs));
                    j = 0;
                } else {
                    j = zzO.zzbcm;
                    zzFh().zza(zzO.zzag(eventParcel.zzbcs).zzFA());
                }
                zzh zzh = new zzh(this, eventParcel.zzbcr, str, eventParcel.name, eventParcel.zzbcs, j, zzFB);
                zzb zzb = new zzb();
                zze.zzbhe = new zzb[]{zzb};
                zzb.zzbgX = Long.valueOf(zzh.zzajg);
                zzb.name = zzh.mName;
                zzb.zzbgY = Long.valueOf(zzh.zzbci);
                zzb.zzbgW = new zzc[zzh.zzbcj.size()];
                Iterator it = zzh.zzbcj.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    String str2 = (String) it.next();
                    zzc zzc = new zzc();
                    int i3 = i2 + 1;
                    zzb.zzbgW[i2] = zzc;
                    zzc.name = str2;
                    zzFi().zza(zzc, zzh.zzbcj.get(str2));
                    i2 = i3;
                }
                zze.zzbhx = zza(zzfk.zziC(), zze.zzbhf, zze.zzbhe);
                zze.zzbhh = zzb.zzbgX;
                zze.zzbhi = zzb.zzbgX;
                long zzEf = zzfk.zzEf();
                zze.zzbhk = zzEf != 0 ? Long.valueOf(zzEf) : null;
                long zzEe = zzfk.zzEe();
                if (zzEe != 0) {
                    zzEf = zzEe;
                }
                zze.zzbhj = zzEf != 0 ? Long.valueOf(zzEf) : null;
                zzfk.zzEo();
                zze.zzbhv = Integer.valueOf((int) zzfk.zzEl());
                zze.zzbhq = Long.valueOf(zzFo().zzEi());
                zze.zzbhg = Long.valueOf(zzlQ().currentTimeMillis());
                zze.zzbhw = Boolean.TRUE;
                zzfk.zzR(zze.zzbhh.longValue());
                zzfk.zzS(zze.zzbhi.longValue());
                zzFh().zza(zzfk);
                zzFh().setTransactionSuccessful();
                zzFh().endTransaction();
                try {
                    bArr = new byte[zzd.getSerializedSize()];
                    zzamc zzO2 = zzamc.zzO(bArr);
                    zzd.writeTo(zzO2);
                    zzO2.zzWU();
                    return zzFi().zzh(bArr);
                } catch (IOException e) {
                    zzFm().zzFE().zzj("Data loss. Failed to bundle and serialize", e);
                    return null;
                }
            } else {
                zzFm().zzFK().zzj("Log and bundle disabled. package_name", str);
                bArr = new byte[0];
                zzFh().endTransaction();
                return bArr;
            }
        } finally {
            zzFh().endTransaction();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzai(long j) {
        return zzg(null, j);
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void zzb(AppMetadata appMetadata, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("_c", 1);
        zzb(new EventParcel("_f", new EventParams(bundle), "auto", j), appMetadata);
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:48:0x01d3=Splitter:B:48:0x01d3, B:75:0x02b4=Splitter:B:75:0x02b4} */
    @android.support.annotation.WorkerThread
    public void zzb(com.google.android.gms.measurement.internal.EventParcel r19, com.google.android.gms.measurement.internal.AppMetadata r20) {
        /*
        r18 = this;
        r16 = java.lang.System.nanoTime();
        r18.zzkN();
        r18.zzma();
        r0 = r20;
        r4 = r0.packageName;
        com.google.android.gms.common.internal.zzaa.zzdl(r4);
        r0 = r20;
        r2 = r0.zzbbK;
        r2 = android.text.TextUtils.isEmpty(r2);
        if (r2 == 0) goto L_0x001c;
    L_0x001b:
        return;
    L_0x001c:
        r0 = r20;
        r2 = r0.zzbbP;
        if (r2 != 0) goto L_0x002a;
    L_0x0022:
        r0 = r18;
        r1 = r20;
        r0.zze(r1);
        goto L_0x001b;
    L_0x002a:
        r2 = r18.zzFj();
        r0 = r19;
        r3 = r0.name;
        r2 = r2.zzV(r4, r3);
        if (r2 == 0) goto L_0x0059;
    L_0x0038:
        r2 = r18.zzFm();
        r2 = r2.zzFG();
        r3 = "Dropping blacklisted event";
        r0 = r19;
        r4 = r0.name;
        r2.zzj(r3, r4);
        r2 = r18.zzFi();
        r3 = 11;
        r4 = "_ev";
        r0 = r19;
        r5 = r0.name;
        r2.zze(r3, r4, r5);
        goto L_0x001b;
    L_0x0059:
        r2 = r18.zzFm();
        r3 = 2;
        r2 = r2.zzX(r3);
        if (r2 == 0) goto L_0x0073;
    L_0x0064:
        r2 = r18.zzFm();
        r2 = r2.zzFL();
        r3 = "Logging event";
        r0 = r19;
        r2.zzj(r3, r0);
    L_0x0073:
        r2 = r18.zzFh();
        r2.beginTransaction();
        r0 = r19;
        r2 = r0.zzbcq;	 Catch:{ all -> 0x0204 }
        r14 = r2.zzFB();	 Catch:{ all -> 0x0204 }
        r0 = r18;
        r1 = r20;
        r0.zze(r1);	 Catch:{ all -> 0x0204 }
        r2 = "_iap";
        r0 = r19;
        r3 = r0.name;	 Catch:{ all -> 0x0204 }
        r2 = r2.equals(r3);	 Catch:{ all -> 0x0204 }
        if (r2 != 0) goto L_0x00a1;
    L_0x0095:
        r2 = "ecommerce_purchase";
        r0 = r19;
        r3 = r0.name;	 Catch:{ all -> 0x0204 }
        r2 = r2.equals(r3);	 Catch:{ all -> 0x0204 }
        if (r2 == 0) goto L_0x0163;
    L_0x00a1:
        r2 = "currency";
        r5 = r14.getString(r2);	 Catch:{ all -> 0x0204 }
        r2 = "ecommerce_purchase";
        r0 = r19;
        r3 = r0.name;	 Catch:{ all -> 0x0204 }
        r2 = r2.equals(r3);	 Catch:{ all -> 0x0204 }
        if (r2 == 0) goto L_0x01f4;
    L_0x00b3:
        r2 = "value";
        r2 = r14.getDouble(r2);	 Catch:{ all -> 0x0204 }
        r6 = 4696837146684686336; // 0x412e848000000000 float:0.0 double:1000000.0;
        r2 = r2 * r6;
        r6 = 0;
        r6 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r6 != 0) goto L_0x00d2;
    L_0x00c5:
        r2 = "value";
        r2 = r14.getLong(r2);	 Catch:{ all -> 0x0204 }
        r2 = (double) r2;	 Catch:{ all -> 0x0204 }
        r6 = 4696837146684686336; // 0x412e848000000000 float:0.0 double:1000000.0;
        r2 = r2 * r6;
    L_0x00d2:
        r6 = 4890909195324358656; // 0x43e0000000000000 float:0.0 double:9.223372036854776E18;
        r6 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r6 > 0) goto L_0x01d3;
    L_0x00d8:
        r6 = -4332462841530417152; // 0xc3e0000000000000 float:0.0 double:-9.223372036854776E18;
        r6 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r6 < 0) goto L_0x01d3;
    L_0x00de:
        r2 = java.lang.Math.round(r2);	 Catch:{ all -> 0x0204 }
        r8 = r2;
    L_0x00e3:
        r2 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x0204 }
        if (r2 != 0) goto L_0x0163;
    L_0x00e9:
        r2 = java.util.Locale.US;	 Catch:{ all -> 0x0204 }
        r2 = r5.toUpperCase(r2);	 Catch:{ all -> 0x0204 }
        r3 = "[A-Z]{3}";
        r3 = r2.matches(r3);	 Catch:{ all -> 0x0204 }
        if (r3 == 0) goto L_0x0163;
    L_0x00f7:
        r3 = "_ltv_";
        r3 = java.lang.String.valueOf(r3);	 Catch:{ all -> 0x0204 }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ all -> 0x0204 }
        r5 = r2.length();	 Catch:{ all -> 0x0204 }
        if (r5 == 0) goto L_0x01fd;
    L_0x0107:
        r5 = r3.concat(r2);	 Catch:{ all -> 0x0204 }
    L_0x010b:
        r2 = r18.zzFh();	 Catch:{ all -> 0x0204 }
        r2 = r2.zzQ(r4, r5);	 Catch:{ all -> 0x0204 }
        if (r2 == 0) goto L_0x011b;
    L_0x0115:
        r3 = r2.zzRF;	 Catch:{ all -> 0x0204 }
        r3 = r3 instanceof java.lang.Long;	 Catch:{ all -> 0x0204 }
        if (r3 != 0) goto L_0x020d;
    L_0x011b:
        r2 = r18.zzFh();	 Catch:{ all -> 0x0204 }
        r3 = r18.zzFo();	 Catch:{ all -> 0x0204 }
        r3 = r3.zzfe(r4);	 Catch:{ all -> 0x0204 }
        r3 = r3 + -1;
        r2.zzy(r4, r3);	 Catch:{ all -> 0x0204 }
        r3 = new com.google.android.gms.measurement.internal.zzak;	 Catch:{ all -> 0x0204 }
        r2 = r18.zzlQ();	 Catch:{ all -> 0x0204 }
        r6 = r2.currentTimeMillis();	 Catch:{ all -> 0x0204 }
        r8 = java.lang.Long.valueOf(r8);	 Catch:{ all -> 0x0204 }
        r3.<init>(r4, r5, r6, r8);	 Catch:{ all -> 0x0204 }
    L_0x013d:
        r2 = r18.zzFh();	 Catch:{ all -> 0x0204 }
        r2 = r2.zza(r3);	 Catch:{ all -> 0x0204 }
        if (r2 != 0) goto L_0x0163;
    L_0x0147:
        r2 = r18.zzFm();	 Catch:{ all -> 0x0204 }
        r2 = r2.zzFE();	 Catch:{ all -> 0x0204 }
        r5 = "Too many unique user properties are set. Ignoring user property.";
        r6 = r3.mName;	 Catch:{ all -> 0x0204 }
        r3 = r3.zzRF;	 Catch:{ all -> 0x0204 }
        r2.zze(r5, r6, r3);	 Catch:{ all -> 0x0204 }
        r2 = r18.zzFi();	 Catch:{ all -> 0x0204 }
        r3 = 9;
        r5 = 0;
        r6 = 0;
        r2.zze(r3, r5, r6);	 Catch:{ all -> 0x0204 }
    L_0x0163:
        r0 = r19;
        r2 = r0.name;	 Catch:{ all -> 0x0204 }
        r9 = com.google.android.gms.measurement.internal.zzal.zzfG(r2);	 Catch:{ all -> 0x0204 }
        com.google.android.gms.measurement.internal.zzal.zzK(r14);	 Catch:{ all -> 0x0204 }
        r2 = "_err";
        r0 = r19;
        r3 = r0.name;	 Catch:{ all -> 0x0204 }
        r11 = r2.equals(r3);	 Catch:{ all -> 0x0204 }
        r5 = r18.zzFh();	 Catch:{ all -> 0x0204 }
        r6 = r18.zzGi();	 Catch:{ all -> 0x0204 }
        r10 = 0;
        r8 = r4;
        r2 = r5.zza(r6, r8, r9, r10, r11);	 Catch:{ all -> 0x0204 }
        r6 = r2.zzbbY;	 Catch:{ all -> 0x0204 }
        r3 = r18.zzFo();	 Catch:{ all -> 0x0204 }
        r12 = r3.zzEE();	 Catch:{ all -> 0x0204 }
        r6 = r6 - r12;
        r12 = 0;
        r3 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r3 <= 0) goto L_0x0229;
    L_0x0197:
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r4 = r6 % r4;
        r6 = 1;
        r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r3 != 0) goto L_0x01b4;
    L_0x01a1:
        r3 = r18.zzFm();	 Catch:{ all -> 0x0204 }
        r3 = r3.zzFE();	 Catch:{ all -> 0x0204 }
        r4 = "Data loss. Too many events logged. count";
        r6 = r2.zzbbY;	 Catch:{ all -> 0x0204 }
        r2 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x0204 }
        r3.zzj(r4, r2);	 Catch:{ all -> 0x0204 }
    L_0x01b4:
        r2 = r18.zzFi();	 Catch:{ all -> 0x0204 }
        r3 = 16;
        r4 = "_ev";
        r0 = r19;
        r5 = r0.name;	 Catch:{ all -> 0x0204 }
        r2.zze(r3, r4, r5);	 Catch:{ all -> 0x0204 }
        r2 = r18.zzFh();	 Catch:{ all -> 0x0204 }
        r2.setTransactionSuccessful();	 Catch:{ all -> 0x0204 }
        r2 = r18.zzFh();
        r2.endTransaction();
        goto L_0x001b;
    L_0x01d3:
        r4 = r18.zzFm();	 Catch:{ all -> 0x0204 }
        r4 = r4.zzFG();	 Catch:{ all -> 0x0204 }
        r5 = "Data lost. Currency value is too big";
        r2 = java.lang.Double.valueOf(r2);	 Catch:{ all -> 0x0204 }
        r4.zzj(r5, r2);	 Catch:{ all -> 0x0204 }
        r2 = r18.zzFh();	 Catch:{ all -> 0x0204 }
        r2.setTransactionSuccessful();	 Catch:{ all -> 0x0204 }
        r2 = r18.zzFh();
        r2.endTransaction();
        goto L_0x001b;
    L_0x01f4:
        r2 = "value";
        r2 = r14.getLong(r2);	 Catch:{ all -> 0x0204 }
        r8 = r2;
        goto L_0x00e3;
    L_0x01fd:
        r5 = new java.lang.String;	 Catch:{ all -> 0x0204 }
        r5.<init>(r3);	 Catch:{ all -> 0x0204 }
        goto L_0x010b;
    L_0x0204:
        r2 = move-exception;
        r3 = r18.zzFh();
        r3.endTransaction();
        throw r2;
    L_0x020d:
        r2 = r2.zzRF;	 Catch:{ all -> 0x0204 }
        r2 = (java.lang.Long) r2;	 Catch:{ all -> 0x0204 }
        r10 = r2.longValue();	 Catch:{ all -> 0x0204 }
        r3 = new com.google.android.gms.measurement.internal.zzak;	 Catch:{ all -> 0x0204 }
        r2 = r18.zzlQ();	 Catch:{ all -> 0x0204 }
        r6 = r2.currentTimeMillis();	 Catch:{ all -> 0x0204 }
        r8 = r8 + r10;
        r8 = java.lang.Long.valueOf(r8);	 Catch:{ all -> 0x0204 }
        r3.<init>(r4, r5, r6, r8);	 Catch:{ all -> 0x0204 }
        goto L_0x013d;
    L_0x0229:
        if (r9 == 0) goto L_0x0278;
    L_0x022b:
        r6 = r2.zzbbX;	 Catch:{ all -> 0x0204 }
        r3 = r18.zzFo();	 Catch:{ all -> 0x0204 }
        r8 = r3.zzEF();	 Catch:{ all -> 0x0204 }
        r6 = r6 - r8;
        r8 = 0;
        r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r3 <= 0) goto L_0x0278;
    L_0x023c:
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r4 = r6 % r4;
        r6 = 1;
        r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r3 != 0) goto L_0x0259;
    L_0x0246:
        r3 = r18.zzFm();	 Catch:{ all -> 0x0204 }
        r3 = r3.zzFE();	 Catch:{ all -> 0x0204 }
        r4 = "Data loss. Too many public events logged. count";
        r6 = r2.zzbbX;	 Catch:{ all -> 0x0204 }
        r2 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x0204 }
        r3.zzj(r4, r2);	 Catch:{ all -> 0x0204 }
    L_0x0259:
        r2 = r18.zzFi();	 Catch:{ all -> 0x0204 }
        r3 = 16;
        r4 = "_ev";
        r0 = r19;
        r5 = r0.name;	 Catch:{ all -> 0x0204 }
        r2.zze(r3, r4, r5);	 Catch:{ all -> 0x0204 }
        r2 = r18.zzFh();	 Catch:{ all -> 0x0204 }
        r2.setTransactionSuccessful();	 Catch:{ all -> 0x0204 }
        r2 = r18.zzFh();
        r2.endTransaction();
        goto L_0x001b;
    L_0x0278:
        if (r11 == 0) goto L_0x02b4;
    L_0x027a:
        r6 = r2.zzbca;	 Catch:{ all -> 0x0204 }
        r3 = r18.zzFo();	 Catch:{ all -> 0x0204 }
        r8 = r3.zzEG();	 Catch:{ all -> 0x0204 }
        r6 = r6 - r8;
        r8 = 0;
        r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r3 <= 0) goto L_0x02b4;
    L_0x028b:
        r4 = 1;
        r3 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1));
        if (r3 != 0) goto L_0x02a4;
    L_0x0291:
        r3 = r18.zzFm();	 Catch:{ all -> 0x0204 }
        r3 = r3.zzFE();	 Catch:{ all -> 0x0204 }
        r4 = "Too many error events logged. count";
        r6 = r2.zzbca;	 Catch:{ all -> 0x0204 }
        r2 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x0204 }
        r3.zzj(r4, r2);	 Catch:{ all -> 0x0204 }
    L_0x02a4:
        r2 = r18.zzFh();	 Catch:{ all -> 0x0204 }
        r2.setTransactionSuccessful();	 Catch:{ all -> 0x0204 }
        r2 = r18.zzFh();
        r2.endTransaction();
        goto L_0x001b;
    L_0x02b4:
        r2 = r18.zzFi();	 Catch:{ all -> 0x0204 }
        r3 = "_o";
        r0 = r19;
        r5 = r0.zzbcr;	 Catch:{ all -> 0x0204 }
        r2.zza(r14, r3, r5);	 Catch:{ all -> 0x0204 }
        r2 = r18.zzFh();	 Catch:{ all -> 0x0204 }
        r2 = r2.zzfl(r4);	 Catch:{ all -> 0x0204 }
        r6 = 0;
        r5 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r5 <= 0) goto L_0x02e0;
    L_0x02cf:
        r5 = r18.zzFm();	 Catch:{ all -> 0x0204 }
        r5 = r5.zzFG();	 Catch:{ all -> 0x0204 }
        r6 = "Data lost. Too many events stored on disk, deleted";
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ all -> 0x0204 }
        r5.zzj(r6, r2);	 Catch:{ all -> 0x0204 }
    L_0x02e0:
        r5 = new com.google.android.gms.measurement.internal.zzh;	 Catch:{ all -> 0x0204 }
        r0 = r19;
        r7 = r0.zzbcr;	 Catch:{ all -> 0x0204 }
        r0 = r19;
        r9 = r0.name;	 Catch:{ all -> 0x0204 }
        r0 = r19;
        r10 = r0.zzbcs;	 Catch:{ all -> 0x0204 }
        r12 = 0;
        r6 = r18;
        r8 = r4;
        r5.<init>(r6, r7, r8, r9, r10, r12, r14);	 Catch:{ all -> 0x0204 }
        r2 = r18.zzFh();	 Catch:{ all -> 0x0204 }
        r3 = r5.mName;	 Catch:{ all -> 0x0204 }
        r2 = r2.zzO(r4, r3);	 Catch:{ all -> 0x0204 }
        if (r2 != 0) goto L_0x03ac;
    L_0x0302:
        r2 = r18.zzFh();	 Catch:{ all -> 0x0204 }
        r2 = r2.zzfr(r4);	 Catch:{ all -> 0x0204 }
        r6 = r18.zzFo();	 Catch:{ all -> 0x0204 }
        r6 = r6.zzED();	 Catch:{ all -> 0x0204 }
        r6 = (long) r6;	 Catch:{ all -> 0x0204 }
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 < 0) goto L_0x0346;
    L_0x0317:
        r2 = r18.zzFm();	 Catch:{ all -> 0x0204 }
        r2 = r2.zzFE();	 Catch:{ all -> 0x0204 }
        r3 = "Too many event names used, ignoring event. name, supported count";
        r4 = r5.mName;	 Catch:{ all -> 0x0204 }
        r5 = r18.zzFo();	 Catch:{ all -> 0x0204 }
        r5 = r5.zzED();	 Catch:{ all -> 0x0204 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ all -> 0x0204 }
        r2.zze(r3, r4, r5);	 Catch:{ all -> 0x0204 }
        r2 = r18.zzFi();	 Catch:{ all -> 0x0204 }
        r3 = 8;
        r4 = 0;
        r5 = 0;
        r2.zze(r3, r4, r5);	 Catch:{ all -> 0x0204 }
        r2 = r18.zzFh();
        r2.endTransaction();
        goto L_0x001b;
    L_0x0346:
        r7 = new com.google.android.gms.measurement.internal.zzi;	 Catch:{ all -> 0x0204 }
        r9 = r5.mName;	 Catch:{ all -> 0x0204 }
        r10 = 0;
        r12 = 0;
        r14 = r5.zzajg;	 Catch:{ all -> 0x0204 }
        r8 = r4;
        r7.<init>(r8, r9, r10, r12, r14);	 Catch:{ all -> 0x0204 }
    L_0x0354:
        r2 = r18.zzFh();	 Catch:{ all -> 0x0204 }
        r2.zza(r7);	 Catch:{ all -> 0x0204 }
        r0 = r18;
        r1 = r20;
        r0.zza(r5, r1);	 Catch:{ all -> 0x0204 }
        r2 = r18.zzFh();	 Catch:{ all -> 0x0204 }
        r2.setTransactionSuccessful();	 Catch:{ all -> 0x0204 }
        r2 = r18.zzFm();	 Catch:{ all -> 0x0204 }
        r3 = 2;
        r2 = r2.zzX(r3);	 Catch:{ all -> 0x0204 }
        if (r2 == 0) goto L_0x0381;
    L_0x0374:
        r2 = r18.zzFm();	 Catch:{ all -> 0x0204 }
        r2 = r2.zzFL();	 Catch:{ all -> 0x0204 }
        r3 = "Event recorded";
        r2.zzj(r3, r5);	 Catch:{ all -> 0x0204 }
    L_0x0381:
        r2 = r18.zzFh();
        r2.endTransaction();
        r18.zzGn();
        r2 = r18.zzFm();
        r2 = r2.zzFL();
        r3 = "Background event processing time, ms";
        r4 = java.lang.System.nanoTime();
        r4 = r4 - r16;
        r6 = 500000; // 0x7a120 float:7.00649E-40 double:2.47033E-318;
        r4 = r4 + r6;
        r6 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r4 = r4 / r6;
        r4 = java.lang.Long.valueOf(r4);
        r2.zzj(r3, r4);
        goto L_0x001b;
    L_0x03ac:
        r6 = r2.zzbcm;	 Catch:{ all -> 0x0204 }
        r0 = r18;
        r5 = r5.zza(r0, r6);	 Catch:{ all -> 0x0204 }
        r6 = r5.zzajg;	 Catch:{ all -> 0x0204 }
        r7 = r2.zzag(r6);	 Catch:{ all -> 0x0204 }
        goto L_0x0354;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzx.zzb(com.google.android.gms.measurement.internal.EventParcel, com.google.android.gms.measurement.internal.AppMetadata):void");
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void zzb(EventParcel eventParcel, String str) {
        zza zzfk = zzFh().zzfk(str);
        if (zzfk == null || TextUtils.isEmpty(zzfk.zzkV())) {
            zzFm().zzFK().zzj("No app data available; dropping event", str);
            return;
        }
        try {
            String str2 = getContext().getPackageManager().getPackageInfo(str, 0).versionName;
            if (!(zzfk.zzkV() == null || zzfk.zzkV().equals(str2))) {
                zzFm().zzFG().zzj("App version does not match; dropping event", str);
                return;
            }
        } catch (NameNotFoundException e) {
            if (!"_ui".equals(eventParcel.name)) {
                zzFm().zzFG().zzj("Could not find package", str);
            }
        }
        EventParcel eventParcel2 = eventParcel;
        zzb(eventParcel2, new AppMetadata(str, zzfk.zzEb(), zzfk.zzkV(), zzfk.zzEg(), zzfk.zzEh(), zzfk.zzEi(), zzfk.zzEj(), null, zzfk.zzEk(), false, zzfk.zzEd()));
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void zzb(UserAttributeParcel userAttributeParcel, AppMetadata appMetadata) {
        zzkN();
        zzma();
        if (!TextUtils.isEmpty(appMetadata.zzbbK)) {
            if (appMetadata.zzbbP) {
                int zzfK = zzFi().zzfK(userAttributeParcel.name);
                if (zzfK != 0) {
                    zzFi().zze(zzfK, "_ev", zzFi().zza(userAttributeParcel.name, zzFo().zzEx(), true));
                    return;
                }
                zzfK = zzFi().zzm(userAttributeParcel.name, userAttributeParcel.getValue());
                if (zzfK != 0) {
                    zzFi().zze(zzfK, "_ev", zzFi().zza(userAttributeParcel.name, zzFo().zzEx(), true));
                    return;
                }
                Object zzn = zzFi().zzn(userAttributeParcel.name, userAttributeParcel.getValue());
                if (zzn != null) {
                    zzak zzak = new zzak(appMetadata.packageName, userAttributeParcel.name, userAttributeParcel.zzbgc, zzn);
                    zzFm().zzFK().zze("Setting user property", zzak.mName, zzn);
                    zzFh().beginTransaction();
                    try {
                        zze(appMetadata);
                        boolean zza = zzFh().zza(zzak);
                        zzFh().setTransactionSuccessful();
                        if (zza) {
                            zzFm().zzFK().zze("User property set", zzak.mName, zzak.zzRF);
                        } else {
                            zzFm().zzFE().zze("Too many unique user properties are set. Ignoring user property.", zzak.mName, zzak.zzRF);
                            zzFi().zze(9, null, null);
                        }
                        zzFh().endTransaction();
                        return;
                    } catch (Throwable th) {
                        zzFh().endTransaction();
                    }
                } else {
                    return;
                }
            }
            zze(appMetadata);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zzb(zzaa zzaa) {
        this.zzbeV++;
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void zzb(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        int i2 = 0;
        zzkN();
        zzma();
        zzaa.zzdl(str);
        if (bArr == null) {
            bArr = new byte[0];
        }
        zzFh().beginTransaction();
        try {
            zza zzfk = zzFh().zzfk(str);
            int i3 = ((i == 200 || i == 204 || i == 304) && th == null) ? 1 : 0;
            if (zzfk == null) {
                zzFm().zzFG().zzj("App does not exist in onConfigFetched", str);
            } else if (i3 != 0 || i == 404) {
                List list = map != null ? (List) map.get("Last-Modified") : null;
                String str2 = (list == null || list.size() <= 0) ? null : (String) list.get(0);
                if (i == 404 || i == 304) {
                    if (zzFj().zzfy(str) == null && !zzFj().zzb(str, null, null)) {
                        zzFh().endTransaction();
                        return;
                    }
                } else if (!zzFj().zzb(str, bArr, str2)) {
                    zzFh().endTransaction();
                    return;
                }
                zzfk.zzX(zzlQ().currentTimeMillis());
                zzFh().zza(zzfk);
                if (i == 404) {
                    zzFm().zzFG().log("Config not found. Using empty config");
                } else {
                    zzFm().zzFL().zze("Successfully fetched config. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                }
                if (zzGb().zzod() && zzGm()) {
                    zzGl();
                } else {
                    zzGn();
                }
            } else {
                zzfk.zzY(zzlQ().currentTimeMillis());
                zzFh().zza(zzfk);
                zzFm().zzFL().zze("Fetching config failed. code, error", Integer.valueOf(i), th);
                zzFj().zzfA(str);
                zzFn().zzbdH.set(zzlQ().currentTimeMillis());
                if (i == 503 || i == 429) {
                    i2 = 1;
                }
                if (i2 != 0) {
                    zzFn().zzbdI.set(zzlQ().currentTimeMillis());
                }
                zzGn();
            }
            zzFh().setTransactionSuccessful();
        } finally {
            zzFh().endTransaction();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zzc(AppMetadata appMetadata) {
        zzkN();
        zzma();
        zzaa.zzdl(appMetadata.packageName);
        zze(appMetadata);
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void zzc(AppMetadata appMetadata, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("_et", 1);
        zzb(new EventParcel("_e", new EventParams(bundle), "auto", j), appMetadata);
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void zzc(UserAttributeParcel userAttributeParcel, AppMetadata appMetadata) {
        zzkN();
        zzma();
        if (!TextUtils.isEmpty(appMetadata.zzbbK)) {
            if (appMetadata.zzbbP) {
                zzFm().zzFK().zzj("Removing user property", userAttributeParcel.name);
                zzFh().beginTransaction();
                try {
                    zze(appMetadata);
                    zzFh().zzP(appMetadata.packageName, userAttributeParcel.name);
                    zzFh().setTransactionSuccessful();
                    zzFm().zzFK().zzj("User property removed", userAttributeParcel.name);
                } finally {
                    zzFh().endTransaction();
                }
            } else {
                zze(appMetadata);
            }
        }
    }

    @WorkerThread
    public void zzd(AppMetadata appMetadata) {
        zzkN();
        zzma();
        zzaa.zzz(appMetadata);
        zzaa.zzdl(appMetadata.packageName);
        if (!TextUtils.isEmpty(appMetadata.zzbbK)) {
            if (appMetadata.zzbbP) {
                long currentTimeMillis = zzlQ().currentTimeMillis();
                zzFh().beginTransaction();
                try {
                    zza(appMetadata, currentTimeMillis);
                    zze(appMetadata);
                    if (zzFh().zzO(appMetadata.packageName, "_f") == null) {
                        zzb(new UserAttributeParcel("_fot", currentTimeMillis, Long.valueOf((1 + (currentTimeMillis / 3600000)) * 3600000), "auto"), appMetadata);
                        zzb(appMetadata, currentTimeMillis);
                        zzc(appMetadata, currentTimeMillis);
                    } else if (appMetadata.zzbbQ) {
                        zzd(appMetadata, currentTimeMillis);
                    }
                    zzFh().setTransactionSuccessful();
                } finally {
                    zzFh().endTransaction();
                }
            } else {
                zze(appMetadata);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void zzd(AppMetadata appMetadata, long j) {
        zzb(new EventParcel("_cd", new EventParams(new Bundle()), "auto", j), appMetadata);
    }

    @WorkerThread
    public void zzkN() {
        zzFl().zzkN();
    }

    /* Access modifiers changed, original: 0000 */
    public void zzlP() {
        if (zzFo().zzmW()) {
            throw new IllegalStateException("Unexpected call on package side");
        }
    }

    public zze zzlQ() {
        return this.zzsd;
    }

    /* Access modifiers changed, original: 0000 */
    public void zzma() {
        if (!this.zzWh) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public boolean zzv(int i, int i2) {
        zzkN();
        if (i > i2) {
            zzFm().zzFE().zze("Panic: can't downgrade version. Previous, current version", Integer.valueOf(i), Integer.valueOf(i2));
            return false;
        }
        if (i < i2) {
            if (zza(i2, zzGe())) {
                zzFm().zzFL().zze("Storage version upgraded. Previous, current version", Integer.valueOf(i), Integer.valueOf(i2));
            } else {
                zzFm().zzFE().zze("Storage version upgrade failed. Previous, current version", Integer.valueOf(i), Integer.valueOf(i2));
                return false;
            }
        }
        return true;
    }
}
