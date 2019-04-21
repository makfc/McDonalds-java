package com.mcdonalds.app.ordering.instorepickup;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.start.PickupLocationHolder;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.storelocator.MapManager;
import com.mcdonalds.app.storelocator.MapManager.Callback;
import com.mcdonalds.app.storelocator.StoreDetailsActivity;
import com.mcdonalds.app.storelocator.StoreDetailsFragment;
import com.mcdonalds.app.storelocator.StoreLocatorSection;
import com.mcdonalds.app.storelocator.maps.McdMap;
import com.mcdonalds.app.util.MapUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.PaymentMethod.PaymentMode;
import com.mcdonalds.sdk.modules.models.StoreCapabilties;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.location.LocationServicesManager;

public class ChoosePickUpFragment extends URLNavigationFragment implements OnClickListener, Callback {
    private TextView cashNotSupported;
    private Store currentStore;
    private CustomerModule customerModule;
    private View driveThruButton;
    private View insideButton;
    private boolean isUsingCash;
    private McdMap map;
    private MapManager mapManager;
    private PickupLocationHolder pickupLocationHolder;

    /* renamed from: com.mcdonalds.app.ordering.instorepickup.ChoosePickUpFragment$1 */
    class C35081 implements DialogInterface.OnClickListener {
        C35081() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            ChoosePickUpFragment.this.getActivity().startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z;
        int i = 0;
        View view = inflater.inflate(C2658R.layout.fragment_choose_pick_up, container, false);
        this.pickupLocationHolder = new PickupLocationHolder(view.findViewById(C2358R.C2357id.pickup_location));
        this.pickupLocationHolder.getExtraView().setVisibility(0);
        this.pickupLocationHolder.getDisclosureIcon().setVisibility(8);
        this.pickupLocationHolder.getInfoIcon().setOnClickListener(this);
        this.mapManager = new MapManager(getActivity(), this);
        getChildFragmentManager().beginTransaction().add(C2358R.C2357id.map_container, this.mapManager.getFragment(), "MAP").commit();
        this.driveThruButton = view.findViewById(C2358R.C2357id.drive_thru_button);
        this.insideButton = view.findViewById(C2358R.C2357id.inside_button);
        this.cashNotSupported = (TextView) view.findViewById(C2358R.C2357id.cash_not_supported);
        this.insideButton.setOnClickListener(this);
        this.currentStore = this.customerModule.getCurrentStore();
        updateStore();
        setDriveThruButton();
        if (OrderingManager.getInstance().getCurrentOrder().getPaymentMode() == PaymentMode.Cash) {
            z = true;
        } else {
            z = false;
        }
        this.isUsingCash = z;
        TextView textView = this.cashNotSupported;
        if (!this.isUsingCash) {
            i = 8;
        }
        textView.setVisibility(i);
        this.cashNotSupported.setText(C2658R.string.skip_the_line_cash_not_supported_subtitle);
        return view;
    }

    public void onResume() {
        super.onResume();
        if (!LocationServicesManager.isLocationEnabled(getActivity())) {
            this.cashNotSupported.setVisibility(0);
            this.cashNotSupported.setText(C2658R.string.skip_the_line_enable_location_services_subtitle);
        } else if (!this.isUsingCash) {
            this.cashNotSupported.setVisibility(8);
        }
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        if (v == this.pickupLocationHolder.getInfoIcon()) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("extra_store_detail", this.currentStore);
            bundle.putInt("extra_store_section", StoreLocatorSection.Current.ordinal());
            startActivity(StoreDetailsActivity.class, StoreDetailsFragment.NAME, bundle);
        } else if (v == this.driveThruButton) {
            startActivity(DriveThruConfirmationActivity.class, "drive_thru_confirmation");
        } else if (v != this.insideButton) {
        } else {
            if (!LocationServicesManager.isLocationEnabled(getActivity())) {
                MCDAlertDialogBuilder.withContext(getNavigationActivity()).setMessage((int) C2658R.string.skip_the_line_enable_location_services_dialog).setNegativeButton((int) C2658R.string.skip_the_line_cash_not_supported_ok_dialog, null).setPositiveButton((int) C2658R.string.skip_the_line_enable_location_services_settings_dialog, new C35081()).create().show();
            } else if (this.isUsingCash) {
                MCDAlertDialogBuilder.withContext(getNavigationActivity()).setMessage((int) C2658R.string.skip_the_line_cash_not_supported_dialog).setPositiveButton((int) C2658R.string.skip_the_line_cash_not_supported_ok_dialog, null).create().show();
            } else {
                startActivity(SkipTheLineActivity.class, "order_check_in");
            }
        }
    }

    public void onMapAvailable() {
        Ensighten.evaluateEvent(this, "onMapAvailable", null);
        this.map = this.mapManager.getMap();
        this.mapManager.setCallback(null);
        this.map.setMyLocationEnabled(LocationServicesManager.isLocationEnabled(getContext()));
        MapUtils.updateMap(getActivity(), this.map, this.currentStore);
    }

    public void onMapError(Dialog dialog) {
        Ensighten.evaluateEvent(this, "onMapError", new Object[]{dialog});
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.choose_pickup_method_title);
    }

    private void updateStore() {
        Ensighten.evaluateEvent(this, "updateStore", null);
        if (this.currentStore != null) {
            String name;
            if (this.currentStore.getStoreFavoriteName() != null) {
                name = this.currentStore.getStoreFavoriteName();
            } else {
                name = this.currentStore.getAddress1();
            }
            this.pickupLocationHolder.getStoreName().setText(name);
            this.pickupLocationHolder.getDistance().setText(UIUtils.distanceFromStore(getContext(), this.currentStore));
        }
    }

    private void setDriveThruButton() {
        Ensighten.evaluateEvent(this, "setDriveThruButton", null);
        if (new StoreCapabilties(this.currentStore.getPODs()).isDriveThruAvailable()) {
            this.driveThruButton.setVisibility(0);
            this.driveThruButton.setOnClickListener(this);
            return;
        }
        this.driveThruButton.setVisibility(8);
    }
}
