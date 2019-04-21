package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.net.Proxy;

/* renamed from: com.amap.api.mapcore.util.fv */
public class NetManger extends BaseNetManager {
    /* renamed from: a */
    private static NetManger f2007a;
    /* renamed from: b */
    private ThreadPool f2008b;
    /* renamed from: c */
    private Handler f2009c;

    /* compiled from: NetManger */
    /* renamed from: com.amap.api.mapcore.util.fv$1 */
    class C08481 extends ThreadTask {
        /* renamed from: a */
        final /* synthetic */ Request f2004a;
        /* renamed from: b */
        final /* synthetic */ Response f2005b;
        /* renamed from: c */
        final /* synthetic */ NetManger f2006c;

        /* renamed from: a */
        public void mo8931a() {
            try {
                this.f2006c.m2824a(this.f2006c.mo9428b(this.f2004a, false), this.f2005b);
            } catch (AMapCoreException e) {
                this.f2006c.m2821a(e, this.f2005b);
            }
        }
    }

    /* compiled from: NetManger */
    /* renamed from: com.amap.api.mapcore.util.fv$a */
    static class C0849a extends Handler {
        /* synthetic */ C0849a(Looper looper, C08481 c08481) {
            this(looper);
        }

        private C0849a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 0:
                        ((ResponseMessageEntity) message.obj).f2013b.mo9430a();
                        return;
                    case 1:
                        ResponseMessageEntity responseMessageEntity = (ResponseMessageEntity) message.obj;
                        responseMessageEntity.f2013b.mo9431a(responseMessageEntity.f2012a);
                        return;
                    default:
                        return;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static NetManger m2819a(boolean z) {
        return NetManger.m2820a(z, 5);
    }

    /* renamed from: a */
    private static synchronized NetManger m2820a(boolean z, int i) {
        NetManger netManger;
        synchronized (NetManger.class) {
            try {
                if (f2007a == null) {
                    f2007a = new NetManger(z, i);
                } else if (z) {
                    if (f2007a.f2008b == null) {
                        f2007a.f2008b = ThreadPool.m2831a(i);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            netManger = f2007a;
        }
        return netManger;
    }

    private NetManger(boolean z, int i) {
        if (z) {
            try {
                this.f2008b = ThreadPool.m2831a(i);
            } catch (Throwable th) {
                SDKLogHandler.m2563a(th, "NetManger", "NetManger1");
                th.printStackTrace();
                return;
            }
        }
        if (Looper.myLooper() == null) {
            this.f2009c = new C0849a(Looper.getMainLooper(), null);
        } else {
            this.f2009c = new C0849a();
        }
    }

    /* renamed from: b */
    public byte[] mo9415b(Request request) throws AMapCoreException {
        AMapCoreException e;
        try {
            ResponseEntity a = mo9413a(request, false);
            if (a != null) {
                return a.f2010a;
            }
            return null;
        } catch (AMapCoreException e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            SDKLogHandler.m2561a().mo9308b(th, "NetManager", "makeSyncPostRequest");
            e2 = new AMapCoreException("未知的错误");
        }
    }

    /* renamed from: d */
    public byte[] mo9429d(Request request) throws AMapCoreException {
        AMapCoreException e;
        try {
            ResponseEntity b = mo9428b(request, false);
            if (b != null) {
                return b.f2010a;
            }
            return null;
        } catch (AMapCoreException e2) {
            throw e2;
        } catch (Throwable th) {
            e2 = new AMapCoreException("未知的错误");
        }
    }

    /* renamed from: b */
    public ResponseEntity mo9428b(Request request, boolean z) throws AMapCoreException {
        AMapCoreException e;
        try {
            Proxy proxy;
            mo9416c(request);
            if (request.f1399i == null) {
                proxy = null;
            } else {
                proxy = request.f1399i;
            }
            return new HttpUrlUtil(request.f1397g, request.f1398h, proxy, z).mo9419a(request.mo8901a(), request.mo8907c(), request.mo8905b());
        } catch (AMapCoreException e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            e2 = new AMapCoreException("未知的错误");
        }
    }

    /* renamed from: a */
    private void m2821a(AMapCoreException aMapCoreException, Response response) {
        ResponseMessageEntity responseMessageEntity = new ResponseMessageEntity();
        responseMessageEntity.f2012a = aMapCoreException;
        responseMessageEntity.f2013b = response;
        Message obtain = Message.obtain();
        obtain.obj = responseMessageEntity;
        obtain.what = 1;
        this.f2009c.sendMessage(obtain);
    }

    /* renamed from: a */
    private void m2824a(ResponseEntity responseEntity, Response response) {
        response.mo9432a(responseEntity.f2011b, responseEntity.f2010a);
        ResponseMessageEntity responseMessageEntity = new ResponseMessageEntity();
        responseMessageEntity.f2013b = response;
        Message obtain = Message.obtain();
        obtain.obj = responseMessageEntity;
        obtain.what = 0;
        this.f2009c.sendMessage(obtain);
    }
}
