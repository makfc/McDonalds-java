package com.mcdonalds.app.ordering.choiceselector;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.p001v7.widget.LinearLayoutManager;
import android.support.p001v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import com.ensighten.Ensighten;
import com.facebook.widget.FacebookDialog;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.databinding.ActivityChoiceSelectorBinding;
import com.mcdonalds.app.navigation.NavigationManager;
import com.mcdonalds.app.ordering.ProductCustomizationActivity;
import com.mcdonalds.app.ordering.ProductCustomizationFragment;
import com.mcdonalds.app.ordering.choiceselector.ChoiceSelectorAdapter.OnProductClickedListener;
import com.mcdonalds.app.ordering.choiceselector.ChoiceSelectorAdapter.OnProductCustomizeClickedListener;
import com.mcdonalds.app.ordering.choiceselector.ChoiceSelectorAdapter.OnProductInfoClickedListener;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.services.data.DataPasser;
import java.util.List;

public class ChoiceSelectorActivity extends URLActionBarActivity implements OnProductClickedListener, OnProductCustomizeClickedListener, OnProductInfoClickedListener, ChoiceSelectorView {
    private ChoiceSelectorAdapter mAdapter;
    private MenuItem mDoneButton;
    private ChoiceSelectorPresenter mPresenter;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChoiceSelectorBinding binding = (ActivityChoiceSelectorBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C2658R.layout.activity_choice_selector, null, false);
        setContentView(binding.getRoot());
        Bundle extras = getIntent().getExtras();
        OrderProduct orderProduct = (OrderProduct) DataPasser.getInstance().getData("ARG_CHOICE_KEY");
        int index = extras.getInt("ARG_INDEX");
        int productPosition = extras.getInt("ARG_PRODUCT_POSITION");
        this.mAdapter = new ChoiceSelectorAdapter();
        this.mPresenter = new ChoiceSelectorPresenter(this, orderProduct);
        this.mPresenter.setIndex(index);
        this.mPresenter.setProductPosition(productPosition);
        this.mAdapter.setBasePrice(this.mPresenter.getBasePrice());
        this.mAdapter.setOnProductClickedListener(this);
        this.mAdapter.setOnProductCustomizeClickedListener(this);
        this.mAdapter.setOnProductInfoClickedListener(this);
        binding.choiceList.setLayoutManager(new LinearLayoutManager(this));
        ((SimpleItemAnimator) binding.choiceList.getItemAnimator()).setSupportsChangeAnimations(false);
        binding.choiceList.setAdapter(this.mAdapter);
        if (extras.getString("ARG_TITLE") != null) {
            setTitle(extras.getString("ARG_TITLE"));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu});
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(C2358R.C2360menu.done, menu);
        this.mDoneButton = menu.findItem(C2358R.C2357id.action_done);
        this.mDoneButton.setEnabled(false);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        switch (item.getItemId()) {
            case 16908332:
                this.mPresenter.back();
                return true;
            case C2358R.C2357id.action_done /*2131821900*/:
                this.mPresenter.done();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showOptions(List<OrderProduct> options, List<Integer> outageCodes) {
        Ensighten.evaluateEvent(this, "showOptions", new Object[]{options, outageCodes});
        this.mAdapter.setOptions(options, outageCodes);
    }

    public void setSelected(int position) {
        Ensighten.evaluateEvent(this, "setSelected", new Object[]{new Integer(position)});
        this.mAdapter.setSelected(position);
    }

    public void setDoneEnabled(boolean enable) {
        Ensighten.evaluateEvent(this, "setDoneEnabled", new Object[]{new Boolean(enable)});
        this.mDoneButton.setEnabled(enable);
    }

    public void cancel() {
        Ensighten.evaluateEvent(this, FacebookDialog.COMPLETION_GESTURE_CANCEL, null);
        setResult(0);
        finish();
    }

    public void finalize(OrderProduct selection, int index, int position) {
        Ensighten.evaluateEvent(this, "finalize", new Object[]{selection, new Integer(index), new Integer(position)});
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        DataPasser.getInstance().putData("RESULT_PRODUCT", selection);
        bundle.putInt("RESULT_INDEX", index);
        bundle.putInt("RESULT_POSITION", position);
        intent.putExtras(bundle);
        setResult(-1, intent);
        finish();
    }

    public void onProductClicked(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "onProductClicked", new Object[]{orderProduct});
        this.mPresenter.setSelection(orderProduct);
    }

    public void onProductCustomizeClicked(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "onProductCustomizeClicked", new Object[]{orderProduct});
        Bundle bundle = new Bundle();
        DataPasser.getInstance().putData("ARG_PRODUCT", orderProduct);
        startActivityForResult(ProductCustomizationActivity.class, "product_customization", bundle, 45352);
    }

    public void updateCustomization(int position) {
        Ensighten.evaluateEvent(this, "updateCustomization", new Object[]{new Integer(position)});
        this.mAdapter.notifyItemChanged(position);
    }

    public void onProductInfoClicked(String recipeId) {
        Ensighten.evaluateEvent(this, "onProductInfoClicked", new Object[]{recipeId});
        NavigationManager.getInstance().showNutrition(this, recipeId, null, null, this);
    }

    public void onBackPressed() {
        Ensighten.evaluateEvent(this, "onBackPressed", null);
        this.mPresenter.back();
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 45352 && resultCode == -1) {
            OrderProduct product;
            if (data.getExtras().containsKey(ProductCustomizationFragment.RESULT_PRODUCT)) {
                product = (OrderProduct) data.getExtras().getParcelable(ProductCustomizationFragment.RESULT_PRODUCT);
            } else {
                product = (OrderProduct) DataPasser.getInstance().getData(ProductCustomizationFragment.RESULT_PRODUCT);
            }
            this.mPresenter.productCustomized(product);
        }
    }
}
