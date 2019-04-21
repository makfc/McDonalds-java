package com.amap.api.mapcore.util;

/* renamed from: com.amap.api.mapcore.util.de */
public class IntArray {
    /* renamed from: a */
    public int[] f1741a;
    /* renamed from: b */
    public int f1742b;
    /* renamed from: c */
    public boolean f1743c;

    public IntArray() {
        this(true, 16);
    }

    public IntArray(boolean z, int i) {
        this.f1743c = z;
        this.f1741a = new int[i];
    }

    /* renamed from: a */
    public void mo9269a(int i) {
        int[] iArr = this.f1741a;
        if (this.f1742b == iArr.length) {
            iArr = mo9272d(Math.max(8, (int) (((float) this.f1742b) * 1.75f)));
        }
        int i2 = this.f1742b;
        this.f1742b = i2 + 1;
        iArr[i2] = i;
    }

    /* renamed from: b */
    public int mo9270b(int i) {
        if (i >= this.f1742b) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.f1742b);
        }
        int[] iArr = this.f1741a;
        int i2 = iArr[i];
        this.f1742b--;
        if (this.f1743c) {
            System.arraycopy(iArr, i + 1, iArr, i, this.f1742b - i);
        } else {
            iArr[i] = iArr[this.f1742b];
        }
        return i2;
    }

    /* renamed from: a */
    public void mo9268a() {
        this.f1742b = 0;
    }

    /* renamed from: c */
    public int[] mo9271c(int i) {
        int i2 = this.f1742b + i;
        if (i2 > this.f1741a.length) {
            mo9272d(Math.max(8, i2));
        }
        return this.f1741a;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: d */
    public int[] mo9272d(int i) {
        int[] iArr = new int[i];
        System.arraycopy(this.f1741a, 0, iArr, 0, Math.min(this.f1742b, iArr.length));
        this.f1741a = iArr;
        return iArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IntArray)) {
            return false;
        }
        IntArray intArray = (IntArray) obj;
        int i = this.f1742b;
        if (i != intArray.f1742b) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (this.f1741a[i2] != intArray.f1741a[i2]) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        if (this.f1742b == 0) {
            return "[]";
        }
        int[] iArr = this.f1741a;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('[');
        stringBuilder.append(iArr[0]);
        for (int i = 1; i < this.f1742b; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(iArr[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
