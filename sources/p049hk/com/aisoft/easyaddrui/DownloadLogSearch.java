package p049hk.com.aisoft.easyaddrui;

import android.os.AsyncTask;
import android.util.Base64;
import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/* renamed from: hk.com.aisoft.easyaddrui.DownloadLogSearch */
public class DownloadLogSearch extends AsyncTask<String, Void, String> implements TraceFieldInterface {
    protected static String sBundle = "";
    protected static String sCity = "";
    protected static String sGPSX = "";
    protected static String sGPSY = "";
    protected static String sKey = "";
    public Trace _nr_trace;

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    /* Access modifiers changed, original: protected */
    public void onPreExecute() {
        super.onPreExecute();
    }

    /* Access modifiers changed, original: protected|varargs */
    public String doInBackground(String... urls) {
        String sPrefix = eaView.random(5);
        String sPayload = sPrefix + "|" + sBundle + "|" + sGPSX + "|" + sGPSY + "|" + eaView.random(5);
        String sPayloadEn = "";
        try {
            sPayloadEn = Base64.encodeToString(new AES256JNCryptor().encryptData(sPayload.getBytes(Utf8Charset.NAME), eaView.sSysHash.toCharArray()), 0);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
            }
        }
        try {
            return downloadUrl(urls[0] + "?keywords=" + URLEncoder.encode(sKey, Utf8Charset.NAME) + "&city=" + URLEncoder.encode(sCity, Utf8Charset.NAME) + "&cc=" + sPayloadEn.replace("\n", ""));
        } catch (IOException e2) {
            return "ERR";
        }
    }

    /* Access modifiers changed, original: protected */
    public void onPostExecute(String sResult) {
    }

    private String downloadUrl(String sUrl) throws IOException {
        InputStream is = null;
        String sData = "";
        try {
            HttpURLConnection conn = (HttpURLConnection) HttpInstrumentation.openConnection(new URL(sUrl).openConnection());
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            StringBuilder total = new StringBuilder();
            while (true) {
                String line = r.readLine();
                if (line == null) {
                    break;
                }
                total.append(line);
            }
            sData = total.toString();
            return sData;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}
