package com.mcdonalds.app.firstload;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.start.FindStoreActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.widget.GeoSuggestionsEditText;
import com.mcdonalds.gma.hongkong.C2658R;

public class SearchByAddressFragment extends URLNavigationFragment implements OnEditorActionListener {
    public static final String NAME = SearchByAddressFragment.class.getSimpleName();
    private GeoSuggestionsEditText mGeoSearchView;

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_enter_address);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup group = (ViewGroup) inflater.inflate(C2658R.layout.fragment_search_by_address_method, container, false);
        this.mGeoSearchView = (GeoSuggestionsEditText) group.findViewById(C2358R.C2357id.geoSearchText);
        this.mGeoSearchView.setOnEditorActionListener(this);
        return group;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        Ensighten.evaluateEvent(this, "onEditorAction", new Object[]{textView, new Integer(i), keyEvent});
        if (i == 3 || keyEvent.getKeyCode() == 66) {
            if (this.mGeoSearchView.isEmpty()) {
                return false;
            }
            String query = this.mGeoSearchView.getText();
            if (AppUtils.isNetworkConnected(getNavigationActivity())) {
                this.mGeoSearchView.clearFocus();
                ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.mGeoSearchView.getField().getWindowToken(), 0);
                AnalyticsUtils.trackSearch(getAnalyticsTitle(), query);
                Bundle extras = new Bundle();
                extras.putBoolean("map_only", true);
                extras.putString("search_by_address", query);
                extras.putBoolean("set_current_store", true);
                startActivity(FindStoreActivity.class, "store_locator", extras);
            } else {
                UIUtils.showNoNetworkAlert(getNavigationActivity());
            }
        }
        return true;
    }
}
