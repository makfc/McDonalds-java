package com.amap.api.mapcore.util;

import android.content.Context;
import com.autonavi.amap.mapcore.MapCore;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager.RetStyleIconsFile;

/* renamed from: com.amap.api.mapcore.util.u */
public class GLMapResManager {
    /* renamed from: a */
    public boolean f2165a = true;
    /* renamed from: b */
    private AMapDelegateImp f2166b = null;
    /* renamed from: c */
    private Context f2167c = null;
    /* renamed from: d */
    private MapCore f2168d = null;

    /* compiled from: GLMapResManager */
    /* renamed from: com.amap.api.mapcore.util.u$a */
    public enum C0872a {
        NORAML,
        SATELLITE,
        BUS
    }

    /* compiled from: GLMapResManager */
    /* renamed from: com.amap.api.mapcore.util.u$b */
    public enum C0873b {
        NORMAL,
        PREVIEW_CAR,
        PREVIEW_BUS,
        PREVIEW_FOOT,
        NAVI_CAR,
        NAVI_BUS,
        NAVI_FOOT
    }

    /* compiled from: GLMapResManager */
    /* renamed from: com.amap.api.mapcore.util.u$c */
    public enum C0874c {
        DAY,
        NIGHT
    }

    public GLMapResManager(AMapDelegateImp aMapDelegateImp, Context context) {
        this.f2166b = aMapDelegateImp;
        this.f2167c = context;
        this.f2168d = this.f2166b.getMapCore();
    }

    /* renamed from: a */
    public void mo9550a() {
        if (this.f2166b != null) {
            RetStyleIconsFile retStyleIconsFile = new RetStyleIconsFile();
            byte[] styleData = MapTilsCacheAndResManager.getInstance(this.f2167c).getStyleData(mo9552b(), retStyleIconsFile);
            if (styleData != null) {
                this.f2168d.setStyleData(styleData, 0, 1);
            }
            byte[] styleData2 = MapTilsCacheAndResManager.getInstance(this.f2167c).getStyleData("style_50_7_1445670996.data", retStyleIconsFile);
            if (styleData2 != null) {
                this.f2168d.setStyleData(styleData2, 1, 1);
            }
        }
    }

    /* renamed from: a */
    public void mo9551a(boolean z) {
        if (this.f2166b != null) {
            byte[] bArr = null;
            RetStyleIconsFile retStyleIconsFile = new RetStyleIconsFile();
            String c = mo9554c();
            String a = m2907a(c);
            final byte[] iconsData = MapTilsCacheAndResManager.getInstance(this.f2167c).getIconsData(c, retStyleIconsFile);
            if (this.f2165a) {
                bArr = MapTilsCacheAndResManager.getInstance(this.f2167c).getIconsData(a, new RetStyleIconsFile());
            }
            final byte[] iconsData2 = MapTilsCacheAndResManager.getInstance(this.f2167c).getIconsData("icons_50_7_1444880375.data", retStyleIconsFile);
            if (z) {
                if (iconsData != null) {
                    this.f2168d.setInternaltexture(iconsData, 0);
                }
                if (iconsData2 != null) {
                    this.f2168d.setInternaltexture(iconsData2, 31);
                }
                if (this.f2165a && bArr != null) {
                    this.f2168d.setInternaltexture(bArr, 20);
                    return;
                }
                return;
            }
            this.f2166b.queueEvent(new Runnable() {
                public void run() {
                    if (iconsData != null) {
                        GLMapResManager.this.f2168d.setInternaltexture(iconsData, 0);
                    }
                    if (iconsData2 != null) {
                        GLMapResManager.this.f2168d.setInternaltexture(iconsData2, 31);
                    }
                    if (GLMapResManager.this.f2165a && bArr != null) {
                        GLMapResManager.this.f2168d.setInternaltexture(bArr, 20);
                    }
                }
            });
        }
    }

    /* renamed from: a */
    private String m2907a(String str) {
        if (str.equals("icons_1_7_1444880368.data")) {
            this.f2165a = true;
            return "icons_4_6_1437480571.data";
        }
        this.f2165a = false;
        return null;
    }

    /* renamed from: b */
    public String mo9552b() {
        String str = "";
        if (this.f2166b == null) {
            return str;
        }
        C0874c h = this.f2166b.mo9178h();
        C0872a i = this.f2166b.mo9179i();
        C0873b j = this.f2166b.mo9180j();
        if (C0874c.DAY == h) {
            if (C0872a.NORAML == i) {
                if (C0873b.NAVI_CAR == j) {
                    return "style_4_7_1445391691.data";
                }
                if (C0873b.PREVIEW_BUS == j) {
                    return "style_6_7_1445325996.data";
                }
                if (C0873b.PREVIEW_CAR == j) {
                    return "style_8_7_1445391734.data";
                }
                if (C0873b.NAVI_BUS == j) {
                    return "style_9_7_1445327958.data";
                }
                return "style_1_7_1445219169.data";
            } else if (C0872a.SATELLITE == i) {
                if (C0873b.NAVI_CAR == j) {
                    return "style_4_7_1445391691.data";
                }
                if (C0873b.PREVIEW_BUS == j) {
                    return "style_6_7_1445325996.data";
                }
                return "style_3_7_1445827513.data";
            } else if (C0872a.BUS != i) {
                return str;
            } else {
                if (C0873b.NAVI_CAR == j) {
                    return "style_4_7_1445391691.data";
                }
                if (C0873b.PREVIEW_BUS == j) {
                    return "style_6_7_1445325996.data";
                }
                return "style_6_7_1445325996.data";
            }
        } else if (C0874c.NIGHT != h) {
            return str;
        } else {
            if (C0872a.NORAML == i) {
                if (C0873b.NAVI_CAR == j) {
                    return "style_5_7_1445391719.data";
                }
                if (C0873b.PREVIEW_BUS == j) {
                    return "style_6_7_1445325996.data";
                }
                return "style_1_7_1445219169.data";
            } else if (C0872a.SATELLITE == i) {
                if (C0873b.NAVI_CAR == j) {
                    return "style_5_7_1445391719.data";
                }
                if (C0873b.PREVIEW_BUS == j) {
                    return "style_6_7_1445325996.data";
                }
                return "style_3_7_1445827513.data";
            } else if (C0872a.BUS != i) {
                return str;
            } else {
                if (C0873b.NAVI_CAR == j) {
                    return "style_5_7_1445391719.data";
                }
                if (C0873b.PREVIEW_BUS == j) {
                    return "style_6_7_1445325996.data";
                }
                return "style_6_7_1445325996.data";
            }
        }
    }

    /* renamed from: c */
    public String mo9554c() {
        String str = "";
        if (this.f2166b == null) {
            return str;
        }
        C0874c h = this.f2166b.mo9178h();
        C0872a i = this.f2166b.mo9179i();
        if (C0874c.DAY == h) {
            if (C0872a.BUS == i) {
                return "icons_3_7_1444880372.data";
            }
            return "icons_1_7_1444880368.data";
        } else if (C0874c.NIGHT != h) {
            return str;
        } else {
            if (C0872a.BUS == i) {
                return "icons_3_7_1444880372.data";
            }
            return "icons_2_7_1445580283.data";
        }
    }

    /* renamed from: b */
    public void mo9553b(boolean z) {
        byte[] otherResData;
        byte[] otherResData2;
        byte[] otherResData3;
        byte[] otherResData4;
        byte[] otherResData5;
        if (this.f2166b.mo9178h() != C0874c.NIGHT) {
            otherResData = MapTilsCacheAndResManager.getInstance(this.f2167c).getOtherResData("tgl_l.data");
            otherResData2 = MapTilsCacheAndResManager.getInstance(this.f2167c).getOtherResData("trl_l.data");
            otherResData3 = MapTilsCacheAndResManager.getInstance(this.f2167c).getOtherResData("tyl_l.data");
            otherResData4 = MapTilsCacheAndResManager.getInstance(this.f2167c).getOtherResData("tbl_l.data");
            otherResData5 = MapTilsCacheAndResManager.getInstance(this.f2167c).getOtherResData("tnl_l.data");
        } else {
            otherResData = MapTilsCacheAndResManager.getInstance(this.f2167c).getOtherResData("tgl_n.data");
            otherResData2 = MapTilsCacheAndResManager.getInstance(this.f2167c).getOtherResData("trl_n.data");
            otherResData3 = MapTilsCacheAndResManager.getInstance(this.f2167c).getOtherResData("tyl_n.data");
            otherResData4 = MapTilsCacheAndResManager.getInstance(this.f2167c).getOtherResData("tbl_n.data");
            otherResData5 = MapTilsCacheAndResManager.getInstance(this.f2167c).getOtherResData("tnl_n.data");
        }
        if (z) {
            m2909a(otherResData, otherResData2, otherResData3, otherResData4, otherResData5);
            return;
        }
        final byte[] bArr = otherResData;
        final byte[] bArr2 = otherResData2;
        final byte[] bArr3 = otherResData3;
        final byte[] bArr4 = otherResData4;
        final byte[] bArr5 = otherResData5;
        this.f2166b.queueEvent(new Runnable() {
            public void run() {
                GLMapResManager.this.m2909a(bArr, bArr2, bArr3, bArr4, bArr5);
            }
        });
    }

    /* renamed from: a */
    private void m2909a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        if (bArr != null) {
            this.f2168d.setInternaltexture(bArr, 6);
        }
        if (bArr2 != null) {
            this.f2168d.setInternaltexture(bArr2, 4);
        }
        if (bArr3 != null) {
            this.f2168d.setInternaltexture(bArr3, 5);
        }
        if (bArr4 != null) {
            this.f2168d.setInternaltexture(bArr4, 7);
        }
        if (bArr5 != null) {
            this.f2168d.setInternaltexture(bArr5, 18);
        }
    }

    /* renamed from: c */
    public void mo9555c(boolean z) {
        byte[] otherResData;
        byte[] otherResData2;
        if (this.f2166b.mo9178h() != C0874c.NIGHT) {
            otherResData = MapTilsCacheAndResManager.getInstance(this.f2167c).getOtherResData("bktile.data");
            otherResData2 = MapTilsCacheAndResManager.getInstance(this.f2167c).getOtherResData("3d_sky_day.dat");
        } else {
            otherResData = MapTilsCacheAndResManager.getInstance(this.f2167c).getOtherResData("bktile_n.data");
            otherResData2 = MapTilsCacheAndResManager.getInstance(this.f2167c).getOtherResData("3d_sky_night.dat");
        }
        if (z) {
            this.f2168d.setInternaltexture(otherResData, 1);
            this.f2168d.setInternaltexture(otherResData2, 41);
            return;
        }
        this.f2166b.queueEvent(new Runnable() {
            public void run() {
                GLMapResManager.this.f2168d.setInternaltexture(otherResData, 1);
                GLMapResManager.this.f2168d.setInternaltexture(otherResData2, 41);
            }
        });
    }

    /* renamed from: d */
    public void mo9556d(boolean z) {
    }

    /* renamed from: e */
    public void mo9557e(boolean z) {
        final byte[] otherResData = MapTilsCacheAndResManager.getInstance(this.f2167c).getOtherResData("roadarrow.data");
        final byte[] otherResData2 = MapTilsCacheAndResManager.getInstance(this.f2167c).getOtherResData("lineround.data");
        final byte[] otherResData3 = MapTilsCacheAndResManager.getInstance(this.f2167c).getOtherResData("dash.data");
        final byte[] otherResData4 = MapTilsCacheAndResManager.getInstance(this.f2167c).getOtherResData("dash_tq.data");
        final byte[] otherResData5 = MapTilsCacheAndResManager.getInstance(this.f2167c).getOtherResData("dash_cd.data");
        if (z) {
            this.f2168d.setInternaltexture(otherResData, 2);
            this.f2168d.setInternaltexture(otherResData2, 3);
            this.f2168d.setInternaltexture(otherResData3, 8);
            this.f2168d.setInternaltexture(otherResData4, 9);
            this.f2168d.setInternaltexture(otherResData5, 10);
            return;
        }
        this.f2166b.queueEvent(new Runnable() {
            public void run() {
                GLMapResManager.this.f2168d.setInternaltexture(otherResData, 2);
                GLMapResManager.this.f2168d.setInternaltexture(otherResData2, 3);
                GLMapResManager.this.f2168d.setInternaltexture(otherResData3, 8);
                GLMapResManager.this.f2168d.setInternaltexture(otherResData4, 9);
                GLMapResManager.this.f2168d.setInternaltexture(otherResData5, 10);
            }
        });
    }
}
