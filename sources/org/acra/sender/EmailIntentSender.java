package org.acra.sender;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import org.acra.ACRA;
import org.acra.ACRAConstants;
import org.acra.ReportField;
import org.acra.collector.CrashReportData;

public class EmailIntentSender implements ReportSender {
    private final Context mContext;

    public EmailIntentSender(Context ctx) {
        this.mContext = ctx;
    }

    public void send(Context context, CrashReportData errorContent) throws ReportSenderException {
        String str = this.mContext.getPackageName() + " Crash Report";
        String buildBody = buildBody(errorContent);
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.fromParts("mailto", ACRA.getConfig().mailTo(), null));
        intent.addFlags(268435456);
        intent.putExtra("android.intent.extra.SUBJECT", str);
        intent.putExtra("android.intent.extra.TEXT", buildBody);
        this.mContext.startActivity(intent);
    }

    private String buildBody(CrashReportData errorContent) {
        ReportField[] customReportContent = ACRA.getConfig().customReportContent();
        ReportField[] reportFieldArr;
        if (customReportContent.length == 0) {
            reportFieldArr = ACRAConstants.DEFAULT_MAIL_REPORT_FIELDS;
        } else {
            reportFieldArr = customReportContent;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (ReportField reportField : reportFieldArr) {
            stringBuilder.append(reportField.toString()).append("=");
            stringBuilder.append((String) errorContent.get(reportField));
            stringBuilder.append(10);
        }
        return stringBuilder.toString();
    }
}
