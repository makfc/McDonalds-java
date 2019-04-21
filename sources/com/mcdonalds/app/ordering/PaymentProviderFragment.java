package com.mcdonalds.app.ordering;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import java.net.MalformedURLException;
import java.net.URL;

public class PaymentProviderFragment extends URLNavigationFragment {
    public static final String NAME = PaymentProviderFragment.class.getSimpleName();
    private boolean mOneTimePayment = false;
    private int mPaymentID;
    private boolean mPreferredCardDialogShown;
    private String mRegisterReturnURL;
    private String mURL;
    private WebView mWebView;

    private class JsObject {
        private JsObject() {
        }

        /* synthetic */ JsObject(PaymentProviderFragment x0, C33781 x1) {
            this();
        }

        @JavascriptInterface
        public void processResult(String html) {
            Ensighten.evaluateEvent(this, "processResult", new Object[]{html});
            if (html != null && html.length() > 0) {
                String jsonCardInfo = Html.fromHtml(html).toString();
                CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
                if (customerModule != null && PaymentProviderFragment.this.getNavigationActivity() != null) {
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.PaymentProviderFragment", "access$300", new Object[]{PaymentProviderFragment.this})) {
                        UIUtils.stopActivityIndicator();
                        Intent resultData = new Intent();
                        resultData.putExtra("EXTRA_ONE_TIME_PAYMENT", jsonCardInfo);
                        PaymentProviderFragment.this.getActivity().setResult(-1, resultData);
                        PaymentProviderFragment.this.getActivity().finish();
                        return;
                    }
                    PaymentProviderFragment.access$400(PaymentProviderFragment.this, customerModule, jsonCardInfo);
                }
            } else if (ConfigurationUtils.isOneClickPaymentFlow()) {
                DataLayerManager.getInstance().recordError("RegisterRequest Error = response is empty");
                PaymentProviderFragment.this.getActivity().setResult(15521);
                PaymentProviderFragment.this.getActivity().finish();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.PaymentProviderFragment$1 */
    class C33781 extends WebChromeClient {
        private int lastProgress = 0;

        C33781() {
        }

        public void onProgressChanged(WebView view, int newProgress) {
            Ensighten.evaluateEvent(this, "onProgressChanged", new Object[]{view, new Integer(newProgress)});
            if (this.lastProgress == 0) {
                PaymentProviderActivity.enableBackButton = false;
                UIUtils.startActivityIndicator(PaymentProviderFragment.this.getActivity(), null, PaymentProviderFragment.this.getString(C2658R.string.title_loading), false);
            }
            this.lastProgress = newProgress;
            if (newProgress >= 100) {
                UIUtils.stopActivityIndicator();
                this.lastProgress = 0;
                PaymentProviderActivity.enableBackButton = true;
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.PaymentProviderFragment$2 */
    class C33792 extends WebViewClient {
        C33792() {
        }

        public void onPageFinished(WebView view, String url) {
            Ensighten.evaluateEvent(this, "onPageFinished", new Object[]{view, url});
            if (!ConfigurationUtils.isOneClickPaymentFlow()) {
                view.loadUrl("javascript:paymentBridge.processResult((window.document.getElementById('hiddenResult')) ? window.document.getElementById('hiddenResult').value : null);");
            } else if (url.startsWith(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.PaymentProviderFragment", "access$000", new Object[]{PaymentProviderFragment.this}))) {
                view.loadUrl("javascript:paymentBridge.processResult((window.document.getElementById('hiddenResult')) ? window.document.getElementById('hiddenResult').value : null);");
            }
        }

        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            Ensighten.evaluateEvent(this, "onReceivedHttpError", new Object[]{view, request, errorResponse});
            super.onReceivedHttpError(view, request, errorResponse);
            String statusCode = "";
            if (VERSION.SDK_INT >= 21) {
                statusCode = String.valueOf(errorResponse.getStatusCode());
            }
            DataLayerManager.getInstance().recordError("URL load error = " + statusCode);
            PaymentProviderFragment.this.getActivity().setResult(15521);
            PaymentProviderFragment.this.getActivity().finish();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.PaymentProviderFragment$3 */
    class C33803 implements AsyncListener<String> {
        C33803() {
        }

        public void onResponse(String response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (PaymentProviderFragment.this.getNavigationActivity() != null) {
                UIUtils.stopActivityIndicator();
                URL url = null;
                if (response != null) {
                    try {
                        url = new URL(response);
                    } catch (MalformedURLException e) {
                        exception = new AsyncException("Invalid Payment Provider URL");
                    }
                }
                if (url != null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.PaymentProviderFragment", "access$200", new Object[]{PaymentProviderFragment.this}).loadUrl(url.toString());
                } else if (exception != null) {
                    AsyncException.report(exception);
                }
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.PaymentProviderFragment$4 */
    class C33824 implements AsyncListener<CustomerProfile> {

        /* renamed from: com.mcdonalds.app.ordering.PaymentProviderFragment$4$1 */
        class C33811 implements OnClickListener {
            C33811() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                AsyncException.report(new AsyncException(PaymentProviderFragment.this.getString(C2658R.string.error_updating_payment)));
                PaymentProviderFragment.this.getActivity().setResult(0);
                PaymentProviderFragment.this.getActivity().finish();
            }
        }

        C33824() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (PaymentProviderFragment.this.getActivity() == null) {
                return;
            }
            if (response == null || exception != null) {
                UIUtils.stopActivityIndicator();
                MCDAlertDialogBuilder builder = MCDAlertDialogBuilder.withContext(PaymentProviderFragment.this.getNavigationActivity());
                if (exception != null) {
                    builder.setMessage(exception.getLocalizedMessage());
                } else {
                    builder.setMessage((int) C2658R.string.error_updating_payment);
                }
                builder.setPositiveButton((int) C2658R.string.f6083ok, new C33811()).create().show();
                return;
            }
            PaymentProviderFragment.this.getActivity().setResult(-1);
            UIUtils.stopActivityIndicator();
            PaymentProviderFragment.this.getActivity().finish();
        }
    }

    static /* synthetic */ void access$400(PaymentProviderFragment x0, CustomerModule x1, String x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.PaymentProviderFragment", "access$400", new Object[]{x0, x1, x2});
        x0.updatePayment(x1, x2);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_checkout_cards_add);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mOneTimePayment = getArguments().getBoolean("EXTRA_ONE_TIME_PAYMENT");
            this.mPaymentID = getArguments().getInt("EXTRA_PAYMENT_ID");
            this.mURL = getArguments().getString("EXTRA_URL");
            this.mRegisterReturnURL = getArguments().getString("EXTRA_REGISTER_RETURN_URL");
        }
        this.mPreferredCardDialogShown = false;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_payment_provider, container, false);
        this.mWebView = (WebView) rootView.findViewById(C2358R.C2357id.main_webview);
        if (this.mWebView != null) {
            this.mWebView.getSettings().setLoadWithOverviewMode(true);
            this.mWebView.getSettings().setBuiltInZoomControls(true);
            this.mWebView.getSettings().setDisplayZoomControls(false);
            this.mWebView.getSettings().setUseWideViewPort(true);
            this.mWebView.getSettings().setJavaScriptEnabled(true);
            this.mWebView.setWebChromeClient(new C33781());
            this.mWebView.setWebViewClient(new C33792());
            this.mWebView.addJavascriptInterface(new JsObject(this, null), "paymentBridge");
            if (this.mURL != null) {
                this.mWebView.loadUrl(this.mURL);
            } else {
                UIUtils.startActivityIndicator(getActivity(), (int) C2658R.string.label_processing);
                ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getPaymentTypeRegistrationURL(this.mPaymentID, Boolean.valueOf(this.mOneTimePayment), new C33803());
            }
        }
        return rootView;
    }

    private void updatePayment(CustomerModule customerModule, String jsonCardInfo) {
        Ensighten.evaluateEvent(this, "updatePayment", new Object[]{customerModule, jsonCardInfo});
        UIUtils.startActivityIndicator(getContext(), (int) C2658R.string.label_processing);
        customerModule.updatePayment(jsonCardInfo, false, new C33824());
    }
}
