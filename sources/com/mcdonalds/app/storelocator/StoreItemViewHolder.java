package com.mcdonalds.app.storelocator;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.p043ui.listview.ExpandableListItemAdapter.ViewHolder;
import com.mcdonalds.sdk.modules.storelocator.Store;

class StoreItemViewHolder implements OnClickListener, ViewHolder {
    private Button mAddFavoritesButton;
    private Button mChooseAnotherLocation;
    private ViewGroup mContentParent;
    private View mContentView;
    private View mCurrentStoreContainer;
    private TextView mCurrentStoreLabel;
    private View mExpandableContent;
    private View mFavoritesContainer;
    private boolean mHasBeenPopulated = false;
    private ImageView mInfoButton;
    private PagerItemListener mListener;
    private View mMyMcDonaldsContainer;
    private ImageView mMyMcDonaldsIcon;
    private TextView mMyMcDonaldsLabel;
    private TextView mNickName;
    private TextView mNickNameLabel;
    private View mNonHeaderContent;
    private LinearLayout mNotAuthorizedContainer;
    private TextView mNotAuthorizedLabel;
    private ImageView mOffersStatusIcon;
    private Button mOrderHere;
    private Button mRemoveFromFavorites;
    private Button mSaveToFavoritesButton;
    private StoreLocatorSection mSection;
    private View mSectionHeader;
    private TextView mSectionHeaderLabel;
    private ImageView mSectionIconView;
    private TextView mSelectStoreButton;
    private Button mSkipButton;
    private Store mStore;
    private TextView mStoreDistanceLabel;
    private TextView mStoreHoursLabel;
    private TextView mStoreSubtitleLabel;
    private TextView mStoreTitleLabel;
    private ViewGroup mTitleParent;
    private View mTitleView;

    StoreItemViewHolder() {
    }

    StoreItemViewHolder(PagerItemListener itemListener) {
        this.mListener = itemListener;
    }

    public void populateFromView(View container) {
        Ensighten.evaluateEvent(this, "populateFromView", new Object[]{container});
        if (!this.mHasBeenPopulated) {
            this.mSectionHeader = container.findViewById(C2358R.C2357id.section_header);
            this.mSectionHeaderLabel = (TextView) container.findViewById(C2358R.C2357id.section_header_label);
            this.mSectionIconView = (ImageView) container.findViewById(C2358R.C2357id.section_header_icon);
            this.mNonHeaderContent = container.findViewById(C2358R.C2357id.non_header_content);
            this.mExpandableContent = container.findViewById(C2358R.C2357id.expanded_content_view);
            this.mStoreTitleLabel = (TextView) container.findViewById(C2358R.C2357id.label_store_title);
            this.mStoreSubtitleLabel = (TextView) container.findViewById(C2358R.C2357id.label_store_subtitle);
            this.mMyMcDonaldsContainer = container.findViewById(C2358R.C2357id.container_my_mcdonalds);
            this.mMyMcDonaldsLabel = (TextView) container.findViewById(C2358R.C2357id.label_my_mcdonalds);
            this.mMyMcDonaldsIcon = (ImageView) container.findViewById(C2358R.C2357id.icon_my_mcdonalds);
            this.mSelectStoreButton = (TextView) container.findViewById(C2358R.C2357id.button_eat_here);
            this.mNotAuthorizedContainer = (LinearLayout) container.findViewById(C2358R.C2357id.container_not_participating);
            this.mNotAuthorizedLabel = (TextView) container.findViewById(C2358R.C2357id.label_not_participating);
            this.mInfoButton = (ImageView) container.findViewById(C2358R.C2357id.info_button);
            this.mStoreHoursLabel = (TextView) container.findViewById(C2358R.C2357id.label_store_hours);
            this.mStoreDistanceLabel = (TextView) container.findViewById(C2358R.C2357id.label_store_distance);
            this.mFavoritesContainer = container.findViewById(C2358R.C2357id.favorites_container);
            this.mNickNameLabel = (TextView) container.findViewById(C2358R.C2357id.nickname_label);
            this.mNickName = (TextView) container.findViewById(C2358R.C2357id.nickname_button);
            this.mSkipButton = (Button) container.findViewById(C2358R.C2357id.skip_button);
            this.mSkipButton.setVisibility(0);
            this.mAddFavoritesButton = (Button) container.findViewById(C2358R.C2357id.button_add_to_favorites);
            this.mCurrentStoreContainer = container.findViewById(C2358R.C2357id.current_store_container);
            this.mSaveToFavoritesButton = (Button) container.findViewById(C2358R.C2357id.button_save_to_favorites);
            this.mOrderHere = (Button) container.findViewById(C2358R.C2357id.button_place_order);
            this.mCurrentStoreLabel = (TextView) container.findViewById(C2358R.C2357id.label_current_store);
            this.mRemoveFromFavorites = (Button) container.findViewById(C2358R.C2357id.button_remove_from_favorites);
            this.mChooseAnotherLocation = (Button) container.findViewById(C2358R.C2357id.button_choose_another);
            this.mOffersStatusIcon = (ImageView) container.findViewById(C2358R.C2357id.icon_offers);
            configureItemListeners();
            this.mHasBeenPopulated = true;
        }
    }

    private void configureItemListeners() {
        Ensighten.evaluateEvent(this, "configureItemListeners", null);
        this.mStoreTitleLabel.setOnClickListener(this);
        this.mStoreSubtitleLabel.setOnClickListener(this);
        getSelectStoreButton().setOnClickListener(this);
        getMyMcDonaldsContainer().setOnClickListener(this);
        getInfoButton().setOnClickListener(this);
        getSaveToFavoritesButton().setOnClickListener(this);
        getAddFavoritesButton().setOnClickListener(this);
        getSkipButton().setOnClickListener(this);
        getOrderHere().setOnClickListener(this);
        getChooseAnotherLocation().setOnClickListener(this);
        getFavoritesContainer().setOnClickListener(this);
        getRemoveFromFavorites().setOnClickListener(this);
        DataLayerClickListener.setDataLayerTag(getInfoButton(), "InfoButtonPressed");
        DataLayerClickListener.setDataLayerTag(this.mStoreTitleLabel, "InfoButtonPressed");
        DataLayerClickListener.setDataLayerTag(this.mStoreSubtitleLabel, "InfoButtonPressed");
        DataLayerClickListener.setDataLayerTag(getMyMcDonaldsContainer(), "SelectAsCurrentStoreButtonPressed");
    }

    public void setListener(PagerItemListener listener) {
        Ensighten.evaluateEvent(this, "setListener", new Object[]{listener});
        this.mListener = listener;
    }

    public void onClick(View view) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
        if (this.mListener == null) {
            return;
        }
        if (view == getSelectStoreButton()) {
            this.mListener.eatHereClicked(Integer.valueOf(this.mStore.getStoreId()), this.mSection);
        } else if (view == getMyMcDonaldsContainer()) {
            this.mListener.myMcDonaldsClicked(Integer.valueOf(this.mStore.getStoreId()), this.mSection);
        } else if (view == getSaveToFavoritesButton()) {
            this.mListener.saveToFavoritesClicked(Integer.valueOf(this.mStore.getStoreId()), this.mSection);
        } else if (view == getAddFavoritesButton()) {
            this.mListener.addToFavoritesClicked(Integer.valueOf(this.mStore.getStoreId()), this.mSection);
        } else if (view == getInfoButton() || view == this.mStoreTitleLabel || view == this.mStoreSubtitleLabel) {
            this.mListener.infoClicked(Integer.valueOf(this.mStore.getStoreId()), this.mSection);
        } else if (view == getSkipButton()) {
            this.mListener.skipClicked(Integer.valueOf(this.mStore.getStoreId()), this.mSection);
        } else if (view == getOrderHere() || view == getChooseAnotherLocation()) {
            this.mListener.placeOrderClicked(Integer.valueOf(this.mStore.getStoreId()), this.mSection);
        } else if (view == getFavoritesContainer()) {
            this.mListener.nicknameClicked(Integer.valueOf(this.mStore.getStoreId()), this.mSection);
        } else if (view == getRemoveFromFavorites()) {
            this.mListener.removeFromFavoritesClicked(Integer.valueOf(this.mStore.getStoreId()), this.mSection);
        }
    }

    public ViewGroup getTitleParent() {
        Ensighten.evaluateEvent(this, "getTitleParent", null);
        return this.mTitleParent;
    }

    public void setTitleParent(ViewGroup parent) {
        Ensighten.evaluateEvent(this, "setTitleParent", new Object[]{parent});
        this.mTitleParent = parent;
    }

    public ViewGroup getContentParent() {
        Ensighten.evaluateEvent(this, "getContentParent", null);
        return this.mContentParent;
    }

    public void setContentParent(ViewGroup parent) {
        Ensighten.evaluateEvent(this, "setContentParent", new Object[]{parent});
        this.mContentParent = parent;
    }

    public View getTitleView() {
        Ensighten.evaluateEvent(this, "getTitleView", null);
        return this.mTitleView;
    }

    public void setTitleView(View titleView) {
        Ensighten.evaluateEvent(this, "setTitleView", new Object[]{titleView});
        this.mTitleView = titleView;
    }

    public View getContentView() {
        Ensighten.evaluateEvent(this, "getContentView", null);
        return this.mContentView;
    }

    public void setContentView(View contentView) {
        Ensighten.evaluateEvent(this, "setContentView", new Object[]{contentView});
        this.mContentView = contentView;
    }

    public void setSection(StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "setSection", new Object[]{section});
        this.mSection = section;
    }

    public void setStore(Store store) {
        Ensighten.evaluateEvent(this, "setStore", new Object[]{store});
        this.mStore = store;
    }

    public View getSectionHeader() {
        Ensighten.evaluateEvent(this, "getSectionHeader", null);
        return this.mSectionHeader;
    }

    public TextView getSectionHeaderLabel() {
        Ensighten.evaluateEvent(this, "getSectionHeaderLabel", null);
        return this.mSectionHeaderLabel;
    }

    public ImageView getSectionIconView() {
        Ensighten.evaluateEvent(this, "getSectionIconView", null);
        return this.mSectionIconView;
    }

    public TextView getStoreTitleLabel() {
        Ensighten.evaluateEvent(this, "getStoreTitleLabel", null);
        return this.mStoreTitleLabel;
    }

    public TextView getStoreSubtitleLabel() {
        Ensighten.evaluateEvent(this, "getStoreSubtitleLabel", null);
        return this.mStoreSubtitleLabel;
    }

    public View getMyMcDonaldsContainer() {
        Ensighten.evaluateEvent(this, "getMyMcDonaldsContainer", null);
        return this.mMyMcDonaldsContainer;
    }

    public ImageView getMyMcDonaldsIcon() {
        Ensighten.evaluateEvent(this, "getMyMcDonaldsIcon", null);
        return this.mMyMcDonaldsIcon;
    }

    public TextView getSelectStoreButton() {
        Ensighten.evaluateEvent(this, "getSelectStoreButton", null);
        return this.mSelectStoreButton;
    }

    public LinearLayout getNotAuthorizedContainer() {
        Ensighten.evaluateEvent(this, "getNotAuthorizedContainer", null);
        return this.mNotAuthorizedContainer;
    }

    public TextView getNotAuthorizedLabel() {
        Ensighten.evaluateEvent(this, "getNotAuthorizedLabel", null);
        return this.mNotAuthorizedLabel;
    }

    public ImageView getInfoButton() {
        Ensighten.evaluateEvent(this, "getInfoButton", null);
        return this.mInfoButton;
    }

    public TextView getStoreHoursLabel() {
        Ensighten.evaluateEvent(this, "getStoreHoursLabel", null);
        return this.mStoreHoursLabel;
    }

    public TextView getStoreDistanceLabel() {
        Ensighten.evaluateEvent(this, "getStoreDistanceLabel", null);
        return this.mStoreDistanceLabel;
    }

    public View getFavoritesContainer() {
        Ensighten.evaluateEvent(this, "getFavoritesContainer", null);
        return this.mFavoritesContainer;
    }

    public TextView getNickName() {
        Ensighten.evaluateEvent(this, "getNickName", null);
        return this.mNickName;
    }

    public Button getSkipButton() {
        Ensighten.evaluateEvent(this, "getSkipButton", null);
        return this.mSkipButton;
    }

    public Button getSaveToFavoritesButton() {
        Ensighten.evaluateEvent(this, "getSaveToFavoritesButton", null);
        return this.mSaveToFavoritesButton;
    }

    public View getCurrentStoreContainer() {
        Ensighten.evaluateEvent(this, "getCurrentStoreContainer", null);
        return this.mCurrentStoreContainer;
    }

    public Button getAddFavoritesButton() {
        Ensighten.evaluateEvent(this, "getAddFavoritesButton", null);
        return this.mAddFavoritesButton;
    }

    public Button getOrderHere() {
        Ensighten.evaluateEvent(this, "getOrderHere", null);
        return this.mOrderHere;
    }

    public Button getRemoveFromFavorites() {
        Ensighten.evaluateEvent(this, "getRemoveFromFavorites", null);
        return this.mRemoveFromFavorites;
    }

    public Button getChooseAnotherLocation() {
        Ensighten.evaluateEvent(this, "getChooseAnotherLocation", null);
        return this.mChooseAnotherLocation;
    }

    public ImageView getOffersStatusIcon() {
        Ensighten.evaluateEvent(this, "getOffersStatusIcon", null);
        return this.mOffersStatusIcon;
    }

    public boolean hasBeenPopulated() {
        Ensighten.evaluateEvent(this, "hasBeenPopulated", null);
        return this.mHasBeenPopulated;
    }
}
