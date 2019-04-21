package com.ensighten;

import android.app.Dialog;
import android.content.Context;
import dalvik.system.DexClassLoader;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.ensighten.e */
public final class C1750e {
    /* renamed from: A */
    public int f5841A = 20;
    /* renamed from: B */
    public int f5842B = 20;
    /* renamed from: a */
    Context f5843a;
    /* renamed from: b */
    public JSONObject f5844b;
    /* renamed from: c */
    public String f5845c = "unknown";
    /* renamed from: d */
    public String f5846d = "unknown";
    /* renamed from: e */
    public JSONArray f5847e;
    /* renamed from: f */
    Map<String, Pattern> f5848f = new HashMap();
    /* renamed from: g */
    Map<String, Pattern> f5849g = new HashMap();
    /* renamed from: h */
    ArrayList<C1856o> f5850h = new ArrayList();
    /* renamed from: i */
    public Map<String, Map<String, Pattern>> f5851i = new HashMap();
    /* renamed from: j */
    public Map<String, JSONArray> f5852j = new HashMap();
    /* renamed from: k */
    public int f5853k = 1000;
    /* renamed from: l */
    public int f5854l = -1;
    /* renamed from: m */
    public long f5855m = 30;
    /* renamed from: n */
    public boolean f5856n = false;
    /* renamed from: o */
    public boolean f5857o = false;
    /* renamed from: p */
    public Dialog f5858p;
    /* renamed from: q */
    public String f5859q = "This application tracks data to improve your experience, you can choose to disable tracking below. Disable tracking?";
    /* renamed from: r */
    String f5860r = Utils.sha256(this.f5859q);
    /* renamed from: s */
    public boolean f5861s = false;
    /* renamed from: t */
    public boolean f5862t = false;
    /* renamed from: u */
    HashMap<C1730q, JSONObject> f5863u = new HashMap();
    /* renamed from: v */
    public boolean f5864v = false;
    /* renamed from: w */
    public boolean f5865w = false;
    /* renamed from: x */
    public boolean f5866x = false;
    /* renamed from: y */
    public boolean f5867y = false;
    /* renamed from: z */
    public boolean f5868z = false;

    /* renamed from: com.ensighten.e$1 */
    class C17491 extends C1748r {
        C17491(String[] strArr) {
            super(strArr);
        }

        /* renamed from: a */
        public final void mo15107a(byte[] bArr) {
            if (C1845i.m7352b()) {
                C1845i.m7356d("Downloaded the module successfully.");
            }
            C1750e c1750e = C1750e.this;
            try {
                JSONObject jSONObject = (JSONObject) c1750e.f5863u.remove(this);
                String string = jSONObject.getString("name");
                if (C1845i.m7352b()) {
                    C1845i.m7350b(String.format("Loading module %s.", new Object[]{string}));
                }
                File file = new File(c1750e.f5843a.getDir("dex", 0), string);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                bufferedOutputStream.write(bArr);
                bufferedOutputStream.close();
                Class loadClass = new DexClassLoader(file.getAbsolutePath(), c1750e.f5843a.getCacheDir().getAbsolutePath(), null, c1750e.f5843a.getClassLoader()).loadClass(jSONObject.getString("fullName"));
                if (C1693E.class.isAssignableFrom(loadClass)) {
                    if (C1845i.m7352b()) {
                        C1845i.m7350b("Setting CPU from module.");
                    }
                    Ensighten.getConfigurationManager().mo15115a(loadClass);
                } else if (C1697F.class.isAssignableFrom(loadClass)) {
                    loadClass.newInstance();
                }
                Ensighten.getConfigurationManager().mo15117b();
                c1750e.f5847e = null;
                if (c1750e.f5863u.isEmpty()) {
                    if (C1845i.m7352b()) {
                        C1845i.m7350b("Finished loading modules.");
                    }
                    c1750e.f5864v = true;
                }
            } catch (Exception e) {
                if (C1845i.m7352b()) {
                    C1845i.m7353c(e);
                }
                if (!c1750e.f5863u.isEmpty()) {
                    return;
                }
                if (C1845i.m7352b()) {
                    C1845i.m7350b("Finished loading modules.");
                }
            } catch (Throwable th) {
                if (c1750e.f5863u.isEmpty()) {
                    if (C1845i.m7352b()) {
                        C1845i.m7350b("Finished loading modules.");
                    }
                    c1750e.f5864v = true;
                }
            }
        }

        /* renamed from: a */
        public final void mo15073a(Throwable th) {
            if (C1845i.m7352b()) {
                C1845i.m7356d(String.format("Error downloading the module: %s.", new Object[]{th.getMessage()}));
            }
        }
    }

    public C1750e(Context context) {
        this.f5843a = context;
    }

    /* renamed from: a */
    public final boolean mo15108a() {
        return this.f5844b != null;
    }
}
