package com.ensighten;

import android.content.Context;
import android.os.Looper;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.net.URLEncoder;
import org.acra.ACRA;
import org.acra.ACRAConstants;
import org.acra.ReportField;
import org.acra.collector.CrashReportData;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;
import org.json.JSONObject;

/* renamed from: com.ensighten.n */
public final class C1852n implements ReportSender {
    public final void send(Context context, CrashReportData crashReportData) throws ReportSenderException {
        try {
            String jSONObjectInstrumentation;
            JSONObject a = C1852n.m7410a(crashReportData);
            Ensighten.callJSErrorHandler(a);
            String str = "ensightenError";
            if (a instanceof JSONObject) {
                jSONObjectInstrumentation = JSONObjectInstrumentation.toString(a);
            } else {
                jSONObjectInstrumentation = a.toString();
            }
            Ensighten.saveStringToSharedPrefs(str, jSONObjectInstrumentation);
            Object exceptionManager = Ensighten.getExceptionManager();
            String convertJSONObjectToJavascriptCompatibleString = Utils.convertJSONObjectToJavascriptCompatibleString(a);
            Ensighten.getEventManager().mo15499a(exceptionManager, "onCrash", new Object[]{convertJSONObjectToJavascriptCompatibleString}, Looper.getMainLooper().getThread() == Thread.currentThread());
        } catch (Exception e) {
            if (C1845i.m7348a()) {
                C1845i.m7353c(e);
            }
        }
    }

    /* renamed from: a */
    private static JSONObject m7410a(CrashReportData crashReportData) {
        ReportField[] customReportContent = ACRA.getConfig().customReportContent();
        ReportField[] reportFieldArr;
        if (customReportContent.length == 0) {
            reportFieldArr = ACRAConstants.DEFAULT_MAIL_REPORT_FIELDS;
        } else {
            reportFieldArr = customReportContent;
        }
        JSONObject jSONObject = new JSONObject();
        for (Object obj : reportFieldArr) {
            try {
                Object obj2 = crashReportData.get(obj);
                jSONObject.put(obj.toString(), obj2 != null ? URLEncoder.encode((String) obj2) : "");
            } catch (Exception e) {
                if (C1845i.m7348a()) {
                    C1845i.m7353c(e);
                }
            }
        }
        return jSONObject;
    }
}
