package com.google.android.gms.internal;

public final class zzamf implements Cloneable {
    private static final zzamg zzcac = new zzamg();
    private int mSize;
    private boolean zzcad;
    private int[] zzcae;
    private zzamg[] zzcaf;

    zzamf() {
        this(10);
    }

    zzamf(int i) {
        this.zzcad = false;
        int idealIntArraySize = idealIntArraySize(i);
        this.zzcae = new int[idealIntArraySize];
        this.zzcaf = new zzamg[idealIntArraySize];
        this.mSize = 0;
    }

    /* renamed from: gc */
    private void m7467gc() {
        int i = this.mSize;
        int[] iArr = this.zzcae;
        zzamg[] zzamgArr = this.zzcaf;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            zzamg zzamg = zzamgArr[i3];
            if (zzamg != zzcac) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    zzamgArr[i2] = zzamg;
                    zzamgArr[i3] = null;
                }
                i2++;
            }
        }
        this.zzcad = false;
        this.mSize = i2;
    }

    private int idealByteArraySize(int i) {
        for (int i2 = 4; i2 < 32; i2++) {
            if (i <= (1 << i2) - 12) {
                return (1 << i2) - 12;
            }
        }
        return i;
    }

    private int idealIntArraySize(int i) {
        return idealByteArraySize(i * 4) / 4;
    }

    private boolean zza(int[] iArr, int[] iArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private boolean zza(zzamg[] zzamgArr, zzamg[] zzamgArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (!zzamgArr[i2].equals(zzamgArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    private int zzom(int i) {
        int i2 = 0;
        int i3 = this.mSize - 1;
        while (i2 <= i3) {
            int i4 = (i2 + i3) >>> 1;
            int i5 = this.zzcae[i4];
            if (i5 < i) {
                i2 = i4 + 1;
            } else if (i5 <= i) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return i2 ^ -1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzamf)) {
            return false;
        }
        zzamf zzamf = (zzamf) obj;
        return size() != zzamf.size() ? false : zza(this.zzcae, zzamf.zzcae, this.mSize) && zza(this.zzcaf, zzamf.zzcaf, this.mSize);
    }

    public int hashCode() {
        if (this.zzcad) {
            m7467gc();
        }
        int i = 17;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i = (((i * 31) + this.zzcae[i2]) * 31) + this.zzcaf[i2].hashCode();
        }
        return i;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /* Access modifiers changed, original: 0000 */
    public int size() {
        if (this.zzcad) {
            m7467gc();
        }
        return this.mSize;
    }

    /* renamed from: zzWV */
    public final zzamf clone() {
        int size = size();
        zzamf zzamf = new zzamf(size);
        System.arraycopy(this.zzcae, 0, zzamf.zzcae, 0, size);
        for (int i = 0; i < size; i++) {
            if (this.zzcaf[i] != null) {
                zzamf.zzcaf[i] = (zzamg) this.zzcaf[i].clone();
            }
        }
        zzamf.mSize = size;
        return zzamf;
    }

    /* Access modifiers changed, original: 0000 */
    public void zza(int i, zzamg zzamg) {
        int zzom = zzom(i);
        if (zzom >= 0) {
            this.zzcaf[zzom] = zzamg;
            return;
        }
        zzom ^= -1;
        if (zzom >= this.mSize || this.zzcaf[zzom] != zzcac) {
            if (this.zzcad && this.mSize >= this.zzcae.length) {
                m7467gc();
                zzom = zzom(i) ^ -1;
            }
            if (this.mSize >= this.zzcae.length) {
                int idealIntArraySize = idealIntArraySize(this.mSize + 1);
                int[] iArr = new int[idealIntArraySize];
                zzamg[] zzamgArr = new zzamg[idealIntArraySize];
                System.arraycopy(this.zzcae, 0, iArr, 0, this.zzcae.length);
                System.arraycopy(this.zzcaf, 0, zzamgArr, 0, this.zzcaf.length);
                this.zzcae = iArr;
                this.zzcaf = zzamgArr;
            }
            if (this.mSize - zzom != 0) {
                System.arraycopy(this.zzcae, zzom, this.zzcae, zzom + 1, this.mSize - zzom);
                System.arraycopy(this.zzcaf, zzom, this.zzcaf, zzom + 1, this.mSize - zzom);
            }
            this.zzcae[zzom] = i;
            this.zzcaf[zzom] = zzamg;
            this.mSize++;
            return;
        }
        this.zzcae[zzom] = i;
        this.zzcaf[zzom] = zzamg;
    }

    /* Access modifiers changed, original: 0000 */
    public zzamg zzok(int i) {
        int zzom = zzom(i);
        return (zzom < 0 || this.zzcaf[zzom] == zzcac) ? null : this.zzcaf[zzom];
    }

    /* Access modifiers changed, original: 0000 */
    public zzamg zzol(int i) {
        if (this.zzcad) {
            m7467gc();
        }
        return this.zzcaf[i];
    }
}
