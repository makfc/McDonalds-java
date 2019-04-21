package p041io.github.inflationx.calligraphy3;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.support.p001v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import p041io.github.inflationx.viewpump.ReflectionUtils;

/* renamed from: io.github.inflationx.calligraphy3.Calligraphy */
class Calligraphy {
    private static final String ACTION_BAR_SUBTITLE = "action_bar_subtitle";
    private static final String ACTION_BAR_TITLE = "action_bar_title";
    private final int[] mAttributeId;
    private final CalligraphyConfig mCalligraphyConfig;

    /* renamed from: io.github.inflationx.calligraphy3.Calligraphy$ToolbarLayoutListener */
    private static class ToolbarLayoutListener implements OnGlobalLayoutListener {
        static String BLANK = " ";
        private final WeakReference<Calligraphy> mCalligraphyFactory;
        private final WeakReference<Context> mContextRef;
        private final WeakReference<Toolbar> mToolbarReference;
        private final CharSequence originalSubTitle;

        private ToolbarLayoutListener(Calligraphy calligraphy, Context context, Toolbar toolbar) {
            this.mCalligraphyFactory = new WeakReference(calligraphy);
            this.mContextRef = new WeakReference(context);
            this.mToolbarReference = new WeakReference(toolbar);
            this.originalSubTitle = toolbar.getSubtitle();
            toolbar.setSubtitle(BLANK);
        }

        @TargetApi(16)
        public void onGlobalLayout() {
            Toolbar toolbar = (Toolbar) this.mToolbarReference.get();
            Context context = (Context) this.mContextRef.get();
            Calligraphy factory = (Calligraphy) this.mCalligraphyFactory.get();
            if (toolbar != null) {
                if (factory == null || context == null) {
                    removeSelf(toolbar);
                    return;
                }
                int childCount = toolbar.getChildCount();
                if (childCount != 0) {
                    for (int i = 0; i < childCount; i++) {
                        factory.onViewCreated(toolbar.getChildAt(i), context, null);
                    }
                }
                removeSelf(toolbar);
                toolbar.setSubtitle(this.originalSubTitle);
            }
        }

        private void removeSelf(Toolbar toolbar) {
            if (VERSION.SDK_INT < 16) {
                toolbar.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            } else {
                toolbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public int[] getStyleForTextView(TextView view) {
        int[] styleIds = new int[]{-1, -1};
        if (Calligraphy.isActionBarTitle(view)) {
            styleIds[0] = 16843470;
            styleIds[1] = 16843512;
        } else if (Calligraphy.isActionBarSubTitle(view)) {
            styleIds[0] = 16843470;
            styleIds[1] = 16843513;
        }
        if (styleIds[0] == -1) {
            styleIds[0] = this.mCalligraphyConfig.getClassStyles().containsKey(view.getClass()) ? ((Integer) this.mCalligraphyConfig.getClassStyles().get(view.getClass())).intValue() : 16842804;
        }
        return styleIds;
    }

    @SuppressLint({"NewApi"})
    protected static boolean isActionBarTitle(TextView view) {
        if (Calligraphy.matchesResourceIdName(view, ACTION_BAR_TITLE)) {
            return true;
        }
        if (Calligraphy.parentIsToolbarV7(view)) {
            return TextUtils.equals(((Toolbar) view.getParent()).getTitle(), view.getText());
        }
        return false;
    }

    @SuppressLint({"NewApi"})
    protected static boolean isActionBarSubTitle(TextView view) {
        if (Calligraphy.matchesResourceIdName(view, ACTION_BAR_SUBTITLE)) {
            return true;
        }
        if (Calligraphy.parentIsToolbarV7(view)) {
            return TextUtils.equals(((Toolbar) view.getParent()).getSubtitle(), view.getText());
        }
        return false;
    }

    protected static boolean parentIsToolbarV7(View view) {
        return CalligraphyUtils.canCheckForV7Toolbar() && view.getParent() != null && (view.getParent() instanceof Toolbar);
    }

    protected static boolean matchesResourceIdName(View view, String matches) {
        if (view.getId() == -1) {
            return false;
        }
        return view.getResources().getResourceEntryName(view.getId()).equalsIgnoreCase(matches);
    }

    public Calligraphy(CalligraphyConfig calligraphyConfig) {
        this.mCalligraphyConfig = calligraphyConfig;
        this.mAttributeId = new int[]{calligraphyConfig.getAttrId()};
    }

    public View onViewCreated(View view, Context context, AttributeSet attrs) {
        if (!(view == null || view.getTag(C4619R.C4618id.calligraphy_tag_id) == Boolean.TRUE)) {
            onViewCreatedInternal(view, context, attrs);
            view.setTag(C4619R.C4618id.calligraphy_tag_id, Boolean.TRUE);
        }
        return view;
    }

    /* Access modifiers changed, original: 0000 */
    public void onViewCreatedInternal(View view, Context context, AttributeSet attrs) {
        if (view instanceof TextView) {
            if (!TypefaceUtils.isLoaded(((TextView) view).getTypeface())) {
                boolean deferred;
                String textViewFont = resolveFontPath(context, attrs);
                if (TextUtils.isEmpty(textViewFont)) {
                    int[] styleForTextView = getStyleForTextView((TextView) view);
                    if (styleForTextView[1] != -1) {
                        textViewFont = CalligraphyUtils.pullFontPathFromTheme(context, styleForTextView[0], styleForTextView[1], this.mAttributeId);
                    } else {
                        textViewFont = CalligraphyUtils.pullFontPathFromTheme(context, styleForTextView[0], this.mAttributeId);
                    }
                }
                textViewFont = applyFontMapper(textViewFont);
                if (Calligraphy.matchesResourceIdName(view, ACTION_BAR_TITLE) || Calligraphy.matchesResourceIdName(view, ACTION_BAR_SUBTITLE)) {
                    deferred = true;
                } else {
                    deferred = false;
                }
                CalligraphyUtils.applyFontToTextView(context, (TextView) view, this.mCalligraphyConfig, textViewFont, deferred);
            } else {
                return;
            }
        }
        if (CalligraphyUtils.canCheckForV7Toolbar() && (view instanceof Toolbar)) {
            Toolbar toolbar = (Toolbar) view;
            toolbar.getViewTreeObserver().addOnGlobalLayoutListener(new ToolbarLayoutListener(context, toolbar));
        }
        Typeface typeface;
        if (view instanceof HasTypeface) {
            typeface = getDefaultTypeface(context, applyFontMapper(resolveFontPath(context, attrs)));
            if (typeface != null) {
                ((HasTypeface) view).setTypeface(typeface);
            }
        } else if (this.mCalligraphyConfig.isCustomViewTypefaceSupport() && this.mCalligraphyConfig.isCustomViewHasTypeface(view)) {
            Method setTypeface = ReflectionUtils.getMethod(view.getClass(), "setTypeface");
            typeface = getDefaultTypeface(context, applyFontMapper(resolveFontPath(context, attrs)));
            if (setTypeface != null && typeface != null) {
                ReflectionUtils.invokeMethod(view, setTypeface, typeface);
            }
        }
    }

    private Typeface getDefaultTypeface(Context context, String fontPath) {
        CharSequence fontPath2;
        if (TextUtils.isEmpty(fontPath2)) {
            fontPath2 = this.mCalligraphyConfig.getFontPath();
        }
        if (TextUtils.isEmpty(fontPath2)) {
            return null;
        }
        return TypefaceUtils.load(context.getAssets(), fontPath2);
    }

    private String resolveFontPath(Context context, AttributeSet attrs) {
        String textViewFont = CalligraphyUtils.pullFontPathFromView(context, attrs, this.mAttributeId);
        if (TextUtils.isEmpty(textViewFont)) {
            textViewFont = CalligraphyUtils.pullFontPathFromStyle(context, attrs, this.mAttributeId);
        }
        if (TextUtils.isEmpty(textViewFont)) {
            return CalligraphyUtils.pullFontPathFromTextAppearance(context, attrs, this.mAttributeId);
        }
        return textViewFont;
    }

    private String applyFontMapper(String textViewFont) {
        FontMapper fontMapper = this.mCalligraphyConfig.getFontMapper();
        return fontMapper != null ? fontMapper.map(textViewFont) : textViewFont;
    }
}
