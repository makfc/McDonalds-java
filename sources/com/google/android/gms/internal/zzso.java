package com.google.android.gms.internal;

import java.io.IOException;

public interface zzso {

    public static final class zza extends zzamj {
        private static volatile zza[] zzbgI;
        public String name;
        public Boolean zzbgJ;
        public Boolean zzbgK;

        public zza() {
            zzGX();
        }

        public static zza[] zzGW() {
            if (zzbgI == null) {
                synchronized (zzamh.zzcai) {
                    if (zzbgI == null) {
                        zzbgI = new zza[0];
                    }
                }
            }
            return zzbgI;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.name == null) {
                if (zza.name != null) {
                    return false;
                }
            } else if (!this.name.equals(zza.name)) {
                return false;
            }
            if (this.zzbgJ == null) {
                if (zza.zzbgJ != null) {
                    return false;
                }
            } else if (!this.zzbgJ.equals(zza.zzbgJ)) {
                return false;
            }
            return this.zzbgK == null ? zza.zzbgK == null : this.zzbgK.equals(zza.zzbgK);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbgJ == null ? 0 : this.zzbgJ.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (this.zzbgK != null) {
                i = this.zzbgK.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(zzamc zzamc) throws IOException {
            if (this.name != null) {
                zzamc.zzq(1, this.name);
            }
            if (this.zzbgJ != null) {
                zzamc.zzj(2, this.zzbgJ.booleanValue());
            }
            if (this.zzbgK != null) {
                zzamc.zzj(3, this.zzbgK.booleanValue());
            }
            super.writeTo(zzamc);
        }

        public zza zzGX() {
            this.name = null;
            this.zzbgJ = null;
            this.zzbgK = null;
            this.zzcaj = -1;
            return this;
        }

        /* Access modifiers changed, original: protected */
        public int zzy() {
            int zzy = super.zzy();
            if (this.name != null) {
                zzy += zzamc.zzr(1, this.name);
            }
            if (this.zzbgJ != null) {
                zzy += zzamc.zzk(2, this.zzbgJ.booleanValue());
            }
            return this.zzbgK != null ? zzy + zzamc.zzk(3, this.zzbgK.booleanValue()) : zzy;
        }

        /* renamed from: zzz */
        public zza mergeFrom(zzamb zzamb) throws IOException {
            while (true) {
                int zzWC = zzamb.zzWC();
                switch (zzWC) {
                    case 0:
                        break;
                    case 10:
                        this.name = zzamb.readString();
                        continue;
                    case 16:
                        this.zzbgJ = Boolean.valueOf(zzamb.zzWI());
                        continue;
                    case 24:
                        this.zzbgK = Boolean.valueOf(zzamb.zzWI());
                        continue;
                    default:
                        if (!zzamm.zzb(zzamb, zzWC)) {
                            break;
                        }
                        continue;
                }
            }
            return this;
        }
    }

    public static final class zzb extends zzamj {
        public String zzbbK;
        public Long zzbgL;
        public Integer zzbgM;
        public zzc[] zzbgN;
        public zza[] zzbgO;
        public com.google.android.gms.internal.zzsn.zza[] zzbgP;

        public zzb() {
            zzGY();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            if (this.zzbgL == null) {
                if (zzb.zzbgL != null) {
                    return false;
                }
            } else if (!this.zzbgL.equals(zzb.zzbgL)) {
                return false;
            }
            if (this.zzbbK == null) {
                if (zzb.zzbbK != null) {
                    return false;
                }
            } else if (!this.zzbbK.equals(zzb.zzbbK)) {
                return false;
            }
            if (this.zzbgM == null) {
                if (zzb.zzbgM != null) {
                    return false;
                }
            } else if (!this.zzbgM.equals(zzb.zzbgM)) {
                return false;
            }
            return !zzamh.equals(this.zzbgN, zzb.zzbgN) ? false : !zzamh.equals(this.zzbgO, zzb.zzbgO) ? false : zzamh.equals(this.zzbgP, zzb.zzbgP);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbbK == null ? 0 : this.zzbbK.hashCode()) + (((this.zzbgL == null ? 0 : this.zzbgL.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (this.zzbgM != null) {
                i = this.zzbgM.hashCode();
            }
            return ((((((hashCode + i) * 31) + zzamh.hashCode(this.zzbgN)) * 31) + zzamh.hashCode(this.zzbgO)) * 31) + zzamh.hashCode(this.zzbgP);
        }

        public void writeTo(zzamc zzamc) throws IOException {
            int i = 0;
            if (this.zzbgL != null) {
                zzamc.zzb(1, this.zzbgL.longValue());
            }
            if (this.zzbbK != null) {
                zzamc.zzq(2, this.zzbbK);
            }
            if (this.zzbgM != null) {
                zzamc.zzE(3, this.zzbgM.intValue());
            }
            if (this.zzbgN != null && this.zzbgN.length > 0) {
                for (zzamj zzamj : this.zzbgN) {
                    if (zzamj != null) {
                        zzamc.zza(4, zzamj);
                    }
                }
            }
            if (this.zzbgO != null && this.zzbgO.length > 0) {
                for (zzamj zzamj2 : this.zzbgO) {
                    if (zzamj2 != null) {
                        zzamc.zza(5, zzamj2);
                    }
                }
            }
            if (this.zzbgP != null && this.zzbgP.length > 0) {
                while (i < this.zzbgP.length) {
                    zzamj zzamj3 = this.zzbgP[i];
                    if (zzamj3 != null) {
                        zzamc.zza(6, zzamj3);
                    }
                    i++;
                }
            }
            super.writeTo(zzamc);
        }

        /* renamed from: zzA */
        public zzb mergeFrom(zzamb zzamb) throws IOException {
            while (true) {
                int zzWC = zzamb.zzWC();
                int zzc;
                switch (zzWC) {
                    case 0:
                        break;
                    case 8:
                        this.zzbgL = Long.valueOf(zzamb.zzWF());
                        continue;
                    case 18:
                        this.zzbbK = zzamb.readString();
                        continue;
                    case 24:
                        this.zzbgM = Integer.valueOf(zzamb.zzWG());
                        continue;
                    case 34:
                        zzc = zzamm.zzc(zzamb, 34);
                        zzWC = this.zzbgN == null ? 0 : this.zzbgN.length;
                        zzc[] zzcArr = new zzc[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzbgN, 0, zzcArr, 0, zzWC);
                        }
                        while (zzWC < zzcArr.length - 1) {
                            zzcArr[zzWC] = new zzc();
                            zzamb.zza(zzcArr[zzWC]);
                            zzamb.zzWC();
                            zzWC++;
                        }
                        zzcArr[zzWC] = new zzc();
                        zzamb.zza(zzcArr[zzWC]);
                        this.zzbgN = zzcArr;
                        continue;
                    case 42:
                        zzc = zzamm.zzc(zzamb, 42);
                        zzWC = this.zzbgO == null ? 0 : this.zzbgO.length;
                        zza[] zzaArr = new zza[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzbgO, 0, zzaArr, 0, zzWC);
                        }
                        while (zzWC < zzaArr.length - 1) {
                            zzaArr[zzWC] = new zza();
                            zzamb.zza(zzaArr[zzWC]);
                            zzamb.zzWC();
                            zzWC++;
                        }
                        zzaArr[zzWC] = new zza();
                        zzamb.zza(zzaArr[zzWC]);
                        this.zzbgO = zzaArr;
                        continue;
                    case 50:
                        zzc = zzamm.zzc(zzamb, 50);
                        zzWC = this.zzbgP == null ? 0 : this.zzbgP.length;
                        com.google.android.gms.internal.zzsn.zza[] zzaArr2 = new com.google.android.gms.internal.zzsn.zza[(zzc + zzWC)];
                        if (zzWC != 0) {
                            System.arraycopy(this.zzbgP, 0, zzaArr2, 0, zzWC);
                        }
                        while (zzWC < zzaArr2.length - 1) {
                            zzaArr2[zzWC] = new com.google.android.gms.internal.zzsn.zza();
                            zzamb.zza(zzaArr2[zzWC]);
                            zzamb.zzWC();
                            zzWC++;
                        }
                        zzaArr2[zzWC] = new com.google.android.gms.internal.zzsn.zza();
                        zzamb.zza(zzaArr2[zzWC]);
                        this.zzbgP = zzaArr2;
                        continue;
                    default:
                        if (!zzamm.zzb(zzamb, zzWC)) {
                            break;
                        }
                        continue;
                }
            }
            return this;
        }

        public zzb zzGY() {
            this.zzbgL = null;
            this.zzbbK = null;
            this.zzbgM = null;
            this.zzbgN = zzc.zzGZ();
            this.zzbgO = zza.zzGW();
            this.zzbgP = com.google.android.gms.internal.zzsn.zza.zzGM();
            this.zzcaj = -1;
            return this;
        }

        /* Access modifiers changed, original: protected */
        public int zzy() {
            int i;
            int i2 = 0;
            int zzy = super.zzy();
            if (this.zzbgL != null) {
                zzy += zzamc.zze(1, this.zzbgL.longValue());
            }
            if (this.zzbbK != null) {
                zzy += zzamc.zzr(2, this.zzbbK);
            }
            if (this.zzbgM != null) {
                zzy += zzamc.zzG(3, this.zzbgM.intValue());
            }
            if (this.zzbgN != null && this.zzbgN.length > 0) {
                i = zzy;
                for (zzamj zzamj : this.zzbgN) {
                    if (zzamj != null) {
                        i += zzamc.zzc(4, zzamj);
                    }
                }
                zzy = i;
            }
            if (this.zzbgO != null && this.zzbgO.length > 0) {
                i = zzy;
                for (zzamj zzamj2 : this.zzbgO) {
                    if (zzamj2 != null) {
                        i += zzamc.zzc(5, zzamj2);
                    }
                }
                zzy = i;
            }
            if (this.zzbgP != null && this.zzbgP.length > 0) {
                while (i2 < this.zzbgP.length) {
                    zzamj zzamj3 = this.zzbgP[i2];
                    if (zzamj3 != null) {
                        zzy += zzamc.zzc(6, zzamj3);
                    }
                    i2++;
                }
            }
            return zzy;
        }
    }

    public static final class zzc extends zzamj {
        private static volatile zzc[] zzbgQ;
        public String value;
        public String zzaB;

        public zzc() {
            zzHa();
        }

        public static zzc[] zzGZ() {
            if (zzbgQ == null) {
                synchronized (zzamh.zzcai) {
                    if (zzbgQ == null) {
                        zzbgQ = new zzc[0];
                    }
                }
            }
            return zzbgQ;
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
            return this.value == null ? zzc.value == null : this.value.equals(zzc.value);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzaB == null ? 0 : this.zzaB.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31;
            if (this.value != null) {
                i = this.value.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(zzamc zzamc) throws IOException {
            if (this.zzaB != null) {
                zzamc.zzq(1, this.zzaB);
            }
            if (this.value != null) {
                zzamc.zzq(2, this.value);
            }
            super.writeTo(zzamc);
        }

        /* renamed from: zzB */
        public zzc mergeFrom(zzamb zzamb) throws IOException {
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
                        if (!zzamm.zzb(zzamb, zzWC)) {
                            break;
                        }
                        continue;
                }
            }
            return this;
        }

        public zzc zzHa() {
            this.zzaB = null;
            this.value = null;
            this.zzcaj = -1;
            return this;
        }

        /* Access modifiers changed, original: protected */
        public int zzy() {
            int zzy = super.zzy();
            if (this.zzaB != null) {
                zzy += zzamc.zzr(1, this.zzaB);
            }
            return this.value != null ? zzy + zzamc.zzr(2, this.value) : zzy;
        }
    }
}
