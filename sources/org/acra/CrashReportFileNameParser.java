package org.acra;

final class CrashReportFileNameParser {
    CrashReportFileNameParser() {
    }

    public final boolean isSilent(String reportFileName) {
        return reportFileName.contains(ACRAConstants.SILENT_SUFFIX);
    }

    public final boolean isApproved(String reportFileName) {
        return isSilent(reportFileName) || reportFileName.contains("-approved");
    }
}
