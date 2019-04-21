package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableParcelable<T extends Parcelable> extends ObservableField<T> implements Parcelable, Serializable {
    public static final Creator<ObservableParcelable> CREATOR = new C00101();

    /* renamed from: android.databinding.ObservableParcelable$1 */
    static class C00101 implements Creator<ObservableParcelable> {
        C00101() {
        }

        public ObservableParcelable createFromParcel(Parcel source) {
            return new ObservableParcelable(source.readParcelable(getClass().getClassLoader()));
        }

        public ObservableParcelable[] newArray(int size) {
            return new ObservableParcelable[size];
        }
    }

    public ObservableParcelable(T value) {
        super(value);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) get(), 0);
    }
}
