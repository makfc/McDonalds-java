package com.aps;

import com.amap.api.location.LocationManagerProxy;
import com.amap.api.services.district.DistrictSearchQuery;
import com.facebook.stetho.common.Utf8Charset;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.SAXParserFactory;
import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/* renamed from: com.aps.o */
public class Parser {

    /* compiled from: Parser */
    /* renamed from: com.aps.o$a */
    private static class C1263a extends DefaultHandler {
        /* renamed from: a */
        public AmapLoc f4492a;
        /* renamed from: b */
        private String f4493b;

        private C1263a() {
            this.f4492a = new AmapLoc();
            this.f4493b = "";
        }

        public void characters(char[] cArr, int i, int i2) {
            this.f4493b = String.valueOf(cArr, i, i2);
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) {
            this.f4493b = "";
        }

        public void endElement(String str, String str2, String str3) {
            if (str2.equals("retype")) {
                this.f4492a.mo13213h(this.f4493b);
            } else if (str2.equals("adcode")) {
                this.f4492a.mo13219k(this.f4493b);
            } else if (str2.equals("citycode")) {
                this.f4492a.mo13215i(this.f4493b);
            } else if (str2.equals(Parameters.RADIUS)) {
                try {
                    this.f4492a.mo13193a(Float.valueOf(this.f4493b).floatValue());
                } catch (Throwable th) {
                    th.printStackTrace();
                    this.f4492a.mo13193a(3891.0f);
                }
            } else if (str2.equals("cenx")) {
                try {
                    this.f4493b = Storage.m5711a(Double.valueOf(this.f4493b), "#.000000");
                    this.f4492a.mo13192a(Double.valueOf(this.f4493b).doubleValue());
                } catch (Throwable th2) {
                    th2.printStackTrace();
                    this.f4492a.mo13192a(0.0d);
                }
            } else if (str2.equals("ceny")) {
                try {
                    this.f4493b = Storage.m5711a(Double.valueOf(this.f4493b), "#.000000");
                    this.f4492a.mo13199b(Double.valueOf(this.f4493b).doubleValue());
                } catch (Throwable th22) {
                    th22.printStackTrace();
                    this.f4492a.mo13199b(0.0d);
                }
            } else if (str2.equals("desc")) {
                this.f4492a.mo13217j(this.f4493b);
            } else if (str2.equals(DistrictSearchQuery.KEYWORDS_COUNTRY)) {
                this.f4492a.mo13221l(this.f4493b);
            } else if (str2.equals(DistrictSearchQuery.KEYWORDS_PROVINCE)) {
                this.f4492a.mo13223m(this.f4493b);
            } else if (str2.equals(DistrictSearchQuery.KEYWORDS_CITY)) {
                this.f4492a.mo13225n(this.f4493b);
            } else if (str2.equals("road")) {
                this.f4492a.mo13227o(this.f4493b);
            } else if (str2.equals("street")) {
                this.f4492a.mo13229p(this.f4493b);
            } else if (str2.equals("poiname")) {
                this.f4492a.mo13231q(this.f4493b);
            } else if (str2.equals("BIZ")) {
                if (this.f4492a.mo13236v() == null) {
                    this.f4492a.mo13197a(new JSONObject());
                }
                try {
                    this.f4492a.mo13236v().put("BIZ", this.f4493b);
                } catch (Throwable th222) {
                    th222.printStackTrace();
                }
            } else if (str2.equals("flr")) {
                this.f4492a.mo13201b(this.f4493b);
            } else if (str2.equals("pid")) {
                this.f4492a.mo13196a(this.f4493b);
            } else if (str2.equals("apiTime")) {
                try {
                    if (!"".equals(this.f4493b)) {
                        this.f4492a.mo13194a(Long.parseLong(this.f4493b));
                    }
                } catch (Throwable th2222) {
                    th2222.printStackTrace();
                    this.f4492a.mo13194a(C1269v.m5726a());
                }
            } else if (str2.equals("coord")) {
                try {
                    this.f4492a.mo13205d(this.f4493b);
                } catch (Throwable th22222) {
                    th22222.printStackTrace();
                }
            } else if (str2.equals("mcell")) {
                try {
                    this.f4492a.mo13207e(this.f4493b);
                } catch (Throwable th222222) {
                    th222222.printStackTrace();
                }
            } else if (str2.equals(DistrictSearchQuery.KEYWORDS_DISTRICT)) {
                try {
                    this.f4492a.mo13203c(this.f4493b);
                } catch (Throwable th2222222) {
                    th2222222.printStackTrace();
                }
            }
            if (this.f4492a.mo13236v() == null) {
                this.f4492a.mo13197a(new JSONObject());
            }
            try {
                if (str2.equals("eab")) {
                    this.f4492a.mo13236v().put(str2, this.f4493b);
                } else if (str2.equals("ctl")) {
                    this.f4492a.mo13236v().put(str2, this.f4493b);
                } else if (str2.equals("suc")) {
                    this.f4492a.mo13236v().put(str2, this.f4493b);
                } else if (str2.equals("spa")) {
                    this.f4492a.mo13236v().put(str2, this.f4493b);
                }
            } catch (Throwable th22222222) {
                th22222222.printStackTrace();
            }
        }
    }

    protected Parser() {
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public AmapLoc mo13285a(String str) {
        if (str == null || str.length() == 0 || str.contains("SuccessCode=\"0\"")) {
            return null;
        }
        InputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(str.getBytes(Utf8Charset.NAME));
        } catch (UnsupportedEncodingException e) {
            byteArrayInputStream = null;
        }
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        C1263a c1263a = new C1263a();
        if (byteArrayInputStream != null) {
            try {
                newInstance.newSAXParser().parse(byteArrayInputStream, c1263a);
                byteArrayInputStream.close();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        c1263a.f4492a.mo13209f(LocationManagerProxy.NETWORK_PROVIDER);
        if (c1263a.f4492a.mo13216j() == 0) {
            c1263a.f4492a.mo13194a(C1269v.m5726a());
        }
        return c1263a.f4492a;
    }
}
