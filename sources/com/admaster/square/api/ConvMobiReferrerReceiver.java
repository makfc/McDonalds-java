package com.admaster.square.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.stetho.common.Utf8Charset;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ConvMobiReferrerReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("referrer");
        if (stringExtra != null) {
            try {
                stringExtra = URLDecoder.decode(stringExtra, Utf8Charset.NAME);
            } catch (UnsupportedEncodingException e) {
                stringExtra = "";
            }
            ConvMobiSDK.setReferrer(stringExtra);
        }
    }
}
