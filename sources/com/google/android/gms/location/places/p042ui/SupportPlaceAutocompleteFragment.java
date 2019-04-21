package com.google.android.gms.location.places.p042ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p000v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import com.google.android.gms.C2063R;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.p042ui.PlaceAutocomplete.IntentBuilder;
import com.google.android.gms.maps.model.LatLngBounds;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
/* renamed from: com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment */
public class SupportPlaceAutocompleteFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    @Nullable
    private LatLngBounds zzaYA;
    @Nullable
    private AutocompleteFilter zzaYB;
    @Nullable
    private PlaceSelectionListener zzaYC;
    private View zzaYx;
    private View zzaYy;
    private EditText zzaYz;

    /* renamed from: com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment$1 */
    class C23201 implements OnClickListener {
        C23201() {
        }

        public void onClick(View view) {
            SupportPlaceAutocompleteFragment.this.zzDp();
        }
    }

    /* renamed from: com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment$2 */
    class C23212 implements OnClickListener {
        C23212() {
        }

        public void onClick(View view) {
            SupportPlaceAutocompleteFragment.this.setText("");
        }
    }

    private void zzDo() {
        int i = 0;
        int i2 = !this.zzaYz.getText().toString().isEmpty() ? 1 : 0;
        View view = this.zzaYy;
        if (i2 == 0) {
            i = 8;
        }
        view.setVisibility(i);
    }

    private void zzDp() {
        int i;
        int connectionStatusCode;
        try {
            startActivityForResult(new IntentBuilder(2).setBoundsBias(this.zzaYA).setFilter(this.zzaYB).zzeR(this.zzaYz.getText().toString()).zziH(1).build(getActivity()), 30421);
            i = -1;
        } catch (GooglePlayServicesRepairableException e) {
            connectionStatusCode = e.getConnectionStatusCode();
            Log.e("Places", "Could not open autocomplete activity", e);
            i = connectionStatusCode;
        } catch (GooglePlayServicesNotAvailableException e2) {
            connectionStatusCode = e2.errorCode;
            Log.e("Places", "Could not open autocomplete activity", e2);
            i = connectionStatusCode;
        }
        if (i != -1) {
            GoogleApiAvailability.getInstance().showErrorDialogFragment(getActivity(), i, 30422);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 30421) {
            if (i2 == -1) {
                Place place = PlaceAutocomplete.getPlace(getActivity(), intent);
                if (this.zzaYC != null) {
                    this.zzaYC.onPlaceSelected(place);
                }
                setText(place.getName().toString());
            } else if (i2 == 2) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), intent);
                if (this.zzaYC != null) {
                    this.zzaYC.onError(status);
                }
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "SupportPlaceAutocompleteFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "SupportPlaceAutocompleteFragment#onCreateView", null);
            }
        }
        View inflate = layoutInflater.inflate(C2063R.layout.place_autocomplete_fragment, viewGroup, false);
        this.zzaYx = inflate.findViewById(C2063R.C2062id.place_autocomplete_search_button);
        this.zzaYy = inflate.findViewById(C2063R.C2062id.place_autocomplete_clear_button);
        this.zzaYz = (EditText) inflate.findViewById(C2063R.C2062id.place_autocomplete_search_input);
        C23201 c23201 = new C23201();
        this.zzaYx.setOnClickListener(c23201);
        this.zzaYz.setOnClickListener(c23201);
        this.zzaYy.setOnClickListener(new C23212());
        zzDo();
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onDestroyView() {
        this.zzaYx = null;
        this.zzaYy = null;
        this.zzaYz = null;
        super.onDestroyView();
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public void setText(CharSequence charSequence) {
        this.zzaYz.setText(charSequence);
        zzDo();
    }
}
