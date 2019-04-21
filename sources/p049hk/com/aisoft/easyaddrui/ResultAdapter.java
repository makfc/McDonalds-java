package p049hk.com.aisoft.easyaddrui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

/* renamed from: hk.com.aisoft.easyaddrui.ResultAdapter */
public class ResultAdapter extends BaseAdapter {
    private LayoutInflater sInflater;
    List<ResponseAddr> sResponseAddrs;

    public ResultAdapter(Context context, List<ResponseAddr> mResponseAddrs) {
        this.sInflater = LayoutInflater.from(context);
        this.sResponseAddrs = mResponseAddrs;
    }

    public int getCount() {
        System.out.println("[getCount]: " + this.sResponseAddrs.size());
        return this.sResponseAddrs.size();
    }

    public ResponseAddr getItem(int position) {
        System.out.println("[getItem]: " + position);
        return (ResponseAddr) this.sResponseAddrs.get(position);
    }

    public long getItemId(int position) {
        System.out.println("[getItemId]: " + position);
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((LayoutInflater) eaView.sContext.getSystemService("layout_inflater")).inflate(C4560R.layout.ea_result, parent, false);
        }
        ResponseAddr sResponseAddr = (ResponseAddr) this.sResponseAddrs.get(position);
        TextView sAddrDetails = (TextView) convertView.findViewById(C4560R.C4559id.txtAddrDetails);
        TextView sAreaC = (TextView) convertView.findViewById(C4560R.C4559id.txtAreaC);
        TextView sAreaE = (TextView) convertView.findViewById(C4560R.C4559id.txtAreaE);
        TextView sDistrictC = (TextView) convertView.findViewById(C4560R.C4559id.txtDistrictC);
        TextView sDistrictE = (TextView) convertView.findViewById(C4560R.C4559id.txtDistrictE);
        TextView sStreetC = (TextView) convertView.findViewById(C4560R.C4559id.txtStreetC);
        TextView sStreetE = (TextView) convertView.findViewById(C4560R.C4559id.txtStreetE);
        TextView sStreetLon = (TextView) convertView.findViewById(C4560R.C4559id.txtStreetLon);
        TextView sStreetFromNo = (TextView) convertView.findViewById(C4560R.C4559id.txtStreetFromNo);
        TextView sStreetFromCode = (TextView) convertView.findViewById(C4560R.C4559id.txtStreetFromCode);
        TextView sStreetToNo = (TextView) convertView.findViewById(C4560R.C4559id.txtStreetToNo);
        TextView sStreetToCode = (TextView) convertView.findViewById(C4560R.C4559id.txtStreetToCode);
        TextView sEstateC = (TextView) convertView.findViewById(C4560R.C4559id.txtEstateC);
        TextView sEstateE = (TextView) convertView.findViewById(C4560R.C4559id.txtEstateE);
        TextView sBldgC = (TextView) convertView.findViewById(C4560R.C4559id.txtBldgC);
        TextView sBldgE = (TextView) convertView.findViewById(C4560R.C4559id.txtBldgE);
        TextView sBlockC = (TextView) convertView.findViewById(C4560R.C4559id.txtBlockC);
        TextView sBlockE = (TextView) convertView.findViewById(C4560R.C4559id.txtBlockE);
        TextView sHub1 = (TextView) convertView.findViewById(C4560R.C4559id.txtHub1);
        TextView sHub2 = (TextView) convertView.findViewById(C4560R.C4559id.txtHub2);
        TextView sHub3 = (TextView) convertView.findViewById(C4560R.C4559id.txtHub3);
        TextView sHub4 = (TextView) convertView.findViewById(C4560R.C4559id.txtHub4);
        TextView sAddrC = (TextView) convertView.findViewById(C4560R.C4559id.txtAddrC);
        TextView sAddrE = (TextView) convertView.findViewById(C4560R.C4559id.txtAddrE);
        TextView sUBI = (TextView) convertView.findViewById(C4560R.C4559id.txtUBI);
        TextView sLot = (TextView) convertView.findViewById(C4560R.C4559id.txtLot);
        ((TextView) convertView.findViewById(C4560R.C4559id.txtAddrName)).setText(ResultAdapter.getAddrName(sResponseAddr));
        sAddrDetails.setText(ResultAdapter.getAddrDetails(sResponseAddr));
        sAreaC.setText(sResponseAddr.sAreaC);
        sAreaE.setText(sResponseAddr.sAreaE);
        sDistrictC.setText(sResponseAddr.sDistrictC);
        sDistrictE.setText(sResponseAddr.sDistrictE);
        sStreetC.setText(sResponseAddr.sStreetC);
        sStreetE.setText(sResponseAddr.sStreetE);
        sStreetLon.setText(sResponseAddr.sStreetLon);
        sStreetFromNo.setText(sResponseAddr.sStreetFromNo);
        sStreetFromCode.setText(sResponseAddr.sStreetFromCode);
        sStreetToNo.setText(sResponseAddr.sStreetToNo);
        sStreetToCode.setText(sResponseAddr.sStreetToCode);
        sEstateC.setText(sResponseAddr.sEstateC);
        sEstateE.setText(sResponseAddr.sEstateE);
        sBldgC.setText(sResponseAddr.sBldgC);
        sBldgE.setText(sResponseAddr.sBldgE);
        sBlockC.setText(sResponseAddr.sBlockC);
        sBlockE.setText(sResponseAddr.sBlockE);
        sHub1.setText(sResponseAddr.sHub1);
        sHub2.setText(sResponseAddr.sHub2);
        sHub3.setText(sResponseAddr.sHub3);
        sHub4.setText(sResponseAddr.sHub4);
        sAddrC.setText(sResponseAddr.sAddrC);
        sAddrE.setText(sResponseAddr.sAddrE);
        sUBI.setText(sResponseAddr.sUBI);
        sLot.setText(sResponseAddr.sLot);
        sAreaC.setVisibility(8);
        sAreaE.setVisibility(8);
        sDistrictC.setVisibility(8);
        sDistrictE.setVisibility(8);
        sStreetC.setVisibility(8);
        sStreetE.setVisibility(8);
        sStreetLon.setVisibility(8);
        sStreetFromNo.setVisibility(8);
        sStreetFromCode.setVisibility(8);
        sStreetToNo.setVisibility(8);
        sStreetToCode.setVisibility(8);
        sEstateC.setVisibility(8);
        sEstateE.setVisibility(8);
        sBldgC.setVisibility(8);
        sBldgE.setVisibility(8);
        sBlockC.setVisibility(8);
        sBlockE.setVisibility(8);
        sHub1.setVisibility(8);
        sHub2.setVisibility(8);
        sHub3.setVisibility(8);
        sHub4.setVisibility(8);
        sAddrC.setVisibility(8);
        sAddrE.setVisibility(8);
        sUBI.setVisibility(8);
        sLot.setVisibility(8);
        return convertView;
    }

    public static String getAddrName(ResponseAddr sAddress) {
        String sBldgStr = "";
        String sEstateStr = "";
        String sStreetStr = "";
        if (eaView.sEALang.equals("zh-HK")) {
            sStreetStr = sAddress.sStreetC;
            if (!sAddress.sStreetFromNo.equals("")) {
                if (sAddress.sStreetToNo.equals("")) {
                    sStreetStr = sStreetStr + sAddress.sStreetFromNo + sAddress.sStreetFromCode + "號";
                } else {
                    sStreetStr = sStreetStr + sAddress.sStreetFromNo + sAddress.sStreetFromCode + "-" + sAddress.sStreetToNo + sAddress.sStreetToCode + "號";
                }
            }
            sEstateStr = sAddress.sEstateC;
            if (!sAddress.sPhaseNo.equals("")) {
                sEstateStr = sEstateStr + sAddress.sPhaseNo + "期";
            }
            sEstateStr = sEstateStr + sAddress.sPhaseNameC;
            sBldgStr = sAddress.sBldgC;
            if (!sAddress.sBlockC.equals("")) {
                sBldgStr = sBldgStr + sAddress.sBlockC + "座";
            }
            if (sEstateStr.equals("") && sBldgStr.equals("")) {
                return sStreetStr;
            }
            return sEstateStr + sBldgStr;
        }
        sStreetStr = sAddress.sStreetE;
        if (!sAddress.sStreetFromNo.equals("")) {
            if (sAddress.sStreetToNo.equals("")) {
                sStreetStr = (sStreetStr + " " + sAddress.sStreetFromNo + sAddress.sStreetFromCode).trim();
            } else {
                sStreetStr = (sStreetStr + " " + sAddress.sStreetFromNo + sAddress.sStreetFromCode + "-" + sAddress.sStreetToNo + sAddress.sStreetToCode).trim();
            }
        }
        sEstateStr = sAddress.sEstateE;
        if (!sAddress.sPhaseNo.equals("")) {
            sEstateStr = sEstateStr + " PHASE " + sAddress.sPhaseNo;
        }
        sEstateStr = (sEstateStr + " " + sAddress.sPhaseNameE).trim();
        sBldgStr = sAddress.sBldgE;
        if (!sAddress.sBlockE.equals("")) {
            sBldgStr = sBldgStr + " BLOCK " + sAddress.sBlockE;
        }
        if (sEstateStr.equals("") && sBldgStr.equals("")) {
            return sStreetStr;
        }
        return (sEstateStr + " " + sBldgStr).trim();
    }

    public static String getAddrDetails(ResponseAddr sAddress) {
        String sBldgStr = "";
        String sEstateStr = "";
        String sStreetStr = "";
        if (eaView.sEALang.equals("zh-HK")) {
            sStreetStr = sAddress.sStreetC;
            if (!sAddress.sStreetFromNo.equals("")) {
                if (sAddress.sStreetToNo.equals("")) {
                    sStreetStr = sStreetStr + sAddress.sStreetFromNo + sAddress.sStreetFromCode + "號";
                } else {
                    sStreetStr = sStreetStr + sAddress.sStreetFromNo + sAddress.sStreetFromCode + "-" + sAddress.sStreetToNo + sAddress.sStreetToCode + "號";
                }
            }
            sEstateStr = sAddress.sEstateC;
            if (!sAddress.sPhaseNo.equals("")) {
                sEstateStr = sEstateStr + sAddress.sPhaseNo + "期";
            }
            sEstateStr = sEstateStr + sAddress.sPhaseNameC;
            sBldgStr = sAddress.sBldgC;
            if (!sAddress.sBlockC.equals("")) {
                sBldgStr = sBldgStr + sAddress.sBlockC + "座";
            }
            if (sEstateStr.equals("") && sBldgStr.equals("")) {
                return sEstateStr + sBldgStr;
            }
            return sStreetStr;
        }
        sStreetStr = sAddress.sStreetE;
        if (!sAddress.sStreetFromNo.equals("")) {
            if (sAddress.sStreetToNo.equals("")) {
                sStreetStr = (sStreetStr + " " + sAddress.sStreetFromNo + sAddress.sStreetFromCode).trim();
            } else {
                sStreetStr = (sStreetStr + " " + sAddress.sStreetFromNo + sAddress.sStreetFromCode + "-" + sAddress.sStreetToNo + sAddress.sStreetToCode).trim();
            }
        }
        sEstateStr = sAddress.sEstateE;
        if (!sAddress.sPhaseNo.equals("")) {
            sEstateStr = sEstateStr + " PHASE " + sAddress.sPhaseNo;
        }
        sEstateStr = (sEstateStr + " " + sAddress.sPhaseNameE).trim();
        sBldgStr = sAddress.sBldgE;
        if (!sAddress.sBlockE.equals("")) {
            sBldgStr = sBldgStr + " BLOCK " + sAddress.sBlockE;
        }
        if (sEstateStr.equals("") && sBldgStr.equals("")) {
            return (sEstateStr + " " + sBldgStr).trim();
        }
        return sStreetStr;
    }

    public static String getDisplayAddrFrmRespAddr(ResponseAddr sAddress) {
        String sBldgStr = "";
        String sEstateStr = "";
        String sStreetStr = "";
        if (eaView.sEALang.equals("zh-HK")) {
            sStreetStr = sAddress.sStreetC;
            if (!sAddress.sStreetFromNo.equals("")) {
                if (sAddress.sStreetToNo.equals("")) {
                    sStreetStr = sStreetStr + sAddress.sStreetFromNo + sAddress.sStreetFromCode + "號";
                } else {
                    sStreetStr = sStreetStr + sAddress.sStreetFromNo + sAddress.sStreetFromCode + "-" + sAddress.sStreetToNo + sAddress.sStreetToCode + "號";
                }
            }
            sEstateStr = sAddress.sEstateC;
            if (!sAddress.sPhaseNo.equals("")) {
                sEstateStr = sEstateStr + sAddress.sPhaseNo + "期";
            }
            sEstateStr = sEstateStr + sAddress.sPhaseNameC;
            sBldgStr = sAddress.sBldgC;
            if (!sAddress.sBlockC.equals("")) {
                sBldgStr = sBldgStr + sAddress.sBlockC + "座";
            }
            return sStreetStr + sEstateStr + sBldgStr;
        }
        sStreetStr = sAddress.sStreetE;
        if (!sAddress.sStreetFromNo.equals("")) {
            if (sAddress.sStreetToNo.equals("")) {
                sStreetStr = (sStreetStr + " " + sAddress.sStreetFromNo + sAddress.sStreetFromCode).trim();
            } else {
                sStreetStr = (sStreetStr + " " + sAddress.sStreetFromNo + sAddress.sStreetFromCode + "-" + sAddress.sStreetToNo + sAddress.sStreetToCode).trim();
            }
        }
        sEstateStr = sAddress.sEstateE;
        if (!sAddress.sPhaseNo.equals("")) {
            sEstateStr = sEstateStr + " PHASE " + sAddress.sPhaseNo;
        }
        sEstateStr = (sEstateStr + " " + sAddress.sPhaseNameE).trim();
        sBldgStr = sAddress.sBldgE;
        if (!sAddress.sBlockE.equals("")) {
            sBldgStr = sBldgStr + " BLOCK " + sAddress.sBlockE;
        }
        return ((sStreetStr + " " + sEstateStr).trim() + " " + sBldgStr).trim();
    }

    public static String getDisplayAddrFrmAddrRet(Address sAddress, String sLang, boolean addrOnly) {
        String sBldgStr = "";
        String sEstateStr = "";
        String sStreetStr = "";
        String sAddrMisc = "";
        String sRemarks = "";
        if (sLang.equals("zh-HK")) {
            sStreetStr = sAddress.sStreetC + sAddress.sStreetNo;
            sEstateStr = sAddress.sEstateC;
            sBldgStr = sAddress.sBldgC;
            if (!sAddress.sBlockC.equals("")) {
                sBldgStr = sBldgStr + sAddress.sBlockC + "座";
            }
            if (!addrOnly) {
                if (!sAddress.sFloor.equals("")) {
                    sAddrMisc = sAddrMisc + sAddress.sFloor + "樓";
                }
                if (!sAddress.sFlat.equals("")) {
                    sAddrMisc = sAddrMisc + sAddress.sFlat + "室";
                }
                if (!sAddress.sRemarks.equals("")) {
                    sRemarks = "*" + sAddress.sRemarks;
                }
            }
            return (sStreetStr + sEstateStr + sBldgStr + sAddrMisc + sRemarks).trim();
        }
        sStreetStr = (sAddress.sStreetE + " " + sAddress.sStreetNo).trim();
        sEstateStr = sAddress.sEstateE;
        sBldgStr = sAddress.sBldgE;
        if (!sAddress.sBlockE.equals("")) {
            sBldgStr = sBldgStr + " BLOCK " + sAddress.sBlockE;
        }
        if (!addrOnly) {
            if (!sAddress.sFloor.equals("")) {
                sAddrMisc = (sAddrMisc + " FLOOR " + sAddress.sFloor).trim();
            }
            if (!sAddress.sFlat.equals("")) {
                sAddrMisc = (sAddrMisc + " FLAT " + sAddress.sFlat).trim();
            }
            if (!sAddress.sRemarks.equals("")) {
                sRemarks = "*" + sAddress.sRemarks;
            }
        }
        return ((((sStreetStr + " " + sEstateStr).trim() + " " + sBldgStr).trim() + " " + sAddrMisc).trim() + " " + sRemarks).trim();
    }
}
