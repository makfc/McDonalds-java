package com.threatmetrix.TrustDefender;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* renamed from: com.threatmetrix.TrustDefender.aj */
class C4492aj {
    /* renamed from: h */
    private static final String f7397h = C4549w.m8585a(C4492aj.class);
    /* renamed from: a */
    public long f7398a = 0;
    /* renamed from: b */
    public long f7399b = 0;
    /* renamed from: c */
    public String f7400c = "";
    /* renamed from: d */
    public final ArrayList<String> f7401d = new ArrayList();
    /* renamed from: e */
    public final ArrayList<URI> f7402e = new ArrayList();
    /* renamed from: f */
    public String f7403f = "";
    /* renamed from: g */
    public int f7404g = 0;

    C4492aj() {
    }

    /* renamed from: a */
    private void m8355a(XmlPullParser xpp) throws XmlPullParserException, IOException {
        int eventType = xpp.next();
        String currentTag = "";
        while (eventType != 1) {
            String str;
            switch (eventType) {
                case 0:
                    str = f7397h;
                    break;
                case 2:
                    currentTag = xpp.getName();
                    break;
                case 3:
                    if (!xpp.getName().equals("PS")) {
                        break;
                    }
                    return;
                case 4:
                    if (currentTag != null) {
                        if (!currentTag.equals("P")) {
                            str = f7397h;
                            new StringBuilder("Found tag content unexpectedly: ").append(currentTag);
                            break;
                        }
                        this.f7401d.add(xpp.getText());
                        break;
                    }
                    break;
                default:
                    str = f7397h;
                    new StringBuilder("Found unexpected event type: ").append(eventType);
                    break;
            }
            eventType = xpp.next();
        }
    }

    /* renamed from: b */
    private void m8356b(XmlPullParser xpp) throws XmlPullParserException, IOException {
        int eventType = xpp.next();
        String currentTag = "";
        while (eventType != 1) {
            String str;
            switch (eventType) {
                case 0:
                    str = f7397h;
                    break;
                case 2:
                    currentTag = xpp.getName();
                    break;
                case 3:
                    if (!xpp.getName().equals("EX")) {
                        break;
                    }
                    return;
                case 4:
                    if (currentTag != null) {
                        if (!currentTag.equals("E")) {
                            str = f7397h;
                            new StringBuilder("Found tag content unexpectedly: ").append(currentTag);
                            break;
                        }
                        try {
                            this.f7402e.add(new URI(xpp.getText()));
                            break;
                        } catch (URISyntaxException e) {
                            str = f7397h;
                            break;
                        }
                    }
                    break;
                default:
                    C4549w.m8594c(f7397h, "Found unexpected event type: " + eventType);
                    break;
            }
            eventType = xpp.next();
        }
    }

    /* renamed from: a */
    public final boolean mo34136a() {
        return (this.f7400c == null || this.f7400c.isEmpty()) ? false : true;
    }

    /* renamed from: a */
    public final void mo34135a(InputStream in) {
        String currentTag = null;
        if (in != null) {
            try {
                XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
                xpp.setInput(new InputStreamReader(in));
                for (int eventType = xpp.getEventType(); eventType != 1; eventType = xpp.next()) {
                    switch (eventType) {
                        case 2:
                            if (!xpp.getName().equals("PS")) {
                                if (!xpp.getName().equals("EX")) {
                                    currentTag = xpp.getName();
                                    break;
                                } else {
                                    m8356b(xpp);
                                    break;
                                }
                            }
                            m8355a(xpp);
                            break;
                        case 3:
                            currentTag = null;
                            break;
                        case 4:
                            if (currentTag != null) {
                                if (!currentTag.equals("w")) {
                                    if (!currentTag.equals("O")) {
                                        if (!currentTag.equals("D")) {
                                            if (!currentTag.equals("cpath")) {
                                                if (!currentTag.equals("Q")) {
                                                    break;
                                                }
                                                this.f7404g = Integer.valueOf(xpp.getText()).intValue();
                                                break;
                                            }
                                            this.f7403f = xpp.getText();
                                            break;
                                        }
                                        this.f7399b = Long.valueOf(xpp.getText()).longValue();
                                        break;
                                    }
                                    this.f7398a = Long.valueOf(xpp.getText()).longValue();
                                    break;
                                }
                                this.f7400c = xpp.getText();
                                break;
                            }
                            break;
                        default:
                            break;
                    }
                }
            } catch (IOException e) {
                String str = f7397h;
            } catch (XmlPullParserException e2) {
                C4549w.m8588a(f7397h, "XML Parse Error", e2);
            }
        }
    }
}
