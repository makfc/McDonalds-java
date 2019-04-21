package com.admaster.jice.api;

import android.annotation.SuppressLint;

public enum JicePushShowError {
    JicePushNoData(1000, "暂时没有可以展示的推送"),
    JicePushIsShowing(1001, "及策推送已经正在展示"),
    JicePushNoNet(1002, "暂时没有网络,无法完成推送展示"),
    JicePushIsDownloading(1003, "正在下载推送活动的配置和素材");
    
    private String msg;
    private int type;

    private JicePushShowError(int i, String str) {
        this.type = i;
        this.msg = str;
    }

    @SuppressLint({"DefaultLocale"})
    public String toString() {
        return String.format("ErrorCode:%d ErrorMsg:%s", new Object[]{Integer.valueOf(this.type), this.msg});
    }
}
