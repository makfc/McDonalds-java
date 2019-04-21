package com.alipay.sdk.widget;

import java.util.Iterator;
import java.util.Stack;

/* renamed from: com.alipay.sdk.widget.u */
public class C0685u {
    /* renamed from: a */
    private Stack<WebViewWindow> f720a = new Stack();

    /* renamed from: a */
    public WebViewWindow mo8170a() {
        return (WebViewWindow) this.f720a.pop();
    }

    /* renamed from: a */
    public void mo8171a(WebViewWindow webViewWindow) {
        this.f720a.push(webViewWindow);
    }

    /* renamed from: b */
    public boolean mo8172b() {
        return this.f720a.isEmpty();
    }

    /* renamed from: c */
    public void mo8173c() {
        if (!mo8172b()) {
            Iterator it = this.f720a.iterator();
            while (it.hasNext()) {
                ((WebViewWindow) it.next()).mo8126a();
            }
            this.f720a.clear();
        }
    }
}
