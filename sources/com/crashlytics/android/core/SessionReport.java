package com.crashlytics.android.core;

import com.crashlytics.android.core.Report.Type;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import p041io.fabric.sdk.android.Fabric;

class SessionReport implements Report {
    private final Map<String, String> customHeaders;
    private final File file;
    private final File[] files;

    public SessionReport(File file) {
        this(file, Collections.emptyMap());
    }

    public SessionReport(File file, Map<String, String> customHeaders) {
        this.file = file;
        this.files = new File[]{file};
        this.customHeaders = new HashMap(customHeaders);
        if (this.file.length() == 0) {
            this.customHeaders.putAll(ReportUploader.HEADER_INVALID_CLS_FILE);
        }
    }

    public File getFile() {
        return this.file;
    }

    public File[] getFiles() {
        return this.files;
    }

    public String getFileName() {
        return getFile().getName();
    }

    public String getIdentifier() {
        String fileName = getFileName();
        return fileName.substring(0, fileName.lastIndexOf(46));
    }

    public Map<String, String> getCustomHeaders() {
        return Collections.unmodifiableMap(this.customHeaders);
    }

    public void remove() {
        Fabric.getLogger().mo34403d("CrashlyticsCore", "Removing report at " + this.file.getPath());
        this.file.delete();
    }

    public Type getType() {
        return Type.JAVA;
    }
}
