package com.mcdonalds.app.account;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.ensighten.Ensighten;
import com.mcdonalds.app.customer.SignInActivity;

public class Authenticator extends AbstractAccountAuthenticator {
    private Context mContext;

    public Authenticator(Context context) {
        super(context);
        this.mContext = context;
    }

    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        Ensighten.evaluateEvent(this, "editProperties", new Object[]{response, accountType});
        throw new UnsupportedOperationException();
    }

    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
        Ensighten.evaluateEvent(this, "addAccount", new Object[]{response, accountType, authTokenType, requiredFeatures, options});
        Intent intent = new Intent(this.mContext, SignInActivity.class);
        intent.putExtra(SignInActivity.ARG_ACCOUNT_TYPE, accountType);
        intent.putExtra(SignInActivity.ARG_AUTH_TYPE, authTokenType);
        intent.putExtra(SignInActivity.ARG_IS_ADDING_NEW_ACCOUNT, true);
        intent.putExtra("accountAuthenticatorResponse", response);
        Bundle bundle = new Bundle();
        bundle.putParcelable("intent", intent);
        return bundle;
    }

    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        Ensighten.evaluateEvent(this, "confirmCredentials", new Object[]{response, account, options});
        return null;
    }

    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        Ensighten.evaluateEvent(this, "getAuthToken", new Object[]{response, account, authTokenType, options});
        throw new UnsupportedOperationException();
    }

    public String getAuthTokenLabel(String authTokenType) {
        Ensighten.evaluateEvent(this, "getAuthTokenLabel", new Object[]{authTokenType});
        throw new UnsupportedOperationException();
    }

    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        Ensighten.evaluateEvent(this, "updateCredentials", new Object[]{response, account, authTokenType, options});
        throw new UnsupportedOperationException();
    }

    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        Ensighten.evaluateEvent(this, "hasFeatures", new Object[]{response, account, features});
        throw new UnsupportedOperationException();
    }
}
