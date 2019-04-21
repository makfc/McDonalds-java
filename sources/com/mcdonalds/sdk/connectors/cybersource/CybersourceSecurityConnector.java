package com.mcdonalds.sdk.connectors.cybersource;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.connectors.BaseConnector;
import com.mcdonalds.sdk.connectors.SecurityConnector;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.threatmetrix.TrustDefender.C4473TrustDefender;
import com.threatmetrix.TrustDefender.Config;
import com.threatmetrix.TrustDefender.EndNotifier;
import com.threatmetrix.TrustDefender.ProfilingResult;
import com.threatmetrix.TrustDefender.THMStatusCode;

public class CybersourceSecurityConnector extends BaseConnector implements SecurityConnector {
    private static final String KEY_CYBERSOURCE_MERCHANT_ID = "connectors.CybersourceSecurity.merchantId";
    private static final String KEY_CYBERSOURCE_ORG_ID = "connectors.CybersourceSecurity.orgId";
    public static final String NAME = "cybersourcesecurity";
    private String merchantId;
    private AsyncListener<String> sessionListener;

    private class CompletionNotifier implements EndNotifier {
        private CompletionNotifier() {
        }

        public void complete(final ProfilingResult profilingResult) {
            Log.i("CYBER SOURCE", "Profile completed with: " + profilingResult.getStatus().toString() + " - " + profilingResult.getStatus().getDesc());
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (CybersourceSecurityConnector.this.sessionListener != null) {
                        String sessionId = "";
                        if (profilingResult.getStatus() == THMStatusCode.THM_OK) {
                            sessionId = profilingResult.getSessionID().replace(CybersourceSecurityConnector.this.merchantId, "");
                        }
                        CybersourceSecurityConnector.this.sessionListener.onResponse(sessionId, null, null);
                    }
                }
            });
            C4473TrustDefender.getInstance().doPackageScan(0);
        }
    }

    public void retriveSecurityToken(Context context, String merchantId, AsyncListener<String> sessionIdListener) {
        String orgId = (String) Configuration.getSharedInstance().getValueForKey(KEY_CYBERSOURCE_ORG_ID);
        this.merchantId = merchantId;
        if (orgId == null || merchantId == null) {
            sessionIdListener.onResponse(null, null, null);
            return;
        }
        THMStatusCode initStatus = C4473TrustDefender.getInstance().init(new Config().setOrgId(orgId).setContext(context.getApplicationContext()));
        if (initStatus == THMStatusCode.THM_OK || initStatus == THMStatusCode.THM_Already_Initialised) {
            Log.e("CYBER SOURCE", "Succesfully init-ed " + initStatus.getDesc());
            Log.i("CYBER SOURCE", "Using 4.0-90");
            THMStatusCode status = C4473TrustDefender.getInstance().doProfileRequest(merchantId + String.valueOf(System.currentTimeMillis()), new CompletionNotifier());
            if (status == THMStatusCode.THM_OK) {
                Log.i("CYBER SOURCE", "Session ID is: " + C4473TrustDefender.getInstance().getResult().getSessionID());
                sessionIdListener.onResponse(C4473TrustDefender.getInstance().getResult().getSessionID().replace(merchantId, ""), null, null);
                return;
            }
            this.sessionListener = sessionIdListener;
            if (status == THMStatusCode.THM_NotYet) {
            }
            return;
        }
        Log.e("CYBER SOURCE", "init was not successfull, cannot perform profiling " + initStatus.getDesc());
    }
}
