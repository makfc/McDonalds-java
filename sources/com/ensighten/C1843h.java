package com.ensighten;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.CookieSyncManager;
import com.ensighten.C1742X.C1741c;
import com.ensighten.C1866z.C1865a;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import org.json.JSONObject;

/* renamed from: com.ensighten.h */
public final class C1843h extends Handler implements C1741c {
    /* renamed from: a */
    public Context f5891a;
    /* renamed from: b */
    public Thread f5892b;
    /* renamed from: c */
    public Map<String, Object> f5893c = new HashMap();
    /* renamed from: d */
    public Set<C1841a> f5894d = new HashSet();
    /* renamed from: e */
    public boolean f5895e = false;
    /* renamed from: f */
    public boolean f5896f = false;
    /* renamed from: g */
    public boolean f5897g = true;
    /* renamed from: h */
    private LinkedBlockingQueue<Serializable> f5898h;
    /* renamed from: i */
    private C1842b f5899i;
    /* renamed from: j */
    private Set<Object> f5900j = new HashSet();
    /* renamed from: k */
    private boolean f5901k = false;

    /* renamed from: com.ensighten.h$1 */
    public class C18381 extends Thread {
        public final void run() {
            try {
                if (C1845i.m7357d()) {
                    C1845i.m7350b("Started saving the queue.");
                }
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(C1843h.this.f5891a.openFileOutput("ensightenQ", 0));
                objectOutputStream.writeObject(C1843h.this.f5898h);
                objectOutputStream.close();
                C1843h.this.f5895e = false;
                if (C1845i.m7357d()) {
                    C1845i.m7350b("Finished saving the queue.");
                }
            } catch (Exception e) {
                if (C1845i.m7357d()) {
                    C1845i.m7353c(e);
                }
            }
        }
    }

    /* renamed from: com.ensighten.h$2 */
    public class C18392 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ Lock f5884a;
        /* renamed from: b */
        final /* synthetic */ String f5885b;
        /* renamed from: c */
        final /* synthetic */ Condition f5886c;

        public C18392(Lock lock, String str, Condition condition) {
            this.f5884a = lock;
            this.f5885b = str;
            this.f5886c = condition;
        }

        public final void run() {
            this.f5884a.lock();
            C1843h.m7332a(String.format("%s", new Object[]{this.f5885b}));
            this.f5886c.signal();
            this.f5884a.unlock();
        }
    }

    /* renamed from: com.ensighten.h$3 */
    public static /* synthetic */ class C18403 {
        /* renamed from: a */
        public static final /* synthetic */ int[] f5888a = new int[C1865a.values().length];

        static {
            try {
                f5888a[C1865a.UPGRADE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f5888a[C1865a.INSTALL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* renamed from: com.ensighten.h$a */
    public interface C1841a {
        /* renamed from: a */
        void mo15481a();
    }

    /* renamed from: com.ensighten.h$b */
    class C1842b implements Runnable {
        /* renamed from: b */
        private boolean f5890b;

        private C1842b() {
            this.f5890b = true;
        }

        /* synthetic */ C1842b(C1843h c1843h, byte b) {
            this();
        }

        public final void run() {
            while (this.f5890b) {
                try {
                    Thread.sleep(300000);
                    C1843h.this.mo15483a();
                } catch (InterruptedException e) {
                    if (C1845i.m7357d()) {
                        C1845i.m7344a(e);
                        return;
                    }
                    return;
                }
            }
        }
    }

    public C1843h(Context context) {
        this.f5891a = context;
        try {
            this.f5898h = (LinkedBlockingQueue) new ObjectInputStream(this.f5891a.openFileInput("ensightenQ")).readObject();
            this.f5891a.deleteFile("ensightenQ");
            mo15483a();
        } catch (Exception e) {
            this.f5898h = new LinkedBlockingQueue(1000);
        }
        this.f5899i = new C1842b(this, (byte) 0);
        new Thread(this.f5899i).start();
    }

    public final void handleMessage(Message message) {
        C1843h.m7332a(message.getData().getString("js"));
    }

    /* renamed from: a */
    public static void m7332a(String str) {
        C1742X webManager = Ensighten.getWebManager();
        String str2 = "javascript:" + str;
        if (webManager.f5812b != null) {
            long j = 0;
            if (C1846j.m7373d()) {
                j = System.currentTimeMillis();
            }
            webManager.f5812b.loadUrl(str2);
            if (C1846j.m7373d()) {
                long currentTimeMillis = System.currentTimeMillis() - j;
                String substring = str2.length() > 50 ? str2.substring(0, 50) : str2;
                C1846j.m7366a(String.format("The load URL call to %s took %s.", new Object[]{substring, Utils.durationToString(currentTimeMillis)}));
            }
        }
    }

    /* renamed from: a */
    private void m7331a(C1700I c1700i) {
        if (C1845i.m7357d()) {
            C1845i.m7345a(String.format("Processing queue item with javascript %s.", new Object[]{c1700i.f5635a}));
        }
        mo15489d(c1700i.f5635a);
        String a = c1700i.mo15030a();
        if (C1845i.m7357d()) {
            C1845i.m7350b(String.format("The gateway wrapper is %s.", new Object[]{a}));
        }
        mo15487b(String.format("%s", new Object[]{a}));
    }

    /* renamed from: b */
    public final void mo15487b(String str) {
        if (!Ensighten.isPrivacyMode()) {
            Message obtain = Message.obtain();
            Bundle bundle = new Bundle();
            bundle.putString("js", str);
            obtain.setData(bundle);
            sendMessage(obtain);
        }
    }

    /* renamed from: b */
    private void m7335b(C1700I c1700i) {
        if (C1845i.m7357d()) {
            C1845i.m7350b("Adding rule to queue.");
        }
        this.f5898h.add(c1700i);
        this.f5895e = true;
        mo15483a();
    }

    /* renamed from: a */
    public final void mo15483a() {
        if (C1845i.m7357d()) {
            C1845i.m7350b("Queue processing has been requested.");
        }
        if (Ensighten.getWebManager().f5822l) {
            C1750e c1750e = Ensighten.getConfigurationManager().f5875a;
            if (this.f5898h != null && this.f5898h.size() >= c1750e.f5854l) {
                try {
                    if (C1845i.m7357d()) {
                        C1845i.m7350b("Started processing the queue.");
                    }
                    while (!this.f5898h.isEmpty() && Utils.isNetworkConnected()) {
                        m7331a((C1700I) ((Serializable) this.f5898h.take()));
                        if (c1750e.f5861s) {
                            CookieSyncManager.getInstance().sync();
                        }
                    }
                    if (this.f5898h.isEmpty()) {
                        this.f5895e = false;
                        this.f5891a.deleteFile("ensightenQ");
                    }
                    if (C1845i.m7357d()) {
                        C1845i.m7345a("Finished processing the queue.");
                    }
                } catch (Exception e) {
                    if (C1845i.m7357d()) {
                        C1845i.m7353c(e);
                    }
                }
            }
            this.f5901k = false;
        } else if (C1845i.m7357d()) {
            C1845i.m7350b("Queue was not processed because the webview has not yet been loaded.");
        }
    }

    /* renamed from: c */
    public final void mo15488c(String str) {
        if (str != null) {
            Ensighten.getInstance();
            C1700I c1700i = new C1700I(str, Ensighten.getEventManager().f5948c, new Date(), Ensighten.getStorageManager().mo15056c());
            int size = this.f5898h.size();
            if ((!str.contains("Bootstrapper.") && !str.contains("http")) || str.contains("nexus.ensighten")) {
                m7331a(c1700i);
            } else if (size <= Ensighten.getConfigurationManager().f5875a.f5853k) {
                if (C1845i.m7357d()) {
                    C1845i.m7350b("Queued event.");
                }
                m7335b(c1700i);
            }
        }
    }

    /* renamed from: a */
    public final void mo15485a(JSONObject jSONObject) {
        String format = String.format("Bootstrapper.onMobileError(%s, params);", new Object[]{Utils.convertJSONObjectToString(jSONObject)});
        if (C1845i.m7357d()) {
            C1845i.m7345a(String.format("Processing javascript error %s.", new Object[]{format}));
        }
        mo15488c(format);
        if (Ensighten.getConfigurationManager().f5875a.f5861s) {
            CookieSyncManager.getInstance().sync();
        }
    }

    /* renamed from: a */
    public final void mo15093a(boolean z) {
        if (z) {
            mo15483a();
        }
    }

    /* renamed from: a */
    public final void mo15484a(C1841a c1841a) {
        synchronized (this.f5894d) {
            this.f5894d.add(c1841a);
        }
    }

    /* renamed from: b */
    public final void mo15486b() {
        synchronized (this.f5894d) {
            for (C1841a a : this.f5894d) {
                a.mo15481a();
            }
        }
    }

    /* renamed from: d */
    public final void mo15489d(String str) {
        synchronized (this.f5900j) {
            Iterator it = this.f5900j.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
    }
}
