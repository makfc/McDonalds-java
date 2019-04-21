package com.mcdonalds.app.analytics.datalayer.mapping;

import com.ensighten.Ensighten;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataLayerButtonMapping {
    private static final Map<String, String> MAP;

    public static String get(String page, String tag) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.analytics.datalayer.mapping.DataLayerButtonMapping", "get", new Object[]{page, tag});
        return (String) MAP.get(format(page, tag));
    }

    private static String format(String page, String tag) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.analytics.datalayer.mapping.DataLayerButtonMapping", "format", new Object[]{page, tag});
        return String.format("%s-%s", new Object[]{page, tag});
    }

    static {
        Map<String, String> result = new LinkedHashMap();
        result.put(format("FirstLoadTutorial", "tutorial_next_button"), "NextButtonPressed");
        result.put(format("Dashboard", "button_title_sign_in"), "SignInButtonPressed");
        result.put(format("Dashboard", JiceArgs.EVENT_REGISTER), "RegisterButtonPressed");
        result.put(format("Registration", "continue_button"), "RegisterButtonPressed");
        result.put(format("Registration", "sign_in"), "SignInButtonPressed");
        result.put(format("OffersRequest", "offers_registration_button_text"), "AcceptOffersButtonAction");
        result.put(format("OffersRequest", "offers_deny_text"), "AcceptOffersButtonAction");
        result.put(format("AboutTheApp", "end_user_license_agreement"), "EULAItemPressed");
        result.put(format("AboutTheApp", "terms_and_conditions"), "TermsItemPressed");
        result.put(format("AboutTheApp", "privacy"), "PrivacyItemPressed");
        result.put(format("AboutTheApp", "faq"), "FAQItemPressed");
        result.put(format("RestaurantLocator", "button_select"), "SelectAsCurrentStoreButtonPressed");
        result.put(format("RestaurantLocator", "save_to_favorites"), "FavoriteButtonPressed");
        result.put(format("RestaurantLocator", "sl_skip_button"), "ChooseAnotherStoreButtonPressed");
        result.put(format("RestaurantLocator", "start_an_order"), "PlaceOrderButtonPressed");
        result.put(format("RestaurantLocator", "remove_from_favorites"), "UnfavoriteButtonPressed");
        result.put(format("StoreDetail", "get_directions_btn"), "GetDirectionsButtonPressed");
        result.put(format("StoreDetail", "button_rename_txt"), "RenameStoreButtonPressed");
        result.put(format("StoreDetail", "favorites_btn_txt"), "UnfavoriteButtonPressed");
        result.put(format("ProductMenu", "continue_button"), "ContinueButtonPressed");
        result.put(format("ProductMenu", "find_another_store_label"), "FindAnotherStoreButtonPressed");
        result.put(format("ProductsFilterAllSubview", "continue_button"), "ContinueButtonPressed");
        result.put(format("ProductsFilterAllSubview", "find_another_store_label"), "FindAnotherStoreButtonPressed");
        result.put(format("ProductsFilterFavoritesSubview", "continue_button"), "ContinueButtonPressed");
        result.put(format("ProductsFilterFavoritesSubview", "find_another_store_label"), "FindAnotherStoreButtonPressed");
        result.put(format("ProductsFilterRecentsSubview", "continue_button"), "ContinueButtonPressed");
        result.put(format("ProductsFilterRecentsSubview", "find_another_store_label"), "FindAnotherStoreButtonPressed");
        result.put(format("ProductsFilterSearchSubview", "continue_button"), "ContinueButtonPressed");
        result.put(format("ProductsFilterSearchSubview", "find_another_store_label"), "FindAnotherStoreButtonPressed");
        result.put(format("ProductMenu", "button_title_sign_in"), "SignInButtonPressed");
        result.put(format("ProductMenu", JiceArgs.EVENT_REGISTER), "RegisterButtonPressed");
        result.put(format("ProductsFilterRecentsSubview", "button_title_sign_in"), "SignInButtonPressed");
        result.put(format("ProductsFilterRecentsSubview", JiceArgs.EVENT_REGISTER), "RegisterButtonPressed");
        result.put(format("ProductsFilterFavoritesSubview", "button_title_sign_in"), "SignInButtonPressed");
        result.put(format("ProductsFilterFavoritesSubview", JiceArgs.EVENT_REGISTER), "RegisterButtonPressed");
        result.put(format("ProductsFilterAllSubview", "button_title_sign_in"), "SignInButtonPressed");
        result.put(format("ProductsFilterAllSubview", JiceArgs.EVENT_REGISTER), "RegisterButtonPressed");
        result.put(format("ProductDetails", "plus_sign"), "UpdateMealQuantityPressed");
        result.put(format("ProductDetails", "minus_sign"), "UpdateMealQuantityPressed");
        result.put(format("ProductDetails", "add_item_to_favorites"), "FavoriteButtonPressed");
        result.put(format("ProductDetails", "save_to_favorites"), "FavoriteButtonPressed");
        result.put(format("ProductDetails", "label_add_to_basket"), "AddToBasketButtonPressed");
        result.put(format("Basket", JiceArgs.EVENT_REGISTER), "RegisterButtonPressed");
        result.put(format("Basket", "text_signin_title"), "SignInButtonPressed");
        result.put(format("Basket", "button_delete_order"), "DeleteButtonPressed");
        result.put(format("Basket", "edit"), "EditButtonPressed");
        result.put(format("Basket", "remove"), "RemoveButtonPressed");
        result.put(format("Basket", "make_it_a_meal"), "RemoveButtonPressed");
        result.put(format("LogIn", "button_text_login"), "SignInButtonPressed");
        result.put(format("LogIn", "register_signin_button"), "RegisterButtonPressed");
        result.put(format("PushNotificationRequest", "push_notification_and_offers_confirm_text"), "AcceptOffersButtonAction");
        result.put(format("PushNotificationRequest", "push_notification_deny_text"), "DeclineOffersButtonAction");
        result.put(format("FirstLoadTutorial", "button_title_sign_in"), "SignInButtonPressed");
        result.put(format("ProductsFilterFavoritesSubview", "order_again"), "OrderAgainButtonPressed");
        result.put(format("ProductsFilterFavoritesSubview", "see_all"), "SeeAllButtonPressed");
        result.put(format("ProductsFilterRecentsSubview", "order_again"), "OrderAgainButtonPressed");
        result.put(format("ProductsFilterRecentsSubview", "see_all"), "SeeAllButtonPressed");
        result.put(format("ProductMenu", "order_again"), "OrderAgainButtonPressed");
        result.put(format("ProductMenu", "see_all"), "SeeAllButtonPressed");
        result.put(format("AllFavorites", "order_again"), "OrderAgainButtonPressed");
        result.put(format("AllRecents", "order_again"), "OrderAgainButtonPressed");
        result.put(format("OrderDetail", "order_again"), "OrderAgainButtonPressed");
        result.put(format("OrderDetail", "favorite_order"), "FavoriteButtonPressed");
        result.put(format("OrderDetail", "save_to_favorites"), "FavoriteButtonPressed");
        MAP = Collections.unmodifiableMap(result);
    }
}
