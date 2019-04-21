package com.amap.api.services.core;

import com.amap.api.location.LocationManagerProxy;
import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.district.DistrictItem;
import com.amap.api.services.district.DistrictSearchQuery;
import com.amap.api.services.geocoder.BusinessArea;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeRoad;
import com.amap.api.services.geocoder.StreetNumber;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.Cinema;
import com.amap.api.services.poisearch.Dining;
import com.amap.api.services.poisearch.Discount;
import com.amap.api.services.poisearch.Groupbuy;
import com.amap.api.services.poisearch.Hotel;
import com.amap.api.services.poisearch.Photo;
import com.amap.api.services.poisearch.PoiItemDetail;
import com.amap.api.services.poisearch.PoiItemDetail.DeepType;
import com.amap.api.services.poisearch.Scenic;
import com.amap.api.services.road.Crossroad;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.BusStep;
import com.amap.api.services.route.District;
import com.amap.api.services.route.Doorway;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.RouteBusLineItem;
import com.amap.api.services.route.RouteBusWalkItem;
import com.amap.api.services.route.RouteSearchCity;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.amap.api.services.route.WalkStep;
import com.facebook.internal.NativeProtocol;
import com.kochava.base.InstallReferrer;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.mcdonalds.sdk.modules.models.Promotion;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.amap.api.services.core.j */
public class JSONHelper {
    /* renamed from: a */
    public static ArrayList<SuggestionCity> m4998a(JSONObject jSONObject) throws JSONException, NumberFormatException {
        ArrayList<SuggestionCity> arrayList = new ArrayList();
        if (!jSONObject.has("cities")) {
            return arrayList;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("cities");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(new SuggestionCity(JSONHelper.m5011b(optJSONObject, "name"), JSONHelper.m5011b(optJSONObject, "citycode"), JSONHelper.m5011b(optJSONObject, "adcode"), JSONHelper.m5034i(JSONHelper.m5011b(optJSONObject, "num"))));
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    public static ArrayList<String> m5012b(JSONObject jSONObject) throws JSONException {
        ArrayList<String> arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray(Parameters.KEYWORDS);
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            arrayList.add(optJSONArray.optString(i));
        }
        return arrayList;
    }

    /* renamed from: c */
    public static ArrayList<PoiItem> m5018c(JSONObject jSONObject) throws JSONException {
        ArrayList<PoiItem> arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("pois");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(JSONHelper.m5021d(optJSONObject));
            }
        }
        return arrayList;
    }

    /* renamed from: d */
    public static PoiItemDetail m5021d(JSONObject jSONObject) throws JSONException {
        PoiItemDetail poiItemDetail = new PoiItemDetail(JSONHelper.m5011b(jSONObject, "id"), JSONHelper.m5016c(jSONObject, LocationManagerProxy.KEY_LOCATION_CHANGED), JSONHelper.m5011b(jSONObject, "name"), JSONHelper.m5011b(jSONObject, "address"));
        poiItemDetail.setAdCode(JSONHelper.m5011b(jSONObject, "adcode"));
        poiItemDetail.setProvinceName(JSONHelper.m5011b(jSONObject, "pname"));
        poiItemDetail.setCityName(JSONHelper.m5011b(jSONObject, "cityname"));
        poiItemDetail.setAdName(JSONHelper.m5011b(jSONObject, "adname"));
        poiItemDetail.setCityCode(JSONHelper.m5011b(jSONObject, "citycode"));
        poiItemDetail.setProvinceCode(JSONHelper.m5011b(jSONObject, "pcode"));
        poiItemDetail.setDirection(JSONHelper.m5011b(jSONObject, "direction"));
        if (jSONObject.has("distance")) {
            String b = JSONHelper.m5011b(jSONObject, "distance");
            if (!JSONHelper.m5033h(b)) {
                try {
                    poiItemDetail.setDistance((int) Float.parseFloat(b));
                } catch (NumberFormatException e) {
                    C1128d.m4975a(e, "JSONHelper", "parseBasePoi");
                } catch (Exception e2) {
                    C1128d.m4975a(e2, "JSONHelper", "parseBasePoi");
                }
                if (poiItemDetail.getDistance() == 0) {
                    poiItemDetail.setDistance(-1);
                }
            }
        }
        poiItemDetail.setTel(JSONHelper.m5011b(jSONObject, "tel"));
        poiItemDetail.setTypeDes(JSONHelper.m5011b(jSONObject, "type"));
        poiItemDetail.setEnter(JSONHelper.m5016c(jSONObject, "entr_location"));
        poiItemDetail.setExit(JSONHelper.m5016c(jSONObject, "exit_location"));
        poiItemDetail.setWebsite(JSONHelper.m5011b(jSONObject, "website"));
        poiItemDetail.setPostcode(JSONHelper.m5011b(jSONObject, "citycode"));
        poiItemDetail.setEmail(JSONHelper.m5011b(jSONObject, "email"));
        if (JSONHelper.m5031g(JSONHelper.m5011b(jSONObject, "groupbuy_num"))) {
            poiItemDetail.setGroupbuyInfo(false);
        } else {
            poiItemDetail.setGroupbuyInfo(true);
        }
        if (JSONHelper.m5031g(JSONHelper.m5011b(jSONObject, "discount_num"))) {
            poiItemDetail.setDiscountInfo(false);
        } else {
            poiItemDetail.setDiscountInfo(true);
        }
        if (JSONHelper.m5031g(JSONHelper.m5011b(jSONObject, "indoor_map"))) {
            poiItemDetail.setIndoorMap(false);
        } else {
            poiItemDetail.setIndoorMap(true);
        }
        return poiItemDetail;
    }

    /* renamed from: e */
    public static ArrayList<BusStationItem> m5026e(JSONObject jSONObject) throws JSONException {
        ArrayList<BusStationItem> arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("busstops");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(JSONHelper.m5028f(optJSONObject));
            }
        }
        return arrayList;
    }

    /* renamed from: f */
    public static BusStationItem m5028f(JSONObject jSONObject) throws JSONException {
        BusStationItem g = JSONHelper.m5030g(jSONObject);
        if (g == null) {
            return g;
        }
        g.setAdCode(JSONHelper.m5011b(jSONObject, "adcode"));
        g.setCityCode(JSONHelper.m5011b(jSONObject, "citycode"));
        JSONArray optJSONArray = jSONObject.optJSONArray("buslines");
        ArrayList arrayList = new ArrayList();
        if (optJSONArray == null) {
            g.setBusLineItems(arrayList);
            return g;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(JSONHelper.m5032h(optJSONObject));
            }
        }
        g.setBusLineItems(arrayList);
        return g;
    }

    /* renamed from: g */
    public static BusStationItem m5030g(JSONObject jSONObject) throws JSONException {
        BusStationItem busStationItem = new BusStationItem();
        busStationItem.setBusStationId(JSONHelper.m5011b(jSONObject, "id"));
        busStationItem.setLatLonPoint(JSONHelper.m5016c(jSONObject, LocationManagerProxy.KEY_LOCATION_CHANGED));
        busStationItem.setBusStationName(JSONHelper.m5011b(jSONObject, "name"));
        return busStationItem;
    }

    /* renamed from: h */
    public static BusLineItem m5032h(JSONObject jSONObject) throws JSONException {
        BusLineItem busLineItem = new BusLineItem();
        busLineItem.setBusLineId(JSONHelper.m5011b(jSONObject, "id"));
        busLineItem.setBusLineType(JSONHelper.m5011b(jSONObject, "type"));
        busLineItem.setBusLineName(JSONHelper.m5011b(jSONObject, "name"));
        busLineItem.setDirectionsCoordinates(JSONHelper.m5023d(jSONObject, "polyline"));
        busLineItem.setCityCode(JSONHelper.m5011b(jSONObject, "citycode"));
        busLineItem.setOriginatingStation(JSONHelper.m5011b(jSONObject, "start_stop"));
        busLineItem.setTerminalStation(JSONHelper.m5011b(jSONObject, "end_stop"));
        return busLineItem;
    }

    /* renamed from: i */
    public static ArrayList<BusLineItem> m5035i(JSONObject jSONObject) throws JSONException {
        ArrayList<BusLineItem> arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("buslines");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(JSONHelper.m5037j(optJSONObject));
            }
        }
        return arrayList;
    }

    /* renamed from: j */
    public static BusLineItem m5037j(JSONObject jSONObject) throws JSONException {
        BusLineItem h = JSONHelper.m5032h(jSONObject);
        if (h == null) {
            return h;
        }
        h.setFirstBusTime(C1128d.m4979d(JSONHelper.m5011b(jSONObject, "start_time")));
        h.setLastBusTime(C1128d.m4979d(JSONHelper.m5011b(jSONObject, "end_time")));
        h.setBusCompany(JSONHelper.m5011b(jSONObject, "company"));
        h.setDistance(JSONHelper.m5036j(JSONHelper.m5011b(jSONObject, "distance")));
        h.setBasicPrice(JSONHelper.m5036j(JSONHelper.m5011b(jSONObject, "basic_price")));
        h.setTotalPrice(JSONHelper.m5036j(JSONHelper.m5011b(jSONObject, "total_price")));
        h.setBounds(JSONHelper.m5023d(jSONObject, "bounds"));
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("busstops");
        if (optJSONArray == null) {
            h.setBusStations(arrayList);
            return h;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(JSONHelper.m5030g(optJSONObject));
            }
        }
        h.setBusStations(arrayList);
        return h;
    }

    /* renamed from: a */
    public static Scenic m4997a(PoiItemDetail poiItemDetail, JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        Scenic scenic = new Scenic();
        scenic.setIntro(JSONHelper.m5011b(jSONObject, "intro"));
        scenic.setRating(JSONHelper.m5011b(jSONObject, "rating"));
        scenic.setDeepsrc(JSONHelper.m5011b(jSONObject, "deepsrc"));
        scenic.setLevel(JSONHelper.m5011b(jSONObject, "level"));
        scenic.setPrice(JSONHelper.m5011b(jSONObject, AnalyticsArgs.TRANSACTION_ITEM_PRICE));
        scenic.setSeason(JSONHelper.m5011b(jSONObject, "season"));
        scenic.setRecommend(JSONHelper.m5011b(jSONObject, "recommend"));
        scenic.setTheme(JSONHelper.m5011b(jSONObject, "theme"));
        scenic.setOrderWapUrl(JSONHelper.m5011b(jSONObject, "ordering_wap_url"));
        scenic.setOrderWebUrl(JSONHelper.m5011b(jSONObject, "ordering_web_url"));
        scenic.setOpentimeGDF(JSONHelper.m5011b(jSONObject, "opentime_GDF"));
        scenic.setOpentime(JSONHelper.m5011b(jSONObject, "opentime"));
        scenic.setPhotos(JSONHelper.m5040l(jSONObject));
        poiItemDetail.setDeepType(DeepType.SCENIC);
        poiItemDetail.setScenic(scenic);
        return scenic;
    }

    /* renamed from: b */
    public static void m5014b(PoiItemDetail poiItemDetail, JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        Cinema cinema = new Cinema();
        cinema.setIntro(JSONHelper.m5011b(jSONObject, "intro"));
        cinema.setRating(JSONHelper.m5011b(jSONObject, "rating"));
        cinema.setDeepsrc(JSONHelper.m5011b(jSONObject, "deepsrc"));
        cinema.setParking(JSONHelper.m5011b(jSONObject, "parking"));
        cinema.setOpentimeGDF(JSONHelper.m5011b(jSONObject, "opentime_GDF"));
        cinema.setOpentime(JSONHelper.m5011b(jSONObject, "opentime"));
        cinema.setPhotos(JSONHelper.m5040l(jSONObject));
        if (JSONHelper.m5039k(jSONObject2)) {
            cinema.setSeatOrdering(JSONHelper.m5009a(jSONObject2, "seat_ordering"));
        }
        poiItemDetail.setDeepType(DeepType.CINEMA);
        poiItemDetail.setCinema(cinema);
    }

    /* renamed from: c */
    public static void m5020c(PoiItemDetail poiItemDetail, JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        Hotel hotel = new Hotel();
        hotel.setStar(JSONHelper.m5011b(jSONObject, "star"));
        hotel.setIntro(JSONHelper.m5011b(jSONObject, "intro"));
        hotel.setRating(JSONHelper.m5011b(jSONObject, "rating"));
        hotel.setLowestPrice(JSONHelper.m5011b(jSONObject, "lowest_price"));
        hotel.setDeepsrc(JSONHelper.m5011b(jSONObject, "deepsrc"));
        hotel.setFaciRating(JSONHelper.m5011b(jSONObject, "faci_rating"));
        hotel.setHealthRating(JSONHelper.m5011b(jSONObject, "health_rating"));
        hotel.setEnvironmentRating(JSONHelper.m5011b(jSONObject, "environment_rating"));
        hotel.setServiceRating(JSONHelper.m5011b(jSONObject, "service_rating"));
        hotel.setTraffic(JSONHelper.m5011b(jSONObject, "traffic"));
        hotel.setAddition(JSONHelper.m5011b(jSONObject, "addition"));
        hotel.setPhotos(JSONHelper.m5040l(jSONObject));
        poiItemDetail.setDeepType(DeepType.HOTEL);
        poiItemDetail.setHotel(hotel);
    }

    /* renamed from: d */
    public static void m5024d(PoiItemDetail poiItemDetail, JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        Dining dining = new Dining();
        dining.setCuisines(JSONHelper.m5011b(jSONObject, "cuisines"));
        dining.setTag(JSONHelper.m5011b(jSONObject, "tag"));
        dining.setIntro(JSONHelper.m5011b(jSONObject, "intro"));
        dining.setRating(JSONHelper.m5011b(jSONObject, "rating"));
        dining.setCpRating(JSONHelper.m5011b(jSONObject, "cp_rating"));
        dining.setDeepsrc(JSONHelper.m5011b(jSONObject, "deepsrc"));
        dining.setTasteRating(JSONHelper.m5011b(jSONObject, "taste_rating"));
        dining.setEnvironmentRating(JSONHelper.m5011b(jSONObject, "environment_rating"));
        dining.setServiceRating(JSONHelper.m5011b(jSONObject, "service_rating"));
        dining.setCost(JSONHelper.m5011b(jSONObject, "cost"));
        dining.setRecommend(JSONHelper.m5011b(jSONObject, "recommend"));
        dining.setAtmosphere(JSONHelper.m5011b(jSONObject, "atmosphere"));
        dining.setOrderingWapUrl(JSONHelper.m5011b(jSONObject, "ordering_wap_url"));
        dining.setOrderingWebUrl(JSONHelper.m5011b(jSONObject, "ordering_web_url"));
        dining.setOrderinAppUrl(JSONHelper.m5011b(jSONObject, "ordering_app_url"));
        dining.setOpentimeGDF(JSONHelper.m5011b(jSONObject, "opentime_GDF"));
        dining.setOpentime(JSONHelper.m5011b(jSONObject, "opentime"));
        dining.setAddition(JSONHelper.m5011b(jSONObject, "addition"));
        dining.setPhotos(JSONHelper.m5040l(jSONObject));
        if (JSONHelper.m5039k(jSONObject2)) {
            dining.setMealOrdering(JSONHelper.m5009a(jSONObject2, "meal_ordering"));
        }
        poiItemDetail.setDeepType(DeepType.DINING);
        poiItemDetail.setDining(dining);
    }

    /* renamed from: e */
    public static void m5027e(PoiItemDetail poiItemDetail, JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        if (jSONObject != null) {
            String b = JSONHelper.m5011b(jSONObject, "type");
            if (b.equalsIgnoreCase("hotel")) {
                JSONHelper.m5020c(poiItemDetail, jSONObject, jSONObject2);
            }
            if (b.equalsIgnoreCase(JiceArgs.EVENT_BASKET_ADDRESS_PICKUP)) {
                JSONHelper.m5024d(poiItemDetail, jSONObject, jSONObject2);
            }
            if (b.equalsIgnoreCase("cinema")) {
                JSONHelper.m5014b(poiItemDetail, jSONObject, jSONObject2);
            }
            if (b.equalsIgnoreCase("scenic")) {
                JSONHelper.m4997a(poiItemDetail, jSONObject, jSONObject2);
            }
        }
    }

    /* renamed from: a */
    public static boolean m5009a(JSONObject jSONObject, String str) throws JSONException {
        return JSONHelper.m5008a(JSONHelper.m5011b(jSONObject.optJSONObject("biz_ext"), str));
    }

    /* renamed from: a */
    public static boolean m5008a(String str) {
        try {
            if (str.equals("")) {
                return false;
            }
            int parseInt = Integer.parseInt(str);
            if (parseInt != 0 && parseInt == 1) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            C1128d.m4975a(e, "JSONHelper", "ordingStr2Boolean");
            return false;
        } catch (Exception e2) {
            C1128d.m4975a(e2, "JSONHelper", "ordingStr2BooleanException");
            return false;
        }
    }

    /* renamed from: k */
    public static boolean m5039k(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.has("biz_ext")) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public static void m5002a(PoiItemDetail poiItemDetail, JSONObject jSONObject) throws JSONException {
        if (jSONObject != null) {
            if (poiItemDetail.isGroupbuyInfo()) {
                JSONHelper.m5013b(poiItemDetail, jSONObject);
            }
            if (poiItemDetail.isDiscountInfo()) {
                JSONHelper.m5019c(poiItemDetail, jSONObject);
            }
        }
    }

    /* renamed from: b */
    public static void m5013b(PoiItemDetail poiItemDetail, JSONObject jSONObject) throws JSONException {
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray("groupbuys");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        Groupbuy groupbuy = new Groupbuy();
                        groupbuy.setTypeCode(JSONHelper.m5011b(optJSONObject, "typecode"));
                        groupbuy.setTypeDes(JSONHelper.m5011b(optJSONObject, "type"));
                        groupbuy.setDetail(JSONHelper.m5011b(optJSONObject, "detail"));
                        groupbuy.setStartTime(C1128d.m4978c(JSONHelper.m5011b(optJSONObject, "start_time")));
                        groupbuy.setEndTime(C1128d.m4978c(JSONHelper.m5011b(optJSONObject, "end_time")));
                        groupbuy.setCount(JSONHelper.m5034i(JSONHelper.m5011b(optJSONObject, "num")));
                        groupbuy.setSoldCount(JSONHelper.m5034i(JSONHelper.m5011b(optJSONObject, "sold_num")));
                        groupbuy.setOriginalPrice(JSONHelper.m5036j(JSONHelper.m5011b(optJSONObject, Promotion.COLUMN_ORIGINAL_PRICE)));
                        groupbuy.setGroupbuyPrice(JSONHelper.m5036j(JSONHelper.m5011b(optJSONObject, "groupbuy_price")));
                        groupbuy.setDiscount(JSONHelper.m5036j(JSONHelper.m5011b(optJSONObject, "discount")));
                        groupbuy.setTicketAddress(JSONHelper.m5011b(optJSONObject, "ticket_address"));
                        groupbuy.setTicketTel(JSONHelper.m5011b(optJSONObject, "ticket_tel"));
                        groupbuy.setUrl(JSONHelper.m5011b(optJSONObject, NativeProtocol.IMAGE_URL_KEY));
                        groupbuy.setProvider(JSONHelper.m5011b(optJSONObject, "provider"));
                        JSONHelper.m5001a(groupbuy, optJSONObject);
                        poiItemDetail.addGroupbuy(groupbuy);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static void m5001a(Groupbuy groupbuy, JSONObject jSONObject) throws JSONException {
        groupbuy.initPhotos(JSONHelper.m5040l(jSONObject));
    }

    /* renamed from: c */
    public static void m5019c(PoiItemDetail poiItemDetail, JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray("discounts");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    Discount discount = new Discount();
                    discount.setTitle(JSONHelper.m5011b(optJSONObject, PushConstants.TITLE_KEY));
                    discount.setDetail(JSONHelper.m5011b(optJSONObject, "detail"));
                    discount.setStartTime(C1128d.m4978c(JSONHelper.m5011b(optJSONObject, "start_time")));
                    discount.setEndTime(C1128d.m4978c(JSONHelper.m5011b(optJSONObject, "end_time")));
                    discount.setSoldCount(JSONHelper.m5034i(JSONHelper.m5011b(optJSONObject, "sold_num")));
                    discount.setUrl(JSONHelper.m5011b(optJSONObject, NativeProtocol.IMAGE_URL_KEY));
                    discount.setProvider(JSONHelper.m5011b(optJSONObject, "provider"));
                    JSONHelper.m5000a(discount, optJSONObject);
                    poiItemDetail.addDiscount(discount);
                }
            }
        }
    }

    /* renamed from: a */
    public static void m5000a(Discount discount, JSONObject jSONObject) {
        discount.initPhotos(JSONHelper.m5040l(jSONObject));
    }

    /* renamed from: l */
    public static List<Photo> m5040l(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject != null && jSONObject.has("photos")) {
            try {
                JSONArray optJSONArray = jSONObject.optJSONArray("photos");
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    Photo photo = new Photo();
                    photo.setTitle(JSONHelper.m5011b(optJSONObject, PushConstants.TITLE_KEY));
                    photo.setUrl(JSONHelper.m5011b(optJSONObject, NativeProtocol.IMAGE_URL_KEY));
                    arrayList.add(photo);
                }
            } catch (Exception e) {
                C1128d.m4975a(e, "JSONHelper", "getPhotoList");
            }
        }
        return arrayList;
    }

    /* renamed from: m */
    public static DistrictItem m5042m(JSONObject jSONObject) throws JSONException {
        DistrictItem districtItem = new DistrictItem();
        districtItem.setCitycode(JSONHelper.m5011b(jSONObject, "citycode"));
        districtItem.setAdcode(JSONHelper.m5011b(jSONObject, "adcode"));
        districtItem.setName(JSONHelper.m5011b(jSONObject, "name"));
        districtItem.setLevel(JSONHelper.m5011b(jSONObject, "level"));
        districtItem.setCenter(JSONHelper.m5016c(jSONObject, Parameters.CENTER));
        JSONHelper.m5006a(jSONObject.optJSONArray("districts"), new ArrayList(), districtItem);
        return districtItem;
    }

    /* renamed from: a */
    public static void m5006a(JSONArray jSONArray, ArrayList<DistrictItem> arrayList, DistrictItem districtItem) throws JSONException {
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    arrayList.add(JSONHelper.m5042m(optJSONObject));
                }
            }
            if (districtItem != null) {
                districtItem.setSubDistrict(arrayList);
            }
        }
    }

    /* renamed from: n */
    public static ArrayList<GeocodeAddress> m5043n(JSONObject jSONObject) throws JSONException {
        ArrayList<GeocodeAddress> arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("geocodes");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                GeocodeAddress geocodeAddress = new GeocodeAddress();
                geocodeAddress.setFormatAddress(JSONHelper.m5011b(optJSONObject, "formatted_address"));
                geocodeAddress.setProvince(JSONHelper.m5011b(optJSONObject, DistrictSearchQuery.KEYWORDS_PROVINCE));
                geocodeAddress.setCity(JSONHelper.m5011b(optJSONObject, DistrictSearchQuery.KEYWORDS_CITY));
                geocodeAddress.setDistrict(JSONHelper.m5011b(optJSONObject, DistrictSearchQuery.KEYWORDS_DISTRICT));
                geocodeAddress.setTownship(JSONHelper.m5011b(optJSONObject, "township"));
                geocodeAddress.setNeighborhood(JSONHelper.m5011b(optJSONObject.optJSONObject("neighborhood"), "name"));
                geocodeAddress.setBuilding(JSONHelper.m5011b(optJSONObject.optJSONObject("building"), "name"));
                geocodeAddress.setAdcode(JSONHelper.m5011b(optJSONObject, "adcode"));
                geocodeAddress.setLatLonPoint(JSONHelper.m5016c(optJSONObject, LocationManagerProxy.KEY_LOCATION_CHANGED));
                geocodeAddress.setLevel(JSONHelper.m5011b(optJSONObject, "level"));
                arrayList.add(geocodeAddress);
            }
        }
        return arrayList;
    }

    /* renamed from: o */
    public static ArrayList<Tip> m5044o(JSONObject jSONObject) throws JSONException {
        ArrayList<Tip> arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("tips");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            Tip tip = new Tip();
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                tip.setName(JSONHelper.m5011b(optJSONObject, "name"));
                tip.setDistrict(JSONHelper.m5011b(optJSONObject, DistrictSearchQuery.KEYWORDS_DISTRICT));
                tip.setAdcode(JSONHelper.m5011b(optJSONObject, "adcode"));
                arrayList.add(tip);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static void m5005a(JSONArray jSONArray, RegeocodeAddress regeocodeAddress) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Crossroad crossroad = new Crossroad();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                crossroad.setId(JSONHelper.m5011b(optJSONObject, "id"));
                crossroad.setDirection(JSONHelper.m5011b(optJSONObject, "direction"));
                crossroad.setDistance(JSONHelper.m5036j(JSONHelper.m5011b(optJSONObject, "distance")));
                crossroad.setCenterPoint(JSONHelper.m5016c(optJSONObject, LocationManagerProxy.KEY_LOCATION_CHANGED));
                crossroad.setFirstRoadId(JSONHelper.m5011b(optJSONObject, "first_id"));
                crossroad.setFirstRoadName(JSONHelper.m5011b(optJSONObject, "first_name"));
                crossroad.setSecondRoadId(JSONHelper.m5011b(optJSONObject, "second_id"));
                crossroad.setSecondRoadName(JSONHelper.m5011b(optJSONObject, "second_name"));
                arrayList.add(crossroad);
            }
        }
        regeocodeAddress.setCrossroads(arrayList);
    }

    /* renamed from: b */
    public static void m5015b(JSONArray jSONArray, RegeocodeAddress regeocodeAddress) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            RegeocodeRoad regeocodeRoad = new RegeocodeRoad();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                regeocodeRoad.setId(JSONHelper.m5011b(optJSONObject, "id"));
                regeocodeRoad.setName(JSONHelper.m5011b(optJSONObject, "name"));
                regeocodeRoad.setLatLngPoint(JSONHelper.m5016c(optJSONObject, LocationManagerProxy.KEY_LOCATION_CHANGED));
                regeocodeRoad.setDirection(JSONHelper.m5011b(optJSONObject, "direction"));
                regeocodeRoad.setDistance(JSONHelper.m5036j(JSONHelper.m5011b(optJSONObject, "distance")));
                arrayList.add(regeocodeRoad);
            }
        }
        regeocodeAddress.setRoads(arrayList);
    }

    /* renamed from: a */
    public static void m5007a(JSONObject jSONObject, RegeocodeAddress regeocodeAddress) throws JSONException {
        regeocodeAddress.setProvince(JSONHelper.m5011b(jSONObject, DistrictSearchQuery.KEYWORDS_PROVINCE));
        regeocodeAddress.setCity(JSONHelper.m5011b(jSONObject, DistrictSearchQuery.KEYWORDS_CITY));
        regeocodeAddress.setCityCode(JSONHelper.m5011b(jSONObject, "citycode"));
        regeocodeAddress.setAdCode(JSONHelper.m5011b(jSONObject, "adcode"));
        regeocodeAddress.setDistrict(JSONHelper.m5011b(jSONObject, DistrictSearchQuery.KEYWORDS_DISTRICT));
        regeocodeAddress.setTownship(JSONHelper.m5011b(jSONObject, "township"));
        regeocodeAddress.setNeighborhood(JSONHelper.m5011b(jSONObject.optJSONObject("neighborhood"), "name"));
        regeocodeAddress.setBuilding(JSONHelper.m5011b(jSONObject.optJSONObject("building"), "name"));
        StreetNumber streetNumber = new StreetNumber();
        JSONObject optJSONObject = jSONObject.optJSONObject("streetNumber");
        streetNumber.setStreet(JSONHelper.m5011b(optJSONObject, "street"));
        streetNumber.setNumber(JSONHelper.m5011b(optJSONObject, "number"));
        streetNumber.setLatLonPoint(JSONHelper.m5016c(optJSONObject, LocationManagerProxy.KEY_LOCATION_CHANGED));
        streetNumber.setDirection(JSONHelper.m5011b(optJSONObject, "direction"));
        streetNumber.setDistance(JSONHelper.m5036j(JSONHelper.m5011b(optJSONObject, "distance")));
        regeocodeAddress.setStreetNumber(streetNumber);
        regeocodeAddress.setBusinessAreas(JSONHelper.m5045p(jSONObject));
    }

    /* renamed from: p */
    public static List<BusinessArea> m5045p(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("businessAreas");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            BusinessArea businessArea = new BusinessArea();
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                businessArea.setCenterPoint(JSONHelper.m5016c(optJSONObject, LocationManagerProxy.KEY_LOCATION_CHANGED));
                businessArea.setName(JSONHelper.m5011b(optJSONObject, "name"));
                arrayList.add(businessArea);
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    public static BusRouteResult m5010b(String str) throws AMapException {
        BusRouteResult busRouteResult = null;
        try {
            JSONObject init = JSONObjectInstrumentation.init(str);
            if (init.has("route")) {
                busRouteResult = new BusRouteResult();
                init = init.optJSONObject("route");
                if (init != null) {
                    busRouteResult.setStartPos(JSONHelper.m5016c(init, "origin"));
                    busRouteResult.setTargetPos(JSONHelper.m5016c(init, "destination"));
                    busRouteResult.setTaxiCost(JSONHelper.m5036j(JSONHelper.m5011b(init, "taxi_cost")));
                    if (init.has("transits")) {
                        JSONArray optJSONArray = init.optJSONArray("transits");
                        if (optJSONArray != null) {
                            busRouteResult.setPaths(JSONHelper.m4999a(optJSONArray));
                        }
                    }
                }
            }
            return busRouteResult;
        } catch (JSONException e) {
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    /* renamed from: a */
    public static List<BusPath> m4999a(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            BusPath busPath = new BusPath();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                busPath.setCost(JSONHelper.m5036j(JSONHelper.m5011b(optJSONObject, "cost")));
                busPath.setDuration(JSONHelper.m5038k(JSONHelper.m5011b(optJSONObject, InstallReferrer.KEY_DURATION)));
                busPath.setNightBus(JSONHelper.m5041l(JSONHelper.m5011b(optJSONObject, "nightflag")));
                busPath.setWalkDistance(JSONHelper.m5036j(JSONHelper.m5011b(optJSONObject, "walking_distance")));
                JSONArray optJSONArray = optJSONObject.optJSONArray("segments");
                if (optJSONArray != null) {
                    ArrayList arrayList2 = new ArrayList();
                    float f = 0.0f;
                    float f2 = 0.0f;
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                        if (optJSONObject2 != null) {
                            BusStep q = JSONHelper.m5046q(optJSONObject2);
                            if (q != null) {
                                arrayList2.add(q);
                                if (q.getWalk() != null) {
                                    f += q.getWalk().getDistance();
                                }
                                if (q.getBusLine() != null) {
                                    f2 += q.getBusLine().getDistance();
                                }
                            }
                        }
                    }
                    busPath.setSteps(arrayList2);
                    busPath.setBusDistance(f2);
                    busPath.setWalkDistance(f);
                    arrayList.add(busPath);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: q */
    public static BusStep m5046q(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        BusStep busStep = new BusStep();
        JSONObject optJSONObject = jSONObject.optJSONObject("walking");
        if (optJSONObject != null) {
            busStep.setWalk(JSONHelper.m5047r(optJSONObject));
        }
        optJSONObject = jSONObject.optJSONObject("bus");
        if (optJSONObject != null) {
            busStep.setBusLines(JSONHelper.m5048s(optJSONObject));
        }
        optJSONObject = jSONObject.optJSONObject("entrance");
        if (optJSONObject != null) {
            busStep.setEntrance(JSONHelper.m5049t(optJSONObject));
        }
        optJSONObject = jSONObject.optJSONObject("exit");
        if (optJSONObject == null) {
            return busStep;
        }
        busStep.setExit(JSONHelper.m5049t(optJSONObject));
        return busStep;
    }

    /* renamed from: r */
    public static RouteBusWalkItem m5047r(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        RouteBusWalkItem routeBusWalkItem = new RouteBusWalkItem();
        routeBusWalkItem.setOrigin(JSONHelper.m5016c(jSONObject, "origin"));
        routeBusWalkItem.setDestination(JSONHelper.m5016c(jSONObject, "destination"));
        routeBusWalkItem.setDistance(JSONHelper.m5036j(JSONHelper.m5011b(jSONObject, "distance")));
        routeBusWalkItem.setDuration(JSONHelper.m5038k(JSONHelper.m5011b(jSONObject, InstallReferrer.KEY_DURATION)));
        if (!jSONObject.has("steps")) {
            return routeBusWalkItem;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("steps");
        if (optJSONArray == null) {
            return routeBusWalkItem;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(JSONHelper.m5050u(optJSONObject));
            }
        }
        routeBusWalkItem.setSteps(arrayList);
        return routeBusWalkItem;
    }

    /* renamed from: s */
    public static List<RouteBusLineItem> m5048s(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("buslines");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(JSONHelper.m5051v(optJSONObject));
            }
        }
        return arrayList;
    }

    /* renamed from: t */
    public static Doorway m5049t(JSONObject jSONObject) throws JSONException {
        Doorway doorway = new Doorway();
        doorway.setName(JSONHelper.m5011b(jSONObject, "name"));
        doorway.setLatLonPoint(JSONHelper.m5016c(jSONObject, LocationManagerProxy.KEY_LOCATION_CHANGED));
        return doorway;
    }

    /* renamed from: u */
    public static WalkStep m5050u(JSONObject jSONObject) throws JSONException {
        WalkStep walkStep = new WalkStep();
        walkStep.setInstruction(JSONHelper.m5011b(jSONObject, "instruction"));
        walkStep.setOrientation(JSONHelper.m5011b(jSONObject, "orientation"));
        walkStep.setRoad(JSONHelper.m5011b(jSONObject, "road"));
        walkStep.setDistance(JSONHelper.m5036j(JSONHelper.m5011b(jSONObject, "distance")));
        walkStep.setDuration(JSONHelper.m5036j(JSONHelper.m5011b(jSONObject, InstallReferrer.KEY_DURATION)));
        walkStep.setPolyline(JSONHelper.m5023d(jSONObject, "polyline"));
        walkStep.setAction(JSONHelper.m5011b(jSONObject, com.mcdonalds.sdk.services.analytics.tagmanager.Parameters.ACTION));
        walkStep.setAssistantAction(JSONHelper.m5011b(jSONObject, "assistant_action"));
        return walkStep;
    }

    /* renamed from: v */
    public static RouteBusLineItem m5051v(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        RouteBusLineItem routeBusLineItem = new RouteBusLineItem();
        routeBusLineItem.setDepartureBusStation(JSONHelper.m5053x(jSONObject.optJSONObject("departure_stop")));
        routeBusLineItem.setArrivalBusStation(JSONHelper.m5053x(jSONObject.optJSONObject("arrival_stop")));
        routeBusLineItem.setBusLineName(JSONHelper.m5011b(jSONObject, "name"));
        routeBusLineItem.setBusLineId(JSONHelper.m5011b(jSONObject, "id"));
        routeBusLineItem.setBusLineType(JSONHelper.m5011b(jSONObject, "type"));
        routeBusLineItem.setDistance(JSONHelper.m5036j(JSONHelper.m5011b(jSONObject, "distance")));
        routeBusLineItem.setDuration(JSONHelper.m5036j(JSONHelper.m5011b(jSONObject, InstallReferrer.KEY_DURATION)));
        routeBusLineItem.setPolyline(JSONHelper.m5023d(jSONObject, "polyline"));
        routeBusLineItem.setFirstBusTime(C1128d.m4979d(JSONHelper.m5011b(jSONObject, "start_time")));
        routeBusLineItem.setLastBusTime(C1128d.m4979d(JSONHelper.m5011b(jSONObject, "end_time")));
        routeBusLineItem.setPassStationNum(JSONHelper.m5034i(JSONHelper.m5011b(jSONObject, "via_num")));
        routeBusLineItem.setPassStations(JSONHelper.m5052w(jSONObject));
        return routeBusLineItem;
    }

    /* renamed from: w */
    public static List<BusStationItem> m5052w(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("via_stops");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(JSONHelper.m5053x(optJSONObject));
            }
        }
        return arrayList;
    }

    /* renamed from: x */
    public static BusStationItem m5053x(JSONObject jSONObject) throws JSONException {
        BusStationItem busStationItem = new BusStationItem();
        busStationItem.setBusStationName(JSONHelper.m5011b(jSONObject, "name"));
        busStationItem.setBusStationId(JSONHelper.m5011b(jSONObject, "id"));
        busStationItem.setLatLonPoint(JSONHelper.m5016c(jSONObject, LocationManagerProxy.KEY_LOCATION_CHANGED));
        return busStationItem;
    }

    /* renamed from: c */
    public static DriveRouteResult m5017c(String str) throws AMapException {
        DriveRouteResult driveRouteResult = null;
        try {
            JSONObject init = JSONObjectInstrumentation.init(str);
            if (init.has("route")) {
                driveRouteResult = new DriveRouteResult();
                init = init.optJSONObject("route");
                if (init != null) {
                    driveRouteResult.setStartPos(JSONHelper.m5016c(init, "origin"));
                    driveRouteResult.setTargetPos(JSONHelper.m5016c(init, "destination"));
                    driveRouteResult.setTaxiCost(JSONHelper.m5036j(JSONHelper.m5011b(init, "taxi_cost")));
                    if (init.has("paths")) {
                        JSONArray optJSONArray = init.optJSONArray("paths");
                        if (optJSONArray != null) {
                            ArrayList arrayList = new ArrayList();
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                DrivePath drivePath = new DrivePath();
                                init = optJSONArray.optJSONObject(i);
                                if (init != null) {
                                    drivePath.setDistance(JSONHelper.m5036j(JSONHelper.m5011b(init, "distance")));
                                    drivePath.setDuration(JSONHelper.m5038k(JSONHelper.m5011b(init, InstallReferrer.KEY_DURATION)));
                                    drivePath.setStrategy(JSONHelper.m5011b(init, "strategy"));
                                    drivePath.setTolls(JSONHelper.m5036j(JSONHelper.m5011b(init, "tolls")));
                                    drivePath.setTollDistance(JSONHelper.m5036j(JSONHelper.m5011b(init, "toll_distance")));
                                    JSONArray optJSONArray2 = init.optJSONArray("steps");
                                    if (optJSONArray2 != null) {
                                        ArrayList arrayList2 = new ArrayList();
                                        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                            DriveStep driveStep = new DriveStep();
                                            JSONObject optJSONObject = optJSONArray2.optJSONObject(i2);
                                            if (optJSONObject != null) {
                                                driveStep.setInstruction(JSONHelper.m5011b(optJSONObject, "instruction"));
                                                driveStep.setOrientation(JSONHelper.m5011b(optJSONObject, "orientation"));
                                                driveStep.setRoad(JSONHelper.m5011b(optJSONObject, "road"));
                                                driveStep.setDistance(JSONHelper.m5036j(JSONHelper.m5011b(optJSONObject, "distance")));
                                                driveStep.setTolls(JSONHelper.m5036j(JSONHelper.m5011b(optJSONObject, "tolls")));
                                                driveStep.setTollDistance(JSONHelper.m5036j(JSONHelper.m5011b(optJSONObject, "toll_distance")));
                                                driveStep.setTollRoad(JSONHelper.m5011b(optJSONObject, "toll_road"));
                                                driveStep.setDuration(JSONHelper.m5036j(JSONHelper.m5011b(optJSONObject, InstallReferrer.KEY_DURATION)));
                                                driveStep.setPolyline(JSONHelper.m5023d(optJSONObject, "polyline"));
                                                driveStep.setAction(JSONHelper.m5011b(optJSONObject, com.mcdonalds.sdk.services.analytics.tagmanager.Parameters.ACTION));
                                                driveStep.setAssistantAction(JSONHelper.m5011b(optJSONObject, "assistant_action"));
                                                JSONHelper.m5003a(driveStep, optJSONObject);
                                                arrayList2.add(driveStep);
                                            }
                                        }
                                        drivePath.setSteps(arrayList2);
                                        arrayList.add(drivePath);
                                    }
                                }
                            }
                            driveRouteResult.setPaths(arrayList);
                        }
                    }
                }
            }
            return driveRouteResult;
        } catch (JSONException e) {
            C1128d.m4975a(e, "JSONHelper", "parseDriveRoute");
            throw new AMapException("协议解析错误 - ProtocolException");
        } catch (Throwable th) {
            C1128d.m4975a(th, "JSONHelper", "parseDriveRouteThrowable");
            AMapException aMapException = new AMapException("协议解析错误 - ProtocolException");
        }
    }

    /* renamed from: a */
    public static void m5003a(DriveStep driveStep, JSONObject jSONObject) {
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("cities");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    RouteSearchCity routeSearchCity = new RouteSearchCity();
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        routeSearchCity.setSearchCityName(JSONHelper.m5011b(optJSONObject, "name"));
                        routeSearchCity.setSearchCitycode(JSONHelper.m5011b(optJSONObject, "citycode"));
                        routeSearchCity.setSearchCityhAdCode(JSONHelper.m5011b(optJSONObject, "adcode"));
                        JSONHelper.m5004a(routeSearchCity, optJSONObject);
                        arrayList.add(routeSearchCity);
                    }
                }
                driveStep.setRouteSearchCityList(arrayList);
            }
        } catch (JSONException e) {
            C1128d.m4975a(e, "JSONHelper", "parseCrossCity");
        }
    }

    /* renamed from: a */
    public static void m5004a(RouteSearchCity routeSearchCity, JSONObject jSONObject) {
        if (jSONObject.has("districts")) {
            try {
                ArrayList arrayList = new ArrayList();
                JSONArray optJSONArray = jSONObject.optJSONArray("districts");
                if (optJSONArray == null) {
                    routeSearchCity.setDistricts(arrayList);
                    return;
                }
                for (int i = 0; i < optJSONArray.length(); i++) {
                    District district = new District();
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        district.setDistrictName(JSONHelper.m5011b(optJSONObject, "name"));
                        district.setDistrictAdcode(JSONHelper.m5011b(optJSONObject, "adcode"));
                        arrayList.add(district);
                    }
                }
                routeSearchCity.setDistricts(arrayList);
            } catch (JSONException e) {
                C1128d.m4975a(e, "JSONHelper", "parseCrossDistricts");
            }
        }
    }

    /* renamed from: d */
    public static WalkRouteResult m5022d(String str) {
        Throwable e;
        WalkRouteResult walkRouteResult;
        try {
            JSONObject init = JSONObjectInstrumentation.init(str);
            if (!init.has("route")) {
                return null;
            }
            walkRouteResult = new WalkRouteResult();
            try {
                JSONObject optJSONObject = init.optJSONObject("route");
                walkRouteResult.setStartPos(JSONHelper.m5016c(optJSONObject, "origin"));
                walkRouteResult.setTargetPos(JSONHelper.m5016c(optJSONObject, "destination"));
                if (!optJSONObject.has("paths")) {
                    return walkRouteResult;
                }
                ArrayList arrayList = new ArrayList();
                JSONArray optJSONArray = optJSONObject.optJSONArray("paths");
                if (optJSONArray == null) {
                    walkRouteResult.setPaths(arrayList);
                    return walkRouteResult;
                }
                for (int i = 0; i < optJSONArray.length(); i++) {
                    WalkPath walkPath = new WalkPath();
                    optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        walkPath.setDistance(JSONHelper.m5036j(JSONHelper.m5011b(optJSONObject, "distance")));
                        walkPath.setDuration(JSONHelper.m5038k(JSONHelper.m5011b(optJSONObject, InstallReferrer.KEY_DURATION)));
                        if (optJSONObject.has("steps")) {
                            JSONArray optJSONArray2 = optJSONObject.optJSONArray("steps");
                            ArrayList arrayList2 = new ArrayList();
                            if (optJSONArray2 != null) {
                                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                    WalkStep walkStep = new WalkStep();
                                    JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                                    if (optJSONObject2 != null) {
                                        walkStep.setInstruction(JSONHelper.m5011b(optJSONObject2, "instruction"));
                                        walkStep.setOrientation(JSONHelper.m5011b(optJSONObject2, "orientation"));
                                        walkStep.setRoad(JSONHelper.m5011b(optJSONObject2, "road"));
                                        walkStep.setDistance(JSONHelper.m5036j(JSONHelper.m5011b(optJSONObject2, "distance")));
                                        walkStep.setDuration(JSONHelper.m5036j(JSONHelper.m5011b(optJSONObject2, InstallReferrer.KEY_DURATION)));
                                        walkStep.setPolyline(JSONHelper.m5023d(optJSONObject2, "polyline"));
                                        walkStep.setAction(JSONHelper.m5011b(optJSONObject2, com.mcdonalds.sdk.services.analytics.tagmanager.Parameters.ACTION));
                                        walkStep.setAssistantAction(JSONHelper.m5011b(optJSONObject2, "assistant_action"));
                                        arrayList2.add(walkStep);
                                    }
                                }
                                walkPath.setSteps(arrayList2);
                            }
                        }
                        arrayList.add(walkPath);
                    }
                }
                walkRouteResult.setPaths(arrayList);
                return walkRouteResult;
            } catch (JSONException e2) {
                e = e2;
                C1128d.m4975a(e, "JSONHelper", "parseWalkRoute");
                return walkRouteResult;
            }
        } catch (JSONException e3) {
            Throwable th = e3;
            walkRouteResult = null;
            e = th;
            C1128d.m4975a(e, "JSONHelper", "parseWalkRoute");
            return walkRouteResult;
        }
    }

    /* renamed from: b */
    public static String m5011b(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null) {
            return "";
        }
        String str2 = "";
        if (!jSONObject.has(str) || jSONObject.getString(str).equals("[]")) {
            return str2;
        }
        return jSONObject.optString(str);
    }

    /* renamed from: c */
    public static LatLonPoint m5016c(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject != null && jSONObject.has(str)) {
            return JSONHelper.m5029f(jSONObject.optString(str));
        }
        return null;
    }

    /* renamed from: d */
    public static ArrayList<LatLonPoint> m5023d(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject.has(str)) {
            return JSONHelper.m5025e(jSONObject.getString(str));
        }
        return null;
    }

    /* renamed from: e */
    public static ArrayList<LatLonPoint> m5025e(String str) {
        ArrayList arrayList = new ArrayList();
        String[] split = str.split(";");
        for (String f : split) {
            arrayList.add(JSONHelper.m5029f(f));
        }
        return arrayList;
    }

    /* renamed from: f */
    public static LatLonPoint m5029f(String str) {
        if (str == null || str.equals("") || str.equals("[]")) {
            return null;
        }
        String[] split = str.split(",");
        if (split.length != 2) {
            return null;
        }
        return new LatLonPoint(Double.parseDouble(split[1]), Double.parseDouble(split[0]));
    }

    /* renamed from: g */
    public static boolean m5031g(String str) {
        if (str == null || str.equals("") || str.equals("0")) {
            return true;
        }
        return false;
    }

    /* renamed from: h */
    public static boolean m5033h(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        return false;
    }

    /* renamed from: i */
    public static int m5034i(String str) {
        int i = 0;
        if (str == null || str.equals("") || str.equals("[]")) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            C1128d.m4975a(e, "JSONHelper", "str2int");
            return i;
        }
    }

    /* renamed from: j */
    public static float m5036j(String str) {
        float f = 0.0f;
        if (str == null || str.equals("") || str.equals("[]")) {
            return f;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            C1128d.m4975a(e, "JSONHelper", "str2float");
            return f;
        }
    }

    /* renamed from: k */
    public static long m5038k(String str) {
        long j = 0;
        if (str == null || str.equals("") || str.equals("[]")) {
            return j;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            C1128d.m4975a(e, "JSONHelper", "str2long");
            return j;
        }
    }

    /* renamed from: l */
    public static boolean m5041l(String str) {
        if (str == null || str.equals("") || str.equals("[]") || str.equals("0") || !str.equals("1")) {
            return false;
        }
        return true;
    }
}
