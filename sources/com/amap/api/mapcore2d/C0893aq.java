package com.amap.api.mapcore2d;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amap.api.maps2d.AMapOptions;
import com.amap.api.maps2d.model.CameraPosition;

/* compiled from: MapFragmentDelegateImp */
/* renamed from: com.amap.api.mapcore2d.aq */
public class C0893aq implements IMapFragmentDelegate {
    /* renamed from: a */
    public static volatile Context f2276a;
    /* renamed from: b */
    private IAMapDelegate f2277b;
    /* renamed from: c */
    private AMapOptions f2278c;

    /* renamed from: a */
    public void mo9804a(Context context) {
        if (context != null) {
            f2276a = context.getApplicationContext();
        }
    }

    /* renamed from: a */
    public void mo9806a(AMapOptions aMapOptions) {
        this.f2278c = aMapOptions;
    }

    /* renamed from: a */
    public IAMapDelegate mo9802a() throws RemoteException {
        if (this.f2277b == null) {
            if (f2276a == null) {
                throw new NullPointerException("Context 为 null 请在地图调用之前 使用 MapsInitializer.initialize(Context paramContext) 来设置Context");
            }
            mo9814g();
            this.f2277b = new AMapDelegateImpGLSurfaceView(f2276a);
        }
        return this.f2277b;
    }

    /* renamed from: a */
    public void mo9803a(Activity activity, AMapOptions aMapOptions, Bundle bundle) throws RemoteException {
        f2276a = activity.getApplicationContext();
        this.f2278c = aMapOptions;
    }

    /* renamed from: a */
    public void mo9805a(Bundle bundle) throws RemoteException {
        LogManager.m3875a("MapFragmentDelegateImp", "onCreate", 113);
    }

    /* renamed from: a */
    public View mo9801a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) throws RemoteException {
        if (this.f2277b == null) {
            if (f2276a == null && layoutInflater != null) {
                f2276a = layoutInflater.getContext().getApplicationContext();
            }
            if (f2276a == null) {
                throw new NullPointerException("Context 为 null 请在地图调用之前 使用 MapsInitializer.initialize(Context paramContext) 来设置Context");
            }
            mo9814g();
            this.f2277b = new AMapDelegateImpGLSurfaceView(f2276a);
        }
        if (this.f2278c == null && bundle != null) {
            this.f2278c = (AMapOptions) bundle.getParcelable("MapOptions");
        }
        mo9813b(this.f2278c);
        LogManager.m3875a("MapFragmentDelegateImp", "onCreateView", 113);
        return this.f2277b.mo9983e();
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: g */
    public void mo9814g() {
        int i = f2276a.getResources().getDisplayMetrics().densityDpi;
        C1042p.f3041k = i;
        if (i <= 320) {
            C1042p.f3039i = 256;
        } else if (i <= 480) {
            C1042p.f3039i = 384;
        } else {
            C1042p.f3039i = 512;
        }
        if (i <= 120) {
            C1042p.f3031a = 0.5f;
        } else if (i <= 160) {
            C1042p.f3031a = 0.6f;
        } else if (i <= 240) {
            C1042p.f3031a = 0.87f;
        } else if (i <= 320) {
            C1042p.f3031a = 1.0f;
        } else if (i <= 480) {
            C1042p.f3031a = 1.5f;
        } else {
            C1042p.f3031a = 1.8f;
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo9813b(AMapOptions aMapOptions) throws RemoteException {
        if (aMapOptions != null && this.f2277b != null) {
            CameraPosition camera = aMapOptions.getCamera();
            if (camera != null) {
                this.f2277b.mo9951a(CameraUpdateFactoryDelegate.m4324a(camera.target, camera.zoom, camera.bearing, camera.tilt));
            }
            IUiSettingsDelegate q = this.f2277b.mo9997q();
            q.mo9724e(aMapOptions.getScrollGesturesEnabled().booleanValue());
            q.mo9718b(aMapOptions.getZoomControlsEnabled().booleanValue());
            q.mo9726f(aMapOptions.getZoomGesturesEnabled().booleanValue());
            q.mo9720c(aMapOptions.getCompassEnabled().booleanValue());
            q.mo9715a(aMapOptions.getScaleControlsEnabled().booleanValue());
            q.mo9714a(aMapOptions.getLogoPosition());
            this.f2277b.mo9948a(aMapOptions.getMapType());
            this.f2277b.mo9968a(aMapOptions.getZOrderOnTop().booleanValue());
        }
    }

    /* renamed from: b */
    public void mo9807b() throws RemoteException {
        if (this.f2277b != null) {
            this.f2277b.mo10002y();
        }
    }

    /* renamed from: c */
    public void mo9809c() throws RemoteException {
        if (this.f2277b != null) {
            this.f2277b.mo10003z();
        }
    }

    /* renamed from: d */
    public void mo9810d() throws RemoteException {
    }

    /* renamed from: e */
    public void mo9811e() throws RemoteException {
        if (mo9802a() != null) {
            mo9802a().mo9992k();
            mo9802a().mo10000v();
        }
    }

    /* renamed from: f */
    public void mo9812f() throws RemoteException {
        Log.d("onLowMemory", "onLowMemory run");
    }

    /* renamed from: b */
    public void mo9808b(Bundle bundle) throws RemoteException {
        if (this.f2277b != null) {
            if (this.f2278c == null) {
                this.f2278c = new AMapOptions();
            }
            this.f2278c = this.f2278c.camera(mo9802a().mo9987g());
            bundle.putParcelable("MapOptions", this.f2278c);
        }
    }
}
