package com.amap.api.location;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.location.core.AMapLocException;
import com.amap.api.location.core.AuthManager;
import com.amap.api.location.core.ClientInfoUtil;
import com.amap.api.location.core.CoreUtil;
import com.aps.NetManager;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.amap.api.location.b */
public class AMapWeather implements AMapLocationListener {
    /* renamed from: a */
    C0725a f859a = null;
    /* renamed from: b */
    AMapLocalWeatherListener f860b;
    /* renamed from: c */
    AMapLocationManager f861c;
    /* renamed from: d */
    List<Integer> f862d = new ArrayList();
    /* renamed from: e */
    List<AMapLocalWeatherListener> f863e = new ArrayList();
    /* renamed from: f */
    private Context f864f;
    /* renamed from: g */
    private int f865g;
    /* renamed from: h */
    private AMapLocalWeatherListener f866h;
    /* renamed from: i */
    private boolean f867i = false;

    /* compiled from: AMapWeather */
    /* renamed from: com.amap.api.location.b$a */
    static class C0725a extends Handler {
        /* renamed from: a */
        private WeakReference<AMapWeather> f858a;

        C0725a(AMapWeather aMapWeather, Looper looper) {
            super(looper);
            try {
                this.f858a = new WeakReference(aMapWeather);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        public void handleMessage(android.os.Message r6) {
            /*
            r5 = this;
            super.handleMessage(r6);	 Catch:{ Throwable -> 0x001f }
            r0 = r5.f858a;	 Catch:{ Throwable -> 0x001f }
            r0 = r0.get();	 Catch:{ Throwable -> 0x001f }
            r0 = (com.amap.api.location.AMapWeather) r0;	 Catch:{ Throwable -> 0x001f }
            r1 = r6.what;	 Catch:{ Throwable -> 0x001f }
            switch(r1) {
                case 1: goto L_0x0011;
                case 2: goto L_0x0024;
                case 3: goto L_0x0032;
                case 4: goto L_0x0044;
                default: goto L_0x0010;
            };	 Catch:{ Throwable -> 0x001f }
        L_0x0010:
            return;
        L_0x0011:
            r1 = r0.f860b;	 Catch:{ Throwable -> 0x001f }
            if (r1 == 0) goto L_0x0010;
        L_0x0015:
            r1 = r0.f860b;	 Catch:{ Throwable -> 0x001f }
            r0 = r6.obj;	 Catch:{ Throwable -> 0x001f }
            r0 = (com.amap.api.location.AMapLocalWeatherLive) r0;	 Catch:{ Throwable -> 0x001f }
            r1.onWeatherLiveSearched(r0);	 Catch:{ Throwable -> 0x001f }
            goto L_0x0010;
        L_0x001f:
            r0 = move-exception;
            r0.printStackTrace();
            goto L_0x0010;
        L_0x0024:
            r1 = r0.f860b;	 Catch:{ Throwable -> 0x001f }
            if (r1 == 0) goto L_0x0010;
        L_0x0028:
            r1 = r0.f860b;	 Catch:{ Throwable -> 0x001f }
            r0 = r6.obj;	 Catch:{ Throwable -> 0x001f }
            r0 = (com.amap.api.location.AMapLocalWeatherForecast) r0;	 Catch:{ Throwable -> 0x001f }
            r1.onWeatherForecaseSearched(r0);	 Catch:{ Throwable -> 0x001f }
            goto L_0x0010;
        L_0x0032:
            r1 = r6.obj;	 Catch:{ Throwable -> 0x003f }
            r1 = (com.amap.api.location.AMapLocation) r1;	 Catch:{ Throwable -> 0x003f }
            r2 = new com.amap.api.location.b$a$1;	 Catch:{ Throwable -> 0x003f }
            r2.<init>(r0, r1);	 Catch:{ Throwable -> 0x003f }
            r2.start();	 Catch:{ Throwable -> 0x003f }
            goto L_0x0010;
        L_0x003f:
            r0 = move-exception;
            r0.printStackTrace();	 Catch:{ Throwable -> 0x001f }
            goto L_0x0010;
        L_0x0044:
            r1 = r6.obj;	 Catch:{ Throwable -> 0x00ac }
            r1 = (com.amap.api.location.core.AMapLocException) r1;	 Catch:{ Throwable -> 0x00ac }
            r2 = 0;
            r3 = r2;
        L_0x004a:
            r2 = r0.f862d;	 Catch:{ Throwable -> 0x00a6 }
            r2 = r2.size();	 Catch:{ Throwable -> 0x00a6 }
            if (r3 >= r2) goto L_0x009a;
        L_0x0052:
            r2 = r0.f862d;	 Catch:{ Throwable -> 0x00b2 }
            r2 = r2.get(r3);	 Catch:{ Throwable -> 0x00b2 }
            r2 = (java.lang.Integer) r2;	 Catch:{ Throwable -> 0x00b2 }
            r2 = r2.intValue();	 Catch:{ Throwable -> 0x00b2 }
            r4 = 1;
            if (r2 != r4) goto L_0x0074;
        L_0x0061:
            r4 = new com.amap.api.location.AMapLocalWeatherLive;	 Catch:{ Throwable -> 0x00b2 }
            r4.<init>();	 Catch:{ Throwable -> 0x00b2 }
            r4.mo8249a(r1);	 Catch:{ Throwable -> 0x00b2 }
            r2 = r0.f863e;	 Catch:{ Throwable -> 0x00b2 }
            r2 = r2.get(r3);	 Catch:{ Throwable -> 0x00b2 }
            r2 = (com.amap.api.location.AMapLocalWeatherListener) r2;	 Catch:{ Throwable -> 0x00b2 }
            r2.onWeatherLiveSearched(r4);	 Catch:{ Throwable -> 0x00b2 }
        L_0x0074:
            r2 = r0.f862d;	 Catch:{ Throwable -> 0x00b2 }
            r2 = r2.get(r3);	 Catch:{ Throwable -> 0x00b2 }
            r2 = (java.lang.Integer) r2;	 Catch:{ Throwable -> 0x00b2 }
            r2 = r2.intValue();	 Catch:{ Throwable -> 0x00b2 }
            r4 = 2;
            if (r2 != r4) goto L_0x0096;
        L_0x0083:
            r4 = new com.amap.api.location.AMapLocalWeatherForecast;	 Catch:{ Throwable -> 0x00b2 }
            r4.<init>();	 Catch:{ Throwable -> 0x00b2 }
            r4.mo8241a(r1);	 Catch:{ Throwable -> 0x00b2 }
            r2 = r0.f863e;	 Catch:{ Throwable -> 0x00b2 }
            r2 = r2.get(r3);	 Catch:{ Throwable -> 0x00b2 }
            r2 = (com.amap.api.location.AMapLocalWeatherListener) r2;	 Catch:{ Throwable -> 0x00b2 }
            r2.onWeatherForecaseSearched(r4);	 Catch:{ Throwable -> 0x00b2 }
        L_0x0096:
            r2 = r3 + 1;
            r3 = r2;
            goto L_0x004a;
        L_0x009a:
            r1 = r0.f862d;	 Catch:{ Throwable -> 0x00a6 }
            r1.clear();	 Catch:{ Throwable -> 0x00a6 }
            r0 = r0.f863e;	 Catch:{ Throwable -> 0x00a6 }
            r0.clear();	 Catch:{ Throwable -> 0x00a6 }
            goto L_0x0010;
        L_0x00a6:
            r0 = move-exception;
            r0.printStackTrace();	 Catch:{ Throwable -> 0x00ac }
            goto L_0x0010;
        L_0x00ac:
            r0 = move-exception;
            r0.printStackTrace();	 Catch:{ Throwable -> 0x001f }
            goto L_0x0010;
        L_0x00b2:
            r2 = move-exception;
            goto L_0x0096;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.location.AMapWeather$C0725a.handleMessage(android.os.Message):void");
        }
    }

    public AMapWeather(AMapLocationManager aMapLocationManager, Context context) {
        this.f864f = context;
        this.f861c = aMapLocationManager;
        this.f859a = new C0725a(this, context.getMainLooper());
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8366a(int i, AMapLocalWeatherListener aMapLocalWeatherListener, AMapLocation aMapLocation) {
        try {
            this.f865g = i;
            this.f866h = aMapLocalWeatherListener;
            if (aMapLocation == null) {
                if (this.f862d != null) {
                    this.f862d.add(Integer.valueOf(this.f865g));
                }
                if (this.f863e != null) {
                    this.f863e.add(this.f866h);
                }
                if (!this.f867i) {
                    this.f867i = true;
                    this.f861c.mo8354a(-1, 10.0f, (AMapLocationListener) this, LocationProviderProxy.AMapNetwork, true);
                    return;
                }
                return;
            }
            if (i == 1) {
                mo8367a(aMapLocation, "base", aMapLocalWeatherListener);
            }
            if (i == 2) {
                mo8367a(aMapLocation, "all", aMapLocalWeatherListener);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8367a(AMapLocation aMapLocation, String str, AMapLocalWeatherListener aMapLocalWeatherListener) throws Exception {
        this.f860b = aMapLocalWeatherListener;
        if (aMapLocation != null) {
            AMapLocException aMapLocException;
            byte[] a = m1374a(aMapLocation, str);
            String a2 = m1373a();
            AMapLocException e = new AMapLocException();
            String str2 = null;
            try {
                str2 = NetManager.m5684a().mo13279a(this.f864f, a2, a, "sea");
            } catch (AMapLocException e2) {
                e = e2;
            }
            if ("base".equals(str)) {
                AMapLocalWeatherLive a3;
                if (str2 != null) {
                    aMapLocException = e;
                    a3 = m1372a(str2, aMapLocation);
                } else {
                    a3 = new AMapLocalWeatherLive();
                    aMapLocException = new AMapLocException("http连接失败 - ConnectionException");
                }
                a3.mo8249a(aMapLocException);
                a3.setCity(aMapLocation.getCity());
                a3.setCityCode(aMapLocation.getCityCode());
                a3.setProvince(aMapLocation.getProvince());
                Message obtain = Message.obtain();
                obtain.what = 1;
                obtain.obj = a3;
                this.f859a.sendMessage(obtain);
            } else {
                aMapLocException = e;
            }
            if ("all".equals(str)) {
                AMapLocalWeatherForecast b;
                if (str2 != null) {
                    b = m1375b(str2, aMapLocation);
                } else {
                    b = new AMapLocalWeatherForecast();
                    aMapLocException = new AMapLocException("http连接失败 - ConnectionException");
                }
                b.mo8241a(aMapLocException);
                Message obtain2 = Message.obtain();
                obtain2.what = 2;
                obtain2.obj = b;
                this.f859a.sendMessage(obtain2);
            }
        }
    }

    /* renamed from: a */
    private byte[] m1374a(AMapLocation aMapLocation, String str) throws UnsupportedEncodingException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("output=json&ec=1").append("&extensions=" + str).append("&city=").append(aMapLocation.getAdCode());
        stringBuffer.append("&key=" + ClientInfoUtil.m1422a());
        return AuthManager.m1403b(AuthManager.m1398a(stringBuffer.toString())).getBytes("utf-8");
    }

    /* renamed from: a */
    private String m1373a() {
        return "http://restapi.amap.com/v3/weather/weatherInfo?";
    }

    /* renamed from: a */
    private AMapLocalWeatherLive m1372a(String str, AMapLocation aMapLocation) throws JSONException {
        AMapLocalWeatherLive aMapLocalWeatherLive = new AMapLocalWeatherLive();
        try {
            CoreUtil.m1459a(str);
        } catch (AMapLocException e) {
            aMapLocalWeatherLive.mo8249a(e);
        }
        try {
            JSONArray jSONArray = JSONObjectInstrumentation.init(str).getJSONArray("lives");
            if (jSONArray != null && jSONArray.length() > 0) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(0);
                String a = mo8365a(jSONObject, "weather");
                String a2 = mo8365a(jSONObject, "temperature");
                String a3 = mo8365a(jSONObject, "winddirection");
                String a4 = mo8365a(jSONObject, "windpower");
                String a5 = mo8365a(jSONObject, "humidity");
                String a6 = mo8365a(jSONObject, "reporttime");
                aMapLocalWeatherLive.mo8250a(a);
                aMapLocalWeatherLive.mo8255f(a6);
                aMapLocalWeatherLive.mo8254e(a5);
                aMapLocalWeatherLive.mo8251b(a2);
                aMapLocalWeatherLive.mo8252c(a3);
                aMapLocalWeatherLive.mo8253d(a4);
                aMapLocalWeatherLive.setCity(aMapLocation.getCity());
                aMapLocalWeatherLive.setCityCode(aMapLocation.getCityCode());
                aMapLocalWeatherLive.setProvince(aMapLocation.getProvince());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return aMapLocalWeatherLive;
    }

    /* renamed from: b */
    private AMapLocalWeatherForecast m1375b(String str, AMapLocation aMapLocation) throws JSONException {
        AMapLocalWeatherForecast aMapLocalWeatherForecast = new AMapLocalWeatherForecast();
        try {
            CoreUtil.m1459a(str);
        } catch (AMapLocException e) {
            aMapLocalWeatherForecast.mo8241a(e);
            e.printStackTrace();
        }
        JSONArray jSONArray = JSONObjectInstrumentation.init(str).getJSONArray("forecasts");
        if (jSONArray != null && jSONArray.length() > 0) {
            JSONObject jSONObject = (JSONObject) jSONArray.get(0);
            aMapLocalWeatherForecast.mo8242a(mo8365a(jSONObject, "reporttime"));
            JSONArray jSONArray2 = jSONObject.getJSONArray("casts");
            if (jSONArray2 != null && jSONArray2.length() > 0) {
                List arrayList = new ArrayList();
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= jSONArray2.length()) {
                        break;
                    }
                    AMapLocalDayWeatherForecast aMapLocalDayWeatherForecast = new AMapLocalDayWeatherForecast();
                    jSONObject = (JSONObject) jSONArray2.get(i2);
                    String a = mo8365a(jSONObject, "date");
                    String a2 = mo8365a(jSONObject, "week");
                    String a3 = mo8365a(jSONObject, "dayweather");
                    String a4 = mo8365a(jSONObject, "nightweather");
                    String a5 = mo8365a(jSONObject, "daytemp");
                    String a6 = mo8365a(jSONObject, "nighttemp");
                    String a7 = mo8365a(jSONObject, "daywind");
                    String a8 = mo8365a(jSONObject, "nightwind");
                    String a9 = mo8365a(jSONObject, "daypower");
                    String a10 = mo8365a(jSONObject, "nightpower");
                    aMapLocalDayWeatherForecast.mo8215a(a);
                    aMapLocalDayWeatherForecast.mo8216b(a2);
                    aMapLocalDayWeatherForecast.mo8217c(a3);
                    aMapLocalDayWeatherForecast.mo8218d(a4);
                    aMapLocalDayWeatherForecast.mo8219e(a5);
                    aMapLocalDayWeatherForecast.mo8220f(a6);
                    aMapLocalDayWeatherForecast.mo8221g(a7);
                    aMapLocalDayWeatherForecast.mo8235h(a8);
                    aMapLocalDayWeatherForecast.mo8236i(a9);
                    aMapLocalDayWeatherForecast.mo8237j(a10);
                    aMapLocalDayWeatherForecast.setCity(aMapLocation.getCity());
                    aMapLocalDayWeatherForecast.setCityCode(aMapLocation.getCityCode());
                    aMapLocalDayWeatherForecast.setProvince(aMapLocation.getProvince());
                    arrayList.add(aMapLocalDayWeatherForecast);
                    i = i2 + 1;
                }
                aMapLocalWeatherForecast.mo8243a(arrayList);
            }
        }
        return aMapLocalWeatherForecast;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo8365a(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null) {
            return "";
        }
        String str2 = "";
        if (!jSONObject.has(str) || jSONObject.getString(str).equals("[]")) {
            return str2;
        }
        return jSONObject.optString(str);
    }

    public void onLocationChanged(Location location) {
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public void onLocationChanged(AMapLocation aMapLocation) {
        Message obtain;
        if (aMapLocation != null) {
            try {
                if (aMapLocation.getAMapException() != null && aMapLocation.getAMapException().getErrorCode() == 0 && aMapLocation.getAdCode() != null && aMapLocation.getAdCode().length() > 0) {
                    this.f861c.mo8356a((AMapLocationListener) this);
                    obtain = Message.obtain();
                    obtain.what = 3;
                    obtain.obj = aMapLocation;
                    this.f859a.sendMessage(obtain);
                    this.f867i = false;
                }
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        this.f861c.mo8356a((AMapLocationListener) this);
        obtain = Message.obtain();
        AMapLocException aMapLocException = new AMapLocException(AMapLocException.ERROR_FAILURE_LOCATION);
        obtain.what = 4;
        obtain.obj = aMapLocException;
        this.f859a.sendMessage(obtain);
        this.f867i = false;
    }
}
