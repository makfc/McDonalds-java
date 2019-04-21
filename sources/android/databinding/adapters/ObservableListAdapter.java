package android.databinding.adapters;

import android.content.Context;
import android.databinding.ObservableList;
import android.databinding.ObservableList.OnListChangedCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

class ObservableListAdapter<T> extends BaseAdapter {
    private final Context mContext;
    private final int mDropDownResourceId;
    private final LayoutInflater mLayoutInflater;
    private List<T> mList;
    private final int mResourceId;
    private final int mTextViewResourceId;

    /* renamed from: android.databinding.adapters.ObservableListAdapter$1 */
    class C00271 extends OnListChangedCallback {
        final /* synthetic */ ObservableListAdapter this$0;

        public void onChanged(ObservableList observableList) {
            this.this$0.notifyDataSetChanged();
        }

        public void onItemRangeChanged(ObservableList observableList, int i, int i1) {
            this.this$0.notifyDataSetChanged();
        }

        public void onItemRangeInserted(ObservableList observableList, int i, int i1) {
            this.this$0.notifyDataSetChanged();
        }

        public void onItemRangeMoved(ObservableList observableList, int i, int i1, int i2) {
            this.this$0.notifyDataSetChanged();
        }

        public void onItemRangeRemoved(ObservableList observableList, int i, int i1) {
            this.this$0.notifyDataSetChanged();
        }
    }

    public int getCount() {
        return this.mList.size();
    }

    public Object getItem(int position) {
        return this.mList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return getViewForResource(this.mResourceId, position, convertView, parent);
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getViewForResource(this.mDropDownResourceId, position, convertView, parent);
    }

    public View getViewForResource(int resourceId, int position, View convertView, ViewGroup parent) {
        View view;
        CharSequence value;
        if (convertView == null) {
            if (resourceId == 0) {
                convertView = new TextView(this.mContext);
            } else {
                convertView = this.mLayoutInflater.inflate(resourceId, parent, false);
            }
        }
        if (this.mTextViewResourceId == 0) {
            view = convertView;
        } else {
            view = convertView.findViewById(this.mTextViewResourceId);
        }
        TextView text = (TextView) view;
        T item = this.mList.get(position);
        if (item instanceof CharSequence) {
            value = (CharSequence) item;
        } else {
            value = String.valueOf(item);
        }
        text.setText(value);
        return convertView;
    }
}
