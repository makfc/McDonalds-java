package com.alipay.sdk.widget;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.widget.ImageView;
import com.alipay.sdk.app.C0588j;
import com.alipay.sdk.app.statistic.C0590a;
import com.alipay.sdk.util.C0657m;
import com.alipay.sdk.widget.WebViewWindow.C0660a;
import com.alipay.sdk.widget.WebViewWindow.C0661b;
import com.alipay.sdk.widget.WebViewWindow.C0662c;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.facebook.Response;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.widget.j */
public class C0674j extends C0670g implements C0660a, C0661b, C0662c {
    /* renamed from: t */
    private boolean f699t = true;
    /* renamed from: u */
    private String f700u = "GET";
    /* renamed from: v */
    private boolean f701v = false;
    /* renamed from: w */
    private boolean f702w;
    /* renamed from: x */
    private WebViewWindow f703x = null;
    /* renamed from: y */
    private C0685u f704y = new C0685u();

    /* renamed from: com.alipay.sdk.widget.j$a */
    private abstract class C0673a implements AnimationListener {
        private C0673a() {
        }

        /* synthetic */ C0673a(C0674j c0674j, C0675k c0675k) {
            this();
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f701v ? true : super.onInterceptTouchEvent(motionEvent);
    }

    public C0674j(Activity activity) {
        super(activity);
        m1136c();
    }

    /* renamed from: c */
    private boolean m1136c() {
        try {
            this.f703x = new WebViewWindow(this.f694a);
            this.f703x.setChromeProxy(this);
            this.f703x.setWebClientProxy(this);
            this.f703x.setWebEventProxy(this);
            addView(this.f703x);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: d */
    private void m1137d() {
        if (this.f699t) {
            this.f694a.finish();
        } else {
            this.f703x.mo8128a("javascript:window.AlipayJSBridge.callListener('h5BackAction');");
        }
    }

    /* renamed from: e */
    private void m1138e() {
        WebView webView = this.f703x.getWebView();
        if (webView.canGoBack()) {
            webView.goBack();
        } else if (this.f704y == null || this.f704y.mo8172b()) {
            m1131a(false);
        } else {
            m1139f();
        }
    }

    /* renamed from: a */
    public void mo8155a(String str, String str2, boolean z) {
        this.f700u = str2;
        this.f703x.getTitle().setText(str);
        this.f699t = z;
    }

    /* renamed from: a */
    private void m1131a(boolean z) {
        C0588j.m791a(z);
        this.f694a.finish();
    }

    /* renamed from: a */
    public void mo8148a(String str) {
        if ("POST".equals(this.f700u)) {
            this.f703x.mo8129a(str, null);
        } else {
            this.f703x.mo8128a(str);
        }
    }

    /* renamed from: a */
    public void mo8147a() {
        this.f703x.mo8126a();
        this.f704y.mo8173c();
    }

    /* renamed from: b */
    public boolean mo8150b() {
        if (!this.f701v) {
            m1137d();
        }
        return true;
    }

    /* renamed from: a */
    public boolean mo8119a(WebViewWindow webViewWindow, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (str2.startsWith("<head>") && str2.contains("sdk_result_code:")) {
            this.f694a.runOnUiThread(new C0675k(this));
        }
        jsPromptResult.cancel();
        return true;
    }

    /* renamed from: a */
    public void mo8118a(WebViewWindow webViewWindow, String str) {
        if (!str.startsWith("http") && !webViewWindow.getUrl().endsWith(str)) {
            this.f703x.getTitle().setText(str);
        }
    }

    /* renamed from: f */
    private boolean m1139f() {
        if (this.f704y.mo8172b()) {
            this.f694a.finish();
        } else {
            this.f701v = true;
            WebViewWindow webViewWindow = this.f703x;
            this.f703x = this.f704y.mo8170a();
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 1.0f, 1, 0.0f, 1, 0.0f);
            translateAnimation.setDuration(400);
            translateAnimation.setFillAfter(false);
            translateAnimation.setAnimationListener(new C0676l(this, webViewWindow));
            webViewWindow.setAnimation(translateAnimation);
            removeView(webViewWindow);
            addView(this.f703x);
        }
        return true;
    }

    /* renamed from: b */
    private boolean m1135b(String str, String str2) {
        WebViewWindow webViewWindow = this.f703x;
        try {
            this.f703x = new WebViewWindow(this.f694a);
            this.f703x.setChromeProxy(this);
            this.f703x.setWebClientProxy(this);
            this.f703x.setWebEventProxy(this);
            if (!TextUtils.isEmpty(str2)) {
                this.f703x.getTitle().setText(str2);
            }
            this.f701v = true;
            this.f704y.mo8171a(webViewWindow);
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
            translateAnimation.setDuration(400);
            translateAnimation.setFillAfter(false);
            translateAnimation.setAnimationListener(new C0677m(this, webViewWindow, str));
            this.f703x.setAnimation(translateAnimation);
            addView(this.f703x);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: b */
    public boolean mo8122b(WebViewWindow webViewWindow, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.startsWith("alipayjsbridge://")) {
            m1133b(str.substring("alipayjsbridge://".length()));
        } else if (TextUtils.equals(str, "sdklite://h5quit")) {
            m1131a(false);
        } else if (str.startsWith("http://") || str.startsWith("https://")) {
            this.f703x.mo8128a(str);
        } else {
            try {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                this.f694a.startActivity(intent);
            } catch (Throwable th) {
                C0590a.m804a("biz", th);
            }
        }
        return true;
    }

    /* renamed from: c */
    public boolean mo8123c(WebViewWindow webViewWindow, String str) {
        webViewWindow.mo8128a("javascript:window.prompt('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');(function() {\n    if (window.AlipayJSBridge) {\n        return\n    }\n\n    function alipayjsbridgeFunc(url) {\n        var iframe = document.createElement(\"iframe\");\n        iframe.style.width = \"1px\";\n        iframe.style.height = \"1px\";\n        iframe.style.display = \"none\";\n        iframe.src = url;\n        document.body.appendChild(iframe);\n        setTimeout(function() {\n            document.body.removeChild(iframe)\n        }, 100)\n    }\n    window.alipayjsbridgeSetTitle = function(title) {\n        document.title = title;\n        alipayjsbridgeFunc(\"alipayjsbridge://setTitle?title=\" + encodeURIComponent(title))\n    };\n    window.alipayjsbridgeRefresh = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onRefresh?\")\n    };\n    window.alipayjsbridgeBack = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onBack?\")\n    };\n    window.alipayjsbridgeExit = function(bsucc) {\n        alipayjsbridgeFunc(\"alipayjsbridge://onExit?bsucc=\" + bsucc)\n    };\n    window.alipayjsbridgeShowBackButton = function(bshow) {\n        alipayjsbridgeFunc(\"alipayjsbridge://showBackButton?bshow=\" + bshow)\n    };\n    window.AlipayJSBridge = {\n        version: \"2.0\",\n        addListener: addListener,\n        hasListener: hasListener,\n        callListener: callListener,\n        callNativeFunc: callNativeFunc,\n        callBackFromNativeFunc: callBackFromNativeFunc\n    };\n    var uniqueId = 1;\n    var h5JsCallbackMap = {};\n\n    function iframeCall(paramStr) {\n        setTimeout(function() {\n        \tvar iframe = document.createElement(\"iframe\");\n        \tiframe.style.width = \"1px\";\n        \tiframe.style.height = \"1px\";\n        \tiframe.style.display = \"none\";\n        \tiframe.src = \"alipayjsbridge://callNativeFunc?\" + paramStr;\n        \tvar parent = document.body || document.documentElement;\n        \tparent.appendChild(iframe);\n        \tsetTimeout(function() {\n            \tparent.removeChild(iframe)\n        \t}, 0)\n        }, 0)\n    }\n\n    function callNativeFunc(nativeFuncName, data, h5JsCallback) {\n        var h5JsCallbackId = \"\";\n        if (h5JsCallback) {\n            h5JsCallbackId = \"cb_\" + (uniqueId++) + \"_\" + new Date().getTime();\n            h5JsCallbackMap[h5JsCallbackId] = h5JsCallback\n        }\n        var dataStr = \"\";\n        if (data) {\n            dataStr = encodeURIComponent(JSON.stringify(data))\n        }\n        var paramStr = \"func=\" + nativeFuncName + \"&cbId=\" + h5JsCallbackId + \"&data=\" + dataStr;\n        iframeCall(paramStr)\n    }\n\n    function callBackFromNativeFunc(h5JsCallbackId, data) {\n        var h5JsCallback = h5JsCallbackMap[h5JsCallbackId];\n        if (h5JsCallback) {\n            h5JsCallback(data);\n            delete h5JsCallbackMap[callbackId]\n        }\n    }\n    var h5ListenerMap = {};\n\n    function addListener(jsFuncName, jsFunc) {\n        h5ListenerMap[jsFuncName] = jsFunc\n    }\n\n    function hasListener(jsFuncName) {\n        var jsFunc = h5ListenerMap[jsFuncName];\n        if (!jsFunc) {\n            return false\n        }\n        return true\n    }\n\n    function callListener(h5JsFuncName, data, nativeCallbackId) {\n        var responseCallback;\n        if (nativeCallbackId) {\n            responseCallback = function(responseData) {\n                var dataStr = \"\";\n                if (responseData) {\n                    dataStr = encodeURIComponent(JSON.stringify(responseData))\n                }\n                var paramStr = \"func=h5JsFuncCallback\" + \"&cbId=\" + nativeCallbackId + \"&data=\" + dataStr;\n                iframeCall(paramStr)\n            }\n        }\n        var h5JsFunc = h5ListenerMap[h5JsFuncName];\n        if (h5JsFunc) {\n            h5JsFunc(data, responseCallback)\n        } else if (h5JsFuncName == \"h5BackAction\") {\n            if (!window.alipayjsbridgeH5BackAction || !alipayjsbridgeH5BackAction()) {\n                var paramStr = \"func=back\";\n                iframeCall(paramStr)\n            }\n        } else {\n            console.log(\"AlipayJSBridge: no h5JsFunc \" + h5JsFuncName + data)\n        }\n    }\n    var event;\n    if (window.CustomEvent) {\n        event = new CustomEvent(\"alipayjsbridgeready\")\n    } else {\n        event = document.createEvent(\"Event\");\n        event.initEvent(\"alipayjsbridgeready\", true, true)\n    }\n    document.dispatchEvent(event);\n    setTimeout(excuteH5InitFuncs, 0);\n\n    function excuteH5InitFuncs() {\n        if (window.AlipayJSBridgeInitArray) {\n            var h5InitFuncs = window.AlipayJSBridgeInitArray;\n            delete window.AlipayJSBridgeInitArray;\n            for (var i = 0; i < h5InitFuncs.length; i++) {\n                try {\n                    h5InitFuncs[i](AlipayJSBridge)\n                } catch (e) {\n                    setTimeout(function() {\n                        throw e\n                    })\n                }\n            }\n        }\n    }\n})();\n;window.AlipayJSBridge.callListener('h5PageFinished');");
        webViewWindow.getRefreshButton().setVisibility(0);
        return true;
    }

    /* renamed from: a */
    public boolean mo8120a(WebViewWindow webViewWindow, int i, String str, String str2) {
        C0590a.m801a("net", "SSLError", "onReceivedError:" + str2);
        webViewWindow.getRefreshButton().setVisibility(0);
        return false;
    }

    /* renamed from: a */
    public boolean mo8121a(WebViewWindow webViewWindow, SslErrorHandler sslErrorHandler, SslError sslError) {
        C0590a.m801a("net", "SSLError", String.valueOf(sslError));
        if (this.f702w) {
            sslErrorHandler.proceed();
            this.f702w = false;
        } else {
            this.f694a.runOnUiThread(new C0678n(this, sslErrorHandler));
        }
        return true;
    }

    /* renamed from: b */
    private void m1133b(String str) {
        Map c = C0657m.m1069c(str);
        if (str.startsWith("callNativeFunc")) {
            m1130a((String) c.get("func"), (String) c.get("cbId"), (String) c.get(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH));
        } else if (str.startsWith("onBack")) {
            m1138e();
        } else if (str.startsWith("setTitle") && c.containsKey(PushConstants.TITLE_KEY)) {
            this.f703x.getTitle().setText((CharSequence) c.get(PushConstants.TITLE_KEY));
        } else if (str.startsWith("onRefresh")) {
            this.f703x.getWebView().reload();
        } else if (str.startsWith("showBackButton") && c.containsKey("bshow")) {
            this.f703x.getBackButton().setVisibility(TextUtils.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE, (CharSequence) c.get("bshow")) ? 0 : 4);
        } else if (str.startsWith("onExit")) {
            C0588j.m790a((String) c.get("result"));
            m1131a(TextUtils.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE, (CharSequence) c.get("bsucc")));
        } else if (str.startsWith("onLoadJs")) {
            this.f703x.mo8128a("javascript:(function() {\n    if (window.AlipayJSBridge) {\n        return\n    }\n\n    function alipayjsbridgeFunc(url) {\n        var iframe = document.createElement(\"iframe\");\n        iframe.style.width = \"1px\";\n        iframe.style.height = \"1px\";\n        iframe.style.display = \"none\";\n        iframe.src = url;\n        document.body.appendChild(iframe);\n        setTimeout(function() {\n            document.body.removeChild(iframe)\n        }, 100)\n    }\n    window.alipayjsbridgeSetTitle = function(title) {\n        document.title = title;\n        alipayjsbridgeFunc(\"alipayjsbridge://setTitle?title=\" + encodeURIComponent(title))\n    };\n    window.alipayjsbridgeRefresh = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onRefresh?\")\n    };\n    window.alipayjsbridgeBack = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onBack?\")\n    };\n    window.alipayjsbridgeExit = function(bsucc) {\n        alipayjsbridgeFunc(\"alipayjsbridge://onExit?bsucc=\" + bsucc)\n    };\n    window.alipayjsbridgeShowBackButton = function(bshow) {\n        alipayjsbridgeFunc(\"alipayjsbridge://showBackButton?bshow=\" + bshow)\n    };\n    window.AlipayJSBridge = {\n        version: \"2.0\",\n        addListener: addListener,\n        hasListener: hasListener,\n        callListener: callListener,\n        callNativeFunc: callNativeFunc,\n        callBackFromNativeFunc: callBackFromNativeFunc\n    };\n    var uniqueId = 1;\n    var h5JsCallbackMap = {};\n\n    function iframeCall(paramStr) {\n        setTimeout(function() {\n        \tvar iframe = document.createElement(\"iframe\");\n        \tiframe.style.width = \"1px\";\n        \tiframe.style.height = \"1px\";\n        \tiframe.style.display = \"none\";\n        \tiframe.src = \"alipayjsbridge://callNativeFunc?\" + paramStr;\n        \tvar parent = document.body || document.documentElement;\n        \tparent.appendChild(iframe);\n        \tsetTimeout(function() {\n            \tparent.removeChild(iframe)\n        \t}, 0)\n        }, 0)\n    }\n\n    function callNativeFunc(nativeFuncName, data, h5JsCallback) {\n        var h5JsCallbackId = \"\";\n        if (h5JsCallback) {\n            h5JsCallbackId = \"cb_\" + (uniqueId++) + \"_\" + new Date().getTime();\n            h5JsCallbackMap[h5JsCallbackId] = h5JsCallback\n        }\n        var dataStr = \"\";\n        if (data) {\n            dataStr = encodeURIComponent(JSON.stringify(data))\n        }\n        var paramStr = \"func=\" + nativeFuncName + \"&cbId=\" + h5JsCallbackId + \"&data=\" + dataStr;\n        iframeCall(paramStr)\n    }\n\n    function callBackFromNativeFunc(h5JsCallbackId, data) {\n        var h5JsCallback = h5JsCallbackMap[h5JsCallbackId];\n        if (h5JsCallback) {\n            h5JsCallback(data);\n            delete h5JsCallbackMap[callbackId]\n        }\n    }\n    var h5ListenerMap = {};\n\n    function addListener(jsFuncName, jsFunc) {\n        h5ListenerMap[jsFuncName] = jsFunc\n    }\n\n    function hasListener(jsFuncName) {\n        var jsFunc = h5ListenerMap[jsFuncName];\n        if (!jsFunc) {\n            return false\n        }\n        return true\n    }\n\n    function callListener(h5JsFuncName, data, nativeCallbackId) {\n        var responseCallback;\n        if (nativeCallbackId) {\n            responseCallback = function(responseData) {\n                var dataStr = \"\";\n                if (responseData) {\n                    dataStr = encodeURIComponent(JSON.stringify(responseData))\n                }\n                var paramStr = \"func=h5JsFuncCallback\" + \"&cbId=\" + nativeCallbackId + \"&data=\" + dataStr;\n                iframeCall(paramStr)\n            }\n        }\n        var h5JsFunc = h5ListenerMap[h5JsFuncName];\n        if (h5JsFunc) {\n            h5JsFunc(data, responseCallback)\n        } else if (h5JsFuncName == \"h5BackAction\") {\n            if (!window.alipayjsbridgeH5BackAction || !alipayjsbridgeH5BackAction()) {\n                var paramStr = \"func=back\";\n                iframeCall(paramStr)\n            }\n        } else {\n            console.log(\"AlipayJSBridge: no h5JsFunc \" + h5JsFuncName + data)\n        }\n    }\n    var event;\n    if (window.CustomEvent) {\n        event = new CustomEvent(\"alipayjsbridgeready\")\n    } else {\n        event = document.createEvent(\"Event\");\n        event.initEvent(\"alipayjsbridgeready\", true, true)\n    }\n    document.dispatchEvent(event);\n    setTimeout(excuteH5InitFuncs, 0);\n\n    function excuteH5InitFuncs() {\n        if (window.AlipayJSBridgeInitArray) {\n            var h5InitFuncs = window.AlipayJSBridgeInitArray;\n            delete window.AlipayJSBridgeInitArray;\n            for (var i = 0; i < h5InitFuncs.length; i++) {\n                try {\n                    h5InitFuncs[i](AlipayJSBridge)\n                } catch (e) {\n                    setTimeout(function() {\n                        throw e\n                    })\n                }\n            }\n        }\n    }\n})();\n");
        }
    }

    /* renamed from: a */
    private void m1130a(String str, String str2, String str3) {
        int i = 0;
        JSONObject d = C0657m.m1074d(str3);
        boolean optBoolean;
        ImageView backButton;
        if (PushConstants.TITLE_KEY.equals(str) && d.has(PushConstants.TITLE_KEY)) {
            this.f703x.getTitle().setText(d.optString(PushConstants.TITLE_KEY, ""));
        } else if ("refresh".equals(str)) {
            this.f703x.getWebView().reload();
        } else if ("back".equals(str)) {
            m1138e();
        } else if ("exit".equals(str)) {
            C0588j.m790a(d.optString("result", null));
            m1131a(d.optBoolean(Response.SUCCESS_KEY, false));
        } else if ("backButton".equals(str)) {
            optBoolean = d.optBoolean("show", true);
            backButton = this.f703x.getBackButton();
            if (!optBoolean) {
                i = 4;
            }
            backButton.setVisibility(i);
        } else if ("refreshButton".equals(str)) {
            optBoolean = d.optBoolean("show", true);
            backButton = this.f703x.getRefreshButton();
            if (!optBoolean) {
                i = 4;
            }
            backButton.setVisibility(i);
        } else if ("pushWindow".equals(str) && d.optString(NativeProtocol.IMAGE_URL_KEY, null) != null) {
            m1135b(d.optString(NativeProtocol.IMAGE_URL_KEY), d.optString(PushConstants.TITLE_KEY, ""));
        }
    }

    /* renamed from: a */
    public void mo8124a(WebViewWindow webViewWindow) {
        m1137d();
    }

    /* renamed from: b */
    public void mo8125b(WebViewWindow webViewWindow) {
        webViewWindow.getWebView().reload();
        webViewWindow.getRefreshButton().setVisibility(4);
    }
}
