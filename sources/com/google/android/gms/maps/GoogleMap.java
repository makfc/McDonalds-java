package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.RemoteException;
import android.support.annotation.RequiresPermission;
import android.view.View;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.maps.LocationSource.OnLocationChangedListener;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzk;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import com.google.android.gms.maps.model.internal.zzc;
import com.google.android.gms.maps.model.internal.zzf;
import com.google.android.gms.maps.model.internal.zzg;

public final class GoogleMap {
    private final IGoogleMapDelegate zzaYH;
    private UiSettings zzaYI;

    /* renamed from: com.google.android.gms.maps.GoogleMap$10 */
    class C232210 extends com.google.android.gms.maps.internal.zzab.zza {
        final /* synthetic */ SnapshotReadyCallback zzaYT;

        public void onSnapshotReady(Bitmap bitmap) throws RemoteException {
            this.zzaYT.onSnapshotReady(bitmap);
        }

        public void zzz(zzd zzd) throws RemoteException {
            this.zzaYT.onSnapshotReady((Bitmap) zze.zzx(zzd));
        }
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap$14 */
    class C232714 extends com.google.android.gms.maps.internal.zzn.zza {
        final /* synthetic */ OnMapLongClickListener zzaYZ;

        public void onMapLongClick(LatLng latLng) {
            this.zzaYZ.onMapLongClick(latLng);
        }
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap$16 */
    class C232916 extends com.google.android.gms.maps.internal.zzq.zza {
        final /* synthetic */ OnMarkerDragListener zzaZb;

        public void zze(zzf zzf) {
            this.zzaZb.onMarkerDragStart(new Marker(zzf));
        }

        public void zzf(zzf zzf) {
            this.zzaZb.onMarkerDragEnd(new Marker(zzf));
        }

        public void zzg(zzf zzf) {
            this.zzaZb.onMarkerDrag(new Marker(zzf));
        }
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap$17 */
    class C233017 extends com.google.android.gms.maps.internal.zzh.zza {
        final /* synthetic */ OnInfoWindowClickListener zzaZc;

        public void zzh(zzf zzf) {
            this.zzaZc.onInfoWindowClick(new Marker(zzf));
        }
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap$18 */
    class C233118 extends com.google.android.gms.maps.internal.zzj.zza {
        final /* synthetic */ OnInfoWindowLongClickListener zzaZd;

        public void zzi(zzf zzf) {
            this.zzaZd.onInfoWindowLongClick(new Marker(zzf));
        }
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap$1 */
    class C23321 extends com.google.android.gms.maps.internal.zzg.zza {
        final /* synthetic */ OnIndoorStateChangeListener zzaYJ;

        public void onIndoorBuildingFocused() {
            this.zzaYJ.onIndoorBuildingFocused();
        }

        public void zza(com.google.android.gms.maps.model.internal.zzd zzd) {
            this.zzaYJ.onIndoorLevelActivated(new IndoorBuilding(zzd));
        }
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap$2 */
    class C23332 extends com.google.android.gms.maps.internal.zzi.zza {
        final /* synthetic */ OnInfoWindowCloseListener zzaYL;

        public void zza(zzf zzf) {
            this.zzaYL.onInfoWindowClose(new Marker(zzf));
        }
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap$4 */
    class C23354 extends com.google.android.gms.maps.internal.zzs.zza {
        final /* synthetic */ OnMyLocationChangeListener zzaYN;

        public void zzy(zzd zzd) {
            this.zzaYN.onMyLocationChange((Location) zze.zzx(zzd));
        }
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap$5 */
    class C23365 extends com.google.android.gms.maps.internal.zzr.zza {
        final /* synthetic */ OnMyLocationButtonClickListener zzaYO;

        public boolean onMyLocationButtonClick() throws RemoteException {
            return this.zzaYO.onMyLocationButtonClick();
        }
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap$6 */
    class C23376 extends com.google.android.gms.maps.internal.zzm.zza {
        final /* synthetic */ OnMapLoadedCallback zzaYP;

        public void onMapLoaded() throws RemoteException {
            this.zzaYP.onMapLoaded();
        }
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap$7 */
    class C23387 extends com.google.android.gms.maps.internal.zzf.zza {
        final /* synthetic */ OnGroundOverlayClickListener zzaYQ;

        public void zza(zzc zzc) {
            this.zzaYQ.onGroundOverlayClick(new GroundOverlay(zzc));
        }
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap$8 */
    class C23398 extends com.google.android.gms.maps.internal.zzu.zza {
        final /* synthetic */ OnPolygonClickListener zzaYR;

        public void zza(zzg zzg) {
            this.zzaYR.onPolygonClick(new Polygon(zzg));
        }
    }

    /* renamed from: com.google.android.gms.maps.GoogleMap$9 */
    class C23409 extends com.google.android.gms.maps.internal.zzv.zza {
        final /* synthetic */ OnPolylineClickListener zzaYS;

        public void zza(IPolylineDelegate iPolylineDelegate) {
            this.zzaYS.onPolylineClick(new Polyline(iPolylineDelegate));
        }
    }

    public interface CancelableCallback {
        void onCancel();

        void onFinish();
    }

    public interface InfoWindowAdapter {
        View getInfoContents(Marker marker);

        View getInfoWindow(Marker marker);
    }

    public interface OnCameraChangeListener {
        void onCameraChange(CameraPosition cameraPosition);
    }

    public interface OnGroundOverlayClickListener {
        void onGroundOverlayClick(GroundOverlay groundOverlay);
    }

    public interface OnIndoorStateChangeListener {
        void onIndoorBuildingFocused();

        void onIndoorLevelActivated(IndoorBuilding indoorBuilding);
    }

    public interface OnInfoWindowClickListener {
        void onInfoWindowClick(Marker marker);
    }

    public interface OnInfoWindowCloseListener {
        void onInfoWindowClose(Marker marker);
    }

    public interface OnInfoWindowLongClickListener {
        void onInfoWindowLongClick(Marker marker);
    }

    public interface OnMapClickListener {
        void onMapClick(LatLng latLng);
    }

    public interface OnMapLoadedCallback {
        void onMapLoaded();
    }

    public interface OnMapLongClickListener {
        void onMapLongClick(LatLng latLng);
    }

    public interface OnMarkerClickListener {
        boolean onMarkerClick(Marker marker);
    }

    public interface OnMarkerDragListener {
        void onMarkerDrag(Marker marker);

        void onMarkerDragEnd(Marker marker);

        void onMarkerDragStart(Marker marker);
    }

    public interface OnMyLocationButtonClickListener {
        boolean onMyLocationButtonClick();
    }

    @Deprecated
    public interface OnMyLocationChangeListener {
        void onMyLocationChange(Location location);
    }

    public interface OnPolygonClickListener {
        void onPolygonClick(Polygon polygon);
    }

    public interface OnPolylineClickListener {
        void onPolylineClick(Polyline polyline);
    }

    public interface SnapshotReadyCallback {
        void onSnapshotReady(Bitmap bitmap);
    }

    private static final class zza extends com.google.android.gms.maps.internal.zzb.zza {
        private final CancelableCallback zzaZe;

        zza(CancelableCallback cancelableCallback) {
            this.zzaZe = cancelableCallback;
        }

        public void onCancel() {
            this.zzaZe.onCancel();
        }

        public void onFinish() {
            this.zzaZe.onFinish();
        }
    }

    protected GoogleMap(IGoogleMapDelegate iGoogleMapDelegate) {
        this.zzaYH = (IGoogleMapDelegate) zzaa.zzz(iGoogleMapDelegate);
    }

    public final Marker addMarker(MarkerOptions markerOptions) {
        try {
            zzf addMarker = this.zzaYH.addMarker(markerOptions);
            return addMarker != null ? new Marker(addMarker) : null;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate) {
        try {
            this.zzaYH.animateCamera(cameraUpdate.zzDq());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate, CancelableCallback cancelableCallback) {
        try {
            this.zzaYH.animateCameraWithCallback(cameraUpdate.zzDq(), cancelableCallback == null ? null : new zza(cancelableCallback));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final CameraPosition getCameraPosition() {
        try {
            return this.zzaYH.getCameraPosition();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final UiSettings getUiSettings() {
        try {
            if (this.zzaYI == null) {
                this.zzaYI = new UiSettings(this.zzaYH.getUiSettings());
            }
            return this.zzaYI;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void moveCamera(CameraUpdate cameraUpdate) {
        try {
            this.zzaYH.moveCamera(cameraUpdate.zzDq());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setInfoWindowAdapter(final InfoWindowAdapter infoWindowAdapter) {
        if (infoWindowAdapter == null) {
            try {
                this.zzaYH.setInfoWindowAdapter(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.zzaYH.setInfoWindowAdapter(new com.google.android.gms.maps.internal.zzd.zza() {
            public zzd zzb(zzf zzf) {
                return zze.zzD(infoWindowAdapter.getInfoWindow(new Marker(zzf)));
            }

            public zzd zzc(zzf zzf) {
                return zze.zzD(infoWindowAdapter.getInfoContents(new Marker(zzf)));
            }
        });
    }

    public final void setLocationSource(final LocationSource locationSource) {
        if (locationSource == null) {
            try {
                this.zzaYH.setLocationSource(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.zzaYH.setLocationSource(new com.google.android.gms.maps.internal.ILocationSourceDelegate.zza() {
            public void activate(final zzk zzk) {
                locationSource.activate(new OnLocationChangedListener() {
                    public void onLocationChanged(Location location) {
                        try {
                            zzk.zzd(location);
                        } catch (RemoteException e) {
                            throw new RuntimeRemoteException(e);
                        }
                    }
                });
            }

            public void deactivate() {
                locationSource.deactivate();
            }
        });
    }

    @RequiresPermission
    public final void setMyLocationEnabled(boolean z) {
        try {
            this.zzaYH.setMyLocationEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnCameraChangeListener(final OnCameraChangeListener onCameraChangeListener) {
        if (onCameraChangeListener == null) {
            try {
                this.zzaYH.setOnCameraChangeListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.zzaYH.setOnCameraChangeListener(new com.google.android.gms.maps.internal.zze.zza() {
            public void onCameraChange(CameraPosition cameraPosition) {
                onCameraChangeListener.onCameraChange(cameraPosition);
            }
        });
    }

    public final void setOnMapClickListener(final OnMapClickListener onMapClickListener) {
        if (onMapClickListener == null) {
            try {
                this.zzaYH.setOnMapClickListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.zzaYH.setOnMapClickListener(new com.google.android.gms.maps.internal.zzl.zza() {
            public void onMapClick(LatLng latLng) {
                onMapClickListener.onMapClick(latLng);
            }
        });
    }

    public final void setOnMarkerClickListener(final OnMarkerClickListener onMarkerClickListener) {
        if (onMarkerClickListener == null) {
            try {
                this.zzaYH.setOnMarkerClickListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.zzaYH.setOnMarkerClickListener(new com.google.android.gms.maps.internal.zzp.zza() {
            public boolean zzd(zzf zzf) {
                return onMarkerClickListener.onMarkerClick(new Marker(zzf));
            }
        });
    }
}
