package com.mcdonalds.app.ordering.menu;

import android.content.Context;
import android.support.p001v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MenuGridAdapter extends Adapter<ViewHolder> implements OnClickListener {
    private SparseArray<Category> mCategories;
    private Context mContext;
    private int mCount;
    private List<String> mEvents = new ArrayList();
    private List<Integer> mFavoriteCodes = new ArrayList();
    private List<Integer> mOfferCodes = new ArrayList();
    private OnMenuGridItemClickListener mOnMenuGridItemClickListener;
    private List<Integer> mOutageProductCodes = new ArrayList();
    private Map<Category, List<Product>> mRecipeMap;

    public interface OnMenuGridItemClickListener {
        void onMenuGridItemClicked(View view, Product product, String str, int i);
    }

    public class ViewHolder extends android.support.p001v7.widget.RecyclerView.ViewHolder {
        public RelativeLayout container;
        public ImageView favorite;
        public ImageView image;
        public LinearLayout itemNotAvailable;
        public TextView itemNotAvailableText;
        public ImageView offer;
        public TextView title;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == 1) {
                this.container = (RelativeLayout) itemView.findViewById(C2358R.C2357id.grid_item);
                this.title = (TextView) itemView.findViewById(C2358R.C2357id.name);
                this.image = (ImageView) itemView.findViewById(2131820643);
                this.offer = (ImageView) itemView.findViewById(C2358R.C2357id.offer_icon);
                this.favorite = (ImageView) itemView.findViewById(C2358R.C2357id.favorite_icon);
                this.itemNotAvailable = (LinearLayout) itemView.findViewById(C2358R.C2357id.item_not_available);
                this.itemNotAvailableText = (TextView) itemView.findViewById(C2358R.C2357id.item_not_available_text);
            } else if (viewType == 0) {
                this.title = (TextView) itemView.findViewById(C2358R.C2357id.section_name);
            }
        }
    }

    static /* synthetic */ void access$000(MenuGridAdapter x0, View x1, Product x2, String x3, int x4) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuGridAdapter", "access$000", new Object[]{x0, x1, x2, x3, new Integer(x4)});
        x0.onMenuGridItemSelected(x1, x2, x3, x4);
    }

    public MenuGridAdapter(Context context) {
        this.mContext = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == 0) {
            v = LayoutInflater.from(parent.getContext()).inflate(C2658R.layout.section_header, parent, false);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(C2658R.layout.grid_item, parent, false);
        }
        return new ViewHolder(v, viewType);
    }

    public void onBindViewHolder(final ViewHolder holder, int position) {
        Ensighten.evaluateEvent(this, "onBindViewHolder", new Object[]{holder, new Integer(position)});
        int type = getItemViewType(position);
        if (type == 0) {
            holder.title.setText(getCategoryForPosition(position).getName());
        } else if (type == 1) {
            int categoryCount = 0;
            for (final Category category : this.mRecipeMap.keySet()) {
                categoryCount++;
                List<Product> products = (List) this.mRecipeMap.get(category);
                int itemPosition = position - categoryCount;
                if (itemPosition < products.size()) {
                    final Product product = (Product) products.get(itemPosition);
                    AdvertisableMenuUtils.setItemTitle(this.mContext, holder.title, product);
                    holder.container.setVisibility(0);
                    holder.title.setVisibility(0);
                    holder.image.setVisibility(0);
                    loadImage(product.getImageUrl(), holder.image);
                    holder.container.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                            MenuGridAdapter.access$000(MenuGridAdapter.this, holder.image, product, category.getName(), category.getID());
                        }
                    });
                    if (AdvertisableMenuUtils.isFavorite(product, this.mFavoriteCodes)) {
                        holder.favorite.setVisibility(0);
                    } else {
                        holder.favorite.setVisibility(8);
                    }
                    if (this.mOfferCodes.contains(product.getExternalId())) {
                        holder.offer.setVisibility(0);
                    } else {
                        holder.offer.setVisibility(8);
                    }
                    if (this.mOutageProductCodes.contains(product.getExternalId())) {
                        holder.container.setAlpha(0.5f);
                        holder.container.setClickable(false);
                        holder.itemNotAvailable.setVisibility(0);
                        String lang = Configuration.getSharedInstance().getCurrentLanguage();
                        if (lang.equals("zh")) {
                            holder.itemNotAvailableText.setTextSize(20.0f);
                            return;
                        } else if (lang.equals("en")) {
                            holder.itemNotAvailableText.setTextSize(10.0f);
                            return;
                        } else {
                            return;
                        }
                    }
                    holder.container.setAlpha(1.0f);
                    holder.container.setClickable(true);
                    holder.itemNotAvailable.setVisibility(8);
                    return;
                }
                position -= products.size();
            }
        }
        DataLayerClickListener.setDataLayerTag(holder.container, ViewHolder.class, position);
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

    public int getItemCount() {
        Ensighten.evaluateEvent(this, "getItemCount", null);
        return this.mCount;
    }

    public int getItemViewType(int position) {
        Ensighten.evaluateEvent(this, "getItemViewType", new Object[]{new Integer(position)});
        if (this.mCategories.get(position) != null) {
            return 0;
        }
        return 1;
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
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
        if (Configuration.getSharedInstance().getBooleanForKey("interface.hideOutagedItemsInMenu")) {
            filterOutageProducts(productMap, this.mOutageProductCodes);
        }
        this.mRecipeMap = productMap;
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
        notifyDataSetChanged();
    }

    public int getPositionForCategoryId(int id) {
        Ensighten.evaluateEvent(this, "getPositionForCategoryId", new Object[]{new Integer(id)});
        int size = this.mCategories.size();
        for (int i = 0; i < size; i++) {
            int key = this.mCategories.keyAt(i);
            if (((Category) this.mCategories.get(key)).getID() == id) {
                return key;
            }
        }
        return -1;
    }

    public Category getCategoryForPosition(int position) {
        Ensighten.evaluateEvent(this, "getCategoryForPosition", new Object[]{new Integer(position)});
        if (isHeader(position)) {
            return (Category) this.mCategories.get(position);
        }
        for (int i = 0; i < this.mCategories.size(); i++) {
            int key = this.mCategories.keyAt(i);
            if (position > key) {
                int nextIndex = i + 1;
                if (nextIndex >= this.mCategories.size()) {
                    return (Category) this.mCategories.get(key);
                }
                if (position < this.mCategories.keyAt(nextIndex)) {
                    return (Category) this.mCategories.get(key);
                }
            }
        }
        return null;
    }

    public boolean isHeader(int position) {
        Ensighten.evaluateEvent(this, "isHeader", new Object[]{new Integer(position)});
        return getItemViewType(position) == 0;
    }

    public void setOutageProduct(List<String> outageProductCodes) {
        Ensighten.evaluateEvent(this, "setOutageProduct", new Object[]{outageProductCodes});
        this.mOutageProductCodes.clear();
        int size = outageProductCodes.size();
        for (int i = 0; i < size; i++) {
            String productCode = (String) outageProductCodes.get(i);
            if (productCode != null) {
                this.mOutageProductCodes.add(Integer.valueOf(Integer.parseInt(productCode)));
            }
        }
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

    private void filterOutageProducts(Map<Category, List<Product>> productMap, List<Integer> outageCodes) {
        List<Product> list;
        Ensighten.evaluateEvent(this, "filterOutageProducts", new Object[]{productMap, outageCodes});
        ArrayList<List<Product>> emptyCategories = new ArrayList();
        for (List<Product> list2 : productMap.values()) {
            ArrayList<Product> outageProducts = new ArrayList();
            for (Product product : list2) {
                if (outageCodes.contains(product.getExternalId())) {
                    outageProducts.add(product);
                }
            }
            list2.removeAll(outageProducts);
            if (list2.isEmpty()) {
                emptyCategories.add(list2);
            }
        }
        Iterator it = emptyCategories.iterator();
        while (it.hasNext()) {
            list2 = (List) it.next();
            Category emptyCategory = null;
            for (Category c : productMap.keySet()) {
                if (productMap.get(c) == list2) {
                    emptyCategory = c;
                    break;
                }
            }
            if (emptyCategory != null) {
                productMap.remove(emptyCategory);
            }
        }
    }
}
