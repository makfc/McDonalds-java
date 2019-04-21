package com.ensighten;

import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

/* renamed from: com.ensighten.x */
final class C1863x implements HttpEntity {
    /* renamed from: d */
    private static final char[] f6001d = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    /* renamed from: a */
    ByteArrayOutputStream f6002a = new ByteArrayOutputStream();
    /* renamed from: b */
    boolean f6003b = false;
    /* renamed from: c */
    boolean f6004c = false;
    /* renamed from: e */
    private String f6005e = null;

    public C1863x() {
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer();
        SecureRandom secureRandom = new SecureRandom();
        while (i < 30) {
            stringBuffer.append(f6001d[secureRandom.nextInt(f6001d.length)]);
            i++;
        }
        this.f6005e = stringBuffer.toString();
    }

    /* renamed from: a */
    private void m7428a() {
        try {
            this.f6002a.write(("--" + this.f6005e + "\r\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    private void m7429b() {
        if (!this.f6003b) {
            try {
                this.f6002a.write(("--" + this.f6005e + "--\r\n").getBytes());
                this.f6002a.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.f6003b = true;
        }
    }

    /* renamed from: a */
    public final void mo15526a(String str, String str2) {
        String str3 = "text/plain; charset=UTF-8";
        m7428a();
        try {
            this.f6002a.write(("Content-Disposition: form-data; name=\"" + str + "\"\r\n").getBytes());
            this.f6002a.write(("Content-Type: " + str3 + "\r\n\r\n").getBytes());
            this.f6002a.write(str2.getBytes());
            this.f6002a.write("\r\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public final void mo15527a(String str, String str2, InputStream inputStream, String str3) {
        m7428a();
        IOException e;
        try {
            String str4 = "Content-Type: " + str3 + "\r\n";
            this.f6002a.write(("Content-Disposition: form-data; name=\"" + str + "\"; filename=\"" + str2 + "\"\r\n").getBytes());
            this.f6002a.write(str4.getBytes());
            this.f6002a.write("Content-Transfer-Encoding: binary\r\n\r\n".getBytes());
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    this.f6002a.write(bArr, 0, read);
                } else {
                    this.f6002a.write("\r\n".getBytes());
                    try {
                        inputStream.close();
                        return;
                    } catch (IOException e2) {
                        e = e2;
                    }
                }
            }
            e.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
            try {
                inputStream.close();
            } catch (IOException e4) {
                e3 = e4;
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e5) {
                e5.printStackTrace();
            }
            throw th;
        }
    }

    public final long getContentLength() {
        m7429b();
        return (long) this.f6002a.toByteArray().length;
    }

    public final Header getContentType() {
        return new BasicHeader(TransactionStateUtil.CONTENT_TYPE_HEADER, "multipart/form-data; boundary=" + this.f6005e);
    }

    public final boolean isChunked() {
        return false;
    }

    public final boolean isRepeatable() {
        return false;
    }

    public final boolean isStreaming() {
        return false;
    }

    public final void writeTo(OutputStream outstream) throws IOException {
        m7429b();
        outstream.write(this.f6002a.toByteArray());
    }

    public final Header getContentEncoding() {
        return null;
    }

    public final void consumeContent() throws IOException, UnsupportedOperationException {
        if (isStreaming()) {
            throw new UnsupportedOperationException("Streaming entity does not implement #consumeContent()");
        }
    }

    public final InputStream getContent() throws IOException, UnsupportedOperationException {
        m7429b();
        return new ByteArrayInputStream(this.f6002a.toByteArray());
    }
}
