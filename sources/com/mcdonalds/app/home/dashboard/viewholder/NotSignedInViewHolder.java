package com.mcdonalds.app.home.dashboard.viewholder;

import android.content.Context;
import android.support.p001v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.home.dashboard.DashboardAdapter.DashboardListener;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class NotSignedInViewHolder extends ViewHolder implements OnClickListener {
    private final View mItemView;
    private final NotSignedInViewListener mListener;
    public TextView mRegisterButton;
    public TextView mRegisterPrompt;
    public TextView mSignInButton;

    public interface NotSignedInViewListener {
        void onRegisterClicked();

        void onSignInClicked();
    }

    private NotSignedInViewHolder(View itemView, NotSignedInViewListener notSignedInViewListener) {
        super(itemView);
        this.mItemView = itemView;
        this.mListener = notSignedInViewListener;
        this.mRegisterPrompt = (TextView) itemView.findViewById(C2358R.C2357id.register_prompt);
        this.mRegisterButton = (TextView) itemView.findViewById(C2358R.C2357id.offers_register);
        this.mSignInButton = (TextView) itemView.findViewById(C2358R.C2357id.offers_sign_in);
        this.mRegisterButton.setOnClickListener(this);
        this.mSignInButton.setOnClickListener(this);
    }

    public static void bind(Context context, NotSignedInViewHolder viewHolder) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.NotSignedInViewHolder", "bind", new Object[]{context, viewHolder});
        viewHolder.mRegisterPrompt.setText(UIUtils.getStringByName(context, (String) Configuration.getSharedInstance().getValueForKey("interface.dashboard.offersTextGuest")));
    }

    public static ViewHolder create(LayoutInflater layoutInflater, final DashboardListener dashboardListener) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.NotSignedInViewHolder", "create", new Object[]{layoutInflater, dashboardListener});
        return new NotSignedInViewHolder(layoutInflater.inflate(C2658R.layout.signed_out_offers_item, null), new NotSignedInViewListener() {
            public void onRegisterClicked() {
                Ensighten.evaluateEvent(this, "onRegisterClicked", null);
                if (dashboardListener != null) {
                    dashboardListener.onRegisterClick();
                }
            }

            public void onSignInClicked() {
                Ensighten.evaluateEvent(this, "onSignInClicked", null);
                if (dashboardListener != null) {
                    dashboardListener.onSignInClick();
                }
            }
        });
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        switch (v.getId()) {
            case C2358R.C2357id.offers_sign_in /*2131821847*/:
                this.mListener.onSignInClicked();
                return;
            case C2358R.C2357id.offers_register /*2131821848*/:
                this.mListener.onRegisterClicked();
                return;
            default:
                return;
        }
    }
}
