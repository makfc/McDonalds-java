package com.google.api.client.http;

import com.google.api.client.util.ArrayValueMap;
import com.google.api.client.util.Charsets;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Throwables;
import com.google.api.client.util.Types;
import com.google.api.client.util.escape.CharEscapers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class UrlEncodedParser implements ObjectParser {
    public static final String MEDIA_TYPE = new HttpMediaType("application/x-www-form-urlencoded").setCharsetParameter(Charsets.UTF_8).build();

    public static void parse(String content, Object data) {
        if (content != null) {
            try {
                parse(new StringReader(content), data);
            } catch (IOException exception) {
                throw Throwables.propagate(exception);
            }
        }
    }

    public static void parse(Reader reader, Object data) throws IOException {
        Class<?> clazz = data.getClass();
        ClassInfo classInfo = ClassInfo.m7508of(clazz);
        List<Type> context = Arrays.asList(new Type[]{clazz});
        GenericData genericData = GenericData.class.isAssignableFrom(clazz) ? (GenericData) data : null;
        Map<Object, Object> map = Map.class.isAssignableFrom(clazz) ? (Map) data : null;
        ArrayValueMap arrayValueMap = new ArrayValueMap(data);
        StringWriter nameWriter = new StringWriter();
        StringWriter valueWriter = new StringWriter();
        boolean readingName = true;
        while (true) {
            int read = reader.read();
            switch (read) {
                case -1:
                case 38:
                    String name = CharEscapers.decodeUri(nameWriter.toString());
                    if (name.length() != 0) {
                        String stringValue = CharEscapers.decodeUri(valueWriter.toString());
                        FieldInfo fieldInfo = classInfo.getFieldInfo(name);
                        if (fieldInfo != null) {
                            Type type = Data.resolveWildcardTypeOrTypeVariable(context, fieldInfo.getGenericType());
                            if (Types.isArray(type)) {
                                Class<?> rawArrayComponentType = Types.getRawArrayComponentType(context, Types.getArrayComponentType(type));
                                arrayValueMap.put(fieldInfo.getField(), rawArrayComponentType, parseValue(rawArrayComponentType, context, stringValue));
                            } else if (Types.isAssignableToOrFrom(Types.getRawArrayComponentType(context, type), Iterable.class)) {
                                Collection<Object> collection = (Collection) fieldInfo.getValue(data);
                                if (collection == null) {
                                    collection = Data.newCollectionInstance(type);
                                    fieldInfo.setValue(data, collection);
                                }
                                collection.add(parseValue(type == Object.class ? null : Types.getIterableParameter(type), context, stringValue));
                            } else {
                                fieldInfo.setValue(data, parseValue(type, context, stringValue));
                            }
                        } else if (map != null) {
                            ArrayList<String> listValue = (ArrayList) map.get(name);
                            if (listValue == null) {
                                listValue = new ArrayList();
                                if (genericData != null) {
                                    genericData.set(name, listValue);
                                } else {
                                    map.put(name, listValue);
                                }
                            }
                            listValue.add(stringValue);
                        }
                    }
                    readingName = true;
                    nameWriter = new StringWriter();
                    valueWriter = new StringWriter();
                    if (read != -1) {
                        break;
                    }
                    arrayValueMap.setValues();
                    return;
                case 61:
                    readingName = false;
                    break;
                default:
                    if (!readingName) {
                        valueWriter.write(read);
                        break;
                    } else {
                        nameWriter.write(read);
                        break;
                    }
            }
        }
    }

    private static Object parseValue(Type valueType, List<Type> context, String value) {
        return Data.parsePrimitiveValue(Data.resolveWildcardTypeOrTypeVariable(context, valueType), value);
    }

    public <T> T parseAndClose(InputStream in, Charset charset, Class<T> dataClass) throws IOException {
        return parseAndClose(new InputStreamReader(in, charset), (Class) dataClass);
    }

    public <T> T parseAndClose(Reader reader, Class<T> dataClass) throws IOException {
        return parseAndClose(reader, (Type) dataClass);
    }

    public Object parseAndClose(Reader reader, Type dataType) throws IOException {
        Preconditions.checkArgument(dataType instanceof Class, "dataType has to be of type Class<?>");
        Object newInstance = Types.newInstance((Class) dataType);
        parse(new BufferedReader(reader), newInstance);
        return newInstance;
    }
}
