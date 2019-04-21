package p041io.fabric.sdk.android.services.common;

import com.autonavi.amap.mapcore.VTMCDataCache;
import com.mcdonalds.sdk.connectors.middleware.response.MWDeliveryStatusResponse;

/* renamed from: io.fabric.sdk.android.services.common.ResponseParser */
public class ResponseParser {
    public static int parse(int statusCode) {
        if (statusCode >= 200 && statusCode <= 299) {
            return 0;
        }
        if (statusCode >= 300 && statusCode <= 399) {
            return 1;
        }
        if (statusCode >= MWDeliveryStatusResponse.STATUS_ORDER_DELIVERED && statusCode <= 499) {
            return 0;
        }
        if (statusCode >= VTMCDataCache.MAXSIZE) {
            return 1;
        }
        return 1;
    }
}
