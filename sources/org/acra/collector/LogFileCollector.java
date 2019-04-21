package org.acra.collector;

import android.content.Context;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import org.acra.ACRA;
import org.acra.util.BoundedLinkedList;

class LogFileCollector {
    private LogFileCollector() {
    }

    public static String collectLogFile(Context context, String fileName, int numberOfLines) throws IOException {
        BoundedLinkedList boundedLinkedList = new BoundedLinkedList(numberOfLines);
        BufferedReader reader = getReader(context, fileName);
        try {
            for (String readLine = reader.readLine(); readLine != null; readLine = reader.readLine()) {
                boundedLinkedList.add(readLine + "\n");
            }
            return boundedLinkedList.toString();
        } finally {
            CollectorUtil.safeClose(reader);
        }
    }

    private static BufferedReader getReader(Context context, String fileName) {
        try {
            if (fileName.contains("/")) {
                return new BufferedReader(new InputStreamReader(new FileInputStream(fileName)), 1024);
            }
            return new BufferedReader(new InputStreamReader(context.openFileInput(fileName)), 1024);
        } catch (FileNotFoundException e) {
            ACRA.log.mo23349e(ACRA.LOG_TAG, "Cannot find application log file : '" + ACRA.getConfig().applicationLogFile() + "'");
            return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(new byte[0])));
        }
    }
}
