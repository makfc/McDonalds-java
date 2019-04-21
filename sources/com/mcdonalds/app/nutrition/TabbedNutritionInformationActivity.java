package com.mcdonalds.app.nutrition;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.p000v4.view.ViewPager;
import android.support.p001v7.app.ActionBar;
import android.support.p001v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.ensighten.model.EnsightenGestureRecognizerFactory;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Allergen;
import com.mcdonalds.sdk.modules.models.Nutrient;
import com.mcdonalds.sdk.modules.models.RecipeComponent;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.List;

@Instrumented
public class TabbedNutritionInformationActivity extends AppCompatActivity implements NutritionInformationView, TraceFieldInterface {
    public Trace _nr_trace;
    private TabbedNutritionInformationPagerAdapter mPagerAdapter;
    private NutritionInformationPresenter mPresenter;

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

    public void onCreate(@Nullable Bundle bundle) {
        TraceMachine.startTracing("TabbedNutritionInformationActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "TabbedNutritionInformationActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "TabbedNutritionInformationActivity#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        setContentView((int) C2658R.layout.activity_tabbed_nutrition_information);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setCustomView((int) C2658R.layout.action_bar_auto_fit_custom_view);
            ((TextView) actionBar.getCustomView()).setText(C2658R.string.title_activity_nutrition_info);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mPresenter = new NutritionInformationPresenter(this, this, extras.getString("recipe_id"));
        }
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public void displayRecipeInformation(String recipeName, String description, List<Nutrient> nutrients, List<Allergen> allergens, List<String> componentAllergens, List<RecipeComponent> ingredients, List<String> footers, String recipeComponentsString) {
        Ensighten.evaluateEvent(this, "displayRecipeInformation", new Object[]{recipeName, description, nutrients, allergens, componentAllergens, ingredients, footers, recipeComponentsString});
        ((TextView) getSupportActionBar().getCustomView()).setText(recipeName);
        this.mPresenter.loadProductImage((ImageView) findViewById(2131820643));
        TextView descriptionView = (TextView) findViewById(C2358R.C2357id.description);
        if (description != null) {
            descriptionView.setText(Html.fromHtml(description));
        }
        this.mPagerAdapter = new TabbedNutritionInformationPagerAdapter(this, this.mPresenter, nutrients, allergens, componentAllergens, ingredients, footers, recipeComponentsString);
        ViewPager pager = (ViewPager) findViewById(C2358R.C2357id.pager);
        pager.setAdapter(this.mPagerAdapter);
        ((TabLayout) findViewById(C2358R.C2357id.tabs)).setupWithViewPager(pager);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            default:
                return false;
        }
    }

    public void onBackPressed() {
        Ensighten.evaluateEvent(this, "onBackPressed", null);
        if (VERSION.SDK_INT >= 21) {
            finishAfterTransition();
        } else {
            finish();
        }
    }
}
