package com.crashlytics.android.core;

import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.services.common.CommonUtils;
import p041io.fabric.sdk.android.services.common.QueueFile;
import p041io.fabric.sdk.android.services.common.QueueFile.ElementReader;

class QueueFileLogStore implements FileLogStore {
    private QueueFile logFile;
    private final int maxLogSize;
    private final File workingFile;

    public class LogBytes {
        public final byte[] bytes;
        public final int offset;

        public LogBytes(byte[] bytes, int offset) {
            this.bytes = bytes;
            this.offset = offset;
        }
    }

    public QueueFileLogStore(File workingFile, int maxLogSize) {
        this.workingFile = workingFile;
        this.maxLogSize = maxLogSize;
    }

    public void writeToLog(long timestamp, String msg) {
        openLogFile();
        doWriteToLog(timestamp, msg);
    }

    public ByteString getLogAsByteString() {
        LogBytes logBytes = getLogBytes();
        return logBytes == null ? null : ByteString.copyFrom(logBytes.bytes, 0, logBytes.offset);
    }

    public byte[] getLogAsBytes() {
        LogBytes logBytes = getLogBytes();
        return logBytes == null ? null : logBytes.bytes;
    }

    private LogBytes getLogBytes() {
        if (!this.workingFile.exists()) {
            return null;
        }
        openLogFile();
        if (this.logFile == null) {
            return null;
        }
        final int[] offsetHolder = new int[]{0};
        final byte[] logBytes = new byte[this.logFile.usedBytes()];
        try {
            this.logFile.forEach(new ElementReader() {
                public void read(InputStream in, int length) throws IOException {
                    try {
                        in.read(logBytes, offsetHolder[0], length);
                        int[] iArr = offsetHolder;
                        iArr[0] = iArr[0] + length;
                    } finally {
                        in.close();
                    }
                }
            });
        } catch (IOException e) {
            Fabric.getLogger().mo34406e("CrashlyticsCore", "A problem occurred while reading the Crashlytics log file.", e);
        }
        return new LogBytes(logBytes, offsetHolder[0]);
    }

    public void closeLogFile() {
        CommonUtils.closeOrLog(this.logFile, "There was a problem closing the Crashlytics log file.");
        this.logFile = null;
    }

    public void deleteLogFile() {
        closeLogFile();
        this.workingFile.delete();
    }

    private void openLogFile() {
        if (this.logFile == null) {
            try {
                this.logFile = new QueueFile(this.workingFile);
            } catch (IOException e) {
                Fabric.getLogger().mo34406e("CrashlyticsCore", "Could not open log file: " + this.workingFile, e);
            }
        }
    }

    private void doWriteToLog(long timestamp, String msg) {
        if (this.logFile != null) {
            if (msg == null) {
                msg = SafeJsonPrimitive.NULL_STRING;
            }
            try {
                int quarterMaxLogSize = this.maxLogSize / 4;
                if (msg.length() > quarterMaxLogSize) {
                    msg = "..." + msg.substring(msg.length() - quarterMaxLogSize);
                }
                msg = msg.replaceAll("\r", " ").replaceAll("\n", " ");
                this.logFile.add(String.format(Locale.US, "%d %s%n", new Object[]{Long.valueOf(timestamp), msg}).getBytes(Utf8Charset.NAME));
                while (!this.logFile.isEmpty() && this.logFile.usedBytes() > this.maxLogSize) {
                    this.logFile.remove();
                }
            } catch (IOException e) {
                Fabric.getLogger().mo34406e("CrashlyticsCore", "There was a problem writing to the Crashlytics log.", e);
            }
        }
    }
}
