package com.google.api.client.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public final class ArrayValueMap {
    private final Object destination;
    private final Map<Field, ArrayValue> fieldMap = ArrayMap.create();
    private final Map<String, ArrayValue> keyMap = ArrayMap.create();

    static class ArrayValue {
        final Class<?> componentType;
        final ArrayList<Object> values = new ArrayList();

        ArrayValue(Class<?> componentType) {
            this.componentType = componentType;
        }

        /* Access modifiers changed, original: 0000 */
        public Object toArray() {
            return Types.toArray(this.values, this.componentType);
        }

        /* Access modifiers changed, original: 0000 */
        public void addValue(Class<?> componentType, Object value) {
            Preconditions.checkArgument(componentType == this.componentType);
            this.values.add(value);
        }
    }

    public ArrayValueMap(Object destination) {
        this.destination = destination;
    }

    public void setValues() {
        for (Entry<String, ArrayValue> entry : this.keyMap.entrySet()) {
            this.destination.put(entry.getKey(), ((ArrayValue) entry.getValue()).toArray());
        }
        for (Entry<Field, ArrayValue> entry2 : this.fieldMap.entrySet()) {
            FieldInfo.setFieldValue((Field) entry2.getKey(), this.destination, ((ArrayValue) entry2.getValue()).toArray());
        }
    }

    public void put(Field field, Class<?> arrayComponentType, Object value) {
        ArrayValue arrayValue = (ArrayValue) this.fieldMap.get(field);
        if (arrayValue == null) {
            arrayValue = new ArrayValue(arrayComponentType);
            this.fieldMap.put(field, arrayValue);
        }
        arrayValue.addValue(arrayComponentType, value);
    }
}
