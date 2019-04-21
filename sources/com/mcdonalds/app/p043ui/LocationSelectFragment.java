package com.mcdonalds.app.p043ui;

import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.location.LocationServicesManager;

/* renamed from: com.mcdonalds.app.ui.LocationSelectFragment */
public class LocationSelectFragment extends URLNavigationFragment implements TextWatcher {
    private TextView mLatitudeTextView;
    private TextView mLongitudeTextView;
    private Switch mUseDeviceSwitch;

    /* renamed from: com.mcdonalds.app.ui.LocationSelectFragment$1 */
    class C38031 implements OnCheckedChangeListener {
        C38031() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Ensighten.evaluateEvent(this, "onCheckedChanged", new Object[]{buttonView, new Boolean(isChecked)});
            LocationSelectFragment.access$000(LocationSelectFragment.this);
        }
    }

    static /* synthetic */ void access$000(LocationSelectFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.LocationSelectFragment", "access$000", new Object[]{x0});
        x0.updateMockLocation();
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_choose_location);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_location_select, container, false);
        this.mLatitudeTextView = (TextView) rootView.findViewById(C2358R.C2357id.location_latitude_textview);
        this.mLongitudeTextView = (TextView) rootView.findViewById(C2358R.C2357id.location_longitude_textview);
        this.mUseDeviceSwitch = (Switch) rootView.findViewById(C2358R.C2357id.location_switch);
        Location mockLocation = ModuleManager.getMockLocation();
        if (mockLocation != null) {
            this.mUseDeviceSwitch.setChecked(false);
            this.mLatitudeTextView.setText(new Double(mockLocation.getLatitude()).toString());
            this.mLongitudeTextView.setText(new Double(mockLocation.getLongitude()).toString());
        } else {
            this.mUseDeviceSwitch.setChecked(true);
            Location mDeviceLocation = getDeviceLocation();
            if (mDeviceLocation != null) {
                this.mLatitudeTextView.setText(new Double(mDeviceLocation.getLatitude()).toString());
                this.mLongitudeTextView.setText(new Double(mDeviceLocation.getLongitude()).toString());
            }
            this.mLatitudeTextView.setEnabled(false);
            this.mLongitudeTextView.setEnabled(false);
        }
        this.mLongitudeTextView.addTextChangedListener(this);
        this.mLatitudeTextView.addTextChangedListener(this);
        this.mUseDeviceSwitch.setOnCheckedChangeListener(new C38031());
        return rootView;
    }

    private Location getDeviceLocation() {
        Ensighten.evaluateEvent(this, "getDeviceLocation", null);
        Location location = null;
        if (!LocationServicesManager.getInstance().areGooglePlayServicesAvailable()) {
            return location;
        }
        try {
            return LocationServicesManager.getInstance().getCurrentUserLocation();
        } catch (IllegalStateException e) {
            return location;
        }
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{s, new Integer(start), new Integer(count), new Integer(after)});
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{s, new Integer(start), new Integer(before), new Integer(count)});
    }

    public void afterTextChanged(Editable s) {
        Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{s});
        if (!this.mUseDeviceSwitch.isChecked()) {
            updateMockLocation();
        }
    }

    private void updateMockLocation() {
        Ensighten.evaluateEvent(this, "updateMockLocation", null);
        LocalDataManager manager = LocalDataManager.getSharedInstance();
        if (this.mUseDeviceSwitch.isChecked()) {
            ModuleManager.setMockLocation(null, null);
            Location mDeviceLocation = getDeviceLocation();
            if (mDeviceLocation != null) {
                this.mLatitudeTextView.setText(new Double(mDeviceLocation.getLatitude()).toString());
                this.mLongitudeTextView.setText(new Double(mDeviceLocation.getLongitude()).toString());
            }
            this.mLatitudeTextView.setEnabled(false);
            this.mLongitudeTextView.setEnabled(false);
            return;
        }
        this.mLatitudeTextView.setEnabled(true);
        this.mLongitudeTextView.setEnabled(true);
        Double lat = null;
        Double lng = null;
        try {
            lat = Double.valueOf(Double.parseDouble(this.mLatitudeTextView.getText().toString()));
            lng = Double.valueOf(Double.parseDouble(this.mLongitudeTextView.getText().toString()));
        } catch (Exception e) {
        } finally {
            ModuleManager.setMockLocation(lat, lng);
        }
    }
}
