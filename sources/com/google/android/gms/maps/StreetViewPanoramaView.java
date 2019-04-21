package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzad;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class StreetViewPanoramaView extends FrameLayout {
    private StreetViewPanorama zzaZO;
    private final zzb zzbaa;

    static class zza implements StreetViewLifecycleDelegate {
        private final ViewGroup zzaZA;
        private final IStreetViewPanoramaViewDelegate zzbab;
        private View zzbac;

        public zza(ViewGroup viewGroup, IStreetViewPanoramaViewDelegate iStreetViewPanoramaViewDelegate) {
            this.zzbab = (IStreetViewPanoramaViewDelegate) zzaa.zzz(iStreetViewPanoramaViewDelegate);
            this.zzaZA = (ViewGroup) zzaa.zzz(viewGroup);
        }

        public void getStreetViewPanoramaAsync(final OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
            try {
                this.zzbab.getStreetViewPanoramaAsync(new com.google.android.gms.maps.internal.zzaa.zza() {
                    public void zza(IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate) throws RemoteException {
                        onStreetViewPanoramaReadyCallback.onStreetViewPanoramaReady(new StreetViewPanorama(iStreetViewPanoramaDelegate));
                    }
                });
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onCreate(Bundle bundle) {
            try {
                this.zzbab.onCreate(bundle);
                this.zzbac = (View) zze.zzx(this.zzbab.getView());
                this.zzaZA.removeAllViews();
                this.zzaZA.addView(this.zzbac);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            throw new UnsupportedOperationException("onCreateView not allowed on StreetViewPanoramaViewDelegate");
        }

        public void onDestroy() {
            try {
                this.zzbab.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroyView() {
            throw new UnsupportedOperationException("onDestroyView not allowed on StreetViewPanoramaViewDelegate");
        }

        public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            throw new UnsupportedOperationException("onInflate not allowed on StreetViewPanoramaViewDelegate");
        }

        public void onLowMemory() {
            try {
                this.zzbab.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onPause() {
            try {
                this.zzbab.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onResume() {
            try {
                this.zzbab.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onSaveInstanceState(Bundle bundle) {
            try {
                this.zzbab.onSaveInstanceState(bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onStart() {
        }

        public IStreetViewPanoramaViewDelegate zzDN() {
            return this.zzbab;
        }
    }

    static class zzb extends com.google.android.gms.dynamic.zza<zza> {
        private final Context mContext;
        private final ViewGroup zzaZE;
        private final List<OnStreetViewPanoramaReadyCallback> zzaZS = new ArrayList();
        protected zzf<zza> zzaZx;
        private final StreetViewPanoramaOptions zzbae;

        zzb(ViewGroup viewGroup, Context context, StreetViewPanoramaOptions streetViewPanoramaOptions) {
            this.zzaZE = viewGroup;
            this.mContext = context;
            this.zzbae = streetViewPanoramaOptions;
        }

        public void zzDF() {
            if (this.zzaZx != null && zzxd() == null) {
                try {
                    this.zzaZx.zza(new zza(this.zzaZE, zzad.zzaZ(this.mContext).zza(zze.zzD(this.mContext), this.zzbae)));
                    for (OnStreetViewPanoramaReadyCallback streetViewPanoramaAsync : this.zzaZS) {
                        ((zza) zzxd()).getStreetViewPanoramaAsync(streetViewPanoramaAsync);
                    }
                    this.zzaZS.clear();
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                } catch (GooglePlayServicesNotAvailableException e2) {
                }
            }
        }

        /* Access modifiers changed, original: protected */
        public void zza(zzf<zza> zzf) {
            this.zzaZx = zzf;
            zzDF();
        }
    }

    public StreetViewPanoramaView(Context context) {
        super(context);
        this.zzbaa = new zzb(this, context, null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzbaa = new zzb(this, context, null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzbaa = new zzb(this, context, null);
    }

    @Deprecated
    public final StreetViewPanorama getStreetViewPanorama() {
        if (this.zzaZO != null) {
            return this.zzaZO;
        }
        this.zzbaa.zzDF();
        if (this.zzbaa.zzxd() == null) {
            return null;
        }
        try {
            this.zzaZO = new StreetViewPanorama(((zza) this.zzbaa.zzxd()).zzDN().getStreetViewPanorama());
            return this.zzaZO;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
