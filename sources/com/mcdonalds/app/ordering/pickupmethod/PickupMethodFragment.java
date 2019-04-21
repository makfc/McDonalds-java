package com.mcdonalds.app.ordering.pickupmethod;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.payment.PaymentActivity;
import com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodActivity;
import com.mcdonalds.app.ordering.pickupmethod.kiosk.KioskPickupMethodFragment;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.storelocator.StoreDetailsActivity;
import com.mcdonalds.app.storelocator.StoreDetailsFragment;
import com.mcdonalds.app.storelocator.StoreLocatorSection;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.connectors.middleware.model.MWPointOfDistribution;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.StoreCapabilties;
import com.mcdonalds.sdk.modules.models.StoreCapabilties.StoreCapability;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.List;

public class PickupMethodFragment extends URLNavigationFragment implements OnClickListener {
    public static final String NAME = PickupMethodFragment.class.getSimpleName();
    boolean hasCarbsideSCANNER = false;
    boolean hasDriveThruSCANNER = false;
    boolean hasKioskSCANNER = false;
    boolean hasLobbySCANNER = false;

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_checkout_check_in_options);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        if (((ArrayList) Configuration.getSharedInstance().getValueForKey("interface.checkin.checkinSeclectionDisplayNameMappings")) != null) {
            rootView = inflater.inflate(C2658R.layout.fragment_pickup_method_visual_selection, null);
        } else {
            rootView = inflater.inflate(C2658R.layout.fragment_pickup_method, null);
        }
        TextView storeName = (TextView) rootView.findViewById(C2358R.C2357id.store_name);
        View lobbyButton = rootView.findViewById(C2358R.C2357id.lobby_button);
        View kioskButton = rootView.findViewById(C2358R.C2357id.kiosk_button);
        View driveThruButton = rootView.findViewById(C2358R.C2357id.drive_thru_button);
        View curbsideButton = rootView.findViewById(C2358R.C2357id.curbside_button);
        View imageButtonWithInstructions = rootView.findViewById(C2358R.C2357id.image_button_with_instructions);
        TextView pickupOrderTextView = (TextView) rootView.findViewById(C2358R.C2357id.pickup_order_text);
        if (lobbyButton != null) {
            lobbyButton.setVisibility(8);
        }
        if (kioskButton != null) {
            kioskButton.setVisibility(8);
        }
        if (driveThruButton != null) {
            driveThruButton.setVisibility(8);
        }
        if (curbsideButton != null) {
            curbsideButton.setVisibility(8);
        }
        Store store = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentStore();
        if (!TextUtils.isEmpty(store.getStoreName())) {
            storeName.setText(store.getStoreName());
            ((View) storeName.getParent()).setVisibility(0);
        }
        List<StoreCapability> availableCapabilities = new StoreCapabilties(store.getPODs()).filterAvailableCapabilities();
        for (StoreCapability capability : availableCapabilities) {
            if (capability.getCapabilityId() == MWPointOfDistribution.FrontCounter.integerValue().intValue()) {
                lobbyButton.setVisibility(0);
                lobbyButton.setOnClickListener(this);
                if (capability.isHasScanner()) {
                    this.hasLobbySCANNER = true;
                }
            } else if (capability.getCapabilityId() == MWPointOfDistribution.DriveThru.integerValue().intValue()) {
                driveThruButton.setVisibility(0);
                driveThruButton.setOnClickListener(this);
                if (capability.isHasScanner()) {
                    this.hasDriveThruSCANNER = true;
                }
            } else if (capability.getCapabilityId() == MWPointOfDistribution.ColdKiosk.integerValue().intValue()) {
                curbsideButton.setVisibility(0);
                curbsideButton.setOnClickListener(this);
                if (capability.isHasScanner()) {
                    this.hasCarbsideSCANNER = true;
                }
            } else if (capability.getCapabilityId() == MWPointOfDistribution.CSO.integerValue().intValue()) {
                kioskButton.setVisibility(0);
                kioskButton.setOnClickListener(this);
                if (capability.isHasScanner()) {
                    this.hasKioskSCANNER = true;
                }
            }
        }
        if (!ListUtils.isEmpty(availableCapabilities) && availableCapabilities.size() == 1) {
            pickupOrderTextView.setVisibility(8);
            lobbyButton.setVisibility(8);
            kioskButton.setVisibility(8);
            if (imageButtonWithInstructions != null) {
                imageButtonWithInstructions.setVisibility(0);
                imageButtonWithInstructions.setOnClickListener(this);
            }
        }
        setHasOptionsMenu(true);
        return rootView;
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        switch (v.getId()) {
            case C2358R.C2357id.drive_thru_button /*2131821198*/:
                if (this.hasDriveThruSCANNER) {
                    startActivity(KioskPickupMethodActivity.class, KioskPickupMethodFragment.NAME);
                    return;
                } else {
                    startActivity(PaymentActivity.class);
                    return;
                }
            case C2358R.C2357id.lobby_button /*2131821411*/:
                AnalyticsUtils.trackOnClickEvent("/checkout", "Checkin at Lobby");
                startActivity(PaymentActivity.class);
                return;
            case C2358R.C2357id.kiosk_button /*2131821412*/:
                AnalyticsUtils.trackOnClickEvent("/checkout", "Checkin at Kiosk");
                startActivity(KioskPickupMethodActivity.class, KioskPickupMethodFragment.NAME);
                return;
            case C2358R.C2357id.curbside_button /*2131821413*/:
                if (this.hasCarbsideSCANNER) {
                    startActivity(KioskPickupMethodActivity.class, KioskPickupMethodFragment.NAME);
                    return;
                } else {
                    startActivity(PaymentActivity.class);
                    return;
                }
            case C2358R.C2357id.image_button_with_instructions /*2131821415*/:
                startActivity(PaymentActivity.class);
                getActivity().finish();
                return;
            default:
                return;
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        inflater.inflate(C2358R.C2360menu.menu_info, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        if (item.getItemId() == C2358R.C2357id.menu_info) {
            Store store = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentStore();
            if (store != null) {
                Bundle bundle = new Bundle();
                bundle.putInt("extra_store_section", StoreLocatorSection.Current.ordinal());
                bundle.putParcelable("extra_store_detail", store);
                startActivity(StoreDetailsActivity.class, StoreDetailsFragment.NAME, bundle);
            } else {
                AsyncException.report(getNavigationActivity().getString(C2658R.string.dialog_store_details_unavailable));
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
