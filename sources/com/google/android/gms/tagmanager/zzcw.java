package com.google.android.gms.tagmanager;

import android.content.Context;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.internal.zzabt;
import com.google.android.gms.internal.zzabt.zze;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzaf.zzi;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzcw {
    private static final zzcd<com.google.android.gms.internal.zzag.zza> zzbqA = new zzcd(zzdl.zzKT(), true);
    private final DataLayer zzbnS;
    private final com.google.android.gms.internal.zzabt.zzc zzbqB;
    private final zzai zzbqC;
    private final Map<String, zzal> zzbqD;
    private final Map<String, zzal> zzbqE;
    private final Map<String, zzal> zzbqF;
    private final zzl<com.google.android.gms.internal.zzabt.zza, zzcd<com.google.android.gms.internal.zzag.zza>> zzbqG;
    private final zzl<String, zzb> zzbqH;
    private final Set<zze> zzbqI;
    private final Map<String, zzc> zzbqJ;
    private volatile String zzbqK;
    private int zzbqL;

    /* renamed from: com.google.android.gms.tagmanager.zzcw$1 */
    class C27291 implements com.google.android.gms.tagmanager.zzm.zza<com.google.android.gms.internal.zzabt.zza, zzcd<com.google.android.gms.internal.zzag.zza>> {
        C27291() {
        }

        /* renamed from: zza */
        public int sizeOf(com.google.android.gms.internal.zzabt.zza zza, zzcd<com.google.android.gms.internal.zzag.zza> zzcd) {
            return ((com.google.android.gms.internal.zzag.zza) zzcd.getObject()).getCachedSize();
        }
    }

    /* renamed from: com.google.android.gms.tagmanager.zzcw$2 */
    class C27302 implements com.google.android.gms.tagmanager.zzm.zza<String, zzb> {
        C27302() {
        }

        /* renamed from: zza */
        public int sizeOf(String str, zzb zzb) {
            return str.length() + zzb.getSize();
        }
    }

    interface zza {
        void zza(zze zze, Set<com.google.android.gms.internal.zzabt.zza> set, Set<com.google.android.gms.internal.zzabt.zza> set2, zzcr zzcr);
    }

    /* renamed from: com.google.android.gms.tagmanager.zzcw$4 */
    class C27324 implements zza {
        C27324() {
        }

        public void zza(zze zze, Set<com.google.android.gms.internal.zzabt.zza> set, Set<com.google.android.gms.internal.zzabt.zza> set2, zzcr zzcr) {
            set.addAll(zze.zzMc());
            set2.addAll(zze.zzMd());
            zzcr.zzJY().zzc(zze.zzMc(), zze.zzMI());
            zzcr.zzJZ().zzc(zze.zzMd(), zze.zzMJ());
        }
    }

    private static class zzb {
        private zzcd<com.google.android.gms.internal.zzag.zza> zzbqR;
        private com.google.android.gms.internal.zzag.zza zzbqS;

        public zzb(zzcd<com.google.android.gms.internal.zzag.zza> zzcd, com.google.android.gms.internal.zzag.zza zza) {
            this.zzbqR = zzcd;
            this.zzbqS = zza;
        }

        public int getSize() {
            return (this.zzbqS == null ? 0 : this.zzbqS.getCachedSize()) + ((com.google.android.gms.internal.zzag.zza) this.zzbqR.getObject()).getCachedSize();
        }

        public zzcd<com.google.android.gms.internal.zzag.zza> zzKt() {
            return this.zzbqR;
        }

        public com.google.android.gms.internal.zzag.zza zzKu() {
            return this.zzbqS;
        }
    }

    private static class zzc {
        private final Set<zze> zzbqI = new HashSet();
        private final Map<zze, List<com.google.android.gms.internal.zzabt.zza>> zzbqT = new HashMap();
        private final Map<zze, List<com.google.android.gms.internal.zzabt.zza>> zzbqU = new HashMap();
        private final Map<zze, List<String>> zzbqV = new HashMap();
        private final Map<zze, List<String>> zzbqW = new HashMap();
        private com.google.android.gms.internal.zzabt.zza zzbqX;

        public com.google.android.gms.internal.zzabt.zza zzKA() {
            return this.zzbqX;
        }

        public Set<zze> zzKv() {
            return this.zzbqI;
        }

        public Map<zze, List<com.google.android.gms.internal.zzabt.zza>> zzKw() {
            return this.zzbqT;
        }

        public Map<zze, List<String>> zzKx() {
            return this.zzbqV;
        }

        public Map<zze, List<String>> zzKy() {
            return this.zzbqW;
        }

        public Map<zze, List<com.google.android.gms.internal.zzabt.zza>> zzKz() {
            return this.zzbqU;
        }

        public void zza(zze zze) {
            this.zzbqI.add(zze);
        }

        public void zza(zze zze, com.google.android.gms.internal.zzabt.zza zza) {
            List list = (List) this.zzbqT.get(zze);
            if (list == null) {
                list = new ArrayList();
                this.zzbqT.put(zze, list);
            }
            list.add(zza);
        }

        public void zza(zze zze, String str) {
            List list = (List) this.zzbqV.get(zze);
            if (list == null) {
                list = new ArrayList();
                this.zzbqV.put(zze, list);
            }
            list.add(str);
        }

        public void zzb(com.google.android.gms.internal.zzabt.zza zza) {
            this.zzbqX = zza;
        }

        public void zzb(zze zze, com.google.android.gms.internal.zzabt.zza zza) {
            List list = (List) this.zzbqU.get(zze);
            if (list == null) {
                list = new ArrayList();
                this.zzbqU.put(zze, list);
            }
            list.add(zza);
        }

        public void zzb(zze zze, String str) {
            List list = (List) this.zzbqW.get(zze);
            if (list == null) {
                list = new ArrayList();
                this.zzbqW.put(zze, list);
            }
            list.add(str);
        }
    }

    public zzcw(Context context, com.google.android.gms.internal.zzabt.zzc zzc, DataLayer dataLayer, com.google.android.gms.tagmanager.zzt.zza zza, com.google.android.gms.tagmanager.zzt.zza zza2, zzai zzai) {
        if (zzc == null) {
            throw new NullPointerException("resource cannot be null");
        }
        com.google.android.gms.internal.zzabt.zza zza3;
        this.zzbqB = zzc;
        this.zzbqI = new HashSet(zzc.zzLW());
        this.zzbnS = dataLayer;
        this.zzbqC = zzai;
        this.zzbqG = new zzm().zza(1048576, new C27291());
        this.zzbqH = new zzm().zza(1048576, new C27302());
        this.zzbqD = new HashMap();
        zzb(new zzj(context));
        zzb(new zzt(zza2));
        zzb(new zzx(dataLayer));
        zzb(new zzdm(context, dataLayer));
        this.zzbqE = new HashMap();
        zzc(new zzr());
        zzc(new zzaf());
        zzc(new zzag());
        zzc(new zzan());
        zzc(new zzao());
        zzc(new zzbj());
        zzc(new zzbk());
        zzc(new zzcm());
        zzc(new zzdf());
        this.zzbqF = new HashMap();
        zza(new zzb(context));
        zza(new zzc(context));
        zza(new zze(context));
        zza(new zzf(context));
        zza(new zzg(context));
        zza(new zzh(context));
        zza(new zzi(context));
        zza(new zzn());
        zza(new zzq(this.zzbqB.getVersion()));
        zza(new zzt(zza));
        zza(new zzv(dataLayer));
        zza(new zzaa(context));
        zza(new zzab());
        zza(new zzae());
        zza(new zzaj(this));
        zza(new zzap());
        zza(new zzaq());
        zza(new zzbd(context));
        zza(new zzbf());
        zza(new zzbi());
        zza(new zzbp());
        zza(new zzbr(context));
        zza(new zzce());
        zza(new zzcg());
        zza(new zzcj());
        zza(new zzcl());
        zza(new zzcn(context));
        zza(new zzcx());
        zza(new zzcy());
        zza(new zzdh());
        zza(new zzdn());
        this.zzbqJ = new HashMap();
        for (zze zze : this.zzbqI) {
            String str;
            zzc zzi;
            if (zzai.zzJN()) {
                zza(zze.zzMF(), zze.zzMG(), "add macro");
                zza(zze.zzMK(), zze.zzMH(), "remove macro");
                zza(zze.zzMc(), zze.zzMI(), "add tag");
                zza(zze.zzMd(), zze.zzMJ(), "remove tag");
            }
            int i = 0;
            while (i < zze.zzMF().size()) {
                zza3 = (com.google.android.gms.internal.zzabt.zza) zze.zzMF().get(i);
                str = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                if (zzai.zzJN() && i < zze.zzMG().size()) {
                    str = (String) zze.zzMG().get(i);
                }
                zzi = zzi(this.zzbqJ, zza(zza3));
                zzi.zza(zze);
                zzi.zza(zze, zza3);
                zzi.zza(zze, str);
                i++;
            }
            i = 0;
            while (i < zze.zzMK().size()) {
                zza3 = (com.google.android.gms.internal.zzabt.zza) zze.zzMK().get(i);
                str = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                if (zzai.zzJN() && i < zze.zzMH().size()) {
                    str = (String) zze.zzMH().get(i);
                }
                zzi = zzi(this.zzbqJ, zza(zza3));
                zzi.zza(zze);
                zzi.zzb(zze, zza3);
                zzi.zzb(zze, str);
                i++;
            }
        }
        for (Entry entry : this.zzbqB.zzMC().entrySet()) {
            for (com.google.android.gms.internal.zzabt.zza zza32 : (List) entry.getValue()) {
                if (!zzdl.zzk((com.google.android.gms.internal.zzag.zza) zza32.zzLY().get(zzae.NOT_DEFAULT_MACRO.toString())).booleanValue()) {
                    zzi(this.zzbqJ, (String) entry.getKey()).zzb(zza32);
                }
            }
        }
    }

    private String zzKs() {
        if (this.zzbqL <= 1) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Integer.toString(this.zzbqL));
        for (int i = 2; i < this.zzbqL; i++) {
            stringBuilder.append(SafeJsonPrimitive.NULL_CHAR);
        }
        stringBuilder.append(": ");
        return stringBuilder.toString();
    }

    private zzcd<com.google.android.gms.internal.zzag.zza> zza(com.google.android.gms.internal.zzag.zza zza, Set<String> set, zzdo zzdo) {
        if (!zza.zzjT) {
            return new zzcd(zza, true);
        }
        com.google.android.gms.internal.zzag.zza zzo;
        int i;
        zzcd zza2;
        String str;
        String valueOf;
        switch (zza.type) {
            case 2:
                zzo = zzabt.zzo(zza);
                zzo.zzjK = new com.google.android.gms.internal.zzag.zza[zza.zzjK.length];
                for (i = 0; i < zza.zzjK.length; i++) {
                    zza2 = zza(zza.zzjK[i], (Set) set, zzdo.zzkQ(i));
                    if (zza2 == zzbqA) {
                        return zzbqA;
                    }
                    zzo.zzjK[i] = (com.google.android.gms.internal.zzag.zza) zza2.getObject();
                }
                return new zzcd(zzo, false);
            case 3:
                zzo = zzabt.zzo(zza);
                if (zza.zzjL.length != zza.zzjM.length) {
                    str = "Invalid serving value: ";
                    valueOf = String.valueOf(zza.toString());
                    zzbn.m7501e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    return zzbqA;
                }
                zzo.zzjL = new com.google.android.gms.internal.zzag.zza[zza.zzjL.length];
                zzo.zzjM = new com.google.android.gms.internal.zzag.zza[zza.zzjL.length];
                for (i = 0; i < zza.zzjL.length; i++) {
                    zza2 = zza(zza.zzjL[i], (Set) set, zzdo.zzkR(i));
                    zzcd zza3 = zza(zza.zzjM[i], (Set) set, zzdo.zzkS(i));
                    if (zza2 == zzbqA || zza3 == zzbqA) {
                        return zzbqA;
                    }
                    zzo.zzjL[i] = (com.google.android.gms.internal.zzag.zza) zza2.getObject();
                    zzo.zzjM[i] = (com.google.android.gms.internal.zzag.zza) zza3.getObject();
                }
                return new zzcd(zzo, false);
            case 4:
                if (set.contains(zza.zzjN)) {
                    valueOf = String.valueOf(zza.zzjN);
                    str = String.valueOf(set.toString());
                    zzbn.m7501e(new StringBuilder((String.valueOf(valueOf).length() + 79) + String.valueOf(str).length()).append("Macro cycle detected.  Current macro reference: ").append(valueOf).append(".  Previous macro references: ").append(str).append(".").toString());
                    return zzbqA;
                }
                set.add(zza.zzjN);
                zza2 = zzdp.zza(zza(zza.zzjN, (Set) set, zzdo.zzKb()), zza.zzjS);
                set.remove(zza.zzjN);
                return zza2;
            case 7:
                zzo = zzabt.zzo(zza);
                zzo.zzjR = new com.google.android.gms.internal.zzag.zza[zza.zzjR.length];
                for (i = 0; i < zza.zzjR.length; i++) {
                    zza2 = zza(zza.zzjR[i], (Set) set, zzdo.zzkT(i));
                    if (zza2 == zzbqA) {
                        return zzbqA;
                    }
                    zzo.zzjR[i] = (com.google.android.gms.internal.zzag.zza) zza2.getObject();
                }
                return new zzcd(zzo, false);
            default:
                zzbn.m7501e("Unknown type: " + zza.type);
                return zzbqA;
        }
    }

    private zzcd<com.google.android.gms.internal.zzag.zza> zza(String str, Set<String> set, zzbq zzbq) {
        this.zzbqL++;
        zzb zzb = (zzb) this.zzbqH.get(str);
        if (zzb == null || this.zzbqC.zzJN()) {
            zzc zzc = (zzc) this.zzbqJ.get(str);
            String valueOf;
            if (zzc == null) {
                valueOf = String.valueOf(zzKs());
                zzbn.m7501e(new StringBuilder((String.valueOf(valueOf).length() + 15) + String.valueOf(str).length()).append(valueOf).append("Invalid macro: ").append(str).toString());
                this.zzbqL--;
                return zzbqA;
            }
            com.google.android.gms.internal.zzabt.zza zzKA;
            zzcd zza = zza(str, zzc.zzKv(), zzc.zzKw(), zzc.zzKx(), zzc.zzKz(), zzc.zzKy(), set, zzbq.zzJz());
            if (((Set) zza.getObject()).isEmpty()) {
                zzKA = zzc.zzKA();
            } else {
                if (((Set) zza.getObject()).size() > 1) {
                    valueOf = String.valueOf(zzKs());
                    zzbn.zzaW(new StringBuilder((String.valueOf(valueOf).length() + 37) + String.valueOf(str).length()).append(valueOf).append("Multiple macros active for macroName ").append(str).toString());
                }
                zzKA = (com.google.android.gms.internal.zzabt.zza) ((Set) zza.getObject()).iterator().next();
            }
            if (zzKA == null) {
                this.zzbqL--;
                return zzbqA;
            }
            zzcd zza2 = zza(this.zzbqF, zzKA, (Set) set, zzbq.zzJT());
            boolean z = zza.zzKc() && zza2.zzKc();
            zzcd zzcd = zza2 == zzbqA ? zzbqA : new zzcd((com.google.android.gms.internal.zzag.zza) zza2.getObject(), z);
            com.google.android.gms.internal.zzag.zza zzKu = zzKA.zzKu();
            if (zzcd.zzKc()) {
                this.zzbqH.zzi(str, new zzb(zzcd, zzKu));
            }
            zza(zzKu, (Set) set);
            this.zzbqL--;
            return zzcd;
        }
        zza(zzb.zzKu(), (Set) set);
        this.zzbqL--;
        return zzb.zzKt();
    }

    private zzcd<com.google.android.gms.internal.zzag.zza> zza(Map<String, zzal> map, com.google.android.gms.internal.zzabt.zza zza, Set<String> set, zzco zzco) {
        boolean z = true;
        com.google.android.gms.internal.zzag.zza zza2 = (com.google.android.gms.internal.zzag.zza) zza.zzLY().get(zzae.FUNCTION.toString());
        if (zza2 == null) {
            zzbn.m7501e("No function id in properties");
            return zzbqA;
        }
        String str = zza2.zzjO;
        zzal zzal = (zzal) map.get(str);
        if (zzal == null) {
            zzbn.m7501e(String.valueOf(str).concat(" has no backing implementation."));
            return zzbqA;
        }
        zzcd zzcd = (zzcd) this.zzbqG.get(zza);
        if (zzcd != null && !this.zzbqC.zzJN()) {
            return zzcd;
        }
        HashMap hashMap = new HashMap();
        boolean z2 = true;
        for (Entry entry : zza.zzLY().entrySet()) {
            zzcd zza3 = zza((com.google.android.gms.internal.zzag.zza) entry.getValue(), (Set) set, zzco.zzgD((String) entry.getKey()).zze((com.google.android.gms.internal.zzag.zza) entry.getValue()));
            if (zza3 == zzbqA) {
                return zzbqA;
            }
            boolean z3;
            if (zza3.zzKc()) {
                zza.zza((String) entry.getKey(), (com.google.android.gms.internal.zzag.zza) zza3.getObject());
                z3 = z2;
            } else {
                z3 = false;
            }
            hashMap.put((String) entry.getKey(), (com.google.android.gms.internal.zzag.zza) zza3.getObject());
            z2 = z3;
        }
        if (zzal.zze(hashMap.keySet())) {
            if (!(z2 && zzal.zzJf())) {
                z = false;
            }
            zzcd = new zzcd(zzal.zzV(hashMap), z);
            if (z) {
                this.zzbqG.zzi(zza, zzcd);
            }
            zzco.zzd((com.google.android.gms.internal.zzag.zza) zzcd.getObject());
            return zzcd;
        }
        String valueOf = String.valueOf(zzal.zzJP());
        String valueOf2 = String.valueOf(hashMap.keySet());
        zzbn.m7501e(new StringBuilder(((String.valueOf(str).length() + 43) + String.valueOf(valueOf).length()) + String.valueOf(valueOf2).length()).append("Incorrect keys for function ").append(str).append(" required ").append(valueOf).append(" had ").append(valueOf2).toString());
        return zzbqA;
    }

    private zzcd<Set<com.google.android.gms.internal.zzabt.zza>> zza(Set<zze> set, Set<String> set2, zza zza, zzcv zzcv) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        boolean z = true;
        for (zze zze : set) {
            zzcr zzKa = zzcv.zzKa();
            zzcd zza2 = zza(zze, (Set) set2, zzKa);
            if (((Boolean) zza2.getObject()).booleanValue()) {
                zza.zza(zze, hashSet, hashSet2, zzKa);
            }
            boolean z2 = z && zza2.zzKc();
            z = z2;
        }
        hashSet.removeAll(hashSet2);
        zzcv.zzf(hashSet);
        return new zzcd(hashSet, z);
    }

    private static String zza(com.google.android.gms.internal.zzabt.zza zza) {
        return zzdl.zzg((com.google.android.gms.internal.zzag.zza) zza.zzLY().get(zzae.INSTANCE_NAME.toString()));
    }

    private void zza(com.google.android.gms.internal.zzag.zza zza, Set<String> set) {
        if (zza != null) {
            zzcd zza2 = zza(zza, (Set) set, new zzcb());
            if (zza2 != zzbqA) {
                Object zzl = zzdl.zzl((com.google.android.gms.internal.zzag.zza) zza2.getObject());
                if (zzl instanceof Map) {
                    this.zzbnS.push((Map) zzl);
                } else if (zzl instanceof List) {
                    for (Object zzl2 : (List) zzl2) {
                        if (zzl2 instanceof Map) {
                            this.zzbnS.push((Map) zzl2);
                        } else {
                            zzbn.zzaW("pushAfterEvaluate: value not a Map");
                        }
                    }
                } else {
                    zzbn.zzaW("pushAfterEvaluate: value not a Map or List");
                }
            }
        }
    }

    private static void zza(List<com.google.android.gms.internal.zzabt.zza> list, List<String> list2, String str) {
        if (list.size() != list2.size()) {
            zzbn.zzaV(new StringBuilder(String.valueOf(str).length() + 102).append("Invalid resource: imbalance of rule names of functions for ").append(str).append(" operation. Using default rule name instead").toString());
        }
    }

    private static void zza(Map<String, zzal> map, zzal zzal) {
        if (map.containsKey(zzal.zzJO())) {
            String str = "Duplicate function type name: ";
            String valueOf = String.valueOf(zzal.zzJO());
            throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        map.put(zzal.zzJO(), zzal);
    }

    private static zzc zzi(Map<String, zzc> map, String str) {
        zzc zzc = (zzc) map.get(str);
        if (zzc != null) {
            return zzc;
        }
        zzc = new zzc();
        map.put(str, zzc);
        return zzc;
    }

    public synchronized void zzJ(List<zzi> list) {
        for (zzi zzi : list) {
            if (zzi.name == null || !zzi.name.startsWith("gaExperiment:")) {
                String valueOf = String.valueOf(zzi);
                zzbn.m7502v(new StringBuilder(String.valueOf(valueOf).length() + 22).append("Ignored supplemental: ").append(valueOf).toString());
            } else {
                zzak.zza(this.zzbnS, zzi);
            }
        }
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized String zzKr() {
        return this.zzbqK;
    }

    /* Access modifiers changed, original: 0000 */
    public zzcd<Boolean> zza(com.google.android.gms.internal.zzabt.zza zza, Set<String> set, zzco zzco) {
        zzcd zza2 = zza(this.zzbqE, zza, (Set) set, zzco);
        Boolean zzk = zzdl.zzk((com.google.android.gms.internal.zzag.zza) zza2.getObject());
        zzco.zzd(zzdl.zzQ(zzk));
        return new zzcd(zzk, zza2.zzKc());
    }

    /* Access modifiers changed, original: 0000 */
    public zzcd<Boolean> zza(zze zze, Set<String> set, zzcr zzcr) {
        zzcd zza;
        boolean z = true;
        for (com.google.android.gms.internal.zzabt.zza zza2 : zze.zzMb()) {
            zza = zza(zza2, (Set) set, zzcr.zzJU());
            if (((Boolean) zza.getObject()).booleanValue()) {
                zzcr.zzf(zzdl.zzQ(Boolean.valueOf(false)));
                return new zzcd(Boolean.valueOf(false), zza.zzKc());
            }
            boolean z2 = z && zza.zzKc();
            z = z2;
        }
        for (com.google.android.gms.internal.zzabt.zza zza22 : zze.zzMa()) {
            zza = zza(zza22, (Set) set, zzcr.zzJV());
            if (((Boolean) zza.getObject()).booleanValue()) {
                z = z && zza.zzKc();
            } else {
                zzcr.zzf(zzdl.zzQ(Boolean.valueOf(false)));
                return new zzcd(Boolean.valueOf(false), zza.zzKc());
            }
        }
        zzcr.zzf(zzdl.zzQ(Boolean.valueOf(true)));
        return new zzcd(Boolean.valueOf(true), z);
    }

    /* Access modifiers changed, original: 0000 */
    public zzcd<Set<com.google.android.gms.internal.zzabt.zza>> zza(String str, Set<zze> set, Map<zze, List<com.google.android.gms.internal.zzabt.zza>> map, Map<zze, List<String>> map2, Map<zze, List<com.google.android.gms.internal.zzabt.zza>> map3, Map<zze, List<String>> map4, Set<String> set2, zzcv zzcv) {
        final Map<zze, List<com.google.android.gms.internal.zzabt.zza>> map5 = map;
        final Map<zze, List<String>> map6 = map2;
        final Map<zze, List<com.google.android.gms.internal.zzabt.zza>> map7 = map3;
        final Map<zze, List<String>> map8 = map4;
        return zza((Set) set, (Set) set2, new zza() {
            public void zza(zze zze, Set<com.google.android.gms.internal.zzabt.zza> set, Set<com.google.android.gms.internal.zzabt.zza> set2, zzcr zzcr) {
                List list = (List) map5.get(zze);
                List list2 = (List) map6.get(zze);
                if (list != null) {
                    set.addAll(list);
                    zzcr.zzJW().zzc(list, list2);
                }
                list = (List) map7.get(zze);
                list2 = (List) map8.get(zze);
                if (list != null) {
                    set2.addAll(list);
                    zzcr.zzJX().zzc(list, list2);
                }
            }
        }, zzcv);
    }

    /* Access modifiers changed, original: 0000 */
    public zzcd<Set<com.google.android.gms.internal.zzabt.zza>> zza(Set<zze> set, zzcv zzcv) {
        return zza((Set) set, new HashSet(), new C27324(), zzcv);
    }

    /* Access modifiers changed, original: 0000 */
    public void zza(zzal zzal) {
        zza(this.zzbqF, zzal);
    }

    /* Access modifiers changed, original: 0000 */
    public void zzb(zzal zzal) {
        zza(this.zzbqD, zzal);
    }

    /* Access modifiers changed, original: 0000 */
    public void zzc(zzal zzal) {
        zza(this.zzbqE, zzal);
    }

    public zzcd<com.google.android.gms.internal.zzag.zza> zzgH(String str) {
        this.zzbqL = 0;
        zzah zzgx = this.zzbqC.zzgx(str);
        zzcd zza = zza(str, new HashSet(), zzgx.zzJK());
        zzgx.zzJM();
        return zza;
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void zzgI(String str) {
        this.zzbqK = str;
    }

    public synchronized void zzgl(String str) {
        zzgI(str);
        zzah zzgy = this.zzbqC.zzgy(str);
        zzu zzJL = zzgy.zzJL();
        for (com.google.android.gms.internal.zzabt.zza zza : (Set) zza(this.zzbqI, zzJL.zzJz()).getObject()) {
            zza(this.zzbqD, zza, new HashSet(), zzJL.zzJy());
        }
        zzgy.zzJM();
        zzgI(null);
    }
}
