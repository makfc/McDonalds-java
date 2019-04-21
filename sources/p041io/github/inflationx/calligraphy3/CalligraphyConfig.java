package p041io.github.inflationx.calligraphy3;

import android.support.p001v7.widget.AppCompatAutoCompleteTextView;
import android.support.p001v7.widget.AppCompatButton;
import android.support.p001v7.widget.AppCompatCheckBox;
import android.support.p001v7.widget.AppCompatCheckedTextView;
import android.support.p001v7.widget.AppCompatEditText;
import android.support.p001v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.p001v7.widget.AppCompatRadioButton;
import android.support.p001v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* renamed from: io.github.inflationx.calligraphy3.CalligraphyConfig */
public class CalligraphyConfig {
    private static final Map<Class<? extends TextView>, Integer> DEFAULT_STYLES = new HashMap();
    private final Set<Class<?>> hasTypefaceViews;
    private final int mAttrId;
    private final Map<Class<? extends TextView>, Integer> mClassStyleAttributeMap;
    private final boolean mCustomViewTypefaceSupport;
    private final FontMapper mFontMapper;
    private final String mFontPath;
    private final boolean mIsFontSet;

    /* renamed from: io.github.inflationx.calligraphy3.CalligraphyConfig$Builder */
    public static class Builder {
        public static final int INVALID_ATTR_ID = -1;
        private int attrId = C4619R.attr.fontPath;
        private boolean customViewTypefaceSupport = false;
        private String fontAssetPath = null;
        private FontMapper fontMapper;
        private boolean isFontSet = false;
        private Set<Class<?>> mHasTypefaceClasses = new HashSet();
        private Map<Class<? extends TextView>, Integer> mStyleClassMap = new HashMap();

        public Builder setFontAttrId(int fontAssetAttrId) {
            this.attrId = fontAssetAttrId;
            return this;
        }

        public Builder setDefaultFontPath(String defaultFontAssetPath) {
            this.isFontSet = !TextUtils.isEmpty(defaultFontAssetPath);
            this.fontAssetPath = defaultFontAssetPath;
            return this;
        }

        public Builder addCustomStyle(Class<? extends TextView> styleClass, int styleResourceAttribute) {
            if (!(styleClass == null || styleResourceAttribute == 0)) {
                this.mStyleClassMap.put(styleClass, Integer.valueOf(styleResourceAttribute));
            }
            return this;
        }

        public Builder addCustomViewWithSetTypeface(Class<?> clazz) {
            this.customViewTypefaceSupport = true;
            this.mHasTypefaceClasses.add(clazz);
            return this;
        }

        public Builder setFontMapper(FontMapper fontMapper) {
            this.fontMapper = fontMapper;
            return this;
        }

        public CalligraphyConfig build() {
            this.isFontSet = !TextUtils.isEmpty(this.fontAssetPath);
            return new CalligraphyConfig(this);
        }
    }

    static {
        DEFAULT_STYLES.put(TextView.class, Integer.valueOf(16842884));
        DEFAULT_STYLES.put(Button.class, Integer.valueOf(16842824));
        DEFAULT_STYLES.put(EditText.class, Integer.valueOf(16842862));
        DEFAULT_STYLES.put(AutoCompleteTextView.class, Integer.valueOf(16842859));
        DEFAULT_STYLES.put(MultiAutoCompleteTextView.class, Integer.valueOf(16842859));
        DEFAULT_STYLES.put(CheckBox.class, Integer.valueOf(16842860));
        DEFAULT_STYLES.put(RadioButton.class, Integer.valueOf(16842878));
        DEFAULT_STYLES.put(ToggleButton.class, Integer.valueOf(16842827));
        if (CalligraphyUtils.canAddV7AppCompatViews()) {
            CalligraphyConfig.addAppCompatViews();
        }
    }

    private static void addAppCompatViews() {
        DEFAULT_STYLES.put(AppCompatTextView.class, Integer.valueOf(16842884));
        DEFAULT_STYLES.put(AppCompatButton.class, Integer.valueOf(16842824));
        DEFAULT_STYLES.put(AppCompatEditText.class, Integer.valueOf(16842862));
        DEFAULT_STYLES.put(AppCompatAutoCompleteTextView.class, Integer.valueOf(16842859));
        DEFAULT_STYLES.put(AppCompatMultiAutoCompleteTextView.class, Integer.valueOf(16842859));
        DEFAULT_STYLES.put(AppCompatCheckBox.class, Integer.valueOf(16842860));
        DEFAULT_STYLES.put(AppCompatRadioButton.class, Integer.valueOf(16842878));
        DEFAULT_STYLES.put(AppCompatCheckedTextView.class, Integer.valueOf(16843720));
    }

    private CalligraphyConfig(Builder builder) {
        this.mIsFontSet = builder.isFontSet;
        this.mFontPath = builder.fontAssetPath;
        this.mAttrId = builder.attrId;
        this.mCustomViewTypefaceSupport = builder.customViewTypefaceSupport;
        Map<Class<? extends TextView>, Integer> tempMap = new HashMap(DEFAULT_STYLES);
        tempMap.putAll(builder.mStyleClassMap);
        this.mClassStyleAttributeMap = Collections.unmodifiableMap(tempMap);
        this.hasTypefaceViews = Collections.unmodifiableSet(builder.mHasTypefaceClasses);
        this.mFontMapper = builder.fontMapper;
    }

    public String getFontPath() {
        return this.mFontPath;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isFontSet() {
        return this.mIsFontSet;
    }

    public boolean isCustomViewTypefaceSupport() {
        return this.mCustomViewTypefaceSupport;
    }

    public boolean isCustomViewHasTypeface(View view) {
        return this.hasTypefaceViews.contains(view.getClass());
    }

    /* Access modifiers changed, original: 0000 */
    public Map<Class<? extends TextView>, Integer> getClassStyles() {
        return this.mClassStyleAttributeMap;
    }

    public int getAttrId() {
        return this.mAttrId;
    }

    public FontMapper getFontMapper() {
        return this.mFontMapper;
    }
}
