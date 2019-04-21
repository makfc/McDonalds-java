package com.mcdonalds.sdk.connectors.middleware.helpers;

import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.CustomerConnector;
import com.mcdonalds.sdk.connectors.middleware.MWException;
import com.mcdonalds.sdk.connectors.middleware.model.DCSFavorite;
import com.mcdonalds.sdk.connectors.middleware.model.DCSFavorite.DCSFavoriteDetails;
import com.mcdonalds.sdk.connectors.middleware.model.DCSFavorite.DCSFavoriteDetailsList;
import com.mcdonalds.sdk.connectors.middleware.model.DCSFeedbackProfile;
import com.mcdonalds.sdk.connectors.middleware.model.DCSProduct;
import com.mcdonalds.sdk.connectors.middleware.model.DCSProfile;
import com.mcdonalds.sdk.connectors.middleware.model.DCSProfile.DCSProfileExtended;
import com.mcdonalds.sdk.connectors.middleware.model.MWPaymentWallet;
import com.mcdonalds.sdk.connectors.middleware.request.DCSAuthenticationRequest;
import com.mcdonalds.sdk.connectors.middleware.request.DCSChangePasswordRequest;
import com.mcdonalds.sdk.connectors.middleware.request.DCSDeregisterRequest;
import com.mcdonalds.sdk.connectors.middleware.request.DCSGetCustomerDataRequest;
import com.mcdonalds.sdk.connectors.middleware.request.DCSRefreshTokenRequest;
import com.mcdonalds.sdk.connectors.middleware.request.DCSRegistrationRequest;
import com.mcdonalds.sdk.connectors.middleware.request.DCSRequest;
import com.mcdonalds.sdk.connectors.middleware.request.DCSResetPasswordRequest;
import com.mcdonalds.sdk.connectors.middleware.request.DCSSignOutRequest;
import com.mcdonalds.sdk.connectors.middleware.request.DCSSocialAuthenticationRequest;
import com.mcdonalds.sdk.connectors.middleware.request.DCSSocialRegistrationRequest;
import com.mcdonalds.sdk.connectors.middleware.request.DCSUpdateProfileAddingRequest;
import com.mcdonalds.sdk.connectors.middleware.request.DCSUpdateProfileRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetPaymentWalletRequest;
import com.mcdonalds.sdk.connectors.middleware.response.DCSAuthenticationResponse;
import com.mcdonalds.sdk.connectors.middleware.response.DCSAuthenticationResponse.DCSAuthenticationDetails;
import com.mcdonalds.sdk.connectors.middleware.response.DCSChangePasswordResponse;
import com.mcdonalds.sdk.connectors.middleware.response.DCSGetProfileDetails;
import com.mcdonalds.sdk.connectors.middleware.response.DCSGetProfileResponse;
import com.mcdonalds.sdk.connectors.middleware.response.DCSRegistrationResponse;
import com.mcdonalds.sdk.connectors.middleware.response.DCSResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetPaymentWalletResponse;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;
import com.mcdonalds.sdk.modules.models.FavoriteItem;
import com.mcdonalds.sdk.modules.models.FeedBackType;
import com.mcdonalds.sdk.modules.models.NotificationPreferences;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.PaymentWallet;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.network.SessionManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MWDCSConnectorHelper extends MWCustomerConnectorHelper implements CustomerConnector {
    private static final int STATUS_CODE_SUCCESS = 11011;
    private DCSProfile mDCSProfile;

    public MWDCSConnectorHelper(MWConnectorShared sharedData) {
        super(sharedData);
    }

    public AsyncToken authenticate(AuthenticationParameters parameters, AsyncListener<CustomerProfile> listener) {
        if (SessionManager.getInstance().getToken() == null || !SessionManager.getInstance().isTokenAuthenticated()) {
            signIn(parameters, listener);
        } else {
            refreshToken(parameters.getUserName(), listener);
        }
        return null;
    }

    private void signIn(final AuthenticationParameters parameters, final AsyncListener<CustomerProfile> listener) {
        DCSAuthenticationRequest request;
        if (parameters.isUsingSocialLogin()) {
            request = new DCSSocialAuthenticationRequest(parameters.getUserName(), "email", parameters.getSocialAuthenticationToken(), parameters.getSocialProvider());
        } else {
            request = new DCSAuthenticationRequest(parameters.getUserName(), "email", parameters.getPassword());
        }
        this.mSharedData.getNetworkConnection().processRequest(request, new AsyncListener<DCSAuthenticationResponse>() {
            public void onResponse(final DCSAuthenticationResponse authenticationResponse, AsyncToken token, AsyncException exception) {
                if (authenticationResponse == null) {
                    listener.onResponse(null, token, exception);
                } else if (authenticationResponse.getStatusCode() == MWDCSConnectorHelper.STATUS_CODE_SUCCESS) {
                    CustomerProfile profile = ((DCSAuthenticationDetails) authenticationResponse.getDetails()).profile.toCustomerProfile();
                    if (profile != null) {
                        profile.setPassword(parameters.getPassword());
                    }
                    MWDCSConnectorHelper.this.addCardInfoToCustomerProfile(profile, new AsyncListener<CustomerProfile>() {
                        public void onResponse(CustomerProfile profileWithCards, AsyncToken token, AsyncException exception) {
                            MWDCSConnectorHelper.this.mDCSProfile = ((DCSAuthenticationDetails) authenticationResponse.getDetails()).profile;
                            MWDCSConnectorHelper.this.setUserLoggedIn(authenticationResponse);
                            listener.onResponse(profileWithCards, token, exception);
                        }
                    });
                } else {
                    listener.onResponse(null, token, MWException.fromErrorCode(authenticationResponse.getStatusCode()));
                }
            }
        });
    }

    private void refreshToken(final String username, final AsyncListener<CustomerProfile> listener) {
        this.mSharedData.getNetworkConnection().processRequest(new DCSRefreshTokenRequest(SessionManager.getInstance().getRefreshToken()), new AsyncListener<DCSAuthenticationResponse>() {
            public void onResponse(DCSAuthenticationResponse response, AsyncToken token, AsyncException exception) {
                if (response == null) {
                    SessionManager.getInstance().clearToken();
                    listener.onResponse(null, token, exception);
                } else if (response.getStatusCode() == MWDCSConnectorHelper.STATUS_CODE_SUCCESS) {
                    MWDCSConnectorHelper.this.setUserLoggedIn(response);
                    MWDCSConnectorHelper.this.getCustomerData(username, listener);
                } else {
                    SessionManager.getInstance().clearToken();
                    listener.onResponse(null, token, MWException.fromErrorCode(response.getStatusCode()));
                }
            }
        });
    }

    private void setUserLoggedIn(DCSAuthenticationResponse response) {
        SessionManager.getInstance().setRefreshing(false);
        SessionManager.getInstance().setToken(((DCSAuthenticationDetails) response.getDetails()).token);
        SessionManager.getInstance().setRefreshToken(((DCSAuthenticationDetails) response.getDetails()).refreshToken);
        SessionManager.getInstance().setTokenAuthenticated(true);
        SessionManager.getInstance().notifyTokenRefreshed();
        this.mSharedData.retrieveAppParameters(((DCSAuthenticationDetails) response.getDetails()).token);
    }

    public AsyncToken signOut(final AsyncListener listener) {
        this.mSharedData.setAppParams(null);
        this.mSharedData.getNetworkConnection().processRequest(new DCSSignOutRequest(SessionManager.getInstance().getRefreshToken()), new AsyncListener() {
            public void onResponse(Object response, AsyncToken token, AsyncException exception) {
                MWDCSConnectorHelper.this.mSharedData.setAppParams(null);
                MWDCSConnectorHelper.this.mDCSProfile = null;
                listener.onResponse(response, token, null);
            }
        });
        return null;
    }

    public AsyncToken register(CustomerProfile customer, final AsyncListener<CustomerProfile> listener) {
        DCSRequest dcsRequest;
        final DCSProfile dcsProfile = DCSProfile.fromCustomerProfile(customer);
        if (customer.isUsingSocialLogin()) {
            dcsRequest = new DCSSocialRegistrationRequest(customer.getUserName(), "email", customer.getSocialAuthenticationToken(), customer.getSocialProvider(), dcsProfile);
        } else {
            dcsRequest = new DCSRegistrationRequest(customer.getUserName(), "email", customer.getPassword(), dcsProfile);
        }
        this.mSharedData.getNetworkConnection().processRequest(dcsRequest, new AsyncListener<DCSRegistrationResponse>() {
            public void onResponse(DCSRegistrationResponse response, AsyncToken token, AsyncException exception) {
                if (response == null) {
                    listener.onResponse(null, token, exception);
                } else if (response.getStatusCode() == MWDCSConnectorHelper.STATUS_CODE_SUCCESS) {
                    MWDCSConnectorHelper.this.mDCSProfile = dcsProfile;
                    listener.onResponse(MWDCSConnectorHelper.this.mDCSProfile.toCustomerProfile(), token, exception);
                } else {
                    listener.onResponse(null, token, MWException.fromErrorCode(response.getStatusCode()));
                }
            }
        });
        return null;
    }

    public AsyncToken updateProfile(final CustomerProfile customer, final AsyncListener<CustomerProfile> listener) {
        if (customer == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
        } else {
            updateProfile(DCSProfile.difference(this.mDCSProfile, customer), new AsyncListener<DCSResponse>() {
                public void onResponse(DCSResponse response, AsyncToken token, AsyncException exception) {
                    if (response == null) {
                        listener.onResponse(null, token, exception);
                    } else if (response.getStatusCode() == MWDCSConnectorHelper.STATUS_CODE_SUCCESS) {
                        MWDCSConnectorHelper.this.getCustomerData(customer.getUserName(), listener);
                    } else {
                        listener.onResponse(null, token, MWException.fromErrorCode(response.getStatusCode()));
                    }
                }
            });
        }
        return null;
    }

    private void updateProfile(DCSProfile newDCSProfile, AsyncListener<DCSResponse> responseAsyncListener) {
        this.mSharedData.getNetworkConnection().processRequest(new DCSUpdateProfileRequest(newDCSProfile), responseAsyncListener);
    }

    public AsyncToken changePassword(String username, String oldPassword, String newPassword, String authorizationCode, final AsyncListener<Void> listener) {
        if (username == null || oldPassword == null || newPassword == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
        } else {
            this.mSharedData.getNetworkConnection().processRequest(new DCSChangePasswordRequest(username, oldPassword, newPassword, newPassword), new AsyncListener<DCSChangePasswordResponse>() {
                public void onResponse(DCSChangePasswordResponse response, AsyncToken token, AsyncException exception) {
                    if (response.getStatusCode() == MWDCSConnectorHelper.STATUS_CODE_SUCCESS) {
                        listener.onResponse(null, token, null);
                        return;
                    }
                    listener.onResponse(null, token, MWException.fromErrorCode(response.getStatusCode()));
                }
            });
        }
        return null;
    }

    public AsyncToken sendRating(String username, int rating, AsyncListener<Boolean> listener) {
        return sendRating(username, "", rating, listener);
    }

    public AsyncToken sendRating(String username, String comment, int rating, final AsyncListener<Boolean> listener) {
        int feedbackTypeId = 5;
        FeedBackType feedBackType = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getFeedBackTypeForRating();
        if (feedBackType != null) {
            feedbackTypeId = feedBackType.getID();
        }
        DCSProfile feedbackProfile = new DCSFeedbackProfile();
        feedbackProfile.comment.feedback.feedbackType = String.valueOf(feedbackTypeId);
        feedbackProfile.comment.feedback.feedbackRating = String.valueOf(rating);
        feedbackProfile.comment.feedback.userComment = comment;
        updateProfile(feedbackProfile, new AsyncListener<DCSResponse>() {
            public void onResponse(DCSResponse response, AsyncToken token, AsyncException exception) {
                if (response.getStatusCode() == MWDCSConnectorHelper.STATUS_CODE_SUCCESS) {
                    listener.onResponse(null, token, null);
                    return;
                }
                listener.onResponse(null, token, MWException.fromErrorCode(response.getStatusCode()));
            }
        });
        return null;
    }

    public AsyncToken setNotificationPreferences(CustomerProfile customer, NotificationPreferences preferences, final AsyncListener<NotificationPreferences> listener) {
        customer.setNotificationPreferences(preferences);
        updateProfile(customer, new AsyncListener<CustomerProfile>() {
            public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                if (response != null) {
                    listener.onResponse(response.getNotificationPreferences(), token, exception);
                } else {
                    listener.onResponse(null, token, exception);
                }
            }
        });
        return null;
    }

    public AsyncToken resetPassword(String username, final AsyncListener<Void> listener) {
        if (username == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
        } else {
            this.mSharedData.getNetworkConnection().processRequest(new DCSResetPasswordRequest(username), new AsyncListener<DCSResponse>() {
                public void onResponse(DCSResponse response, AsyncToken token, AsyncException exception) {
                    if (response == null) {
                        listener.onResponse(null, token, exception);
                    } else if (response.getStatusCode() == MWDCSConnectorHelper.STATUS_CODE_SUCCESS) {
                        listener.onResponse(null, token, null);
                    } else {
                        listener.onResponse(null, token, MWException.fromErrorCode(response.getStatusCode()));
                    }
                }
            });
        }
        return null;
    }

    public AsyncToken getFavoriteProducts(String userName, final AsyncListener<List<FavoriteItem>> listener) {
        if (this.mDCSProfile == null) {
            fetchCustomerData(userName, new AsyncListener<DCSProfile>() {
                public void onResponse(DCSProfile response, AsyncToken token, AsyncException exception) {
                    if (exception == null) {
                        listener.onResponse(MWDCSConnectorHelper.this.mDCSProfile.toCustomerProfile().getFavoriteItems(), null, null);
                        return;
                    }
                    listener.onResponse(null, token, exception);
                }
            });
        } else {
            listener.onResponse(this.mDCSProfile.toCustomerProfile().getFavoriteItems(), null, null);
        }
        return null;
    }

    public AsyncToken addFavoriteProducts(String username, String favoriteName, List<OrderProduct> products, Boolean isProduct, final AsyncListener<List<FavoriteItem>> listener) {
        DCSFavorite dcsFavorite = null;
        if (this.mDCSProfile.extended != null && this.mDCSProfile.extended.favorites != null) {
            for (DCSFavorite favorite : this.mDCSProfile.extended.favorites) {
                if ((favorite.type.equals(DCSFavorite.TYPE_ITEM) && isProduct.booleanValue()) || (favorite.type.equals(DCSFavorite.TYPE_ORDER) && !isProduct.booleanValue())) {
                    dcsFavorite = favorite;
                    break;
                }
            }
        }
        if (dcsFavorite == null) {
            dcsFavorite = new DCSFavorite();
            dcsFavorite.type = isProduct.booleanValue() ? DCSFavorite.TYPE_ITEM : DCSFavorite.TYPE_ORDER;
            dcsFavorite.favoriteId = isProduct.booleanValue() ? "2" : "1";
            if (this.mDCSProfile.extended == null) {
                this.mDCSProfile.extended = new DCSProfileExtended();
            }
            if (this.mDCSProfile.extended.favorites == null) {
                this.mDCSProfile.extended.favorites = new ArrayList();
            }
            this.mDCSProfile.extended.favorites.add(dcsFavorite);
        }
        if (dcsFavorite.details == null) {
            dcsFavorite.details = new DCSFavoriteDetailsList();
        }
        DCSFavoriteDetails details = new DCSFavoriteDetails();
        details.name = favoriteName;
        details.data = new ArrayList();
        for (OrderProduct orderProduct : products) {
            details.data.add(DCSProduct.fromOrderProduct(orderProduct));
        }
        dcsFavorite.details.add(details);
        DCSProfile newProfile = new DCSProfile();
        newProfile.extended = new DCSProfileExtended();
        newProfile.extended.favorites = this.mDCSProfile.extended.favorites;
        processUpdateProfileRequest(new DCSUpdateProfileAddingRequest(newProfile), new AsyncListener<DCSProfile>() {
            public void onResponse(DCSProfile response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    listener.onResponse(MWDCSConnectorHelper.this.mDCSProfile.toCustomerProfile().getFavoriteItems(), null, null);
                    return;
                }
                listener.onResponse(null, token, exception);
            }
        });
        return null;
    }

    public AsyncToken deleteFavoriteProducts(String username, List<FavoriteItem> favoriteItems, final AsyncListener<List<FavoriteItem>> listener) {
        if (this.mDCSProfile.extended == null || this.mDCSProfile.extended.favorites == null) {
            listener.onResponse(null, null, new AsyncException());
        } else {
            List<DCSFavorite> dcsFavorites = new ArrayList(this.mDCSProfile.extended.favorites);
            List<DCSFavoriteDetails> favoriteDetailsToRemove = new ArrayList(favoriteItems.size());
            for (FavoriteItem item : favoriteItems) {
                favoriteDetailsToRemove.add(DCSFavoriteDetails.fromFavoriteItem(item));
            }
            for (DCSFavorite favorite : dcsFavorites) {
                if (!favorite.type.equals(DCSFavorite.TYPE_LOCATION)) {
                    favorite.details.removeAll(favoriteDetailsToRemove);
                }
            }
            DCSProfile newProfile = new DCSProfile();
            newProfile.extended = new DCSProfileExtended();
            newProfile.extended.favorites = dcsFavorites;
            processUpdateProfileRequest(new DCSUpdateProfileRequest(newProfile), new AsyncListener<DCSProfile>() {
                public void onResponse(DCSProfile response, AsyncToken token, AsyncException exception) {
                    if (exception == null) {
                        listener.onResponse(MWDCSConnectorHelper.this.mDCSProfile.toCustomerProfile().getFavoriteItems(), null, null);
                        return;
                    }
                    listener.onResponse(null, token, exception);
                }
            });
        }
        return null;
    }

    public AsyncToken addFavoriteLocations(List<Store> favoriteLocations, String username, final AsyncListener<List<Store>> listener) {
        DCSFavorite dcsFavorite = null;
        if (this.mDCSProfile.extended != null && this.mDCSProfile.extended.favorites != null) {
            for (DCSFavorite favorite : this.mDCSProfile.extended.favorites) {
                if (favorite.type.equals(DCSFavorite.TYPE_LOCATION)) {
                    dcsFavorite = favorite;
                    break;
                }
            }
        }
        if (dcsFavorite == null) {
            dcsFavorite = new DCSFavorite();
            dcsFavorite.type = DCSFavorite.TYPE_LOCATION;
            dcsFavorite.favoriteId = "3";
            if (this.mDCSProfile.extended == null) {
                this.mDCSProfile.extended = new DCSProfileExtended();
            }
            if (this.mDCSProfile.extended.favorites == null) {
                this.mDCSProfile.extended.favorites = new ArrayList();
            }
            this.mDCSProfile.extended.favorites.add(dcsFavorite);
        }
        if (dcsFavorite.details == null) {
            dcsFavorite.details = new DCSFavoriteDetailsList();
        }
        for (Store favoriteStore : favoriteLocations) {
            DCSFavoriteDetails details = new DCSFavoriteDetails();
            details.name = favoriteStore.getStoreFavoriteName();
            details.storeNumber = favoriteStore.getStoreId();
            dcsFavorite.details.add(details);
        }
        DCSProfile newProfile = new DCSProfile();
        newProfile.extended = new DCSProfileExtended();
        newProfile.extended.favorites = this.mDCSProfile.extended.favorites;
        processUpdateProfileRequest(new DCSUpdateProfileAddingRequest(newProfile), new AsyncListener<DCSProfile>() {
            public void onResponse(DCSProfile response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    MWDCSConnectorHelper.this.dispatchFavoriteStores(listener);
                } else {
                    listener.onResponse(null, token, exception);
                }
            }
        });
        return null;
    }

    public AsyncToken deleteFavoriteLocations(List<Integer> favoriteLocationIds, String username, final AsyncListener<List<Store>> listener) {
        if (this.mDCSProfile.extended == null || this.mDCSProfile.extended.favorites == null) {
            listener.onResponse(null, null, new AsyncException());
        } else {
            DCSFavorite dcsFavorite = null;
            List<DCSFavorite> dcsFavorites = new ArrayList(this.mDCSProfile.extended.favorites);
            for (DCSFavorite favorite : dcsFavorites) {
                if (favorite.type.equals(DCSFavorite.TYPE_LOCATION)) {
                    dcsFavorite = favorite;
                    break;
                }
            }
            if (dcsFavorite == null) {
                listener.onResponse(null, null, new AsyncException());
                return null;
            }
            DCSFavoriteDetailsList newDetailsList = new DCSFavoriteDetailsList();
            newDetailsList.addAll(dcsFavorite.details);
            for (Integer favoriteLocationId : favoriteLocationIds) {
                Iterator it = dcsFavorite.details.iterator();
                while (it.hasNext()) {
                    DCSFavoriteDetails details = (DCSFavoriteDetails) it.next();
                    if (details.storeNumber == favoriteLocationId.intValue()) {
                        newDetailsList.remove(details);
                    }
                }
            }
            dcsFavorite.details = newDetailsList;
            DCSProfile newProfile = new DCSProfile();
            newProfile.extended = new DCSProfileExtended();
            newProfile.extended.favorites = dcsFavorites;
            processUpdateProfileRequest(new DCSUpdateProfileRequest(newProfile), new AsyncListener<DCSProfile>() {
                public void onResponse(DCSProfile response, AsyncToken token, AsyncException exception) {
                    if (exception == null) {
                        MWDCSConnectorHelper.this.dispatchFavoriteStores(listener);
                    } else {
                        listener.onResponse(null, token, exception);
                    }
                }
            });
        }
        return null;
    }

    public AsyncToken retrieveFavoriteStores(String username, final AsyncListener<List<Store>> listener) {
        if (this.mDCSProfile == null) {
            fetchCustomerData(username, new AsyncListener<DCSProfile>() {
                public void onResponse(DCSProfile response, AsyncToken token, AsyncException exception) {
                    if (exception == null) {
                        MWDCSConnectorHelper.this.dispatchFavoriteStores(listener);
                    } else {
                        listener.onResponse(null, token, exception);
                    }
                }
            });
        } else {
            dispatchFavoriteStores(listener);
        }
        return null;
    }

    private void dispatchFavoriteStores(AsyncListener<List<Store>> listener) {
        List<Store> favoriteStores = this.mDCSProfile.toCustomerProfile().getFavoriteStores();
        if (favoriteStores != null) {
            listener.onResponse(favoriteStores, null, null);
        } else {
            listener.onResponse(null, null, new AsyncException("No Favorite Stores"));
        }
    }

    public AsyncToken renameFavoriteLocations(List<Store> favoriteLocations, String username, AsyncListener<List<Store>> listener) {
        return addFavoriteLocations(favoriteLocations, username, listener);
    }

    public AsyncToken getCustomerData(String username, final AsyncListener<CustomerProfile> listener) {
        if (username == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
        } else {
            fetchCustomerData(username, new AsyncListener<DCSProfile>() {
                public void onResponse(DCSProfile response, AsyncToken token, AsyncException exception) {
                    if (exception == null) {
                        MWDCSConnectorHelper.this.addCardInfoToCustomerProfile(MWDCSConnectorHelper.this.mDCSProfile.toCustomerProfile(), listener);
                        return;
                    }
                    listener.onResponse(null, null, exception);
                }
            });
        }
        return null;
    }

    public AsyncToken deregister(CustomerProfile customer, String cancellationReason, final AsyncListener<String> listener) {
        if (customer == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
        } else {
            this.mSharedData.getNetworkConnection().processRequest(new DCSDeregisterRequest(), new AsyncListener<DCSResponse>() {
                public void onResponse(DCSResponse response, AsyncToken token, AsyncException exception) {
                    if (response == null) {
                        listener.onResponse("Fail", token, null);
                    } else if (response.getStatusCode() == MWDCSConnectorHelper.STATUS_CODE_SUCCESS) {
                        listener.onResponse("Pass", token, null);
                    } else {
                        listener.onResponse("Fail", token, MWException.fromErrorCode(response.getStatusCode()));
                    }
                }
            });
        }
        return null;
    }

    private void processUpdateProfileRequest(DCSUpdateProfileRequest request, final AsyncListener<DCSProfile> listener) {
        this.mSharedData.getNetworkConnection().processRequest(request, new AsyncListener<DCSResponse>() {
            public void onResponse(DCSResponse response, AsyncToken token, AsyncException exception) {
                if (response == null) {
                    listener.onResponse(null, token, exception);
                } else if (response.getStatusCode() == MWDCSConnectorHelper.STATUS_CODE_SUCCESS) {
                    MWDCSConnectorHelper.this.fetchCustomerData(MWDCSConnectorHelper.this.mDCSProfile.base.username, listener);
                } else {
                    listener.onResponse(null, token, MWException.fromErrorCode(response.getStatusCode()));
                }
            }
        });
    }

    private void fetchCustomerData(String username, final AsyncListener<DCSProfile> listener) {
        this.mSharedData.getNetworkConnection().processRequest(new DCSGetCustomerDataRequest(username), new AsyncListener<DCSGetProfileResponse>() {
            public void onResponse(DCSGetProfileResponse response, AsyncToken token, AsyncException exception) {
                if (response == null) {
                    listener.onResponse(null, token, exception);
                } else if (response.getStatusCode() == MWDCSConnectorHelper.STATUS_CODE_SUCCESS) {
                    MWDCSConnectorHelper.this.mDCSProfile = ((DCSGetProfileDetails) response.getDetails()).profile;
                    listener.onResponse(((DCSGetProfileDetails) response.getDetails()).profile, null, null);
                } else {
                    listener.onResponse(null, token, MWException.fromErrorCode(response.getStatusCode()));
                }
            }
        });
    }

    private void addCardInfoToCustomerProfile(final CustomerProfile profile, final AsyncListener<CustomerProfile> listener) {
        this.mSharedData.getNetworkConnection().processRequest(new MWGetPaymentWalletRequest(SessionManager.getInstance().getToken()), new AsyncListener<MWGetPaymentWalletResponse>() {
            public void onResponse(MWGetPaymentWalletResponse response, AsyncToken token, AsyncException exception) {
                if (exception != null || response.getData() == null) {
                    listener.onResponse(profile, token, exception);
                    return;
                }
                PaymentWallet wallet = ((MWPaymentWallet) response.getData()).toPaymentWallet();
                profile.setCardItems(wallet.getCardItems());
                profile.setAccountItems(wallet.getAccountItems());
                listener.onResponse(profile, token, null);
            }
        });
    }
}
