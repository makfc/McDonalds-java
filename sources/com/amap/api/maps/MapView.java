package com.amap.api.maps;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.amap.api.mapcore.util.AMapCoreException;
import com.amap.api.mapcore.util.InstanceFactory;
import com.amap.api.mapcore.util.MapFragmentDelegateImp;
import com.amap.api.mapcore.util.Util;
import com.amap.api.maps.model.RuntimeRemoteException;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate;

public class MapView extends FrameLayout {
    /* renamed from: a */
    private IMapFragmentDelegate f3106a;
    /* renamed from: b */
    private AMap f3107b;
    /* renamed from: c */
    private int f3108c = 0;

    public MapView(Context context) {
        super(context);
        MapFragmentDelegateImp.f974a = context.getApplicationContext();
        getMapFragmentDelegate().setContext(context);
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3108c = attributeSet.getAttributeIntValue(16842972, 0);
        MapFragmentDelegateImp.f974a = context.getApplicationContext();
        getMapFragmentDelegate().setContext(context);
        getMapFragmentDelegate().setVisibility(this.f3108c);
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3108c = attributeSet.getAttributeIntValue(16842972, 0);
        MapFragmentDelegateImp.f974a = context.getApplicationContext();
        getMapFragmentDelegate().setContext(context);
        getMapFragmentDelegate().setVisibility(this.f3108c);
    }

    public MapView(Context context, AMapOptions aMapOptions) {
        super(context);
        MapFragmentDelegateImp.f974a = context.getApplicationContext();
        getMapFragmentDelegate().setContext(context);
        getMapFragmentDelegate().setOptions(aMapOptions);
    }

    /* Access modifiers changed, original: protected */
    public IMapFragmentDelegate getMapFragmentDelegate() {
        if (this.f3106a == null) {
            try {
                this.f3106a = (IMapFragmentDelegate) InstanceFactory.m2730a(getContext(), Util.m2377e(), "com.amap.api.mapcore.wrapper.MapFragmentDelegateWrapper", MapFragmentDelegateImp.class, new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(MapFragmentDelegateImp.f975c)});
            } catch (AMapCoreException e) {
                e.printStackTrace();
            }
            if (this.f3106a == null) {
                this.f3106a = new MapFragmentDelegateImp(MapFragmentDelegateImp.f975c);
            }
        }
        return this.f3106a;
    }

    public AMap getMap() {
        try {
            IAMapDelegate map = getMapFragmentDelegate().getMap();
            if (map == null) {
                return null;
            }
            if (this.f3107b == null) {
                this.f3107b = new AMap(map);
            }
            return this.f3107b;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void onCreate(Bundle bundle) {
        try {
            addView(getMapFragmentDelegate().onCreateView(null, null, bundle), new LayoutParams(-1, -1));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public final void onResume() {
        try {
            getMapFragmentDelegate().onResume();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public final void onPause() {
        try {
            getMapFragmentDelegate().onPause();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public final void onDestroy() {
        try {
            getMapFragmentDelegate().onDestroy();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public final void onLowMemory() {
        try {
            getMapFragmentDelegate().onLowMemory();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        try {
            getMapFragmentDelegate().onSaveInstanceState(bundle);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setLayerType(int i, Paint paint) {
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        getMapFragmentDelegate().setVisibility(i);
    }
}
