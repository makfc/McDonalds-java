package com.newrelic.agent.android.stores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.util.Base64;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressLint({"NewApi"})
public abstract class SharedPrefsStore {
    protected static final Charset ENCODING = StandardCharsets.ISO_8859_1;
    protected static final AgentLog log = AgentLogManager.getAgentLog();
    protected final SharedPreferences sharedPrefs;
    protected final String storeFilename;

    public SharedPrefsStore(Context context, String storeFilename) {
        this.sharedPrefs = context.getSharedPreferences(storeFilename, 0);
        this.storeFilename = storeFilename;
    }

    public String getStoreFilename() {
        return this.storeFilename;
    }

    public boolean store(String uuid, byte[] bytes) {
        try {
            Editor editor = this.sharedPrefs.edit();
            editor.putString(uuid, decodeBytesToString(bytes));
            return applyOrCommitEditor(editor);
        } catch (Exception e) {
            log.error("SharedPrefsStore.store(String, byte[]): ", e);
            return false;
        }
    }

    public boolean store(String uuid, Set<String> stringSet) {
        try {
            Editor editor = this.sharedPrefs.edit();
            editor.putStringSet(uuid, stringSet);
            return applyOrCommitEditor(editor);
        } catch (Exception e) {
            log.error("SharedPrefsStore.store(String, Set<String>): ", e);
            return false;
        }
    }

    public boolean store(String uuid, String string) {
        try {
            Editor editor = this.sharedPrefs.edit();
            editor.putString(uuid, string);
            return applyOrCommitEditor(editor);
        } catch (Exception e) {
            log.error("SharedPrefsStore.store(String, String): ", e);
            return false;
        }
    }

    public Map<String, ?> getAll() {
        Map objectList = new HashMap();
        try {
            synchronized (this) {
                objectList.putAll(this.sharedPrefs.getAll());
            }
        } catch (Exception e) {
            log.error("SharedPrefsStore.fetchAll(): ", e);
        }
        return objectList;
    }

    public List<?> fetchAll() {
        List<Object> objectList = new ArrayList();
        try {
            synchronized (this) {
                objectList.addAll(this.sharedPrefs.getAll().values());
            }
        } catch (Exception e) {
            log.error("SharedPrefsStore.fetchAll(): ", e);
        }
        return objectList;
    }

    public int count() {
        try {
            int size;
            synchronized (this.sharedPrefs) {
                size = this.sharedPrefs.getAll().size();
            }
            return size;
        } catch (Exception e) {
            log.error("SharedPrefsStore.count(): ", e);
            return 0;
        }
    }

    public void clear() {
        try {
            synchronized (this) {
                Editor editor = this.sharedPrefs.edit();
                editor.clear();
                applyOrCommitEditor(editor);
            }
        } catch (Exception e) {
            log.error("SharedPrefsStore.clear(): ", e);
        }
    }

    public void delete(String uuid) {
        try {
            synchronized (this) {
                Editor editor = this.sharedPrefs.edit();
                editor.remove(uuid);
                applyOrCommitEditor(editor);
            }
        } catch (Exception e) {
            log.error("SharedPrefsStore.delete(): ", e);
        }
    }

    /* Access modifiers changed, original: protected */
    public String encodeBytes(byte[] bytes) {
        try {
            return Base64.encodeToString(bytes, 2);
        } catch (Exception e) {
            log.error("SharedPrefsStore.encodeBytes(byte[]): ", e);
            return null;
        }
    }

    /* Access modifiers changed, original: protected */
    public byte[] decodeStringToBytes(String encodedString) {
        try {
            return Base64.decode(encodedString, 0);
        } catch (Exception e) {
            log.error("SharedPrefsStore.decodeStringToBytes(String): ", e);
            return null;
        }
    }

    /* Access modifiers changed, original: protected */
    public String decodeBytesToString(byte[] decodedString) {
        try {
            return new String(decodedString, ENCODING);
        } catch (Exception e) {
            log.error("SharedPrefsStore.decodeBytesToString(byte[]): ", e);
            return null;
        }
    }

    /* Access modifiers changed, original: protected */
    @SuppressLint({"CommitPrefEdits"})
    public boolean applyOrCommitEditor(Editor editor) {
        try {
            if (VERSION.SDK_INT < 9) {
                return editor.commit();
            }
            editor.apply();
            return true;
        } catch (Exception e) {
            log.error("SharedPrefsStore.applyOrCommitEditor(SharedPreferences.Editor): ", e);
            return true;
        }
    }
}
