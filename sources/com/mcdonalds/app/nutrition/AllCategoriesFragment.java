package com.mcdonalds.app.nutrition;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.List;

@Instrumented
public class AllCategoriesFragment extends ListFragment implements TraceFieldInterface {
    public Trace _nr_trace;
    private List<Category> mCategoryList;
    private CategoryListAdapter mCategoryListAdapter;
    private OnCategoryFragmentInteractionListener mListener;
    private OrderingModule mOrderingModule;

    /* renamed from: com.mcdonalds.app.nutrition.AllCategoriesFragment$1 */
    class C32911 implements AsyncListener<List<Category>> {
        C32911() {
        }

        public void onResponse(List<Category> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            AllCategoriesFragment.access$002(AllCategoriesFragment.this, response);
            Toast.makeText(AllCategoriesFragment.this.getActivity(), response.size() + " categories loaded", 0).show();
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.AllCategoriesFragment", "access$100", new Object[]{AllCategoriesFragment.this}).setCategoryList(Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.AllCategoriesFragment", "access$000", new Object[]{AllCategoriesFragment.this}));
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.AllCategoriesFragment", "access$100", new Object[]{AllCategoriesFragment.this}).notifyDataSetChanged();
        }
    }

    class CategoryListAdapter extends ArrayAdapter<Category> {
        private List<Category> mCategoryList;
        private Context mContext;
        private int mResource;

        class ViewHolder {
            TextView mCategoryDescription;
            TextView mCategoryTitle;

            ViewHolder() {
            }
        }

        CategoryListAdapter(Context context, int resource, List<Category> recipeList) {
            super(context, resource, recipeList);
            this.mContext = context;
            this.mResource = resource;
            this.mCategoryList = recipeList;
        }

        public void setCategoryList(List<Category> categoryList) {
            Ensighten.evaluateEvent(this, "setCategoryList", new Object[]{categoryList});
            this.mCategoryList = categoryList;
        }

        public Category getItem(int position) {
            return (Category) this.mCategoryList.get(position);
        }

        public int getCount() {
            Ensighten.evaluateEvent(this, "getCount", null);
            return this.mCategoryList.size();
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = ((Activity) this.mContext).getLayoutInflater().inflate(this.mResource, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.mCategoryTitle = (TextView) convertView.findViewById(C2358R.C2357id.category_title);
                viewHolder.mCategoryDescription = (TextView) convertView.findViewById(C2358R.C2357id.category_description);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            DataLayerClickListener.setDataLayerTag(convertView, ViewHolder.class, position);
            viewHolder.mCategoryTitle.setText(((Category) this.mCategoryList.get(position)).getName());
            viewHolder.mCategoryDescription.setText(((Category) this.mCategoryList.get(position)).getCategoryDescription());
            Ensighten.getViewReturnValue(convertView, position);
            Ensighten.processView((Object) this, "getView");
            Ensighten.getViewReturnValue(null, -1);
            return convertView;
        }
    }

    public interface OnCategoryFragmentInteractionListener {
        void onCategoryListItemClick(Category category);
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
            TraceMachine.enterMethod(this._nr_trace, "AllCategoriesFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "AllCategoriesFragment#onCreateView", null);
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

    static /* synthetic */ List access$002(AllCategoriesFragment x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.AllCategoriesFragment", "access$002", new Object[]{x0, x1});
        x0.mCategoryList = x1;
        return x1;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("AllCategoriesFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "AllCategoriesFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "AllCategoriesFragment#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        this.mCategoryList = new ArrayList();
        this.mCategoryListAdapter = new CategoryListAdapter(getActivity(), C2658R.layout.category_list_item, this.mCategoryList);
        this.mOrderingModule = new OrderingModule(getActivity());
        setListAdapter(this.mCategoryListAdapter);
        this.mOrderingModule.getAllCategories(new C32911());
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public void onAttach(Activity activity) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onAttach", new Object[]{activity});
        super.onAttach(activity);
        try {
            this.mListener = (OnCategoryFragmentInteractionListener) activity;
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
            this.mListener.onCategoryListItemClick(this.mCategoryListAdapter.getItem(position));
        }
    }
}
