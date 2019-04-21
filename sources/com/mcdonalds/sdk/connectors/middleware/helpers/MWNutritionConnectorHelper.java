package com.mcdonalds.sdk.connectors.middleware.helpers;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.facebook.internal.ServerProtocol;
import com.facebook.stetho.common.Utf8Charset;
import com.google.gson.reflect.TypeToken;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.connectors.NutritionConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWCarouselImage;
import com.mcdonalds.sdk.connectors.middleware.model.MWCategoryDetailsCategory;
import com.mcdonalds.sdk.connectors.middleware.model.MWGetItemByExternalIdResponse;
import com.mcdonalds.sdk.connectors.middleware.model.MWItemHeroImage;
import com.mcdonalds.sdk.connectors.middleware.model.MWItemImageBase;
import com.mcdonalds.sdk.connectors.middleware.model.MWItemThumbnailImage;
import com.mcdonalds.sdk.connectors.middleware.model.MWMenuItem;
import com.mcdonalds.sdk.connectors.middleware.model.MWMenuItemRelatedItem;
import com.mcdonalds.sdk.connectors.middleware.model.MWMenuItemRelatedItems;
import com.mcdonalds.sdk.connectors.middleware.model.MWMenuItemRelationType;
import com.mcdonalds.sdk.connectors.middleware.model.MWMenuItemRelationTypes;
import com.mcdonalds.sdk.connectors.middleware.model.MWNutritionCategory;
import com.mcdonalds.sdk.connectors.middleware.model.MWNutritionGetAllCategoriesResponse;
import com.mcdonalds.sdk.connectors.middleware.model.MWNutritionGetCategoryDetailsResponse;
import com.mcdonalds.sdk.connectors.middleware.model.MWRecipeForIdItem;
import com.mcdonalds.sdk.connectors.middleware.request.MWNutritionGetAllCategoriesRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWNutritionGetAllItemsRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWNutritionGetCategoryDetailsRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWNutritionGetItemDetailsRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWNutritionGetItemListOnExternalIDRequest;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetAllRecipesResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetRecipeForIdResponse;
import com.mcdonalds.sdk.connectors.utils.Utils;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.NutritionRecipe;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.mcdonalds.sdk.utils.ListUtils;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MWNutritionConnectorHelper implements NutritionConnector {
    public static final String CORE_KEY = "Core";
    public static final String CURRENT_LANGUAGE = "CURRENT_LANGUAGE";
    private static final long DEFAULT_CACHE_DAY_COUNT = 7;
    public static final String MW_CATEGORY_RECIPE_RESPONSE_KEY = "MW_CATEGORY_RECIPE_RESPONSE_KEY";
    public static final String MW_CATEGORY_RESPONSE_KEY = "MW_CATEGORY_RESPONSE_KEY";
    public static final String MW_RECIPES_KEY = "MW_ALL_RECIPES";
    public static final String MW_RECIPE_EXTERNAL_MAP_KEY = "MW_RECIPE_EXTERNAL_MAP_KEY";
    public static final String MW_RECIPE_MAP_KEY = "MW_RECIPE_MAP_KEY";
    private static final String REFRESH_CACHE_DAY_COUNT_KEY = "modules.nutritionInfo.refreshCacheDayCount";
    private MWGetAllRecipesResponse mAllRecipesResponseCache;
    private String mBaseImagePath;
    private boolean mCatalogOperationInProgress = false;
    private Context mContext;
    private List<Runnable> mDelayedTasks = new ArrayList();
    private Map<String, MWMenuItem> mExternalMenuItemCache;
    private Handler mHandler;
    private Map<String, MWMenuItem> mMenuItemCache;
    private MWConnectorShared mSharedData;
    private Type mapStringMWMenuItemType = new C39802().getType();
    private Type mwGetAllRecipesResponseType = new C39781().getType();

    /* renamed from: com.mcdonalds.sdk.connectors.middleware.helpers.MWNutritionConnectorHelper$14 */
    class C397014 extends TypeToken<MWNutritionGetAllCategoriesResponse> {
        C397014() {
        }
    }

    /* renamed from: com.mcdonalds.sdk.connectors.middleware.helpers.MWNutritionConnectorHelper$15 */
    class C397115 extends TypeToken<String> {
        C397115() {
        }
    }

    /* renamed from: com.mcdonalds.sdk.connectors.middleware.helpers.MWNutritionConnectorHelper$1 */
    class C39781 extends TypeToken<MWGetAllRecipesResponse> {
        C39781() {
        }
    }

    /* renamed from: com.mcdonalds.sdk.connectors.middleware.helpers.MWNutritionConnectorHelper$20 */
    class C397920 implements Runnable {
        C397920() {
        }

        public void run() {
            while (MWNutritionConnectorHelper.this.mDelayedTasks.size() > 0) {
                List<Runnable> tasks = new ArrayList(MWNutritionConnectorHelper.this.mDelayedTasks);
                for (Runnable task : tasks) {
                    task.run();
                }
                MWNutritionConnectorHelper.this.mDelayedTasks.removeAll(tasks);
            }
            MWNutritionConnectorHelper.this.mCatalogOperationInProgress = false;
        }
    }

    /* renamed from: com.mcdonalds.sdk.connectors.middleware.helpers.MWNutritionConnectorHelper$2 */
    class C39802 extends TypeToken<Map<String, MWMenuItem>> {
        C39802() {
        }
    }

    /* renamed from: com.mcdonalds.sdk.connectors.middleware.helpers.MWNutritionConnectorHelper$9 */
    class C39909 extends TypeToken<MWNutritionGetCategoryDetailsResponse> {
        C39909() {
        }
    }

    class GetAllRecipesFromCacheTasks extends AsyncTask<Void, Void, List<NutritionRecipe>> implements TraceFieldInterface {
        public Trace _nr_trace;
        private AsyncListener<List<NutritionRecipe>> mListener;
        private AsyncToken mToken;

        /* renamed from: com.mcdonalds.sdk.connectors.middleware.helpers.MWNutritionConnectorHelper$GetAllRecipesFromCacheTasks$1 */
        class C39911 extends TypeToken<MWGetAllRecipesResponse> {
            C39911() {
            }
        }

        public void _nr_setTrace(Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        public GetAllRecipesFromCacheTasks(AsyncListener<List<NutritionRecipe>> listener, AsyncToken token) {
            this.mListener = listener;
            this.mToken = token;
        }

        /* Access modifiers changed, original: protected|varargs */
        public List<NutritionRecipe> doInBackground(Void... params) {
            MWGetAllRecipesResponse cacheResponse = (MWGetAllRecipesResponse) LocalDataManager.getSharedInstance().getObjectFromCache(MWNutritionConnectorHelper.MW_RECIPES_KEY, new C39911().getType());
            if (cacheResponse != null) {
                return MWNutritionConnectorHelper.this.processDepMenuItems(cacheResponse);
            }
            return null;
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(List<NutritionRecipe> recipes) {
            super.onPostExecute(recipes);
            this.mListener.onResponse(recipes, this.mToken, null);
        }
    }

    public void setBaseImagePath(String mBaseImagePath) {
        this.mBaseImagePath = mBaseImagePath;
    }

    public MWNutritionConnectorHelper(MWConnectorShared sharedData, Context context) {
        this.mSharedData = sharedData;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mContext = context;
    }

    public String getFullImagePath(String itemName) {
        String imageName = "";
        if (!TextUtils.isEmpty(itemName)) {
            if (itemName.contains("/")) {
                String[] parsingImageName = itemName.split("/");
                for (int i = 0; i < parsingImageName.length; i++) {
                    try {
                        imageName = imageName + URLEncoder.encode(parsingImageName[i].trim(), Utf8Charset.NAME);
                    } catch (UnsupportedEncodingException uee) {
                        MCDLog.error("MWNutritionConnectorHelper", uee.getMessage());
                        uee.printStackTrace();
                    }
                    if (i != parsingImageName.length - 1) {
                        imageName = imageName + "/";
                    }
                }
            } else {
                try {
                    imageName = URLEncoder.encode(itemName, Utf8Charset.NAME);
                } catch (UnsupportedEncodingException uee2) {
                    MCDLog.error("MWNutritionConnectorHelper", uee2.getMessage());
                    uee2.printStackTrace();
                }
            }
        }
        return this.mBaseImagePath + imageName;
    }

    public void getRecipeForId(final String itemId, final AsyncListener<NutritionRecipe> listener) {
        runRecipeTask(new Runnable() {
            public void run() {
                boolean fetchFromNetwork;
                MWMenuItem mwMenuItem = null;
                if (MWNutritionConnectorHelper.this.mMenuItemCache == null) {
                    fetchFromNetwork = true;
                } else {
                    mwMenuItem = (MWMenuItem) MWNutritionConnectorHelper.this.mMenuItemCache.get(itemId);
                    if (mwMenuItem == null || mwMenuItem.recipeDetailedInfo == null || mwMenuItem.recipeDetailedInfo.externalId == null) {
                        fetchFromNetwork = true;
                    } else {
                        MWGetRecipeForIdResponse mwGetRecipeForIdResponse = new MWGetRecipeForIdResponse();
                        mwGetRecipeForIdResponse.setItem(mwMenuItem.recipeDetailedInfo);
                        listener.onResponse(MWNutritionConnectorHelper.this.processRecipeForId(mwGetRecipeForIdResponse), null, null);
                        fetchFromNetwork = false;
                    }
                }
                if (fetchFromNetwork) {
                    final MWMenuItem menuItem = mwMenuItem;
                    MWNutritionConnectorHelper.this.mSharedData.getNetworkConnection().processRequest(new MWNutritionGetItemDetailsRequest(itemId, "0"), new AsyncListener<MWGetRecipeForIdResponse>() {
                        public void onResponse(MWGetRecipeForIdResponse response, AsyncToken token, AsyncException exception) {
                            if (response != null && response.getItem() != null && response.getItem().externalId != null && exception == null) {
                                if (menuItem != null) {
                                    menuItem.recipeDetailedInfo = response.getItem();
                                    MWRecipeForIdItem itemDetails = response.getItem();
                                    itemDetails.carouselImage = menuItem.carouselImage;
                                    itemDetails.itemThumbNailImage = menuItem.itemThumbNailImage;
                                    itemDetails.itemHeroImage = menuItem.itemHeroImage;
                                }
                                listener.onResponse(MWNutritionConnectorHelper.this.processRecipeForId(response), token, null);
                            } else if (Utils.checkConnection(MWNutritionConnectorHelper.this.mContext)) {
                                listener.onResponse(null, token, new AsyncException("No item found with ID " + itemId));
                            } else {
                                listener.onResponse(null, token, new AsyncException(MWNutritionConnectorHelper.this.mContext.getString(C3883R.string.connectionless_error)));
                            }
                        }
                    });
                }
            }
        });
    }

    private void refreshCache() {
        try {
            LocalDataManager.getSharedInstance().updateObjectInCache(MW_RECIPE_MAP_KEY, this.mMenuItemCache);
        } catch (RuntimeException e) {
        }
    }

    public void getRecipeForExternalId(String externalId, AsyncListener<NutritionRecipe> listener) {
        commonRecipeForExternalId(externalId, listener);
    }

    public void getAllRecipes(final AsyncListener<List<NutritionRecipe>> listener) {
        if (this.mMenuItemCache != null || this.mCatalogOperationInProgress) {
            runRecipeTask(new Runnable() {
                public void run() {
                    GetAllRecipesFromCacheTasks getAllRecipesFromCacheTasks = new GetAllRecipesFromCacheTasks(listener, null);
                    Void[] voidArr = new Void[0];
                    if (getAllRecipesFromCacheTasks instanceof AsyncTask) {
                        AsyncTaskInstrumentation.execute(getAllRecipesFromCacheTasks, voidArr);
                    } else {
                        getAllRecipesFromCacheTasks.execute(voidArr);
                    }
                }
            });
            return;
        }
        this.mCatalogOperationInProgress = true;
        final Runnable networkTask = new Runnable() {
            public void run() {
                MWNutritionConnectorHelper.this.getAllRecipesFromNetwork(listener);
            }
        };
        new Thread(new Runnable() {

            /* renamed from: com.mcdonalds.sdk.connectors.middleware.helpers.MWNutritionConnectorHelper$6$1 */
            class C39851 implements Runnable {
                C39851() {
                }

                public void run() {
                    MWNutritionConnectorHelper.this.mCatalogOperationInProgress = false;
                    GetAllRecipesFromCacheTasks getAllRecipesFromCacheTasks = new GetAllRecipesFromCacheTasks(listener, null);
                    Void[] voidArr = new Void[0];
                    if (getAllRecipesFromCacheTasks instanceof AsyncTask) {
                        AsyncTaskInstrumentation.execute(getAllRecipesFromCacheTasks, voidArr);
                    } else {
                        getAllRecipesFromCacheTasks.execute(voidArr);
                    }
                    MWNutritionConnectorHelper.this.runDelayedRecipeTasks();
                }
            }

            public void run() {
                MWNutritionConnectorHelper.this.mAllRecipesResponseCache = (MWGetAllRecipesResponse) LocalDataManager.getSharedInstance().getObjectFromCache(MWNutritionConnectorHelper.MW_RECIPES_KEY, MWNutritionConnectorHelper.this.mwGetAllRecipesResponseType);
                if (MWNutritionConnectorHelper.this.mAllRecipesResponseCache == null) {
                    new Handler(Looper.getMainLooper()).post(networkTask);
                    return;
                }
                MWNutritionConnectorHelper.this.mMenuItemCache = (Map) LocalDataManager.getSharedInstance().getObjectFromCache(MWNutritionConnectorHelper.MW_RECIPE_MAP_KEY, MWNutritionConnectorHelper.this.mapStringMWMenuItemType);
                MWNutritionConnectorHelper.this.mExternalMenuItemCache = (Map) LocalDataManager.getSharedInstance().getObjectFromCache(MWNutritionConnectorHelper.MW_RECIPE_EXTERNAL_MAP_KEY, MWNutritionConnectorHelper.this.mapStringMWMenuItemType);
                new Handler(Looper.getMainLooper()).post(new C39851());
            }
        }).start();
    }

    private void getAllRecipesFromNetwork(final AsyncListener<List<NutritionRecipe>> listener) {
        this.mSharedData.getNetworkConnection().processRequest(new MWNutritionGetAllItemsRequest("1", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE), new AsyncListener<MWGetAllRecipesResponse>() {
            public void onResponse(MWGetAllRecipesResponse response, AsyncToken token, AsyncException exception) {
                MWNutritionConnectorHelper.this.onMWGetAllRecipesResponse(listener, response, token, exception);
            }
        });
    }

    private void onMWGetAllRecipesResponse(final AsyncListener<List<NutritionRecipe>> listener, final MWGetAllRecipesResponse response, final AsyncToken token, AsyncException exception) {
        if (exception != null) {
            GetAllRecipesFromCacheTasks getAllRecipesFromCacheTasks = new GetAllRecipesFromCacheTasks(listener, token);
            Void[] voidArr = new Void[0];
            if (getAllRecipesFromCacheTasks instanceof AsyncTask) {
                AsyncTaskInstrumentation.execute(getAllRecipesFromCacheTasks, voidArr);
            } else {
                getAllRecipesFromCacheTasks.execute(voidArr);
            }
            runDelayedRecipeTasks();
            return;
        }
        new Thread(new Runnable() {
            public void run() {
                List<NutritionRecipe> recipes = null;
                if (response != null && response.hasFullMenuItemList()) {
                    MWNutritionConnectorHelper.this.mMenuItemCache = new HashMap();
                    MWNutritionConnectorHelper.this.mExternalMenuItemCache = new HashMap();
                    recipes = MWNutritionConnectorHelper.this.processDepMenuItems(response);
                    LocalDataManager.getSharedInstance().addObjectToCache(MWNutritionConnectorHelper.MW_RECIPES_KEY, response, MWNutritionConnectorHelper.this.getNutritionCacheExpirationInterval());
                    LocalDataManager.getSharedInstance().addObjectToCache(MWNutritionConnectorHelper.MW_RECIPE_MAP_KEY, MWNutritionConnectorHelper.this.mMenuItemCache, MWNutritionConnectorHelper.this.getNutritionCacheExpirationInterval());
                    LocalDataManager.getSharedInstance().addObjectToCache(MWNutritionConnectorHelper.MW_RECIPE_EXTERNAL_MAP_KEY, MWNutritionConnectorHelper.this.mExternalMenuItemCache, MWNutritionConnectorHelper.this.getNutritionCacheExpirationInterval());
                }
                final List<NutritionRecipe> finalRecipes = recipes;
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        listener.onResponse(finalRecipes, token, null);
                    }
                });
                MWNutritionConnectorHelper.this.mCatalogOperationInProgress = false;
                MWNutritionConnectorHelper.this.runDelayedRecipeTasks();
            }
        }).start();
    }

    public void getAllRecipesForCategory(final String categoryId, final AsyncListener<List<NutritionRecipe>> listener) {
        final AsyncToken asyncToken = new AsyncToken("#getAllRecipesForCategory");
        final MWNutritionGetCategoryDetailsResponse cacheResponse = (MWNutritionGetCategoryDetailsResponse) LocalDataManager.getSharedInstance().getObjectFromCache(MW_CATEGORY_RECIPE_RESPONSE_KEY + categoryId, new C39909().getType());
        if (cacheResponse != null) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    MWNutritionConnectorHelper.this.processRecipesForCategory(cacheResponse, asyncToken, new ArrayList(), new ArrayList(), listener);
                }
            });
            return;
        }
        this.mSharedData.getNetworkConnection().processRequest(new MWNutritionGetCategoryDetailsRequest(categoryId, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE), new AsyncListener<MWNutritionGetCategoryDetailsResponse>() {
            public void onResponse(MWNutritionGetCategoryDetailsResponse response, AsyncToken token, AsyncException exception) {
                List<NutritionRecipe> categoryRecipeList = new ArrayList();
                List<MWMenuItem> mwMenuItems = new ArrayList();
                if (exception == null) {
                    LocalDataManager.getSharedInstance().addObjectToCache(MWNutritionConnectorHelper.MW_CATEGORY_RECIPE_RESPONSE_KEY + categoryId, response, MWNutritionConnectorHelper.this.getNutritionCacheExpirationInterval());
                    MWNutritionConnectorHelper.this.processRecipesForCategory(response, asyncToken, categoryRecipeList, mwMenuItems, listener);
                    return;
                }
                AsyncException.report(exception);
            }
        });
    }

    public void getNutritionCategoryDetail(String categoryId, final AsyncListener<MWNutritionGetCategoryDetailsResponse> listener) {
        this.mSharedData.getNetworkConnection().processRequest(new MWNutritionGetCategoryDetailsRequest(categoryId, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE), new AsyncListener<MWNutritionGetCategoryDetailsResponse>() {
            public void onResponse(MWNutritionGetCategoryDetailsResponse response, AsyncToken token, AsyncException exception) {
                listener.onResponse(response, token, exception);
            }
        });
    }

    public void getRecipesForCategory(String categoryId, final AsyncListener<List<NutritionRecipe>> listener) {
        this.mSharedData.getNetworkConnection().processRequest(new MWNutritionGetCategoryDetailsRequest(categoryId, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE), new AsyncListener<MWNutritionGetCategoryDetailsResponse>() {
            public void onResponse(MWNutritionGetCategoryDetailsResponse response, AsyncToken token, AsyncException exception) {
                List<NutritionRecipe> recipes = null;
                if (exception == null) {
                    recipes = new ArrayList();
                    if (!(response == null || response.categoryDetailsCategory == null || response.categoryDetailsCategory.categoryItems == null || response.categoryDetailsCategory.categoryItems.categoryItemList == null)) {
                        List<MWMenuItem> categoryItemList = response.categoryDetailsCategory.categoryItems.categoryItemList;
                        int size = categoryItemList.size();
                        for (int i = 0; i < size; i++) {
                            NutritionRecipe recipe = ((MWMenuItem) categoryItemList.get(i)).toRecipe(MWNutritionConnectorHelper.this.mBaseImagePath);
                            recipe.setCategoryMarketingName(response.categoryDetailsCategory.categoryMarketingName);
                            recipes.add(recipe);
                        }
                    }
                }
                listener.onResponse(recipes, token, exception);
            }
        });
    }

    public void getAllCategories(final AsyncListener<List<Category>> listener) {
        final AsyncToken asyncToken = new AsyncToken("getAllCategories");
        final MWNutritionGetAllCategoriesResponse cacheResponse = (MWNutritionGetAllCategoriesResponse) LocalDataManager.getSharedInstance().getObjectFromCache(MW_CATEGORY_RESPONSE_KEY, new C397014().getType());
        String languageResponse = (String) LocalDataManager.getSharedInstance().getObjectFromCache(CURRENT_LANGUAGE, new C397115().getType());
        final String currentLanguage = Configuration.getSharedInstance().getCurrentLanguage();
        if (cacheResponse != null && cacheResponse.error == null && currentLanguage.equals(languageResponse)) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    listener.onResponse(MWNutritionConnectorHelper.this.processCategoryResponse(cacheResponse), asyncToken, null);
                }
            });
            return;
        }
        this.mSharedData.getNetworkConnection().processRequest(new MWNutritionGetAllCategoriesRequest("1"), new AsyncListener<MWNutritionGetAllCategoriesResponse>() {
            public void onResponse(MWNutritionGetAllCategoriesResponse response, AsyncToken token, AsyncException exception) {
                if (exception == null || !(response == null || response.error == null)) {
                    LocalDataManager.getSharedInstance().addObjectToCache(MWNutritionConnectorHelper.CURRENT_LANGUAGE, currentLanguage, MWNutritionConnectorHelper.this.getNutritionCacheExpirationInterval());
                    LocalDataManager.getSharedInstance().addObjectToCache(MWNutritionConnectorHelper.MW_CATEGORY_RESPONSE_KEY, response, MWNutritionConnectorHelper.this.getNutritionCacheExpirationInterval());
                    listener.onResponse(MWNutritionConnectorHelper.this.processCategoryResponse(response), asyncToken, null);
                    return;
                }
                listener.onResponse(null, asyncToken, exception);
            }
        });
    }

    public void populateFullRecipeDetails(final NutritionRecipe recipe, final AsyncListener<NutritionRecipe> listener) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                listener.onResponse(recipe, null, null);
            }
        });
    }

    private void processRecipesForCategory(MWNutritionGetCategoryDetailsResponse response, AsyncToken token, List<NutritionRecipe> categoryRecipeList, List<MWMenuItem> mwMenuItems, AsyncListener<List<NutritionRecipe>> listener) {
        MWCategoryDetailsCategory details = response.categoryDetailsCategory;
        if (details == null || details.categoryItems == null || details.categoryItems.categoryItemList == null) {
            listener.onResponse(categoryRecipeList, token, new AsyncException(details != null ? "No category details for " + details.categoryId : "No category details"));
            return;
        }
        mwMenuItems.addAll(details.categoryItems.categoryItemList);
        listener.onResponse(processRecipes(mwMenuItems), token, null);
    }

    private void commonRecipeForExternalId(final String externalId, final AsyncListener<NutritionRecipe> listener) {
        runRecipeTask(new Runnable() {

            /* renamed from: com.mcdonalds.sdk.connectors.middleware.helpers.MWNutritionConnectorHelper$19$1 */
            class C39751 implements Runnable {
                C39751() {
                }

                public void run() {
                    listener.onResponse(null, null, null);
                }
            }

            public void run() {
                boolean fetchFromNetwork = false;
                MWMenuItem mwMenuItem = null;
                if (MWNutritionConnectorHelper.this.mExternalMenuItemCache == null) {
                    fetchFromNetwork = true;
                } else {
                    mwMenuItem = (MWMenuItem) MWNutritionConnectorHelper.this.mExternalMenuItemCache.get(externalId);
                    if (mwMenuItem == null || mwMenuItem.recipeDetailedInfo == null) {
                        fetchFromNetwork = true;
                    } else if (mwMenuItem.externalId.equalsIgnoreCase(externalId)) {
                        MWGetRecipeForIdResponse mwGetRecipeForIdResponse = new MWGetRecipeForIdResponse();
                        mwGetRecipeForIdResponse.setItem(mwMenuItem.recipeDetailedInfo);
                        listener.onResponse(MWNutritionConnectorHelper.this.processRecipeForId(mwGetRecipeForIdResponse), null, null);
                        fetchFromNetwork = false;
                    } else {
                        new Handler(Looper.getMainLooper()).post(new C39751());
                    }
                }
                if (fetchFromNetwork) {
                    final MWMenuItem menuItem = mwMenuItem;
                    MWNutritionConnectorHelper.this.mSharedData.getNetworkConnection().processRequest(new MWNutritionGetItemListOnExternalIDRequest(externalId), new AsyncListener<MWGetItemByExternalIdResponse>() {
                        public void onResponse(MWGetItemByExternalIdResponse response, AsyncToken token, AsyncException exception) {
                            if (response == null || response.item == null || exception != null) {
                                listener.onResponse(null, token, exception);
                                return;
                            }
                            NutritionRecipe recipe = MWNutritionConnectorHelper.this.processRecipeForId(response.item);
                            if (recipe.getExternalId() == Integer.valueOf(externalId).intValue()) {
                                if (menuItem != null) {
                                    menuItem.recipeDetailedInfo = response.item.getItem();
                                }
                                listener.onResponse(recipe, token, null);
                                return;
                            }
                            listener.onResponse(null, token, null);
                        }
                    });
                }
            }
        });
    }

    private NutritionRecipe processRecipeForId(MWGetRecipeForIdResponse response) {
        return response.getItem().toRecipe(this.mBaseImagePath);
    }

    public List<Category> processCategoryResponse(MWNutritionGetAllCategoriesResponse response) {
        if (response == null || response.categories == null) {
            return new ArrayList();
        }
        List<MWNutritionCategory> mwCategories = response.categories.categoryList;
        int size = mwCategories.size();
        List<Category> categories = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            if (((MWNutritionCategory) mwCategories.get(i)).doNotShow.equalsIgnoreCase(CORE_KEY)) {
                categories.add(((MWNutritionCategory) mwCategories.get(i)).toCategory());
            }
        }
        return categories;
    }

    public List<NutritionRecipe> processDepMenuItems(MWGetAllRecipesResponse response) {
        return processRecipes(response.getFullMenu().fullMenuItems.menuItemList);
    }

    /* Access modifiers changed, original: protected */
    public MWMenuItem findDefaultItem(MWMenuItem mwMenuItem, Map<String, MWMenuItem> defaultMenuItems, Map<String, List<String>> relationMap) {
        for (String defaultId : relationMap.keySet()) {
            if (defaultId != null) {
                List<String> relationIds = (List) relationMap.get(defaultId);
                if (ListUtils.isEmpty(relationIds)) {
                    continue;
                } else {
                    int size = relationIds.size();
                    for (int i = 0; i < size; i++) {
                        if (mwMenuItem.f6067id.equals((String) relationIds.get(i))) {
                            return (MWMenuItem) defaultMenuItems.get(defaultId);
                        }
                    }
                    continue;
                }
            }
        }
        return null;
    }

    private List<NutritionRecipe> processRecipes(List<MWMenuItem> menuItemList) {
        List<NutritionRecipe> recipes = new ArrayList();
        Map<String, List<String>> relationMap = new LinkedHashMap();
        Map<String, MWMenuItem> defaultMenuItems = new LinkedHashMap();
        processRelations(menuItemList, relationMap, defaultMenuItems);
        if (this.mMenuItemCache == null) {
            this.mMenuItemCache = new HashMap();
        }
        int size = menuItemList.size();
        for (int i = 0; i < size; i++) {
            MWMenuItem mwMenuItem = (MWMenuItem) menuItemList.get(i);
            if (mwMenuItem != null) {
                fixMWMenuItemImages(mwMenuItem, defaultMenuItems, relationMap);
                if (mwMenuItem.itemRelationTypes == null || !mwMenuItem.itemRelationTypes.hasMasterChild() || !defaultMenuItems.containsKey(mwMenuItem.f6067id)) {
                    NutritionRecipe recipe = mwMenuItem.toRecipe(this.mBaseImagePath);
                    this.mMenuItemCache.put(mwMenuItem.f6067id, mwMenuItem);
                    recipes.add(recipe);
                }
            }
        }
        return recipes;
    }

    /* Access modifiers changed, original: protected */
    public void fixMWMenuItemImages(MWMenuItem mwMenuItem, Map<String, MWMenuItem> defaultMenuItems, Map<String, List<String>> relationMap) {
        MWItemImageBase mWItemImageBase = null;
        MWMenuItem parentItem = findDefaultItem(mwMenuItem, defaultMenuItems, relationMap);
        if (parentItem != null) {
            MWMenuItem grandParentItem = findDefaultItem(parentItem, defaultMenuItems, relationMap);
            if (mwMenuItem.carouselImage == null || mwMenuItem.carouselImage.isEmpty()) {
                mwMenuItem.carouselImage = (MWCarouselImage) getMWMenuItemImage(parentItem.carouselImage, grandParentItem != null ? grandParentItem.carouselImage : null);
            }
            if (mwMenuItem.itemThumbNailImage == null || mwMenuItem.itemThumbNailImage.isEmpty()) {
                MWItemImageBase mWItemImageBase2;
                MWItemThumbnailImage mWItemThumbnailImage = parentItem.itemThumbNailImage;
                if (grandParentItem != null) {
                    mWItemImageBase2 = grandParentItem.itemThumbNailImage;
                } else {
                    mWItemImageBase2 = null;
                }
                mwMenuItem.itemThumbNailImage = (MWItemThumbnailImage) getMWMenuItemImage(mWItemThumbnailImage, mWItemImageBase2);
            }
            if (mwMenuItem.itemHeroImage == null || mwMenuItem.itemHeroImage.isEmpty()) {
                MWItemHeroImage mWItemHeroImage = parentItem.itemHeroImage;
                if (grandParentItem != null) {
                    mWItemImageBase = grandParentItem.itemHeroImage;
                }
                mwMenuItem.itemHeroImage = (MWItemHeroImage) getMWMenuItemImage(mWItemHeroImage, mWItemImageBase);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public <T extends MWItemImageBase> T getMWMenuItemImage(T parentImage, T grandParentImage) {
        if (parentImage != null && !parentImage.isEmpty()) {
            return parentImage;
        }
        if (grandParentImage == null || grandParentImage.isEmpty()) {
            return null;
        }
        return grandParentImage;
    }

    /* Access modifiers changed, original: protected */
    public String findDefault(Map<String, List<String>> relationMap, Map<String, MWMenuItem> defaultMenuItems, MWMenuItem mwMenuItem, MWMenuItemRelationType mwMenuItemRelationType) {
        if (mwMenuItemRelationType == null) {
            return null;
        }
        MWMenuItemRelatedItems itemRelatedItems = mwMenuItemRelationType.menuItemRelatedItems;
        if (itemRelatedItems == null) {
            return null;
        }
        List<MWMenuItemRelatedItem> menuItemRelatedItemList = itemRelatedItems.menuItemRelatedItemList;
        if (menuItemRelatedItemList == null || menuItemRelatedItemList.isEmpty()) {
            return null;
        }
        String defaultId = null;
        int size = menuItemRelatedItemList.size();
        for (int i = 0; i < size; i++) {
            MWMenuItemRelatedItem mwMenuItemRelatedItem = (MWMenuItemRelatedItem) menuItemRelatedItemList.get(i);
            if (mwMenuItemRelatedItem.isDefault != null && mwMenuItemRelatedItem.isDefault.booleanValue()) {
                defaultId = mwMenuItemRelatedItem.f6068id;
                defaultMenuItems.put(defaultId, mwMenuItem);
                relationMap.put(defaultId, null);
            }
        }
        return defaultId;
    }

    /* Access modifiers changed, original: protected */
    public void processRelations(List<MWMenuItem> menuItemList, Map<String, List<String>> relationMap, Map<String, MWMenuItem> defaultMenuItems) {
        int size = menuItemList.size();
        for (int i = 0; i < size; i++) {
            MWMenuItem mwMenuItem = (MWMenuItem) menuItemList.get(i);
            if (mwMenuItem != null) {
                MWMenuItemRelationTypes itemRelationTypes = mwMenuItem.itemRelationTypes;
                if (itemRelationTypes != null) {
                    List<MWMenuItemRelationType> itemRelationType = itemRelationTypes.getItemRelationType();
                    if (itemRelationTypes.hasSize()) {
                        processRelation(relationMap, defaultMenuItems, mwMenuItem, itemRelationTypes.getSize(), null);
                    }
                    if (itemRelationTypes.hasMasterChild()) {
                        processRelation(relationMap, defaultMenuItems, mwMenuItem, itemRelationTypes.getMasterChild(), null);
                    } else {
                        String defaultId = null;
                        int typeSize = itemRelationType.size();
                        for (int j = 0; j < typeSize; j++) {
                            MWMenuItemRelationType mwMenuItemRelationType = (MWMenuItemRelationType) itemRelationType.get(j);
                            if (defaultId == null) {
                                defaultId = findDefault(relationMap, defaultMenuItems, mwMenuItem, mwMenuItemRelationType);
                            }
                            processRelation(relationMap, defaultMenuItems, mwMenuItem, mwMenuItemRelationType, defaultId);
                        }
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void processRelation(Map<String, List<String>> relationMap, Map<String, MWMenuItem> defaultMenuItems, MWMenuItem mwMenuItem, MWMenuItemRelationType mwMenuItemRelationType, String defaultId) {
        if (mwMenuItemRelationType != null) {
            MWMenuItemRelatedItems itemRelatedItems = mwMenuItemRelationType.menuItemRelatedItems;
            if (itemRelatedItems != null) {
                List<MWMenuItemRelatedItem> menuItemRelatedItemList = itemRelatedItems.menuItemRelatedItemList;
                if (menuItemRelatedItemList != null && !menuItemRelatedItemList.isEmpty()) {
                    int i;
                    List<String> relatedIds = relationMap.get(defaultId) == null ? new ArrayList() : (List) relationMap.get(defaultId);
                    int size = menuItemRelatedItemList.size();
                    for (i = 0; i < size; i++) {
                        MWMenuItemRelatedItem mwMenuItemRelatedItem = (MWMenuItemRelatedItem) menuItemRelatedItemList.get(i);
                        if (mwMenuItemRelatedItem.isDefault == null || !mwMenuItemRelatedItem.isDefault.booleanValue()) {
                            relatedIds.add(mwMenuItemRelatedItem.f6068id);
                        } else {
                            defaultId = mwMenuItemRelatedItem.f6068id;
                            defaultMenuItems.put(defaultId, mwMenuItem);
                        }
                    }
                    if (relationMap.get(defaultId) != null) {
                        List<String> existingRelations = (List) relationMap.get(defaultId);
                        size = relatedIds.size();
                        for (i = 0; i < size; i++) {
                            String id = (String) relatedIds.get(i);
                            if (!existingRelations.contains(id)) {
                                existingRelations.add(id);
                            }
                        }
                        relationMap.put(defaultId, existingRelations);
                        return;
                    }
                    relationMap.put(defaultId, relatedIds);
                }
            }
        }
    }

    private void runRecipeTask(Runnable recipeTask) {
        if (this.mCatalogOperationInProgress) {
            this.mDelayedTasks.add(recipeTask);
        } else {
            new Handler(Looper.getMainLooper()).post(recipeTask);
        }
    }

    private void runDelayedRecipeTasks() {
        new Handler(Looper.getMainLooper()).post(new C397920());
    }

    private long getNutritionCacheExpirationInterval() {
        if (Configuration.getSharedInstance().hasKey(REFRESH_CACHE_DAY_COUNT_KEY)) {
            return TimeUnit.DAYS.toMillis(Configuration.getSharedInstance().getLongForKey(REFRESH_CACHE_DAY_COUNT_KEY));
        }
        return TimeUnit.DAYS.toMillis(DEFAULT_CACHE_DAY_COUNT);
    }
}
