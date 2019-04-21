package com.mcdonalds.app.ordering;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.ensighten.Ensighten;
import com.facebook.stetho.common.Utf8Charset;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.DataPasser;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class MPGSPaymentFragment extends URLNavigationFragment {
    public static final String NAME = MPGSPaymentFragment.class.getSimpleName();
    private String mNickName;
    private boolean mOneTimePayment = false;
    private int mPaymentID;
    private PaymentTypeRegisterData mPaymentTypeRegisterData;
    private WebView mWebView;

    private class JsObject {
        private JsObject() {
        }

        /* synthetic */ JsObject(MPGSPaymentFragment x0, C33671 x1) {
            this();
        }

        @JavascriptInterface
        public void onSessionUpdate(String sessionId, int errCode, String scheme, String nickname) {
            Ensighten.evaluateEvent(this, "onSessionUpdate", new Object[]{sessionId, new Integer(errCode), scheme, nickname});
            if (errCode == 0) {
                MPGSPaymentFragment.access$402(MPGSPaymentFragment.this, nickname);
                MPGSPaymentFragment.access$500(MPGSPaymentFragment.this, sessionId, scheme);
                return;
            }
            MPGSPaymentFragment.access$600(MPGSPaymentFragment.this, errCode);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.MPGSPaymentFragment$1 */
    class C33671 extends WebChromeClient {
        private int lastProgress = 0;

        C33671() {
        }

        public void onProgressChanged(WebView view, int newProgress) {
            Ensighten.evaluateEvent(this, "onProgressChanged", new Object[]{view, new Integer(newProgress)});
            if (this.lastProgress == 0) {
                UIUtils.startActivityIndicator(MPGSPaymentFragment.this.getActivity(), MPGSPaymentFragment.this.getString(C2658R.string.title_loading));
            }
            this.lastProgress = newProgress;
            if (newProgress >= 100) {
                UIUtils.stopActivityIndicator();
                this.lastProgress = 0;
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.MPGSPaymentFragment$2 */
    class C33682 implements AsyncListener<String> {
        C33682() {
        }

        public void onResponse(String response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (MPGSPaymentFragment.this.getNavigationActivity() != null) {
                UIUtils.stopActivityIndicator();
                if (response != null) {
                    MPGSPaymentFragment.access$102(MPGSPaymentFragment.this, new PaymentTypeRegisterData(response));
                    String merchantId = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.MPGSPaymentFragment", "access$100", new Object[]{MPGSPaymentFragment.this}).getMerchantId();
                    String libURL = Configuration.getSharedInstance().getStringForKey("supportedPaymentMethods.mpgs.libraryURL");
                    if (merchantId != null) {
                        String mpgsHtml = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.MPGSPaymentFragment", "access$200", new Object[]{MPGSPaymentFragment.this, merchantId});
                        if (mpgsHtml != null) {
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.MPGSPaymentFragment", "access$300", new Object[]{MPGSPaymentFragment.this}).loadDataWithBaseURL(libURL, mpgsHtml, "text/html", null, null);
                        }
                    }
                } else if (exception != null) {
                    AsyncException.report(exception);
                }
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.MPGSPaymentFragment$4 */
    class C33704 implements AsyncListener<CustomerProfile> {
        C33704() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (MPGSPaymentFragment.this.isActivityAlive()) {
                MPGSPaymentFragment.this.getActivity().setResult(-1);
                MPGSPaymentFragment.this.getActivity().finish();
            }
        }
    }

    private class PaymentTypeRegisterData {
        private String mCustomerId;
        private String mMerchantId;
        private String mRedirectUrl;

        public PaymentTypeRegisterData(String dataStr) {
            if (dataStr != null) {
                for (String kv : dataStr.split("&")) {
                    String[] kvPair = kv.split("=");
                    if (kvPair.length >= 2) {
                        try {
                            String v = URLDecoder.decode(kvPair[1], Utf8Charset.NAME);
                            if (kv.contains("merchant")) {
                                setMerchantId(v);
                            } else if (kv.contains("customerId")) {
                                setCustomerId(v);
                            } else if (kv.contains("redirectUrl")) {
                                setRedirectUrl(v);
                            }
                        } catch (UnsupportedEncodingException e) {
                        }
                    }
                }
            }
        }

        public void setMerchantId(String merchantId) {
            Ensighten.evaluateEvent(this, "setMerchantId", new Object[]{merchantId});
            this.mMerchantId = merchantId;
        }

        public void setCustomerId(String customerId) {
            Ensighten.evaluateEvent(this, "setCustomerId", new Object[]{customerId});
            this.mCustomerId = customerId;
        }

        public void setRedirectUrl(String redirectUrl) {
            Ensighten.evaluateEvent(this, "setRedirectUrl", new Object[]{redirectUrl});
            this.mRedirectUrl = redirectUrl;
        }

        public String getMerchantId() {
            Ensighten.evaluateEvent(this, "getMerchantId", null);
            return this.mMerchantId;
        }

        public String getCustomerId() {
            Ensighten.evaluateEvent(this, "getCustomerId", null);
            return this.mCustomerId;
        }

        public String getRedirectUrl() {
            Ensighten.evaluateEvent(this, "getRedirectUrl", null);
            return this.mRedirectUrl;
        }
    }

    static /* synthetic */ PaymentTypeRegisterData access$102(MPGSPaymentFragment x0, PaymentTypeRegisterData x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.MPGSPaymentFragment", "access$102", new Object[]{x0, x1});
        x0.mPaymentTypeRegisterData = x1;
        return x1;
    }

    static /* synthetic */ String access$402(MPGSPaymentFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.MPGSPaymentFragment", "access$402", new Object[]{x0, x1});
        x0.mNickName = x1;
        return x1;
    }

    static /* synthetic */ void access$500(MPGSPaymentFragment x0, String x1, String x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.MPGSPaymentFragment", "access$500", new Object[]{x0, x1, x2});
        x0.registerCard(x1, x2);
    }

    static /* synthetic */ void access$600(MPGSPaymentFragment x0, int x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.MPGSPaymentFragment", "access$600", new Object[]{x0, new Integer(x1)});
        x0.handleErrCode(x1);
    }

    static /* synthetic */ void access$800(MPGSPaymentFragment x0, PaymentCard x1, String x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.MPGSPaymentFragment", "access$800", new Object[]{x0, x1, x2});
        x0.updateNickname(x1, x2);
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
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_payment_provider, container, false);
        this.mWebView = (WebView) rootView.findViewById(C2358R.C2357id.main_webview);
        if (this.mWebView != null) {
            this.mWebView.clearCache(true);
            this.mWebView.getSettings().setLoadWithOverviewMode(true);
            this.mWebView.getSettings().setBuiltInZoomControls(true);
            this.mWebView.getSettings().setDisplayZoomControls(false);
            this.mWebView.getSettings().setUseWideViewPort(true);
            this.mWebView.getSettings().setJavaScriptEnabled(true);
            WebView.setWebContentsDebuggingEnabled(true);
            this.mWebView.setWebChromeClient(new C33671());
            this.mWebView.addJavascriptInterface(new JsObject(this, null), "paymentBridge");
            UIUtils.startActivityIndicator(getActivity(), (int) C2658R.string.label_processing);
            ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getPaymentTypeRegistrationURL(this.mPaymentID, Boolean.valueOf(this.mOneTimePayment), new C33682());
        }
        return rootView;
    }

    private void handleErrCode(int errCode) {
        Ensighten.evaluateEvent(this, "handleErrCode", new Object[]{new Integer(errCode)});
        int resId = -1;
        if ((errCode & 1) > 0) {
            resId = C2658R.string.mpgs_err_code_card_num;
        } else if ((errCode & 4) > 0) {
            resId = C2658R.string.mpgs_err_code_expiry_month;
        } else if ((errCode & 2) > 0) {
            resId = C2658R.string.mpgs_err_code_expiry_year;
        } else if ((errCode & 16) > 0) {
            resId = C2658R.string.mpgs_err_code_request_timeout;
        } else if ((errCode & 8) > 0) {
            resId = C2658R.string.mpgs_err_code_security_code;
        } else if ((errCode & 32) > 0) {
            resId = C2658R.string.mpgs_err_code_system_error;
        }
        if (resId > 0) {
            MCDAlertDialogBuilder.withContext(getActivity()).setMessage(resId).setPositiveButton((int) C2658R.string.f6083ok, null).create().show();
        }
    }

    private String getMpgsHtml(String merchantId) {
        Ensighten.evaluateEvent(this, "getMpgsHtml", new Object[]{merchantId});
        String mpgsHtml = null;
        AssetManager am = getResources().getAssets();
        try {
            InputStream is;
            if (Configuration.getSharedInstance().getCurrentLanguage().equals("zh")) {
                if (this.mOneTimePayment) {
                    is = am.open("mpgs_noname_tc.html");
                } else {
                    is = am.open("mpgs_tc.html");
                }
            } else if (this.mOneTimePayment) {
                is = am.open("mpgs_noname.html");
            } else {
                is = am.open("mpgs.html");
            }
            int fileLength = 0;
            for (int r = is.read(); r != -1; r = is.read()) {
                fileLength++;
            }
            if (fileLength <= 0) {
                return mpgsHtml;
            }
            byte[] contents = new byte[fileLength];
            is.close();
            if (Configuration.getSharedInstance().getCurrentLanguage().equals("zh")) {
                if (this.mOneTimePayment) {
                    is = am.open("mpgs_noname_tc.html");
                } else {
                    is = am.open("mpgs_tc.html");
                }
            } else if (this.mOneTimePayment) {
                is = am.open("mpgs_noname.html");
            } else {
                is = am.open("mpgs.html");
            }
            is.read(contents);
            String mpgsHtml2 = new String(contents, Utf8Charset.NAME);
            try {
                mpgsHtml = mpgsHtml2.replaceFirst("<MERCHANTURL>", Configuration.getSharedInstance().getStringForKey("supportedPaymentMethods.mpgs.libraryURL")).replaceFirst("<MERCHANTID>", merchantId);
                is.close();
                return mpgsHtml;
            } catch (IOException e) {
                return mpgsHtml2;
            }
        } catch (IOException e2) {
            return mpgsHtml;
        }
    }

    private void registerCard(String sessionId, final String scheme) {
        Ensighten.evaluateEvent(this, "registerCard", new Object[]{sessionId, scheme});
        UIUtils.startActivityIndicator(getContext(), (int) C2658R.string.label_processing);
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (customerModule != null && this.mPaymentTypeRegisterData != null) {
            customerModule.registerCard(this.mPaymentTypeRegisterData.getRedirectUrl(), sessionId, this.mPaymentTypeRegisterData.getCustomerId(), this.mOneTimePayment, new AsyncListener<PaymentCard>() {
                public void onResponse(PaymentCard response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    UIUtils.stopActivityIndicator();
                    if (MPGSPaymentFragment.this.getActivity() == null) {
                        return;
                    }
                    if (response == null || exception != null) {
                        MPGSPaymentFragment.this.getActivity().setResult(0);
                        MPGSPaymentFragment.this.getActivity().finish();
                        return;
                    }
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.MPGSPaymentFragment", "access$700", new Object[]{MPGSPaymentFragment.this})) {
                        DataPasser.getInstance().putData("EXTRA_ONE_TIME_PAYMENT_CARD_DATA", response);
                    }
                    MPGSPaymentFragment.access$800(MPGSPaymentFragment.this, response, scheme);
                }
            });
        }
    }

    private void updateNickname(PaymentCard paymentCard, String scheme) {
        Ensighten.evaluateEvent(this, "updateNickname", new Object[]{paymentCard, scheme});
        boolean updatedPayment = false;
        if (paymentCard == null || paymentCard.getIdentifier() == null || paymentCard.getAlias() == null) {
            MCDAlertDialogBuilder.withContext(getActivity()).setTitle((int) C2658R.string.error_updating_payment).setMessage((int) C2658R.string.ecp_error_1000).setPositiveButton((int) C2658R.string.f6083ok, null).create().show();
            return;
        }
        int cardId = paymentCard.getIdentifier().intValue();
        String nickName = this.mNickName;
        if (nickName == null || nickName.isEmpty()) {
            String alias = paymentCard.getAlias();
            if (alias != null && alias.length() >= 4) {
                String lastFourChars = alias.substring(alias.length() - 4);
                if (scheme != null) {
                    nickName = scheme + "-" + lastFourChars;
                }
            }
        }
        if (!(nickName == null || nickName.isEmpty())) {
            CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            if (customerModule != null) {
                CustomerProfile profile = customerModule.getCurrentProfile();
                if (profile != null) {
                    List<PaymentCard> cards = profile.getCardItems();
                    if (cards != null) {
                        boolean cardFound = false;
                        for (PaymentCard card : cards) {
                            if (card.getIdentifier().intValue() == cardId) {
                                card.setNickName(nickName);
                                cardFound = true;
                                break;
                            }
                        }
                        if (!cardFound) {
                            paymentCard.setNickName(nickName);
                            cards.add(paymentCard);
                        }
                    } else {
                        cards = new ArrayList();
                        paymentCard.setNickName(nickName);
                        cards.add(paymentCard);
                    }
                    customerModule.updatePayments(cards, new C33704());
                    updatedPayment = true;
                }
            }
        }
        if (!updatedPayment) {
            getActivity().setResult(-1);
            getActivity().finish();
        }
    }
}
