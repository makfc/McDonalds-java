package p049hk.com.aisoft.easyaddrui;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.widget.TextView;
import android.widget.Toast;
import com.mcdonalds.sdk.connectors.middleware.model.DCSProfile;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import java.util.Collections;
import java.util.concurrent.Executor;

/* renamed from: hk.com.aisoft.easyaddrui.ResponseTrigger */
public class ResponseTrigger {
    protected static void getVerifyResult() {
        try {
            if (eaView.sReqBack) {
                if (eaView.sResponseQK.sPage.equals("1")) {
                    Collections.sort(eaView.sResponseQK.sResponseAddrs, new ResponseAddrScoreCompare());
                }
                if (eaView.sResponseQK.sResponseAddrs == null) {
                    eaView.sResponse = "VERIFY NO RECORD";
                    return;
                } else if (eaView.sResponseQK.sResponseAddrs.size() > 0) {
                    eaView.sAddressReturn = new AddressReturn();
                    eaView.sAddressReturn.sCode = "1";
                    eaView.sAddressReturn.sAddress = new Address((ResponseAddr) eaView.sResponseQK.sResponseAddrs.get(0));
                    TextView textView = eaView.lbKeyword;
                    SysParameter sysParameter = eaView.sSysParameter;
                    textView.setText(SysParameter.lbFormSelAddr);
                    String tmpFloorLabelC = "樓";
                    String tmpFloorLabelE = "FLOOR";
                    String tmpFlatLabelC = "室";
                    String tmpFlatLabelE = "FLAT";
                    if (eaView.sEACurAddr.sFloor.length() >= tmpFloorLabelC.length() && eaView.sEACurAddr.sFloor.substring(eaView.sEACurAddr.sFloor.length() - tmpFloorLabelC.length()).equals(tmpFloorLabelC)) {
                        eaView.sEACurAddr.sFloor = eaView.sEACurAddr.sFloor.substring(0, eaView.sEACurAddr.sFloor.length() - tmpFloorLabelC.length());
                    }
                    if (eaView.sEACurAddr.sFlat.length() >= tmpFlatLabelC.length() && eaView.sEACurAddr.sFlat.substring(eaView.sEACurAddr.sFlat.length() - tmpFlatLabelC.length()).equals(tmpFlatLabelC)) {
                        eaView.sEACurAddr.sFlat = eaView.sEACurAddr.sFlat.substring(0, eaView.sEACurAddr.sFlat.length() - tmpFlatLabelC.length());
                    }
                    if (eaView.sEACurAddr.sFloor.length() >= tmpFloorLabelE.length() && eaView.sEACurAddr.sFloor.substring(0, tmpFloorLabelE.length()).toUpperCase().equals(tmpFloorLabelE.toUpperCase())) {
                        eaView.sEACurAddr.sFloor = eaView.sEACurAddr.sFloor.substring(tmpFloorLabelE.length()).trim();
                    }
                    if (eaView.sEACurAddr.sFlat.length() >= tmpFlatLabelE.length() && eaView.sEACurAddr.sFlat.substring(0, tmpFlatLabelE.length()).toUpperCase().equals(tmpFlatLabelE.toUpperCase())) {
                        eaView.sEACurAddr.sFlat = eaView.sEACurAddr.sFlat.substring(tmpFlatLabelE.length()).trim();
                    }
                    eaView.sEACurAddr.sFloor = eaView.sEACurAddr.sFloor.trim();
                    eaView.sEACurAddr.sFlat = eaView.sEACurAddr.sFlat.trim();
                    eaView.sAddressReturn.sAddress.sFloor = eaView.sEACurAddr.sFloor;
                    eaView.sAddressReturn.sAddress.sFlat = eaView.sEACurAddr.sFlat;
                    eaView.sAddressReturn.sAddress.sRemarks = eaView.sEACurAddr.sRemarks;
                    if (eaView.sEALang.equals("zh-HK")) {
                        eaView.txtEAKeyword.setText(ResultAdapter.getDisplayAddrFrmAddrRet(eaView.sAddressReturn.sAddress, "zh-HK", true));
                    } else {
                        eaView.txtEAKeyword.setText(ResultAdapter.getDisplayAddrFrmAddrRet(eaView.sAddressReturn.sAddress, "en-HK", true));
                    }
                    if (eaView.sEALang.equals("zh-HK")) {
                        eaView.txtAddrNotice.setText(eaView.sAddressReturn.sAddress.sNoticeC);
                    } else {
                        eaView.txtAddrNotice.setText(eaView.sAddressReturn.sAddress.sNoticeE);
                    }
                    if (!eaView.sEACurAddr.sFloor.equals("")) {
                        eaView.txtAddrFloor.setText(eaView.sEACurAddr.sFloor);
                    }
                    if (!eaView.sEACurAddr.sFlat.equals("")) {
                        eaView.txtAddrFlat.setText(eaView.sEACurAddr.sFlat);
                    }
                    if (!eaView.sEACurAddr.sRemarks.equals("")) {
                        eaView.txtAddrRemarks.setText(eaView.sEACurAddr.sRemarks);
                    }
                    eaView.txtEAKeyword.setEnabled(false);
                    eaView.txtEAKeyword.setFocusableInTouchMode(false);
                    eaView.txtEAKeyword.setFocusable(false);
                    eaView.btnSearch.setVisibility(8);
                    eaView.btnClear.setVisibility(0);
                    eaView.llAddrMisc.setVisibility(0);
                    eaView.sCurrLot = ((ResponseAddr) eaView.sResponseQK.sResponseAddrs.get(0)).sLot;
                    eaView.sCurrDau = ((ResponseAddr) eaView.sResponseQK.sResponseAddrs.get(0)).sUBI;
                    if (((ResponseAddr) eaView.sResponseQK.sResponseAddrs.get(0)).sHub1.equals("")) {
                        eaView.sAddressReturn.sCode = "-4005";
                        eaView.llAddrMisc.setVisibility(8);
                        eaView.llAddrWarn.setVisibility(0);
                        try {
                            ((InputMethodManager) eaView.sContext.getSystemService("input_method")).hideSoftInputFromWindow(eaView.txtEAKeyword.getWindowToken(), 0);
                        } catch (Exception e) {
                        }
                    } else {
                        eaView.sAddressReturn.sCode = "1";
                        eaView.llAddrMisc.setVisibility(0);
                        eaView.llAddrWarn.setVisibility(8);
                        eaView.webView.setVisibility(8);
                    }
                    eaView.sResponse = "VERIFY DONE";
                    return;
                } else {
                    return;
                }
            }
            eaView.sHandler.removeCallbacks(eaView.sTimeoutVerifyRun);
            eaView.sHandler.postDelayed(eaView.sTimeoutVerifyRun, 500);
        } catch (Exception e2) {
            if (BuildConfig.DEBUG) {
            }
        }
    }

    protected static void getGPSResult() {
        try {
            if (eaView.sReqBack) {
                if (eaView.sResponseAM != null) {
                    if (eaView.sResponseQK == null) {
                        eaView.sResponseQK = eaView.sResponseAM;
                    } else if (eaView.sResponseAM.sResponseAddrs != null) {
                        eaView.sResponseQK.setReponseAddr(eaView.sResponseAM);
                    }
                }
                if (eaView.sResponseQK.sPage.equals("1")) {
                    Collections.sort(eaView.sResponseQK.sResponseAddrs, new ResponseAddrScoreCompare());
                }
                if (eaView.sResponseQK.sResponseAddrs == null) {
                    eaView.sResponse = "GPS NO RECORD";
                    Toast.makeText(eaView.sContext, eaView.lbNoGPSFound, 0).show();
                    return;
                } else if (eaView.sResponseQK.sResponseAddrs.size() > 0) {
                    eaView.listResult.setAdapter(null);
                    eaView.listResult.setAdapter(new ResultAdapter(eaView.sContext, eaView.sResponseQK.sResponseAddrs));
                    eaView.listResult.setVisibility(0);
                    eaView.lbLoading.setVisibility(8);
                    eaView.sResponse = "GPS DONE";
                    return;
                } else {
                    eaView.sResponse = "GPS NO RECORD";
                    Toast.makeText(eaView.sContext, eaView.lbNoGPSFound, 0).show();
                    return;
                }
            }
            eaView.sHandler.removeCallbacks(eaView.sTimeoutGPSRun);
            eaView.sHandler.postDelayed(eaView.sTimeoutGPSRun, Math.round(eaView.sEAChkResult.doubleValue() * 1000.0d));
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
            }
        }
    }

    protected static void getSearchResult() {
        try {
            if (eaView.sReqBack) {
                if (eaView.sResponseAM != null) {
                    if (eaView.sResponseQK == null) {
                        eaView.sResponseQK = eaView.sResponseAM;
                    } else if (eaView.sResponseAM.sResponseAddrs != null) {
                        eaView.sResponseQK.setReponseAddr(eaView.sResponseAM);
                    }
                }
                if (eaView.sResponseQK.sPage.equals("1")) {
                    Collections.sort(eaView.sResponseQK.sResponseAddrs, new ResponseAddrScoreCompare());
                }
                if (eaView.sResponseQK.sResponseAddrs == null) {
                    eaView.sResponse = "SEARCH NO RECORD";
                    eaView.lbLoading.setVisibility(8);
                    eaView.listResult.setVisibility(8);
                    Toast.makeText(eaView.sContext, eaView.lbNoAddrFound, 0).show();
                    return;
                } else if (eaView.sResponseQK.sResponseAddrs.size() <= 0) {
                    eaView.sResponse = "SEARCH NO RECORD";
                    eaView.lbLoading.setVisibility(8);
                    eaView.listResult.setVisibility(8);
                    Toast.makeText(eaView.sContext, eaView.lbNoAddrFound, 0).show();
                    return;
                } else if (eaView.sAddressReturn.sCode.equals("0")) {
                    eaView.listResult.setAdapter(null);
                    eaView.listResult.setAdapter(new ResultAdapter(eaView.sContext, eaView.sResponseQK.sResponseAddrs));
                    eaView.listResult.setVisibility(0);
                    eaView.lbLoading.setVisibility(8);
                    eaView.sResponse = "SEARCH DONE";
                    return;
                } else {
                    return;
                }
            }
            eaView.sHandler.removeCallbacks(eaView.sTimeoutSearchRun);
            eaView.sHandler.postDelayed(eaView.sTimeoutSearchRun, Math.round(eaView.sEAChkResult.doubleValue() * 1000.0d));
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
            }
        }
    }

    protected static void btnGPSPressAction() {
        Log.v("Action", "Start GPS");
        eaView.sResponseQK = null;
        eaView.sResponseAM = null;
        eaView.sEATimeoutCnt = 0;
        eaView.sReqBack = false;
        DownloadGPSSearch dlTask2 = new DownloadGPSSearch(String.valueOf(eaView.sGPSX), String.valueOf(eaView.sGPSY), "1");
        Executor executor;
        String[] strArr;
        if (eaView.sEAUAT.equals(DCSProfile.INDICATOR_TRUE)) {
            executor = AsyncTask.THREAD_POOL_EXECUTOR;
            strArr = new String[]{eaView.sContext.getString(C4560R.string.uat_gps_search).replace("[DOMAIN]", eaView.sDomain)};
            if (dlTask2 instanceof AsyncTask) {
                AsyncTaskInstrumentation.executeOnExecutor(dlTask2, executor, strArr);
            } else {
                dlTask2.executeOnExecutor(executor, strArr);
            }
        } else {
            executor = AsyncTask.THREAD_POOL_EXECUTOR;
            strArr = new String[]{eaView.sContext.getString(C4560R.string.url_gps_search).replace("[DOMAIN]", eaView.sDomain)};
            if (dlTask2 instanceof AsyncTask) {
                AsyncTaskInstrumentation.executeOnExecutor(dlTask2, executor, strArr);
            } else {
                dlTask2.executeOnExecutor(executor, strArr);
            }
        }
        eaView.sHandler.removeCallbacks(eaView.sTimeoutGPSRun);
        eaView.sHandler.postDelayed(eaView.sTimeoutGPSRun, Math.round(eaView.sEAChkResult.doubleValue() * 1000.0d));
    }

    protected static void btnSearchPressAction() {
        if (eaView.txtEAKeyword.getText().toString().length() > 1) {
            Log.v("Action", "Start Search Keyword");
            eaView.sResponseQK = null;
            eaView.sEATimeoutCnt = 0;
            eaView.listResult.setAdapter(null);
            eaView.listResult.setVisibility(8);
            eaView.sReqBack = false;
            DownloadQKSearch dlTask2 = new DownloadQKSearch(eaView.sEACity, eaView.txtEAKeyword.getText().toString(), "1");
            Executor executor;
            String[] strArr;
            if (eaView.sEAUAT.equals(DCSProfile.INDICATOR_TRUE)) {
                executor = AsyncTask.THREAD_POOL_EXECUTOR;
                strArr = new String[]{eaView.sContext.getString(C4560R.string.uat_addr_search).replace("[DOMAIN]", eaView.sDomain)};
                if (dlTask2 instanceof AsyncTask) {
                    AsyncTaskInstrumentation.executeOnExecutor(dlTask2, executor, strArr);
                } else {
                    dlTask2.executeOnExecutor(executor, strArr);
                }
            } else {
                executor = AsyncTask.THREAD_POOL_EXECUTOR;
                strArr = new String[]{eaView.sContext.getString(C4560R.string.url_addr_search).replace("[DOMAIN]", eaView.sDomain)};
                if (dlTask2 instanceof AsyncTask) {
                    AsyncTaskInstrumentation.executeOnExecutor(dlTask2, executor, strArr);
                } else {
                    dlTask2.executeOnExecutor(executor, strArr);
                }
            }
            eaView.sHandler.removeCallbacks(eaView.sTimeoutSearchRun);
            eaView.sHandler.postDelayed(eaView.sTimeoutSearchRun, Math.round(eaView.sEAChkResult.doubleValue() * 1000.0d));
        }
    }

    protected static void getAddrSelected(String sInOut) {
        if (sInOut.equals("INBOUND")) {
            eaView.sAddressReturn.sCode = "1";
            eaView.llAddrMisc.setVisibility(0);
            eaView.llAddrWarn.setVisibility(8);
            eaView.txtAddrFloor.requestFocus();
            return;
        }
        eaView.sAddressReturn.sCode = "-4005";
        eaView.llAddrMisc.setVisibility(8);
        eaView.llAddrWarn.setVisibility(0);
        try {
            String url = "";
            if (eaView.sEAUAT.equals(DCSProfile.INDICATOR_TRUE)) {
                url = eaView.sContext.getString(C4560R.string.uat_get_map).replace("[DOMAIN]", eaView.sDomain) + "?l=" + eaView.sEALang + "&p=" + eaView.sCurrLot;
            } else {
                url = eaView.sContext.getString(C4560R.string.url_get_map).replace("[DOMAIN]", eaView.sDomain) + "?l=" + eaView.sEALang + "&p=" + eaView.sCurrLot;
            }
            eaView.webView.loadUrl(url);
            WebSettings webSettings = eaView.webView.getSettings();
            int currentapiVersion = VERSION.SDK_INT;
            webSettings.setJavaScriptEnabled(true);
        } catch (Exception e) {
        }
        try {
            ((InputMethodManager) eaView.sContext.getSystemService("input_method")).hideSoftInputFromWindow(eaView.txtEAKeyword.getWindowToken(), 0);
        } catch (Exception e2) {
        }
    }
}
