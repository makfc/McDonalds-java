package org.acra.collector;

import java.util.EnumMap;
import org.acra.ReportField;
import org.acra.util.JSONReportBuilder;
import org.acra.util.JSONReportBuilder.JSONReportException;
import org.json.JSONObject;

public final class CrashReportData extends EnumMap<ReportField, String> {
    public CrashReportData() {
        super(ReportField.class);
    }

    public final String getProperty(ReportField key) {
        return (String) super.get(key);
    }

    public final JSONObject toJSON() throws JSONReportException {
        return JSONReportBuilder.buildJSONReport(this);
    }
}
