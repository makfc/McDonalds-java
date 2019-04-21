package com.mcdonalds.app.account;

import android.support.p001v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.widget.InertCheckBox;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.List;

public class AddressesAdapter extends Adapter<ViewHolder> {
    private List<CustomerAddress> mAddresses = new ArrayList();
    private ItemClickListener mClickListener;
    private boolean mEditing;
    private int mSelected = -1;
    private CustomerAddress mSelectedAddress;

    public interface ItemClickListener {
        void onItemClicked(CustomerAddress customerAddress);

        void onItemDismissed(int i);
    }

    public class ViewHolder extends android.support.p001v7.widget.RecyclerView.ViewHolder {
        public TextView address;
        public InertCheckBox checkBox;
        public View container;
        public CheckBox deleteBox;
        public TextView header;

        public ViewHolder(View itemView) {
            super(itemView);
            this.container = itemView.findViewById(C2358R.C2357id.address_container);
            this.deleteBox = (CheckBox) itemView.findViewById(C2358R.C2357id.delete_check_box);
            this.checkBox = (InertCheckBox) itemView.findViewById(C2358R.C2357id.address_check_box);
            this.header = (TextView) itemView.findViewById(C2358R.C2357id.address_header_textview);
            this.address = (TextView) itemView.findViewById(C2358R.C2357id.address_textview);
        }
    }

    static /* synthetic */ void access$100(AddressesAdapter x0, CustomerAddress x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.AddressesAdapter", "access$100", new Object[]{x0, x1});
        x0.onItemClicked(x1);
    }

    public void setAddresses(List<CustomerAddress> addresses) {
        Ensighten.evaluateEvent(this, "setAddresses", new Object[]{addresses});
        this.mAddresses = addresses;
        notifyDataSetChanged();
    }

    public void setSelectedAddress(CustomerAddress selectedAddress) {
        Ensighten.evaluateEvent(this, "setSelectedAddress", new Object[]{selectedAddress});
        this.mSelectedAddress = selectedAddress;
    }

    public boolean isEditing() {
        Ensighten.evaluateEvent(this, "isEditing", null);
        return this.mEditing;
    }

    public void setEditing(boolean editing) {
        Ensighten.evaluateEvent(this, "setEditing", new Object[]{new Boolean(editing)});
        this.mEditing = editing;
        notifyDataSetChanged();
    }

    public void setClickListener(ItemClickListener clickListener) {
        Ensighten.evaluateEvent(this, "setClickListener", new Object[]{clickListener});
        this.mClickListener = clickListener;
    }

    public void setSelected(CustomerAddress address) {
        Ensighten.evaluateEvent(this, "setSelected", new Object[]{address});
        this.mSelected = this.mAddresses.indexOf(address);
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(C2658R.layout.fragment_modify_addresses_list_item, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        Ensighten.evaluateEvent(this, "onBindViewHolder", new Object[]{holder, new Integer(position)});
        final CustomerAddress address = (CustomerAddress) this.mAddresses.get(position);
        String label = CustomerAddress.getAddressLabel(address.getAddressType(), holder.itemView.getContext());
        if (Configuration.getSharedInstance().getBooleanForKey("interface.shouldHideAddressTypes")) {
            holder.header.setVisibility(8);
        } else {
            holder.header.setVisibility(0);
            holder.header.setText(label);
        }
        holder.address.setText(getAddressString(address));
        holder.container.setTranslationX(0.0f);
        if (isEditing()) {
            holder.deleteBox.setVisibility(0);
            holder.checkBox.setVisibility(8);
            holder.container.setClickable(false);
            holder.deleteBox.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.AddressesAdapter", "access$000", new Object[]{AddressesAdapter.this}) != null) {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.AddressesAdapter", "access$000", new Object[]{AddressesAdapter.this}).onItemDismissed(position);
                    }
                }
            });
        } else {
            holder.container.setClickable(true);
            holder.deleteBox.setVisibility(8);
            holder.checkBox.setVisibility(0);
            if (this.mSelectedAddress != null) {
                if (address == this.mSelectedAddress) {
                    holder.checkBox.setChecked(true);
                } else {
                    holder.checkBox.setChecked(false);
                }
            } else if (address.isDefaultAddress()) {
                holder.checkBox.setChecked(true);
            } else {
                holder.checkBox.setChecked(false);
            }
        }
        holder.container.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                AddressesAdapter.access$100(AddressesAdapter.this, address);
            }
        });
        DataLayerClickListener.setDataLayerTag(holder.container, ViewHolder.class, position);
    }

    public int getItemCount() {
        Ensighten.evaluateEvent(this, "getItemCount", null);
        return this.mAddresses.size();
    }

    public CustomerAddress getAddressForPosition(int position) {
        Ensighten.evaluateEvent(this, "getAddressForPosition", new Object[]{new Integer(position)});
        return (CustomerAddress) this.mAddresses.get(position);
    }

    public void removeAddress(CustomerAddress address) {
        Ensighten.evaluateEvent(this, "removeAddress", new Object[]{address});
        int position = this.mAddresses.indexOf(address);
        if (position >= 0) {
            this.mAddresses.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void restoreAddress(CustomerAddress address, int position) {
        Ensighten.evaluateEvent(this, "restoreAddress", new Object[]{address, new Integer(position)});
        int size = this.mAddresses.size();
        if (position < size) {
            this.mAddresses.add(position, address);
        } else {
            this.mAddresses.add(address);
            position = size;
        }
        notifyItemInserted(position);
    }

    public boolean hasAddress(CustomerAddress address) {
        Ensighten.evaluateEvent(this, "hasAddress", new Object[]{address});
        return this.mAddresses.contains(address);
    }

    private void onItemClicked(CustomerAddress address) {
        Ensighten.evaluateEvent(this, "onItemClicked", new Object[]{address});
        if (this.mClickListener != null) {
            this.mClickListener.onItemClicked(address);
        }
    }

    private String getAddressString(CustomerAddress address) {
        Ensighten.evaluateEvent(this, "getAddressString", new Object[]{address});
        return address.getFullAddress();
    }
}
