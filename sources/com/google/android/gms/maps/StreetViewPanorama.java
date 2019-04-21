package com.google.android.gms.maps;

import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.zzw;
import com.google.android.gms.maps.internal.zzx.zza;
import com.google.android.gms.maps.internal.zzy;
import com.google.android.gms.maps.internal.zzz;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

public class StreetViewPanorama {
    private final IStreetViewPanoramaDelegate zzaZH;

    /* renamed from: com.google.android.gms.maps.StreetViewPanorama$1 */
    class C23441 extends zza {
        final /* synthetic */ OnStreetViewPanoramaChangeListener zzaZI;

        public void onStreetViewPanoramaChange(StreetViewPanoramaLocation streetViewPanoramaLocation) {
            this.zzaZI.onStreetViewPanoramaChange(streetViewPanoramaLocation);
        }
    }

    /* renamed from: com.google.android.gms.maps.StreetViewPanorama$2 */
    class C23452 extends zzw.zza {
        final /* synthetic */ OnStreetViewPanoramaCameraChangeListener zzaZK;

        public void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera streetViewPanoramaCamera) {
            this.zzaZK.onStreetViewPanoramaCameraChange(streetViewPanoramaCamera);
        }
    }

    /* renamed from: com.google.android.gms.maps.StreetViewPanorama$3 */
    class C23463 extends zzy.zza {
        final /* synthetic */ OnStreetViewPanoramaClickListener zzaZL;

        public void onStreetViewPanoramaClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
            this.zzaZL.onStreetViewPanoramaClick(streetViewPanoramaOrientation);
        }
    }

    /* renamed from: com.google.android.gms.maps.StreetViewPanorama$4 */
    class C23474 extends zzz.zza {
        final /* synthetic */ OnStreetViewPanoramaLongClickListener zzaZM;

        public void onStreetViewPanoramaLongClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
            this.zzaZM.onStreetViewPanoramaLongClick(streetViewPanoramaOrientation);
        }
    }

    public interface OnStreetViewPanoramaCameraChangeListener {
        void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera streetViewPanoramaCamera);
    }

    public interface OnStreetViewPanoramaChangeListener {
        void onStreetViewPanoramaChange(StreetViewPanoramaLocation streetViewPanoramaLocation);
    }

    public interface OnStreetViewPanoramaClickListener {
        void onStreetViewPanoramaClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation);
    }

    public interface OnStreetViewPanoramaLongClickListener {
        void onStreetViewPanoramaLongClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation);
    }

    protected StreetViewPanorama(IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate) {
        this.zzaZH = (IStreetViewPanoramaDelegate) zzaa.zzz(iStreetViewPanoramaDelegate);
    }
}
