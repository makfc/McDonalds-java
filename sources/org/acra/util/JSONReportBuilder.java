package org.acra.util;

import com.facebook.internal.ServerProtocol;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Locale;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.collector.CollectorUtil;
import org.acra.collector.CrashReportData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONReportBuilder {

    public static class JSONReportException extends Exception {
        public JSONReportException(String message, Throwable e) {
            super(message, e);
        }
    }

    public static JSONObject buildJSONReport(CrashReportData errorContent) throws JSONReportException {
        Throwable e;
        Throwable th;
        JSONObject jSONObject = new JSONObject();
        Reader reader = null;
        Iterator it = errorContent.keySet().iterator();
        while (true) {
            Reader reader2 = reader;
            if (!it.hasNext()) {
                return jSONObject;
            }
            ReportField reportField = (ReportField) it.next();
            Reader bufferedReader;
            try {
                if (reportField.containsKeyValuePairs()) {
                    JSONObject jSONObject2 = new JSONObject();
                    bufferedReader = new BufferedReader(new StringReader(errorContent.getProperty(reportField)), 1024);
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            addJSONFromProperty(jSONObject2, readLine);
                        } catch (IOException e2) {
                            try {
                                ACRA.log.mo23350e(ACRA.LOG_TAG, "Error while converting " + reportField.name() + " to JSON.", e2);
                            } catch (JSONException e3) {
                                e = e3;
                                try {
                                    throw new JSONReportException("Could not create JSON object for key " + reportField, e);
                                } catch (Throwable th2) {
                                    th = th2;
                                    CollectorUtil.safeClose(bufferedReader);
                                    throw th;
                                }
                            }
                        }
                    }
                    jSONObject.accumulate(reportField.name(), jSONObject2);
                    reader = bufferedReader;
                } else {
                    jSONObject.accumulate(reportField.name(), guessType(errorContent.getProperty(reportField)));
                    reader = reader2;
                }
                CollectorUtil.safeClose(reader);
            } catch (JSONException e4) {
                Throwable th3 = e4;
                bufferedReader = reader2;
                e = th3;
                throw new JSONReportException("Could not create JSON object for key " + reportField, e);
            } catch (Throwable th4) {
                th = th4;
                bufferedReader = reader2;
                CollectorUtil.safeClose(bufferedReader);
                throw th;
            }
        }
    }

    private static void addJSONFromProperty(JSONObject destination, String propertyString) throws JSONException {
        int indexOf = propertyString.indexOf(61);
        if (indexOf > 0) {
            String trim = propertyString.substring(0, indexOf).trim();
            Object guessType = guessType(propertyString.substring(indexOf + 1).trim());
            if (guessType instanceof String) {
                guessType = ((String) guessType).replaceAll("\\\\n", "\n");
            }
            String[] split = trim.split("\\.");
            if (split.length > 1) {
                addJSONSubTree(destination, split, guessType);
                return;
            } else {
                destination.accumulate(trim, guessType);
                return;
            }
        }
        destination.put(propertyString.trim(), true);
    }

    private static Object guessType(String value) {
        if (value.equalsIgnoreCase(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
            return Boolean.valueOf(true);
        }
        if (value.equalsIgnoreCase("false")) {
            return Boolean.valueOf(false);
        }
        if (!value.matches("(?:^|\\s)([1-9](?:\\d*|(?:\\d{0,2})(?:,\\d{3})*)(?:\\.\\d*[1-9])?|0?\\.\\d*[1-9]|0)(?:\\s|$)")) {
            return value;
        }
        try {
            return NumberFormat.getInstance(Locale.US).parse(value);
        } catch (ParseException e) {
            return value;
        }
    }

    private static void addJSONSubTree(JSONObject destination, String[] keys, Object value) throws JSONException {
        for (int i = 0; i < keys.length; i++) {
            String str = keys[i];
            if (i >= keys.length - 1) {
                destination.accumulate(str, value);
            } else if (destination.isNull(str)) {
                JSONObject jSONObject = new JSONObject();
                destination.accumulate(str, jSONObject);
                destination = jSONObject;
            } else {
                Object obj = destination.get(str);
                if (obj instanceof JSONObject) {
                    destination = destination.getJSONObject(str);
                } else if (obj instanceof JSONArray) {
                    JSONArray jSONArray = destination.getJSONArray(str);
                    JSONObject jSONObject2 = null;
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        jSONObject2 = jSONArray.optJSONObject(i2);
                        if (jSONObject2 != null) {
                            break;
                        }
                    }
                    destination = jSONObject2;
                } else {
                    destination = null;
                }
                if (destination == null) {
                    ACRA.log.mo23349e(ACRA.LOG_TAG, "Unknown json subtree type, see issue #186");
                    return;
                }
            }
        }
    }
}
