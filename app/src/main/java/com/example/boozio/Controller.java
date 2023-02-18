package com.example.boozio;

import java.util.ArrayList;

public class Controller {
    private static Controller instance;

    private final ArrayList<Ingredient> selectedIngredients = new ArrayList<>();

    private ArrayList<Recipe> recipesFound = new ArrayList<>();

    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }

    public ArrayList<Ingredient> getSpiritIngredients(){
        return DataAccessLayer.getInstance().getSpiritIngredients();
    }

    public ArrayList<Ingredient> getFruitsHerbsIngredients(){
        return DataAccessLayer.getInstance().getFruitsHerbsIngredientsIngredients();
    }

    public ArrayList<Ingredient> getJuiceIngredients(){
        return DataAccessLayer.getInstance().getJuiceIngredients();
    }

    public ArrayList<Ingredient> getSodaIngredients(){
        return DataAccessLayer.getInstance().getSodaIngredients();
    }

    public ArrayList<Ingredient> getSyrupsIngredients(){
        return DataAccessLayer.getInstance().getSyrupsIngredients();
    }

    public ArrayList<Recipe> getRecipes(){
        return DataAccessLayer.getInstance().getRecipes();
    }

    public ArrayList<Ingredient> getSelectedIngredients() {
        return selectedIngredients;
    }

    public ArrayList<Recipe> getRecipesFound() {
        return recipesFound;
    }

    public void setRecipesFound(ArrayList<Recipe> recipesFound) {
        this.recipesFound = recipesFound;
    }

    public String getIngredientType(String ingredientName){
        return DataAccessLayer.getInstance().getIngredientType(ingredientName);
    }
}
