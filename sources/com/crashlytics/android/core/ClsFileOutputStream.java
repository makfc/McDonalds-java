package com.crashlytics.android.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

class ClsFileOutputStream extends FileOutputStream {
    public static final FilenameFilter TEMP_FILENAME_FILTER = new C16301();
    private boolean closed = false;
    private File complete;
    private File inProgress;
    private final String root;

    /* renamed from: com.crashlytics.android.core.ClsFileOutputStream$1 */
    static class C16301 implements FilenameFilter {
        C16301() {
        }

        public boolean accept(File dir, String filename) {
            return filename.endsWith(".cls_temp");
        }
    }

    public ClsFileOutputStream(File dir, String fileRoot) throws FileNotFoundException {
        super(new File(dir, fileRoot + ".cls_temp"));
        this.root = dir + File.separator + fileRoot;
        this.inProgress = new File(this.root + ".cls_temp");
    }

    public synchronized void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            super.flush();
            super.close();
            File complete = new File(this.root + ".cls");
            if (this.inProgress.renameTo(complete)) {
                this.inProgress = null;
                this.complete = complete;
            } else {
                String reason = "";
                if (complete.exists()) {
                    reason = " (target already exists)";
                } else if (!this.inProgress.exists()) {
                    reason = " (source does not exist)";
                }
                throw new IOException("Could not rename temp file: " + this.inProgress + " -> " + complete + reason);
            }
        }
    }

    public void closeInProgressStream() throws IOException {
        if (!this.closed) {
            this.closed = true;
            super.flush();
            super.close();
        }
    }
}
