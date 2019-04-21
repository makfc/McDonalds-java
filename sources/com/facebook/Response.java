package com.facebook;

import android.content.Context;
import com.facebook.internal.CacheableRequestBatch;
import com.facebook.internal.FileLruCache;
import com.facebook.internal.FileLruCache.Limits;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObject.Factory;
import com.facebook.model.GraphObjectList;
import com.mcdonalds.sdk.connectors.middleware.response.MWDeliveryStatusResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Response {
    static final /* synthetic */ boolean $assertionsDisabled = (!Response.class.desiredAssertionStatus());
    private static final String BODY_KEY = "body";
    private static final String CODE_KEY = "code";
    private static final int INVALID_SESSION_FACEBOOK_ERROR_CODE = 190;
    public static final String NON_JSON_RESPONSE_PROPERTY = "FACEBOOK_NON_JSON_RESULT";
    private static final String RESPONSE_CACHE_TAG = "ResponseCache";
    private static final String RESPONSE_LOG_TAG = "Response";
    public static final String SUCCESS_KEY = "success";
    private static FileLruCache responseCache;
    private final HttpURLConnection connection;
    private final FacebookRequestError error;
    private final GraphObject graphObject;
    private final GraphObjectList<GraphObject> graphObjectList;
    private final boolean isFromCache;
    private final String rawResponse;
    private final Request request;

    interface PagedResults extends GraphObject {
        GraphObjectList<GraphObject> getData();

        PagingInfo getPaging();
    }

    public enum PagingDirection {
        NEXT,
        PREVIOUS
    }

    interface PagingInfo extends GraphObject {
        String getNext();

        String getPrevious();
    }

    Response(Request request, HttpURLConnection connection, String rawResponse, GraphObject graphObject, boolean isFromCache) {
        this(request, connection, rawResponse, graphObject, null, isFromCache, null);
    }

    Response(Request request, HttpURLConnection connection, String rawResponse, GraphObjectList<GraphObject> graphObjects, boolean isFromCache) {
        this(request, connection, rawResponse, null, graphObjects, isFromCache, null);
    }

    Response(Request request, HttpURLConnection connection, FacebookRequestError error) {
        this(request, connection, null, null, null, false, error);
    }

    Response(Request request, HttpURLConnection connection, String rawResponse, GraphObject graphObject, GraphObjectList<GraphObject> graphObjects, boolean isFromCache, FacebookRequestError error) {
        this.request = request;
        this.connection = connection;
        this.rawResponse = rawResponse;
        this.graphObject = graphObject;
        this.graphObjectList = graphObjects;
        this.isFromCache = isFromCache;
        this.error = error;
    }

    public final FacebookRequestError getError() {
        return this.error;
    }

    public final GraphObject getGraphObject() {
        return this.graphObject;
    }

    public final <T extends GraphObject> T getGraphObjectAs(Class<T> graphObjectClass) {
        if (this.graphObject == null) {
            return null;
        }
        if (graphObjectClass != null) {
            return this.graphObject.cast(graphObjectClass);
        }
        throw new NullPointerException("Must pass in a valid interface that extends GraphObject");
    }

    public final GraphObjectList<GraphObject> getGraphObjectList() {
        return this.graphObjectList;
    }

    public final <T extends GraphObject> GraphObjectList<T> getGraphObjectListAs(Class<T> graphObjectClass) {
        if (this.graphObjectList == null) {
            return null;
        }
        return this.graphObjectList.castToListOf(graphObjectClass);
    }

    public final HttpURLConnection getConnection() {
        return this.connection;
    }

    public Request getRequest() {
        return this.request;
    }

    public String getRawResponse() {
        return this.rawResponse;
    }

    public Request getRequestForPagedResults(PagingDirection direction) {
        String link = null;
        if (this.graphObject != null) {
            PagingInfo pagingInfo = ((PagedResults) this.graphObject.cast(PagedResults.class)).getPaging();
            if (pagingInfo != null) {
                link = direction == PagingDirection.NEXT ? pagingInfo.getNext() : pagingInfo.getPrevious();
            }
        }
        if (Utility.isNullOrEmpty(link)) {
            return null;
        }
        if (link != null && link.equals(this.request.getUrlForSingleRequest())) {
            return null;
        }
        try {
            return new Request(this.request.getSession(), new URL(link));
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public String toString() {
        String responseCode;
        try {
            String str = "%d";
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(this.connection != null ? this.connection.getResponseCode() : 200);
            responseCode = String.format(str, objArr);
        } catch (IOException e) {
            responseCode = "unknown";
        }
        return "{Response: " + " responseCode: " + responseCode + ", graphObject: " + this.graphObject + ", error: " + this.error + ", isFromCache:" + this.isFromCache + "}";
    }

    public final boolean getIsFromCache() {
        return this.isFromCache;
    }

    static FileLruCache getResponseCache() {
        if (responseCache == null) {
            Context applicationContext = Session.getStaticContext();
            if (applicationContext != null) {
                responseCache = new FileLruCache(applicationContext, RESPONSE_CACHE_TAG, new Limits());
            }
        }
        return responseCache;
    }

    static List<Response> fromHttpConnection(HttpURLConnection connection, RequestBatch requests) {
        List<Response> e;
        InputStream stream = null;
        FileLruCache cache = null;
        String cacheKey = null;
        if (requests instanceof CacheableRequestBatch) {
            CacheableRequestBatch cacheableRequestBatch = (CacheableRequestBatch) requests;
            cache = getResponseCache();
            cacheKey = cacheableRequestBatch.getCacheKeyOverride();
            if (Utility.isNullOrEmpty(cacheKey)) {
                if (requests.size() == 1) {
                    cacheKey = requests.get(0).getUrlForSingleRequest();
                } else {
                    Logger.log(LoggingBehavior.REQUESTS, RESPONSE_CACHE_TAG, "Not using cache for cacheable request because no key was specified");
                }
            }
            if (!(cacheableRequestBatch.getForceRoundTrip() || cache == null || Utility.isNullOrEmpty(cacheKey))) {
                try {
                    stream = cache.get(cacheKey);
                    if (stream != null) {
                        e = createResponsesFromStream(stream, null, requests, true);
                        return e;
                    }
                    Utility.closeQuietly(stream);
                } catch (FacebookException e2) {
                    e = e2;
                } catch (JSONException e3) {
                    e = e3;
                } catch (IOException e4) {
                    e = e4;
                } finally {
                    Utility.closeQuietly(stream);
                }
            }
        }
        try {
            if (connection.getResponseCode() >= MWDeliveryStatusResponse.STATUS_ORDER_DELIVERED) {
                stream = connection.getErrorStream();
            } else {
                stream = connection.getInputStream();
                if (!(cache == null || cacheKey == null || stream == null)) {
                    InputStream interceptStream = cache.interceptAndPut(cacheKey, stream);
                    if (interceptStream != null) {
                        stream = interceptStream;
                    }
                }
            }
            e = createResponsesFromStream(stream, connection, requests, false);
        } catch (FacebookException facebookException) {
            Logger.log(LoggingBehavior.REQUESTS, RESPONSE_LOG_TAG, "Response <Error>: %s", facebookException);
            e = constructErrorResponses(requests, connection, facebookException);
        } catch (JSONException exception) {
            Logger.log(LoggingBehavior.REQUESTS, RESPONSE_LOG_TAG, "Response <Error>: %s", exception);
            e = constructErrorResponses(requests, connection, new FacebookException(exception));
        } catch (IOException exception2) {
            Logger.log(LoggingBehavior.REQUESTS, RESPONSE_LOG_TAG, "Response <Error>: %s", exception2);
            e = constructErrorResponses(requests, connection, new FacebookException(exception2));
        } catch (SecurityException exception22) {
            Logger.log(LoggingBehavior.REQUESTS, RESPONSE_LOG_TAG, "Response <Error>: %s", exception22);
            e = constructErrorResponses(requests, connection, new FacebookException(exception22));
        } finally {
            Utility.closeQuietly(stream);
        }
        return e;
    }

    static List<Response> createResponsesFromStream(InputStream stream, HttpURLConnection connection, RequestBatch requests, boolean isFromCache) throws FacebookException, JSONException, IOException {
        Logger.log(LoggingBehavior.INCLUDE_RAW_RESPONSES, RESPONSE_LOG_TAG, "Response (raw)\n  Size: %d\n  Response:\n%s\n", Integer.valueOf(Utility.readStreamToString(stream).length()), responseString);
        return createResponsesFromString(Utility.readStreamToString(stream), connection, requests, isFromCache);
    }

    static List<Response> createResponsesFromString(String responseString, HttpURLConnection connection, RequestBatch requests, boolean isFromCache) throws FacebookException, JSONException, IOException {
        Logger.log(LoggingBehavior.REQUESTS, RESPONSE_LOG_TAG, "Response\n  Id: %s\n  Size: %d\n  Responses:\n%s\n", requests.getId(), Integer.valueOf(responseString.length()), createResponsesFromObject(connection, requests, new JSONTokener(responseString).nextValue(), isFromCache));
        return createResponsesFromObject(connection, requests, new JSONTokener(responseString).nextValue(), isFromCache);
    }

    private static List<Response> createResponsesFromObject(HttpURLConnection connection, List<Request> requests, Object object, boolean isFromCache) throws FacebookException, JSONException {
        Request request;
        if ($assertionsDisabled || connection != null || isFromCache) {
            JSONArray jsonArray;
            int numRequests = requests.size();
            List<Response> responses = new ArrayList(numRequests);
            Object originalResult = object;
            if (numRequests == 1) {
                request = (Request) requests.get(0);
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("body", object);
                    jsonObject.put("code", connection != null ? connection.getResponseCode() : 200);
                    jsonArray = new JSONArray();
                    jsonArray.put(jsonObject);
                    object = jsonArray;
                } catch (JSONException e) {
                    responses.add(new Response(request, connection, new FacebookRequestError(connection, e)));
                } catch (IOException e2) {
                    responses.add(new Response(request, connection, new FacebookRequestError(connection, e2)));
                }
            }
            if ((object instanceof JSONArray) && ((JSONArray) object).length() == numRequests) {
                jsonArray = (JSONArray) object;
                for (int i = 0; i < jsonArray.length(); i++) {
                    request = (Request) requests.get(i);
                    try {
                        responses.add(createResponseFromObject(request, connection, jsonArray.get(i), isFromCache, originalResult));
                    } catch (JSONException e3) {
                        responses.add(new Response(request, connection, new FacebookRequestError(connection, e3)));
                    } catch (FacebookException e4) {
                        responses.add(new Response(request, connection, new FacebookRequestError(connection, e4)));
                    }
                }
                return responses;
            }
            throw new FacebookException("Unexpected number of results");
        }
        throw new AssertionError();
    }

    private static Response createResponseFromObject(Request request, HttpURLConnection connection, Object object, boolean isFromCache, Object originalResult) throws JSONException {
        if (object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            FacebookRequestError error = FacebookRequestError.checkResponseAndCreateError(jsonObject, originalResult, connection);
            if (error != null) {
                if (error.getErrorCode() == INVALID_SESSION_FACEBOOK_ERROR_CODE) {
                    Session session = request.getSession();
                    if (session != null) {
                        session.closeAndClearTokenInformation();
                    }
                }
                return new Response(request, connection, error);
            }
            Object body = Utility.getStringPropertyAsJSON(jsonObject, "body", NON_JSON_RESPONSE_PROPERTY);
            if (body instanceof JSONObject) {
                return new Response(request, connection, body.toString(), Factory.create((JSONObject) body), isFromCache);
            } else if (body instanceof JSONArray) {
                return new Response(request, connection, body.toString(), Factory.createList((JSONArray) body, GraphObject.class), isFromCache);
            } else {
                object = JSONObject.NULL;
            }
        }
        if (object == JSONObject.NULL) {
            return new Response(request, connection, object.toString(), (GraphObject) null, isFromCache);
        }
        throw new FacebookException("Got unexpected object type in response, class: " + object.getClass().getSimpleName());
    }

    static List<Response> constructErrorResponses(List<Request> requests, HttpURLConnection connection, FacebookException error) {
        int count = requests.size();
        List<Response> responses = new ArrayList(count);
        for (int i = 0; i < count; i++) {
            responses.add(new Response((Request) requests.get(i), connection, new FacebookRequestError(connection, error)));
        }
        return responses;
    }
}
