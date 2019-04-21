package com.crashlytics.android.core;

import android.content.Context;
import java.io.File;
import java.util.Set;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.services.common.CommonUtils;

class LogFileManager {
    private static final NoopLogStore NOOP_LOG_STORE = new NoopLogStore();
    private final Context context;
    private FileLogStore currentLog;
    private final DirectoryProvider directoryProvider;

    public interface DirectoryProvider {
        File getLogFileDir();
    }

    private static final class NoopLogStore implements FileLogStore {
        private NoopLogStore() {
        }

        public void writeToLog(long timestamp, String msg) {
        }

        public ByteString getLogAsByteString() {
            return null;
        }

        public byte[] getLogAsBytes() {
            return null;
        }

        public void closeLogFile() {
        }

        public void deleteLogFile() {
        }
    }

    LogFileManager(Context context, DirectoryProvider directoryProvider) {
        this(context, directoryProvider, null);
    }

    LogFileManager(Context context, DirectoryProvider directoryProvider, String currentSessionId) {
        this.context = context;
        this.directoryProvider = directoryProvider;
        this.currentLog = NOOP_LOG_STORE;
        setCurrentSession(currentSessionId);
    }

    /* Access modifiers changed, original: final */
    public final void setCurrentSession(String sessionId) {
        this.currentLog.closeLogFile();
        this.currentLog = NOOP_LOG_STORE;
        if (sessionId != null) {
            if (CommonUtils.getBooleanResourceValue(this.context, "com.crashlytics.CollectCustomLogs", true)) {
                setLogFile(getWorkingFileForSession(sessionId), 65536);
            } else {
                Fabric.getLogger().mo34403d("CrashlyticsCore", "Preferences requested no custom logs. Aborting log file creation.");
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void writeToLog(long timestamp, String msg) {
        this.currentLog.writeToLog(timestamp, msg);
    }

    /* Access modifiers changed, original: 0000 */
    public ByteString getByteStringForLog() {
        return this.currentLog.getLogAsByteString();
    }

    /* Access modifiers changed, original: 0000 */
    public byte[] getBytesForLog() {
        return this.currentLog.getLogAsBytes();
    }

    /* Access modifiers changed, original: 0000 */
    public void clearLog() {
        this.currentLog.deleteLogFile();
    }

    /* Access modifiers changed, original: 0000 */
    public void discardOldLogFiles(Set<String> sessionIdsToKeep) {
        File[] logFiles = this.directoryProvider.getLogFileDir().listFiles();
        if (logFiles != null) {
            for (File file : logFiles) {
                if (!sessionIdsToKeep.contains(getSessionIdForFile(file))) {
                    file.delete();
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void setLogFile(File workingFile, int maxLogSize) {
        this.currentLog = new QueueFileLogStore(workingFile, maxLogSize);
    }

    private File getWorkingFileForSession(String sessionId) {
        return new File(this.directoryProvider.getLogFileDir(), "crashlytics-userlog-" + sessionId + ".temp");
    }

    private String getSessionIdForFile(File workingFile) {
        String filename = workingFile.getName();
        int indexOfExtension = filename.lastIndexOf(".temp");
        return indexOfExtension == -1 ? filename : filename.substring("crashlytics-userlog-".length(), indexOfExtension);
    }
}
