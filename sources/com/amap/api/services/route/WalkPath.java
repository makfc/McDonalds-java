package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class WalkPath extends Path implements Parcelable {
    public static final Creator<WalkPath> CREATOR = new C1191t();
    /* renamed from: a */
    private List<WalkStep> f4091a = new ArrayList();

    public List<WalkStep> getSteps() {
        return this.f4091a;
    }

    public void setSteps(List<WalkStep> list) {
        this.f4091a = list;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.f4091a);
    }

    public WalkPath(Parcel parcel) {
        super(parcel);
        this.f4091a = parcel.createTypedArrayList(WalkStep.CREATOR);
    }
}
