package com.ensighten.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.p000v4.widget.ExploreByTouchHelper;
import com.ensighten.C1845i;
import com.ensighten.Ensighten;
import org.json.JSONException;
import org.json.JSONObject;

public class BatteryInfoManager extends BroadcastReceiver {
    /* renamed from: a */
    public int f5987a = ExploreByTouchHelper.INVALID_ID;
    /* renamed from: b */
    private int f5988b = ExploreByTouchHelper.INVALID_ID;
    /* renamed from: c */
    private int f5989c = ExploreByTouchHelper.INVALID_ID;
    /* renamed from: d */
    private boolean f5990d = false;
    /* renamed from: e */
    private int f5991e = ExploreByTouchHelper.INVALID_ID;
    /* renamed from: f */
    private int f5992f = ExploreByTouchHelper.INVALID_ID;
    /* renamed from: g */
    private String f5993g = "na";
    /* renamed from: h */
    private int f5994h = ExploreByTouchHelper.INVALID_ID;
    /* renamed from: i */
    private int f5995i = ExploreByTouchHelper.INVALID_ID;

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            this.f5988b = intent.getIntExtra("health", 0);
            this.f5987a = intent.getIntExtra("level", 0);
            this.f5989c = intent.getIntExtra("plugged", 0);
            this.f5990d = intent.getExtras().getBoolean("present");
            this.f5991e = intent.getIntExtra("scale", 0);
            this.f5992f = intent.getIntExtra("status", 0);
            this.f5993g = intent.getExtras().getString("technology");
            this.f5994h = intent.getIntExtra("temperature", 0);
            this.f5995i = intent.getIntExtra("voltage", 0);
            Ensighten.processBatteryLevel();
        }
    }

    /* renamed from: a */
    public final JSONObject mo15522a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("health", this.f5988b);
            jSONObject.put("level", this.f5987a);
            jSONObject.put("plugged", this.f5989c);
            jSONObject.put("present", this.f5990d);
            jSONObject.put("scale", this.f5991e);
            jSONObject.put("status", this.f5992f);
            jSONObject.put("technology", this.f5993g);
            jSONObject.put("temperature", this.f5994h);
            jSONObject.put("voltage", this.f5995i);
        } catch (JSONException e) {
            if (C1845i.m7357d()) {
                C1845i.m7353c(e);
            }
        }
        return jSONObject;
    }
}
