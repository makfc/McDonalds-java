package com.ensighten;

import android.os.Message;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.io.IOException;
import java.util.regex.Pattern;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;

/* renamed from: com.ensighten.r */
public class C1748r extends C1730q {
    /* renamed from: a */
    private static String[] f5839a = new String[]{"image/jpeg", "image/png"};

    public C1748r(String[] strArr) {
        this();
        f5839a = strArr;
    }

    /* renamed from: a */
    public void mo15107a(byte[] bArr) {
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final void mo15075a(Throwable th, byte[] bArr) {
        mo15078b(mo15069a(1, (Object) new Object[]{th, bArr}));
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final void mo15072a(Message message) {
        Object[] objArr;
        switch (message.what) {
            case 0:
                objArr = (Object[]) message.obj;
                ((Integer) objArr[0]).intValue();
                mo15107a((byte[]) objArr[1]);
                return;
            case 1:
                objArr = (Object[]) message.obj;
                Throwable th = (Throwable) objArr[0];
                byte[] bArr = (byte[]) objArr[1];
                mo15073a(th);
                return;
            default:
                super.mo15072a(message);
                return;
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo15076a(HttpResponse httpResponse) {
        StatusLine statusLine = httpResponse.getStatusLine();
        Header[] headers = httpResponse.getHeaders(TransactionStateUtil.CONTENT_TYPE_HEADER);
        if (headers.length != 1) {
            mo15075a(new HttpResponseException(statusLine.getStatusCode(), "None, or more than one, Content-Type Header found!"), null);
            return;
        }
        Header header = headers[0];
        int i = 0;
        for (String matches : f5839a) {
            if (Pattern.matches(matches, header.getValue())) {
                i = 1;
            }
        }
        if (i == 0) {
            mo15075a(new HttpResponseException(statusLine.getStatusCode(), "Content-Type not allowed!"), null);
            return;
        }
        byte[] toByteArray;
        try {
            HttpEntity bufferedHttpEntity;
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                bufferedHttpEntity = new BufferedHttpEntity(entity);
            } else {
                bufferedHttpEntity = null;
            }
            toByteArray = EntityUtils.toByteArray(bufferedHttpEntity);
        } catch (IOException e) {
            mo15075a(e, null);
            toByteArray = null;
        }
        if (statusLine.getStatusCode() >= 300) {
            mo15075a(new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase()), toByteArray);
            return;
        }
        mo15078b(mo15069a(0, (Object) new Object[]{Integer.valueOf(statusLine.getStatusCode()), toByteArray}));
    }
}
