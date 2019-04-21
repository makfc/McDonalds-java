package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableFloat extends BaseObservable implements Parcelable, Serializable {
    public static final Creator<ObservableFloat> CREATOR = new C00071();
    private float mValue;

    /* renamed from: android.databinding.ObservableFloat$1 */
    static class C00071 implements Creator<ObservableFloat> {
        C00071() {
        }

        public ObservableFloat createFromParcel(Parcel source) {
            return new ObservableFloat(source.readFloat());
        }

        public ObservableFloat[] newArray(int size) {
            return new ObservableFloat[size];
        }
    }

    public ObservableFloat(float value) {
        this.mValue = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.mValue);
    }
}
