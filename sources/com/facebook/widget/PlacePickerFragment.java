package com.facebook.widget;

import android.app.Activity;
import android.content.res.TypedArray;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import com.facebook.AppEventsLogger;
import com.facebook.FacebookException;
import com.facebook.LoggingBehavior;
import com.facebook.Request;
import com.facebook.Session;
import com.facebook.android.C1926R;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.model.GraphPlace;
import com.facebook.widget.GraphObjectAdapter.DataNeededListener;
import com.facebook.widget.PickerFragment.OnErrorListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class PlacePickerFragment extends PickerFragment<GraphPlace> {
    private static final String CATEGORY = "category";
    public static final int DEFAULT_RADIUS_IN_METERS = 1000;
    public static final int DEFAULT_RESULTS_LIMIT = 100;
    /* renamed from: ID */
    private static final String f6037ID = "id";
    private static final String LOCATION = "location";
    public static final String LOCATION_BUNDLE_KEY = "com.facebook.widget.PlacePickerFragment.Location";
    private static final String NAME = "name";
    public static final String RADIUS_IN_METERS_BUNDLE_KEY = "com.facebook.widget.PlacePickerFragment.RadiusInMeters";
    public static final String RESULTS_LIMIT_BUNDLE_KEY = "com.facebook.widget.PlacePickerFragment.ResultsLimit";
    public static final String SEARCH_TEXT_BUNDLE_KEY = "com.facebook.widget.PlacePickerFragment.SearchText";
    public static final String SHOW_SEARCH_BOX_BUNDLE_KEY = "com.facebook.widget.PlacePickerFragment.ShowSearchBox";
    private static final String TAG = "PlacePickerFragment";
    private static final String WERE_HERE_COUNT = "were_here_count";
    private static final int searchTextTimerDelayInMilliseconds = 2000;
    private boolean hasSearchTextChangedSinceLastQuery;
    private Location location;
    private int radiusInMeters;
    private int resultsLimit;
    private EditText searchBox;
    private String searchText;
    private Timer searchTextTimer;
    private boolean showSearchBox;

    /* renamed from: com.facebook.widget.PlacePickerFragment$2 */
    class C20522 extends TimerTask {
        C20522() {
        }

        public void run() {
            PlacePickerFragment.this.onSearchTextTimerTriggered();
        }
    }

    /* renamed from: com.facebook.widget.PlacePickerFragment$3 */
    class C20533 implements Runnable {
        C20533() {
        }

        public void run() {
            FacebookException error;
            OnErrorListener onErrorListener;
            try {
                PlacePickerFragment.this.loadData(true);
                if (null != null) {
                    onErrorListener = PlacePickerFragment.this.getOnErrorListener();
                    if (onErrorListener != null) {
                        onErrorListener.onError(PlacePickerFragment.this, null);
                        return;
                    }
                    Logger.log(LoggingBehavior.REQUESTS, PlacePickerFragment.TAG, "Error loading data : %s", null);
                }
            } catch (FacebookException fe) {
                error = fe;
                if (error != null) {
                    onErrorListener = PlacePickerFragment.this.getOnErrorListener();
                    if (onErrorListener != null) {
                        onErrorListener.onError(PlacePickerFragment.this, error);
                        return;
                    }
                    Logger.log(LoggingBehavior.REQUESTS, PlacePickerFragment.TAG, "Error loading data : %s", error);
                }
            } catch (Exception e) {
                FacebookException error2 = new FacebookException(e);
                if (error2 != null) {
                    onErrorListener = PlacePickerFragment.this.getOnErrorListener();
                    if (onErrorListener != null) {
                        onErrorListener.onError(PlacePickerFragment.this, error2);
                    } else {
                        Logger.log(LoggingBehavior.REQUESTS, PlacePickerFragment.TAG, "Error loading data : %s", error2);
                    }
                    error = error2;
                    return;
                }
            } catch (Throwable th) {
                if (null != null) {
                    onErrorListener = PlacePickerFragment.this.getOnErrorListener();
                    if (onErrorListener != null) {
                        onErrorListener.onError(PlacePickerFragment.this, null);
                    } else {
                        Logger.log(LoggingBehavior.REQUESTS, PlacePickerFragment.TAG, "Error loading data : %s", null);
                    }
                }
            }
        }
    }

    private class AsNeededLoadingStrategy extends LoadingStrategy {

        /* renamed from: com.facebook.widget.PlacePickerFragment$AsNeededLoadingStrategy$1 */
        class C20541 implements DataNeededListener {
            C20541() {
            }

            public void onDataNeeded() {
                if (!AsNeededLoadingStrategy.this.loader.isLoading()) {
                    AsNeededLoadingStrategy.this.loader.followNextLink();
                }
            }
        }

        private AsNeededLoadingStrategy() {
            super();
        }

        /* synthetic */ AsNeededLoadingStrategy(PlacePickerFragment x0, C20511 x1) {
            this();
        }

        public void attach(GraphObjectAdapter<GraphPlace> adapter) {
            super.attach(adapter);
            this.adapter.setDataNeededListener(new C20541());
        }

        /* Access modifiers changed, original: protected */
        public void onLoadFinished(GraphObjectPagingLoader<GraphPlace> loader, SimpleGraphObjectCursor<GraphPlace> data) {
            super.onLoadFinished(loader, data);
            if (data != null && !loader.isLoading()) {
                PlacePickerFragment.this.hideActivityCircle();
                if (data.isFromCache()) {
                    loader.refreshOriginalRequest(data.areMoreObjectsAvailable() ? 2000 : 0);
                }
            }
        }
    }

    private class SearchTextWatcher implements TextWatcher {
        private SearchTextWatcher() {
        }

        /* synthetic */ SearchTextWatcher(PlacePickerFragment x0, C20511 x1) {
            this();
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            PlacePickerFragment.this.onSearchBoxTextChanged(s.toString(), false);
        }

        public void afterTextChanged(Editable s) {
        }
    }

    public PlacePickerFragment() {
        this(null);
    }

    public PlacePickerFragment(Bundle args) {
        super(GraphPlace.class, C1926R.layout.com_facebook_placepickerfragment, args);
        this.radiusInMeters = 1000;
        this.resultsLimit = 100;
        this.showSearchBox = true;
        setPlacePickerSettingsFromBundle(args);
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getRadiusInMeters() {
        return this.radiusInMeters;
    }

    public void setRadiusInMeters(int radiusInMeters) {
        this.radiusInMeters = radiusInMeters;
    }

    public int getResultsLimit() {
        return this.resultsLimit;
    }

    public void setResultsLimit(int resultsLimit) {
        this.resultsLimit = resultsLimit;
    }

    public String getSearchText() {
        return this.searchText;
    }

    public void setSearchText(String searchText) {
        if (TextUtils.isEmpty(searchText)) {
            searchText = null;
        }
        this.searchText = searchText;
        if (this.searchBox != null) {
            this.searchBox.setText(searchText);
        }
    }

    public void onSearchBoxTextChanged(String searchText, boolean forceReloadEventIfSameText) {
        if (forceReloadEventIfSameText || !Utility.stringsEqualOrEmpty(this.searchText, searchText)) {
            if (TextUtils.isEmpty(searchText)) {
                searchText = null;
            }
            this.searchText = searchText;
            this.hasSearchTextChangedSinceLastQuery = true;
            if (this.searchTextTimer == null) {
                this.searchTextTimer = createSearchTextTimer();
            }
        }
    }

    public GraphPlace getSelection() {
        Collection<GraphPlace> selection = getSelectedGraphObjects();
        return (selection == null || selection.isEmpty()) ? null : (GraphPlace) selection.iterator().next();
    }

    public void setSettingsFromBundle(Bundle inState) {
        super.setSettingsFromBundle(inState);
        setPlacePickerSettingsFromBundle(inState);
    }

    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
        TypedArray a = activity.obtainStyledAttributes(attrs, C1926R.styleable.com_facebook_place_picker_fragment);
        setRadiusInMeters(a.getInt(C1926R.styleable.com_facebook_place_picker_fragment_radius_in_meters, this.radiusInMeters));
        setResultsLimit(a.getInt(C1926R.styleable.com_facebook_place_picker_fragment_results_limit, this.resultsLimit));
        if (a.hasValue(C1926R.styleable.com_facebook_place_picker_fragment_results_limit)) {
            setSearchText(a.getString(C1926R.styleable.com_facebook_place_picker_fragment_search_text));
        }
        this.showSearchBox = a.getBoolean(C1926R.styleable.com_facebook_place_picker_fragment_show_search_box, this.showSearchBox);
        a.recycle();
    }

    /* Access modifiers changed, original: 0000 */
    public void setupViews(ViewGroup view) {
        if (this.showSearchBox) {
            ListView listView = (ListView) view.findViewById(C1926R.C1925id.com_facebook_picker_list_view);
            listView.addHeaderView(getActivity().getLayoutInflater().inflate(C1926R.layout.com_facebook_picker_search_box, listView, false), null, false);
            this.searchBox = (EditText) view.findViewById(C1926R.C1925id.com_facebook_picker_search_text);
            this.searchBox.addTextChangedListener(new SearchTextWatcher(this, null));
            if (!TextUtils.isEmpty(this.searchText)) {
                this.searchBox.setText(this.searchText);
            }
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (this.searchBox != null) {
            ((InputMethodManager) getActivity().getSystemService("input_method")).showSoftInput(this.searchBox, 1);
        }
    }

    public void onDetach() {
        super.onDetach();
        if (this.searchBox != null) {
            ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.searchBox.getWindowToken(), 0);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void saveSettingsToBundle(Bundle outState) {
        super.saveSettingsToBundle(outState);
        outState.putInt(RADIUS_IN_METERS_BUNDLE_KEY, this.radiusInMeters);
        outState.putInt(RESULTS_LIMIT_BUNDLE_KEY, this.resultsLimit);
        outState.putString(SEARCH_TEXT_BUNDLE_KEY, this.searchText);
        outState.putParcelable(LOCATION_BUNDLE_KEY, this.location);
        outState.putBoolean(SHOW_SEARCH_BOX_BUNDLE_KEY, this.showSearchBox);
    }

    /* Access modifiers changed, original: 0000 */
    public void onLoadingData() {
        this.hasSearchTextChangedSinceLastQuery = false;
    }

    /* Access modifiers changed, original: 0000 */
    public Request getRequestForLoadData(Session session) {
        return createRequest(this.location, this.radiusInMeters, this.resultsLimit, this.searchText, this.extraFields, session);
    }

    /* Access modifiers changed, original: 0000 */
    public String getDefaultTitleText() {
        return getString(C1926R.string.com_facebook_nearby);
    }

    /* Access modifiers changed, original: 0000 */
    public void logAppEvents(boolean doneButtonClicked) {
        AppEventsLogger logger = AppEventsLogger.newLogger(getActivity(), getSession());
        Bundle parameters = new Bundle();
        parameters.putString(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME, doneButtonClicked ? AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_COMPLETED : AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN);
        parameters.putInt("num_places_picked", getSelection() != null ? 1 : 0);
        logger.logSdkEvent(AnalyticsEvents.EVENT_PLACE_PICKER_USAGE, null, parameters);
    }

    /* Access modifiers changed, original: 0000 */
    public PickerFragmentAdapter<GraphPlace> createAdapter() {
        PickerFragmentAdapter<GraphPlace> adapter = new PickerFragmentAdapter<GraphPlace>(getActivity()) {
            /* Access modifiers changed, original: protected */
            public CharSequence getSubTitleOfGraphObject(GraphPlace graphObject) {
                String category = graphObject.getCategory();
                Integer wereHereCount = (Integer) graphObject.getProperty(PlacePickerFragment.WERE_HERE_COUNT);
                if (category != null && wereHereCount != null) {
                    return PlacePickerFragment.this.getString(C1926R.string.com_facebook_placepicker_subtitle_format, category, wereHereCount);
                } else if (category == null && wereHereCount != null) {
                    return PlacePickerFragment.this.getString(C1926R.string.com_facebook_placepicker_subtitle_were_here_only_format, wereHereCount);
                } else if (category == null || wereHereCount != null) {
                    return null;
                } else {
                    return PlacePickerFragment.this.getString(C1926R.string.com_facebook_placepicker_subtitle_catetory_only_format, category);
                }
            }

            /* Access modifiers changed, original: protected */
            public int getGraphObjectRowLayoutId(GraphPlace graphObject) {
                return C1926R.layout.com_facebook_placepickerfragment_list_row;
            }

            /* Access modifiers changed, original: protected */
            public int getDefaultPicture() {
                return C1926R.C1924drawable.com_facebook_place_default_icon;
            }
        };
        adapter.setShowCheckbox(false);
        adapter.setShowPicture(getShowPictures());
        return adapter;
    }

    /* Access modifiers changed, original: 0000 */
    public LoadingStrategy createLoadingStrategy() {
        return new AsNeededLoadingStrategy(this, null);
    }

    /* Access modifiers changed, original: 0000 */
    public SelectionStrategy createSelectionStrategy() {
        return new SingleSelectionStrategy();
    }

    private Request createRequest(Location location, int radiusInMeters, int resultsLimit, String searchText, Set<String> extraFields, Session session) {
        Request request = Request.newPlacesSearchRequest(session, location, radiusInMeters, resultsLimit, searchText, null);
        Set<String> fields = new HashSet(extraFields);
        fields.addAll(Arrays.asList(new String[]{"id", "name", "location", "category", WERE_HERE_COUNT}));
        String pictureField = this.adapter.getPictureFieldSpecifier();
        if (pictureField != null) {
            fields.add(pictureField);
        }
        Bundle parameters = request.getParameters();
        parameters.putString("fields", TextUtils.join(",", fields));
        request.setParameters(parameters);
        return request;
    }

    private void setPlacePickerSettingsFromBundle(Bundle inState) {
        if (inState != null) {
            setRadiusInMeters(inState.getInt(RADIUS_IN_METERS_BUNDLE_KEY, this.radiusInMeters));
            setResultsLimit(inState.getInt(RESULTS_LIMIT_BUNDLE_KEY, this.resultsLimit));
            if (inState.containsKey(SEARCH_TEXT_BUNDLE_KEY)) {
                setSearchText(inState.getString(SEARCH_TEXT_BUNDLE_KEY));
            }
            if (inState.containsKey(LOCATION_BUNDLE_KEY)) {
                setLocation((Location) inState.getParcelable(LOCATION_BUNDLE_KEY));
            }
            this.showSearchBox = inState.getBoolean(SHOW_SEARCH_BOX_BUNDLE_KEY, this.showSearchBox);
        }
    }

    private Timer createSearchTextTimer() {
        Timer timer = new Timer();
        timer.schedule(new C20522(), 0, 2000);
        return timer;
    }

    private void onSearchTextTimerTriggered() {
        if (this.hasSearchTextChangedSinceLastQuery) {
            new Handler(Looper.getMainLooper()).post(new C20533());
            return;
        }
        this.searchTextTimer.cancel();
        this.searchTextTimer = null;
    }
}
