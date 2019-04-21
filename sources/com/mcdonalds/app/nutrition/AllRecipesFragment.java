package com.mcdonalds.app.nutrition;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.List;

@Instrumented
public class AllRecipesFragment extends ListFragment implements TraceFieldInterface {
    private static final String TAG = AllRecipesFragment.class.getSimpleName();
    public Trace _nr_trace;
    private OnRecipeFragmentInteractionListener mListener;
    private OrderingModule mOrderingModule;
    private List<Product> mProductList;

    public interface OnRecipeFragmentInteractionListener {
        void onRecipeFragmentInteraction(Product product);
    }

    class RecipeListAdapter extends ArrayAdapter<Product> {
        private Context mContext;
        private List<Product> mProductList;
        private int mResource;
        private int mTextViewResourceId;

        RecipeListAdapter(Context context, int resource, int textViewResourceId, List<Product> objects) {
            super(context, resource, textViewResourceId, objects);
            this.mContext = context;
            this.mResource = resource;
            this.mTextViewResourceId = textViewResourceId;
            this.mProductList = objects;
        }

        public void setProductList(List<Product> productList) {
            Ensighten.evaluateEvent(this, "setProductList", new Object[]{productList});
            this.mProductList = productList;
        }

        public Product getItem(int position) {
            return (Product) this.mProductList.get(position);
        }

        public int getCount() {
            Ensighten.evaluateEvent(this, "getCount", null);
            return this.mProductList.size();
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(this.mResource, parent, false);
            DataLayerClickListener.setDataLayerTag(convertView, RecipeListAdapter.class, position);
            ((TextView) rowView.findViewById(this.mTextViewResourceId)).setText(((Product) this.mProductList.get(position)).getName());
            Ensighten.getViewReturnValue(rowView, position);
            Ensighten.processView((Object) this, "getView");
            Ensighten.getViewReturnValue(null, -1);
            return rowView;
        }
    }

    public void onActivityCreated(Bundle bundle) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityCreated", new Object[]{bundle});
        super.onActivityCreated(bundle);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityResult", new Object[]{new Integer(i), new Integer(i2), intent});
        super.onActivityResult(i, i2, intent);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "AllRecipesFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "AllRecipesFragment#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        TraceMachine.exitMethod();
        return onCreateView;
    }

    public void onDestroy() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroy", null);
        super.onDestroy();
    }

    public void onDestroyView() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroyView", null);
        super.onDestroyView();
    }

    public void onPause() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onPause", null);
        super.onPause();
        Ensighten.processView((Object) this, "onPause");
    }

    public void onResume() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onResume", null);
        super.onResume();
        Ensighten.processView((Object) this, "onResume");
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStart", null);
        super.onStart();
        Ensighten.processView((Object) this, "onStart");
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStop", null);
        super.onStop();
    }

    public void onViewStateRestored(Bundle bundle) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onViewStateRestored", new Object[]{bundle});
        super.onViewStateRestored(bundle);
        Ensighten.processView((Object) this, "onViewStateRestored");
    }

    static /* synthetic */ List access$002(AllRecipesFragment x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.AllRecipesFragment", "access$002", new Object[]{x0, x1});
        x0.mProductList = x1;
        return x1;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("AllRecipesFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "AllRecipesFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "AllRecipesFragment#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        this.mProductList = new ArrayList();
        final RecipeListAdapter recipeListAdapter = new RecipeListAdapter(getActivity(), 17367043, 16908308, this.mProductList);
        this.mOrderingModule = new OrderingModule(getActivity());
        setListAdapter(recipeListAdapter);
        this.mOrderingModule.getAllProducts(new AsyncListener<List<Product>>() {
            public void onResponse(List<Product> response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (exception == null) {
                    AllRecipesFragment.access$002(AllRecipesFragment.this, response);
                    Toast.makeText(AllRecipesFragment.this.getActivity(), response.size() + " recipes loaded", 0).show();
                    recipeListAdapter.setProductList(Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.AllRecipesFragment", "access$000", new Object[]{AllRecipesFragment.this}));
                    recipeListAdapter.notifyDataSetChanged();
                    return;
                }
                Log.d(Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.AllRecipesFragment", "access$100", null), "ERROR");
            }
        });
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public void onAttach(Activity activity) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onAttach", new Object[]{activity});
        super.onAttach(activity);
        try {
            this.mListener = (OnRecipeFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnRecipeFragmentInteractionListener");
        }
    }

    public void onDetach() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDetach", null);
        super.onDetach();
        this.mListener = null;
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        Ensighten.evaluateEvent(this, "onListItemClick", new Object[]{l, v, new Integer(position), new Long(id)});
        super.onListItemClick(l, v, position, id);
        if (this.mListener != null) {
            this.mListener.onRecipeFragmentInteraction((Product) l.getAdapter().getItem(position));
        }
    }
}
