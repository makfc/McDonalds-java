package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.FileDescriptor;

/* renamed from: com.amap.api.mapcore.util.dc */
public class ImageResizer extends ImageWorker {
    /* renamed from: a */
    protected int f1738a;
    /* renamed from: b */
    protected int f1739b;

    public ImageResizer(Context context, int i, int i2) {
        super(context);
        mo9266a(i, i2);
    }

    /* renamed from: a */
    public void mo9266a(int i, int i2) {
        this.f1738a = i;
        this.f1739b = i2;
    }

    /* renamed from: a */
    private Bitmap m2310a(int i) {
        return ImageResizer.m2311a(this.f1732d, i, this.f1738a, this.f1739b, mo9250a());
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public Bitmap mo9249a(Object obj) {
        return m2310a(Integer.parseInt(String.valueOf(obj)));
    }

    /* renamed from: a */
    public static Bitmap m2311a(Resources resources, int i, int i2, int i3, ImageCache imageCache) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactoryInstrumentation.decodeResource(resources, i, options);
        options.inSampleSize = ImageResizer.m2309a(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactoryInstrumentation.decodeResource(resources, i, options);
    }

    /* renamed from: a */
    public static Bitmap m2312a(FileDescriptor fileDescriptor, int i, int i2, ImageCache imageCache) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactoryInstrumentation.decodeFileDescriptor(fileDescriptor, null, options);
        options.inSampleSize = ImageResizer.m2309a(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactoryInstrumentation.decodeFileDescriptor(fileDescriptor, null, options);
    }

    /* renamed from: a */
    public static int m2309a(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            i5 = Math.round(((float) i3) / ((float) i2));
            int round = Math.round(((float) i4) / ((float) i));
            if (i5 >= round) {
                i5 = round;
            }
            float f = (float) (i4 * i3);
            while (f / ((float) (i5 * i5)) > ((float) ((i * i2) * 2))) {
                i5++;
            }
        }
        return i5;
    }
}
