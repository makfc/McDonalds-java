package com.crashlytics.android.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p041io.fabric.sdk.android.Fabric;

final class ProcMapEntryParser {
    private static final Pattern MAP_REGEX = Pattern.compile("\\s*(\\p{XDigit}+)-\\s*(\\p{XDigit}+)\\s+(.{4})\\s+\\p{XDigit}+\\s+.+\\s+\\d+\\s+(.*)");

    private ProcMapEntryParser() {
    }

    public static ProcMapEntry parse(String mapEntry) {
        Matcher m = MAP_REGEX.matcher(mapEntry);
        if (!m.matches()) {
            return null;
        }
        try {
            long address = Long.valueOf(m.group(1), 16).longValue();
            return new ProcMapEntry(address, Long.valueOf(m.group(2), 16).longValue() - address, m.group(3), m.group(4));
        } catch (Exception e) {
            Fabric.getLogger().mo34403d("CrashlyticsCore", "Could not parse map entry: " + mapEntry);
            return null;
        }
    }
}
