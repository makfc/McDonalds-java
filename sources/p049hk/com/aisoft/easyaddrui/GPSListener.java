package p049hk.com.aisoft.easyaddrui;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

/* renamed from: hk.com.aisoft.easyaddrui.GPSListener */
public class GPSListener implements LocationListener {
    public String provider1 = "";
    public String provider2 = "";

    public void onLocationChanged(Location locFromGps) {
        eaView.sGPSY = locFromGps.getLatitude();
        eaView.sGPSX = locFromGps.getLongitude();
        if (ContextCompat.checkSelfPermission(eaView.sContext, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            eaView.sLocationManager.removeUpdates(this);
        }
        ResponseTrigger.btnGPSPressAction();
    }

    public void onProviderDisabled(String provider) {
        if (provider.equals("gps")) {
            this.provider1 = "ERR";
        } else {
            this.provider2 = "ERR";
        }
        if (this.provider1.equals("ERR") && this.provider2.equals("ERR")) {
            if (eaView.sEALang.equals("zh-HK")) {
                Toast.makeText(eaView.sContext, "未开启定位服务", 0).show();
            } else {
                Toast.makeText(eaView.sContext, "GPS is disabled", 0).show();
            }
            eaView.sResponse = "onProviderDisabled";
        }
    }

    public void onProviderEnabled(String provider) {
        if (eaView.sEALang.equals("zh-HK")) {
            Toast.makeText(eaView.sContext, "已开启定位服务", 0).show();
        } else {
            Toast.makeText(eaView.sContext, "GPS is enabled", 0).show();
        }
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.v("AAA", "onStatusChanged");
    }
}
