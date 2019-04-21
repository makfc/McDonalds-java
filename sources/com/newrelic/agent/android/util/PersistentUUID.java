package com.newrelic.agent.android.util;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.analytics.AnalyticsControllerImpl;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.metric.MetricNames;
import com.newrelic.agent.android.stats.StatsEngine;
import java.io.File;
import java.util.Arrays;
import java.util.UUID;

public class PersistentUUID {
    public static final String METRIC_UUID_RECOVERED = "UUIDRecovered";
    private static File UUID_FILE = new File(Environment.getDataDirectory(), UUID_FILENAME);
    private static final String UUID_FILENAME = "nr_installation";
    private static final String UUID_KEY = "nr_uuid";
    private static AgentLog log = AgentLogManager.getAgentLog();

    public PersistentUUID(Context context) {
        UUID_FILE = new File(context.getFilesDir(), UUID_FILENAME);
    }

    public String getDeviceId(Context context) {
        String id = generateUniqueID(context);
        if (TextUtils.isEmpty(id)) {
            return UUID.randomUUID().toString();
        }
        return id;
    }

    private String generateUniqueID(Context context) {
        String hardwareDeviceId = Build.SERIAL;
        String androidDeviceId = Build.ID;
        try {
            androidDeviceId = Secure.getString(context.getContentResolver(), "android_id");
            if (TextUtils.isEmpty(androidDeviceId)) {
                return UUID.randomUUID().toString();
            }
            try {
                TelephonyManager tm = (TelephonyManager) context.getSystemService("phone");
                if (tm != null) {
                    hardwareDeviceId = tm.getDeviceId();
                }
            } catch (Exception e) {
                hardwareDeviceId = "badf00d";
            }
            if (TextUtils.isEmpty(hardwareDeviceId)) {
                hardwareDeviceId = Build.HARDWARE + Build.DEVICE + Build.BOARD + Build.BRAND;
            }
            String str = intToHexString(androidDeviceId.hashCode(), 8) + "-" + intToHexString(hardwareDeviceId.hashCode(), 4) + "-" + intToHexString(VERSION.SDK_INT, 4) + "-" + intToHexString(VERSION.RELEASE.hashCode(), 12);
            throw new RuntimeException("Not supported (TODO)");
        } catch (Exception e2) {
            return UUID.randomUUID().toString();
        }
    }

    private String intToHexString(int value, int sublen) {
        String result = "";
        String string = Integer.toHexString(value);
        char[] chars = new char[(sublen - string.length())];
        Arrays.fill(chars, '0');
        string = new String(chars) + string;
        int count = 0;
        for (int i = string.length() - 1; i >= 0; i--) {
            count++;
            result = string.substring(i, i + 1) + result;
            if (count % sublen == 0) {
                result = "-" + result;
            }
        }
        if (result.startsWith("-")) {
            return result.substring(1, result.length());
        }
        return result;
    }

    /* Access modifiers changed, original: protected */
    public void noticeUUIDMetric(String tag) {
        StatsEngine statsEngine = StatsEngine.get();
        if (statsEngine != null) {
            statsEngine.inc("Supportability/AgentHealth/" + tag);
        } else {
            log.error("StatsEngine is null. " + tag + "  not recorded.");
        }
    }

    public String getPersistentUUID() {
        String uuid = getUUIDFromFileStore();
        if (TextUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            log.info("Created random UUID: " + uuid);
            StatsEngine.get().inc(MetricNames.MOBILE_APP_INSTALL);
            AnalyticsControllerImpl.getInstance().addAttributeUnchecked(new AnalyticAttribute(AnalyticAttribute.APP_INSTALL_ATTRIBUTE, true), false);
            setPersistedUUID(uuid);
            return uuid;
        }
        noticeUUIDMetric(METRIC_UUID_RECOVERED);
        return uuid;
    }

    /* Access modifiers changed, original: protected */
    public void setPersistedUUID(String uuid) {
        putUUIDToFileStore(uuid);
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:22:0x0053=Splitter:B:22:0x0053, B:14:0x0038=Splitter:B:14:0x0038, B:38:0x0089=Splitter:B:38:0x0089, B:30:0x006e=Splitter:B:30:0x006e} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005e A:{SYNTHETIC, Splitter:B:25:0x005e} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0079 A:{SYNTHETIC, Splitter:B:33:0x0079} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0094 A:{SYNTHETIC, Splitter:B:41:0x0094} */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00a6 A:{SYNTHETIC, Splitter:B:47:0x00a6} */
    public java.lang.String getUUIDFromFileStore() {
        /*
        r9 = this;
        r4 = "";
        r6 = UUID_FILE;
        r6 = r6.exists();
        if (r6 == 0) goto L_0x002b;
    L_0x000a:
        r1 = 0;
        r2 = new java.io.BufferedReader;	 Catch:{ FileNotFoundException -> 0x0037, IOException -> 0x0052, JSONException -> 0x006d, NullPointerException -> 0x0088 }
        r6 = new java.io.FileReader;	 Catch:{ FileNotFoundException -> 0x0037, IOException -> 0x0052, JSONException -> 0x006d, NullPointerException -> 0x0088 }
        r7 = UUID_FILE;	 Catch:{ FileNotFoundException -> 0x0037, IOException -> 0x0052, JSONException -> 0x006d, NullPointerException -> 0x0088 }
        r6.<init>(r7);	 Catch:{ FileNotFoundException -> 0x0037, IOException -> 0x0052, JSONException -> 0x006d, NullPointerException -> 0x0088 }
        r2.<init>(r6);	 Catch:{ FileNotFoundException -> 0x0037, IOException -> 0x0052, JSONException -> 0x006d, NullPointerException -> 0x0088 }
        r5 = r2.readLine();	 Catch:{ FileNotFoundException -> 0x00c1, IOException -> 0x00be, JSONException -> 0x00bb, NullPointerException -> 0x00b8, all -> 0x00b5 }
        r3 = new org.json.JSONObject;	 Catch:{ FileNotFoundException -> 0x00c1, IOException -> 0x00be, JSONException -> 0x00bb, NullPointerException -> 0x00b8, all -> 0x00b5 }
        r3.<init>(r5);	 Catch:{ FileNotFoundException -> 0x00c1, IOException -> 0x00be, JSONException -> 0x00bb, NullPointerException -> 0x00b8, all -> 0x00b5 }
        r6 = "nr_uuid";
        r4 = r3.getString(r6);	 Catch:{ FileNotFoundException -> 0x00c1, IOException -> 0x00be, JSONException -> 0x00bb, NullPointerException -> 0x00b8, all -> 0x00b5 }
        if (r2 == 0) goto L_0x002b;
    L_0x0028:
        r2.close();	 Catch:{ IOException -> 0x002c }
    L_0x002b:
        return r4;
    L_0x002c:
        r0 = move-exception;
        r6 = log;
        r7 = r0.getMessage();
        r6.error(r7);
        goto L_0x002b;
    L_0x0037:
        r0 = move-exception;
    L_0x0038:
        r6 = log;	 Catch:{ all -> 0x00a3 }
        r7 = r0.getMessage();	 Catch:{ all -> 0x00a3 }
        r6.error(r7);	 Catch:{ all -> 0x00a3 }
        if (r1 == 0) goto L_0x002b;
    L_0x0043:
        r1.close();	 Catch:{ IOException -> 0x0047 }
        goto L_0x002b;
    L_0x0047:
        r0 = move-exception;
        r6 = log;
        r7 = r0.getMessage();
        r6.error(r7);
        goto L_0x002b;
    L_0x0052:
        r0 = move-exception;
    L_0x0053:
        r6 = log;	 Catch:{ all -> 0x00a3 }
        r7 = r0.getMessage();	 Catch:{ all -> 0x00a3 }
        r6.error(r7);	 Catch:{ all -> 0x00a3 }
        if (r1 == 0) goto L_0x002b;
    L_0x005e:
        r1.close();	 Catch:{ IOException -> 0x0062 }
        goto L_0x002b;
    L_0x0062:
        r0 = move-exception;
        r6 = log;
        r7 = r0.getMessage();
        r6.error(r7);
        goto L_0x002b;
    L_0x006d:
        r0 = move-exception;
    L_0x006e:
        r6 = log;	 Catch:{ all -> 0x00a3 }
        r7 = r0.getMessage();	 Catch:{ all -> 0x00a3 }
        r6.error(r7);	 Catch:{ all -> 0x00a3 }
        if (r1 == 0) goto L_0x002b;
    L_0x0079:
        r1.close();	 Catch:{ IOException -> 0x007d }
        goto L_0x002b;
    L_0x007d:
        r0 = move-exception;
        r6 = log;
        r7 = r0.getMessage();
        r6.error(r7);
        goto L_0x002b;
    L_0x0088:
        r0 = move-exception;
    L_0x0089:
        r6 = log;	 Catch:{ all -> 0x00a3 }
        r7 = r0.getMessage();	 Catch:{ all -> 0x00a3 }
        r6.error(r7);	 Catch:{ all -> 0x00a3 }
        if (r1 == 0) goto L_0x002b;
    L_0x0094:
        r1.close();	 Catch:{ IOException -> 0x0098 }
        goto L_0x002b;
    L_0x0098:
        r0 = move-exception;
        r6 = log;
        r7 = r0.getMessage();
        r6.error(r7);
        goto L_0x002b;
    L_0x00a3:
        r6 = move-exception;
    L_0x00a4:
        if (r1 == 0) goto L_0x00a9;
    L_0x00a6:
        r1.close();	 Catch:{ IOException -> 0x00aa }
    L_0x00a9:
        throw r6;
    L_0x00aa:
        r0 = move-exception;
        r7 = log;
        r8 = r0.getMessage();
        r7.error(r8);
        goto L_0x00a9;
    L_0x00b5:
        r6 = move-exception;
        r1 = r2;
        goto L_0x00a4;
    L_0x00b8:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0089;
    L_0x00bb:
        r0 = move-exception;
        r1 = r2;
        goto L_0x006e;
    L_0x00be:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0053;
    L_0x00c1:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0038;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newrelic.agent.android.util.PersistentUUID.getUUIDFromFileStore():java.lang.String");
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:20:0x0050=Splitter:B:20:0x0050, B:12:0x0035=Splitter:B:12:0x0035} */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0040 A:{SYNTHETIC, Splitter:B:15:0x0040} */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005b A:{SYNTHETIC, Splitter:B:23:0x005b} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006d A:{SYNTHETIC, Splitter:B:29:0x006d} */
    public void putUUIDToFileStore(java.lang.String r8) {
        /*
        r7 = this;
        r2 = 0;
        r1 = new org.json.JSONObject;	 Catch:{ IOException -> 0x0034, JSONException -> 0x004f }
        r1.<init>();	 Catch:{ IOException -> 0x0034, JSONException -> 0x004f }
        r4 = "nr_uuid";
        r1.put(r4, r8);	 Catch:{ IOException -> 0x0034, JSONException -> 0x004f }
        r3 = new java.io.BufferedWriter;	 Catch:{ IOException -> 0x0034, JSONException -> 0x004f }
        r4 = new java.io.FileWriter;	 Catch:{ IOException -> 0x0034, JSONException -> 0x004f }
        r5 = UUID_FILE;	 Catch:{ IOException -> 0x0034, JSONException -> 0x004f }
        r4.<init>(r5);	 Catch:{ IOException -> 0x0034, JSONException -> 0x004f }
        r3.<init>(r4);	 Catch:{ IOException -> 0x0034, JSONException -> 0x004f }
        r4 = r1.toString();	 Catch:{ IOException -> 0x0082, JSONException -> 0x007f, all -> 0x007c }
        r3.write(r4);	 Catch:{ IOException -> 0x0082, JSONException -> 0x007f, all -> 0x007c }
        r3.flush();	 Catch:{ IOException -> 0x0082, JSONException -> 0x007f, all -> 0x007c }
        if (r3 == 0) goto L_0x0085;
    L_0x0023:
        r3.close();	 Catch:{ IOException -> 0x0028 }
        r2 = r3;
    L_0x0027:
        return;
    L_0x0028:
        r0 = move-exception;
        r4 = log;
        r5 = r0.getMessage();
        r4.error(r5);
        r2 = r3;
        goto L_0x0027;
    L_0x0034:
        r0 = move-exception;
    L_0x0035:
        r4 = log;	 Catch:{ all -> 0x006a }
        r5 = r0.getMessage();	 Catch:{ all -> 0x006a }
        r4.error(r5);	 Catch:{ all -> 0x006a }
        if (r2 == 0) goto L_0x0027;
    L_0x0040:
        r2.close();	 Catch:{ IOException -> 0x0044 }
        goto L_0x0027;
    L_0x0044:
        r0 = move-exception;
        r4 = log;
        r5 = r0.getMessage();
        r4.error(r5);
        goto L_0x0027;
    L_0x004f:
        r0 = move-exception;
    L_0x0050:
        r4 = log;	 Catch:{ all -> 0x006a }
        r5 = r0.getMessage();	 Catch:{ all -> 0x006a }
        r4.error(r5);	 Catch:{ all -> 0x006a }
        if (r2 == 0) goto L_0x0027;
    L_0x005b:
        r2.close();	 Catch:{ IOException -> 0x005f }
        goto L_0x0027;
    L_0x005f:
        r0 = move-exception;
        r4 = log;
        r5 = r0.getMessage();
        r4.error(r5);
        goto L_0x0027;
    L_0x006a:
        r4 = move-exception;
    L_0x006b:
        if (r2 == 0) goto L_0x0070;
    L_0x006d:
        r2.close();	 Catch:{ IOException -> 0x0071 }
    L_0x0070:
        throw r4;
    L_0x0071:
        r0 = move-exception;
        r5 = log;
        r6 = r0.getMessage();
        r5.error(r6);
        goto L_0x0070;
    L_0x007c:
        r4 = move-exception;
        r2 = r3;
        goto L_0x006b;
    L_0x007f:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0050;
    L_0x0082:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0035;
    L_0x0085:
        r2 = r3;
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newrelic.agent.android.util.PersistentUUID.putUUIDToFileStore(java.lang.String):void");
    }
}
