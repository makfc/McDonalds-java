package com.mcdonalds.sdk.services.network;

import android.content.Context;
import android.text.TextUtils;
import com.mcdonalds.sdk.McDonalds;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CacheUtils {
    private static final char[] HEX = "0123456789abcdef".toCharArray();
    private static final String LOG_TAG = CacheUtils.class.getSimpleName();

    public static File getDiskCacheDir(Context context, String uniqueName) {
        if (context == null) {
            return null;
        }
        String cachePath = null;
        File internal = context.getCacheDir();
        if (internal != null) {
            cachePath = internal.getPath();
        }
        if (!TextUtils.isEmpty(cachePath)) {
            File cacheFile = new File(cachePath + File.separator + uniqueName);
            boolean dirs = cacheFile.exists() || cacheFile.mkdir();
            if (dirs) {
                return cacheFile;
            }
        }
        return null;
    }

    public static File getOrderCacheFile() {
        File file = getDiskCacheDir(McDonalds.getContext(), "/persistantOrder");
        if (file != null) {
            return new File(file.getPath(), "/Order.obj");
        }
        return null;
    }

    public static String md5(String s) {
        String MD5 = "MD5";
        try {
            MessageDigest digestor = MessageDigest.getInstance("MD5");
            digestor.update(s.getBytes());
            return bytesToHex(digestor.digest());
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[(bytes.length * 2)];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = HEX[v >>> 4];
            hexChars[(j * 2) + 1] = HEX[v & 15];
        }
        return new String(hexChars);
    }
}
