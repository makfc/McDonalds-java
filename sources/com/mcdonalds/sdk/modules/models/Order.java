package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.mcdonalds.sdk.connectors.middleware.model.DCSFavorite;
import com.mcdonalds.sdk.modules.AppModel;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.Offer.OfferType;
import com.mcdonalds.sdk.modules.models.PaymentMethod.PaymentMode;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.AppParameters;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Order extends AppModel implements Parcelable, Comparable<Order> {
    public static final Creator<Order> CREATOR = new C25771();
    private static final int OFFER_TYPE_DELIVERY_FEE = 10;
    private String mAlipayResult;
    private OrderResponse mCheckinResult;
    private OrderResponse mCheckoutResult;
    private String mCompanyName;
    private CustomerAddress mDeliveryAddress;
    private Date mDeliveryDate;
    private String mDeliveryDateString;
    private Store mDeliveryStore;
    private List<Integer> mEnabledMenuTypes;
    private Integer mFavoriteId;
    private String mFavoriteName;
    private boolean mInvoiceRequested;
    private boolean mIsDelivery;
    private boolean mIsNormalOrder;
    private boolean mIsPODSet;
    private boolean mNeedsUpdatedRecipes;
    private List<OrderOffer> mOffers;
    private String mOrderNumber;
    private String mOrderRemark;
    private boolean mOrderRemarkAvailable;
    private OrderTableService mOrderTableService;
    private OrderPayment mPayment;
    private PaymentCard mPaymentCard;
    private String mPaymentMethodDisplayName;
    private PaymentMode mPaymentMode;
    private OrderResponse mPreparePaymentResult;
    private PriceType mPriceType;
    private List<OrderProduct> mProducts;
    private CustomerProfile mProfile;
    private Integer mRecentId;
    private String mRecentName;
    private boolean mShowUpsell;
    private String mStoreId;
    private double mTenderAmount;
    private int mTenderType;
    private OrderResponse mTotalizeBeforeCheckin;
    private OrderResponse mTotalizeResult;
    private List<String> mUnavailableProductCodes;
    private int mWechatPaymentResult;
    private boolean mZeroPriced;

    /* renamed from: com.mcdonalds.sdk.modules.models.Order$1 */
    static class C25771 implements Creator<Order> {
        C25771() {
        }

        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }

        public Order[] newArray(int size) {
            return new Order[size];
        }
    }

    public interface OrderChangedListener {
        void onOrderChange();
    }

    public enum PriceType {
        Undefined(0),
        EatIn(1),
        TakeOut(2),
        Delivery(3);
        
        private Integer mIntegerValue;

        private PriceType(int intValue) {
            this.mIntegerValue = Integer.valueOf(intValue);
        }

        public Integer integerValue() {
            return this.mIntegerValue;
        }
    }

    public enum QRCodeSaleType {
        UNUSED(0),
        EatIn(1),
        TakeOut(2),
        EatInTakeOut(3),
        Other(4),
        EatInOther(5),
        TakeOutOther(6),
        EatInTakeOutOther(7);
        
        private Integer mIntegerValue;

        private QRCodeSaleType(int intValue) {
            this.mIntegerValue = Integer.valueOf(intValue);
        }

        public Integer integerValue() {
            return this.mIntegerValue;
        }
    }

    public static Order cloneOrderForEditing(Order order) {
        Order ret = new Order();
        ret.setStoreId(order.getStoreId());
        ret.setPriceType(order.getPriceType());
        ret.setProfile(order.getProfile());
        ret.setRecentId(order.getRecentId());
        ret.setRecentName(order.getRecentName());
        ret.setFavoriteId(order.getFavoriteId());
        ret.setFavoriteName(order.getFavoriteName());
        ret.setIsDelivery(order.isDelivery());
        ret.setDeliveryStore(order.getDeliveryStore());
        ret.setPaymentCard(order.getPaymentCard());
        ret.setDeliveryDate(order.getDeliveryDate());
        ret.setDeliveryDateString(order.getDeliveryDateString());
        ret.setDeliveryAddress(order.getDeliveryAddress());
        ret.setAlipayResult(order.getAlipayResult());
        ret.setPaymentMethodDisplayName(order.getPaymentMethodDisplayName());
        ret.mTotalizeResult = order.mTotalizeResult;
        ret.mTotalizeBeforeCheckin = order.mTotalizeBeforeCheckin;
        ret.mPreparePaymentResult = order.mPreparePaymentResult;
        if (order.isDelivery()) {
            ret.mCheckoutResult = order.mCheckoutResult;
        } else {
            ret.mCheckinResult = order.mCheckinResult;
        }
        ret.mPayment = order.mPayment;
        ret.mProducts.addAll(cloneProductsForEditing(order.getProducts()));
        ret.mOffers = cloneOffersForEditing(order.getOffers());
        ret.mOrderTableService = order.mOrderTableService;
        ret.mZeroPriced = order.mZeroPriced;
        ret.setIsPODSet(order.getIsPODSet());
        ret.mTenderType = order.mTenderType;
        ret.mTenderAmount = order.mTenderAmount;
        ret.mIsDelivery = order.mIsDelivery;
        ret.mIsNormalOrder = order.mIsNormalOrder;
        ret.mOrderRemark = order.mOrderRemark;
        ret.mOrderRemarkAvailable = order.mOrderRemarkAvailable;
        return ret;
    }

    private static Collection<? extends OrderProduct> cloneProductsForEditing(Collection<OrderProduct> products) {
        Collection<OrderProduct> productsNew = new ArrayList();
        for (OrderProduct product : products) {
            productsNew.add(OrderProduct.cloneProductForEditing(product));
        }
        return productsNew;
    }

    private static List<OrderOffer> cloneOffersForEditing(Collection<OrderOffer> orderOffers) {
        List<OrderOffer> newOrderOffers = new ArrayList();
        for (OrderOffer orderOffer : orderOffers) {
            newOrderOffers.add(orderOffer.clone());
        }
        return newOrderOffers;
    }

    public Order() {
        this.mShowUpsell = true;
        this.mIsNormalOrder = true;
        this.mCompanyName = "";
        this.mOrderRemark = "";
        this.mWechatPaymentResult = 1;
        this.mProducts = new ArrayList();
        this.mOffers = new ArrayList();
        this.mEnabledMenuTypes = new ArrayList();
    }

    public boolean canAddProducts(Order order) {
        if (this.mEnabledMenuTypes.isEmpty()) {
            return true;
        }
        for (OrderProduct product : order.getProducts()) {
            if (product.getProduct() != null && !canAddProduct(product)) {
                return false;
            }
        }
        return true;
    }

    public boolean addProduct(OrderProduct orderProduct) {
        if (orderProduct == null || orderProduct.getProduct() == null) {
            return true;
        }
        boolean canAdd = canAddProduct(orderProduct);
        if (!canAdd) {
            return canAdd;
        }
        this.mProducts.add(orderProduct);
        setEnabledMenuTypes();
        return canAdd;
    }

    public boolean addEditedProduct(OrderProduct editedProduct, OrderProduct oldProduct) {
        if (editedProduct == null || editedProduct.getProduct() == null) {
            return true;
        }
        boolean canAdd = canAddProduct(editedProduct);
        if (!canAdd) {
            return canAdd;
        }
        int productIndex = 0;
        for (int i = 0; i < this.mProducts.size(); i++) {
            if (((OrderProduct) this.mProducts.get(i)).equals(oldProduct)) {
                productIndex = i;
            }
        }
        this.mProducts.set(productIndex, editedProduct);
        return canAdd;
    }

    private boolean canAddProduct(OrderProduct orderProduct) {
        if (AppParameters.getBooleanForParameter(AppParameters.ENABLE_MULTIPLE_MENU_TYPES)) {
            List<Integer> productMenuTypeIDs = orderProduct.getProduct().getExtendedMenuTypeIDs();
            if (!this.mEnabledMenuTypes.isEmpty() || productMenuTypeIDs == null) {
                List<Integer> newList = new ArrayList(this.mEnabledMenuTypes);
                if (productMenuTypeIDs != null) {
                    newList.retainAll(productMenuTypeIDs);
                }
                if (newList.isEmpty()) {
                    return false;
                }
                this.mEnabledMenuTypes = newList;
                return true;
            }
            this.mEnabledMenuTypes.addAll(productMenuTypeIDs);
            return true;
        }
        int menuTypeId = orderProduct.getProduct().getMenuTypeID().intValue();
        if (this.mEnabledMenuTypes.isEmpty() || this.mEnabledMenuTypes.contains(Integer.valueOf(menuTypeId)) || menuTypeId == 2) {
            return true;
        }
        return false;
    }

    private void setEnabledMenuTypes() {
        this.mEnabledMenuTypes = new ArrayList();
        for (OrderProduct orderProduct : this.mProducts) {
            addMenuTypes(orderProduct);
        }
        if (this.mOffers != null) {
            int size = this.mOffers.size();
            for (int i = 0; i < size; i++) {
                List<OrderOfferProduct> orderOfferProducts = ((OrderOffer) this.mOffers.get(i)).getOrderOfferProducts();
                if (orderOfferProducts != null) {
                    int sizeP = orderOfferProducts.size();
                    for (int j = 0; j < sizeP; j++) {
                        addMenuTypes(((OrderOfferProduct) orderOfferProducts.get(j)).getSelectedProductOption());
                    }
                }
            }
        }
    }

    public boolean haveProducts() {
        if (getProducts() != null && !getProducts().isEmpty()) {
            return true;
        }
        if (this.mOffers != null) {
            int size = this.mOffers.size();
            for (int i = 0; i < size; i++) {
                if (((OrderOffer) this.mOffers.get(i)).getOrderOfferProducts() != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasOffer(Offer offer) {
        if (this.mOffers == null) {
            return false;
        }
        for (OrderOffer orderOffer : this.mOffers) {
            if (orderOffer.getOffer().getOfferId().equals(offer.getOfferId())) {
                return true;
            }
        }
        return false;
    }

    private void addMenuTypes(OrderProduct orderProduct) {
        if (!AppParameters.getBooleanForParameter(AppParameters.ENABLE_MULTIPLE_MENU_TYPES)) {
            int menuTypeId = orderProduct.getProduct().getMenuTypeID().intValue();
            if (this.mEnabledMenuTypes.isEmpty() && menuTypeId != 2) {
                this.mEnabledMenuTypes.add(Integer.valueOf(menuTypeId));
            }
        } else if (orderProduct.getProduct().getExtendedMenuTypeIDs() != null) {
            if (this.mEnabledMenuTypes.isEmpty()) {
                this.mEnabledMenuTypes.addAll(orderProduct.getProduct().getExtendedMenuTypeIDs());
            } else {
                this.mEnabledMenuTypes.retainAll(orderProduct.getProduct().getExtendedMenuTypeIDs());
            }
        }
    }

    public void removeProduct(OrderProduct orderProduct) {
        Iterator<OrderProduct> iterator = this.mProducts.iterator();
        while (iterator.hasNext()) {
            if (((OrderProduct) iterator.next()) == orderProduct) {
                iterator.remove();
                setEnabledMenuTypes();
            }
        }
    }

    public boolean addOffer(OrderOffer orderOffer) {
        if (orderOffer == null) {
            return true;
        }
        List<OrderOfferProduct> products = orderOffer.getOrderOfferProducts();
        if (products != null) {
            int size = products.size();
            for (int i = 0; i < size; i++) {
                OrderProduct option = ((OrderOfferProduct) products.get(i)).getSelectedProductOption();
                if (option == null) {
                    return true;
                }
                if (!canAddProduct(option)) {
                    return false;
                }
            }
        }
        this.mOffers.add(orderOffer);
        setEnabledMenuTypes();
        return true;
    }

    public void removeOffer(OrderOffer orderOffer) {
        if (this.mOffers.contains(orderOffer)) {
            this.mOffers.remove(orderOffer);
            setEnabledMenuTypes();
            return;
        }
        Log.d(DCSFavorite.TYPE_ORDER, "Offer not in basket");
    }

    public void clearOffers() {
        this.mOffers.clear();
        setEnabledMenuTypes();
    }

    public void clearProducts() {
        this.mProducts.clear();
        setEnabledMenuTypes();
    }

    public double getTotalValue() {
        return getProductTotalValue() + getOfferTotalValue();
    }

    public double getProductTotalValue() {
        double ret = 0.0d;
        boolean allowDownCharge = OrderManager.getInstance().allowDownCharge(this);
        for (OrderProduct product : getProducts()) {
            if (!product.isUnavailable()) {
                ret += product.getTotalPrice(getPriceType(), allowDownCharge);
            }
        }
        return ret;
    }

    public double getOfferTotalValue() {
        double ret = 0.0d;
        for (OrderOffer offer : getOffers()) {
            if (offer.getOffer().getOfferType() != OfferType.OFFER_TYPE_DELIVERY_FEE) {
                ret = offer.getTotalPrice(getPriceType());
            }
        }
        return ret;
    }

    public double getTotalEnergy() {
        return getProductTotalEnergy() + getOfferTotalEnergy();
    }

    public double getTotalSecondaryEnergy() {
        return getProductSecondaryEnergy() + getOfferSecondaryEnergy();
    }

    public double getProductSecondaryEnergy() {
        double ret = 0.0d;
        for (OrderProduct product : getProducts()) {
            ret += product.getTotalSecondaryEnergy();
        }
        return ret;
    }

    public double getOfferSecondaryEnergy() {
        double ret = 0.0d;
        for (OrderOffer offer : getOffers()) {
            if (offer.getOrderOfferProducts() != null) {
                for (OrderOfferProduct product : offer.getOrderOfferProducts()) {
                    if (product.getSelectedProductOption() != null) {
                        ret += product.getSelectedProductOption().getTotalSecondaryEnergy();
                    }
                }
            }
        }
        return ret;
    }

    public double getProductTotalEnergy() {
        double ret = 0.0d;
        for (OrderProduct product : getProducts()) {
            ret += product.getTotalEnergy();
        }
        return ret;
    }

    public String getDeliveryChargeOfferName() {
        for (OrderOffer offer : getOffers()) {
            if (offer.getOffer().getOfferType() == OfferType.OFFER_TYPE_DELIVERY_FEE) {
                return offer.getOffer().getName();
            }
        }
        return null;
    }

    public Double getDiscountedDeliveryCharge() {
        for (OrderOffer offer : getOffers()) {
            if (offer.getOffer().getOfferType() == OfferType.OFFER_TYPE_DELIVERY_FEE) {
                List<OrderOfferProduct> orderOfferProduct = offer.getOrderOfferProducts();
                if (orderOfferProduct != null && orderOfferProduct.size() > 0) {
                    return ((OrderOfferProduct) orderOfferProduct.get(0)).getTotalValue(PriceType.Delivery);
                }
            }
        }
        return null;
    }

    public boolean hasDeliveryFeeOffer() {
        for (OrderOffer offer : getOffers()) {
            if (offer.getOffer().getOfferType() == OfferType.OFFER_TYPE_DELIVERY_FEE) {
                return true;
            }
        }
        return false;
    }

    public double getOfferTotalEnergy() {
        double ret = 0.0d;
        for (OrderOffer offer : getOffers()) {
            if (offer.getOrderOfferProducts() != null) {
                for (OrderOfferProduct product : offer.getOrderOfferProducts()) {
                    if (product.getSelectedProductOption() != null) {
                        ret += product.getSelectedProductOption().getTotalEnergy();
                    }
                }
            }
        }
        return ret;
    }

    public Double getTotalTax() {
        if (getCheckinResult() != null) {
            return getCheckinResult().getTotalTax();
        }
        if (getTotalizeResult() != null) {
            return getTotalizeResult().getTotalTax();
        }
        return null;
    }

    public int getTotalOrderCount() {
        OrderProduct product;
        int ret = 0;
        for (OrderProduct product2 : getProducts()) {
            ret += product2.getQuantity();
        }
        if (getOffers() != null) {
            for (OrderOffer orderOffer : getOffers()) {
                if (orderOffer.getOrderOfferProducts() != null) {
                    for (OrderOfferProduct offerProduct : orderOffer.getOrderOfferProducts()) {
                        product2 = offerProduct.getSelectedProductOption();
                        if (product2 != null) {
                            ret += product2.getQuantity();
                        }
                    }
                }
            }
        }
        return ret;
    }

    public int getTotalProductCount(int productId) {
        int ret = 0;
        for (OrderProduct product : getProducts()) {
            if (product.getProduct() != null && product.getProduct().getExternalId().intValue() == productId) {
                ret += product.getQuantity();
            }
        }
        return ret;
    }

    public Collection<OrderProduct> getProducts() {
        return Collections.unmodifiableList(this.mProducts);
    }

    public Collection<OrderOffer> getOffers() {
        return Collections.unmodifiableList(this.mOffers);
    }

    public String getStoreId() {
        return this.mStoreId;
    }

    public void setStoreId(int storeId) {
        setStoreId(Integer.toString(storeId));
    }

    public void setStoreId(String storeId) {
        boolean z = false;
        if (storeId == null || (getProducts().isEmpty() && getOffers().isEmpty())) {
            this.mNeedsUpdatedRecipes = false;
        } else if (this.mStoreId == null) {
            this.mNeedsUpdatedRecipes = true;
        } else {
            if (this.mNeedsUpdatedRecipes || !storeId.equals(this.mStoreId)) {
                z = true;
            }
            this.mNeedsUpdatedRecipes = z;
        }
        this.mStoreId = storeId;
    }

    public PriceType getPriceType() {
        return this.mPriceType;
    }

    public void setPriceType(PriceType priceType) {
        this.mPriceType = priceType;
    }

    public CustomerProfile getProfile() {
        return this.mProfile;
    }

    public void setProfile(CustomerProfile profile) {
        this.mProfile = profile;
    }

    public OrderPayment getPayment() {
        return this.mPayment;
    }

    public void clearPayment() {
        this.mPayment = null;
        this.mPaymentCard = null;
        this.mPaymentMethodDisplayName = null;
    }

    public void setPayment(OrderPayment payment) {
        this.mPayment = payment;
    }

    public PaymentMode getPaymentMode() {
        return this.mPaymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.mPaymentMode = paymentMode;
    }

    public PaymentCard getPaymentCard() {
        return this.mPaymentCard;
    }

    public void setPaymentCard(PaymentCard paymentCard) {
        this.mPaymentCard = paymentCard;
    }

    public Integer getRecentId() {
        return this.mRecentId;
    }

    public void setRecentId(Integer recentId) {
        this.mRecentId = recentId;
    }

    public String getRecentName() {
        return this.mRecentName;
    }

    public void setRecentName(String recentName) {
        this.mRecentName = recentName;
    }

    public String getOrderNumber() {
        return this.mOrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.mOrderNumber = orderNumber;
    }

    public Integer getFavoriteId() {
        return this.mFavoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.mFavoriteId = favoriteId;
    }

    public String getFavoriteName() {
        return this.mFavoriteName;
    }

    public void setFavoriteName(String favoriteName) {
        this.mFavoriteName = favoriteName;
    }

    public boolean isDelivery() {
        return this.mIsDelivery;
    }

    public void setIsDelivery(boolean isDelivery) {
        this.mIsDelivery = isDelivery;
    }

    public OrderResponse getTotalizeResult() {
        return this.mTotalizeResult;
    }

    public void setTotalizeResult(OrderResponse totalizeResult) {
        this.mTotalizeResult = totalizeResult;
    }

    public OrderResponse getTotalizeBeforeCheckin() {
        return this.mTotalizeBeforeCheckin;
    }

    public void setTotalizeBeforeCheckin(OrderResponse totalizeResult) {
        this.mTotalizeBeforeCheckin = totalizeResult;
    }

    public OrderResponse getPreparePaymentResult() {
        return this.mPreparePaymentResult;
    }

    public void setPreparePaymentResult(OrderResponse preparePaymentResult) {
        this.mPreparePaymentResult = preparePaymentResult;
    }

    public OrderResponse getCheckinResult() {
        return this.mCheckinResult;
    }

    public void setCheckinResult(OrderResponse checkinResult) {
        this.mCheckinResult = checkinResult;
    }

    public OrderResponse getCheckoutResult() {
        return this.mCheckoutResult;
    }

    public void setCheckoutResult(OrderResponse mCheckoutResult) {
        this.mCheckoutResult = mCheckoutResult;
    }

    public Date getDeliveryDate() {
        return this.mDeliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.mDeliveryDate = deliveryDate;
    }

    public String getDeliveryDateString() {
        return this.mDeliveryDateString;
    }

    public void setDeliveryDateString(String deliveryDateString) {
        this.mDeliveryDateString = deliveryDateString;
    }

    public boolean isNormalOrder() {
        return this.mIsNormalOrder;
    }

    public void setNormalOrder(boolean normalOrder) {
        this.mIsNormalOrder = normalOrder;
    }

    public CustomerAddress getDeliveryAddress() {
        return this.mDeliveryAddress;
    }

    public void setDeliveryAddress(CustomerAddress deliveryAddress) {
        this.mDeliveryAddress = deliveryAddress;
    }

    public Store getDeliveryStore() {
        return this.mDeliveryStore;
    }

    public void setDeliveryStore(Store deliveryStore) {
        this.mDeliveryStore = deliveryStore;
    }

    public int getTenderType() {
        return this.mTenderType;
    }

    public void setTenderType(int tenderType) {
        this.mTenderType = tenderType;
    }

    public double getTenderAmount() {
        return this.mTenderAmount;
    }

    public void setTenderAmount(double tenderAmount) {
        this.mTenderAmount = tenderAmount;
    }

    public String getAlipayResult() {
        return this.mAlipayResult;
    }

    public void setAlipayResult(String alipayResult) {
        this.mAlipayResult = alipayResult;
    }

    public String getWechatPaymentResult() {
        if (this.mWechatPaymentResult == 0) {
            return "SUCCESS";
        }
        return null;
    }

    public String getPaymentResult() {
        if (getAlipayResult() != null) {
            return getAlipayResult();
        }
        if (this.mWechatPaymentResult != 1) {
            return getWechatPaymentResult();
        }
        return null;
    }

    public void clearPaymentResult() {
        setAlipayResult(null);
        setWechatPaymentResult(1);
    }

    public void setWechatPaymentResult(int result) {
        this.mWechatPaymentResult = result;
    }

    public String getPaymentMethodDisplayName() {
        return this.mPaymentMethodDisplayName;
    }

    public void setPaymentMethodDisplayName(String paymentMethodDisplayName) {
        this.mPaymentMethodDisplayName = paymentMethodDisplayName;
    }

    public int getBasketCounter() {
        return this.mProducts.size();
    }

    public boolean isEmpty() {
        return getBasketCounter() == 0 && ListUtils.isEmpty(getOffers());
    }

    public boolean needsUpdatedRecipes() {
        return this.mNeedsUpdatedRecipes;
    }

    public void setNeedsUpdatedRecipes(boolean needsUpdatedRecipes) {
        this.mNeedsUpdatedRecipes = needsUpdatedRecipes;
    }

    @Deprecated
    public int getDayPart() {
        if (this.mEnabledMenuTypes == null || this.mEnabledMenuTypes.isEmpty()) {
            return -1;
        }
        return ((Integer) this.mEnabledMenuTypes.get(0)).intValue();
    }

    public List<Integer> getEnabledDayParts() {
        return this.mEnabledMenuTypes;
    }

    public boolean checkDayPart(int dayPart) {
        if (this.mEnabledMenuTypes != null) {
            return this.mEnabledMenuTypes.contains(Integer.valueOf(dayPart));
        }
        return false;
    }

    public int compareTo(@NonNull Order another) {
        if (this.mRecentId == null || another.getRecentId() == null) {
            return 0;
        }
        return another.getRecentId().intValue() - this.mRecentId.intValue();
    }

    public void setCompanyName(String name) {
        this.mCompanyName = name;
    }

    public String getCompanyName() {
        return this.mCompanyName;
    }

    private String getCompanyNameOrPersonal() {
        if (TextUtils.isEmpty(this.mCompanyName)) {
            return Configuration.getSharedInstance().getStringForKey("modules.ordering.personal");
        }
        return this.mCompanyName;
    }

    public void setOrderRemark(String orderRemark) {
        this.mOrderRemark = orderRemark;
    }

    public String getOrderRemarkString() {
        return this.mOrderRemark;
    }

    public String getOrderRemark() {
        String orderRemark = Configuration.getSharedInstance().getStringForKey("modules.ordering.orderRemark");
        return String.format("%s: %s", new Object[]{orderRemark, this.mOrderRemark});
    }

    public String getInvoiceInfo() {
        String requested = Configuration.getSharedInstance().getStringForKey("modules.ordering.invoiceRequested");
        String title = Configuration.getSharedInstance().getStringForKey("modules.ordering.invoiceTitle");
        String parameter = "%s: %s: %s";
        if (!this.mInvoiceRequested) {
            return null;
        }
        return String.format(parameter, new Object[]{requested, title, getCompanyNameOrPersonal()});
    }

    public void setOrderRemarkAvailable(boolean avaliable) {
        this.mOrderRemarkAvailable = avaliable;
    }

    public boolean isOrderRemarkAvailable() {
        return this.mOrderRemarkAvailable;
    }

    public void setInvoiceRequested(boolean value) {
        this.mInvoiceRequested = value;
    }

    public boolean invoiceRequested() {
        return this.mInvoiceRequested;
    }

    public boolean showUpsell() {
        return this.mShowUpsell;
    }

    public void setShowUpsell(boolean showUpsell) {
        this.mShowUpsell = showUpsell;
    }

    public void setIsPODSet(boolean isPODSet) {
        this.mIsPODSet = isPODSet;
    }

    public boolean getIsPODSet() {
        return this.mIsPODSet;
    }

    public OrderTableService getOrderTableService() {
        return this.mOrderTableService;
    }

    public void setOrderTableService(OrderTableService orderTableService) {
        this.mOrderTableService = orderTableService;
    }

    public boolean isZeroPriced() {
        return this.mZeroPriced;
    }

    public void setZeroPriced(boolean mZeroPriced) {
        this.mZeroPriced = mZeroPriced;
    }

    public List<String> getUnavailableProductCodes() {
        return this.mUnavailableProductCodes;
    }

    public void setUnavailableProductCodes(List<String> unavailableProductCodes) {
        this.mUnavailableProductCodes = unavailableProductCodes;
    }

    public int getItemsCount() {
        int count = 0;
        for (OrderProduct product : this.mProducts) {
            count += product.getQuantity();
        }
        return count;
    }

    public boolean hasOffers() {
        return this.mOffers != null && this.mOffers.size() > 0;
    }

    public OrderResponse getMostRecentOrderResponse() {
        if (this.mIsDelivery && this.mCheckoutResult != null) {
            return this.mCheckoutResult;
        }
        if (!this.mIsDelivery && this.mCheckinResult != null) {
            return this.mCheckinResult;
        }
        if (this.mPreparePaymentResult != null) {
            return this.mPreparePaymentResult;
        }
        return this.mTotalizeResult;
    }

    public String toString() {
        return "Order{mProducts=" + this.mProducts + ", mOffers=" + this.mOffers + ", mStoreId=\"" + this.mStoreId + "\", mPriceType=" + this.mPriceType + ", mProfile=" + this.mProfile + "}";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        byte b;
        byte b2 = (byte) 1;
        dest.writeList(this.mProducts);
        dest.writeList(this.mOffers);
        dest.writeString(this.mStoreId);
        dest.writeInt(this.mPriceType == null ? -1 : this.mPriceType.ordinal());
        dest.writeParcelable(this.mProfile, flags);
        dest.writeParcelable(this.mPayment, flags);
        dest.writeValue(this.mRecentId);
        dest.writeString(this.mRecentName);
        dest.writeString(this.mOrderNumber);
        dest.writeValue(this.mFavoriteId);
        dest.writeString(this.mFavoriteName);
        if (this.mIsDelivery) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        dest.writeByte(b);
        dest.writeList(this.mEnabledMenuTypes);
        dest.writeParcelable(this.mPaymentCard, flags);
        dest.writeSerializable(this.mTotalizeResult);
        dest.writeSerializable(this.mPreparePaymentResult);
        dest.writeSerializable(this.mTotalizeBeforeCheckin);
        dest.writeSerializable(this.mCheckinResult);
        dest.writeSerializable(this.mCheckoutResult);
        dest.writeLong(this.mDeliveryDate != null ? this.mDeliveryDate.getTime() : -1);
        dest.writeString(this.mDeliveryDateString);
        dest.writeParcelable(this.mDeliveryAddress, 0);
        dest.writeParcelable(this.mDeliveryStore, flags);
        dest.writeInt(this.mTenderType);
        dest.writeDouble(this.mTenderAmount);
        if (this.mNeedsUpdatedRecipes) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        dest.writeByte(b);
        if (!this.mIsPODSet) {
            b2 = (byte) 0;
        }
        dest.writeByte(b2);
        dest.writeParcelable(this.mOrderTableService, flags);
    }

    protected Order(Parcel in) {
        boolean z;
        Date date = null;
        boolean z2 = true;
        this.mShowUpsell = true;
        this.mIsNormalOrder = true;
        this.mCompanyName = "";
        this.mOrderRemark = "";
        this.mWechatPaymentResult = 1;
        this.mProducts = new ArrayList();
        in.readList(this.mProducts, OrderProduct.class.getClassLoader());
        this.mOffers = new ArrayList();
        in.readList(this.mOffers, OrderOffer.class.getClassLoader());
        this.mStoreId = in.readString();
        int tmpMPriceType = in.readInt();
        this.mPriceType = tmpMPriceType == -1 ? null : PriceType.values()[tmpMPriceType];
        this.mProfile = (CustomerProfile) in.readParcelable(CustomerProfile.class.getClassLoader());
        this.mPayment = (OrderPayment) in.readParcelable(OrderPayment.class.getClassLoader());
        this.mRecentId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mRecentName = in.readString();
        this.mOrderNumber = in.readString();
        this.mFavoriteId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mFavoriteName = in.readString();
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.mIsDelivery = z;
        this.mEnabledMenuTypes = new ArrayList();
        in.readList(this.mEnabledMenuTypes, Integer.class.getClassLoader());
        this.mPaymentCard = (PaymentCard) in.readParcelable(PaymentCard.class.getClassLoader());
        this.mTotalizeResult = (OrderResponse) in.readSerializable();
        this.mPreparePaymentResult = (OrderResponse) in.readSerializable();
        this.mTotalizeBeforeCheckin = (OrderResponse) in.readSerializable();
        this.mCheckinResult = (OrderResponse) in.readSerializable();
        this.mCheckoutResult = (OrderResponse) in.readSerializable();
        long tmpMDeliveryDate = in.readLong();
        if (tmpMDeliveryDate != -1) {
            date = new Date(tmpMDeliveryDate);
        }
        this.mDeliveryDate = date;
        this.mDeliveryDateString = in.readString();
        this.mDeliveryAddress = (CustomerAddress) in.readParcelable(CustomerAddress.class.getClassLoader());
        this.mDeliveryStore = (Store) in.readParcelable(Store.class.getClassLoader());
        this.mTenderType = in.readInt();
        this.mTenderAmount = in.readDouble();
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.mNeedsUpdatedRecipes = z;
        if (in.readByte() == (byte) 0) {
            z2 = false;
        }
        this.mIsPODSet = z2;
        this.mOrderTableService = (OrderTableService) in.readParcelable(OrderTableService.class.getClassLoader());
    }
}
