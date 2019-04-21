package com.amap.api.services.route;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.BusRouteSearchHandler;
import com.amap.api.services.core.C1128d;
import com.amap.api.services.core.DriveRouteSearchHandler;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.ManifestConfig;
import com.amap.api.services.core.WalkRouteSearchHandler;
import java.util.ArrayList;
import java.util.List;

public class RouteSearch {
    public static final int BusComfortable = 4;
    public static final int BusDefault = 0;
    public static final int BusLeaseChange = 2;
    public static final int BusLeaseWalk = 3;
    public static final int BusNoSubway = 5;
    public static final int BusSaveMoney = 1;
    public static final int DrivingAvoidCongestion = 4;
    public static final int DrivingDefault = 0;
    public static final int DrivingMultiStrategy = 5;
    public static final int DrivingNoExpressways = 3;
    public static final int DrivingNoHighAvoidCongestionSaveMoney = 9;
    public static final int DrivingNoHighWay = 6;
    public static final int DrivingNoHighWaySaveMoney = 7;
    public static final int DrivingSaveMoney = 1;
    public static final int DrivingSaveMoneyAvoidCongestion = 8;
    public static final int DrivingShortDistance = 2;
    public static final int WalkDefault = 0;
    public static final int WalkMultipath = 1;
    /* renamed from: a */
    Handler f4115a = new C1184m(this);
    /* renamed from: b */
    private OnRouteSearchListener f4116b;
    /* renamed from: c */
    private Context f4117c;

    public static class BusRouteQuery implements Parcelable, Cloneable {
        public static final Creator<BusRouteQuery> CREATOR = new C1185n();
        /* renamed from: a */
        private FromAndTo f4100a;
        /* renamed from: b */
        private int f4101b;
        /* renamed from: c */
        private String f4102c;
        /* renamed from: d */
        private int f4103d;

        public BusRouteQuery(FromAndTo fromAndTo, int i, String str, int i2) {
            this.f4100a = fromAndTo;
            this.f4101b = i;
            this.f4102c = str;
            this.f4103d = i2;
        }

        public FromAndTo getFromAndTo() {
            return this.f4100a;
        }

        public int getMode() {
            return this.f4101b;
        }

        public String getCity() {
            return this.f4102c;
        }

        public int getNightFlag() {
            return this.f4103d;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f4100a, i);
            parcel.writeInt(this.f4101b);
            parcel.writeString(this.f4102c);
            parcel.writeInt(this.f4103d);
        }

        public BusRouteQuery(Parcel parcel) {
            this.f4100a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.f4101b = parcel.readInt();
            this.f4102c = parcel.readString();
            this.f4103d = parcel.readInt();
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.f4102c == null ? 0 : this.f4102c.hashCode()) + 31) * 31;
            if (this.f4100a != null) {
                i = this.f4100a.hashCode();
            }
            return ((((hashCode + i) * 31) + this.f4101b) * 31) + this.f4103d;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            BusRouteQuery busRouteQuery = (BusRouteQuery) obj;
            if (this.f4102c == null) {
                if (busRouteQuery.f4102c != null) {
                    return false;
                }
            } else if (!this.f4102c.equals(busRouteQuery.f4102c)) {
                return false;
            }
            if (this.f4100a == null) {
                if (busRouteQuery.f4100a != null) {
                    return false;
                }
            } else if (!this.f4100a.equals(busRouteQuery.f4100a)) {
                return false;
            }
            if (this.f4101b != busRouteQuery.f4101b) {
                return false;
            }
            if (this.f4103d != busRouteQuery.f4103d) {
                return false;
            }
            return true;
        }

        public BusRouteQuery clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                C1128d.m4975a(e, "RouteSearch", "BusRouteQueryclone");
            }
            return new BusRouteQuery(this.f4100a, this.f4101b, this.f4102c, this.f4103d);
        }
    }

    public static class DriveRouteQuery implements Parcelable, Cloneable {
        public static final Creator<DriveRouteQuery> CREATOR = new C1186o();
        /* renamed from: a */
        private FromAndTo f4104a;
        /* renamed from: b */
        private int f4105b;
        /* renamed from: c */
        private List<LatLonPoint> f4106c;
        /* renamed from: d */
        private List<List<LatLonPoint>> f4107d;
        /* renamed from: e */
        private String f4108e;

        public DriveRouteQuery(FromAndTo fromAndTo, int i, List<LatLonPoint> list, List<List<LatLonPoint>> list2, String str) {
            this.f4104a = fromAndTo;
            this.f4105b = i;
            this.f4106c = list;
            this.f4107d = list2;
            this.f4108e = str;
        }

        public FromAndTo getFromAndTo() {
            return this.f4104a;
        }

        public int getMode() {
            return this.f4105b;
        }

        public List<LatLonPoint> getPassedByPoints() {
            return this.f4106c;
        }

        public List<List<LatLonPoint>> getAvoidpolygons() {
            return this.f4107d;
        }

        public String getAvoidRoad() {
            return this.f4108e;
        }

        public String getPassedPointStr() {
            StringBuffer stringBuffer = new StringBuffer();
            if (this.f4106c == null || this.f4106c.size() == 0) {
                return null;
            }
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.f4106c.size()) {
                    return stringBuffer.toString();
                }
                LatLonPoint latLonPoint = (LatLonPoint) this.f4106c.get(i2);
                stringBuffer.append(latLonPoint.getLongitude());
                stringBuffer.append(",");
                stringBuffer.append(latLonPoint.getLatitude());
                if (i2 < this.f4106c.size() - 1) {
                    stringBuffer.append(";");
                }
                i = i2 + 1;
            }
        }

        public boolean hasPassPoint() {
            if (C1128d.m4976a(getPassedPointStr())) {
                return false;
            }
            return true;
        }

        public String getAvoidpolygonsStr() {
            StringBuffer stringBuffer = new StringBuffer();
            if (this.f4107d == null || this.f4107d.size() == 0) {
                return null;
            }
            for (int i = 0; i < this.f4107d.size(); i++) {
                List list = (List) this.f4107d.get(i);
                for (int i2 = 0; i2 < list.size(); i2++) {
                    LatLonPoint latLonPoint = (LatLonPoint) list.get(i2);
                    stringBuffer.append(latLonPoint.getLongitude());
                    stringBuffer.append(",");
                    stringBuffer.append(latLonPoint.getLatitude());
                    if (i2 < list.size() - 1) {
                        stringBuffer.append(";");
                    }
                }
                if (i < this.f4107d.size() - 1) {
                    stringBuffer.append("|");
                }
            }
            return stringBuffer.toString();
        }

        public boolean hasAvoidpolygons() {
            if (C1128d.m4976a(getAvoidpolygonsStr())) {
                return false;
            }
            return true;
        }

        public boolean hasAvoidRoad() {
            if (C1128d.m4976a(getAvoidRoad())) {
                return false;
            }
            return true;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f4104a, i);
            parcel.writeInt(this.f4105b);
            parcel.writeTypedList(this.f4106c);
            if (this.f4107d == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(this.f4107d.size());
                for (List writeTypedList : this.f4107d) {
                    parcel.writeTypedList(writeTypedList);
                }
            }
            parcel.writeString(this.f4108e);
        }

        public DriveRouteQuery(Parcel parcel) {
            this.f4104a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.f4105b = parcel.readInt();
            this.f4106c = parcel.createTypedArrayList(LatLonPoint.CREATOR);
            int readInt = parcel.readInt();
            if (readInt == 0) {
                this.f4107d = null;
            } else {
                this.f4107d = new ArrayList();
            }
            for (int i = 0; i < readInt; i++) {
                this.f4107d.add(parcel.createTypedArrayList(LatLonPoint.CREATOR));
            }
            this.f4108e = parcel.readString();
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((this.f4104a == null ? 0 : this.f4104a.hashCode()) + (((this.f4107d == null ? 0 : this.f4107d.hashCode()) + (((this.f4108e == null ? 0 : this.f4108e.hashCode()) + 31) * 31)) * 31)) * 31) + this.f4105b) * 31;
            if (this.f4106c != null) {
                i = this.f4106c.hashCode();
            }
            return hashCode + i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            DriveRouteQuery driveRouteQuery = (DriveRouteQuery) obj;
            if (this.f4108e == null) {
                if (driveRouteQuery.f4108e != null) {
                    return false;
                }
            } else if (!this.f4108e.equals(driveRouteQuery.f4108e)) {
                return false;
            }
            if (this.f4107d == null) {
                if (driveRouteQuery.f4107d != null) {
                    return false;
                }
            } else if (!this.f4107d.equals(driveRouteQuery.f4107d)) {
                return false;
            }
            if (this.f4104a == null) {
                if (driveRouteQuery.f4104a != null) {
                    return false;
                }
            } else if (!this.f4104a.equals(driveRouteQuery.f4104a)) {
                return false;
            }
            if (this.f4105b != driveRouteQuery.f4105b) {
                return false;
            }
            if (this.f4106c == null) {
                if (driveRouteQuery.f4106c != null) {
                    return false;
                }
                return true;
            } else if (this.f4106c.equals(driveRouteQuery.f4106c)) {
                return true;
            } else {
                return false;
            }
        }

        public DriveRouteQuery clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                C1128d.m4975a(e, "RouteSearch", "DriveRouteQueryclone");
            }
            return new DriveRouteQuery(this.f4104a, this.f4105b, this.f4106c, this.f4107d, this.f4108e);
        }
    }

    public static class FromAndTo implements Parcelable, Cloneable {
        public static final Creator<FromAndTo> CREATOR = new C1187p();
        /* renamed from: a */
        private LatLonPoint f4109a;
        /* renamed from: b */
        private LatLonPoint f4110b;
        /* renamed from: c */
        private String f4111c;
        /* renamed from: d */
        private String f4112d;

        public FromAndTo(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
            this.f4109a = latLonPoint;
            this.f4110b = latLonPoint2;
        }

        public LatLonPoint getFrom() {
            return this.f4109a;
        }

        public LatLonPoint getTo() {
            return this.f4110b;
        }

        public String getStartPoiID() {
            return this.f4111c;
        }

        public void setStartPoiID(String str) {
            this.f4111c = str;
        }

        public String getDestinationPoiID() {
            return this.f4112d;
        }

        public void setDestinationPoiID(String str) {
            this.f4112d = str;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f4109a, i);
            parcel.writeParcelable(this.f4110b, i);
            parcel.writeString(this.f4111c);
            parcel.writeString(this.f4112d);
        }

        public FromAndTo(Parcel parcel) {
            this.f4109a = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
            this.f4110b = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
            this.f4111c = parcel.readString();
            this.f4112d = parcel.readString();
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.f4111c == null ? 0 : this.f4111c.hashCode()) + (((this.f4109a == null ? 0 : this.f4109a.hashCode()) + (((this.f4112d == null ? 0 : this.f4112d.hashCode()) + 31) * 31)) * 31)) * 31;
            if (this.f4110b != null) {
                i = this.f4110b.hashCode();
            }
            return hashCode + i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            FromAndTo fromAndTo = (FromAndTo) obj;
            if (this.f4112d == null) {
                if (fromAndTo.f4112d != null) {
                    return false;
                }
            } else if (!this.f4112d.equals(fromAndTo.f4112d)) {
                return false;
            }
            if (this.f4109a == null) {
                if (fromAndTo.f4109a != null) {
                    return false;
                }
            } else if (!this.f4109a.equals(fromAndTo.f4109a)) {
                return false;
            }
            if (this.f4111c == null) {
                if (fromAndTo.f4111c != null) {
                    return false;
                }
            } else if (!this.f4111c.equals(fromAndTo.f4111c)) {
                return false;
            }
            if (this.f4110b == null) {
                if (fromAndTo.f4110b != null) {
                    return false;
                }
                return true;
            } else if (this.f4110b.equals(fromAndTo.f4110b)) {
                return true;
            } else {
                return false;
            }
        }

        public FromAndTo clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                C1128d.m4975a(e, "RouteSearch", "FromAndToclone");
            }
            FromAndTo fromAndTo = new FromAndTo(this.f4109a, this.f4110b);
            fromAndTo.setStartPoiID(this.f4111c);
            fromAndTo.setDestinationPoiID(this.f4112d);
            return fromAndTo;
        }
    }

    public interface OnRouteSearchListener {
        void onBusRouteSearched(BusRouteResult busRouteResult, int i);

        void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i);

        void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i);
    }

    public static class WalkRouteQuery implements Parcelable, Cloneable {
        public static final Creator<WalkRouteQuery> CREATOR = new C1188q();
        /* renamed from: a */
        private FromAndTo f4113a;
        /* renamed from: b */
        private int f4114b;

        public WalkRouteQuery(FromAndTo fromAndTo, int i) {
            this.f4113a = fromAndTo;
            this.f4114b = i;
        }

        public FromAndTo getFromAndTo() {
            return this.f4113a;
        }

        public int getMode() {
            return this.f4114b;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f4113a, i);
            parcel.writeInt(this.f4114b);
        }

        public WalkRouteQuery(Parcel parcel) {
            this.f4113a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.f4114b = parcel.readInt();
        }

        public int hashCode() {
            return (((this.f4113a == null ? 0 : this.f4113a.hashCode()) + 31) * 31) + this.f4114b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            WalkRouteQuery walkRouteQuery = (WalkRouteQuery) obj;
            if (this.f4113a == null) {
                if (walkRouteQuery.f4113a != null) {
                    return false;
                }
            } else if (!this.f4113a.equals(walkRouteQuery.f4113a)) {
                return false;
            }
            if (this.f4114b != walkRouteQuery.f4114b) {
                return false;
            }
            return true;
        }

        public WalkRouteQuery clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                C1128d.m4975a(e, "RouteSearch", "WalkRouteQueryclone");
            }
            return new WalkRouteQuery(this.f4113a, this.f4114b);
        }
    }

    public RouteSearch(Context context) {
        this.f4117c = context.getApplicationContext();
    }

    public void setRouteSearchListener(OnRouteSearchListener onRouteSearchListener) {
        this.f4116b = onRouteSearchListener;
    }

    public WalkRouteResult calculateWalkRoute(WalkRouteQuery walkRouteQuery) throws AMapException {
        ManifestConfig.m5058a(this.f4117c);
        WalkRouteQuery clone = walkRouteQuery.clone();
        WalkRouteResult walkRouteResult = (WalkRouteResult) new WalkRouteSearchHandler(this.f4117c, clone).mo11981g();
        if (walkRouteResult != null) {
            walkRouteResult.setWalkQuery(clone);
        }
        return walkRouteResult;
    }

    public void calculateWalkRouteAsyn(final WalkRouteQuery walkRouteQuery) {
        new Thread() {
            public void run() {
                Message message = new Message();
                message.what = 12;
                Bundle bundle = new Bundle();
                Object obj = null;
                try {
                    obj = RouteSearch.this.calculateWalkRoute(walkRouteQuery);
                    bundle.putInt("errorCode", 0);
                } catch (AMapException e) {
                    C1128d.m4975a(e, "RouteSearch", "calculateWalkRouteAsyn");
                    bundle.putInt("errorCode", e.getErrorCode());
                } finally {
                    message.obj = obj;
                    message.setData(bundle);
                    RouteSearch.this.f4115a.sendMessage(message);
                }
            }
        }.start();
    }

    public BusRouteResult calculateBusRoute(BusRouteQuery busRouteQuery) throws AMapException {
        ManifestConfig.m5058a(this.f4117c);
        BusRouteQuery clone = busRouteQuery.clone();
        BusRouteResult busRouteResult = (BusRouteResult) new BusRouteSearchHandler(this.f4117c, clone).mo11981g();
        if (busRouteResult != null) {
            busRouteResult.setBusQuery(clone);
        }
        return busRouteResult;
    }

    public void calculateBusRouteAsyn(final BusRouteQuery busRouteQuery) {
        new Thread() {
            public void run() {
                Message message = new Message();
                message.what = 10;
                Bundle bundle = new Bundle();
                Object obj = null;
                try {
                    obj = RouteSearch.this.calculateBusRoute(busRouteQuery);
                    bundle.putInt("errorCode", 0);
                } catch (AMapException e) {
                    C1128d.m4975a(e, "RouteSearch", "calculateBusRouteAsyn");
                    bundle.putInt("errorCode", e.getErrorCode());
                } finally {
                    message.obj = obj;
                    message.setData(bundle);
                    RouteSearch.this.f4115a.sendMessage(message);
                }
            }
        }.start();
    }

    public DriveRouteResult calculateDriveRoute(DriveRouteQuery driveRouteQuery) throws AMapException {
        ManifestConfig.m5058a(this.f4117c);
        DriveRouteQuery clone = driveRouteQuery.clone();
        DriveRouteResult driveRouteResult = (DriveRouteResult) new DriveRouteSearchHandler(this.f4117c, clone).mo11981g();
        if (driveRouteResult != null) {
            driveRouteResult.setDriveQuery(clone);
        }
        return driveRouteResult;
    }

    public void calculateDriveRouteAsyn(final DriveRouteQuery driveRouteQuery) {
        new Thread() {
            public void run() {
                Message message = new Message();
                message.what = 11;
                Bundle bundle = new Bundle();
                Object obj = null;
                try {
                    obj = RouteSearch.this.calculateDriveRoute(driveRouteQuery);
                    bundle.putInt("errorCode", 0);
                } catch (AMapException e) {
                    C1128d.m4975a(e, "RouteSearch", "calculateDriveRouteAsyn");
                    bundle.putInt("errorCode", e.getErrorCode());
                } finally {
                    message.obj = obj;
                    message.setData(bundle);
                    RouteSearch.this.f4115a.sendMessage(message);
                }
            }
        }.start();
    }
}
