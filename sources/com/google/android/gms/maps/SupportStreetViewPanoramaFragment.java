package com.google.android.gms.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.p000v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
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
public class SupportStreetViewPanoramaFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    private final zzb zzbah = new zzb(this);

    static class zza implements StreetViewLifecycleDelegate {
        private final Fragment zzaCl;
        private final IStreetViewPanoramaFragmentDelegate zzaZP;

        public zza(Fragment fragment, IStreetViewPanoramaFragmentDelegate iStreetViewPanoramaFragmentDelegate) {
            this.zzaZP = (IStreetViewPanoramaFragmentDelegate) zzaa.zzz(iStreetViewPanoramaFragmentDelegate);
            this.zzaCl = (Fragment) zzaa.zzz(fragment);
        }

        public void getStreetViewPanoramaAsync(final OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
            try {
                this.zzaZP.getStreetViewPanoramaAsync(new com.google.android.gms.maps.internal.zzaa.zza() {
                    public void zza(IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate) throws RemoteException {
                        onStreetViewPanoramaReadyCallback.onStreetViewPanoramaReady(new StreetViewPanorama(iStreetViewPanoramaDelegate));
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
            Bundle arguments = this.zzaCl.getArguments();
            if (arguments != null && arguments.containsKey("StreetViewPanoramaOptions")) {
                zzac.zza(bundle, "StreetViewPanoramaOptions", arguments.getParcelable("StreetViewPanoramaOptions"));
            }
            this.zzaZP.onCreate(bundle);
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            try {
                return (View) zze.zzx(this.zzaZP.onCreateView(zze.zzD(layoutInflater), zze.zzD(viewGroup), bundle));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroy() {
            try {
                this.zzaZP.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroyView() {
            try {
                this.zzaZP.onDestroyView();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            try {
                this.zzaZP.onInflate(zze.zzD(activity), null, bundle2);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onLowMemory() {
            try {
                this.zzaZP.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onPause() {
            try {
                this.zzaZP.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onResume() {
            try {
                this.zzaZP.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onSaveInstanceState(Bundle bundle) {
            try {
                this.zzaZP.onSaveInstanceState(bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onStart() {
        }
    }

    static class zzb extends com.google.android.gms.dynamic.zza<zza> {
        private Activity mActivity;
        private final Fragment zzaCl;
        private final List<OnStreetViewPanoramaReadyCallback> zzaZS = new ArrayList();
        protected zzf<zza> zzaZx;

        zzb(Fragment fragment) {
            this.zzaCl = fragment;
        }

        private void setActivity(Activity activity) {
            this.mActivity = activity;
            zzDF();
        }

        public void zzDF() {
            if (this.mActivity != null && this.zzaZx != null && zzxd() == null) {
                try {
                    MapsInitializer.initialize(this.mActivity);
                    this.zzaZx.zza(new zza(this.zzaCl, zzad.zzaZ(this.mActivity).zzB(zze.zzD(this.mActivity))));
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

    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.zzbah.setActivity(activity);
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("SupportStreetViewPanoramaFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "SupportStreetViewPanoramaFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "SupportStreetViewPanoramaFragment#onCreate", null);
            }
        }
        super.onCreate(bundle);
        this.zzbah.onCreate(bundle);
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "SupportStreetViewPanoramaFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "SupportStreetViewPanoramaFragment#onCreateView", null);
            }
        }
        View onCreateView = this.zzbah.onCreateView(layoutInflater, viewGroup, bundle);
        TraceMachine.exitMethod();
        return onCreateView;
    }

    public void onDestroy() {
        this.zzbah.onDestroy();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.zzbah.onDestroyView();
        super.onDestroyView();
    }

    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        this.zzbah.setActivity(activity);
        this.zzbah.onInflate(activity, new Bundle(), bundle);
    }

    public void onLowMemory() {
        this.zzbah.onLowMemory();
        super.onLowMemory();
    }

    public void onPause() {
        this.zzbah.onPause();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.zzbah.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.zzbah.onSaveInstanceState(bundle);
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
