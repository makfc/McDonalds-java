package com.baidu.android.pushservice;

public interface IPushLightappListener {
    void initialComplete(PushLightapp pushLightapp);

    void onSubscribeByApiKey(int i, String str);

    void onSubscribeResult(int i, String str);

    void onUnsubscribeResult(int i, String str);
}
