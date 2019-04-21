package com.google.android.gms.internal;

import com.google.android.gms.internal.zzaf.zzh;
import com.google.android.gms.tagmanager.zzbn;
import com.google.android.gms.tagmanager.zzdl;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class zzabt {

    public static class zza {
        private final com.google.android.gms.internal.zzag.zza zzbqS;
        private final Map<String, com.google.android.gms.internal.zzag.zza> zzbvr;

        private zza(Map<String, com.google.android.gms.internal.zzag.zza> map, com.google.android.gms.internal.zzag.zza zza) {
            this.zzbvr = map;
            this.zzbqS = zza;
        }

        public static zzb zzMz() {
            return new zzb();
        }

        public String toString() {
            String valueOf = String.valueOf(zzLY());
            String valueOf2 = String.valueOf(this.zzbqS);
            return new StringBuilder((String.valueOf(valueOf).length() + 32) + String.valueOf(valueOf2).length()).append("Properties: ").append(valueOf).append(" pushAfterEvaluate: ").append(valueOf2).toString();
        }

        public com.google.android.gms.internal.zzag.zza zzKu() {
            return this.zzbqS;
        }

        public Map<String, com.google.android.gms.internal.zzag.zza> zzLY() {
            return Collections.unmodifiableMap(this.zzbvr);
        }

        public void zza(String str, com.google.android.gms.internal.zzag.zza zza) {
            this.zzbvr.put(str, zza);
        }
    }

    public static class zzb {
        private com.google.android.gms.internal.zzag.zza zzbqS;
        private final Map<String, com.google.android.gms.internal.zzag.zza> zzbvr;

        private zzb() {
            this.zzbvr = new HashMap();
        }

        public zza zzMA() {
            return new zza(this.zzbvr, this.zzbqS);
        }

        public zzb zzb(String str, com.google.android.gms.internal.zzag.zza zza) {
            this.zzbvr.put(str, zza);
            return this;
        }

        public zzb zzq(com.google.android.gms.internal.zzag.zza zza) {
            this.zzbqS = zza;
            return this;
        }
    }

    public static class zzc {
        private final String zzahE;
        private final List<zze> zzbvo;
        private final Map<String, List<zza>> zzbvp;
        private final int zzbvq;

        private zzc(List<zze> list, Map<String, List<zza>> map, String str, int i) {
            this.zzbvo = Collections.unmodifiableList(list);
            this.zzbvp = Collections.unmodifiableMap(map);
            this.zzahE = str;
            this.zzbvq = i;
        }

        public static zzd zzMB() {
            return new zzd();
        }

        public String getVersion() {
            return this.zzahE;
        }

        public String toString() {
            String valueOf = String.valueOf(zzLW());
            String valueOf2 = String.valueOf(this.zzbvp);
            return new StringBuilder((String.valueOf(valueOf).length() + 17) + String.valueOf(valueOf2).length()).append("Rules: ").append(valueOf).append("  Macros: ").append(valueOf2).toString();
        }

        public List<zze> zzLW() {
            return this.zzbvo;
        }

        public Map<String, List<zza>> zzMC() {
            return this.zzbvp;
        }
    }

    public static class zzd {
        private String zzahE;
        private final List<zze> zzbvo;
        private final Map<String, List<zza>> zzbvp;
        private int zzbvq;

        private zzd() {
            this.zzbvo = new ArrayList();
            this.zzbvp = new HashMap();
            this.zzahE = "";
            this.zzbvq = 0;
        }

        public zzc zzMD() {
            return new zzc(this.zzbvo, this.zzbvp, this.zzahE, this.zzbvq);
        }

        public zzd zzb(zze zze) {
            this.zzbvo.add(zze);
            return this;
        }

        public zzd zzc(zza zza) {
            String zzg = zzdl.zzg((com.google.android.gms.internal.zzag.zza) zza.zzLY().get(zzae.INSTANCE_NAME.toString()));
            List list = (List) this.zzbvp.get(zzg);
            if (list == null) {
                list = new ArrayList();
                this.zzbvp.put(zzg, list);
            }
            list.add(zza);
            return this;
        }

        public zzd zzhx(String str) {
            this.zzahE = str;
            return this;
        }

        public zzd zzlc(int i) {
            this.zzbvq = i;
            return this;
        }
    }

    public static class zze {
        private final List<zza> zzbvt;
        private final List<zza> zzbvu;
        private final List<zza> zzbvv;
        private final List<zza> zzbvw;
        private final List<zza> zzbwc;
        private final List<zza> zzbwd;
        private final List<String> zzbwe;
        private final List<String> zzbwf;
        private final List<String> zzbwg;
        private final List<String> zzbwh;

        private zze(List<zza> list, List<zza> list2, List<zza> list3, List<zza> list4, List<zza> list5, List<zza> list6, List<String> list7, List<String> list8, List<String> list9, List<String> list10) {
            this.zzbvt = Collections.unmodifiableList(list);
            this.zzbvu = Collections.unmodifiableList(list2);
            this.zzbvv = Collections.unmodifiableList(list3);
            this.zzbvw = Collections.unmodifiableList(list4);
            this.zzbwc = Collections.unmodifiableList(list5);
            this.zzbwd = Collections.unmodifiableList(list6);
            this.zzbwe = Collections.unmodifiableList(list7);
            this.zzbwf = Collections.unmodifiableList(list8);
            this.zzbwg = Collections.unmodifiableList(list9);
            this.zzbwh = Collections.unmodifiableList(list10);
        }

        public static zzf zzME() {
            return new zzf();
        }

        public String toString() {
            String valueOf = String.valueOf(zzMa());
            String valueOf2 = String.valueOf(zzMb());
            String valueOf3 = String.valueOf(zzMc());
            String valueOf4 = String.valueOf(zzMd());
            String valueOf5 = String.valueOf(zzMF());
            String valueOf6 = String.valueOf(zzMK());
            return new StringBuilder((((((String.valueOf(valueOf).length() + 102) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length()) + String.valueOf(valueOf4).length()) + String.valueOf(valueOf5).length()) + String.valueOf(valueOf6).length()).append("Positive predicates: ").append(valueOf).append("  Negative predicates: ").append(valueOf2).append("  Add tags: ").append(valueOf3).append("  Remove tags: ").append(valueOf4).append("  Add macros: ").append(valueOf5).append("  Remove macros: ").append(valueOf6).toString();
        }

        public List<zza> zzMF() {
            return this.zzbwc;
        }

        public List<String> zzMG() {
            return this.zzbwe;
        }

        public List<String> zzMH() {
            return this.zzbwf;
        }

        public List<String> zzMI() {
            return this.zzbwg;
        }

        public List<String> zzMJ() {
            return this.zzbwh;
        }

        public List<zza> zzMK() {
            return this.zzbwd;
        }

        public List<zza> zzMa() {
            return this.zzbvt;
        }

        public List<zza> zzMb() {
            return this.zzbvu;
        }

        public List<zza> zzMc() {
            return this.zzbvv;
        }

        public List<zza> zzMd() {
            return this.zzbvw;
        }
    }

    public static class zzf {
        private final List<zza> zzbvt;
        private final List<zza> zzbvu;
        private final List<zza> zzbvv;
        private final List<zza> zzbvw;
        private final List<zza> zzbwc;
        private final List<zza> zzbwd;
        private final List<String> zzbwe;
        private final List<String> zzbwf;
        private final List<String> zzbwg;
        private final List<String> zzbwh;

        private zzf() {
            this.zzbvt = new ArrayList();
            this.zzbvu = new ArrayList();
            this.zzbvv = new ArrayList();
            this.zzbvw = new ArrayList();
            this.zzbwc = new ArrayList();
            this.zzbwd = new ArrayList();
            this.zzbwe = new ArrayList();
            this.zzbwf = new ArrayList();
            this.zzbwg = new ArrayList();
            this.zzbwh = new ArrayList();
        }

        public zze zzML() {
            return new zze(this.zzbvt, this.zzbvu, this.zzbvv, this.zzbvw, this.zzbwc, this.zzbwd, this.zzbwe, this.zzbwf, this.zzbwg, this.zzbwh);
        }

        public zzf zzd(zza zza) {
            this.zzbvt.add(zza);
            return this;
        }

        public zzf zze(zza zza) {
            this.zzbvu.add(zza);
            return this;
        }

        public zzf zzf(zza zza) {
            this.zzbvv.add(zza);
            return this;
        }

        public zzf zzg(zza zza) {
            this.zzbvw.add(zza);
            return this;
        }

        public zzf zzh(zza zza) {
            this.zzbwc.add(zza);
            return this;
        }

        public zzf zzhA(String str) {
            this.zzbwe.add(str);
            return this;
        }

        public zzf zzhB(String str) {
            this.zzbwf.add(str);
            return this;
        }

        public zzf zzhy(String str) {
            this.zzbwg.add(str);
            return this;
        }

        public zzf zzhz(String str) {
            this.zzbwh.add(str);
            return this;
        }

        public zzf zzi(zza zza) {
            this.zzbwd.add(zza);
            return this;
        }
    }

    public static class zzg extends Exception {
        public zzg(String str) {
            super(str);
        }
    }

    private static zza zza(com.google.android.gms.internal.zzaf.zzb zzb, com.google.android.gms.internal.zzaf.zzf zzf, com.google.android.gms.internal.zzag.zza[] zzaArr, int i) throws zzg {
        zzb zzMz = zza.zzMz();
        for (int valueOf : zzb.zziF) {
            com.google.android.gms.internal.zzaf.zze zze = (com.google.android.gms.internal.zzaf.zze) zza(zzf.zziV, Integer.valueOf(valueOf).intValue(), "properties");
            String str = (String) zza(zzf.zziT, zze.key, "keys");
            com.google.android.gms.internal.zzag.zza zza = (com.google.android.gms.internal.zzag.zza) zza(zzaArr, zze.value, "values");
            if (zzae.PUSH_AFTER_EVALUATE.toString().equals(str)) {
                zzMz.zzq(zza);
            } else {
                zzMz.zzb(str, zza);
            }
        }
        return zzMz.zzMA();
    }

    private static zze zza(com.google.android.gms.internal.zzaf.zzg zzg, List<zza> list, List<zza> list2, List<zza> list3, com.google.android.gms.internal.zzaf.zzf zzf) {
        zzf zzME = zze.zzME();
        for (int valueOf : zzg.zzjj) {
            zzME.zzd((zza) list3.get(Integer.valueOf(valueOf).intValue()));
        }
        for (int valueOf2 : zzg.zzjk) {
            zzME.zze((zza) list3.get(Integer.valueOf(valueOf2).intValue()));
        }
        for (int valueOf22 : zzg.zzjl) {
            zzME.zzf((zza) list.get(Integer.valueOf(valueOf22).intValue()));
        }
        for (int valueOf3 : zzg.zzjn) {
            zzME.zzhy(zzf.zziU[Integer.valueOf(valueOf3).intValue()].zzjJ);
        }
        for (int valueOf222 : zzg.zzjm) {
            zzME.zzg((zza) list.get(Integer.valueOf(valueOf222).intValue()));
        }
        for (int valueOf32 : zzg.zzjo) {
            zzME.zzhz(zzf.zziU[Integer.valueOf(valueOf32).intValue()].zzjJ);
        }
        for (int valueOf2222 : zzg.zzjp) {
            zzME.zzh((zza) list2.get(Integer.valueOf(valueOf2222).intValue()));
        }
        for (int valueOf322 : zzg.zzjr) {
            zzME.zzhA(zzf.zziU[Integer.valueOf(valueOf322).intValue()].zzjJ);
        }
        for (int valueOf22222 : zzg.zzjq) {
            zzME.zzi((zza) list2.get(Integer.valueOf(valueOf22222).intValue()));
        }
        for (int valueOf4 : zzg.zzjs) {
            zzME.zzhB(zzf.zziU[Integer.valueOf(valueOf4).intValue()].zzjJ);
        }
        return zzME.zzML();
    }

    private static com.google.android.gms.internal.zzag.zza zza(int i, com.google.android.gms.internal.zzaf.zzf zzf, com.google.android.gms.internal.zzag.zza[] zzaArr, Set<Integer> set) throws zzg {
        String valueOf;
        int i2 = 0;
        if (set.contains(Integer.valueOf(i))) {
            valueOf = String.valueOf(set);
            zzhi(new StringBuilder(String.valueOf(valueOf).length() + 90).append("Value cycle detected.  Current value reference: ").append(i).append(".  Previous value references: ").append(valueOf).append(".").toString());
        }
        com.google.android.gms.internal.zzag.zza zza = (com.google.android.gms.internal.zzag.zza) zza(zzf.zziU, i, "values");
        if (zzaArr[i] != null) {
            return zzaArr[i];
        }
        com.google.android.gms.internal.zzag.zza zza2 = null;
        set.add(Integer.valueOf(i));
        zzh zzp;
        int[] iArr;
        int length;
        int i3;
        int i4;
        switch (zza.type) {
            case 1:
            case 5:
            case 6:
            case 8:
                zza2 = zza;
                break;
            case 2:
                zzp = zzp(zza);
                zza2 = zzo(zza);
                zza2.zzjK = new com.google.android.gms.internal.zzag.zza[zzp.zzjv.length];
                iArr = zzp.zzjv;
                length = iArr.length;
                i3 = 0;
                while (i2 < length) {
                    i4 = i3 + 1;
                    zza2.zzjK[i3] = zza(iArr[i2], zzf, zzaArr, (Set) set);
                    i2++;
                    i3 = i4;
                }
                break;
            case 3:
                zza2 = zzo(zza);
                zzh zzp2 = zzp(zza);
                if (zzp2.zzjw.length != zzp2.zzjx.length) {
                    i3 = zzp2.zzjw.length;
                    zzhi("Uneven map keys (" + i3 + ") and map values (" + zzp2.zzjx.length + ")");
                }
                zza2.zzjL = new com.google.android.gms.internal.zzag.zza[zzp2.zzjw.length];
                zza2.zzjM = new com.google.android.gms.internal.zzag.zza[zzp2.zzjw.length];
                int[] iArr2 = zzp2.zzjw;
                int length2 = iArr2.length;
                i3 = 0;
                i4 = 0;
                while (i3 < length2) {
                    int i5 = i4 + 1;
                    zza2.zzjL[i4] = zza(iArr2[i3], zzf, zzaArr, (Set) set);
                    i3++;
                    i4 = i5;
                }
                iArr = zzp2.zzjx;
                length = iArr.length;
                i3 = 0;
                while (i2 < length) {
                    i4 = i3 + 1;
                    zza2.zzjM[i3] = zza(iArr[i2], zzf, zzaArr, (Set) set);
                    i2++;
                    i3 = i4;
                }
                break;
            case 4:
                zza2 = zzo(zza);
                zza2.zzjN = zzdl.zzg(zza(zzp(zza).zzjA, zzf, zzaArr, (Set) set));
                break;
            case 7:
                zza2 = zzo(zza);
                zzp = zzp(zza);
                zza2.zzjR = new com.google.android.gms.internal.zzag.zza[zzp.zzjz.length];
                iArr = zzp.zzjz;
                length = iArr.length;
                i3 = 0;
                while (i2 < length) {
                    i4 = i3 + 1;
                    zza2.zzjR[i3] = zza(iArr[i2], zzf, zzaArr, (Set) set);
                    i2++;
                    i3 = i4;
                }
                break;
        }
        if (zza2 == null) {
            valueOf = String.valueOf(zza);
            zzhi(new StringBuilder(String.valueOf(valueOf).length() + 15).append("Invalid value: ").append(valueOf).toString());
        }
        zzaArr[i] = zza2;
        set.remove(Integer.valueOf(i));
        return zza2;
    }

    private static <T> T zza(T[] tArr, int i, String str) throws zzg {
        if (i < 0 || i >= tArr.length) {
            zzhi(new StringBuilder(String.valueOf(str).length() + 45).append("Index out of bounds detected: ").append(i).append(" in ").append(str).toString());
        }
        return tArr[i];
    }

    public static zzc zzb(com.google.android.gms.internal.zzaf.zzf zzf) throws zzg {
        int i;
        int i2 = 0;
        com.google.android.gms.internal.zzag.zza[] zzaArr = new com.google.android.gms.internal.zzag.zza[zzf.zziU.length];
        for (i = 0; i < zzf.zziU.length; i++) {
            zza(i, zzf, zzaArr, new HashSet(0));
        }
        zzd zzMB = zzc.zzMB();
        ArrayList arrayList = new ArrayList();
        for (i = 0; i < zzf.zziX.length; i++) {
            arrayList.add(zza(zzf.zziX[i], zzf, zzaArr, i));
        }
        ArrayList arrayList2 = new ArrayList();
        for (i = 0; i < zzf.zziY.length; i++) {
            arrayList2.add(zza(zzf.zziY[i], zzf, zzaArr, i));
        }
        ArrayList arrayList3 = new ArrayList();
        for (i = 0; i < zzf.zziW.length; i++) {
            zza zza = zza(zzf.zziW[i], zzf, zzaArr, i);
            zzMB.zzc(zza);
            arrayList3.add(zza);
        }
        com.google.android.gms.internal.zzaf.zzg[] zzgArr = zzf.zziZ;
        int length = zzgArr.length;
        while (i2 < length) {
            zzMB.zzb(zza(zzgArr[i2], arrayList, arrayList3, arrayList2, zzf));
            i2++;
        }
        zzMB.zzhx(zzf.version);
        zzMB.zzlc(zzf.zzjh);
        return zzMB.zzMD();
    }

    public static void zzc(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    private static void zzhi(String str) throws zzg {
        zzbn.m7501e(str);
        throw new zzg(str);
    }

    public static com.google.android.gms.internal.zzag.zza zzo(com.google.android.gms.internal.zzag.zza zza) {
        com.google.android.gms.internal.zzag.zza zza2 = new com.google.android.gms.internal.zzag.zza();
        zza2.type = zza.type;
        zza2.zzjS = (int[]) zza.zzjS.clone();
        if (zza.zzjT) {
            zza2.zzjT = zza.zzjT;
        }
        return zza2;
    }

    private static zzh zzp(com.google.android.gms.internal.zzag.zza zza) throws zzg {
        if (((zzh) zza.getExtension(zzh.zzjt)) == null) {
            String valueOf = String.valueOf(zza);
            zzhi(new StringBuilder(String.valueOf(valueOf).length() + 54).append("Expected a ServingValue and didn't get one. Value is: ").append(valueOf).toString());
        }
        return (zzh) zza.getExtension(zzh.zzjt);
    }
}
