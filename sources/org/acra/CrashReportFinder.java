package org.acra;

import android.content.Context;
import java.io.File;
import java.io.FilenameFilter;

final class CrashReportFinder {
    private final Context context;

    /* renamed from: org.acra.CrashReportFinder$1 */
    class C26321 implements FilenameFilter {
        C26321() {
        }

        public boolean accept(File dir, String name) {
            return name.endsWith(".stacktrace");
        }
    }

    public CrashReportFinder(Context context) {
        this.context = context;
    }

    public final String[] getCrashReportFiles() {
        if (this.context == null) {
            ACRA.log.mo23349e(ACRA.LOG_TAG, "Trying to get ACRA reports but ACRA is not initialized.");
            return new String[0];
        }
        File filesDir = this.context.getFilesDir();
        if (filesDir == null) {
            ACRA.log.mo23353w(ACRA.LOG_TAG, "Application files directory does not exist! The application may not be installed correctly. Please try reinstalling.");
            return new String[0];
        }
        ACRA.log.mo23347d(ACRA.LOG_TAG, "Looking for error files in " + filesDir.getAbsolutePath());
        String[] list = filesDir.list(new C26321());
        return list == null ? new String[0] : list;
    }
}
