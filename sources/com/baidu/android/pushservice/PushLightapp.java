package com.baidu.android.pushservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.baidu.android.pushservice.p026j.C1281c;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p027b.C1284b.C1285a;
import com.baidu.android.pushservice.p027b.C1295a;
import com.baidu.android.pushservice.p027b.C1295a.C1296a;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1578v;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import org.json.JSONException;
import org.json.JSONObject;

public class PushLightapp {
    private static int BIND_TIME_OUT = 3500;
    private static boolean EVER_BIND = false;
    private static int RUNNING_PUSH_VERSION = 0;
    private static PushLightapp sInstance;
    private static IPushLightappListener sListener;
    private int bind_times = 0;
    private boolean mBound = false;
    private ServiceConnection mConnection = new C12832();
    private Context mContext;
    C1295a mIPushService;

    /* renamed from: com.baidu.android.pushservice.PushLightapp$2 */
    class C12832 implements ServiceConnection {
        C12832() {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (PushLightapp.EVER_BIND) {
                PushLightapp.this.mBound = true;
                PushLightapp.this.mIPushService = C1296a.m5802a(iBinder);
                PushLightapp.RUNNING_PUSH_VERSION = PushLightapp.this.getRunningServiceVersion();
                if (PushLightapp.sListener != null) {
                    if (PushLightapp.sInstance != null) {
                        PushLightapp.sListener.initialComplete(PushLightapp.sInstance);
                    } else if (PushLightapp.this.mContext != null) {
                        PushLightapp.sInstance = new PushLightapp(PushLightapp.this.mContext);
                        return;
                    }
                }
                PushLightapp.this.bind_times = 0;
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            if (PushLightapp.this.bind_times <= 3) {
                PushLightapp.this.tryBindPush();
            } else {
                PushLightapp.this.bind_times = 0;
            }
        }
    }

    /* renamed from: com.baidu.android.pushservice.PushLightapp$3 */
    class C12873 extends C1285a {
        /* renamed from: a */
        final /* synthetic */ IPushLightappListener f4594a;

        /* renamed from: a */
        public void mo13494a(int i, String str) throws RemoteException {
        }

        /* renamed from: b */
        public void mo13495b(int i, String str) throws RemoteException {
            if (this.f4594a != null) {
                this.f4594a.onSubscribeResult(i, str);
            }
        }

        /* renamed from: c */
        public void mo13496c(int i, String str) throws RemoteException {
        }
    }

    /* renamed from: com.baidu.android.pushservice.PushLightapp$4 */
    class C12884 extends C1285a {
        /* renamed from: a */
        final /* synthetic */ IPushLightappListener f4595a;
        /* renamed from: b */
        final /* synthetic */ String f4596b;

        /* renamed from: a */
        public void mo13494a(int i, String str) throws RemoteException {
        }

        /* renamed from: b */
        public void mo13495b(int i, String str) throws RemoteException {
            if (this.f4595a != null) {
                if (i != 0) {
                    try {
                        JSONObject init = JSONObjectInstrumentation.init(str);
                        if (init.optString("api_key", SafeJsonPrimitive.NULL_STRING).equals(SafeJsonPrimitive.NULL_STRING)) {
                            init.put("api_key", this.f4596b);
                        }
                        if (!TextUtils.isEmpty(init.optString("app_id", ""))) {
                            init.remove("app_id");
                        }
                        str = !(init instanceof JSONObject) ? init.toString() : JSONObjectInstrumentation.toString(init);
                    } catch (JSONException e) {
                        C1425a.m6440a("PushLightapp", e);
                    }
                }
                this.f4595a.onSubscribeByApiKey(i, str);
            }
        }

        /* renamed from: c */
        public void mo13496c(int i, String str) throws RemoteException {
        }
    }

    /* renamed from: com.baidu.android.pushservice.PushLightapp$5 */
    class C12895 extends C1285a {
        /* renamed from: a */
        final /* synthetic */ IPushLightappListener f4597a;

        /* renamed from: a */
        public void mo13494a(int i, String str) throws RemoteException {
            if (this.f4597a != null) {
                this.f4597a.onUnsubscribeResult(i, str);
            }
        }

        /* renamed from: b */
        public void mo13495b(int i, String str) throws RemoteException {
        }

        /* renamed from: c */
        public void mo13496c(int i, String str) throws RemoteException {
        }
    }

    public PushLightapp(Context context) {
        this.mContext = context.getApplicationContext();
        tryBindPush();
    }

    private int getRunningServiceVersion() {
        if (this.mIPushService != null) {
            try {
                return this.mIPushService.mo13530c();
            } catch (Exception e) {
                C1425a.m6440a("PushLightapp", e);
            }
        }
        return 0;
    }

    private void tryBindPush() {
        if (this.mContext != null) {
            if (EVER_BIND || this.mIPushService != null || RUNNING_PUSH_VERSION > 0) {
                unbindService();
            }
            EVER_BIND = true;
            Intent intent = new Intent();
            intent.setClassName(C1578v.m7149v(this.mContext), "com.baidu.android.pushservice.PushService");
            try {
                this.mContext.bindService(intent, this.mConnection, 1);
            } catch (Exception e) {
                C1425a.m6440a("PushLightapp", e);
            }
            this.bind_times++;
            C1462d.m6637a().mo13938a(new C1281c("unbindService", (short) 95) {
                /* renamed from: a */
                public void mo13487a() {
                    try {
                        Thread.sleep((long) PushLightapp.BIND_TIME_OUT);
                        if (PushLightapp.EVER_BIND && !PushLightapp.this.mBound) {
                            PushLightapp.this.unbindService();
                        }
                    } catch (Exception e) {
                        C1425a.m6440a("PushLightapp", e);
                    }
                }
            });
        }
    }

    private void unbindService() {
        try {
            this.mIPushService = null;
            EVER_BIND = false;
            RUNNING_PUSH_VERSION = 0;
            this.mBound = false;
            if (this.mContext != null) {
                this.mContext.unbindService(this.mConnection);
            }
        } catch (Exception e) {
            C1425a.m6440a("PushLightapp", e);
        }
    }
}
