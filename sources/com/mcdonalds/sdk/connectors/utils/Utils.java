package com.mcdonalds.sdk.connectors.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import com.facebook.stetho.common.Utf8Charset;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLEncoder;
import java.util.Enumeration;

public class Utils {
    public static boolean isNotNull(Object... params) {
        for (Object param : params) {
            if ((param instanceof String) && TextUtils.isEmpty((String) param)) {
                return false;
            }
            if (param == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkConnection(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService("connectivity");
        if (conMgr == null) {
            return false;
        }
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected() && netInfo.isAvailable()) {
            return true;
        }
        return false;
    }

    public static String cleanToMarketName(String name) {
        if (name != null) {
            return name.replace("†", "").replace("§", "").replace("+++", "").replace("++", "").replace("+", "").replace("***", "").replace("**", "").replace("*", "").replaceAll("Ãˆ", "È");
        }
        return name;
    }

    public static String urlEncode(String value) {
        String str = null;
        if (value == null) {
            return str;
        }
        try {
            return URLEncoder.encode(value, Utf8Charset.NAME);
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    public static String getLocalIpAddress() {
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                Enumeration<InetAddress> enumIpAddr = ((NetworkInterface) en.nextElement()).getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ip = Formatter.formatIpAddress(inetAddress.hashCode());
                        Log.i("RAUL111", "***** IP=" + ip);
                        return ip;
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("RAUL111", ex.toString());
        }
        return null;
    }
}
