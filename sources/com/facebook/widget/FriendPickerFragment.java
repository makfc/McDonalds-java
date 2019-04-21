package com.facebook.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.facebook.AppEventsLogger;
import com.facebook.FacebookException;
import com.facebook.Request;
import com.facebook.Session;
import com.facebook.android.C1926R;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.model.GraphUser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FriendPickerFragment extends PickerFragment<GraphUser> {
    public static final String FRIEND_PICKER_TYPE_KEY = "com.facebook.widget.FriendPickerFragment.FriendPickerType";
    /* renamed from: ID */
    private static final String f6036ID = "id";
    public static final String MULTI_SELECT_BUNDLE_KEY = "com.facebook.widget.FriendPickerFragment.MultiSelect";
    private static final String NAME = "name";
    public static final String USER_ID_BUNDLE_KEY = "com.facebook.widget.FriendPickerFragment.UserId";
    private FriendPickerType friendPickerType;
    private boolean multiSelect;
    private List<String> preSelectedFriendIds;
    private String userId;

    public enum FriendPickerType {
        FRIENDS("/friends", true),
        TAGGABLE_FRIENDS("/taggable_friends", false),
        INVITABLE_FRIENDS("/invitable_friends", false);
        
        private final boolean requestIsCacheable;
        private final String requestPath;

        private FriendPickerType(String path, boolean cacheable) {
            this.requestPath = path;
            this.requestIsCacheable = cacheable;
        }

        /* Access modifiers changed, original: 0000 */
        public String getRequestPath() {
            return this.requestPath;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean isCacheable() {
            return this.requestIsCacheable;
        }
    }

    private class ImmediateLoadingStrategy extends LoadingStrategy {
        private ImmediateLoadingStrategy() {
            super();
        }

        /* synthetic */ ImmediateLoadingStrategy(FriendPickerFragment x0, C20331 x1) {
            this();
        }

        /* Access modifiers changed, original: protected */
        public void onLoadFinished(GraphObjectPagingLoader<GraphUser> loader, SimpleGraphObjectCursor<GraphUser> data) {
            super.onLoadFinished(loader, data);
            if (data != null && !loader.isLoading()) {
                if (data.areMoreObjectsAvailable()) {
                    followNextLink();
                    return;
                }
                FriendPickerFragment.this.hideActivityCircle();
                if (data.isFromCache()) {
                    loader.refreshOriginalRequest(data.getCount() == 0 ? 2000 : 0);
                }
            }
        }

        /* Access modifiers changed, original: protected */
        public boolean canSkipRoundTripIfCached() {
            return FriendPickerFragment.this.friendPickerType.isCacheable();
        }

        private void followNextLink() {
            FriendPickerFragment.this.displayActivityCircle();
            this.loader.followNextLink();
        }
    }

    public FriendPickerFragment() {
        this(null);
    }

    @SuppressLint({"ValidFragment"})
    public FriendPickerFragment(Bundle args) {
        super(GraphUser.class, C1926R.layout.com_facebook_friendpickerfragment, args);
        this.multiSelect = true;
        this.friendPickerType = FriendPickerType.FRIENDS;
        this.preSelectedFriendIds = new ArrayList();
        setFriendPickerSettingsFromBundle(args);
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean getMultiSelect() {
        return this.multiSelect;
    }

    public void setMultiSelect(boolean multiSelect) {
        if (this.multiSelect != multiSelect) {
            this.multiSelect = multiSelect;
            setSelectionStrategy(createSelectionStrategy());
        }
    }

    public void setFriendPickerType(FriendPickerType type) {
        this.friendPickerType = type;
    }

    public void setSelectionByIds(List<String> userIds) {
        this.preSelectedFriendIds.addAll(userIds);
    }

    public void setSelectionByIds(String... userIds) {
        setSelectionByIds(Arrays.asList(userIds));
    }

    public void setSelection(GraphUser... graphUsers) {
        setSelection(Arrays.asList(graphUsers));
    }

    public void setSelection(List<GraphUser> graphUsers) {
        List userIds = new ArrayList();
        for (GraphUser graphUser : graphUsers) {
            userIds.add(graphUser.getId());
        }
        setSelectionByIds(userIds);
    }

    public List<GraphUser> getSelection() {
        return getSelectedGraphObjects();
    }

    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
        TypedArray a = activity.obtainStyledAttributes(attrs, C1926R.styleable.com_facebook_friend_picker_fragment);
        setMultiSelect(a.getBoolean(C1926R.styleable.com_facebook_friend_picker_fragment_multi_select, this.multiSelect));
        a.recycle();
    }

    public void setSettingsFromBundle(Bundle inState) {
        super.setSettingsFromBundle(inState);
        setFriendPickerSettingsFromBundle(inState);
    }

    /* Access modifiers changed, original: 0000 */
    public void saveSettingsToBundle(Bundle outState) {
        super.saveSettingsToBundle(outState);
        outState.putString(USER_ID_BUNDLE_KEY, this.userId);
        outState.putBoolean(MULTI_SELECT_BUNDLE_KEY, this.multiSelect);
    }

    /* Access modifiers changed, original: 0000 */
    public PickerFragmentAdapter<GraphUser> createAdapter() {
        PickerFragmentAdapter<GraphUser> adapter = new PickerFragmentAdapter<GraphUser>(getActivity()) {
            /* Access modifiers changed, original: protected */
            public int getGraphObjectRowLayoutId(GraphUser graphObject) {
                return C1926R.layout.com_facebook_picker_list_row;
            }

            /* Access modifiers changed, original: protected */
            public int getDefaultPicture() {
                return C1926R.C1924drawable.com_facebook_profile_default_icon;
            }
        };
        adapter.setShowCheckbox(true);
        adapter.setShowPicture(getShowPictures());
        adapter.setSortFields(Arrays.asList(new String[]{"name"}));
        adapter.setGroupByField("name");
        return adapter;
    }

    /* Access modifiers changed, original: 0000 */
    public LoadingStrategy createLoadingStrategy() {
        return new ImmediateLoadingStrategy(this, null);
    }

    /* Access modifiers changed, original: 0000 */
    public SelectionStrategy createSelectionStrategy() {
        return this.multiSelect ? new MultiSelectionStrategy() : new SingleSelectionStrategy();
    }

    /* Access modifiers changed, original: 0000 */
    public Request getRequestForLoadData(Session session) {
        if (this.adapter == null) {
            throw new FacebookException("Can't issue requests until Fragment has been created.");
        }
        return createRequest(this.userId != null ? this.userId : "me", this.extraFields, session);
    }

    /* Access modifiers changed, original: 0000 */
    public String getDefaultTitleText() {
        return getString(C1926R.string.com_facebook_choose_friends);
    }

    /* Access modifiers changed, original: 0000 */
    public void logAppEvents(boolean doneButtonClicked) {
        AppEventsLogger logger = AppEventsLogger.newLogger(getActivity(), getSession());
        Bundle parameters = new Bundle();
        parameters.putString(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME, doneButtonClicked ? AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_COMPLETED : AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN);
        parameters.putInt("num_friends_picked", getSelection().size());
        logger.logSdkEvent(AnalyticsEvents.EVENT_FRIEND_PICKER_USAGE, null, parameters);
    }

    public void loadData(boolean forceReload) {
        super.loadData(forceReload);
        setSelectedGraphObjects(this.preSelectedFriendIds);
    }

    private Request createRequest(String userID, Set<String> extraFields, Session session) {
        Request request = Request.newGraphPathRequest(session, userID + this.friendPickerType.getRequestPath(), null);
        Set<String> fields = new HashSet(extraFields);
        fields.addAll(Arrays.asList(new String[]{"id", "name"}));
        String pictureField = this.adapter.getPictureFieldSpecifier();
        if (pictureField != null) {
            fields.add(pictureField);
        }
        Bundle parameters = request.getParameters();
        parameters.putString("fields", TextUtils.join(",", fields));
        request.setParameters(parameters);
        return request;
    }

    private void setFriendPickerSettingsFromBundle(Bundle inState) {
        if (inState != null) {
            if (inState.containsKey(USER_ID_BUNDLE_KEY)) {
                setUserId(inState.getString(USER_ID_BUNDLE_KEY));
            }
            setMultiSelect(inState.getBoolean(MULTI_SELECT_BUNDLE_KEY, this.multiSelect));
            if (inState.containsKey(FRIEND_PICKER_TYPE_KEY)) {
                try {
                    this.friendPickerType = FriendPickerType.valueOf(inState.getString(FRIEND_PICKER_TYPE_KEY));
                } catch (Exception e) {
                }
            }
        }
    }
}
