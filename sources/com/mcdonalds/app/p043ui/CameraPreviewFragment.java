package com.mcdonalds.app.p043ui;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.log.MCDLog;

/* renamed from: com.mcdonalds.app.ui.CameraPreviewFragment */
public class CameraPreviewFragment extends URLNavigationFragment implements PreviewCallback, Callback {
    private static final String LOG_TAG = CameraPreviewFragment.class.getSimpleName();
    private Camera mCamera;
    private boolean mIsPreviewing;
    private boolean mIsSurfaceCreated;
    private SurfaceView mSurface;

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_camera_preview, container, false);
        this.mSurface = (SurfaceView) view.findViewById(C2358R.C2357id.surface);
        this.mSurface.getHolder().addCallback(this);
        return view;
    }

    public void onStop() {
        super.onStop();
        stopPreview();
    }

    public void onStart() {
        super.onStart();
        startPreview(this.mSurface.getHolder());
    }

    public void onDestroy() {
        super.onDestroy();
        stopPreview();
        if (this.mCamera != null) {
            this.mCamera.setPreviewCallback(null);
            this.mCamera.release();
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        Ensighten.evaluateEvent(this, "surfaceCreated", new Object[]{holder});
        this.mIsSurfaceCreated = true;
        startPreview(holder);
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Ensighten.evaluateEvent(this, "surfaceChanged", new Object[]{holder, new Integer(format), new Integer(width), new Integer(height)});
        if (holder.getSurface() != null) {
            stopPreview();
            startPreview(holder);
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        Ensighten.evaluateEvent(this, "surfaceDestroyed", new Object[]{holder});
        this.mIsSurfaceCreated = false;
    }

    private void startPreview(SurfaceHolder holder) {
        Ensighten.evaluateEvent(this, "startPreview", new Object[]{holder});
        if (this.mIsSurfaceCreated && !this.mIsPreviewing) {
            try {
                if (this.mCamera == null) {
                    this.mCamera = Camera.open();
                }
                Parameters params = this.mCamera.getParameters();
                params.setFocusMode("continuous-picture");
                this.mCamera.setParameters(params);
                this.mCamera.setDisplayOrientation(getDisplayOrientation());
                this.mCamera.setPreviewCallback(this);
                this.mCamera.setPreviewDisplay(holder);
                this.mCamera.startPreview();
                this.mIsPreviewing = true;
            } catch (Exception e) {
                MCDLog.error(LOG_TAG, "start preview", e);
            }
        }
    }

    private void stopPreview() {
        Ensighten.evaluateEvent(this, "stopPreview", null);
        if (this.mIsPreviewing) {
            try {
                this.mCamera.stopPreview();
            } catch (Exception e) {
                MCDLog.error(LOG_TAG, "stop preview", e);
            }
            this.mIsPreviewing = false;
        }
    }

    public void onPreviewFrame(byte[] data, Camera camera) {
        Ensighten.evaluateEvent(this, "onPreviewFrame", new Object[]{data, camera});
    }

    private int getDisplayOrientation() {
        Ensighten.evaluateEvent(this, "getDisplayOrientation", null);
        int displayRotation = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (displayRotation) {
            case 0:
                degrees = 0;
                break;
            case 1:
                degrees = 90;
                break;
            case 2:
                degrees = 180;
                break;
            case 3:
                degrees = 270;
                break;
            default:
                if (displayRotation % 90 == 0) {
                    degrees = (displayRotation + 360) % 360;
                    break;
                }
                break;
        }
        CameraInfo selectedCameraInfo = new CameraInfo();
        Camera.getCameraInfo(0, selectedCameraInfo);
        return ((selectedCameraInfo.orientation + 360) - degrees) % 360;
    }
}
