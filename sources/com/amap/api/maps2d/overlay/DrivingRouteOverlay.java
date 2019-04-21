package com.amap.api.maps2d.overlay;

import android.content.Context;
import android.graphics.Bitmap;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.LatLngBounds.Builder;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveStep;
import java.util.ArrayList;
import java.util.List;

public class DrivingRouteOverlay extends C1067b {
    /* renamed from: a */
    private DrivePath f3512a;
    /* renamed from: b */
    private Bitmap f3513b;
    /* renamed from: c */
    private List<LatLonPoint> f3514c;
    /* renamed from: d */
    private boolean f3515d = true;
    protected List<Marker> mPassByMarkers = new ArrayList();

    public /* bridge */ /* synthetic */ void setNodeIconVisibility(boolean z) {
        super.setNodeIconVisibility(z);
    }

    public /* bridge */ /* synthetic */ void zoomToSpan() {
        super.zoomToSpan();
    }

    public DrivingRouteOverlay(Context context, AMap aMap, DrivePath drivePath, LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        super(context);
        this.mAMap = aMap;
        this.f3512a = drivePath;
        this.startPoint = C1068a.m4636a(latLonPoint);
        this.endPoint = C1068a.m4636a(latLonPoint2);
    }

    public DrivingRouteOverlay(Context context, AMap aMap, DrivePath drivePath, LatLonPoint latLonPoint, LatLonPoint latLonPoint2, List<LatLonPoint> list) {
        super(context);
        this.mAMap = aMap;
        this.f3512a = drivePath;
        this.startPoint = C1068a.m4636a(latLonPoint);
        this.endPoint = C1068a.m4636a(latLonPoint2);
        this.f3514c = list;
    }

    public void addToMap() {
        List steps = this.f3512a.getSteps();
        for (int i = 0; i < steps.size(); i++) {
            DriveStep driveStep = (DriveStep) steps.get(i);
            LatLng a = C1068a.m4636a((LatLonPoint) driveStep.getPolyline().get(0));
            LatLng a2;
            if (i < steps.size() - 1) {
                if (i == 0) {
                    this.allPolyLines.add(this.mAMap.addPolyline(new PolylineOptions().add(this.startPoint, a).color(getDriveColor()).width(getBuslineWidth())));
                }
                if (!C1068a.m4636a((LatLonPoint) driveStep.getPolyline().get(driveStep.getPolyline().size() - 1)).equals(C1068a.m4636a((LatLonPoint) ((DriveStep) steps.get(i + 1)).getPolyline().get(0)))) {
                    this.allPolyLines.add(this.mAMap.addPolyline(new PolylineOptions().add(r6, a2).color(getDriveColor()).width(getBuslineWidth())));
                }
            } else {
                a2 = C1068a.m4636a((LatLonPoint) driveStep.getPolyline().get(driveStep.getPolyline().size() - 1));
                this.allPolyLines.add(this.mAMap.addPolyline(new PolylineOptions().add(a2, this.endPoint).color(getDriveColor()).width(getBuslineWidth())));
            }
            this.stationMarkers.add(this.mAMap.addMarker(new MarkerOptions().position(a).title("方向:" + driveStep.getAction() + "\n道路:" + driveStep.getRoad()).snippet(driveStep.getInstruction()).anchor(0.5f, 0.5f).visible(this.mNodeIconVisible).icon(getDriveBitmapDescriptor())));
            this.allPolyLines.add(this.mAMap.addPolyline(new PolylineOptions().addAll(C1068a.m4637a(driveStep.getPolyline())).color(getDriveColor()).width(getBuslineWidth())));
        }
        m4632a();
        addStartAndEndMarker();
    }

    /* renamed from: a */
    private void m4632a() {
        if (this.f3514c != null && this.f3514c.size() != 0) {
            for (LatLonPoint a : this.f3514c) {
                this.mPassByMarkers.add(this.mAMap.addMarker(new MarkerOptions().position(C1068a.m4636a(a)).title("途经点").visible(this.f3515d).icon(getPassedByBitmapDescriptor())));
            }
        }
    }

    public void removeFromMap() {
        super.removeFromMap();
        for (Marker remove : this.mPassByMarkers) {
            remove.remove();
        }
    }

    public void setThroughPointIconVisibility(boolean z) {
        this.f3515d = z;
        for (Marker visible : this.mPassByMarkers) {
            visible.setVisible(z);
        }
        this.mAMap.postInvalidate();
    }

    /* Access modifiers changed, original: protected */
    public float getBuslineWidth() {
        return 18.0f;
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getPassedByBitmapDescriptor() {
        return getBitDes(this.f3513b, "amap_throughpoint.png");
    }

    /* Access modifiers changed, original: protected */
    public LatLngBounds getLatLngBounds() {
        Builder builder = LatLngBounds.builder();
        builder.include(new LatLng(this.startPoint.latitude, this.startPoint.longitude));
        builder.include(new LatLng(this.endPoint.latitude, this.endPoint.longitude));
        if (this.f3514c != null && this.f3514c.size() > 0) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.f3514c.size()) {
                    break;
                }
                builder.include(new LatLng(((LatLonPoint) this.f3514c.get(i2)).getLatitude(), ((LatLonPoint) this.f3514c.get(i2)).getLongitude()));
                i = i2 + 1;
            }
        }
        return builder.build();
    }
}
