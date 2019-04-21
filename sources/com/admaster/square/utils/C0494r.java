package com.admaster.square.utils;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/* compiled from: SelfInfoUtil */
/* renamed from: com.admaster.square.utils.r */
class C0494r implements FileFilter {
    C0494r() {
    }

    public boolean accept(File file) {
        if (Pattern.matches("cpu[0-9]", file.getName())) {
            return true;
        }
        return false;
    }
}
