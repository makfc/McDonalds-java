package p041io.github.inflationx.calligraphy3;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.TextView.BufferType;

/* renamed from: io.github.inflationx.calligraphy3.CalligraphyUtils */
public final class CalligraphyUtils {
    public static final int[] ANDROID_ATTR_TEXT_APPEARANCE = new int[]{16842804};
    private static Boolean sAppCompatViewCheck = null;
    private static Boolean sToolbarCheck = null;

    /*  JADX ERROR: JadxRuntimeException in pass: SSATransform
        jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r5_3 ?) in PHI: PHI: (r5_8 ?) = (r5_0 ?), (r5_0 ?), (r5_0 ?), (r5_4 ?), (r5_7 ?), (r5_0 ?), (r5_3 ?), (r5_0 ?) binds: {(r5_0 ?)=B:22:0x0032, (r5_0 ?)=B:2:0x0003, (r5_0 ?)=B:20:0x0028, (r5_4 ?)=B:23:0x0022, (r5_7 ?)=B:19:0x0037}
        	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
        	at jadx.core.dex.visitors.ssa.SSATransform.inlinePhiInsn(SSATransform.java:392)
        	at jadx.core.dex.visitors.ssa.SSATransform.replacePhiWithMove(SSATransform.java:360)
        	at jadx.core.dex.visitors.ssa.SSATransform.fixPhiWithSameArgs(SSATransform.java:300)
        	at jadx.core.dex.visitors.ssa.SSATransform.fixUselessPhi(SSATransform.java:275)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:61)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.util.ArrayList.forEach(ArrayList.java:1249)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
        	at java.lang.Iterable.forEach(Iterable.java:75)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
        	at jadx.core.ProcessClass.process(ProcessClass.java:37)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    static java.lang.String pullFontPathFromTextAppearance(android.content.Context r8, android.util.AttributeSet r9, int[] r10) {
        /*
        r5 = 0;
        if (r10 == 0) goto L_0x0005;
        if (r9 != 0) goto L_0x0006;
        return r5;
        r3 = -1;
        r6 = ANDROID_ATTR_TEXT_APPEARANCE;
        r4 = r8.obtainStyledAttributes(r9, r6);
        if (r4 == 0) goto L_0x0018;
        r6 = 0;
        r7 = -1;
        r3 = r4.getResourceId(r6, r7);	 Catch:{ Exception -> 0x0027, all -> 0x002c }
        r4.recycle();
        r2 = r8.obtainStyledAttributes(r3, r10);
        if (r2 == 0) goto L_0x0005;
        r6 = 0;
        r5 = r2.getString(r6);	 Catch:{ Exception -> 0x0031, all -> 0x0036 }
        r2.recycle();
        goto L_0x0005;
        r1 = move-exception;
        r4.recycle();
        goto L_0x0005;
        r5 = move-exception;
        r4.recycle();
        throw r5;
        r0 = move-exception;
        r2.recycle();
        goto L_0x0005;
        r5 = move-exception;
        r2.recycle();
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: p041io.github.inflationx.calligraphy3.CalligraphyUtils.pullFontPathFromTextAppearance(android.content.Context, android.util.AttributeSet, int[]):java.lang.String");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: SSATransform
        jadx.core.utils.exceptions.JadxRuntimeException: Unknown predecessor block by arg (r6_1 ?) in PHI: PHI: (r6_8 ?) = (r6_0 ?), (r6_0 ?), (r6_2 ?), (r6_5 ?), (r6_0 ?), (r6_0 ?), (r6_1 ?), (r6_0 ?), (r6_0 ?) binds: {(r6_0 ?)=B:9:0x002f, (r6_0 ?)=B:2:0x0006, (r6_2 ?)=B:22:0x0035, (r6_5 ?)=B:18:0x004a, (r6_0 ?)=B:19:0x003b}
        	at jadx.core.dex.instructions.PhiInsn.replaceArg(PhiInsn.java:78)
        	at jadx.core.dex.visitors.ssa.SSATransform.inlinePhiInsn(SSATransform.java:392)
        	at jadx.core.dex.visitors.ssa.SSATransform.replacePhiWithMove(SSATransform.java:360)
        	at jadx.core.dex.visitors.ssa.SSATransform.fixPhiWithSameArgs(SSATransform.java:300)
        	at jadx.core.dex.visitors.ssa.SSATransform.fixUselessPhi(SSATransform.java:275)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:61)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.util.ArrayList.forEach(ArrayList.java:1249)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
        	at java.lang.Iterable.forEach(Iterable.java:75)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
        	at jadx.core.ProcessClass.process(ProcessClass.java:37)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    static java.lang.String pullFontPathFromTheme(android.content.Context r11, int r12, int r13, int[] r14) {
        /*
        r8 = 1;
        r10 = 0;
        r9 = -1;
        r6 = 0;
        if (r12 == r9) goto L_0x0008;
        if (r14 != 0) goto L_0x0009;
        return r6;
        r4 = r11.getTheme();
        r5 = new android.util.TypedValue;
        r5.<init>();
        r4.resolveAttribute(r12, r5, r8);
        r2 = -1;
        r7 = r5.resourceId;
        r8 = new int[r8];
        r8[r10] = r13;
        r1 = r4.obtainStyledAttributes(r7, r8);
        r7 = 0;
        r8 = -1;
        r2 = r1.getResourceId(r7, r8);	 Catch:{ Exception -> 0x003a, all -> 0x003f }
        r1.recycle();
        if (r2 == r9) goto L_0x0008;
        r3 = r11.obtainStyledAttributes(r2, r14);
        if (r3 == 0) goto L_0x0008;
        r7 = 0;
        r6 = r3.getString(r7);	 Catch:{ Exception -> 0x0044, all -> 0x0049 }
        r3.recycle();
        goto L_0x0008;
        r0 = move-exception;
        r1.recycle();
        goto L_0x0008;
        r6 = move-exception;
        r1.recycle();
        throw r6;
        r0 = move-exception;
        r3.recycle();
        goto L_0x0008;
        r6 = move-exception;
        r3.recycle();
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: p041io.github.inflationx.calligraphy3.CalligraphyUtils.pullFontPathFromTheme(android.content.Context, int, int, int[]):java.lang.String");
    }

    public static CharSequence applyTypefaceSpan(CharSequence s, Typeface typeface) {
        if (s != null && s.length() > 0) {
            if (!(s instanceof Spannable)) {
                s = new SpannableString(s);
            }
            ((Spannable) s).setSpan(TypefaceUtils.getSpan(typeface), 0, s.length(), 33);
        }
        return s;
    }

    public static boolean applyFontToTextView(TextView textView, Typeface typeface) {
        return CalligraphyUtils.applyFontToTextView(textView, typeface, false);
    }

    public static boolean applyFontToTextView(TextView textView, final Typeface typeface, boolean deferred) {
        if (textView == null || typeface == null) {
            return false;
        }
        textView.setPaintFlags((textView.getPaintFlags() | 128) | 1);
        textView.setTypeface(typeface);
        if (deferred) {
            textView.setText(CalligraphyUtils.applyTypefaceSpan(textView.getText(), typeface), BufferType.SPANNABLE);
            textView.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                public void afterTextChanged(Editable s) {
                    CalligraphyUtils.applyTypefaceSpan(s, typeface);
                }
            });
        }
        return true;
    }

    public static boolean applyFontToTextView(Context context, TextView textView, String filePath) {
        return CalligraphyUtils.applyFontToTextView(context, textView, filePath, false);
    }

    static boolean applyFontToTextView(Context context, TextView textView, String filePath, boolean deferred) {
        if (textView == null || context == null) {
            return false;
        }
        return CalligraphyUtils.applyFontToTextView(textView, TypefaceUtils.load(context.getAssets(), filePath), deferred);
    }

    static void applyFontToTextView(Context context, TextView textView, CalligraphyConfig config) {
        CalligraphyUtils.applyFontToTextView(context, textView, config, false);
    }

    static void applyFontToTextView(Context context, TextView textView, CalligraphyConfig config, boolean deferred) {
        if (context != null && textView != null && config != null && config.isFontSet()) {
            CalligraphyUtils.applyFontToTextView(context, textView, config.getFontPath(), deferred);
        }
    }

    public static void applyFontToTextView(Context context, TextView textView, CalligraphyConfig config, String textViewFont) {
        CalligraphyUtils.applyFontToTextView(context, textView, config, textViewFont, false);
    }

    static void applyFontToTextView(Context context, TextView textView, CalligraphyConfig config, String textViewFont, boolean deferred) {
        if (context != null && textView != null && config != null) {
            if (TextUtils.isEmpty(textViewFont) || !CalligraphyUtils.applyFontToTextView(context, textView, textViewFont, deferred)) {
                CalligraphyUtils.applyFontToTextView(context, textView, config, deferred);
            }
        }
    }

    static String pullFontPathFromView(Context context, AttributeSet attrs, int[] attributeId) {
        if (attributeId == null || attrs == null) {
            return null;
        }
        try {
            String attributeName = context.getResources().getResourceEntryName(attributeId[0]);
            int stringResourceId = attrs.getAttributeResourceValue(null, attributeName, -1);
            if (stringResourceId > 0) {
                return context.getString(stringResourceId);
            }
            return attrs.getAttributeValue(null, attributeName);
        } catch (NotFoundException e) {
            return null;
        }
    }

    static String pullFontPathFromStyle(Context context, AttributeSet attrs, int[] attributeId) {
        if (attributeId == null || attrs == null) {
            return null;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, attributeId);
        if (typedArray != null) {
            try {
                String fontFromAttribute = typedArray.getString(0);
                if (!TextUtils.isEmpty(fontFromAttribute)) {
                    return fontFromAttribute;
                }
                typedArray.recycle();
            } catch (Exception e) {
            } finally {
                typedArray.recycle();
            }
        }
        return null;
    }

    static String pullFontPathFromTheme(Context context, int styleAttrId, int[] attributeId) {
        String str = null;
        if (!(styleAttrId == -1 || attributeId == null)) {
            Theme theme = context.getTheme();
            TypedValue value = new TypedValue();
            theme.resolveAttribute(styleAttrId, value, true);
            TypedArray typedArray = theme.obtainStyledAttributes(value.resourceId, attributeId);
            try {
                str = typedArray.getString(0);
            } catch (Exception e) {
            } finally {
                typedArray.recycle();
            }
        }
        return str;
    }

    static boolean canCheckForV7Toolbar() {
        if (sToolbarCheck == null) {
            try {
                Class.forName("android.support.v7.widget.Toolbar");
                sToolbarCheck = Boolean.TRUE;
            } catch (ClassNotFoundException e) {
                sToolbarCheck = Boolean.FALSE;
            }
        }
        return sToolbarCheck.booleanValue();
    }

    static boolean canAddV7AppCompatViews() {
        if (sAppCompatViewCheck == null) {
            try {
                Class.forName("android.support.v7.widget.AppCompatTextView");
                sAppCompatViewCheck = Boolean.TRUE;
            } catch (ClassNotFoundException e) {
                sAppCompatViewCheck = Boolean.FALSE;
            }
        }
        return sAppCompatViewCheck.booleanValue();
    }

    private CalligraphyUtils() {
    }
}
