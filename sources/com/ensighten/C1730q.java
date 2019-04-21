package com.ensighten;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.facebook.stetho.common.Utf8Charset;
import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;

/* renamed from: com.ensighten.q */
public class C1730q {
    /* renamed from: a */
    private Handler f5759a;

    /* renamed from: com.ensighten.q$1 */
    class C17311 extends Handler {
        C17311() {
        }

        public final void handleMessage(Message msg) {
            C1730q.this.mo15072a(msg);
        }
    }

    public C1730q() {
        if (Looper.myLooper() != null) {
            this.f5759a = new C17311();
        }
    }

    @Deprecated
    /* renamed from: a */
    public void mo15073a(Throwable th) {
    }

    /* renamed from: b */
    public void mo15079b(Throwable th) {
        mo15073a(th);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo15071a(int i, Header[] headerArr, String str) {
        mo15078b(mo15069a(0, new Object[]{new Integer(i), headerArr, str}));
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final void mo15074a(Throwable th, String str) {
        mo15078b(mo15069a(1, new Object[]{th, str}));
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo15075a(Throwable th, byte[] bArr) {
        mo15078b(mo15069a(1, new Object[]{th, bArr}));
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final void mo15070a() {
        mo15078b(mo15069a(2, null));
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: b */
    public final void mo15077b() {
        mo15078b(mo15069a(3, null));
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public void mo15080b(Throwable th, String str) {
        mo15079b(th);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo15072a(Message message) {
        switch (message.what) {
            case 0:
                ((Integer) ((Object[]) message.obj)[0]).intValue();
                return;
            case 1:
                Object[] objArr = (Object[]) message.obj;
                mo15080b((Throwable) objArr[0], (String) objArr[1]);
                return;
            default:
                return;
        }
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: b */
    public final void mo15078b(Message message) {
        if (this.f5759a != null) {
            this.f5759a.sendMessage(message);
        } else {
            mo15072a(message);
        }
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final Message mo15069a(int i, Object obj) {
        if (this.f5759a != null) {
            return this.f5759a.obtainMessage(i, obj);
        }
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = obj;
        return obtain;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo15076a(HttpResponse httpResponse) {
        String str = null;
        StatusLine statusLine = httpResponse.getStatusLine();
        try {
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                str = EntityUtils.toString(new BufferedHttpEntity(entity), Utf8Charset.NAME);
            }
        } catch (IOException e) {
            mo15074a(e, null);
        }
        if (statusLine.getStatusCode() >= 300) {
            mo15074a(new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase()), str);
        } else {
            mo15071a(statusLine.getStatusCode(), httpResponse.getAllHeaders(), str);
        }
    }
}
