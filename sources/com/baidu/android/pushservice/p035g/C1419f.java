package com.baidu.android.pushservice.p035g;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.text.TextUtils;
import com.baidu.android.pushservice.p034f.C1402a;
import com.baidu.android.pushservice.p034f.C1403b;
import com.baidu.android.pushservice.p036h.C1425a;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.baidu.android.pushservice.g.f */
public class C1419f {
    /* renamed from: a */
    private static final String f4983a = ("baidu/pushservice" + File.separator + "lappicons");

    /* renamed from: a */
    private static int m6409a(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    /* renamed from: a */
    public static Bitmap m6410a(Context context, Bitmap bitmap) {
        if (bitmap == null) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int a = C1419f.m6409a(context);
        int i = (a / 40) + 9;
        BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, width, height, false));
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        RectF rectF = new RectF(0.0f, 0.0f, (float) width, (float) height);
        Paint paint = new Paint(1);
        paint.setColor(-7829368);
        canvas.drawRoundRect(rectF, (float) i, (float) i, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        bitmapDrawable.setBounds(0, 0, width, height);
        canvas.saveLayer(rectF, paint, 31);
        bitmapDrawable.draw(canvas);
        canvas.restore();
        switch (a) {
            case 120:
                height = 36;
                width = 36;
                break;
            case 160:
                height = 48;
                width = 48;
                break;
            case 240:
                height = 72;
                width = 72;
                break;
            case 320:
                height = 96;
                width = 96;
                break;
            case 480:
                height = 144;
                width = 144;
                break;
            default:
                height = 192;
                width = 192;
                break;
        }
        return Bitmap.createScaledBitmap(createBitmap, height, width, true);
    }

    /* renamed from: a */
    public static void m6411a(byte[] bArr, String str) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            try {
                String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + f4983a;
                File file = new File(str2);
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(new File(str2 + File.separator + str + ".bdi"));
                fileOutputStream.write(bArr);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                C1425a.m6444e("ShortcutUtils", "error " + e.getMessage());
            }
        }
    }

    /* renamed from: a */
    public static byte[] m6412a(Context context, String str, String str2, boolean z) {
        byte[] a = C1419f.m6413a(context, z, str);
        if (a == null) {
            if (!TextUtils.isEmpty(str2)) {
                C1425a.m6442c("ShortcutUtils", "download Lightapp icon: " + str2);
                try {
                    C1402a a2 = C1403b.m6259a(str2, "GET", null);
                    InputStream a3 = a2.mo13742a();
                    if (a2.mo13745b() == 200 && a3 != null) {
                        a = C1419f.m6414a(a3);
                    }
                    C1403b.m6265a(a3);
                } catch (Exception e) {
                    C1425a.m6444e("ShortcutUtils", "error " + e.getMessage());
                    C1403b.m6265a(null);
                } catch (Throwable th) {
                    C1403b.m6265a(null);
                }
            }
            if (a != null) {
                C1419f.m6411a(a, str);
            }
        }
        return a;
    }

    /* renamed from: a */
    public static byte[] m6413a(Context context, boolean z, String str) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + f4983a + File.separator + str + ".bdi");
            if (!file.exists()) {
                return null;
            }
            if (z && System.currentTimeMillis() - file.lastModified() > 604800000) {
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[((int) file.length())];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return bArr;
        } catch (IOException e) {
            C1425a.m6444e("ShortcutUtils", "error " + e.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    private static byte[] m6414a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read;
            try {
                read = inputStream.read(bArr, 0, bArr.length);
            } catch (IOException e) {
                C1425a.m6444e("ShortcutUtils", "error " + e.getMessage());
                read = 0;
            }
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return toByteArray;
            }
        }
    }

    /* renamed from: b */
    public static Bitmap m6415b(Context context, Bitmap bitmap) {
        int i = 195;
        if (bitmap == null) {
            return bitmap;
        }
        int i2;
        bitmap.getWidth();
        bitmap.getHeight();
        switch (C1419f.m6409a(context)) {
            case 120:
                i2 = 540;
                break;
            case 160:
                i2 = 540;
                break;
            case 240:
                i = 260;
                i2 = 720;
                break;
            case 320:
                i = 260;
                i2 = 720;
                break;
            case 480:
                i2 = 1080;
                i = 390;
                break;
            default:
                i2 = 1080;
                i = 390;
                break;
        }
        return Bitmap.createScaledBitmap(bitmap, i2, i, true);
    }

    /* renamed from: c */
    public static Bitmap m6416c(Context context, Bitmap bitmap) {
        int i = 26;
        int i2 = 20;
        if (bitmap == null) {
            return bitmap;
        }
        bitmap.getWidth();
        bitmap.getHeight();
        switch (C1419f.m6409a(context)) {
            case 120:
                i = 20;
                break;
            case 160:
                i = 20;
                break;
            case 240:
                i2 = 26;
                break;
            case 320:
                i2 = 26;
                break;
            case 480:
                i2 = 38;
                i = 38;
                break;
            default:
                i2 = 38;
                i = 38;
                break;
        }
        return Bitmap.createScaledBitmap(bitmap, i, i2, true);
    }
}
