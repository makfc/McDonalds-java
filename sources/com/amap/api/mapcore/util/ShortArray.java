package com.amap.api.mapcore.util;

/* renamed from: com.amap.api.mapcore.util.di */
public class ShortArray {
    /* renamed from: a */
    public short[] f1745a;
    /* renamed from: b */
    public int f1746b;
    /* renamed from: c */
    public boolean f1747c;

    public ShortArray() {
        this(true, 16);
    }

    public ShortArray(boolean z, int i) {
        this.f1747c = z;
        this.f1745a = new short[i];
    }

    /* renamed from: a */
    public void mo9277a(short s) {
        short[] sArr = this.f1745a;
        if (this.f1746b == sArr.length) {
            sArr = mo9280d(Math.max(8, (int) (((float) this.f1746b) * 1.75f)));
        }
        int i = this.f1746b;
        this.f1746b = i + 1;
        sArr[i] = s;
    }

    /* renamed from: a */
    public short mo9275a(int i) {
        if (i < this.f1746b) {
            return this.f1745a[i];
        }
        throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.f1746b);
    }

    /* renamed from: b */
    public short mo9278b(int i) {
        if (i >= this.f1746b) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.f1746b);
        }
        short[] sArr = this.f1745a;
        short s = sArr[i];
        this.f1746b--;
        if (this.f1747c) {
            System.arraycopy(sArr, i + 1, sArr, i, this.f1746b - i);
        } else {
            sArr[i] = sArr[this.f1746b];
        }
        return s;
    }

    /* renamed from: a */
    public void mo9276a() {
        this.f1746b = 0;
    }

    /* renamed from: c */
    public short[] mo9279c(int i) {
        int i2 = this.f1746b + i;
        if (i2 > this.f1745a.length) {
            mo9280d(Math.max(8, i2));
        }
        return this.f1745a;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: d */
    public short[] mo9280d(int i) {
        short[] sArr = new short[i];
        System.arraycopy(this.f1745a, 0, sArr, 0, Math.min(this.f1746b, sArr.length));
        this.f1745a = sArr;
        return sArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ShortArray)) {
            return false;
        }
        ShortArray shortArray = (ShortArray) obj;
        int i = this.f1746b;
        if (i != shortArray.f1746b) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (this.f1745a[i2] != shortArray.f1745a[i2]) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        if (this.f1746b == 0) {
            return "[]";
        }
        short[] sArr = this.f1745a;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('[');
        stringBuilder.append(sArr[0]);
        for (int i = 1; i < this.f1746b; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(sArr[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
