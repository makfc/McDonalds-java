package com.amap.api.location;

import com.amap.api.location.core.AMapLocException;
import java.util.List;

public class AMapLocalWeatherForecast {
    /* renamed from: a */
    private String f787a;
    /* renamed from: b */
    private List<AMapLocalDayWeatherForecast> f788b;
    /* renamed from: c */
    private AMapLocException f789c;

    public AMapLocException getAMapException() {
        return this.f789c;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8241a(AMapLocException aMapLocException) {
        this.f789c = aMapLocException;
    }

    public String getReportTime() {
        return this.f787a;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8242a(String str) {
        this.f787a = str;
    }

    public List<AMapLocalDayWeatherForecast> getWeatherForecast() {
        return this.f788b;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8243a(List<AMapLocalDayWeatherForecast> list) {
        this.f788b = list;
    }
}
