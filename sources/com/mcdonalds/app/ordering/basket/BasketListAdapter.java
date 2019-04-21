package com.mcdonalds.app.ordering.basket;

import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout.LayoutParams;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.offers.OfferActivity;
import com.mcdonalds.app.offers.OfferFragment;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.services.data.DataPasser;
import java.text.NumberFormat;

public class BasketListAdapter extends ArrayAdapter<BasketListItem> {
    private final URLNavigationActivity mContext;
    private int mEditingPosition = -1;
    private Boolean mIsEditMode = Boolean.valueOf(false);
    private final BasketItemActionListener mListener;
    private Order mOrder;

    static /* synthetic */ void access$000(BasketListAdapter x0, Object x1, int x2, int x3) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketListAdapter", "access$000", new Object[]{x0, x1, new Integer(x2), new Integer(x3)});
        x0.removeItem(x1, x2, x3);
    }

    static /* synthetic */ void access$100(BasketListAdapter x0, Object x1, int x2, int x3) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketListAdapter", "access$100", new Object[]{x0, x1, new Integer(x2), new Integer(x3)});
        x0.makeItAMeal(x1, x2, x3);
    }

    static /* synthetic */ void access$200(BasketListAdapter x0, Object x1, int x2, int x3) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.basket.BasketListAdapter", "access$200", new Object[]{x0, x1, new Integer(x2), new Integer(x3)});
        x0.editItem(x1, x2, x3);
    }

    public BasketListAdapter(URLNavigationActivity context, BasketItemActionListener listener, Order order) {
        super(context, C2658R.layout.basket_list_item);
        this.mListener = listener;
        this.mContext = context;
        this.mOrder = order;
    }

    public void setIsEditMode(Boolean isEditMode) {
        Ensighten.evaluateEvent(this, "setIsEditMode", new Object[]{isEditMode});
        this.mIsEditMode = isEditMode;
        notifyDataSetChanged();
    }

    public int getItemViewType(int position) {
        Ensighten.evaluateEvent(this, "getItemViewType", new Object[]{new Integer(position)});
        if (((BasketListItem) getItem(position)).isSubtotalItem()) {
            return 1;
        }
        return 0;
    }

    public int getViewTypeCount() {
        Ensighten.evaluateEvent(this, "getViewTypeCount", null);
        return 2;
    }

    public View getView(int position, View view, ViewGroup viewGroup) {
        BasketListItemViewHolder holder;
        final BasketListItem item = (BasketListItem) getItem(position);
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            if (item.isSubtotalItem()) {
                view = inflater.inflate(C2658R.layout.basket_subtotal_item, null);
                view.setTag(new BasketSubtotalItemViewHolder(view));
            } else {
                view = inflater.inflate(C2658R.layout.basket_list_item, null);
                holder = new BasketListItemViewHolder(view);
                view.setTag(holder);
                holder.getSelectedButton().setVisibility(8);
                holder.getHatButton().setVisibility(8);
                holder.getInfoButton().setVisibility(8);
                holder.getView().setLayoutParams(new LayoutParams(-1, -2));
                holder.getView().setMinimumHeight(UIUtils.dpAsPixels(getContext(), 50));
            }
        }
        DataLayerClickListener.setDataLayerTag(view, BasketListAdapter.class, position);
        final int i;
        if (item.isSubtotalItem()) {
            SubtotalBasketListItem offerItem = (SubtotalBasketListItem) item;
            BasketSubtotalItemViewHolder viewHolder = (BasketSubtotalItemViewHolder) view.getTag();
            if (offerItem.isDeliveryHidden()) {
                viewHolder.getDeliveryFeeContainer().setVisibility(8);
                viewHolder.getDeliveryFeeOfferContainer().setVisibility(8);
                if (offerItem.isShowTotal()) {
                    viewHolder.getTotalContainer().setVisibility(0);
                    viewHolder.getTotalAmount().setText(offerItem.getPriceTotal());
                    viewHolder.getSubtotalContainer().setVisibility(8);
                } else {
                    viewHolder.getTotalContainer().setVisibility(8);
                }
            } else {
                viewHolder.getDeliveryFeeContainer().setVisibility(0);
                NumberFormat formatter = UIUtils.getLocalizedCurrencyFormatter();
                viewHolder.getDeliveryFeeAmount().setText(formatter.format(offerItem.getDeliveryFee()));
                viewHolder.getTotalContainer().setVisibility(0);
                if (offerItem.isDeliveryFeeOfferHidden()) {
                    viewHolder.getDeliveryFeeOfferContainer().setVisibility(8);
                } else {
                    viewHolder.getDeliveryFeeOfferContainer().setVisibility(0);
                    viewHolder.getDeliveryFeeOfferTitle().setText(offerItem.getOfferName());
                    viewHolder.getDeliveryFeeOfferDiscountAmount().setText(formatter.format(offerItem.getDeliveryFeeDiscount() - offerItem.getDeliveryFee()));
                }
                viewHolder.getTotalAmount().setText(offerItem.getPriceTotal());
            }
            if (AppUtils.hideNutritionOnOrderingPages()) {
                viewHolder.getSubtotalEnergy().setVisibility(8);
            } else {
                viewHolder.getSubtotalEnergy().setText(item.getEnergyTotal());
            }
            viewHolder.getSubtotalPrice().setText(UIUtils.getLocalizedCurrencyFormatter().format(this.mOrder.getTotalValue()));
            viewHolder.getOfferWillApply().setVisibility(8);
            if (offerItem.getIsNonProductOfferAvailable() && this.mIsEditMode.booleanValue() && ConfigurationUtils.shouldEnableEditForOrderDiscountOffer()) {
                viewHolder.getEditContainer().setVisibility(0);
                i = position;
                viewHolder.getRemoveButton().setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                        BasketListAdapter.access$000(BasketListAdapter.this, item.getBasketItem(), i, item.getPositionInMeal());
                    }
                });
            } else {
                viewHolder.getEditContainer().setVisibility(8);
            }
            if (offerItem.getIsNonProductOfferAvailable() && offerItem.getHideOfferUnavailableContainer()) {
                viewHolder.getAlertIcon().setVisibility(8);
                viewHolder.getNotAvailableWarning().setVisibility(8);
                viewHolder.getOfferName().setText(TextUtils.join(",", offerItem.getNonProductOfferNames()));
                viewHolder.getOfferWillApply().setVisibility(0);
            } else if (offerItem.getHideOfferUnavailableContainer() || !offerItem.getIsNonProductOfferAvailable()) {
                viewHolder.getOfferUnavailableContainer().setVisibility(8);
            } else {
                viewHolder.getOfferUnavailableContainer().setVisibility(0);
                viewHolder.getOfferName().setText(TextUtils.join(",", offerItem.getNonProductOfferNames()));
            }
        } else {
            holder = (BasketListItemViewHolder) view.getTag();
            holder.getTopPad().setVisibility(item.getTopPaddingHidden().booleanValue() ? 8 : 0);
            holder.getErrorContainer().setVisibility(8);
            holder.getInfoButton().setVisibility(8);
            if (item.hasError()) {
                holder.getErrorContainer().setVisibility(0);
                if (item.isUnavailable()) {
                    if (!item.isMealHeaderNonErrorWarningItem()) {
                        holder.getInfoButton().setImageDrawable(ContextCompat.getDrawable(this.mContext, C2358R.C2359drawable.icon_warn));
                        holder.getInfoButton().setVisibility(0);
                    }
                    if (item.isMealErrorItem()) {
                        holder.getErrorContainer().setVisibility(8);
                    } else {
                        holder.getErrorTextView().setText(getContext().getString(C2658R.string.item_unavailable));
                    }
                } else if (item.isOutOfStock()) {
                    if (item.isMealErrorItem()) {
                        holder.getErrorContainer().setVisibility(8);
                    } else {
                        holder.getErrorTextView().setText(getContext().getString(C2658R.string.item_out_of_stock));
                    }
                    if (!item.isMealHeaderNonErrorWarningItem()) {
                        holder.getInfoButton().setImageDrawable(ContextCompat.getDrawable(this.mContext, C2358R.C2359drawable.icon_warn));
                        holder.getInfoButton().setVisibility(0);
                    }
                } else if (item.isOfferUnavailable()) {
                    if (item.getHeaderHidden().booleanValue()) {
                        holder.getErrorContainer().setVisibility(8);
                    } else {
                        if (item.getOfferPODErrorCode() == -8002) {
                            String pickup = viewGroup.getContext().getString(C2658R.string.pickup);
                            holder.getErrorTextView().setText(viewGroup.getContext().getString(C2658R.string.offer_unavailable_for_pod_short, new Object[]{pickup}));
                        } else if (item.getOfferPODErrorCode() == -8003) {
                            String delivery = viewGroup.getContext().getString(C2658R.string.delivery);
                            holder.getErrorTextView().setText(viewGroup.getContext().getString(C2658R.string.offer_unavailable_for_pod_short, new Object[]{delivery}));
                        } else {
                            holder.getErrorTextView().setText(C2658R.string.offer_unavailable);
                        }
                        if (item.isOutOfStock() || item.isUnavailable()) {
                            holder.getInfoButton().setImageDrawable(ContextCompat.getDrawable(this.mContext, C2358R.C2359drawable.icon_warn));
                            holder.getInfoButton().setVisibility(0);
                        }
                    }
                } else if (item.isPriceChanged()) {
                    holder.getErrorTextView().setText(getContext().getString(C2658R.string.label_error_price_changed));
                }
            }
            if (item.getHeaderHidden().booleanValue()) {
                holder.getHeaderContainer().setVisibility(8);
            } else {
                holder.getHeaderContainer().setVisibility(0);
                holder.getHeaderIconView().setVisibility(item.getHeaderIconHidden().booleanValue() ? 8 : 0);
                if (item.getBasketItem() instanceof OrderProduct) {
                    OrderProduct orderProduct = (OrderProduct) item.getBasketItem();
                    holder.getHeaderTextView().setText(orderProduct.getQuantity() + " " + orderProduct.getProduct().getLongName());
                } else {
                    holder.getHeaderTextView().setText(item.getHeaderText());
                }
            }
            if (item.getDividerHidden().booleanValue()) {
                holder.getDividerContainer().setVisibility(8);
            } else {
                holder.getDividerContainer().setVisibility(0);
            }
            if (item.isNonProductOffer()) {
                holder.getMakeItAMealButton().setVisibility(8);
                holder.getName().setText(item.getItemName());
                if (item.getIconImage() != null) {
                    Glide.with(this.mContext).load(item.getIconImage().getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) C2358R.C2359drawable.icon_large_meal).into(holder.getFoodImageIcon());
                }
            } else {
                holder.getName().setText(item.getItemName());
                double uplift = item.getItemUplift();
                if (!ConfigurationUtils.shouldShowUpLiftPrice() || uplift < 0.01d) {
                    holder.getPriceUplift().setVisibility(8);
                } else {
                    holder.getPriceUplift().setVisibility(0);
                    holder.getPriceUplift().setText(String.format("+ %s", new Object[]{UIUtils.getLocalizedCurrencyFormatter().format(uplift)}));
                }
                if (item.getHeaderDetailsText() == null || item.getHeaderDetailsText().isEmpty()) {
                    holder.getNameDetails().setText("");
                    holder.getNameDetails().setVisibility(8);
                } else {
                    holder.getNameDetails().setVisibility(0);
                    holder.getNameDetails().setText(item.getHeaderDetailsText());
                }
                holder.getSpecialInstructions().setText(item.getItemInstructions());
                if (item.getMakeItAMealHidden().booleanValue()) {
                    holder.getMakeItAMealButton().setVisibility(8);
                } else {
                    holder.getMakeItAMealButton().setVisibility(0);
                    i = position;
                    holder.getMakeItAMealButton().setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                            BasketListAdapter.access$100(BasketListAdapter.this, item.getBasketItem(), i, item.getPositionInMeal());
                        }
                    });
                }
                if (item.getIconImage() != null) {
                    Glide.with(this.mContext).load(item.getIconImage().getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) C2358R.C2359drawable.icon_large_meal).into(holder.getFoodImageIcon());
                }
                String limitedPods = item.getUnavailablePODMessage();
                Log.d("MY_TAG", "getview List PODs: " + limitedPods);
                if (TextUtils.isEmpty(limitedPods)) {
                    holder.getUnAvailablePODMessage().setVisibility(8);
                } else {
                    holder.getUnAvailablePODMessage().setVisibility(8);
                    holder.getUnAvailablePODMessage().setText(limitedPods);
                }
            }
            if (item.getFooterHidden().booleanValue()) {
                holder.getEnergyPriceContainer().setVisibility(8);
                holder.getEditContainer().setVisibility(8);
            } else if (this.mIsEditMode.booleanValue()) {
                holder.getEnergyPriceContainer().setVisibility(8);
                holder.getEditContainer().setVisibility(0);
                i = position;
                holder.getRemoveButton().setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                        BasketListAdapter.access$000(BasketListAdapter.this, item.getBasketItem(), i, item.getPositionInMeal());
                    }
                });
                i = position;
                holder.getEditButton().setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                        BasketListAdapter.access$200(BasketListAdapter.this, item.getBasketItem(), i, item.getPositionInMeal());
                    }
                });
            } else {
                String totalPriceLabel = String.format("%s", new Object[]{item.getPriceTotal()});
                holder.getEnergyPriceContainer().setVisibility(0);
                holder.getPriceTotalView().setText(totalPriceLabel);
                holder.getEditContainer().setVisibility(8);
                UIUtils.showTimeRestrictionAlert(holder.getTimeRestrictionWarning(), item.getTimeRestrictions());
                if (AppUtils.hideNutritionOnOrderingPages()) {
                    holder.getEnergyTotalView().setVisibility(8);
                } else {
                    holder.getEnergyTotalView().setText(item.getEnergyTotal());
                }
            }
        }
        Ensighten.getViewReturnValue(view, position);
        Ensighten.processView((Object) this, "getView");
        Ensighten.getViewReturnValue(null, -1);
        return view;
    }

    public void updateOrder(Order order) {
        Ensighten.evaluateEvent(this, "updateOrder", new Object[]{order});
        this.mOrder = order;
    }

    private void removeItem(Object item, int viewPosition, int itemPosition) {
        Ensighten.evaluateEvent(this, "removeItem", new Object[]{item, new Integer(viewPosition), new Integer(itemPosition)});
        if (itemPosition <= viewPosition) {
            this.mEditingPosition = viewPosition - itemPosition;
        }
        if (this.mListener != null) {
            this.mListener.onActionRemove(item);
        }
    }

    private void makeItAMeal(Object item, int viewPosition, int itemPosition) {
        Ensighten.evaluateEvent(this, "makeItAMeal", new Object[]{item, new Integer(viewPosition), new Integer(itemPosition)});
        if (itemPosition <= viewPosition) {
            this.mEditingPosition = viewPosition - itemPosition;
        }
        if (this.mListener != null) {
            this.mListener.onActionMakeItAMeal(item);
        }
    }

    private void editItem(Object item, int viewPosition, int itemPosition) {
        Ensighten.evaluateEvent(this, "editItem", new Object[]{item, new Integer(viewPosition), new Integer(itemPosition)});
        if (itemPosition <= viewPosition) {
            this.mEditingPosition = viewPosition - itemPosition;
        }
        Bundle extras;
        if (item instanceof OrderProduct) {
            if (this.mListener != null) {
                this.mListener.onActionEdit(item);
            }
        } else if (item instanceof OrderOffer) {
            extras = new Bundle();
            extras.putParcelable("extra_offer", ((OrderOffer) item).getOffer());
            extras.putInt("edit_order_data_passer_id", DataPasser.getInstance().putData(this.mOrder));
            extras.putInt("edit_order_offer_data_passer_id", DataPasser.getInstance().putData(item));
            extras.putBoolean("IN_EDIT_MODE", true);
            this.mContext.startActivityForResult(OfferActivity.class, "offer_detail", extras, OfferFragment.REQUEST_CODE.intValue());
        } else if ((item instanceof BasketListItem) && (((BasketListItem) item).getBasketItem() instanceof OrderOffer)) {
            extras = new Bundle();
            extras.putParcelable("extra_offer", ((OrderOffer) ((BasketListItem) item).getBasketItem()).getOffer());
            extras.putInt("edit_order_data_passer_id", DataPasser.getInstance().putData(this.mOrder));
            extras.putInt("edit_order_offer_data_passer_id", DataPasser.getInstance().putData(item));
            extras.putBoolean("IN_EDIT_MODE", true);
            this.mContext.startActivityForResult(OfferActivity.class, "offer_detail", extras, OfferFragment.REQUEST_CODE.intValue());
        }
    }

    public void productEdited() {
        Ensighten.evaluateEvent(this, "productEdited", null);
        if (!this.mOrder.isEmpty() && this.mEditingPosition != -1 && this.mEditingPosition < getCount()) {
            BasketListItem item = (BasketListItem) getItem(this.mEditingPosition);
            if (item != null) {
                item.setOfferPODErrorCode(0);
                item.setHasError(false);
                item.setOutOfStock(false);
                item.setUnavailable(false);
                item.setPriceChanged(false);
                item.setOfferUnavailable(false);
                item.setOfferPriceChanged(false);
            }
            notifyDataSetChanged();
        }
    }
}
