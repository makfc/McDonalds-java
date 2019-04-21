package com.mcdonalds.app.ordering;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
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
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.location.LocationServicesManager;

public class LatestOrderFragment extends URLNavigationFragment implements OnClickListener, Callback {
    private Store currentStore;
    private CustomerModule customerModule;
    private McdMap map;
    private MapManager mapManager;
    private PickupLocationHolder pickupLocationHolder;

    /* renamed from: com.mcdonalds.app.ordering.LatestOrderFragment$1 */
    class C33661 implements OnClickListener {
        C33661() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            LatestOrderFragment.this.getActivity().onBackPressed();
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        Bundle extras = getActivity().getIntent().getExtras();
        String orderNumber = (String) extras.get("EXTRA_ORDER_NUMBER");
        if (extras.getBoolean("EXTRA_IS_DRIVE_THRU", false)) {
            view = inflater.inflate(C2658R.layout.fragment_latest_order_drive_thru, container, false);
            this.pickupLocationHolder = new PickupLocationHolder(view.findViewById(C2358R.C2357id.pickup_location));
            this.pickupLocationHolder.getExtraView().setVisibility(0);
            this.pickupLocationHolder.getDisclosureIcon().setVisibility(8);
            this.pickupLocationHolder.getInfoIcon().setOnClickListener(this);
            this.mapManager = new MapManager(getActivity(), this);
            getChildFragmentManager().beginTransaction().add(C2358R.C2357id.map_container, this.mapManager.getFragment(), "MAP").commit();
            this.currentStore = this.customerModule.getCurrentStore();
            updateStore();
        } else {
            view = inflater.inflate(C2658R.layout.fragment_latest_order, container, false);
        }
        if (orderNumber != null) {
            ((TextView) view.findViewById(C2358R.C2357id.order_number_label)).setText(orderNumber);
        }
        ((Button) view.findViewById(C2358R.C2357id.done_button)).setOnClickListener(new C33661());
        return view;
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        if (v == this.pickupLocationHolder.getInfoIcon()) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("extra_store_detail", this.currentStore);
            bundle.putInt("extra_store_section", StoreLocatorSection.Current.ordinal());
            startActivity(StoreDetailsActivity.class, StoreDetailsFragment.NAME, bundle);
        }
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.order_number_display_title);
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
}
