package com.threatmetrix.TrustDefender;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Environment;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.SparseIntArray;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.ServerProtocol;
import com.newrelic.agent.android.api.common.WanType;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.threatmetrix.TrustDefender.C4532g.C4515a;
import com.threatmetrix.TrustDefender.C4532g.C4518b;
import com.threatmetrix.TrustDefender.C4532g.C4518b.C4516a;
import com.threatmetrix.TrustDefender.C4532g.C4518b.C4517b;
import com.threatmetrix.TrustDefender.C4532g.C4520d;
import com.threatmetrix.TrustDefender.C4532g.C4524h;
import com.threatmetrix.TrustDefender.C4532g.C4525i;
import com.threatmetrix.TrustDefender.C4532g.C4527k;
import com.threatmetrix.TrustDefender.C4532g.C4529m;
import com.threatmetrix.TrustDefender.C4532g.C4530n;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.threatmetrix.TrustDefender.p */
class C4543p {
    /* renamed from: a */
    private static final String f7817a = C4549w.m8585a(C4543p.class);
    /* renamed from: b */
    private static SparseIntArray f7818b = new SparseIntArray(5);

    /* renamed from: com.threatmetrix.TrustDefender.p$a */
    enum C4541a {
        CDMA(WanType.CDMA, 3),
        GSM("GSM", 1),
        LTE(WanType.LTE, 0),
        UNKOWN("OTHER", 99),
        WCDMA(WanType.UMTS, 2);
        
        /* renamed from: f */
        private String f7813f;
        /* renamed from: g */
        private int f7814g;

        private C4541a(String label, int priority) {
            this.f7813f = label;
            this.f7814g = priority;
        }

        /* renamed from: a */
        public final String mo34255a() {
            return this.f7813f;
        }

        /* renamed from: b */
        public final int mo34256b() {
            return this.f7814g;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.p$b */
    static class C4542b {
        /* renamed from: a */
        int f7815a = 0;
        /* renamed from: b */
        int f7816b = 0;

        C4542b() {
        }
    }

    C4543p() {
    }

    static {
        Class devicePolicyManager = C4485at.m8327b("android.app.admin.DevicePolicyManager");
        Object value = C4485at.m8319a(devicePolicyManager, "ENCRYPTION_STATUS_UNSUPPORTED", null);
        if (value != null) {
            f7818b.put(((Integer) value).intValue(), 1);
        }
        value = C4485at.m8319a(devicePolicyManager, "ENCRYPTION_STATUS_INACTIVE", null);
        if (value != null) {
            f7818b.put(((Integer) value).intValue(), 2);
        }
        value = C4485at.m8319a(devicePolicyManager, "ENCRYPTION_STATUS_ACTIVATING", null);
        if (value != null) {
            f7818b.put(((Integer) value).intValue(), 4);
        }
        value = C4485at.m8319a(devicePolicyManager, "ENCRYPTION_STATUS_ACTIVE", null);
        if (value != null) {
            f7818b.put(((Integer) value).intValue(), 8);
        }
        value = C4485at.m8319a(devicePolicyManager, "ENCRYPTION_STATUS_ACTIVE_DEFAULT_KEY", null);
        if (value != null) {
            f7818b.put(((Integer) value).intValue(), 32);
        }
    }

    /* renamed from: a */
    static List<String> m8559a(Context context, List<String> paths) throws InterruptedException {
        String str;
        List foundURLs = new ArrayList();
        int not_matched = 0;
        if (!(paths == null || paths.isEmpty())) {
            URI url;
            List<String> validPaths = new ArrayList();
            List<URI> validURLs = new ArrayList();
            for (String p : paths) {
                url = null;
                try {
                    url = new URI(p);
                } catch (URISyntaxException e) {
                    str = f7817a;
                }
                if (url != null) {
                    if (url.getScheme() == null) {
                        str = f7817a;
                        new StringBuilder("Failed to get url scheme from: ").append(url);
                    } else {
                        validURLs.add(url);
                        StringBuilder stringBuilder = new StringBuilder(url.getScheme());
                        stringBuilder.append("://");
                        if (url.getHost() != null && !url.getHost().isEmpty()) {
                            stringBuilder.append(url.getHost());
                        } else if (!(url.getAuthority() == null || url.getAuthority().isEmpty())) {
                            stringBuilder.append(url.getAuthority());
                        }
                        if (!(url.getPath() == null || url.getPath().isEmpty())) {
                            stringBuilder.append(url.getPath());
                        }
                        if (!(url.getQuery() == null || url.getQuery().isEmpty())) {
                            stringBuilder.append("?").append(url.getQuery());
                        }
                        validPaths.add(stringBuilder.toString());
                    }
                }
            }
            String[] nativeURLs = NativeGatherer.m8207a().mo34049a((String[]) validPaths.toArray(new String[validPaths.size()]));
            String buildTags = C4518b.f7604c;
            C4525i c4525i = new C4525i(context);
            for (int i = 0; i < validURLs.size(); i++) {
                url = (URI) validURLs.get(i);
                String path = (String) validPaths.get(i);
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }
                boolean foundMatch = false;
                if (url.getScheme().equals("file")) {
                    foundMatch = new File(url).exists();
                } else if (url.getScheme().equals("tags")) {
                    if (buildTags != null) {
                        String tagToCheck = url.getHost() == null ? url.getAuthority() : url.getHost();
                        if (!(tagToCheck == null || tagToCheck.isEmpty())) {
                            foundMatch = buildTags.contains(tagToCheck);
                        }
                    }
                } else if (url.getScheme().equals("pkg")) {
                    String uri = url.getHost() == null ? url.getAuthority() : url.getHost();
                    if (uri != null) {
                        foundMatch = c4525i.mo34227a(uri, C4520d.f7618a);
                    }
                } else if (url.getScheme().equals("prop")) {
                    String property = url.getHost() == null ? url.getAuthority() : url.getHost();
                    if (property != null) {
                        String value = url.getQuery();
                        if (value != null) {
                            if (property.equals("ro.build.version.codename")) {
                                if (value.equalsIgnoreCase(C4516a.f7583b)) {
                                    foundMatch = true;
                                }
                            } else if (property.equals("ro.build.date.utc")) {
                                if (value.equals(Long.valueOf(C4518b.f7602a))) {
                                    foundMatch = true;
                                }
                            } else if (property.equals("ro.build.type")) {
                                if (value.equalsIgnoreCase(C4518b.f7603b)) {
                                    foundMatch = true;
                                }
                            } else if (property.equals("ro.build.tags")) {
                                if (value.equalsIgnoreCase(C4518b.f7604c)) {
                                    foundMatch = true;
                                }
                            } else if (property.equals("ro.build.host")) {
                                if (value.equalsIgnoreCase(C4518b.f7605d)) {
                                    foundMatch = true;
                                }
                            } else if (property.equals("ro.build.user")) {
                                if (value.equalsIgnoreCase(C4518b.f7607f)) {
                                    foundMatch = true;
                                }
                            } else if (!property.equals("ro.build.id")) {
                                ArrayList<String> keys = new ArrayList();
                                Collections.addAll(keys, new String[]{property});
                                String found = C4543p.m8556a("/system/build.prop", keys, "=", false);
                                if (found == null || !value.equalsIgnoreCase(found)) {
                                    found = C4543p.m8556a("/default.prop", keys, "=", false);
                                    if (found != null && value.equalsIgnoreCase(found)) {
                                        foundMatch = true;
                                    }
                                } else {
                                    foundMatch = true;
                                }
                            } else if (value.equalsIgnoreCase(C4518b.f7608g)) {
                                foundMatch = true;
                            }
                        }
                    }
                }
                if (foundMatch) {
                    foundURLs.add(path);
                } else {
                    not_matched++;
                }
            }
            C4549w.m8594c(f7817a, "matched " + not_matched + "/" + paths.size());
            if (nativeURLs != null && nativeURLs.length > 0) {
                String[] arr$ = nativeURLs;
                int len$ = nativeURLs.length;
                for (int i$ = 0; i$ < len$; i$++) {
                    foundURLs.add("a" + arr$[i$]);
                }
            }
            Collections.sort(foundURLs);
            if (!foundURLs.isEmpty() && C4549w.m8590a()) {
                str = f7817a;
                new StringBuilder("found ").append(C4491ai.m8340a(foundURLs, ";"));
            }
        }
        return foundURLs;
    }

    /* renamed from: a */
    static String m8557a(StringBuilder fontCount) throws InterruptedException {
        List<String> fonts;
        if (NativeGatherer.m8207a().mo34053b()) {
            fonts = NativeGatherer.m8207a().mo34060f("/system/fonts");
            if (fonts == null || fonts.isEmpty() || fonts.size() != 2) {
                return null;
            }
            String fontHash = (String) fonts.get(0);
            fontCount.append((String) fonts.get(1));
            return fontHash;
        }
        fonts = new ArrayList();
        String str = ".ttf";
        String[] list = new File("/system/fonts/").list();
        if (list != null) {
            for (String str2 : list) {
                if (str2 != null && str2.endsWith(str)) {
                    StringBuilder stringBuilder = new StringBuilder(str2);
                    fonts.add(stringBuilder.substring(0, stringBuilder.length() - 4));
                }
            }
        }
        StringBuilder fontString = new StringBuilder();
        for (String f : fonts) {
            fontString.append(f);
        }
        fontCount.append(fonts.size());
        return C4491ai.m8344b(fontString.toString());
    }

    /* renamed from: a */
    static String m8553a() {
        Locale locale = Locale.getDefault();
        StringBuffer buffer = new StringBuffer();
        String language = locale.getLanguage();
        if (language != null) {
            buffer.append(language);
            String country = locale.getCountry();
            if (country != null) {
                buffer.append("-");
                buffer.append(country);
            }
        }
        return buffer.toString();
    }

    /* renamed from: b */
    static String m8561b() {
        Locale locale = Locale.getDefault();
        StringBuffer buffer = new StringBuffer();
        String language = locale.getLanguage();
        if (language != null) {
            buffer.append(language);
            String country = locale.getCountry();
            if (country != null) {
                buffer.append("_");
                buffer.append(country);
            }
        }
        return buffer.toString();
    }

    /* renamed from: a */
    static String m8554a(long freeSpace, long bootTime) throws InterruptedException {
        StringBuilder state = new StringBuilder();
        state.append(freeSpace).append("-").append(bootTime);
        String deviceState = C4491ai.m8344b(state.toString());
        C4549w.m8594c(f7817a, "getDeviceState: " + deviceState);
        return deviceState;
    }

    /* JADX WARNING: Removed duplicated region for block: B:69:0x00d5 A:{SYNTHETIC, Splitter:B:69:0x00d5} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00a9 A:{SYNTHETIC, Splitter:B:52:0x00a9} */
    /* renamed from: a */
    private static java.lang.String m8556a(java.lang.String r13, java.util.List<java.lang.String> r14, java.lang.String r15, boolean r16) {
        /*
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r0 = 0;
        if (r13 == 0) goto L_0x00ac;
    L_0x0008:
        if (r14 == 0) goto L_0x00ac;
    L_0x000a:
        r3 = new java.io.File;
        r3.<init>(r13);
        r11 = r3.exists();
        if (r11 == 0) goto L_0x00ac;
    L_0x0015:
        r1 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x00ed }
        r11 = new java.io.FileReader;	 Catch:{ IOException -> 0x00ed }
        r11.<init>(r3);	 Catch:{ IOException -> 0x00ed }
        r1.<init>(r11);	 Catch:{ IOException -> 0x00ed }
    L_0x001f:
        r10 = r1.readLine();	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        if (r10 == 0) goto L_0x00d9;
    L_0x0025:
        r8 = "";
        if (r15 == 0) goto L_0x006d;
    L_0x0029:
        r11 = r15.isEmpty();	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        if (r11 != 0) goto L_0x006d;
    L_0x002f:
        r9 = com.threatmetrix.TrustDefender.C4491ai.m8345b(r10, r15);	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        r11 = r9.isEmpty();	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        if (r11 != 0) goto L_0x001f;
    L_0x0039:
        r11 = 0;
        r11 = r9.get(r11);	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        r11 = (java.lang.String) r11;	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        r7 = r11.trim();	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        r11 = r7.length();	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        if (r11 == 0) goto L_0x001f;
    L_0x004a:
        r11 = r14.contains(r7);	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        if (r11 == 0) goto L_0x00bb;
    L_0x0050:
        r11 = r9.size();	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        r12 = 1;
        if (r11 <= r12) goto L_0x0062;
    L_0x0057:
        r11 = 1;
        r11 = r9.get(r11);	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        r11 = (java.lang.String) r11;	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        r8 = r11.trim();	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
    L_0x0062:
        if (r16 != 0) goto L_0x00bb;
    L_0x0064:
        r1.close();	 Catch:{ IOException -> 0x0069 }
    L_0x0067:
        r0 = r1;
    L_0x0068:
        return r8;
    L_0x0069:
        r11 = move-exception;
        r11 = f7817a;
        goto L_0x0067;
    L_0x006d:
        r2 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        r2.<init>();	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        r4 = r14.iterator();	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
    L_0x0076:
        r11 = r4.hasNext();	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        if (r11 == 0) goto L_0x00b1;
    L_0x007c:
        r6 = r4.next();	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        r6 = (java.lang.String) r6;	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        r11 = r10.contains(r6);	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        if (r11 == 0) goto L_0x0076;
    L_0x0088:
        if (r16 != 0) goto L_0x0094;
    L_0x008a:
        r1.close();	 Catch:{ IOException -> 0x0090 }
    L_0x008d:
        r0 = r1;
        r8 = r10;
        goto L_0x0068;
    L_0x0090:
        r11 = move-exception;
        r11 = f7817a;
        goto L_0x008d;
    L_0x0094:
        r11 = r2.length();	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        if (r11 <= 0) goto L_0x009f;
    L_0x009a:
        r11 = ",";
        r2.append(r11);	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
    L_0x009f:
        r2.append(r10);	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        goto L_0x0076;
    L_0x00a3:
        r11 = move-exception;
        r0 = r1;
    L_0x00a5:
        r11 = f7817a;	 Catch:{ all -> 0x00eb }
        if (r0 == 0) goto L_0x00ac;
    L_0x00a9:
        r0.close();	 Catch:{ IOException -> 0x00e3 }
    L_0x00ac:
        r8 = r5.toString();
        goto L_0x0068;
    L_0x00b1:
        r11 = r2.length();	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        if (r11 == 0) goto L_0x00bb;
    L_0x00b7:
        r8 = r2.toString();	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
    L_0x00bb:
        r11 = r8.isEmpty();	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        if (r11 != 0) goto L_0x001f;
    L_0x00c1:
        r11 = r5.length();	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        if (r11 <= 0) goto L_0x00cc;
    L_0x00c7:
        r11 = ",";
        r5.append(r11);	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
    L_0x00cc:
        r5.append(r8);	 Catch:{ IOException -> 0x00a3, all -> 0x00d1 }
        goto L_0x001f;
    L_0x00d1:
        r11 = move-exception;
        r0 = r1;
    L_0x00d3:
        if (r0 == 0) goto L_0x00d8;
    L_0x00d5:
        r0.close();	 Catch:{ IOException -> 0x00e7 }
    L_0x00d8:
        throw r11;
    L_0x00d9:
        r1.close();	 Catch:{ IOException -> 0x00de }
        r0 = r1;
        goto L_0x00ac;
    L_0x00de:
        r11 = move-exception;
        r11 = f7817a;
        r0 = r1;
        goto L_0x00ac;
    L_0x00e3:
        r11 = move-exception;
        r11 = f7817a;
        goto L_0x00ac;
    L_0x00e7:
        r12 = move-exception;
        r12 = f7817a;
        goto L_0x00d8;
    L_0x00eb:
        r11 = move-exception;
        goto L_0x00d3;
    L_0x00ed:
        r11 = move-exception;
        goto L_0x00a5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.C4543p.m8556a(java.lang.String, java.util.List, java.lang.String, boolean):java.lang.String");
    }

    /* renamed from: a */
    static String m8555a(Context m_context, Context context) throws InterruptedException {
        String str = f7817a;
        StringBuilder fingerprint = new StringBuilder();
        if (Thread.currentThread().isInterrupted()) {
            return "";
        }
        try {
            Object telephonyService = context.getApplicationContext().getSystemService("phone");
            if (telephonyService == null || !(telephonyService instanceof TelephonyManager)) {
                return "";
            }
            TelephonyManager manager = (TelephonyManager) telephonyService;
            String carrierName = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            if (manager.getPhoneType() == 1) {
                carrierName = manager.getNetworkOperatorName();
            }
            fingerprint.append(carrierName);
            fingerprint.append(manager.getSimCountryIso());
            fingerprint.append(((((float) C4543p.m8567e()) / 1024.0f) / 1024.0f) / 1024.0f);
            C4534j display = new C4534j(m_context);
            int x = display.mo34237a();
            int y = display.mo34238b();
            if (x >= y) {
                fingerprint.append(x).append("x").append(y);
            } else {
                fingerprint.append(y).append("x").append(x);
            }
            str = f7817a;
            ArrayList arrayList = new ArrayList();
            Collections.addAll(arrayList, new String[]{"Processor", "Hardware", "Serial"});
            str = C4543p.m8556a("/proc/cpuinfo", arrayList, ":", true);
            C4549w.m8594c(f7817a, "getCPUInfo returned: " + str);
            fingerprint.append(str);
            str = f7817a;
            arrayList = new ArrayList();
            Collections.addAll(arrayList, new String[]{"MemTotal"});
            str = C4543p.m8556a("/proc/meminfo", arrayList, ":", true);
            C4549w.m8594c(f7817a, "getMemInfo returned: " + str);
            fingerprint.append(str);
            fingerprint.append(C4518b.f7610i).append(" ").append(C4518b.f7611j).append(" ").append(C4518b.f7613l).append(" ").append(C4518b.f7614m).append(" ").append(C4516a.f7582a);
            str = f7817a;
            new StringBuilder("getDeviceFingerprint returned: hash(").append(fingerprint.toString()).append(")");
            return C4491ai.m8344b(fingerprint.toString());
        } catch (SecurityException e) {
            str = f7817a;
            return "";
        } catch (Exception e2) {
            C4549w.m8594c(f7817a, e2.getMessage());
            return "";
        }
    }

    /* renamed from: a */
    static String m8558a(List<URI> paths) throws InterruptedException {
        StringBuilder found = new StringBuilder();
        if (paths == null || paths.isEmpty()) {
            return null;
        }
        String str;
        for (URI uri : paths) {
            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedException();
            } else if (uri != null) {
                if (uri.getScheme().equals("file")) {
                    File file = new File(uri.getPath());
                    String query = uri.getQuery();
                    if (query == null || query.isEmpty()) {
                        if (found.length() > 0) {
                            found.append(";");
                        }
                        found.append(uri.getPath()).append("=").append(file.exists() ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
                    } else if (file.exists()) {
                        Map<String, String> kvs = C4491ai.m8347d(query);
                        boolean isGrep = kvs.containsKey("grep");
                        if (isGrep || kvs.containsKey("keys")) {
                            String keys;
                            String sep = (String) kvs.get("sep");
                            if (!isGrep && (sep == null || sep.isEmpty())) {
                                sep = ":";
                            }
                            if (isGrep) {
                                keys = (String) kvs.get("grep");
                            } else {
                                keys = (String) kvs.get("keys");
                            }
                            if (keys == null || keys.isEmpty()) {
                                break;
                            }
                            String info = C4543p.m8556a(file.getAbsolutePath(), C4491ai.m8345b(keys, ","), sep, true);
                            if (!(info == null || info.isEmpty())) {
                                if (found.length() > 0) {
                                    found.append(";");
                                }
                                found.append(uri.getPath()).append("=").append(info);
                            }
                        }
                    } else {
                        continue;
                    }
                } else if (uri.getScheme().equals("intro")) {
                    try {
                        String className = uri.getHost();
                        String methodOrFieldName = uri.getPath();
                        if (className == null || className.isEmpty()) {
                            C4549w.m8594c(f7817a, "getURLs: empty className");
                        } else if (methodOrFieldName == null || methodOrFieldName.isEmpty()) {
                            str = f7817a;
                        } else {
                            if (methodOrFieldName.startsWith("/")) {
                                methodOrFieldName = methodOrFieldName.substring(1);
                            }
                            Object c = C4485at.m8327b(className);
                            if (c == null) {
                                str = f7817a;
                                new StringBuilder("getURLs: failed to find class: ").append(className);
                            } else {
                                Method m = C4485at.m8325a((Class) c, methodOrFieldName, new Class[0]);
                                Object o;
                                if (m != null) {
                                    o = C4485at.m8323a(c, m, new Object[0]);
                                    if (o != null) {
                                        String v = o.toString();
                                        if (v != null) {
                                            if (found.length() > 0) {
                                                found.append(";");
                                            }
                                            found.append(uri.getHost()).append(uri.getPath()).append("=").append(v);
                                        }
                                    }
                                } else {
                                    Field f = C4485at.m8324a((Class) c, methodOrFieldName);
                                    if (f != null) {
                                        o = C4485at.m8322a(c, f);
                                        if (o != null && (o instanceof String)) {
                                            if (found.length() > 0) {
                                                found.append(";");
                                            }
                                            found.append(uri.getHost()).append(uri.getPath()).append("=").append((String) o);
                                        }
                                    } else {
                                        str = f7817a;
                                        new StringBuilder("getURLs: failed to find method or field: ").append(methodOrFieldName);
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        if (found.length() > 0) {
            str = f7817a;
            new StringBuilder("found ").append(found.toString());
        }
        return found.toString();
    }

    /* renamed from: a */
    static boolean m8560a(C4542b tzInfo) {
        if (tzInfo == null) {
            throw new IllegalArgumentException("tzInfo cannot be null");
        }
        TimeZone tz = TimeZone.getDefault();
        if (tz != null) {
            tzInfo.f7815a = tz.getRawOffset() / 60000;
            tzInfo.f7816b = tz.getDSTSavings() / 60000;
            C4549w.m8594c(f7817a, "getTimeZoneInfo: dstDiff=" + tzInfo.f7816b + " gmfOffset=" + tzInfo.f7815a);
            return true;
        }
        String str = f7817a;
        return false;
    }

    /* renamed from: a */
    static C4551x m8552a(Context context) throws InterruptedException {
        return new C4551x(context);
    }

    /* renamed from: c */
    static long m8563c() {
        long bootTime = (System.currentTimeMillis() - C4529m.m8517b()) / 1000;
        C4549w.m8594c(f7817a, " getBootTime: " + bootTime);
        return bootTime;
    }

    /* renamed from: b */
    static String m8562b(Context context) {
        String unknown = "-";
        String packageName = new C4515a(context).mo34221a();
        C4524h packageInfo = new C4524h(context, packageName, C4520d.f7619b);
        int versionCode = packageInfo.mo34224a();
        String versionName = packageInfo.mo34225b();
        StringBuilder builder = new StringBuilder();
        StringBuilder append = builder.append(packageName).append(":");
        if (versionName == null) {
            versionName = unknown;
        }
        append.append(versionName).append(":").append(versionCode == -1 ? unknown : Integer.valueOf(versionCode));
        String binaryArch = NativeGatherer.m8207a().mo34063h();
        append = builder.append(":");
        if (binaryArch != null) {
            unknown = binaryArch;
        }
        append.append(unknown);
        C4549w.m8594c(f7817a, "Application Info " + builder.toString());
        return builder.toString();
    }

    /* renamed from: d */
    static long m8566d() {
        C4490ah wrapper = new C4490ah(Environment.getDataDirectory().getPath());
        long availableBlocks = wrapper.mo34134c();
        long blockSizeInBytes = wrapper.mo34133b();
        long freeSpaceInMeg = 0;
        if (!(availableBlocks == 0 || blockSizeInBytes == 0)) {
            freeSpaceInMeg = ((((availableBlocks * blockSizeInBytes) >> 20) << 20) / 10) * 10;
        }
        C4549w.m8594c(f7817a, "Free space on the phone " + freeSpaceInMeg);
        return freeSpaceInMeg;
    }

    /* renamed from: e */
    static long m8567e() {
        C4490ah wrapper = new C4490ah(Environment.getDataDirectory().getPath());
        return wrapper.mo34132a() * wrapper.mo34133b();
    }

    /* renamed from: c */
    static boolean m8564c(Context context) {
        String mockLocation = C4527k.m8508a(context.getContentResolver(), "ALLOW_MOCK_LOCATION");
        return mockLocation != null && mockLocation.equals("1");
    }

    /* renamed from: f */
    static String m8569f() {
        return "android";
    }

    /* renamed from: g */
    static String m8571g() {
        return "Android";
    }

    @TargetApi(11)
    /* renamed from: d */
    static int m8565d(Context context) {
        if (C4516a.f7584c < C4517b.f7589d) {
            return 1;
        }
        int value = f7818b.get(C4544q.m8574a(context));
        if (value != 0) {
            return value;
        }
        return 16;
    }

    /* renamed from: e */
    static String m8568e(Context context) {
        String str;
        if (!C4530n.m8519b()) {
            return null;
        }
        try {
            Object telephonyService = context.getApplicationContext().getSystemService("phone");
            if (telephonyService == null || !(telephonyService instanceof TelephonyManager)) {
                return null;
            }
            TelephonyManager telephonyManager = (TelephonyManager) telephonyService;
            Map<String, String> cellTowerInformation = new HashMap();
            try {
                String no = telephonyManager.getNetworkOperator();
                String ncIso = telephonyManager.getNetworkCountryIso();
                String non = telephonyManager.getNetworkOperatorName();
                if (!(no == null || "".equals(no.trim()))) {
                    cellTowerInformation.put("no", no);
                }
                if (!(non == null || "".equals(non.trim()))) {
                    cellTowerInformation.put("non", non);
                }
                if (!(ncIso == null || "".equals(ncIso.trim()))) {
                    cellTowerInformation.put("nc_iso", ncIso);
                }
            } catch (SecurityException e) {
                str = f7817a;
            } catch (Exception e2) {
                C4549w.m8594c(f7817a, e2.getMessage());
            }
            C4525i c4525i = new C4525i(context);
            boolean finePermission = c4525i.mo34228a("android.permission.ACCESS_FINE_LOCATION", context.getPackageName());
            boolean coarsePermission = c4525i.mo34228a("android.permission.ACCESS_COARSE_LOCATION", context.getPackageName());
            if (finePermission || coarsePermission) {
                if (C4516a.f7584c >= C4517b.f7596k) {
                    Map<String, String> api18Info = C4514f.m8473a(context);
                    if (api18Info != null) {
                        cellTowerInformation.putAll(api18Info);
                    }
                }
                if (C4530n.m8520c() || C4530n.m8521d()) {
                    CellLocation cellLocation = null;
                    try {
                        cellLocation = telephonyManager.getCellLocation();
                    } catch (SecurityException e3) {
                        str = f7817a;
                    } catch (Exception e22) {
                        C4549w.m8594c(f7817a, e22.getMessage());
                    }
                    if (cellLocation != null) {
                        int temp;
                        if (cellLocation instanceof GsmCellLocation) {
                            GsmCellLocation loc = (GsmCellLocation) telephonyManager.getCellLocation();
                            temp = loc.getLac();
                            if (temp != -1) {
                                cellTowerInformation.put("lac", String.valueOf(temp));
                            }
                            temp = loc.getCid();
                            if (temp != -1) {
                                cellTowerInformation.put("cid", String.valueOf(temp));
                            }
                            temp = loc.getPsc();
                            if (temp != -1) {
                                cellTowerInformation.put("psc", String.valueOf(temp));
                            }
                        } else if (cellLocation instanceof CdmaCellLocation) {
                            CdmaCellLocation loc2 = (CdmaCellLocation) telephonyManager.getCellLocation();
                            temp = loc2.getSystemId();
                            if (temp != -1) {
                                cellTowerInformation.put("sid", String.valueOf(temp));
                            }
                            temp = loc2.getBaseStationId();
                            if (temp != -1) {
                                cellTowerInformation.put("bsid", String.valueOf(temp));
                            }
                            temp = loc2.getBaseStationLatitude();
                            if (temp != Integer.MAX_VALUE) {
                                cellTowerInformation.put("bs_lat", String.valueOf(temp));
                            }
                            temp = loc2.getBaseStationLongitude();
                            if (temp != Integer.MAX_VALUE) {
                                cellTowerInformation.put("bs_lng", String.valueOf(temp));
                            }
                        }
                    }
                }
                if (C4530n.m8522e()) {
                    List<NeighboringCellInfo> nci = null;
                    try {
                        Method getNeighboringCellInfo = C4485at.m8328b(TelephonyManager.class, "getNeighboringCellInfo", new Class[0]);
                        if (getNeighboringCellInfo != null) {
                            Object list = C4485at.m8323a((Object) telephonyManager, getNeighboringCellInfo, new Object[0]);
                            if (list != null && (list instanceof List)) {
                                nci = (List) list;
                            }
                        }
                    } catch (SecurityException e4) {
                        str = f7817a;
                    } catch (Exception e222) {
                        C4549w.m8594c(f7817a, e222.getMessage());
                    }
                    if (nci != null && nci.size() > 0) {
                        for (NeighboringCellInfo info : nci) {
                            if (!(info.getCid() == -1 || info.getRssi() == 99)) {
                                if (!String.valueOf(info.getCid()).equalsIgnoreCase((String) cellTowerInformation.get("cid"))) {
                                    if (!String.valueOf(info.getCid()).equalsIgnoreCase((String) cellTowerInformation.get("bsid"))) {
                                    }
                                }
                                cellTowerInformation.put("sl_ASU", String.valueOf(info.getRssi()));
                            }
                        }
                    }
                }
            }
            if (cellTowerInformation.size() >= 3) {
                return cellTowerInformation.toString();
            }
            return null;
        } catch (SecurityException e5) {
            str = f7817a;
            return null;
        } catch (Exception e2222) {
            C4549w.m8594c(f7817a, e2222.getMessage());
            return null;
        }
    }

    /* renamed from: f */
    static String m8570f(Context context) {
        return new C4497l().mo34156b(context);
    }

    /* renamed from: g */
    static String m8572g(Context context) {
        String str;
        if (new C4525i(context).mo34228a("android.permission.READ_PHONE_STATE", context.getPackageName())) {
            try {
                Object telephonyService = context.getApplicationContext().getSystemService("phone");
                if (telephonyService != null && (telephonyService instanceof TelephonyManager)) {
                    TelephonyManager telephonyManager = (TelephonyManager) telephonyService;
                    Map<String, String> result = new HashMap();
                    C4491ai.m8343a(telephonyManager.getDeviceId(), true, "di", result);
                    C4491ai.m8343a(telephonyManager.getLine1Number(), true, "ln", result);
                    C4491ai.m8343a(telephonyManager.getSimSerialNumber(), true, "ss", result);
                    C4491ai.m8343a(telephonyManager.getSubscriberId(), true, "si", result);
                    C4491ai.m8343a(telephonyManager.getVoiceMailNumber(), true, "vn", result);
                    C4491ai.m8343a(telephonyManager.getDeviceSoftwareVersion(), false, "sv", result);
                    String data = telephonyManager.getVoiceMailAlphaTag();
                    if (C4491ai.m8349f(data) && !data.equalsIgnoreCase("VoiceMail")) {
                        result.put("VMAlphaTag", data);
                    }
                    if (C4516a.f7584c >= 22) {
                        result.putAll(C4546s.m8576a(context));
                    }
                    JSONObject json = new JSONObject();
                    for (String key : result.keySet()) {
                        try {
                            json.put(key, result.get(key));
                        } catch (JSONException exception) {
                            str = f7817a;
                            new StringBuilder("Can't create JSON: ").append(exception.getMessage());
                            return "";
                        }
                    }
                    return !(json instanceof JSONObject) ? json.toString() : JSONObjectInstrumentation.toString(json);
                }
            } catch (SecurityException e) {
                str = f7817a;
                return "";
            } catch (Exception e2) {
                C4549w.m8594c(f7817a, e2.getMessage());
                return "";
            }
        }
        return "";
    }

    /* renamed from: h */
    static String m8573h(Context context) {
        String str;
        if (!C4530n.m8519b()) {
            return null;
        }
        String cellId = null;
        C4525i packageManager = new C4525i(context);
        boolean finePermission = packageManager.mo34228a("android.permission.ACCESS_FINE_LOCATION", context.getPackageName());
        boolean coarsePermission = packageManager.mo34228a("android.permission.ACCESS_COARSE_LOCATION", context.getPackageName());
        if (!finePermission && !coarsePermission) {
            return null;
        }
        if (C4516a.f7584c >= C4517b.f7596k) {
            cellId = C4514f.m8475b(context);
        }
        if (!C4491ai.m8348e(cellId)) {
            return cellId;
        }
        if (!C4530n.m8520c() && !C4530n.m8521d()) {
            return cellId;
        }
        try {
            Object telephonyService = context.getApplicationContext().getSystemService("phone");
            if (telephonyService == null || !(telephonyService instanceof TelephonyManager)) {
                return null;
            }
            TelephonyManager telephonyManager = (TelephonyManager) telephonyService;
            CellLocation cellLocation = null;
            try {
                cellLocation = telephonyManager.getCellLocation();
            } catch (SecurityException e) {
                str = f7817a;
            } catch (Exception e2) {
                C4549w.m8594c(f7817a, e2.getMessage());
            }
            if (cellLocation == null) {
                return cellId;
            }
            StringBuilder cellInfo = new StringBuilder();
            if (cellLocation instanceof GsmCellLocation) {
                GsmCellLocation loc = (GsmCellLocation) telephonyManager.getCellLocation();
                int cid = loc.getCid();
                int lac = loc.getLac();
                if (cid == -1 && lac == -1) {
                    return null;
                }
                cellInfo.append("GSM:");
                if (cid != -1) {
                    cellInfo.append(cid);
                }
                cellInfo.append(":::");
                if (lac != -1) {
                    cellInfo.append(lac);
                }
                return cellInfo.toString();
            } else if (!(cellLocation instanceof CdmaCellLocation)) {
                return cellId;
            } else {
                CdmaCellLocation loc2 = (CdmaCellLocation) telephonyManager.getCellLocation();
                int bid = loc2.getBaseStationId();
                int sid = loc2.getSystemId();
                int nid = loc2.getNetworkId();
                if (bid == -1 && sid == -1 && nid == -1) {
                    return null;
                }
                cellInfo.append("CDMA:");
                if (bid != -1) {
                    cellInfo.append(bid);
                }
                cellInfo.append(":");
                if (sid != -1) {
                    cellInfo.append(sid);
                }
                cellInfo.append(":");
                if (nid != -1) {
                    cellInfo.append(nid);
                }
                return cellInfo.toString();
            }
        } catch (SecurityException e3) {
            str = f7817a;
            return cellId;
        } catch (Exception e22) {
            C4549w.m8594c(f7817a, e22.getMessage());
            return cellId;
        }
    }
}
