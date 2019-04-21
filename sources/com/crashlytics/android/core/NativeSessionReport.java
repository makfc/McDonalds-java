package com.crashlytics.android.core;

import com.crashlytics.android.core.Report.Type;
import java.io.File;
import java.util.Map;
import p041io.fabric.sdk.android.Fabric;

class NativeSessionReport implements Report {
    private final File reportDirectory;

    public NativeSessionReport(File reportDirectory) {
        this.reportDirectory = reportDirectory;
    }

    public void remove() {
        for (File file : getFiles()) {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Removing native report file at " + file.getPath());
            file.delete();
        }
        Fabric.getLogger().mo34403d("CrashlyticsCore", "Removing native report directory at " + this.reportDirectory);
        this.reportDirectory.delete();
    }

    public String getFileName() {
        return null;
    }

    public String getIdentifier() {
        return this.reportDirectory.getName();
    }

    public File getFile() {
        return null;
    }

    public File[] getFiles() {
        return this.reportDirectory.listFiles();
    }

    public Map<String, String> getCustomHeaders() {
        return null;
    }

    public Type getType() {
        return Type.NATIVE;
    }
}
