package com.google.android.gms.internal;

import android.support.p000v4.media.TransportMediator;
import java.io.IOException;
import java.util.Arrays;

public interface zzamo {

    public static final class zza extends zzamd<zza> {
        public long[] zzcaA;
        public String[] zzcax;
        public String[] zzcay;
        public int[] zzcaz;

        public zza() {
            zzXh();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            return (zzamh.equals(this.zzcax, zza.zzcax) && zzamh.equals(this.zzcay, zza.zzcay) && zzamh.equals(this.zzcaz, zza.zzcaz) && zzamh.equals(this.zzcaA, zza.zzcaA)) ? (this.zzcaa == null || this.zzcaa.isEmpty()) ? zza.zzcaa == null || zza.zzcaa.isEmpty() : this.zzcaa.equals(zza.zzcaa) : false;
        }

        public int hashCode() {
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + zzamh.hashCode(this.zzcax)) * 31) + zzamh.hashCode(this.zzcay)) * 31) + zzamh.hashCode(this.zzcaz)) * 31) + zzamh.hashCode(this.zzcaA)) * 31;
            int hashCode2 = (this.zzcaa == null || this.zzcaa.isEmpty()) ? 0 : this.zzcaa.hashCode();
            return hashCode2 + hashCode;
        }

        public void writeTo(zzamc zzamc) throws IOException {
            int i = 0;
            if (this.zzcax != null && this.zzcax.length > 0) {
                for (String str : this.zzcax) {
                    if (str != null) {
                        zzamc.zzq(1, str);
                    }
                }
            }
            if (this.zzcay != null && this.zzcay.length > 0) {
                for (String str2 : this.zzcay) {
                    if (str2 != null) {
                        zzamc.zzq(2, str2);
                    }
                }
            }
            if (this.zzcaz != null && this.zzcaz.length > 0) {
                for (int zzE : this.zzcaz) {
                    zzamc.zzE(3, zzE);
                }
            }
            if (this.zzcaA != null && this.zzcaA.length > 0) {
                while (i < this.zzcaA.length) {
                    zzamc.zzb(4, this.zzcaA[i]);
                    i++;
                }
            }
            super.writeTo(zzamc);
        }

        public zza zzXh() {
            this.zzcax = zzamm.zzcaq;
            this.zzcay = zzamm.zzcaq;
            this.zzcaz = zzamm.zzcal;
            this.zzcaA = zzamm.zzcam;
            this.zzcaa = null;
            this.zzcaj = -1;
            return this;
        }

        /* renamed from: zzY */
        public zza mergeFrom(zzamb zzamb) throws IOException {
            while (true) {
                int zzWC = zzamb.zzWC();
                int zzc;
                String[] strArr;
                int zznW;
                switch (zzWC) {
                    case 0:
                        break;
                    case 10:
                        zzc = zzamm.zzc(zzamb, 10);
                        zzWC = this.zzcax == null ? 0 : this.zzcax.length;
                        strArr = new String[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzcax, 0, strArr, 0, zzWC);
                        }
                        while (zzWC < strArr.length - 1) {
                            strArr[zzWC] = zzamb.readString();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        strArr[zzWC] = zzamb.readString();
                        this.zzcax = strArr;
                        continue;
                    case 18:
                        zzc = zzamm.zzc(zzamb, 18);
                        zzWC = this.zzcay == null ? 0 : this.zzcay.length;
                        strArr = new String[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzcay, 0, strArr, 0, zzWC);
                        }
                        while (zzWC < strArr.length - 1) {
                            strArr[zzWC] = zzamb.readString();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        strArr[zzWC] = zzamb.readString();
                        this.zzcay = strArr;
                        continue;
                    case 24:
                        zzc = zzamm.zzc(zzamb, 24);
                        zzWC = this.zzcaz == null ? 0 : this.zzcaz.length;
                        int[] iArr = new int[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzcaz, 0, iArr, 0, zzWC);
                        }
                        while (zzWC < iArr.length - 1) {
                            iArr[zzWC] = zzamb.zzWG();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        iArr[zzWC] = zzamb.zzWG();
                        this.zzcaz = iArr;
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
                        zzc = this.zzcaz == null ? 0 : this.zzcaz.length;
                        int[] iArr2 = new int[(zzWC + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzcaz, 0, iArr2, 0, zzc);
                        }
                        while (zzc < iArr2.length) {
                            iArr2[zzc] = zzamb.zzWG();
                            zzc++;
                        }
                        this.zzcaz = iArr2;
                        zzamb.zznX(zznW);
                        continue;
                    case 32:
                        zzc = zzamm.zzc(zzamb, 32);
                        zzWC = this.zzcaA == null ? 0 : this.zzcaA.length;
                        long[] jArr = new long[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzcaA, 0, jArr, 0, zzWC);
                        }
                        while (zzWC < jArr.length - 1) {
                            jArr[zzWC] = zzamb.zzWF();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        jArr[zzWC] = zzamb.zzWF();
                        this.zzcaA = jArr;
                        continue;
                    case 34:
                        zznW = zzamb.zznW(zzamb.zzWL());
                        zzc = zzamb.getPosition();
                        zzWC = 0;
                        while (zzamb.zzWQ() > 0) {
                            zzamb.zzWF();
                            zzWC++;
                        }
                        zzamb.zznY(zzc);
                        zzc = this.zzcaA == null ? 0 : this.zzcaA.length;
                        long[] jArr2 = new long[(zzWC + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzcaA, 0, jArr2, 0, zzc);
                        }
                        while (zzc < jArr2.length) {
                            jArr2[zzc] = zzamb.zzWF();
                            zzc++;
                        }
                        this.zzcaA = jArr2;
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
            int i3;
            int i4 = 0;
            int zzy = super.zzy();
            if (this.zzcax == null || this.zzcax.length <= 0) {
                i = zzy;
            } else {
                i2 = 0;
                i3 = 0;
                for (String str : this.zzcax) {
                    if (str != null) {
                        i3++;
                        i2 += zzamc.zziZ(str);
                    }
                }
                i = (zzy + i2) + (i3 * 1);
            }
            if (this.zzcay != null && this.zzcay.length > 0) {
                i3 = 0;
                zzy = 0;
                for (String str2 : this.zzcay) {
                    if (str2 != null) {
                        zzy++;
                        i3 += zzamc.zziZ(str2);
                    }
                }
                i = (i + i3) + (zzy * 1);
            }
            if (this.zzcaz != null && this.zzcaz.length > 0) {
                i3 = 0;
                for (int zzy2 : this.zzcaz) {
                    i3 += zzamc.zzoc(zzy2);
                }
                i = (i + i3) + (this.zzcaz.length * 1);
            }
            if (this.zzcaA == null || this.zzcaA.length <= 0) {
                return i;
            }
            i2 = 0;
            while (i4 < this.zzcaA.length) {
                i2 += zzamc.zzaU(this.zzcaA[i4]);
                i4++;
            }
            return (i + i2) + (this.zzcaA.length * 1);
        }
    }

    public static final class zzb extends zzamd<zzb> {
        public String version;
        public int zzcaB;
        public String zzcaC;

        public zzb() {
            zzXi();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            if (this.zzcaB != zzb.zzcaB) {
                return false;
            }
            if (this.zzcaC == null) {
                if (zzb.zzcaC != null) {
                    return false;
                }
            } else if (!this.zzcaC.equals(zzb.zzcaC)) {
                return false;
            }
            if (this.version == null) {
                if (zzb.version != null) {
                    return false;
                }
            } else if (!this.version.equals(zzb.version)) {
                return false;
            }
            return (this.zzcaa == null || this.zzcaa.isEmpty()) ? zzb.zzcaa == null || zzb.zzcaa.isEmpty() : this.zzcaa.equals(zzb.zzcaa);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.version == null ? 0 : this.version.hashCode()) + (((this.zzcaC == null ? 0 : this.zzcaC.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + this.zzcaB) * 31)) * 31)) * 31;
            if (!(this.zzcaa == null || this.zzcaa.isEmpty())) {
                i = this.zzcaa.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(zzamc zzamc) throws IOException {
            if (this.zzcaB != 0) {
                zzamc.zzE(1, this.zzcaB);
            }
            if (!this.zzcaC.equals("")) {
                zzamc.zzq(2, this.zzcaC);
            }
            if (!this.version.equals("")) {
                zzamc.zzq(3, this.version);
            }
            super.writeTo(zzamc);
        }

        public zzb zzXi() {
            this.zzcaB = 0;
            this.zzcaC = "";
            this.version = "";
            this.zzcaa = null;
            this.zzcaj = -1;
            return this;
        }

        /* renamed from: zzZ */
        public zzb mergeFrom(zzamb zzamb) throws IOException {
            while (true) {
                int zzWC = zzamb.zzWC();
                switch (zzWC) {
                    case 0:
                        break;
                    case 8:
                        zzWC = zzamb.zzWG();
                        switch (zzWC) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                            case 16:
                            case 17:
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                                this.zzcaB = zzWC;
                                break;
                            default:
                                continue;
                        }
                    case 18:
                        this.zzcaC = zzamb.readString();
                        continue;
                    case 26:
                        this.version = zzamb.readString();
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
            if (this.zzcaB != 0) {
                zzy += zzamc.zzG(1, this.zzcaB);
            }
            if (!this.zzcaC.equals("")) {
                zzy += zzamc.zzr(2, this.zzcaC);
            }
            return !this.version.equals("") ? zzy + zzamc.zzr(3, this.version) : zzy;
        }
    }

    public static final class zzc extends zzamd<zzc> {
        public byte[] zzcaD;
        public byte[][] zzcaE;
        public boolean zzcaF;

        public zzc() {
            zzXj();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc zzc = (zzc) obj;
            return (Arrays.equals(this.zzcaD, zzc.zzcaD) && zzamh.zza(this.zzcaE, zzc.zzcaE) && this.zzcaF == zzc.zzcaF) ? (this.zzcaa == null || this.zzcaa.isEmpty()) ? zzc.zzcaa == null || zzc.zzcaa.isEmpty() : this.zzcaa.equals(zzc.zzcaa) : false;
        }

        public int hashCode() {
            int hashCode = ((this.zzcaF ? 1231 : 1237) + ((((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.zzcaD)) * 31) + zzamh.zza(this.zzcaE)) * 31)) * 31;
            int hashCode2 = (this.zzcaa == null || this.zzcaa.isEmpty()) ? 0 : this.zzcaa.hashCode();
            return hashCode2 + hashCode;
        }

        public void writeTo(zzamc zzamc) throws IOException {
            if (!Arrays.equals(this.zzcaD, zzamm.zzcas)) {
                zzamc.zza(1, this.zzcaD);
            }
            if (this.zzcaE != null && this.zzcaE.length > 0) {
                for (byte[] bArr : this.zzcaE) {
                    if (bArr != null) {
                        zzamc.zza(2, bArr);
                    }
                }
            }
            if (this.zzcaF) {
                zzamc.zzj(3, this.zzcaF);
            }
            super.writeTo(zzamc);
        }

        public zzc zzXj() {
            this.zzcaD = zzamm.zzcas;
            this.zzcaE = zzamm.zzcar;
            this.zzcaF = false;
            this.zzcaa = null;
            this.zzcaj = -1;
            return this;
        }

        /* renamed from: zzaa */
        public zzc mergeFrom(zzamb zzamb) throws IOException {
            while (true) {
                int zzWC = zzamb.zzWC();
                switch (zzWC) {
                    case 0:
                        break;
                    case 10:
                        this.zzcaD = zzamb.readBytes();
                        continue;
                    case 18:
                        int zzc = zzamm.zzc(zzamb, 18);
                        zzWC = this.zzcaE == null ? 0 : this.zzcaE.length;
                        byte[][] bArr = new byte[(zzc + zzWC)][];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzcaE, 0, bArr, 0, zzWC);
                        }
                        while (zzWC < bArr.length - 1) {
                            bArr[zzWC] = zzamb.readBytes();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        bArr[zzWC] = zzamb.readBytes();
                        this.zzcaE = bArr;
                        continue;
                    case 24:
                        this.zzcaF = zzamb.zzWI();
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
            if (!Arrays.equals(this.zzcaD, zzamm.zzcas)) {
                zzy += zzamc.zzb(1, this.zzcaD);
            }
            if (this.zzcaE != null && this.zzcaE.length > 0) {
                int i2 = 0;
                int i3 = 0;
                while (i < this.zzcaE.length) {
                    byte[] bArr = this.zzcaE[i];
                    if (bArr != null) {
                        i3++;
                        i2 += zzamc.zzQ(bArr);
                    }
                    i++;
                }
                zzy = (zzy + i2) + (i3 * 1);
            }
            return this.zzcaF ? zzy + zzamc.zzk(3, this.zzcaF) : zzy;
        }
    }

    public static final class zzd extends zzamd<zzd> {
        public String tag;
        public boolean zzbLB;
        public long zzcaG;
        public long zzcaH;
        public long zzcaI;
        public int zzcaJ;
        public zze[] zzcaK;
        public zzb zzcaL;
        public byte[] zzcaM;
        public byte[] zzcaN;
        public byte[] zzcaO;
        public zza zzcaP;
        public String zzcaQ;
        public long zzcaR;
        public zzc zzcaS;
        public byte[] zzcaT;
        public int zzcaU;
        public int[] zzcaV;
        public long zzcaW;
        public zzf zzcaX;
        public int zzoZ;

        public zzd() {
            zzXk();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzd)) {
                return false;
            }
            zzd zzd = (zzd) obj;
            if (this.zzcaG != zzd.zzcaG || this.zzcaH != zzd.zzcaH || this.zzcaI != zzd.zzcaI) {
                return false;
            }
            if (this.tag == null) {
                if (zzd.tag != null) {
                    return false;
                }
            } else if (!this.tag.equals(zzd.tag)) {
                return false;
            }
            if (this.zzcaJ != zzd.zzcaJ || this.zzoZ != zzd.zzoZ || this.zzbLB != zzd.zzbLB || !zzamh.equals(this.zzcaK, zzd.zzcaK)) {
                return false;
            }
            if (this.zzcaL == null) {
                if (zzd.zzcaL != null) {
                    return false;
                }
            } else if (!this.zzcaL.equals(zzd.zzcaL)) {
                return false;
            }
            if (!Arrays.equals(this.zzcaM, zzd.zzcaM) || !Arrays.equals(this.zzcaN, zzd.zzcaN) || !Arrays.equals(this.zzcaO, zzd.zzcaO)) {
                return false;
            }
            if (this.zzcaP == null) {
                if (zzd.zzcaP != null) {
                    return false;
                }
            } else if (!this.zzcaP.equals(zzd.zzcaP)) {
                return false;
            }
            if (this.zzcaQ == null) {
                if (zzd.zzcaQ != null) {
                    return false;
                }
            } else if (!this.zzcaQ.equals(zzd.zzcaQ)) {
                return false;
            }
            if (this.zzcaR != zzd.zzcaR) {
                return false;
            }
            if (this.zzcaS == null) {
                if (zzd.zzcaS != null) {
                    return false;
                }
            } else if (!this.zzcaS.equals(zzd.zzcaS)) {
                return false;
            }
            if (!Arrays.equals(this.zzcaT, zzd.zzcaT) || this.zzcaU != zzd.zzcaU || !zzamh.equals(this.zzcaV, zzd.zzcaV) || this.zzcaW != zzd.zzcaW) {
                return false;
            }
            if (this.zzcaX == null) {
                if (zzd.zzcaX != null) {
                    return false;
                }
            } else if (!this.zzcaX.equals(zzd.zzcaX)) {
                return false;
            }
            return (this.zzcaa == null || this.zzcaa.isEmpty()) ? zzd.zzcaa == null || zzd.zzcaa.isEmpty() : this.zzcaa.equals(zzd.zzcaa);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzcaX == null ? 0 : this.zzcaX.hashCode()) + (((((((((((this.zzcaS == null ? 0 : this.zzcaS.hashCode()) + (((((this.zzcaQ == null ? 0 : this.zzcaQ.hashCode()) + (((this.zzcaP == null ? 0 : this.zzcaP.hashCode()) + (((((((((this.zzcaL == null ? 0 : this.zzcaL.hashCode()) + (((((this.zzbLB ? 1231 : 1237) + (((((((this.tag == null ? 0 : this.tag.hashCode()) + ((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.zzcaG ^ (this.zzcaG >>> 32)))) * 31) + ((int) (this.zzcaH ^ (this.zzcaH >>> 32)))) * 31) + ((int) (this.zzcaI ^ (this.zzcaI >>> 32)))) * 31)) * 31) + this.zzcaJ) * 31) + this.zzoZ) * 31)) * 31) + zzamh.hashCode(this.zzcaK)) * 31)) * 31) + Arrays.hashCode(this.zzcaM)) * 31) + Arrays.hashCode(this.zzcaN)) * 31) + Arrays.hashCode(this.zzcaO)) * 31)) * 31)) * 31) + ((int) (this.zzcaR ^ (this.zzcaR >>> 32)))) * 31)) * 31) + Arrays.hashCode(this.zzcaT)) * 31) + this.zzcaU) * 31) + zzamh.hashCode(this.zzcaV)) * 31) + ((int) (this.zzcaW ^ (this.zzcaW >>> 32)))) * 31)) * 31;
            if (!(this.zzcaa == null || this.zzcaa.isEmpty())) {
                i = this.zzcaa.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(zzamc zzamc) throws IOException {
            int i = 0;
            if (this.zzcaG != 0) {
                zzamc.zzb(1, this.zzcaG);
            }
            if (!this.tag.equals("")) {
                zzamc.zzq(2, this.tag);
            }
            if (this.zzcaK != null && this.zzcaK.length > 0) {
                for (zzamj zzamj : this.zzcaK) {
                    if (zzamj != null) {
                        zzamc.zza(3, zzamj);
                    }
                }
            }
            if (!Arrays.equals(this.zzcaM, zzamm.zzcas)) {
                zzamc.zza(6, this.zzcaM);
            }
            if (this.zzcaP != null) {
                zzamc.zza(7, this.zzcaP);
            }
            if (!Arrays.equals(this.zzcaN, zzamm.zzcas)) {
                zzamc.zza(8, this.zzcaN);
            }
            if (this.zzcaL != null) {
                zzamc.zza(9, this.zzcaL);
            }
            if (this.zzbLB) {
                zzamc.zzj(10, this.zzbLB);
            }
            if (this.zzcaJ != 0) {
                zzamc.zzE(11, this.zzcaJ);
            }
            if (this.zzoZ != 0) {
                zzamc.zzE(12, this.zzoZ);
            }
            if (!Arrays.equals(this.zzcaO, zzamm.zzcas)) {
                zzamc.zza(13, this.zzcaO);
            }
            if (!this.zzcaQ.equals("")) {
                zzamc.zzq(14, this.zzcaQ);
            }
            if (this.zzcaR != 180000) {
                zzamc.zzd(15, this.zzcaR);
            }
            if (this.zzcaS != null) {
                zzamc.zza(16, this.zzcaS);
            }
            if (this.zzcaH != 0) {
                zzamc.zzb(17, this.zzcaH);
            }
            if (!Arrays.equals(this.zzcaT, zzamm.zzcas)) {
                zzamc.zza(18, this.zzcaT);
            }
            if (this.zzcaU != 0) {
                zzamc.zzE(19, this.zzcaU);
            }
            if (this.zzcaV != null && this.zzcaV.length > 0) {
                while (i < this.zzcaV.length) {
                    zzamc.zzE(20, this.zzcaV[i]);
                    i++;
                }
            }
            if (this.zzcaI != 0) {
                zzamc.zzb(21, this.zzcaI);
            }
            if (this.zzcaW != 0) {
                zzamc.zzb(22, this.zzcaW);
            }
            if (this.zzcaX != null) {
                zzamc.zza(23, this.zzcaX);
            }
            super.writeTo(zzamc);
        }

        public zzd zzXk() {
            this.zzcaG = 0;
            this.zzcaH = 0;
            this.zzcaI = 0;
            this.tag = "";
            this.zzcaJ = 0;
            this.zzoZ = 0;
            this.zzbLB = false;
            this.zzcaK = zze.zzXl();
            this.zzcaL = null;
            this.zzcaM = zzamm.zzcas;
            this.zzcaN = zzamm.zzcas;
            this.zzcaO = zzamm.zzcas;
            this.zzcaP = null;
            this.zzcaQ = "";
            this.zzcaR = 180000;
            this.zzcaS = null;
            this.zzcaT = zzamm.zzcas;
            this.zzcaU = 0;
            this.zzcaV = zzamm.zzcal;
            this.zzcaW = 0;
            this.zzcaX = null;
            this.zzcaa = null;
            this.zzcaj = -1;
            return this;
        }

        /* renamed from: zzab */
        public zzd mergeFrom(zzamb zzamb) throws IOException {
            while (true) {
                int zzWC = zzamb.zzWC();
                int zzc;
                switch (zzWC) {
                    case 0:
                        break;
                    case 8:
                        this.zzcaG = zzamb.zzWF();
                        continue;
                    case 18:
                        this.tag = zzamb.readString();
                        continue;
                    case 26:
                        zzc = zzamm.zzc(zzamb, 26);
                        zzWC = this.zzcaK == null ? 0 : this.zzcaK.length;
                        zze[] zzeArr = new zze[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzcaK, 0, zzeArr, 0, zzWC);
                        }
                        while (zzWC < zzeArr.length - 1) {
                            zzeArr[zzWC] = new zze();
                            zzamb.zza(zzeArr[zzWC]);
                            zzamb.zzWC();
                            zzWC++;
                        }
                        zzeArr[zzWC] = new zze();
                        zzamb.zza(zzeArr[zzWC]);
                        this.zzcaK = zzeArr;
                        continue;
                    case 50:
                        this.zzcaM = zzamb.readBytes();
                        continue;
                    case 58:
                        if (this.zzcaP == null) {
                            this.zzcaP = new zza();
                        }
                        zzamb.zza(this.zzcaP);
                        continue;
                    case 66:
                        this.zzcaN = zzamb.readBytes();
                        continue;
                    case 74:
                        if (this.zzcaL == null) {
                            this.zzcaL = new zzb();
                        }
                        zzamb.zza(this.zzcaL);
                        continue;
                    case 80:
                        this.zzbLB = zzamb.zzWI();
                        continue;
                    case 88:
                        this.zzcaJ = zzamb.zzWG();
                        continue;
                    case 96:
                        this.zzoZ = zzamb.zzWG();
                        continue;
                    case 106:
                        this.zzcaO = zzamb.readBytes();
                        continue;
                    case 114:
                        this.zzcaQ = zzamb.readString();
                        continue;
                    case 120:
                        this.zzcaR = zzamb.zzWK();
                        continue;
                    case TransportMediator.KEYCODE_MEDIA_RECORD /*130*/:
                        if (this.zzcaS == null) {
                            this.zzcaS = new zzc();
                        }
                        zzamb.zza(this.zzcaS);
                        continue;
                    case 136:
                        this.zzcaH = zzamb.zzWF();
                        continue;
                    case 146:
                        this.zzcaT = zzamb.readBytes();
                        continue;
                    case 152:
                        zzWC = zzamb.zzWG();
                        switch (zzWC) {
                            case 0:
                            case 1:
                            case 2:
                                this.zzcaU = zzWC;
                                break;
                            default:
                                continue;
                        }
                    case 160:
                        zzc = zzamm.zzc(zzamb, 160);
                        zzWC = this.zzcaV == null ? 0 : this.zzcaV.length;
                        int[] iArr = new int[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzcaV, 0, iArr, 0, zzWC);
                        }
                        while (zzWC < iArr.length - 1) {
                            iArr[zzWC] = zzamb.zzWG();
                            zzamb.zzWC();
                            zzWC++;
                        }
                        iArr[zzWC] = zzamb.zzWG();
                        this.zzcaV = iArr;
                        continue;
                    case 162:
                        int zznW = zzamb.zznW(zzamb.zzWL());
                        zzc = zzamb.getPosition();
                        zzWC = 0;
                        while (zzamb.zzWQ() > 0) {
                            zzamb.zzWG();
                            zzWC++;
                        }
                        zzamb.zznY(zzc);
                        zzc = this.zzcaV == null ? 0 : this.zzcaV.length;
                        int[] iArr2 = new int[(zzWC + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzcaV, 0, iArr2, 0, zzc);
                        }
                        while (zzc < iArr2.length) {
                            iArr2[zzc] = zzamb.zzWG();
                            zzc++;
                        }
                        this.zzcaV = iArr2;
                        zzamb.zznX(zznW);
                        continue;
                    case 168:
                        this.zzcaI = zzamb.zzWF();
                        continue;
                    case 176:
                        this.zzcaW = zzamb.zzWF();
                        continue;
                    case 186:
                        if (this.zzcaX == null) {
                            this.zzcaX = new zzf();
                        }
                        zzamb.zza(this.zzcaX);
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
            if (this.zzcaG != 0) {
                zzy += zzamc.zze(1, this.zzcaG);
            }
            if (!this.tag.equals("")) {
                zzy += zzamc.zzr(2, this.tag);
            }
            if (this.zzcaK != null && this.zzcaK.length > 0) {
                i = zzy;
                for (zzamj zzamj : this.zzcaK) {
                    if (zzamj != null) {
                        i += zzamc.zzc(3, zzamj);
                    }
                }
                zzy = i;
            }
            if (!Arrays.equals(this.zzcaM, zzamm.zzcas)) {
                zzy += zzamc.zzb(6, this.zzcaM);
            }
            if (this.zzcaP != null) {
                zzy += zzamc.zzc(7, this.zzcaP);
            }
            if (!Arrays.equals(this.zzcaN, zzamm.zzcas)) {
                zzy += zzamc.zzb(8, this.zzcaN);
            }
            if (this.zzcaL != null) {
                zzy += zzamc.zzc(9, this.zzcaL);
            }
            if (this.zzbLB) {
                zzy += zzamc.zzk(10, this.zzbLB);
            }
            if (this.zzcaJ != 0) {
                zzy += zzamc.zzG(11, this.zzcaJ);
            }
            if (this.zzoZ != 0) {
                zzy += zzamc.zzG(12, this.zzoZ);
            }
            if (!Arrays.equals(this.zzcaO, zzamm.zzcas)) {
                zzy += zzamc.zzb(13, this.zzcaO);
            }
            if (!this.zzcaQ.equals("")) {
                zzy += zzamc.zzr(14, this.zzcaQ);
            }
            if (this.zzcaR != 180000) {
                zzy += zzamc.zzg(15, this.zzcaR);
            }
            if (this.zzcaS != null) {
                zzy += zzamc.zzc(16, this.zzcaS);
            }
            if (this.zzcaH != 0) {
                zzy += zzamc.zze(17, this.zzcaH);
            }
            if (!Arrays.equals(this.zzcaT, zzamm.zzcas)) {
                zzy += zzamc.zzb(18, this.zzcaT);
            }
            if (this.zzcaU != 0) {
                zzy += zzamc.zzG(19, this.zzcaU);
            }
            if (this.zzcaV != null && this.zzcaV.length > 0) {
                i = 0;
                while (i2 < this.zzcaV.length) {
                    i += zzamc.zzoc(this.zzcaV[i2]);
                    i2++;
                }
                zzy = (zzy + i) + (this.zzcaV.length * 2);
            }
            if (this.zzcaI != 0) {
                zzy += zzamc.zze(21, this.zzcaI);
            }
            if (this.zzcaW != 0) {
                zzy += zzamc.zze(22, this.zzcaW);
            }
            return this.zzcaX != null ? zzy + zzamc.zzc(23, this.zzcaX) : zzy;
        }
    }

    public static final class zze extends zzamd<zze> {
        private static volatile zze[] zzcaY;
        public String value;
        public String zzaB;

        public zze() {
            zzXm();
        }

        public static zze[] zzXl() {
            if (zzcaY == null) {
                synchronized (zzamh.zzcai) {
                    if (zzcaY == null) {
                        zzcaY = new zze[0];
                    }
                }
            }
            return zzcaY;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zze)) {
                return false;
            }
            zze zze = (zze) obj;
            if (this.zzaB == null) {
                if (zze.zzaB != null) {
                    return false;
                }
            } else if (!this.zzaB.equals(zze.zzaB)) {
                return false;
            }
            if (this.value == null) {
                if (zze.value != null) {
                    return false;
                }
            } else if (!this.value.equals(zze.value)) {
                return false;
            }
            return (this.zzcaa == null || this.zzcaa.isEmpty()) ? zze.zzcaa == null || zze.zzcaa.isEmpty() : this.zzcaa.equals(zze.zzcaa);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.value == null ? 0 : this.value.hashCode()) + (((this.zzaB == null ? 0 : this.zzaB.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (!(this.zzcaa == null || this.zzcaa.isEmpty())) {
                i = this.zzcaa.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(zzamc zzamc) throws IOException {
            if (!this.zzaB.equals("")) {
                zzamc.zzq(1, this.zzaB);
            }
            if (!this.value.equals("")) {
                zzamc.zzq(2, this.value);
            }
            super.writeTo(zzamc);
        }

        public zze zzXm() {
            this.zzaB = "";
            this.value = "";
            this.zzcaa = null;
            this.zzcaj = -1;
            return this;
        }

        /* renamed from: zzac */
        public zze mergeFrom(zzamb zzamb) throws IOException {
            while (true) {
                int zzWC = zzamb.zzWC();
                switch (zzWC) {
                    case 0:
                        break;
                    case 10:
                        this.zzaB = zzamb.readString();
                        continue;
                    case 18:
                        this.value = zzamb.readString();
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
            return !this.value.equals("") ? zzy + zzamc.zzr(2, this.value) : zzy;
        }
    }

    public static final class zzf extends zzamd<zzf> {
        public int zzcaZ;

        public zzf() {
            zzXn();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzf)) {
                return false;
            }
            zzf zzf = (zzf) obj;
            return this.zzcaZ == zzf.zzcaZ ? (this.zzcaa == null || this.zzcaa.isEmpty()) ? zzf.zzcaa == null || zzf.zzcaa.isEmpty() : this.zzcaa.equals(zzf.zzcaa) : false;
        }

        public int hashCode() {
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.zzcaZ) * 31;
            int hashCode2 = (this.zzcaa == null || this.zzcaa.isEmpty()) ? 0 : this.zzcaa.hashCode();
            return hashCode2 + hashCode;
        }

        public void writeTo(zzamc zzamc) throws IOException {
            if (this.zzcaZ != -1) {
                zzamc.zzE(1, this.zzcaZ);
            }
            super.writeTo(zzamc);
        }

        public zzf zzXn() {
            this.zzcaZ = -1;
            this.zzcaa = null;
            this.zzcaj = -1;
            return this;
        }

        /* renamed from: zzad */
        public zzf mergeFrom(zzamb zzamb) throws IOException {
            while (true) {
                int zzWC = zzamb.zzWC();
                switch (zzWC) {
                    case 0:
                        break;
                    case 8:
                        zzWC = zzamb.zzWG();
                        switch (zzWC) {
                            case -1:
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                            case 16:
                            case 17:
                                this.zzcaZ = zzWC;
                                break;
                            default:
                                continue;
                        }
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
            return this.zzcaZ != -1 ? zzy + zzamc.zzG(1, this.zzcaZ) : zzy;
        }
    }
}
