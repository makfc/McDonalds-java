package com.mcdonalds.app.ordering.menu;

import android.content.Context;
import android.content.Intent;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.view.ViewPager;
import android.support.p001v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
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
import com.mcdonalds.app.nutrition.AllFavoritesActivity;
import com.mcdonalds.app.nutrition.OrderPagerAdapter;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.widget.PagerIndicator;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.services.data.DataPasser;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends Adapter<ViewHolder> {
    private Context mContext;
    private OnMenuGridItemClickListener mOnMenuGridItemClickListener;
    private OrderPagerAdapter mOrderPagerAdapter;
    private ArrayList<Order> mOrders;
    private List<OrderProduct> mProducts;
    private OnClickListener mSeeAllClickListener = new C35232();

    /* renamed from: com.mcdonalds.app.ordering.menu.FavoritesAdapter$2 */
    class C35232 implements OnClickListener {
        C35232() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Intent intent = new Intent(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoritesAdapter", "access$100", new Object[]{FavoritesAdapter.this}), AllFavoritesActivity.class);
            DataPasser.getInstance().putData("favoriteOrders", Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoritesAdapter", "access$200", new Object[]{FavoritesAdapter.this}));
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoritesAdapter", "access$100", new Object[]{FavoritesAdapter.this}).startActivity(intent);
        }
    }

    public interface OnMenuGridItemClickListener {
        void onMenuGridItemClicked(View view, OrderProduct orderProduct);
    }

    public class ViewHolder extends android.support.p001v7.widget.RecyclerView.ViewHolder {
        public RelativeLayout container;
        public ImageView favorite;
        public ImageView image;
        public LinearLayout item_no_available;
        public ImageView offer;
        public ViewPager orderPager;
        public PagerIndicator pagerIndicator;
        public TextView seeAll;
        public TextView title;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == 1) {
                this.container = (RelativeLayout) itemView.findViewById(C2358R.C2357id.grid_item);
                this.title = (TextView) itemView.findViewById(C2358R.C2357id.name);
                this.image = (ImageView) itemView.findViewById(2131820643);
                this.offer = (ImageView) itemView.findViewById(C2358R.C2357id.offer_icon);
                this.favorite = (ImageView) itemView.findViewById(C2358R.C2357id.favorite_icon);
                this.item_no_available = (LinearLayout) itemView.findViewById(C2358R.C2357id.item_not_available);
            } else if (viewType == 0) {
                this.orderPager = (ViewPager) itemView.findViewById(C2358R.C2357id.order_pager);
                this.pagerIndicator = (PagerIndicator) itemView.findViewById(C2358R.C2357id.pager_indicator);
                this.seeAll = (TextView) itemView.findViewById(C2358R.C2357id.see_all_recents);
            }
        }
    }

    static /* synthetic */ void access$000(FavoritesAdapter x0, View x1, OrderProduct x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoritesAdapter", "access$000", new Object[]{x0, x1, x2});
        x0.onMenuGridItemSelected(x1, x2);
    }

    public FavoritesAdapter(FragmentManager fragmentManager, Context context) {
        this.mOrderPagerAdapter = new OrderPagerAdapter(fragmentManager);
        this.mOrders = new ArrayList();
        this.mProducts = new ArrayList();
        this.mContext = context;
    }

    public void setOrders(List<Order> orders) {
        Ensighten.evaluateEvent(this, "setOrders", new Object[]{orders});
        this.mOrders = new ArrayList(orders);
        this.mOrderPagerAdapter.setOrders(this.mOrders);
        notifyDataSetChanged();
    }

    public void setProducts(List<OrderProduct> products) {
        Ensighten.evaluateEvent(this, "setProducts", new Object[]{products});
        if (products == null) {
            products = new ArrayList();
        }
        this.mProducts = products;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 0:
                view = inflater.inflate(C2658R.layout.favorites_order_pager, parent, false);
                break;
            case 2:
                view = inflater.inflate(C2658R.layout.favorites_section_header, parent, false);
                break;
            default:
                view = inflater.inflate(C2658R.layout.grid_item, parent, false);
                break;
        }
        return new ViewHolder(view, viewType);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Ensighten.evaluateEvent(this, "onBindViewHolder", new Object[]{holder, new Integer(position)});
        int type = getItemViewType(position);
        if (type == 0 && this.mOrders.size() != 0) {
            holder.orderPager.setVisibility(0);
            holder.pagerIndicator.setVisibility(0);
            holder.seeAll.setVisibility(0);
            holder.orderPager.setAdapter(this.mOrderPagerAdapter);
            holder.pagerIndicator.setupWithViewPager(holder.orderPager);
            holder.seeAll.setOnClickListener(this.mSeeAllClickListener);
        } else if (type == 1) {
            final OrderProduct orderProduct = (OrderProduct) this.mProducts.get(position - getCountBuffer());
            setItemTitle(holder.title, orderProduct);
            holder.container.setVisibility(0);
            holder.title.setVisibility(0);
            holder.image.setVisibility(0);
            holder.item_no_available.setVisibility(8);
            loadImage(orderProduct.getProduct().getImageUrl(), holder.image);
            holder.container.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                    FavoritesAdapter.access$000(FavoritesAdapter.this, v, orderProduct);
                }
            });
            DataLayerClickListener.setDataLayerTag(holder.container, "OrderPressed", position);
        }
    }

    private void setItemTitle(TextView title, OrderProduct orderProduct) {
        int color;
        Ensighten.evaluateEvent(this, "setItemTitle", new Object[]{title, orderProduct});
        String name = orderProduct.getFavoriteName();
        Product product = orderProduct.getProduct();
        if (product == null || !product.isAdvertisable().booleanValue()) {
            color = C2658R.color.mcd_black;
        } else {
            color = C2658R.color.mcd_red;
        }
        title.setText(name);
        title.setTextColor(ContextCompat.getColor(this.mContext, color));
    }

    public void setOnMenuGridItemClickListener(OnMenuGridItemClickListener onMenuGridItemClickListener) {
        Ensighten.evaluateEvent(this, "setOnMenuGridItemClickListener", new Object[]{onMenuGridItemClickListener});
        this.mOnMenuGridItemClickListener = onMenuGridItemClickListener;
    }

    private void onMenuGridItemSelected(View view, OrderProduct product) {
        Ensighten.evaluateEvent(this, "onMenuGridItemSelected", new Object[]{view, product});
        if (this.mOnMenuGridItemClickListener != null) {
            this.mOnMenuGridItemClickListener.onMenuGridItemClicked(view, product);
        }
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

    private int getCountBuffer() {
        Ensighten.evaluateEvent(this, "getCountBuffer", null);
        return ListUtils.isEmpty(this.mProducts) ? 1 : 2;
    }

    public int getItemCount() {
        Ensighten.evaluateEvent(this, "getItemCount", null);
        return this.mProducts.size() + getCountBuffer();
    }

    public int getItemViewType(int position) {
        Ensighten.evaluateEvent(this, "getItemViewType", new Object[]{new Integer(position)});
        if (position == 0) {
            return 0;
        }
        return (position != 1 || ListUtils.isEmpty(this.mProducts)) ? 1 : 2;
    }

    public int getSpan(int position) {
        Ensighten.evaluateEvent(this, "getSpan", new Object[]{new Integer(position)});
        if (position == 0 || (position == 1 && !ListUtils.isEmpty(this.mProducts))) {
            return 2;
        }
        return 1;
    }
}
