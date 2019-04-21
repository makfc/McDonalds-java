package com.amap.api.services.geocoder;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.C1128d;
import com.amap.api.services.core.GeocodingHandler;
import com.amap.api.services.core.ManifestConfig;
import com.amap.api.services.core.ReverseGeocodingHandler;
import java.util.List;

public final class GeocodeSearch {
    public static final String AMAP = "autonavi";
    public static final String GPS = "gps";
    /* renamed from: a */
    Handler f3867a = new C1149c(this);
    /* renamed from: b */
    private Context f3868b;
    /* renamed from: c */
    private OnGeocodeSearchListener f3869c;

    public interface OnGeocodeSearchListener {
        void onGeocodeSearched(GeocodeResult geocodeResult, int i);

        void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i);
    }

    public GeocodeSearch(Context context) {
        this.f3868b = context.getApplicationContext();
    }

    public RegeocodeAddress getFromLocation(RegeocodeQuery regeocodeQuery) throws AMapException {
        ManifestConfig.m5058a(this.f3868b);
        return (RegeocodeAddress) new ReverseGeocodingHandler(this.f3868b, regeocodeQuery).mo11981g();
    }

    public List<GeocodeAddress> getFromLocationName(GeocodeQuery geocodeQuery) throws AMapException {
        ManifestConfig.m5058a(this.f3868b);
        return (List) new GeocodingHandler(this.f3868b, geocodeQuery).mo11981g();
    }

    public void setOnGeocodeSearchListener(OnGeocodeSearchListener onGeocodeSearchListener) {
        this.f3869c = onGeocodeSearchListener;
    }

    public void getFromLocationAsyn(final RegeocodeQuery regeocodeQuery) {
        new Thread(new Runnable() {
            public void run() {
                Message message = new Message();
                try {
                    message.what = 101;
                    RegeocodeAddress fromLocation = GeocodeSearch.this.getFromLocation(regeocodeQuery);
                    message.arg1 = 0;
                    message.obj = new RegeocodeResult(regeocodeQuery, fromLocation);
                } catch (AMapException e) {
                    C1128d.m4975a(e, "GeocodeSearch", "getFromLocationAsyn");
                    message.arg1 = e.getErrorCode();
                } finally {
                    GeocodeSearch.this.f3867a.sendMessage(message);
                }
            }
        }).start();
    }

    public void getFromLocationNameAsyn(final GeocodeQuery geocodeQuery) {
        new Thread(new Runnable() {
            public void run() {
                Message message = new Message();
                try {
                    message.what = 100;
                    List fromLocationName = GeocodeSearch.this.getFromLocationName(geocodeQuery);
                    message.arg1 = 0;
                    message.obj = new GeocodeResult(geocodeQuery, fromLocationName);
                } catch (AMapException e) {
                    C1128d.m4975a(e, "GeocodeSearch", "getFromLocationNameAsyn");
                    message.arg1 = e.getErrorCode();
                } finally {
                    GeocodeSearch.this.f3867a.sendMessage(message);
                }
            }
        }).start();
    }
}
