package com.facebook.stetho.common;

import java.io.FileInputStream;
import java.io.IOException;
import javax.annotation.Nullable;

public class ProcessUtil {
    private static final int CMDLINE_BUFFER_SIZE = 64;
    private static String sProcessName;
    private static boolean sProcessNameRead;

    @Nullable
    public static synchronized String getProcessName() {
        String str;
        synchronized (ProcessUtil.class) {
            if (!sProcessNameRead) {
                sProcessNameRead = true;
                try {
                    sProcessName = readProcessName();
                } catch (IOException e) {
                }
            }
            str = sProcessName;
        }
        return str;
    }

    private static String readProcessName() throws IOException {
        boolean z = true;
        byte[] cmdlineBuffer = new byte[64];
        FileInputStream stream = new FileInputStream("/proc/self/cmdline");
        boolean success = false;
        try {
            int n = stream.read(cmdlineBuffer);
            success = true;
            int endIndex = indexOf(cmdlineBuffer, 0, n, (byte) 0);
            if (endIndex <= 0) {
                endIndex = n;
            }
            String str = new String(cmdlineBuffer, 0, endIndex);
            return str;
        } finally {
            if (success) {
                z = false;
            }
            Util.close(stream, z);
        }
    }

    private static int indexOf(byte[] haystack, int offset, int length, byte needle) {
        for (int i = 0; i < haystack.length; i++) {
            if (haystack[i] == needle) {
                return i;
            }
        }
        return -1;
    }
}
