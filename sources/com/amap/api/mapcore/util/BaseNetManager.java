package com.amap.api.mapcore.util;

import java.net.Proxy;

/* renamed from: com.amap.api.mapcore.util.fq */
public class BaseNetManager {
    /* renamed from: a */
    private static BaseNetManager f1990a;

    /* renamed from: a */
    public static BaseNetManager m2800a() {
        if (f1990a == null) {
            f1990a = new BaseNetManager();
        }
        return f1990a;
    }

    /* renamed from: a */
    public byte[] mo9414a(Request request) throws AMapCoreException {
        AMapCoreException e;
        try {
            ResponseEntity a = mo9413a(request, true);
            if (a != null) {
                return a.f2010a;
            }
            return null;
        } catch (AMapCoreException e2) {
            throw e2;
        } catch (Throwable th) {
            e2 = new AMapCoreException("未知的错误");
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
            BasicLogHandler.m2542a(th, "BaseNetManager", "makeSyncPostRequest");
            e2 = new AMapCoreException("未知的错误");
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: c */
    public void mo9416c(Request request) throws AMapCoreException {
        if (request == null) {
            throw new AMapCoreException("requeust is null");
        } else if (request.mo8901a() == null || "".equals(request.mo8901a())) {
            throw new AMapCoreException("request url is empty");
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public ResponseEntity mo9413a(Request request, boolean z) throws AMapCoreException {
        AMapCoreException e;
        try {
            Proxy proxy;
            mo9416c(request);
            if (request.f1399i == null) {
                proxy = null;
            } else {
                proxy = request.f1399i;
            }
            return new HttpUrlUtil(request.f1397g, request.f1398h, proxy, z).mo9420a(request.mo8908f(), request.mo8907c(), request.mo8909g());
        } catch (AMapCoreException e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            e2 = new AMapCoreException("未知的错误");
        }
    }
}
