package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.autonavi.amap.mapcore.interfaces.CameraAnimator;
import com.google.android.gms.common.images.ImageManager.OnImageLoadedListener;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.internal.zzph;
import com.google.android.gms.internal.zzpi;
import com.google.android.gms.internal.zzpj;
import java.lang.ref.WeakReference;

public abstract class zza {
    final zza zzapK;
    protected int zzapM;
    private boolean zzapO;
    private boolean zzapQ;

    static final class zza {
        public final Uri uri;

        public zza(Uri uri) {
            this.uri = uri;
        }

        public boolean equals(Object obj) {
            return !(obj instanceof zza) ? false : this == obj ? true : zzz.equal(((zza) obj).uri, this.uri);
        }

        public int hashCode() {
            return zzz.hashCode(this.uri);
        }
    }

    public static final class zzb extends zza {
        private WeakReference<ImageView> zzapR;

        private void zza(ImageView imageView, Drawable drawable, boolean z, boolean z2, boolean z3) {
            Object obj = (z2 || z3) ? null : 1;
            if (obj != null && (imageView instanceof zzpi)) {
                int zzte = ((zzpi) imageView).zzte();
                if (this.zzapM != 0 && zzte == this.zzapM) {
                    return;
                }
            }
            boolean zzb = zzb(z, z2);
            Drawable zza = zzb ? zza(imageView.getDrawable(), drawable) : drawable;
            imageView.setImageDrawable(zza);
            if (imageView instanceof zzpi) {
                zzpi zzpi = (zzpi) imageView;
                zzpi.zzn(z3 ? this.zzapK.uri : null);
                zzpi.zzbX(obj != null ? this.zzapM : 0);
            }
            if (zzb) {
                ((zzph) zza).startTransition(CameraAnimator.DEFAULT_DURATION);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zzb)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            ImageView imageView = (ImageView) this.zzapR.get();
            ImageView imageView2 = (ImageView) ((zzb) obj).zzapR.get();
            boolean z = (imageView2 == null || imageView == null || !zzz.equal(imageView2, imageView)) ? false : true;
            return z;
        }

        public int hashCode() {
            return 0;
        }

        /* Access modifiers changed, original: protected */
        public void zza(Drawable drawable, boolean z, boolean z2, boolean z3) {
            ImageView imageView = (ImageView) this.zzapR.get();
            if (imageView != null) {
                zza(imageView, drawable, z, z2, z3);
            }
        }
    }

    public static final class zzc extends zza {
        private WeakReference<OnImageLoadedListener> zzapS;

        public boolean equals(Object obj) {
            if (!(obj instanceof zzc)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzc zzc = (zzc) obj;
            OnImageLoadedListener onImageLoadedListener = (OnImageLoadedListener) this.zzapS.get();
            OnImageLoadedListener onImageLoadedListener2 = (OnImageLoadedListener) zzc.zzapS.get();
            boolean z = onImageLoadedListener2 != null && onImageLoadedListener != null && zzz.equal(onImageLoadedListener2, onImageLoadedListener) && zzz.equal(zzc.zzapK, this.zzapK);
            return z;
        }

        public int hashCode() {
            return zzz.hashCode(this.zzapK);
        }

        /* Access modifiers changed, original: protected */
        public void zza(Drawable drawable, boolean z, boolean z2, boolean z3) {
            if (!z2) {
                OnImageLoadedListener onImageLoadedListener = (OnImageLoadedListener) this.zzapS.get();
                if (onImageLoadedListener != null) {
                    onImageLoadedListener.onImageLoaded(this.zzapK.uri, drawable, z3);
                }
            }
        }
    }

    private Drawable zza(Context context, zzpj zzpj, int i) {
        return context.getResources().getDrawable(i);
    }

    /* Access modifiers changed, original: protected */
    public zzph zza(Drawable drawable, Drawable drawable2) {
        if (drawable == null) {
            drawable = null;
        } else if (drawable instanceof zzph) {
            drawable = ((zzph) drawable).zztc();
        }
        return new zzph(drawable, drawable2);
    }

    /* Access modifiers changed, original: 0000 */
    public void zza(Context context, Bitmap bitmap, boolean z) {
        com.google.android.gms.common.internal.zzb.zzv(bitmap);
        zza(new BitmapDrawable(context.getResources(), bitmap), z, false, true);
    }

    /* Access modifiers changed, original: 0000 */
    public void zza(Context context, zzpj zzpj) {
        if (this.zzapQ) {
            zza(null, false, true, false);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zza(Context context, zzpj zzpj, boolean z) {
        Drawable drawable = null;
        if (this.zzapM != 0) {
            drawable = zza(context, zzpj, this.zzapM);
        }
        zza(drawable, z, false, false);
    }

    public abstract void zza(Drawable drawable, boolean z, boolean z2, boolean z3);

    /* Access modifiers changed, original: protected */
    public boolean zzb(boolean z, boolean z2) {
        return (!this.zzapO || z2 || z) ? false : true;
    }
}
