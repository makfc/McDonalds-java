package com.mcdonalds.app.ordering;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.offers.OfferActivity;
import com.mcdonalds.app.offers.OfferItemView;
import com.mcdonalds.app.storelocator.OffersStoreLocatorController.OfferSelection;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.OfferUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.services.data.DataPasser;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.List;

public class ProductRelatedOffersView extends FrameLayout implements OnClickListener {
    public static final String LOG_TAG = ProductRelatedOffersView.class.getSimpleName();
    private ViewGroup mOffersContainer;
    private Product mProduct;
    private String mProductId;
    private List<Product> mProducts;
    private List<Offer> mRelatedOffers;

    public ProductRelatedOffersView(Context context) {
        super(context);
        inflate(context);
    }

    public ProductRelatedOffersView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context);
    }

    private void inflate(Context context) {
        Ensighten.evaluateEvent(this, "inflate", new Object[]{context});
        inflate(context, C2658R.layout.view_product_related_offers, this);
        this.mOffersContainer = (ViewGroup) findViewById(C2358R.C2357id.offers_container);
        this.mRelatedOffers = new ArrayList();
    }

    public void filter(List<Offer> offers, Activity activity) {
        Ensighten.evaluateEvent(this, Parameters.FILTER, new Object[]{offers, activity});
        if (!TextUtils.isEmpty(this.mProductId) && !ListUtils.isEmpty(offers)) {
            this.mRelatedOffers.clear();
            for (Offer offer : offers) {
                for (Product product : this.mProducts) {
                    if (offer.relatesToProductId(String.valueOf(product.getExternalId())).booleanValue() && !offer.isArchived().booleanValue() && !offer.isExpired().booleanValue() && checkOrderMtd(offer) && OfferUtils.checkStore(offer)) {
                        this.mRelatedOffers.add(offer);
                        break;
                    }
                }
            }
            refresh(activity);
        }
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

    public void refresh(Activity activity) {
        Ensighten.evaluateEvent(this, "refresh", new Object[]{activity});
        Log.i(LOG_TAG, "refresh called: " + this.mRelatedOffers.size() + " offers");
        Context context = getContext();
        if (context != null) {
            this.mOffersContainer.removeAllViews();
            if (this.mRelatedOffers.isEmpty()) {
                setVisibility(8);
                return;
            }
            int margin = UIUtils.dpAsPixels(context, 5);
            LayoutParams params = new LayoutParams(-1, -2);
            params.setMargins(margin, margin, margin, 0);
            for (Offer offer : this.mRelatedOffers) {
                OfferItemView item = new OfferItemView(context, offer);
                item.setLayoutParams(params);
                item.display(activity);
                item.setOnClickListener(this);
                this.mOffersContainer.addView(item);
            }
            setVisibility(0);
        }
    }

    public void setProductId(String productId, List<Product> products) {
        Ensighten.evaluateEvent(this, "setProductId", new Object[]{productId, products});
        this.mProductId = productId;
        this.mProducts = products;
        Log.i(LOG_TAG, "Product id set: " + productId);
    }

    public void onClick(View view) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
        if (view instanceof OfferItemView) {
            Offer offer = ((OfferItemView) view).getOffer();
            if (getContext() instanceof Activity) {
                trackOfferClick(offer);
                Bundle extras = new Bundle();
                extras.putInt("offer_selection_type", OfferSelection.Nearby.ordinal());
                extras.putBoolean("extra_from_product", true);
                DataPasser.getInstance().putData("extra_current_recipe", this.mProduct);
                DataPasser.getInstance().putData("extra_offer", offer);
                Activity activity = (Activity) getContext();
                Intent intent = new Intent(activity, OfferActivity.class);
                intent.putExtras(extras);
                activity.startActivityForResult(intent, 1389);
                Log.i(LOG_TAG, "Clicked: " + offer);
            }
        }
    }

    private void trackOfferClick(Offer offer) {
        Ensighten.evaluateEvent(this, "trackOfferClick", new Object[]{offer});
        AnalyticsUtils.trackOnClickEvent("/order/item", offer.getName());
    }

    public void setProduct(Product product) {
        Ensighten.evaluateEvent(this, "setProduct", new Object[]{product});
        this.mProduct = product;
    }
}
