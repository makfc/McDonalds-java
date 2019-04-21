package com.facebook.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.FacebookDialogException;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookServiceException;
import com.facebook.Session;
import com.facebook.android.C1926R;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;

public class WebDialog extends Dialog {
    private static final int API_EC_DIALOG_CANCEL = 4201;
    private static final int BACKGROUND_GRAY = -872415232;
    static final String CANCEL_URI = "fbconnect://cancel";
    public static final int DEFAULT_THEME = 16973840;
    static final boolean DISABLE_SSL_CHECK_FOR_TESTING = false;
    private static final String DISPLAY_TOUCH = "touch";
    private static final String LOG_TAG = "FacebookSDK.WebDialog";
    private static final int MAX_PADDING_SCREEN_HEIGHT = 1280;
    private static final int MAX_PADDING_SCREEN_WIDTH = 800;
    private static final double MIN_SCALE_FACTOR = 0.5d;
    private static final int NO_PADDING_SCREEN_HEIGHT = 800;
    private static final int NO_PADDING_SCREEN_WIDTH = 480;
    static final String REDIRECT_URI = "fbconnect://success";
    private FrameLayout contentFrameLayout;
    private ImageView crossImageView;
    private String expectedRedirectUrl;
    private boolean isDetached;
    private boolean isDismissed;
    private boolean listenerCalled;
    private OnCompleteListener onCompleteListener;
    private ProgressDialog spinner;
    private String url;
    private WebView webView;

    private static class BuilderBase<CONCRETE extends BuilderBase<?>> {
        private String action;
        private String applicationId;
        private Context context;
        private OnCompleteListener listener;
        private Bundle parameters;
        private Session session;
        private int theme = WebDialog.DEFAULT_THEME;

        protected BuilderBase(Context context, String action) {
            Session activeSession = Session.getActiveSession();
            if (activeSession == null || !activeSession.isOpened()) {
                String applicationId = Utility.getMetadataApplicationId(context);
                if (applicationId != null) {
                    this.applicationId = applicationId;
                } else {
                    throw new FacebookException("Attempted to create a builder without an open Active Session or a valid default Application ID.");
                }
            }
            this.session = activeSession;
            finishInit(context, action, null);
        }

        protected BuilderBase(Context context, Session session, String action, Bundle parameters) {
            Validate.notNull(session, "session");
            if (session.isOpened()) {
                this.session = session;
                finishInit(context, action, parameters);
                return;
            }
            throw new FacebookException("Attempted to use a Session that was not open.");
        }

        protected BuilderBase(Context context, String applicationId, String action, Bundle parameters) {
            if (applicationId == null) {
                applicationId = Utility.getMetadataApplicationId(context);
            }
            Validate.notNullOrEmpty(applicationId, "applicationId");
            this.applicationId = applicationId;
            finishInit(context, action, parameters);
        }

        public CONCRETE setTheme(int theme) {
            this.theme = theme;
            return this;
        }

        public CONCRETE setOnCompleteListener(OnCompleteListener listener) {
            this.listener = listener;
            return this;
        }

        public WebDialog build() {
            if (this.session == null || !this.session.isOpened()) {
                this.parameters.putString("app_id", this.applicationId);
            } else {
                this.parameters.putString("app_id", this.session.getApplicationId());
                this.parameters.putString("access_token", this.session.getAccessToken());
            }
            return new WebDialog(this.context, this.action, this.parameters, this.theme, this.listener);
        }

        /* Access modifiers changed, original: protected */
        public String getApplicationId() {
            return this.applicationId;
        }

        /* Access modifiers changed, original: protected */
        public Context getContext() {
            return this.context;
        }

        /* Access modifiers changed, original: protected */
        public int getTheme() {
            return this.theme;
        }

        /* Access modifiers changed, original: protected */
        public Bundle getParameters() {
            return this.parameters;
        }

        /* Access modifiers changed, original: protected */
        public OnCompleteListener getListener() {
            return this.listener;
        }

        private void finishInit(Context context, String action, Bundle parameters) {
            this.context = context;
            this.action = action;
            if (parameters != null) {
                this.parameters = parameters;
            } else {
                this.parameters = new Bundle();
            }
        }
    }

    public static class Builder extends BuilderBase<Builder> {
        public /* bridge */ /* synthetic */ WebDialog build() {
            return super.build();
        }

        public Builder(Context context, String action) {
            super(context, action);
        }

        public Builder(Context context, Session session, String action, Bundle parameters) {
            super(context, session, action, parameters);
        }

        public Builder(Context context, String applicationId, String action, Bundle parameters) {
            super(context, applicationId, action, parameters);
        }
    }

    public interface OnCompleteListener {
        void onComplete(Bundle bundle, FacebookException facebookException);
    }

    /* renamed from: com.facebook.widget.WebDialog$1 */
    class C19211 implements OnCancelListener {
        C19211() {
        }

        public void onCancel(DialogInterface dialogInterface) {
            WebDialog.this.dismiss();
        }
    }

    /* renamed from: com.facebook.widget.WebDialog$2 */
    class C19222 implements OnClickListener {
        C19222() {
        }

        public void onClick(View v) {
            WebDialog.this.dismiss();
        }
    }

    private class DialogWebViewClient extends WebViewClient {
        private DialogWebViewClient() {
        }

        /* synthetic */ DialogWebViewClient(WebDialog x0, C19211 x1) {
            this();
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Utility.logd(WebDialog.LOG_TAG, "Redirect URL: " + url);
            if (url.startsWith(WebDialog.this.expectedRedirectUrl)) {
                Bundle values = WebDialog.this.parseResponseUri(url);
                String error = values.getString("error");
                if (error == null) {
                    error = values.getString(NativeProtocol.BRIDGE_ARG_ERROR_TYPE);
                }
                String errorMessage = values.getString("error_msg");
                if (errorMessage == null) {
                    errorMessage = values.getString(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION);
                }
                String errorCodeString = values.getString(NativeProtocol.BRIDGE_ARG_ERROR_CODE);
                int errorCode = -1;
                if (!Utility.isNullOrEmpty(errorCodeString)) {
                    try {
                        errorCode = Integer.parseInt(errorCodeString);
                    } catch (NumberFormatException e) {
                        errorCode = -1;
                    }
                }
                if (Utility.isNullOrEmpty(error) && Utility.isNullOrEmpty(errorMessage) && errorCode == -1) {
                    WebDialog.this.sendSuccessToListener(values);
                    return true;
                } else if (error != null && (error.equals("access_denied") || error.equals("OAuthAccessDeniedException"))) {
                    WebDialog.this.sendCancelToListener();
                    return true;
                } else if (errorCode == WebDialog.API_EC_DIALOG_CANCEL) {
                    WebDialog.this.sendCancelToListener();
                    return true;
                } else {
                    WebDialog.this.sendErrorToListener(new FacebookServiceException(new FacebookRequestError(errorCode, error, errorMessage), errorMessage));
                    return true;
                }
            } else if (url.startsWith("fbconnect://cancel")) {
                WebDialog.this.sendCancelToListener();
                return true;
            } else if (url.contains("touch")) {
                return false;
            } else {
                WebDialog.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                return true;
            }
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            WebDialog.this.sendErrorToListener(new FacebookDialogException(description, errorCode, failingUrl));
        }

        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            handler.cancel();
            WebDialog.this.sendErrorToListener(new FacebookDialogException(null, -11, null));
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Utility.logd(WebDialog.LOG_TAG, "Webview loading URL: " + url);
            super.onPageStarted(view, url, favicon);
            if (!WebDialog.this.isDetached) {
                WebDialog.this.spinner.show();
            }
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (!WebDialog.this.isDetached) {
                WebDialog.this.spinner.dismiss();
            }
            WebDialog.this.contentFrameLayout.setBackgroundColor(0);
            WebDialog.this.webView.setVisibility(0);
            WebDialog.this.crossImageView.setVisibility(0);
        }
    }

    public static class FeedDialogBuilder extends BuilderBase<FeedDialogBuilder> {
        private static final String CAPTION_PARAM = "caption";
        private static final String DESCRIPTION_PARAM = "description";
        private static final String FEED_DIALOG = "feed";
        private static final String FROM_PARAM = "from";
        private static final String LINK_PARAM = "link";
        private static final String NAME_PARAM = "name";
        private static final String PICTURE_PARAM = "picture";
        private static final String SOURCE_PARAM = "source";
        private static final String TO_PARAM = "to";

        public /* bridge */ /* synthetic */ WebDialog build() {
            return super.build();
        }

        public FeedDialogBuilder(Context context) {
            super(context, FEED_DIALOG);
        }

        public FeedDialogBuilder(Context context, Session session) {
            super(context, session, FEED_DIALOG, null);
        }

        public FeedDialogBuilder(Context context, Session session, Bundle parameters) {
            super(context, session, FEED_DIALOG, parameters);
        }

        public FeedDialogBuilder(Context context, String applicationId, Bundle parameters) {
            super(context, applicationId, FEED_DIALOG, parameters);
        }

        public FeedDialogBuilder setFrom(String id) {
            getParameters().putString("from", id);
            return this;
        }

        public FeedDialogBuilder setTo(String id) {
            getParameters().putString(TO_PARAM, id);
            return this;
        }

        public FeedDialogBuilder setLink(String link) {
            getParameters().putString(LINK_PARAM, link);
            return this;
        }

        public FeedDialogBuilder setPicture(String picture) {
            getParameters().putString(PICTURE_PARAM, picture);
            return this;
        }

        public FeedDialogBuilder setSource(String source) {
            getParameters().putString(SOURCE_PARAM, source);
            return this;
        }

        public FeedDialogBuilder setName(String name) {
            getParameters().putString("name", name);
            return this;
        }

        public FeedDialogBuilder setCaption(String caption) {
            getParameters().putString(CAPTION_PARAM, caption);
            return this;
        }

        public FeedDialogBuilder setDescription(String description) {
            getParameters().putString("description", description);
            return this;
        }
    }

    public static class RequestsDialogBuilder extends BuilderBase<RequestsDialogBuilder> {
        private static final String APPREQUESTS_DIALOG = "apprequests";
        private static final String DATA_PARAM = "data";
        private static final String MESSAGE_PARAM = "message";
        private static final String TITLE_PARAM = "title";
        private static final String TO_PARAM = "to";

        public /* bridge */ /* synthetic */ WebDialog build() {
            return super.build();
        }

        public RequestsDialogBuilder(Context context) {
            super(context, APPREQUESTS_DIALOG);
        }

        public RequestsDialogBuilder(Context context, Session session) {
            super(context, session, APPREQUESTS_DIALOG, null);
        }

        public RequestsDialogBuilder(Context context, Session session, Bundle parameters) {
            super(context, session, APPREQUESTS_DIALOG, parameters);
        }

        public RequestsDialogBuilder(Context context, String applicationId, Bundle parameters) {
            super(context, applicationId, APPREQUESTS_DIALOG, parameters);
        }

        public RequestsDialogBuilder setMessage(String message) {
            getParameters().putString("message", message);
            return this;
        }

        public RequestsDialogBuilder setTo(String id) {
            getParameters().putString(TO_PARAM, id);
            return this;
        }

        public RequestsDialogBuilder setData(String data) {
            getParameters().putString("data", data);
            return this;
        }

        public RequestsDialogBuilder setTitle(String title) {
            getParameters().putString("title", title);
            return this;
        }
    }

    public WebDialog(Context context, String url) {
        this(context, url, DEFAULT_THEME);
    }

    public WebDialog(Context context, String url, int theme) {
        super(context, theme);
        this.expectedRedirectUrl = "fbconnect://success";
        this.listenerCalled = false;
        this.isDetached = false;
        this.isDismissed = false;
        this.url = url;
    }

    public WebDialog(Context context, String action, Bundle parameters, int theme, OnCompleteListener listener) {
        super(context, theme);
        this.expectedRedirectUrl = "fbconnect://success";
        this.listenerCalled = false;
        this.isDetached = false;
        this.isDismissed = false;
        if (parameters == null) {
            parameters = new Bundle();
        }
        parameters.putString(ServerProtocol.DIALOG_PARAM_REDIRECT_URI, "fbconnect://success");
        parameters.putString(ServerProtocol.DIALOG_PARAM_DISPLAY, "touch");
        this.url = Utility.buildUri(ServerProtocol.getDialogAuthority(), ServerProtocol.getAPIVersion() + "/" + ServerProtocol.DIALOG_PATH + action, parameters).toString();
        this.onCompleteListener = listener;
    }

    public void setOnCompleteListener(OnCompleteListener listener) {
        this.onCompleteListener = listener;
    }

    public OnCompleteListener getOnCompleteListener() {
        return this.onCompleteListener;
    }

    public void dismiss() {
        if (!this.isDismissed) {
            this.isDismissed = true;
            if (!this.listenerCalled) {
                sendCancelToListener();
            }
            if (this.webView != null) {
                this.webView.stopLoading();
            }
            if (!this.isDetached) {
                if (this.spinner.isShowing()) {
                    this.spinner.dismiss();
                }
                super.dismiss();
            }
        }
    }

    public void onDetachedFromWindow() {
        this.isDetached = true;
        super.onDetachedFromWindow();
    }

    public void onAttachedToWindow() {
        this.isDetached = false;
        super.onAttachedToWindow();
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.spinner = new ProgressDialog(getContext());
        this.spinner.requestWindowFeature(1);
        this.spinner.setMessage(getContext().getString(C1926R.string.com_facebook_loading));
        this.spinner.setOnCancelListener(new C19211());
        requestWindowFeature(1);
        this.contentFrameLayout = new FrameLayout(getContext());
        calculateSize();
        getWindow().setGravity(17);
        getWindow().setSoftInputMode(16);
        createCrossImage();
        setUpWebView((this.crossImageView.getDrawable().getIntrinsicWidth() / 2) + 1);
        this.contentFrameLayout.addView(this.crossImageView, new LayoutParams(-2, -2));
        setContentView(this.contentFrameLayout);
    }

    /* Access modifiers changed, original: protected */
    public void setExpectedRedirectUrl(String expectedRedirectUrl) {
        this.expectedRedirectUrl = expectedRedirectUrl;
    }

    /* Access modifiers changed, original: protected */
    public Bundle parseResponseUri(String urlString) {
        Uri u = Uri.parse(urlString);
        Bundle b = Utility.parseUrlQueryString(u.getQuery());
        b.putAll(Utility.parseUrlQueryString(u.getFragment()));
        return b;
    }

    /* Access modifiers changed, original: protected */
    public boolean isListenerCalled() {
        return this.listenerCalled;
    }

    /* Access modifiers changed, original: protected */
    public WebView getWebView() {
        return this.webView;
    }

    private void calculateSize() {
        Display display = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        getWindow().setLayout(Math.min(getScaledSize(metrics.widthPixels < metrics.heightPixels ? metrics.widthPixels : metrics.heightPixels, metrics.density, NO_PADDING_SCREEN_WIDTH, 800), metrics.widthPixels), Math.min(getScaledSize(metrics.widthPixels < metrics.heightPixels ? metrics.heightPixels : metrics.widthPixels, metrics.density, 800, MAX_PADDING_SCREEN_HEIGHT), metrics.heightPixels));
    }

    private int getScaledSize(int screenSize, float density, int noPaddingSize, int maxPaddingSize) {
        double scaleFactor;
        int scaledSize = (int) (((float) screenSize) / density);
        if (scaledSize <= noPaddingSize) {
            scaleFactor = 1.0d;
        } else if (scaledSize >= maxPaddingSize) {
            scaleFactor = MIN_SCALE_FACTOR;
        } else {
            scaleFactor = MIN_SCALE_FACTOR + ((((double) (maxPaddingSize - scaledSize)) / ((double) (maxPaddingSize - noPaddingSize))) * MIN_SCALE_FACTOR);
        }
        return (int) (((double) screenSize) * scaleFactor);
    }

    /* Access modifiers changed, original: protected */
    public void sendSuccessToListener(Bundle values) {
        if (this.onCompleteListener != null && !this.listenerCalled) {
            this.listenerCalled = true;
            this.onCompleteListener.onComplete(values, null);
            dismiss();
        }
    }

    /* Access modifiers changed, original: protected */
    public void sendErrorToListener(Throwable error) {
        if (this.onCompleteListener != null && !this.listenerCalled) {
            FacebookException facebookException;
            this.listenerCalled = true;
            if (error instanceof FacebookException) {
                facebookException = (FacebookException) error;
            } else {
                facebookException = new FacebookException(error);
            }
            this.onCompleteListener.onComplete(null, facebookException);
            dismiss();
        }
    }

    /* Access modifiers changed, original: protected */
    public void sendCancelToListener() {
        sendErrorToListener(new FacebookOperationCanceledException());
    }

    private void createCrossImage() {
        this.crossImageView = new ImageView(getContext());
        this.crossImageView.setOnClickListener(new C19222());
        this.crossImageView.setImageDrawable(getContext().getResources().getDrawable(C1926R.C1924drawable.com_facebook_close));
        this.crossImageView.setVisibility(4);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void setUpWebView(int margin) {
        LinearLayout webViewContainer = new LinearLayout(getContext());
        this.webView = new WebView(getContext()) {
            public void onWindowFocusChanged(boolean hasWindowFocus) {
                try {
                    super.onWindowFocusChanged(hasWindowFocus);
                } catch (NullPointerException e) {
                }
            }
        };
        this.webView.setVerticalScrollBarEnabled(false);
        this.webView.setHorizontalScrollBarEnabled(false);
        this.webView.setWebViewClient(new DialogWebViewClient(this, null));
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.loadUrl(this.url);
        this.webView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.webView.setVisibility(4);
        this.webView.getSettings().setSavePassword(false);
        this.webView.getSettings().setSaveFormData(false);
        webViewContainer.setPadding(margin, margin, margin, margin);
        webViewContainer.addView(this.webView);
        webViewContainer.setBackgroundColor(BACKGROUND_GRAY);
        this.contentFrameLayout.addView(webViewContainer);
    }
}
