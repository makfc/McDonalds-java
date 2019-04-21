package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.mapcore.util.DownloadManager.C0799a;
import com.amap.api.mapcore.util.IDownloadListener.C0758a;
import com.amap.api.maps.AMapException;
import com.mcdonalds.sdk.connectors.middleware.response.MWDeliveryStatusResponse;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* renamed from: com.amap.api.mapcore.util.cc */
public class NetFileFetch implements C0799a {
    /* renamed from: a */
    SiteInfoBean f1660a = null;
    /* renamed from: b */
    long f1661b = 0;
    /* renamed from: c */
    long f1662c = 0;
    /* renamed from: d */
    long f1663d;
    /* renamed from: e */
    boolean f1664e = true;
    /* renamed from: f */
    OfflineDBOperation f1665f;
    /* renamed from: g */
    long f1666g = 0;
    /* renamed from: h */
    C0765a f1667h;
    /* renamed from: i */
    private Context f1668i;
    /* renamed from: j */
    private IDownloadListener f1669j;
    /* renamed from: k */
    private String f1670k;
    /* renamed from: l */
    private DownloadManager f1671l;
    /* renamed from: m */
    private FileAccessI f1672m;

    /* compiled from: NetFileFetch */
    /* renamed from: com.amap.api.mapcore.util.cc$a */
    public interface C0765a {
        /* renamed from: d */
        void mo8937d();
    }

    public NetFileFetch(SiteInfoBean siteInfoBean, String str, Context context, IDownloadListener iDownloadListener) throws IOException {
        this.f1665f = OfflineDBOperation.m1975a(context.getApplicationContext());
        this.f1660a = siteInfoBean;
        this.f1668i = context;
        this.f1670k = str;
        this.f1669j = iDownloadListener;
        m2138g();
    }

    /* renamed from: f */
    private void m2137f() throws IOException {
        OfflineDownloadRequest offlineDownloadRequest = new OfflineDownloadRequest(this.f1670k);
        offlineDownloadRequest.mo8902a(1800000);
        offlineDownloadRequest.mo8906b(1800000);
        this.f1671l = new DownloadManager(offlineDownloadRequest, this.f1661b, this.f1662c);
        this.f1672m = new FileAccessI(this.f1660a.mo9193b() + File.separator + this.f1660a.mo9194c(), this.f1661b);
    }

    /* renamed from: g */
    private void m2138g() {
        if (this.f1665f.mo8985f(this.f1660a.mo9196e())) {
            this.f1664e = false;
            m2143l();
            return;
        }
        this.f1661b = 0;
        this.f1662c = 0;
    }

    /* renamed from: a */
    public void mo9188a() {
        try {
            if (Util.m2375c(this.f1668i)) {
                m2140i();
                if (C0811dm.f1758a == 1) {
                    if (!m2139h()) {
                        this.f1664e = true;
                    }
                    if (this.f1664e) {
                        this.f1663d = mo9190b();
                        if (this.f1663d == -1) {
                            Utility.m2180a("File Length is not known!");
                        } else if (this.f1663d == -2) {
                            Utility.m2180a("File is not access!");
                        } else {
                            this.f1662c = this.f1663d;
                        }
                        this.f1661b = 0;
                    }
                    if (this.f1669j != null) {
                        this.f1669j.mo8844m();
                    }
                    m2137f();
                    this.f1671l.mo9418a(this);
                } else if (this.f1669j != null) {
                    this.f1669j.mo8843a(C0758a.amap_exception);
                }
            } else if (this.f1669j != null) {
                this.f1669j.mo8843a(C0758a.network_exception);
            }
        } catch (AMapException e) {
            SDKLogHandler.m2563a(e, "SiteFileFetch", "download");
            if (this.f1669j != null) {
                this.f1669j.mo8843a(C0758a.amap_exception);
            }
        } catch (IOException e2) {
            if (this.f1669j != null) {
                this.f1669j.mo8843a(C0758a.file_io_exception);
            }
        }
    }

    /* renamed from: h */
    private boolean m2139h() {
        if (new File(this.f1660a.mo9193b() + File.separator + this.f1660a.mo9194c()).length() < 10) {
            return false;
        }
        return true;
    }

    /* renamed from: i */
    private void m2140i() throws AMapException {
        if (C0811dm.f1758a != 1) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < 3) {
                    try {
                        if (!C0811dm.m2392a(this.f1668i, Util.m2377e())) {
                            i = i2 + 1;
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        SDKLogHandler.m2563a(th, "SiteFileFetch", "authOffLineDownLoad");
                        th.printStackTrace();
                    }
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: b */
    public long mo9190b() throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) HttpInstrumentation.openConnection(new URL(this.f1660a.mo9192a()).openConnection());
        httpURLConnection.setRequestProperty("User-Agent", ConfigableConst.f2124d);
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode >= MWDeliveryStatusResponse.STATUS_ORDER_DELIVERED) {
            m2135a(responseCode);
            return -2;
        }
        int i;
        responseCode = 1;
        while (true) {
            String headerFieldKey = httpURLConnection.getHeaderFieldKey(responseCode);
            if (headerFieldKey == null) {
                i = -1;
                break;
            } else if (headerFieldKey.equalsIgnoreCase(TransactionStateUtil.CONTENT_LENGTH_HEADER)) {
                i = Integer.parseInt(httpURLConnection.getHeaderField(headerFieldKey));
                break;
            } else {
                responseCode++;
            }
        }
        return (long) i;
    }

    /* renamed from: j */
    private void m2141j() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f1660a != null && currentTimeMillis - this.f1666g > 500) {
            m2142k();
            this.f1666g = currentTimeMillis;
            m2136a(this.f1661b);
        }
    }

    /* renamed from: k */
    private void m2142k() {
        this.f1665f.mo8978a(this.f1660a.mo9196e(), this.f1660a.mo9195d(), this.f1663d, this.f1661b, this.f1662c);
    }

    /* renamed from: a */
    private void m2136a(long j) {
        if (this.f1663d > 0 && this.f1669j != null) {
            this.f1669j.mo8842a(this.f1663d, j);
            this.f1666g = System.currentTimeMillis();
        }
    }

    /* renamed from: l */
    private boolean m2143l() {
        if (!this.f1665f.mo8985f(this.f1660a.mo9196e())) {
            return false;
        }
        this.f1663d = (long) this.f1665f.mo8983d(this.f1660a.mo9196e());
        long[] a = this.f1665f.mo8980a(this.f1660a.mo9196e(), 0);
        this.f1661b = a[0];
        this.f1662c = a[1];
        return true;
    }

    /* renamed from: a */
    private void m2135a(int i) {
        System.err.println("Error Code : " + i);
    }

    /* renamed from: c */
    public void mo9191c() {
        if (this.f1671l != null) {
            this.f1671l.mo9417a();
        }
    }

    /* renamed from: d */
    public void mo9186d() {
        if (this.f1669j != null) {
            this.f1669j.mo8846o();
        }
        m2142k();
    }

    /* renamed from: e */
    public void mo9187e() {
        m2141j();
        if (this.f1669j != null) {
            this.f1669j.mo8845n();
        }
        if (this.f1672m != null) {
            this.f1672m.mo8987a();
        }
        if (this.f1667h != null) {
            this.f1667h.mo8937d();
        }
    }

    /* renamed from: a */
    public void mo9184a(Throwable th) {
        if (this.f1669j != null) {
            this.f1669j.mo8843a(C0758a.network_exception);
        }
        if (!(th instanceof IOException) && this.f1672m != null) {
            this.f1672m.mo8987a();
        }
    }

    /* renamed from: a */
    public void mo9185a(byte[] bArr, long j) {
        try {
            this.f1672m.mo8986a(bArr);
            this.f1661b = j;
            m2141j();
        } catch (IOException e) {
            e.printStackTrace();
            SDKLogHandler.m2563a(e, "fileAccessI", "fileAccessI.write(byte[] data)");
            if (this.f1669j != null) {
                this.f1669j.mo8843a(C0758a.file_io_exception);
            }
            if (this.f1671l != null) {
                this.f1671l.mo9417a();
            }
        }
    }

    /* renamed from: a */
    public void mo9189a(C0765a c0765a) {
        this.f1667h = c0765a;
    }
}
