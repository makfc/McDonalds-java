package com.amap.api.mapcore2d;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amap.api.maps2d.AMapOptions;

/* renamed from: com.amap.api.mapcore2d.z */
public interface IMapFragmentDelegate {
    /* renamed from: a */
    View mo9801a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) throws RemoteException;

    /* renamed from: a */
    IAMapDelegate mo9802a() throws RemoteException;

    /* renamed from: a */
    void mo9803a(Activity activity, AMapOptions aMapOptions, Bundle bundle) throws RemoteException;

    /* renamed from: a */
    void mo9804a(Context context);

    /* renamed from: a */
    void mo9805a(Bundle bundle) throws RemoteException;

    /* renamed from: a */
    void mo9806a(AMapOptions aMapOptions);

    /* renamed from: b */
    void mo9807b() throws RemoteException;

    /* renamed from: b */
    void mo9808b(Bundle bundle) throws RemoteException;

    /* renamed from: c */
    void mo9809c() throws RemoteException;

    /* renamed from: d */
    void mo9810d() throws RemoteException;

    /* renamed from: e */
    void mo9811e() throws RemoteException;

    /* renamed from: f */
    void mo9812f() throws RemoteException;
}
