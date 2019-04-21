package android.support.p001v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.support.p000v4.view.ActionProvider;
import android.support.p001v7.appcompat.C0334R;
import android.support.p001v7.content.res.AppCompatResources;
import android.support.p001v7.widget.ActivityChooserModel.OnChooseActivityListener;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;

/* renamed from: android.support.v7.widget.ShareActionProvider */
public class ShareActionProvider extends ActionProvider {
    final Context mContext;
    private int mMaxShownActivityCount;
    private final ShareMenuItemOnMenuItemClickListener mOnMenuItemClickListener;
    OnShareTargetSelectedListener mOnShareTargetSelectedListener;
    String mShareHistoryFileName;

    /* renamed from: android.support.v7.widget.ShareActionProvider$OnShareTargetSelectedListener */
    public interface OnShareTargetSelectedListener {
        boolean onShareTargetSelected(ShareActionProvider shareActionProvider, Intent intent);
    }

    /* renamed from: android.support.v7.widget.ShareActionProvider$ShareActivityChooserModelPolicy */
    private class ShareActivityChooserModelPolicy implements OnChooseActivityListener {
        final /* synthetic */ ShareActionProvider this$0;

        public boolean onChooseActivity(ActivityChooserModel host, Intent intent) {
            if (this.this$0.mOnShareTargetSelectedListener != null) {
                this.this$0.mOnShareTargetSelectedListener.onShareTargetSelected(this.this$0, intent);
            }
            return false;
        }
    }

    /* renamed from: android.support.v7.widget.ShareActionProvider$ShareMenuItemOnMenuItemClickListener */
    private class ShareMenuItemOnMenuItemClickListener implements OnMenuItemClickListener {
        final /* synthetic */ ShareActionProvider this$0;

        public boolean onMenuItemClick(MenuItem item) {
            Intent launchIntent = ActivityChooserModel.get(this.this$0.mContext, this.this$0.mShareHistoryFileName).chooseActivity(item.getItemId());
            if (launchIntent != null) {
                String action = launchIntent.getAction();
                if ("android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action)) {
                    this.this$0.updateIntent(launchIntent);
                }
                this.this$0.mContext.startActivity(launchIntent);
            }
            return true;
        }
    }

    public View onCreateActionView() {
        ActivityChooserView activityChooserView = new ActivityChooserView(this.mContext);
        if (!activityChooserView.isInEditMode()) {
            activityChooserView.setActivityChooserModel(ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName));
        }
        TypedValue outTypedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(C0334R.attr.actionModeShareDrawable, outTypedValue, true);
        activityChooserView.setExpandActivityOverflowButtonDrawable(AppCompatResources.getDrawable(this.mContext, outTypedValue.resourceId));
        activityChooserView.setProvider(this);
        activityChooserView.setDefaultActionButtonContentDescription(C0334R.string.abc_shareactionprovider_share_with_application);
        activityChooserView.setExpandActivityOverflowButtonContentDescription(C0334R.string.abc_shareactionprovider_share_with);
        return activityChooserView;
    }

    public boolean hasSubMenu() {
        return true;
    }

    public void onPrepareSubMenu(SubMenu subMenu) {
        int i;
        ResolveInfo activity;
        subMenu.clear();
        ActivityChooserModel dataModel = ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName);
        PackageManager packageManager = this.mContext.getPackageManager();
        int expandedActivityCount = dataModel.getActivityCount();
        int collapsedActivityCount = Math.min(expandedActivityCount, this.mMaxShownActivityCount);
        for (i = 0; i < collapsedActivityCount; i++) {
            activity = dataModel.getActivity(i);
            subMenu.add(0, i, i, activity.loadLabel(packageManager)).setIcon(activity.loadIcon(packageManager)).setOnMenuItemClickListener(this.mOnMenuItemClickListener);
        }
        if (collapsedActivityCount < expandedActivityCount) {
            SubMenu expandedSubMenu = subMenu.addSubMenu(0, collapsedActivityCount, collapsedActivityCount, this.mContext.getString(C0334R.string.abc_activity_chooser_view_see_all));
            for (i = 0; i < expandedActivityCount; i++) {
                activity = dataModel.getActivity(i);
                expandedSubMenu.add(0, i, i, activity.loadLabel(packageManager)).setIcon(activity.loadIcon(packageManager)).setOnMenuItemClickListener(this.mOnMenuItemClickListener);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void updateIntent(Intent intent) {
        if (VERSION.SDK_INT >= 21) {
            intent.addFlags(134742016);
        } else {
            intent.addFlags(524288);
        }
    }
}
