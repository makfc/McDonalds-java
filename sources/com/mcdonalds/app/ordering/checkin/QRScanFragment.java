package com.mcdonalds.app.ordering.checkin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.Result;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.CameraPreviewFragment;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QRScanFragment extends CameraPreviewFragment {
    private TextView mInstructions;
    private MultiFormatReader mQRReader;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<BarcodeFormat> formats = new ArrayList(1);
        formats.add(BarcodeFormat.QR_CODE);
        Map<DecodeHintType, Object> hints = new HashMap();
        hints.put(DecodeHintType.POSSIBLE_FORMATS, formats);
        this.mQRReader = new MultiFormatReader();
        this.mQRReader.setHints(hints);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (view != null) {
            this.mInstructions = (TextView) view.findViewById(C2358R.C2357id.instructions);
            updateQRScanInstructionsText();
        }
        return view;
    }

    private void updateQRScanInstructionsText() {
        Ensighten.evaluateEvent(this, "updateQRScanInstructionsText", null);
        Store store = OrderManager.getInstance().getCurrentStore();
        if (ListUtils.isEmpty(store.getPODs())) {
            this.mInstructions.setVisibility(4);
        } else {
            displayInstructions(store);
        }
    }

    private void displayInstructions(Store store) {
        Ensighten.evaluateEvent(this, "displayInstructions", new Object[]{store});
        this.mInstructions.setText(getString(C2658R.string.qr_scan_instructions_description));
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public void onPreviewFrame(byte[] r10, android.hardware.Camera r11) {
        /*
        r9 = this;
        r6 = "onPreviewFrame";
        r7 = 2;
        r7 = new java.lang.Object[r7];
        r8 = 0;
        r7[r8] = r10;
        r8 = 1;
        r7[r8] = r11;
        com.ensighten.Ensighten.evaluateEvent(r9, r6, r7);
        super.onPreviewFrame(r10, r11);
        r2 = r11.getParameters();	 Catch:{ Exception -> 0x0044 }
        r4 = r2.getPreviewSize();	 Catch:{ Exception -> 0x0044 }
        r6 = r4.width;	 Catch:{ Exception -> 0x0044 }
        r7 = r4.height;	 Catch:{ Exception -> 0x0044 }
        r5 = r9.buildLuminanceSource(r10, r6, r7);	 Catch:{ Exception -> 0x0044 }
        r0 = new com.google.zxing.BinaryBitmap;	 Catch:{ Exception -> 0x0044 }
        r6 = new com.google.zxing.common.HybridBinarizer;	 Catch:{ Exception -> 0x0044 }
        r6.<init>(r5);	 Catch:{ Exception -> 0x0044 }
        r0.<init>(r6);	 Catch:{ Exception -> 0x0044 }
        r3 = 0;
        r6 = r9.mQRReader;	 Catch:{ ReaderException -> 0x003d, all -> 0x0049 }
        r3 = r6.decodeWithState(r0);	 Catch:{ ReaderException -> 0x003d, all -> 0x0049 }
        r6 = r9.mQRReader;	 Catch:{ Exception -> 0x0044 }
        r6.reset();	 Catch:{ Exception -> 0x0044 }
    L_0x0037:
        if (r3 == 0) goto L_0x003c;
    L_0x0039:
        r9.onQRCodeAcquired(r3);	 Catch:{ Exception -> 0x0044 }
    L_0x003c:
        return;
    L_0x003d:
        r6 = move-exception;
        r6 = r9.mQRReader;	 Catch:{ Exception -> 0x0044 }
        r6.reset();	 Catch:{ Exception -> 0x0044 }
        goto L_0x0037;
    L_0x0044:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x003c;
    L_0x0049:
        r6 = move-exception;
        r7 = r9.mQRReader;	 Catch:{ Exception -> 0x0044 }
        r7.reset();	 Catch:{ Exception -> 0x0044 }
        throw r6;	 Catch:{ Exception -> 0x0044 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mcdonalds.app.ordering.checkin.QRScanFragment.onPreviewFrame(byte[], android.hardware.Camera):void");
    }

    public void onQRCodeAcquired(Result result) {
        Ensighten.evaluateEvent(this, "onQRCodeAcquired", new Object[]{result});
        Bundle b = new Bundle();
        b.putString("result_code", result.getText());
        Intent intent = new Intent();
        intent.putExtras(b);
        getActivity().setResult(-1, intent);
        getActivity().finish();
    }

    private PlanarYUVLuminanceSource buildLuminanceSource(byte[] data, int width, int height) {
        Ensighten.evaluateEvent(this, "buildLuminanceSource", new Object[]{data, new Integer(width), new Integer(height)});
        return new PlanarYUVLuminanceSource(data, width, height, 0, 0, width, height, false);
    }
}
