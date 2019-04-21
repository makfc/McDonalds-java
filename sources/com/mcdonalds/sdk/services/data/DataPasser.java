package com.mcdonalds.sdk.services.data;

import android.util.SparseArray;
import java.util.HashMap;

public class DataPasser {
    public static int KEY_NOT_FOUND = -1;
    private static DataPasser instance;
    private int currentKey = 1;
    private HashMap<String, Object> mMap = new HashMap();
    private SparseArray<Object> mSparseArray = new SparseArray();

    private DataPasser() {
    }

    public static DataPasser getInstance() {
        if (instance == null) {
            instance = new DataPasser();
        }
        return instance;
    }

    public void putData(String key, Object data) {
        this.mMap.put(key, data);
    }

    @Deprecated
    public int putData(Object data) {
        this.currentKey++;
        this.mSparseArray.put(this.currentKey, data);
        return this.currentKey;
    }

    public <T> T getData(String key) {
        try {
            return this.mMap.remove(key);
        } catch (ClassCastException e) {
            return null;
        }
    }

    @Deprecated
    public Object getData(int key) {
        Object ret = this.mSparseArray.get(key);
        this.mSparseArray.delete(key);
        return ret;
    }
}
