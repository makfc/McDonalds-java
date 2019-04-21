package com.ensighten.utils;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import com.ensighten.C1845i;
import com.ensighten.Ensighten;
import com.mcdonalds.sdk.services.analytics.tagmanager.Parameters;
import org.json.JSONObject;

public class EnsightenBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Parameters.ACTION, intent.getAction());
            jSONObject.put("dataString", intent.getDataString());
            jSONObject.put("referrer", intent.getStringExtra("referrer"));
            Ensighten.setReferrerInfo(jSONObject);
            ActivityInfo receiverInfo = context.getPackageManager().getReceiverInfo(new ComponentName(context, "com.ensighten.utils.EnsightenBroadcastReceiver"), 128);
            if (receiverInfo != null) {
                Bundle bundle = receiverInfo.metaData;
                if (bundle != null) {
                    for (String string : bundle.keySet()) {
                        ((BroadcastReceiver) Class.forName(bundle.getString(string)).newInstance()).onReceive(context, intent);
                    }
                }
            }
        } catch (Exception e) {
            if (C1845i.m7357d()) {
                C1845i.m7353c(e);
            }
        }
    }
}
