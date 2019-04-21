package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewDebug.FlagToString;
import android.view.ViewDebug.IntToString;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.StringUtil;
import com.facebook.stetho.common.android.ResourcesUtil;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.AttributeAccumulator;
import com.facebook.stetho.inspector.elements.Descriptor;
import com.facebook.stetho.inspector.elements.StyleAccumulator;
import com.facebook.stetho.inspector.helper.IntegerFormatter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

final class ViewDescriptor extends AbstractChainedDescriptor<View> implements HighlightableDescriptor {
    private static final String ID_NAME = "id";
    private static final String NONE_MAPPING = "<no mapping>";
    private static final String NONE_VALUE = "(none)";
    private final MethodInvoker mMethodInvoker;
    @GuardedBy
    @Nullable
    private volatile List<ViewCSSProperty> mViewProperties;
    @Nullable
    private Pattern mWordBoundaryPattern;

    private abstract class ViewCSSProperty {
        private final ExportedProperty mAnnotation;
        private final String mCSSName;

        public abstract Object getValue(View view) throws InvocationTargetException, IllegalAccessException;

        public ViewCSSProperty(String cssName, ExportedProperty annotation) {
            this.mCSSName = cssName;
            this.mAnnotation = annotation;
        }

        public final String getCSSName() {
            return this.mCSSName;
        }

        @Nullable
        public final ExportedProperty getAnnotation() {
            return this.mAnnotation;
        }
    }

    private final class FieldBackedCSSProperty extends ViewCSSProperty {
        private final Field mField;

        public FieldBackedCSSProperty(Field field, String cssName, ExportedProperty annotation) {
            super(cssName, annotation);
            this.mField = field;
            this.mField.setAccessible(true);
        }

        public Object getValue(View view) throws InvocationTargetException, IllegalAccessException {
            return this.mField.get(view);
        }
    }

    private final class MethodBackedCSSProperty extends ViewCSSProperty {
        private final Method mMethod;

        public MethodBackedCSSProperty(Method method, String cssName, ExportedProperty annotation) {
            super(cssName, annotation);
            this.mMethod = method;
            this.mMethod.setAccessible(true);
        }

        public Object getValue(View view) throws InvocationTargetException, IllegalAccessException {
            return this.mMethod.invoke(view, new Object[0]);
        }
    }

    private Pattern getWordBoundaryPattern() {
        if (this.mWordBoundaryPattern == null) {
            this.mWordBoundaryPattern = Pattern.compile("(?<=\\p{Lower})(?=\\p{Upper})");
        }
        return this.mWordBoundaryPattern;
    }

    private List<ViewCSSProperty> getViewProperties() {
        int i = 0;
        if (this.mViewProperties == null) {
            synchronized (this) {
                if (this.mViewProperties == null) {
                    ExportedProperty annotation;
                    List<ViewCSSProperty> props = new ArrayList();
                    for (Method method : View.class.getDeclaredMethods()) {
                        annotation = (ExportedProperty) method.getAnnotation(ExportedProperty.class);
                        if (annotation != null) {
                            props.add(new MethodBackedCSSProperty(method, convertViewPropertyNameToCSSName(method.getName()), annotation));
                        }
                    }
                    Field[] declaredFields = View.class.getDeclaredFields();
                    int length = declaredFields.length;
                    while (i < length) {
                        Field field = declaredFields[i];
                        annotation = (ExportedProperty) field.getAnnotation(ExportedProperty.class);
                        if (annotation != null) {
                            props.add(new FieldBackedCSSProperty(field, convertViewPropertyNameToCSSName(field.getName()), annotation));
                        }
                        i++;
                    }
                    this.mViewProperties = Collections.unmodifiableList(props);
                }
            }
        }
        return this.mViewProperties;
    }

    public ViewDescriptor() {
        this(new MethodInvoker());
    }

    public ViewDescriptor(MethodInvoker methodInvoker) {
        this.mMethodInvoker = methodInvoker;
    }

    /* Access modifiers changed, original: protected */
    public String onGetNodeName(View element) {
        String className = element.getClass().getName();
        return StringUtil.removePrefix(className, "android.view.", StringUtil.removePrefix(className, "android.widget."));
    }

    /* Access modifiers changed, original: protected */
    public void onGetAttributes(View element, AttributeAccumulator attributes) {
        String id = getIdAttribute(element);
        if (id != null) {
            attributes.store("id", id);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onSetAttributesAsText(View element, String text) {
        for (Entry<String, String> entry : Descriptor.parseSetAttributesAsTextArg(text).entrySet()) {
            this.mMethodInvoker.invoke(element, "set" + capitalize((String) entry.getKey()), (String) entry.getValue());
        }
    }

    @Nullable
    private static String getIdAttribute(View element) {
        int id = element.getId();
        if (id == -1) {
            return null;
        }
        return ResourcesUtil.getIdStringQuietly(element, element.getResources(), id);
    }

    public View getViewForHighlighting(Object element) {
        return (View) element;
    }

    /* Access modifiers changed, original: protected */
    public void onGetStyles(View element, StyleAccumulator styles) {
        List<ViewCSSProperty> properties = getViewProperties();
        int size = properties.size();
        for (int i = 0; i < size; i++) {
            ViewCSSProperty property = (ViewCSSProperty) properties.get(i);
            try {
                getStyleFromValue(element, property.getCSSName(), property.getValue(element), property.getAnnotation(), styles);
            } catch (IllegalAccessException | InvocationTargetException e) {
                LogUtil.m7449e(e, "failed to get style property " + property.getCSSName() + " of element= " + element.toString());
            }
        }
    }

    private static boolean canIntBeMappedToString(@Nullable ExportedProperty annotation) {
        return (annotation == null || annotation.mapping() == null || annotation.mapping().length <= 0) ? false : true;
    }

    private static String mapIntToStringUsingAnnotation(int value, @Nullable ExportedProperty annotation) {
        if (canIntBeMappedToString(annotation)) {
            for (IntToString map : annotation.mapping()) {
                if (map.from() == value) {
                    return map.to();
                }
            }
            return NONE_MAPPING;
        }
        throw new IllegalStateException("Cannot map using this annotation");
    }

    private static boolean canFlagsBeMappedToString(@Nullable ExportedProperty annotation) {
        return (annotation == null || annotation.flagMapping() == null || annotation.flagMapping().length <= 0) ? false : true;
    }

    private static String mapFlagsToStringUsingAnnotation(int value, @Nullable ExportedProperty annotation) {
        if (canFlagsBeMappedToString(annotation)) {
            StringBuilder stringBuilder = null;
            boolean atLeastOneFlag = false;
            for (FlagToString flagToString : annotation.flagMapping()) {
                boolean z;
                boolean outputIf = flagToString.outputIf();
                if ((flagToString.mask() & value) == flagToString.equals()) {
                    z = true;
                } else {
                    z = false;
                }
                if (outputIf == z) {
                    if (stringBuilder == null) {
                        stringBuilder = new StringBuilder();
                    }
                    if (atLeastOneFlag) {
                        stringBuilder.append(" | ");
                    }
                    stringBuilder.append(flagToString.name());
                    atLeastOneFlag = true;
                }
            }
            if (atLeastOneFlag) {
                return stringBuilder.toString();
            }
            return NONE_MAPPING;
        }
        throw new IllegalStateException("Cannot map using this annotation");
    }

    private static boolean isDefaultValue(Float value) {
        return value.floatValue() == 0.0f;
    }

    private static boolean isDefaultValue(Integer value, @Nullable ExportedProperty annotation) {
        if (canFlagsBeMappedToString(annotation) || canIntBeMappedToString(annotation) || value.intValue() != 0) {
            return false;
        }
        return true;
    }

    private String convertViewPropertyNameToCSSName(String getterName) {
        String[] words = getWordBoundaryPattern().split(getterName);
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < words.length) {
            if (!(words[i].equals("get") || words[i].equals("m"))) {
                result.append(words[i].toLowerCase());
                if (i < words.length - 1) {
                    result.append('-');
                }
            }
            i++;
        }
        return result.toString();
    }

    private void getStyleFromValue(View element, String name, Object value, @Nullable ExportedProperty annotation, StyleAccumulator styles) {
        if (name.equals("id")) {
            getIdStyle(element, styles);
        } else if (value instanceof Integer) {
            getStyleFromInteger(name, (Integer) value, annotation, styles);
        } else if (value instanceof Float) {
            getStyleFromFloat(name, (Float) value, annotation, styles);
        }
    }

    private void getIdStyle(View element, StyleAccumulator styles) {
        String id = getIdAttribute(element);
        if (id == null) {
            styles.store("id", NONE_VALUE, false);
        } else {
            styles.store("id", id, false);
        }
    }

    private void getStyleFromInteger(String name, Integer value, @Nullable ExportedProperty annotation, StyleAccumulator styles) {
        String intValueStr = IntegerFormatter.getInstance().format(value, annotation);
        if (canIntBeMappedToString(annotation)) {
            styles.store(name, intValueStr + " (" + mapIntToStringUsingAnnotation(value.intValue(), annotation) + ")", false);
        } else if (canFlagsBeMappedToString(annotation)) {
            styles.store(name, intValueStr + " (" + mapFlagsToStringUsingAnnotation(value.intValue(), annotation) + ")", false);
        } else {
            styles.store(name, intValueStr, isDefaultValue(value, annotation));
        }
    }

    private void getStyleFromFloat(String name, Float value, @Nullable ExportedProperty annotation, StyleAccumulator styles) {
        styles.store(name, String.valueOf(value), isDefaultValue(value));
    }

    private static String capitalize(String str) {
        if (str == null || str.length() == 0 || Character.isTitleCase(str.charAt(0))) {
            return str;
        }
        StringBuilder buffer = new StringBuilder(str);
        buffer.setCharAt(0, Character.toTitleCase(buffer.charAt(0)));
        return buffer.toString();
    }
}
