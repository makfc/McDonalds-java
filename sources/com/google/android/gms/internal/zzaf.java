package com.google.android.gms.internal;

import android.support.p000v4.media.TransportMediator;
import java.io.IOException;

public interface zzaf {

    public static final class zza extends zzamd<zza> {
        public int level;
        public int zziC;
        public int zziD;

        public zza() {
            zzA();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            return (this.level == zza.level && this.zziC == zza.zziC && this.zziD == zza.zziD) ? (this.zzcaa == null || this.zzcaa.isEmpty()) ? zza.zzcaa == null || zza.zzcaa.isEmpty() : this.zzcaa.equals(zza.zzcaa) : false;
        }

        public int hashCode() {
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.level) * 31) + this.zziC) * 31) + this.zziD) * 31;
            int hashCode2 = (this.zzcaa == null || this.zzcaa.isEmpty()) ? 0 : this.zzcaa.hashCode();
            return hashCode2 + hashCode;
        }

        public void writeTo(zzamc zzamc) throws IOException {
            if (this.level != 1) {
                zzamc.zzE(1, this.level);
            }
            if (this.zziC != 0) {
                zzamc.zzE(2, this.zziC);
            }
            if (this.zziD != 0) {
                zzamc.zzE(3, this.zziD);
            }
            super.writeTo(zzamc);
        }

        public zza zzA() {
            this.level = 1;
            this.zziC = 0;
            this.zziD = 0;
            this.zzcaa = null;
            this.zzcaj = -1;
            return this;
        }

        /* renamed from: zza */
        public zza mergeFrom(zzamb zzamb) throws IOException {
            while (true) {
                int zzWC = zzamb.zzWC();
                switch (zzWC) {
                    case 0:
                        break;
                    case 8:
                        zzWC = zzamb.zzWG();
                        switch (zzWC) {
                            case 1:
                            case 2:
                            case 3:
                                this.level = zzWC;
                                break;
                            default:
                                continue;
                        }
                    case 16:
                        this.zziC = zzamb.zzWG();
                        continue;
                    case 24:
                        this.zziD = zzamb.zzWG();
                        continue;
                    default:
                        if (!super.zza(zzamb, zzWC)) {
                            break;
                        }
                        continue;
                }
            }
            return this;
        }

        /* Access modifiers changed, original: protected */
        public int zzy() {
            int zzy = super.zzy();
            if (this.level != 1) {
                zzy += zzamc.zzG(1, this.level);
            }
            if (this.zziC != 0) {
                zzy += zzamc.zzG(2, this.zziC);
            }
            return this.zziD != 0 ? zzy + zzamc.zzG(3, this.zziD) : zzy;
        }
    }

    public static final class zzb extends zzamd<zzb> {
        private static volatile zzb[] zziE;
        public int name;
        public int[] zziF;
        public int zziG;
        public boolean zziH;
        public boolean zziI;

        public zzb() {
            zzC();
        }

        public static zzb[] zzB() {
            if (zziE == null) {
                synchronized (zzamh.zzcai) {
                    if (zziE == null) {
                        zziE = new zzb[0];
                    }
                }
            }
            return zziE;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            return (zzamh.equals(this.zziF, zzb.zziF) && this.zziG == zzb.zziG && this.name == zzb.name && this.zziH == zzb.zziH && this.zziI == zzb.zziI) ? (this.zzcaa == null || this.zzcaa.isEmpty()) ? zzb.zzcaa == null || zzb.zzcaa.isEmpty() : this.zzcaa.equals(zzb.zzcaa) : false;
        }

        public int hashCode() {
            int i = 1231;
            int hashCode = ((this.zziH ? 1231 : 1237) + ((((((((getClass().getName().hashCode() + 527) * 31) + zzamh.hashCode(this.zziF)) * 31) + this.zziG) * 31) + this.name) * 31)) * 31;
            if (!this.zziI) {
                i = 1237;
            }
            i = (hashCode + i) * 31;
            hashCode = (this.zzcaa == null || this.zzcaa.isEmpty()) ? 0 : this.zzcaa.hashCode();
            return hashCode + i;
        }

        public void writeTo(zzamc zzamc) throws IOException {
            if (this.zziI) {
                zzamc.zzj(1, this.zziI);
            }
            zzamc.zzE(2, this.zziG);
            if (this.zziF != null && this.zziF.length > 0) {
                for (int zzE : this.zziF) {
                    zzamc.zzE(3, zzE);
                }
            }
            if (this.name != 0) {
                zzamc.zzE(4, this.name);
            }
            if (this.zziH) {
                zzamc.zzj(6, this.zziH);
            }
            super.writeTo(zzamc);
        }

        public zzb zzC() {
            this.zziF = zzamm.zzcal;
            this.zziG = 0;
            this.name = 0;
            this.zziH = false;
            this.zziI = false;
            this.zzcaa = null;
            this.zzcaj = -1;
            return this;
        }

        /* renamed from: zzb */
        public zzb mergeFrom(zzamb zzamb) throws IOException {
            while (true) {
                int zzWC = zzamb.zzWC();
                int zzc;
                switch (zzWC) {
                    case 0:
                        break;
                    case 8:
                        this.zziI = zzamb.zzWI();
                        continue;
                    case 16:
                        this.zziG = zzamb.zzWG();
                        continue;
                    case 24:
                        zzc = zzamm.zzc(zzamb, 24);
                        zzWC = this.zziF == null ? 0 : this.zziF.length;
                        int[] iArr = new int[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zziF, 0, iArr, 0, zzWC);
                        }
                        while (zzWC < iArr.length - 1) {
                            iArr[zzWC] = zzamb.zzWG();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        iArr[zzWC] = zzamb.zzWG();
                        this.zziF = iArr;
                        continue;
                    case 26:
                        int zznW = zzamb.zznW(zzamb.zzWL());
                        zzc = zzamb.getPosition();
                        zzWC = 0;
                        while (zzamb.zzWQ() > 0) {
                            zzamb.zzWG();
                            zzWC++;
                        }
                        zzamb.zznY(zzc);
                        zzc = this.zziF == null ? 0 : this.zziF.length;
                        int[] iArr2 = new int[(zzWC + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zziF, 0, iArr2, 0, zzc);
                        }
                        while (zzc < iArr2.length) {
                            iArr2[zzc] = zzamb.zzWG();
                            zzc++;
                        }
                        this.zziF = iArr2;
                        zzamb.zznX(zznW);
                        continue;
                    case 32:
                        this.name = zzamb.zzWG();
                        continue;
                    case 48:
                        this.zziH = zzamb.zzWI();
                        continue;
                    default:
                        if (!super.zza(zzamb, zzWC)) {
                            break;
                        }
                        continue;
                }
            }
            return this;
        }

        /* Access modifiers changed, original: protected */
        public int zzy() {
            int i = 0;
            int zzy = super.zzy();
            if (this.zziI) {
                zzy += zzamc.zzk(1, this.zziI);
            }
            int zzG = zzamc.zzG(2, this.zziG) + zzy;
            if (this.zziF == null || this.zziF.length <= 0) {
                zzy = zzG;
            } else {
                for (int zzoc : this.zziF) {
                    i += zzamc.zzoc(zzoc);
                }
                zzy = (zzG + i) + (this.zziF.length * 1);
            }
            if (this.name != 0) {
                zzy += zzamc.zzG(4, this.name);
            }
            return this.zziH ? zzy + zzamc.zzk(6, this.zziH) : zzy;
        }
    }

    public static final class zzc extends zzamd<zzc> {
        private static volatile zzc[] zziJ;
        public String zzaB;
        public long zziK;
        public long zziL;
        public boolean zziM;
        public long zziN;

        public zzc() {
            zzE();
        }

        public static zzc[] zzD() {
            if (zziJ == null) {
                synchronized (zzamh.zzcai) {
                    if (zziJ == null) {
                        zziJ = new zzc[0];
                    }
                }
            }
            return zziJ;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc zzc = (zzc) obj;
            if (this.zzaB == null) {
                if (zzc.zzaB != null) {
                    return false;
                }
            } else if (!this.zzaB.equals(zzc.zzaB)) {
                return false;
            }
            return (this.zziK == zzc.zziK && this.zziL == zzc.zziL && this.zziM == zzc.zziM && this.zziN == zzc.zziN) ? (this.zzcaa == null || this.zzcaa.isEmpty()) ? zzc.zzcaa == null || zzc.zzcaa.isEmpty() : this.zzcaa.equals(zzc.zzcaa) : false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((this.zziM ? 1231 : 1237) + (((((((this.zzaB == null ? 0 : this.zzaB.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31) + ((int) (this.zziK ^ (this.zziK >>> 32)))) * 31) + ((int) (this.zziL ^ (this.zziL >>> 32)))) * 31)) * 31) + ((int) (this.zziN ^ (this.zziN >>> 32)))) * 31;
            if (!(this.zzcaa == null || this.zzcaa.isEmpty())) {
                i = this.zzcaa.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(zzamc zzamc) throws IOException {
            if (!this.zzaB.equals("")) {
                zzamc.zzq(1, this.zzaB);
            }
            if (this.zziK != 0) {
                zzamc.zzb(2, this.zziK);
            }
            if (this.zziL != 2147483647L) {
                zzamc.zzb(3, this.zziL);
            }
            if (this.zziM) {
                zzamc.zzj(4, this.zziM);
            }
            if (this.zziN != 0) {
                zzamc.zzb(5, this.zziN);
            }
            super.writeTo(zzamc);
        }

        public zzc zzE() {
            this.zzaB = "";
            this.zziK = 0;
            this.zziL = 2147483647L;
            this.zziM = false;
            this.zziN = 0;
            this.zzcaa = null;
            this.zzcaj = -1;
            return this;
        }

        /* renamed from: zzc */
        public zzc mergeFrom(zzamb zzamb) throws IOException {
            while (true) {
                int zzWC = zzamb.zzWC();
                switch (zzWC) {
                    case 0:
                        break;
                    case 10:
                        this.zzaB = zzamb.readString();
                        continue;
                    case 16:
                        this.zziK = zzamb.zzWF();
                        continue;
                    case 24:
                        this.zziL = zzamb.zzWF();
                        continue;
                    case 32:
                        this.zziM = zzamb.zzWI();
                        continue;
                    case 40:
                        this.zziN = zzamb.zzWF();
                        continue;
                    default:
                        if (!super.zza(zzamb, zzWC)) {
                            break;
                        }
                        continue;
                }
            }
            return this;
        }

        /* Access modifiers changed, original: protected */
        public int zzy() {
            int zzy = super.zzy();
            if (!this.zzaB.equals("")) {
                zzy += zzamc.zzr(1, this.zzaB);
            }
            if (this.zziK != 0) {
                zzy += zzamc.zze(2, this.zziK);
            }
            if (this.zziL != 2147483647L) {
                zzy += zzamc.zze(3, this.zziL);
            }
            if (this.zziM) {
                zzy += zzamc.zzk(4, this.zziM);
            }
            return this.zziN != 0 ? zzy + zzamc.zze(5, this.zziN) : zzy;
        }
    }

    public static final class zzd extends zzamd<zzd> {
        public com.google.android.gms.internal.zzag.zza[] zziO;
        public com.google.android.gms.internal.zzag.zza[] zziP;
        public zzc[] zziQ;

        public zzd() {
            zzF();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzd)) {
                return false;
            }
            zzd zzd = (zzd) obj;
            return (zzamh.equals(this.zziO, zzd.zziO) && zzamh.equals(this.zziP, zzd.zziP) && zzamh.equals(this.zziQ, zzd.zziQ)) ? (this.zzcaa == null || this.zzcaa.isEmpty()) ? zzd.zzcaa == null || zzd.zzcaa.isEmpty() : this.zzcaa.equals(zzd.zzcaa) : false;
        }

        public int hashCode() {
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + zzamh.hashCode(this.zziO)) * 31) + zzamh.hashCode(this.zziP)) * 31) + zzamh.hashCode(this.zziQ)) * 31;
            int hashCode2 = (this.zzcaa == null || this.zzcaa.isEmpty()) ? 0 : this.zzcaa.hashCode();
            return hashCode2 + hashCode;
        }

        public void writeTo(zzamc zzamc) throws IOException {
            int i = 0;
            if (this.zziO != null && this.zziO.length > 0) {
                for (zzamj zzamj : this.zziO) {
                    if (zzamj != null) {
                        zzamc.zza(1, zzamj);
                    }
                }
            }
            if (this.zziP != null && this.zziP.length > 0) {
                for (zzamj zzamj2 : this.zziP) {
                    if (zzamj2 != null) {
                        zzamc.zza(2, zzamj2);
                    }
                }
            }
            if (this.zziQ != null && this.zziQ.length > 0) {
                while (i < this.zziQ.length) {
                    zzamj zzamj3 = this.zziQ[i];
                    if (zzamj3 != null) {
                        zzamc.zza(3, zzamj3);
                    }
                    i++;
                }
            }
            super.writeTo(zzamc);
        }

        public zzd zzF() {
            this.zziO = com.google.android.gms.internal.zzag.zza.zzP();
            this.zziP = com.google.android.gms.internal.zzag.zza.zzP();
            this.zziQ = zzc.zzD();
            this.zzcaa = null;
            this.zzcaj = -1;
            return this;
        }

        /* renamed from: zzd */
        public zzd mergeFrom(zzamb zzamb) throws IOException {
            while (true) {
                int zzWC = zzamb.zzWC();
                int zzc;
                com.google.android.gms.internal.zzag.zza[] zzaArr;
                switch (zzWC) {
                    case 0:
                        break;
                    case 10:
                        zzc = zzamm.zzc(zzamb, 10);
                        zzWC = this.zziO == null ? 0 : this.zziO.length;
                        zzaArr = new com.google.android.gms.internal.zzag.zza[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zziO, 0, zzaArr, 0, zzWC);
                        }
                        while (zzWC < zzaArr.length - 1) {
                            zzaArr[zzWC] = new com.google.android.gms.internal.zzag.zza();
                            zzamb.zza(zzaArr[zzWC]);
                            zzamb.zzWC();
                            zzWC++;
                        }
                        zzaArr[zzWC] = new com.google.android.gms.internal.zzag.zza();
                        zzamb.zza(zzaArr[zzWC]);
                        this.zziO = zzaArr;
                        continue;
                    case 18:
                        zzc = zzamm.zzc(zzamb, 18);
                        zzWC = this.zziP == null ? 0 : this.zziP.length;
                        zzaArr = new com.google.android.gms.internal.zzag.zza[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zziP, 0, zzaArr, 0, zzWC);
                        }
                        while (zzWC < zzaArr.length - 1) {
                            zzaArr[zzWC] = new com.google.android.gms.internal.zzag.zza();
                            zzamb.zza(zzaArr[zzWC]);
                            zzamb.zzWC();
                            zzWC++;
                        }
                        zzaArr[zzWC] = new com.google.android.gms.internal.zzag.zza();
                        zzamb.zza(zzaArr[zzWC]);
                        this.zziP = zzaArr;
                        continue;
                    case 26:
                        zzc = zzamm.zzc(zzamb, 26);
                        zzWC = this.zziQ == null ? 0 : this.zziQ.length;
                        zzc[] zzcArr = new zzc[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zziQ, 0, zzcArr, 0, zzWC);
                        }
                        while (zzWC < zzcArr.length - 1) {
                            zzcArr[zzWC] = new zzc();
                            zzamb.zza(zzcArr[zzWC]);
                            zzamb.zzWC();
                            zzWC++;
                        }
                        zzcArr[zzWC] = new zzc();
                        zzamb.zza(zzcArr[zzWC]);
                        this.zziQ = zzcArr;
                        continue;
                    default:
                        if (!super.zza(zzamb, zzWC)) {
                            break;
                        }
                        continue;
                }
            }
            return this;
        }

        /* Access modifiers changed, original: protected */
        public int zzy() {
            int i;
            int i2 = 0;
            int zzy = super.zzy();
            if (this.zziO != null && this.zziO.length > 0) {
                i = zzy;
                for (zzamj zzamj : this.zziO) {
                    if (zzamj != null) {
                        i += zzamc.zzc(1, zzamj);
                    }
                }
                zzy = i;
            }
            if (this.zziP != null && this.zziP.length > 0) {
                i = zzy;
                for (zzamj zzamj2 : this.zziP) {
                    if (zzamj2 != null) {
                        i += zzamc.zzc(2, zzamj2);
                    }
                }
                zzy = i;
            }
            if (this.zziQ != null && this.zziQ.length > 0) {
                while (i2 < this.zziQ.length) {
                    zzamj zzamj3 = this.zziQ[i2];
                    if (zzamj3 != null) {
                        zzy += zzamc.zzc(3, zzamj3);
                    }
                    i2++;
                }
            }
            return zzy;
        }
    }

    public static final class zze extends zzamd<zze> {
        private static volatile zze[] zziR;
        public int key;
        public int value;

        public zze() {
            zzH();
        }

        public static zze[] zzG() {
            if (zziR == null) {
                synchronized (zzamh.zzcai) {
                    if (zziR == null) {
                        zziR = new zze[0];
                    }
                }
            }
            return zziR;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zze)) {
                return false;
            }
            zze zze = (zze) obj;
            return (this.key == zze.key && this.value == zze.value) ? (this.zzcaa == null || this.zzcaa.isEmpty()) ? zze.zzcaa == null || zze.zzcaa.isEmpty() : this.zzcaa.equals(zze.zzcaa) : false;
        }

        public int hashCode() {
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.key) * 31) + this.value) * 31;
            int hashCode2 = (this.zzcaa == null || this.zzcaa.isEmpty()) ? 0 : this.zzcaa.hashCode();
            return hashCode2 + hashCode;
        }

        public void writeTo(zzamc zzamc) throws IOException {
            zzamc.zzE(1, this.key);
            zzamc.zzE(2, this.value);
            super.writeTo(zzamc);
        }

        public zze zzH() {
            this.key = 0;
            this.value = 0;
            this.zzcaa = null;
            this.zzcaj = -1;
            return this;
        }

        /* renamed from: zze */
        public zze mergeFrom(zzamb zzamb) throws IOException {
            while (true) {
                int zzWC = zzamb.zzWC();
                switch (zzWC) {
                    case 0:
                        break;
                    case 8:
                        this.key = zzamb.zzWG();
                        continue;
                    case 16:
                        this.value = zzamb.zzWG();
                        continue;
                    default:
                        if (!super.zza(zzamb, zzWC)) {
                            break;
                        }
                        continue;
                }
            }
            return this;
        }

        /* Access modifiers changed, original: protected */
        public int zzy() {
            return (super.zzy() + zzamc.zzG(1, this.key)) + zzamc.zzG(2, this.value);
        }
    }

    public static final class zzf extends zzamd<zzf> {
        public String version;
        public String[] zziS;
        public String[] zziT;
        public com.google.android.gms.internal.zzag.zza[] zziU;
        public zze[] zziV;
        public zzb[] zziW;
        public zzb[] zziX;
        public zzb[] zziY;
        public zzg[] zziZ;
        public String zzja;
        public String zzjb;
        public String zzjc;
        public zza zzjd;
        public float zzje;
        public boolean zzjf;
        public String[] zzjg;
        public int zzjh;

        public zzf() {
            zzI();
        }

        public static zzf zzc(byte[] bArr) throws zzami {
            return (zzf) zzamj.mergeFrom(new zzf(), bArr);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzf)) {
                return false;
            }
            zzf zzf = (zzf) obj;
            if (!zzamh.equals(this.zziS, zzf.zziS) || !zzamh.equals(this.zziT, zzf.zziT) || !zzamh.equals(this.zziU, zzf.zziU) || !zzamh.equals(this.zziV, zzf.zziV) || !zzamh.equals(this.zziW, zzf.zziW) || !zzamh.equals(this.zziX, zzf.zziX) || !zzamh.equals(this.zziY, zzf.zziY) || !zzamh.equals(this.zziZ, zzf.zziZ)) {
                return false;
            }
            if (this.zzja == null) {
                if (zzf.zzja != null) {
                    return false;
                }
            } else if (!this.zzja.equals(zzf.zzja)) {
                return false;
            }
            if (this.zzjb == null) {
                if (zzf.zzjb != null) {
                    return false;
                }
            } else if (!this.zzjb.equals(zzf.zzjb)) {
                return false;
            }
            if (this.zzjc == null) {
                if (zzf.zzjc != null) {
                    return false;
                }
            } else if (!this.zzjc.equals(zzf.zzjc)) {
                return false;
            }
            if (this.version == null) {
                if (zzf.version != null) {
                    return false;
                }
            } else if (!this.version.equals(zzf.version)) {
                return false;
            }
            if (this.zzjd == null) {
                if (zzf.zzjd != null) {
                    return false;
                }
            } else if (!this.zzjd.equals(zzf.zzjd)) {
                return false;
            }
            return (Float.floatToIntBits(this.zzje) == Float.floatToIntBits(zzf.zzje) && this.zzjf == zzf.zzjf && zzamh.equals(this.zzjg, zzf.zzjg) && this.zzjh == zzf.zzjh) ? (this.zzcaa == null || this.zzcaa.isEmpty()) ? zzf.zzcaa == null || zzf.zzcaa.isEmpty() : this.zzcaa.equals(zzf.zzcaa) : false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((((this.zzjf ? 1231 : 1237) + (((((this.zzjd == null ? 0 : this.zzjd.hashCode()) + (((this.version == null ? 0 : this.version.hashCode()) + (((this.zzjc == null ? 0 : this.zzjc.hashCode()) + (((this.zzjb == null ? 0 : this.zzjb.hashCode()) + (((this.zzja == null ? 0 : this.zzja.hashCode()) + ((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzamh.hashCode(this.zziS)) * 31) + zzamh.hashCode(this.zziT)) * 31) + zzamh.hashCode(this.zziU)) * 31) + zzamh.hashCode(this.zziV)) * 31) + zzamh.hashCode(this.zziW)) * 31) + zzamh.hashCode(this.zziX)) * 31) + zzamh.hashCode(this.zziY)) * 31) + zzamh.hashCode(this.zziZ)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + Float.floatToIntBits(this.zzje)) * 31)) * 31) + zzamh.hashCode(this.zzjg)) * 31) + this.zzjh) * 31;
            if (!(this.zzcaa == null || this.zzcaa.isEmpty())) {
                i = this.zzcaa.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(zzamc zzamc) throws IOException {
            int i = 0;
            if (this.zziT != null && this.zziT.length > 0) {
                for (String str : this.zziT) {
                    if (str != null) {
                        zzamc.zzq(1, str);
                    }
                }
            }
            if (this.zziU != null && this.zziU.length > 0) {
                for (zzamj zzamj : this.zziU) {
                    if (zzamj != null) {
                        zzamc.zza(2, zzamj);
                    }
                }
            }
            if (this.zziV != null && this.zziV.length > 0) {
                for (zzamj zzamj2 : this.zziV) {
                    if (zzamj2 != null) {
                        zzamc.zza(3, zzamj2);
                    }
                }
            }
            if (this.zziW != null && this.zziW.length > 0) {
                for (zzamj zzamj22 : this.zziW) {
                    if (zzamj22 != null) {
                        zzamc.zza(4, zzamj22);
                    }
                }
            }
            if (this.zziX != null && this.zziX.length > 0) {
                for (zzamj zzamj222 : this.zziX) {
                    if (zzamj222 != null) {
                        zzamc.zza(5, zzamj222);
                    }
                }
            }
            if (this.zziY != null && this.zziY.length > 0) {
                for (zzamj zzamj2222 : this.zziY) {
                    if (zzamj2222 != null) {
                        zzamc.zza(6, zzamj2222);
                    }
                }
            }
            if (this.zziZ != null && this.zziZ.length > 0) {
                for (zzamj zzamj22222 : this.zziZ) {
                    if (zzamj22222 != null) {
                        zzamc.zza(7, zzamj22222);
                    }
                }
            }
            if (!this.zzja.equals("")) {
                zzamc.zzq(9, this.zzja);
            }
            if (!this.zzjb.equals("")) {
                zzamc.zzq(10, this.zzjb);
            }
            if (!this.zzjc.equals("0")) {
                zzamc.zzq(12, this.zzjc);
            }
            if (!this.version.equals("")) {
                zzamc.zzq(13, this.version);
            }
            if (this.zzjd != null) {
                zzamc.zza(14, this.zzjd);
            }
            if (Float.floatToIntBits(this.zzje) != Float.floatToIntBits(0.0f)) {
                zzamc.zzb(15, this.zzje);
            }
            if (this.zzjg != null && this.zzjg.length > 0) {
                for (String str2 : this.zzjg) {
                    if (str2 != null) {
                        zzamc.zzq(16, str2);
                    }
                }
            }
            if (this.zzjh != 0) {
                zzamc.zzE(17, this.zzjh);
            }
            if (this.zzjf) {
                zzamc.zzj(18, this.zzjf);
            }
            if (this.zziS != null && this.zziS.length > 0) {
                while (i < this.zziS.length) {
                    String str3 = this.zziS[i];
                    if (str3 != null) {
                        zzamc.zzq(19, str3);
                    }
                    i++;
                }
            }
            super.writeTo(zzamc);
        }

        public zzf zzI() {
            this.zziS = zzamm.zzcaq;
            this.zziT = zzamm.zzcaq;
            this.zziU = com.google.android.gms.internal.zzag.zza.zzP();
            this.zziV = zze.zzG();
            this.zziW = zzb.zzB();
            this.zziX = zzb.zzB();
            this.zziY = zzb.zzB();
            this.zziZ = zzg.zzJ();
            this.zzja = "";
            this.zzjb = "";
            this.zzjc = "0";
            this.version = "";
            this.zzjd = null;
            this.zzje = 0.0f;
            this.zzjf = false;
            this.zzjg = zzamm.zzcaq;
            this.zzjh = 0;
            this.zzcaa = null;
            this.zzcaj = -1;
            return this;
        }

        /* renamed from: zzf */
        public zzf mergeFrom(zzamb zzamb) throws IOException {
            while (true) {
                int zzWC = zzamb.zzWC();
                int zzc;
                String[] strArr;
                zzb[] zzbArr;
                switch (zzWC) {
                    case 0:
                        break;
                    case 10:
                        zzc = zzamm.zzc(zzamb, 10);
                        zzWC = this.zziT == null ? 0 : this.zziT.length;
                        strArr = new String[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zziT, 0, strArr, 0, zzWC);
                        }
                        while (zzWC < strArr.length - 1) {
                            strArr[zzWC] = zzamb.readString();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        strArr[zzWC] = zzamb.readString();
                        this.zziT = strArr;
                        continue;
                    case 18:
                        zzc = zzamm.zzc(zzamb, 18);
                        zzWC = this.zziU == null ? 0 : this.zziU.length;
                        com.google.android.gms.internal.zzag.zza[] zzaArr = new com.google.android.gms.internal.zzag.zza[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zziU, 0, zzaArr, 0, zzWC);
                        }
                        while (zzWC < zzaArr.length - 1) {
                            zzaArr[zzWC] = new com.google.android.gms.internal.zzag.zza();
                            zzamb.zza(zzaArr[zzWC]);
                            zzamb.zzWC();
                            zzWC++;
                        }
                        zzaArr[zzWC] = new com.google.android.gms.internal.zzag.zza();
                        zzamb.zza(zzaArr[zzWC]);
                        this.zziU = zzaArr;
                        continue;
                    case 26:
                        zzc = zzamm.zzc(zzamb, 26);
                        zzWC = this.zziV == null ? 0 : this.zziV.length;
                        zze[] zzeArr = new zze[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zziV, 0, zzeArr, 0, zzWC);
                        }
                        while (zzWC < zzeArr.length - 1) {
                            zzeArr[zzWC] = new zze();
                            zzamb.zza(zzeArr[zzWC]);
                            zzamb.zzWC();
                            zzWC++;
                        }
                        zzeArr[zzWC] = new zze();
                        zzamb.zza(zzeArr[zzWC]);
                        this.zziV = zzeArr;
                        continue;
                    case 34:
                        zzc = zzamm.zzc(zzamb, 34);
                        zzWC = this.zziW == null ? 0 : this.zziW.length;
                        zzbArr = new zzb[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zziW, 0, zzbArr, 0, zzWC);
                        }
                        while (zzWC < zzbArr.length - 1) {
                            zzbArr[zzWC] = new zzb();
                            zzamb.zza(zzbArr[zzWC]);
                            zzamb.zzWC();
                            zzWC++;
                        }
                        zzbArr[zzWC] = new zzb();
                        zzamb.zza(zzbArr[zzWC]);
                        this.zziW = zzbArr;
                        continue;
                    case 42:
                        zzc = zzamm.zzc(zzamb, 42);
                        zzWC = this.zziX == null ? 0 : this.zziX.length;
                        zzbArr = new zzb[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zziX, 0, zzbArr, 0, zzWC);
                        }
                        while (zzWC < zzbArr.length - 1) {
                            zzbArr[zzWC] = new zzb();
                            zzamb.zza(zzbArr[zzWC]);
                            zzamb.zzWC();
                            zzWC++;
                        }
                        zzbArr[zzWC] = new zzb();
                        zzamb.zza(zzbArr[zzWC]);
                        this.zziX = zzbArr;
                        continue;
                    case 50:
                        zzc = zzamm.zzc(zzamb, 50);
                        zzWC = this.zziY == null ? 0 : this.zziY.length;
                        zzbArr = new zzb[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zziY, 0, zzbArr, 0, zzWC);
                        }
                        while (zzWC < zzbArr.length - 1) {
                            zzbArr[zzWC] = new zzb();
                            zzamb.zza(zzbArr[zzWC]);
                            zzamb.zzWC();
                            zzWC++;
                        }
                        zzbArr[zzWC] = new zzb();
                        zzamb.zza(zzbArr[zzWC]);
                        this.zziY = zzbArr;
                        continue;
                    case 58:
                        zzc = zzamm.zzc(zzamb, 58);
                        zzWC = this.zziZ == null ? 0 : this.zziZ.length;
                        zzg[] zzgArr = new zzg[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zziZ, 0, zzgArr, 0, zzWC);
                        }
                        while (zzWC < zzgArr.length - 1) {
                            zzgArr[zzWC] = new zzg();
                            zzamb.zza(zzgArr[zzWC]);
                            zzamb.zzWC();
                            zzWC++;
                        }
                        zzgArr[zzWC] = new zzg();
                        zzamb.zza(zzgArr[zzWC]);
                        this.zziZ = zzgArr;
                        continue;
                    case 74:
                        this.zzja = zzamb.readString();
                        continue;
                    case 82:
                        this.zzjb = zzamb.readString();
                        continue;
                    case 98:
                        this.zzjc = zzamb.readString();
                        continue;
                    case 106:
                        this.version = zzamb.readString();
                        continue;
                    case 114:
                        if (this.zzjd == null) {
                            this.zzjd = new zza();
                        }
                        zzamb.zza(this.zzjd);
                        continue;
                    case 125:
                        this.zzje = zzamb.readFloat();
                        continue;
                    case TransportMediator.KEYCODE_MEDIA_RECORD /*130*/:
                        zzc = zzamm.zzc(zzamb, TransportMediator.KEYCODE_MEDIA_RECORD);
                        zzWC = this.zzjg == null ? 0 : this.zzjg.length;
                        strArr = new String[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjg, 0, strArr, 0, zzWC);
                        }
                        while (zzWC < strArr.length - 1) {
                            strArr[zzWC] = zzamb.readString();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        strArr[zzWC] = zzamb.readString();
                        this.zzjg = strArr;
                        continue;
                    case 136:
                        this.zzjh = zzamb.zzWG();
                        continue;
                    case 144:
                        this.zzjf = zzamb.zzWI();
                        continue;
                    case 154:
                        zzc = zzamm.zzc(zzamb, 154);
                        zzWC = this.zziS == null ? 0 : this.zziS.length;
                        strArr = new String[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zziS, 0, strArr, 0, zzWC);
                        }
                        while (zzWC < strArr.length - 1) {
                            strArr[zzWC] = zzamb.readString();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        strArr[zzWC] = zzamb.readString();
                        this.zziS = strArr;
                        continue;
                    default:
                        if (!super.zza(zzamb, zzWC)) {
                            break;
                        }
                        continue;
                }
            }
            return this;
        }

        /* Access modifiers changed, original: protected */
        public int zzy() {
            int i;
            int i2;
            int i3;
            int i4 = 0;
            int zzy = super.zzy();
            if (this.zziT == null || this.zziT.length <= 0) {
                i = zzy;
            } else {
                i2 = 0;
                i3 = 0;
                for (String str : this.zziT) {
                    if (str != null) {
                        i3++;
                        i2 += zzamc.zziZ(str);
                    }
                }
                i = (zzy + i2) + (i3 * 1);
            }
            if (this.zziU != null && this.zziU.length > 0) {
                i2 = i;
                for (zzamj zzamj : this.zziU) {
                    if (zzamj != null) {
                        i2 += zzamc.zzc(2, zzamj);
                    }
                }
                i = i2;
            }
            if (this.zziV != null && this.zziV.length > 0) {
                i2 = i;
                for (zzamj zzamj2 : this.zziV) {
                    if (zzamj2 != null) {
                        i2 += zzamc.zzc(3, zzamj2);
                    }
                }
                i = i2;
            }
            if (this.zziW != null && this.zziW.length > 0) {
                i2 = i;
                for (zzamj zzamj22 : this.zziW) {
                    if (zzamj22 != null) {
                        i2 += zzamc.zzc(4, zzamj22);
                    }
                }
                i = i2;
            }
            if (this.zziX != null && this.zziX.length > 0) {
                i2 = i;
                for (zzamj zzamj222 : this.zziX) {
                    if (zzamj222 != null) {
                        i2 += zzamc.zzc(5, zzamj222);
                    }
                }
                i = i2;
            }
            if (this.zziY != null && this.zziY.length > 0) {
                i2 = i;
                for (zzamj zzamj2222 : this.zziY) {
                    if (zzamj2222 != null) {
                        i2 += zzamc.zzc(6, zzamj2222);
                    }
                }
                i = i2;
            }
            if (this.zziZ != null && this.zziZ.length > 0) {
                i2 = i;
                for (zzamj zzamj22222 : this.zziZ) {
                    if (zzamj22222 != null) {
                        i2 += zzamc.zzc(7, zzamj22222);
                    }
                }
                i = i2;
            }
            if (!this.zzja.equals("")) {
                i += zzamc.zzr(9, this.zzja);
            }
            if (!this.zzjb.equals("")) {
                i += zzamc.zzr(10, this.zzjb);
            }
            if (!this.zzjc.equals("0")) {
                i += zzamc.zzr(12, this.zzjc);
            }
            if (!this.version.equals("")) {
                i += zzamc.zzr(13, this.version);
            }
            if (this.zzjd != null) {
                i += zzamc.zzc(14, this.zzjd);
            }
            if (Float.floatToIntBits(this.zzje) != Float.floatToIntBits(0.0f)) {
                i += zzamc.zzc(15, this.zzje);
            }
            if (this.zzjg != null && this.zzjg.length > 0) {
                i3 = 0;
                zzy = 0;
                for (String str2 : this.zzjg) {
                    if (str2 != null) {
                        zzy++;
                        i3 += zzamc.zziZ(str2);
                    }
                }
                i = (i + i3) + (zzy * 2);
            }
            if (this.zzjh != 0) {
                i += zzamc.zzG(17, this.zzjh);
            }
            if (this.zzjf) {
                i += zzamc.zzk(18, this.zzjf);
            }
            if (this.zziS == null || this.zziS.length <= 0) {
                return i;
            }
            i2 = 0;
            i3 = 0;
            while (i4 < this.zziS.length) {
                String str3 = this.zziS[i4];
                if (str3 != null) {
                    i3++;
                    i2 += zzamc.zziZ(str3);
                }
                i4++;
            }
            return (i + i2) + (i3 * 2);
        }
    }

    public static final class zzg extends zzamd<zzg> {
        private static volatile zzg[] zzji;
        public int[] zzjj;
        public int[] zzjk;
        public int[] zzjl;
        public int[] zzjm;
        public int[] zzjn;
        public int[] zzjo;
        public int[] zzjp;
        public int[] zzjq;
        public int[] zzjr;
        public int[] zzjs;

        public zzg() {
            zzK();
        }

        public static zzg[] zzJ() {
            if (zzji == null) {
                synchronized (zzamh.zzcai) {
                    if (zzji == null) {
                        zzji = new zzg[0];
                    }
                }
            }
            return zzji;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzg)) {
                return false;
            }
            zzg zzg = (zzg) obj;
            return (zzamh.equals(this.zzjj, zzg.zzjj) && zzamh.equals(this.zzjk, zzg.zzjk) && zzamh.equals(this.zzjl, zzg.zzjl) && zzamh.equals(this.zzjm, zzg.zzjm) && zzamh.equals(this.zzjn, zzg.zzjn) && zzamh.equals(this.zzjo, zzg.zzjo) && zzamh.equals(this.zzjp, zzg.zzjp) && zzamh.equals(this.zzjq, zzg.zzjq) && zzamh.equals(this.zzjr, zzg.zzjr) && zzamh.equals(this.zzjs, zzg.zzjs)) ? (this.zzcaa == null || this.zzcaa.isEmpty()) ? zzg.zzcaa == null || zzg.zzcaa.isEmpty() : this.zzcaa.equals(zzg.zzcaa) : false;
        }

        public int hashCode() {
            int hashCode = (((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzamh.hashCode(this.zzjj)) * 31) + zzamh.hashCode(this.zzjk)) * 31) + zzamh.hashCode(this.zzjl)) * 31) + zzamh.hashCode(this.zzjm)) * 31) + zzamh.hashCode(this.zzjn)) * 31) + zzamh.hashCode(this.zzjo)) * 31) + zzamh.hashCode(this.zzjp)) * 31) + zzamh.hashCode(this.zzjq)) * 31) + zzamh.hashCode(this.zzjr)) * 31) + zzamh.hashCode(this.zzjs)) * 31;
            int hashCode2 = (this.zzcaa == null || this.zzcaa.isEmpty()) ? 0 : this.zzcaa.hashCode();
            return hashCode2 + hashCode;
        }

        public void writeTo(zzamc zzamc) throws IOException {
            int i = 0;
            if (this.zzjj != null && this.zzjj.length > 0) {
                for (int zzE : this.zzjj) {
                    zzamc.zzE(1, zzE);
                }
            }
            if (this.zzjk != null && this.zzjk.length > 0) {
                for (int zzE2 : this.zzjk) {
                    zzamc.zzE(2, zzE2);
                }
            }
            if (this.zzjl != null && this.zzjl.length > 0) {
                for (int zzE22 : this.zzjl) {
                    zzamc.zzE(3, zzE22);
                }
            }
            if (this.zzjm != null && this.zzjm.length > 0) {
                for (int zzE222 : this.zzjm) {
                    zzamc.zzE(4, zzE222);
                }
            }
            if (this.zzjn != null && this.zzjn.length > 0) {
                for (int zzE2222 : this.zzjn) {
                    zzamc.zzE(5, zzE2222);
                }
            }
            if (this.zzjo != null && this.zzjo.length > 0) {
                for (int zzE22222 : this.zzjo) {
                    zzamc.zzE(6, zzE22222);
                }
            }
            if (this.zzjp != null && this.zzjp.length > 0) {
                for (int zzE222222 : this.zzjp) {
                    zzamc.zzE(7, zzE222222);
                }
            }
            if (this.zzjq != null && this.zzjq.length > 0) {
                for (int zzE2222222 : this.zzjq) {
                    zzamc.zzE(8, zzE2222222);
                }
            }
            if (this.zzjr != null && this.zzjr.length > 0) {
                for (int zzE22222222 : this.zzjr) {
                    zzamc.zzE(9, zzE22222222);
                }
            }
            if (this.zzjs != null && this.zzjs.length > 0) {
                while (i < this.zzjs.length) {
                    zzamc.zzE(10, this.zzjs[i]);
                    i++;
                }
            }
            super.writeTo(zzamc);
        }

        public zzg zzK() {
            this.zzjj = zzamm.zzcal;
            this.zzjk = zzamm.zzcal;
            this.zzjl = zzamm.zzcal;
            this.zzjm = zzamm.zzcal;
            this.zzjn = zzamm.zzcal;
            this.zzjo = zzamm.zzcal;
            this.zzjp = zzamm.zzcal;
            this.zzjq = zzamm.zzcal;
            this.zzjr = zzamm.zzcal;
            this.zzjs = zzamm.zzcal;
            this.zzcaa = null;
            this.zzcaj = -1;
            return this;
        }

        /* renamed from: zzg */
        public zzg mergeFrom(zzamb zzamb) throws IOException {
            while (true) {
                int zzWC = zzamb.zzWC();
                int zzc;
                int[] iArr;
                int zznW;
                int[] iArr2;
                switch (zzWC) {
                    case 0:
                        break;
                    case 8:
                        zzc = zzamm.zzc(zzamb, 8);
                        zzWC = this.zzjj == null ? 0 : this.zzjj.length;
                        iArr = new int[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjj, 0, iArr, 0, zzWC);
                        }
                        while (zzWC < iArr.length - 1) {
                            iArr[zzWC] = zzamb.zzWG();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        iArr[zzWC] = zzamb.zzWG();
                        this.zzjj = iArr;
                        continue;
                    case 10:
                        zznW = zzamb.zznW(zzamb.zzWL());
                        zzc = zzamb.getPosition();
                        zzWC = 0;
                        while (zzamb.zzWQ() > 0) {
                            zzamb.zzWG();
                            zzWC++;
                        }
                        zzamb.zznY(zzc);
                        zzc = this.zzjj == null ? 0 : this.zzjj.length;
                        iArr2 = new int[(zzWC + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzjj, 0, iArr2, 0, zzc);
                        }
                        while (zzc < iArr2.length) {
                            iArr2[zzc] = zzamb.zzWG();
                            zzc++;
                        }
                        this.zzjj = iArr2;
                        zzamb.zznX(zznW);
                        continue;
                    case 16:
                        zzc = zzamm.zzc(zzamb, 16);
                        zzWC = this.zzjk == null ? 0 : this.zzjk.length;
                        iArr = new int[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjk, 0, iArr, 0, zzWC);
                        }
                        while (zzWC < iArr.length - 1) {
                            iArr[zzWC] = zzamb.zzWG();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        iArr[zzWC] = zzamb.zzWG();
                        this.zzjk = iArr;
                        continue;
                    case 18:
                        zznW = zzamb.zznW(zzamb.zzWL());
                        zzc = zzamb.getPosition();
                        zzWC = 0;
                        while (zzamb.zzWQ() > 0) {
                            zzamb.zzWG();
                            zzWC++;
                        }
                        zzamb.zznY(zzc);
                        zzc = this.zzjk == null ? 0 : this.zzjk.length;
                        iArr2 = new int[(zzWC + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzjk, 0, iArr2, 0, zzc);
                        }
                        while (zzc < iArr2.length) {
                            iArr2[zzc] = zzamb.zzWG();
                            zzc++;
                        }
                        this.zzjk = iArr2;
                        zzamb.zznX(zznW);
                        continue;
                    case 24:
                        zzc = zzamm.zzc(zzamb, 24);
                        zzWC = this.zzjl == null ? 0 : this.zzjl.length;
                        iArr = new int[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjl, 0, iArr, 0, zzWC);
                        }
                        while (zzWC < iArr.length - 1) {
                            iArr[zzWC] = zzamb.zzWG();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        iArr[zzWC] = zzamb.zzWG();
                        this.zzjl = iArr;
                        continue;
                    case 26:
                        zznW = zzamb.zznW(zzamb.zzWL());
                        zzc = zzamb.getPosition();
                        zzWC = 0;
                        while (zzamb.zzWQ() > 0) {
                            zzamb.zzWG();
                            zzWC++;
                        }
                        zzamb.zznY(zzc);
                        zzc = this.zzjl == null ? 0 : this.zzjl.length;
                        iArr2 = new int[(zzWC + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzjl, 0, iArr2, 0, zzc);
                        }
                        while (zzc < iArr2.length) {
                            iArr2[zzc] = zzamb.zzWG();
                            zzc++;
                        }
                        this.zzjl = iArr2;
                        zzamb.zznX(zznW);
                        continue;
                    case 32:
                        zzc = zzamm.zzc(zzamb, 32);
                        zzWC = this.zzjm == null ? 0 : this.zzjm.length;
                        iArr = new int[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjm, 0, iArr, 0, zzWC);
                        }
                        while (zzWC < iArr.length - 1) {
                            iArr[zzWC] = zzamb.zzWG();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        iArr[zzWC] = zzamb.zzWG();
                        this.zzjm = iArr;
                        continue;
                    case 34:
                        zznW = zzamb.zznW(zzamb.zzWL());
                        zzc = zzamb.getPosition();
                        zzWC = 0;
                        while (zzamb.zzWQ() > 0) {
                            zzamb.zzWG();
                            zzWC++;
                        }
                        zzamb.zznY(zzc);
                        zzc = this.zzjm == null ? 0 : this.zzjm.length;
                        iArr2 = new int[(zzWC + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzjm, 0, iArr2, 0, zzc);
                        }
                        while (zzc < iArr2.length) {
                            iArr2[zzc] = zzamb.zzWG();
                            zzc++;
                        }
                        this.zzjm = iArr2;
                        zzamb.zznX(zznW);
                        continue;
                    case 40:
                        zzc = zzamm.zzc(zzamb, 40);
                        zzWC = this.zzjn == null ? 0 : this.zzjn.length;
                        iArr = new int[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjn, 0, iArr, 0, zzWC);
                        }
                        while (zzWC < iArr.length - 1) {
                            iArr[zzWC] = zzamb.zzWG();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        iArr[zzWC] = zzamb.zzWG();
                        this.zzjn = iArr;
                        continue;
                    case 42:
                        zznW = zzamb.zznW(zzamb.zzWL());
                        zzc = zzamb.getPosition();
                        zzWC = 0;
                        while (zzamb.zzWQ() > 0) {
                            zzamb.zzWG();
                            zzWC++;
                        }
                        zzamb.zznY(zzc);
                        zzc = this.zzjn == null ? 0 : this.zzjn.length;
                        iArr2 = new int[(zzWC + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzjn, 0, iArr2, 0, zzc);
                        }
                        while (zzc < iArr2.length) {
                            iArr2[zzc] = zzamb.zzWG();
                            zzc++;
                        }
                        this.zzjn = iArr2;
                        zzamb.zznX(zznW);
                        continue;
                    case 48:
                        zzc = zzamm.zzc(zzamb, 48);
                        zzWC = this.zzjo == null ? 0 : this.zzjo.length;
                        iArr = new int[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjo, 0, iArr, 0, zzWC);
                        }
                        while (zzWC < iArr.length - 1) {
                            iArr[zzWC] = zzamb.zzWG();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        iArr[zzWC] = zzamb.zzWG();
                        this.zzjo = iArr;
                        continue;
                    case 50:
                        zznW = zzamb.zznW(zzamb.zzWL());
                        zzc = zzamb.getPosition();
                        zzWC = 0;
                        while (zzamb.zzWQ() > 0) {
                            zzamb.zzWG();
                            zzWC++;
                        }
                        zzamb.zznY(zzc);
                        zzc = this.zzjo == null ? 0 : this.zzjo.length;
                        iArr2 = new int[(zzWC + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzjo, 0, iArr2, 0, zzc);
                        }
                        while (zzc < iArr2.length) {
                            iArr2[zzc] = zzamb.zzWG();
                            zzc++;
                        }
                        this.zzjo = iArr2;
                        zzamb.zznX(zznW);
                        continue;
                    case 56:
                        zzc = zzamm.zzc(zzamb, 56);
                        zzWC = this.zzjp == null ? 0 : this.zzjp.length;
                        iArr = new int[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjp, 0, iArr, 0, zzWC);
                        }
                        while (zzWC < iArr.length - 1) {
                            iArr[zzWC] = zzamb.zzWG();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        iArr[zzWC] = zzamb.zzWG();
                        this.zzjp = iArr;
                        continue;
                    case 58:
                        zznW = zzamb.zznW(zzamb.zzWL());
                        zzc = zzamb.getPosition();
                        zzWC = 0;
                        while (zzamb.zzWQ() > 0) {
                            zzamb.zzWG();
                            zzWC++;
                        }
                        zzamb.zznY(zzc);
                        zzc = this.zzjp == null ? 0 : this.zzjp.length;
                        iArr2 = new int[(zzWC + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzjp, 0, iArr2, 0, zzc);
                        }
                        while (zzc < iArr2.length) {
                            iArr2[zzc] = zzamb.zzWG();
                            zzc++;
                        }
                        this.zzjp = iArr2;
                        zzamb.zznX(zznW);
                        continue;
                    case 64:
                        zzc = zzamm.zzc(zzamb, 64);
                        zzWC = this.zzjq == null ? 0 : this.zzjq.length;
                        iArr = new int[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjq, 0, iArr, 0, zzWC);
                        }
                        while (zzWC < iArr.length - 1) {
                            iArr[zzWC] = zzamb.zzWG();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        iArr[zzWC] = zzamb.zzWG();
                        this.zzjq = iArr;
                        continue;
                    case 66:
                        zznW = zzamb.zznW(zzamb.zzWL());
                        zzc = zzamb.getPosition();
                        zzWC = 0;
                        while (zzamb.zzWQ() > 0) {
                            zzamb.zzWG();
                            zzWC++;
                        }
                        zzamb.zznY(zzc);
                        zzc = this.zzjq == null ? 0 : this.zzjq.length;
                        iArr2 = new int[(zzWC + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzjq, 0, iArr2, 0, zzc);
                        }
                        while (zzc < iArr2.length) {
                            iArr2[zzc] = zzamb.zzWG();
                            zzc++;
                        }
                        this.zzjq = iArr2;
                        zzamb.zznX(zznW);
                        continue;
                    case 72:
                        zzc = zzamm.zzc(zzamb, 72);
                        zzWC = this.zzjr == null ? 0 : this.zzjr.length;
                        iArr = new int[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjr, 0, iArr, 0, zzWC);
                        }
                        while (zzWC < iArr.length - 1) {
                            iArr[zzWC] = zzamb.zzWG();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        iArr[zzWC] = zzamb.zzWG();
                        this.zzjr = iArr;
                        continue;
                    case 74:
                        zznW = zzamb.zznW(zzamb.zzWL());
                        zzc = zzamb.getPosition();
                        zzWC = 0;
                        while (zzamb.zzWQ() > 0) {
                            zzamb.zzWG();
                            zzWC++;
                        }
                        zzamb.zznY(zzc);
                        zzc = this.zzjr == null ? 0 : this.zzjr.length;
                        iArr2 = new int[(zzWC + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzjr, 0, iArr2, 0, zzc);
                        }
                        while (zzc < iArr2.length) {
                            iArr2[zzc] = zzamb.zzWG();
                            zzc++;
                        }
                        this.zzjr = iArr2;
                        zzamb.zznX(zznW);
                        continue;
                    case 80:
                        zzc = zzamm.zzc(zzamb, 80);
                        zzWC = this.zzjs == null ? 0 : this.zzjs.length;
                        iArr = new int[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjs, 0, iArr, 0, zzWC);
                        }
                        while (zzWC < iArr.length - 1) {
                            iArr[zzWC] = zzamb.zzWG();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        iArr[zzWC] = zzamb.zzWG();
                        this.zzjs = iArr;
                        continue;
                    case 82:
                        zznW = zzamb.zznW(zzamb.zzWL());
                        zzc = zzamb.getPosition();
                        zzWC = 0;
                        while (zzamb.zzWQ() > 0) {
                            zzamb.zzWG();
                            zzWC++;
                        }
                        zzamb.zznY(zzc);
                        zzc = this.zzjs == null ? 0 : this.zzjs.length;
                        iArr2 = new int[(zzWC + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzjs, 0, iArr2, 0, zzc);
                        }
                        while (zzc < iArr2.length) {
                            iArr2[zzc] = zzamb.zzWG();
                            zzc++;
                        }
                        this.zzjs = iArr2;
                        zzamb.zznX(zznW);
                        continue;
                    default:
                        if (!super.zza(zzamb, zzWC)) {
                            break;
                        }
                        continue;
                }
            }
            return this;
        }

        /* Access modifiers changed, original: protected */
        public int zzy() {
            int i;
            int i2;
            int i3 = 0;
            int zzy = super.zzy();
            if (this.zzjj == null || this.zzjj.length <= 0) {
                i = zzy;
            } else {
                i2 = 0;
                for (int zzoc : this.zzjj) {
                    i2 += zzamc.zzoc(zzoc);
                }
                i = (zzy + i2) + (this.zzjj.length * 1);
            }
            if (this.zzjk != null && this.zzjk.length > 0) {
                zzy = 0;
                for (int zzoc2 : this.zzjk) {
                    zzy += zzamc.zzoc(zzoc2);
                }
                i = (i + zzy) + (this.zzjk.length * 1);
            }
            if (this.zzjl != null && this.zzjl.length > 0) {
                zzy = 0;
                for (int zzoc22 : this.zzjl) {
                    zzy += zzamc.zzoc(zzoc22);
                }
                i = (i + zzy) + (this.zzjl.length * 1);
            }
            if (this.zzjm != null && this.zzjm.length > 0) {
                zzy = 0;
                for (int zzoc222 : this.zzjm) {
                    zzy += zzamc.zzoc(zzoc222);
                }
                i = (i + zzy) + (this.zzjm.length * 1);
            }
            if (this.zzjn != null && this.zzjn.length > 0) {
                zzy = 0;
                for (int zzoc2222 : this.zzjn) {
                    zzy += zzamc.zzoc(zzoc2222);
                }
                i = (i + zzy) + (this.zzjn.length * 1);
            }
            if (this.zzjo != null && this.zzjo.length > 0) {
                zzy = 0;
                for (int zzoc22222 : this.zzjo) {
                    zzy += zzamc.zzoc(zzoc22222);
                }
                i = (i + zzy) + (this.zzjo.length * 1);
            }
            if (this.zzjp != null && this.zzjp.length > 0) {
                zzy = 0;
                for (int zzoc222222 : this.zzjp) {
                    zzy += zzamc.zzoc(zzoc222222);
                }
                i = (i + zzy) + (this.zzjp.length * 1);
            }
            if (this.zzjq != null && this.zzjq.length > 0) {
                zzy = 0;
                for (int zzoc2222222 : this.zzjq) {
                    zzy += zzamc.zzoc(zzoc2222222);
                }
                i = (i + zzy) + (this.zzjq.length * 1);
            }
            if (this.zzjr != null && this.zzjr.length > 0) {
                zzy = 0;
                for (int zzoc22222222 : this.zzjr) {
                    zzy += zzamc.zzoc(zzoc22222222);
                }
                i = (i + zzy) + (this.zzjr.length * 1);
            }
            if (this.zzjs == null || this.zzjs.length <= 0) {
                return i;
            }
            i2 = 0;
            while (i3 < this.zzjs.length) {
                i2 += zzamc.zzoc(this.zzjs[i3]);
                i3++;
            }
            return (i + i2) + (this.zzjs.length * 1);
        }
    }

    public static final class zzh extends zzamd<zzh> {
        public static final zzame<com.google.android.gms.internal.zzag.zza, zzh> zzjt = zzame.zza(11, zzh.class, 810);
        private static final zzh[] zzju = new zzh[0];
        public int zzjA;
        public int zzjB;
        public int[] zzjv;
        public int[] zzjw;
        public int[] zzjx;
        public int zzjy;
        public int[] zzjz;

        public zzh() {
            zzL();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzh)) {
                return false;
            }
            zzh zzh = (zzh) obj;
            return (zzamh.equals(this.zzjv, zzh.zzjv) && zzamh.equals(this.zzjw, zzh.zzjw) && zzamh.equals(this.zzjx, zzh.zzjx) && this.zzjy == zzh.zzjy && zzamh.equals(this.zzjz, zzh.zzjz) && this.zzjA == zzh.zzjA && this.zzjB == zzh.zzjB) ? (this.zzcaa == null || this.zzcaa.isEmpty()) ? zzh.zzcaa == null || zzh.zzcaa.isEmpty() : this.zzcaa.equals(zzh.zzcaa) : false;
        }

        public int hashCode() {
            int hashCode = (((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzamh.hashCode(this.zzjv)) * 31) + zzamh.hashCode(this.zzjw)) * 31) + zzamh.hashCode(this.zzjx)) * 31) + this.zzjy) * 31) + zzamh.hashCode(this.zzjz)) * 31) + this.zzjA) * 31) + this.zzjB) * 31;
            int hashCode2 = (this.zzcaa == null || this.zzcaa.isEmpty()) ? 0 : this.zzcaa.hashCode();
            return hashCode2 + hashCode;
        }

        public void writeTo(zzamc zzamc) throws IOException {
            int i = 0;
            if (this.zzjv != null && this.zzjv.length > 0) {
                for (int zzE : this.zzjv) {
                    zzamc.zzE(1, zzE);
                }
            }
            if (this.zzjw != null && this.zzjw.length > 0) {
                for (int zzE2 : this.zzjw) {
                    zzamc.zzE(2, zzE2);
                }
            }
            if (this.zzjx != null && this.zzjx.length > 0) {
                for (int zzE22 : this.zzjx) {
                    zzamc.zzE(3, zzE22);
                }
            }
            if (this.zzjy != 0) {
                zzamc.zzE(4, this.zzjy);
            }
            if (this.zzjz != null && this.zzjz.length > 0) {
                while (i < this.zzjz.length) {
                    zzamc.zzE(5, this.zzjz[i]);
                    i++;
                }
            }
            if (this.zzjA != 0) {
                zzamc.zzE(6, this.zzjA);
            }
            if (this.zzjB != 0) {
                zzamc.zzE(7, this.zzjB);
            }
            super.writeTo(zzamc);
        }

        public zzh zzL() {
            this.zzjv = zzamm.zzcal;
            this.zzjw = zzamm.zzcal;
            this.zzjx = zzamm.zzcal;
            this.zzjy = 0;
            this.zzjz = zzamm.zzcal;
            this.zzjA = 0;
            this.zzjB = 0;
            this.zzcaa = null;
            this.zzcaj = -1;
            return this;
        }

        /* renamed from: zzh */
        public zzh mergeFrom(zzamb zzamb) throws IOException {
            while (true) {
                int zzWC = zzamb.zzWC();
                int zzc;
                int[] iArr;
                int zznW;
                int[] iArr2;
                switch (zzWC) {
                    case 0:
                        break;
                    case 8:
                        zzc = zzamm.zzc(zzamb, 8);
                        zzWC = this.zzjv == null ? 0 : this.zzjv.length;
                        iArr = new int[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjv, 0, iArr, 0, zzWC);
                        }
                        while (zzWC < iArr.length - 1) {
                            iArr[zzWC] = zzamb.zzWG();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        iArr[zzWC] = zzamb.zzWG();
                        this.zzjv = iArr;
                        continue;
                    case 10:
                        zznW = zzamb.zznW(zzamb.zzWL());
                        zzc = zzamb.getPosition();
                        zzWC = 0;
                        while (zzamb.zzWQ() > 0) {
                            zzamb.zzWG();
                            zzWC++;
                        }
                        zzamb.zznY(zzc);
                        zzc = this.zzjv == null ? 0 : this.zzjv.length;
                        iArr2 = new int[(zzWC + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzjv, 0, iArr2, 0, zzc);
                        }
                        while (zzc < iArr2.length) {
                            iArr2[zzc] = zzamb.zzWG();
                            zzc++;
                        }
                        this.zzjv = iArr2;
                        zzamb.zznX(zznW);
                        continue;
                    case 16:
                        zzc = zzamm.zzc(zzamb, 16);
                        zzWC = this.zzjw == null ? 0 : this.zzjw.length;
                        iArr = new int[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjw, 0, iArr, 0, zzWC);
                        }
                        while (zzWC < iArr.length - 1) {
                            iArr[zzWC] = zzamb.zzWG();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        iArr[zzWC] = zzamb.zzWG();
                        this.zzjw = iArr;
                        continue;
                    case 18:
                        zznW = zzamb.zznW(zzamb.zzWL());
                        zzc = zzamb.getPosition();
                        zzWC = 0;
                        while (zzamb.zzWQ() > 0) {
                            zzamb.zzWG();
                            zzWC++;
                        }
                        zzamb.zznY(zzc);
                        zzc = this.zzjw == null ? 0 : this.zzjw.length;
                        iArr2 = new int[(zzWC + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzjw, 0, iArr2, 0, zzc);
                        }
                        while (zzc < iArr2.length) {
                            iArr2[zzc] = zzamb.zzWG();
                            zzc++;
                        }
                        this.zzjw = iArr2;
                        zzamb.zznX(zznW);
                        continue;
                    case 24:
                        zzc = zzamm.zzc(zzamb, 24);
                        zzWC = this.zzjx == null ? 0 : this.zzjx.length;
                        iArr = new int[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjx, 0, iArr, 0, zzWC);
                        }
                        while (zzWC < iArr.length - 1) {
                            iArr[zzWC] = zzamb.zzWG();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        iArr[zzWC] = zzamb.zzWG();
                        this.zzjx = iArr;
                        continue;
                    case 26:
                        zznW = zzamb.zznW(zzamb.zzWL());
                        zzc = zzamb.getPosition();
                        zzWC = 0;
                        while (zzamb.zzWQ() > 0) {
                            zzamb.zzWG();
                            zzWC++;
                        }
                        zzamb.zznY(zzc);
                        zzc = this.zzjx == null ? 0 : this.zzjx.length;
                        iArr2 = new int[(zzWC + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzjx, 0, iArr2, 0, zzc);
                        }
                        while (zzc < iArr2.length) {
                            iArr2[zzc] = zzamb.zzWG();
                            zzc++;
                        }
                        this.zzjx = iArr2;
                        zzamb.zznX(zznW);
                        continue;
                    case 32:
                        this.zzjy = zzamb.zzWG();
                        continue;
                    case 40:
                        zzc = zzamm.zzc(zzamb, 40);
                        zzWC = this.zzjz == null ? 0 : this.zzjz.length;
                        iArr = new int[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjz, 0, iArr, 0, zzWC);
                        }
                        while (zzWC < iArr.length - 1) {
                            iArr[zzWC] = zzamb.zzWG();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        iArr[zzWC] = zzamb.zzWG();
                        this.zzjz = iArr;
                        continue;
                    case 42:
                        zznW = zzamb.zznW(zzamb.zzWL());
                        zzc = zzamb.getPosition();
                        zzWC = 0;
                        while (zzamb.zzWQ() > 0) {
                            zzamb.zzWG();
                            zzWC++;
                        }
                        zzamb.zznY(zzc);
                        zzc = this.zzjz == null ? 0 : this.zzjz.length;
                        iArr2 = new int[(zzWC + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzjz, 0, iArr2, 0, zzc);
                        }
                        while (zzc < iArr2.length) {
                            iArr2[zzc] = zzamb.zzWG();
                            zzc++;
                        }
                        this.zzjz = iArr2;
                        zzamb.zznX(zznW);
                        continue;
                    case 48:
                        this.zzjA = zzamb.zzWG();
                        continue;
                    case 56:
                        this.zzjB = zzamb.zzWG();
                        continue;
                    default:
                        if (!super.zza(zzamb, zzWC)) {
                            break;
                        }
                        continue;
                }
            }
            return this;
        }

        /* Access modifiers changed, original: protected */
        public int zzy() {
            int i;
            int i2;
            int i3 = 0;
            int zzy = super.zzy();
            if (this.zzjv == null || this.zzjv.length <= 0) {
                i = zzy;
            } else {
                i2 = 0;
                for (int zzoc : this.zzjv) {
                    i2 += zzamc.zzoc(zzoc);
                }
                i = (zzy + i2) + (this.zzjv.length * 1);
            }
            if (this.zzjw != null && this.zzjw.length > 0) {
                zzy = 0;
                for (int zzoc2 : this.zzjw) {
                    zzy += zzamc.zzoc(zzoc2);
                }
                i = (i + zzy) + (this.zzjw.length * 1);
            }
            if (this.zzjx != null && this.zzjx.length > 0) {
                zzy = 0;
                for (int zzoc22 : this.zzjx) {
                    zzy += zzamc.zzoc(zzoc22);
                }
                i = (i + zzy) + (this.zzjx.length * 1);
            }
            if (this.zzjy != 0) {
                i += zzamc.zzG(4, this.zzjy);
            }
            if (this.zzjz != null && this.zzjz.length > 0) {
                i2 = 0;
                while (i3 < this.zzjz.length) {
                    i2 += zzamc.zzoc(this.zzjz[i3]);
                    i3++;
                }
                i = (i + i2) + (this.zzjz.length * 1);
            }
            if (this.zzjA != 0) {
                i += zzamc.zzG(6, this.zzjA);
            }
            return this.zzjB != 0 ? i + zzamc.zzG(7, this.zzjB) : i;
        }
    }

    public static final class zzi extends zzamd<zzi> {
        private static volatile zzi[] zzjC;
        public String name;
        public com.google.android.gms.internal.zzag.zza zzjD;
        public zzd zzjE;

        public zzi() {
            zzN();
        }

        public static zzi[] zzM() {
            if (zzjC == null) {
                synchronized (zzamh.zzcai) {
                    if (zzjC == null) {
                        zzjC = new zzi[0];
                    }
                }
            }
            return zzjC;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzi)) {
                return false;
            }
            zzi zzi = (zzi) obj;
            if (this.name == null) {
                if (zzi.name != null) {
                    return false;
                }
            } else if (!this.name.equals(zzi.name)) {
                return false;
            }
            if (this.zzjD == null) {
                if (zzi.zzjD != null) {
                    return false;
                }
            } else if (!this.zzjD.equals(zzi.zzjD)) {
                return false;
            }
            if (this.zzjE == null) {
                if (zzi.zzjE != null) {
                    return false;
                }
            } else if (!this.zzjE.equals(zzi.zzjE)) {
                return false;
            }
            return (this.zzcaa == null || this.zzcaa.isEmpty()) ? zzi.zzcaa == null || zzi.zzcaa.isEmpty() : this.zzcaa.equals(zzi.zzcaa);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzjE == null ? 0 : this.zzjE.hashCode()) + (((this.zzjD == null ? 0 : this.zzjD.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31;
            if (!(this.zzcaa == null || this.zzcaa.isEmpty())) {
                i = this.zzcaa.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(zzamc zzamc) throws IOException {
            if (!this.name.equals("")) {
                zzamc.zzq(1, this.name);
            }
            if (this.zzjD != null) {
                zzamc.zza(2, this.zzjD);
            }
            if (this.zzjE != null) {
                zzamc.zza(3, this.zzjE);
            }
            super.writeTo(zzamc);
        }

        public zzi zzN() {
            this.name = "";
            this.zzjD = null;
            this.zzjE = null;
            this.zzcaa = null;
            this.zzcaj = -1;
            return this;
        }

        /* renamed from: zzi */
        public zzi mergeFrom(zzamb zzamb) throws IOException {
            while (true) {
                int zzWC = zzamb.zzWC();
                switch (zzWC) {
                    case 0:
                        break;
                    case 10:
                        this.name = zzamb.readString();
                        continue;
                    case 18:
                        if (this.zzjD == null) {
                            this.zzjD = new com.google.android.gms.internal.zzag.zza();
                        }
                        zzamb.zza(this.zzjD);
                        continue;
                    case 26:
                        if (this.zzjE == null) {
                            this.zzjE = new zzd();
                        }
                        zzamb.zza(this.zzjE);
                        continue;
                    default:
                        if (!super.zza(zzamb, zzWC)) {
                            break;
                        }
                        continue;
                }
            }
            return this;
        }

        /* Access modifiers changed, original: protected */
        public int zzy() {
            int zzy = super.zzy();
            if (!this.name.equals("")) {
                zzy += zzamc.zzr(1, this.name);
            }
            if (this.zzjD != null) {
                zzy += zzamc.zzc(2, this.zzjD);
            }
            return this.zzjE != null ? zzy + zzamc.zzc(3, this.zzjE) : zzy;
        }
    }

    public static final class zzj extends zzamd<zzj> {
        public zzi[] zzjF;
        public zzf zzjG;
        public String zzjH;

        public zzj() {
            zzO();
        }

        public static zzj zzd(byte[] bArr) throws zzami {
            return (zzj) zzamj.mergeFrom(new zzj(), bArr);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzj)) {
                return false;
            }
            zzj zzj = (zzj) obj;
            if (!zzamh.equals(this.zzjF, zzj.zzjF)) {
                return false;
            }
            if (this.zzjG == null) {
                if (zzj.zzjG != null) {
                    return false;
                }
            } else if (!this.zzjG.equals(zzj.zzjG)) {
                return false;
            }
            if (this.zzjH == null) {
                if (zzj.zzjH != null) {
                    return false;
                }
            } else if (!this.zzjH.equals(zzj.zzjH)) {
                return false;
            }
            return (this.zzcaa == null || this.zzcaa.isEmpty()) ? zzj.zzcaa == null || zzj.zzcaa.isEmpty() : this.zzcaa.equals(zzj.zzcaa);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzjH == null ? 0 : this.zzjH.hashCode()) + (((this.zzjG == null ? 0 : this.zzjG.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + zzamh.hashCode(this.zzjF)) * 31)) * 31)) * 31;
            if (!(this.zzcaa == null || this.zzcaa.isEmpty())) {
                i = this.zzcaa.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(zzamc zzamc) throws IOException {
            if (this.zzjF != null && this.zzjF.length > 0) {
                for (zzamj zzamj : this.zzjF) {
                    if (zzamj != null) {
                        zzamc.zza(1, zzamj);
                    }
                }
            }
            if (this.zzjG != null) {
                zzamc.zza(2, this.zzjG);
            }
            if (!this.zzjH.equals("")) {
                zzamc.zzq(3, this.zzjH);
            }
            super.writeTo(zzamc);
        }

        public zzj zzO() {
            this.zzjF = zzi.zzM();
            this.zzjG = null;
            this.zzjH = "";
            this.zzcaa = null;
            this.zzcaj = -1;
            return this;
        }

        /* renamed from: zzj */
        public zzj mergeFrom(zzamb zzamb) throws IOException {
            while (true) {
                int zzWC = zzamb.zzWC();
                switch (zzWC) {
                    case 0:
                        break;
                    case 10:
                        int zzc = zzamm.zzc(zzamb, 10);
                        zzWC = this.zzjF == null ? 0 : this.zzjF.length;
                        zzi[] zziArr = new zzi[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjF, 0, zziArr, 0, zzWC);
                        }
                        while (zzWC < zziArr.length - 1) {
                            zziArr[zzWC] = new zzi();
                            zzamb.zza(zziArr[zzWC]);
                            zzamb.zzWC();
                            zzWC++;
                        }
                        zziArr[zzWC] = new zzi();
                        zzamb.zza(zziArr[zzWC]);
                        this.zzjF = zziArr;
                        continue;
                    case 18:
                        if (this.zzjG == null) {
                            this.zzjG = new zzf();
                        }
                        zzamb.zza(this.zzjG);
                        continue;
                    case 26:
                        this.zzjH = zzamb.readString();
                        continue;
                    default:
                        if (!super.zza(zzamb, zzWC)) {
                            break;
                        }
                        continue;
                }
            }
            return this;
        }

        /* Access modifiers changed, original: protected */
        public int zzy() {
            int zzy = super.zzy();
            if (this.zzjF != null && this.zzjF.length > 0) {
                for (zzamj zzamj : this.zzjF) {
                    if (zzamj != null) {
                        zzy += zzamc.zzc(1, zzamj);
                    }
                }
            }
            if (this.zzjG != null) {
                zzy += zzamc.zzc(2, this.zzjG);
            }
            return !this.zzjH.equals("") ? zzy + zzamc.zzr(3, this.zzjH) : zzy;
        }
    }
}
