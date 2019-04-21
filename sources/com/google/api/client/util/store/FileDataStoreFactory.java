package com.google.api.client.util.store;

import com.google.api.client.util.IOUtils;
import com.google.api.client.util.Maps;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Logger;

public class FileDataStoreFactory extends AbstractDataStoreFactory {
    private static final Logger LOGGER = Logger.getLogger(FileDataStoreFactory.class.getName());
    private final File dataDirectory;

    static class FileDataStore<V extends Serializable> extends AbstractMemoryDataStore<V> {
        private final File dataFile;

        FileDataStore(FileDataStoreFactory dataStore, File dataDirectory, String id) throws IOException {
            super(dataStore, id);
            this.dataFile = new File(dataDirectory, id);
            if (IOUtils.isSymbolicLink(this.dataFile)) {
                String valueOf = String.valueOf(String.valueOf(this.dataFile));
                throw new IOException(new StringBuilder(valueOf.length() + 31).append("unable to use a symbolic link: ").append(valueOf).toString());
            } else if (this.dataFile.createNewFile()) {
                this.keyValueMap = Maps.newHashMap();
                save();
            } else {
                this.keyValueMap = (HashMap) IOUtils.deserialize(new FileInputStream(this.dataFile));
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void save() throws IOException {
            IOUtils.serialize(this.keyValueMap, new FileOutputStream(this.dataFile));
        }
    }

    public FileDataStoreFactory(File dataDirectory) throws IOException {
        dataDirectory = dataDirectory.getCanonicalFile();
        this.dataDirectory = dataDirectory;
        String valueOf;
        if (IOUtils.isSymbolicLink(dataDirectory)) {
            valueOf = String.valueOf(String.valueOf(dataDirectory));
            throw new IOException(new StringBuilder(valueOf.length() + 31).append("unable to use a symbolic link: ").append(valueOf).toString());
        } else if (dataDirectory.exists() || dataDirectory.mkdirs()) {
            setPermissionsToOwnerOnly(dataDirectory);
        } else {
            valueOf = String.valueOf(String.valueOf(dataDirectory));
            throw new IOException(new StringBuilder(valueOf.length() + 28).append("unable to create directory: ").append(valueOf).toString());
        }
    }

    /* Access modifiers changed, original: protected */
    public <V extends Serializable> DataStore<V> createDataStore(String id) throws IOException {
        return new FileDataStore(this, this.dataDirectory, id);
    }

    /* JADX WARNING: Missing block: B:6:0x009a, code skipped:
            if (((java.lang.Boolean) r2.invoke(r10, new java.lang.Object[]{java.lang.Boolean.valueOf(false), java.lang.Boolean.valueOf(false)})).booleanValue() == false) goto L_0x009c;
     */
    static void setPermissionsToOwnerOnly(java.io.File r10) throws java.io.IOException {
        /*
        r5 = java.io.File.class;
        r6 = "setReadable";
        r7 = 2;
        r7 = new java.lang.Class[r7];	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r8 = 0;
        r9 = java.lang.Boolean.TYPE;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r7[r8] = r9;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r8 = 1;
        r9 = java.lang.Boolean.TYPE;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r7[r8] = r9;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r3 = r5.getMethod(r6, r7);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = java.io.File.class;
        r6 = "setWritable";
        r7 = 2;
        r7 = new java.lang.Class[r7];	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r8 = 0;
        r9 = java.lang.Boolean.TYPE;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r7[r8] = r9;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r8 = 1;
        r9 = java.lang.Boolean.TYPE;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r7[r8] = r9;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r4 = r5.getMethod(r6, r7);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = java.io.File.class;
        r6 = "setExecutable";
        r7 = 2;
        r7 = new java.lang.Class[r7];	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r8 = 0;
        r9 = java.lang.Boolean.TYPE;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r7[r8] = r9;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r8 = 1;
        r9 = java.lang.Boolean.TYPE;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r7[r8] = r9;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r2 = r5.getMethod(r6, r7);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = 2;
        r5 = new java.lang.Object[r5];	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = 0;
        r7 = 0;
        r7 = java.lang.Boolean.valueOf(r7);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5[r6] = r7;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = 1;
        r7 = 0;
        r7 = java.lang.Boolean.valueOf(r7);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5[r6] = r7;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = r3.invoke(r10, r5);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = (java.lang.Boolean) r5;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = r5.booleanValue();	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        if (r5 == 0) goto L_0x009c;
    L_0x005e:
        r5 = 2;
        r5 = new java.lang.Object[r5];	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = 0;
        r7 = 0;
        r7 = java.lang.Boolean.valueOf(r7);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5[r6] = r7;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = 1;
        r7 = 0;
        r7 = java.lang.Boolean.valueOf(r7);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5[r6] = r7;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = r4.invoke(r10, r5);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = (java.lang.Boolean) r5;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = r5.booleanValue();	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        if (r5 == 0) goto L_0x009c;
    L_0x007d:
        r5 = 2;
        r5 = new java.lang.Object[r5];	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = 0;
        r7 = 0;
        r7 = java.lang.Boolean.valueOf(r7);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5[r6] = r7;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = 1;
        r7 = 0;
        r7 = java.lang.Boolean.valueOf(r7);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5[r6] = r7;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = r2.invoke(r10, r5);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = (java.lang.Boolean) r5;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = r5.booleanValue();	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        if (r5 != 0) goto L_0x00c2;
    L_0x009c:
        r5 = LOGGER;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = java.lang.String.valueOf(r10);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = java.lang.String.valueOf(r6);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r7 = new java.lang.StringBuilder;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r8 = r6.length();	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r8 = r8 + 44;
        r7.<init>(r8);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r8 = "unable to change permissions for everybody: ";
        r7 = r7.append(r8);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = r7.append(r6);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = r6.toString();	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5.warning(r6);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
    L_0x00c2:
        r5 = 2;
        r5 = new java.lang.Object[r5];	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = 0;
        r7 = 1;
        r7 = java.lang.Boolean.valueOf(r7);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5[r6] = r7;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = 1;
        r7 = 1;
        r7 = java.lang.Boolean.valueOf(r7);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5[r6] = r7;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = r3.invoke(r10, r5);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = (java.lang.Boolean) r5;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = r5.booleanValue();	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        if (r5 == 0) goto L_0x011f;
    L_0x00e1:
        r5 = 2;
        r5 = new java.lang.Object[r5];	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = 0;
        r7 = 1;
        r7 = java.lang.Boolean.valueOf(r7);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5[r6] = r7;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = 1;
        r7 = 1;
        r7 = java.lang.Boolean.valueOf(r7);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5[r6] = r7;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = r4.invoke(r10, r5);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = (java.lang.Boolean) r5;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = r5.booleanValue();	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        if (r5 == 0) goto L_0x011f;
    L_0x0100:
        r5 = 2;
        r5 = new java.lang.Object[r5];	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = 0;
        r7 = 1;
        r7 = java.lang.Boolean.valueOf(r7);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5[r6] = r7;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = 1;
        r7 = 1;
        r7 = java.lang.Boolean.valueOf(r7);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5[r6] = r7;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = r2.invoke(r10, r5);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = (java.lang.Boolean) r5;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5 = r5.booleanValue();	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        if (r5 != 0) goto L_0x0145;
    L_0x011f:
        r5 = LOGGER;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = java.lang.String.valueOf(r10);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = java.lang.String.valueOf(r6);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r7 = new java.lang.StringBuilder;	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r8 = r6.length();	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r8 = r8 + 40;
        r7.<init>(r8);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r8 = "unable to change permissions for owner: ";
        r7 = r7.append(r8);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = r7.append(r6);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r6 = r6.toString();	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
        r5.warning(r6);	 Catch:{ InvocationTargetException -> 0x0146, NoSuchMethodException -> 0x0156, SecurityException -> 0x0188, IllegalAccessException -> 0x0186, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x0184 }
    L_0x0145:
        return;
    L_0x0146:
        r1 = move-exception;
        r0 = r1.getCause();
        r5 = java.io.IOException.class;
        com.google.api.client.util.Throwables.propagateIfPossible(r0, r5);
        r5 = new java.lang.RuntimeException;
        r5.<init>(r0);
        throw r5;
    L_0x0156:
        r1 = move-exception;
        r5 = LOGGER;
        r6 = java.lang.String.valueOf(r10);
        r6 = java.lang.String.valueOf(r6);
        r7 = new java.lang.StringBuilder;
        r8 = r6.length();
        r8 = r8 + 93;
        r7.<init>(r8);
        r8 = "Unable to set permissions for ";
        r7 = r7.append(r8);
        r6 = r7.append(r6);
        r7 = ", likely because you are running a version of Java prior to 1.6";
        r6 = r6.append(r7);
        r6 = r6.toString();
        r5.warning(r6);
        goto L_0x0145;
    L_0x0184:
        r5 = move-exception;
        goto L_0x0145;
    L_0x0186:
        r5 = move-exception;
        goto L_0x0145;
    L_0x0188:
        r5 = move-exception;
        goto L_0x0145;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.util.store.FileDataStoreFactory.setPermissionsToOwnerOnly(java.io.File):void");
    }
}
