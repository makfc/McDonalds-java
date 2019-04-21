package com.amap.api.services.core;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CoreUtil */
/* renamed from: com.amap.api.services.core.d */
public class C1128d {
    /* renamed from: a */
    public static boolean m4976a(String str) {
        return str == null || str.trim().length() == 0;
    }

    /* renamed from: a */
    public static double m4971a(int i) {
        return ((double) i) / 111700.0d;
    }

    /* renamed from: b */
    public static void m4977b(String str) throws AMapException {
        try {
            JSONObject init = JSONObjectInstrumentation.init(str);
            if (init.has("status") && init.has("info")) {
                String string = init.getString("status");
                String string2 = init.getString("info");
                if (!string.equals("1") && string.equals("0")) {
                    if (string2.equals("INVALID_USER_KEY") || string2.equals("INSUFFICIENT_PRIVILEGES") || string2.equals("INVALID_USER_SCODE") || string2.equals("INVALID_USER_SIGNATURE")) {
                        throw new AMapException("key鉴权失败");
                    } else if (string2.equals("SERVICE_NOT_EXIST") || string2.equals("SERVICE_RESPONSE_ERROR") || string2.equals("OVER_QUOTA") || string2.equals("UNKNOWN_ERROR")) {
                        throw new AMapException("未知的错误");
                    } else if (string2.equals("INVALID_PARAMS")) {
                        throw new AMapException("无效的参数 - IllegalArgumentException");
                    } else if (string2.equals("服务正在维护中")) {
                        throw new AMapException("未知的错误");
                    } else if (string2.equals("参数缺失或格式非法")) {
                        throw new AMapException("无效的参数 - IllegalArgumentException");
                    } else if (string2.equals("账号未激活或已被冻结")) {
                        throw new AMapException("无效的参数 - IllegalArgumentException");
                    } else if (string2.startsWith("UNKOWN_ERROR")) {
                        throw new AMapException("未知的错误");
                    } else {
                        throw new AMapException(string2);
                    }
                }
            }
        } catch (JSONException e) {
            C1128d.m4975a(e, "CoreUtil", "paseAuthFailurJson");
        }
    }

    /* renamed from: a */
    public static double m4970a(double d) {
        return Double.parseDouble(new DecimalFormat("0.000000", new DecimalFormatSymbols(Locale.US)).format(d));
    }

    /* renamed from: a */
    public static String m4972a(LatLonPoint latLonPoint) {
        if (latLonPoint == null) {
            return "";
        }
        double a = C1128d.m4970a(latLonPoint.getLongitude());
        return a + "," + C1128d.m4970a(latLonPoint.getLatitude());
    }

    /* renamed from: c */
    public static Date m4978c(String str) {
        Date date = null;
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (ParseException e) {
            C1128d.m4975a(e, "CoreUtil", "parseString2Date");
            return date;
        }
    }

    /* renamed from: d */
    public static Date m4979d(String str) {
        Date date = null;
        if (str == null || str.trim().equals("")) {
            return date;
        }
        try {
            return new SimpleDateFormat("HHmm").parse(str);
        } catch (ParseException e) {
            C1128d.m4975a(e, "CoreUtil", "parseString2Time");
            return date;
        }
    }

    /* renamed from: a */
    public static String m4973a(Date date) {
        return date != null ? new SimpleDateFormat("HH:mm").format(date) : "";
    }

    /* renamed from: e */
    public static Date m4980e(String str) {
        Date date = null;
        if (str == null || str.trim().equals("")) {
            return date;
        }
        try {
            return new SimpleDateFormat("HH:mm").parse(str);
        } catch (ParseException e) {
            C1128d.m4975a(e, "CoreUtil", "parseTime");
            return date;
        }
    }

    /* renamed from: a */
    public static String m4974a(List<LatLonPoint> list) {
        if (list == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < list.size()) {
                LatLonPoint latLonPoint = (LatLonPoint) list.get(i2);
                double a = C1128d.m4970a(latLonPoint.getLongitude());
                stringBuffer.append(a).append(",").append(C1128d.m4970a(latLonPoint.getLatitude()));
                stringBuffer.append(";");
                i = i2 + 1;
            } else {
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                return stringBuffer.toString();
            }
        }
    }

    /* renamed from: a */
    public static void m4975a(Throwable th, String str, String str2) {
        C1099ax b = C1099ax.m4801b();
        if (b != null) {
            b.mo12039b(th, str, str2);
        }
        th.printStackTrace();
    }
}
