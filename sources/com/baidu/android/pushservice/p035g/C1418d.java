package com.baidu.android.pushservice.p035g;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.android.pushservice.C1427h;
import com.baidu.android.pushservice.C1463j;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.jni.BaiduAppSSOJni;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p032d.C1355a;
import com.baidu.android.pushservice.p033e.C1361a;
import com.baidu.android.pushservice.p033e.C1378l;
import com.baidu.android.pushservice.p033e.C1386s;
import com.baidu.android.pushservice.p033e.C1386s.C1385a;
import com.baidu.android.pushservice.p033e.C1387t;
import com.baidu.android.pushservice.p033e.C1388u;
import com.baidu.android.pushservice.p033e.C1389v;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p037i.C1442i;
import com.baidu.android.pushservice.util.C1578v;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.g.d */
public class C1418d {
    /* renamed from: c */
    private static C1418d f4980c;
    /* renamed from: a */
    private C1463j f4981a;
    /* renamed from: b */
    private Context f4982b;

    private C1418d(Context context) {
        this.f4981a = C1427h.m6451a(context).mo13831c();
        this.f4982b = context;
        C1355a.m6114a().mo13673a(this.f4982b);
    }

    /* renamed from: a */
    public static C1418d m6363a(Context context) {
        if (f4980c == null) {
            f4980c = new C1418d(context);
        }
        return f4980c;
    }

    /* renamed from: a */
    private String m6364a(int i, String str, String str2, String str3, String str4, String str5, int i2) {
        JSONObject jSONObject = new JSONObject();
        if (i == 0) {
            try {
                if (!TextUtils.isEmpty(str2)) {
                    jSONObject.put("app_id", str2);
                }
                if (!TextUtils.isEmpty(str4)) {
                    jSONObject.put("user_id", str4);
                }
                if (!TextUtils.isEmpty(str)) {
                    jSONObject.put("channel_id", str);
                }
                if (i2 == 0) {
                    jSONObject.put("add_shortcut", true);
                } else if (i2 == 1) {
                    jSONObject.put("add_shortcut", false);
                }
                if (!TextUtils.isEmpty(str3)) {
                    jSONObject.put("api_key", str3);
                }
            } catch (Exception e) {
                C1425a.m6440a("LightAppManager", e);
            }
        } else {
            jSONObject.put("error", i);
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("app_id", str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put("api_key", str3);
            }
            jSONObject.put("error_msg", str5);
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
    }

    /* renamed from: a */
    private void m6366a(C1361a c1361a) {
        if (this.f4981a != null) {
            this.f4981a.mo13941a(c1361a);
            return;
        }
        this.f4981a = C1427h.m6451a(this.f4982b).mo13831c();
        C1355a.m6114a().mo13673a(this.f4982b);
        if (this.f4981a != null) {
            this.f4981a.mo13941a(c1361a);
        } else {
            C1425a.m6442c("LightAppManager", " mRegistrationService null! try restart pushserice, ple");
        }
    }

    /* renamed from: a */
    private void m6369a(String str, String str2, String str3, C1385a c1385a) {
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
            C1386s c1386s = new C1386s(this.f4982b, str, str2, c1385a);
            c1386s.mo13735a(0);
            C1462d.m6637a().mo13938a(c1386s);
        }
    }

    /* renamed from: a */
    private void m6370a(String str, String str2, String str3, String str4, C1408a c1408a, boolean z) {
        C1378l c1378l = new C1378l("method_lapp_unbind", str, str3);
        final boolean z2 = z;
        final String str5 = str;
        final String str6 = str3;
        final C1408a c1408a2 = c1408a;
        C14153 c14153 = new C1411e() {
            /* renamed from: a */
            public void mo13788a(int i, HashMap<String, String> hashMap) {
                String str;
                if (i != 0) {
                    str = (String) hashMap.get("error_msg");
                } else if (z2) {
                    C1355a.m6114a().mo13666a(str5, str6);
                    str = null;
                } else {
                    C1418d.this.mo13800a(null, str6, false);
                    str = null;
                }
                if (c1408a2 != null) {
                    String a = C1418d.this.m6364a(i, null, str6, str5, null, str, -1);
                    if (z2) {
                        c1408a2.mo13764d(i, a);
                    } else {
                        c1408a2.mo13760b(i, a);
                    }
                }
            }
        };
        if (!TextUtils.isEmpty(str)) {
            m6366a(new C1389v(c1378l, 3, str2, null, null, str4, null, c14153, this.f4982b));
        } else if (!TextUtils.isEmpty(str3)) {
            String pushHash = BaiduAppSSOJni.getPushHash(this.f4982b, C1355a.m6114a().mo13698e(str4), str3, null);
            if (!TextUtils.isEmpty(pushHash)) {
                m6366a(new C1389v(c1378l, 3, str4, pushHash, c14153, this.f4982b));
            } else if (c1408a == null) {
            } else {
                C1408a c1408a3;
                if (z) {
                    c1408a3 = c1408a;
                    c1408a3.mo13764d(40003, m6364a(40003, null, str3, str, null, "host not resgisted or incorrect channel", -1));
                    return;
                }
                c1408a3 = c1408a;
                c1408a3.mo13760b(40003, m6364a(40003, null, str3, str, null, "host not resgisted or incorrect channel", -1));
            }
        }
    }

    /* renamed from: b */
    private void m6371b(final String str, String str2, String str3, String str4, String str5, String str6, C1408a c1408a, int i) {
        final C1408a c1408a2 = c1408a;
        m6366a(new C1388u(new C1378l("method_set_lapp_tags", str, null), str2, str3, str4, str5, str6, new C1411e() {
            /* JADX WARNING: Unknown top exception splitter block from list: {B:18:0x0048=Splitter:B:18:0x0048, B:7:0x002a=Splitter:B:7:0x002a} */
            /* renamed from: a */
            public void mo13788a(int r6, java.util.HashMap<java.lang.String, java.lang.String> r7) {
                /*
                r5 = this;
                r3 = 0;
                r1 = r0;
                if (r1 == 0) goto L_0x003d;
            L_0x0005:
                r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0058 }
                r2.<init>();	 Catch:{ Exception -> 0x0058 }
                r1 = "type";
                r4 = "subscribe_service";
                r2.put(r1, r4);	 Catch:{ Exception -> 0x0058 }
                r1 = "error";
                r2.put(r1, r6);	 Catch:{ Exception -> 0x0058 }
                r1 = "api_key";
                r4 = r12;	 Catch:{ Exception -> 0x0058 }
                r2.put(r1, r4);	 Catch:{ Exception -> 0x0058 }
                if (r6 == 0) goto L_0x003e;
            L_0x0020:
                if (r7 == 0) goto L_0x006b;
            L_0x0022:
                r1 = "error_msg";
                r1 = r7.get(r1);	 Catch:{ Exception -> 0x0058 }
                r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x0058 }
            L_0x002a:
                r3 = "error_msg";
                r2.put(r3, r1);	 Catch:{ Exception -> 0x0064 }
                r3 = r1;
            L_0x0030:
                r1 = r2 instanceof org.json.JSONObject;	 Catch:{ Exception -> 0x0066 }
                if (r1 != 0) goto L_0x004f;
            L_0x0034:
                r1 = r2.toString();	 Catch:{ Exception -> 0x0066 }
            L_0x0038:
                r2 = r0;
                r2.mo13766e(r6, r1);
            L_0x003d:
                return;
            L_0x003e:
                if (r7 == 0) goto L_0x0069;
            L_0x0040:
                r1 = "details";
                r1 = r7.get(r1);	 Catch:{ Exception -> 0x0058 }
                r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x0058 }
            L_0x0048:
                r3 = "tag";
                r2.put(r3, r1);	 Catch:{ Exception -> 0x0064 }
                r3 = r1;
                goto L_0x0030;
            L_0x004f:
                r0 = r2;
                r0 = (org.json.JSONObject) r0;	 Catch:{ Exception -> 0x0066 }
                r1 = r0;
                r1 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.toString(r1);	 Catch:{ Exception -> 0x0066 }
                goto L_0x0038;
            L_0x0058:
                r1 = move-exception;
                r1 = r3;
            L_0x005a:
                r2 = r0;
                r3 = r7.toString();
                r2.mo13766e(r6, r3);
                goto L_0x0038;
            L_0x0064:
                r2 = move-exception;
                goto L_0x005a;
            L_0x0066:
                r1 = move-exception;
                r1 = r3;
                goto L_0x005a;
            L_0x0069:
                r1 = r3;
                goto L_0x0048;
            L_0x006b:
                r1 = r3;
                goto L_0x002a;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p035g.C1418d$C14164.mo13788a(int, java.util.HashMap):void");
            }
        }, this.f4982b, i));
    }

    /* renamed from: b */
    private void m6372b(String str, String str2, String str3, String str4, boolean z, String str5, C1408a c1408a, int i) {
        final String str6 = str4;
        final boolean z2 = z;
        final C1408a c1408a2 = c1408a;
        final String str7 = str3;
        final String str8 = str;
        final int i2 = i;
        final String str9 = str2;
        final String str10 = str5;
        m6369a(str, str3, str4, new C1385a() {
            /* renamed from: a */
            public void mo13734a(boolean z, C1410c c1410c) {
                if (z) {
                    final C1410c c1410c2 = c1410c;
                    C14131 c14131 = new C1411e() {
                        /* renamed from: a */
                        public void mo13788a(int i, HashMap<String, String> hashMap) {
                            if (hashMap != null) {
                                String str = (String) hashMap.get("channel_id");
                                String str2 = (String) hashMap.get("user_id");
                                String str3 = null;
                                if (i == 0) {
                                    C1418d.this.mo13795a(str6, c1410c2);
                                } else {
                                    str3 = (String) hashMap.get("error_msg");
                                }
                                Object obj = (c1410c2.mo13785g() && z2) ? 1 : null;
                                if (c1408a2 != null) {
                                    c1408a2.mo13762c(i, C1418d.this.m6364a(i, str, str7, str8, str2, str3, obj != null ? 0 : 1));
                                }
                            }
                        }
                    };
                    C1378l c1378l = new C1378l("method_deal_lapp_bind_intent", str8, str7);
                    if (!TextUtils.isEmpty(str8)) {
                        C1418d.this.m6366a(new C1387t(c1378l, i2, str9, null, null, str6, c14131, C1418d.this.f4982b));
                    } else if (!TextUtils.isEmpty(str7)) {
                        String e = C1355a.m6114a().mo13698e(str6);
                        String str = "other";
                        if (!TextUtils.isEmpty(str10)) {
                            try {
                                str = JSONObjectInstrumentation.init(str10).optString("src");
                            } catch (Exception e2) {
                                C1425a.m6440a("LightAppManager", e2);
                            }
                        }
                        String pushHash = BaiduAppSSOJni.getPushHash(C1418d.this.f4982b, e, str7, str);
                        if (!TextUtils.isEmpty(pushHash)) {
                            C1418d.this.m6366a(new C1387t(c1378l, 2, str6, pushHash, str10, c14131, C1418d.this.f4982b));
                        } else if (c1408a2 != null) {
                            c1408a2.mo13762c(40003, C1418d.this.m6364a(40003, null, str7, str8, null, "host not resgisted or incorrect channel", -1));
                        }
                    }
                } else if (c1408a2 != null) {
                    c1408a2.mo13762c(40002, C1418d.this.m6364a(40002, null, str7, str8, null, "GET LIGHTAPPINFO FAILED , TYPE/APPID/APIKEY NOT VALID", -1));
                }
            }
        });
    }

    /* renamed from: a */
    public int mo13789a(int i, String str, C1408a c1408a) {
        int a = C1355a.m6114a().mo13665a(i, str);
        if (c1408a != null) {
            c1408a.mo13756a(a);
        }
        return a;
    }

    /* renamed from: a */
    public long mo13790a(String str, String str2, String str3, String str4, String str5, int i, int i2) {
        return C1355a.m6114a().mo13669a(str, str2, str3, str4, str5, i, i2);
    }

    /* renamed from: a */
    public String mo13791a(C1408a c1408a) {
        String e = C1355a.m6114a().mo13697e();
        if (!TextUtils.isEmpty(e)) {
            if (c1408a != null) {
                c1408a.mo13767f(0, e);
            }
            return e;
        } else if (c1408a == null) {
            return null;
        } else {
            c1408a.mo13767f(-1, null);
            return null;
        }
    }

    /* renamed from: a */
    public String mo13792a(String str, int i, boolean z, int i2, int i3, C1408a c1408a) {
        HashMap a = C1355a.m6114a().mo13672a(str, i, z, i2, i3);
        if (a != null) {
            boolean z2;
            String str2 = (String) a.get("msg");
            try {
                z2 = Integer.valueOf((String) a.get("ismore")).intValue() == 1;
            } catch (Exception e) {
                C1425a.m6440a("LightAppManager", e);
                z2 = false;
            }
            if (c1408a == null) {
                return str2;
            }
            c1408a.mo13758a(0, str2, z2);
            return str2;
        }
        if (c1408a != null) {
            c1408a.mo13758a(1, null, false);
        }
        return null;
    }

    /* renamed from: a */
    public String mo13793a(ArrayList<String> arrayList, C1408a c1408a) {
        String a = C1355a.m6114a().mo13670a((ArrayList) arrayList);
        if (!TextUtils.isEmpty(a)) {
            if (c1408a != null) {
                c1408a.mo13769h(0, a);
            }
            return a;
        } else if (c1408a == null) {
            return null;
        } else {
            c1408a.mo13769h(-1, null);
            return null;
        }
    }

    /* renamed from: a */
    public ArrayList<String> mo13794a(String str, int i) {
        ArrayList<String> arrayList = new ArrayList();
        HashMap g = C1355a.m6114a().mo13702g();
        if (g == null) {
            return arrayList;
        }
        HashMap i2 = C1355a.m6114a().mo13704i(str);
        for (String str2 : g.keySet()) {
            if (i2 == null || !i2.containsKey(str2)) {
                arrayList.add(str2);
            } else {
                int intValue = ((Integer) i2.get(str2)).intValue();
                if (!(intValue == 0 || intValue == i)) {
                    arrayList.add(str2);
                }
            }
        }
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13795a(String str, C1410c c1410c) {
        C1355a.m6114a().mo13668a(c1410c.mo13774b(), c1410c.mo13776c(), c1410c.mo13778d(), c1410c.mo13782f(), str);
    }

    /* renamed from: a */
    public void mo13796a(String str, String str2, C1408a c1408a) {
        m6370a(null, null, str, str2, c1408a, true);
    }

    /* renamed from: a */
    public void mo13797a(String str, String str2, String str3, String str4) {
        C1355a.m6114a().mo13667a(str, str2, str3, str4);
    }

    /* renamed from: a */
    public void mo13798a(String str, String str2, String str3, String str4, String str5, String str6, C1408a c1408a, int i) {
        if (C1355a.m6114a().mo13677a(str)) {
            m6371b(str, str2, str3, str4, str5, str6, c1408a, i);
            return;
        }
        final String str7 = str;
        final String str8 = str2;
        final String str9 = str3;
        final String str10 = str4;
        final String str11 = str5;
        final String str12 = str6;
        final C1408a c1408a2 = c1408a;
        final int i2 = i;
        m6369a(str, null, str6, new C1385a() {
            /* renamed from: a */
            public void mo13734a(boolean z, C1410c c1410c) {
                if (z) {
                    C1418d.this.m6371b(str7, str8, str9, str10, str11, str12, c1408a2, i2);
                    C1418d.this.mo13797a(c1410c.mo13774b(), c1410c.mo13776c(), c1410c.mo13778d(), c1410c.mo13782f());
                } else if (c1408a2 != null) {
                    String jSONObject;
                    try {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("type", "subscribe_service");
                        jSONObject2.put("error", 40002);
                        jSONObject2.put("api_key", str7);
                        jSONObject2.put("error_msg", "GET LIGHTAPPINFO FAILED , TYPE/APPID/APIKEY NOT VALID");
                        jSONObject2.put("tag", str8);
                        jSONObject = !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : JSONObjectInstrumentation.toString(jSONObject2);
                    } catch (Exception e) {
                        jSONObject = null;
                    }
                    c1408a2.mo13766e(40002, jSONObject);
                }
            }
        });
    }

    /* renamed from: a */
    public void mo13799a(final String str, String str2, String str3, String str4, boolean z, String str5, C1408a c1408a, int i) {
        final C1408a c1408a2 = c1408a;
        m6366a(new C1387t(new C1378l("method_deal_lapp_bind_intent", str, null), i, str2, str3, str4, str5, new C1411e() {
            /* renamed from: a */
            public void mo13788a(int i, HashMap<String, String> hashMap) {
                if (hashMap != null) {
                    String str = (String) hashMap.get("channel_id");
                    String str2 = (String) hashMap.get("user_id");
                    if (c1408a2 != null) {
                        str2 = C1578v.m7103c(C1418d.this.f4982b, str, str2);
                        JSONObject jSONObject = new JSONObject();
                        if (i != 0 || TextUtils.isEmpty(str2)) {
                            try {
                                jSONObject.put("error", i);
                                jSONObject.put("api_key", str);
                                jSONObject.put("error_msg", hashMap.get("error_msg"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            c1408a2.mo13757a(i, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
                            return;
                        }
                        try {
                            jSONObject.put("type", "bind_light");
                            jSONObject.put("error", i);
                            jSONObject.put("pushToken", str2);
                            jSONObject.put("api_key", str);
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                        c1408a2.mo13757a(i, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
                    }
                }
            }
        }, this.f4982b));
    }

    /* renamed from: a */
    public void mo13800a(String str, String str2, boolean z) {
        C1355a.m6114a().mo13675a(str, str2, z);
    }

    /* renamed from: a */
    public void mo13801a(String str, String str2, boolean z, C1408a c1408a) {
        m6372b(null, null, str, str2, z, null, c1408a, 2);
    }

    /* renamed from: a */
    public void mo13802a(String str, String str2, boolean z, String str3, C1408a c1408a) {
        try {
            JSONObject init = JSONObjectInstrumentation.init(str3);
            String optString = init.optString("type");
            String string;
            if (TextUtils.isEmpty(optString) || optString.equals("subscribe")) {
                m6372b(null, null, str, str2, z, str3, c1408a, 2);
            } else if (optString.equals("subscribe_service")) {
                String string2 = init.getString("nonce");
                string = init.getString("csrfToken");
                mo13798a(str, init.getString("tags"), string, string2, init.optString("referer", null), str2, c1408a, 1);
            } else if (optString.equals("bind_light")) {
                string = init.getString("nonce");
                mo13799a(str, init.getString("csrfToken"), string, init.optString("referer", null), z, str2, c1408a, 4);
            } else {
                c1408a.mo13762c(30602, m6364a(30602, null, str, str, null, PushConstants.m5756a(30602) + " WRONG TYPE", 0));
            }
        } catch (Exception e) {
            c1408a.mo13762c(30602, m6364a(30602, null, str, str, null, PushConstants.m5756a(30602) + e, 0));
        }
    }

    /* renamed from: a */
    public void mo13803a(ArrayList<String> arrayList) {
        C1355a.m6114a().mo13694d((ArrayList) arrayList);
    }

    /* renamed from: a */
    public boolean mo13804a() {
        HashMap g = C1355a.m6114a().mo13702g();
        return (g == null || g.isEmpty()) ? false : true;
    }

    /* renamed from: a */
    public boolean mo13805a(String str) {
        return C1355a.m6114a().mo13683b(str);
    }

    /* renamed from: a */
    public boolean mo13806a(String str, String str2) {
        boolean c = C1355a.m6114a().mo13691c(str2);
        if (c) {
            C1425a.m6442c("LightAppManager", "has registered");
        } else {
            C1425a.m6442c("LightAppManager", "registering");
        }
        return c ? C1355a.m6114a().mo13695d(str2) : C1355a.m6114a().mo13685b(str, str2);
    }

    /* renamed from: a */
    public boolean mo13807a(String str, String str2, int i) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? false : C1355a.m6114a().mo13692c(str2, str) ? C1355a.m6114a().mo13678a(str2, str, i) : C1355a.m6114a().mo13686b(str2, str, i);
    }

    /* renamed from: a */
    public boolean mo13808a(String str, boolean z) {
        return TextUtils.isEmpty(str) ? false : C1355a.m6114a().mo13679a(str, z);
    }

    /* renamed from: b */
    public int mo13809b(int i, String str, C1408a c1408a) {
        int b = C1355a.m6114a().mo13680b(i, str);
        List arrayList = new ArrayList();
        arrayList.add(str);
        C1355a.m6114a().mo13676a(null, arrayList);
        if (c1408a != null) {
            c1408a.mo13761c(b);
        }
        return b;
    }

    /* renamed from: b */
    public int mo13810b(ArrayList<String> arrayList, C1408a c1408a) {
        int b = C1355a.m6114a().mo13681b((ArrayList) arrayList);
        C1355a.m6114a().mo13676a((List) arrayList, null);
        if (c1408a != null) {
            c1408a.mo13759b(b);
        }
        return b;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0022  */
    /* renamed from: b */
    public java.lang.String mo13811b(com.baidu.android.pushservice.p035g.C1408a r5) {
        /*
        r4 = this;
        r2 = 0;
        r0 = com.baidu.android.pushservice.p032d.C1355a.m6114a();
        r3 = r0.mo13700f();
        r1 = "";
        if (r3 == 0) goto L_0x0038;
    L_0x000d:
        r0 = new org.json.JSONArray;
        r0.<init>(r3);
        if (r0 == 0) goto L_0x0038;
    L_0x0014:
        r1 = r0 instanceof org.json.JSONArray;
        if (r1 != 0) goto L_0x002a;
    L_0x0018:
        r0 = r0.toString();
    L_0x001c:
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 == 0) goto L_0x0031;
    L_0x0022:
        if (r5 == 0) goto L_0x0028;
    L_0x0024:
        r0 = -1;
        r5.mo13768g(r0, r2);
    L_0x0028:
        r0 = r2;
    L_0x0029:
        return r0;
    L_0x002a:
        r0 = (org.json.JSONArray) r0;
        r0 = com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation.toString(r0);
        goto L_0x001c;
    L_0x0031:
        if (r5 == 0) goto L_0x0029;
    L_0x0033:
        r1 = 0;
        r5.mo13768g(r1, r0);
        goto L_0x0029;
    L_0x0038:
        r0 = r1;
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p035g.C1418d.mo13811b(com.baidu.android.pushservice.g.a):java.lang.String");
    }

    /* renamed from: b */
    public ArrayList<String> mo13812b(String str, int i) {
        return C1355a.m6114a().mo13671a(str, i);
    }

    /* renamed from: b */
    public void mo13813b(String str, String str2, C1408a c1408a) {
        m6370a(null, null, str, str2, c1408a, false);
    }

    /* renamed from: b */
    public boolean mo13814b(String str) {
        List arrayList = new ArrayList();
        arrayList.add(str);
        int b = C1355a.m6114a().mo13681b((ArrayList) arrayList);
        C1355a.m6114a().mo13676a(arrayList, null);
        return b >= 0;
    }

    /* renamed from: b */
    public boolean mo13815b(String str, String str2) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? false : C1355a.m6114a().mo13696d(str2, str);
    }

    /* renamed from: b */
    public boolean mo13816b(String str, String str2, String str3, String str4) {
        return C1355a.m6114a().mo13687b(str, str2, str3, str4);
    }

    /* renamed from: c */
    public int mo13817c(int i, String str, C1408a c1408a) {
        int c = C1355a.m6114a().mo13688c(i, str);
        List arrayList = new ArrayList();
        arrayList.add(str);
        C1355a.m6114a().mo13676a(null, arrayList);
        if (c1408a != null) {
            c1408a.mo13765e(c);
        }
        return c;
    }

    /* renamed from: c */
    public int mo13818c(String str) {
        return C1355a.m6114a().mo13701g(str);
    }

    /* renamed from: c */
    public int mo13819c(ArrayList<String> arrayList, C1408a c1408a) {
        int c = C1355a.m6114a().mo13689c((ArrayList) arrayList);
        C1355a.m6114a().mo13676a((List) arrayList, null);
        if (c1408a != null) {
            c1408a.mo13763d(c);
        }
        return c;
    }

    /* renamed from: c */
    public boolean mo13820c(String str, int i) {
        return C1355a.m6114a().mo13684b(str, i);
    }

    /* renamed from: d */
    public boolean mo13821d(String str) {
        return C1355a.m6114a().mo13703h(str);
    }

    /* renamed from: e */
    public String mo13822e(String str) {
        String str2 = "";
        try {
            JSONObject jSONObject = JSONObjectInstrumentation.init(C1355a.m6114a().mo13705j(str)).getJSONObject("thumbnails");
            int i = this.f4982b.getResources().getDisplayMetrics().densityDpi;
            switch (i) {
                case 120:
                    return jSONObject.getString("S");
                case 160:
                    return jSONObject.getString("M");
                case 240:
                    return jSONObject.getString("L");
                default:
                    return i > 240 ? jSONObject.getString("XL") : jSONObject.getString("S");
            }
        } catch (JSONException e) {
            C1425a.m6440a("LightAppManager", e);
            return str2;
        }
    }

    /* renamed from: f */
    public C1442i mo13823f(String str) {
        return C1355a.m6114a().mo13699f(str);
    }

    /* renamed from: g */
    public String mo13824g(String str) {
        return C1355a.m6114a().mo13706k(str);
    }
}
