package com.mcdonalds.sdk.connectors.autonavi;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AutoNaviResponse {
    @SerializedName("count")
    private int count;
    @SerializedName("info")
    private String info;
    @SerializedName("status")
    private int status;
    @SerializedName("datas")
    private List<AutoNaviStore> stores;

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<AutoNaviStore> getStores() {
        return this.stores;
    }

    public void setStores(List<AutoNaviStore> stores) {
        this.stores = stores;
    }
}
