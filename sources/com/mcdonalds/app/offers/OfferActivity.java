package com.mcdonalds.app.offers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.ordering.ProductCustomizationFragment;
import com.mcdonalds.app.p043ui.URLBasketNavigationActivity;
import com.mcdonalds.app.util.ServiceUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.OfferProduct;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.data.DataPasser;
import java.util.List;

public class OfferActivity extends URLBasketNavigationActivity {
    protected TextView mABTitle;
    private Offer mOffer;
    private OffersListener mOfferListener;
    private OrderOffer mOrderOffer;
    private SparseArray<Product> mSelectedOfferRecipes;

    /* renamed from: com.mcdonalds.app.offers.OfferActivity$2 */
    class C33192 implements OnClickListener {
        C33192() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            OfferActivity.this.setResult(0);
            OfferActivity.this.finish();
        }
    }

    static /* synthetic */ Offer access$002(OfferActivity x0, Offer x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferActivity", "access$002", new Object[]{x0, x1});
        x0.mOffer = x1;
        return x1;
    }

    static /* synthetic */ void access$100(OfferActivity x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferActivity", "access$100", new Object[]{x0});
        x0.showOfferFragment();
    }

    static /* synthetic */ void access$200(OfferActivity x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.offers.OfferActivity", "access$200", new Object[]{x0});
        x0.showOfferIdError();
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        this.mSelectedOfferRecipes = new SparseArray();
        this.mABTitle = (TextView) findViewById(titleId);
        if (getIntent().getExtras().containsKey("extra_offer")) {
            this.mOffer = (Offer) getIntent().getExtras().getParcelable("extra_offer");
        } else {
            this.mOffer = (Offer) DataPasser.getInstance().getData("extra_offer");
        }
        boolean inEditMode = getIntent().getExtras().getBoolean("IN_EDIT_MODE");
        if (this.mOffer != null || inEditMode) {
            showOfferFragment();
        } else {
            setOfferFromId(getIntent().getExtras().getString("extra_offer"));
        }
    }

    private void setOfferFromId(String offerIdString) {
        Ensighten.evaluateEvent(this, "setOfferFromId", new Object[]{offerIdString});
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (!TextUtils.isEmpty(offerIdString)) {
            final Integer offerId = Integer.valueOf(offerIdString);
            if (offerId == null) {
                showOfferIdError();
            } else if (customerModule.isLoggedIn()) {
                String userName = customerModule.getCurrentProfile().getUserName();
                Store store = OrderManager.getInstance().getCurrentStore();
                if (!TextUtils.isEmpty(userName) && store != null) {
                    UIUtils.startActivityIndicator((Context) this, (int) C2658R.string.updating_offer);
                    ServiceUtils.getSharedInstance().retrieveOffers(userName, String.valueOf(store.getStoreId()), null, null, new AsyncListener<List<Offer>>() {
                        public void onResponse(List<Offer> response, AsyncToken token, AsyncException exception) {
                            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                            if (response != null) {
                                for (Offer offer : response) {
                                    if (offer.getOfferId().equals(offerId)) {
                                        OfferActivity.access$002(OfferActivity.this, offer);
                                        UIUtils.stopActivityIndicator();
                                        OfferActivity.access$100(OfferActivity.this);
                                        return;
                                    }
                                }
                            }
                            UIUtils.stopActivityIndicator();
                            OfferActivity.access$200(OfferActivity.this);
                        }
                    });
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        Ensighten.evaluateEvent(this, "onNewIntent", new Object[]{intent});
        super.onNewIntent(intent);
        showOfferFragment(intent.getExtras());
    }

    private void showOfferFragment() {
        Ensighten.evaluateEvent(this, "showOfferFragment", null);
        Fragment fragment = passIntentExtrasAsArgument(new OfferFragment());
        fragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().replace(getContainerResource(), fragment).commit();
    }

    private void showOfferFragment(Bundle arguments) {
        Ensighten.evaluateEvent(this, "showOfferFragment", new Object[]{arguments});
        Fragment fragment = passIntentExtrasAsArgument(new OfferFragment());
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction().replace(getContainerResource(), fragment).commit();
    }

    private void showOfferIdError() {
        Ensighten.evaluateEvent(this, "showOfferIdError", null);
        if (!isFinishing()) {
            new MCDAlertDialogBuilder(this).setMessage((int) C2658R.string.offer_not_found).setPositiveButton((int) C2658R.string.f6083ok, new C33192()).create().show();
        }
    }

    public TextView getABTitle() {
        Ensighten.evaluateEvent(this, "getABTitle", null);
        if (getSupportActionBar() != null) {
            return (TextView) getSupportActionBar().getCustomView();
        }
        return null;
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldShowHamburgerMenu() {
        Ensighten.evaluateEvent(this, "shouldShowHamburgerMenu", null);
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void navigateToBasket() {
        Ensighten.evaluateEvent(this, "navigateToBasket", null);
        ((OfferFragment) getDisplayedFragment()).basketWillBeDisplayed();
        super.navigateToBasket();
    }

    public Product getSelectedOfferProduct(int key) {
        Ensighten.evaluateEvent(this, "getSelectedOfferProduct", new Object[]{new Integer(key)});
        return (Product) this.mSelectedOfferRecipes.get(key);
    }

    public boolean hasSelectedOfferProduct(int key) {
        Ensighten.evaluateEvent(this, "hasSelectedOfferProduct", new Object[]{new Integer(key)});
        return this.mSelectedOfferRecipes.indexOfKey(key) >= 0;
    }

    public Offer getOffer() {
        Ensighten.evaluateEvent(this, "getOffer", null);
        return this.mOffer;
    }

    public void showOfferProductSelection(int position, OfferProduct orderOfferProduct) {
        Ensighten.evaluateEvent(this, "showOfferProductSelection", new Object[]{new Integer(position), orderOfferProduct});
        Bundle args = new Bundle();
        args.putInt("offer_key", position);
        DataPasser.getInstance().putData("offer_product_key", orderOfferProduct);
        startActivityForResult(OfferProductActivity.class, "offerproductfragment", args, 12090);
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        OfferFragment fragment = (OfferFragment) getDisplayedFragment();
        Bundle extraData;
        OrderProduct product;
        switch (requestCode) {
            case 12090:
                switch (resultCode) {
                    case -1:
                        extraData = data.getExtras();
                        int offerKey = extraData.getInt("offer_key");
                        if (extraData.containsKey("selected_recipe_key")) {
                            product = (OrderProduct) extraData.getParcelable("selected_recipe_key");
                        } else {
                            product = (OrderProduct) DataPasser.getInstance().getData("selected_recipe_key");
                        }
                        this.mOfferListener.onOfferProductSelected(offerKey, product);
                        return;
                    default:
                        return;
                }
            case 13098:
                if (resultCode == -1) {
                    OrderProduct choice;
                    extraData = data.getExtras();
                    if (extraData.containsKey("RESULT_PRODUCT")) {
                        choice = (OrderProduct) extraData.getParcelable("RESULT_PRODUCT");
                    } else {
                        choice = (OrderProduct) DataPasser.getInstance().getData("RESULT_PRODUCT");
                    }
                    fragment.productChoiceSelected(choice, extraData.getInt("RESULT_INDEX"), extraData.getInt("RESULT_POSITION"));
                    return;
                }
                return;
            case 38176:
                if (resultCode == -1) {
                    setResult(-1);
                    return;
                }
                return;
            case 45352:
                if (resultCode == -1) {
                    extraData = data.getExtras();
                    if (extraData.containsKey(ProductCustomizationFragment.RESULT_PRODUCT)) {
                        product = (OrderProduct) extraData.getParcelable(ProductCustomizationFragment.RESULT_PRODUCT);
                    } else {
                        product = (OrderProduct) DataPasser.getInstance().getData(ProductCustomizationFragment.RESULT_PRODUCT);
                    }
                    fragment.productCustomizationsUpdated(product, extraData.getInt(ProductCustomizationFragment.RESULT_PRODUCT_INDEX));
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void setOnOfferSelectedListener(OffersListener offerListener) {
        Ensighten.evaluateEvent(this, "setOnOfferSelectedListener", new Object[]{offerListener});
        this.mOfferListener = offerListener;
    }

    public void setOrderOffer(OrderOffer orderOffer) {
        Ensighten.evaluateEvent(this, "setOrderOffer", new Object[]{orderOffer});
        this.mOrderOffer = orderOffer;
    }
}
