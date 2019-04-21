package com.google.android.gms.internal;

import java.io.IOException;

public final class zzacl extends zzamd<zzacl> {
    public int[] zzbEA;
    public byte[][] zzbEB;
    public String[] zzbEz;

    public zzacl() {
        zzNR();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzacl)) {
            return false;
        }
        zzacl zzacl = (zzacl) obj;
        return (zzamh.equals(this.zzbEz, zzacl.zzbEz) && zzamh.equals(this.zzbEA, zzacl.zzbEA) && zzamh.zza(this.zzbEB, zzacl.zzbEB)) ? (this.zzcaa == null || this.zzcaa.isEmpty()) ? zzacl.zzcaa == null || zzacl.zzcaa.isEmpty() : this.zzcaa.equals(zzacl.zzcaa) : false;
    }

    public int hashCode() {
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + zzamh.hashCode(this.zzbEz)) * 31) + zzamh.hashCode(this.zzbEA)) * 31) + zzamh.zza(this.zzbEB)) * 31;
        int hashCode2 = (this.zzcaa == null || this.zzcaa.isEmpty()) ? 0 : this.zzcaa.hashCode();
        return hashCode2 + hashCode;
    }

    public void writeTo(zzamc zzamc) throws IOException {
        int i = 0;
        if (this.zzbEz != null && this.zzbEz.length > 0) {
            for (String str : this.zzbEz) {
                if (str != null) {
                    zzamc.zzq(1, str);
                }
            }
        }
        if (this.zzbEA != null && this.zzbEA.length > 0) {
            for (int zzE : this.zzbEA) {
                zzamc.zzE(2, zzE);
            }
        }
        if (this.zzbEB != null && this.zzbEB.length > 0) {
            while (i < this.zzbEB.length) {
                byte[] bArr = this.zzbEB[i];
                if (bArr != null) {
                    zzamc.zza(3, bArr);
                }
                i++;
            }
        }
        super.writeTo(zzamc);
    }

    public zzacl zzNR() {
        this.zzbEz = zzamm.zzcaq;
        this.zzbEA = zzamm.zzcal;
        this.zzbEB = zzamm.zzcar;
        this.zzcaa = null;
        this.zzcaj = -1;
        return this;
    }

    /* renamed from: zzO */
    public zzacl mergeFrom(zzamb zzamb) throws IOException {
        while (true) {
            int zzWC = zzamb.zzWC();
            int zzc;
            switch (zzWC) {
                case 0:
                    break;
                case 10:
                    zzc = zzamm.zzc(zzamb, 10);
                    zzWC = this.zzbEz == null ? 0 : this.zzbEz.length;
                    String[] strArr = new String[(zzc + zzWC)];
                    if (zzWC != 0) {
                        System.arraycopy(this.zzbEz, 0, strArr, 0, zzWC);
                    }
                    while (zzWC < strArr.length - 1) {
                        strArr[zzWC] = zzamb.readString();
                        zzamb.zzWC();
                        zzWC++;
                    }
                    strArr[zzWC] = zzamb.readString();
                    this.zzbEz = strArr;
                    continue;
                case 16:
                    zzc = zzamm.zzc(zzamb, 16);
                    zzWC = this.zzbEA == null ? 0 : this.zzbEA.length;
                    int[] iArr = new int[(zzc + zzWC)];
                    if (zzWC != 0) {
                        System.arraycopy(this.zzbEA, 0, iArr, 0, zzWC);
                    }
                    while (zzWC < iArr.length - 1) {
                        iArr[zzWC] = zzamb.zzWG();
                        zzamb.zzWC();
                        zzWC++;
                    }
                    iArr[zzWC] = zzamb.zzWG();
                    this.zzbEA = iArr;
                    continue;
                case 18:
                    int zznW = zzamb.zznW(zzamb.zzWL());
                    zzc = zzamb.getPosition();
                    zzWC = 0;
                    while (zzamb.zzWQ() > 0) {
                        zzamb.zzWG();
                        zzWC++;
                    }
                    zzamb.zznY(zzc);
                    zzc = this.zzbEA == null ? 0 : this.zzbEA.length;
                    int[] iArr2 = new int[(zzWC + zzc)];
                    if (zzc != 0) {
                        System.arraycopy(this.zzbEA, 0, iArr2, 0, zzc);
                    }
                    while (zzc < iArr2.length) {
                        iArr2[zzc] = zzamb.zzWG();
                        zzc++;
                    }
                    this.zzbEA = iArr2;
                    zzamb.zznX(zznW);
                    continue;
                case 26:
                    zzc = zzamm.zzc(zzamb, 26);
                    zzWC = this.zzbEB == null ? 0 : this.zzbEB.length;
                    byte[][] bArr = new byte[(zzc + zzWC)][];
                    if (zzWC != 0) {
                        System.arraycopy(this.zzbEB, 0, bArr, 0, zzWC);
                    }
                    while (zzWC < bArr.length - 1) {
                        bArr[zzWC] = zzamb.readBytes();
                        zzamb.zzWC();
                        zzWC++;
                    }
                    bArr[zzWC] = zzamb.readBytes();
                    this.zzbEB = bArr;
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
        if (this.zzbEz == null || this.zzbEz.length <= 0) {
            i = zzy;
        } else {
            i2 = 0;
            i3 = 0;
            for (String str : this.zzbEz) {
                if (str != null) {
                    i3++;
                    i2 += zzamc.zziZ(str);
                }
            }
            i = (zzy + i2) + (i3 * 1);
        }
        if (this.zzbEA != null && this.zzbEA.length > 0) {
            i3 = 0;
            for (int zzy2 : this.zzbEA) {
                i3 += zzamc.zzoc(zzy2);
            }
            i = (i + i3) + (this.zzbEA.length * 1);
        }
        if (this.zzbEB == null || this.zzbEB.length <= 0) {
            return i;
        }
        i2 = 0;
        i3 = 0;
        while (i4 < this.zzbEB.length) {
            byte[] bArr = this.zzbEB[i4];
            if (bArr != null) {
                i3++;
                i2 += zzamc.zzQ(bArr);
            }
            i4++;
        }
        return (i + i2) + (i3 * 1);
    }
}
