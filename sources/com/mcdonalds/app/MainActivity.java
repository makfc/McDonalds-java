package com.mcdonalds.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import android.widget.Toast;
import com.ensighten.Ensighten;
import com.facebook.internal.NativeProtocol;
import com.mcdonalds.app.account.AccountProfileFragment;
import com.mcdonalds.app.customer.SignInFragment;
import com.mcdonalds.app.customer.SignOutFragment;
import com.mcdonalds.app.customer.SignUpFragment;
import com.mcdonalds.app.customer.TermsOfServiceFragment;
import com.mcdonalds.app.customer.UserValidationFragment;
import com.mcdonalds.app.customer.ValidationResendFragment;
import com.mcdonalds.app.firstload.SelectStoreActivity;
import com.mcdonalds.app.gmalite.account.LiteAccountProfileFragment;
import com.mcdonalds.app.gmalite.customer.LiteEmailVerificationFragment;
import com.mcdonalds.app.gmalite.customer.LiteForgotPasswordFragment;
import com.mcdonalds.app.gmalite.customer.LitePolicyUpdatesFragment;
import com.mcdonalds.app.gmalite.customer.LiteResetPasswordFragment;
import com.mcdonalds.app.gmalite.customer.LiteSignInFragment;
import com.mcdonalds.app.gmalite.customer.LiteSignUpFragment;
import com.mcdonalds.app.home.dashboard.DashboardFragment;
import com.mcdonalds.app.msa.MSALoggedInLandingFragment;
import com.mcdonalds.app.msa.MSANotLoggedInLandingFragment;
import com.mcdonalds.app.navigation.NavigationManager;
import com.mcdonalds.app.nutrition.NutritionCategoryListFragment;
import com.mcdonalds.app.ordering.menu.FavoriteOrderUpdateFragment;
import com.mcdonalds.app.ordering.menu.LastOrderActivity;
import com.mcdonalds.app.ordering.menu.MenuActivity;
import com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity;
import com.mcdonalds.app.p043ui.ConfigSwitcherFragment;
import com.mcdonalds.app.p043ui.LocationSelectFragment;
import com.mcdonalds.app.p043ui.URLBasketNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.about.AboutAppFragment;
import com.mcdonalds.app.p043ui.about.AboutMcDonaldsFragment;
import com.mcdonalds.app.storelocator.StoreLocatorContainerFragment;
import com.mcdonalds.app.tutorial.TutorialFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.web.WebActivity;
import com.mcdonalds.app.web.WebFragment;
import com.mcdonalds.app.web.WebHamburgerActivity;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.mcdonalds.sdk.modules.nutrition.NutritionModule;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends URLBasketNavigationActivity {
    private List<String> validAuthorities = new ArrayList();

    /* Access modifiers changed, original: protected */
    public boolean shouldShowHamburgerMenu() {
        Ensighten.evaluateEvent(this, "shouldShowHamburgerMenu", null);
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        String screen;
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String home = Configuration.getSharedInstance().getStringForKey("interface.home");
        if (TextUtils.isEmpty(home)) {
            home = "dashboard";
        }
        if (extras != null) {
            screen = extras.getString(URLNavigationActivity.ARG_FRAGMENT, home);
        } else {
            screen = home;
        }
        getSupportFragmentManager().beginTransaction().replace(getContainerResource(), passIntentExtrasAsArgument(getScreenFragment(screen)), screen).commit();
        initValidAuthorities();
        openLink(extras, screen, getIntent());
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        LocalDataManager.getSharedInstance().setLastActive(Calendar.getInstance().getTimeInMillis());
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        Order order = OrderManager.getInstance().getCurrentOrder();
        if (order != null && !ListUtils.isEmpty(order.getProducts())) {
            AnalyticsUtils.trackAppFinish();
        }
    }

    /* Access modifiers changed, original: protected */
    public Fragment getScreenFragment(@NonNull String screen) {
        Ensighten.evaluateEvent(this, "getScreenFragment", new Object[]{screen});
        if (screen.equals("store_locator")) {
            return new StoreLocatorContainerFragment();
        }
        if (screen.equals("account_settings")) {
            return new AccountProfileFragment();
        }
        if (screen.equals("signout")) {
            return new SignOutFragment();
        }
        if (screen.equals("sign_up")) {
            return new SignUpFragment();
        }
        if (screen.equals(FavoriteOrderUpdateFragment.NAME)) {
            return new FavoriteOrderUpdateFragment();
        }
        if (screen.equals(JiceArgs.EVENT_CHECK_IN)) {
            return new SignInFragment();
        }
        if (screen.equals("tutorial")) {
            return new TutorialFragment();
        }
        if (screen.equals(JiceArgs.EVENT_REGISTER)) {
            return new TermsOfServiceFragment();
        }
        if (screen.equals("web")) {
            return new WebFragment(getString(C2658R.string.title_loading));
        }
        if (screen.equals("about_app")) {
            return new AboutAppFragment();
        }
        if (screen.equals("config_select")) {
            return new ConfigSwitcherFragment();
        }
        if (screen.equals("location_select")) {
            return new LocationSelectFragment();
        }
        if (screen.equals("nutrition_list")) {
            return new NutritionCategoryListFragment();
        }
        if (screen.equals("mail_resend")) {
            return new ValidationResendFragment();
        }
        if (screen.equals("mail_validation")) {
            return new UserValidationFragment();
        }
        if (screen.equals("about_mcd")) {
            return new AboutMcDonaldsFragment();
        }
        if (screen.equals("gmalite_signin")) {
            return new LiteSignInFragment();
        }
        if (screen.equals("gmalite_sign_up")) {
            return new LiteSignUpFragment();
        }
        if (screen.equals(LiteForgotPasswordFragment.NAME)) {
            return new LiteForgotPasswordFragment();
        }
        if (screen.equals("literesetpassword")) {
            return new LiteResetPasswordFragment();
        }
        if (screen.equals("litepolicyupdates")) {
            return new LitePolicyUpdatesFragment();
        }
        if (screen.equals("lite_account_settings")) {
            return new LiteAccountProfileFragment();
        }
        if (screen.equals("liteverifyemail")) {
            return new LiteEmailVerificationFragment();
        }
        if (screen.equals("msa_not_logged_in")) {
            return new MSANotLoggedInLandingFragment();
        }
        if (screen.equals("msa_logged_in")) {
            return new MSALoggedInLandingFragment();
        }
        return new DashboardFragment();
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldAutoSetParentIntent() {
        Ensighten.evaluateEvent(this, "shouldAutoSetParentIntent", null);
        return false;
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldNavigateUp() {
        Ensighten.evaluateEvent(this, "shouldNavigateUp", null);
        return false;
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getDisplayedFragment();
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        Ensighten.evaluateEvent(this, "onNewIntent", new Object[]{intent});
        super.onNewIntent(intent);
        getSupportFragmentManager().popBackStackImmediate(null, 1);
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.getBoolean("REFRESH_LAST_ORDER", false)) {
                getIntent().putExtras(extras);
            }
            String screen = extras.getString(URLNavigationActivity.ARG_FRAGMENT, null);
            if (TextUtils.isEmpty(screen)) {
                Offer offer = (Offer) intent.getExtras().getParcelable(PushConstants.BUNDLE_OFFER_KEY);
                if (offer != null) {
                    ((DashboardFragment) getDisplayedFragment()).setPushOffer(offer);
                    return;
                }
            } else if (Configuration.getSharedInstance().getBooleanForKey("interface.dashboard.newPromoWorkflow")) {
                Bundle b = new Bundle();
                b.putBoolean("GO_TO_OFFER", extras.getBoolean("GO_TO_OFFER"));
                b.putString("extra_offer", extras.getString("extra_offer"));
                b.putBoolean("GO_TO_PRODUCT", extras.getBoolean("GO_TO_PRODUCT"));
                b.putString("PRODUCT_RECIPE_ID", extras.getString("PRODUCT_RECIPE_ID"));
                showFragment(screen, b);
            } else {
                showFragment(screen);
            }
        }
        openLink(extras, null, intent);
    }

    private void openLink(Bundle extras, String screen, Intent intent) {
        Ensighten.evaluateEvent(this, "openLink", new Object[]{extras, screen, intent});
        if (intent.getData() != null && !startNutrition(intent) && !showOffer(intent) && !showWebLink(intent)) {
            String authority = intent.getData().getAuthority();
            if (authority.equals("store_locator") && Configuration.getSharedInstance().getBooleanForKey("interface.skipFirstLoadAddressSelection") && OrderManager.getInstance().getCurrentStore() == null) {
                startActivity(SelectStoreActivity.class);
            } else if (authority.equals("latest_order")) {
                startActivity(LastOrderActivity.class);
            } else if (authority.equals("myvoice")) {
                Intent intentForPackage = getPackageManager().getLaunchIntentForPackage("kodo.app.mcdhk");
                if (intentForPackage != null) {
                    startActivity(intentForPackage);
                    return;
                }
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=kodo.app.mcdhk"));
                if (viewIntent != null) {
                    startActivity(viewIntent);
                } else {
                    Toast.makeText(this, C2658R.string.no_application, 0).show();
                }
            } else if (authority.equals("whats_more")) {
                if (LocalDataManager.getSharedInstance().getDeviceLanguage().toLowerCase().contains("zh")) {
                    extras.putString("link", "http://campaign.mcdonalds.com.hk/GMA/explore-tc.html");
                } else {
                    extras.putString("link", "http://campaign.mcdonalds.com.hk/GMA/explore-en.html");
                }
                extras.putInt("view_title", C2658R.string.appmenu_whats_more);
                startActivity(WebHamburgerActivity.class, "web", extras);
            } else {
                String fragmentName = normalizeFragmentName(authority);
                if (!fragmentName.equals(screen)) {
                    if (screen != null && screen.contains(fragmentName)) {
                        return;
                    }
                    if (Configuration.getSharedInstance().getBooleanForKey("interface.skipFirstLoadAddressSelection") && OrderManager.getInstance().getCurrentStore() == null && fragmentName.equals("msa_logged_in")) {
                        startActivityForResult(OrderMethodSelectionActivity.class, 1691);
                    } else if (!MenuActivity.ENDPOINTS.contains(fragmentName)) {
                        if (intent.getData() != null) {
                            if (extras == null) {
                                extras = new Bundle();
                            }
                            extras.putParcelable("Uri", intent.getData());
                            String home = Configuration.getSharedInstance().getStringForKey("interface.home");
                            boolean goingHome = intent.getData().toString().equals(home);
                            if (!(TextUtils.isEmpty(home) || goingHome)) {
                                extras.putString("interface.home", home.replace(URLNavigationActivity.URI_SCHEME, ""));
                            }
                        }
                        showFragment(fragmentName, extras);
                    } else if (!Configuration.getSharedInstance().getBooleanForKey("interface.skipFirstLoadAddressSelection")) {
                        startActivity(MenuActivity.class, fragmentName);
                    } else if (OrderManager.getInstance().getCurrentStore() == null && (fragmentName.equals("recents_grid") || fragmentName.equals("favorites_grid"))) {
                        getDisplayedFragment().getArguments().putString("GO_TO_MENU_LINK", fragmentName);
                        startActivityForResult(OrderMethodSelectionActivity.class, 1223);
                    } else {
                        startActivity(MenuActivity.class, fragmentName);
                    }
                }
            }
        }
    }

    private boolean startNutrition(Intent intent) {
        Ensighten.evaluateEvent(this, "startNutrition", new Object[]{intent});
        if (!ModuleManager.isModuleEnabled(NutritionModule.NAME).booleanValue() || !intent.getData().getHost().equals("nutrition_item")) {
            return false;
        }
        String recipeId = intent.getData().getQueryParameter("recipeId");
        if (recipeId == null) {
            return false;
        }
        NavigationManager.getInstance().showNutrition(this, recipeId, null, null, this);
        return true;
    }

    private boolean showOffer(Intent intent) {
        Ensighten.evaluateEvent(this, "showOffer", new Object[]{intent});
        if (intent.getData().getHost().equals("offer_detail")) {
            String offerId = intent.getData().getQueryParameter("offerId");
            if (offerId != null) {
                NavigationManager.getInstance().showOffer(this, offerId);
                return true;
            }
        }
        return false;
    }

    private boolean showWebLink(Intent intent) {
        Ensighten.evaluateEvent(this, "showWebLink", new Object[]{intent});
        if (!intent.getData().getHost().equals("external_link") || intent.getData().getQueryParameter(NativeProtocol.IMAGE_URL_KEY) == null) {
            return false;
        }
        Bundle extras = intent.getExtras() != null ? intent.getExtras() : new Bundle();
        extras.putString("link", "http://www.mcdonalds.com/usmobile/en/your_questions/our_food.html?survey=no");
        startActivity(WebActivity.class, extras);
        return true;
    }

    private String normalizeFragmentName(String authority) {
        Ensighten.evaluateEvent(this, "normalizeFragmentName", new Object[]{authority});
        if (!this.validAuthorities.contains(authority)) {
            if (authority.equals("nutritional_category")) {
                if (ModuleManager.isModuleEnabled(NutritionModule.NAME).booleanValue()) {
                    return "nutrition_list";
                }
                if (ModuleManager.isModuleEnabled("ordering").booleanValue()) {
                    return "start_order";
                }
                if (ModuleManager.isModuleEnabled("ordering").booleanValue()) {
                    return "start_order_delivery";
                }
            }
            return "dashboard";
        } else if (!authority.equals("account_settings")) {
            return authority;
        } else {
            CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            if (customerModule == null || customerModule.isLoggedIn()) {
                return authority;
            }
            return JiceArgs.EVENT_CHECK_IN;
        }
    }

    private void initValidAuthorities() {
        Ensighten.evaluateEvent(this, "initValidAuthorities", null);
        this.validAuthorities.add("store_locator");
        this.validAuthorities.add("account_settings");
        this.validAuthorities.add("menu_grid");
        this.validAuthorities.add("menu_grid_food");
        this.validAuthorities.add("recents_grid");
        this.validAuthorities.add("favorites_grid");
        this.validAuthorities.add("start_order");
        this.validAuthorities.add("start_order_delivery");
        this.validAuthorities.add("signout");
        this.validAuthorities.add("sign_up");
        this.validAuthorities.add(FavoriteOrderUpdateFragment.NAME);
        this.validAuthorities.add(JiceArgs.EVENT_CHECK_IN);
        this.validAuthorities.add("tutorial");
        this.validAuthorities.add(JiceArgs.EVENT_REGISTER);
        this.validAuthorities.add("web");
        this.validAuthorities.add("about_mcd");
        this.validAuthorities.add("about_app");
        this.validAuthorities.add("config_select");
        this.validAuthorities.add("location_select");
        this.validAuthorities.add("nutrition_list");
        this.validAuthorities.add("nutrition_item");
        this.validAuthorities.add("mail_resend");
        this.validAuthorities.add("mail_validation");
        this.validAuthorities.add("gmalite_signin");
        this.validAuthorities.add("gmalite_sign_up");
        this.validAuthorities.add(LiteForgotPasswordFragment.NAME);
        this.validAuthorities.add("literesetpassword");
        this.validAuthorities.add("litepolicyupdates");
        this.validAuthorities.add("lite_account_settings");
        this.validAuthorities.add("liteverifyemail");
        this.validAuthorities.add("msa_not_logged_in");
        this.validAuthorities.add("msa_logged_in");
    }
}
