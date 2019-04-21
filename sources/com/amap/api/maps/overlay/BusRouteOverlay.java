package com.amap.api.maps.overlay;

import android.content.Context;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusStep;
import com.amap.api.services.route.RouteBusLineItem;
import com.amap.api.services.route.WalkStep;
import java.util.Iterator;
import java.util.List;

public class BusRouteOverlay extends RouteOverlay {
    /* renamed from: a */
    private BusPath f3334a;
    /* renamed from: b */
    private LatLng f3335b;

    public /* bridge */ /* synthetic */ void removeFromMap() {
        super.removeFromMap();
    }

    public /* bridge */ /* synthetic */ void setNodeIconVisibility(boolean z) {
        super.setNodeIconVisibility(z);
    }

    public /* bridge */ /* synthetic */ void zoomToSpan() {
        super.zoomToSpan();
    }

    public BusRouteOverlay(Context context, AMap aMap, BusPath busPath, LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        super(context);
        this.f3334a = busPath;
        this.startPoint = AMapServicesUtil.m4556a(latLonPoint);
        this.endPoint = AMapServicesUtil.m4556a(latLonPoint2);
        this.mAMap = aMap;
    }

    public void addToMap() {
        try {
            List steps = this.f3334a.getSteps();
            for (int i = 0; i < steps.size(); i++) {
                BusStep busStep = (BusStep) steps.get(i);
                if (i < steps.size() - 1) {
                    BusStep busStep2 = (BusStep) steps.get(i + 1);
                    if (!(busStep.getWalk() == null || busStep.getBusLine() == null)) {
                        m4526b(busStep);
                    }
                    if (!(busStep.getBusLine() == null || busStep2.getWalk() == null)) {
                        m4533c(busStep, busStep2);
                    }
                    if (!(busStep.getBusLine() == null || busStep2.getWalk() != null || busStep2.getBusLine() == null)) {
                        m4527b(busStep, busStep2);
                    }
                    if (!(busStep.getBusLine() == null || busStep2.getWalk() != null || busStep2.getBusLine() == null)) {
                        m4523a(busStep, busStep2);
                    }
                }
                if (busStep.getWalk() != null && busStep.getWalk().getSteps().size() > 0) {
                    m4522a(busStep);
                } else if (busStep.getBusLine() == null) {
                    m4519a(this.f3335b, this.endPoint);
                }
                if (busStep.getBusLine() != null) {
                    RouteBusLineItem busLine = busStep.getBusLine();
                    m4524a(busLine);
                    m4528b(busLine);
                }
            }
            addStartAndEndMarker();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m4522a(BusStep busStep) {
        List steps = busStep.getWalk().getSteps();
        for (int i = 0; i < steps.size(); i++) {
            WalkStep walkStep = (WalkStep) steps.get(i);
            if (i == 0) {
                m4520a(AMapServicesUtil.m4556a((LatLonPoint) walkStep.getPolyline().get(0)), walkStep.getRoad(), m4532c(steps));
            }
            List a = AMapServicesUtil.m4557a(walkStep.getPolyline());
            this.f3335b = (LatLng) a.get(a.size() - 1);
            m4529b(a);
            if (i < steps.size() - 1) {
                LatLng latLng = (LatLng) a.get(a.size() - 1);
                LatLng a2 = AMapServicesUtil.m4556a((LatLonPoint) ((WalkStep) steps.get(i + 1)).getPolyline().get(0));
                if (!latLng.equals(a2)) {
                    m4519a(latLng, a2);
                }
            }
        }
    }

    /* renamed from: a */
    private void m4524a(RouteBusLineItem routeBusLineItem) {
        m4525a(routeBusLineItem.getPolyline());
    }

    /* renamed from: a */
    private void m4525a(List<LatLonPoint> list) {
        if (list.size() >= 1) {
            addPolyLine(new PolylineOptions().width(getRouteWidth()).color(getBusColor()).addAll(AMapServicesUtil.m4557a((List) list)));
        }
    }

    /* renamed from: a */
    private void m4520a(LatLng latLng, String str, String str2) {
        addStationMarker(new MarkerOptions().position(latLng).title(str).snippet(str2).anchor(0.5f, 0.5f).visible(this.nodeIconVisible).icon(getWalkBitmapDescriptor()));
    }

    /* renamed from: b */
    private void m4528b(RouteBusLineItem routeBusLineItem) {
        LatLng a = AMapServicesUtil.m4556a(routeBusLineItem.getDepartureBusStation().getLatLonPoint());
        String busLineName = routeBusLineItem.getBusLineName();
        addStationMarker(new MarkerOptions().position(a).title(busLineName).snippet(m4531c(routeBusLineItem)).anchor(0.5f, 0.5f).visible(this.nodeIconVisible).icon(getBusBitmapDescriptor()));
    }

    /* renamed from: a */
    private void m4523a(BusStep busStep, BusStep busStep2) {
        LatLng a = AMapServicesUtil.m4556a(m4535e(busStep));
        LatLng a2 = AMapServicesUtil.m4556a(m4536f(busStep2));
        if (a2.latitude - a.latitude > 1.0E-4d || a2.longitude - a.longitude > 1.0E-4d) {
            drawLineArrow(a, a2);
        }
    }

    /* renamed from: b */
    private void m4527b(BusStep busStep, BusStep busStep2) {
        LatLng a = AMapServicesUtil.m4556a(m4535e(busStep));
        LatLng a2 = AMapServicesUtil.m4556a(m4536f(busStep2));
        if (!a.equals(a2)) {
            drawLineArrow(a, a2);
        }
    }

    /* renamed from: c */
    private void m4533c(BusStep busStep, BusStep busStep2) {
        LatLonPoint e = m4535e(busStep);
        LatLonPoint c = m4530c(busStep2);
        if (!e.equals(c)) {
            m4521a(e, c);
        }
    }

    /* renamed from: b */
    private void m4526b(BusStep busStep) {
        LatLonPoint d = m4534d(busStep);
        LatLonPoint f = m4536f(busStep);
        if (!d.equals(f)) {
            m4521a(d, f);
        }
    }

    /* renamed from: c */
    private LatLonPoint m4530c(BusStep busStep) {
        return (LatLonPoint) ((WalkStep) busStep.getWalk().getSteps().get(0)).getPolyline().get(0);
    }

    /* renamed from: a */
    private void m4521a(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        m4519a(AMapServicesUtil.m4556a(latLonPoint), AMapServicesUtil.m4556a(latLonPoint2));
    }

    /* renamed from: a */
    private void m4519a(LatLng latLng, LatLng latLng2) {
        addPolyLine(new PolylineOptions().add(latLng, latLng2).width(getRouteWidth()).color(getWalkColor()));
    }

    /* renamed from: b */
    private void m4529b(List<LatLng> list) {
        addPolyLine(new PolylineOptions().addAll(list).color(getWalkColor()).width(getRouteWidth()));
    }

    /* renamed from: c */
    private String m4532c(List<WalkStep> list) {
        float f = 0.0f;
        Iterator it = list.iterator();
        while (true) {
            float f2 = f;
            if (!it.hasNext()) {
                return "步行" + f2 + "米";
            }
            f = ((WalkStep) it.next()).getDistance() + f2;
        }
    }

    public void drawLineArrow(LatLng latLng, LatLng latLng2) {
        addPolyLine(new PolylineOptions().add(latLng, latLng2).width(3.0f).color(getBusColor()).width(getRouteWidth()));
    }

    /* renamed from: c */
    private String m4531c(RouteBusLineItem routeBusLineItem) {
        return "(" + routeBusLineItem.getDepartureBusStation().getBusStationName() + "-->" + routeBusLineItem.getArrivalBusStation().getBusStationName() + ") 经过" + (routeBusLineItem.getPassStationNum() + 1) + "站";
    }

    /* renamed from: d */
    private LatLonPoint m4534d(BusStep busStep) {
        List steps = busStep.getWalk().getSteps();
        steps = ((WalkStep) steps.get(steps.size() - 1)).getPolyline();
        return (LatLonPoint) steps.get(steps.size() - 1);
    }

    /* renamed from: e */
    private LatLonPoint m4535e(BusStep busStep) {
        List polyline = busStep.getBusLine().getPolyline();
        return (LatLonPoint) polyline.get(polyline.size() - 1);
    }

    /* renamed from: f */
    private LatLonPoint m4536f(BusStep busStep) {
        return (LatLonPoint) busStep.getBusLine().getPolyline().get(0);
    }
}
