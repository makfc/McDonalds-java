package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.zzf;
import com.google.android.gms.location.places.zzf.zza;

public class zzp implements PlacePhotoMetadata {
    private int mIndex;
    private final int zzGB;
    private final int zzGC;
    private final String zzaYh;
    private final CharSequence zzaYi;

    /* renamed from: com.google.android.gms.location.places.internal.zzp$1 */
    class C23171 extends zza<zze> {
        final /* synthetic */ int zzaYj;
        final /* synthetic */ int zzaYk;
        final /* synthetic */ zzp zzaYl;

        /* Access modifiers changed, original: protected */
        public void zza(zze zze) throws RemoteException {
            zze.zza(new zzf((zza) this), this.zzaYl.zzaYh, this.zzaYj, this.zzaYk, this.zzaYl.mIndex);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzp)) {
            return false;
        }
        zzp zzp = (zzp) obj;
        return zzp.zzGB == this.zzGB && zzp.zzGC == this.zzGC && zzz.equal(zzp.zzaYh, this.zzaYh) && zzz.equal(zzp.zzaYi, this.zzaYi);
    }

    public int hashCode() {
        return zzz.hashCode(Integer.valueOf(this.zzGB), Integer.valueOf(this.zzGC), this.zzaYh, this.zzaYi);
    }
}
