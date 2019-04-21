package com.google.api.client.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class GenericData extends AbstractMap<String, Object> implements Cloneable {
    final ClassInfo classInfo;
    Map<String, Object> unknownFields;

    final class EntryIterator implements Iterator<Entry<String, Object>> {
        private final Iterator<Entry<String, Object>> fieldIterator;
        private boolean startedUnknown;
        private final Iterator<Entry<String, Object>> unknownIterator;

        EntryIterator(EntrySet dataEntrySet) {
            this.fieldIterator = dataEntrySet.iterator();
            this.unknownIterator = GenericData.this.unknownFields.entrySet().iterator();
        }

        public boolean hasNext() {
            return this.fieldIterator.hasNext() || this.unknownIterator.hasNext();
        }

        public Entry<String, Object> next() {
            if (!this.startedUnknown) {
                if (this.fieldIterator.hasNext()) {
                    return (Entry) this.fieldIterator.next();
                }
                this.startedUnknown = true;
            }
            return (Entry) this.unknownIterator.next();
        }

        public void remove() {
            if (this.startedUnknown) {
                this.unknownIterator.remove();
            }
            this.fieldIterator.remove();
        }
    }

    final class EntrySet extends AbstractSet<Entry<String, Object>> {
        private final EntrySet dataEntrySet;

        EntrySet() {
            this.dataEntrySet = new DataMap(GenericData.this, GenericData.this.classInfo.getIgnoreCase()).entrySet();
        }

        public Iterator<Entry<String, Object>> iterator() {
            return new EntryIterator(this.dataEntrySet);
        }

        public int size() {
            return GenericData.this.unknownFields.size() + this.dataEntrySet.size();
        }

        public void clear() {
            GenericData.this.unknownFields.clear();
            this.dataEntrySet.clear();
        }
    }

    public enum Flags {
        IGNORE_CASE
    }

    public GenericData() {
        this(EnumSet.noneOf(Flags.class));
    }

    public GenericData(EnumSet<Flags> flags) {
        this.unknownFields = ArrayMap.create();
        this.classInfo = ClassInfo.m7509of(getClass(), flags.contains(Flags.IGNORE_CASE));
    }

    public final Object get(Object name) {
        if (!(name instanceof String)) {
            return null;
        }
        String fieldName = (String) name;
        FieldInfo fieldInfo = this.classInfo.getFieldInfo(fieldName);
        if (fieldInfo != null) {
            return fieldInfo.getValue(this);
        }
        if (this.classInfo.getIgnoreCase()) {
            fieldName = fieldName.toLowerCase();
        }
        return this.unknownFields.get(fieldName);
    }

    public final Object put(String fieldName, Object value) {
        FieldInfo fieldInfo = this.classInfo.getFieldInfo(fieldName);
        if (fieldInfo != null) {
            Object oldValue = fieldInfo.getValue(this);
            fieldInfo.setValue(this, value);
            return oldValue;
        }
        if (this.classInfo.getIgnoreCase()) {
            fieldName = fieldName.toLowerCase();
        }
        return this.unknownFields.put(fieldName, value);
    }

    public GenericData set(String fieldName, Object value) {
        FieldInfo fieldInfo = this.classInfo.getFieldInfo(fieldName);
        if (fieldInfo != null) {
            fieldInfo.setValue(this, value);
        } else {
            if (this.classInfo.getIgnoreCase()) {
                fieldName = fieldName.toLowerCase();
            }
            this.unknownFields.put(fieldName, value);
        }
        return this;
    }

    public final void putAll(Map<? extends String, ?> map) {
        for (Entry<? extends String, ?> entry : map.entrySet()) {
            set((String) entry.getKey(), entry.getValue());
        }
    }

    public final Object remove(Object name) {
        if (!(name instanceof String)) {
            return null;
        }
        String fieldName = (String) name;
        if (this.classInfo.getFieldInfo(fieldName) != null) {
            throw new UnsupportedOperationException();
        }
        if (this.classInfo.getIgnoreCase()) {
            fieldName = fieldName.toLowerCase();
        }
        return this.unknownFields.remove(fieldName);
    }

    public Set<Entry<String, Object>> entrySet() {
        return new EntrySet();
    }

    public GenericData clone() {
        try {
            GenericData result = (GenericData) super.clone();
            Data.deepCopy(this, result);
            result.unknownFields = (Map) Data.clone(this.unknownFields);
            return result;
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
        }
    }

    public final ClassInfo getClassInfo() {
        return this.classInfo;
    }
}
