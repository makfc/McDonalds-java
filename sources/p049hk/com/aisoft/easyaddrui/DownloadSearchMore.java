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

/* renamed from: hk.com.aisoft.easyaddrui.DownloadSearchMore */
public class DownloadSearchMore extends AsyncTask<String, Void, String> implements TraceFieldInterface {
    public Trace _nr_trace;
    String sAreaC = "";
    String sAreaE = "";
    String sBldgC = "";
    String sBldgE = "";
    String sBlockC = "";
    String sBlockE = "";
    String sBundle = "";
    String sCity = "";
    String sDistrictC = "";
    String sDistrictE = "";
    String sEstateC = "";
    String sEstateE = "";
    String sPage = "";
    String sPhaseNameC = "";
    String sPhaseNameE = "";
    String sPhaseNo = "";
    String sStreetC = "";
    String sStreetE = "";
    String sStreetFromCode = "";
    String sStreetFromNo = "";
    String sStreetLon = "";
    String sStreetToCode = "";
    String sStreetToNo = "";
    String sType = "";

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    protected DownloadSearchMore(String mType, String mPage, String mCity, String mAreaC, String mAreaE, String mDistrictC, String mDistrictE, String mStreetC, String mStreetE, String mStreetLon, String mStreetFromNo, String mStreetFromCode, String mStreetToNo, String mStreetToCode, String mEstateC, String mEstateE, String mPhaseNo, String mPhaseNameC, String mPhaseNameE, String mBldgC, String mBldgE, String mBlockC, String mBlockE) {
        this.sType = mType;
        this.sPage = mPage;
        this.sCity = mCity;
        this.sAreaC = mAreaC;
        this.sAreaE = mAreaE;
        this.sDistrictC = mDistrictC;
        this.sDistrictE = mDistrictE;
        this.sStreetC = mStreetC;
        this.sStreetE = mStreetE;
        this.sStreetLon = mStreetLon;
        this.sStreetFromNo = mStreetFromNo;
        this.sStreetFromCode = mStreetFromCode;
        this.sStreetToNo = mStreetToNo;
        this.sStreetToCode = mStreetToCode;
        this.sEstateC = mEstateC;
        this.sEstateE = mEstateE;
        this.sPhaseNo = mPhaseNo;
        this.sPhaseNameC = mPhaseNameC;
        this.sPhaseNameE = mPhaseNameE;
        this.sBldgC = mBldgC;
        this.sBldgE = mBldgE;
        this.sBlockC = mBlockC;
        this.sBlockE = mBlockE;
        this.sBundle = eaView.sEABundle;
    }

    /* Access modifiers changed, original: protected */
    public void onPreExecute() {
        super.onPreExecute();
    }

    /* Access modifiers changed, original: protected|varargs */
    public String doInBackground(String... urls) {
        String sPrefix = eaView.random(5);
        String sPayload = sPrefix + "|" + this.sBundle + "|" + eaView.random(5);
        String sPayloadEn = "";
        try {
            sPayloadEn = Base64.encodeToString(new AES256JNCryptor().encryptData(sPayload.getBytes(Utf8Charset.NAME), eaView.sSysHash.toCharArray()), 0);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
            }
        }
        try {
            return downloadUrl(urls[0] + "?l=" + URLEncoder.encode(eaView.sEALang, Utf8Charset.NAME) + "&=&t=B&p=" + URLEncoder.encode(this.sPage, Utf8Charset.NAME) + "&c=" + URLEncoder.encode(eaView.sEACity, Utf8Charset.NAME) + "&ac=" + URLEncoder.encode(this.sAreaC, Utf8Charset.NAME) + "&ae=" + URLEncoder.encode(this.sAreaE, Utf8Charset.NAME).replaceAll("\\+", "%20") + "&dc=" + URLEncoder.encode(this.sDistrictC, Utf8Charset.NAME) + "&de=" + URLEncoder.encode(this.sDistrictE, Utf8Charset.NAME).replaceAll("\\+", "%20") + "&sc=" + URLEncoder.encode(this.sStreetC, Utf8Charset.NAME) + "&se=" + URLEncoder.encode(this.sStreetE, Utf8Charset.NAME).replaceAll("\\+", "%20") + "&ssc=" + "&sse=" + "&sl=" + URLEncoder.encode(this.sStreetLon, Utf8Charset.NAME) + "&sno1=" + URLEncoder.encode(this.sStreetFromNo, Utf8Charset.NAME) + "&sno2=" + URLEncoder.encode(this.sStreetFromCode, Utf8Charset.NAME) + "&sno3=" + URLEncoder.encode(this.sStreetToNo, Utf8Charset.NAME) + "&sno4=" + URLEncoder.encode(this.sStreetToCode, Utf8Charset.NAME) + "&estc=" + URLEncoder.encode(this.sEstateC, Utf8Charset.NAME) + "&este=" + URLEncoder.encode(this.sEstateE, Utf8Charset.NAME).replaceAll("\\+", "%20") + "&pn=" + URLEncoder.encode(this.sPhaseNo, Utf8Charset.NAME) + "&pnc=" + URLEncoder.encode(this.sPhaseNameC, Utf8Charset.NAME) + "&pne=" + URLEncoder.encode(this.sPhaseNameE, Utf8Charset.NAME) + "&bc=" + URLEncoder.encode(this.sBldgC, Utf8Charset.NAME) + "&be=" + URLEncoder.encode(this.sBldgE, Utf8Charset.NAME).replaceAll("\\+", "%20") + "&bkc=" + URLEncoder.encode(this.sBlockC, Utf8Charset.NAME) + "&bke=" + URLEncoder.encode(this.sBlockE, Utf8Charset.NAME).replaceAll("\\+", "%20") + "&cc=" + sPayloadEn.replace("\n", ""));
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
            sResult = sResult.substring(sResult.indexOf("\"content\":\"") + "\"content\":\"".length());
            sResult = sResult.substring(0, sResult.indexOf("\""));
            int sPosition = eaView.listResult.getCount();
            eaView.sResponseQK = DownloadSearchMore.getResponseQK(sResult);
            if (eaView.sResponseQK.sResponseAddrs.size() > 0) {
                eaView.listResult.setAdapter(new ResultAdapter(eaView.sContext, eaView.sResponseQK.sResponseAddrs));
                if (eaView.sResponseQK.sPage.equals("1")) {
                    eaView.listResult.setSelection(0);
                } else {
                    eaView.listResult.setSelection(sPosition - eaView.sVisibleCnt);
                }
                eaView.sResponse = "SEARCH MORE DONE";
                eaView.listResult.setVisibility(0);
                return;
            }
            eaView.sResponse = "SEARCH MORE FAILED";
            Toast.makeText(eaView.sContext, eaView.lbNoAddrFound, 0).show();
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
    protected static p049hk.com.aisoft.easyaddrui.ResponseQK getResponseQK(java.lang.String r10) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        r4 = android.util.Xml.newPullParser();
        r7 = 0;
        r5 = 0;
        r7 = new hk.com.aisoft.easyaddrui.ResponseQK;
        r7.<init>();
        r8 = new java.io.StringReader;	 Catch:{ Exception -> 0x0397 }
        r8.<init>(r10);	 Catch:{ Exception -> 0x0397 }
        r4.setInput(r8);	 Catch:{ Exception -> 0x0397 }
        r1 = r4.getEventType();	 Catch:{ Exception -> 0x0397 }
        r0 = 0;
        r6 = r5;
    L_0x0019:
        r8 = 1;
        if (r1 == r8) goto L_0x0395;
    L_0x001c:
        if (r0 != 0) goto L_0x0395;
    L_0x001e:
        r3 = 0;
        switch(r1) {
            case 0: goto L_0x0029;
            case 1: goto L_0x0022;
            case 2: goto L_0x002b;
            case 3: goto L_0x02e2;
            default: goto L_0x0022;
        };	 Catch:{ Exception -> 0x0397 }
    L_0x0022:
        r5 = r6;
    L_0x0023:
        r1 = r4.next();	 Catch:{ Exception -> 0x0397 }
        r6 = r5;
        goto L_0x0019;
    L_0x0029:
        r5 = r6;
        goto L_0x0023;
    L_0x002b:
        r3 = r4.getName();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r8 = "p";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0058;
    L_0x0037:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r7.sPage = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r8 = r7.sPage;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r9 = "1";
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0050;
    L_0x0047:
        r8 = new java.util.ArrayList;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r8.<init>();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r7.sResponseAddrs = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x0050:
        r8 = p049hk.com.aisoft.easyaddrui.eaView.sResponseQK;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r8 = r8.sResponseAddrs;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r7.sResponseAddrs = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x0058:
        r8 = "t";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0068;
    L_0x0060:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r7.sType = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x0068:
        r8 = "result";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0076;
    L_0x0070:
        r5 = new hk.com.aisoft.easyaddrui.ResponseAddr;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5.<init>();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        goto L_0x0023;
    L_0x0076:
        if (r6 == 0) goto L_0x0022;
    L_0x0078:
        r8 = "o";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0088;
    L_0x0080:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sAddr = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x0088:
        r8 = "s";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0092;
    L_0x0090:
        r5 = r6;
        goto L_0x0023;
    L_0x0092:
        r8 = "c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x00a2;
    L_0x009a:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sCity = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x00a2:
        r8 = "addr1c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x00b3;
    L_0x00aa:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sAreaC = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x00b3:
        r8 = "addr1e";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x00c4;
    L_0x00bb:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sAreaE = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x00c4:
        r8 = "addr2c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x00d5;
    L_0x00cc:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sDistrictC = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x00d5:
        r8 = "addr2e";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x00e6;
    L_0x00dd:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sDistrictE = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x00e6:
        r8 = "addr3c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x00f7;
    L_0x00ee:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sStreetC = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x00f7:
        r8 = "addr3e";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0108;
    L_0x00ff:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sStreetE = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x0108:
        r8 = "addr4c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0113;
    L_0x0110:
        r5 = r6;
        goto L_0x0023;
    L_0x0113:
        r8 = "addr4e";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x011e;
    L_0x011b:
        r5 = r6;
        goto L_0x0023;
    L_0x011e:
        r8 = "addr5";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x012f;
    L_0x0126:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sStreetLon = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x012f:
        r8 = "addr6";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0140;
    L_0x0137:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sStreetFromNo = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x0140:
        r8 = "addr7";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0151;
    L_0x0148:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sStreetFromCode = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x0151:
        r8 = "addr8";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0162;
    L_0x0159:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sStreetToNo = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x0162:
        r8 = "addr9";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0173;
    L_0x016a:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sStreetToCode = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x0173:
        r8 = "addr10c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0184;
    L_0x017b:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sEstateC = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x0184:
        r8 = "addr10e";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0195;
    L_0x018c:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sEstateE = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x0195:
        r8 = "addr11";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x01a6;
    L_0x019d:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sPhaseNo = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x01a6:
        r8 = "addr12c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x01b7;
    L_0x01ae:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sPhaseNameC = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x01b7:
        r8 = "addr12e";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x01c8;
    L_0x01bf:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sPhaseNameE = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x01c8:
        r8 = "addr13c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x01d9;
    L_0x01d0:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sBldgC = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x01d9:
        r8 = "addr13e";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x01ea;
    L_0x01e1:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sBldgE = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x01ea:
        r8 = "addr14c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x01fb;
    L_0x01f2:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sBlockC = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x01fb:
        r8 = "addr14e";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x020c;
    L_0x0203:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sBlockE = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x020c:
        r8 = "addr15c";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x021d;
    L_0x0214:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sAddrRmkC = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x021d:
        r8 = "addr15e";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x022e;
    L_0x0225:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sAddrRmkE = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x022e:
        r8 = "hub1a";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x023f;
    L_0x0236:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sHub1 = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x023f:
        r8 = "hub1b";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x024a;
    L_0x0247:
        r5 = r6;
        goto L_0x0023;
    L_0x024a:
        r8 = "hub2a";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x025b;
    L_0x0252:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sHub2 = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x025b:
        r8 = "hub2b";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0266;
    L_0x0263:
        r5 = r6;
        goto L_0x0023;
    L_0x0266:
        r8 = "hub3a";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0277;
    L_0x026e:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sHub3 = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x0277:
        r8 = "hub3b";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0282;
    L_0x027f:
        r5 = r6;
        goto L_0x0023;
    L_0x0282:
        r8 = "hub4a";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0293;
    L_0x028a:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sHub4 = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x0293:
        r8 = "hub4b";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x029e;
    L_0x029b:
        r5 = r6;
        goto L_0x0023;
    L_0x029e:
        r8 = "addrc";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x02af;
    L_0x02a6:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sAddrC = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x02af:
        r8 = "addre";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x02c0;
    L_0x02b7:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sAddrE = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x02c0:
        r8 = "ubi";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x02d1;
    L_0x02c8:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sUBI = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x02d1:
        r8 = "lot";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0022;
    L_0x02d9:
        r8 = r4.nextText();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r6.sLot = r8;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x02e2:
        r3 = r4.getName();	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r8 = "result";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0389;
    L_0x02ee:
        if (r6 == 0) goto L_0x0389;
    L_0x02f0:
        r8 = r6.sAddr;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r9 = "@NEXT@";
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 != 0) goto L_0x0022;
    L_0x02fa:
        r8 = p049hk.com.aisoft.easyaddrui.eaView.sEALang;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r9 = "en-HK";
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x031d;
    L_0x0304:
        r8 = r6.sUBI;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r9 = "";
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x031d;
    L_0x030e:
        r8 = r6.sStreetE;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r9 = "";
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 != 0) goto L_0x031d;
    L_0x0318:
        r8 = r7.sResponseAddrs;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r8.add(r6);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
    L_0x031d:
        r8 = p049hk.com.aisoft.easyaddrui.eaView.sEALang;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r9 = "en-HK";
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0340;
    L_0x0327:
        r8 = r6.sUBI;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r9 = "";
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 != 0) goto L_0x0340;
    L_0x0331:
        r8 = r6.sBldgE;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r9 = "";
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 != 0) goto L_0x0340;
    L_0x033b:
        r8 = r7.sResponseAddrs;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r8.add(r6);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
    L_0x0340:
        r8 = p049hk.com.aisoft.easyaddrui.eaView.sEALang;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r9 = "en-HK";
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0377;
    L_0x034a:
        r8 = r6.sUBI;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r9 = "";
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 != 0) goto L_0x0377;
    L_0x0354:
        r8 = r6.sStreetE;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r9 = "";
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 != 0) goto L_0x0377;
    L_0x035e:
        r8 = r6.sStreetLon;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r9 = "";
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0372;
    L_0x0368:
        r8 = r6.sStreetFromNo;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r9 = "";
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 != 0) goto L_0x0377;
    L_0x0372:
        r8 = r7.sResponseAddrs;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r8.add(r6);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
    L_0x0377:
        r8 = p049hk.com.aisoft.easyaddrui.eaView.sEALang;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r9 = "zh-HK";
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0022;
    L_0x0381:
        r8 = r7.sResponseAddrs;	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r8.add(r6);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        r5 = r6;
        goto L_0x0023;
    L_0x0389:
        r8 = "content";
        r8 = r3.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x03a2, all -> 0x039f }
        if (r8 == 0) goto L_0x0022;
    L_0x0391:
        r0 = 1;
        r5 = r6;
        goto L_0x0023;
    L_0x0395:
        r5 = r6;
    L_0x0396:
        return r7;
    L_0x0397:
        r2 = move-exception;
    L_0x0398:
        r8 = p049hk.com.aisoft.easyaddrui.BuildConfig.DEBUG;	 Catch:{ all -> 0x039d }
        if (r8 == 0) goto L_0x0396;
    L_0x039c:
        goto L_0x0396;
    L_0x039d:
        r8 = move-exception;
    L_0x039e:
        throw r8;
    L_0x039f:
        r8 = move-exception;
        r5 = r6;
        goto L_0x039e;
    L_0x03a2:
        r2 = move-exception;
        r5 = r6;
        goto L_0x0398;
        */
        throw new UnsupportedOperationException("Method not decompiled: p049hk.com.aisoft.easyaddrui.DownloadSearchMore.getResponseQK(java.lang.String):hk.com.aisoft.easyaddrui.ResponseQK");
    }
}
