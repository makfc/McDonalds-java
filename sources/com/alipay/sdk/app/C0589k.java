package com.alipay.sdk.app;

/* renamed from: com.alipay.sdk.app.k */
public enum C0589k {
    SUCCEEDED(9000, "处理成功"),
    FAILED(4000, "系统繁忙，请稍后再试"),
    CANCELED(6001, "用户取消"),
    NETWORK_ERROR(6002, "网络连接异常"),
    PARAMS_ERROR(4001, "参数错误"),
    DOUBLE_REQUEST(5000, "重复请求"),
    PAY_WAITTING(8000, "支付结果确认中");
    
    /* renamed from: h */
    private int f509h;
    /* renamed from: i */
    private String f510i;

    private C0589k(int i, String str) {
        this.f509h = i;
        this.f510i = str;
    }

    /* renamed from: a */
    public int mo8007a() {
        return this.f509h;
    }

    /* renamed from: b */
    public String mo8008b() {
        return this.f510i;
    }

    /* renamed from: b */
    public static C0589k m796b(int i) {
        switch (i) {
            case 4001:
                return PARAMS_ERROR;
            case 5000:
                return DOUBLE_REQUEST;
            case 6001:
                return CANCELED;
            case 6002:
                return NETWORK_ERROR;
            case 8000:
                return PAY_WAITTING;
            case 9000:
                return SUCCEEDED;
            default:
                return FAILED;
        }
    }
}
