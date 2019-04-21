package com.mcdonalds.app.p043ui.font;

import com.ensighten.Ensighten;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.configuration.Configuration;
import p041io.github.inflationx.calligraphy3.CalligraphyConfig.Builder;
import p041io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import p041io.github.inflationx.viewpump.ViewPump;

/* renamed from: com.mcdonalds.app.ui.font.FontManager */
public class FontManager {
    public static String getFontSpecifications() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.font.FontManager", "getFontSpecifications", null);
        return "fonts/" + ((String) Configuration.getSharedInstance().getValueForKey("interface.fontSpecification.android.normalFont"));
    }

    public static void initializeFonts() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.font.FontManager", "initializeFonts", null);
        ViewPump.init(ViewPump.builder().addInterceptor(new CalligraphyInterceptor(new Builder().setDefaultFontPath(FontManager.getFontSpecifications()).setFontAttrId(C2658R.attr.fontPath).build())).build());
    }
}
