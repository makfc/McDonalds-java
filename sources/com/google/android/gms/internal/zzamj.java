package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzamj {
    protected volatile int zzcaj = -1;

    public static final <T extends zzamj> T mergeFrom(T t, byte[] bArr) throws zzami {
        return mergeFrom(t, bArr, 0, bArr.length);
    }

    public static final <T extends zzamj> T mergeFrom(T t, byte[] bArr, int i, int i2) throws zzami {
        try {
            zzamb zza = zzamb.zza(bArr, i, i2);
            t.mergeFrom(zza);
            zza.zznT(0);
            return t;
        } catch (zzami e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    public static final void toByteArray(zzamj zzamj, byte[] bArr, int i, int i2) {
        try {
            zzamc zzb = zzamc.zzb(bArr, i, i2);
            zzamj.writeTo(zzb);
            zzb.zzWU();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public static final byte[] toByteArray(zzamj zzamj) {
        byte[] bArr = new byte[zzamj.getSerializedSize()];
        toByteArray(zzamj, bArr, 0, bArr.length);
        return bArr;
    }

    public zzamj clone() throws CloneNotSupportedException {
        return (zzamj) super.clone();
    }

    public int getCachedSize() {
        if (this.zzcaj < 0) {
            getSerializedSize();
        }
        return this.zzcaj;
    }

    public int getSerializedSize() {
        int zzy = zzy();
        this.zzcaj = zzy;
        return zzy;
    }

    public abstract zzamj mergeFrom(zzamb zzamb) throws IOException;

    public String toString() {
        return zzamk.zzf(this);
    }

    public void writeTo(zzamc zzamc) throws IOException {
    }

    /* Access modifiers changed, original: protected */
    public int zzy() {
        return 0;
    }
}
