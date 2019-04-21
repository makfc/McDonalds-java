package com.mcdonalds.app.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.support.p000v4.view.ViewCompat;
import com.ensighten.Ensighten;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.aztec.AztecWriter;
import com.google.zxing.common.BitMatrix;

public class Barcode {
    public static final String NAME = Barcode.class.getSimpleName();

    public static Bitmap generateAztec(String content, int size) {
        Object obj = null;
        Ensighten.evaluateEvent(obj, "com.mcdonalds.app.util.Barcode", "generateAztec", new Object[]{content, new Integer(size)});
        try {
            return toBitmap(new AztecWriter().encode(content, BarcodeFormat.AZTEC, size, size));
        } catch (Exception e) {
            SafeLog.m7738e(NAME, "error generating bar code", e);
            return obj;
        }
    }

    public static Bitmap toBitmap(BitMatrix matrix) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.Barcode", "toBitmap", new Object[]{matrix});
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        Bitmap bmp = Bitmap.createBitmap(width, height, Config.RGB_565);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bmp.setPixel(x, y, matrix.get(x, y) ? ViewCompat.MEASURED_STATE_MASK : -1);
            }
        }
        return bmp;
    }
}
