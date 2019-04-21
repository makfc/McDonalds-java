package com.google.android.gms.maps;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzac;
import com.google.android.gms.maps.internal.zzad;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.List;

@Instrumented
@TargetApi(11)
public class MapFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    private final zzb zzaZs = new zzb(this);

    static class zza implements MapLifecycleDelegate {
        private final Fragment zzaCi;
        private final IMapFragmentDelegate zzaZu;

        public zza(Fragment fragment, IMapFragmentDelegate iMapFragmentDelegate) {
            this.zzaZu = (IMapFragmentDelegate) zzaa.zzz(iMapFragmentDelegate);
            this.zzaCi = (Fragment) zzaa.zzz(fragment);
        }

        public void getMapAsync(final OnMapReadyCallback onMapReadyCallback) {
            try {
                this.zzaZu.getMapAsync(new com.google.android.gms.maps.internal.zzo.zza() {
                    public void zza(IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException {
                        onMapReadyCallback.onMapReady(new GoogleMap(iGoogleMapDelegate));
                    }
                });
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onCreate(Bundle bundle) {
            if (bundle == null) {
                try {
                    bundle = new Bundle();
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            }
            Bundle arguments = this.zzaCi.getArguments();
            if (arguments != null && arguments.containsKey("MapOptions")) {
                zzac.zza(bundle, "MapOptions", arguments.getParcelable("MapOptions"));
            }
            this.zzaZu.onCreate(bundle);
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            try {
                return (View) zze.zzx(this.zzaZu.onCreateView(zze.zzD(layoutInflater), zze.zzD(viewGroup), bundle));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroy() {
            try {
                this.zzaZu.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroyView() {
            try {
                this.zzaZu.onDestroyView();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            try {
                this.zzaZu.onInflate(zze.zzD(activity), (GoogleMapOptions) bundle.getParcelable("MapOptions"), bundle2);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onLowMemory() {
            try {
                this.zzaZu.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onPause() {
            try {
                this.zzaZu.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onResume() {
            try {
                this.zzaZu.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onSaveInstanceState(Bundle bundle) {
            try {
                this.zzaZu.onSaveInstanceState(bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onStart() {
        }
    }

    static class zzb extends com.google.android.gms.dynamic.zza<zza> {
        private Activity mActivity;
        private final Fragment zzaCi;
        protected zzf<zza> zzaZx;
        private final List<OnMapReadyCallback> zzaZy = new ArrayList();

        zzb(Fragment fragment) {
            this.zzaCi = fragment;
        }

        private void setActivity(Activity activity) {
            this.mActivity = activity;
            zzDF();
        }

        public void zzDF() {
            if (this.mActivity != null && this.zzaZx != null && zzxd() == null) {
                try {
                    MapsInitializer.initialize(this.mActivity);
                    IMapFragmentDelegate zzA = zzad.zzaZ(this.mActivity).zzA(zze.zzD(this.mActivity));
                    if (zzA != null) {
                        this.zzaZx.zza(new zza(this.zzaCi, zzA));
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

    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MapFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.zzaZs.setActivity(activity);
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("MapFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "MapFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "MapFragment#onCreate", null);
            }
        }
        super.onCreate(bundle);
        this.zzaZs.onCreate(bundle);
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "MapFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "MapFragment#onCreateView", null);
            }
        }
        View onCreateView = this.zzaZs.onCreateView(layoutInflater, viewGroup, bundle);
        onCreateView.setClickable(true);
        TraceMachine.exitMethod();
        return onCreateView;
    }

    public void onDestroy() {
        this.zzaZs.onDestroy();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.zzaZs.onDestroyView();
        super.onDestroyView();
    }

    @SuppressLint({"NewApi"})
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        this.zzaZs.setActivity(activity);
        GoogleMapOptions createFromAttributes = GoogleMapOptions.createFromAttributes(activity, attributeSet);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("MapOptions", createFromAttributes);
        this.zzaZs.onInflate(activity, bundle2, bundle);
    }

    public void onLowMemory() {
        this.zzaZs.onLowMemory();
        super.onLowMemory();
    }

    public void onPause() {
        this.zzaZs.onPause();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.zzaZs.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MapFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.zzaZs.onSaveInstanceState(bundle);
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }
}
