package com.mcdonalds.app.home.dashboard;

import android.content.Context;
import android.support.p001v7.widget.RecyclerView.Adapter;
import android.support.p001v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.home.HomeListItem;
import com.mcdonalds.app.home.dashboard.viewholder.AlertViewHolder;
import com.mcdonalds.app.home.dashboard.viewholder.HeaderPagerViewHolder;
import com.mcdonalds.app.home.dashboard.viewholder.HomeItemViewHolder;
import com.mcdonalds.app.home.dashboard.viewholder.NoGPSViewHolder;
import com.mcdonalds.app.home.dashboard.viewholder.NoOffersAvailableViewHolder;
import com.mcdonalds.app.home.dashboard.viewholder.NoRestaurantsNearbyViewHolder;
import com.mcdonalds.app.home.dashboard.viewholder.NoSelectedStoreViewHolder;
import com.mcdonalds.app.home.dashboard.viewholder.NotSignedInViewHolder;
import com.mcdonalds.app.home.dashboard.viewholder.NotSubscribedToOffersViewHolder;
import com.mcdonalds.app.home.dashboard.viewholder.OfferGridViewHolder;
import com.mcdonalds.app.home.dashboard.viewholder.OfferListViewHolder;
import com.mcdonalds.app.home.dashboard.viewholder.OfferLocationSelectorViewHolder;
import com.mcdonalds.app.home.dashboard.viewholder.ProgressBarViewHolder;
import com.mcdonalds.app.home.dashboard.viewholder.PunchcardOfferGridViewHolder;
import com.mcdonalds.app.home.dashboard.viewholder.PunchcardOfferListViewHolder;
import com.mcdonalds.app.home.dashboard.viewholder.RemoveFromBasketViewHolder;
import com.mcdonalds.app.home.dashboard.viewholder.SectionHeaderViewHolder;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.widget.offers.MCDListSectionHeaderModel;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DashboardAdapter extends Adapter {
    boolean containsRemoveFromBasketView = false;
    private final DashboardItem mAlertViewItem = new DashboardItem(18, "ALERT");
    private boolean mContainsOfferSelector;
    private boolean mContainsOffersSubsectionHeader;
    private boolean mContainsSectionHeader;
    private final Context mContext;
    private List<OrderOffer> mCurrentOrderOffers;
    private DashboardListener mDashboardListener;
    private final ArrayList<DashboardItem> mDataSet;
    private boolean mDeliveryOffers;
    private boolean mForceOfferTabSelection;
    private int mHomeListItemPointer = 0;
    private boolean mIsDeliveryOffers;
    private final LayoutInflater mLayoutInflater;
    private boolean mNearbyOffers = true;
    private final DashboardItem mNoGPSViewItem = new DashboardItem(16, "NO_GPS");
    private final DashboardItem mNoOffersViewItem = new DashboardItem(10, "NO_OFFERS");
    private final DashboardItem mNoRestaurantsNearbyViewItem = new DashboardItem(17, "NO_GPS");
    private final DashboardItem mNoStoreViewItem = new DashboardItem(19, "NO_STORE");
    private View mSelectorView;
    private final RequestManagerServiceConnection mServiceConnection;

    public interface DashboardListener {
        void onAlertClicked();

        void onDismissAlertClicked();

        void onGridItemClick(Offer offer);

        void onHomeListItemClicked(HomeListItem homeListItem);

        void onListItemClick(Offer offer);

        void onOffersTypeSelectorChanged(RadioGroup radioGroup);

        void onRegisterClick();

        void onRemoveFromBasketClicked(OrderOffer orderOffer);

        void onSelectStoreClicked();

        void onSignInClick();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewType {
    }

    public DashboardAdapter(Context context, RequestManagerServiceConnection serviceConnection, boolean nearbyOffers) {
        this.mContext = context;
        this.mServiceConnection = serviceConnection;
        this.mNearbyOffers = nearbyOffers;
        this.mDataSet = new ArrayList();
        this.mLayoutInflater = LayoutInflater.from(this.mContext);
    }

    public void setLoginState(boolean isLoggedIn) {
        Ensighten.evaluateEvent(this, "setLoginState", new Object[]{new Boolean(isLoggedIn)});
        if (isLoggedIn) {
            DashboardItem dashboardItemToRemove = null;
            int indexOfRemovedItem = 0;
            Iterator it = this.mDataSet.iterator();
            while (it.hasNext()) {
                DashboardItem dashboardItem = (DashboardItem) it.next();
                if (dashboardItem.getViewType() == 9) {
                    dashboardItemToRemove = dashboardItem;
                    indexOfRemovedItem = this.mDataSet.indexOf(dashboardItem);
                }
            }
            if (dashboardItemToRemove != null) {
                this.mDataSet.remove(dashboardItemToRemove);
                notifyItemRemoved(indexOfRemovedItem);
                return;
            }
            return;
        }
        DashboardItem<Boolean> dashboardItem2 = new DashboardItem(9, Boolean.valueOf(false));
        if (!this.mDataSet.contains(dashboardItem2)) {
            this.mDataSet.add(dashboardItem2);
            notifyItemInserted(this.mDataSet.indexOf(dashboardItem2));
        }
    }

    public void setCurrentStoreSubtitle(String currentStoreName) {
        Ensighten.evaluateEvent(this, "setCurrentStoreSubtitle", new Object[]{currentStoreName});
        Iterator it = this.mDataSet.iterator();
        while (it.hasNext()) {
            DashboardItem dashboardItem = (DashboardItem) it.next();
            if ((dashboardItem.getObject() instanceof HomeListItem) && ((HomeListItem) dashboardItem.getObject()).getTitle().equals(this.mContext.getResources().getString(C2658R.string.home_link_current_location))) {
                ((HomeListItem) dashboardItem.getObject()).setSubTitle(currentStoreName);
                notifyItemChanged(this.mDataSet.indexOf(dashboardItem));
            }
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        boolean z = false;
        Ensighten.evaluateEvent(this, "onCreateViewHolder", new Object[]{viewGroup, new Integer(viewType)});
        switch (viewType) {
            case 0:
                return new HeaderPagerViewHolder((View) ((DashboardItem) this.mDataSet.get(0)).getObject());
            case 2:
                return HomeItemViewHolder.create(this.mLayoutInflater, this.mDashboardListener, this.mDataSet);
            case 3:
                return SectionHeaderViewHolder.create(this.mLayoutInflater);
            case 4:
                return OfferGridViewHolder.create(this.mLayoutInflater, this.mDataSet, this.mDashboardListener);
            case 7:
                return OfferListViewHolder.create(this.mLayoutInflater, this.mDataSet, this.mDashboardListener);
            case 8:
                return OfferLocationSelectorViewHolder.create(this.mSelectorView, this.mDashboardListener);
            case 9:
                return NotSignedInViewHolder.create(this.mLayoutInflater, this.mDashboardListener);
            case 10:
                return NoOffersAvailableViewHolder.create(this.mLayoutInflater);
            case 11:
                return new ProgressBarViewHolder((View) ((DashboardItem) this.mDataSet.get(this.mDataSet.size() - 1)).getObject());
            case 12:
                return NotSubscribedToOffersViewHolder.create(this.mLayoutInflater);
            case 13:
                return PunchcardOfferGridViewHolder.create(this.mLayoutInflater, this.mDataSet, this.mDashboardListener);
            case 14:
                return PunchcardOfferListViewHolder.create(this.mLayoutInflater, this.mDataSet, this.mDashboardListener);
            case 15:
                return RemoveFromBasketViewHolder.create(this.mLayoutInflater, this.mDashboardListener);
            case 16:
                return NoGPSViewHolder.create(this.mLayoutInflater);
            case 17:
                return NoRestaurantsNearbyViewHolder.create(this.mLayoutInflater);
            case 18:
                CustomerProfile mProfile = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentProfile();
                Boolean phoneVerification = Boolean.valueOf(false);
                mProfile.getLoginPreference();
                if (mProfile != null) {
                    if (mProfile.isUsingSocialLoginWithoutEmail() || mProfile.getLoginPreference() == 2) {
                        z = true;
                    }
                    phoneVerification = Boolean.valueOf(z);
                }
                return AlertViewHolder.create(this.mLayoutInflater, this.mDashboardListener, phoneVerification);
            case 19:
                return NoSelectedStoreViewHolder.create(this.mLayoutInflater, this.mDashboardListener);
            default:
                return null;
        }
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Ensighten.evaluateEvent(this, "onBindViewHolder", new Object[]{viewHolder, new Integer(position)});
        switch (getItemViewType(position)) {
            case 2:
                HomeItemViewHolder.bind(this.mContext, (HomeItemViewHolder) viewHolder, (HomeListItem) ((DashboardItem) this.mDataSet.get(position)).getObject());
                return;
            case 3:
                SectionHeaderViewHolder.bind(this.mContext, (SectionHeaderViewHolder) viewHolder, (MCDListSectionHeaderModel) ((DashboardItem) this.mDataSet.get(position)).getObject());
                return;
            case 4:
                Offer offer = (Offer) ((DashboardItem) this.mDataSet.get(position)).getObject();
                OfferGridViewHolder.bind((OfferGridViewHolder) viewHolder, offer, isOfferAppliedToOrder(offer), this.mContext);
                return;
            case 7:
                Offer listItemOffer = (Offer) ((DashboardItem) this.mDataSet.get(position)).getObject();
                OfferListViewHolder.bind((OfferListViewHolder) viewHolder, listItemOffer, isOfferAppliedToOrder(listItemOffer), this.mContext);
                DataLayerClickListener.setDataLayerTag(viewHolder.itemView, "OtherOfferItemPressedPressed", position);
                return;
            case 8:
                if (this.mForceOfferTabSelection) {
                    OfferLocationSelectorViewHolder.forceTabSelection((OfferLocationSelectorViewHolder) viewHolder, this.mNearbyOffers, this.mDeliveryOffers);
                    this.mForceOfferTabSelection = false;
                    return;
                }
                return;
            case 9:
                NotSignedInViewHolder.bind(this.mContext, (NotSignedInViewHolder) viewHolder);
                return;
            case 10:
                NoOffersAvailableViewHolder.bind(this.mContext, (NoOffersAvailableViewHolder) viewHolder);
                return;
            case 12:
                NotSubscribedToOffersViewHolder.bind(this.mContext, (NotSubscribedToOffersViewHolder) viewHolder);
                return;
            case 13:
                Offer punchcardGridOffer = (Offer) ((DashboardItem) this.mDataSet.get(position)).getObject();
                PunchcardOfferGridViewHolder.bind(this.mContext, (PunchcardOfferGridViewHolder) viewHolder, punchcardGridOffer, isOfferAppliedToOrder(punchcardGridOffer));
                return;
            case 14:
                Offer punchcardListOffer = (Offer) ((DashboardItem) this.mDataSet.get(position)).getObject();
                PunchcardOfferListViewHolder.bind(this.mContext, (PunchcardOfferListViewHolder) viewHolder, punchcardListOffer, isOfferAppliedToOrder(punchcardListOffer));
                DataLayerClickListener.setDataLayerTag(viewHolder.itemView, "OtherOfferItemPressedPressed", position);
                return;
            case 15:
                RemoveFromBasketViewHolder.bind((RemoveFromBasketViewHolder) viewHolder, (OrderOffer) ((DashboardItem) this.mDataSet.get(position)).getObject());
                return;
            case 19:
                NoSelectedStoreViewHolder.bind((NoSelectedStoreViewHolder) viewHolder, this.mIsDeliveryOffers);
                return;
            default:
                return;
        }
    }

    public int getItemViewType(int position) {
        Ensighten.evaluateEvent(this, "getItemViewType", new Object[]{new Integer(position)});
        return ((DashboardItem) this.mDataSet.get(position)).getViewType();
    }

    public int getItemCount() {
        Ensighten.evaluateEvent(this, "getItemCount", null);
        return this.mDataSet.size();
    }

    public void addNotSubscribedToOffersView() {
        Ensighten.evaluateEvent(this, "addNotSubscribedToOffersView", null);
        this.mDataSet.add(new DashboardItem(12, "Not subscribed"));
        notifyItemInserted(this.mDataSet.size() - 1);
    }

    public void clear() {
        Ensighten.evaluateEvent(this, "clear", null);
        this.mContainsSectionHeader = false;
        this.mContainsOffersSubsectionHeader = false;
        this.mContainsOfferSelector = false;
        this.containsRemoveFromBasketView = false;
        this.mDataSet.clear();
        notifyDataSetChanged();
    }

    public void addNoOffersView() {
        Ensighten.evaluateEvent(this, "addNoOffersView", null);
        removeNoOffersView();
        this.mDataSet.add(this.mNoOffersViewItem);
        notifyItemInserted(this.mDataSet.size() - 1);
    }

    public void removeNoOffersView() {
        Ensighten.evaluateEvent(this, "removeNoOffersView", null);
        if (this.mDataSet.contains(this.mNoOffersViewItem)) {
            int index = this.mDataSet.indexOf(this.mNoOffersViewItem);
            this.mDataSet.remove(index);
            notifyItemRemoved(index);
        }
    }

    public void addNoSelectedStoreView(boolean isDeliveryOffers) {
        Ensighten.evaluateEvent(this, "addNoSelectedStoreView", new Object[]{new Boolean(isDeliveryOffers)});
        this.mIsDeliveryOffers = isDeliveryOffers;
        removeNoSelectedStoreView();
        this.mDataSet.add(this.mNoStoreViewItem);
        notifyItemInserted(this.mDataSet.size() - 1);
    }

    public void removeNoSelectedStoreView() {
        Ensighten.evaluateEvent(this, "removeNoSelectedStoreView", null);
        if (this.mDataSet.contains(this.mNoStoreViewItem)) {
            int index = this.mDataSet.indexOf(this.mNoStoreViewItem);
            this.mDataSet.remove(index);
            notifyItemRemoved(index);
        }
    }

    public void addNoGPSView() {
        Ensighten.evaluateEvent(this, "addNoGPSView", null);
        if (this.mDataSet.contains(this.mNoGPSViewItem)) {
            int index = this.mDataSet.indexOf(this.mNoGPSViewItem);
            this.mDataSet.remove(index);
            notifyItemRemoved(index);
        }
        this.mDataSet.add(this.mNoGPSViewItem);
        notifyItemInserted(this.mDataSet.size() - 1);
    }

    public void addNoRestaurantsNearbyView() {
        Ensighten.evaluateEvent(this, "addNoRestaurantsNearbyView", null);
        if (this.mDataSet.contains(this.mNoRestaurantsNearbyViewItem)) {
            int index = this.mDataSet.indexOf(this.mNoRestaurantsNearbyViewItem);
            this.mDataSet.remove(index);
            notifyItemRemoved(index);
        }
        this.mDataSet.add(this.mNoRestaurantsNearbyViewItem);
        notifyItemInserted(this.mDataSet.size() - 1);
    }

    public void addOfferSelectorView() {
        Ensighten.evaluateEvent(this, "addOfferSelectorView", null);
        if (!this.mContainsOfferSelector) {
            this.mSelectorView = this.mLayoutInflater.inflate(C2658R.layout.offers_location_selector, null);
            if (!this.mNearbyOffers) {
                if (Configuration.getSharedInstance().getBooleanForKey("interface.dashboard.showDeliveryOnlyTab")) {
                    RadioButton r;
                    if (this.mDeliveryOffers) {
                        r = (RadioButton) this.mSelectorView.findViewById(C2358R.C2357id.offers_type_delivery);
                    } else {
                        r = (RadioButton) this.mSelectorView.findViewById(C2358R.C2357id.offers_type_current);
                    }
                    r.setChecked(true);
                } else {
                    ((RadioButton) this.mSelectorView.findViewById(C2358R.C2357id.offers_type_current)).setChecked(true);
                }
            }
            if (!Configuration.getSharedInstance().getBooleanForKey("interface.dashboard.showDeliveryOnlyTab")) {
                this.mSelectorView.findViewById(C2358R.C2357id.offers_type_delivery_spacer).setVisibility(8);
                this.mSelectorView.findViewById(C2358R.C2357id.offers_type_delivery).setVisibility(8);
            }
            if (Configuration.getSharedInstance().getBooleanForKey("interface.dashboard.hidenearyouofferstab")) {
                this.mSelectorView.findViewById(C2358R.C2357id.offers_type_near_you_spacer).setVisibility(8);
                this.mSelectorView.findViewById(C2358R.C2357id.offers_type_near_you).setVisibility(8);
            }
            if (Configuration.getSharedInstance().getBooleanForKey("interface.dashboard.showPickUpTab")) {
                this.mSelectorView.findViewById(C2358R.C2357id.offers_type_delivery_spacer).setVisibility(8);
                this.mSelectorView.findViewById(C2358R.C2357id.offers_type_current).setVisibility(8);
                this.mSelectorView.findViewById(C2358R.C2357id.offers_type_pickup_spacer).setVisibility(0);
                this.mSelectorView.findViewById(C2358R.C2357id.offers_type_pickup).setVisibility(0);
            }
            String tabName = Configuration.getSharedInstance().getStringForKey("interface.dashboard.offersTextCurrentStore");
            if (tabName != null) {
                ((RadioButton) this.mSelectorView.findViewById(C2358R.C2357id.offers_type_current)).setText(UIUtils.getStringByName(this.mContext, tabName));
            }
            DashboardItem<View> dashboardItem = new DashboardItem(8, this.mSelectorView);
            int index = 0;
            if (!this.mDataSet.contains(dashboardItem)) {
                int i;
                for (i = 0; i < this.mDataSet.size(); i++) {
                    if (((DashboardItem) this.mDataSet.get(i)).getViewType() == 3) {
                        index = i;
                    }
                }
                for (i = 0; i < this.mDataSet.size(); i++) {
                    if (((DashboardItem) this.mDataSet.get(i)).getViewType() == 15) {
                        index = i;
                    }
                }
            }
            index++;
            this.mDataSet.add(index, dashboardItem);
            this.mContainsOfferSelector = true;
            notifyItemInserted(index);
        }
    }

    public void addOffer(Offer offer, boolean isGridItem) {
        Ensighten.evaluateEvent(this, "addOffer", new Object[]{offer, new Boolean(isGridItem)});
        DashboardItem<Offer> dashboardItem = new DashboardItem(isGridItem ? 4 : 7, offer);
        if (offer.isPunchCard() || offer.isFullPunchCard()) {
            dashboardItem.setViewType(isGridItem ? 13 : 14);
        }
        int index = findIndexForNewOffer(isGridItem);
        this.mDataSet.add(index, dashboardItem);
        notifyItemInserted(index);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003e  */
    private int findIndexForNewOffer(boolean r8) {
        /*
        r7 = this;
        r3 = "findIndexForNewOffer";
        r4 = 1;
        r4 = new java.lang.Object[r4];
        r5 = 0;
        r6 = new java.lang.Boolean;
        r6.<init>(r8);
        r4[r5] = r6;
        com.ensighten.Ensighten.evaluateEvent(r7, r3, r4);
        r3 = r7.mDataSet;
        r3 = r3.size();
        r1 = r3 + -1;
        r3 = r7.mDataSet;
        r3 = r3.size();
        r0 = r3 + -1;
    L_0x0020:
        if (r0 < 0) goto L_0x0033;
    L_0x0022:
        r3 = r7.mDataSet;
        r2 = r3.get(r0);
        r2 = (com.mcdonalds.app.home.dashboard.DashboardItem) r2;
        if (r8 == 0) goto L_0x0036;
    L_0x002c:
        r3 = r7.isOffersGridItem(r2);
        if (r3 == 0) goto L_0x003c;
    L_0x0032:
        r1 = r0;
    L_0x0033:
        r1 = r1 + 1;
        return r1;
    L_0x0036:
        r3 = r7.isOffersListItem(r2);
        if (r3 != 0) goto L_0x0032;
    L_0x003c:
        if (r8 == 0) goto L_0x0048;
    L_0x003e:
        r3 = r2.getViewType();
        r4 = 8;
        if (r3 != r4) goto L_0x0059;
    L_0x0046:
        r1 = r0;
        goto L_0x0033;
    L_0x0048:
        r3 = r2.getViewType();
        r4 = 3;
        if (r3 != r4) goto L_0x0059;
    L_0x004f:
        r3 = r2.getObject();
        r3 = r3 instanceof com.mcdonalds.app.widget.offers.MCDListSectionHeaderModel;
        if (r3 == 0) goto L_0x0059;
    L_0x0057:
        r1 = r0;
        goto L_0x0033;
    L_0x0059:
        r0 = r0 + -1;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mcdonalds.app.home.dashboard.DashboardAdapter.findIndexForNewOffer(boolean):int");
    }

    private boolean isOffersGridItem(DashboardItem item) {
        Ensighten.evaluateEvent(this, "isOffersGridItem", new Object[]{item});
        if (item.getViewType() == 4 || item.getViewType() == 13) {
            return true;
        }
        return false;
    }

    private boolean isOffersListItem(DashboardItem item) {
        Ensighten.evaluateEvent(this, "isOffersListItem", new Object[]{item});
        if (item.getViewType() == 7 || item.getViewType() == 14) {
            return true;
        }
        return false;
    }

    public void removeAllOffers(boolean removeHeader) {
        Ensighten.evaluateEvent(this, "removeAllOffers", new Object[]{new Boolean(removeHeader)});
        int offersStartIndex = -1;
        ArrayList<DashboardItem> dashboardItemsToRemove = new ArrayList();
        DashboardItem selectorToRemove = null;
        DashboardItem headerToRemove = null;
        DashboardItem subsectionHeaderToRemove = null;
        int i = 0;
        while (i < this.mDataSet.size()) {
            if (((DashboardItem) this.mDataSet.get(i)).getObject() instanceof Offer) {
                if (offersStartIndex == -1) {
                    offersStartIndex = i;
                }
                dashboardItemsToRemove.add(this.mDataSet.get(i));
            } else if (((DashboardItem) this.mDataSet.get(i)).getObject() instanceof MCDListSectionHeaderModel) {
                if (!this.mContext.getResources().getString(C2658R.string.title_your_offers).equals(((MCDListSectionHeaderModel) ((DashboardItem) this.mDataSet.get(i)).getObject()).getTitle())) {
                    subsectionHeaderToRemove = (DashboardItem) this.mDataSet.get(i);
                    this.mContainsOffersSubsectionHeader = false;
                } else if (removeHeader) {
                    headerToRemove = (DashboardItem) this.mDataSet.get(i);
                    this.mContainsSectionHeader = false;
                }
            } else if (removeHeader && (((DashboardItem) this.mDataSet.get(i)).getObject() instanceof View) && ((DashboardItem) this.mDataSet.get(i)).getViewType() == 8) {
                selectorToRemove = (DashboardItem) this.mDataSet.get(i);
                this.mContainsOfferSelector = false;
            } else if (removeHeader && (((DashboardItem) this.mDataSet.get(i)).getObject() instanceof View) && ((DashboardItem) this.mDataSet.get(i)).getViewType() == 15) {
                removeRemoveFromBasketView();
            }
            i++;
        }
        this.mDataSet.removeAll(dashboardItemsToRemove);
        this.mDataSet.remove(subsectionHeaderToRemove);
        if (removeHeader) {
            if (headerToRemove != null) {
                this.mDataSet.remove(headerToRemove);
            }
            if (selectorToRemove != null) {
                this.mDataSet.remove(selectorToRemove);
            }
            if (subsectionHeaderToRemove != null) {
                this.mDataSet.remove(subsectionHeaderToRemove);
            }
        }
        notifyDataSetChanged();
    }

    public void addRemoveFromBasketView(OrderOffer orderOffer) {
        Ensighten.evaluateEvent(this, "addRemoveFromBasketView", new Object[]{orderOffer});
        if (!this.containsRemoveFromBasketView) {
            DashboardItem<OrderOffer> dashboardItem = new DashboardItem(15, orderOffer);
            int index = 0;
            if (!this.mDataSet.contains(dashboardItem)) {
                int i = 0;
                while (i < this.mDataSet.size()) {
                    if (((DashboardItem) this.mDataSet.get(i)).getViewType() == 3 && this.mContext.getResources().getString(C2658R.string.title_your_offers).equals(((MCDListSectionHeaderModel) ((DashboardItem) this.mDataSet.get(i)).getObject()).getTitle())) {
                        index = i;
                    }
                    i++;
                }
            }
            index++;
            this.mDataSet.add(index, dashboardItem);
            this.containsRemoveFromBasketView = true;
            notifyItemInserted(index);
        }
    }

    public void removeRemoveFromBasketView() {
        Ensighten.evaluateEvent(this, "removeRemoveFromBasketView", null);
        int index = -1;
        if (this.containsRemoveFromBasketView) {
            for (int i = 0; i < this.mDataSet.size(); i++) {
                if (((DashboardItem) this.mDataSet.get(i)).getViewType() == 15) {
                    index = i;
                }
            }
            if (index != -1) {
                this.mDataSet.remove(index);
                this.containsRemoveFromBasketView = false;
                notifyItemRemoved(index);
            }
        }
    }

    public void removeAlertView() {
        Ensighten.evaluateEvent(this, "removeAlertView", null);
        if (((DashboardItem) this.mDataSet.get(1)).getViewType() == 18) {
            this.mDataSet.remove(1);
            notifyItemRemoved(1);
        }
    }

    public void addHeaderView(View headerView) {
        Ensighten.evaluateEvent(this, "addHeaderView", new Object[]{headerView});
        this.mDataSet.add(0, new DashboardItem(0, headerView));
        notifyItemInserted(0);
    }

    public void addAlertItem(boolean verified) {
        Ensighten.evaluateEvent(this, "addAlertItem", new Object[]{new Boolean(verified)});
        if (Configuration.getSharedInstance().getBooleanForKey("interface.register.chooseEmailOrPhoneAsUsername") && !this.mContext.getSharedPreferences("config", 0).getBoolean("email_verification_alert", false) && !verified) {
            if (this.mDataSet.contains(this.mAlertViewItem)) {
                int index = this.mDataSet.indexOf(this.mAlertViewItem);
                this.mDataSet.remove(index);
                notifyItemRemoved(index);
            }
            this.mDataSet.add(1, this.mAlertViewItem);
            notifyItemInserted(1);
        }
    }

    public void addHomeListItem(HomeListItem homeListItem) {
        Ensighten.evaluateEvent(this, "addHomeListItem", new Object[]{homeListItem});
        DashboardItem<HomeListItem> dashboardItem = new DashboardItem(2, homeListItem);
        if (this.mDataSet.size() == 1 && ((DashboardItem) this.mDataSet.get(0)).getViewType() == 0) {
            this.mHomeListItemPointer = 1;
        }
        this.mDataSet.add(this.mHomeListItemPointer, dashboardItem);
        notifyItemInserted(this.mHomeListItemPointer);
        this.mHomeListItemPointer++;
    }

    public void addSectionHeader(MCDListSectionHeaderModel mcdListSectionHeaderModel, boolean selector) {
        Ensighten.evaluateEvent(this, "addSectionHeader", new Object[]{mcdListSectionHeaderModel, new Boolean(selector)});
        DashboardItem<MCDListSectionHeaderModel> dashboardItem = new DashboardItem(3, mcdListSectionHeaderModel);
        if (!this.mContainsSectionHeader) {
            this.mContainsSectionHeader = true;
            this.mDataSet.add(dashboardItem);
            notifyItemInserted(this.mDataSet.indexOf(dashboardItem));
            if (selector) {
                addOfferSelectorView();
            }
        }
    }

    public void addOffersSubsectionHeader(MCDListSectionHeaderModel mcdListSectionHeaderModel) {
        Ensighten.evaluateEvent(this, "addOffersSubsectionHeader", new Object[]{mcdListSectionHeaderModel});
        DashboardItem<MCDListSectionHeaderModel> dashboardItem = new DashboardItem(3, mcdListSectionHeaderModel);
        if (this.mContainsOffersSubsectionHeader) {
            for (int i = this.mDataSet.size() - 1; i >= 0; i--) {
                if (((DashboardItem) this.mDataSet.get(i)).getViewType() == 3) {
                    this.mDataSet.remove(i);
                    this.mDataSet.add(i, dashboardItem);
                    notifyItemChanged(i);
                    return;
                }
            }
            return;
        }
        this.mDataSet.add(dashboardItem);
        this.mContainsOffersSubsectionHeader = true;
        notifyItemInserted(this.mDataSet.indexOf(dashboardItem));
    }

    public void setDashboardListener(DashboardListener dashboardListener) {
        Ensighten.evaluateEvent(this, "setDashboardListener", new Object[]{dashboardListener});
        this.mDashboardListener = dashboardListener;
    }

    public void showProgressSpinner(boolean shouldShow) {
        Ensighten.evaluateEvent(this, "showProgressSpinner", new Object[]{new Boolean(shouldShow)});
        DashboardItem<View> progressBarDashboardItem = new DashboardItem(11, this.mLayoutInflater.inflate(C2658R.layout.progressbar, null));
        boolean progressBarDisplayed = false;
        int index = 0;
        int notSubscribedItemIndex = 0;
        int noOffersItemIndex = 0;
        int noGpsItemIndex = 0;
        Iterator it = this.mDataSet.iterator();
        while (it.hasNext()) {
            DashboardItem item = (DashboardItem) it.next();
            if (item.getViewType() == 11) {
                index = this.mDataSet.indexOf(item);
                if (shouldShow) {
                    progressBarDisplayed = true;
                } else {
                    this.mDataSet.remove(index);
                    notifyItemRemoved(index);
                    return;
                }
            } else if (item.getObject().equals("Not subscribed")) {
                notSubscribedItemIndex = this.mDataSet.indexOf(item);
            } else if (item.getObject().equals("NO_OFFERS")) {
                noOffersItemIndex = this.mDataSet.indexOf(item);
            } else if (item.getObject().equals("NO_GPS")) {
                noGpsItemIndex = this.mDataSet.indexOf(item);
            }
        }
        if (!progressBarDisplayed && shouldShow) {
            this.mDataSet.add(progressBarDashboardItem);
            if (notSubscribedItemIndex != 0) {
                this.mDataSet.remove(notSubscribedItemIndex);
                notifyItemRemoved(notSubscribedItemIndex);
            }
            if (noOffersItemIndex != 0) {
                this.mDataSet.remove(noOffersItemIndex);
                notifyItemRemoved(noOffersItemIndex);
            }
            if (noGpsItemIndex != 0) {
                this.mDataSet.remove(noGpsItemIndex);
                notifyItemRemoved(noGpsItemIndex);
            }
            notifyItemChanged(this.mDataSet.size());
        } else if (notSubscribedItemIndex != 0) {
            this.mDataSet.remove(index);
            notifyItemRemoved(index);
        }
    }

    public void setCurrentOrderOffers(List<OrderOffer> currentOrderOffers) {
        Ensighten.evaluateEvent(this, "setCurrentOrderOffers", new Object[]{currentOrderOffers});
        this.mCurrentOrderOffers = currentOrderOffers;
    }

    private boolean isOfferAppliedToOrder(Offer offer) {
        Ensighten.evaluateEvent(this, "isOfferAppliedToOrder", new Object[]{offer});
        boolean isApplied = false;
        if (this.mCurrentOrderOffers != null) {
            for (OrderOffer currentOrderOffer : this.mCurrentOrderOffers) {
                if (currentOrderOffer.getOffer().getOfferId().equals(offer.getOfferId())) {
                    isApplied = true;
                }
            }
        }
        return isApplied;
    }

    public void removeAppliedOfferBadge(OrderOffer orderOffer) {
        Ensighten.evaluateEvent(this, "removeAppliedOfferBadge", new Object[]{orderOffer});
        DashboardItem<Offer> dashboardItemToUpdate = null;
        int dashboardItemToUpdateIndex = -1;
        for (int i = 0; i < this.mDataSet.size(); i++) {
            DashboardItem<Offer> dashboardItem = (DashboardItem) this.mDataSet.get(i);
            if ((dashboardItem.getObject() instanceof Offer) && ((Offer) dashboardItem.getObject()).getOfferId().equals(orderOffer.getOffer().getOfferId())) {
                dashboardItemToUpdate = dashboardItem;
                dashboardItemToUpdateIndex = i;
            }
        }
        int indexToRemove = -1;
        if (dashboardItemToUpdate != null) {
            for (OrderOffer currentOrderOffer : this.mCurrentOrderOffers) {
                if (currentOrderOffer.getOffer().getOfferId().equals(((Offer) dashboardItemToUpdate.getObject()).getOfferId())) {
                    indexToRemove = this.mCurrentOrderOffers.indexOf(currentOrderOffer);
                }
            }
            if (indexToRemove != -1) {
                this.mCurrentOrderOffers.remove(indexToRemove);
                notifyItemChanged(dashboardItemToUpdateIndex);
            }
        }
    }

    public void setOfferTabSelection(boolean deliveryOffers) {
        Ensighten.evaluateEvent(this, "setOfferTabSelection", new Object[]{new Boolean(deliveryOffers)});
        this.mDeliveryOffers = deliveryOffers;
        this.mForceOfferTabSelection = true;
        notifyDataSetChanged();
    }
}
