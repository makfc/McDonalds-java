package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableDouble extends BaseObservable implements Parcelable, Serializable {
    public static final Creator<ObservableDouble> CREATOR = new C00061();
    private double mValue;

    /* renamed from: android.databinding.ObservableDouble$1 */
    static class C00061 implements Creator<ObservableDouble> {
        C00061() {
        }

        public ObservableDouble createFromParcel(Parcel source) {
            return new ObservableDouble(source.readDouble());
        }

        public ObservableDouble[] newArray(int size) {
            return new ObservableDouble[size];
        }
    }

    public ObservableDouble(double value) {
        this.mValue = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.mValue);
    }
}
