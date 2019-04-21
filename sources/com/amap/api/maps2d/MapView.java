package com.amap.api.maps2d;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.amap.api.mapcore2d.C0893aq;
import com.amap.api.mapcore2d.C0955ck;
import com.amap.api.mapcore2d.IAMapDelegate;
import com.amap.api.mapcore2d.IMapFragmentDelegate;
import com.amap.api.maps2d.model.RuntimeRemoteException;

public class MapView extends FrameLayout {
    /* renamed from: a */
    private IMapFragmentDelegate f3369a;
    /* renamed from: b */
    private AMap f3370b;

    public MapView(Context context) {
        super(context);
        getMapFragmentDelegate().mo9804a(context);
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        getMapFragmentDelegate().mo9804a(context);
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        getMapFragmentDelegate().mo9804a(context);
    }

    public MapView(Context context, AMapOptions aMapOptions) {
        super(context);
        getMapFragmentDelegate().mo9804a(context);
        getMapFragmentDelegate().mo9806a(aMapOptions);
    }

    /* Access modifiers changed, original: protected */
    public IMapFragmentDelegate getMapFragmentDelegate() {
        if (this.f3369a == null) {
            this.f3369a = new C0893aq();
        }
        return this.f3369a;
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
            if (this.f3370b == null) {
                this.f3370b = new AMap(a);
            }
            return this.f3370b;
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "MapView", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void onCreate(Bundle bundle) {
        String str = "onCreate";
        try {
            addView(getMapFragmentDelegate().mo9801a(null, null, bundle), new LayoutParams(-1, -1));
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "MapView", str);
        } catch (Throwable th) {
            C0955ck.m3888a(th, "MapView", str);
        }
    }

    public final void onResume() {
        String str = "onResume";
        try {
            getMapFragmentDelegate().mo9807b();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "MapView", str);
        }
    }

    public final void onPause() {
        String str = "onPause";
        try {
            getMapFragmentDelegate().mo9809c();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "MapView", str);
        }
    }

    public final void onDestroy() {
        String str = "onDestroy";
        try {
            getMapFragmentDelegate().mo9811e();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "MapView", str);
        }
    }

    public final void onLowMemory() {
        String str = "onLowMemory";
        try {
            getMapFragmentDelegate().mo9812f();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "MapView", str);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        String str = "onSaveInstanceState";
        try {
            getMapFragmentDelegate().mo9808b(bundle);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "MapView", str);
        }
    }
}
