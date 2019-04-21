package p041io.github.inflationx.calligraphy3;

import android.graphics.Typeface;
import java.util.HashMap;
import java.util.Map;

/* renamed from: io.github.inflationx.calligraphy3.TypefaceUtils */
public final class TypefaceUtils {
    private static final Map<String, Typeface> sCachedFonts = new HashMap();
    private static final Map<Typeface, CalligraphyTypefaceSpan> sCachedSpans = new HashMap();

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public static android.graphics.Typeface load(android.content.res.AssetManager r7, java.lang.String r8) {
        /*
        r2 = 0;
        r3 = sCachedFonts;
        monitor-enter(r3);
        r4 = sCachedFonts;	 Catch:{ Exception -> 0x0017 }
        r4 = r4.containsKey(r8);	 Catch:{ Exception -> 0x0017 }
        if (r4 != 0) goto L_0x003f;
    L_0x000c:
        r1 = android.graphics.Typeface.createFromAsset(r7, r8);	 Catch:{ Exception -> 0x0017 }
        r4 = sCachedFonts;	 Catch:{ Exception -> 0x0017 }
        r4.put(r8, r1);	 Catch:{ Exception -> 0x0017 }
        monitor-exit(r3);	 Catch:{ all -> 0x004a }
    L_0x0016:
        return r1;
    L_0x0017:
        r0 = move-exception;
        r4 = "Calligraphy";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004a }
        r5.<init>();	 Catch:{ all -> 0x004a }
        r6 = "Can't create asset from ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x004a }
        r5 = r5.append(r8);	 Catch:{ all -> 0x004a }
        r6 = ". Make sure you have passed in the correct path and file name.";
        r5 = r5.append(r6);	 Catch:{ all -> 0x004a }
        r5 = r5.toString();	 Catch:{ all -> 0x004a }
        android.util.Log.w(r4, r5, r0);	 Catch:{ all -> 0x004a }
        r4 = sCachedFonts;	 Catch:{ all -> 0x004a }
        r5 = 0;
        r4.put(r8, r5);	 Catch:{ all -> 0x004a }
        monitor-exit(r3);	 Catch:{ all -> 0x004a }
        r1 = r2;
        goto L_0x0016;
    L_0x003f:
        r2 = sCachedFonts;	 Catch:{ all -> 0x004a }
        r2 = r2.get(r8);	 Catch:{ all -> 0x004a }
        r2 = (android.graphics.Typeface) r2;	 Catch:{ all -> 0x004a }
        monitor-exit(r3);	 Catch:{ all -> 0x004a }
        r1 = r2;
        goto L_0x0016;
    L_0x004a:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x004a }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: p041io.github.inflationx.calligraphy3.TypefaceUtils.load(android.content.res.AssetManager, java.lang.String):android.graphics.Typeface");
    }

    public static CalligraphyTypefaceSpan getSpan(Typeface typeface) {
        if (typeface == null) {
            return null;
        }
        synchronized (sCachedSpans) {
            if (sCachedSpans.containsKey(typeface)) {
                CalligraphyTypefaceSpan calligraphyTypefaceSpan = (CalligraphyTypefaceSpan) sCachedSpans.get(typeface);
                return calligraphyTypefaceSpan;
            }
            CalligraphyTypefaceSpan span = new CalligraphyTypefaceSpan(typeface);
            sCachedSpans.put(typeface, span);
            return span;
        }
    }

    public static boolean isLoaded(Typeface typeface) {
        return typeface != null && sCachedFonts.containsValue(typeface);
    }

    private TypefaceUtils() {
    }
}
