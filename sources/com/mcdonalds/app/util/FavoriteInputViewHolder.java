package com.mcdonalds.app.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.customer.SignInActivity;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.FavoriteItem.FavoriteProductType;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.RecipeComponent;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import java.util.Collections;

public class FavoriteInputViewHolder {
    private final Button mCancelFavoritesButton;
    private String mCategoryName;
    private final Context mContext;
    private CustomerModule mCustomerModule = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME));
    private final EditText mFavoriteNameInputEditText;
    private final Button mRemoveFromFavoritesButton;
    private final View mRootView;
    private final Button mSaveToFavoritesButton;
    private final Animation mSlideDown;
    private final Animation mSlideUp;

    /* renamed from: com.mcdonalds.app.util.FavoriteInputViewHolder$1 */
    class C38431 implements AnimationListener {
        C38431() {
        }

        public void onAnimationStart(Animation animation) {
            Ensighten.evaluateEvent(this, "onAnimationStart", new Object[]{animation});
        }

        public void onAnimationEnd(Animation animation) {
            Ensighten.evaluateEvent(this, "onAnimationEnd", new Object[]{animation});
            FavoriteInputViewHolder.this.getFavoriteNameInputEditText().requestFocus();
        }

        public void onAnimationRepeat(Animation animation) {
            Ensighten.evaluateEvent(this, "onAnimationRepeat", new Object[]{animation});
        }
    }

    /* renamed from: com.mcdonalds.app.util.FavoriteInputViewHolder$2 */
    class C38442 implements AnimationListener {
        C38442() {
        }

        public void onAnimationStart(Animation animation) {
            Ensighten.evaluateEvent(this, "onAnimationStart", new Object[]{animation});
        }

        public void onAnimationEnd(Animation animation) {
            Ensighten.evaluateEvent(this, "onAnimationEnd", new Object[]{animation});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.FavoriteInputViewHolder", "access$000", new Object[]{FavoriteInputViewHolder.this}).setVisibility(8);
        }

        public void onAnimationRepeat(Animation animation) {
            Ensighten.evaluateEvent(this, "onAnimationRepeat", new Object[]{animation});
        }
    }

    /* renamed from: com.mcdonalds.app.util.FavoriteInputViewHolder$4 */
    class C38464 implements OnClickListener {
        C38464() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
            FavoriteInputViewHolder.this.hide();
        }
    }

    /* renamed from: com.mcdonalds.app.util.FavoriteInputViewHolder$5 */
    class C38475 implements DialogInterface.OnClickListener {
        C38475() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.util.FavoriteInputViewHolder$8 */
    class C38518 implements DialogInterface.OnClickListener {
        C38518() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    private class FavoriteViewAnimationListener implements AnimationListener {
        OrderProduct mOrderProduct;

        public FavoriteViewAnimationListener(OrderProduct orderProduct) {
            this.mOrderProduct = orderProduct;
        }

        public void onAnimationStart(Animation animation) {
            Ensighten.evaluateEvent(this, "onAnimationStart", new Object[]{animation});
        }

        public void onAnimationEnd(Animation animation) {
            Ensighten.evaluateEvent(this, "onAnimationEnd", new Object[]{animation});
            EditText favoriteNameInput = FavoriteInputViewHolder.this.getFavoriteNameInputEditText();
            favoriteNameInput.setText(this.mOrderProduct.getProduct().getLongName());
            favoriteNameInput.setEnabled(true);
            favoriteNameInput.setSelection(favoriteNameInput.getText().length());
            favoriteNameInput.requestFocus();
            UIUtils.showKeyboard(Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.FavoriteInputViewHolder", "access$400", new Object[]{FavoriteInputViewHolder.this}), favoriteNameInput, false);
        }

        public void onAnimationRepeat(Animation animation) {
            Ensighten.evaluateEvent(this, "onAnimationRepeat", new Object[]{animation});
        }
    }

    static /* synthetic */ void access$100(FavoriteInputViewHolder x0, OrderProduct x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.FavoriteInputViewHolder", "access$100", new Object[]{x0, x1});
        x0.saveToFavoritesClicked(x1);
    }

    static /* synthetic */ void access$200(FavoriteInputViewHolder x0, OrderProduct x1, String x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.FavoriteInputViewHolder", "access$200", new Object[]{x0, x1, x2});
        x0.productAddedToFavorites(x1, x2);
    }

    static /* synthetic */ void access$300(FavoriteInputViewHolder x0, AsyncException x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.FavoriteInputViewHolder", "access$300", new Object[]{x0, x1});
        x0.reportAsyncException(x1);
    }

    public FavoriteInputViewHolder(Context context, View parentView) {
        this.mContext = context;
        this.mRootView = parentView.findViewById(C2358R.C2357id.favorite_name_layout);
        this.mSlideDown = AnimationUtils.loadAnimation(context, C2658R.anim.fade_in);
        this.mSlideUp = AnimationUtils.loadAnimation(context, C2658R.anim.fade_out);
        if (this.mSlideDown != null) {
            this.mSlideDown.setAnimationListener(new C38431());
        }
        if (this.mSlideUp != null) {
            this.mSlideUp.setAnimationListener(new C38442());
        }
        this.mFavoriteNameInputEditText = (EditText) this.mRootView.findViewById(C2358R.C2357id.favorite_name_input);
        this.mSaveToFavoritesButton = (Button) this.mRootView.findViewById(C2358R.C2357id.save_to_favorites_button);
        this.mRemoveFromFavoritesButton = (Button) this.mRootView.findViewById(C2358R.C2357id.remove_from_favorites_button);
        this.mCancelFavoritesButton = (Button) this.mRootView.findViewById(C2358R.C2357id.favorite_cancel_button);
    }

    public void show() {
        Ensighten.evaluateEvent(this, "show", null);
        startSlideDown();
        this.mRootView.setVisibility(0);
    }

    public void hide() {
        Ensighten.evaluateEvent(this, "hide", null);
        startSlideUp();
        UIUtils.dismissKeyboard(this.mContext, getFavoriteNameInputEditText());
    }

    private void startSlideUp() {
        Ensighten.evaluateEvent(this, "startSlideUp", null);
        this.mRootView.startAnimation(this.mSlideUp);
    }

    private void startSlideDown() {
        Ensighten.evaluateEvent(this, "startSlideDown", null);
        this.mRootView.startAnimation(this.mSlideDown);
    }

    public EditText getFavoriteNameInputEditText() {
        Ensighten.evaluateEvent(this, "getFavoriteNameInputEditText", null);
        return this.mFavoriteNameInputEditText;
    }

    public Button getSaveToFavoritesButton() {
        Ensighten.evaluateEvent(this, "getSaveToFavoritesButton", null);
        return this.mSaveToFavoritesButton;
    }

    public Button getRemoveFromFavoritesButton() {
        Ensighten.evaluateEvent(this, "getRemoveFromFavoritesButton", null);
        return this.mRemoveFromFavoritesButton;
    }

    public void setSaveToFavoritesButtonVisible() {
        Ensighten.evaluateEvent(this, "setSaveToFavoritesButtonVisible", null);
        this.mSaveToFavoritesButton.setVisibility(0);
        this.mRemoveFromFavoritesButton.setVisibility(8);
    }

    public void setRemoveFromFavoritesButtonVisible() {
        Ensighten.evaluateEvent(this, "setRemoveFromFavoritesButtonVisible", null);
        this.mSaveToFavoritesButton.setVisibility(8);
        this.mRemoveFromFavoritesButton.setVisibility(0);
    }

    public Button getCancelToFavoritesButton() {
        Ensighten.evaluateEvent(this, "getCancelToFavoritesButton", null);
        return this.mCancelFavoritesButton;
    }

    public void setCategoryName(String categoryName) {
        Ensighten.evaluateEvent(this, "setCategoryName", new Object[]{categoryName});
        this.mCategoryName = categoryName;
    }

    public void addToFavoritesClicked(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "addToFavoritesClicked", new Object[]{orderProduct});
        Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory(orderProduct.getProduct().getLongName()).setAction("On click").setLabel("PDP - Favorite").build());
        AnalyticsUtils.trackEvent(new ArgBuilder().setLabel("save_item_as_favorite").setMapping("product_id", orderProduct.getProductCode()).setMapping(RecipeComponent.COLUMN_PRODUCT_NAME, orderProduct.getDisplayName()).setMapping("product_quantity", Integer.valueOf(orderProduct.getQuantity())).build());
        if (!this.mCustomerModule.isLoggedIn()) {
            startSignInActivity();
        } else if (this.mCustomerModule.getCurrentProfile().isFavoriteOrderProduct(orderProduct, FavoriteProductType.FAVORITE_PRODUCT_TYPE_ITEM)) {
            removeProductFromFavorites(orderProduct);
        } else {
            showAddToFavoritesView(orderProduct);
        }
    }

    private void startSignInActivity() {
        Ensighten.evaluateEvent(this, "startSignInActivity", null);
        this.mContext.startActivity(new Intent(this.mContext, SignInActivity.class));
    }

    private void showAddToFavoritesView(final OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "showAddToFavoritesView", new Object[]{orderProduct});
        show();
        this.mSlideDown.setAnimationListener(new FavoriteViewAnimationListener(orderProduct));
        this.mSaveToFavoritesButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                FavoriteInputViewHolder.access$100(FavoriteInputViewHolder.this, orderProduct);
            }
        });
        this.mCancelFavoritesButton.setOnClickListener(new C38464());
    }

    private void saveToFavoritesClicked(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "saveToFavoritesClicked", new Object[]{orderProduct});
        if (TextUtils.isEmpty(getFavoriteNameInputEditText().getText().toString().trim())) {
            MCDAlertDialogBuilder.withContext(this.mContext).setMessage(this.mContext.getString(C2658R.string.alert_error_empty_favorite_order_name_msg)).setPositiveButton(this.mContext.getResources().getString(C2658R.string.f6083ok), new C38475()).create().show();
        } else {
            addProductToFavorites(orderProduct);
        }
    }

    private void addProductToFavorites(final OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "addProductToFavorites", new Object[]{orderProduct});
        EditText favoriteNameInput = getFavoriteNameInputEditText();
        final String favoriteName = favoriteNameInput.getText().toString();
        UIUtils.startActivityIndicator(this.mContext, this.mContext.getResources().getString(C2658R.string.saving) + " " + favoriteName);
        UIUtils.dismissKeyboard(this.mContext, favoriteNameInput);
        this.mCustomerModule.addFavoriteProducts(this.mCustomerModule.getCurrentProfile(), Collections.singletonList(orderProduct), favoriteName.trim(), true, new AsyncListener<Boolean>() {
            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                UIUtils.stopActivityIndicator();
                if (exception == null) {
                    FavoriteInputViewHolder.access$200(FavoriteInputViewHolder.this, orderProduct, favoriteName);
                } else {
                    FavoriteInputViewHolder.access$300(FavoriteInputViewHolder.this, exception);
                }
            }
        });
    }

    private void removeProductFromFavorites(final OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "removeProductFromFavorites", new Object[]{orderProduct});
        UIUtils.startActivityIndicator(this.mContext, this.mContext.getResources().getString(C2658R.string.removing_from_favorites));
        this.mCustomerModule.deleteFavoriteProducts(this.mCustomerModule.getCurrentProfile(), Collections.singletonList(this.mCustomerModule.getCurrentProfile().getFavoriteOrderProduct(orderProduct)), new AsyncListener<Boolean>() {

            /* renamed from: com.mcdonalds.app.util.FavoriteInputViewHolder$7$1 */
            class C38491 implements DialogInterface.OnClickListener {
                C38491() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                    dialog.dismiss();
                }
            }

            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (exception == null && response.booleanValue()) {
                    UIUtils.stopActivityIndicator();
                    MCDAlertDialogBuilder.withContext(Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.FavoriteInputViewHolder", "access$400", new Object[]{FavoriteInputViewHolder.this})).setTitle((int) C2658R.string.deleted_favorite).setMessage(Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.FavoriteInputViewHolder", "access$400", new Object[]{FavoriteInputViewHolder.this}).getResources().getString(C2658R.string.deleted_from_favorites_message, new Object[]{orderProduct.getDisplayName()})).setPositiveButton((int) C2658R.string.f6083ok, new C38491()).create().show();
                }
            }
        });
    }

    private void productAddedToFavorites(OrderProduct orderProduct, String favoriteName) {
        Ensighten.evaluateEvent(this, "productAddedToFavorites", new Object[]{orderProduct, favoriteName});
        hide();
        Analytics.trackCustom(31, orderProduct.getProduct().getLongName());
        Analytics.trackCustom(32, favoriteName);
        if (this.mCategoryName != null) {
            Analytics.trackCustom(30, this.mCategoryName);
        }
    }

    private void reportAsyncException(AsyncException exception) {
        Ensighten.evaluateEvent(this, "reportAsyncException", new Object[]{exception});
        if (this.mContext != null) {
            MCDAlertDialogBuilder.withContext(this.mContext).setPositiveButton((int) C2658R.string.f6083ok, new C38518()).setMessage(exception.getMessage()).create().show();
        }
    }
}
