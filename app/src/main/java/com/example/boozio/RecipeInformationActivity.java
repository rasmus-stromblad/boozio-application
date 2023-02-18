package com.example.boozio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class RecipeInformationActivity extends AppCompatActivity {

    private ImageView recipeImageView;
    private TextView recipeNameTextView;
    private TextView recipeDescTextView;
    private RecyclerView recipeDescRecyclerView;

    private Intent intent;

    private DividerItemDecorator itemDecorator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_information);

        intent = getIntent();

        recipeImageView = findViewById(R.id.recipeInfoImageView);
        recipeNameTextView = findViewById(R.id.recipeNameTextView);
        recipeDescTextView = findViewById(R.id.recipeDescTextView);
        recipeDescRecyclerView = findViewById(R.id.recipeDescRecyclerView);

        // Get the sent position from the clicked recipe row
        int recipe_position = intent.getIntExtra("recipe_index", 0);

        // Get the recipe on the clicked row
        Recipe found_recipe = Controller.getInstance().getRecipesFound().get(recipe_position);

        // Get information for the current recipe
        HashMap<String, String> ingredients = found_recipe.getIngredients();
        String recipeDescription = found_recipe.getDescription();

        // Set what data the different recycler views shall display
        RecipeInfoRecyclerAdapter recipeInfoAdapter = new RecipeInfoRecyclerAdapter(ingredients);
        RecyclerView.LayoutManager spiritsLayoutManager = new LinearLayoutManager(this);
        recipeDescRecyclerView.setLayoutManager(spiritsLayoutManager);
        recipeDescRecyclerView.setItemAnimator(new DefaultItemAnimator());
        itemDecorator = new DividerItemDecorator(30, 0);
        recipeDescRecyclerView.addItemDecoration(itemDecorator);
        recipeDescRecyclerView.setAdapter(recipeInfoAdapter);

        // Set the text for the text views
        recipeNameTextView.setText(found_recipe.getName());
        recipeDescTextView.setText(recipeDescription);

        if(found_recipe.getImage_url() != null){
            Picasso.get().load(found_recipe.getImage_url()).into(recipeImageView);
        }

    }
}