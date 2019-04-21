package com.facebook.stetho.dumpapp;

import java.util.Iterator;

public class ArgsHelper {
    public static String nextOptionalArg(Iterator<String> argsIter, String defaultValue) {
        return argsIter.hasNext() ? (String) argsIter.next() : defaultValue;
    }

    public static String nextArg(Iterator<String> argsIter, String errorIfMissing) throws DumpUsageException {
        if (argsIter.hasNext()) {
            return (String) argsIter.next();
        }
        throw new DumpUsageException(errorIfMissing);
    }
}
