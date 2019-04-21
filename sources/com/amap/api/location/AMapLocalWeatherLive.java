package com.amap.api.location;

import com.amap.api.location.core.AMapLocException;

public class AMapLocalWeatherLive {
    /* renamed from: a */
    private String f790a;
    /* renamed from: b */
    private String f791b;
    /* renamed from: c */
    private String f792c;
    /* renamed from: d */
    private String f793d;
    /* renamed from: e */
    private String f794e;
    /* renamed from: f */
    private String f795f;
    /* renamed from: g */
    private AMapLocException f796g;
    /* renamed from: h */
    private String f797h;
    /* renamed from: i */
    private String f798i;
    /* renamed from: j */
    private String f799j;

    public String getCity() {
        return this.f797h;
    }

    public void setCity(String str) {
        this.f797h = str;
    }

    public String getProvince() {
        return this.f798i;
    }

    public void setProvince(String str) {
        this.f798i = str;
    }

    public String getCityCode() {
        return this.f799j;
    }

    public void setCityCode(String str) {
        this.f799j = str;
    }

    public AMapLocException getAMapException() {
        return this.f796g;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8249a(AMapLocException aMapLocException) {
        this.f796g = aMapLocException;
    }

    public String getWeather() {
        return this.f790a;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8250a(String str) {
        this.f790a = str;
    }

    public String getTemperature() {
        return this.f791b;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo8251b(String str) {
        this.f791b = str;
    }

    public String getWindDir() {
        return this.f792c;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: c */
    public void mo8252c(String str) {
        this.f792c = str;
    }

    public String getWindPower() {
        return this.f793d;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: d */
    public void mo8253d(String str) {
        this.f793d = str;
    }

    public String getHumidity() {
        return this.f794e;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: e */
    public void mo8254e(String str) {
        this.f794e = str;
    }

    public String getReportTime() {
        return this.f795f;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: f */
    public void mo8255f(String str) {
        this.f795f = str;
    }
}
