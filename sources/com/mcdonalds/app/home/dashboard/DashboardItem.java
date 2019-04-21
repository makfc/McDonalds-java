package com.mcdonalds.app.home.dashboard;

import com.ensighten.Ensighten;

public class DashboardItem<T> {
    T mObject;
    int mViewType;

    public DashboardItem(int viewType, T object) {
        this.mViewType = viewType;
        this.mObject = object;
    }

    public boolean equals(Object o) {
        Ensighten.evaluateEvent(this, "equals", new Object[]{o});
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DashboardItem that = (DashboardItem) o;
        if (this.mViewType != that.mViewType) {
            return false;
        }
        if (this.mObject != null) {
            if (this.mObject.equals(that.mObject)) {
                return true;
            }
        } else if (that.mObject == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Ensighten.evaluateEvent(this, "hashCode", null);
        return (this.mViewType * 31) + (this.mObject != null ? this.mObject.hashCode() : 0);
    }

    public int getViewType() {
        Ensighten.evaluateEvent(this, "getViewType", null);
        return this.mViewType;
    }

    public void setViewType(int viewType) {
        Ensighten.evaluateEvent(this, "setViewType", new Object[]{new Integer(viewType)});
        this.mViewType = viewType;
    }

    public T getObject() {
        Ensighten.evaluateEvent(this, "getObject", null);
        return this.mObject;
    }
}
