package com.amap.api.mapcore.util;

/* renamed from: com.amap.api.mapcore.util.dk */
public class AMapCoreException extends Exception {
    /* renamed from: a */
    private String f1751a = "未知的错误";
    /* renamed from: b */
    private int f1752b = -1;

    public AMapCoreException(String str) {
        super(str);
        this.f1751a = str;
        m2378a(str);
    }

    /* renamed from: a */
    public String mo9283a() {
        return this.f1751a;
    }

    /* renamed from: b */
    public int mo9284b() {
        return this.f1752b;
    }

    /* renamed from: a */
    private void m2378a(String str) {
        if ("IO 操作异常 - IOException".equals(str)) {
            this.f1752b = 21;
        } else if ("socket 连接异常 - SocketException".equals(str)) {
            this.f1752b = 22;
        } else if ("socket 连接超时 - SocketTimeoutException".equals(str)) {
            this.f1752b = 23;
        } else if ("无效的参数 - IllegalArgumentException".equals(str)) {
            this.f1752b = 24;
        } else if ("空指针异常 - NullPointException".equals(str)) {
            this.f1752b = 25;
        } else if ("url异常 - MalformedURLException".equals(str)) {
            this.f1752b = 26;
        } else if ("未知主机 - UnKnowHostException".equals(str)) {
            this.f1752b = 27;
        } else if ("服务器连接失败 - UnknownServiceException".equals(str)) {
            this.f1752b = 28;
        } else if ("协议解析错误 - ProtocolException".equals(str)) {
            this.f1752b = 29;
        } else if ("http连接失败 - ConnectionException".equals(str)) {
            this.f1752b = 30;
        } else if ("未知的错误".equals(str)) {
            this.f1752b = 31;
        } else if ("key鉴权失败".equals(str)) {
            this.f1752b = 32;
        } else if ("requeust is null".equals(str)) {
            this.f1752b = 1;
        } else if ("request url is empty".equals(str)) {
            this.f1752b = 2;
        } else if ("response is null".equals(str)) {
            this.f1752b = 3;
        } else if ("thread pool has exception".equals(str)) {
            this.f1752b = 4;
        } else if ("sdk name is invalid".equals(str)) {
            this.f1752b = 5;
        } else if ("sdk info is null".equals(str)) {
            this.f1752b = 6;
        } else if ("sdk packages is null".equals(str)) {
            this.f1752b = 7;
        } else if ("线程池为空".equals(str)) {
            this.f1752b = 8;
        } else if ("获取对象错误".equals(str)) {
            this.f1752b = 101;
        } else {
            this.f1752b = -1;
        }
    }
}
