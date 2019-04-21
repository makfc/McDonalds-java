package com.facebook.widget;

import android.content.Context;
import android.os.Handler;
import android.support.p000v4.content.Loader;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Request.Callback;
import com.facebook.RequestBatch;
import com.facebook.Response;
import com.facebook.Response.PagingDirection;
import com.facebook.internal.CacheableRequestBatch;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;

class GraphObjectPagingLoader<T extends GraphObject> extends Loader<SimpleGraphObjectCursor<T>> {
    private boolean appendResults = false;
    private Request currentRequest;
    private SimpleGraphObjectCursor<T> cursor;
    private final Class<T> graphObjectClass;
    private boolean loading = false;
    private Request nextRequest;
    private OnErrorListener onErrorListener;
    private Request originalRequest;
    private boolean skipRoundtripIfCached;

    /* renamed from: com.facebook.widget.GraphObjectPagingLoader$1 */
    class C20431 implements Callback {
        C20431() {
        }

        public void onCompleted(Response response) {
            GraphObjectPagingLoader.this.requestCompleted(response);
        }
    }

    /* renamed from: com.facebook.widget.GraphObjectPagingLoader$2 */
    class C20442 implements Callback {
        C20442() {
        }

        public void onCompleted(Response response) {
            GraphObjectPagingLoader.this.requestCompleted(response);
        }
    }

    public interface OnErrorListener {
        void onError(FacebookException facebookException, GraphObjectPagingLoader<?> graphObjectPagingLoader);
    }

    interface PagedResults extends GraphObject {
        GraphObjectList<GraphObject> getData();
    }

    public GraphObjectPagingLoader(Context context, Class<T> graphObjectClass) {
        super(context);
        this.graphObjectClass = graphObjectClass;
    }

    public OnErrorListener getOnErrorListener() {
        return this.onErrorListener;
    }

    public void setOnErrorListener(OnErrorListener listener) {
        this.onErrorListener = listener;
    }

    public SimpleGraphObjectCursor<T> getCursor() {
        return this.cursor;
    }

    public void clearResults() {
        this.nextRequest = null;
        this.originalRequest = null;
        this.currentRequest = null;
        deliverResult(null);
    }

    public boolean isLoading() {
        return this.loading;
    }

    public void startLoading(Request request, boolean skipRoundtripIfCached) {
        this.originalRequest = request;
        startLoading(request, skipRoundtripIfCached, 0);
    }

    public void refreshOriginalRequest(long afterDelay) {
        if (this.originalRequest == null) {
            throw new FacebookException("refreshOriginalRequest may not be called until after startLoading has been called.");
        }
        startLoading(this.originalRequest, false, afterDelay);
    }

    public void followNextLink() {
        if (this.nextRequest != null) {
            this.appendResults = true;
            this.currentRequest = this.nextRequest;
            this.currentRequest.setCallback(new C20431());
            this.loading = true;
            Request.executeBatchAsync(putRequestIntoBatch(this.currentRequest, this.skipRoundtripIfCached));
        }
    }

    public void deliverResult(SimpleGraphObjectCursor<T> cursor) {
        SimpleGraphObjectCursor<T> oldCursor = this.cursor;
        this.cursor = cursor;
        if (isStarted()) {
            super.deliverResult(cursor);
            if (oldCursor != null && oldCursor != cursor && !oldCursor.isClosed()) {
                oldCursor.close();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onStartLoading() {
        super.onStartLoading();
        if (this.cursor != null) {
            deliverResult(this.cursor);
        }
    }

    private void startLoading(Request request, boolean skipRoundtripIfCached, long afterDelay) {
        this.skipRoundtripIfCached = skipRoundtripIfCached;
        this.appendResults = false;
        this.nextRequest = null;
        this.currentRequest = request;
        this.currentRequest.setCallback(new C20442());
        this.loading = true;
        final RequestBatch batch = putRequestIntoBatch(request, skipRoundtripIfCached);
        Runnable r = new Runnable() {
            public void run() {
                Request.executeBatchAsync(batch);
            }
        };
        if (afterDelay == 0) {
            r.run();
        } else {
            new Handler().postDelayed(r, afterDelay);
        }
    }

    private CacheableRequestBatch putRequestIntoBatch(Request request, boolean skipRoundtripIfCached) {
        boolean z = true;
        CacheableRequestBatch batch = new CacheableRequestBatch(request);
        if (skipRoundtripIfCached) {
            z = false;
        }
        batch.setForceRoundTrip(z);
        return batch;
    }

    private void requestCompleted(Response response) {
        if (response.getRequest() == this.currentRequest) {
            this.loading = false;
            this.currentRequest = null;
            FacebookRequestError requestError = response.getError();
            FacebookException exception = requestError == null ? null : requestError.getException();
            if (response.getGraphObject() == null && exception == null) {
                exception = new FacebookException("GraphObjectPagingLoader received neither a result nor an error.");
            }
            if (exception != null) {
                this.nextRequest = null;
                if (this.onErrorListener != null) {
                    this.onErrorListener.onError(exception, this);
                    return;
                }
                return;
            }
            addResults(response);
        }
    }

    private void addResults(Response response) {
        boolean haveData;
        SimpleGraphObjectCursor cursorToModify = (this.cursor == null || !this.appendResults) ? new SimpleGraphObjectCursor() : new SimpleGraphObjectCursor(this.cursor);
        PagedResults result = (PagedResults) response.getGraphObjectAs(PagedResults.class);
        boolean fromCache = response.getIsFromCache();
        GraphObjectList<T> data = result.getData().castToListOf(this.graphObjectClass);
        if (data.size() > 0) {
            haveData = true;
        } else {
            haveData = false;
        }
        if (haveData) {
            this.nextRequest = response.getRequestForPagedResults(PagingDirection.NEXT);
            cursorToModify.addGraphObjects(data, fromCache);
            if (this.nextRequest != null) {
                cursorToModify.setMoreObjectsAvailable(true);
            } else {
                cursorToModify.setMoreObjectsAvailable(false);
            }
        }
        if (!haveData) {
            cursorToModify.setMoreObjectsAvailable(false);
            cursorToModify.setFromCache(fromCache);
            this.nextRequest = null;
        }
        if (!fromCache) {
            this.skipRoundtripIfCached = false;
        }
        deliverResult(cursorToModify);
    }
}
