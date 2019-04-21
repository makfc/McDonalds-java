package p049hk.com.aisoft.easyaddrui;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;
import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: hk.com.aisoft.easyaddrui.DownloadParaAccess */
public class DownloadParaAccess extends AsyncTask<String, Void, String> implements TraceFieldInterface {
    public Trace _nr_trace;
    String sBundle = "";

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    protected DownloadParaAccess(SysParaCallbackInterface mSysParaCallbackInterface) {
        eaView.sSysParaCallbackInterface = mSysParaCallbackInterface;
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
            return downloadUrl(urls[0] + "?l=" + URLEncoder.encode(eaView.sEALang, Utf8Charset.NAME) + "&cc=" + sPayloadEn.replace("\n", ""));
        } catch (IOException e2) {
            return "ERR";
        }
    }

    /* Access modifiers changed, original: protected */
    public void onPostExecute(String sResult) {
        try {
            if (sResult.equals("ERR")) {
                eaView.eaNetErr = true;
                if (eaView.sEALang.equals("zh-HK")) {
                    Toast.makeText(eaView.sContext, eaView.lbNetworkErrorC, 0).show();
                    return;
                } else {
                    Toast.makeText(eaView.sContext, eaView.lbNetworkErrorE, 0).show();
                    return;
                }
            }
            eaView.sSysParameter = DownloadParaAccess.getSystemPara(sResult);
            SysParameter sysParameter = eaView.sSysParameter;
            eaView.sDomain = SysParameter.domain;
            eaView.sSysParaCallbackInterface.onCompleteLoad();
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

    protected static SysParameter getSystemPara(String sXML) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser();
        SysParameter sSysParameter = new SysParameter();
        try {
            parser.setInput(new StringReader(sXML));
            boolean done = false;
            for (int eventType = parser.getEventType(); eventType != 1 && !done; eventType = parser.next()) {
                switch (eventType) {
                    case 2:
                        String name = parser.getName();
                        if (!name.equalsIgnoreCase("areaList")) {
                            if (!name.equalsIgnoreCase("domain")) {
                                if (!name.equalsIgnoreCase("msgLoading")) {
                                    if (!name.equalsIgnoreCase("msgLoadingGPS")) {
                                        if (!name.equalsIgnoreCase("msgPleaseWait")) {
                                            if (!name.equalsIgnoreCase("msgInComAddr")) {
                                                if (!name.equalsIgnoreCase("msgAddrNotFind")) {
                                                    if (!name.equalsIgnoreCase("mgsGPSNotSupport")) {
                                                        if (!name.equalsIgnoreCase("msgLocNotFind")) {
                                                            if (!name.equalsIgnoreCase("msgNoAddrSel")) {
                                                                if (!name.equalsIgnoreCase("msgNoCitySel")) {
                                                                    if (!name.equalsIgnoreCase("msgAddrOOB")) {
                                                                        if (!name.equalsIgnoreCase("msgWarnOOB")) {
                                                                            if (!name.equalsIgnoreCase("msgWarnCity")) {
                                                                                if (!name.equalsIgnoreCase("lbKeyInputHints")) {
                                                                                    if (!name.equalsIgnoreCase("lbSmallRemarks")) {
                                                                                        if (!name.equalsIgnoreCase("lbFormContact")) {
                                                                                            if (!name.equalsIgnoreCase("lbFormPhone")) {
                                                                                                if (!name.equalsIgnoreCase("lbFormCity")) {
                                                                                                    if (!name.equalsIgnoreCase("lbFormSelAddr")) {
                                                                                                        if (!name.equalsIgnoreCase("lbFormKeyAddr")) {
                                                                                                            if (!name.equalsIgnoreCase("lbFormMapAddr")) {
                                                                                                                if (!name.equalsIgnoreCase("lbFormBlock")) {
                                                                                                                    if (!name.equalsIgnoreCase("lbFormFloor")) {
                                                                                                                        if (!name.equalsIgnoreCase("lbFormFlat")) {
                                                                                                                            if (!name.equalsIgnoreCase("lbFormNotice")) {
                                                                                                                                if (!name.equalsIgnoreCase("lbFormRemarks")) {
                                                                                                                                    break;
                                                                                                                                }
                                                                                                                                SysParameter.lbFormRemarks = parser.nextText();
                                                                                                                                break;
                                                                                                                            }
                                                                                                                            SysParameter.lbFormNotice = parser.nextText();
                                                                                                                            break;
                                                                                                                        }
                                                                                                                        SysParameter.lbFormFlat = parser.nextText();
                                                                                                                        break;
                                                                                                                    }
                                                                                                                    SysParameter.lbFormFloor = parser.nextText();
                                                                                                                    break;
                                                                                                                }
                                                                                                                SysParameter.lbFormBlock = parser.nextText();
                                                                                                                break;
                                                                                                            }
                                                                                                            SysParameter.lbFormMapAddr = parser.nextText();
                                                                                                            break;
                                                                                                        }
                                                                                                        SysParameter.lbFormKeyAddr = parser.nextText();
                                                                                                        break;
                                                                                                    }
                                                                                                    SysParameter.lbFormSelAddr = parser.nextText();
                                                                                                    break;
                                                                                                }
                                                                                                SysParameter.lbFormCity = parser.nextText();
                                                                                                break;
                                                                                            }
                                                                                            SysParameter.lbFormPhone = parser.nextText();
                                                                                            break;
                                                                                        }
                                                                                        SysParameter.lbFormContact = parser.nextText();
                                                                                        break;
                                                                                    }
                                                                                    SysParameter.lbSmallRemarks = parser.nextText();
                                                                                    break;
                                                                                }
                                                                                SysParameter.lbKeyInputHints = parser.nextText();
                                                                                break;
                                                                            }
                                                                            SysParameter.msgWarnCity = parser.nextText();
                                                                            break;
                                                                        }
                                                                        SysParameter.msgWarnOOB = parser.nextText();
                                                                        break;
                                                                    }
                                                                    SysParameter.msgAddrOOB = parser.nextText();
                                                                    break;
                                                                }
                                                                SysParameter.msgNoCitySel = parser.nextText();
                                                                break;
                                                            }
                                                            SysParameter.msgNoAddrSel = parser.nextText();
                                                            break;
                                                        }
                                                        SysParameter.msgLocNotFind = parser.nextText();
                                                        break;
                                                    }
                                                    SysParameter.mgsGPSNotSupport = parser.nextText();
                                                    break;
                                                }
                                                SysParameter.msgAddrNotFind = parser.nextText();
                                                break;
                                            }
                                            SysParameter.msgInComAddr = parser.nextText();
                                            break;
                                        }
                                        SysParameter.msgPleaseWait = parser.nextText();
                                        break;
                                    }
                                    SysParameter.msgLoadingGPS = parser.nextText();
                                    break;
                                }
                                SysParameter.msgLoading = parser.nextText();
                                break;
                            }
                            SysParameter.domain = parser.nextText();
                            break;
                        }
                        SysParameter.paraCityList = parser.nextText();
                        break;
                    case 3:
                        if (!parser.getName().equalsIgnoreCase("para")) {
                            break;
                        }
                        done = true;
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception ex) {
            if (BuildConfig.DEBUG) {
                Log.e("getResponseQK", "exception", ex);
            }
        }
        return sSysParameter;
    }
}
