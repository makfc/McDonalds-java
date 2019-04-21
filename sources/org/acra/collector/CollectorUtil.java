package org.acra.collector;

import java.io.IOException;
import java.io.Reader;

public final class CollectorUtil {
    public static void safeClose(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }
    }
}
