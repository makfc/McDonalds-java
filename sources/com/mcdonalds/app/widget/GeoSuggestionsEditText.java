package com.mcdonalds.app.widget;

import android.content.Context;
import android.location.Address;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.FrameLayout;
import android.widget.TextView.OnEditorActionListener;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.storelocator.maps.model.LatLng;
import com.mcdonalds.app.widget.GeoLocationTask.GeolocationListener;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import java.util.List;
import java.util.concurrent.Executor;

public class GeoSuggestionsEditText extends FrameLayout implements GeolocationListener {
    private AutoCompleteTextView field;
    private LatLng mNorthwestBound;
    private OnFocusChangeListener mOnFocusChangeListener;
    private OnFocusChangeListener mSearchFocusChangedListener = new C38732();
    private LatLng mSoutheastBound;
    private String suffix;
    private GeoSuggestionsAdapter suggestionsAdapter;

    /* renamed from: com.mcdonalds.app.widget.GeoSuggestionsEditText$1 */
    class C38721 implements OnClickListener {
        C38721() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.GeoSuggestionsEditText", "access$100", new Object[]{GeoSuggestionsEditText.this}).setText("");
        }
    }

    /* renamed from: com.mcdonalds.app.widget.GeoSuggestionsEditText$2 */
    class C38732 implements OnFocusChangeListener {
        C38732() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            Ensighten.evaluateEvent(this, "onFocusChange", new Object[]{v, new Boolean(hasFocus)});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.GeoSuggestionsEditText", "access$200", new Object[]{GeoSuggestionsEditText.this}) != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.GeoSuggestionsEditText", "access$200", new Object[]{GeoSuggestionsEditText.this}).onFocusChange(v, hasFocus);
            }
        }
    }

    private class GeoSuggestionsFilter extends Filter {
        private GeoSuggestionsFilter() {
        }

        /* synthetic */ GeoSuggestionsFilter(GeoSuggestionsEditText x0, C38721 x1) {
            this();
        }

        /* Access modifiers changed, original: protected */
        public FilterResults performFiltering(CharSequence constraint) {
            Ensighten.evaluateEvent(this, "performFiltering", new Object[]{constraint});
            if (constraint != null) {
                GeoLocationTask listener = new GeoLocationTask().setBounds(Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.GeoSuggestionsEditText", "access$300", new Object[]{GeoSuggestionsEditText.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.GeoSuggestionsEditText", "access$400", new Object[]{GeoSuggestionsEditText.this})).setListener(GeoSuggestionsEditText.this);
                Executor executor = AsyncTask.THREAD_POOL_EXECUTOR;
                String[] strArr = new String[]{GeoSuggestionsEditText.this.getText()};
                if (listener instanceof AsyncTask) {
                    AsyncTaskInstrumentation.executeOnExecutor(listener, executor, strArr);
                } else {
                    listener.executeOnExecutor(executor, strArr);
                }
            }
            return null;
        }

        /* Access modifiers changed, original: protected */
        public void publishResults(CharSequence constraint, FilterResults results) {
            Ensighten.evaluateEvent(this, "publishResults", new Object[]{constraint, results});
        }
    }

    public GeoSuggestionsEditText(Context context) {
        super(context);
        init(context);
    }

    public GeoSuggestionsEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        Ensighten.evaluateEvent(this, "init", new Object[]{context});
        inflate(context, C2658R.layout.view_geolocator_textview, this);
        if (!isInEditMode()) {
            Configuration configuration = Configuration.getSharedInstance();
            this.suffix = configuration.getStringForKey("interface.storelocator.predictiveGeocoder.android.searchSuffix");
            if (this.suffix == null) {
                this.suffix = configuration.getStringForKey("interface.storelocator.predictiveGeocoderSearchSuffix");
            }
            double southeastLatitude = configuration.getDoubleForKey("interface.storelocator.predictiveGeocoder.android.bounds.southeast.latitude");
            double southeastLongitude = configuration.getDoubleForKey("interface.storelocator.predictiveGeocoder.android.bounds.southeast.longitude");
            double northwestLatitude = configuration.getDoubleForKey("interface.storelocator.predictiveGeocoder.android.bounds.northwest.latitude");
            double northwestLongitude = configuration.getDoubleForKey("interface.storelocator.predictiveGeocoder.android.bounds.northwest.longitude");
            if (!(southeastLatitude == 0.0d || southeastLongitude == 0.0d || northwestLatitude == 0.0d || northwestLongitude == 0.0d)) {
                this.mSoutheastBound = new LatLng(southeastLatitude, southeastLongitude);
                this.mNorthwestBound = new LatLng(northwestLatitude, northwestLongitude);
            }
            this.suggestionsAdapter = new GeoSuggestionsAdapter(getContext());
            this.suggestionsAdapter.setFilter(new GeoSuggestionsFilter(this, null));
            int threshold = Configuration.getSharedInstance().getIntForKey("interface.storelocator.predictiveGeocoderInputThreshold");
            boolean showSuggestions = Configuration.getSharedInstance().getBooleanForKey("interface.storelocator.showPredictiveGeocoderResults");
            this.field = (AutoCompleteTextView) findViewById(C2358R.C2357id.input);
            this.field.setOnFocusChangeListener(this.mSearchFocusChangedListener);
            this.field.setDropDownVerticalOffset(Math.round(TypedValue.applyDimension(1, 15.0f, getResources().getDisplayMetrics())));
            if (showSuggestions) {
                this.field.setAdapter(this.suggestionsAdapter);
                this.field.setThreshold(threshold);
            }
            findViewById(C2358R.C2357id.clear).setOnClickListener(new C38721());
        }
    }

    public void onGeolocation(List<Address> addresses) {
        Ensighten.evaluateEvent(this, "onGeolocation", new Object[]{addresses});
        this.suggestionsAdapter.setAddresses(addresses);
    }

    public void setOnEditorActionListener(OnEditorActionListener onEditorActionListener) {
        Ensighten.evaluateEvent(this, "setOnEditorActionListener", new Object[]{onEditorActionListener});
        this.field.setOnEditorActionListener(onEditorActionListener);
    }

    public void setOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        Ensighten.evaluateEvent(this, "setOnFocusChangeListener", new Object[]{onFocusChangeListener});
        this.mOnFocusChangeListener = onFocusChangeListener;
    }

    public void clearFocus() {
        Ensighten.evaluateEvent(this, "clearFocus", null);
        this.field.clearFocus();
    }

    public String getText() {
        Ensighten.evaluateEvent(this, "getText", null);
        String text = this.field.getText().toString();
        return (this.suffix == null || text.contains(this.suffix)) ? text : text + " " + this.suffix;
    }

    public void setText(String text) {
        Ensighten.evaluateEvent(this, "setText", new Object[]{text});
        this.field.setText(text);
    }

    public AutoCompleteTextView getField() {
        Ensighten.evaluateEvent(this, "getField", null);
        return this.field;
    }

    public String getCleanText() {
        Ensighten.evaluateEvent(this, "getCleanText", null);
        return this.field.getText().toString();
    }

    public boolean isEmpty() {
        Ensighten.evaluateEvent(this, "isEmpty", null);
        return this.field.getText() == null || this.field.getText().toString().isEmpty();
    }
}
