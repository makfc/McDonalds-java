package com.mcdonalds.app.social;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.ensighten.Ensighten;
import com.facebook.Session;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.plus.Plus;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.ConnectorManager;
import com.mcdonalds.sdk.connectors.CustomerConnector;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.MWCustomerSecurityConnector;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.SocialLoginAuthenticationResults;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SocialLogin {
    private static GoogleApiClient googleApiClient;
    private final SocialLoginClientCallback mClientCallback;
    private final CustomerModule mCustomerModule = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME));
    private final URLNavigationFragment mFragment;
    private SocialLoginListener mListener;
    private List<SocialNetwork> mSocialNetworks;
    private final SocialLoginViewHolder mViewHolder;

    public interface SocialLoginClientCallback {
    }

    public interface SocialLoginListener {
        void onSocialNetworkAuthenticationComplete(SocialLoginAuthenticationResults socialLoginAuthenticationResults);

        void onSocialNetworkAvailable();

        void onSocialNetworkSelected(SocialNetwork socialNetwork);
    }

    /* renamed from: com.mcdonalds.app.social.SocialLogin$1 */
    class C37001 implements AsyncListener<List<SocialNetwork>> {
        C37001() {
        }

        public void onResponse(List<SocialNetwork> socialNetworks, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{socialNetworks, token, exception});
            if (exception == null) {
                SocialLogin.access$002(SocialLogin.this, new ArrayList());
                for (SocialNetwork socialNetwork : socialNetworks) {
                    if (socialNetwork.isValid()) {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLogin", "access$000", new Object[]{SocialLogin.this}).add(socialNetwork);
                    }
                }
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLogin", "access$100", new Object[]{SocialLogin.this}) != null && ListUtils.isNotEmpty(Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLogin", "access$000", new Object[]{SocialLogin.this}))) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLogin", "access$100", new Object[]{SocialLogin.this}).onSocialNetworkAvailable();
                }
                SocialLogin.access$200(SocialLogin.this);
                return;
            }
            AsyncException.report(exception);
        }
    }

    /* renamed from: com.mcdonalds.app.social.SocialLogin$2 */
    class C37012 implements AsyncListener<List<SocialNetwork>> {
        C37012() {
        }

        public void onResponse(List<SocialNetwork> socialNetworks, AsyncToken asyncToken, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{socialNetworks, asyncToken, exception});
            if (exception == null) {
                SocialLogin.access$002(SocialLogin.this, new ArrayList());
                for (SocialNetwork socialNetwork : socialNetworks) {
                    if (socialNetwork.isValid()) {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLogin", "access$000", new Object[]{SocialLogin.this}).add(socialNetwork);
                    }
                }
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLogin", "access$100", new Object[]{SocialLogin.this}) != null && ListUtils.isNotEmpty(Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLogin", "access$000", new Object[]{SocialLogin.this}))) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLogin", "access$100", new Object[]{SocialLogin.this}).onSocialNetworkAvailable();
                }
                SocialLogin.access$200(SocialLogin.this);
                return;
            }
            AsyncException.report(exception);
        }
    }

    /* renamed from: com.mcdonalds.app.social.SocialLogin$4 */
    static class C37034 implements OnConnectionFailedListener {
        C37034() {
        }

        public void onConnectionFailed(ConnectionResult connectionResult) {
            Ensighten.evaluateEvent(this, "onConnectionFailed", new Object[]{connectionResult});
        }
    }

    /* renamed from: com.mcdonalds.app.social.SocialLogin$5 */
    static class C37045 implements ConnectionCallbacks {
        C37045() {
        }

        public void onConnected(Bundle bundle) {
            Ensighten.evaluateEvent(this, "onConnected", new Object[]{bundle});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLogin", "access$400", null).isConnected()) {
                Plus.AccountApi.clearDefaultAccount(Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLogin", "access$400", null));
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLogin", "access$400", null).disconnect();
                return;
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLogin", "access$400", null).connect();
        }

        public void onConnectionSuspended(int i) {
            Ensighten.evaluateEvent(this, "onConnectionSuspended", new Object[]{new Integer(i)});
        }
    }

    static /* synthetic */ List access$002(SocialLogin x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLogin", "access$002", new Object[]{x0, x1});
        x0.mSocialNetworks = x1;
        return x1;
    }

    static /* synthetic */ void access$200(SocialLogin x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLogin", "access$200", new Object[]{x0});
        x0.refreshViews();
    }

    static /* synthetic */ void access$300(SocialLogin x0, SocialNetwork x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLogin", "access$300", new Object[]{x0, x1});
        x0.onSocialNetworkClicked(x1);
    }

    public SocialLogin(URLNavigationFragment navFragment, SocialLoginViewHolder viewHolder, SocialLoginClientCallback clientCallback) {
        this.mFragment = navFragment;
        this.mViewHolder = viewHolder;
        this.mClientCallback = clientCallback;
    }

    public void setListener(SocialLoginListener listener) {
        Ensighten.evaluateEvent(this, "setListener", new Object[]{listener});
        this.mListener = listener;
    }

    public void refresh() {
        Ensighten.evaluateEvent(this, "refresh", null);
        if (this.mSocialNetworks != null) {
            refreshViews();
        } else if (ConfigurationUtils.isGmaLiteFlow()) {
            ((CustomerConnector) ConnectorManager.getSharedInstance().getConnectorImpl(MWCustomerSecurityConnector.NAME)).getSocialLoginCatalogUpdate(new C37001());
        } else {
            this.mCustomerModule.getSocialLoginCatalog(new C37012());
        }
    }

    private void refreshViews() {
        Ensighten.evaluateEvent(this, "refreshViews", null);
        int[] buttonIds = new int[]{C2358R.C2357id.social_button_1, C2358R.C2357id.social_button_2, C2358R.C2357id.social_button_3};
        if (this.mSocialNetworks != null && !this.mSocialNetworks.isEmpty() && this.mFragment.getNavigationActivity() != null) {
            int i;
            this.mViewHolder.getItemsContainer().removeAllViews();
            int numRows = this.mSocialNetworks.size() / 3;
            if (this.mSocialNetworks.size() % 3 > 0) {
                i = 1;
            } else {
                i = 0;
            }
            numRows += i;
            LayoutInflater inflater = LayoutInflater.from(this.mFragment.getNavigationActivity());
            for (int rowCounter = 0; rowCounter < numRows; rowCounter++) {
                LinearLayout rowContainer = (LinearLayout) inflater.inflate(C2658R.layout.social_login_row, null);
                for (int index = 0; index < 3; index++) {
                    int itemIndex = (rowCounter * 3) + index;
                    ImageView button = (ImageView) rowContainer.findViewById(buttonIds[index]);
                    if (itemIndex < this.mSocialNetworks.size()) {
                        final SocialNetwork network = (SocialNetwork) this.mSocialNetworks.get(itemIndex);
                        button.setImageResource(network.getImageResource());
                        button.setOnClickListener(new OnClickListener() {
                            public void onClick(View v) {
                                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                                SocialLogin.access$300(SocialLogin.this, network);
                                HashMap<String, Object> jiceMap = new HashMap();
                                jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_LOGIN_WECHAT);
                                Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).build());
                            }
                        });
                        button.setVisibility(0);
                    }
                }
                this.mViewHolder.getItemsContainer().addView(rowContainer);
            }
        }
    }

    private void onSocialNetworkClicked(SocialNetwork socialNetwork) {
        Ensighten.evaluateEvent(this, "onSocialNetworkClicked", new Object[]{socialNetwork});
        if (this.mListener != null) {
            this.mListener.onSocialNetworkSelected(socialNetwork);
        }
    }

    public static void clearSocialLogins(Activity activity) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLogin", "clearSocialLogins", new Object[]{activity});
        if (Session.getActiveSession() != null) {
            Session.getActiveSession().closeAndClearTokenInformation();
        }
        if (!Configuration.getSharedInstance().getBooleanForKey("interface.disableFacebookAndGoogle")) {
            googleApiClient = new Builder(activity).addConnectionCallbacks(new C37045()).addOnConnectionFailedListener(new C37034()).addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN).build();
            googleApiClient.connect();
        }
    }
}
