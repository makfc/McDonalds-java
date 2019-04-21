package com.facebook.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.facebook.FacebookException;
import com.facebook.android.C1926R;
import com.facebook.internal.ImageDownloader;
import com.facebook.internal.ImageRequest;
import com.facebook.internal.ImageRequest.Builder;
import com.facebook.internal.ImageRequest.Callback;
import com.facebook.internal.ImageResponse;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObject.Factory;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

class GraphObjectAdapter<T extends GraphObject> extends BaseAdapter implements SectionIndexer {
    static final /* synthetic */ boolean $assertionsDisabled = (!GraphObjectAdapter.class.desiredAssertionStatus());
    private static final int ACTIVITY_CIRCLE_VIEW_TYPE = 2;
    private static final int DISPLAY_SECTIONS_THRESHOLD = 1;
    private static final int GRAPH_OBJECT_VIEW_TYPE = 1;
    private static final int HEADER_VIEW_TYPE = 0;
    /* renamed from: ID */
    private static final String f6034ID = "id";
    private static final int MAX_PREFETCHED_PICTURES = 20;
    private static final String NAME = "name";
    private static final String PICTURE = "picture";
    private Context context;
    private GraphObjectCursor<T> cursor;
    private DataNeededListener dataNeededListener;
    private boolean displaySections;
    private Filter<T> filter;
    private Map<String, T> graphObjectsById = new HashMap();
    private Map<String, ArrayList<T>> graphObjectsBySection = new HashMap();
    private String groupByField;
    private final LayoutInflater inflater;
    private OnErrorListener onErrorListener;
    private final Map<String, ImageRequest> pendingRequests = new HashMap();
    private Map<String, ImageResponse> prefetchedPictureCache = new HashMap();
    private ArrayList<String> prefetchedProfilePictureIds = new ArrayList();
    private List<String> sectionKeys = new ArrayList();
    private boolean showCheckbox;
    private boolean showPicture;
    private List<String> sortFields;

    public interface DataNeededListener {
        void onDataNeeded();
    }

    interface Filter<T> {
        boolean includeItem(T t);
    }

    private interface ItemPicture extends GraphObject {
        ItemPictureData getData();
    }

    private interface ItemPictureData extends GraphObject {
        String getUrl();
    }

    public interface OnErrorListener {
        void onError(GraphObjectAdapter<?> graphObjectAdapter, FacebookException facebookException);
    }

    public static class SectionAndItem<T extends GraphObject> {
        public T graphObject;
        public String sectionKey;

        public enum Type {
            GRAPH_OBJECT,
            SECTION_HEADER,
            ACTIVITY_CIRCLE
        }

        public SectionAndItem(String sectionKey, T graphObject) {
            this.sectionKey = sectionKey;
            this.graphObject = graphObject;
        }

        public Type getType() {
            if (this.sectionKey == null) {
                return Type.ACTIVITY_CIRCLE;
            }
            if (this.graphObject == null) {
                return Type.SECTION_HEADER;
            }
            return Type.GRAPH_OBJECT;
        }
    }

    public GraphObjectAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public List<String> getSortFields() {
        return this.sortFields;
    }

    public void setSortFields(List<String> sortFields) {
        this.sortFields = sortFields;
    }

    public String getGroupByField() {
        return this.groupByField;
    }

    public void setGroupByField(String groupByField) {
        this.groupByField = groupByField;
    }

    public boolean getShowPicture() {
        return this.showPicture;
    }

    public void setShowPicture(boolean showPicture) {
        this.showPicture = showPicture;
    }

    public boolean getShowCheckbox() {
        return this.showCheckbox;
    }

    public void setShowCheckbox(boolean showCheckbox) {
        this.showCheckbox = showCheckbox;
    }

    public DataNeededListener getDataNeededListener() {
        return this.dataNeededListener;
    }

    public void setDataNeededListener(DataNeededListener dataNeededListener) {
        this.dataNeededListener = dataNeededListener;
    }

    public OnErrorListener getOnErrorListener() {
        return this.onErrorListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }

    public GraphObjectCursor<T> getCursor() {
        return this.cursor;
    }

    public boolean changeCursor(GraphObjectCursor<T> cursor) {
        if (this.cursor == cursor) {
            return false;
        }
        if (this.cursor != null) {
            this.cursor.close();
        }
        this.cursor = cursor;
        rebuildAndNotify();
        return true;
    }

    public void rebuildAndNotify() {
        rebuildSections();
        notifyDataSetChanged();
    }

    public void prioritizeViewRange(int firstVisibleItem, int lastVisibleItem, int prefetchBuffer) {
        if (lastVisibleItem >= firstVisibleItem && this.sectionKeys.size() != 0) {
            int i;
            SectionAndItem<T> sectionAndItem;
            for (i = lastVisibleItem; i >= 0; i--) {
                sectionAndItem = getSectionAndItem(i);
                if (sectionAndItem.graphObject != null) {
                    ImageRequest request = (ImageRequest) this.pendingRequests.get(getIdOfGraphObject(sectionAndItem.graphObject));
                    if (request != null) {
                        ImageDownloader.prioritizeRequest(request);
                    }
                }
            }
            int start = Math.max(0, firstVisibleItem - prefetchBuffer);
            int end = Math.min(lastVisibleItem + prefetchBuffer, getCount() - 1);
            ArrayList<T> graphObjectsToPrefetchPicturesFor = new ArrayList();
            for (i = start; i < firstVisibleItem; i++) {
                sectionAndItem = getSectionAndItem(i);
                if (sectionAndItem.graphObject != null) {
                    graphObjectsToPrefetchPicturesFor.add(sectionAndItem.graphObject);
                }
            }
            for (i = lastVisibleItem + 1; i <= end; i++) {
                sectionAndItem = getSectionAndItem(i);
                if (sectionAndItem.graphObject != null) {
                    graphObjectsToPrefetchPicturesFor.add(sectionAndItem.graphObject);
                }
            }
            Iterator i$ = graphObjectsToPrefetchPicturesFor.iterator();
            while (i$.hasNext()) {
                GraphObject graphObject = (GraphObject) i$.next();
                URI uri = getPictureUriOfGraphObject(graphObject);
                String id = getIdOfGraphObject(graphObject);
                boolean alreadyPrefetching = this.prefetchedProfilePictureIds.remove(id);
                this.prefetchedProfilePictureIds.add(id);
                if (!alreadyPrefetching) {
                    downloadProfilePicture(id, uri, null);
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public String getSectionKeyOfGraphObject(T graphObject) {
        String result = null;
        if (this.groupByField != null) {
            result = (String) graphObject.getProperty(this.groupByField);
            if (result != null && result.length() > 0) {
                result = result.substring(0, 1).toUpperCase();
            }
        }
        return result != null ? result : "";
    }

    /* Access modifiers changed, original: protected */
    public CharSequence getTitleOfGraphObject(T graphObject) {
        return (String) graphObject.getProperty("name");
    }

    /* Access modifiers changed, original: protected */
    public CharSequence getSubTitleOfGraphObject(T t) {
        return null;
    }

    /* Access modifiers changed, original: protected */
    public URI getPictureUriOfGraphObject(T graphObject) {
        String uri = null;
        String o = graphObject.getProperty(PICTURE);
        if (o instanceof String) {
            uri = o;
        } else if (o instanceof JSONObject) {
            ItemPictureData data = ((ItemPicture) Factory.create((JSONObject) o).cast(ItemPicture.class)).getData();
            if (data != null) {
                uri = data.getUrl();
            }
        }
        if (uri != null) {
            try {
                return new URI(uri);
            } catch (URISyntaxException e) {
            }
        }
        return null;
    }

    /* Access modifiers changed, original: protected */
    public View getSectionHeaderView(String sectionHeader, View convertView, ViewGroup parent) {
        TextView result = (TextView) convertView;
        if (result == null) {
            result = (TextView) this.inflater.inflate(C1926R.layout.com_facebook_picker_list_section_header, null);
        }
        result.setText(sectionHeader);
        return result;
    }

    /* Access modifiers changed, original: protected */
    public View getGraphObjectView(T graphObject, View convertView, ViewGroup parent) {
        View result = convertView;
        if (result == null) {
            result = createGraphObjectView(graphObject);
        }
        populateGraphObjectView(result, graphObject);
        return result;
    }

    private View getActivityCircleView(View convertView, ViewGroup parent) {
        View result = convertView;
        if (result == null) {
            result = this.inflater.inflate(C1926R.layout.com_facebook_picker_activity_circle_row, null);
        }
        ((ProgressBar) result.findViewById(C1926R.C1925id.com_facebook_picker_row_activity_circle)).setVisibility(0);
        return result;
    }

    /* Access modifiers changed, original: protected */
    public int getGraphObjectRowLayoutId(T t) {
        return C1926R.layout.com_facebook_picker_list_row;
    }

    /* Access modifiers changed, original: protected */
    public int getDefaultPicture() {
        return C1926R.C1924drawable.com_facebook_profile_default_icon;
    }

    /* Access modifiers changed, original: protected */
    public View createGraphObjectView(T graphObject) {
        View result = this.inflater.inflate(getGraphObjectRowLayoutId(graphObject), null);
        ViewStub checkboxStub = (ViewStub) result.findViewById(C1926R.C1925id.com_facebook_picker_checkbox_stub);
        if (checkboxStub != null) {
            if (getShowCheckbox()) {
                updateCheckboxState((CheckBox) checkboxStub.inflate(), false);
            } else {
                checkboxStub.setVisibility(8);
            }
        }
        ViewStub profilePicStub = (ViewStub) result.findViewById(C1926R.C1925id.com_facebook_picker_profile_pic_stub);
        if (getShowPicture()) {
            ((ImageView) profilePicStub.inflate()).setVisibility(0);
        } else {
            profilePicStub.setVisibility(8);
        }
        return result;
    }

    /* Access modifiers changed, original: protected */
    public void populateGraphObjectView(View view, T graphObject) {
        String id = getIdOfGraphObject(graphObject);
        view.setTag(id);
        CharSequence title = getTitleOfGraphObject(graphObject);
        TextView titleView = (TextView) view.findViewById(C1926R.C1925id.com_facebook_picker_title);
        if (titleView != null) {
            titleView.setText(title, BufferType.SPANNABLE);
        }
        CharSequence subtitle = getSubTitleOfGraphObject(graphObject);
        TextView subtitleView = (TextView) view.findViewById(C1926R.C1925id.picker_subtitle);
        if (subtitleView != null) {
            if (subtitle != null) {
                subtitleView.setText(subtitle, BufferType.SPANNABLE);
                subtitleView.setVisibility(0);
            } else {
                subtitleView.setVisibility(8);
            }
        }
        if (getShowCheckbox()) {
            updateCheckboxState((CheckBox) view.findViewById(C1926R.C1925id.com_facebook_picker_checkbox), isGraphObjectSelected(id));
        }
        if (getShowPicture()) {
            URI pictureURI = getPictureUriOfGraphObject(graphObject);
            if (pictureURI != null) {
                ImageView profilePic = (ImageView) view.findViewById(C1926R.C1925id.com_facebook_picker_image);
                if (this.prefetchedPictureCache.containsKey(id)) {
                    ImageResponse response = (ImageResponse) this.prefetchedPictureCache.get(id);
                    profilePic.setImageBitmap(response.getBitmap());
                    profilePic.setTag(response.getRequest().getImageUri());
                    return;
                }
                downloadProfilePicture(id, pictureURI, profilePic);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public String getIdOfGraphObject(T graphObject) {
        if (graphObject.asMap().containsKey("id")) {
            Object obj = graphObject.getProperty("id");
            if (obj instanceof String) {
                return (String) obj;
            }
        }
        throw new FacebookException("Received an object without an ID.");
    }

    /* Access modifiers changed, original: 0000 */
    public boolean filterIncludesItem(T graphObject) {
        return this.filter == null || this.filter.includeItem(graphObject);
    }

    /* Access modifiers changed, original: 0000 */
    public Filter<T> getFilter() {
        return this.filter;
    }

    /* Access modifiers changed, original: 0000 */
    public void setFilter(Filter<T> filter) {
        this.filter = filter;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isGraphObjectSelected(String graphObjectId) {
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    public void updateCheckboxState(CheckBox checkBox, boolean graphObjectSelected) {
    }

    /* Access modifiers changed, original: 0000 */
    public String getPictureFieldSpecifier() {
        ImageView picture = (ImageView) createGraphObjectView(null).findViewById(C1926R.C1925id.com_facebook_picker_image);
        if (picture == null) {
            return null;
        }
        LayoutParams layoutParams = picture.getLayoutParams();
        return String.format(Locale.US, "picture.height(%d).width(%d)", new Object[]{Integer.valueOf(layoutParams.height), Integer.valueOf(layoutParams.width)});
    }

    private boolean shouldShowActivityCircleCell() {
        return (this.cursor == null || !this.cursor.areMoreObjectsAvailable() || this.dataNeededListener == null || isEmpty()) ? false : true;
    }

    private void rebuildSections() {
        boolean z = true;
        this.sectionKeys = new ArrayList();
        this.graphObjectsBySection = new HashMap();
        this.graphObjectsById = new HashMap();
        this.displaySections = false;
        if (this.cursor != null && this.cursor.getCount() != 0) {
            int objectsAdded = 0;
            this.cursor.moveToFirst();
            do {
                T graphObject = this.cursor.getGraphObject();
                if (filterIncludesItem(graphObject)) {
                    objectsAdded++;
                    String sectionKeyOfItem = getSectionKeyOfGraphObject(graphObject);
                    if (!this.graphObjectsBySection.containsKey(sectionKeyOfItem)) {
                        this.sectionKeys.add(sectionKeyOfItem);
                        this.graphObjectsBySection.put(sectionKeyOfItem, new ArrayList());
                    }
                    ((List) this.graphObjectsBySection.get(sectionKeyOfItem)).add(graphObject);
                    this.graphObjectsById.put(getIdOfGraphObject(graphObject), graphObject);
                }
            } while (this.cursor.moveToNext());
            if (this.sortFields != null) {
                final Collator collator = Collator.getInstance();
                for (List<T> section : this.graphObjectsBySection.values()) {
                    Collections.sort(section, new Comparator<GraphObject>() {
                        public int compare(GraphObject a, GraphObject b) {
                            return GraphObjectAdapter.compareGraphObjects(a, b, GraphObjectAdapter.this.sortFields, collator);
                        }
                    });
                }
            }
            Collections.sort(this.sectionKeys, Collator.getInstance());
            if (this.sectionKeys.size() <= 1 || objectsAdded <= 1) {
                z = false;
            }
            this.displaySections = z;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public SectionAndItem<T> getSectionAndItem(int position) {
        if (this.sectionKeys.size() == 0) {
            return null;
        }
        String sectionKey = null;
        T graphObject = null;
        List<T> section;
        if (this.displaySections) {
            for (String key : this.sectionKeys) {
                int position2 = position - 1;
                if (position == 0) {
                    sectionKey = key;
                    position = position2;
                    break;
                }
                section = (List) this.graphObjectsBySection.get(key);
                if (position2 < section.size()) {
                    sectionKey = key;
                    GraphObject graphObject2 = (GraphObject) section.get(position2);
                    position = position2;
                    break;
                }
                position = position2 - section.size();
            }
        } else {
            sectionKey = (String) this.sectionKeys.get(0);
            section = (List) this.graphObjectsBySection.get(sectionKey);
            if (position >= 0 && position < section.size()) {
                graphObject2 = (GraphObject) ((ArrayList) this.graphObjectsBySection.get(sectionKey)).get(position);
            } else if ($assertionsDisabled || (this.dataNeededListener != null && this.cursor.areMoreObjectsAvailable())) {
                return new SectionAndItem(null, null);
            } else {
                throw new AssertionError();
            }
        }
        if (sectionKey != null) {
            return new SectionAndItem(sectionKey, graphObject2);
        }
        throw new IndexOutOfBoundsException("position");
    }

    /* Access modifiers changed, original: 0000 */
    public int getPosition(String sectionKey, T graphObject) {
        Iterator i$;
        int position = 0;
        boolean found = false;
        for (String key : this.sectionKeys) {
            if (this.displaySections) {
                position++;
            }
            if (key.equals(sectionKey)) {
                found = true;
                break;
            }
            position += ((ArrayList) this.graphObjectsBySection.get(key)).size();
        }
        if (!found) {
            return -1;
        }
        if (graphObject == null) {
            return position - (this.displaySections ? 1 : 0);
        }
        i$ = ((ArrayList) this.graphObjectsBySection.get(sectionKey)).iterator();
        while (i$.hasNext()) {
            if (Factory.hasSameId((GraphObject) i$.next(), graphObject)) {
                return position;
            }
            position++;
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.sectionKeys.size() == 0;
    }

    public int getCount() {
        int count = 0;
        if (this.sectionKeys.size() == 0) {
            return 0;
        }
        if (this.displaySections) {
            count = this.sectionKeys.size();
        }
        for (List<T> section : this.graphObjectsBySection.values()) {
            count += section.size();
        }
        if (shouldShowActivityCircleCell()) {
            return count + 1;
        }
        return count;
    }

    public boolean areAllItemsEnabled() {
        return this.displaySections;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isEnabled(int position) {
        return getSectionAndItem(position).getType() == Type.GRAPH_OBJECT;
    }

    public Object getItem(int position) {
        SectionAndItem<T> sectionAndItem = getSectionAndItem(position);
        return sectionAndItem.getType() == Type.GRAPH_OBJECT ? sectionAndItem.graphObject : null;
    }

    public long getItemId(int position) {
        SectionAndItem<T> sectionAndItem = getSectionAndItem(position);
        if (!(sectionAndItem == null || sectionAndItem.graphObject == null)) {
            String id = getIdOfGraphObject(sectionAndItem.graphObject);
            if (id != null) {
                try {
                    return Long.parseLong(id);
                } catch (NumberFormatException e) {
                }
            }
        }
        return 0;
    }

    public int getViewTypeCount() {
        return 3;
    }

    public int getItemViewType(int position) {
        switch (getSectionAndItem(position).getType()) {
            case SECTION_HEADER:
                return 0;
            case GRAPH_OBJECT:
                return 1;
            case ACTIVITY_CIRCLE:
                return 2;
            default:
                throw new FacebookException("Unexpected type of section and item.");
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        SectionAndItem<T> sectionAndItem = getSectionAndItem(position);
        switch (sectionAndItem.getType()) {
            case SECTION_HEADER:
                return getSectionHeaderView(sectionAndItem.sectionKey, convertView, parent);
            case GRAPH_OBJECT:
                return getGraphObjectView(sectionAndItem.graphObject, convertView, parent);
            case ACTIVITY_CIRCLE:
                if ($assertionsDisabled || (this.cursor.areMoreObjectsAvailable() && this.dataNeededListener != null)) {
                    this.dataNeededListener.onDataNeeded();
                    return getActivityCircleView(convertView, parent);
                }
                throw new AssertionError();
            default:
                throw new FacebookException("Unexpected type of section and item.");
        }
    }

    public Object[] getSections() {
        if (this.displaySections) {
            return this.sectionKeys.toArray();
        }
        return new Object[0];
    }

    public int getPositionForSection(int section) {
        if (!this.displaySections) {
            return 0;
        }
        section = Math.max(0, Math.min(section, this.sectionKeys.size() - 1));
        if (section < this.sectionKeys.size()) {
            return getPosition((String) this.sectionKeys.get(section), null);
        }
        return 0;
    }

    public int getSectionForPosition(int position) {
        SectionAndItem<T> sectionAndItem = getSectionAndItem(position);
        if (sectionAndItem == null || sectionAndItem.getType() == Type.ACTIVITY_CIRCLE) {
            return 0;
        }
        return Math.max(0, Math.min(this.sectionKeys.indexOf(sectionAndItem.sectionKey), this.sectionKeys.size() - 1));
    }

    public List<T> getGraphObjectsById(Collection<String> ids) {
        Set<String> idSet = new HashSet();
        idSet.addAll(ids);
        ArrayList<T> result = new ArrayList(idSet.size());
        for (String id : idSet) {
            GraphObject graphObject = (GraphObject) this.graphObjectsById.get(id);
            if (graphObject != null) {
                result.add(graphObject);
            }
        }
        return result;
    }

    private void downloadProfilePicture(final String profileId, URI pictureURI, final ImageView imageView) {
        if (pictureURI != null) {
            boolean prefetching = imageView == null;
            if (prefetching || !pictureURI.equals(imageView.getTag())) {
                if (!prefetching) {
                    imageView.setTag(profileId);
                    imageView.setImageResource(getDefaultPicture());
                }
                ImageRequest newRequest = new Builder(this.context.getApplicationContext(), pictureURI).setCallerTag(this).setCallback(new Callback() {
                    public void onCompleted(ImageResponse response) {
                        GraphObjectAdapter.this.processImageResponse(response, profileId, imageView);
                    }
                }).build();
                this.pendingRequests.put(profileId, newRequest);
                ImageDownloader.downloadAsync(newRequest);
            }
        }
    }

    private void callOnErrorListener(Exception exception) {
        if (this.onErrorListener != null) {
            if (!(exception instanceof FacebookException)) {
                exception = new FacebookException((Throwable) exception);
            }
            this.onErrorListener.onError(this, (FacebookException) exception);
        }
    }

    private void processImageResponse(ImageResponse response, String graphObjectId, ImageView imageView) {
        this.pendingRequests.remove(graphObjectId);
        if (response.getError() != null) {
            callOnErrorListener(response.getError());
        }
        if (imageView == null) {
            if (response.getBitmap() != null) {
                if (this.prefetchedPictureCache.size() >= 20) {
                    this.prefetchedPictureCache.remove((String) this.prefetchedProfilePictureIds.remove(0));
                }
                this.prefetchedPictureCache.put(graphObjectId, response);
            }
        } else if (graphObjectId.equals(imageView.getTag())) {
            Exception error = response.getError();
            Bitmap bitmap = response.getBitmap();
            if (error == null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
                imageView.setTag(response.getRequest().getImageUri());
            }
        }
    }

    private static int compareGraphObjects(GraphObject a, GraphObject b, Collection<String> sortFields, Collator collator) {
        for (String sortField : sortFields) {
            String sa = (String) a.getProperty(sortField);
            String sb = (String) b.getProperty(sortField);
            if (sa != null && sb != null) {
                int result = collator.compare(sa, sb);
                if (result != 0) {
                    return result;
                }
            } else if (sa != null || sb != null) {
                return sa == null ? -1 : 1;
            }
        }
        return 0;
    }
}
