package com.aps;

import android.text.TextUtils;
import com.amap.api.location.core.AMapLocException;
import com.amap.api.services.district.DistrictSearchQuery;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.aps.c */
public class AmapLoc {
    /* renamed from: a */
    private String f4378a = "";
    /* renamed from: b */
    private double f4379b = 0.0d;
    /* renamed from: c */
    private double f4380c = 0.0d;
    /* renamed from: d */
    private float f4381d = 0.0f;
    /* renamed from: e */
    private float f4382e = 0.0f;
    /* renamed from: f */
    private float f4383f = 0.0f;
    /* renamed from: g */
    private long f4384g = 0;
    /* renamed from: h */
    private AMapLocException f4385h = new AMapLocException();
    /* renamed from: i */
    private String f4386i = "new";
    /* renamed from: j */
    private String f4387j = "";
    /* renamed from: k */
    private String f4388k = "";
    /* renamed from: l */
    private String f4389l = "";
    /* renamed from: m */
    private String f4390m = "";
    /* renamed from: n */
    private String f4391n = "";
    /* renamed from: o */
    private String f4392o = "";
    /* renamed from: p */
    private String f4393p = "";
    /* renamed from: q */
    private String f4394q = "";
    /* renamed from: r */
    private String f4395r = "";
    /* renamed from: s */
    private String f4396s = "";
    /* renamed from: t */
    private String f4397t = "";
    /* renamed from: u */
    private String f4398u = "";
    /* renamed from: v */
    private int f4399v = -1;
    /* renamed from: w */
    private String f4400w = "";
    /* renamed from: x */
    private String f4401x = "";
    /* renamed from: y */
    private JSONObject f4402y = null;

    /* renamed from: a */
    public AMapLocException mo13191a() {
        return this.f4385h;
    }

    /* renamed from: a */
    public void mo13195a(AMapLocException aMapLocException) {
        this.f4385h = aMapLocException;
    }

    /* renamed from: b */
    public String mo13198b() {
        return this.f4397t;
    }

    /* renamed from: a */
    public void mo13196a(String str) {
        this.f4397t = str;
    }

    /* renamed from: c */
    public String mo13202c() {
        return this.f4398u;
    }

    /* renamed from: b */
    public void mo13201b(String str) {
        this.f4398u = str;
    }

    /* renamed from: d */
    public String mo13204d() {
        return this.f4401x;
    }

    /* renamed from: c */
    public void mo13203c(String str) {
        this.f4401x = str;
    }

    /* renamed from: e */
    public int mo13206e() {
        return this.f4399v;
    }

    /* renamed from: d */
    public void mo13205d(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f4399v = -1;
        } else if (this.f4378a.equals("gps")) {
            this.f4399v = 0;
        } else if (str.equals("0")) {
            this.f4399v = 0;
        } else if (str.equals("1")) {
            this.f4399v = 1;
        } else {
            this.f4399v = -1;
        }
    }

    /* renamed from: e */
    public void mo13207e(String str) {
        this.f4400w = str;
    }

    public AmapLoc(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                this.f4378a = jSONObject.getString("provider");
                this.f4379b = jSONObject.getDouble("lon");
                this.f4380c = jSONObject.getDouble("lat");
                this.f4381d = (float) jSONObject.getLong("accuracy");
                this.f4382e = (float) jSONObject.getLong("speed");
                this.f4383f = (float) jSONObject.getLong("bearing");
                this.f4384g = jSONObject.getLong("time");
                this.f4386i = jSONObject.getString("type");
                this.f4387j = jSONObject.getString("retype");
                this.f4388k = jSONObject.getString("citycode");
                this.f4389l = jSONObject.getString("desc");
                this.f4390m = jSONObject.getString("adcode");
                this.f4391n = jSONObject.getString(DistrictSearchQuery.KEYWORDS_COUNTRY);
                this.f4392o = jSONObject.getString(DistrictSearchQuery.KEYWORDS_PROVINCE);
                this.f4393p = jSONObject.getString(DistrictSearchQuery.KEYWORDS_CITY);
                this.f4394q = jSONObject.getString("road");
                this.f4395r = jSONObject.getString("street");
                this.f4396s = jSONObject.getString("poiname");
                this.f4398u = jSONObject.getString("floor");
                this.f4397t = jSONObject.getString("poiid");
                String string = jSONObject.getString("coord");
                if (TextUtils.isEmpty(string)) {
                    this.f4399v = -1;
                } else if (string.equals("0")) {
                    this.f4399v = 0;
                } else if (string.equals("1")) {
                    this.f4399v = 1;
                } else {
                    this.f4399v = -1;
                }
                this.f4400w = jSONObject.getString("mcell");
                this.f4401x = jSONObject.getString(DistrictSearchQuery.KEYWORDS_DISTRICT);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: f */
    public String mo13208f() {
        return this.f4378a;
    }

    /* renamed from: f */
    public void mo13209f(String str) {
        this.f4378a = str;
    }

    /* renamed from: g */
    public double mo13210g() {
        return this.f4379b;
    }

    /* renamed from: a */
    public void mo13192a(double d) {
        this.f4379b = d;
    }

    /* renamed from: h */
    public double mo13212h() {
        return this.f4380c;
    }

    /* renamed from: b */
    public void mo13199b(double d) {
        this.f4380c = d;
    }

    /* renamed from: i */
    public float mo13214i() {
        return this.f4381d;
    }

    /* renamed from: a */
    public void mo13193a(float f) {
        this.f4381d = f;
    }

    /* renamed from: b */
    public void mo13200b(float f) {
        this.f4383f = f;
    }

    /* renamed from: j */
    public long mo13216j() {
        return this.f4384g;
    }

    /* renamed from: a */
    public void mo13194a(long j) {
        this.f4384g = j;
    }

    /* renamed from: k */
    public String mo13218k() {
        return this.f4386i;
    }

    /* renamed from: g */
    public void mo13211g(String str) {
        this.f4386i = str;
    }

    /* renamed from: l */
    public String mo13220l() {
        return this.f4387j;
    }

    /* renamed from: h */
    public void mo13213h(String str) {
        this.f4387j = str;
    }

    /* renamed from: m */
    public String mo13222m() {
        return this.f4388k;
    }

    /* renamed from: i */
    public void mo13215i(String str) {
        this.f4388k = str;
    }

    /* renamed from: n */
    public String mo13224n() {
        return this.f4389l;
    }

    /* renamed from: j */
    public void mo13217j(String str) {
        this.f4389l = str;
    }

    /* renamed from: o */
    public String mo13226o() {
        return this.f4390m;
    }

    /* renamed from: k */
    public void mo13219k(String str) {
        this.f4390m = str;
    }

    /* renamed from: p */
    public String mo13228p() {
        return this.f4391n;
    }

    /* renamed from: l */
    public void mo13221l(String str) {
        this.f4391n = str;
    }

    /* renamed from: q */
    public String mo13230q() {
        return this.f4392o;
    }

    /* renamed from: m */
    public void mo13223m(String str) {
        this.f4392o = str;
    }

    /* renamed from: r */
    public String mo13232r() {
        return this.f4393p;
    }

    /* renamed from: n */
    public void mo13225n(String str) {
        this.f4393p = str;
    }

    /* renamed from: s */
    public String mo13233s() {
        return this.f4394q;
    }

    /* renamed from: o */
    public void mo13227o(String str) {
        this.f4394q = str;
    }

    /* renamed from: t */
    public String mo13234t() {
        return this.f4395r;
    }

    /* renamed from: p */
    public void mo13229p(String str) {
        this.f4395r = str;
    }

    /* renamed from: u */
    public String mo13235u() {
        return this.f4396s;
    }

    /* renamed from: q */
    public void mo13231q(String str) {
        this.f4396s = str;
    }

    /* renamed from: v */
    public JSONObject mo13236v() {
        return this.f4402y;
    }

    /* renamed from: a */
    public void mo13197a(JSONObject jSONObject) {
        this.f4402y = jSONObject;
    }

    /* renamed from: w */
    public String mo13237w() {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject();
            jSONObject.put("provider", this.f4378a);
            jSONObject.put("lon", this.f4379b);
            jSONObject.put("lat", this.f4380c);
            jSONObject.put("accuracy", (double) this.f4381d);
            jSONObject.put("speed", (double) this.f4382e);
            jSONObject.put("bearing", (double) this.f4383f);
            jSONObject.put("time", this.f4384g);
            jSONObject.put("type", this.f4386i);
            jSONObject.put("retype", this.f4387j);
            jSONObject.put("citycode", this.f4388k);
            jSONObject.put("desc", this.f4389l);
            jSONObject.put("adcode", this.f4390m);
            jSONObject.put(DistrictSearchQuery.KEYWORDS_COUNTRY, this.f4391n);
            jSONObject.put(DistrictSearchQuery.KEYWORDS_PROVINCE, this.f4392o);
            jSONObject.put(DistrictSearchQuery.KEYWORDS_CITY, this.f4393p);
            jSONObject.put("road", this.f4394q);
            jSONObject.put("street", this.f4395r);
            jSONObject.put("poiname", this.f4396s);
            jSONObject.put("poiid", this.f4397t);
            jSONObject.put("floor", this.f4398u);
            jSONObject.put("coord", this.f4399v);
            jSONObject.put("mcell", this.f4400w);
            jSONObject.put(DistrictSearchQuery.KEYWORDS_DISTRICT, this.f4401x);
        } catch (JSONException e) {
            C1269v.m5728a(e);
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
    }
}
