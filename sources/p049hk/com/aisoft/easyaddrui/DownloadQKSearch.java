package p049hk.com.aisoft.easyaddrui;

import android.os.AsyncTask;
import android.util.Base64;
import android.widget.Toast;
import com.facebook.stetho.common.Utf8Charset;
import com.mcdonalds.sdk.connectors.middleware.model.DCSProfile;
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

/* renamed from: hk.com.aisoft.easyaddrui.DownloadQKSearch */
public class DownloadQKSearch extends AsyncTask<String, Void, String> implements TraceFieldInterface {
    protected static String sBundle = "";
    protected static String sCity = "";
    protected static String sKey = "";
    protected static String sPage = "";
    public Trace _nr_trace;

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    protected DownloadQKSearch(String mCity, String mKey, String mPage) {
        sCity = mCity;
        sPage = mPage;
        sKey = mKey;
        sBundle = eaView.sEABundle;
    }

    /* Access modifiers changed, original: protected */
    public void onPreExecute() {
        super.onPreExecute();
    }

    /* Access modifiers changed, original: protected|varargs */
    public String doInBackground(String... urls) {
        String sPrefix = eaView.random(5);
        String sPayload = sPrefix + "|" + sBundle + "|" + eaView.random(5);
        String sPayloadEn = "";
        try {
            sPayloadEn = Base64.encodeToString(new AES256JNCryptor().encryptData(sPayload.getBytes(Utf8Charset.NAME), eaView.sSysHash.toCharArray()), 0);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
            }
        }
        try {
            return downloadUrl(urls[0] + "?l=" + URLEncoder.encode(eaView.sEALang, Utf8Charset.NAME) + "&t=AUTO&p=" + URLEncoder.encode(sPage, Utf8Charset.NAME) + "&q=" + URLEncoder.encode(sKey, Utf8Charset.NAME) + "&c=" + URLEncoder.encode(sCity, Utf8Charset.NAME) + "&ac=" + URLEncoder.encode(sCity, Utf8Charset.NAME) + "&ae=&dc=&de=&sc=&se=&ssc=&sse=&sl=&sno1=&sno2=&sno3=&sno4=&estc=&este=&pn=&pnc=&pne=&bc=&be=&bkc=&bke=&ip=&cc=" + sPayloadEn.replace("\n", ""));
        } catch (IOException e2) {
            return "ERR";
        }
    }

    /* Access modifiers changed, original: protected */
    public void onPostExecute(String sResult) {
        try {
            if (sResult.equals("ERR")) {
                eaView.sResponse = sResult;
                if (eaView.sEALang.equals("zh-HK")) {
                    Toast.makeText(eaView.sContext, eaView.lbNetworkErrorC, 0).show();
                    return;
                } else {
                    Toast.makeText(eaView.sContext, eaView.lbNetworkErrorE, 0).show();
                    return;
                }
            }
            String sRespAddr = "";
            sRespAddr = sResult.substring(sResult.indexOf("\"instr\":\"") + "\"instr\":\"".length());
            if (!sRespAddr.substring(0, sRespAddr.indexOf("\"")).equals(eaView.txtEAKeyword.getText().toString()) || eaView.sCurrDau.equals(DCSProfile.INDICATOR_TRUE)) {
                eaView.sResponse = "SEARCH RESULT BUT KEY NOT SAME/SELECTED";
                return;
            }
            sResult = sResult.substring(sResult.indexOf("\"content\":\"") + "\"content\":\"".length());
            sResult = sResult.substring(0, sResult.indexOf("\""));
            int sPosition = eaView.listResult.getCount();
            if (!eaView.sReqBack) {
                eaView.sResponseQK = DownloadQKSearch.getResponseQK(sResult);
                eaView.sReqBack = true;
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
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

    /* JADX WARNING: Missing block: B:8:0x0022, code skipped:
            r5 = r6;
     */
    /* JADX WARNING: Missing block: B:9:0x0023, code skipped:
            r1 = r4.next();
     */
    protected static p049hk.com.aisoft.easyaddrui.ResponseQK getResponseQK(java.lang.String r13) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        r4 = android.util.Xml.newPullParser();
        r7 = 0;
        r5 = 0;
        r7 = new hk.com.aisoft.easyaddrui.ResponseQK;
        r7.<init>();
        r8 = new java.io.StringReader;	 Catch:{ Exception -> 0x0354 }
        r8.<init>(r13);	 Catch:{ Exception -> 0x0354 }
        r4.setInput(r8);	 Catch:{ Exception -> 0x0354 }
        r1 = r4.getEventType();	 Catch:{ Exception -> 0x0354 }
        r0 = 0;
        r6 = r5;
    L_0x0019:
        r8 = 1;
        if (r1 == r8) goto L_0x0352;
    L_0x001c:
        if (r0 != 0) goto L_0x0352;
    L_0x001e:
        r3 = 0;
        switch(r1) {
            case 0: goto L_0x0029;
            case 1: goto L_0x0022;
            case 2: goto L_0x002b;
            case 3: goto L_0x02f1;
            default: goto L_0x0022;
        };	 Catch:{ Exception -> 0x0354 }
    L_0x0022:
        r5 = r6;
    L_0x0023:
        r1 = r4.next();	 Catch:{ Exception -> 0x0354 }
        r6 = r5;
        goto L_0x0019;
    L_0x0029:
        r5 = r6;
        goto L_0x0023;
    L_0x002b:
        r3 = r4.getName();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r8 = "p";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x0058;
    L_0x0037:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r7.sPage = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r8 = r7.sPage;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r9 = "1";
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x0050;
    L_0x0047:
        r8 = new java.util.ArrayList;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r8.<init>();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r7.sResponseAddrs = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x0050:
        r8 = p049hk.com.aisoft.easyaddrui.eaView.sResponseQK;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r8 = r8.sResponseAddrs;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r7.sResponseAddrs = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x0058:
        r8 = "t";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x0068;
    L_0x0060:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r7.sType = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x0068:
        r8 = "result";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x0076;
    L_0x0070:
        r5 = new hk.com.aisoft.easyaddrui.ResponseAddr;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5.<init>();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        goto L_0x0023;
    L_0x0076:
        if (r6 == 0) goto L_0x0022;
    L_0x0078:
        r8 = "o";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x0088;
    L_0x0080:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sAddr = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x0088:
        r8 = "s";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x00a0;
    L_0x0090:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r8 = java.lang.Double.parseDouble(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r8 = java.lang.Double.valueOf(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sScore = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x00a0:
        r8 = "c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x00b1;
    L_0x00a8:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sCity = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x00b1:
        r8 = "addr1c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x00c2;
    L_0x00b9:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sAreaC = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x00c2:
        r8 = "addr1e";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x00d3;
    L_0x00ca:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sAreaE = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x00d3:
        r8 = "addr2c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x00e4;
    L_0x00db:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sDistrictC = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x00e4:
        r8 = "addr2e";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x00f5;
    L_0x00ec:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sDistrictE = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x00f5:
        r8 = "addr3c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x0106;
    L_0x00fd:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sStreetC = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x0106:
        r8 = "addr3e";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x0117;
    L_0x010e:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sStreetE = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x0117:
        r8 = "addr4c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x0122;
    L_0x011f:
        r5 = r6;
        goto L_0x0023;
    L_0x0122:
        r8 = "addr4e";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x012d;
    L_0x012a:
        r5 = r6;
        goto L_0x0023;
    L_0x012d:
        r8 = "addr5";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x013e;
    L_0x0135:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sStreetLon = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x013e:
        r8 = "addr6";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x014f;
    L_0x0146:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sStreetFromNo = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x014f:
        r8 = "addr7";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x0160;
    L_0x0157:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sStreetFromCode = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x0160:
        r8 = "addr8";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x0171;
    L_0x0168:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sStreetToNo = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x0171:
        r8 = "addr9";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x0182;
    L_0x0179:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sStreetToCode = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x0182:
        r8 = "addr10c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x0193;
    L_0x018a:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sEstateC = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x0193:
        r8 = "addr10e";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x01a4;
    L_0x019b:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sEstateE = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x01a4:
        r8 = "addr11";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x01b5;
    L_0x01ac:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sPhaseNo = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x01b5:
        r8 = "addr12c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x01c6;
    L_0x01bd:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sPhaseNameC = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x01c6:
        r8 = "addr12e";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x01d7;
    L_0x01ce:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sPhaseNameE = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x01d7:
        r8 = "addr13c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x01e8;
    L_0x01df:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sBldgC = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x01e8:
        r8 = "addr13e";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x01f9;
    L_0x01f0:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sBldgE = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x01f9:
        r8 = "addr14c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x020a;
    L_0x0201:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sBlockC = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x020a:
        r8 = "addr14e";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x021b;
    L_0x0212:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sBlockE = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x021b:
        r8 = "addr15c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x022c;
    L_0x0223:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sAddrRmkC = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x022c:
        r8 = "addr15e";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x023d;
    L_0x0234:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sAddrRmkE = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x023d:
        r8 = "hub1a";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x024e;
    L_0x0245:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sHub1 = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x024e:
        r8 = "hub1b";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x0259;
    L_0x0256:
        r5 = r6;
        goto L_0x0023;
    L_0x0259:
        r8 = "hub2a";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x026a;
    L_0x0261:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sHub2 = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x026a:
        r8 = "hub2b";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x0275;
    L_0x0272:
        r5 = r6;
        goto L_0x0023;
    L_0x0275:
        r8 = "hub3a";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x0286;
    L_0x027d:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sHub3 = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x0286:
        r8 = "hub3b";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x0291;
    L_0x028e:
        r5 = r6;
        goto L_0x0023;
    L_0x0291:
        r8 = "hub4a";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x02a2;
    L_0x0299:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sHub4 = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x02a2:
        r8 = "hub4b";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x02ad;
    L_0x02aa:
        r5 = r6;
        goto L_0x0023;
    L_0x02ad:
        r8 = "addrc";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x02be;
    L_0x02b5:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sAddrC = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x02be:
        r8 = "addre";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x02cf;
    L_0x02c6:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sAddrE = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x02cf:
        r8 = "ubi";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x02e0;
    L_0x02d7:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sUBI = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x02e0:
        r8 = "lot";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x0022;
    L_0x02e8:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sLot = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x02f1:
        r3 = r4.getName();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r8 = "result";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x0346;
    L_0x02fd:
        if (r6 == 0) goto L_0x0346;
    L_0x02ff:
        r8 = r6.sAddr;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r9 = "@NEXT@";
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 != 0) goto L_0x0022;
    L_0x0309:
        r8 = r6.sAddr;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r8 = r8.length();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r8 = (double) r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r10 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r10.<init>();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r11 = r6.sAddr;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r12 = sKey;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r11 = p049hk.com.aisoft.easyaddrui.eaView.LevenshteinDistance(r11, r12);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r10 = r10.append(r11);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r11 = "";
        r10 = r10.append(r11);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r10 = r10.toString();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r10 = java.lang.Double.parseDouble(r10);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r8 = r8 - r10;
        r10 = r6.sAddr;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r10 = r10.length();	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r10 = (double) r10;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r8 = r8 / r10;
        r8 = java.lang.Double.valueOf(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r6.sScore = r8;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r8 = r7.sResponseAddrs;	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r8.add(r6);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        r5 = r6;
        goto L_0x0023;
    L_0x0346:
        r8 = "content";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x035f, all -> 0x035c }
        if (r8 == 0) goto L_0x0022;
    L_0x034e:
        r0 = 1;
        r5 = r6;
        goto L_0x0023;
    L_0x0352:
        r5 = r6;
    L_0x0353:
        return r7;
    L_0x0354:
        r2 = move-exception;
    L_0x0355:
        r8 = p049hk.com.aisoft.easyaddrui.BuildConfig.DEBUG;	 Catch:{ all -> 0x035a }
        if (r8 == 0) goto L_0x0353;
    L_0x0359:
        goto L_0x0353;
    L_0x035a:
        r8 = move-exception;
    L_0x035b:
        throw r8;
    L_0x035c:
        r8 = move-exception;
        r5 = r6;
        goto L_0x035b;
    L_0x035f:
        r2 = move-exception;
        r5 = r6;
        goto L_0x0355;
        */
        throw new UnsupportedOperationException("Method not decompiled: p049hk.com.aisoft.easyaddrui.DownloadQKSearch.getResponseQK(java.lang.String):hk.com.aisoft.easyaddrui.ResponseQK");
    }
}
