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

/* renamed from: hk.com.aisoft.easyaddrui.DownloadGSSearch */
public class DownloadGSSearch extends AsyncTask<String, Void, String> implements TraceFieldInterface {
    public Trace _nr_trace;
    Address sAddress;
    String sBundle = "";

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    protected DownloadGSSearch(Address mAddress) {
        this.sAddress = mAddress;
        this.sBundle = eaView.sEABundle;
    }

    private static String getPayload(String mArea, String mDistrict, String mStreet, String mStreetLon, String mStreetNo, String mEstate, String mBldg, String mBlk, String mBlock, String mFloor, String mFlat, String mRemarks, String sGSMarket, String sGSApplication, String sGSLanguage) {
        return "" + "{\"RequestID\":-99,\"MessageType\":2,\"Body\":\"" + ((((((((((((("" + "{\\\"AddressElements\\\":[") + "{\\\"AddressElementTypeCode\\\":1,\\\"Value\\\":[{\\\"AliasTypeCode\\\":1,\\\"Alias\\\":\\\"" + mArea + "\\\"}]},") + "{\\\"AddressElementTypeCode\\\":6,\\\"Value\\\":[{\\\"AliasTypeCode\\\":1,\\\"Alias\\\":\\\"" + mDistrict + "\\\"}]},") + "{\\\"AddressElementTypeCode\\\":9,\\\"Value\\\":[{\\\"AliasTypeCode\\\":1,\\\"Alias\\\":\\\"" + mStreet + "\\\"}]},") + "{\\\"AddressElementTypeCode\\\":20,\\\"Value\\\":[{\\\"AliasTypeCode\\\":1,\\\"Alias\\\":\\\"" + mStreetLon + "\\\"}]},") + "{\\\"AddressElementTypeCode\\\":16,\\\"Value\\\":[{\\\"AliasTypeCode\\\":1,\\\"Alias\\\":\\\"" + mStreetNo + "\\\"}]},") + "{\\\"AddressElementTypeCode\\\":7,\\\"Value\\\":[{\\\"AliasTypeCode\\\":1,\\\"Alias\\\":\\\"" + mEstate + "\\\"}]},") + "{\\\"AddressElementTypeCode\\\":2,\\\"Value\\\":[{\\\"AliasTypeCode\\\":1,\\\"Alias\\\":\\\"" + mBldg + "\\\"}]},") + "{\\\"AddressElementTypeCode\\\":13,\\\"Value\\\":[{\\\"AliasTypeCode\\\":1,\\\"Alias\\\":\\\"" + mBlk + "\\\"}]},") + "{\\\"AddressElementTypeCode\\\":14,\\\"Value\\\":[{\\\"AliasTypeCode\\\":1,\\\"Alias\\\":\\\"" + mFloor + "\\\"}]},") + "{\\\"AddressElementTypeCode\\\":15,\\\"Value\\\":[{\\\"AliasTypeCode\\\":1,\\\"Alias\\\":\\\"" + mFlat + "\\\"}]},") + "{\\\"AddressElementTypeCode\\\":21,\\\"Value\\\":[{\\\"AliasTypeCode\\\":1,\\\"Alias\\\":\\\"" + mRemarks + "\\\"}]}") + "],\\\"DeliveryTime\\\":\\\"2015-10-22T05:25:42.499Z\\\"}") + "\",\"ExternalMarketCode\":\"" + sGSMarket + "\",\"Application\":\"" + sGSApplication + "\",\"Language\":\"" + sGSLanguage + "\"}";
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
        JNCryptor cryptor = new AES256JNCryptor();
        String password = eaView.sSysHash;
        try {
            byte[] plaintext = sPayload.getBytes(Utf8Charset.NAME);
            sPayloadEn = Base64.encodeToString(cryptor.encryptData(plaintext, password.toCharArray()), 0);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
            }
        }
        try {
            String sGSLanguage = eaView.sEALang;
            return downloadUrl(urls[0] + "?access_token=" + URLEncoder.encode("YUFDBHAMQJWD673242ST", Utf8Charset.NAME) + "&data=" + URLEncoder.encode(DownloadGSSearch.getPayload(this.sAddress.sAreaC, this.sAddress.sDistrictC, this.sAddress.sStreetC, this.sAddress.sStreetLon, this.sAddress.sStreetNo, this.sAddress.sEstateC, this.sAddress.sBldgC, this.sAddress.sBlockC, this.sAddress.sBlock, this.sAddress.sFloor, this.sAddress.sFlat, this.sAddress.sRemarks, "HK", "IBA", sGSLanguage), Utf8Charset.NAME) + "&cc=" + sPayloadEn.replace("\n", ""));
        } catch (IOException e2) {
            e2.printStackTrace();
            return "ERR";
        }
    }

    /* Access modifiers changed, original: protected */
    public void onPostExecute(String sResult) {
        StoreReturn sStoreReturn;
        try {
            if (sResult.equals("ERR")) {
                sStoreReturn = new StoreReturn("-5005");
            } else {
                StoreReturn sStoreReturn2 = new StoreReturn("1");
                try {
                    sStoreReturn2.setHub(sResult.toString());
                    sStoreReturn = sStoreReturn2;
                } catch (Exception e) {
                    sStoreReturn = sStoreReturn2;
                    sStoreReturn = new StoreReturn("-5005");
                    eaView.sGetStoreCallbackInterface.onGetStoreReturn(sStoreReturn);
                }
            }
        } catch (Exception e2) {
            sStoreReturn = new StoreReturn("-5005");
            eaView.sGetStoreCallbackInterface.onGetStoreReturn(sStoreReturn);
        }
        eaView.sGetStoreCallbackInterface.onGetStoreReturn(sStoreReturn);
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
            if (is != null) {
                is.close();
            }
        } catch (Exception e) {
            sData = "ERR";
            return sData;
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return sData;
    }
}
