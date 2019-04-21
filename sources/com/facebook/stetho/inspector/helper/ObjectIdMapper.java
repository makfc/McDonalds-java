package com.facebook.stetho.inspector.helper;

import android.util.SparseArray;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class ObjectIdMapper {
    @GuardedBy
    private SparseArray<Object> mIdToObjectMap = new SparseArray();
    @GuardedBy
    private int mNextId = 1;
    @GuardedBy
    private final Map<Object, Integer> mObjectToIdMap = new IdentityHashMap();
    protected final Object mSync = new Object();

    public void clear() {
        SparseArray<Object> idToObjectMap;
        synchronized (this.mSync) {
            idToObjectMap = this.mIdToObjectMap;
            this.mObjectToIdMap.clear();
            this.mIdToObjectMap = new SparseArray();
        }
        int size = idToObjectMap.size();
        for (int i = 0; i < size; i++) {
            onUnmapped(idToObjectMap.valueAt(i), idToObjectMap.keyAt(i));
        }
    }

    public boolean containsId(int id) {
        boolean z;
        synchronized (this.mSync) {
            z = this.mIdToObjectMap.get(id) != null;
        }
        return z;
    }

    public boolean containsObject(Object object) {
        boolean containsKey;
        synchronized (this.mSync) {
            containsKey = this.mObjectToIdMap.containsKey(object);
        }
        return containsKey;
    }

    @Nullable
    public Object getObjectForId(int id) {
        Object obj;
        synchronized (this.mSync) {
            obj = this.mIdToObjectMap.get(id);
        }
        return obj;
    }

    @Nullable
    public Integer getIdForObject(Object object) {
        Integer num;
        synchronized (this.mSync) {
            num = (Integer) this.mObjectToIdMap.get(object);
        }
        return num;
    }

    public int putObject(Object object) {
        synchronized (this.mSync) {
            Integer id = (Integer) this.mObjectToIdMap.get(object);
            int intValue;
            if (id != null) {
                intValue = id.intValue();
                return intValue;
            }
            intValue = this.mNextId;
            this.mNextId = intValue + 1;
            id = Integer.valueOf(intValue);
            this.mObjectToIdMap.put(object, id);
            this.mIdToObjectMap.put(id.intValue(), object);
            onMapped(object, id.intValue());
            return id.intValue();
        }
    }

    @Nullable
    public Object removeObjectById(int id) {
        Object object;
        synchronized (this.mSync) {
            object = this.mIdToObjectMap.get(id);
            if (object == null) {
                object = null;
            } else {
                this.mIdToObjectMap.remove(id);
                this.mObjectToIdMap.remove(object);
                onUnmapped(object, id);
            }
        }
        return object;
    }

    @Nullable
    public Integer removeObject(Object object) {
        Integer id;
        synchronized (this.mSync) {
            id = (Integer) this.mObjectToIdMap.remove(object);
            if (id == null) {
                id = null;
            } else {
                this.mIdToObjectMap.remove(id.intValue());
                onUnmapped(object, id.intValue());
            }
        }
        return id;
    }

    public int size() {
        int size;
        synchronized (this.mSync) {
            size = this.mObjectToIdMap.size();
        }
        return size;
    }

    /* Access modifiers changed, original: protected */
    public void onMapped(Object object, int id) {
    }

    /* Access modifiers changed, original: protected */
    public void onUnmapped(Object object, int id) {
    }
}
