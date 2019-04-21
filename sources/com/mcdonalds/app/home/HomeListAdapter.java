package com.mcdonalds.app.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.menu.OrderDetailsFragment;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.widget.offers.GridRowModel;
import com.mcdonalds.app.widget.offers.ListRowModel;
import com.mcdonalds.app.widget.offers.MCDListSectionHeaderModel;
import com.mcdonalds.app.widget.offers.OfferHomeItemModel;
import com.mcdonalds.app.widget.offers.SelectorRowModel;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@Deprecated
public class HomeListAdapter extends BaseAdapter {
    private final int OFFERS_COLUMNS = 2;
    private List<OrderOffer> mAppliedOffers;
    private final Context mContext;
    private boolean mHasOffers;
    private List<HomeListItem> mHomeListItems;
    private final LayoutInflater mInflater;
    private HomeListAdapterListener mListener;
    private Boolean mLocationDisabled = Boolean.valueOf(false);
    private boolean mLoggedIn = false;
    private View mNoOffersView;
    private View mNotLoggedInView;
    private HomeListObservable mObservable;
    private List<OfferHomeItemModel> mOfferItems;
    private Boolean mRefreshing = Boolean.valueOf(false);
    private int mSelectedOffersTypeID = -1;
    private boolean mSubscribedToOffers;

    @Deprecated
    public interface HomeListAdapterListener {
        void onGridItemClick(Offer offer);

        void onOffersTypeSelectorChanged(RadioGroup radioGroup);

        void onRegisterClick();

        void onRemoveFromBasketClicked(OrderOffer orderOffer);

        void onSignInClick();
    }

    @Deprecated
    class AppliedOfferViewHolder {
        TextView mExpirationTextView;
        ImageView mFoodImageView;
        TextView mFoodNameTextView;
        TextView mFoodSubtitleTextView;
        LinearLayout mGridItem;
        LinearLayout mParent;
        Button mRemoveFromBasket;

        AppliedOfferViewHolder(View view) {
            this.mParent = (LinearLayout) view.findViewById(C2358R.C2357id.parent);
            this.mFoodNameTextView = (TextView) view.findViewById(C2358R.C2357id.name);
            this.mFoodSubtitleTextView = (TextView) view.findViewById(C2358R.C2357id.subtitle);
            this.mFoodImageView = (ImageView) view.findViewById(2131820643);
            this.mGridItem = (LinearLayout) view.findViewById(C2358R.C2357id.grid_item);
            this.mExpirationTextView = (TextView) view.findViewById(C2358R.C2357id.expiration);
            this.mRemoveFromBasket = (Button) view.findViewById(C2358R.C2357id.remove_from_basket_button);
        }
    }

    @Deprecated
    class HeaderViewHolder {
        ImageView mHeaderImage;
        TextView mHeaderText;

        HeaderViewHolder() {
        }
    }

    @Deprecated
    class HomeItemViewHolder {
        ImageView mIcon;
        View mRootView;
        TextView mSubtitle;
        TextView mTitle;

        HomeItemViewHolder(View view) {
            this.mRootView = view.findViewById(C2358R.C2357id.container);
            this.mIcon = (ImageView) view.findViewById(C2358R.C2357id.icon_imageview);
            this.mTitle = (TextView) view.findViewById(C2358R.C2357id.title_textview);
            this.mSubtitle = (TextView) view.findViewById(C2358R.C2357id.subtitle_textview);
        }
    }

    @Deprecated
    class NoOfferViewHolder {
        TextView mCheckBackPrompt;
        View mContainer;
        ProgressBar mProgressBar;

        NoOfferViewHolder(View noOffersView) {
            this.mContainer = noOffersView.findViewById(C2358R.C2357id.check_back_container);
            this.mProgressBar = (ProgressBar) noOffersView.findViewById(C2358R.C2357id.progress_bar);
            this.mCheckBackPrompt = (TextView) noOffersView.findViewById(C2358R.C2357id.check_back_prompt);
        }
    }

    @Deprecated
    class OfferViewHolder {
        TextView mExpirationTextView;
        ImageView mFoodImageView;
        TextView mFoodNameTextView;
        TextView mFoodSubtitleTextView;
        LinearLayout mGridItem;

        OfferViewHolder() {
        }
    }

    /* renamed from: com.mcdonalds.app.home.HomeListAdapter$1 */
    class C23731 implements OnClickListener {
        C23731() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeListAdapter", "access$100", new Object[]{HomeListAdapter.this}).onRemoveFromBasketClicked((OrderOffer) Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeListAdapter", "access$000", new Object[]{HomeListAdapter.this}).get(0));
        }
    }

    /* renamed from: com.mcdonalds.app.home.HomeListAdapter$2 */
    class C23742 implements OnCheckedChangeListener {
        C23742() {
        }

        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            Ensighten.evaluateEvent(this, "onCheckedChanged", new Object[]{radioGroup, new Integer(i)});
            HomeListAdapter.access$202(HomeListAdapter.this, i);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeListAdapter", "access$100", new Object[]{HomeListAdapter.this}).onOffersTypeSelectorChanged(radioGroup);
        }
    }

    /* renamed from: com.mcdonalds.app.home.HomeListAdapter$3 */
    class C23753 implements OnClickListener {
        C23753() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeListAdapter", "access$300", new Object[]{HomeListAdapter.this}).startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        }
    }

    /* renamed from: com.mcdonalds.app.home.HomeListAdapter$7 */
    class C23797 implements OnClickListener {
        C23797() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeListAdapter", "access$100", new Object[]{HomeListAdapter.this}).onRegisterClick();
        }
    }

    /* renamed from: com.mcdonalds.app.home.HomeListAdapter$8 */
    class C23808 implements OnClickListener {
        C23808() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeListAdapter", "access$100", new Object[]{HomeListAdapter.this}).onSignInClick();
        }
    }

    static /* synthetic */ int access$202(HomeListAdapter x0, int x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeListAdapter", "access$202", new Object[]{x0, new Integer(x1)});
        x0.mSelectedOffersTypeID = x1;
        return x1;
    }

    public HomeListAdapter(Context context, URLNavigationFragment fragment) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mHomeListItems = new ArrayList();
        this.mListener = (HomeListAdapterListener) fragment;
        this.mNotLoggedInView = new View(this.mContext);
        this.mObservable = new HomeListObservable();
        this.mAppliedOffers = new ArrayList();
        this.mOfferItems = new ArrayList();
    }

    public void setSubscribedToOffers(boolean subscribedToOffers) {
        Ensighten.evaluateEvent(this, "setSubscribedToOffers", new Object[]{new Boolean(subscribedToOffers)});
        this.mSubscribedToOffers = subscribedToOffers;
    }

    public void addAppliedOffer(OrderOffer orderOffer) {
        Ensighten.evaluateEvent(this, "addAppliedOffer", new Object[]{orderOffer});
        this.mAppliedOffers.add(orderOffer);
    }

    public void clearAppliedOffers() {
        Ensighten.evaluateEvent(this, "clearAppliedOffers", null);
        this.mAppliedOffers.clear();
    }

    public void addOffersGridSection(List<Offer> section) {
        Ensighten.evaluateEvent(this, "addOffersGridSection", new Object[]{section});
        initRowList(section);
    }

    public void addOffersListSection(MCDListSectionHeaderModel sectionHeaderModel, List<Offer> section) {
        Ensighten.evaluateEvent(this, "addOffersListSection", new Object[]{sectionHeaderModel, section});
        this.mOfferItems.add(sectionHeaderModel);
        addListOffers(section);
    }

    public void addSectionHeader(MCDListSectionHeaderModel sectionHeaderModel) {
        Ensighten.evaluateEvent(this, "addSectionHeader", new Object[]{sectionHeaderModel});
        this.mOfferItems.add(sectionHeaderModel);
    }

    public void addOffersTypeSelector() {
        Ensighten.evaluateEvent(this, "addOffersTypeSelector", null);
        this.mOfferItems.add(new SelectorRowModel());
    }

    public void setOffersTypeSelectorSelected(int ID) {
        Ensighten.evaluateEvent(this, "setOffersTypeSelectorSelected", new Object[]{new Integer(ID)});
        this.mSelectedOffersTypeID = ID;
    }

    public void setLocationDisabled(boolean locationDisabled) {
        Ensighten.evaluateEvent(this, "setLocationDisabled", new Object[]{new Boolean(locationDisabled)});
        this.mLocationDisabled = Boolean.valueOf(locationDisabled);
    }

    public void addHomeListItem(HomeListItem homeListItem) {
        Ensighten.evaluateEvent(this, "addHomeListItem", new Object[]{homeListItem});
        this.mHomeListItems.add(homeListItem);
    }

    public void removeHomeListItem(String title) {
        Ensighten.evaluateEvent(this, "removeHomeListItem", new Object[]{title});
        for (HomeListItem homeListItem : new ArrayList(this.mHomeListItems)) {
            if (homeListItem.getTitle().equals(title)) {
                this.mHomeListItems.remove(homeListItem);
            }
        }
    }

    public List<HomeListItem> getHomeListItems() {
        Ensighten.evaluateEvent(this, "getHomeListItems", null);
        return this.mHomeListItems;
    }

    public void clearOffers() {
        Ensighten.evaluateEvent(this, "clearOffers", null);
        this.mOfferItems.clear();
    }

    public boolean isEnabled(int position) {
        Ensighten.evaluateEvent(this, "isEnabled", new Object[]{new Integer(position)});
        switch (getItemViewType(position)) {
            case 2:
            case 3:
                return false;
            default:
                return true;
        }
    }

    public int getItemViewType(int position) {
        boolean hasAppliedOffers = false;
        Ensighten.evaluateEvent(this, "getItemViewType", new Object[]{new Integer(position)});
        if (this.mLoggedIn && !this.mAppliedOffers.isEmpty()) {
            hasAppliedOffers = true;
        }
        int appliedOffersIndex = this.mHomeListItems.size() + this.mOfferItems.size();
        if (position < this.mHomeListItems.size()) {
            return 0;
        }
        if (!this.mLoggedIn) {
            return 7;
        }
        if (hasAppliedOffers && position >= appliedOffersIndex) {
            return 4;
        }
        if (!this.mHasOffers) {
            return 8;
        }
        int index = position - this.mHomeListItems.size();
        switch (((OfferHomeItemModel) this.mOfferItems.get(index)).getItemType()) {
            case Header:
                return index == 0 ? 1 : 3;
            case Selector:
                return 6;
            case GridRow:
                return 2;
            case ListRow:
                return 5;
            default:
                return 0;
        }
    }

    public int getViewTypeCount() {
        Ensighten.evaluateEvent(this, "getViewTypeCount", null);
        return 9;
    }

    public boolean isEmpty() {
        Ensighten.evaluateEvent(this, "isEmpty", null);
        return this.mOfferItems.size() <= 1;
    }

    public int getCount() {
        Ensighten.evaluateEvent(this, "getCount", null);
        int count = this.mHomeListItems.size();
        boolean hasAppliedOffers = this.mLoggedIn && !this.mAppliedOffers.isEmpty();
        if (!this.mLoggedIn) {
            return count + 1;
        }
        if (!this.mHasOffers) {
            return count + 1;
        }
        count += this.mOfferItems.size();
        if (hasAppliedOffers) {
            return count + this.mAppliedOffers.size();
        }
        return count;
    }

    public Object getItem(int position) {
        boolean hasAppliedOffers = true;
        Ensighten.evaluateEvent(this, "getItem", new Object[]{new Integer(position)});
        if (!this.mLoggedIn || this.mAppliedOffers.isEmpty()) {
            hasAppliedOffers = false;
        }
        if (position < this.mHomeListItems.size()) {
            return this.mHomeListItems.get(position);
        }
        if (!this.mLoggedIn) {
            return this.mNotLoggedInView;
        }
        if (!this.mHasOffers) {
            return this.mNoOffersView;
        }
        int index = position - this.mHomeListItems.size();
        if (index < this.mOfferItems.size()) {
            return this.mOfferItems.get(index);
        }
        if (hasAppliedOffers) {
            return this.mAppliedOffers.get(0);
        }
        return null;
    }

    public long getItemId(int position) {
        Ensighten.evaluateEvent(this, "getItemId", new Object[]{new Integer(position)});
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View homeItemView;
        switch (getItemViewType(position)) {
            case 0:
                homeItemView = getHomeItemView(position, convertView);
                break;
            case 1:
                homeItemView = getOffersHeaderView(position, convertView);
                break;
            case 2:
                homeItemView = getOfferGridRowView(position, convertView);
                break;
            case 3:
                homeItemView = getHeaderView(position, convertView);
                break;
            case 4:
                homeItemView = getAppliedOfferView(position, convertView);
                break;
            case 5:
                homeItemView = getOfferListRowView(position, convertView);
                break;
            case 6:
                homeItemView = getOfferSelectorView(position, convertView);
                break;
            case 7:
                homeItemView = getNotSignedInView();
                break;
            case 8:
                homeItemView = getNoOffersView();
                break;
            default:
                homeItemView = null;
                break;
        }
        Ensighten.getViewReturnValue(homeItemView, position);
        Ensighten.processView((Object) this, "getView");
        Ensighten.getViewReturnValue(null, -1);
        return homeItemView;
    }

    private View getAppliedOfferView(int position, View convertView) {
        AppliedOfferViewHolder appliedOfferViewHolder;
        Ensighten.evaluateEvent(this, "getAppliedOfferView", new Object[]{new Integer(position), convertView});
        if (convertView == null || convertView.getTag() == null) {
            convertView = this.mInflater.inflate(C2658R.layout.applied_offer_layout, null);
            appliedOfferViewHolder = new AppliedOfferViewHolder(convertView);
            convertView.setTag(appliedOfferViewHolder);
        } else {
            appliedOfferViewHolder = (AppliedOfferViewHolder) convertView.getTag();
        }
        appliedOfferViewHolder.mFoodNameTextView.setText(((OrderOffer) this.mAppliedOffers.get(0)).getOffer().getName());
        appliedOfferViewHolder.mFoodSubtitleTextView.setText(((OrderOffer) this.mAppliedOffers.get(0)).getOffer().getSubtitle());
        appliedOfferViewHolder.mExpirationTextView.setText(this.mContext.getString(C2658R.string.expires) + UIUtils.formatDateMonthDayYear(((OrderOffer) this.mAppliedOffers.get(0)).getOffer().getLocalValidThrough()));
        appliedOfferViewHolder.mRemoveFromBasket.setOnClickListener(new C23731());
        if (this.mContext != null) {
            Glide.with(this.mContext).load(((OrderOffer) this.mAppliedOffers.get(0)).getOffer().getSmallImagePath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(appliedOfferViewHolder.mFoodImageView);
        }
        return convertView;
    }

    private View getHomeItemView(int position, View convertView) {
        HomeItemViewHolder homeItemViewHolder;
        Ensighten.evaluateEvent(this, "getHomeItemView", new Object[]{new Integer(position), convertView});
        View view = convertView;
        if (view == null) {
            view = this.mInflater.inflate(C2658R.layout.view_home_list_item, null);
            homeItemViewHolder = new HomeItemViewHolder(view);
            view.setTag(homeItemViewHolder);
        } else {
            homeItemViewHolder = (HomeItemViewHolder) view.getTag();
        }
        HomeListItem item = (HomeListItem) getItem(position);
        homeItemViewHolder.mTitle.setText(item.getTitle());
        homeItemViewHolder.mSubtitle.setText(item.getSubTitle());
        homeItemViewHolder.mIcon.setImageResource(item.getIconImageResource());
        if (item.getLink().equals(URLNavigationActivity.URI_SCHEME + OrderDetailsFragment.NAME)) {
            homeItemViewHolder.mRootView.setBackgroundDrawable(this.mContext.getResources().getDrawable(C2358R.C2359drawable.transparent));
            homeItemViewHolder.mRootView.setBackgroundColor(this.mContext.getResources().getColor(17170445));
        } else {
            homeItemViewHolder.mRootView.setBackgroundDrawable(this.mContext.getResources().getDrawable(C2358R.C2359drawable.home_item_bg));
        }
        return view;
    }

    private View getOffersHeaderView(int position, View convertView) {
        HeaderViewHolder headerViewHolder;
        Ensighten.evaluateEvent(this, "getOffersHeaderView", new Object[]{new Integer(position), convertView});
        View headerView = convertView;
        if (headerView == null || headerView.getTag() == null) {
            headerView = this.mInflater.inflate(C2658R.layout.section_header, null);
            headerViewHolder = new HeaderViewHolder();
            headerViewHolder.mHeaderText = (TextView) headerView.findViewById(C2358R.C2357id.section_name);
            headerViewHolder.mHeaderText.setBackgroundColor(this.mContext.getResources().getColor(C2658R.color.mcd_yellow));
            headerViewHolder.mHeaderImage = (ImageView) headerView.findViewById(C2358R.C2357id.section_icon);
            headerView.setTag(headerViewHolder);
        } else {
            headerViewHolder = (HeaderViewHolder) headerView.getTag();
        }
        MCDListSectionHeaderModel item = (MCDListSectionHeaderModel) getItem(position);
        if (item.getTitle() != null) {
            headerViewHolder.mHeaderText.setText(item.getTitle());
            headerViewHolder.mHeaderText.setVisibility(0);
        } else {
            headerViewHolder.mHeaderText.setVisibility(8);
        }
        if (item.isImageVisible()) {
            headerViewHolder.mHeaderImage.setVisibility(0);
            headerViewHolder.mHeaderImage.setBackgroundResource(item.getImageResource());
        } else {
            headerViewHolder.mHeaderImage.setVisibility(8);
        }
        return headerView;
    }

    private View getHeaderView(int position, View convertView) {
        HeaderViewHolder headerViewHolder;
        Ensighten.evaluateEvent(this, "getHeaderView", new Object[]{new Integer(position), convertView});
        View headerView = convertView;
        if (headerView == null || headerView.getTag() == null) {
            headerView = this.mInflater.inflate(C2658R.layout.section_header, null);
            headerViewHolder = new HeaderViewHolder();
            headerViewHolder.mHeaderText = (TextView) headerView.findViewById(C2358R.C2357id.section_name);
            headerViewHolder.mHeaderImage = (ImageView) headerView.findViewById(C2358R.C2357id.section_icon);
            headerView.setTag(headerViewHolder);
        } else {
            headerViewHolder = (HeaderViewHolder) headerView.getTag();
        }
        MCDListSectionHeaderModel item = (MCDListSectionHeaderModel) getItem(position);
        if (item.getTitle() != null) {
            headerViewHolder.mHeaderText.setText(item.getTitle());
            headerViewHolder.mHeaderText.setVisibility(0);
        } else {
            headerViewHolder.mHeaderText.setVisibility(8);
        }
        if (item.isImageVisible()) {
            headerViewHolder.mHeaderImage.setVisibility(0);
            headerViewHolder.mHeaderImage.setBackgroundResource(item.getImageResource());
        } else {
            headerViewHolder.mHeaderImage.setVisibility(8);
        }
        return headerView;
    }

    private View getOfferSelectorView(int position, View convertView) {
        Ensighten.evaluateEvent(this, "getOfferSelectorView", new Object[]{new Integer(position), convertView});
        if (convertView == null) {
            convertView = this.mInflater.inflate(C2658R.layout.offers_location_selector, null);
        }
        RadioGroup radioGroup = (RadioGroup) convertView.findViewById(C2358R.C2357id.displayTypeSelector);
        radioGroup.setOnCheckedChangeListener(null);
        if (this.mSelectedOffersTypeID != -1) {
            radioGroup.check(this.mSelectedOffersTypeID);
        }
        radioGroup.setOnCheckedChangeListener(new C23742());
        LinearLayout locationPanel = (LinearLayout) convertView.findViewById(C2358R.C2357id.location_warning_panel);
        if (this.mLocationDisabled.booleanValue()) {
            locationPanel.setVisibility(0);
            ((Button) convertView.findViewById(C2358R.C2357id.goto_settings)).setOnClickListener(new C23753());
        } else {
            locationPanel.setVisibility(8);
        }
        return convertView;
    }

    private View getOfferGridRowView(int position, View convertView) {
        OfferViewHolder[] offerViewHolders;
        int i;
        Ensighten.evaluateEvent(this, "getOfferGridRowView", new Object[]{new Integer(position), convertView});
        View offerRowView = convertView;
        if (offerRowView == null || offerRowView.getTag() == null || !(offerRowView.getTag() instanceof OfferViewHolder[])) {
            View offerGridRowLayout = new LinearLayout(this.mContext);
            offerGridRowLayout.setOrientation(0);
            offerGridRowLayout.setLayoutParams(new LayoutParams(-1, -2));
            View[] offerGridItems = new View[2];
            offerViewHolders = new OfferViewHolder[2];
            for (i = 0; i < 2; i++) {
                offerViewHolders[i] = new OfferViewHolder();
                offerGridItems[i] = this.mInflater.inflate(C2658R.layout.offer_grid_item, null);
                LinearLayout.LayoutParams gridParams = new LinearLayout.LayoutParams(UIUtils.dpAsPixels(this.mContext, 0), -1, 1.0f);
                int margin = UIUtils.dpAsPixels(this.mContext, 8);
                gridParams.setMargins(margin, margin, margin, margin);
                offerGridItems[i].setLayoutParams(gridParams);
                offerViewHolders[i].mFoodNameTextView = (TextView) offerGridItems[i].findViewById(C2358R.C2357id.name);
                offerViewHolders[i].mFoodSubtitleTextView = (TextView) offerGridItems[i].findViewById(C2358R.C2357id.subtitle);
                offerViewHolders[i].mFoodImageView = (ImageView) offerGridItems[i].findViewById(2131820643);
                offerViewHolders[i].mGridItem = (LinearLayout) offerGridItems[i].findViewById(C2358R.C2357id.grid_item);
                offerViewHolders[i].mExpirationTextView = (TextView) offerGridItems[i].findViewById(C2358R.C2357id.expiration);
                offerViewHolders[i].mGridItem.setVisibility(4);
                offerGridRowLayout.addView(offerGridItems[i]);
            }
            offerRowView = offerGridRowLayout;
            offerRowView.setTag(offerViewHolders);
        } else {
            offerViewHolders = (OfferViewHolder[]) offerRowView.getTag();
        }
        for (i = 0; i < 2; i++) {
            GridRowModel item = (GridRowModel) getItem(position);
            if (i < item.getOffers().size()) {
                final Offer offer = (Offer) item.getOffers().get(i);
                if (offer != null) {
                    offerViewHolders[i].mFoodNameTextView.setText(offer.getName());
                    offerViewHolders[i].mFoodSubtitleTextView.setText(offer.getSubtitle());
                    offerViewHolders[i].mExpirationTextView.setText(this.mContext.getString(C2658R.string.expires) + UIUtils.formatDateMonthDayYear(offer.getLocalValidThrough()));
                    offerViewHolders[i].mGridItem.setVisibility(0);
                    Glide.with(this.mContext).load(offer.getSmallImagePath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) C2358R.C2359drawable.transparent).into(offerViewHolders[i].mFoodImageView);
                    offerViewHolders[i].mGridItem.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeListAdapter", "access$100", new Object[]{HomeListAdapter.this}).onGridItemClick(offer);
                        }
                    });
                }
            }
        }
        return offerRowView;
    }

    private View getOfferListRowView(int position, View convertView) {
        HomeItemViewHolder homeItemViewHolder;
        Ensighten.evaluateEvent(this, "getOfferListRowView", new Object[]{new Integer(position), convertView});
        View view = convertView;
        if (view == null) {
            view = this.mInflater.inflate(C2658R.layout.offer_list_item, null);
            homeItemViewHolder = new HomeItemViewHolder(view);
            homeItemViewHolder.mIcon = (ImageView) view.findViewById(C2358R.C2357id.icon_imageview);
            homeItemViewHolder.mTitle = (TextView) view.findViewById(C2358R.C2357id.title_textview);
            homeItemViewHolder.mSubtitle = (TextView) view.findViewById(C2358R.C2357id.subtitle_textview);
        } else {
            homeItemViewHolder = (HomeItemViewHolder) view.getTag();
        }
        final ListRowModel item = (ListRowModel) getItem(position);
        homeItemViewHolder.mTitle.setText(item.getOffer().getName());
        homeItemViewHolder.mSubtitle.setText(this.mContext.getString(C2658R.string.expires) + UIUtils.formatDateMonthDayYear(item.getOffer().getLocalValidThrough()));
        Glide.with(this.mContext).load(item.getOffer().getSmallImagePath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) C2358R.C2359drawable.transparent).into(homeItemViewHolder.mIcon);
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeListAdapter", "access$100", new Object[]{HomeListAdapter.this}).onGridItemClick(item.getOffer());
            }
        });
        view.setTag(homeItemViewHolder);
        return view;
    }

    private View getNoOffersView() {
        NoOfferViewHolder noOfferViewHolder;
        Ensighten.evaluateEvent(this, "getNoOffersView", null);
        if (this.mNoOffersView == null || !(this.mNoOffersView.getTag() instanceof NoOfferViewHolder)) {
            this.mNoOffersView = this.mInflater.inflate(C2658R.layout.check_back_for_offers, null);
            noOfferViewHolder = new NoOfferViewHolder(this.mNoOffersView);
            noOfferViewHolder.mContainer.setVisibility(4);
            noOfferViewHolder.mProgressBar.setVisibility(0);
            this.mNoOffersView.setTag(noOfferViewHolder);
        } else {
            noOfferViewHolder = (NoOfferViewHolder) this.mNoOffersView.getTag();
        }
        Observer observer = new Observer() {
            public void update(Observable observable, Object data) {
                Ensighten.evaluateEvent(this, "update", new Object[]{observable, data});
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeListAdapter", "access$400", new Object[]{HomeListAdapter.this}).booleanValue()) {
                    noOfferViewHolder.mContainer.setVisibility(4);
                    noOfferViewHolder.mProgressBar.setVisibility(0);
                } else if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeListAdapter", "access$500", new Object[]{HomeListAdapter.this})) {
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeListAdapter", "access$600", new Object[]{HomeListAdapter.this})) {
                        noOfferViewHolder.mCheckBackPrompt.setText(UIUtils.getStringByName(Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeListAdapter", "access$300", new Object[]{HomeListAdapter.this}), (String) Configuration.getSharedInstance().getValueForKey("interface.dashboard.offersTextNoOffers")));
                    } else {
                        noOfferViewHolder.mCheckBackPrompt.setText(UIUtils.getStringByName(Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.HomeListAdapter", "access$300", new Object[]{HomeListAdapter.this}), (String) Configuration.getSharedInstance().getValueForKey("interface.dashboard.offersTextNotSubscribed")));
                    }
                    noOfferViewHolder.mContainer.setVisibility(0);
                    noOfferViewHolder.mProgressBar.setVisibility(4);
                }
            }
        };
        this.mObservable.deleteObservers();
        this.mObservable.addObserver(observer);
        return this.mNoOffersView;
    }

    private View getNotSignedInView() {
        Ensighten.evaluateEvent(this, "getNotSignedInView", null);
        View notSignedInView = this.mInflater.inflate(C2658R.layout.signed_out_offers_item, null);
        TextView registerTextView = (TextView) notSignedInView.findViewById(C2358R.C2357id.offers_register);
        TextView signInTextView = (TextView) notSignedInView.findViewById(C2358R.C2357id.offers_sign_in);
        ((TextView) notSignedInView.findViewById(C2358R.C2357id.register_prompt)).setText(UIUtils.getStringByName(this.mContext, (String) Configuration.getSharedInstance().getValueForKey("interface.dashboard.offersTextGuest")));
        registerTextView.setOnClickListener(new C23797());
        signInTextView.setOnClickListener(new C23808());
        return notSignedInView;
    }

    public void initRowList(List<Offer> offers) {
        Ensighten.evaluateEvent(this, "initRowList", new Object[]{offers});
        for (int i = 0; i < offers.size(); i += 2) {
            if (i + 2 > offers.size()) {
                this.mOfferItems.add(new GridRowModel(offers.subList(i, offers.size())));
            } else {
                this.mOfferItems.add(new GridRowModel(offers.subList(i, i + 2)));
            }
        }
    }

    public void addListOffers(List<Offer> offers) {
        Ensighten.evaluateEvent(this, "addListOffers", new Object[]{offers});
        if (offers != null) {
            for (Offer offer : offers) {
                this.mOfferItems.add(new ListRowModel(offer));
            }
        }
    }

    public void setLoggedIn(boolean loggedIn) {
        Ensighten.evaluateEvent(this, "setLoggedIn", new Object[]{new Boolean(loggedIn)});
        this.mLoggedIn = loggedIn;
        notifyDataSetChanged();
    }

    public void setHasOffers(boolean hasOffers) {
        boolean z = true;
        Ensighten.evaluateEvent(this, "setHasOffers", new Object[]{new Boolean(hasOffers)});
        if (!hasOffers || this.mOfferItems.size() <= 1) {
            z = false;
        }
        this.mHasOffers = z;
        this.mObservable.setChanged();
        this.mObservable.notifyObservers();
    }

    public void setRefreshing(Boolean refreshing) {
        Ensighten.evaluateEvent(this, "setRefreshing", new Object[]{refreshing});
        this.mRefreshing = refreshing;
        this.mObservable.setChanged();
        this.mObservable.notifyObservers();
    }
}
