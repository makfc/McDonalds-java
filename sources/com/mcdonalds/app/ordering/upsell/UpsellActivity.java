package com.mcdonalds.app.ordering.upsell;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.p001v7.app.ActionBar;
import android.support.p001v7.app.AppCompatActivity;
import android.support.p001v7.widget.GridLayoutManager;
import android.support.p001v7.widget.RecyclerView;
import android.support.p001v7.widget.RecyclerView.ItemAnimator;
import android.support.p001v7.widget.SimpleItemAnimator;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.ensighten.model.EnsightenGestureRecognizerFactory;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.upsell.UpsellAdapter.UpsellButtonsCallback;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.services.data.DataPasser;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.List;

@Instrumented
public class UpsellActivity extends AppCompatActivity implements OnClickListener, UpsellButtonsCallback, UpsellView, TraceFieldInterface {
    public Trace _nr_trace;
    private UpsellAdapter mAdapter;
    Button mAddToCartButton;
    Button mCancelButton;
    private UpsellPresenter mPresenter;

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        EnsightenGestureRecognizerFactory.getFourFingerGestureRecognizer().process(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityResult", new Object[]{new Integer(i), new Integer(i2), intent});
        super.onActivityResult(i, i2, intent);
    }

    /* Access modifiers changed, original: protected */
    public Dialog onCreateDialog(int i, Bundle bundle) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateDialog", new Object[]{new Integer(i), bundle});
        return super.onCreateDialog(i);
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroy", null);
        super.onDestroy();
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onPause", null);
        super.onPause();
        Ensighten.processView((Object) this, "onPause");
    }

    /* Access modifiers changed, original: protected */
    public void onRestart() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onRestart", null);
        super.onRestart();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onResume", null);
        super.onResume();
        Ensighten.processView((Object) this, "onResume");
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStart", null);
        super.onStart();
        Ensighten.processView((Object) this, "onStart");
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStop", null);
        super.onStop();
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("UpsellActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "UpsellActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "UpsellActivity#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        setContentView((int) C2658R.layout.activity_upsell);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setCustomView((int) C2658R.layout.action_bar_default_custom_view);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            CharSequence title = getString(C2658R.string.button_checkout_label);
            ((TextView) actionBar.getCustomView()).setText(title);
            actionBar.setTitle(title);
        }
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            dismiss();
        } else {
            List<Product> products;
            if (extras.containsKey("param_products")) {
                products = extras.getParcelableArrayList("param_products");
            } else {
                products = (List) DataPasser.getInstance().getData("param_products");
            }
            if (ListUtils.isEmpty(products)) {
                dismiss();
            } else {
                this.mAdapter = new UpsellAdapter(this, this);
                this.mPresenter = new UpsellPresenter(this, products);
                RecyclerView itemList = (RecyclerView) findViewById(C2358R.C2357id.item_list);
                itemList.setLayoutManager(new GridLayoutManager(this, 2));
                itemList.setAdapter(this.mAdapter);
                ItemAnimator animator = itemList.getItemAnimator();
                if (animator instanceof SimpleItemAnimator) {
                    ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
                }
                this.mCancelButton = (Button) findViewById(C2358R.C2357id.cancel_button);
                this.mCancelButton.setOnClickListener(this);
                this.mAddToCartButton = (Button) findViewById(C2358R.C2357id.add_to_cart_button);
                this.mAddToCartButton.setOnClickListener(this);
            }
        }
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        switch (item.getItemId()) {
            case 16908332:
                setResult(100);
                finish();
                return true;
            default:
                return false;
        }
    }

    public void displayItems(List<Product> products, PriceType priceType) {
        Ensighten.evaluateEvent(this, "displayItems", new Object[]{products, priceType});
        this.mAdapter.setRecipes(products, priceType);
    }

    public void markItemSelected(int position) {
        Ensighten.evaluateEvent(this, "markItemSelected", new Object[]{new Integer(position)});
        this.mAdapter.updateQuantity(position, 1);
        this.mAddToCartButton.setEnabled(true);
    }

    public void markItemUnselected(int position) {
        Ensighten.evaluateEvent(this, "markItemUnselected", new Object[]{new Integer(position)});
        this.mAdapter.updateQuantity(position, 0);
        this.mAddToCartButton.setEnabled(this.mAdapter.hasProductSelected());
    }

    public void updateItemQuantity(int position, int quantity) {
        Ensighten.evaluateEvent(this, "updateItemQuantity", new Object[]{new Integer(position), new Integer(quantity)});
        this.mAdapter.updateQuantity(position, quantity);
    }

    public void showCart() {
        Ensighten.evaluateEvent(this, "showCart", null);
        setResult(-1);
        finish();
    }

    public void dismiss() {
        Ensighten.evaluateEvent(this, "dismiss", null);
        setResult(0);
        finish();
    }

    public void onItemClicked(int position) {
        Ensighten.evaluateEvent(this, "onItemClicked", new Object[]{new Integer(position)});
        this.mPresenter.toggleSelection(position);
    }

    public void onPlusButtonClicked(int position) {
        Ensighten.evaluateEvent(this, "onPlusButtonClicked", new Object[]{new Integer(position)});
        this.mPresenter.increaseItemQuantity(position);
    }

    public void onMinusButtonClicked(int position) {
        Ensighten.evaluateEvent(this, "onMinusButtonClicked", new Object[]{new Integer(position)});
        this.mPresenter.decreaseItemQuantity(position);
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        int id = v.getId();
        if (id == C2358R.C2357id.cancel_button) {
            OrderManager.getInstance().getCurrentOrder().setShowUpsell(false);
            dismiss();
        } else if (id == C2358R.C2357id.add_to_cart_button) {
            OrderManager.getInstance().getCurrentOrder().setShowUpsell(false);
            this.mPresenter.addItemsToBasket();
        }
    }
}
