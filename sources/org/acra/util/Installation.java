package org.acra.util;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;
import org.acra.ACRA;

public class Installation {
    private static String sID;

    /* renamed from: id */
    public static synchronized String m7500id(Context context) {
        String str;
        synchronized (Installation.class) {
            if (sID == null) {
                File file = new File(context.getFilesDir(), "ACRA-INSTALLATION");
                try {
                    if (!file.exists()) {
                        writeInstallationFile(file);
                    }
                    sID = readInstallationFile(file);
                } catch (IOException e) {
                    ACRA.log.mo23354w(ACRA.LOG_TAG, "Couldn't retrieve InstallationId for " + context.getPackageName(), e);
                    str = "Couldn't retrieve InstallationId";
                } catch (RuntimeException e2) {
                    ACRA.log.mo23354w(ACRA.LOG_TAG, "Couldn't retrieve InstallationId for " + context.getPackageName(), e2);
                    str = "Couldn't retrieve InstallationId";
                }
            }
            str = sID;
        }
        return str;
    }

    private static String readInstallationFile(File installation) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(installation, "r");
        byte[] bArr = new byte[((int) randomAccessFile.length())];
        try {
            randomAccessFile.readFully(bArr);
            return new String(bArr);
        } finally {
            randomAccessFile.close();
        }
    }

    private static void writeInstallationFile(File installation) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(installation);
        try {
            fileOutputStream.write(UUID.randomUUID().toString().getBytes());
        } finally {
            fileOutputStream.close();
        }
    }
}
