package com.mcdonalds.app.ordering.utils;

import android.content.res.Resources;
import android.util.SparseArray;
import com.ensighten.Ensighten;
import com.mcdonalds.app.ordering.OrderUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PODUtils {
    public static List<String> getRemainingPODs(List<String> unavailable) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.utils.PODUtils", "getRemainingPODs", new Object[]{unavailable});
        List<String> pods = getMainPODs();
        pods.removeAll(unavailable);
        return pods;
    }

    public static String getUnavailablePODMessage(List<String> unavailable, Resources resources) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.utils.PODUtils", "getUnavailablePODMessage", new Object[]{unavailable, resources});
        if (unavailable.size() == getMainPODs().size() - 1) {
            return resources.getString(C2658R.string.label_one_or_more_pods_available, new Object[]{OrderProduct.getPODDisplayName((String) getRemainingPODs(unavailable).get(0), resources)});
        }
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for (String pod : unavailable) {
            if (first) {
                first = false;
            } else {
                sb.append(resources.getString(C2658R.string.label_and));
            }
            sb.append(OrderProduct.getPODDisplayName(pod, resources));
        }
        return resources.getString(C2658R.string.label_one_or_more_pods_unavailable, new Object[]{sb.toString()});
    }

    public static List<String> getOrderUnavailablePODs(Order order) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.utils.PODUtils", "getOrderUnavailablePODs", new Object[]{order});
        Set<String> unavailable = new HashSet();
        if (ListUtils.isEmpty(order.getProducts())) {
            return new ArrayList(0);
        }
        for (OrderProduct product : order.getProducts()) {
            if (!OrderUtils.isProductBagProduct(product)) {
                for (String pod : getMainPODs()) {
                    if (!product.availableAtPOD(pod)) {
                        unavailable.add(pod);
                    }
                }
            }
        }
        return new ArrayList(unavailable);
    }

    public static List<String> getOrderUnavailablePODs() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.utils.PODUtils", "getOrderUnavailablePODs", null);
        return getOrderUnavailablePODs(OrderManager.getInstance().getCurrentOrder());
    }

    public static List<String> getMainPODs() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.utils.PODUtils", "getMainPODs", null);
        Configuration config = Configuration.getSharedInstance();
        if (config.hasKey("modules.ordering.mainPods")) {
            return (List) config.getValueForKey("modules.ordering.mainPods");
        }
        return Arrays.asList(new String[]{Pod.FRONT_COUNTER, Pod.DRIVETHRU, Pod.COLD_KIOSK});
    }

    public static int getMainPODsLength() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.utils.PODUtils", "getMainPODsLength", null);
        return getMainPODs().size();
    }

    public static boolean validateQRCodePOD(int podId, Order order) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.utils.PODUtils", "validateQRCodePOD", new Object[]{new Integer(podId), order});
        SparseArray<String> PODToQRMap = new SparseArray();
        PODToQRMap.put(0, Pod.FRONT_COUNTER);
        PODToQRMap.put(1, Pod.DRIVETHRU);
        PODToQRMap.put(2, Pod.COLD_KIOSK);
        PODToQRMap.put(3, Pod.DELIVERY);
        PODToQRMap.put(4, Pod.COLD_KIOSK);
        PODToQRMap.put(5, Pod.MC_CAFE);
        PODToQRMap.put(6, Pod.MC_EXPRESS);
        PODToQRMap.put(7, Pod.COLD_KIOSK_DRINK);
        String name = (String) PODToQRMap.get(podId);
        if (!isMainPOD(name)) {
            return true;
        }
        for (OrderProduct product : order.getProducts()) {
            if (!OrderUtils.isProductBagProduct(product) && !product.availableAtPOD(name)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMainPOD(String pod) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.utils.PODUtils", "isMainPOD", new Object[]{pod});
        return getMainPODs().contains(pod);
    }
}
