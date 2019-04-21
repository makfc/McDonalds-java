package com.mcdonalds.app.p043ui;

import android.os.Bundle;
import android.support.p000v4.widget.DrawerLayout;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.p043ui.models.DrawerItem;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.gma.hongkong.C2658R;

/* renamed from: com.mcdonalds.app.ui.URLActionBarActivity */
public abstract class URLActionBarActivity extends URLNavigationActivity {
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(C2358R.C2357id.navigation_drawer);
        if (shouldShowHamburgerMenu()) {
            this.mNavigationDrawerFragment.setUp(C2358R.C2357id.navigation_drawer, (DrawerLayout) findViewById(C2358R.C2357id.drawer_layout));
        }
    }

    /* Access modifiers changed, original: protected */
    public int getContentViewResource() {
        Ensighten.evaluateEvent(this, "getContentViewResource", null);
        if (shouldShowHamburgerMenu()) {
            return C2658R.layout.activity_main;
        }
        return C2658R.layout.activity_no_drawer;
    }

    /* Access modifiers changed, original: protected */
    public int getContainerResource() {
        Ensighten.evaluateEvent(this, "getContainerResource", null);
        return C2358R.C2357id.container;
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldShowHamburgerMenu() {
        Ensighten.evaluateEvent(this, "shouldShowHamburgerMenu", null);
        return false;
    }

    public NavigationDrawerFragment getNavigationDrawerFragment() {
        Ensighten.evaluateEvent(this, "getNavigationDrawerFragment", null);
        return this.mNavigationDrawerFragment;
    }

    /* Access modifiers changed, original: protected */
    public void navigationItemClicked(DrawerItem item) {
        Ensighten.evaluateEvent(this, "navigationItemClicked", new Object[]{item});
        super.navigationItemClicked(item);
        String label = item.getAnalyticsTitle();
        if (!(item.getAttributes() == null || item.getAttributes().get("tag") == null || !(item.getAttributes().get("tag") instanceof String))) {
            label = (String) item.getAttributes().get("tag");
        }
        if (label != null && !label.equals("")) {
            SparseArray custom = new SparseArray();
            custom.put(51, label);
            AnalyticsUtils.trackOnClickEvent("/side-menu", label, custom);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu});
        if (getNavigationDrawerFragment() == null || getNavigationDrawerFragment().isDrawerOpen() || !shouldShowHamburgerMenu()) {
            return super.onCreateOptionsMenu(menu);
        }
        getMenuInflater().inflate(C2358R.C2360menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        if (item.getItemId() != C2358R.C2357id.action_hamburger_menu || getNavigationDrawerFragment() == null) {
            return super.onOptionsItemSelected(item);
        }
        AnalyticsUtils.trackHamburgerMenu();
        DataLayerManager.getInstance().logButtonClick("SideMenuPressed");
        getNavigationDrawerFragment().toggleDrawerState();
        return true;
    }
}
