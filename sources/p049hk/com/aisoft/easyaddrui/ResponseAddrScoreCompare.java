package p049hk.com.aisoft.easyaddrui;

import java.util.Comparator;

/* renamed from: hk.com.aisoft.easyaddrui.ResponseAddrScoreCompare */
public class ResponseAddrScoreCompare implements Comparator<ResponseAddr> {
    public int compare(ResponseAddr left, ResponseAddr right) {
        return right.sScore.compareTo(left.sScore);
    }
}
