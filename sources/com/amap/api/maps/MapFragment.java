package com.amap.api.maps;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amap.api.mapcore.util.AMapCoreException;
import com.amap.api.mapcore.util.InstanceFactory;
import com.amap.api.mapcore.util.MapFragmentDelegateImp;
import com.amap.api.mapcore.util.Util;
import com.amap.api.maps.model.RuntimeRemoteException;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class MapFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    /* renamed from: a */
    private AMap f3104a;
    /* renamed from: b */
    private IMapFragmentDelegate f3105b;

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

    public static MapFragment newInstance() {
        return newInstance(new AMapOptions());
    }

    public static MapFragment newInstance(AMapOptions aMapOptions) {
        MapFragment mapFragment = new MapFragment();
        Bundle bundle = new Bundle();
        try {
            Parcel obtain = Parcel.obtain();
            aMapOptions.writeToParcel(obtain, 0);
            bundle.putByteArray("MapOptions", obtain.marshall());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        mapFragment.setArguments(bundle);
        return mapFragment;
    }

    /* Access modifiers changed, original: protected */
    public IMapFragmentDelegate getMapFragmentDelegate() {
        if (this.f3105b == null) {
            try {
                this.f3105b = (IMapFragmentDelegate) InstanceFactory.m2730a(getActivity(), Util.m2377e(), "com.amap.api.mapcore.wrapper.MapFragmentDelegateWrapper", MapFragmentDelegateImp.class, new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(MapFragmentDelegateImp.f975c)});
            } catch (AMapCoreException e) {
                e.printStackTrace();
                this.f3105b = new MapFragmentDelegateImp(MapFragmentDelegateImp.f975c);
            }
        }
        if (getActivity() != null) {
            MapFragmentDelegateImp.f974a = getActivity().getApplicationContext();
            this.f3105b.setContext(getActivity());
        }
        return this.f3105b;
    }

    public AMap getMap() {
        IMapFragmentDelegate mapFragmentDelegate = getMapFragmentDelegate();
        if (mapFragmentDelegate == null) {
            return null;
        }
        try {
            IAMapDelegate map = mapFragmentDelegate.getMap();
            if (map == null) {
                return null;
            }
            if (this.f3104a == null) {
                this.f3104a = new AMap(map);
            }
            return this.f3104a;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        try {
            getMapFragmentDelegate().onInflate(activity, new AMapOptions(), bundle);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
        try {
            getMapFragmentDelegate().onCreate(bundle);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = null;
        try {
            TraceMachine.enterMethod(this._nr_trace, "MapFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "MapFragment#onCreateView", null);
            }
        }
        if (bundle == null) {
            try {
                bundle = getArguments();
            } catch (RemoteException e2) {
                e2.printStackTrace();
                TraceMachine.exitMethod();
            }
        }
        view = getMapFragmentDelegate().onCreateView(layoutInflater, viewGroup, bundle);
        TraceMachine.exitMethod();
        return view;
    }

    public void onResume() {
        super.onResume();
        try {
            getMapFragmentDelegate().onResume();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onPause() {
        super.onPause();
        try {
            getMapFragmentDelegate().onPause();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onDestroyView() {
        try {
            getMapFragmentDelegate().onDestroyView();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        super.onDestroyView();
    }

    public void onDestroy() {
        try {
            getMapFragmentDelegate().onDestroy();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    public void onLowMemory() {
        super.onLowMemory();
        try {
            getMapFragmentDelegate().onLowMemory();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        try {
            getMapFragmentDelegate().onSaveInstanceState(bundle);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        super.onSaveInstanceState(bundle);
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }

    public void setUserVisibleHint(boolean z) {
        if (z) {
            getMapFragmentDelegate().setVisibility(0);
        } else {
            getMapFragmentDelegate().setVisibility(8);
        }
    }
}
