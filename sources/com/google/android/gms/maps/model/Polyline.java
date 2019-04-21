package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;

public final class Polyline {
    private final IPolylineDelegate zzbbc;

    public Polyline(IPolylineDelegate iPolylineDelegate) {
        this.zzbbc = (IPolylineDelegate) zzaa.zzz(iPolylineDelegate);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Polyline)) {
            return false;
        }
        try {
            return this.zzbbc.equalsRemote(((Polyline) obj).zzbbc);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int hashCode() {
        try {
            return this.zzbbc.hashCodeRemote();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
