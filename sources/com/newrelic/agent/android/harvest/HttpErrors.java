package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class HttpErrors extends HarvestableArray {
    private final Collection<HttpError> httpErrors = new CopyOnWriteArrayList();

    public void addHttpError(HttpError httpError) {
        synchronized (httpError) {
            for (HttpError error : this.httpErrors) {
                if (httpError.getHash().equals(error.getHash())) {
                    error.incrementCount();
                    return;
                }
            }
            this.httpErrors.add(httpError);
        }
    }

    public synchronized void removeHttpError(HttpError error) {
        this.httpErrors.remove(error);
    }

    public void clear() {
        this.httpErrors.clear();
    }

    public JsonArray asJsonArray() {
        JsonArray array = new JsonArray();
        for (HttpError httpError : this.httpErrors) {
            array.add(httpError.asJson());
        }
        return array;
    }

    public Collection<HttpError> getHttpErrors() {
        return this.httpErrors;
    }

    public int count() {
        return this.httpErrors.size();
    }
}
