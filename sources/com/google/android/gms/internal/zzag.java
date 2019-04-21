package com.google.android.gms.internal;

import java.io.IOException;

public interface zzag {

    public static final class zza extends zzamd<zza> {
        private static volatile zza[] zzjI;
        public int type;
        public String zzjJ;
        public zza[] zzjK;
        public zza[] zzjL;
        public zza[] zzjM;
        public String zzjN;
        public String zzjO;
        public long zzjP;
        public boolean zzjQ;
        public zza[] zzjR;
        public int[] zzjS;
        public boolean zzjT;

        public zza() {
            zzQ();
        }

        public static zza[] zzP() {
            if (zzjI == null) {
                synchronized (zzamh.zzcai) {
                    if (zzjI == null) {
                        zzjI = new zza[0];
                    }
                }
            }
            return zzjI;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.type != zza.type) {
                return false;
            }
            if (this.zzjJ == null) {
                if (zza.zzjJ != null) {
                    return false;
                }
            } else if (!this.zzjJ.equals(zza.zzjJ)) {
                return false;
            }
            if (!zzamh.equals(this.zzjK, zza.zzjK) || !zzamh.equals(this.zzjL, zza.zzjL) || !zzamh.equals(this.zzjM, zza.zzjM)) {
                return false;
            }
            if (this.zzjN == null) {
                if (zza.zzjN != null) {
                    return false;
                }
            } else if (!this.zzjN.equals(zza.zzjN)) {
                return false;
            }
            if (this.zzjO == null) {
                if (zza.zzjO != null) {
                    return false;
                }
            } else if (!this.zzjO.equals(zza.zzjO)) {
                return false;
            }
            return (this.zzjP == zza.zzjP && this.zzjQ == zza.zzjQ && zzamh.equals(this.zzjR, zza.zzjR) && zzamh.equals(this.zzjS, zza.zzjS) && this.zzjT == zza.zzjT) ? (this.zzcaa == null || this.zzcaa.isEmpty()) ? zza.zzcaa == null || zza.zzcaa.isEmpty() : this.zzcaa.equals(zza.zzcaa) : false;
        }

        public int hashCode() {
            int i = 1231;
            int i2 = 0;
            int hashCode = ((((((this.zzjQ ? 1231 : 1237) + (((((this.zzjO == null ? 0 : this.zzjO.hashCode()) + (((this.zzjN == null ? 0 : this.zzjN.hashCode()) + (((((((((this.zzjJ == null ? 0 : this.zzjJ.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31)) * 31) + zzamh.hashCode(this.zzjK)) * 31) + zzamh.hashCode(this.zzjL)) * 31) + zzamh.hashCode(this.zzjM)) * 31)) * 31)) * 31) + ((int) (this.zzjP ^ (this.zzjP >>> 32)))) * 31)) * 31) + zzamh.hashCode(this.zzjR)) * 31) + zzamh.hashCode(this.zzjS)) * 31;
            if (!this.zzjT) {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (!(this.zzcaa == null || this.zzcaa.isEmpty())) {
                i2 = this.zzcaa.hashCode();
            }
            return hashCode + i2;
        }

        public void writeTo(zzamc zzamc) throws IOException {
            int i = 0;
            zzamc.zzE(1, this.type);
            if (!this.zzjJ.equals("")) {
                zzamc.zzq(2, this.zzjJ);
            }
            if (this.zzjK != null && this.zzjK.length > 0) {
                for (zzamj zzamj : this.zzjK) {
                    if (zzamj != null) {
                        zzamc.zza(3, zzamj);
                    }
                }
            }
            if (this.zzjL != null && this.zzjL.length > 0) {
                for (zzamj zzamj2 : this.zzjL) {
                    if (zzamj2 != null) {
                        zzamc.zza(4, zzamj2);
                    }
                }
            }
            if (this.zzjM != null && this.zzjM.length > 0) {
                for (zzamj zzamj22 : this.zzjM) {
                    if (zzamj22 != null) {
                        zzamc.zza(5, zzamj22);
                    }
                }
            }
            if (!this.zzjN.equals("")) {
                zzamc.zzq(6, this.zzjN);
            }
            if (!this.zzjO.equals("")) {
                zzamc.zzq(7, this.zzjO);
            }
            if (this.zzjP != 0) {
                zzamc.zzb(8, this.zzjP);
            }
            if (this.zzjT) {
                zzamc.zzj(9, this.zzjT);
            }
            if (this.zzjS != null && this.zzjS.length > 0) {
                for (int zzE : this.zzjS) {
                    zzamc.zzE(10, zzE);
                }
            }
            if (this.zzjR != null && this.zzjR.length > 0) {
                while (i < this.zzjR.length) {
                    zzamj zzamj3 = this.zzjR[i];
                    if (zzamj3 != null) {
                        zzamc.zza(11, zzamj3);
                    }
                    i++;
                }
            }
            if (this.zzjQ) {
                zzamc.zzj(12, this.zzjQ);
            }
            super.writeTo(zzamc);
        }

        public zza zzQ() {
            this.type = 1;
            this.zzjJ = "";
            this.zzjK = zzP();
            this.zzjL = zzP();
            this.zzjM = zzP();
            this.zzjN = "";
            this.zzjO = "";
            this.zzjP = 0;
            this.zzjQ = false;
            this.zzjR = zzP();
            this.zzjS = zzamm.zzcal;
            this.zzjT = false;
            this.zzcaa = null;
            this.zzcaj = -1;
            return this;
        }

        /* renamed from: zzk */
        public zza mergeFrom(zzamb zzamb) throws IOException {
            while (true) {
                int zzWC = zzamb.zzWC();
                int zzc;
                zza[] zzaArr;
                int i;
                switch (zzWC) {
                    case 0:
                        break;
                    case 8:
                        zzWC = zzamb.zzWG();
                        switch (zzWC) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                                this.type = zzWC;
                                break;
                            default:
                                continue;
                        }
                    case 18:
                        this.zzjJ = zzamb.readString();
                        continue;
                    case 26:
                        zzc = zzamm.zzc(zzamb, 26);
                        zzWC = this.zzjK == null ? 0 : this.zzjK.length;
                        zzaArr = new zza[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjK, 0, zzaArr, 0, zzWC);
                        }
                        while (zzWC < zzaArr.length - 1) {
                            zzaArr[zzWC] = new zza();
                            zzamb.zza(zzaArr[zzWC]);
                            zzamb.zzWC();
                            zzWC++;
                        }
                        zzaArr[zzWC] = new zza();
                        zzamb.zza(zzaArr[zzWC]);
                        this.zzjK = zzaArr;
                        continue;
                    case 34:
                        zzc = zzamm.zzc(zzamb, 34);
                        zzWC = this.zzjL == null ? 0 : this.zzjL.length;
                        zzaArr = new zza[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjL, 0, zzaArr, 0, zzWC);
                        }
                        while (zzWC < zzaArr.length - 1) {
                            zzaArr[zzWC] = new zza();
                            zzamb.zza(zzaArr[zzWC]);
                            zzamb.zzWC();
                            zzWC++;
                        }
                        zzaArr[zzWC] = new zza();
                        zzamb.zza(zzaArr[zzWC]);
                        this.zzjL = zzaArr;
                        continue;
                    case 42:
                        zzc = zzamm.zzc(zzamb, 42);
                        zzWC = this.zzjM == null ? 0 : this.zzjM.length;
                        zzaArr = new zza[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjM, 0, zzaArr, 0, zzWC);
                        }
                        while (zzWC < zzaArr.length - 1) {
                            zzaArr[zzWC] = new zza();
                            zzamb.zza(zzaArr[zzWC]);
                            zzamb.zzWC();
                            zzWC++;
                        }
                        zzaArr[zzWC] = new zza();
                        zzamb.zza(zzaArr[zzWC]);
                        this.zzjM = zzaArr;
                        continue;
                    case 50:
                        this.zzjN = zzamb.readString();
                        continue;
                    case 58:
                        this.zzjO = zzamb.readString();
                        continue;
                    case 64:
                        this.zzjP = zzamb.zzWF();
                        continue;
                    case 72:
                        this.zzjT = zzamb.zzWI();
                        continue;
                    case 80:
                        int zzc2 = zzamm.zzc(zzamb, 80);
                        int[] iArr = new int[zzc2];
                        i = 0;
                        zzc = 0;
                        while (i < zzc2) {
                            if (i != 0) {
                                zzamb.zzWC();
                            }
                            int zzWG = zzamb.zzWG();
                            switch (zzWG) {
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
                                    zzWC = zzc + 1;
                                    iArr[zzc] = zzWG;
                                    break;
                                default:
                                    zzWC = zzc;
                                    break;
                            }
                            i++;
                            zzc = zzWC;
                        }
                        if (zzc != 0) {
                            zzWC = this.zzjS == null ? 0 : this.zzjS.length;
                            if (zzWC != 0 || zzc != zzc2) {
                                int[] iArr2 = new int[(zzWC + zzc)];
                                if (zzWC != 0) {
                                    System.arraycopy(this.zzjS, 0, iArr2, 0, zzWC);
                                }
                                System.arraycopy(iArr, 0, iArr2, zzWC, zzc);
                                this.zzjS = iArr2;
                                break;
                            }
                            this.zzjS = iArr;
                            break;
                        }
                        continue;
                    case 82:
                        i = zzamb.zznW(zzamb.zzWL());
                        zzc = zzamb.getPosition();
                        zzWC = 0;
                        while (zzamb.zzWQ() > 0) {
                            switch (zzamb.zzWG()) {
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
                                    zzWC++;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (zzWC != 0) {
                            zzamb.zznY(zzc);
                            zzc = this.zzjS == null ? 0 : this.zzjS.length;
                            int[] iArr3 = new int[(zzWC + zzc)];
                            if (zzc != 0) {
                                System.arraycopy(this.zzjS, 0, iArr3, 0, zzc);
                            }
                            while (zzamb.zzWQ() > 0) {
                                int zzWG2 = zzamb.zzWG();
                                switch (zzWG2) {
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
                                        zzWC = zzc + 1;
                                        iArr3[zzc] = zzWG2;
                                        zzc = zzWC;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            this.zzjS = iArr3;
                        }
                        zzamb.zznX(i);
                        continue;
                    case 90:
                        zzc = zzamm.zzc(zzamb, 90);
                        zzWC = this.zzjR == null ? 0 : this.zzjR.length;
                        zzaArr = new zza[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzjR, 0, zzaArr, 0, zzWC);
                        }
                        while (zzWC < zzaArr.length - 1) {
                            zzaArr[zzWC] = new zza();
                            zzamb.zza(zzaArr[zzWC]);
                            zzamb.zzWC();
                            zzWC++;
                        }
                        zzaArr[zzWC] = new zza();
                        zzamb.zza(zzaArr[zzWC]);
                        this.zzjR = zzaArr;
                        continue;
                    case 96:
                        this.zzjQ = zzamb.zzWI();
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
            int zzy = super.zzy() + zzamc.zzG(1, this.type);
            if (!this.zzjJ.equals("")) {
                zzy += zzamc.zzr(2, this.zzjJ);
            }
            if (this.zzjK != null && this.zzjK.length > 0) {
                i = zzy;
                for (zzamj zzamj : this.zzjK) {
                    if (zzamj != null) {
                        i += zzamc.zzc(3, zzamj);
                    }
                }
                zzy = i;
            }
            if (this.zzjL != null && this.zzjL.length > 0) {
                i = zzy;
                for (zzamj zzamj2 : this.zzjL) {
                    if (zzamj2 != null) {
                        i += zzamc.zzc(4, zzamj2);
                    }
                }
                zzy = i;
            }
            if (this.zzjM != null && this.zzjM.length > 0) {
                i = zzy;
                for (zzamj zzamj22 : this.zzjM) {
                    if (zzamj22 != null) {
                        i += zzamc.zzc(5, zzamj22);
                    }
                }
                zzy = i;
            }
            if (!this.zzjN.equals("")) {
                zzy += zzamc.zzr(6, this.zzjN);
            }
            if (!this.zzjO.equals("")) {
                zzy += zzamc.zzr(7, this.zzjO);
            }
            if (this.zzjP != 0) {
                zzy += zzamc.zze(8, this.zzjP);
            }
            if (this.zzjT) {
                zzy += zzamc.zzk(9, this.zzjT);
            }
            if (this.zzjS != null && this.zzjS.length > 0) {
                int i3 = 0;
                for (int zzoc : this.zzjS) {
                    i3 += zzamc.zzoc(zzoc);
                }
                zzy = (zzy + i3) + (this.zzjS.length * 1);
            }
            if (this.zzjR != null && this.zzjR.length > 0) {
                while (i2 < this.zzjR.length) {
                    zzamj zzamj3 = this.zzjR[i2];
                    if (zzamj3 != null) {
                        zzy += zzamc.zzc(11, zzamj3);
                    }
                    i2++;
                }
            }
            return this.zzjQ ? zzy + zzamc.zzk(12, this.zzjQ) : zzy;
        }
    }
}
