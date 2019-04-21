package com.mcdonalds.app.ordering;

import android.os.AsyncTask;
import com.ensighten.Ensighten;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.modules.models.Product;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;

public class GetFullRecipeDetailsAsyncTask extends AsyncTask<Object, Void, Void> implements TraceFieldInterface {
    public Trace _nr_trace;
    private AsyncListener<Product> mListener;

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    /* Access modifiers changed, original: protected|varargs */
    public Void doInBackground(Object... objects) {
        Product product = objects[0];
        this.mListener = (AsyncListener) objects[1];
        return null;
    }

    /* Access modifiers changed, original: protected */
    public void onPostExecute(Void aVoid) {
        Ensighten.evaluateEvent(this, "onPostExecute", new Object[]{aVoid});
        super.onPostExecute(aVoid);
    }
}
