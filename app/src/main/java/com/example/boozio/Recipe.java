package com.example.boozio;

import java.util.HashMap;

public class Recipe {
    private String name;
    private String description;
    private HashMap<String, String> ingredients;
    private String image_url;

    public Recipe(){}

    public Recipe(String _name, String _description, HashMap<String, String> _ingredients, String image_url){
        this.name = _name;
        this.description = _description;
        this.ingredients = _ingredients;
        this.image_url = image_url;
    }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public HashMap<String, String> getIngredients() { return ingredients; }

    public String getImage_url() { return image_url; }
}
