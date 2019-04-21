package com.facebook;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.Session.AuthorizationRequest;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import com.facebook.model.GraphUser;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestSession extends Session {
    static final /* synthetic */ boolean $assertionsDisabled = (!TestSession.class.desiredAssertionStatus());
    private static final String LOG_TAG = "FacebookSDK.TestSession";
    private static Map<String, TestAccount> appTestAccounts = null;
    private static final long serialVersionUID = 1;
    private static String testApplicationId;
    private static String testApplicationSecret;
    private final Mode mode;
    private final List<String> requestedPermissions;
    private final String sessionUniqueUserTag;
    private String testAccountId;
    private String testAccountUserName;
    private boolean wasAskedToExtendAccessToken;

    private enum Mode {
        PRIVATE,
        SHARED
    }

    private interface TestAccount extends GraphObject {
        String getAccessToken();

        String getId();

        String getName();

        void setName(String str);
    }

    private interface TestAccountsResponse extends GraphObject {
        GraphObjectList<TestAccount> getData();
    }

    private static final class TestTokenCachingStrategy extends TokenCachingStrategy {
        private Bundle bundle;

        private TestTokenCachingStrategy() {
        }

        public Bundle load() {
            return this.bundle;
        }

        public void save(Bundle value) {
            this.bundle = value;
        }

        public void clear() {
            this.bundle = null;
        }
    }

    TestSession(Activity activity, List<String> permissions, TokenCachingStrategy tokenCachingStrategy, String sessionUniqueUserTag, Mode mode) {
        super(activity, testApplicationId, tokenCachingStrategy);
        Validate.notNull(permissions, NativeProtocol.RESULT_ARGS_PERMISSIONS);
        Validate.notNullOrEmpty(testApplicationId, "testApplicationId");
        Validate.notNullOrEmpty(testApplicationSecret, "testApplicationSecret");
        this.sessionUniqueUserTag = sessionUniqueUserTag;
        this.mode = mode;
        this.requestedPermissions = permissions;
    }

    public static TestSession createSessionWithPrivateUser(Activity activity, List<String> permissions) {
        return createTestSession(activity, permissions, Mode.PRIVATE, null);
    }

    public static TestSession createSessionWithSharedUser(Activity activity, List<String> permissions) {
        return createSessionWithSharedUser(activity, permissions, null);
    }

    public static TestSession createSessionWithSharedUser(Activity activity, List<String> permissions, String sessionUniqueUserTag) {
        return createTestSession(activity, permissions, Mode.SHARED, sessionUniqueUserTag);
    }

    public static synchronized String getTestApplicationId() {
        String str;
        synchronized (TestSession.class) {
            str = testApplicationId;
        }
        return str;
    }

    public static synchronized void setTestApplicationId(String applicationId) {
        synchronized (TestSession.class) {
            if (testApplicationId == null || testApplicationId.equals(applicationId)) {
                testApplicationId = applicationId;
            } else {
                throw new FacebookException("Can't have more than one test application ID");
            }
        }
    }

    public static synchronized String getTestApplicationSecret() {
        String str;
        synchronized (TestSession.class) {
            str = testApplicationSecret;
        }
        return str;
    }

    public static synchronized void setTestApplicationSecret(String applicationSecret) {
        synchronized (TestSession.class) {
            if (testApplicationSecret == null || testApplicationSecret.equals(applicationSecret)) {
                testApplicationSecret = applicationSecret;
            } else {
                throw new FacebookException("Can't have more than one test application secret");
            }
        }
    }

    public final String getTestUserId() {
        return this.testAccountId;
    }

    public final String getTestUserName() {
        return this.testAccountUserName;
    }

    private static synchronized TestSession createTestSession(Activity activity, List<String> permissions, Mode mode, String sessionUniqueUserTag) {
        TestSession testSession;
        synchronized (TestSession.class) {
            if (Utility.isNullOrEmpty(testApplicationId) || Utility.isNullOrEmpty(testApplicationSecret)) {
                throw new FacebookException("Must provide app ID and secret");
            }
            if (Utility.isNullOrEmpty((Collection) permissions)) {
                permissions = Arrays.asList(new String[]{"email", "publish_actions"});
            }
            testSession = new TestSession(activity, permissions, new TestTokenCachingStrategy(), sessionUniqueUserTag, mode);
        }
        return testSession;
    }

    private static synchronized void retrieveTestAccountsForAppIfNeeded() {
        synchronized (TestSession.class) {
            if (appTestAccounts == null) {
                appTestAccounts = new HashMap();
                Request.setDefaultBatchApplicationId(testApplicationId);
                Bundle parameters = new Bundle();
                parameters.putString("access_token", getAppAccessToken());
                Request requestTestUsers = new Request(null, "app/accounts/test-users", parameters, null);
                requestTestUsers.setBatchEntryName("testUsers");
                requestTestUsers.setBatchEntryOmitResultOnSuccess(false);
                Bundle testUserNamesParam = new Bundle();
                testUserNamesParam.putString("access_token", getAppAccessToken());
                testUserNamesParam.putString("ids", "{result=testUsers:$.data.*.id}");
                testUserNamesParam.putString("fields", "name");
                new Request(null, "", testUserNamesParam, null).setBatchEntryDependsOn("testUsers");
                List<Response> responses = Request.executeBatchAndWait(requestTestUsers, requestTestUserNames);
                if (responses == null || responses.size() != 2) {
                    throw new FacebookException("Unexpected number of results from TestUsers batch query");
                }
                populateTestAccounts(((TestAccountsResponse) ((Response) responses.get(0)).getGraphObjectAs(TestAccountsResponse.class)).getData(), ((Response) responses.get(1)).getGraphObject());
            }
        }
    }

    private static synchronized void populateTestAccounts(Collection<TestAccount> testAccounts, GraphObject userAccountsMap) {
        synchronized (TestSession.class) {
            for (TestAccount testAccount : testAccounts) {
                testAccount.setName(((GraphUser) userAccountsMap.getPropertyAs(testAccount.getId(), GraphUser.class)).getName());
                storeTestAccount(testAccount);
            }
        }
    }

    private static synchronized void storeTestAccount(TestAccount testAccount) {
        synchronized (TestSession.class) {
            appTestAccounts.put(testAccount.getId(), testAccount);
        }
    }

    private static synchronized TestAccount findTestAccountMatchingIdentifier(String identifier) {
        TestAccount testAccount;
        synchronized (TestSession.class) {
            retrieveTestAccountsForAppIfNeeded();
            for (TestAccount testAccount2 : appTestAccounts.values()) {
                if (testAccount2.getName().contains(identifier)) {
                    break;
                }
            }
            testAccount2 = null;
        }
        return testAccount2;
    }

    public final String toString() {
        return "{TestSession" + " testUserId:" + this.testAccountId + " " + super.toString() + "}";
    }

    /* Access modifiers changed, original: 0000 */
    public void authorize(AuthorizationRequest request) {
        if (this.mode == Mode.PRIVATE) {
            createTestAccountAndFinishAuth();
        } else {
            findOrCreateSharedTestAccount();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void postStateChange(SessionState oldState, SessionState newState, Exception error) {
        String id = this.testAccountId;
        super.postStateChange(oldState, newState, error);
        if (newState.isClosed() && id != null && this.mode == Mode.PRIVATE) {
            deleteTestAccount(id, getAppAccessToken());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean getWasAskedToExtendAccessToken() {
        return this.wasAskedToExtendAccessToken;
    }

    /* Access modifiers changed, original: 0000 */
    public void forceExtendAccessToken(boolean forceExtendAccessToken) {
        AccessToken currentToken = getTokenInfo();
        setTokenInfo(new AccessToken(currentToken.getToken(), new Date(), currentToken.getPermissions(), currentToken.getDeclinedPermissions(), AccessTokenSource.TEST_USER, new Date(0)));
        setLastAttemptedTokenExtendDate(new Date(0));
    }

    /* Access modifiers changed, original: 0000 */
    public boolean shouldExtendAccessToken() {
        boolean result = super.shouldExtendAccessToken();
        this.wasAskedToExtendAccessToken = false;
        return result;
    }

    /* Access modifiers changed, original: 0000 */
    public void extendAccessToken() {
        this.wasAskedToExtendAccessToken = true;
        super.extendAccessToken();
    }

    /* Access modifiers changed, original: 0000 */
    public void fakeTokenRefreshAttempt() {
        setCurrentTokenRefreshRequest(new TokenRefreshRequest());
    }

    static final String getAppAccessToken() {
        return testApplicationId + "|" + testApplicationSecret;
    }

    private void findOrCreateSharedTestAccount() {
        TestAccount testAccount = findTestAccountMatchingIdentifier(getSharedTestAccountIdentifier());
        if (testAccount != null) {
            finishAuthWithTestAccount(testAccount);
        } else {
            createTestAccountAndFinishAuth();
        }
    }

    private void finishAuthWithTestAccount(TestAccount testAccount) {
        this.testAccountId = testAccount.getId();
        this.testAccountUserName = testAccount.getName();
        finishAuthOrReauth(AccessToken.createFromString(testAccount.getAccessToken(), this.requestedPermissions, AccessTokenSource.TEST_USER), null);
    }

    private TestAccount createTestAccountAndFinishAuth() {
        Bundle parameters = new Bundle();
        parameters.putString("installed", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        parameters.putString(NativeProtocol.RESULT_ARGS_PERMISSIONS, getPermissionsString());
        parameters.putString("access_token", getAppAccessToken());
        if (this.mode == Mode.SHARED) {
            parameters.putString("name", String.format("Shared %s Testuser", new Object[]{getSharedTestAccountIdentifier()}));
        }
        Response response = new Request(null, String.format("%s/accounts/test-users", new Object[]{testApplicationId}), parameters, HttpMethod.POST).executeAndWait();
        FacebookRequestError error = response.getError();
        TestAccount testAccount = (TestAccount) response.getGraphObjectAs(TestAccount.class);
        if (error != null) {
            finishAuthOrReauth(null, error.getException());
            return null;
        } else if ($assertionsDisabled || testAccount != null) {
            if (this.mode == Mode.SHARED) {
                testAccount.setName(parameters.getString("name"));
                storeTestAccount(testAccount);
            }
            finishAuthWithTestAccount(testAccount);
            return testAccount;
        } else {
            throw new AssertionError();
        }
    }

    private void deleteTestAccount(String testAccountId, String appAccessToken) {
        Bundle parameters = new Bundle();
        parameters.putString("access_token", appAccessToken);
        Response response = new Request(null, testAccountId, parameters, HttpMethod.DELETE).executeAndWait();
        FacebookRequestError error = response.getError();
        GraphObject graphObject = response.getGraphObject();
        if (error != null) {
            Log.w(LOG_TAG, String.format("Could not delete test account %s: %s", new Object[]{testAccountId, error.getException().toString()}));
        } else if (graphObject.getProperty(Response.NON_JSON_RESPONSE_PROPERTY) == Boolean.valueOf(false) || graphObject.getProperty(Response.SUCCESS_KEY) == Boolean.valueOf(false)) {
            Log.w(LOG_TAG, String.format("Could not delete test account %s: unknown reason", new Object[]{testAccountId}));
        }
    }

    private String getPermissionsString() {
        return TextUtils.join(",", this.requestedPermissions);
    }

    private String getSharedTestAccountIdentifier() {
        return validNameStringFromInteger((((long) getPermissionsString().hashCode()) & 4294967295L) ^ (this.sessionUniqueUserTag != null ? ((long) this.sessionUniqueUserTag.hashCode()) & 4294967295L : 0));
    }

    private String validNameStringFromInteger(long i) {
        String s = Long.toString(i);
        StringBuilder result = new StringBuilder("Perm");
        char lastChar = 0;
        for (char c : s.toCharArray()) {
            char c2;
            if (c2 == lastChar) {
                c2 = (char) (c2 + 10);
            }
            result.append((char) ((c2 + 97) - 48));
            lastChar = c2;
        }
        return result.toString();
    }
}
