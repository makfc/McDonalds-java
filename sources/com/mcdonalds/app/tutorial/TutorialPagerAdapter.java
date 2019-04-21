package com.mcdonalds.app.tutorial;

import android.content.Context;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentStatePagerAdapter;
import com.ensighten.Ensighten;
import com.google.gson.internal.LinkedTreeMap;
import com.mcdonalds.app.util.LanguageUtil;
import com.mcdonalds.app.util.UIUtils;
import java.util.ArrayList;

public class TutorialPagerAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private ArrayList<LinkedTreeMap> mScreens;

    public TutorialPagerAdapter(FragmentManager fm, Context context, ArrayList<LinkedTreeMap> screens) {
        super(fm);
        this.context = context;
        this.mScreens = screens;
    }

    public Fragment getItem(int position) {
        int imageId;
        Ensighten.evaluateEvent(this, "getItem", new Object[]{new Integer(position)});
        if (LanguageUtil.getAppLanguage().equals("zh")) {
            imageId = UIUtils.getDrawableIdByName(this.context, ((String) ((LinkedTreeMap) this.mScreens.get(position)).get("image")) + "_zh");
        } else {
            imageId = UIUtils.getDrawableIdByName(this.context, (String) ((LinkedTreeMap) this.mScreens.get(position)).get("image"));
        }
        return TutorialScreenFragment.newInstance(position, imageId, localizedStringForKey((String) ((LinkedTreeMap) this.mScreens.get(position)).get("caption")), (String) ((LinkedTreeMap) this.mScreens.get(position)).get("imageBackgroundColor"));
    }

    public String localizedStringForKey(String key) {
        Ensighten.evaluateEvent(this, "localizedStringForKey", new Object[]{key});
        if (key == null) {
            return null;
        }
        if (key.startsWith("raw:")) {
            return key.substring(4);
        }
        int resourceId = this.context.getResources().getIdentifier(key, "string", this.context.getPackageName());
        if (resourceId > 0) {
            return this.context.getString(resourceId);
        }
        return key;
    }

    public int getCount() {
        Ensighten.evaluateEvent(this, "getCount", null);
        return this.mScreens.size();
    }
}
