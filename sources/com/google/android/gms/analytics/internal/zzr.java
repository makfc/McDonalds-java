package com.google.android.gms.analytics.internal;

import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zzt;
import java.util.HashSet;
import java.util.Set;

public class zzr {
    private final zzf zzTE;
    private volatile Boolean zzXt;
    private String zzXu;
    private Set<Integer> zzXv;

    protected zzr(zzf zzf) {
        zzaa.zzz(zzf);
        this.zzTE = zzf;
    }

    public boolean zzmW() {
        return false;
    }

    public boolean zzmX() {
        if (this.zzXt == null) {
            synchronized (this) {
                if (this.zzXt == null) {
                    ApplicationInfo applicationInfo = this.zzTE.getContext().getApplicationInfo();
                    String zzvj = zzt.zzvj();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        boolean z = str != null && str.equals(zzvj);
                        this.zzXt = Boolean.valueOf(z);
                    }
                    if ((this.zzXt == null || !this.zzXt.booleanValue()) && "com.google.android.gms.analytics".equals(zzvj)) {
                        this.zzXt = Boolean.TRUE;
                    }
                    if (this.zzXt == null) {
                        this.zzXt = Boolean.TRUE;
                        this.zzTE.zzlR().zzbK("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzXt.booleanValue();
    }

    public boolean zzmY() {
        return ((Boolean) zzy.zzXE.get()).booleanValue();
    }

    public int zzmZ() {
        return ((Integer) zzy.zzXX.get()).intValue();
    }

    public int zznA() {
        return ((Integer) zzy.zzYi.get()).intValue();
    }

    public long zznB() {
        return ((Long) zzy.zzYj.get()).longValue();
    }

    public long zznC() {
        return ((Long) zzy.zzYs.get()).longValue();
    }

    public int zzna() {
        return ((Integer) zzy.zzYb.get()).intValue();
    }

    public int zznb() {
        return ((Integer) zzy.zzYc.get()).intValue();
    }

    public int zznc() {
        return ((Integer) zzy.zzYd.get()).intValue();
    }

    public long zznd() {
        return ((Long) zzy.zzXM.get()).longValue();
    }

    public long zzne() {
        return ((Long) zzy.zzXL.get()).longValue();
    }

    public long zznf() {
        return ((Long) zzy.zzXP.get()).longValue();
    }

    public long zzng() {
        return ((Long) zzy.zzXQ.get()).longValue();
    }

    public int zznh() {
        return ((Integer) zzy.zzXR.get()).intValue();
    }

    public int zzni() {
        return ((Integer) zzy.zzXS.get()).intValue();
    }

    public long zznj() {
        return (long) ((Integer) zzy.zzYf.get()).intValue();
    }

    public String zznk() {
        return (String) zzy.zzXU.get();
    }

    public String zznl() {
        return (String) zzy.zzXT.get();
    }

    public String zznm() {
        return (String) zzy.zzXV.get();
    }

    public String zznn() {
        return (String) zzy.zzXW.get();
    }

    public zzm zzno() {
        return zzm.zzbP((String) zzy.zzXY.get());
    }

    public zzo zznp() {
        return zzo.zzbQ((String) zzy.zzXZ.get());
    }

    public Set<Integer> zznq() {
        String str = (String) zzy.zzYe.get();
        if (this.zzXv == null || this.zzXu == null || !this.zzXu.equals(str)) {
            String[] split = TextUtils.split(str, ",");
            HashSet hashSet = new HashSet();
            for (String parseInt : split) {
                try {
                    hashSet.add(Integer.valueOf(Integer.parseInt(parseInt)));
                } catch (NumberFormatException e) {
                }
            }
            this.zzXu = str;
            this.zzXv = hashSet;
        }
        return this.zzXv;
    }

    public long zznr() {
        return ((Long) zzy.zzYn.get()).longValue();
    }

    public long zzns() {
        return ((Long) zzy.zzYo.get()).longValue();
    }

    public long zznt() {
        return ((Long) zzy.zzYr.get()).longValue();
    }

    public int zznu() {
        return ((Integer) zzy.zzXI.get()).intValue();
    }

    public int zznv() {
        return ((Integer) zzy.zzXK.get()).intValue();
    }

    public String zznw() {
        return "google_analytics_v4.db";
    }

    public String zznx() {
        return "google_analytics2_v4.db";
    }

    public long zzny() {
        return 86400000;
    }

    public int zznz() {
        return ((Integer) zzy.zzYh.get()).intValue();
    }
}
