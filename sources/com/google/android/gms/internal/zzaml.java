package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

final class zzaml {
    final int tag;
    final byte[] zzcak;

    zzaml(int i, byte[] bArr) {
        this.tag = i;
        this.zzcak = bArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzaml)) {
            return false;
        }
        zzaml zzaml = (zzaml) obj;
        return this.tag == zzaml.tag && Arrays.equals(this.zzcak, zzaml.zzcak);
    }

    public int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.zzcak);
    }

    /* Access modifiers changed, original: 0000 */
    public void writeTo(zzamc zzamc) throws IOException {
        zzamc.zzog(this.tag);
        zzamc.zzR(this.zzcak);
    }

    /* Access modifiers changed, original: 0000 */
    public int zzy() {
        return (zzamc.zzoh(this.tag) + 0) + this.zzcak.length;
    }
}
