package com.amap.api.maps.overlay;

import android.content.Context;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveStep;
import java.util.ArrayList;
import java.util.List;

public class DrivingRouteOverlay extends RouteOverlay {
    /* renamed from: a */
    private DrivePath f3336a;
    /* renamed from: b */
    private List<LatLonPoint> f3337b;
    /* renamed from: c */
    private List<Marker> f3338c;
    /* renamed from: d */
    private boolean f3339d;
    /* renamed from: e */
    private Context f3340e;
    /* renamed from: f */
    private PolylineOptions f3341f;

    public /* bridge */ /* synthetic */ void setNodeIconVisibility(boolean z) {
        super.setNodeIconVisibility(z);
    }

    public /* bridge */ /* synthetic */ void zoomToSpan() {
        super.zoomToSpan();
    }

    public DrivingRouteOverlay(Context context, AMap aMap, DrivePath drivePath, LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        this(context, aMap, drivePath, latLonPoint, latLonPoint2, null);
        this.f3340e = context;
    }

    public DrivingRouteOverlay(Context context, AMap aMap, DrivePath drivePath, LatLonPoint latLonPoint, LatLonPoint latLonPoint2, List<LatLonPoint> list) {
        super(context);
        this.f3338c = new ArrayList();
        this.f3339d = true;
        this.mAMap = aMap;
        this.f3336a = drivePath;
        this.startPoint = AMapServicesUtil.m4556a(latLonPoint);
        this.endPoint = AMapServicesUtil.m4556a(latLonPoint2);
        this.f3337b = list;
        this.f3340e = context;
    }

    public void addToMap() {
        m4538a();
        try {
            List steps = this.f3336a.getSteps();
            int i = 0;
            while (i < steps.size()) {
                DriveStep driveStep = (DriveStep) steps.get(i);
                LatLng a = AMapServicesUtil.m4556a(m4537a(driveStep));
                if (i < steps.size() - 1 && i == 0) {
                    m4539a(this.startPoint, a);
                }
                m4541a(driveStep, a);
                m4545c(driveStep);
                if (i == steps.size() - 1) {
                    m4540a(m4542b(driveStep), this.endPoint);
                }
                i++;
            }
            addStartAndEndMarker();
            m4544c();
            m4543b();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m4538a() {
        this.f3341f = null;
        this.f3341f = new PolylineOptions();
        this.f3341f.color(getDriveColor()).width(getRouteWidth());
    }

    /* renamed from: b */
    private void m4543b() {
        addPolyLine(this.f3341f);
    }

    /* renamed from: a */
    private LatLonPoint m4537a(DriveStep driveStep) {
        return (LatLonPoint) driveStep.getPolyline().get(0);
    }

    /* renamed from: b */
    private LatLonPoint m4542b(DriveStep driveStep) {
        return (LatLonPoint) driveStep.getPolyline().get(driveStep.getPolyline().size() - 1);
    }

    /* renamed from: a */
    private void m4540a(LatLonPoint latLonPoint, LatLng latLng) {
        m4539a(AMapServicesUtil.m4556a(latLonPoint), latLng);
    }

    /* renamed from: a */
    private void m4539a(LatLng latLng, LatLng latLng2) {
        this.f3341f.add(latLng, latLng2);
    }

    /* renamed from: c */
    private void m4545c(DriveStep driveStep) {
        this.f3341f.addAll(AMapServicesUtil.m4557a(driveStep.getPolyline()));
    }

    /* renamed from: a */
    private void m4541a(DriveStep driveStep, LatLng latLng) {
        addStationMarker(new MarkerOptions().position(latLng).title("方向:" + driveStep.getAction() + "\n道路:" + driveStep.getRoad()).snippet(driveStep.getInstruction()).visible(this.nodeIconVisible).anchor(0.5f, 0.5f).icon(getDriveBitmapDescriptor()));
    }

    /* renamed from: c */
    private void m4544c() {
        if (this.f3337b != null && this.f3337b.size() > 0) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < this.f3337b.size()) {
                    LatLonPoint latLonPoint = (LatLonPoint) this.f3337b.get(i2);
                    if (latLonPoint != null) {
                        this.f3338c.add(this.mAMap.addMarker(new MarkerOptions().position(new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude())).visible(this.f3339d).icon(m4546d()).title("途经点")));
                    }
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public LatLngBounds getLatLngBounds() {
        Builder builder = LatLngBounds.builder();
        builder.include(new LatLng(this.startPoint.latitude, this.startPoint.longitude));
        builder.include(new LatLng(this.endPoint.latitude, this.endPoint.longitude));
        if (this.f3337b != null && this.f3337b.size() > 0) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.f3337b.size()) {
                    break;
                }
                builder.include(new LatLng(((LatLonPoint) this.f3337b.get(i2)).getLatitude(), ((LatLonPoint) this.f3337b.get(i2)).getLongitude()));
                i = i2 + 1;
            }
        }
        return builder.build();
    }

    public void setThroughPointIconVisibility(boolean z) {
        try {
            this.f3339d = z;
            if (this.f3338c != null && this.f3338c.size() > 0) {
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 < this.f3338c.size()) {
                        ((Marker) this.f3338c.get(i2)).setVisible(z);
                        i = i2 + 1;
                    } else {
                        return;
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0040 A:{SYNTHETIC, Splitter:B:26:0x0040} */
    /* renamed from: d */
    private com.amap.api.maps.model.BitmapDescriptor m4546d() {
        /*
        r4 = this;
        r1 = 0;
        r0 = r4.f3340e;	 Catch:{ Throwable -> 0x0029, all -> 0x003c }
        r0 = com.amap.api.mapcore.util.ResourcesUtil.m2327a(r0);	 Catch:{ Throwable -> 0x0029, all -> 0x003c }
        r2 = "amap_throughpoint.png";
        r2 = r0.open(r2);	 Catch:{ Throwable -> 0x0029, all -> 0x003c }
        r1 = com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation.decodeStream(r2);	 Catch:{ Throwable -> 0x004b }
        r0 = com.amap.api.mapcore.util.ConfigableConst.f2121a;	 Catch:{ Throwable -> 0x0050 }
        r0 = com.amap.api.mapcore.util.Util.m2345a(r1, r0);	 Catch:{ Throwable -> 0x0050 }
        if (r2 == 0) goto L_0x001c;
    L_0x0019:
        r2.close();	 Catch:{ Throwable -> 0x0024 }
    L_0x001c:
        r1 = com.amap.api.maps.model.BitmapDescriptorFactory.fromBitmap(r0);
        r0.recycle();
        return r1;
    L_0x0024:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001c;
    L_0x0029:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
        r1 = r0;
        r0 = r3;
    L_0x002e:
        r1.printStackTrace();	 Catch:{ all -> 0x0049 }
        if (r2 == 0) goto L_0x001c;
    L_0x0033:
        r2.close();	 Catch:{ Throwable -> 0x0037 }
        goto L_0x001c;
    L_0x0037:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001c;
    L_0x003c:
        r0 = move-exception;
        r2 = r1;
    L_0x003e:
        if (r2 == 0) goto L_0x0043;
    L_0x0040:
        r2.close();	 Catch:{ Throwable -> 0x0044 }
    L_0x0043:
        throw r0;
    L_0x0044:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0043;
    L_0x0049:
        r0 = move-exception;
        goto L_0x003e;
    L_0x004b:
        r0 = move-exception;
        r3 = r0;
        r0 = r1;
        r1 = r3;
        goto L_0x002e;
    L_0x0050:
        r0 = move-exception;
        r3 = r0;
        r0 = r1;
        r1 = r3;
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.maps.overlay.DrivingRouteOverlay.m4546d():com.amap.api.maps.model.BitmapDescriptor");
    }

    public void removeFromMap() {
        try {
            super.removeFromMap();
            if (this.f3338c != null && this.f3338c.size() > 0) {
                for (int i = 0; i < this.f3338c.size(); i++) {
                    ((Marker) this.f3338c.get(i)).remove();
                }
                this.f3338c.clear();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
