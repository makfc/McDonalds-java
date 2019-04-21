package com.amap.api.maps.overlay;

import android.content.Context;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkStep;
import java.util.List;

public class WalkRouteOverlay extends RouteOverlay {
    /* renamed from: a */
    private WalkPath f3345a;

    public /* bridge */ /* synthetic */ void removeFromMap() {
        super.removeFromMap();
    }

    public /* bridge */ /* synthetic */ void setNodeIconVisibility(boolean z) {
        super.setNodeIconVisibility(z);
    }

    public /* bridge */ /* synthetic */ void zoomToSpan() {
        super.zoomToSpan();
    }

    public WalkRouteOverlay(Context context, AMap aMap, WalkPath walkPath, LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        super(context);
        this.mAMap = aMap;
        this.f3345a = walkPath;
        this.startPoint = AMapServicesUtil.m4556a(latLonPoint);
        this.endPoint = AMapServicesUtil.m4556a(latLonPoint2);
    }

    public void addToMap() {
        try {
            List steps = this.f3345a.getSteps();
            for (int i = 0; i < steps.size(); i++) {
                WalkStep walkStep = (WalkStep) steps.get(i);
                LatLng a = AMapServicesUtil.m4556a((LatLonPoint) walkStep.getPolyline().get(0));
                if (i < steps.size() - 1) {
                    if (i == 0) {
                        m4550a(this.startPoint, a);
                    }
                    m4553a(walkStep, (WalkStep) steps.get(i + 1));
                } else {
                    m4550a(AMapServicesUtil.m4556a(m4549a(walkStep)), this.endPoint);
                }
                m4552a(walkStep, a);
                m4555c(walkStep);
            }
            addStartAndEndMarker();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m4553a(WalkStep walkStep, WalkStep walkStep2) {
        LatLonPoint a = m4549a(walkStep);
        LatLonPoint b = m4554b(walkStep2);
        if (!a.equals(b)) {
            m4551a(a, b);
        }
    }

    /* renamed from: a */
    private LatLonPoint m4549a(WalkStep walkStep) {
        return (LatLonPoint) walkStep.getPolyline().get(walkStep.getPolyline().size() - 1);
    }

    /* renamed from: b */
    private LatLonPoint m4554b(WalkStep walkStep) {
        return (LatLonPoint) walkStep.getPolyline().get(0);
    }

    /* renamed from: a */
    private void m4551a(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        m4550a(AMapServicesUtil.m4556a(latLonPoint), AMapServicesUtil.m4556a(latLonPoint2));
    }

    /* renamed from: a */
    private void m4550a(LatLng latLng, LatLng latLng2) {
        addPolyLine(new PolylineOptions().add(latLng, latLng2).color(getWalkColor()).width(getRouteWidth()));
    }

    /* renamed from: c */
    private void m4555c(WalkStep walkStep) {
        addPolyLine(new PolylineOptions().addAll(AMapServicesUtil.m4557a(walkStep.getPolyline())).color(getWalkColor()).width(getRouteWidth()));
    }

    /* renamed from: a */
    private void m4552a(WalkStep walkStep, LatLng latLng) {
        addStationMarker(new MarkerOptions().position(latLng).title("方向:" + walkStep.getAction() + "\n道路:" + walkStep.getRoad()).snippet(walkStep.getInstruction()).visible(this.nodeIconVisible).anchor(0.5f, 0.5f).icon(getWalkBitmapDescriptor()));
    }
}
