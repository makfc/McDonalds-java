package com.google.api.client.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.WeakHashMap;

public class FieldInfo {
    private static final Map<Field, FieldInfo> CACHE = new WeakHashMap();
    private final Field field;
    private final boolean isPrimitive;
    private final String name;

    /* renamed from: of */
    public static FieldInfo m7510of(Enum<?> enumValue) {
        boolean z = true;
        try {
            FieldInfo result = m7511of(enumValue.getClass().getField(enumValue.name()));
            if (result == null) {
                z = false;
            }
            Preconditions.checkArgument(z, "enum constant missing @Value or @NullValue annotation: %s", enumValue);
            return result;
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARNING: Missing block: B:36:?, code skipped:
            return r0;
     */
    /* renamed from: of */
    public static com.google.api.client.util.FieldInfo m7511of(java.lang.reflect.Field r9) {
        /*
        r6 = 0;
        if (r9 != 0) goto L_0x0005;
    L_0x0003:
        r0 = r6;
    L_0x0004:
        return r0;
    L_0x0005:
        r7 = CACHE;
        monitor-enter(r7);
        r8 = CACHE;	 Catch:{ all -> 0x004a }
        r0 = r8.get(r9);	 Catch:{ all -> 0x004a }
        r0 = (com.google.api.client.util.FieldInfo) r0;	 Catch:{ all -> 0x004a }
        r2 = r9.isEnumConstant();	 Catch:{ all -> 0x004a }
        if (r0 != 0) goto L_0x0048;
    L_0x0016:
        if (r2 != 0) goto L_0x0022;
    L_0x0018:
        r8 = r9.getModifiers();	 Catch:{ all -> 0x004a }
        r8 = java.lang.reflect.Modifier.isStatic(r8);	 Catch:{ all -> 0x004a }
        if (r8 != 0) goto L_0x0048;
    L_0x0022:
        if (r2 == 0) goto L_0x005c;
    L_0x0024:
        r8 = com.google.api.client.util.Value.class;
        r5 = r9.getAnnotation(r8);	 Catch:{ all -> 0x004a }
        r5 = (com.google.api.client.util.Value) r5;	 Catch:{ all -> 0x004a }
        if (r5 == 0) goto L_0x004d;
    L_0x002e:
        r1 = r5.value();	 Catch:{ all -> 0x004a }
    L_0x0032:
        r6 = "##default";
        r6 = r6.equals(r1);	 Catch:{ all -> 0x004a }
        if (r6 == 0) goto L_0x003e;
    L_0x003a:
        r1 = r9.getName();	 Catch:{ all -> 0x004a }
    L_0x003e:
        r0 = new com.google.api.client.util.FieldInfo;	 Catch:{ all -> 0x004a }
        r0.<init>(r9, r1);	 Catch:{ all -> 0x004a }
        r6 = CACHE;	 Catch:{ all -> 0x004a }
        r6.put(r9, r0);	 Catch:{ all -> 0x004a }
    L_0x0048:
        monitor-exit(r7);	 Catch:{ all -> 0x004a }
        goto L_0x0004;
    L_0x004a:
        r6 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x004a }
        throw r6;
    L_0x004d:
        r8 = com.google.api.client.util.NullValue.class;
        r4 = r9.getAnnotation(r8);	 Catch:{ all -> 0x004a }
        r4 = (com.google.api.client.util.NullValue) r4;	 Catch:{ all -> 0x004a }
        if (r4 == 0) goto L_0x0059;
    L_0x0057:
        r1 = 0;
        goto L_0x0032;
    L_0x0059:
        monitor-exit(r7);	 Catch:{ all -> 0x004a }
        r0 = r6;
        goto L_0x0004;
    L_0x005c:
        r8 = com.google.api.client.util.Key.class;
        r3 = r9.getAnnotation(r8);	 Catch:{ all -> 0x004a }
        r3 = (com.google.api.client.util.Key) r3;	 Catch:{ all -> 0x004a }
        if (r3 != 0) goto L_0x0069;
    L_0x0066:
        monitor-exit(r7);	 Catch:{ all -> 0x004a }
        r0 = r6;
        goto L_0x0004;
    L_0x0069:
        r1 = r3.value();	 Catch:{ all -> 0x004a }
        r6 = 1;
        r9.setAccessible(r6);	 Catch:{ all -> 0x004a }
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.util.FieldInfo.m7511of(java.lang.reflect.Field):com.google.api.client.util.FieldInfo");
    }

    FieldInfo(Field field, String name) {
        this.field = field;
        this.name = name == null ? null : name.intern();
        this.isPrimitive = Data.isPrimitive(getType());
    }

    public Field getField() {
        return this.field;
    }

    public String getName() {
        return this.name;
    }

    public Class<?> getType() {
        return this.field.getType();
    }

    public Type getGenericType() {
        return this.field.getGenericType();
    }

    public boolean isFinal() {
        return Modifier.isFinal(this.field.getModifiers());
    }

    public boolean isPrimitive() {
        return this.isPrimitive;
    }

    public Object getValue(Object obj) {
        return getFieldValue(this.field, obj);
    }

    public void setValue(Object obj, Object value) {
        setFieldValue(this.field, obj, value);
    }

    public <T extends Enum<T>> T enumValue() {
        return Enum.valueOf(this.field.getDeclaringClass(), this.field.getName());
    }

    public static Object getFieldValue(Field field, Object obj) {
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void setFieldValue(Field field, Object obj, Object value) {
        if (Modifier.isFinal(field.getModifiers())) {
            Object finalValue = getFieldValue(field, obj);
            if (value == null) {
                if (finalValue == null) {
                    return;
                }
            } else if (value.equals(finalValue)) {
                return;
            }
            String valueOf = String.valueOf(String.valueOf(finalValue));
            String valueOf2 = String.valueOf(String.valueOf(value));
            String valueOf3 = String.valueOf(String.valueOf(field.getName()));
            String valueOf4 = String.valueOf(String.valueOf(obj.getClass().getName()));
            throw new IllegalArgumentException(new StringBuilder((((valueOf.length() + 48) + valueOf2.length()) + valueOf3.length()) + valueOf4.length()).append("expected final value <").append(valueOf).append("> but was <").append(valueOf2).append("> on ").append(valueOf3).append(" field in ").append(valueOf4).toString());
        }
        try {
            field.set(obj, value);
        } catch (SecurityException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e2) {
            throw new IllegalArgumentException(e2);
        }
    }
}
