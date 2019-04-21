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
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzad;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class MapView extends FrameLayout {
    private GoogleMap zzaZt;
    private final zzb zzaZz;

    static class zza implements MapLifecycleDelegate {
        private final ViewGroup zzaZA;
        private final IMapViewDelegate zzaZB;
        private View zzaZC;

        public zza(ViewGroup viewGroup, IMapViewDelegate iMapViewDelegate) {
            this.zzaZB = (IMapViewDelegate) zzaa.zzz(iMapViewDelegate);
            this.zzaZA = (ViewGroup) zzaa.zzz(viewGroup);
        }

        public void getMapAsync(final OnMapReadyCallback onMapReadyCallback) {
            try {
                this.zzaZB.getMapAsync(new com.google.android.gms.maps.internal.zzo.zza() {
                    public void zza(IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException {
                        onMapReadyCallback.onMapReady(new GoogleMap(iGoogleMapDelegate));
                    }
                });
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onCreate(Bundle bundle) {
            try {
                this.zzaZB.onCreate(bundle);
                this.zzaZC = (View) zze.zzx(this.zzaZB.getView());
                this.zzaZA.removeAllViews();
                this.zzaZA.addView(this.zzaZC);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
        }

        public void onDestroy() {
            try {
                this.zzaZB.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroyView() {
            throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
        }

        public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
        }

        public void onLowMemory() {
            try {
                this.zzaZB.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onPause() {
            try {
                this.zzaZB.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onResume() {
            try {
                this.zzaZB.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onSaveInstanceState(Bundle bundle) {
            try {
                this.zzaZB.onSaveInstanceState(bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onStart() {
        }

        public IMapViewDelegate zzDH() {
            return this.zzaZB;
        }
    }

    static class zzb extends com.google.android.gms.dynamic.zza<zza> {
        private final Context mContext;
        private final ViewGroup zzaZE;
        private final GoogleMapOptions zzaZF;
        protected zzf<zza> zzaZx;
        private final List<OnMapReadyCallback> zzaZy = new ArrayList();

        zzb(ViewGroup viewGroup, Context context, GoogleMapOptions googleMapOptions) {
            this.zzaZE = viewGroup;
            this.mContext = context;
            this.zzaZF = googleMapOptions;
        }

        public void zzDF() {
            if (this.zzaZx != null && zzxd() == null) {
                try {
                    MapsInitializer.initialize(this.mContext);
                    IMapViewDelegate zza = zzad.zzaZ(this.mContext).zza(zze.zzD(this.mContext), this.zzaZF);
                    if (zza != null) {
                        this.zzaZx.zza(new zza(this.zzaZE, zza));
                        for (OnMapReadyCallback mapAsync : this.zzaZy) {
                            ((zza) zzxd()).getMapAsync(mapAsync);
                        }
                        this.zzaZy.clear();
                    }
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

    public MapView(Context context) {
        super(context);
        this.zzaZz = new zzb(this, context, null);
        zzDG();
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzaZz = new zzb(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        zzDG();
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzaZz = new zzb(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        zzDG();
    }

    private void zzDG() {
        setClickable(true);
    }

    @Deprecated
    public final GoogleMap getMap() {
        if (this.zzaZt != null) {
            return this.zzaZt;
        }
        this.zzaZz.zzDF();
        if (this.zzaZz.zzxd() == null) {
            return null;
        }
        try {
            this.zzaZt = new GoogleMap(((zza) this.zzaZz.zzxd()).zzDH().getMap());
            return this.zzaZt;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
