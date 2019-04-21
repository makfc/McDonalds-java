package com.admaster.square.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/* renamed from: com.admaster.square.utils.q */
public class SelfInfoUtil {
    /* renamed from: a */
    public static String m469a(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return telephonyManager.getSimSerialNumber();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m468a() {
        try {
            String str = "";
            try {
                InputStream inputStream = new ProcessBuilder(new String[]{"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"}).start().getInputStream();
                byte[] bArr = new byte[24];
                while (inputStream.read(bArr) != -1) {
                    str = new StringBuilder(String.valueOf(str)).append(new String(bArr)).toString();
                }
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                str = "N/A";
            }
            return str.trim();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "N/A";
        }
    }

    /* renamed from: b */
    public static String m470b() {
        try {
            String str = "";
            try {
                InputStream inputStream = new ProcessBuilder(new String[]{"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"}).start().getInputStream();
                byte[] bArr = new byte[24];
                while (inputStream.read(bArr) != -1) {
                    str = new StringBuilder(String.valueOf(str)).append(new String(bArr)).toString();
                }
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                str = "N/A";
            }
            return str.trim();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "N/A";
        }
    }

    /* renamed from: c */
    public static String m471c() {
        try {
            String[] split = new BufferedReader(new FileReader("/proc/cpuinfo")).readLine().split(":\\s+", 2);
            for (int i = 0; i < split.length; i++) {
            }
            return split[1];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: d */
    public static int m472d() {
        try {
            return new File("/sys/devices/system/cpu/").listFiles(new C0494r()).length;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    /* renamed from: e */
    public static long m473e() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: f */
    public static long m474f() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: g */
    public static String m475g() {
        String str = "";
        str = "";
        str = "0000000000000000";
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /proc/cpuinfo").getInputStream()));
            for (int i = 1; i < 100; i++) {
                String readLine = lineNumberReader.readLine();
                if (readLine == null) {
                    return str;
                }
                if (readLine.indexOf("Serial") > -1) {
                    return readLine.substring(readLine.indexOf(":") + 1, readLine.length()).trim();
                }
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }
}
