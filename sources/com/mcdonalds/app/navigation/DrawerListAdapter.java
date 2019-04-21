package com.mcdonalds.app.navigation;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.ImageView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.p043ui.models.DrawerItem;
import com.mcdonalds.gma.hongkong.C2658R;
import java.util.ArrayList;
import java.util.List;

public class DrawerListAdapter extends ArrayAdapter<DrawerItem> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<DrawerItem> mItems;
    private List<DrawerItem> mOriginalItems = new ArrayList(this.mItems);
    private int mResource;
    private List<DrawerItem> mStateDependentItems = new ArrayList(this.mItems);
    private Filter myFilter;

    /* renamed from: com.mcdonalds.app.navigation.DrawerListAdapter$1 */
    class C32881 extends Filter {
        C32881() {
        }

        /* Access modifiers changed, original: protected */
        public FilterResults performFiltering(CharSequence constraint) {
            Ensighten.evaluateEvent(this, "performFiltering", new Object[]{constraint});
            FilterResults results = new FilterResults();
            ArrayList<DrawerItem> filteredItems = new ArrayList();
            boolean loggedIn = false;
            if ("logged_in_state".equalsIgnoreCase(constraint.toString())) {
                loggedIn = true;
            } else if ("logged_out_state".equalsIgnoreCase(constraint.toString())) {
                loggedIn = false;
            }
            for (DrawerItem item : Ensighten.evaluateEvent(null, "com.mcdonalds.app.navigation.DrawerListAdapter", "access$000", new Object[]{DrawerListAdapter.this})) {
                if (item.isRequiresLoginState() == null && item.isRequiresLogoutState() == null) {
                    filteredItems.add(item);
                } else if (item.isRequiresLoginState().booleanValue() && loggedIn) {
                    filteredItems.add(item);
                } else if (item.isRequiresLogoutState() != null && item.isRequiresLogoutState().booleanValue() && !loggedIn) {
                    filteredItems.add(item);
                } else if (!(item.isRequiresLoginState().booleanValue() || loggedIn)) {
                    filteredItems.add(item);
                }
            }
            results.values = filteredItems;
            return results;
        }

        /* Access modifiers changed, original: protected */
        public void publishResults(CharSequence constraint, FilterResults results) {
            Ensighten.evaluateEvent(this, "publishResults", new Object[]{constraint, results});
            DrawerListAdapter.access$102(DrawerListAdapter.this, (ArrayList) results.values);
            DrawerListAdapter.this.clear();
            DrawerListAdapter.this.addAll(Ensighten.evaluateEvent(null, "com.mcdonalds.app.navigation.DrawerListAdapter", "access$100", new Object[]{DrawerListAdapter.this}));
            DrawerListAdapter.this.notifyDataSetChanged();
        }
    }

    static class ViewHolder {
        View mBackground;
        ImageView mLeftImage;
        ImageView mRightImage;
        TextView mTitle;

        ViewHolder() {
        }
    }

    static /* synthetic */ List access$102(DrawerListAdapter x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.navigation.DrawerListAdapter", "access$102", new Object[]{x0, x1});
        x0.mItems = x1;
        return x1;
    }

    public DrawerListAdapter(Context context, int resource, List<DrawerItem> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.mItems = objects;
        this.mInflater = LayoutInflater.from(context);
        for (DrawerItem item : this.mOriginalItems) {
            if (item.isRequiresLoginState() != null) {
                this.mStateDependentItems.add(item);
            }
        }
    }

    public DrawerItem getItem(int position) {
        return (DrawerItem) this.mItems.get(position);
    }

    public int getViewTypeCount() {
        Ensighten.evaluateEvent(this, "getViewTypeCount", null);
        return 2;
    }

    public int getItemViewType(int position) {
        Ensighten.evaluateEvent(this, "getItemViewType", new Object[]{new Integer(position)});
        return getItem(position).isHeading() ? 0 : 1;
    }

    public long getItemId(int position) {
        Ensighten.evaluateEvent(this, "getItemId", new Object[]{new Integer(position)});
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = this.mInflater.inflate(this.mResource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mLeftImage = (ImageView) convertView.findViewById(C2358R.C2357id.drawer_left_image);
            viewHolder.mTitle = (TextView) convertView.findViewById(C2358R.C2357id.drawer_item_title);
            viewHolder.mRightImage = (ImageView) convertView.findViewById(C2358R.C2357id.drawer_right_image);
            viewHolder.mBackground = convertView.findViewById(C2358R.C2357id.navigation_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        DrawerItem item = getItem(position);
        if (item != null) {
            DataLayerClickListener.setDataLayerTag(convertView, item.getDataLayerTitle());
        }
        if (!TextUtils.isEmpty(item.getSectionTitle())) {
            viewHolder.mTitle.setText(item.getSectionTitle());
        }
        if (item.isHeading()) {
            viewHolder.mBackground.setBackgroundResource(C2658R.color.menu_dark_gray_3);
            viewHolder.mRightImage.setVisibility(8);
        } else {
            viewHolder.mBackground.setBackgroundResource(C2358R.C2359drawable.listitem_dark_gray_light_gray_bg);
            viewHolder.mRightImage.setVisibility(8);
            viewHolder.mLeftImage.setVisibility(8);
            if (item.getRightHandImageName() != null) {
                setImageAndVisibilityForItem(item.getRightHandImageName(), viewHolder.mRightImage);
            }
            if (item.getLeftHandImageName() != null) {
                setImageAndVisibilityForItem(item.getLeftHandImageName(), viewHolder.mLeftImage);
            }
        }
        Ensighten.getViewReturnValue(convertView, position);
        Ensighten.processView((Object) this, "getView");
        Ensighten.getViewReturnValue(null, -1);
        return convertView;
    }

    private void setImageAndVisibilityForItem(String imageName, ImageView imageView) {
        Ensighten.evaluateEvent(this, "setImageAndVisibilityForItem", new Object[]{imageName, imageView});
        int imageResource = this.mContext.getResources().getIdentifier(imageName, "drawable", this.mContext.getPackageName());
        if (imageResource != 0) {
            imageView.setImageResource(imageResource);
            imageView.setVisibility(0);
        }
    }

    public Filter getFilter() {
        Ensighten.evaluateEvent(this, "getFilter", null);
        if (this.myFilter == null) {
            this.myFilter = new C32881();
        }
        return this.myFilter;
    }
}
