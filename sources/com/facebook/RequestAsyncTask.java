package com.facebook;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;

public class RequestAsyncTask extends AsyncTask<Void, Void, List<Response>> implements TraceFieldInterface {
    private static final String TAG = RequestAsyncTask.class.getCanonicalName();
    private static Method executeOnExecutorMethod;
    public Trace _nr_trace;
    private final HttpURLConnection connection;
    private Exception exception;
    private final RequestBatch requests;

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    static {
        for (Method method : AsyncTask.class.getMethods()) {
            if ("executeOnExecutor".equals(method.getName())) {
                Class<?>[] parameters = method.getParameterTypes();
                if (parameters.length == 2 && parameters[0] == Executor.class && parameters[1].isArray()) {
                    executeOnExecutorMethod = method;
                    return;
                }
            }
        }
    }

    public RequestAsyncTask(Request... requests) {
        this(null, new RequestBatch(requests));
    }

    public RequestAsyncTask(Collection<Request> requests) {
        this(null, new RequestBatch((Collection) requests));
    }

    public RequestAsyncTask(RequestBatch requests) {
        this(null, requests);
    }

    public RequestAsyncTask(HttpURLConnection connection, Request... requests) {
        this(connection, new RequestBatch(requests));
    }

    public RequestAsyncTask(HttpURLConnection connection, Collection<Request> requests) {
        this(connection, new RequestBatch((Collection) requests));
    }

    public RequestAsyncTask(HttpURLConnection connection, RequestBatch requests) {
        this.requests = requests;
        this.connection = connection;
    }

    /* Access modifiers changed, original: protected|final */
    public final Exception getException() {
        return this.exception;
    }

    /* Access modifiers changed, original: protected|final */
    public final RequestBatch getRequests() {
        return this.requests;
    }

    public String toString() {
        return "{RequestAsyncTask: " + " connection: " + this.connection + ", requests: " + this.requests + "}";
    }

    /* Access modifiers changed, original: protected */
    public void onPreExecute() {
        super.onPreExecute();
        if (this.requests.getCallbackHandler() == null) {
            this.requests.setCallbackHandler(new Handler());
        }
    }

    /* Access modifiers changed, original: protected */
    public void onPostExecute(List<Response> result) {
        super.onPostExecute(result);
        if (this.exception != null) {
            Log.d(TAG, String.format("onPostExecute: exception encountered during request: %s", new Object[]{this.exception.getMessage()}));
        }
    }

    /* Access modifiers changed, original: protected|varargs */
    public List<Response> doInBackground(Void... params) {
        try {
            if (this.connection == null) {
                return this.requests.executeAndWait();
            }
            return Request.executeConnectionAndWait(this.connection, this.requests);
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public RequestAsyncTask executeOnSettingsExecutor() {
        if (executeOnExecutorMethod != null) {
            try {
                executeOnExecutorMethod.invoke(this, new Object[]{Settings.getExecutor(), null});
            } catch (IllegalAccessException | InvocationTargetException e) {
            }
        } else {
            Void[] voidArr = new Void[0];
            if (this instanceof AsyncTask) {
                AsyncTaskInstrumentation.execute(this, voidArr);
            } else {
                execute(voidArr);
            }
        }
        return this;
    }
}
