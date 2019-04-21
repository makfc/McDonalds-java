package com.google.api.client.json;

import com.google.api.client.json.JsonPolymorphicTypeMap.TypeDef;
import com.google.api.client.util.Beta;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sets;
import com.google.api.client.util.Types;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class JsonParser {
    private static WeakHashMap<Class<?>, Field> cachedTypemapFields = new WeakHashMap();
    private static final Lock lock = new ReentrantLock();

    public abstract void close() throws IOException;

    public abstract BigInteger getBigIntegerValue() throws IOException;

    public abstract byte getByteValue() throws IOException;

    public abstract String getCurrentName() throws IOException;

    public abstract JsonToken getCurrentToken();

    public abstract BigDecimal getDecimalValue() throws IOException;

    public abstract double getDoubleValue() throws IOException;

    public abstract JsonFactory getFactory();

    public abstract float getFloatValue() throws IOException;

    public abstract int getIntValue() throws IOException;

    public abstract long getLongValue() throws IOException;

    public abstract short getShortValue() throws IOException;

    public abstract String getText() throws IOException;

    public abstract JsonToken nextToken() throws IOException;

    public abstract JsonParser skipChildren() throws IOException;

    public final <T> T parseAndClose(Class<T> destinationClass) throws IOException {
        return parseAndClose(destinationClass, null);
    }

    @Beta
    public final <T> T parseAndClose(Class<T> destinationClass, CustomizeJsonParser customizeParser) throws IOException {
        try {
            T parse = parse((Class) destinationClass, customizeParser);
            return parse;
        } finally {
            close();
        }
    }

    public final String skipToKey(Set<String> keysToFind) throws IOException {
        JsonToken curToken = startParsingObjectOrArray();
        while (curToken == JsonToken.FIELD_NAME) {
            String key = getText();
            nextToken();
            if (keysToFind.contains(key)) {
                return key;
            }
            skipChildren();
            curToken = nextToken();
        }
        return null;
    }

    private JsonToken startParsing() throws IOException {
        JsonToken currentToken = getCurrentToken();
        if (currentToken == null) {
            currentToken = nextToken();
        }
        Preconditions.checkArgument(currentToken != null, "no JSON input found");
        return currentToken;
    }

    private JsonToken startParsingObjectOrArray() throws IOException {
        JsonToken currentToken = startParsing();
        switch (currentToken) {
            case START_OBJECT:
                currentToken = nextToken();
                boolean z = currentToken == JsonToken.FIELD_NAME || currentToken == JsonToken.END_OBJECT;
                Preconditions.checkArgument(z, currentToken);
                return currentToken;
            case START_ARRAY:
                return nextToken();
            default:
                return currentToken;
        }
    }

    @Beta
    public final <T> T parse(Class<T> destinationClass, CustomizeJsonParser customizeParser) throws IOException {
        return parse((Type) destinationClass, false, customizeParser);
    }

    public Object parse(Type dataType, boolean close) throws IOException {
        return parse(dataType, close, null);
    }

    @Beta
    public Object parse(Type dataType, boolean close, CustomizeJsonParser customizeParser) throws IOException {
        try {
            if (!Void.class.equals(dataType)) {
                startParsing();
            }
            Object parseValue = parseValue(null, dataType, new ArrayList(), null, customizeParser, true);
            return parseValue;
        } finally {
            if (close) {
                close();
            }
        }
    }

    private void parse(ArrayList<Type> context, Object destination, CustomizeJsonParser customizeParser) throws IOException {
        if (destination instanceof GenericJson) {
            ((GenericJson) destination).setFactory(getFactory());
        }
        JsonToken curToken = startParsingObjectOrArray();
        Class<?> destinationClass = destination.getClass();
        ClassInfo classInfo = ClassInfo.m7508of(destinationClass);
        boolean isGenericData = GenericData.class.isAssignableFrom(destinationClass);
        if (isGenericData || !Map.class.isAssignableFrom(destinationClass)) {
            while (curToken == JsonToken.FIELD_NAME) {
                String key = getText();
                nextToken();
                if (customizeParser == null || !customizeParser.stopAt(destination, key)) {
                    FieldInfo fieldInfo = classInfo.getFieldInfo(key);
                    if (fieldInfo != null) {
                        if (!fieldInfo.isFinal() || fieldInfo.isPrimitive()) {
                            Field field = fieldInfo.getField();
                            int contextSize = context.size();
                            context.add(field.getGenericType());
                            Object fieldValue = parseValue(field, fieldInfo.getGenericType(), context, destination, customizeParser, true);
                            context.remove(contextSize);
                            fieldInfo.setValue(destination, fieldValue);
                        } else {
                            throw new IllegalArgumentException("final array/object fields are not supported");
                        }
                    } else if (isGenericData) {
                        ((GenericData) destination).set(key, parseValue(null, null, context, destination, customizeParser, true));
                    } else {
                        if (customizeParser != null) {
                            customizeParser.handleUnrecognizedKey(destination, key);
                        }
                        skipChildren();
                    }
                    curToken = nextToken();
                } else {
                    return;
                }
            }
            return;
        }
        parseMap(null, (Map) destination, Types.getMapValueParameter(destinationClass), context, customizeParser);
    }

    private <T> void parseArray(Field fieldContext, Collection<T> destinationCollection, Type destinationItemType, ArrayList<Type> context, CustomizeJsonParser customizeParser) throws IOException {
        JsonToken curToken = startParsingObjectOrArray();
        while (curToken != JsonToken.END_ARRAY) {
            destinationCollection.add(parseValue(fieldContext, destinationItemType, context, destinationCollection, customizeParser, true));
            curToken = nextToken();
        }
    }

    private void parseMap(Field fieldContext, Map<String, Object> destinationMap, Type valueType, ArrayList<Type> context, CustomizeJsonParser customizeParser) throws IOException {
        JsonToken curToken = startParsingObjectOrArray();
        while (curToken == JsonToken.FIELD_NAME) {
            String key = getText();
            nextToken();
            if (customizeParser == null || !customizeParser.stopAt(destinationMap, key)) {
                destinationMap.put(key, parseValue(fieldContext, valueType, context, destinationMap, customizeParser, true));
                curToken = nextToken();
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:109:0x0248 A:{Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x020b A:{Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x024b A:{Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0219 A:{Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0199 A:{Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0150 A:{Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x015d A:{Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01ad A:{Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x017a A:{Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0180 A:{Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01ba A:{Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:200:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01c3 A:{Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d8 A:{Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0104 A:{SKIP, Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00df A:{Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0115 A:{Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f6 A:{Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x027d A:{Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0277 A:{Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0295 A:{Catch:{ IllegalArgumentException -> 0x006a }} */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0085  */
    /* JADX WARNING: Missing block: B:32:0x00b9, code skipped:
            if (com.google.api.client.util.Types.isAssignableToOrFrom(r36, java.util.Collection.class) != false) goto L_0x00bb;
     */
    /* JADX WARNING: Missing block: B:116:0x0263, code skipped:
            if (r36.isAssignableFrom(java.lang.Boolean.class) != false) goto L_0x0265;
     */
    /* JADX WARNING: Missing block: B:173:0x0374, code skipped:
            if (r38.getAnnotation(com.google.api.client.json.JsonString.class) != null) goto L_0x0376;
     */
    private final java.lang.Object parseValue(java.lang.reflect.Field r38, java.lang.reflect.Type r39, java.util.ArrayList<java.lang.reflect.Type> r40, java.lang.Object r41, com.google.api.client.json.CustomizeJsonParser r42, boolean r43) throws java.io.IOException {
        /*
        r37 = this;
        r0 = r40;
        r1 = r39;
        r39 = com.google.api.client.util.Data.resolveWildcardTypeOrTypeVariable(r0, r1);
        r0 = r39;
        r4 = r0 instanceof java.lang.Class;
        if (r4 == 0) goto L_0x002d;
    L_0x000e:
        r4 = r39;
        r4 = (java.lang.Class) r4;
        r36 = r4;
    L_0x0014:
        r0 = r39;
        r4 = r0 instanceof java.lang.reflect.ParameterizedType;
        if (r4 == 0) goto L_0x0022;
    L_0x001a:
        r4 = r39;
        r4 = (java.lang.reflect.ParameterizedType) r4;
        r36 = com.google.api.client.util.Types.getRawClass(r4);
    L_0x0022:
        r4 = java.lang.Void.class;
        r0 = r36;
        if (r0 != r4) goto L_0x0030;
    L_0x0028:
        r37.skipChildren();
        r4 = 0;
    L_0x002c:
        return r4;
    L_0x002d:
        r36 = 0;
        goto L_0x0014;
    L_0x0030:
        r30 = r37.getCurrentToken();
        r4 = com.google.api.client.json.JsonParser.C27591.$SwitchMap$com$google$api$client$json$JsonToken;	 Catch:{ IllegalArgumentException -> 0x006a }
        r5 = r37.getCurrentToken();	 Catch:{ IllegalArgumentException -> 0x006a }
        r5 = r5.ordinal();	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = r4[r5];	 Catch:{ IllegalArgumentException -> 0x006a }
        switch(r4) {
            case 1: goto L_0x0118;
            case 2: goto L_0x00a7;
            case 3: goto L_0x00a7;
            case 4: goto L_0x0118;
            case 5: goto L_0x0118;
            case 6: goto L_0x0251;
            case 7: goto L_0x0251;
            case 8: goto L_0x0281;
            case 9: goto L_0x0281;
            case 10: goto L_0x035e;
            case 11: goto L_0x038a;
            default: goto L_0x0043;
        };	 Catch:{ IllegalArgumentException -> 0x006a }
    L_0x0043:
        r4 = new java.lang.IllegalArgumentException;	 Catch:{ IllegalArgumentException -> 0x006a }
        r5 = java.lang.String.valueOf(r30);	 Catch:{ IllegalArgumentException -> 0x006a }
        r5 = java.lang.String.valueOf(r5);	 Catch:{ IllegalArgumentException -> 0x006a }
        r8 = new java.lang.StringBuilder;	 Catch:{ IllegalArgumentException -> 0x006a }
        r9 = r5.length();	 Catch:{ IllegalArgumentException -> 0x006a }
        r9 = r9 + 27;
        r8.<init>(r9);	 Catch:{ IllegalArgumentException -> 0x006a }
        r9 = "unexpected JSON node type: ";
        r8 = r8.append(r9);	 Catch:{ IllegalArgumentException -> 0x006a }
        r5 = r8.append(r5);	 Catch:{ IllegalArgumentException -> 0x006a }
        r5 = r5.toString();	 Catch:{ IllegalArgumentException -> 0x006a }
        r4.<init>(r5);	 Catch:{ IllegalArgumentException -> 0x006a }
        throw r4;	 Catch:{ IllegalArgumentException -> 0x006a }
    L_0x006a:
        r23 = move-exception;
        r21 = new java.lang.StringBuilder;
        r21.<init>();
        r22 = r37.getCurrentName();
        if (r22 == 0) goto L_0x0083;
    L_0x0076:
        r4 = "key ";
        r0 = r21;
        r4 = r0.append(r4);
        r0 = r22;
        r4.append(r0);
    L_0x0083:
        if (r38 == 0) goto L_0x009b;
    L_0x0085:
        if (r22 == 0) goto L_0x008e;
    L_0x0087:
        r4 = ", ";
        r0 = r21;
        r0.append(r4);
    L_0x008e:
        r4 = "field ";
        r0 = r21;
        r4 = r0.append(r4);
        r0 = r38;
        r4.append(r0);
    L_0x009b:
        r4 = new java.lang.IllegalArgumentException;
        r5 = r21.toString();
        r0 = r23;
        r4.<init>(r5, r0);
        throw r4;
    L_0x00a7:
        r26 = com.google.api.client.util.Types.isArray(r39);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r39 == 0) goto L_0x00bb;
    L_0x00ad:
        if (r26 != 0) goto L_0x00bb;
    L_0x00af:
        if (r36 == 0) goto L_0x0102;
    L_0x00b1:
        r4 = java.util.Collection.class;
        r0 = r36;
        r4 = com.google.api.client.util.Types.isAssignableToOrFrom(r0, r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r4 == 0) goto L_0x0102;
    L_0x00bb:
        r4 = 1;
    L_0x00bc:
        r5 = "expected collection or array type but got %s";
        r8 = 1;
        r8 = new java.lang.Object[r8];	 Catch:{ IllegalArgumentException -> 0x006a }
        r9 = 0;
        r8[r9] = r39;	 Catch:{ IllegalArgumentException -> 0x006a }
        com.google.api.client.util.Preconditions.checkArgument(r4, r5, r8);	 Catch:{ IllegalArgumentException -> 0x006a }
        r6 = 0;
        if (r42 == 0) goto L_0x00d6;
    L_0x00ca:
        if (r38 == 0) goto L_0x00d6;
    L_0x00cc:
        r0 = r42;
        r1 = r41;
        r2 = r38;
        r6 = r0.newInstanceForArray(r1, r2);	 Catch:{ IllegalArgumentException -> 0x006a }
    L_0x00d6:
        if (r6 != 0) goto L_0x00dc;
    L_0x00d8:
        r6 = com.google.api.client.util.Data.newCollectionInstance(r39);	 Catch:{ IllegalArgumentException -> 0x006a }
    L_0x00dc:
        r7 = 0;
        if (r26 == 0) goto L_0x0104;
    L_0x00df:
        r7 = com.google.api.client.util.Types.getArrayComponentType(r39);	 Catch:{ IllegalArgumentException -> 0x006a }
    L_0x00e3:
        r0 = r40;
        r7 = com.google.api.client.util.Data.resolveWildcardTypeOrTypeVariable(r0, r7);	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = r37;
        r5 = r38;
        r8 = r40;
        r9 = r42;
        r4.parseArray(r5, r6, r7, r8, r9);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r26 == 0) goto L_0x0115;
    L_0x00f6:
        r0 = r40;
        r4 = com.google.api.client.util.Types.getRawArrayComponentType(r0, r7);	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = com.google.api.client.util.Types.toArray(r6, r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        goto L_0x002c;
    L_0x0102:
        r4 = 0;
        goto L_0x00bc;
    L_0x0104:
        if (r36 == 0) goto L_0x00e3;
    L_0x0106:
        r4 = java.lang.Iterable.class;
        r0 = r36;
        r4 = r4.isAssignableFrom(r0);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r4 == 0) goto L_0x00e3;
    L_0x0110:
        r7 = com.google.api.client.util.Types.getIterableParameter(r39);	 Catch:{ IllegalArgumentException -> 0x006a }
        goto L_0x00e3;
    L_0x0115:
        r4 = r6;
        goto L_0x002c;
    L_0x0118:
        r4 = com.google.api.client.util.Types.isArray(r39);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r4 != 0) goto L_0x0191;
    L_0x011e:
        r4 = 1;
    L_0x011f:
        r5 = "expected object or map type but got %s";
        r8 = 1;
        r8 = new java.lang.Object[r8];	 Catch:{ IllegalArgumentException -> 0x006a }
        r9 = 0;
        r8[r9] = r39;	 Catch:{ IllegalArgumentException -> 0x006a }
        com.google.api.client.util.Preconditions.checkArgument(r4, r5, r8);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r43 == 0) goto L_0x0193;
    L_0x012c:
        r35 = getCachedTypemapFieldFor(r36);	 Catch:{ IllegalArgumentException -> 0x006a }
    L_0x0130:
        r29 = 0;
        if (r36 == 0) goto L_0x0140;
    L_0x0134:
        if (r42 == 0) goto L_0x0140;
    L_0x0136:
        r0 = r42;
        r1 = r41;
        r2 = r36;
        r29 = r0.newInstanceForObject(r1, r2);	 Catch:{ IllegalArgumentException -> 0x006a }
    L_0x0140:
        if (r36 == 0) goto L_0x0196;
    L_0x0142:
        r4 = java.util.Map.class;
        r0 = r36;
        r4 = com.google.api.client.util.Types.isAssignableToOrFrom(r0, r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r4 == 0) goto L_0x0196;
    L_0x014c:
        r27 = 1;
    L_0x014e:
        if (r35 == 0) goto L_0x0199;
    L_0x0150:
        r29 = new com.google.api.client.json.GenericJson;	 Catch:{ IllegalArgumentException -> 0x006a }
        r29.<init>();	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = r29;
    L_0x0157:
        r20 = r40.size();	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r39 == 0) goto L_0x0164;
    L_0x015d:
        r0 = r40;
        r1 = r39;
        r0.add(r1);	 Catch:{ IllegalArgumentException -> 0x006a }
    L_0x0164:
        if (r27 == 0) goto L_0x01af;
    L_0x0166:
        r5 = com.google.api.client.util.GenericData.class;
        r0 = r36;
        r5 = r5.isAssignableFrom(r0);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r5 != 0) goto L_0x01af;
    L_0x0170:
        r5 = java.util.Map.class;
        r0 = r36;
        r5 = r5.isAssignableFrom(r0);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r5 == 0) goto L_0x01ad;
    L_0x017a:
        r11 = com.google.api.client.util.Types.getMapValueParameter(r39);	 Catch:{ IllegalArgumentException -> 0x006a }
    L_0x017e:
        if (r11 == 0) goto L_0x01af;
    L_0x0180:
        r0 = r4;
        r0 = (java.util.Map) r0;	 Catch:{ IllegalArgumentException -> 0x006a }
        r10 = r0;
        r8 = r37;
        r9 = r38;
        r12 = r40;
        r13 = r42;
        r8.parseMap(r9, r10, r11, r12, r13);	 Catch:{ IllegalArgumentException -> 0x006a }
        goto L_0x002c;
    L_0x0191:
        r4 = 0;
        goto L_0x011f;
    L_0x0193:
        r35 = 0;
        goto L_0x0130;
    L_0x0196:
        r27 = 0;
        goto L_0x014e;
    L_0x0199:
        if (r29 != 0) goto L_0x03e2;
    L_0x019b:
        if (r27 != 0) goto L_0x019f;
    L_0x019d:
        if (r36 != 0) goto L_0x01a6;
    L_0x019f:
        r29 = com.google.api.client.util.Data.newMapInstance(r36);	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = r29;
        goto L_0x0157;
    L_0x01a6:
        r29 = com.google.api.client.util.Types.newInstance(r36);	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = r29;
        goto L_0x0157;
    L_0x01ad:
        r11 = 0;
        goto L_0x017e;
    L_0x01af:
        r0 = r37;
        r1 = r40;
        r2 = r42;
        r0.parse(r1, r4, r2);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r39 == 0) goto L_0x01c1;
    L_0x01ba:
        r0 = r40;
        r1 = r20;
        r0.remove(r1);	 Catch:{ IllegalArgumentException -> 0x006a }
    L_0x01c1:
        if (r35 == 0) goto L_0x002c;
    L_0x01c3:
        r0 = r4;
        r0 = (com.google.api.client.json.GenericJson) r0;	 Catch:{ IllegalArgumentException -> 0x006a }
        r5 = r0;
        r8 = r35.getName();	 Catch:{ IllegalArgumentException -> 0x006a }
        r34 = r5.get(r8);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r34 == 0) goto L_0x0243;
    L_0x01d1:
        r5 = 1;
    L_0x01d2:
        r8 = "No value specified for @JsonPolymorphicTypeMap field";
        com.google.api.client.util.Preconditions.checkArgument(r5, r8);	 Catch:{ IllegalArgumentException -> 0x006a }
        r33 = r34.toString();	 Catch:{ IllegalArgumentException -> 0x006a }
        r5 = com.google.api.client.json.JsonPolymorphicTypeMap.class;
        r0 = r35;
        r32 = r0.getAnnotation(r5);	 Catch:{ IllegalArgumentException -> 0x006a }
        r32 = (com.google.api.client.json.JsonPolymorphicTypeMap) r32;	 Catch:{ IllegalArgumentException -> 0x006a }
        r14 = 0;
        r19 = r32.typeDefinitions();	 Catch:{ IllegalArgumentException -> 0x006a }
        r0 = r19;
        r0 = r0.length;	 Catch:{ IllegalArgumentException -> 0x006a }
        r28 = r0;
        r25 = 0;
    L_0x01f1:
        r0 = r25;
        r1 = r28;
        if (r0 >= r1) goto L_0x0209;
    L_0x01f7:
        r31 = r19[r25];	 Catch:{ IllegalArgumentException -> 0x006a }
        r5 = r31.key();	 Catch:{ IllegalArgumentException -> 0x006a }
        r0 = r33;
        r5 = r5.equals(r0);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r5 == 0) goto L_0x0245;
    L_0x0205:
        r14 = r31.ref();	 Catch:{ IllegalArgumentException -> 0x006a }
    L_0x0209:
        if (r14 == 0) goto L_0x0248;
    L_0x020b:
        r5 = 1;
        r8 = r5;
    L_0x020d:
        r9 = "No TypeDef annotation found with key: ";
        r5 = java.lang.String.valueOf(r33);	 Catch:{ IllegalArgumentException -> 0x006a }
        r13 = r5.length();	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r13 == 0) goto L_0x024b;
    L_0x0219:
        r5 = r9.concat(r5);	 Catch:{ IllegalArgumentException -> 0x006a }
    L_0x021d:
        com.google.api.client.util.Preconditions.checkArgument(r8, r5);	 Catch:{ IllegalArgumentException -> 0x006a }
        r24 = r37.getFactory();	 Catch:{ IllegalArgumentException -> 0x006a }
        r0 = r24;
        r4 = r0.toString(r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        r0 = r24;
        r12 = r0.createJsonParser(r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        r12.startParsing();	 Catch:{ IllegalArgumentException -> 0x006a }
        r16 = 0;
        r17 = 0;
        r18 = 0;
        r13 = r38;
        r15 = r40;
        r4 = r12.parseValue(r13, r14, r15, r16, r17, r18);	 Catch:{ IllegalArgumentException -> 0x006a }
        goto L_0x002c;
    L_0x0243:
        r5 = 0;
        goto L_0x01d2;
    L_0x0245:
        r25 = r25 + 1;
        goto L_0x01f1;
    L_0x0248:
        r5 = 0;
        r8 = r5;
        goto L_0x020d;
    L_0x024b:
        r5 = new java.lang.String;	 Catch:{ IllegalArgumentException -> 0x006a }
        r5.<init>(r9);	 Catch:{ IllegalArgumentException -> 0x006a }
        goto L_0x021d;
    L_0x0251:
        if (r39 == 0) goto L_0x0265;
    L_0x0253:
        r4 = java.lang.Boolean.TYPE;	 Catch:{ IllegalArgumentException -> 0x006a }
        r0 = r36;
        if (r0 == r4) goto L_0x0265;
    L_0x0259:
        if (r36 == 0) goto L_0x027b;
    L_0x025b:
        r4 = java.lang.Boolean.class;
        r0 = r36;
        r4 = r0.isAssignableFrom(r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r4 == 0) goto L_0x027b;
    L_0x0265:
        r4 = 1;
    L_0x0266:
        r5 = "expected type Boolean or boolean but got %s";
        r8 = 1;
        r8 = new java.lang.Object[r8];	 Catch:{ IllegalArgumentException -> 0x006a }
        r9 = 0;
        r8[r9] = r39;	 Catch:{ IllegalArgumentException -> 0x006a }
        com.google.api.client.util.Preconditions.checkArgument(r4, r5, r8);	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = com.google.api.client.json.JsonToken.VALUE_TRUE;	 Catch:{ IllegalArgumentException -> 0x006a }
        r0 = r30;
        if (r0 != r4) goto L_0x027d;
    L_0x0277:
        r4 = java.lang.Boolean.TRUE;	 Catch:{ IllegalArgumentException -> 0x006a }
        goto L_0x002c;
    L_0x027b:
        r4 = 0;
        goto L_0x0266;
    L_0x027d:
        r4 = java.lang.Boolean.FALSE;	 Catch:{ IllegalArgumentException -> 0x006a }
        goto L_0x002c;
    L_0x0281:
        if (r38 == 0) goto L_0x028d;
    L_0x0283:
        r4 = com.google.api.client.json.JsonString.class;
        r0 = r38;
        r4 = r0.getAnnotation(r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r4 != 0) goto L_0x02a5;
    L_0x028d:
        r4 = 1;
    L_0x028e:
        r5 = "number type formatted as a JSON number cannot use @JsonString annotation";
        com.google.api.client.util.Preconditions.checkArgument(r4, r5);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r36 == 0) goto L_0x029f;
    L_0x0295:
        r4 = java.math.BigDecimal.class;
        r0 = r36;
        r4 = r0.isAssignableFrom(r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r4 == 0) goto L_0x02a7;
    L_0x029f:
        r4 = r37.getDecimalValue();	 Catch:{ IllegalArgumentException -> 0x006a }
        goto L_0x002c;
    L_0x02a5:
        r4 = 0;
        goto L_0x028e;
    L_0x02a7:
        r4 = java.math.BigInteger.class;
        r0 = r36;
        if (r0 != r4) goto L_0x02b3;
    L_0x02ad:
        r4 = r37.getBigIntegerValue();	 Catch:{ IllegalArgumentException -> 0x006a }
        goto L_0x002c;
    L_0x02b3:
        r4 = java.lang.Double.class;
        r0 = r36;
        if (r0 == r4) goto L_0x02bf;
    L_0x02b9:
        r4 = java.lang.Double.TYPE;	 Catch:{ IllegalArgumentException -> 0x006a }
        r0 = r36;
        if (r0 != r4) goto L_0x02c9;
    L_0x02bf:
        r4 = r37.getDoubleValue();	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = java.lang.Double.valueOf(r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        goto L_0x002c;
    L_0x02c9:
        r4 = java.lang.Long.class;
        r0 = r36;
        if (r0 == r4) goto L_0x02d5;
    L_0x02cf:
        r4 = java.lang.Long.TYPE;	 Catch:{ IllegalArgumentException -> 0x006a }
        r0 = r36;
        if (r0 != r4) goto L_0x02df;
    L_0x02d5:
        r4 = r37.getLongValue();	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        goto L_0x002c;
    L_0x02df:
        r4 = java.lang.Float.class;
        r0 = r36;
        if (r0 == r4) goto L_0x02eb;
    L_0x02e5:
        r4 = java.lang.Float.TYPE;	 Catch:{ IllegalArgumentException -> 0x006a }
        r0 = r36;
        if (r0 != r4) goto L_0x02f5;
    L_0x02eb:
        r4 = r37.getFloatValue();	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = java.lang.Float.valueOf(r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        goto L_0x002c;
    L_0x02f5:
        r4 = java.lang.Integer.class;
        r0 = r36;
        if (r0 == r4) goto L_0x0301;
    L_0x02fb:
        r4 = java.lang.Integer.TYPE;	 Catch:{ IllegalArgumentException -> 0x006a }
        r0 = r36;
        if (r0 != r4) goto L_0x030b;
    L_0x0301:
        r4 = r37.getIntValue();	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        goto L_0x002c;
    L_0x030b:
        r4 = java.lang.Short.class;
        r0 = r36;
        if (r0 == r4) goto L_0x0317;
    L_0x0311:
        r4 = java.lang.Short.TYPE;	 Catch:{ IllegalArgumentException -> 0x006a }
        r0 = r36;
        if (r0 != r4) goto L_0x0321;
    L_0x0317:
        r4 = r37.getShortValue();	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = java.lang.Short.valueOf(r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        goto L_0x002c;
    L_0x0321:
        r4 = java.lang.Byte.class;
        r0 = r36;
        if (r0 == r4) goto L_0x032d;
    L_0x0327:
        r4 = java.lang.Byte.TYPE;	 Catch:{ IllegalArgumentException -> 0x006a }
        r0 = r36;
        if (r0 != r4) goto L_0x0337;
    L_0x032d:
        r4 = r37.getByteValue();	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = java.lang.Byte.valueOf(r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        goto L_0x002c;
    L_0x0337:
        r4 = new java.lang.IllegalArgumentException;	 Catch:{ IllegalArgumentException -> 0x006a }
        r5 = java.lang.String.valueOf(r39);	 Catch:{ IllegalArgumentException -> 0x006a }
        r5 = java.lang.String.valueOf(r5);	 Catch:{ IllegalArgumentException -> 0x006a }
        r8 = new java.lang.StringBuilder;	 Catch:{ IllegalArgumentException -> 0x006a }
        r9 = r5.length();	 Catch:{ IllegalArgumentException -> 0x006a }
        r9 = r9 + 30;
        r8.<init>(r9);	 Catch:{ IllegalArgumentException -> 0x006a }
        r9 = "expected numeric type but got ";
        r8 = r8.append(r9);	 Catch:{ IllegalArgumentException -> 0x006a }
        r5 = r8.append(r5);	 Catch:{ IllegalArgumentException -> 0x006a }
        r5 = r5.toString();	 Catch:{ IllegalArgumentException -> 0x006a }
        r4.<init>(r5);	 Catch:{ IllegalArgumentException -> 0x006a }
        throw r4;	 Catch:{ IllegalArgumentException -> 0x006a }
    L_0x035e:
        if (r36 == 0) goto L_0x0376;
    L_0x0360:
        r4 = java.lang.Number.class;
        r0 = r36;
        r4 = r4.isAssignableFrom(r0);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r4 == 0) goto L_0x0376;
    L_0x036a:
        if (r38 == 0) goto L_0x0388;
    L_0x036c:
        r4 = com.google.api.client.json.JsonString.class;
        r0 = r38;
        r4 = r0.getAnnotation(r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r4 == 0) goto L_0x0388;
    L_0x0376:
        r4 = 1;
    L_0x0377:
        r5 = "number field formatted as a JSON string must use the @JsonString annotation";
        com.google.api.client.util.Preconditions.checkArgument(r4, r5);	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = r37.getText();	 Catch:{ IllegalArgumentException -> 0x006a }
        r0 = r39;
        r4 = com.google.api.client.util.Data.parsePrimitiveValue(r0, r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        goto L_0x002c;
    L_0x0388:
        r4 = 0;
        goto L_0x0377;
    L_0x038a:
        if (r36 == 0) goto L_0x0392;
    L_0x038c:
        r4 = r36.isPrimitive();	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r4 != 0) goto L_0x03ba;
    L_0x0392:
        r4 = 1;
    L_0x0393:
        r5 = "primitive number field but found a JSON null";
        com.google.api.client.util.Preconditions.checkArgument(r4, r5);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r36 == 0) goto L_0x03d4;
    L_0x039a:
        r4 = r36.getModifiers();	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = r4 & 1536;
        if (r4 == 0) goto L_0x03d4;
    L_0x03a2:
        r4 = java.util.Collection.class;
        r0 = r36;
        r4 = com.google.api.client.util.Types.isAssignableToOrFrom(r0, r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r4 == 0) goto L_0x03bc;
    L_0x03ac:
        r4 = com.google.api.client.util.Data.newCollectionInstance(r39);	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = r4.getClass();	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = com.google.api.client.util.Data.nullOf(r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        goto L_0x002c;
    L_0x03ba:
        r4 = 0;
        goto L_0x0393;
    L_0x03bc:
        r4 = java.util.Map.class;
        r0 = r36;
        r4 = com.google.api.client.util.Types.isAssignableToOrFrom(r0, r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        if (r4 == 0) goto L_0x03d4;
    L_0x03c6:
        r4 = com.google.api.client.util.Data.newMapInstance(r36);	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = r4.getClass();	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = com.google.api.client.util.Data.nullOf(r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        goto L_0x002c;
    L_0x03d4:
        r0 = r40;
        r1 = r39;
        r4 = com.google.api.client.util.Types.getRawArrayComponentType(r0, r1);	 Catch:{ IllegalArgumentException -> 0x006a }
        r4 = com.google.api.client.util.Data.nullOf(r4);	 Catch:{ IllegalArgumentException -> 0x006a }
        goto L_0x002c;
    L_0x03e2:
        r4 = r29;
        goto L_0x0157;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.json.JsonParser.parseValue(java.lang.reflect.Field, java.lang.reflect.Type, java.util.ArrayList, java.lang.Object, com.google.api.client.json.CustomizeJsonParser, boolean):java.lang.Object");
    }

    private static Field getCachedTypemapFieldFor(Class<?> key) {
        if (key == null) {
            return null;
        }
        lock.lock();
        try {
            if (cachedTypemapFields.containsKey(key)) {
                Field field = (Field) cachedTypemapFields.get(key);
                return field;
            }
            Field value = null;
            for (FieldInfo fieldInfo : ClassInfo.m7508of(key).getFieldInfos()) {
                Field field2 = fieldInfo.getField();
                JsonPolymorphicTypeMap typemapAnnotation = (JsonPolymorphicTypeMap) field2.getAnnotation(JsonPolymorphicTypeMap.class);
                if (typemapAnnotation != null) {
                    Preconditions.checkArgument(value == null, "Class contains more than one field with @JsonPolymorphicTypeMap annotation: %s", key);
                    Preconditions.checkArgument(Data.isPrimitive(field2.getType()), "Field which has the @JsonPolymorphicTypeMap, %s, is not a supported type: %s", key, field2.getType());
                    value = field2;
                    TypeDef[] typeDefs = typemapAnnotation.typeDefinitions();
                    HashSet<String> typeDefKeys = Sets.newHashSet();
                    Preconditions.checkArgument(typeDefs.length > 0, "@JsonPolymorphicTypeMap must have at least one @TypeDef");
                    for (TypeDef typeDef : typeDefs) {
                        Preconditions.checkArgument(typeDefKeys.add(typeDef.key()), "Class contains two @TypeDef annotations with identical key: %s", arr$[i$].key());
                    }
                }
            }
            cachedTypemapFields.put(key, value);
            lock.unlock();
            return value;
        } finally {
            lock.unlock();
        }
    }
}
