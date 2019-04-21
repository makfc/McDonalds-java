package p049hk.com.aisoft.easyaddrui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.p000v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.amap.api.location.LocationManagerProxy;
import com.mcdonalds.sdk.connectors.middleware.model.DCSProfile;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import java.util.Random;
import java.util.concurrent.Executor;

/* renamed from: hk.com.aisoft.easyaddrui.eaView */
public class eaView extends LinearLayout {
    protected static String VERSION = "AND4.7";
    protected static ImageButton btnClear = null;
    protected static ImageButton btnGPS = null;
    protected static ImageButton btnSearch = null;
    protected static boolean eaNetErr = false;
    protected static TextView lbAddrFlat;
    protected static TextView lbAddrFloor;
    protected static TextView lbAddrNotice;
    protected static TextView lbAddrRemarks;
    protected static TextView lbAddrWarn;
    protected static TextView lbKeyword = null;
    protected static TextView lbLoading = null;
    protected static String lbNetworkErrorC = "";
    protected static String lbNetworkErrorE = "";
    protected static String lbNoAddrFound = "";
    protected static String lbNoGPSFound = "";
    protected static ListView listResult = null;
    public static LinearLayout llAddrMisc = null;
    public static LinearLayout llAddrWarn = null;
    public static LinearLayout llRowFlat = null;
    public static LinearLayout llRowFloor = null;
    public static LinearLayout llRowKey = null;
    public static ScrollView llRowMiscScroll = null;
    public static LinearLayout llRowRemarks = null;
    public static LinearLayout llSearch = null;
    protected static AddressReturn sAddressReturn = null;
    public static CompleteCallbackInterface sCompleteCallbackInterface;
    public static ConfirmCallbackInterface sConfirmCallbackInterface;
    protected static Context sContext;
    protected static String sCurrDau = "";
    protected static String sCurrLot = "";
    protected static Customer sCustomer = null;
    protected static String sDomain = "";
    protected static Double sEAAutoSearch = Double.valueOf(2.0d);
    protected static String sEABundle = "";
    protected static Double sEAChkResult = Double.valueOf(1.0d);
    protected static String sEACity = "香港";
    protected static Address sEACurAddr = null;
    protected static Double sEACurX = Double.valueOf(0.0d);
    protected static Double sEACurY = Double.valueOf(0.0d);
    protected static String sEAKey = "";
    protected static String sEALang = "";
    protected static String sEAMode = "";
    protected static int sEATimeoutCnt = 0;
    protected static String sEAUAT = DCSProfile.INDICATOR_FALSE;
    public static EditCallbackInterface sEditCallbackInterface;
    protected static double sGPSX = 0.0d;
    protected static double sGPSY = 0.0d;
    public static GetStoreCallbackInterface sGetStoreCallbackInterface;
    protected static Handler sHandler = new Handler();
    protected static String sListMode = "";
    protected static Location sLocation;
    protected static LocationListener sLocationListener;
    protected static LocationManager sLocationManager;
    protected static boolean sReqBack = false;
    protected static String sResponse = "";
    protected static ResponseQK sResponseAM = null;
    protected static ResponseQK sResponseQK = null;
    protected static Runnable sRunnable = null;
    public static SaveCallbackInterface sSaveCallbackInterface;
    protected static boolean sSkipAutoSearch = false;
    protected static int sSpinnerChangeCnt = 0;
    protected static String sSysHash = "^&^TR#^%FEG@^Tft8q3T@Hgfv6t";
    public static SysParaCallbackInterface sSysParaCallbackInterface;
    protected static SysParameter sSysParameter;
    protected static Runnable sTimeoutGPSRun = null;
    protected static Runnable sTimeoutSearchRun = null;
    protected static Runnable sTimeoutVerifyRun = null;
    protected static int sVisibleCnt = 0;
    protected static EditText txtAddrFlat = null;
    protected static EditText txtAddrFloor = null;
    protected static TextView txtAddrNotice = null;
    protected static EditText txtAddrRemarks = null;
    protected static EditText txtEAKeyword = null;
    public static WebView webView = null;
    Resources res = getResources();
    String sAddrBeforeEdit = "";
    int sLastScrollCnt = 0;

    /* renamed from: hk.com.aisoft.easyaddrui.eaView$10 */
    class C456110 implements OnScrollListener {
        C456110() {
        }

        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (scrollState == 1) {
                try {
                    ((InputMethodManager) eaView.sContext.getSystemService("input_method")).hideSoftInputFromWindow(eaView.txtEAKeyword.getWindowToken(), 0);
                } catch (Exception e) {
                }
            }
        }

        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (visibleItemCount > 0 && eaView.this.sLastScrollCnt < totalItemCount && firstVisibleItem + visibleItemCount == totalItemCount) {
                eaView.sVisibleCnt = visibleItemCount;
                eaView.this.sLastScrollCnt = totalItemCount;
                if (eaView.sResponseQK != null) {
                    if (eaView.sResponseQK.sType.equals("K")) {
                    }
                    Executor executor;
                    String[] strArr;
                    if (eaView.sResponseQK.sType.equals("G")) {
                        DownloadGPSSearch dlTask = new DownloadGPSSearch(String.valueOf(eaView.sGPSX), String.valueOf(eaView.sGPSY), String.valueOf(Integer.parseInt(eaView.sResponseQK.sPage) + 1));
                        if (eaView.sEAUAT.equals(DCSProfile.INDICATOR_TRUE)) {
                            executor = AsyncTask.THREAD_POOL_EXECUTOR;
                            strArr = new String[]{eaView.this.res.getString(C4560R.string.uat_addr_more).replace("[DOMAIN]", eaView.sDomain)};
                            if (dlTask instanceof AsyncTask) {
                                AsyncTaskInstrumentation.executeOnExecutor(dlTask, executor, strArr);
                            } else {
                                dlTask.executeOnExecutor(executor, strArr);
                            }
                        } else {
                            executor = AsyncTask.THREAD_POOL_EXECUTOR;
                            strArr = new String[]{eaView.this.res.getString(C4560R.string.url_addr_more).replace("[DOMAIN]", eaView.sDomain)};
                            if (dlTask instanceof AsyncTask) {
                                AsyncTaskInstrumentation.executeOnExecutor(dlTask, executor, strArr);
                            } else {
                                dlTask.executeOnExecutor(executor, strArr);
                            }
                        }
                        eaView.getStartupLoad();
                    } else if (eaView.sResponseQK.sType.equals("A") || eaView.sResponseQK.sType.equals("D") || eaView.sResponseQK.sType.equals("S") || eaView.sResponseQK.sType.equals("E") || eaView.sResponseQK.sType.equals("B") || eaView.sResponseQK.sType.equals(DCSProfile.INDICATOR_FALSE)) {
                        DownloadSearchMore dlTask2 = new DownloadSearchMore(eaView.sResponseQK.sType, String.valueOf(Integer.parseInt(eaView.sResponseQK.sPage) + 1), eaView.sEACity, eaView.sAddressReturn.sAddress.sAreaC, eaView.sAddressReturn.sAddress.sAreaE, eaView.sAddressReturn.sAddress.sDistrictC, eaView.sAddressReturn.sAddress.sDistrictE, eaView.sAddressReturn.sAddress.sStreetC, eaView.sAddressReturn.sAddress.sStreetE, eaView.sAddressReturn.sAddress.sStreetLon, eaView.sAddressReturn.sAddress.sStreetNo, "", "", "", eaView.sAddressReturn.sAddress.sEstateC, eaView.sAddressReturn.sAddress.sEstateE, "", "", "", eaView.sAddressReturn.sAddress.sBldgC, eaView.sAddressReturn.sAddress.sBldgE, eaView.sAddressReturn.sAddress.sBlockC, eaView.sAddressReturn.sAddress.sBlockE);
                        if (eaView.sEAUAT.equals(DCSProfile.INDICATOR_TRUE)) {
                            executor = AsyncTask.THREAD_POOL_EXECUTOR;
                            strArr = new String[]{eaView.this.res.getString(C4560R.string.uat_addr_more).replace("[DOMAIN]", eaView.sDomain)};
                            if (dlTask2 instanceof AsyncTask) {
                                AsyncTaskInstrumentation.executeOnExecutor(dlTask2, executor, strArr);
                            } else {
                                dlTask2.executeOnExecutor(executor, strArr);
                            }
                        } else {
                            executor = AsyncTask.THREAD_POOL_EXECUTOR;
                            strArr = new String[]{eaView.this.res.getString(C4560R.string.url_addr_more).replace("[DOMAIN]", eaView.sDomain)};
                            if (dlTask2 instanceof AsyncTask) {
                                AsyncTaskInstrumentation.executeOnExecutor(dlTask2, executor, strArr);
                            } else {
                                dlTask2.executeOnExecutor(executor, strArr);
                            }
                        }
                        eaView.getStartupLoad();
                    }
                }
            }
        }
    }

    /* renamed from: hk.com.aisoft.easyaddrui.eaView$11 */
    class C456211 implements Runnable {
        C456211() {
        }

        public void run() {
            Log.v("Action", "Auto Trigger Search");
            eaView.lbLoading.setVisibility(0);
            ResponseTrigger.btnSearchPressAction();
        }
    }

    /* renamed from: hk.com.aisoft.easyaddrui.eaView$12 */
    class C456312 implements Runnable {
        C456312() {
        }

        public void run() {
            ResponseTrigger.getSearchResult();
        }
    }

    /* renamed from: hk.com.aisoft.easyaddrui.eaView$13 */
    class C456413 implements Runnable {
        C456413() {
        }

        public void run() {
            ResponseTrigger.getVerifyResult();
        }
    }

    /* renamed from: hk.com.aisoft.easyaddrui.eaView$14 */
    class C456514 implements Runnable {
        C456514() {
        }

        public void run() {
            ResponseTrigger.getGPSResult();
        }
    }

    /* renamed from: hk.com.aisoft.easyaddrui.eaView$15 */
    class C456615 implements TextWatcher {
        C456615() {
        }

        public void afterTextChanged(Editable s) {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (eaView.eaNetErr) {
                if (eaView.sEALang.equals("zh-HK")) {
                    Toast.makeText(eaView.sContext, eaView.lbNetworkErrorC, 0).show();
                } else {
                    Toast.makeText(eaView.sContext, eaView.lbNetworkErrorE, 0).show();
                }
            } else if (eaView.sAddressReturn.sCode.equals("0") && s.length() > 1) {
                if (eaView.sSkipAutoSearch) {
                    eaView.sSkipAutoSearch = false;
                    return;
                }
                eaView.lbLoading.setVisibility(8);
                eaView.sHandler.removeCallbacks(eaView.sRunnable);
                eaView.sHandler.postDelayed(eaView.sRunnable, Math.round(eaView.sEAAutoSearch.doubleValue() * 1000.0d));
            }
        }
    }

    /* renamed from: hk.com.aisoft.easyaddrui.eaView$16 */
    class C456716 implements OnKeyListener {
        C456716() {
        }

        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (!eaView.sAddressReturn.sCode.equals("0") || keyCode != 66) {
                return false;
            }
            eaView.btnSearch.callOnClick();
            return true;
        }
    }

    /* renamed from: hk.com.aisoft.easyaddrui.eaView$17 */
    class C456817 implements OnClickListener {
        C456817() {
        }

        public void onClick(View v) {
            Log.v("Action", "Press Search Button");
            eaView.sHandler.removeCallbacks(eaView.sRunnable);
            if (eaView.eaNetErr) {
                if (eaView.sEALang.equals("zh-HK")) {
                    Toast.makeText(eaView.sContext, eaView.lbNetworkErrorC, 0).show();
                } else {
                    Toast.makeText(eaView.sContext, eaView.lbNetworkErrorE, 0).show();
                }
            } else if (eaView.txtEAKeyword.getText().toString().length() > 1) {
                eaView.sListMode = "K";
                eaView.sResponse = "";
                ResponseTrigger.btnSearchPressAction();
                eaView.getStartupLoad();
            } else {
                Context context = eaView.sContext;
                SysParameter sysParameter = eaView.sSysParameter;
                Toast.makeText(context, SysParameter.msgInComAddr, 0).show();
            }
        }
    }

    /* renamed from: hk.com.aisoft.easyaddrui.eaView$18 */
    class C456918 implements OnClickListener {
        C456918() {
        }

        public void onClick(View v) {
            eaView.sCurrLot = "";
            eaView.sCurrDau = "";
            eaView.sAddressReturn.sCode = "0";
            eaView.txtEAKeyword.setText("");
            eaView.txtEAKeyword.setEnabled(true);
            eaView.txtEAKeyword.setFocusableInTouchMode(true);
            eaView.txtEAKeyword.setFocusable(true);
            eaView.btnClear.setVisibility(8);
            eaView.btnSearch.setVisibility(0);
            eaView.listResult.setVisibility(8);
            eaView.llAddrWarn.setVisibility(8);
            eaView.llAddrMisc.setVisibility(8);
            TextView textView = eaView.lbKeyword;
            SysParameter sysParameter = eaView.sSysParameter;
            textView.setText(SysParameter.lbFormKeyAddr);
            eaView.txtAddrFloor.setText("");
            eaView.txtAddrFlat.setText("");
            eaView.txtAddrRemarks.setText("");
            eaView.webView.setVisibility(8);
            eaView.llRowKey.requestFocus();
        }
    }

    /* renamed from: hk.com.aisoft.easyaddrui.eaView$19 */
    class C457019 implements OnClickListener {
        C457019() {
        }

        public void onClick(View v) {
            if (eaView.eaNetErr) {
                if (eaView.sEALang.equals("zh-HK")) {
                    Toast.makeText(eaView.sContext, eaView.lbNetworkErrorC, 0).show();
                } else {
                    Toast.makeText(eaView.sContext, eaView.lbNetworkErrorE, 0).show();
                }
            } else if (eaView.sGPSX <= 0.0d || eaView.sGPSY <= 0.0d) {
                Context context = eaView.sContext;
                SysParameter sysParameter = eaView.sSysParameter;
                Toast.makeText(context, SysParameter.msgInComAddr, 0).show();
            } else {
                eaView.sListMode = "G";
                eaView.sResponse = "";
                DownloadGPSSearch dlTask = new DownloadGPSSearch(String.valueOf(eaView.sGPSX), String.valueOf(eaView.sGPSY), "1");
                Executor executor;
                String[] strArr;
                if (eaView.sEAUAT.equals(DCSProfile.INDICATOR_TRUE)) {
                    executor = AsyncTask.THREAD_POOL_EXECUTOR;
                    strArr = new String[]{eaView.this.res.getString(C4560R.string.uat_gps_search).replace("[DOMAIN]", eaView.sDomain)};
                    if (dlTask instanceof AsyncTask) {
                        AsyncTaskInstrumentation.executeOnExecutor(dlTask, executor, strArr);
                    } else {
                        dlTask.executeOnExecutor(executor, strArr);
                    }
                } else {
                    executor = AsyncTask.THREAD_POOL_EXECUTOR;
                    strArr = new String[]{eaView.this.res.getString(C4560R.string.url_gps_search).replace("[DOMAIN]", eaView.sDomain)};
                    if (dlTask instanceof AsyncTask) {
                        AsyncTaskInstrumentation.executeOnExecutor(dlTask, executor, strArr);
                    } else {
                        dlTask.executeOnExecutor(executor, strArr);
                    }
                }
                eaView.getStartupLoad();
            }
        }
    }

    /* renamed from: hk.com.aisoft.easyaddrui.eaView$1 */
    class C45711 implements SysParaCallbackInterface {
        C45711() {
        }

        public void onCompleteLoad() {
            eaView.this.initialize();
        }
    }

    /* renamed from: hk.com.aisoft.easyaddrui.eaView$2 */
    class C45742 implements OnClickListener {
        C45742() {
        }

        public void onClick(View v) {
            if (eaView.sAddressReturn.sCode.equals("0")) {
                eaView.txtEAKeyword.requestFocus();
            }
        }
    }

    /* renamed from: hk.com.aisoft.easyaddrui.eaView$3 */
    class C45753 implements OnClickListener {
        C45753() {
        }

        public void onClick(View v) {
            eaView.txtAddrFloor.requestFocus();
        }
    }

    /* renamed from: hk.com.aisoft.easyaddrui.eaView$4 */
    class C45764 implements OnClickListener {
        C45764() {
        }

        public void onClick(View v) {
            eaView.txtAddrFlat.requestFocus();
        }
    }

    /* renamed from: hk.com.aisoft.easyaddrui.eaView$5 */
    class C45775 implements OnClickListener {
        C45775() {
        }

        public void onClick(View v) {
            eaView.txtAddrRemarks.requestFocus();
        }
    }

    /* renamed from: hk.com.aisoft.easyaddrui.eaView$6 */
    class C45786 implements OnFocusChangeListener {
        C45786() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                eaView.llRowMiscScroll.scrollTo(0, 0);
            }
        }
    }

    /* renamed from: hk.com.aisoft.easyaddrui.eaView$7 */
    class C45797 implements OnFocusChangeListener {
        C45797() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                eaView.llRowMiscScroll.scrollTo(0, 100);
            }
        }
    }

    /* renamed from: hk.com.aisoft.easyaddrui.eaView$8 */
    class C45808 implements OnFocusChangeListener {
        C45808() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                eaView.llRowMiscScroll.scrollTo(0, 150);
            }
        }
    }

    /* renamed from: hk.com.aisoft.easyaddrui.eaView$9 */
    class C45819 implements OnItemClickListener {
        C45819() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            try {
                String sHub = "";
                String sUBI = "";
                String sLot = "";
                TextView txtAddrName = (TextView) view.findViewById(C4560R.C4559id.txtAddrName);
                TextView txtAddrDetails = (TextView) view.findViewById(C4560R.C4559id.txtAddrDetails);
                TextView txtAreaC = (TextView) view.findViewById(C4560R.C4559id.txtAreaC);
                TextView txtAreaE = (TextView) view.findViewById(C4560R.C4559id.txtAreaE);
                TextView txtDistrictC = (TextView) view.findViewById(C4560R.C4559id.txtDistrictC);
                TextView txtDistrictE = (TextView) view.findViewById(C4560R.C4559id.txtDistrictE);
                TextView txtStreetC = (TextView) view.findViewById(C4560R.C4559id.txtStreetC);
                TextView txtStreetE = (TextView) view.findViewById(C4560R.C4559id.txtStreetE);
                TextView txtStreetLon = (TextView) view.findViewById(C4560R.C4559id.txtStreetLon);
                TextView txtStreetFromNo = (TextView) view.findViewById(C4560R.C4559id.txtStreetFromNo);
                TextView txtStreetFromCode = (TextView) view.findViewById(C4560R.C4559id.txtStreetFromCode);
                TextView txtStreetToNo = (TextView) view.findViewById(C4560R.C4559id.txtStreetToNo);
                TextView txtStreetToCode = (TextView) view.findViewById(C4560R.C4559id.txtStreetToCode);
                TextView txtEstateC = (TextView) view.findViewById(C4560R.C4559id.txtEstateC);
                TextView txtEstateE = (TextView) view.findViewById(C4560R.C4559id.txtEstateE);
                TextView txtBldgC = (TextView) view.findViewById(C4560R.C4559id.txtBldgC);
                TextView txtBldgE = (TextView) view.findViewById(C4560R.C4559id.txtBldgE);
                TextView txtBlockC = (TextView) view.findViewById(C4560R.C4559id.txtBlockC);
                TextView txtBlockE = (TextView) view.findViewById(C4560R.C4559id.txtBlockE);
                TextView txtHub1 = (TextView) view.findViewById(C4560R.C4559id.txtHub1);
                TextView txtHub2 = (TextView) view.findViewById(C4560R.C4559id.txtHub2);
                TextView txtHub3 = (TextView) view.findViewById(C4560R.C4559id.txtHub3);
                TextView txtHub4 = (TextView) view.findViewById(C4560R.C4559id.txtHub4);
                TextView txtAddrC = (TextView) view.findViewById(C4560R.C4559id.txtAddrC);
                TextView txtAddrE = (TextView) view.findViewById(C4560R.C4559id.txtAddrE);
                TextView txtUBI = (TextView) view.findViewById(C4560R.C4559id.txtUBI);
                TextView txtLot = (TextView) view.findViewById(C4560R.C4559id.txtLot);
                sHub = txtHub1.getText() + "";
                sUBI = ((ResponseAddr) eaView.sResponseQK.sResponseAddrs.get(position)).sUBI;
                eaView.sCurrLot = ((ResponseAddr) eaView.sResponseQK.sResponseAddrs.get(position)).sLot;
                eaView.sCurrDau = sUBI;
                eaView.this.sLastScrollCnt = 0;
                eaView.sAddressReturn = new AddressReturn();
                if (((ResponseAddr) eaView.sResponseQK.sResponseAddrs.get(position)).sUBI.equals("")) {
                    eaView.sAddressReturn.sCode = "0";
                    eaView.sAddressReturn.sAddress = new Address((ResponseAddr) eaView.sResponseQK.sResponseAddrs.get(position));
                    eaView.sResponse = "";
                    eaView.sSkipAutoSearch = true;
                    if (eaView.sResponseQK.sType.equals("D")) {
                        eaView.sResponseQK.sType = "S";
                    } else if (eaView.sResponseQK.sType.equals("S")) {
                        eaView.sResponseQK.sType = "E";
                    } else if (eaView.sResponseQK.sType.equals("E")) {
                        eaView.sResponseQK.sType = "B";
                    } else if (eaView.sResponseQK.sType.equals("K") || eaView.sResponseQK.sType.equals("L")) {
                        if (!txtStreetC.getText().toString().equals("") || !txtStreetE.getText().toString().equals("")) {
                            if (eaView.sEALang.equals("zh-HK")) {
                                eaView.txtEAKeyword.setText(txtStreetC.getText().toString());
                            } else {
                                eaView.txtEAKeyword.setText(txtStreetE.getText().toString());
                            }
                            eaView.txtEAKeyword.setSelection(eaView.txtEAKeyword.getText().toString().length());
                            eaView.sResponseQK.sType = "E";
                        } else if (!(txtEstateC.getText().toString().equals("") && txtEstateE.getText().toString().equals(""))) {
                            if (eaView.sEALang.equals("zh-HK")) {
                                eaView.txtEAKeyword.setText(txtEstateC.getText().toString());
                            } else {
                                eaView.txtEAKeyword.setText(txtEstateE.getText().toString());
                            }
                            eaView.txtEAKeyword.setSelection(eaView.txtEAKeyword.getText().toString().length());
                            eaView.sResponseQK.sType = "B";
                        }
                    }
                    DownloadSearchMore dlTask = new DownloadSearchMore(eaView.sResponseQK.sType, "1", txtAreaC.getText().toString(), txtAreaC.getText().toString(), txtAreaE.getText().toString(), txtDistrictC.getText().toString(), txtDistrictE.getText().toString(), txtStreetC.getText().toString(), txtStreetE.getText().toString(), txtStreetLon.getText().toString(), txtStreetFromNo.getText().toString(), txtStreetFromCode.getText().toString(), txtStreetToNo.getText().toString(), txtStreetToCode.getText().toString(), txtEstateC.getText().toString(), txtEstateE.getText().toString(), "", "", "", txtBldgC.getText().toString(), txtBldgE.getText().toString(), txtBlockC.getText().toString(), txtBlockE.getText().toString());
                    Executor executor;
                    String[] strArr;
                    if (eaView.sEAUAT.equals(DCSProfile.INDICATOR_TRUE)) {
                        executor = AsyncTask.THREAD_POOL_EXECUTOR;
                        strArr = new String[]{eaView.this.res.getString(C4560R.string.uat_addr_more).replace("[DOMAIN]", eaView.sDomain)};
                        if (dlTask instanceof AsyncTask) {
                            AsyncTaskInstrumentation.executeOnExecutor(dlTask, executor, strArr);
                        } else {
                            dlTask.executeOnExecutor(executor, strArr);
                        }
                    } else {
                        executor = AsyncTask.THREAD_POOL_EXECUTOR;
                        strArr = new String[]{eaView.this.res.getString(C4560R.string.url_addr_more).replace("[DOMAIN]", eaView.sDomain)};
                        if (dlTask instanceof AsyncTask) {
                            AsyncTaskInstrumentation.executeOnExecutor(dlTask, executor, strArr);
                        } else {
                            dlTask.executeOnExecutor(executor, strArr);
                        }
                    }
                    eaView.getStartupLoad();
                } else {
                    eaView.sAddressReturn.sAddress = new Address((ResponseAddr) eaView.sResponseQK.sResponseAddrs.get(position));
                    eaView.txtEAKeyword.setText(ResultAdapter.getDisplayAddrFrmRespAddr((ResponseAddr) eaView.sResponseQK.sResponseAddrs.get(position)));
                    TextView textView = eaView.lbKeyword;
                    SysParameter sysParameter = eaView.sSysParameter;
                    textView.setText(SysParameter.lbFormSelAddr);
                    eaView.btnClear.setVisibility(0);
                    eaView.btnSearch.setVisibility(8);
                    eaView.listResult.setVisibility(8);
                    eaView.txtEAKeyword.setEnabled(false);
                    eaView.txtEAKeyword.setFocusableInTouchMode(false);
                    eaView.txtEAKeyword.setFocusable(false);
                    eaView.sHandler.removeCallbacks(eaView.sRunnable);
                    if (((ResponseAddr) eaView.sResponseQK.sResponseAddrs.get(position)).sHub1.equals("")) {
                        ResponseTrigger.getAddrSelected("OUTBOUND");
                    } else {
                        ResponseTrigger.getAddrSelected("INBOUND");
                    }
                }
                eaView.txtEAKeyword.requestFocus();
                ((InputMethodManager) eaView.sContext.getSystemService("input_method")).showSoftInput(eaView.txtEAKeyword, 1);
            } catch (Exception ex) {
                if (BuildConfig.DEBUG) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public eaView(Context mContext) {
        super(mContext);
        sContext = mContext;
        sEABundle = sContext.getPackageName();
        sEAMode = "";
        sEACurX = Double.valueOf(0.0d);
        sEACurY = Double.valueOf(0.0d);
    }

    public eaView(Context mContext, AttributeSet sAttrs) {
        super(mContext, sAttrs);
        sContext = mContext;
        sEABundle = sContext.getPackageName();
        sEAMode = "";
        sEACurX = Double.valueOf(0.0d);
        sEACurY = Double.valueOf(0.0d);
    }

    public void loadEAView(String mKey, String mLang) {
        Executor executor;
        String[] strArr;
        eaNetErr = false;
        if (sEAUAT.equals(DCSProfile.INDICATOR_TRUE)) {
            sDomain = "192.1.1.121";
        } else {
            sDomain = "address.aisoft.hk";
        }
        DownloadUIAccess dlTask1 = new DownloadUIAccess(sEAKey);
        if (sEAUAT.equals(DCSProfile.INDICATOR_TRUE)) {
            executor = AsyncTask.THREAD_POOL_EXECUTOR;
            strArr = new String[]{this.res.getString(C4560R.string.uat_get_ui).replace("[DOMAIN]", sDomain)};
            if (dlTask1 instanceof AsyncTask) {
                AsyncTaskInstrumentation.executeOnExecutor(dlTask1, executor, strArr);
            } else {
                dlTask1.executeOnExecutor(executor, strArr);
            }
        } else {
            executor = AsyncTask.THREAD_POOL_EXECUTOR;
            strArr = new String[]{this.res.getString(C4560R.string.url_get_ui).replace("[DOMAIN]", sDomain)};
            if (dlTask1 instanceof AsyncTask) {
                AsyncTaskInstrumentation.executeOnExecutor(dlTask1, executor, strArr);
            } else {
                dlTask1.executeOnExecutor(executor, strArr);
            }
        }
        sEAKey = mKey;
        sEALang = mLang;
        if (sEAKey.equals("")) {
            Toast.makeText(sContext, "No Key is found", 1).show();
        } else if (!sEAKey.equals("g0nk7afhDhSUYFjkf78s")) {
            Toast.makeText(sContext, "Invalid Key is found", 1).show();
        } else if (sEALang.equals("zh-HK") || sEALang.equals("en-HK")) {
            SysParameter sysParameter;
            if (sEALang.equals("zh-HK")) {
                sysParameter = sSysParameter;
                SysParameter.msgWarnOOB = "輸入的地址不在送餐範圍內。";
                sysParameter = sSysParameter;
                SysParameter.lbFormKeyAddr = "送餐地址";
                sysParameter = sSysParameter;
                SysParameter.lbFormFloor = "樓層";
                sysParameter = sSysParameter;
                SysParameter.lbFormFlat = "單位";
                sysParameter = sSysParameter;
                SysParameter.lbFormNotice = "地址備注";
                sysParameter = sSysParameter;
                SysParameter.lbFormRemarks = "送餐要求";
                sysParameter = sSysParameter;
                SysParameter.msgLoading = "載入中…";
                lbNetworkErrorC = "無法連接，請稍後再試。";
            } else {
                sysParameter = sSysParameter;
                SysParameter.msgWarnOOB = "The address is not within our delivery zone.";
                sysParameter = sSysParameter;
                SysParameter.lbFormKeyAddr = "Address";
                sysParameter = sSysParameter;
                SysParameter.lbFormFloor = "Floor";
                sysParameter = sSysParameter;
                SysParameter.lbFormFlat = "Flat";
                sysParameter = sSysParameter;
                SysParameter.lbFormNotice = "Address Remarks";
                sysParameter = sSysParameter;
                SysParameter.lbFormRemarks = "Delivery Instructions";
                sysParameter = sSysParameter;
                SysParameter.msgLoading = "Loading…";
                lbNetworkErrorE = "No network connection available. Please check your connection status.";
            }
            DownloadParaAccess dlTask2 = new DownloadParaAccess(new C45711());
            if (sEAUAT.equals(DCSProfile.INDICATOR_TRUE)) {
                executor = AsyncTask.THREAD_POOL_EXECUTOR;
                strArr = new String[]{this.res.getString(C4560R.string.uat_get_para).replace("[DOMAIN]", sDomain)};
                if (dlTask2 instanceof AsyncTask) {
                    AsyncTaskInstrumentation.executeOnExecutor(dlTask2, executor, strArr);
                    return;
                } else {
                    dlTask2.executeOnExecutor(executor, strArr);
                    return;
                }
            }
            executor = AsyncTask.THREAD_POOL_EXECUTOR;
            strArr = new String[]{this.res.getString(C4560R.string.url_get_para).replace("[DOMAIN]", sDomain)};
            if (dlTask2 instanceof AsyncTask) {
                AsyncTaskInstrumentation.executeOnExecutor(dlTask2, executor, strArr);
            } else {
                dlTask2.executeOnExecutor(executor, strArr);
            }
        } else {
            Toast.makeText(sContext, "Invalid Language Setting", 1).show();
        }
    }

    public void setUAT(String sUAT) {
        sEAUAT = sUAT;
    }

    public void setMode(String sMode) {
        sEAMode = sMode;
    }

    public void setCurrentXY(Double sX, Double sY) {
        sEACurX = sX;
        sEACurY = sY;
    }

    public void setCurrentAddr(Address sAddress) {
        sEACurAddr = sAddress;
    }

    private void initialize() {
        eaView.inflate(sContext, C4560R.layout.ea_view, this);
        SysParameter sysParameter = sSysParameter;
        lbNoAddrFound = SysParameter.msgAddrNotFind;
        sysParameter = sSysParameter;
        lbNoGPSFound = SysParameter.msgLocNotFind;
        sListMode = "F";
        sCurrLot = "";
        sCurrDau = "";
        sAddressReturn = new AddressReturn();
        sCustomer = new Customer();
        webView = (WebView) findViewById(C4560R.C4559id.webView);
        llSearch = (LinearLayout) findViewById(C4560R.C4559id.llSearch);
        llAddrMisc = (LinearLayout) findViewById(C4560R.C4559id.llRowMisc);
        llAddrWarn = (LinearLayout) findViewById(C4560R.C4559id.llAddrWarn);
        llRowMiscScroll = (ScrollView) findViewById(C4560R.C4559id.llRowMiscScroll);
        llRowKey = (LinearLayout) findViewById(C4560R.C4559id.llRowKey);
        llRowKey.setOnClickListener(new C45742());
        llRowFloor = (LinearLayout) findViewById(C4560R.C4559id.llRowFloor);
        llRowFloor.setOnClickListener(new C45753());
        llRowFlat = (LinearLayout) findViewById(C4560R.C4559id.llRowFlat);
        llRowFlat.setOnClickListener(new C45764());
        llRowRemarks = (LinearLayout) findViewById(C4560R.C4559id.llRowRemarks);
        llRowRemarks.setOnClickListener(new C45775());
        lbKeyword = (TextView) findViewById(C4560R.C4559id.lbKeyword);
        lbAddrNotice = (TextView) findViewById(C4560R.C4559id.lbAddrNotice);
        lbAddrFloor = (TextView) findViewById(C4560R.C4559id.lbAddrFloor);
        lbAddrFlat = (TextView) findViewById(C4560R.C4559id.lbAddrFlat);
        lbAddrRemarks = (TextView) findViewById(C4560R.C4559id.lbAddrRemarks);
        lbLoading = (TextView) findViewById(C4560R.C4559id.lbLoading);
        lbAddrWarn = (TextView) findViewById(C4560R.C4559id.lbAddrWarn);
        TextView textView = lbAddrWarn;
        SysParameter sysParameter2 = sSysParameter;
        textView.setText(SysParameter.msgWarnOOB);
        textView = lbLoading;
        sysParameter2 = sSysParameter;
        textView.setText(SysParameter.msgLoading);
        textView = lbAddrNotice;
        sysParameter2 = sSysParameter;
        textView.setText(SysParameter.lbFormNotice);
        textView = lbAddrFloor;
        sysParameter2 = sSysParameter;
        textView.setText(SysParameter.lbFormFloor);
        lbAddrFloor.setOnFocusChangeListener(new C45786());
        textView = lbAddrFlat;
        sysParameter2 = sSysParameter;
        textView.setText(SysParameter.lbFormFlat);
        lbAddrFlat.setOnFocusChangeListener(new C45797());
        textView = lbAddrRemarks;
        sysParameter2 = sSysParameter;
        textView.setText(SysParameter.lbFormRemarks);
        lbAddrRemarks.setOnFocusChangeListener(new C45808());
        textView = lbKeyword;
        sysParameter2 = sSysParameter;
        textView.setText(SysParameter.lbFormKeyAddr);
        txtAddrNotice = (TextView) findViewById(C4560R.C4559id.txtAddrNotice);
        txtAddrFloor = (EditText) findViewById(C4560R.C4559id.txtAddrFloor);
        txtAddrFlat = (EditText) findViewById(C4560R.C4559id.txtAddrFlat);
        txtAddrRemarks = (EditText) findViewById(C4560R.C4559id.txtAddrRemarks);
        listResult = (ListView) findViewById(C4560R.C4559id.listResult);
        listResult.setVisibility(8);
        listResult.setOnItemClickListener(new C45819());
        listResult.setOnScrollListener(new C456110());
        sRunnable = new C456211();
        sTimeoutSearchRun = new C456312();
        sTimeoutVerifyRun = new C456413();
        sTimeoutGPSRun = new C456514();
        txtEAKeyword = (EditText) findViewById(C4560R.C4559id.txtEAKeyword);
        txtEAKeyword.addTextChangedListener(new C456615());
        txtEAKeyword.setOnKeyListener(new C456716());
        EditText editText = txtEAKeyword;
        sysParameter2 = sSysParameter;
        editText.setHint(SysParameter.lbKeyInputHints);
        btnSearch = (ImageButton) findViewById(C4560R.C4559id.btnSearch);
        btnSearch.setBackgroundColor(0);
        btnSearch.setOnClickListener(new C456817());
        btnClear = (ImageButton) findViewById(C4560R.C4559id.btnClear);
        btnClear.setBackgroundColor(0);
        btnClear.setVisibility(8);
        btnClear.setOnClickListener(new C456918());
        btnClear.callOnClick();
        btnGPS = (ImageButton) findViewById(C4560R.C4559id.btnGPS);
        btnGPS.setBackgroundColor(0);
        btnGPS.setVisibility(8);
        btnGPS.setOnClickListener(new C457019());
        if (sEACurAddr != null) {
            sListMode = "M";
            getVerify(sEACurAddr);
            eaView.getStartupLoad();
        } else if (sEACurX.doubleValue() != 0.0d || sEACurY.doubleValue() != 0.0d) {
            sGPSX = sEACurX.doubleValue();
            sGPSY = sEACurY.doubleValue();
            sListMode = "F";
            ResponseTrigger.btnGPSPressAction();
            eaView.getStartupGPSLoad();
        } else if (ContextCompat.checkSelfPermission(sContext, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            try {
                sResponse = "";
                sLocationListener = new GPSListener();
                sLocationManager = (LocationManager) sContext.getSystemService(LocationManagerProxy.KEY_LOCATION_CHANGED);
                if (sLocationManager != null) {
                    sLocation = sLocationManager.getLastKnownLocation(LocationManagerProxy.NETWORK_PROVIDER);
                    if (sLocation != null) {
                        sGPSY = sLocation.getLatitude();
                        sGPSX = sLocation.getLongitude();
                        if (sEAMode.equals("GPS")) {
                            sListMode = "F";
                            ResponseTrigger.btnGPSPressAction();
                            eaView.getStartupGPSLoad();
                        }
                    }
                }
                sLocationManager.requestLocationUpdates(LocationManagerProxy.NETWORK_PROVIDER, 1000, 10.0f, sLocationListener);
                sLocationManager.requestLocationUpdates("gps", 1000, 10.0f, sLocationListener);
            } catch (Exception e) {
            }
        } else {
            Toast.makeText(sContext, SysParameter.mgsGPSNotSupport, 0).show();
        }
    }

    private static void getStartupLoad() {
        Context context = sContext;
        SysParameter sysParameter = sSysParameter;
        String str = SysParameter.msgLoading;
        SysParameter sysParameter2 = sSysParameter;
        final ProgressDialog sProgressDialog = ProgressDialog.show(context, str, SysParameter.msgPleaseWait, true);
        sProgressDialog.setCancelable(true);
        new Thread(new Runnable() {
            public void run() {
                do {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                } while (eaView.sResponse.equals(""));
                sProgressDialog.dismiss();
            }
        }).start();
    }

    private static void getStartupGPSLoad() {
        Context context = sContext;
        SysParameter sysParameter = sSysParameter;
        String str = SysParameter.msgLoadingGPS;
        SysParameter sysParameter2 = sSysParameter;
        final ProgressDialog sProgressDialog = ProgressDialog.show(context, str, SysParameter.msgPleaseWait, true);
        sProgressDialog.setCancelable(true);
        new Thread(new Runnable() {
            public void run() {
                do {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                } while (eaView.sResponse.equals(""));
                sProgressDialog.dismiss();
            }
        }).start();
    }

    public void getVerify(Address mAddr) {
        sReqBack = false;
        sResponseQK = null;
        sResponseAM = null;
        sEATimeoutCnt = 0;
        DownloadManualAddr dlTask = new DownloadManualAddr(mAddr);
        Executor executor;
        String[] strArr;
        if (sEAUAT.equals(DCSProfile.INDICATOR_TRUE)) {
            executor = AsyncTask.THREAD_POOL_EXECUTOR;
            strArr = new String[]{this.res.getString(C4560R.string.uat_get_manual).replace("[DOMAIN]", sDomain)};
            if (dlTask instanceof AsyncTask) {
                AsyncTaskInstrumentation.executeOnExecutor(dlTask, executor, strArr);
            } else {
                dlTask.executeOnExecutor(executor, strArr);
            }
        } else {
            executor = AsyncTask.THREAD_POOL_EXECUTOR;
            strArr = new String[]{this.res.getString(C4560R.string.url_get_manual).replace("[DOMAIN]", sDomain)};
            if (dlTask instanceof AsyncTask) {
                AsyncTaskInstrumentation.executeOnExecutor(dlTask, executor, strArr);
            } else {
                dlTask.executeOnExecutor(executor, strArr);
            }
        }
        sHandler.removeCallbacks(sTimeoutVerifyRun);
        sHandler.postDelayed(sTimeoutVerifyRun, 500);
    }

    public void getStore(Address sAddress, GetStoreCallbackInterface mGetStoreCallbackInterface) {
        sGetStoreCallbackInterface = mGetStoreCallbackInterface;
        if (sEAUAT.equals(DCSProfile.INDICATOR_TRUE)) {
            sDomain = "192.1.1.121";
        } else {
            sDomain = "address.aisoft.hk";
        }
        DownloadGSSearch dlTask = new DownloadGSSearch(sAddress);
        Executor executor;
        String[] strArr;
        if (sEAUAT.equals(DCSProfile.INDICATOR_TRUE)) {
            executor = AsyncTask.THREAD_POOL_EXECUTOR;
            strArr = new String[]{this.res.getString(C4560R.string.uat_get_store).replace("[DOMAIN]", sDomain)};
            if (dlTask instanceof AsyncTask) {
                AsyncTaskInstrumentation.executeOnExecutor(dlTask, executor, strArr);
                return;
            } else {
                dlTask.executeOnExecutor(executor, strArr);
                return;
            }
        }
        executor = AsyncTask.THREAD_POOL_EXECUTOR;
        strArr = new String[]{this.res.getString(C4560R.string.url_get_store).replace("[DOMAIN]", sDomain)};
        if (dlTask instanceof AsyncTask) {
            AsyncTaskInstrumentation.executeOnExecutor(dlTask, executor, strArr);
        } else {
            dlTask.executeOnExecutor(executor, strArr);
        }
    }

    public void setEditCallBack(EditCallbackInterface mEditCallbackInterface) {
        sEditCallbackInterface = mEditCallbackInterface;
    }

    public void setSaveCallBack(SaveCallbackInterface mSaveCallbackInterface) {
        sSaveCallbackInterface = mSaveCallbackInterface;
    }

    public void setCompleteCallBack(CompleteCallbackInterface mCompleteCallbackInterface) {
        sCompleteCallbackInterface = mCompleteCallbackInterface;
    }

    public void setConfirmCallBack(ConfirmCallbackInterface mConfirmCallbackInterface) {
        sConfirmCallbackInterface = mConfirmCallbackInterface;
    }

    static String random(int sLen) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < sLen; i++) {
            sb.append(chars[random.nextInt(chars.length)]);
        }
        return sb.toString();
    }

    public static int LevenshteinDistance(CharSequence lhs, CharSequence rhs) {
        int i;
        int len0 = lhs.length() + 1;
        int len1 = rhs.length() + 1;
        int[] cost = new int[len0];
        int[] newcost = new int[len0];
        for (i = 0; i < len0; i++) {
            cost[i] = i;
        }
        for (int j = 1; j < len1; j++) {
            newcost[0] = j;
            for (i = 1; i < len0; i++) {
                newcost[i] = Math.min(Math.min(cost[i] + 1, newcost[i - 1] + 1), cost[i - 1] + (lhs.charAt(i + -1) == rhs.charAt(j + -1) ? 0 : 1));
            }
            int[] swap = cost;
            cost = newcost;
            newcost = swap;
        }
        return cost[len0 - 1];
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:25:0x013b=Splitter:B:25:0x013b, B:43:0x020e=Splitter:B:43:0x020e} */
    public static boolean btnSavePressAction() {
        /*
        r9 = 0;
        r10 = eaNetErr;
        if (r10 == 0) goto L_0x0027;
    L_0x0005:
        r10 = sEALang;
        r11 = "zh-HK";
        r10 = r10.equals(r11);
        if (r10 == 0) goto L_0x001b;
    L_0x000f:
        r10 = sContext;
        r11 = lbNetworkErrorC;
        r10 = android.widget.Toast.makeText(r10, r11, r9);
        r10.show();
    L_0x001a:
        return r9;
    L_0x001b:
        r10 = sContext;
        r11 = lbNetworkErrorE;
        r10 = android.widget.Toast.makeText(r10, r11, r9);
        r10.show();
        goto L_0x001a;
    L_0x0027:
        r10 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sCode;	 Catch:{ Exception -> 0x01de }
        r11 = "1";
        r10 = r10.equals(r11);	 Catch:{ Exception -> 0x01de }
        if (r10 == 0) goto L_0x01ee;
    L_0x0033:
        r10 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sAddress;	 Catch:{ Exception -> 0x01de }
        r11 = txtAddrFloor;	 Catch:{ Exception -> 0x01de }
        r11 = r11.getText();	 Catch:{ Exception -> 0x01de }
        r11 = r11.toString();	 Catch:{ Exception -> 0x01de }
        r10.sFloor = r11;	 Catch:{ Exception -> 0x01de }
        r10 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sAddress;	 Catch:{ Exception -> 0x01de }
        r11 = txtAddrFlat;	 Catch:{ Exception -> 0x01de }
        r11 = r11.getText();	 Catch:{ Exception -> 0x01de }
        r11 = r11.toString();	 Catch:{ Exception -> 0x01de }
        r10.sFlat = r11;	 Catch:{ Exception -> 0x01de }
        r10 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sAddress;	 Catch:{ Exception -> 0x01de }
        r11 = txtAddrRemarks;	 Catch:{ Exception -> 0x01de }
        r11 = r11.getText();	 Catch:{ Exception -> 0x01de }
        r11 = r11.toString();	 Catch:{ Exception -> 0x01de }
        r10.sRemarks = r11;	 Catch:{ Exception -> 0x01de }
        r8 = "號";
        r2 = "座";
        r3 = "BLOCK";
        r6 = "樓";
        r7 = "FLOOR";
        r4 = "室";
        r5 = "FLAT";
        r10 = sEALang;	 Catch:{ Exception -> 0x01de }
        r11 = "zh-HK";
        r10 = r10.equals(r11);	 Catch:{ Exception -> 0x01de }
        if (r10 == 0) goto L_0x0149;
    L_0x007b:
        r10 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sAddress;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sStreetNo;	 Catch:{ Exception -> 0x01de }
        r11 = "";
        r10 = r10.equals(r11);	 Catch:{ Exception -> 0x01de }
        if (r10 != 0) goto L_0x00a6;
    L_0x0089:
        r10 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sAddress;	 Catch:{ Exception -> 0x01de }
        r11 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01de }
        r11.<init>();	 Catch:{ Exception -> 0x01de }
        r12 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r12 = r12.sAddress;	 Catch:{ Exception -> 0x01de }
        r12 = r12.sStreetNo;	 Catch:{ Exception -> 0x01de }
        r11 = r11.append(r12);	 Catch:{ Exception -> 0x01de }
        r11 = r11.append(r8);	 Catch:{ Exception -> 0x01de }
        r11 = r11.toString();	 Catch:{ Exception -> 0x01de }
        r10.sStreetNo = r11;	 Catch:{ Exception -> 0x01de }
    L_0x00a6:
        r10 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sAddress;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sBlockC;	 Catch:{ Exception -> 0x01de }
        r11 = "";
        r10 = r10.equals(r11);	 Catch:{ Exception -> 0x01de }
        if (r10 != 0) goto L_0x00d1;
    L_0x00b4:
        r10 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sAddress;	 Catch:{ Exception -> 0x01de }
        r11 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01de }
        r11.<init>();	 Catch:{ Exception -> 0x01de }
        r12 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r12 = r12.sAddress;	 Catch:{ Exception -> 0x01de }
        r12 = r12.sBlockC;	 Catch:{ Exception -> 0x01de }
        r11 = r11.append(r12);	 Catch:{ Exception -> 0x01de }
        r11 = r11.append(r2);	 Catch:{ Exception -> 0x01de }
        r11 = r11.toString();	 Catch:{ Exception -> 0x01de }
        r10.sBlockC = r11;	 Catch:{ Exception -> 0x01de }
    L_0x00d1:
        r10 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sAddress;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sFloor;	 Catch:{ Exception -> 0x01de }
        r11 = "";
        r10 = r10.equals(r11);	 Catch:{ Exception -> 0x01de }
        if (r10 != 0) goto L_0x00fc;
    L_0x00df:
        r10 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sAddress;	 Catch:{ Exception -> 0x01de }
        r11 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01de }
        r11.<init>();	 Catch:{ Exception -> 0x01de }
        r12 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r12 = r12.sAddress;	 Catch:{ Exception -> 0x01de }
        r12 = r12.sFloor;	 Catch:{ Exception -> 0x01de }
        r11 = r11.append(r12);	 Catch:{ Exception -> 0x01de }
        r11 = r11.append(r6);	 Catch:{ Exception -> 0x01de }
        r11 = r11.toString();	 Catch:{ Exception -> 0x01de }
        r10.sFloor = r11;	 Catch:{ Exception -> 0x01de }
    L_0x00fc:
        r10 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sAddress;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sFlat;	 Catch:{ Exception -> 0x01de }
        r11 = "";
        r10 = r10.equals(r11);	 Catch:{ Exception -> 0x01de }
        if (r10 != 0) goto L_0x0127;
    L_0x010a:
        r10 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sAddress;	 Catch:{ Exception -> 0x01de }
        r11 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01de }
        r11.<init>();	 Catch:{ Exception -> 0x01de }
        r12 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r12 = r12.sAddress;	 Catch:{ Exception -> 0x01de }
        r12 = r12.sFlat;	 Catch:{ Exception -> 0x01de }
        r11 = r11.append(r12);	 Catch:{ Exception -> 0x01de }
        r11 = r11.append(r4);	 Catch:{ Exception -> 0x01de }
        r11 = r11.toString();	 Catch:{ Exception -> 0x01de }
        r10.sFlat = r11;	 Catch:{ Exception -> 0x01de }
    L_0x0127:
        r10 = sContext;	 Catch:{ Exception -> 0x0230 }
        r11 = "input_method";
        r1 = r10.getSystemService(r11);	 Catch:{ Exception -> 0x0230 }
        r1 = (android.view.inputmethod.InputMethodManager) r1;	 Catch:{ Exception -> 0x0230 }
        r10 = txtEAKeyword;	 Catch:{ Exception -> 0x0230 }
        r10 = r10.getWindowToken();	 Catch:{ Exception -> 0x0230 }
        r11 = 0;
        r1.hideSoftInputFromWindow(r10, r11);	 Catch:{ Exception -> 0x0230 }
    L_0x013b:
        r10 = sConfirmCallbackInterface;	 Catch:{ Exception -> 0x01de }
        r11 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r11 = r11.sAddress;	 Catch:{ Exception -> 0x01de }
        r12 = sCustomer;	 Catch:{ Exception -> 0x01de }
        r10.onConfirmPress(r11, r12);	 Catch:{ Exception -> 0x01de }
        r9 = 1;
        goto L_0x001a;
    L_0x0149:
        r10 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sAddress;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sBlockE;	 Catch:{ Exception -> 0x01de }
        r11 = "";
        r10 = r10.equals(r11);	 Catch:{ Exception -> 0x01de }
        if (r10 != 0) goto L_0x017a;
    L_0x0157:
        r10 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sAddress;	 Catch:{ Exception -> 0x01de }
        r11 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01de }
        r11.<init>();	 Catch:{ Exception -> 0x01de }
        r11 = r11.append(r3);	 Catch:{ Exception -> 0x01de }
        r12 = " ";
        r11 = r11.append(r12);	 Catch:{ Exception -> 0x01de }
        r12 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r12 = r12.sAddress;	 Catch:{ Exception -> 0x01de }
        r12 = r12.sBlockE;	 Catch:{ Exception -> 0x01de }
        r11 = r11.append(r12);	 Catch:{ Exception -> 0x01de }
        r11 = r11.toString();	 Catch:{ Exception -> 0x01de }
        r10.sBlockE = r11;	 Catch:{ Exception -> 0x01de }
    L_0x017a:
        r10 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sAddress;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sFloor;	 Catch:{ Exception -> 0x01de }
        r11 = "";
        r10 = r10.equals(r11);	 Catch:{ Exception -> 0x01de }
        if (r10 != 0) goto L_0x01ab;
    L_0x0188:
        r10 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sAddress;	 Catch:{ Exception -> 0x01de }
        r11 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01de }
        r11.<init>();	 Catch:{ Exception -> 0x01de }
        r11 = r11.append(r7);	 Catch:{ Exception -> 0x01de }
        r12 = " ";
        r11 = r11.append(r12);	 Catch:{ Exception -> 0x01de }
        r12 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r12 = r12.sAddress;	 Catch:{ Exception -> 0x01de }
        r12 = r12.sFloor;	 Catch:{ Exception -> 0x01de }
        r11 = r11.append(r12);	 Catch:{ Exception -> 0x01de }
        r11 = r11.toString();	 Catch:{ Exception -> 0x01de }
        r10.sFloor = r11;	 Catch:{ Exception -> 0x01de }
    L_0x01ab:
        r10 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sAddress;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sFlat;	 Catch:{ Exception -> 0x01de }
        r11 = "";
        r10 = r10.equals(r11);	 Catch:{ Exception -> 0x01de }
        if (r10 != 0) goto L_0x0127;
    L_0x01b9:
        r10 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sAddress;	 Catch:{ Exception -> 0x01de }
        r11 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01de }
        r11.<init>();	 Catch:{ Exception -> 0x01de }
        r11 = r11.append(r5);	 Catch:{ Exception -> 0x01de }
        r12 = " ";
        r11 = r11.append(r12);	 Catch:{ Exception -> 0x01de }
        r12 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r12 = r12.sAddress;	 Catch:{ Exception -> 0x01de }
        r12 = r12.sFlat;	 Catch:{ Exception -> 0x01de }
        r11 = r11.append(r12);	 Catch:{ Exception -> 0x01de }
        r11 = r11.toString();	 Catch:{ Exception -> 0x01de }
        r10.sFlat = r11;	 Catch:{ Exception -> 0x01de }
        goto L_0x0127;
    L_0x01de:
        r0 = move-exception;
        r10 = sContext;
        r11 = sSysParameter;
        r11 = p049hk.com.aisoft.easyaddrui.SysParameter.msgNoAddrSel;
        r10 = android.widget.Toast.makeText(r10, r11, r9);
        r10.show();
        goto L_0x001a;
    L_0x01ee:
        r10 = sAddressReturn;	 Catch:{ Exception -> 0x01de }
        r10 = r10.sCode;	 Catch:{ Exception -> 0x01de }
        r11 = "-4005";
        r10 = r10.equals(r11);	 Catch:{ Exception -> 0x01de }
        if (r10 == 0) goto L_0x021e;
    L_0x01fa:
        r10 = sContext;	 Catch:{ Exception -> 0x022e }
        r11 = "input_method";
        r1 = r10.getSystemService(r11);	 Catch:{ Exception -> 0x022e }
        r1 = (android.view.inputmethod.InputMethodManager) r1;	 Catch:{ Exception -> 0x022e }
        r10 = txtEAKeyword;	 Catch:{ Exception -> 0x022e }
        r10 = r10.getWindowToken();	 Catch:{ Exception -> 0x022e }
        r11 = 0;
        r1.hideSoftInputFromWindow(r10, r11);	 Catch:{ Exception -> 0x022e }
    L_0x020e:
        r10 = sContext;	 Catch:{ Exception -> 0x01de }
        r11 = sSysParameter;	 Catch:{ Exception -> 0x01de }
        r11 = p049hk.com.aisoft.easyaddrui.SysParameter.msgAddrOOB;	 Catch:{ Exception -> 0x01de }
        r12 = 0;
        r10 = android.widget.Toast.makeText(r10, r11, r12);	 Catch:{ Exception -> 0x01de }
        r10.show();	 Catch:{ Exception -> 0x01de }
        goto L_0x001a;
    L_0x021e:
        r10 = sContext;	 Catch:{ Exception -> 0x01de }
        r11 = sSysParameter;	 Catch:{ Exception -> 0x01de }
        r11 = p049hk.com.aisoft.easyaddrui.SysParameter.msgNoAddrSel;	 Catch:{ Exception -> 0x01de }
        r12 = 0;
        r10 = android.widget.Toast.makeText(r10, r11, r12);	 Catch:{ Exception -> 0x01de }
        r10.show();	 Catch:{ Exception -> 0x01de }
        goto L_0x001a;
    L_0x022e:
        r10 = move-exception;
        goto L_0x020e;
    L_0x0230:
        r10 = move-exception;
        goto L_0x013b;
        */
        throw new UnsupportedOperationException("Method not decompiled: p049hk.com.aisoft.easyaddrui.eaView.btnSavePressAction():boolean");
    }

    public static boolean btnCompletePressAction() {
        return true;
    }

    public static boolean btnConfirmPressAction() {
        return true;
    }
}
