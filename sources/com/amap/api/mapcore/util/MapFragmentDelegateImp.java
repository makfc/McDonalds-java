package com.amap.api.mapcore.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.model.CameraPosition;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate;
import com.autonavi.amap.mapcore.interfaces.IUiSettingsDelegate;

/* renamed from: com.amap.api.mapcore.util.ab */
public class MapFragmentDelegateImp implements IMapFragmentDelegate {
    /* renamed from: a */
    public static volatile Context f974a;
    /* renamed from: c */
    public static int f975c = 0;
    /* renamed from: d */
    public static int f976d = 1;
    /* renamed from: b */
    public int f977b = 0;
    /* renamed from: e */
    private IAMapDelegate f978e;
    /* renamed from: f */
    private int f979f = 0;
    /* renamed from: g */
    private AMapOptions f980g;

    public MapFragmentDelegateImp(int i) {
        int i2 = 0;
        if (i > 0) {
            i2 = 1;
        }
        this.f979f = i2;
    }

    public void setContext(Context context) {
        if (context != null) {
            f974a = context.getApplicationContext();
        }
    }

    public void setOptions(AMapOptions aMapOptions) {
        this.f980g = aMapOptions;
    }

    public IAMapDelegate getMap() throws RemoteException {
        if (this.f978e == null) {
            if (f974a == null) {
                throw new NullPointerException("Context 为 null 请在地图调用之前 使用 MapsInitializer.initialize(Context paramContext) 来设置Context");
            }
            int i = f974a.getResources().getDisplayMetrics().densityDpi;
            if (i <= 120) {
                ConfigableConst.f2121a = 0.5f;
            } else if (i <= 160) {
                ConfigableConst.f2121a = 0.8f;
            } else if (i <= 240) {
                ConfigableConst.f2121a = 0.87f;
            } else if (i <= 320) {
                ConfigableConst.f2121a = 1.0f;
            } else if (i <= 480) {
                ConfigableConst.f2121a = 1.5f;
            } else if (i <= 640) {
                ConfigableConst.f2121a = 1.8f;
            } else {
                ConfigableConst.f2121a = 0.9f;
            }
            if (this.f979f == f975c) {
                this.f978e = new AMapGLSurfaceView(f974a).mo9447a();
            } else {
                this.f978e = new AMapGLTextureView(f974a).mo9512a();
            }
        }
        return this.f978e;
    }

    public void onInflate(Activity activity, AMapOptions aMapOptions, Bundle bundle) throws RemoteException {
        f974a = activity.getApplicationContext();
        this.f980g = aMapOptions;
    }

    public void onCreate(Bundle bundle) throws RemoteException {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) throws RemoteException {
        if (this.f978e == null) {
            if (f974a == null && layoutInflater != null) {
                f974a = layoutInflater.getContext().getApplicationContext();
            }
            if (f974a == null) {
                throw new NullPointerException("Context 为 null 请在地图调用之前 使用 MapsInitializer.initialize(Context paramContext) 来设置Context");
            }
            int i = f974a.getResources().getDisplayMetrics().densityDpi;
            if (i <= 120) {
                ConfigableConst.f2121a = 0.5f;
            } else if (i <= 160) {
                ConfigableConst.f2121a = 0.6f;
            } else if (i <= 240) {
                ConfigableConst.f2121a = 0.87f;
            } else if (i <= 320) {
                ConfigableConst.f2121a = 1.0f;
            } else if (i <= 480) {
                ConfigableConst.f2121a = 1.5f;
            } else if (i <= 640) {
                ConfigableConst.f2121a = 1.8f;
            } else {
                ConfigableConst.f2121a = 0.9f;
            }
            if (this.f979f == f975c) {
                this.f978e = new AMapGLSurfaceView(f974a).mo9447a();
            } else {
                this.f978e = new AMapGLTextureView(f974a).mo9512a();
            }
            this.f978e.setVisibilityEx(this.f977b);
        }
        try {
            if (this.f980g == null && bundle != null) {
                byte[] byteArray = bundle.getByteArray("MapOptions");
                if (byteArray != null) {
                    Parcel obtain = Parcel.obtain();
                    obtain.unmarshall(byteArray, 0, byteArray.length);
                    obtain.setDataPosition(0);
                    this.f980g = AMapOptions.CREATOR.createFromParcel(obtain);
                }
            }
            mo8482a(this.f980g);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.f978e.getView();
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8482a(AMapOptions aMapOptions) throws RemoteException {
        if (aMapOptions != null && this.f978e != null) {
            CameraPosition camera = aMapOptions.getCamera();
            if (camera != null) {
                this.f978e.moveCamera(CameraUpdateFactoryDelegate.newCamera(camera.target, camera.zoom, camera.bearing, camera.tilt));
            }
            IUiSettingsDelegate uiSettings = this.f978e.getUiSettings();
            uiSettings.setRotateGesturesEnabled(aMapOptions.getRotateGesturesEnabled().booleanValue());
            uiSettings.setScrollGesturesEnabled(aMapOptions.getScrollGesturesEnabled().booleanValue());
            uiSettings.setTiltGesturesEnabled(aMapOptions.getTiltGesturesEnabled().booleanValue());
            uiSettings.setZoomControlsEnabled(aMapOptions.getZoomControlsEnabled().booleanValue());
            uiSettings.setZoomGesturesEnabled(aMapOptions.getZoomGesturesEnabled().booleanValue());
            uiSettings.setCompassEnabled(aMapOptions.getCompassEnabled().booleanValue());
            uiSettings.setScaleControlsEnabled(aMapOptions.getScaleControlsEnabled().booleanValue());
            uiSettings.setLogoPosition(aMapOptions.getLogoPosition());
            this.f978e.setMapType(aMapOptions.getMapType());
            this.f978e.setZOrderOnTop(aMapOptions.getZOrderOnTop().booleanValue());
        }
    }

    public void onResume() throws RemoteException {
        if (this.f978e != null) {
            this.f978e.onActivityResume();
        }
    }

    public void onPause() throws RemoteException {
        if (this.f978e != null) {
            this.f978e.onActivityPause();
        }
    }

    public void onDestroyView() throws RemoteException {
    }

    public void onDestroy() throws RemoteException {
        if (this.f978e != null) {
            this.f978e.clear();
            this.f978e.destroy();
            this.f978e = null;
        }
    }

    public void onLowMemory() throws RemoteException {
        Log.d("onLowMemory", "onLowMemory run");
    }

    public void onSaveInstanceState(Bundle bundle) throws RemoteException {
        if (this.f978e != null) {
            if (this.f980g == null) {
                this.f980g = new AMapOptions();
            }
            try {
                Parcel obtain = Parcel.obtain();
                this.f980g = this.f980g.camera(getMap().getCameraPositionPrj(false));
                this.f980g.writeToParcel(obtain, 0);
                bundle.putByteArray("MapOptions", obtain.marshall());
            } catch (Throwable th) {
            }
        }
    }

    public boolean isReady() throws RemoteException {
        return false;
    }

    public void setVisibility(int i) {
        this.f977b = i;
        if (this.f978e != null) {
            this.f978e.setVisibilityEx(i);
        }
    }
}
