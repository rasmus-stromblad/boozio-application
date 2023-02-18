package com.example.boozio;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;

public class DataAccessLayer {
    private static DataAccessLayer instance;

    private ArrayList<Ingredient> spiritIngredients;
    private ArrayList<Ingredient> fruitIngredients;
    private ArrayList<Ingredient> juiceIngredients;
    private ArrayList<Ingredient> sodaIngredients;
    private ArrayList<Ingredient> syrupsIngredients;
    private ArrayList<ArrayList<Ingredient>> allIngredients;

    private ArrayList<Recipe> recipes;

    public static DataAccessLayer getInstance(){
        if(instance == null){
            instance = new DataAccessLayer();
        }
        return instance;
    }

    public DataAccessLayer(){
        getData();
    }

    public void getData(){
        // Connect the database
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Arraylists to hold the different ingredient types
        spiritIngredients = new ArrayList<>();
        fruitIngredients = new ArrayList<>();
        juiceIngredients = new ArrayList<>();
        sodaIngredients = new ArrayList<>();
        syrupsIngredients = new ArrayList<>();

        // Arraylist to hold the recipes
        recipes = new ArrayList<>();

        // Get all ingredients
        db.collection("ingredients")
                .orderBy("name", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot document : task.getResult()){

                            Ingredient ingredient = document.toObject(Ingredient.class);

                            // Add ingredients by their specific types
                            switch(ingredient.getType()){
                                case "spirit":
                                    spiritIngredients.add(ingredient);
                                    break;
                                case "fruit_herbs":
                                    fruitIngredients.add(ingredient);
                                    break;
                                case "juice":
                                    juiceIngredients.add(ingredient);
                                    break;
                                case "soda":
                                    sodaIngredients.add(ingredient);
                                    break;
                                case "syrup":
                                    syrupsIngredients.add(ingredient);
                                    break;
                            }
                        }
                    } else{
                    }
                });

        db.collection("recipes")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot document : task.getResult()){
                            Recipe recipe = document.toObject(Recipe.class);

                            recipes.add(recipe);
                        }
                    } else{
                    }
                });


        allIngredients = new ArrayList<>();
        allIngredients.add(spiritIngredients);
        allIngredients.add(fruitIngredients);
        allIngredients.add(sodaIngredients);
        allIngredients.add(syrupsIngredients);
        allIngredients.add(juiceIngredients);
    }

    public ArrayList<Ingredient> getSpiritIngredients() {
        return spiritIngredients;
    }

    public ArrayList<Ingredient> getFruitsHerbsIngredientsIngredients() {
        return fruitIngredients;
    }

    public ArrayList<Ingredient> getJuiceIngredients() {
        return juiceIngredients;
    }

    public ArrayList<Ingredient> getSodaIngredients() {
        return sodaIngredients;
    }

    public ArrayList<Ingredient> getSyrupsIngredients() {
        return syrupsIngredients;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public String getIngredientType(String ingredientName){
        String ingredientType = "";

        for(int i = 0; i < allIngredients.size(); i++){
            for (Ingredient ingredient: allIngredients.get(i)) {
                if(ingredient.getName().equals(ingredientName)){
                    return ingredient.getType();
                }
            }
        }

        return ingredientType;
    }
}
