package com.mcdonalds.app.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.ensighten.Ensighten;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;

public class NetworkChangeReceiver extends BroadcastReceiver {
    private static long lastAlert = -1;

    /* renamed from: com.mcdonalds.app.net.NetworkChangeReceiver$1 */
    class C32901 implements OnClickListener {
        C32901() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    public void onReceive(Context context, Intent intent) {
        boolean isConnected = true;
        Ensighten.evaluateEvent(this, "onReceive", new Object[]{context, intent});
        NetworkInfo activeNetwork = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetwork == null || !activeNetwork.isConnectedOrConnecting()) {
            isConnected = false;
        }
        if (!isConnected && lastAlert + 60000 <= System.currentTimeMillis()) {
            lastAlert = System.currentTimeMillis();
            MCDAlertDialogBuilder.withContext(context).setMessage(context.getResources().getString(C2658R.string.error_no_network_connection)).setPositiveButton(context.getResources().getString(C2658R.string.f6083ok), new C32901()).create().show();
        }
    }
}
