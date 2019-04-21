package org.acra.collector;

import android.os.Process;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import org.acra.ACRA;
import org.acra.util.BoundedLinkedList;

class LogCatCollector {
    LogCatCollector() {
    }

    public static String collectLogCat(String bufferName) {
        CharSequence charSequence;
        Throwable e;
        Reader reader = null;
        int myPid = Process.myPid();
        if (!ACRA.getConfig().logcatFilterByPid() || myPid <= 0) {
            charSequence = null;
        } else {
            charSequence = Integer.toString(myPid) + "):";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("logcat");
        if (bufferName != null) {
            arrayList.add("-b");
            arrayList.add(bufferName);
        }
        ArrayList arrayList2 = new ArrayList(Arrays.asList(ACRA.getConfig().logcatArguments()));
        int indexOf = arrayList2.indexOf("-t");
        if (indexOf < 0 || indexOf >= arrayList2.size()) {
            myPid = -1;
        } else {
            myPid = Integer.parseInt((String) arrayList2.get(indexOf + 1));
            if (Compatibility.getAPILevel() < 8) {
                arrayList2.remove(indexOf + 1);
                arrayList2.remove(indexOf);
                arrayList2.add("-d");
            }
        }
        if (myPid <= 0) {
            myPid = 100;
        }
        BoundedLinkedList boundedLinkedList = new BoundedLinkedList(myPid);
        arrayList.addAll(arrayList2);
        Reader bufferedReader;
        try {
            final Process exec = Runtime.getRuntime().exec((String[]) arrayList.toArray(new String[arrayList.size()]));
            bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()), 8192);
            try {
                ACRA.log.mo23347d(ACRA.LOG_TAG, "Retrieving logcat output...");
                new Thread(new Runnable() {
                    public final void run() {
                        try {
                            do {
                            } while (exec.getErrorStream().read(new byte[8192]) >= 0);
                        } catch (IOException e) {
                        }
                    }
                }).start();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (charSequence == null || readLine.contains(charSequence)) {
                        boundedLinkedList.add(readLine + "\n");
                    }
                }
                CollectorUtil.safeClose(bufferedReader);
            } catch (IOException e2) {
                e = e2;
                reader = bufferedReader;
                try {
                    ACRA.log.mo23350e(ACRA.LOG_TAG, "LogCatCollector.collectLogCat could not retrieve data.", e);
                    CollectorUtil.safeClose(reader);
                    return boundedLinkedList.toString();
                } catch (Throwable th) {
                    e = th;
                    bufferedReader = reader;
                    CollectorUtil.safeClose(bufferedReader);
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                CollectorUtil.safeClose(bufferedReader);
                throw e;
            }
        } catch (IOException e3) {
            e = e3;
            ACRA.log.mo23350e(ACRA.LOG_TAG, "LogCatCollector.collectLogCat could not retrieve data.", e);
            CollectorUtil.safeClose(reader);
            return boundedLinkedList.toString();
        } catch (Throwable th3) {
            e = th3;
            bufferedReader = null;
            CollectorUtil.safeClose(bufferedReader);
            throw e;
        }
        return boundedLinkedList.toString();
    }
}
