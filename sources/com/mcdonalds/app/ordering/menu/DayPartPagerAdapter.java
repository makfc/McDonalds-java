package com.mcdonalds.app.ordering.menu;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.support.p000v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.util.DownloadBitmap;
import com.mcdonalds.app.util.LanguageUtil;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.MenuTypeCalendarItem;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.provider.Contract.MenuTypes;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;
import com.mcdonalds.sdk.utils.DateUtils;
import java.util.ArrayList;
import java.util.List;

public class DayPartPagerAdapter extends PagerAdapter {
    private Context mContext;
    private ContentObserver mMenuTypeObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean selfChange) {
            Ensighten.evaluateEvent(this, "onChange", new Object[]{new Boolean(selfChange)});
            DayPartPagerAdapter.this.refresh();
        }
    };
    private List<MenuType> mMenuTypes;
    private RequestManagerServiceConnection mServiceConnection;
    private Store mStore = OrderManager.getInstance().getCurrentStore();

    static /* synthetic */ void access$000(DayPartPagerAdapter x0, ImageView x1, String x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.DayPartPagerAdapter", "access$000", new Object[]{x0, x1, x2});
        x0.setDayPartImage(x1, x2);
    }

    public DayPartPagerAdapter(Context context, RequestManagerServiceConnection serviceConnection) {
        this.mServiceConnection = serviceConnection;
        this.mContext = context;
        setMenuTypes();
    }

    private void setMenuTypes() {
        Ensighten.evaluateEvent(this, "setMenuTypes", null);
        this.mMenuTypes = new ArrayList();
        boolean isDelivery = OrderManager.getInstance().getCurrentOrder().isDelivery();
        for (MenuType menuType : ((OrderingModule) ModuleManager.getModule("ordering")).getMenuTypes()) {
            if (this.mStore.getMenuTypeCalendarItem(menuType.getID(), isDelivery) != null) {
                this.mMenuTypes.add(menuType);
            }
        }
    }

    public void registerObserver() {
        Ensighten.evaluateEvent(this, "registerObserver", null);
        this.mContext.getContentResolver().registerContentObserver(MenuTypes.CONTENT_URI, true, this.mMenuTypeObserver);
    }

    public void deregisterObserver() {
        Ensighten.evaluateEvent(this, "deregisterObserver", null);
        this.mContext.getContentResolver().unregisterContentObserver(this.mMenuTypeObserver);
    }

    public void refresh() {
        Ensighten.evaluateEvent(this, "refresh", null);
        setMenuTypes();
        notifyDataSetChanged();
    }

    public int getCount() {
        Ensighten.evaluateEvent(this, "getCount", null);
        return this.mMenuTypes.size();
    }

    public boolean isViewFromObject(View view, Object object) {
        Ensighten.evaluateEvent(this, "isViewFromObject", new Object[]{view, object});
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        Ensighten.evaluateEvent(this, "instantiateItem", new Object[]{container, new Integer(position)});
        View v = LayoutInflater.from(this.mContext).inflate(C2658R.layout.day_part_pager_item, container, false);
        TextView carouselText = (TextView) v.findViewById(C2358R.C2357id.carousel_text);
        TextView mNowServing = (TextView) v.findViewById(C2358R.C2357id.now_serving);
        ImageView dayPartIcon = (ImageView) v.findViewById(C2358R.C2357id.day_part_icon);
        ImageView pagerImage = (ImageView) v.findViewById(C2358R.C2357id.pager_image);
        TextView availabilityTextView = (TextView) v.findViewById(C2358R.C2357id.availability_text);
        TextView menuEndingTextView = (TextView) v.findViewById(C2358R.C2357id.menu_ending_text);
        MenuType menuType = (MenuType) this.mMenuTypes.get(position);
        String menuTypeName = "";
        if (menuType.getShortName() != null) {
            menuTypeName = menuType.getShortName().toUpperCase();
        } else if (menuType.getDescription() != null) {
            menuTypeName = menuType.getDescription();
        }
        carouselText.setText(getDayPartText(menuTypeName));
        int dayPartIconResource = getDayPartIconResource(menuTypeName);
        if (dayPartIconResource != 0) {
            dayPartIcon.setImageResource(dayPartIconResource);
        }
        String availabilityText = getDayPartText(menuType);
        if (availabilityText != null) {
            availabilityTextView.setText(availabilityText);
        }
        String menuEndingText = getMenuEndingText(menuType);
        if (menuEndingText != null) {
            menuEndingTextView.setVisibility(0);
            menuEndingTextView.setText(menuEndingText);
        } else {
            menuEndingTextView.setVisibility(8);
        }
        setDisplayImageForDayPart(pagerImage, position);
        boolean nowServing = false;
        if (this.mStore != null) {
            nowServing = this.mStore.getCurrentMenuTypeID(isDelivery()) == menuType.getID();
        }
        if (!nowServing || TextUtils.isEmpty(this.mContext.getString(C2658R.string.now_serving))) {
            mNowServing.setVisibility(4);
        } else {
            mNowServing.setVisibility(0);
        }
        container.addView(v);
        return v;
    }

    private boolean isDelivery() {
        Ensighten.evaluateEvent(this, "isDelivery", null);
        return OrderingManager.getInstance().getCurrentOrder().isDelivery();
    }

    public void destroyItem(ViewGroup container, int position, Object view) {
        Ensighten.evaluateEvent(this, "destroyItem", new Object[]{container, new Integer(position), view});
        container.removeView((View) view);
    }

    public int getItemPosition(Object object) {
        Ensighten.evaluateEvent(this, "getItemPosition", new Object[]{object});
        return -2;
    }

    public void setStore(Store store) {
        Ensighten.evaluateEvent(this, "setStore", new Object[]{store});
        this.mStore = store;
        notifyDataSetChanged();
    }

    public MenuType getMenuTypeForPosition(int position) {
        Ensighten.evaluateEvent(this, "getMenuTypeForPosition", new Object[]{new Integer(position)});
        if (position < this.mMenuTypes.size()) {
            return (MenuType) this.mMenuTypes.get(position);
        }
        return null;
    }

    public int getMenuTypePosition(MenuType menuType) {
        Ensighten.evaluateEvent(this, "getMenuTypePosition", new Object[]{menuType});
        return this.mMenuTypes.indexOf(menuType);
    }

    public int getPositionForMenuTypeId(int menuTypeId) {
        Ensighten.evaluateEvent(this, "getPositionForMenuTypeId", new Object[]{new Integer(menuTypeId)});
        MenuType menuType = new MenuType();
        menuType.setID(menuTypeId);
        return getMenuTypePosition(menuType);
    }

    public String getDayPartText(String menuTypeName) {
        Ensighten.evaluateEvent(this, "getDayPartText", new Object[]{menuTypeName});
        if (menuTypeName.contains("BREAKFAST") || menuTypeName.contains("早餐")) {
            return this.mContext.getString(C2658R.string.daypart_breakfast);
        }
        if (menuTypeName.contains("REGULAR") || menuTypeName.contains("正餐") || menuTypeName.contains("LUNCH")) {
            return this.mContext.getString(C2658R.string.daypart_lunch_dinner);
        }
        if (LanguageUtil.getAppLanguage().equals("en")) {
            return "NIGHT";
        }
        return "晚餐";
    }

    public int getDayPartIconResource(String menuTypeName) {
        Ensighten.evaluateEvent(this, "getDayPartIconResource", new Object[]{menuTypeName});
        if (menuTypeName.contains("BREAKFAST") || menuTypeName.contains("早餐")) {
            return C2358R.C2359drawable.day_switcher_breakfast_icon;
        }
        if (menuTypeName.contains("REGULAR") || menuTypeName.contains("正餐") || menuTypeName.contains("LUNCH")) {
            return C2358R.C2359drawable.day_switcher_lunchdinner_icon;
        }
        if (menuTypeName.contains("NIGHT") || menuTypeName.contains("晚餐")) {
            return C2358R.C2359drawable.day_switcher_latenight_icon;
        }
        return 0;
    }

    public String getDayPartText(MenuType menuType) {
        Ensighten.evaluateEvent(this, "getDayPartText", new Object[]{menuType});
        MenuTypeCalendarItem menuTypeCalendarItem = this.mStore.getMenuTypeCalendarItem(menuType.getID(), isDelivery());
        if (menuTypeCalendarItem != null) {
            return this.mContext.getString(C2658R.string.available) + " " + DateUtils.format24HourTimeToSystemFormat(menuTypeCalendarItem.getFromTime(), this.mContext) + " - " + DateUtils.format24HourTimeToSystemFormat(menuTypeCalendarItem.getToTime(), this.mContext);
        }
        return null;
    }

    public String getMenuEndingText(MenuType menuType) {
        Ensighten.evaluateEvent(this, "getMenuEndingText", new Object[]{menuType});
        String alertTimeConfig = (String) Configuration.getSharedInstance().getValueForKey("interface.dayparts.daypartEndingAlertTime");
        if (!(alertTimeConfig == null || this.mStore == null || menuType == null || menuType.getShortName() == null)) {
            long timeLeftInMenu = this.mStore.getMenuEndingTime(menuType, isDelivery());
            long alertTime = Long.valueOf(alertTimeConfig).longValue();
            if (timeLeftInMenu > 0 && timeLeftInMenu <= alertTime) {
                return this.mContext.getString(C2658R.string.label_daypart_menu_warning_ios, new Object[]{menuType.getShortName(), Long.valueOf(timeLeftInMenu)});
            }
        }
        return null;
    }

    private void setDisplayImageForDayPart(final ImageView imageView, int position) {
        Ensighten.evaluateEvent(this, "setDisplayImageForDayPart", new Object[]{imageView, new Integer(position)});
        UIUtils.getDayPartImageUrl(this.mServiceConnection, position, new AsyncListener<String>() {
            public void onResponse(String url, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{url, token, exception});
                if (url != null) {
                    DayPartPagerAdapter.access$000(DayPartPagerAdapter.this, imageView, url);
                }
            }
        });
    }

    private void setDayPartImage(ImageView imageView, String imageUrl) {
        Ensighten.evaluateEvent(this, "setDayPartImage", new Object[]{imageView, imageUrl});
        Glide.with(this.mContext.getApplicationContext()).load(imageUrl).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) C2358R.C2359drawable.transparent).into((Target) new DownloadBitmap(this.mContext, imageView));
    }
}
