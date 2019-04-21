package com.amap.api.maps2d;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.p000v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amap.api.mapcore2d.C0893aq;
import com.amap.api.mapcore2d.C0955ck;
import com.amap.api.mapcore2d.IAMapDelegate;
import com.amap.api.mapcore2d.IMapFragmentDelegate;
import com.amap.api.maps2d.model.RuntimeRemoteException;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class SupportMapFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    /* renamed from: a */
    private AMap f3373a;
    /* renamed from: b */
    private IMapFragmentDelegate f3374b;

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

    public static SupportMapFragment newInstance() {
        return newInstance(new AMapOptions());
    }

    public static SupportMapFragment newInstance(AMapOptions aMapOptions) {
        SupportMapFragment supportMapFragment = new SupportMapFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("MapOptions", aMapOptions);
        supportMapFragment.setArguments(bundle);
        return supportMapFragment;
    }

    /* Access modifiers changed, original: protected */
    public IMapFragmentDelegate getMapFragmentDelegate() {
        if (this.f3374b == null) {
            this.f3374b = new C0893aq();
        }
        this.f3374b.mo9804a(getActivity());
        return this.f3374b;
    }

    public AMap getMap() {
        String str = "getMap";
        IMapFragmentDelegate mapFragmentDelegate = getMapFragmentDelegate();
        if (mapFragmentDelegate == null) {
            return null;
        }
        try {
            IAMapDelegate a = mapFragmentDelegate.mo9802a();
            if (a == null) {
                return null;
            }
            if (this.f3373a == null) {
                this.f3373a = new AMap(a);
            }
            return this.f3373a;
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "SupportMapFragment", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        String str = "onInflate";
        try {
            getMapFragmentDelegate().mo9803a(activity, new AMapOptions(), bundle);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "SupportMapFragment", str);
        }
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("SupportMapFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "SupportMapFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "SupportMapFragment#onCreate", null);
            }
        }
        super.onCreate(bundle);
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = null;
        try {
            TraceMachine.enterMethod(this._nr_trace, "SupportMapFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "SupportMapFragment#onCreateView", null);
            }
        }
        String str = "onCreateView";
        if (bundle == null) {
            try {
                bundle = getArguments();
            } catch (RemoteException e2) {
                C0955ck.m3888a(e2, "SupportMapFragment", str);
                TraceMachine.exitMethod();
            }
        }
        view = getMapFragmentDelegate().mo9801a(layoutInflater, viewGroup, bundle);
        TraceMachine.exitMethod();
        return view;
    }

    public void onResume() {
        super.onResume();
        String str = "onResume";
        try {
            getMapFragmentDelegate().mo9807b();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "SupportMapFragment", str);
        }
    }

    public void onPause() {
        super.onPause();
        String str = "onPause";
        try {
            getMapFragmentDelegate().mo9809c();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "SupportMapFragment", str);
        }
    }

    public void onDestroyView() {
        String str = "onDestroyView";
        try {
            getMapFragmentDelegate().mo9810d();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "SupportMapFragment", str);
        }
        super.onDestroyView();
    }

    public void onDestroy() {
        String str = "onDestroy";
        try {
            getMapFragmentDelegate().mo9811e();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "SupportMapFragment", str);
        }
        super.onDestroy();
    }

    public void onLowMemory() {
        super.onLowMemory();
        String str = "onLowMemory";
        try {
            getMapFragmentDelegate().mo9812f();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "SupportMapFragment", str);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        String str = "onSaveInstanceState";
        try {
            getMapFragmentDelegate().mo9808b(bundle);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "SupportMapFragment", str);
        }
        super.onSaveInstanceState(bundle);
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }
}
