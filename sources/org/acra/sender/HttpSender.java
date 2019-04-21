package org.acra.sender;

import android.content.Context;
import android.net.Uri;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.acra.ACRA;
import org.acra.ACRAConfiguration;
import org.acra.ACRAConstants;
import org.acra.ReportField;
import org.acra.collector.CrashReportData;
import org.acra.util.HttpRequest;
import org.acra.util.JSONReportBuilder.JSONReportException;
import org.json.JSONObject;

public class HttpSender implements ReportSender {
    private final Uri mFormUri = null;
    private final Map<ReportField, String> mMapping;
    private final Method mMethod;
    private String mPassword;
    private final Type mType;
    private String mUsername;

    public enum Method {
        POST,
        PUT
    }

    public enum Type {
        FORM {
            public final String getContentType() {
                return "application/x-www-form-urlencoded";
            }
        },
        JSON {
            public final String getContentType() {
                return "application/json";
            }
        };

        public abstract String getContentType();
    }

    public HttpSender(Method method, Type type, Map<ReportField, String> mapping) {
        this.mMethod = method;
        this.mMapping = mapping;
        this.mType = type;
        this.mUsername = null;
        this.mPassword = null;
    }

    public void send(Context context, CrashReportData report) throws ReportSenderException {
        String str = null;
        try {
            URL url;
            URL url2;
            if (this.mFormUri == null) {
                url = new URL(ACRA.getConfig().formUri());
            } else {
                url = new URL(this.mFormUri.toString());
            }
            ACRA.log.mo23347d(ACRA.LOG_TAG, "Connect to " + url.toString());
            String formUriBasicAuthLogin = this.mUsername != null ? this.mUsername : ACRAConfiguration.isNull(ACRA.getConfig().formUriBasicAuthLogin()) ? null : ACRA.getConfig().formUriBasicAuthLogin();
            if (this.mPassword != null) {
                str = this.mPassword;
            } else if (!ACRAConfiguration.isNull(ACRA.getConfig().formUriBasicAuthPassword())) {
                str = ACRA.getConfig().formUriBasicAuthPassword();
            }
            HttpRequest httpRequest = new HttpRequest();
            httpRequest.setConnectionTimeOut(ACRA.getConfig().connectionTimeout());
            httpRequest.setSocketTimeOut(ACRA.getConfig().socketTimeout());
            httpRequest.setLogin(formUriBasicAuthLogin);
            httpRequest.setPassword(str);
            httpRequest.setHeaders(ACRA.getConfig().getHttpHeaders());
            switch (this.mType) {
                case JSON:
                    JSONObject toJSON = report.toJSON();
                    if (toJSON instanceof JSONObject) {
                        str = JSONObjectInstrumentation.toString(toJSON);
                    } else {
                        str = toJSON.toString();
                    }
                    formUriBasicAuthLogin = str;
                    break;
                default:
                    formUriBasicAuthLogin = HttpRequest.getParamsAsFormString(remap(report));
                    break;
            }
            switch (this.mMethod) {
                case POST:
                    url2 = url;
                    break;
                case PUT:
                    url2 = new URL(url.toString() + '/' + report.getProperty(ReportField.REPORT_ID));
                    break;
                default:
                    throw new UnsupportedOperationException("Unknown method: " + this.mMethod.name());
            }
            httpRequest.send(url2, this.mMethod, formUriBasicAuthLogin, this.mType);
        } catch (IOException e) {
            throw new ReportSenderException("Error while sending " + ACRA.getConfig().reportType() + " report via Http " + this.mMethod.name(), e);
        } catch (JSONReportException e2) {
            throw new ReportSenderException("Error while sending " + ACRA.getConfig().reportType() + " report via Http " + this.mMethod.name(), e2);
        }
    }

    private Map<String, String> remap(Map<ReportField, String> report) {
        ReportField[] customReportContent = ACRA.getConfig().customReportContent();
        if (customReportContent.length == 0) {
            customReportContent = ACRAConstants.DEFAULT_REPORT_FIELDS;
        }
        HashMap hashMap = new HashMap(report.size());
        for (Object obj : customReportContent) {
            if (this.mMapping == null || this.mMapping.get(obj) == null) {
                hashMap.put(obj.toString(), report.get(obj));
            } else {
                hashMap.put(this.mMapping.get(obj), report.get(obj));
            }
        }
        return hashMap;
    }
}
