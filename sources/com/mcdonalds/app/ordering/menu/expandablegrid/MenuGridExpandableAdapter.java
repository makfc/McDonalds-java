package com.mcdonalds.app.ordering.menu.expandablegrid;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.ordering.AdvertisableMenuUtils;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.OfferUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.CustomerOrderProduct;
import com.mcdonalds.sdk.modules.models.FavoriteItem;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.OfferProduct;
import com.mcdonalds.sdk.modules.models.OfferProductOption;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuGridExpandableAdapter extends ExpandableRecyclerAdapter<CategoryParentViewHolder, CategoryChildViewHolder> {
    private boolean filterFlag;
    private SparseArray<Category> mCategories;
    private SparseArray<CategoryExpandable> mCategoriesExpandable;
    private Context mContext;
    private int mCount;
    private int mCountExp;
    private List<String> mEvents = new ArrayList();
    private List<Integer> mFavoriteCodes = new ArrayList();
    private LayoutInflater mInflater;
    private List<Integer> mOfferCodes = new ArrayList();
    private OnMenuGridItemClickListener mOnMenuGridItemClickListener;
    private Map<CategoryExpandable, List<Product>> mRecipeMapExpandable;

    public interface OnMenuGridItemClickListener {
        void onMenuGridItemClicked(View view, Product product, String str, int i);
    }

    static /* synthetic */ void access$100(MenuGridExpandableAdapter x0, View x1, Product x2, String x3, int x4) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.expandablegrid.MenuGridExpandableAdapter", "access$100", new Object[]{x0, x1, x2, x3, new Integer(x4)});
        x0.onMenuGridItemSelected(x1, x2, x3, x4);
    }

    public MenuGridExpandableAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    public CategoryParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        return new CategoryParentViewHolder(this.mInflater.inflate(C2658R.layout.section_header_expandable, parentViewGroup, false));
    }

    public CategoryChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        return new CategoryChildViewHolder(this.mInflater.inflate(C2658R.layout.grid_item, childViewGroup, false));
    }

    public void onBindParentViewHolder(CategoryParentViewHolder parentViewHolder, int position, CategoryExpandable parentListItem) {
        Ensighten.evaluateEvent(this, "onBindParentViewHolder", new Object[]{parentViewHolder, new Integer(position), parentListItem});
        parentViewHolder.title.setText(parentListItem.getCategory().getName());
        loadImage(((Product) parentListItem.getChildItemList().get(0)).getImageUrl(), parentViewHolder.mParentImage);
        DataLayerClickListener.setDataLayerTag(parentViewHolder.itemView, "ProductSectionButtonPressed");
    }

    public void onBindChildViewHolder(final CategoryChildViewHolder childViewHolder, final int position, Object childListItem) {
        Ensighten.evaluateEvent(this, "onBindChildViewHolder", new Object[]{childViewHolder, new Integer(position), childListItem});
        final Product product = (Product) childListItem;
        AdvertisableMenuUtils.setItemTitle(this.mContext, childViewHolder.title, product);
        childViewHolder.container.setVisibility(0);
        childViewHolder.title.setVisibility(0);
        childViewHolder.image.setVisibility(0);
        loadImage(product.getImageUrl(), childViewHolder.image);
        childViewHolder.container.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                int categoryCount = 0;
                int productCount = 0;
                int catId = 0;
                for (CategoryExpandable catEx : Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.expandablegrid.MenuGridExpandableAdapter", "access$000", new Object[]{MenuGridExpandableAdapter.this}).keySet()) {
                    categoryCount++;
                    List<Product> products = (List) Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.expandablegrid.MenuGridExpandableAdapter", "access$000", new Object[]{MenuGridExpandableAdapter.this}).get(catEx);
                    if ((position - categoryCount) - productCount < products.size()) {
                        catId = catEx.getCategory().getID();
                        break;
                    }
                    productCount += products.size();
                }
                MenuGridExpandableAdapter.access$100(MenuGridExpandableAdapter.this, childViewHolder.image, product, "", catId);
            }
        });
        if (AdvertisableMenuUtils.isFavorite(product, this.mFavoriteCodes)) {
            childViewHolder.favorite.setVisibility(0);
        } else {
            childViewHolder.favorite.setVisibility(8);
        }
        if (this.mOfferCodes.contains(product.getExternalId())) {
            childViewHolder.offer.setVisibility(0);
        } else {
            childViewHolder.offer.setVisibility(8);
        }
    }

    private void onMenuGridItemSelected(View v, Product product, String categoryName, int categoryId) {
        Ensighten.evaluateEvent(this, "onMenuGridItemSelected", new Object[]{v, product, categoryName, new Integer(categoryId)});
        if (this.mOnMenuGridItemClickListener != null) {
            this.mOnMenuGridItemClickListener.onMenuGridItemClicked(v, product, categoryName, categoryId);
        }
    }

    public void setOnMenuGridItemClickListener(OnMenuGridItemClickListener onMenuGridItemClickListener) {
        Ensighten.evaluateEvent(this, "setOnMenuGridItemClickListener", new Object[]{onMenuGridItemClickListener});
        this.mOnMenuGridItemClickListener = onMenuGridItemClickListener;
    }

    public int getSpan(int position) {
        Ensighten.evaluateEvent(this, "getSpan", new Object[]{new Integer(position)});
        if (getItemViewType(position) != 1) {
            return 2;
        }
        return 1;
    }

    public void setProductMap(Map<Category, List<Product>> productMap) {
        Ensighten.evaluateEvent(this, "setProductMap", new Object[]{productMap});
        this.mEvents = new ArrayList();
        this.mCount = 0;
        this.mCategories = new SparseArray();
        if (productMap != null) {
            for (Category category : productMap.keySet()) {
                this.mEvents.add(category.getName());
                List<Product> products = (List) productMap.get(category);
                this.mCategories.put(this.mCount, category);
                this.mCount += products.size() + 1;
            }
        }
        setProductMapExpandable(productMap);
        notifyDataSetChanged();
    }

    public void setProductMapExpandable(Map<Category, List<Product>> productMap) {
        CategoryExpandable categoryExpandable;
        Ensighten.evaluateEvent(this, "setProductMapExpandable", new Object[]{productMap});
        if (this.mRecipeMapExpandable == null) {
            this.mRecipeMapExpandable = new HashMap();
        }
        List<CategoryExpandable> categoryExpandablesList = new ArrayList();
        for (Category cat : productMap.keySet()) {
            categoryExpandable = new CategoryExpandable();
            categoryExpandable.setCategory(cat);
            categoryExpandable.setChildItemList((List) productMap.get(cat));
            categoryExpandable.setExpanded(this.filterFlag);
            this.mRecipeMapExpandable.put(categoryExpandable, productMap.get(cat));
            categoryExpandablesList.add(categoryExpandable);
        }
        super.setmParentItemList(categoryExpandablesList, this.filterFlag);
        this.mEvents = new ArrayList();
        this.mCountExp = 0;
        this.mCategories = new SparseArray();
        this.mCategoriesExpandable = new SparseArray();
        if (productMap != null) {
            for (Category category : productMap.keySet()) {
                this.mEvents.add(category.getName());
                List<Product> products = (List) productMap.get(category);
                categoryExpandable = new CategoryExpandable();
                categoryExpandable.setCategory(category);
                categoryExpandable.setExpanded(this.filterFlag);
                categoryExpandable.setChildItemList(products);
                this.mCategoriesExpandable.put(this.mCountExp, categoryExpandable);
                this.mCountExp += products.size() + 1;
            }
        }
        notifyDataSetChanged();
    }

    public void dispatchEvents() {
        Ensighten.evaluateEvent(this, "dispatchEvents", null);
        for (String category : this.mEvents) {
            Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory("/order").setAction("On Scroll").setLabel(category).build());
        }
        this.mEvents.clear();
    }

    public void updateFavorites(List<FavoriteItem> favoriteItems) {
        Ensighten.evaluateEvent(this, "updateFavorites", new Object[]{favoriteItems});
        this.mFavoriteCodes.clear();
        for (FavoriteItem favoriteItem : favoriteItems) {
            for (CustomerOrderProduct favoriteProduct : favoriteItem.getProducts()) {
                this.mFavoriteCodes.add(favoriteProduct.getProductCode());
            }
        }
        notifyDataSetChanged();
    }

    public void refreshOffersFlags(List<Offer> offers) {
        Ensighten.evaluateEvent(this, "refreshOffersFlags", new Object[]{offers});
        this.mOfferCodes.clear();
        for (Offer offer : offers) {
            if (!offer.isArchived().booleanValue() && !offer.isExpired().booleanValue() && checkOrderMtd(offer) && OfferUtils.checkStore(offer)) {
                List<OfferProduct> offerProducts = offer.getProductSets();
                if (offerProducts != null) {
                    for (OfferProduct offerProduct : offerProducts) {
                        List<OfferProductOption> offerProductOptions = offerProduct.getProducts();
                        if (offerProductOptions != null) {
                            for (OfferProductOption offerProductOption : offerProductOptions) {
                                this.mOfferCodes.add(Integer.valueOf(Integer.parseInt(offerProductOption.getProductCode())));
                            }
                        }
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    private boolean checkOrderMtd(Offer offer) {
        Ensighten.evaluateEvent(this, "checkOrderMtd", new Object[]{offer});
        boolean isDeliveryOrder = OrderingManager.getInstance().getCurrentOrder().isDelivery();
        if (isDeliveryOrder && offer.isDeliveryOffer()) {
            return true;
        }
        if (isDeliveryOrder || !offer.isPickupOffer()) {
            return false;
        }
        return true;
    }

    private void loadImage(String imageUrl, ImageView imageView) {
        Ensighten.evaluateEvent(this, "loadImage", new Object[]{imageUrl, imageView});
        imageView.setImageDrawable(null);
        if (TextUtils.isEmpty(imageUrl)) {
            imageView.setBackgroundResource(C2358R.C2359drawable.icon_large_meal);
            return;
        }
        try {
            String escapedImageUrl = AppUtils.stringByAddingPercentEscapesUsingEncoding(imageUrl, "utf8");
            imageView.setBackgroundResource(0);
            Glide.with(this.mContext).load(escapedImageUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) C2358R.C2359drawable.icon_large_meal).into(imageView);
        } catch (UnsupportedEncodingException e) {
        }
    }

    public void setFilterFlag(boolean filterFlag) {
        Ensighten.evaluateEvent(this, "setFilterFlag", new Object[]{new Boolean(filterFlag)});
        this.filterFlag = filterFlag;
    }
}
