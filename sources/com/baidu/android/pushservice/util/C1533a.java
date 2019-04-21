package com.baidu.android.pushservice.util;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.baidu.android.pushservice.C1328a;
import com.baidu.android.pushservice.PushSettings;
import com.baidu.android.pushservice.p026j.C1281c;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p028a.p029a.C1312e;
import com.baidu.android.pushservice.p031c.C1332b;
import com.baidu.android.pushservice.p031c.C1339h;
import com.baidu.android.pushservice.p033e.C1370b;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p037i.C1448m;
import com.baidu.android.pushservice.p039k.C1471e;
import com.facebook.stetho.common.Utf8Charset;
import com.mcdonalds.sdk.connectors.middleware.model.DCSPreference;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/* renamed from: com.baidu.android.pushservice.util.a */
public class C1533a {
    /* renamed from: a */
    private static String f5371a = "http://api.tuisong.baidu.com/rest/3.0/clientad/update_ad_status";

    /* renamed from: a */
    protected static String m6886a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String substring = str.substring(str.indexOf("ad_id"), str.indexOf("/channelid"));
        return (TextUtils.isEmpty(substring) || substring.length() <= 0) ? null : substring.substring(substring.indexOf("=") + 1, substring.length());
    }

    /* renamed from: a */
    public static void m6887a(final Context context, final int i) {
        C1462d.m6637a().mo13938a(new C1281c("updateADStatus", (short) 98) {
            /* renamed from: a */
            public void mo13487a() {
                DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(C1533a.f5371a);
                defaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(10000));
                defaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(10000));
                httpPost.addHeader(TransactionStateUtil.CONTENT_TYPE_HEADER, "application/x-www-form-urlencoded;charset=utf-8");
                httpPost.addHeader("User-Agent", "BCCS_SDK/3.0");
                List arrayList = new ArrayList();
                C1533a.m6889a(context, arrayList, i);
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(arrayList, Utf8Charset.NAME));
                    HttpResponse execute = !(defaultHttpClient instanceof HttpClient) ? defaultHttpClient.execute(httpPost) : HttpInstrumentation.execute(defaultHttpClient, httpPost);
                    String entityUtils = EntityUtils.toString(execute.getEntity());
                    if (execute.getStatusLine().getStatusCode() == 200) {
                        C1425a.m6441b("AdvertiseUtility", "<<<  updateADStatus  request succeed  return string:  " + entityUtils);
                        if (C1328a.m6006b() > 0) {
                            C1578v.m7095b("  updateADStatus request succeed returnString :   " + entityUtils, context);
                            return;
                        }
                        return;
                    }
                    C1425a.m6441b("AdvertiseUtility", "updateADStatus request failed  " + execute.getStatusLine());
                    if (C1328a.m6006b() > 0) {
                        C1578v.m7095b("  updateADStatus  request failed,  returnString :   " + entityUtils, context);
                    }
                    C1339h c = C1332b.m6020a(context).mo13599c(context.getPackageName());
                    String str = null;
                    if (c != null) {
                        str = c.mo13584a();
                    }
                    C1578v.m7071a(context, i, str, PushSettings.m5874a(context), C1471e.m6687a(context), C1328a.m6003a());
                    C1425a.m6441b("AdvertiseUtility", "<<< networkRegister return string :  " + entityUtils);
                } catch (Exception e) {
                    C1425a.m6440a("AdvertiseUtility", e);
                }
            }
        });
    }

    /* renamed from: a */
    public static void m6888a(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str.trim()) && C1312e.m5924a(Uri.parse(str.trim()))) {
            C1425a.m6441b("AdvertiseUtility", "<<< URL IS VALID return string :  ");
            final Context context2 = context;
            final String str3 = str;
            final String str4 = str2;
            C1462d.m6637a().mo13938a(new C1281c("updateACKStatus", (short) 98) {
                /* renamed from: a */
                public void mo13487a() {
                    DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                    defaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(10000));
                    defaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(10000));
                    String b = C1533a.m6891b(context2, str3, str4);
                    if (!TextUtils.isEmpty(b)) {
                        URI uri;
                        try {
                            URL url = new URL(b);
                            uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
                        } catch (Exception e) {
                            C1425a.m6440a("AdvertiseUtility", e);
                            uri = null;
                        }
                        if (uri != null) {
                            C1425a.m6441b("AdvertiseUtility", "<<<  pushACKUrl  =  :  " + uri.toString());
                            HttpPost httpPost = new HttpPost(uri);
                            httpPost.addHeader(TransactionStateUtil.CONTENT_TYPE_HEADER, "application/x-www-form-urlencoded;charset=utf-8");
                            httpPost.addHeader("User-Agent", "BCCS_SDK/3.0");
                            try {
                                HttpResponse execute = !(defaultHttpClient instanceof HttpClient) ? defaultHttpClient.execute(httpPost) : HttpInstrumentation.execute(defaultHttpClient, httpPost);
                                String entityUtils = EntityUtils.toString(execute.getEntity());
                                if (execute.getStatusLine().getStatusCode() == 200) {
                                    C1425a.m6441b("AdvertiseUtility", "<<< networkRegister return string :  " + entityUtils);
                                    return;
                                }
                                C1425a.m6441b("AdvertiseUtility", "networkRegister request failed  " + execute.getStatusLine());
                                C1339h c = C1332b.m6020a(context2).mo13599c(context2.getPackageName());
                                C1578v.m7075a(context2, c != null ? c.mo13584a() : null, PushSettings.m5874a(context2), C1471e.m6687a(context2), C1533a.m6886a(str3), Integer.parseInt(str4), C1533a.m6892b(str4));
                            } catch (Exception e2) {
                                C1425a.m6440a("AdvertiseUtility", e2);
                            }
                        }
                    }
                }
            });
        }
    }

    /* renamed from: a */
    protected static void m6889a(Context context, List<NameValuePair> list, int i) {
        try {
            C1370b.m6205a((List) list);
            list.add(new BasicNameValuePair("apikey", C1533a.m6894c(context)));
            list.add(new BasicNameValuePair("cuid", C1471e.m6687a(context)));
            list.add(new BasicNameValuePair("channel_id", PushSettings.m5874a(context)));
            list.add(new BasicNameValuePair("sdk_version", C1328a.m6003a() + ""));
            list.add(new BasicNameValuePair("ad_status", i + ""));
            C1425a.m6441b("AdvertiseUtility", "params  = " + list.toString());
            if (C1328a.m6006b() > 0) {
                C1578v.m7095b("  updateADStatus =  " + list.toString(), context);
            }
        } catch (Exception e) {
            C1425a.m6440a("AdvertiseUtility", e);
        }
    }

    /* renamed from: a */
    public static boolean m6890a(Context context) {
        return C1533a.m6896d(context);
    }

    /* renamed from: b */
    protected static String m6891b(Context context, String str, String str2) {
        try {
            String b = C1533a.m6892b(str2);
            C1339h c = C1332b.m6020a(context).mo13599c(context.getPackageName());
            String a = c != null ? c.mo13584a() : null;
            String a2 = C1471e.m6687a(context);
            int indexOf = str.indexOf("/src");
            if (TextUtils.isEmpty(str) || str.length() <= 0) {
                return null;
            }
            return str.substring(0, indexOf) + "/appid=" + a + "/cuid=" + a2 + "/errorcode=" + str2 + "/errormsg=" + b;
        } catch (Exception e) {
            C1425a.m6440a("AdvertiseUtility", e);
            return null;
        }
    }

    /* renamed from: b */
    protected static String m6892b(String str) {
        return str.equals("10") ? "commandservice_receiver_null" : str.equals(DCSPreference.ID_FOOD_PREFERENCE_BREAKFAST) ? "notification_disable" : null;
    }

    /* renamed from: b */
    public static boolean m6893b(Context context) {
        return C1533a.m6896d(context) && !C1533a.m6897e(context);
    }

    /* renamed from: c */
    private static String m6894c(Context context) {
        try {
            return C1550n.m6960b(context, "com.baidu.android.pushservice.PushManager.LOGIN_TYPE", 0) == 0 ? C1550n.m6955a(context, "com.baidu.android.pushservice.PushManager.LONGIN_VALUE") : null;
        } catch (Exception e) {
            C1425a.m6440a("AdvertiseUtility", e);
            return null;
        }
    }

    /* renamed from: c */
    public static boolean m6895c(String str) {
        String str2 = "^http[s]?:\\/\\/[^\\/]+(\\.baidu\\.com|\\.hao123\\.com)(:\\d+)?(\\/.*|)$";
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            if (Pattern.compile(str2).matcher(str).matches()) {
                C1425a.m6442c("AdvertiseUtility", "adurl  is  from baidu");
                return true;
            }
            C1425a.m6442c("AdvertiseUtility", "adurl  is  not from baidu");
            return false;
        } catch (Exception e) {
            C1425a.m6440a("AdvertiseUtility", e);
            return false;
        }
    }

    /* renamed from: d */
    private static boolean m6896d(Context context) {
        try {
            int a = C1554p.m6976c(context).mo13906a();
            C1425a.m6442c("AdvertiseUtility", "pushadswitch =  " + a);
            if (C1328a.m6006b() > 0) {
                C1578v.m7095b("updateADStatus getPushADMsgEnable  pushadswitch =  " + a, context);
            }
            return a != 1;
        } catch (Exception e) {
            C1425a.m6440a("AdvertiseUtility", e);
            return true;
        }
    }

    /* renamed from: e */
    private static boolean m6897e(Context context) {
        try {
            long c = C1578v.m7101c();
            C1425a.m6442c("AdvertiseUtility", "today timestamp is   " + c);
            C1448m c2 = C1554p.m6976c(context);
            long e = c2.mo13915e();
            C1425a.m6442c("AdvertiseUtility", "push ad timestamp is   " + e);
            if (c == e) {
                int d = c2.mo13913d();
                int b = c2.mo13909b();
                int c3 = c2.mo13911c();
                if (d + 1 > b || d + 1 > c3) {
                    C1425a.m6442c("AdvertiseUtility", "curcount = " + d + "  maxcount =  " + b + "  servermaxcount  " + c3);
                    return true;
                }
                C1425a.m6442c("AdvertiseUtility", "currentcount  = " + (d + 1));
                C1554p.m6973a(context, d + 1);
            } else {
                C1554p.m6973a(context, 1);
                C1554p.m6974a(context, C1578v.m7101c());
            }
        } catch (Exception e2) {
            C1425a.m6440a("AdvertiseUtility", e2);
        }
        return false;
    }
}
