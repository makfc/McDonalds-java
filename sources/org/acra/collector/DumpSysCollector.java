package org.acra.collector;

import android.os.Process;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import org.acra.ACRA;

final class DumpSysCollector {
    DumpSysCollector() {
    }

    public static String collectMemInfo() {
        Reader bufferedReader;
        Throwable e;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add("dumpsys");
            arrayList.add("meminfo");
            arrayList.add(Integer.toString(Process.myPid()));
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec((String[]) arrayList.toArray(new String[arrayList.size()])).getInputStream()), 8192);
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuilder.append(readLine);
                    stringBuilder.append("\n");
                } catch (IOException e2) {
                    e = e2;
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "DumpSysCollector.meminfo could not retrieve data", e);
                    CollectorUtil.safeClose(bufferedReader);
                    return stringBuilder.toString();
                }
            }
        } catch (IOException e3) {
            Throwable th = e3;
            bufferedReader = null;
            e = th;
            ACRA.log.mo23350e(ACRA.LOG_TAG, "DumpSysCollector.meminfo could not retrieve data", e);
            CollectorUtil.safeClose(bufferedReader);
            return stringBuilder.toString();
        }
        CollectorUtil.safeClose(bufferedReader);
        return stringBuilder.toString();
    }
}
