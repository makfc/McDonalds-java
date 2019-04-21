package com.mcdonalds.app.ordering;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.widget.CheckableRelativeLayout;
import com.mcdonalds.app.widget.InertCheckBox;

public class ProductDetailsItem {
    private ImageView mDisclosureArrow;
    private ImageView mFoodImageIcon;
    private ImageButton mHatButton;
    private ImageButton mInfoButton;
    private Button mMakeItAMealButton;
    private TextView mName;
    private TextView mNameDetails;
    private TextView mPriceUplift;
    private InertCheckBox mSelectedButton;
    private TextView mSpecialInstructions;
    private CheckableRelativeLayout mView;

    public ProductDetailsItem(View view) {
        this.mView = (CheckableRelativeLayout) view;
        this.mSelectedButton = (InertCheckBox) view.findViewById(C2358R.C2357id.product_check_box);
        this.mFoodImageIcon = (ImageView) view.findViewById(C2358R.C2357id.food_image_small);
        this.mName = (TextView) view.findViewById(C2358R.C2357id.name);
        this.mPriceUplift = (TextView) view.findViewById(C2358R.C2357id.price_uplift);
        this.mNameDetails = (TextView) view.findViewById(C2358R.C2357id.name_details);
        this.mSpecialInstructions = (TextView) view.findViewById(C2358R.C2357id.custom_special_instructions);
        this.mInfoButton = (ImageButton) view.findViewById(C2358R.C2357id.info_button);
        this.mHatButton = (ImageButton) view.findViewById(C2358R.C2357id.hat_button);
        this.mDisclosureArrow = (ImageView) view.findViewById(C2358R.C2357id.disclosure_arrow);
    }

    public void setViewChecked(ViewGroup parent, int position) {
        Ensighten.evaluateEvent(this, "setViewChecked", new Object[]{parent, new Integer(position)});
        if (parent instanceof ListView) {
            this.mView.setChecked(((ListView) parent).isItemChecked(position));
        }
        this.mView.setChecked(false);
    }

    public void setSubSelection(boolean isSubSelection) {
        Ensighten.evaluateEvent(this, "setSubSelection", new Object[]{new Boolean(isSubSelection)});
        if (isSubSelection) {
            this.mDisclosureArrow.setVisibility(0);
        } else {
            this.mDisclosureArrow.setVisibility(8);
        }
    }

    public void setNameText(String name) {
        Ensighten.evaluateEvent(this, "setNameText", new Object[]{name});
        this.mName.setText(name);
    }

    public void setPriceUpliftText(String priceUplift) {
        Ensighten.evaluateEvent(this, "setPriceUpliftText", new Object[]{priceUplift});
        this.mPriceUplift.setText(priceUplift);
    }

    public void setPriceUpliftTextVisible(boolean setVisible) {
        int i = 0;
        Ensighten.evaluateEvent(this, "setPriceUpliftTextVisible", new Object[]{new Boolean(setVisible)});
        TextView textView = this.mPriceUplift;
        if (!setVisible) {
            i = 8;
        }
        textView.setVisibility(i);
    }

    public void setSpecialInstructionsText(String specialInstructions) {
        Ensighten.evaluateEvent(this, "setSpecialInstructionsText", new Object[]{specialInstructions});
        this.mSpecialInstructions.setText(specialInstructions);
        if (!specialInstructions.isEmpty()) {
            fixLayoutOnInstructions();
        }
    }

    public void setHatButtonVisibility(int visibility) {
        Ensighten.evaluateEvent(this, "setHatButtonVisibility", new Object[]{new Integer(visibility)});
        this.mHatButton.setVisibility(visibility);
    }

    public void setHatButtonOnClickListener(OnClickListener onClickListener) {
        Ensighten.evaluateEvent(this, "setHatButtonOnClickListener", new Object[]{onClickListener});
        this.mHatButton.setOnClickListener(onClickListener);
    }

    public CheckableRelativeLayout getView() {
        return this.mView;
    }

    public InertCheckBox getSelectedButton() {
        Ensighten.evaluateEvent(this, "getSelectedButton", null);
        return this.mSelectedButton;
    }

    public ImageView getFoodImageIcon() {
        Ensighten.evaluateEvent(this, "getFoodImageIcon", null);
        return this.mFoodImageIcon;
    }

    public TextView getName() {
        Ensighten.evaluateEvent(this, "getName", null);
        return this.mName;
    }

    public TextView getPriceUplift() {
        Ensighten.evaluateEvent(this, "getPriceUplift", null);
        return this.mPriceUplift;
    }

    public TextView getNameDetails() {
        Ensighten.evaluateEvent(this, "getNameDetails", null);
        return this.mNameDetails;
    }

    public TextView getSpecialInstructions() {
        Ensighten.evaluateEvent(this, "getSpecialInstructions", null);
        return this.mSpecialInstructions;
    }

    public ImageButton getInfoButton() {
        Ensighten.evaluateEvent(this, "getInfoButton", null);
        return this.mInfoButton;
    }

    public ImageButton getHatButton() {
        Ensighten.evaluateEvent(this, "getHatButton", null);
        return this.mHatButton;
    }

    public void setHatButtonHighlighted(boolean highlighted) {
        Ensighten.evaluateEvent(this, "setHatButtonHighlighted", new Object[]{new Boolean(highlighted)});
        if (highlighted) {
            this.mHatButton.setImageResource(C2358R.C2359drawable.icon_customize_gray_selected);
        } else {
            this.mHatButton.setImageResource(C2358R.C2359drawable.icon_chef_hat);
        }
    }

    public void setHatButtonHighlighted(String customizations) {
        Ensighten.evaluateEvent(this, "setHatButtonHighlighted", new Object[]{customizations});
        if (customizations != null && !customizations.isEmpty()) {
            this.mHatButton.setImageResource(C2358R.C2359drawable.icon_customize_gray_selected);
        }
    }

    public ImageView getDisclosureArrow() {
        Ensighten.evaluateEvent(this, "getDisclosureArrow", null);
        return this.mDisclosureArrow;
    }

    public Button getMakeItAMealButton() {
        Ensighten.evaluateEvent(this, "getMakeItAMealButton", null);
        return this.mMakeItAMealButton;
    }

    private void fixLayoutOnInstructions() {
        Ensighten.evaluateEvent(this, "fixLayoutOnInstructions", null);
        this.mView.setLayoutParams(new LayoutParams(-1, -2));
        this.mView.setMinimumHeight(UIUtils.dpAsPixels(this.mView.getContext(), 50));
    }
}
