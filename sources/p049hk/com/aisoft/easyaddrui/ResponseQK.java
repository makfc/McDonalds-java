package p049hk.com.aisoft.easyaddrui;

import java.util.ArrayList;
import java.util.List;

/* renamed from: hk.com.aisoft.easyaddrui.ResponseQK */
public class ResponseQK {
    String sPage = "";
    List<ResponseAddr> sResponseAddrs = null;
    String sType = "";

    protected ResponseQK() {
    }

    /* Access modifiers changed, original: protected */
    public void setReponseAddr(ResponseQK mResponseQK) {
        if (this.sResponseAddrs == null) {
            this.sResponseAddrs = new ArrayList();
        }
        for (int i = 0; i < mResponseQK.sResponseAddrs.size(); i++) {
            this.sResponseAddrs.add(mResponseQK.sResponseAddrs.get(i));
        }
    }
}
