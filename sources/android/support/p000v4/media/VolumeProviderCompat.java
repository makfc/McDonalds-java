package android.support.p000v4.media;

import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.p000v4.media.VolumeProviderCompatApi21.Delegate;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* renamed from: android.support.v4.media.VolumeProviderCompat */
public abstract class VolumeProviderCompat {
    public static final int VOLUME_CONTROL_ABSOLUTE = 2;
    public static final int VOLUME_CONTROL_FIXED = 0;
    public static final int VOLUME_CONTROL_RELATIVE = 1;
    private Callback mCallback;
    private final int mControlType;
    private int mCurrentVolume;
    private final int mMaxVolume;
    private Object mVolumeProviderObj;

    /* renamed from: android.support.v4.media.VolumeProviderCompat$1 */
    class C02291 implements Delegate {
        C02291() {
        }

        public void onSetVolumeTo(int volume) {
            VolumeProviderCompat.this.onSetVolumeTo(volume);
        }

        public void onAdjustVolume(int direction) {
            VolumeProviderCompat.this.onAdjustVolume(direction);
        }
    }

    /* renamed from: android.support.v4.media.VolumeProviderCompat$Callback */
    public static abstract class Callback {
        public abstract void onVolumeChanged(VolumeProviderCompat volumeProviderCompat);
    }

    @RestrictTo
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.media.VolumeProviderCompat$ControlType */
    public @interface ControlType {
    }

    public VolumeProviderCompat(int volumeControl, int maxVolume, int currentVolume) {
        this.mControlType = volumeControl;
        this.mMaxVolume = maxVolume;
        this.mCurrentVolume = currentVolume;
    }

    public final int getCurrentVolume() {
        return this.mCurrentVolume;
    }

    public final int getVolumeControl() {
        return this.mControlType;
    }

    public final int getMaxVolume() {
        return this.mMaxVolume;
    }

    public final void setCurrentVolume(int currentVolume) {
        this.mCurrentVolume = currentVolume;
        Object volumeProviderObj = getVolumeProvider();
        if (volumeProviderObj != null) {
            VolumeProviderCompatApi21.setCurrentVolume(volumeProviderObj, currentVolume);
        }
        if (this.mCallback != null) {
            this.mCallback.onVolumeChanged(this);
        }
    }

    public void onSetVolumeTo(int volume) {
    }

    public void onAdjustVolume(int direction) {
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public Object getVolumeProvider() {
        if (this.mVolumeProviderObj != null || VERSION.SDK_INT < 21) {
            return this.mVolumeProviderObj;
        }
        this.mVolumeProviderObj = VolumeProviderCompatApi21.createVolumeProvider(this.mControlType, this.mMaxVolume, this.mCurrentVolume, new C02291());
        return this.mVolumeProviderObj;
    }
}
