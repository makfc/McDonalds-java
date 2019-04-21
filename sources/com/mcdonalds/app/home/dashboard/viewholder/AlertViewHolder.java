package com.mcdonalds.app.home.dashboard.viewholder;

import android.support.p001v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.home.dashboard.DashboardAdapter.DashboardListener;
import com.mcdonalds.gma.hongkong.C2658R;

public class AlertViewHolder extends ViewHolder {
    public AlertViewHolder(View view) {
        super(view);
    }

    public static ViewHolder create(LayoutInflater inflater, final DashboardListener mDashboardListener, Boolean isPhoneVerfication) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.AlertViewHolder", "create", new Object[]{inflater, mDashboardListener, isPhoneVerfication});
        View view = inflater.inflate(C2658R.layout.alert_view_holder, null);
        ((ImageView) view.findViewById(C2358R.C2357id.cancel_alert)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                mDashboardListener.onDismissAlertClicked();
            }
        });
        TextView message = (TextView) view.findViewById(C2358R.C2357id.verify_pws_message);
        if (isPhoneVerfication.booleanValue()) {
            message.setText(C2658R.string.verify_sms_alert);
        }
        message.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                mDashboardListener.onAlertClicked();
            }
        });
        return new AlertViewHolder(view);
    }
}
