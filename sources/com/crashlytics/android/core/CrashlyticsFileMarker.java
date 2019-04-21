package com.crashlytics.android.core;

import java.io.File;
import java.io.IOException;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.services.persistence.FileStore;

class CrashlyticsFileMarker {
    private final FileStore fileStore;
    private final String markerName;

    public CrashlyticsFileMarker(String markerName, FileStore fileStore) {
        this.markerName = markerName;
        this.fileStore = fileStore;
    }

    public boolean create() {
        boolean wasCreated = false;
        try {
            return getMarkerFile().createNewFile();
        } catch (IOException e) {
            Fabric.getLogger().mo34406e("CrashlyticsCore", "Error creating marker: " + this.markerName, e);
            return wasCreated;
        }
    }

    public boolean isPresent() {
        return getMarkerFile().exists();
    }

    public boolean remove() {
        return getMarkerFile().delete();
    }

    private File getMarkerFile() {
        return new File(this.fileStore.getFilesDir(), this.markerName);
    }
}
