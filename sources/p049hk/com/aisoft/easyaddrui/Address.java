package p049hk.com.aisoft.easyaddrui;

/* renamed from: hk.com.aisoft.easyaddrui.Address */
public class Address {
    public String sAddrC = "";
    public String sAddrE = "";
    public String sAreaC = "";
    public String sAreaE = "";
    public String sBldgC = "";
    public String sBldgE = "";
    public String sBlock = "";
    public String sBlockC = "";
    public String sBlockE = "";
    public String sDistrictC = "";
    public String sDistrictE = "";
    public String sEstateC = "";
    public String sEstateE = "";
    public String sFlat = "";
    public String sFloor = "";
    public String sNoticeC = "";
    public String sNoticeE = "";
    public String sRemarks = "";
    public String sStreetC = "";
    public String sStreetE = "";
    public String sStreetLon = "";
    public String sStreetNo = "";

    public Address(String mArea, String mDistrict, String mStreet, String mStreetLon, String mStreetNo, String mEstate, String mBldg, String mBlock, String mFloor, String mFlat, String mRemarks) {
        this.sAreaC = mArea;
        this.sDistrictC = mDistrict;
        this.sStreetC = mStreet;
        this.sStreetLon = mStreetLon;
        this.sStreetNo = mStreetNo;
        this.sEstateC = mEstate;
        this.sBldgC = mBldg;
        this.sBlockC = mBlock;
        this.sFloor = mFloor;
        this.sFlat = mFlat;
        this.sRemarks = mRemarks;
    }

    public Address(ResponseAddr mResponseAddr) {
        this.sAreaC = mResponseAddr.sAreaC;
        this.sAreaE = mResponseAddr.sAreaE;
        this.sDistrictC = mResponseAddr.sDistrictC;
        this.sDistrictE = mResponseAddr.sDistrictE;
        this.sStreetC = mResponseAddr.sStreetC;
        this.sStreetE = mResponseAddr.sStreetE;
        this.sStreetLon = mResponseAddr.sStreetLon;
        String sStreetStr = "";
        if (!mResponseAddr.sStreetFromNo.equals("")) {
            if (mResponseAddr.sStreetToNo.equals("")) {
                sStreetStr = (mResponseAddr.sStreetFromNo + mResponseAddr.sStreetFromCode).trim();
            } else {
                sStreetStr = (mResponseAddr.sStreetFromNo + mResponseAddr.sStreetFromCode + "-" + mResponseAddr.sStreetToNo + mResponseAddr.sStreetToCode).trim();
            }
        }
        this.sStreetNo = sStreetStr;
        String sEstateStrC = "";
        String sEstateStrE = "";
        sEstateStrC = mResponseAddr.sEstateC;
        if (!mResponseAddr.sPhaseNo.equals("")) {
            sEstateStrC = sEstateStrC + mResponseAddr.sPhaseNo + "期";
        }
        sEstateStrC = sEstateStrC + mResponseAddr.sPhaseNameC;
        sEstateStrE = mResponseAddr.sEstateE;
        if (!mResponseAddr.sPhaseNo.equals("")) {
            sEstateStrE = sEstateStrE + " PHASE " + mResponseAddr.sPhaseNo;
        }
        sEstateStrE = (sEstateStrE + " " + mResponseAddr.sPhaseNameE).trim();
        this.sEstateC = sEstateStrC;
        this.sEstateE = sEstateStrE;
        this.sBldgC = mResponseAddr.sBldgC;
        this.sBldgE = mResponseAddr.sBldgE;
        this.sBlock = mResponseAddr.sBlock;
        this.sBlockC = mResponseAddr.sBlockC;
        this.sBlockE = mResponseAddr.sBlockE;
        this.sFloor = mResponseAddr.sFloor;
        this.sFlat = mResponseAddr.sFlat;
        this.sNoticeC = mResponseAddr.sAddrRmkC;
        this.sNoticeE = mResponseAddr.sAddrRmkE;
        this.sRemarks = mResponseAddr.sRemarks;
        this.sAddrC = mResponseAddr.sAddrC;
        this.sAddrE = mResponseAddr.sAddrE;
    }
}
