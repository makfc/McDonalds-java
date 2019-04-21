package com.mcdonalds.sdk.connectors.middleware.helpers;

import android.text.TextUtils;
import android.util.Base64InputStream;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnectorUtils;
import com.mcdonalds.sdk.connectors.middleware.model.MWCatalog;
import com.mcdonalds.sdk.connectors.middleware.model.MWMarket;
import com.mcdonalds.sdk.connectors.middleware.model.MWName;
import com.mcdonalds.sdk.connectors.middleware.model.MWNameInfo;
import com.mcdonalds.sdk.connectors.middleware.model.MWProduct;
import com.mcdonalds.sdk.connectors.middleware.model.MWStore;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

public class MergeCatalog {
    private final String COPYRIGHT = "©";
    private final String NEWLINE = "\n";
    private final Pattern PATTERN_ALL = Pattern.compile("\\\\[a-z]");
    private final Pattern PATTERN_COPYRIGHT = Pattern.compile("\\\\c");
    private final Pattern PATTERN_NEWLINE = Pattern.compile("\\\\n");
    private final Pattern PATTERN_REGISTER = Pattern.compile("\\\\r");
    private final Pattern PATTERN_TRADEMARK = Pattern.compile("\\\\t");
    private final String REGISTER = "®";
    private final String TRADEMARK = "™";
    MWCatalog catalog = null;
    final String jsonString;

    public MergeCatalog(String str) {
        this.jsonString = str;
    }

    public MWCatalog processData() {
        try {
            GZIPInputStream gis = new GZIPInputStream(new Base64InputStream(new ByteArrayInputStream(this.jsonString.getBytes()), 0));
            Gson gson = new Gson();
            Reader inputStreamReader = new InputStreamReader(gis);
            Class cls = MWCatalog.class;
            this.catalog = (MWCatalog) (!(gson instanceof Gson) ? gson.fromJson(inputStreamReader, cls) : GsonInstrumentation.fromJson(gson, inputStreamReader, cls));
            normalizeNames(this.catalog);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e2) {
            e2.printStackTrace();
        }
        MCDLog.temp("Finished Parsing Catalog.");
        return this.catalog;
    }

    private void normalizeNames(MWCatalog catalog) {
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
                return;
            }
            return;
        }
        MWMarket market = catalog.market;
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
}
