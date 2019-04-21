package com.ensighten;

import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.support.p000v4.app.NotificationCompat.Builder;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.ensighten.google.gson.Gson;
import com.ensighten.google.gson.GsonBuilder;
import com.facebook.internal.ServerProtocol;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class Utils {
    /* renamed from: a */
    private static String f5761a = "";
    /* renamed from: b */
    private static Gson f5762b = new GsonBuilder().disableHtmlEscaping().create();
    /* renamed from: c */
    private static boolean f5763c = true;
    /* renamed from: d */
    private static int f5764d = 0;
    /* renamed from: e */
    private static String f5765e = null;

    /* renamed from: com.ensighten.Utils$1 */
    static class C17321 extends C1730q {
        C17321() {
        }

        /* renamed from: a */
        public final void mo15073a(Throwable th) {
        }
    }

    public static void setUploadURL(String uploadURL) {
        f5761a = uploadURL;
    }

    public static String toJSONString(Object object) {
        return f5762b.toJson(object);
    }

    public static ArrayList<C1692D> jsonRulesToArrayList(JSONArray rules) {
        ArrayList arrayList = new ArrayList();
        try {
            int length = rules.length();
            for (int i = 0; i < length; i++) {
                arrayList.add(new C1692D(rules.getJSONObject(i)));
            }
        } catch (Exception e) {
            if (C1845i.m7348a()) {
                C1845i.m7353c(e);
            }
        }
        return arrayList;
    }

    public static Map<String, Object> getClassInfo(Class<?> instance) {
        for (Field field : instance.getDeclaredFields()) {
            field.getName();
            field.getType().getName();
        }
        return null;
    }

    public static void takeScreenShot(View view) {
        if (view != null) {
            try {
                View rootView = view.getRootView();
                rootView.setDrawingCacheEnabled(true);
                Bitmap drawingCache = rootView.getDrawingCache();
                drawingCache = drawingCache.copy(drawingCache.getConfig(), false);
                rootView.setDrawingCacheEnabled(false);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                drawingCache.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
                C1860u c1860u = new C1860u();
                c1860u.mo15518a("snapshot", new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), Ensighten.getCurrentActivityName() + ".jpg", null);
                JSONObject jSONObject = new JSONObject();
                Ensighten.getDumpManager().mo15495a((ViewGroup) rootView, jSONObject);
                c1860u.mo15519a("meta", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
                new C1856o().mo15509a(null, buildImageURL(Ensighten.getClientId(), Ensighten.getAppId(), Ensighten.getConfigVersion(), String.valueOf(Ensighten.isAdminMode()), String.valueOf(Ensighten.isTestMode())), C1856o.m7412a(c1860u), null, new C17321());
            } catch (Exception e) {
                if (C1845i.m7348a()) {
                    C1845i.m7353c(e);
                }
            }
        }
    }

    public static void showAdminNotification(Activity activity) {
        try {
            NotificationManager notificationManager = (NotificationManager) activity.getSystemService("notification");
            Builder builder = new Builder(activity.getApplicationContext());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(C1719P.m7253a("iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAADoUlEQVRYhe2XX2hbdRTHP9tKb6y26yRdaK7mNmNmATUdhVxxsCibUpTNZQ+bmwR9UfYiyIYP4pODMdAHHwqiL3uQXVCCSthaRWGlZL4sfRDuupLMNlvibhybps2Nk2aw6UOSJmnu/eXG9tEv/F5+5/zO+dz7+8M5m+hScT39BDAEDKwx3QaKR0PBu93E2+TYMxo7DIwTDIVEAKT1y8A3JDR9/QBen0QwdAQ1chIYcwxb1RSp5ATTkz+JnLYIkvcRjb3Pjl1nAH+XyQECyMpLuD1/YC7PUS794xzA6+vjzXfjSK4TgOs/JK+rH7fnZUbVHrKZn60g2gG8vi1EY6eQXO8Am9eRvK5eIIzkmietpzsDhPdG2bHrY+DRDoFLgAncq8XpEUK4PWF6pSvcuG40G9oXqZETwOOCYEVAI6FNAZUa9HZk5S1gXAAygho5xvTklebJ1lsQjb1AMDQjSL7ApYuHmL0832apbt1xBga/wP7vrZDWnyKh3apPrN3jI4Lkd0glP7RMDlDIPyChfQWcE8RwUf1Lq2oABEN+gqHnbJeay9+T1i8IgtchPgeu2vr0b30t/sOPUjsADNeGXfAZCvmKEADAyC1i5BZt7bIyjKys5mkcGK/PB8i2C/u3LhIMDXUEqOpXgW2oNm62AqiRJ4UhZeVTZOWeQ4ARga2vNmgFaJq0Udhh8q60ES/d/wDrUvMZEF8xc/kUhfwtoY8zVTAjq49Z4ynedyCGGjlvuyytj5HQftkAgBY1tqCQ/w24I/B9caOTtwLA3dqwltc3jtfn9CFyrEY90CuZ+APPI7metfSUXCOMqg/JZmbsyqsW7Tuwh9ff1vAHlrhxPcv9ykMxQLn0AFl5DLcnahNyMzCGP1DByM1RLlkfWq9PYv/Bw4yqE8BuBgb3ICuL6LMZK/fWeiC8V2L/wTlgZ4fvWwC+JJW8RiFfrCUeRo3sBI4BT6/xX8HIvUdC0yiX/rYHAPjgk5PAWZwVoytUyzKA7R18ixi505z/bKJ5sr0mzGbm8Qe8SC4nfUAP1eqnU/0I8AgDgxHcnm9J63/WJ9tfwkLeJKF9BFxzELQbFYEzpJILzZPWfUG5ZJLNfIc/0I/kegZxxdtJ94GrXLr4BvFzX6+9QfadUbn0F0YuSWXlNrIyQuc9tlIJI3eW6cnT6LOWvWI3zemrVJvT3cA2wGPhZQJLGLmblEtTpJIXKOSXRGGdA9QU19MeBwC/H31lvHP9CPwLGWUSV9LpaVoAAAAASUVORK5CYII=", 0));
            Bitmap decodeStream = BitmapFactoryInstrumentation.decodeStream(byteArrayInputStream);
            try {
                byteArrayInputStream.close();
            } catch (IOException e) {
            }
            notificationManager.notify(31337, builder.setSmallIcon(17301514).setLargeIcon(decodeStream).setTicker("Ensighten Mobile Admin Mode Enabled").setContentTitle("Ensighten Mobile Admin").setContentText("ClientId: " + Ensighten.getClientId() + ", AppId: " + Ensighten.getAppId()).setAutoCancel(false).setOngoing(true).setContentIntent(PendingIntent.getActivity(Ensighten.getContext(), 0, new Intent(), 0)).build());
        } catch (Throwable th) {
            Toast.makeText(activity, "Ensighten Mobile Admin Mode Enabled, ClientId: " + Ensighten.getClientId() + ", AppId: " + Ensighten.getAppId(), 1).show();
        }
    }

    public static void dismissAdminNotification(Activity activity) {
        if (activity != null) {
            ((NotificationManager) activity.getSystemService("notification")).cancel(31337);
        }
    }

    public static String sha256(String s) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(s.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                stringBuffer.append(Integer.toHexString(b & 255));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String buildConfigURL(String clientId, String appId, String appVersion, String configHash, String eVersion, String isAdminMode, String isTestMode) {
        return String.format("https://%s/mobile2/android/cg.php?clientid=%s&name=%s&version=%s&ch=%s&eVersion=%s&admin=%s&testing=%s", new Object[]{getHost(), URLEncoder.encode(clientId), URLEncoder.encode(appId), URLEncoder.encode(appVersion), URLEncoder.encode(configHash), URLEncoder.encode(eVersion), URLEncoder.encode(isAdminMode), URLEncoder.encode(isTestMode)});
    }

    public static String buildTagContainerURL(String clientId, String appId, String configVersion, String eVersion, String isAdminMode, String isTestMode) {
        return String.format("https://%s/mobile2/android/html/%s/%s/%s/tag_container.html?s=%s&eVersion=%s&admin=%s&testing=%s", new Object[]{getHost(), URLEncoder.encode(clientId), URLEncoder.encode(appId), URLEncoder.encode(configVersion), Long.valueOf(new Date().getTime()), URLEncoder.encode(eVersion), URLEncoder.encode(isAdminMode), URLEncoder.encode(isTestMode)});
    }

    public static String buildModuleURL(String clientId, String appId, String appVersion, String moduleName, String eVersion, String isAdminMode, String isTestMode) {
        return String.format("https://%s/mobile2/android/modules/%s/%s/%s/%s/%s?admin=%s&testing=%s", new Object[]{getHost(), URLEncoder.encode(clientId), URLEncoder.encode(appId), URLEncoder.encode(appVersion), URLEncoder.encode(moduleName), URLEncoder.encode(eVersion), URLEncoder.encode(isAdminMode), URLEncoder.encode(isTestMode)});
    }

    public static String buildImageURL(String clientId, String appId, String configVersion, String isAdminMode, String isTestMode) {
        return String.format("https://%s/mobile2/android/image.php?clientid=%s&app=%s&version=%s&admin=%s&testing=%s", new Object[]{f5761a, URLEncoder.encode(clientId), URLEncoder.encode(appId), URLEncoder.encode(configVersion), URLEncoder.encode(isAdminMode), URLEncoder.encode(isTestMode)});
    }

    public static Method getMethod(Class<?> clazz, String methodName, Class<?>[] classes) throws NoSuchMethodException {
        while (true) {
            try {
                return clazz.getDeclaredMethod(methodName, classes);
            } catch (NoSuchMethodException e) {
                clazz = clazz.getSuperclass();
                if (clazz == null) {
                    throw e;
                }
            }
        }
    }

    public static Field getField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        while (true) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
                if (clazz == null) {
                    return null;
                }
            }
        }
    }

    public static boolean setFieldValue(Object instance, String fieldName, Object value) {
        try {
            Field field = getField(instance.getClass(), fieldName);
            field.setAccessible(true);
            field.set(instance, value);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public static Object getFieldValue(Object instance, String fieldName) {
        try {
            Field field = getField(instance.getClass(), fieldName);
            field.setAccessible(true);
            return field.get(instance);
        } catch (Throwable th) {
            return null;
        }
    }

    public static void showPrivacyDialog(Activity activity) {
        if (activity != null) {
            if (Ensighten.getPrivacyDialog() != null) {
                Ensighten.getPrivacyDialog().show();
                return;
            }
            Dialog dialog = new Dialog(activity);
            dialog.setTitle("Ensighten");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.density;
            int i = (int) (((double) (10.0f * f)) + 0.5d);
            int i2 = (int) (((double) (400.0f * f)) + 0.5d);
            int i3 = (int) (((double) (f * 300.0f)) + 0.5d);
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setPadding(i, i, i, i);
            linearLayout.setLayoutParams(new LayoutParams(i2, i3));
            linearLayout.setOrientation(0);
            LinearLayout linearLayout2 = new LinearLayout(activity);
            linearLayout2.setLayoutParams(new LayoutParams(-1, -2));
            linearLayout2.setOrientation(1);
            TextView textView = new TextView(activity);
            textView.setText(Ensighten.getPrivacyStatement());
            textView.setLayoutParams(new LayoutParams(-2, -2));
            LinearLayout linearLayout3 = new LinearLayout(activity);
            linearLayout3.setLayoutParams(new LayoutParams(-1, -2));
            linearLayout3.setGravity(1);
            Button button = new Button(activity);
            button.setLayoutParams(new LayoutParams(-2, -2));
            button.setText("Yes");
            button.setTag("com.ensighten.privacy.dialog.btn.yes");
            Button button2 = new Button(activity);
            button2.setLayoutParams(new LayoutParams(-2, -2));
            button2.setText("No");
            button2.setTag("com.ensighten.privacy.dialog.btn.no");
            linearLayout3.addView(button2);
            linearLayout3.addView(button);
            linearLayout2.addView(textView);
            linearLayout2.addView(linearLayout3);
            linearLayout.addView(linearLayout2);
            button.setOnClickListener(Ensighten.getInstance());
            button2.setOnClickListener(Ensighten.getInstance());
            dialog.setContentView(linearLayout);
            Ensighten.setPrivacyDialog(dialog);
            dialog.show();
        }
    }

    public static void hidePrivacyDialog() {
        if (Ensighten.getPrivacyDialog() != null) {
            Ensighten.getPrivacyDialog().dismiss();
            Ensighten.setPrivacyDialog(null);
        }
    }

    public static long copy(Reader input, Writer output) throws IOException {
        char[] cArr = new char[4096];
        long j = 0;
        while (true) {
            int read = input.read(cArr);
            if (-1 == read) {
                return j;
            }
            output.write(cArr, 0, read);
            j += (long) read;
        }
    }

    public static boolean isNetworkConnected() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) Ensighten.getContext().getSystemService("connectivity");
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
            if (networkInfo != null && networkInfo.getState() == State.CONNECTED) {
                return true;
            }
            boolean z;
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
            if (networkInfo2 == null || networkInfo2.getState() != State.CONNECTED) {
                z = false;
            } else {
                z = true;
            }
            return z;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getHost() {
        String persistentCookie = Ensighten.getPersistentCookie("NSTNProTestEnabled");
        if (persistentCookie == null || !persistentCookie.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
            return "nexus.ensighten.com";
        }
        return "nexus-test.ensighten.com";
    }

    public static String convertJSONObjectToString(JSONObject jsonObject) {
        if (jsonObject == null) {
            return "";
        }
        return (!(jsonObject instanceof JSONObject) ? jsonObject.toString() : JSONObjectInstrumentation.toString(jsonObject)).replaceAll("\"", "\\'");
    }

    public static String convertJSONObjectToJavascriptCompatibleString(JSONObject jsonObject) {
        if (jsonObject == null) {
            return "";
        }
        return (!(jsonObject instanceof JSONObject) ? jsonObject.toString() : JSONObjectInstrumentation.toString(jsonObject)).replaceAll("'", "\\'");
    }

    public static String objectSizeToString(Object object) {
        return bytesToString(getObjectSize(object));
    }

    public static long getObjectSize(Object object) {
        if (object instanceof String) {
            return (long) ((String) object).getBytes().length;
        }
        return 0;
    }

    public static String durationToString(long duration) {
        String str;
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        double d = (double) duration;
        if (duration < 1000) {
            str = "milliseconds";
        } else if (duration < 60000) {
            str = "seconds";
            d /= 1000.0d;
        } else if (duration < 3600000) {
            str = "seconds";
            d /= 60000.0d;
        } else if (duration < 86400000) {
            str = "hours";
            d /= 3600000.0d;
        } else {
            str = "days";
            d /= 8.64E7d;
        }
        return String.format("%s %s", new Object[]{decimalFormat.format(d), str});
    }

    public static String bytesToString(long bytes) {
        String str;
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        double d = (double) bytes;
        if (bytes < 1000) {
            str = "bytes";
        } else if (bytes < 1000000) {
            str = "KB";
            d /= 1000.0d;
        } else if (bytes < 1000000000) {
            str = "MB";
            d /= 1000000.0d;
        } else if (bytes < 1000000000000L) {
            str = "GB";
            d /= 1.0E9d;
        } else {
            str = "TB";
            d /= 1.0E12d;
        }
        return String.format("%s %s", new Object[]{decimalFormat.format(d), str});
    }

    public static Long decodeStringToLong(String longString) {
        Long l = null;
        try {
            return Long.decode(longString);
        } catch (NumberFormatException e) {
            return l;
        }
    }

    public static boolean isSimpleClassOrMethodName(String classOrMethodName) {
        if (classOrMethodName == null) {
            return false;
        }
        return classOrMethodName.matches("^[a-zA-Z0-9.$]*$");
    }
}
