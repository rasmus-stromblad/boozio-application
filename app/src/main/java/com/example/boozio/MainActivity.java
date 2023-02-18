package com.example.boozio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SelectorRecyclerAdapter.OnIngredientListener{

    private TabLayout tabLayout;

    private ViewPager2 viewPager;

    private FragmentAdapter fragmentAdapter;
    private FragmentManager fragmentManager;

    // Array lists to contain all ingredients
    private ArrayList<Ingredient> spiritIngredients;
    private ArrayList<Ingredient> fruitIngredients;
    private ArrayList<Ingredient> juiceIngredients;
    private ArrayList<Ingredient> sodaIngredients;
    private ArrayList<Ingredient> syrupsIngredients;

    // Dialogs (pop ups)
    private Dialog spiritsDialog;
    private Dialog fruitDialog;
    private Dialog juiceDialog;
    private Dialog sodaDialog;
    private Dialog syrupsDialog;

    // Recycler views to the dialogs (pop ups)
    private RecyclerView spiritsRecyclerView;
    private RecyclerView fruitRecyclerView;
    private RecyclerView juiceRecyclerView;
    private RecyclerView sodaRecyclerView;
    private RecyclerView syrupsRecyclerView;

    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*MobileAds.initialize(this, initializationStatus -> {
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

         */

        // Initialize views
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // Initialize dialogs
        spiritsDialog = new Dialog(this);
        fruitDialog = new Dialog(this);
        juiceDialog = new Dialog(this);
        sodaDialog = new Dialog(this);
        syrupsDialog = new Dialog(this);

        // Set the tab layout tab names
        tabLayout.addTab(tabLayout.newTab().setText("Ingredients"));
        tabLayout.addTab(tabLayout.newTab().setText("Cocktails"));

        // Create the fragment manager to manage the FragmentSelection and the FragmentResults
        fragmentManager = getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapter(fragmentManager, getLifecycle());
        viewPager.setAdapter(fragmentAdapter);

        // Set the content view (what to show) for each of the dialogs (pop ups)
        spiritsDialog.setContentView(R.layout.dialog_spirits_selector);
        fruitDialog.setContentView(R.layout.dialog_fruit_selector);
        juiceDialog.setContentView(R.layout.dialog_juice_selector);
        sodaDialog.setContentView(R.layout.dialog_soda_selector);
        syrupsDialog.setContentView(R.layout.dialog_syrups_selector);

        // Initialize recycler views for the dialogs
        spiritsRecyclerView = spiritsDialog.findViewById(R.id.sodaRecyclerView);
        fruitRecyclerView = fruitDialog.findViewById(R.id.sodaRecyclerView);
        juiceRecyclerView = juiceDialog.findViewById(R.id.sodaRecyclerView);
        sodaRecyclerView = sodaDialog.findViewById(R.id.sodaRecyclerView);
        syrupsRecyclerView = syrupsDialog.findViewById(R.id.sodaRecyclerView);


        // Listener for the tab layout
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Set the view pager to display the correct tab
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        // Get all the ingredients by type
        spiritIngredients = Controller.getInstance().getSpiritIngredients();
        fruitIngredients = Controller.getInstance().getFruitsHerbsIngredients();
        juiceIngredients = Controller.getInstance().getJuiceIngredients();
        sodaIngredients = Controller.getInstance().getSodaIngredients();
        syrupsIngredients = Controller.getInstance().getSyrupsIngredients();

        // Set adapters for the recycler views in the dialogs
        setRecyclerAdapters();
    }

    // Show the dialogs holding ingredients
    public void showIngredients(View view){
        switch (view.getId()){
            case R.id.spiritsSelectorButton:
                spiritsDialog.show();
                break;
            case R.id.sodaSelectorButton:
                sodaDialog.show();
                break;
            case R.id.juiceSelectorButton:
                juiceDialog.show();
                break;
            case R.id.fruitsSelectorButton:
                fruitDialog.show();
                break;
            case R.id.syrupsSelectorButton:
                syrupsDialog.show();
                break;
        }
    }

    private void setRecyclerAdapters(){

        // Set what data the different recycler views shall display
        SelectorRecyclerAdapter spiritsAdapter = new SelectorRecyclerAdapter(spiritIngredients, this);
        spiritsRecyclerView.setAdapter(spiritsAdapter);
        RecyclerView.LayoutManager spiritsLayoutManager = new LinearLayoutManager(getApplicationContext());
        spiritsRecyclerView.setLayoutManager(spiritsLayoutManager);
        spiritsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        SelectorRecyclerAdapter fruitHerbsAdapter = new SelectorRecyclerAdapter(fruitIngredients, this);
        RecyclerView.LayoutManager fruitHerbsLayoutManager = new LinearLayoutManager(getApplicationContext());
        fruitRecyclerView.setLayoutManager(fruitHerbsLayoutManager);
        fruitRecyclerView.setItemAnimator(new DefaultItemAnimator());
        fruitRecyclerView.setAdapter(fruitHerbsAdapter);

        SelectorRecyclerAdapter juiceAdapter = new SelectorRecyclerAdapter(juiceIngredients, this);
        RecyclerView.LayoutManager juiceLayoutManager = new LinearLayoutManager(getApplicationContext());
        juiceRecyclerView.setLayoutManager(juiceLayoutManager);
        juiceRecyclerView.setItemAnimator(new DefaultItemAnimator());
        juiceRecyclerView.setAdapter(juiceAdapter);

        SelectorRecyclerAdapter sodaAdapter = new SelectorRecyclerAdapter(sodaIngredients, this);
        RecyclerView.LayoutManager sodaLayoutManager = new LinearLayoutManager(getApplicationContext());
        sodaRecyclerView.setLayoutManager(sodaLayoutManager);
        sodaRecyclerView.setItemAnimator(new DefaultItemAnimator());
        sodaRecyclerView.setAdapter(sodaAdapter);

        SelectorRecyclerAdapter syrupsAdapter = new SelectorRecyclerAdapter(syrupsIngredients, this);
        RecyclerView.LayoutManager syrupsLayoutManager = new LinearLayoutManager(getApplicationContext());
        syrupsRecyclerView.setLayoutManager(syrupsLayoutManager);
        syrupsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        syrupsRecyclerView.setAdapter(syrupsAdapter);
    }

    @Override
    public void onIngredientClick(int position, View view, ArrayList<Ingredient> ingredientList) {

        if(Controller.getInstance().getSelectedIngredients().contains(ingredientList.get(position))){
            Controller.getInstance().getSelectedIngredients().remove(ingredientList.get(position));
        } else{
            Controller.getInstance().getSelectedIngredients().add(ingredientList.get(position));
        }

        // Contain all recipes found by the selected ingredients
        ArrayList<Recipe> recipesFound = new ArrayList<>();

        // Contain all selected ingredient names
        ArrayList<String> selectedIngredientNames = new ArrayList<>();

        for (Ingredient ingredient : Controller.getInstance().getSelectedIngredients()) {
            selectedIngredientNames.add(ingredient.getName());
        }

        for (Recipe recipe : Controller.getInstance().getRecipes()) {
            // If the selected ingredients list contains all of the ingredients for the current recipe
            boolean containsAll = selectedIngredientNames.containsAll(recipe.getIngredients().keySet());

            if(containsAll){
                recipesFound.add(recipe);
            }
        }
        Controller.getInstance().setRecipesFound(recipesFound);

        // Update text views in fragment
        SelectionFragment f = (SelectionFragment) getSupportFragmentManager().getFragments().get(0);
        f.updateNbrOfRecipesFound();
    }
}