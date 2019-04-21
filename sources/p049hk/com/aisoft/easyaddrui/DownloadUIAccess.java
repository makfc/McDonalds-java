package p049hk.com.aisoft.easyaddrui;

import android.os.AsyncTask;
import android.util.Base64;
import android.widget.Toast;
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

/* renamed from: hk.com.aisoft.easyaddrui.DownloadUIAccess */
public class DownloadUIAccess extends AsyncTask<String, Void, String> implements TraceFieldInterface {
    public Trace _nr_trace;
    String sBundle = "";
    String sKey = "";

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    protected DownloadUIAccess(String mKey) {
        this.sKey = mKey;
        this.sBundle = eaView.sEABundle;
    }

    /* Access modifiers changed, original: protected */
    public void onPreExecute() {
        super.onPreExecute();
    }

    /* Access modifiers changed, original: protected|varargs */
    public String doInBackground(String... urls) {
        String sPrefix = eaView.random(5);
        String sSuffix = eaView.random(5);
        String sCode = "";
        if (this.sKey.equals("dSsLgJsdKr5K9nAsLf8f")) {
            sCode = "48926";
        } else {
            sCode = "95448";
        }
        String sPayload = sPrefix + "|" + sCode + "|" + this.sBundle + "|" + eaView.VERSION + "|" + sSuffix;
        String sPayloadEn = "";
        try {
            sPayloadEn = Base64.encodeToString(new AES256JNCryptor().encryptData(sPayload.getBytes(Utf8Charset.NAME), eaView.sSysHash.toCharArray()), 0);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
            }
        }
        try {
            return downloadUrl(urls[0] + "?l=" + URLEncoder.encode(eaView.sEALang, Utf8Charset.NAME) + "&cc=" + sPayloadEn.replace("\n", ""));
        } catch (IOException e2) {
            return "ERR";
        }
    }

    /* Access modifiers changed, original: protected */
    public void onPostExecute(String sResult) {
        if (sResult.equals("ERR")) {
            eaView.eaNetErr = true;
            if (eaView.sEALang.equals("zh-HK")) {
                Toast.makeText(eaView.sContext, eaView.lbNetworkErrorC, 0).show();
            } else {
                Toast.makeText(eaView.sContext, eaView.lbNetworkErrorE, 0).show();
            }
        } else if (!sResult.equals("")) {
            try {
                eaView.sEAAutoSearch = Double.valueOf(Double.parseDouble(sResult.substring(3, 4)));
                eaView.sEAChkResult = Double.valueOf(Double.parseDouble(sResult.substring(4, 5)));
            } catch (Exception e) {
                if (BuildConfig.DEBUG) {
                }
            }
        }
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
