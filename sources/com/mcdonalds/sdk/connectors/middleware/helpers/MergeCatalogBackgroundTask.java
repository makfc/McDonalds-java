package com.mcdonalds.sdk.connectors.middleware.helpers;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Base64InputStream;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnectorUtils;
import com.mcdonalds.sdk.connectors.middleware.model.MWCatalog;
import com.mcdonalds.sdk.connectors.middleware.model.MWDisplayCategory;
import com.mcdonalds.sdk.connectors.middleware.model.MWMarket;
import com.mcdonalds.sdk.connectors.middleware.model.MWName;
import com.mcdonalds.sdk.connectors.middleware.model.MWNameInfo;
import com.mcdonalds.sdk.connectors.middleware.model.MWProduct;
import com.mcdonalds.sdk.connectors.middleware.model.MWStore;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.CatalogManager;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

class MergeCatalogBackgroundTask extends AsyncTask<Object, Void, MWCatalog> implements TraceFieldInterface {
    private final String COPYRIGHT;
    private final String NEWLINE;
    private final Pattern PATTERN_ALL;
    private final Pattern PATTERN_COPYRIGHT;
    private final Pattern PATTERN_NEWLINE;
    private final Pattern PATTERN_REGISTER;
    private final Pattern PATTERN_TRADEMARK;
    private final String REGISTER;
    private final String TRADEMARK;
    public Trace _nr_trace;
    final String mResponseData;
    final MergeCatalogBackgroundTaskListener mTaskListener;

    public interface MergeCatalogBackgroundTaskListener {
        void onPerformBackgroundTask(MWCatalog mWCatalog, boolean z);

        void onPerformPostExecute(MWCatalog mWCatalog);
    }

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    MergeCatalogBackgroundTask(MergeCatalogBackgroundTaskListener listener) {
        this(null, listener);
    }

    MergeCatalogBackgroundTask(String responseData, MergeCatalogBackgroundTaskListener listener) {
        this.PATTERN_COPYRIGHT = Pattern.compile("\\\\c");
        this.PATTERN_REGISTER = Pattern.compile("\\\\r");
        this.PATTERN_TRADEMARK = Pattern.compile("\\\\t");
        this.PATTERN_NEWLINE = Pattern.compile("\\\\n");
        this.PATTERN_ALL = Pattern.compile("\\\\[a-z]");
        this.COPYRIGHT = "©";
        this.REGISTER = "®";
        this.TRADEMARK = "™";
        this.NEWLINE = "\n";
        this.mResponseData = responseData;
        this.mTaskListener = listener;
    }

    public void execute() {
        Object[] objArr = new Object[]{this.mResponseData, this.mTaskListener};
        if (this instanceof AsyncTask) {
            AsyncTaskInstrumentation.execute(this, objArr);
        } else {
            execute(objArr);
        }
    }

    /* Access modifiers changed, original: protected|varargs */
    public MWCatalog doInBackground(Object... objects) {
        Thread.currentThread().setPriority(10);
        if (objects.length != 2) {
            MCDLog.fatal("Improper of MergeCatalogBackgroundTask, parameter count incorrect");
            return null;
        }
        MWCatalog parsedCatalog = null;
        boolean isCachedMarketCatalog = false;
        if (this.mResponseData == null) {
            parsedCatalog = new MWCatalog();
            parsedCatalog.market = getCachedMarketCatalog();
            MCDLog.temp("Using Cached Catalog.");
            isCachedMarketCatalog = true;
        } else {
            Log.i("catUpdate", "Finished Downloading Catalog.");
            try {
                GZIPInputStream gis = new GZIPInputStream(new Base64InputStream(new ByteArrayInputStream(objects[0].getBytes()), 0));
                Gson gson = new Gson();
                Reader inputStreamReader = new InputStreamReader(gis);
                Class cls = MWCatalog.class;
                parsedCatalog = (MWCatalog) (!(gson instanceof Gson) ? gson.fromJson(inputStreamReader, cls) : GsonInstrumentation.fromJson(gson, inputStreamReader, cls));
                normalizeNames(parsedCatalog);
                MCDLog.temp("Finished Parsing Catalog.");
            } catch (JsonSyntaxException | IOException e) {
                e.printStackTrace();
            }
        }
        if (parsedCatalog != null) {
            this.mTaskListener.onPerformBackgroundTask(parsedCatalog, isCachedMarketCatalog);
        }
        MCDLog.temp("Finished merging catalogs");
        return parsedCatalog;
    }

    /* Access modifiers changed, original: protected */
    public void onPostExecute(MWCatalog catalog) {
        this.mTaskListener.onPerformPostExecute(catalog);
    }

    private void normalizeNames(MWCatalog catalog) {
        MWMarket market;
        if (MiddlewareConnectorUtils.isUsingECP3()) {
            List<MWStore> stores = catalog.stores;
            if (stores != null) {
                for (MWStore store : stores) {
                    List<MWProduct> products = store.products;
                    if (products != null) {
                        for (MWProduct product : products) {
                            normalize(product.name);
                        }
                    }
                }
            }
            market = catalog.market;
            if (market != null) {
                List<MWDisplayCategory> displayCategories = market.displayCategory;
                if (displayCategories != null) {
                    for (MWDisplayCategory category : displayCategories) {
                        List<MWName> names = category.names;
                        if (names != null) {
                            for (MWName name : names) {
                                if (name.longName != null) {
                                    name.longName = normalize(name.longName);
                                }
                                if (name.shortName != null) {
                                    name.shortName = normalize(name.shortName);
                                }
                            }
                        }
                    }
                    return;
                }
                return;
            }
            return;
        }
        market = catalog.market;
        if (market != null && market.names != null) {
            for (MWNameInfo info : market.names) {
                normalize(info);
            }
        }
    }

    private void normalize(MWNameInfo info) {
        if (info != null) {
            for (MWName name : info.names) {
                name.name = normalize(name.name);
                name.longName = normalize(name.longName);
                name.shortName = normalize(name.shortName);
            }
        }
    }

    private String normalize(String name) {
        if (TextUtils.isEmpty(name)) {
            return "";
        }
        return this.PATTERN_ALL.matcher(this.PATTERN_NEWLINE.matcher(this.PATTERN_TRADEMARK.matcher(this.PATTERN_REGISTER.matcher(this.PATTERN_COPYRIGHT.matcher(name).replaceAll("©")).replaceAll("®")).replaceAll("™")).replaceAll("\n")).replaceAll("");
    }

    private MWMarket getCachedMarketCatalog() {
        return (MWMarket) LocalDataManager.getSharedInstance().getObjectFromCache(CatalogManager.CACHE_MARKET_CATALOG, MWMarket.class, Configuration.getSharedInstance().getBooleanForKey(MWCatalogManager.CONFIG_SERIALIZE_MARKET_CATALOG, false));
    }
}
