package android.support.p000v4.print;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.CancellationSignal.OnCancelListener;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintAttributes.Builder;
import android.print.PrintAttributes.Margins;
import android.print.PrintAttributes.MediaSize;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintDocumentAdapter.WriteResultCallback;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@TargetApi(19)
@RequiresApi
/* renamed from: android.support.v4.print.PrintHelperKitkat */
class PrintHelperKitkat {
    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    private static final String LOG_TAG = "PrintHelperKitkat";
    private static final int MAX_PRINT_SIZE = 3500;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    int mColorMode = 2;
    final Context mContext;
    Options mDecodeOptions = null;
    protected boolean mIsMinMarginsHandlingCorrect = true;
    private final Object mLock = new Object();
    int mOrientation;
    protected boolean mPrintActivityRespectsOrientation = true;
    int mScaleMode = 2;

    /* renamed from: android.support.v4.print.PrintHelperKitkat$OnPrintFinishCallback */
    public interface OnPrintFinishCallback {
        void onFinish();
    }

    PrintHelperKitkat(Context context) {
        this.mContext = context;
    }

    public void setScaleMode(int scaleMode) {
        this.mScaleMode = scaleMode;
    }

    public int getScaleMode() {
        return this.mScaleMode;
    }

    public void setColorMode(int colorMode) {
        this.mColorMode = colorMode;
    }

    public void setOrientation(int orientation) {
        this.mOrientation = orientation;
    }

    public int getOrientation() {
        if (this.mOrientation == 0) {
            return 1;
        }
        return this.mOrientation;
    }

    public int getColorMode() {
        return this.mColorMode;
    }

    private static boolean isPortrait(Bitmap bitmap) {
        if (bitmap.getWidth() <= bitmap.getHeight()) {
            return true;
        }
        return false;
    }

    /* Access modifiers changed, original: protected */
    public Builder copyAttributes(PrintAttributes other) {
        Builder b = new Builder().setMediaSize(other.getMediaSize()).setResolution(other.getResolution()).setMinMargins(other.getMinMargins());
        if (other.getColorMode() != 0) {
            b.setColorMode(other.getColorMode());
        }
        return b;
    }

    public void printBitmap(String jobName, Bitmap bitmap, OnPrintFinishCallback callback) {
        if (bitmap != null) {
            MediaSize mediaSize;
            final int fittingMode = this.mScaleMode;
            PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
            if (PrintHelperKitkat.isPortrait(bitmap)) {
                mediaSize = MediaSize.UNKNOWN_PORTRAIT;
            } else {
                mediaSize = MediaSize.UNKNOWN_LANDSCAPE;
            }
            final String str = jobName;
            final Bitmap bitmap2 = bitmap;
            final OnPrintFinishCallback onPrintFinishCallback = callback;
            printManager.print(jobName, new PrintDocumentAdapter() {
                private PrintAttributes mAttributes;

                public void onLayout(PrintAttributes oldPrintAttributes, PrintAttributes newPrintAttributes, CancellationSignal cancellationSignal, LayoutResultCallback layoutResultCallback, Bundle bundle) {
                    boolean changed = true;
                    this.mAttributes = newPrintAttributes;
                    PrintDocumentInfo info = new PrintDocumentInfo.Builder(str).setContentType(1).setPageCount(1).build();
                    if (newPrintAttributes.equals(oldPrintAttributes)) {
                        changed = false;
                    }
                    layoutResultCallback.onLayoutFinished(info, changed);
                }

                public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor fileDescriptor, CancellationSignal cancellationSignal, WriteResultCallback writeResultCallback) {
                    PrintHelperKitkat.this.writeBitmap(this.mAttributes, fittingMode, bitmap2, fileDescriptor, cancellationSignal, writeResultCallback);
                }

                public void onFinish() {
                    if (onPrintFinishCallback != null) {
                        onPrintFinishCallback.onFinish();
                    }
                }
            }, new Builder().setMediaSize(mediaSize).setColorMode(this.mColorMode).build());
        }
    }

    private Matrix getMatrix(int imageWidth, int imageHeight, RectF content, int fittingMode) {
        Matrix matrix = new Matrix();
        float scale = content.width() / ((float) imageWidth);
        if (fittingMode == 2) {
            scale = Math.max(scale, content.height() / ((float) imageHeight));
        } else {
            scale = Math.min(scale, content.height() / ((float) imageHeight));
        }
        matrix.postScale(scale, scale);
        matrix.postTranslate((content.width() - (((float) imageWidth) * scale)) / 2.0f, (content.height() - (((float) imageHeight) * scale)) / 2.0f);
        return matrix;
    }

    private void writeBitmap(PrintAttributes attributes, int fittingMode, Bitmap bitmap, ParcelFileDescriptor fileDescriptor, CancellationSignal cancellationSignal, WriteResultCallback writeResultCallback) {
        PrintAttributes pdfAttributes;
        if (this.mIsMinMarginsHandlingCorrect) {
            pdfAttributes = attributes;
        } else {
            pdfAttributes = copyAttributes(attributes).setMinMargins(new Margins(0, 0, 0, 0)).build();
        }
        final CancellationSignal cancellationSignal2 = cancellationSignal;
        final Bitmap bitmap2 = bitmap;
        final PrintAttributes printAttributes = attributes;
        final int i = fittingMode;
        final ParcelFileDescriptor parcelFileDescriptor = fileDescriptor;
        final WriteResultCallback writeResultCallback2 = writeResultCallback;
        new AsyncTask<Void, Void, Throwable>() {
            /* Access modifiers changed, original: protected|varargs */
            /* JADX WARNING: Unknown top exception splitter block from list: {B:33:0x00b3=Splitter:B:33:0x00b3, B:46:0x00e4=Splitter:B:46:0x00e4, B:20:0x0078=Splitter:B:20:0x0078} */
            /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
            public java.lang.Throwable doInBackground(java.lang.Void... r13) {
                /*
                r12 = this;
                r7 = 0;
                r8 = r2;	 Catch:{ Throwable -> 0x0080 }
                r8 = r8.isCanceled();	 Catch:{ Throwable -> 0x0080 }
                if (r8 == 0) goto L_0x000a;
            L_0x0009:
                return r7;
            L_0x000a:
                r6 = new android.print.pdf.PrintedPdfDocument;	 Catch:{ Throwable -> 0x0080 }
                r8 = android.support.p000v4.print.PrintHelperKitkat.this;	 Catch:{ Throwable -> 0x0080 }
                r8 = r8.mContext;	 Catch:{ Throwable -> 0x0080 }
                r9 = r3;	 Catch:{ Throwable -> 0x0080 }
                r6.<init>(r8, r9);	 Catch:{ Throwable -> 0x0080 }
                r8 = android.support.p000v4.print.PrintHelperKitkat.this;	 Catch:{ Throwable -> 0x0080 }
                r9 = r4;	 Catch:{ Throwable -> 0x0080 }
                r10 = r3;	 Catch:{ Throwable -> 0x0080 }
                r10 = r10.getColorMode();	 Catch:{ Throwable -> 0x0080 }
                r4 = r8.convertBitmapForColorMode(r9, r10);	 Catch:{ Throwable -> 0x0080 }
                r8 = r2;	 Catch:{ Throwable -> 0x0080 }
                r8 = r8.isCanceled();	 Catch:{ Throwable -> 0x0080 }
                if (r8 != 0) goto L_0x0009;
            L_0x002b:
                r8 = 1;
                r5 = r6.startPage(r8);	 Catch:{ all -> 0x00a6 }
                r8 = android.support.p000v4.print.PrintHelperKitkat.this;	 Catch:{ all -> 0x00a6 }
                r8 = r8.mIsMinMarginsHandlingCorrect;	 Catch:{ all -> 0x00a6 }
                if (r8 == 0) goto L_0x0082;
            L_0x0036:
                r0 = new android.graphics.RectF;	 Catch:{ all -> 0x00a6 }
                r8 = r5.getInfo();	 Catch:{ all -> 0x00a6 }
                r8 = r8.getContentRect();	 Catch:{ all -> 0x00a6 }
                r0.<init>(r8);	 Catch:{ all -> 0x00a6 }
            L_0x0043:
                r8 = android.support.p000v4.print.PrintHelperKitkat.this;	 Catch:{ all -> 0x00a6 }
                r9 = r4.getWidth();	 Catch:{ all -> 0x00a6 }
                r10 = r4.getHeight();	 Catch:{ all -> 0x00a6 }
                r11 = r6;	 Catch:{ all -> 0x00a6 }
                r3 = r8.getMatrix(r9, r10, r0, r11);	 Catch:{ all -> 0x00a6 }
                r8 = android.support.p000v4.print.PrintHelperKitkat.this;	 Catch:{ all -> 0x00a6 }
                r8 = r8.mIsMinMarginsHandlingCorrect;	 Catch:{ all -> 0x00a6 }
                if (r8 == 0) goto L_0x00bb;
            L_0x0059:
                r8 = r5.getCanvas();	 Catch:{ all -> 0x00a6 }
                r9 = 0;
                r8.drawBitmap(r4, r3, r9);	 Catch:{ all -> 0x00a6 }
                r6.finishPage(r5);	 Catch:{ all -> 0x00a6 }
                r8 = r2;	 Catch:{ all -> 0x00a6 }
                r8 = r8.isCanceled();	 Catch:{ all -> 0x00a6 }
                if (r8 == 0) goto L_0x00ca;
            L_0x006c:
                r6.close();	 Catch:{ Throwable -> 0x0080 }
                r8 = r7;	 Catch:{ Throwable -> 0x0080 }
                if (r8 == 0) goto L_0x0078;
            L_0x0073:
                r8 = r7;	 Catch:{ IOException -> 0x00f1 }
                r8.close();	 Catch:{ IOException -> 0x00f1 }
            L_0x0078:
                r8 = r4;	 Catch:{ Throwable -> 0x0080 }
                if (r4 == r8) goto L_0x0009;
            L_0x007c:
                r4.recycle();	 Catch:{ Throwable -> 0x0080 }
                goto L_0x0009;
            L_0x0080:
                r7 = move-exception;
                goto L_0x0009;
            L_0x0082:
                r1 = new android.print.pdf.PrintedPdfDocument;	 Catch:{ all -> 0x00a6 }
                r8 = android.support.p000v4.print.PrintHelperKitkat.this;	 Catch:{ all -> 0x00a6 }
                r8 = r8.mContext;	 Catch:{ all -> 0x00a6 }
                r9 = r5;	 Catch:{ all -> 0x00a6 }
                r1.<init>(r8, r9);	 Catch:{ all -> 0x00a6 }
                r8 = 1;
                r2 = r1.startPage(r8);	 Catch:{ all -> 0x00a6 }
                r0 = new android.graphics.RectF;	 Catch:{ all -> 0x00a6 }
                r8 = r2.getInfo();	 Catch:{ all -> 0x00a6 }
                r8 = r8.getContentRect();	 Catch:{ all -> 0x00a6 }
                r0.<init>(r8);	 Catch:{ all -> 0x00a6 }
                r1.finishPage(r2);	 Catch:{ all -> 0x00a6 }
                r1.close();	 Catch:{ all -> 0x00a6 }
                goto L_0x0043;
            L_0x00a6:
                r8 = move-exception;
                r6.close();	 Catch:{ Throwable -> 0x0080 }
                r9 = r7;	 Catch:{ Throwable -> 0x0080 }
                if (r9 == 0) goto L_0x00b3;
            L_0x00ae:
                r9 = r7;	 Catch:{ IOException -> 0x00ed }
                r9.close();	 Catch:{ IOException -> 0x00ed }
            L_0x00b3:
                r9 = r4;	 Catch:{ Throwable -> 0x0080 }
                if (r4 == r9) goto L_0x00ba;
            L_0x00b7:
                r4.recycle();	 Catch:{ Throwable -> 0x0080 }
            L_0x00ba:
                throw r8;	 Catch:{ Throwable -> 0x0080 }
            L_0x00bb:
                r8 = r0.left;	 Catch:{ all -> 0x00a6 }
                r9 = r0.top;	 Catch:{ all -> 0x00a6 }
                r3.postTranslate(r8, r9);	 Catch:{ all -> 0x00a6 }
                r8 = r5.getCanvas();	 Catch:{ all -> 0x00a6 }
                r8.clipRect(r0);	 Catch:{ all -> 0x00a6 }
                goto L_0x0059;
            L_0x00ca:
                r8 = new java.io.FileOutputStream;	 Catch:{ all -> 0x00a6 }
                r9 = r7;	 Catch:{ all -> 0x00a6 }
                r9 = r9.getFileDescriptor();	 Catch:{ all -> 0x00a6 }
                r8.<init>(r9);	 Catch:{ all -> 0x00a6 }
                r6.writeTo(r8);	 Catch:{ all -> 0x00a6 }
                r6.close();	 Catch:{ Throwable -> 0x0080 }
                r8 = r7;	 Catch:{ Throwable -> 0x0080 }
                if (r8 == 0) goto L_0x00e4;
            L_0x00df:
                r8 = r7;	 Catch:{ IOException -> 0x00ef }
                r8.close();	 Catch:{ IOException -> 0x00ef }
            L_0x00e4:
                r8 = r4;	 Catch:{ Throwable -> 0x0080 }
                if (r4 == r8) goto L_0x0009;
            L_0x00e8:
                r4.recycle();	 Catch:{ Throwable -> 0x0080 }
                goto L_0x0009;
            L_0x00ed:
                r9 = move-exception;
                goto L_0x00b3;
            L_0x00ef:
                r8 = move-exception;
                goto L_0x00e4;
            L_0x00f1:
                r8 = move-exception;
                goto L_0x0078;
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.print.PrintHelperKitkat$C02502.doInBackground(java.lang.Void[]):java.lang.Throwable");
            }

            /* Access modifiers changed, original: protected */
            public void onPostExecute(Throwable throwable) {
                if (cancellationSignal2.isCanceled()) {
                    writeResultCallback2.onWriteCancelled();
                } else if (throwable == null) {
                    writeResultCallback2.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                } else {
                    Log.e(PrintHelperKitkat.LOG_TAG, "Error writing printed content", throwable);
                    writeResultCallback2.onWriteFailed(null);
                }
            }
        }.execute(new Void[0]);
    }

    public void printBitmap(String jobName, Uri imageFile, OnPrintFinishCallback callback) throws FileNotFoundException {
        final int fittingMode = this.mScaleMode;
        final String str = jobName;
        final Uri uri = imageFile;
        final OnPrintFinishCallback onPrintFinishCallback = callback;
        PrintDocumentAdapter printDocumentAdapter = new PrintDocumentAdapter() {
            private PrintAttributes mAttributes;
            Bitmap mBitmap = null;
            AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;

            public void onLayout(PrintAttributes oldPrintAttributes, PrintAttributes newPrintAttributes, CancellationSignal cancellationSignal, LayoutResultCallback layoutResultCallback, Bundle bundle) {
                boolean changed = true;
                synchronized (this) {
                    this.mAttributes = newPrintAttributes;
                }
                if (cancellationSignal.isCanceled()) {
                    layoutResultCallback.onLayoutCancelled();
                } else if (this.mBitmap != null) {
                    PrintDocumentInfo info = new PrintDocumentInfo.Builder(str).setContentType(1).setPageCount(1).build();
                    if (newPrintAttributes.equals(oldPrintAttributes)) {
                        changed = false;
                    }
                    layoutResultCallback.onLayoutFinished(info, changed);
                } else {
                    final CancellationSignal cancellationSignal2 = cancellationSignal;
                    final PrintAttributes printAttributes = newPrintAttributes;
                    final PrintAttributes printAttributes2 = oldPrintAttributes;
                    final LayoutResultCallback layoutResultCallback2 = layoutResultCallback;
                    this.mLoadBitmap = new AsyncTask<Uri, Boolean, Bitmap>() {

                        /* renamed from: android.support.v4.print.PrintHelperKitkat$3$1$1 */
                        class C02531 implements OnCancelListener {
                            C02531() {
                            }

                            public void onCancel() {
                                C02513.this.cancelLoad();
                                C02521.this.cancel(false);
                            }
                        }

                        /* Access modifiers changed, original: protected */
                        public void onPreExecute() {
                            cancellationSignal2.setOnCancelListener(new C02531());
                        }

                        /* Access modifiers changed, original: protected|varargs */
                        public Bitmap doInBackground(Uri... uris) {
                            try {
                                return PrintHelperKitkat.this.loadConstrainedBitmap(uri, PrintHelperKitkat.MAX_PRINT_SIZE);
                            } catch (FileNotFoundException e) {
                                return null;
                            }
                        }

                        /* Access modifiers changed, original: protected */
                        public void onPostExecute(Bitmap bitmap) {
                            super.onPostExecute(bitmap);
                            if (bitmap != null && (!PrintHelperKitkat.this.mPrintActivityRespectsOrientation || PrintHelperKitkat.this.mOrientation == 0)) {
                                MediaSize mediaSize;
                                synchronized (this) {
                                    mediaSize = C02513.this.mAttributes.getMediaSize();
                                }
                                if (!(mediaSize == null || mediaSize.isPortrait() == PrintHelperKitkat.isPortrait(bitmap))) {
                                    Matrix rotation = new Matrix();
                                    rotation.postRotate(90.0f);
                                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), rotation, true);
                                }
                            }
                            C02513.this.mBitmap = bitmap;
                            if (bitmap != null) {
                                boolean changed;
                                PrintDocumentInfo info = new PrintDocumentInfo.Builder(str).setContentType(1).setPageCount(1).build();
                                if (printAttributes.equals(printAttributes2)) {
                                    changed = false;
                                } else {
                                    changed = true;
                                }
                                layoutResultCallback2.onLayoutFinished(info, changed);
                            } else {
                                layoutResultCallback2.onLayoutFailed(null);
                            }
                            C02513.this.mLoadBitmap = null;
                        }

                        /* Access modifiers changed, original: protected */
                        public void onCancelled(Bitmap result) {
                            layoutResultCallback2.onLayoutCancelled();
                            C02513.this.mLoadBitmap = null;
                        }
                    }.execute(new Uri[0]);
                }
            }

            private void cancelLoad() {
                synchronized (PrintHelperKitkat.this.mLock) {
                    if (PrintHelperKitkat.this.mDecodeOptions != null) {
                        PrintHelperKitkat.this.mDecodeOptions.requestCancelDecode();
                        PrintHelperKitkat.this.mDecodeOptions = null;
                    }
                }
            }

            public void onFinish() {
                super.onFinish();
                cancelLoad();
                if (this.mLoadBitmap != null) {
                    this.mLoadBitmap.cancel(true);
                }
                if (onPrintFinishCallback != null) {
                    onPrintFinishCallback.onFinish();
                }
                if (this.mBitmap != null) {
                    this.mBitmap.recycle();
                    this.mBitmap = null;
                }
            }

            public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor fileDescriptor, CancellationSignal cancellationSignal, WriteResultCallback writeResultCallback) {
                PrintHelperKitkat.this.writeBitmap(this.mAttributes, fittingMode, this.mBitmap, fileDescriptor, cancellationSignal, writeResultCallback);
            }
        };
        PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
        Builder builder = new Builder();
        builder.setColorMode(this.mColorMode);
        if (this.mOrientation == 1 || this.mOrientation == 0) {
            builder.setMediaSize(MediaSize.UNKNOWN_LANDSCAPE);
        } else if (this.mOrientation == 2) {
            builder.setMediaSize(MediaSize.UNKNOWN_PORTRAIT);
        }
        printManager.print(jobName, printDocumentAdapter, builder.build());
    }

    private Bitmap loadConstrainedBitmap(Uri uri, int maxSideLength) throws FileNotFoundException {
        Bitmap bitmap = null;
        if (maxSideLength <= 0 || uri == null || this.mContext == null) {
            throw new IllegalArgumentException("bad argument to getScaledBitmap");
        }
        Options opt = new Options();
        opt.inJustDecodeBounds = true;
        loadBitmap(uri, opt);
        int w = opt.outWidth;
        int h = opt.outHeight;
        if (w > 0 && h > 0) {
            int imageSide = Math.max(w, h);
            int sampleSize = 1;
            while (imageSide > maxSideLength) {
                imageSide >>>= 1;
                sampleSize <<= 1;
            }
            if (sampleSize > 0 && Math.min(w, h) / sampleSize > 0) {
                Options decodeOptions;
                synchronized (this.mLock) {
                    this.mDecodeOptions = new Options();
                    this.mDecodeOptions.inMutable = true;
                    this.mDecodeOptions.inSampleSize = sampleSize;
                    decodeOptions = this.mDecodeOptions;
                }
                try {
                    bitmap = loadBitmap(uri, decodeOptions);
                    synchronized (this.mLock) {
                        this.mDecodeOptions = null;
                    }
                } catch (Throwable th) {
                    synchronized (this.mLock) {
                        this.mDecodeOptions = null;
                    }
                }
            }
        }
        return bitmap;
    }

    private Bitmap loadBitmap(Uri uri, Options o) throws FileNotFoundException {
        if (uri == null || this.mContext == null) {
            throw new IllegalArgumentException("bad argument to loadBitmap");
        }
        InputStream is = null;
        try {
            is = this.mContext.getContentResolver().openInputStream(uri);
            Bitmap decodeStream = BitmapFactory.decodeStream(is, null, o);
            if (is != null) {
                try {
                    is.close();
                } catch (IOException t) {
                    Log.w(LOG_TAG, "close fail ", t);
                }
            }
            return decodeStream;
        } catch (Throwable th) {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException t2) {
                    Log.w(LOG_TAG, "close fail ", t2);
                }
            }
        }
    }

    private Bitmap convertBitmapForColorMode(Bitmap original, int colorMode) {
        if (colorMode != 1) {
            return original;
        }
        Bitmap grayscale = Bitmap.createBitmap(original.getWidth(), original.getHeight(), Config.ARGB_8888);
        Canvas c = new Canvas(grayscale);
        Paint p = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0.0f);
        p.setColorFilter(new ColorMatrixColorFilter(cm));
        c.drawBitmap(original, 0.0f, 0.0f, p);
        c.setBitmap(null);
        return grayscale;
    }
}
