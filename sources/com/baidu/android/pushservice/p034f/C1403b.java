package com.baidu.android.pushservice.p034f;

import android.os.Build.VERSION;
import android.text.TextUtils;
import com.baidu.android.pushservice.p036h.C1425a;
import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;

/* renamed from: com.baidu.android.pushservice.f.b */
public class C1403b {
    static {
        if (VERSION.SDK_INT <= 8) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    /* renamed from: a */
    public static C1402a m6259a(String str, String str2, HashMap<String, String> hashMap) {
        HttpURLConnection httpURLConnection = null;
        C1402a c1402a = new C1402a();
        try {
            httpURLConnection = C1403b.m6263a(str, str2, null);
            C1403b.m6264a(str2, (HashMap) hashMap, httpURLConnection);
            c1402a = C1403b.m6261a(httpURLConnection);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (Exception e) {
            C1425a.m6439a("HttpUtil", "execRequest>>", e);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return c1402a;
    }

    /* renamed from: a */
    public static C1402a m6260a(String str, String str2, HashMap<String, String> hashMap, String str3) {
        HttpURLConnection httpURLConnection = null;
        C1402a c1402a = new C1402a();
        try {
            httpURLConnection = C1403b.m6263a(str, str2, str3);
            C1403b.m6264a(str2, (HashMap) hashMap, httpURLConnection);
            c1402a = C1403b.m6261a(httpURLConnection);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (Exception e) {
            C1425a.m6439a("HttpUtil", "execRequest>>", e);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return c1402a;
    }

    /* renamed from: a */
    private static C1402a m6261a(HttpURLConnection httpURLConnection) throws Exception {
        int responseCode;
        Throwable th;
        InputStream inputStream;
        Throwable th2;
        C1402a c1402a = new C1402a();
        try {
            InputStream bufferedInputStream;
            responseCode = httpURLConnection.getResponseCode();
            try {
                InputStream errorStream = C1403b.m6266a(responseCode) ? httpURLConnection.getErrorStream() : httpURLConnection.getInputStream();
                try {
                    bufferedInputStream = new BufferedInputStream(errorStream);
                } catch (Exception e) {
                    th = e;
                    inputStream = errorStream;
                    C1425a.m6439a("HttpUtil", "readResponseData>>", th);
                    c1402a.mo13743a(responseCode);
                    c1402a.mo13744a(inputStream);
                    return c1402a;
                }
            } catch (Exception e2) {
                th2 = e2;
                inputStream = null;
                th = th2;
            }
            try {
                if (C1403b.m6269b(httpURLConnection)) {
                    bufferedInputStream = new GZIPInputStream(bufferedInputStream);
                }
                try {
                    inputStream = new ByteArrayInputStream(C1403b.m6268a(bufferedInputStream));
                } catch (Exception e22) {
                    th2 = e22;
                    inputStream = bufferedInputStream;
                    th = th2;
                    C1425a.m6439a("HttpUtil", "readResponseData>>", th);
                    c1402a.mo13743a(responseCode);
                    c1402a.mo13744a(inputStream);
                    return c1402a;
                }
            } catch (Exception e222) {
                th2 = e222;
                inputStream = bufferedInputStream;
                th = th2;
                C1425a.m6439a("HttpUtil", "readResponseData>>", th);
                c1402a.mo13743a(responseCode);
                c1402a.mo13744a(inputStream);
                return c1402a;
            }
        } catch (Exception e3) {
            th2 = e3;
            responseCode = 0;
            inputStream = null;
            th = th2;
        }
        c1402a.mo13743a(responseCode);
        c1402a.mo13744a(inputStream);
        return c1402a;
    }

    /* renamed from: a */
    private static String m6262a(HashMap<String, String> hashMap) throws Exception {
        StringBuffer stringBuffer = new StringBuffer("");
        int i = 0;
        Iterator it = hashMap.entrySet().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return stringBuffer.toString();
            }
            Entry entry = (Entry) it.next();
            if (i2 != 0) {
                stringBuffer.append("&");
            }
            String str = (String) entry.getKey();
            if (!TextUtils.isEmpty(str)) {
                stringBuffer.append(str).append("=");
                String str2 = (String) entry.getValue();
                if (TextUtils.isEmpty(str2)) {
                    stringBuffer.append(URLEncoder.encode("", Utf8Charset.NAME));
                } else {
                    stringBuffer.append(URLEncoder.encode(str2, Utf8Charset.NAME));
                }
            }
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    private static HttpURLConnection m6263a(String str, String str2, String str3) {
        HttpURLConnection httpURLConnection;
        Throwable e;
        try {
            httpURLConnection = (HttpURLConnection) HttpInstrumentation.openConnection(new URL(str).openConnection());
            try {
                httpURLConnection.setConnectTimeout(30000);
                httpURLConnection.setReadTimeout(30000);
                if ("POST".equals(str2) || "PUT".equals(str2)) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setUseCaches(false);
                } else if ("DELETE".equals(str2)) {
                    httpURLConnection.setDoOutput(true);
                } else if ("GET".equals(str2)) {
                    httpURLConnection.setDoOutput(false);
                }
                httpURLConnection.setRequestMethod(str2);
                httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
                httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
                if (!TextUtils.isEmpty(str3)) {
                    httpURLConnection.setRequestProperty("User-Agent", str3);
                }
                if (httpURLConnection instanceof HttpsURLConnection) {
                    C1425a.m6441b("HttpUtil", "https request, url=" + str);
                }
                httpURLConnection.connect();
            } catch (Exception e2) {
                e = e2;
                C1425a.m6439a("HttpUtil", "initConnSet>>", e);
                return httpURLConnection;
            }
        } catch (Exception e3) {
            Throwable th = e3;
            httpURLConnection = null;
            e = th;
            C1425a.m6439a("HttpUtil", "initConnSet>>", e);
            return httpURLConnection;
        }
        return httpURLConnection;
    }

    /* renamed from: a */
    private static void m6264a(String str, HashMap<String, String> hashMap, HttpURLConnection httpURLConnection) throws IOException {
        if (("POST".equals(str) || "PUT".equals(str) || "DELETE".equals(str)) && !C1403b.m6267a(httpURLConnection, hashMap)) {
            throw new IOException("failed to writeRequestParams");
        }
    }

    /* renamed from: a */
    public static void m6265a(Closeable... closeableArr) {
        if (closeableArr != null && closeableArr.length > 0) {
            try {
                for (Closeable closeable : closeableArr) {
                    if (closeable != null) {
                        closeable.close();
                    }
                }
            } catch (Exception e) {
                C1425a.m6440a("HttpUtil", e);
            }
        }
    }

    /* renamed from: a */
    private static boolean m6266a(int i) {
        int i2 = i / 100;
        return i2 == 4 || i2 == 5 || i2 == 6;
    }

    /* renamed from: a */
    private static boolean m6267a(HttpURLConnection httpURLConnection, HashMap<String, String> hashMap) {
        Throwable e;
        Object obj;
        Closeable obj2 = null;
        boolean z = (hashMap == null || hashMap.isEmpty()) ? false : true;
        OutputStream outputStream;
        DataOutputStream dataOutputStream;
        try {
            outputStream = httpURLConnection.getOutputStream();
            try {
                dataOutputStream = new DataOutputStream(outputStream);
                try {
                    dataOutputStream.write(C1403b.m6262a((HashMap) hashMap).getBytes(Utf8Charset.NAME));
                    dataOutputStream.flush();
                    C1403b.m6265a(dataOutputStream, outputStream);
                    return z;
                } catch (Exception e2) {
                    e = e2;
                    obj2 = outputStream;
                    try {
                        C1425a.m6439a("HttpUtil", "writeRequestParams1>>", e);
                        C1403b.m6265a(dataOutputStream, obj2);
                        return false;
                    } catch (Throwable th) {
                        e = th;
                        Closeable outputStream2 = obj2;
                        C1403b.m6265a(dataOutputStream, outputStream2);
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    C1403b.m6265a(dataOutputStream, outputStream2);
                    throw e;
                }
            } catch (Exception e3) {
                e = e3;
                dataOutputStream = null;
                obj2 = outputStream2;
                C1425a.m6439a("HttpUtil", "writeRequestParams1>>", e);
                C1403b.m6265a(dataOutputStream, obj2);
                return false;
            } catch (Throwable th3) {
                e = th3;
                dataOutputStream = null;
                C1403b.m6265a(dataOutputStream, outputStream2);
                throw e;
            }
        } catch (Exception e4) {
            e = e4;
            dataOutputStream = null;
        } catch (Throwable th4) {
            e = th4;
            dataOutputStream = null;
            outputStream2 = null;
            C1403b.m6265a(dataOutputStream, outputStream2);
            throw e;
        }
    }

    /* renamed from: a */
    public static byte[] m6268a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (Exception e) {
                C1425a.m6440a("HttpUtil", e);
                C1403b.m6265a(byteArrayOutputStream, inputStream);
            } catch (Throwable th) {
                C1403b.m6265a(byteArrayOutputStream, inputStream);
            }
        }
        C1403b.m6265a(byteArrayOutputStream, inputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: b */
    private static boolean m6269b(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("Content-Encoding");
        return !TextUtils.isEmpty(headerField) && headerField.contains("zip");
    }
}
