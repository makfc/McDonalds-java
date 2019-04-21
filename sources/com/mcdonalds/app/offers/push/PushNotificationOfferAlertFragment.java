package com.mcdonalds.app.offers.push;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.offers.OfferActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.notification.PushConstants;

public class PushNotificationOfferAlertFragment extends URLNavigationFragment implements OnClickListener {
    public static final String NAME = PushNotificationOfferAlertFragment.class.getSimpleName();
    private Offer mOffer;
    private int mOfferId = -1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mOfferId = arguments.getInt(PushConstants.PROMOTION_ID);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_push_notification_offer_alert, container, false);
        Button goToOfferButton = (Button) rootView.findViewById(C2358R.C2357id.go_to_offer_button);
        ((Button) rootView.findViewById(C2358R.C2357id.ok_button)).setOnClickListener(this);
        goToOfferButton.setOnClickListener(this);
        return rootView;
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        switch (v.getId()) {
            case C2358R.C2357id.ok_button /*2131821158*/:
                getActivity().finish();
                return;
            case C2358R.C2357id.go_to_offer_button /*2131821423*/:
                Bundle bundle = new Bundle();
                bundle.putSerializable(URLNavigationActivity.DATA_PASSER_KEY, this.mOffer);
                startActivity(OfferActivity.class, "offer_detail", bundle);
                getNavigationActivity().finish();
                return;
            default:
                return;
        }
    }
}
