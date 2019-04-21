package com.mcdonalds.app.ordering;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ensighten.Ensighten;
import com.google.gson.Gson;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.model.PaymentResult;
import com.mcdonalds.app.model.PaymentResultResponse;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderPayment;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThirdPartActivity extends URLActionBarActivity {
    public static final String LOG_TAG = ThirdPartActivity.class.getSimpleName();
    private Order mOrder;
    private final WebChromeClient mWebChromeClient = new C33934();
    private final WebViewClient mWebViewClient = new C33923();

    private class PaymentInterface {
        private PaymentInterface() {
        }

        /* synthetic */ PaymentInterface(ThirdPartActivity x0, C33891 x1) {
            this();
        }

        @JavascriptInterface
        public void onPaymentResult(String result) {
            Ensighten.evaluateEvent(this, "onPaymentResult", new Object[]{result});
            if (result != null) {
                ThirdPartActivity.access$300(ThirdPartActivity.this, result);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.ThirdPartActivity$1 */
    class C33891 implements Listener<String> {
        C33891() {
        }

        public void onResponse(String s) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{s});
            ThirdPartActivity.access$100(ThirdPartActivity.this, s);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.ThirdPartActivity$2 */
    class C33912 implements ErrorListener {

        /* renamed from: com.mcdonalds.app.ordering.ThirdPartActivity$2$1 */
        class C33901 implements OnClickListener {
            C33901() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                ThirdPartActivity.this.setResult(0);
                ThirdPartActivity.this.finish();
            }
        }

        C33912() {
        }

        public void onErrorResponse(VolleyError volleyError) {
            Ensighten.evaluateEvent(this, "onErrorResponse", new Object[]{volleyError});
            MCDLog.error(ThirdPartActivity.LOG_TAG, "error", volleyError);
            MCDAlertDialogBuilder.withContext(ThirdPartActivity.this).setCancelable(false).setTitle((int) C2658R.string.network_error_title).setMessage((int) C2658R.string.payment_error_message).setPositiveButton((int) C2658R.string.f6083ok, new C33901()).create().show();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.ThirdPartActivity$3 */
    class C33923 extends WebViewClient {
        C33923() {
        }

        public void onPageFinished(WebView view, String url) {
            Ensighten.evaluateEvent(this, "onPageFinished", new Object[]{view, url});
            super.onPageFinished(view, url);
            if (!TextUtils.isEmpty(url)) {
                Uri uri = Uri.parse(url);
                if (uri.getPath().contains("/Payment/PaymentReturn")) {
                    ThirdPartActivity.access$200(ThirdPartActivity.this, uri);
                }
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.ThirdPartActivity$4 */
    class C33934 extends WebChromeClient {
        private int lastProgress = 0;

        C33934() {
        }

        public void onProgressChanged(WebView view, int newProgress) {
            Ensighten.evaluateEvent(this, "onProgressChanged", new Object[]{view, new Integer(newProgress)});
            if (this.lastProgress == 0) {
                UIUtils.startActivityIndicator(ThirdPartActivity.this, (int) C2658R.string.label_progress_loading);
            }
            this.lastProgress = newProgress;
            if (newProgress >= 100) {
                UIUtils.stopActivityIndicator();
                this.lastProgress = 0;
            }
        }
    }

    static /* synthetic */ void access$100(ThirdPartActivity x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ThirdPartActivity", "access$100", new Object[]{x0, x1});
        x0.parseResultsHtml(x1);
    }

    static /* synthetic */ void access$200(ThirdPartActivity x0, Uri x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ThirdPartActivity", "access$200", new Object[]{x0, x1});
        x0.loadResultsURI(x1);
    }

    static /* synthetic */ void access$300(ThirdPartActivity x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ThirdPartActivity", "access$300", new Object[]{x0, x1});
        x0.processHiddenJsonData(x1);
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String url = extras.getString("payment_url");
            this.mOrder = OrderingManager.getInstance().getCurrentOrder();
            setContentView((int) C2658R.layout.fragment_third_part);
            WebView webView = (WebView) findViewById(C2358R.C2357id.webview);
            webView.setWebViewClient(this.mWebViewClient);
            webView.setWebChromeClient(this.mWebChromeClient);
            if (webView.getSettings() != null) {
                webView.getSettings().setBuiltInZoomControls(false);
                webView.getSettings().setDisplayZoomControls(false);
                webView.getSettings().setJavaScriptEnabled(true);
            }
            webView.addJavascriptInterface(new PaymentInterface(this, null), "android");
            webView.loadUrl(url);
        }
    }

    private void loadResultsURI(Uri resultsUri) {
        Ensighten.evaluateEvent(this, "loadResultsURI", new Object[]{resultsUri});
        Volley.newRequestQueue(this).add(new StringRequest(0, resultsUri.toString(), new C33891(), new C33912()));
    }

    private void parseResultsHtml(String resultsHtml) {
        Ensighten.evaluateEvent(this, "parseResultsHtml", new Object[]{resultsHtml});
        if (resultsHtml != null) {
            Matcher matcher = Pattern.compile("<input type=\"hidden\" name=\"hiddenResult\" id=\"hiddenResult\" value=\"(.*)\"").matcher(resultsHtml);
            if (matcher.find()) {
                processHiddenJsonData(matcher.group(1));
            }
        }
    }

    private void processHiddenJsonData(String result) {
        Ensighten.evaluateEvent(this, "processHiddenJsonData", new Object[]{result});
        String jsonString = Html.fromHtml(result).toString();
        if (!TextUtils.isEmpty(jsonString)) {
            Gson gson = new Gson();
            Class cls = PaymentResultResponse.class;
            PaymentResult paymentResult = (!(gson instanceof Gson) ? gson.fromJson(jsonString, cls) : GsonInstrumentation.fromJson(gson, jsonString, cls)).getData();
            if (paymentResult != null) {
                OrderPayment payment = this.mOrder.getPayment();
                payment.setOrderPaymentId(paymentResult.getOrderNumber());
                payment.setPaymentDataId(paymentResult.getPaymentDataId());
                payment.setCustomerPaymentMethodId(paymentResult.getCutomerPaymentMethodId());
                setResult(-1);
                finish();
            }
        }
    }
}
